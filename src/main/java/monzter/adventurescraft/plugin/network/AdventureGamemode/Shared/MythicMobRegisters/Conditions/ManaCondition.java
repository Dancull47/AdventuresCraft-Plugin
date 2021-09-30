package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.MythicMobRegisters.Conditions;

import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
import io.lumine.xikage.mythicmobs.skills.SkillCondition;
import io.lumine.xikage.mythicmobs.skills.conditions.IEntityCondition;
import net.Indyuce.mmoitems.api.player.PlayerData;
import net.Indyuce.mmoitems.api.player.RPGPlayer;
import net.Indyuce.mmoitems.comp.mmocore.MMOCoreHook;
import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class ManaCondition extends SkillCondition implements IEntityCondition {

    final double manaAmount;

    public ManaCondition(MythicLineConfig config) {
        super(config.getLine());
        this.manaAmount = config.getInteger(new String[]{"mana", "m", "a", "amount"}, 0);
    }

    @Override
    public boolean check(AbstractEntity target) {
        LivingEntity bukkitTarget = (LivingEntity) BukkitAdapter.adapt(target);
        RPGPlayer mmoCoreRPGPlayer = new MMOCoreHook.MMOCoreRPGPlayer(PlayerData.get(bukkitTarget.getUniqueId()));
        double mana = mmoCoreRPGPlayer.getMana();

        if (!manaCheck((Player) bukkitTarget, mana))
            return false;

        mmoCoreRPGPlayer.setMana(mana - manaAmount);
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
}
