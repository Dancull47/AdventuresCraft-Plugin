package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.MythicMobRegisters.Conditions;

import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
import io.lumine.xikage.mythicmobs.skills.SkillCondition;
import io.lumine.xikage.mythicmobs.skills.conditions.IEntityCondition;
import monzter.adventurescraft.plugin.utilities.general.Cooldown;
import net.Indyuce.mmoitems.ItemStats;
import net.Indyuce.mmoitems.api.player.PlayerData;
import net.Indyuce.mmoitems.api.player.RPGPlayer;
import net.Indyuce.mmoitems.comp.mmocore.MMOCoreHook;
import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class SkillCastCondition extends SkillCondition implements IEntityCondition {

    final double manaAmount;
    final long cooldownSeconds;
    final String skillName;

    public SkillCastCondition(MythicLineConfig config) {
        super(config.getLine());

        this.manaAmount = config.getInteger(new String[]{"mana", "m"}, 0);
        this.cooldownSeconds = config.getInteger(new String[]{"cooldown", "cd", "c"}, 1);
        this.skillName = config.getString(new String[]{"skill", "skillName", "s", "n", "name"}, "DEFAULT");
    }

    @Override
    public boolean check(AbstractEntity target) {
        LivingEntity bukkitTarget = (LivingEntity) BukkitAdapter.adapt(target);
        if (!Cooldown.isInCooldown(bukkitTarget.getUniqueId(), "cast")) {
            RPGPlayer mmoCoreRPGPlayer = new MMOCoreHook.MMOCoreRPGPlayer(PlayerData.get(bukkitTarget.getUniqueId()));
            double mana = mmoCoreRPGPlayer.getMana();
            double cooldownReduction = mmoCoreRPGPlayer.getPlayerData().getStats().getStat(ItemStats.COOLDOWN_REDUCTION);

            if (!manaCheck((Player) bukkitTarget, mana) || !cooldownCheck((Player) bukkitTarget, skillName))
                return false;

            mmoCoreRPGPlayer.setMana(mana - manaAmount);
            cooldownReduction = cooldownReduction / 100;
            double cooldownTotal = cooldownSeconds - (cooldownSeconds * cooldownReduction);
            if (cooldownTotal < 0)
                cooldownTotal = 0;
            Cooldown skillCooldown = new Cooldown(bukkitTarget.getUniqueId(), skillName, cooldownTotal);
            skillCooldown.start();
//        castCooldown is used to fix MM's broken double call upon returning true
            Cooldown castCooldown = new Cooldown(bukkitTarget.getUniqueId(), "cast", .01);
            castCooldown.start();
            return true;
        }
        return true;
    }

    public boolean manaCheck(Player player, double currentMana) {
        if (currentMana < manaAmount) {
            player.sendMessage(ChatColor.RED + "You don't have enough mana! ("
                    + ChatColor.GOLD + currentMana + ChatColor.RED + "/" + ChatColor.GOLD + manaAmount + ChatColor.RED + ")");
            return false;
        }
        return true;
    }

    public boolean cooldownCheck(Player player, String skillName) {
        if (Cooldown.isInCooldown(player.getUniqueId(), skillName)) {
            String timeLeft = Cooldown.getTimeLeftTrimmed(player.getUniqueId(), skillName);
            player.sendMessage(ChatColor.RED + "You must wait " + ChatColor.GOLD + timeLeft + ChatColor.RED + " seconds before using this skill again!");
            return false;
        }
        return true;
    }


}
