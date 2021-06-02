package monzter.adventurescraft.plugin.network.PrisonGamemode.shared.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.Dependency;
import io.lumine.mythicenchants.MythicEnchants;
import me.clip.placeholderapi.PlaceholderAPI;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.beton.BetonPointsManager;
import monzter.adventurescraft.plugin.utilities.enchanting.CalculateEnchantments;
import monzter.adventurescraft.plugin.utilities.enums.Enchantments;
import monzter.adventurescraft.plugin.utilities.enums.StatsDisplay;
import monzter.adventurescraft.plugin.utilities.general.ConsoleCommand;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import monzter.adventurescraft.plugin.utilities.text.NumberFormat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Objects;

public class Enchanting extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final NumberFormat numberFormat;
    private final SoundManager soundManager;
    private final ConsoleCommand consoleCommand;
    private final MythicEnchants mythicEnchants;
    private final BetonPointsManager betonPointsManager;
    private final CalculateEnchantments calculateEnchantments;


    public Enchanting(AdventuresCraft plugin, NumberFormat numberFormat, SoundManager soundManager, ConsoleCommand consoleCommand, MythicEnchants mythicEnchants, BetonPointsManager betonPointsManager, CalculateEnchantments calculateEnchantments) {
        this.plugin = plugin;
        this.numberFormat = numberFormat;
        this.soundManager = soundManager;
        this.consoleCommand = consoleCommand;
        this.mythicEnchants = Objects.requireNonNull(mythicEnchants);
        this.betonPointsManager = betonPointsManager;
        this.calculateEnchantments = calculateEnchantments;
    }

    @CommandAlias("enchants|enchant|enchantment")
    @CommandCompletion("Experience|Pet_Experience|Luck|Explosive|Explosive_Chance|Randomizer|Treasurer|Midas_Touch|Stat_Tracker")
    private void enchant(Player player, String enchantment) {
        for (Enchantments enchantments : Enchantments.values()) {
            if (enchantments.getName().equals(enchantment.replace("_"," "))) {
                if (!isMaxLevel(player, enchantment, enchantments.getMaxLevel())) {
                    int price = (calculateEnchantments.calculateEnchantments(player, enchantment.replace("_", " ")) * enchantments.getPrice());
                    if (enoughPoints(player, enchantment, calculateEnchantments.calculateEnchantments(player, enchantment.replace("_", " ")), price)) {
                        increaseEnchantment(player, enchantment, calculateEnchantments.calculateEnchantments(player, enchantment.replace("_", " ")), price);
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
        if (calculateEnchantments.calculateEnchantments(player, enchantment.replace("_", " ")) >= enchantmentMaxLevel) {
            player.sendMessage(ChatColor.RED + "You have reached the max level on this tool for " + ChatColor.DARK_PURPLE + enchantment.replace("_", " ") + ChatColor.RED + "!");
            soundManager.soundNo(player, 1);
            return true;
        }
        return false;
    }
}

