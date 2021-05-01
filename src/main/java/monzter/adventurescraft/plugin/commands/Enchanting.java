package monzter.adventurescraft.plugin.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Dependency;
import co.aikar.commands.annotation.Optional;
import me.clip.placeholderapi.PlaceholderAPI;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.event.extras.StatsDisplay;
import monzter.adventurescraft.plugin.utilities.BukkitConsoleCommand;
import monzter.adventurescraft.plugin.utilities.ConsoleCommand;
import monzter.adventurescraft.plugin.utilities.NumberFormat;
import monzter.adventurescraft.plugin.utilities.SoundManager;
import org.bukkit.*;
import org.bukkit.entity.Player;

public class Enchanting extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final NumberFormat numberFormat;
    private final SoundManager soundManager;
    private final ConsoleCommand consoleCommand;

    public Enchanting(AdventuresCraft plugin, NumberFormat numberFormat, SoundManager soundManager, ConsoleCommand consoleCommand) {
        this.plugin = plugin;
        this.numberFormat = numberFormat;
        this.soundManager = soundManager;
        this.consoleCommand = consoleCommand;
    }

    @CommandAlias("enchants")
    private final void enchant(Player player, String enchantment, @Optional String upgradeAmount) {
        final String enchantmentPlaceholder = "%ac_Enchantment_" + enchantment + "%";
        if (upgradeAmount == null){
            if (enoughPoints(player, enchantmentPlaceholder)) {
                increaseEnchantment(player, enchantment, Integer.valueOf(PlaceholderAPI.setPlaceholders(player, enchantmentPlaceholder)));
            }
//        } else if (Integer.valueOf(upgradeAmount) > 0){
//            for (int i = 0; i < Integer.valueOf(upgradeAmount); i++){
//                if (enoughPoints(player, enchantmentPlaceholder)) {
//                    increaseEnchantment(player, enchantment, Integer.valueOf(PlaceholderAPI.setPlaceholders(player, enchantmentPlaceholder)), i);
//                } else {
//                    break;
//                }
//            }
        }
    }

    private final boolean enoughPoints(Player player, String enchantment) {
        final String exp = PlaceholderAPI.setPlaceholders(player, "%ac_Stat_EXPAmount%");
        String cost = PlaceholderAPI.setPlaceholders(player, enchantment);
        cost = String.valueOf((Integer.valueOf(cost)+1) * 3500);
        if (Integer.valueOf(exp) < Integer.valueOf(cost)) {
            player.sendMessage(ChatColor.RED + "This enchantment costs " + ChatColor.GOLD + numberFormat.numberFormat(Integer.valueOf(cost)) + " " + StatsDisplay.EXPERIENCE_AMOUNT.getName() + ChatColor.RED
                    + " and you only have " + ChatColor.GOLD + numberFormat.numberFormat(Integer.valueOf(exp)) + " " + StatsDisplay.EXPERIENCE_AMOUNT.getName() + ChatColor.RED + "!");
            soundManager.soundNo(player, 1);
            return false;
        }
        soundManager.playSound(player, Sound.BLOCK_ENCHANTMENT_TABLE_USE, 1, 1);
        return true;
    }

    public void increaseEnchantment(Player player, String enchantment, int enchantmentPlaceholder) {
        final boolean previousOp = player.isOp();
        try {
            player.setOp(true);
            player.performCommand("mythicenchants enchant " + enchantment.toUpperCase() + " " + Integer.valueOf(enchantmentPlaceholder+1));
        } finally {
            player.setOp(previousOp);
        }
        consoleCommand.consoleCommand("q point " + player.getName() + " add items.Experience -" + Integer.valueOf((enchantmentPlaceholder)*3500));
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> {
            String exp = PlaceholderAPI.setPlaceholders(player, "%ac_Stat_EXPAmount_formatted%");
            player.sendMessage(ChatColor.GREEN.toString() + "You now have " + ChatColor.GOLD + exp + " " + StatsDisplay.EXPERIENCE_AMOUNT.getName() + ChatColor.GREEN + " left!");
        }, 5L);
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
}

