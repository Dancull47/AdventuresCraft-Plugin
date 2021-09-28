package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.MythicMobRegisters;

import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
import io.lumine.xikage.mythicmobs.skills.SkillCondition;
import io.lumine.xikage.mythicmobs.skills.conditions.IEntityCondition;
import monzter.adventurescraft.plugin.utilities.general.CooldownOld;
import net.Indyuce.mmoitems.comp.mmocore.MMOCoreHook;
import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class ManaCondition2 extends SkillCondition implements IEntityCondition {

    final double manaAmount;
    final long cooldownSeconds;
    final String skillName;
    public HashMap<String, Long> cooldowns = new HashMap<>();

    public ManaCondition2(MythicLineConfig config) {
        super(config.getLine());

        this.manaAmount = config.getInteger(new String[]{"mana", "m"}, 0);
        this.cooldownSeconds = config.getInteger(new String[]{"cooldown", "cd", "c"}, 1);
        this.skillName = config.getString(new String[]{"skill", "skillName", "s", "n", "name"}, "DEFAULT");
    }

//    Issue, seems like it gets called twice, but not sure why. I think it has something to do with the checks.
//    Maybe make the Checks within the method not individually

    @Override
    public boolean check(AbstractEntity target) {
        System.out.println("called");
        LivingEntity bukkitTarget = (LivingEntity) BukkitAdapter.adapt(target);
        System.out.println(ChatColor.RED.toString() + Thread.currentThread().getStackTrace()[2]);

//        MMOCoreHook.MMOCoreRPGPlayer mmoCoreRPGPlayer = new MMOCoreHook.MMOCoreRPGPlayer(PlayerData.get(bukkitTarget.getUniqueId()));
//        double mana = mmoCoreRPGPlayer.getMana();
//        double cooldownReduction = mmoCoreRPGPlayer.getData().getStats().getStat(StatType.COOLDOWN_REDUCTION);
//        System.out.println("b4");
//        if (mana < manaAmount) {
//            bukkitTarget.sendMessage(ChatColor.RED + "You don't have enough mana! ("
//                    + ChatColor.GOLD + mana + ChatColor.RED + "/" + ChatColor.GOLD + manaAmount + ChatColor.RED + ")");
//            System.out.println("Returned False MANA");
//            return false;
//        }
//        if (cooldowns.containsKey(target.getUniqueId().toString())) {
//            long secondsLeft = ((cooldowns.get(target.getUniqueId().toString()) / 1000) + cooldownSeconds) - (System.currentTimeMillis() / 1000);
//            if (secondsLeft > 0) {
//                bukkitTarget.sendMessage(ChatColor.RED + "You must wait " + ChatColor.GOLD + secondsLeft + ChatColor.RED + " seconds before using this skill again!");
//                System.out.println("Returned False COOLDOWN");
//                return false;
//            }
//        }
//        cooldowns.put(target.getUniqueId().toString(), System.currentTimeMillis());
//        mmoCoreRPGPlayer.setMana(mana - manaAmount);
//        cooldownReduction = cooldownReduction / 100;
//        double cooldownTotal = cooldownSeconds - (cooldownSeconds * cooldownReduction);
        return true;
    }

//    @Override
//    public boolean check(AbstractEntity target) {
//        System.out.println("called");
//        LivingEntity bukkitTarget = (LivingEntity) BukkitAdapter.adapt(target);
//        MMOCoreHook.MMOCoreRPGPlayer mmoCoreRPGPlayer = new MMOCoreHook.MMOCoreRPGPlayer(PlayerData.get(bukkitTarget.getUniqueId()));
//        double mana = mmoCoreRPGPlayer.getMana();
//        double cooldownReduction = mmoCoreRPGPlayer.getData().getStats().getStat(StatType.COOLDOWN_REDUCTION);
//        System.out.println("b4");
//        if (!manaCheck((Player) bukkitTarget, mana, mmoCoreRPGPlayer) || !cooldownCheck((Player) bukkitTarget, cooldownReduction, skillName)) {
//            System.out.println("FALSE");
//            return false;
//        }
//
//        mmoCoreRPGPlayer.setMana(mana - manaAmount);
//        cooldownReduction = cooldownReduction / 100;
//        double cooldownTotal = cooldownSeconds - (cooldownSeconds * cooldownReduction);
//        if (cooldownTotal < 0)
//            cooldownTotal = 0;
//        CooldownOld cooldown = new CooldownOld(bukkitTarget.getUniqueId(), skillName, cooldownTotal);
//        cooldown.start();
//        return true;
//    }
//
    //    @Override
//    public boolean check(AbstractEntity target) {
//        System.out.println("called");
//        LivingEntity bukkitTarget = (LivingEntity) BukkitAdapter.adapt(target);
//        MMOCoreHook.MMOCoreRPGPlayer mmoCoreRPGPlayer = new MMOCoreHook.MMOCoreRPGPlayer(PlayerData.get(bukkitTarget.getUniqueId()));
//        double mana = mmoCoreRPGPlayer.getMana();
//        double cooldownReduction = mmoCoreRPGPlayer.getData().getStats().getStat(StatType.COOLDOWN_REDUCTION);
//        if (!cooldownCheck((Player) bukkitTarget, cooldownReduction, skillName)) {
//            System.out.println("FALSE");
//            return false;
//        }
//        if (!manaCheck((Player) bukkitTarget, mana, mmoCoreRPGPlayer)) {
//            System.out.println("FALSE");
//            return false;
//        }
//
//        cooldownReduction = cooldownReduction / 100;
//        double cooldownTotal = cooldownSeconds - (cooldownSeconds * cooldownReduction);
//
//        if (cooldownTotal < 0)
//            cooldownTotal = 0;
//
//        CooldownOld cooldown = new CooldownOld(bukkitTarget.getUniqueId(), skillName, cooldownTotal);
//        cooldown.start();
//        mmoCoreRPGPlayer.setMana(mana - manaAmount);
//        System.out.println("TRUE");
//        return true;
//    }
//

    public boolean manaCheck(Player player, double currentMana, MMOCoreHook.MMOCoreRPGPlayer mmoCoreRPGPlayer) {
        if (currentMana < manaAmount) {
            player.sendMessage(ChatColor.RED + "You don't have enough mana! ("
                    + ChatColor.GOLD + currentMana + ChatColor.RED + "/" + ChatColor.GOLD + manaAmount + ChatColor.RED + ")");
            return false;
        }
        return true;
    }

    public boolean cooldownCheck(Player player, double cooldownReductionStat, String skillName) {
        if (CooldownOld.isInCooldown(player.getUniqueId(), skillName)) {
            String timeLeft = CooldownOld.getTimeLeftTrimmed(player.getUniqueId(), skillName);
            player.sendMessage(ChatColor.RED + "You must wait " + ChatColor.GOLD + timeLeft + ChatColor.RED + " seconds before using this skill again!");
            return false;
        }
        return true;
    }


}
