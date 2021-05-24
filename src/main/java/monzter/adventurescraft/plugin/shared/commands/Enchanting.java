package monzter.adventurescraft.plugin.shared.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Dependency;
import io.lumine.mythicenchants.MythicEnchants;
import me.clip.placeholderapi.PlaceholderAPI;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.beton.BetonPointsManager;
import monzter.adventurescraft.plugin.utilities.enums.Enchantments;
import monzter.adventurescraft.plugin.utilities.enums.StatsDisplay;
import monzter.adventurescraft.plugin.utilities.general.ConsoleCommand;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import monzter.adventurescraft.plugin.utilities.text.NumberFormat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.Objects;

public class Enchanting extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final NumberFormat numberFormat;
    private final SoundManager soundManager;
    private final ConsoleCommand consoleCommand;
    private final MythicEnchants mythicEnchants;
    private final BetonPointsManager betonPointsManager;


    public Enchanting(AdventuresCraft plugin, NumberFormat numberFormat, SoundManager soundManager, ConsoleCommand consoleCommand, MythicEnchants mythicEnchants, BetonPointsManager betonPointsManager) {
        this.plugin = plugin;
        this.numberFormat = numberFormat;
        this.soundManager = soundManager;
        this.consoleCommand = consoleCommand;
        this.mythicEnchants = Objects.requireNonNull(mythicEnchants);
        this.betonPointsManager = betonPointsManager;
    }

    @CommandAlias("enchants")
    private void enchant(Player player, String enchantment) {
        for (Enchantments enchantments : Enchantments.values()) {
            if (enchantments.getName().equals(enchantment)) {
                int price = (calculateEnchantments(player, enchantment) * enchantments.getPrice());
                if (!isMaxLevel(player, enchantment, enchantments.getMaxLevel())) {
                    if (enoughPoints(player, enchantment, calculateEnchantments(player, enchantment), price)) {
                        increaseEnchantment(player, enchantment, calculateEnchantments(player, enchantment), price);
                    }
                }
            }
        }
    }

    private void increaseEnchantment(Player player, String enchantment, int enchantmentLevel, int price) {
        final boolean previousOp = player.isOp();
//        mythicEnchants.getEnchantManager().applyEnchantment(player.getInventory().getItemInMainHand(), enchantment.toUpperCase(), 1);
        try {
            player.setOp(true);
            player.performCommand("mythicenchants enchant " + enchantment.toUpperCase() + " " + (enchantmentLevel + 1));
        } finally {
            player.setOp(previousOp);
        }
        betonPointsManager.takePointEXP(player, price);
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> {
            String exp = PlaceholderAPI.setPlaceholders(player, "%ac_Stat_EXPAmount_formatted%");
            player.sendMessage(ChatColor.GREEN + "You now have " + ChatColor.GOLD + exp + " " + StatsDisplay.EXPERIENCE_AMOUNT.getName() + ChatColor.GREEN + " left!");
        }, 5L);
    }

    private boolean enoughPoints(Player player, String enchantment, int enchantmentLevel, double enchantmentPrice) {
        final String exp = PlaceholderAPI.setPlaceholders(player, "%ac_Stat_EXPAmount%");
        if (Integer.valueOf(exp) >= enchantmentPrice) {
            return true;
        }
        player.sendMessage(ChatColor.DARK_PURPLE + enchantment.replace("_", " ") + ChatColor.RED + " level " + ChatColor.GREEN + enchantmentLevel + ChatColor.RED + " costs " + ChatColor.GOLD + numberFormat.numberFormat(enchantmentPrice) + " " + StatsDisplay.EXPERIENCE_AMOUNT.getName() + ChatColor.RED
                + " and you only have " + ChatColor.GOLD + numberFormat.numberFormat(Integer.valueOf(exp)) + " " + StatsDisplay.EXPERIENCE_AMOUNT.getName() + ChatColor.RED + "!");
        soundManager.soundNo(player, 1);
        return false;
    }

    private boolean isMaxLevel(Player player, String enchantment, int enchantmentMaxLevel) {
        if (calculateEnchantments(player, enchantment) >= enchantmentMaxLevel) {
            player.sendMessage(ChatColor.RED + "You have reached the max level on this tool for " + ChatColor.DARK_PURPLE + enchantment.replace("_", " ") + ChatColor.RED + "!");
            soundManager.soundNo(player, 1);
            return true;
        }
        return false;
    }

//    public void increaseEnchantment(Player player, String enchantment, int enchantmentPlaceholder, int upgradeAmount) {
//        final boolean previousOp = player.isOp();
//        try {
//            player.setOp(true);
//            player.performCommand("mythicenchants enchant " + enchantment.toUpperCase() + " " + Integer.valueOf(enchantmentPlaceholder+1));
//        } finally {
//            player.setOp(previousOp);
//        }
//        consoleCommand.consoleCommand("q point " + player.getName() + " add items.Experience -" + Integer.valueOf((enchantmentPlaceholder)*3500));
//        if (upgradeAmount == 1){
//            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> {
//                String exp = PlaceholderAPI.setPlaceholders(player, "%ac_Stat_EXPAmount_formatted%");
//                player.sendMessage(ChatColor.GREEN.toString() + "You now have " + ChatColor.GOLD + exp + " " + StatsDisplay.EXPERIENCE_AMOUNT.getName() + ChatColor.GREEN + " left!");
//            }, 5L);
//        }
//    }


    private int calculateEnchantments(Player player, String enchantment) {
        if (player.getPlayer().getInventory().getItemInMainHand().getItemMeta() != null) {
            Map<org.bukkit.enchantments.Enchantment, Integer> enchantmentMap = player.getPlayer().getInventory().getItemInMainHand().getItemMeta().getEnchants();
            if (!enchantmentMap.isEmpty()) {
                if (enchantmentMap.containsKey(org.bukkit.enchantments.Enchantment.getByName(enchantment))) {
                    return enchantmentMap.get(Enchantment.getByName(enchantment));
                }
            }
        }
        return 0;
    }

}

