package monzter.adventurescraft.plugin.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import co.aikar.commands.bukkit.contexts.OnlinePlayer;
import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import me.clip.placeholderapi.PlaceholderAPI;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.mmoitems.MMOItemsGive;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class AdminCommands extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final MMOItemsGive mmoItemsGive;
    public AdminCommands(AdventuresCraft plugin, MMOItemsGive mmoItemsGive) {
        this.plugin = plugin;
        this.mmoItemsGive = mmoItemsGive;
    }

    @CommandAlias("stat")
    @CommandPermission("*")
    @Description("Check stats of a player")
    @CommandCompletion("* maxweightm|blockm|sellm|luckm|expm|petexpm|maxweight|weight|exp|pets|maxpets|ac|miningpass|all")
    public void statCommand(Player player, OnlinePlayer targetPlayer, String stat) {
        statsCommand(player, stat, targetPlayer.getPlayer());
        mmoItemsGive.giveMMOItem(player, "MATERIAL", "NULL", 1, false);
    }


    @CommandAlias("booster")
    @CommandPermission("*")
    @Description("Check stats of a player")
    public void boosterCommand(String boosterType, String player, int boosterTier, int boosterDuration) {
        Player target = Bukkit.getPlayer(player);
        if (target != null) {
            booster(boosterType.toUpperCase(), target, boosterTier, boosterDuration);
        } else {
            globalBooster(boosterType.toUpperCase(), boosterTier, boosterDuration);
        }
    }

    private final String[] boosterType = new String[]{"SELL", "EXP", "BLOCK", "LUCK"};
    private final int[] boosterTier = new int[]{1, 3, 5, 7, 10};

    @CommandAlias("randomBooster")
    @CommandPermission("*")
    @Description("Check stats of a player")
    public void randomBoosterCommand() {
        final int randomType = new Random().nextInt(boosterType.length);
        final int randomTier = new Random().nextInt(boosterTier.length);
        final int randomDuration = new Random().nextInt(boosterTier.length);
        globalBooster(boosterType[randomType].toUpperCase(), boosterTier[randomTier], boosterTier[randomDuration]);
    }

    private final ItemStack backgroundItem = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
    private final ItemMeta backgroundItemMeta = backgroundItem.getItemMeta();
    private final ItemStack previousPageItem = new ItemStack(Material.ARROW);
    private final ItemMeta previousPageItemMeta = previousPageItem.getItemMeta();
    private final ItemStack nextPageItem = new ItemStack(Material.ARROW);
    private final ItemMeta nextPageItemMeta = nextPageItem.getItemMeta();

    @CommandAlias("reward")
    @CommandPermission("*")
    @Description("Reward stats to a Player")
    @CommandCompletion("petexperience|petexp|experience|exp|miningpass @nothing *")
    public void rewardCommand(String stat, int amount, OnlinePlayer targetPlayer) {
        switch (stat.toLowerCase()) {
            case "petexperience":
            case "petexp":
                targetPlayer.getPlayer().sendMessage(ChatColor.GREEN + "You gained +" + ChatColor.GOLD + amount + ChatColor.GREEN + "x " + ChatColor.AQUA + "❉ Pet Experience" + ChatColor.GREEN + "!");
                Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "q point " + targetPlayer.getPlayer().getName() + " add items.PetExperience " + amount);
                break;
            case "experience":
            case "exp":
                targetPlayer.getPlayer().sendMessage(ChatColor.GREEN + "You gained +" + ChatColor.GOLD + amount + ChatColor.GREEN + "x " + ChatColor.GREEN + "۞ Experience" + ChatColor.GREEN + "!");
                Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "q point " + targetPlayer.getPlayer().getName() + " add items.Experience " + amount);
                break;
            case "miningpass":
                targetPlayer.getPlayer().sendMessage(ChatColor.GREEN + "You gained +" + ChatColor.GOLD + amount + ChatColor.GREEN + "x " + ChatColor.DARK_PURPLE + "♦ Mining Pass Experience" + ChatColor.GREEN + "!");
                Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "q point " + targetPlayer.getPlayer().getName() + " add miningPass.EXP " + amount);
                break;
        }
    }

    @CommandAlias("restarttime")
    @CommandPermission("*")
    @Description("Check time until restart")
    public void restartTime(Player player) {
        String restartTime = PlaceholderAPI.setPlaceholders(player, "%ac_Restart_formatted%");
        String restartTimeSeconds = PlaceholderAPI.setPlaceholders(player, "%ac_Restart%");
        player.sendMessage(ChatColor.GREEN + "There is " + ChatColor.GOLD + restartTime + ChatColor.GREEN + " until restart!");
        player.sendMessage(ChatColor.GOLD + restartTimeSeconds + ChatColor.GREEN + " seconds!");
    }


    private void booster(String boosterType, Player player, int boosterTier, int boosterDuration) {
        double x = player.getLocation().getX();
        double y = player.getLocation().getY();
        double z = player.getLocation().getZ();
        hologram(player);
        Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "lp user " + player.getName() + " permission settemp " + boosterType.toLowerCase() + ".booster." + boosterTier + " true " + boosterDuration + "m");
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_VILLAGER_CELEBRATE, 1f, 2f);
        player.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, x, y + 2, z, 5, .25, .25, .25);
        player.sendMessage(ChatColor.GREEN + "You've just activated a " + ChatColor.YELLOW + boosterTier + "x " + getType(boosterType)
                + ChatColor.GREEN + "for " + ChatColor.YELLOW + boosterDuration + " minutes" + ChatColor.GREEN + "!");
    }

    private void globalBooster(String boosterType, int boosterTier, int boosterDuration) {
        for (Player player : plugin.getServer().getOnlinePlayers()) {
            player.sendTitle(ChatColor.GOLD.toString() + ChatColor.BOLD + "GLOBAL BOOSTER", ChatColor.GREEN.toString() + ChatColor.BOLD + "ACTIVATED!", 10, 70, 20);
            player.sendMessage(ChatColor.GOLD.toString() + boosterTier + "x " + getType(boosterType)
                    + ChatColor.YELLOW + ChatColor.BOLD + "GLOBAL BOOSTER " + ChatColor.GOLD + "has been activated!");
            player.getWorld().playSound(player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LARGE_BLAST, 1f, 1f);
            player.getWorld().playSound(player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LARGE_BLAST_FAR, 1f, 1f);
            booster(boosterType, player, boosterTier, boosterDuration);
        }
    }

    private void hologram(Player player) {
        final Hologram hologram = HologramsAPI.createHologram(plugin, player.getLocation().add(0.0, 2.0, 0.0));
        hologram.appendTextLine(ChatColor.GREEN.toString() + ChatColor.BOLD.toString() + "BOOSTER ACTIVATED!");
        new BukkitRunnable() {
            int ticks;

            @Override
            public void run() {
                ticks++;
                hologram.teleport(player.getLocation().add(0.0, 2.0, 0.0));
                if (ticks > 60) {
                    hologram.delete();
                    cancel();
                }
            }
        }.runTaskTimer(plugin, 1L, 1L);
    }

    private String getType(String boosterType) {
        if (boosterType.contains("SELL")) {
            return ChatColor.DARK_GREEN + "⛂ Sell Booster ";
        } else if (boosterType.contains("EXP")) {
            return ChatColor.GREEN + "۞ EXP Booster ";
        } else if (boosterType.contains("PET_EXP")) {
            return ChatColor.AQUA + "❉ Pet EXP Booster ";
        } else if (boosterType.contains("BLOCK")) {
            return ChatColor.DARK_RED + "回 Block Multiplier Booster ";
        } else if (boosterType.contains("LUCK")) {
            return ChatColor.YELLOW + "⚅ Luck Multiplier Booster ";
        }
        return ChatColor.RED + "Unknown, report to Admin!";
    }


    private void statsCommand(Player player, String stat, Player targetPlayer) {
        switch (stat) {
            case "maxweightm":
                String statValue = PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_MaxWeightMultiplier%");
                checkStat(player, targetPlayer, stat, statValue);
                break;
            case "blockm":
                statValue = PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_BlockMultiplier%");
                checkStat(player, targetPlayer, stat, statValue);
                break;
            case "sellm":
                statValue = PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_SellMultiplier%");
                checkStat(player, targetPlayer, stat, statValue);
                break;
            case "luckm":
                statValue = PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_LuckMultiplier%");
                checkStat(player, targetPlayer, stat, statValue);
                break;
            case "expm":
                statValue = PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_EXPMultiplier%");
                checkStat(player, targetPlayer, stat, statValue);
                break;
            case "petexpm":
                statValue = PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_Pet_EXPMultiplier%");
                checkStat(player, targetPlayer, stat, statValue);
                break;
            case "maxweight":
                statValue = PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_MaxWeight%");
                checkStat(player, targetPlayer, stat, statValue);
                break;
            case "weight":
                statValue = PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_Weight%");
                checkStat(player, targetPlayer, stat, statValue);
                break;
            case "exp":
                statValue = PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_EXPAmount%");
                checkStat(player, targetPlayer, stat, statValue);
                break;
            case "pets":
                statValue = PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_PetAmount%");
                checkStat(player, targetPlayer, stat, statValue);
                break;
            case "maxpets":
                statValue = PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_MaxPetAmount%");
                checkStat(player, targetPlayer, stat, statValue);
                break;
            case "ac":
                statValue = PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Currency_AdventureCoins%");
                checkStat(player, targetPlayer, stat, statValue);
                break;
            case "miningpass":
                statValue = PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_MiningPassEXPAmount%");
                checkStat(player, targetPlayer, stat, statValue);
                break;
            case "all":
                checkAllStats(player, targetPlayer);
                break;

        }
    }

    public void checkStat(Player player, Player targetPlayer, String statName, String statValue) {
        player.sendMessage(ChatColor.GREEN + "You checked " + ChatColor.GOLD + targetPlayer.getPlayer().getName()
                + ChatColor.DARK_GREEN + " " + statName + ChatColor.GREEN + " they have " + statValue);
    }

    public void checkAllStats(Player player, Player targetPlayer) {
        if (!targetPlayer.isOnline()) {
            player.sendMessage(ChatColor.GOLD + targetPlayer.getName() + " " + ChatColor.DARK_GREEN + "❂ Current Weight: " + PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_Weight%"));
            player.sendMessage(ChatColor.GOLD + targetPlayer.getName() + " " + ChatColor.BLUE + "❂ Max Weight: " + PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_MaxWeight%"));
            player.sendMessage(ChatColor.GOLD + targetPlayer.getName() + " " + ChatColor.DARK_PURPLE + "❂ Max Weight Multiplier: " + PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_MaxWeightMultiplier%"));
            player.sendMessage(ChatColor.GOLD + targetPlayer.getName() + " " + ChatColor.DARK_GREEN + "⛂ Sell Multiplier: " + PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_SellMultiplier%"));
            player.sendMessage(ChatColor.GOLD + targetPlayer.getName() + " " + ChatColor.YELLOW + "⚅ Luck Multiplier: " + PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_LuckMultiplier%"));
            player.sendMessage(ChatColor.GOLD + targetPlayer.getName() + " " + ChatColor.GREEN + "۞ Exp Multiplier: " + PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_EXPMultiplier%"));
            player.sendMessage(ChatColor.GOLD + targetPlayer.getName() + " " + ChatColor.AQUA + "❉ Pet EXP Multiplier: " + PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_Pet_EXPMultiplier%"));
            player.sendMessage(ChatColor.GOLD + targetPlayer.getName() + " " + ChatColor.GREEN + "۞ EXP: " + PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_EXPAmount%"));
            player.sendMessage(ChatColor.GOLD + targetPlayer.getName() + " " + ChatColor.LIGHT_PURPLE + "❉ Pet Amount: " + PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_PetAmount%"));
            player.sendMessage(ChatColor.GOLD + targetPlayer.getName() + " " + ChatColor.AQUA + "❉ Pet EXP: " + PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_PetEXPAmount%"));
            player.sendMessage(ChatColor.GOLD + targetPlayer.getName() + " " + ChatColor.RED + "◎ AdventureCoins: " + PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Currency_AdventureCoins%"));
            player.sendMessage(ChatColor.GOLD + targetPlayer.getName() + " " + ChatColor.DARK_RED + "♦ MiningPass EXP: " + PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_MiningPassEXPAmount%"));
        } else {
            player.sendMessage(ChatColor.GOLD + targetPlayer.getName() + " " + ChatColor.GOLD + "⛏ Mining Speed: " + PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_MiningSpeed%"));
            player.sendMessage(ChatColor.GOLD + targetPlayer.getName() + " " + ChatColor.DARK_GREEN + "❂ Current Weight: " + PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_Weight%"));
            player.sendMessage(ChatColor.GOLD + targetPlayer.getName() + " " + ChatColor.BLUE + "❂ Max Weight: " + PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_MaxWeight%"));
            player.sendMessage(ChatColor.GOLD + targetPlayer.getName() + " " + ChatColor.DARK_PURPLE + "❂ Max Weight Multiplier: " + PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_MaxWeightMultiplier%"));
            player.sendMessage(ChatColor.GOLD + targetPlayer.getName() + " " + ChatColor.DARK_RED + "回 Block Multiplier: " + PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_BlockMultiplier%"));
            player.sendMessage(ChatColor.GOLD + targetPlayer.getName() + " " + ChatColor.DARK_GREEN + "⛂ Sell Multiplier: " + PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_SellMultiplier%"));
            player.sendMessage(ChatColor.GOLD + targetPlayer.getName() + " " + ChatColor.YELLOW + "⚅ Luck Multiplier: " + PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_LuckMultiplier%"));
            player.sendMessage(ChatColor.GOLD + targetPlayer.getName() + " " + ChatColor.GREEN + "۞ Exp Multiplier: " + PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_EXPMultiplier%"));
            player.sendMessage(ChatColor.GOLD + targetPlayer.getName() + " " + ChatColor.AQUA + "❉ Pet EXP Multiplier: " + PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_Pet_EXPMultiplier%"));
            player.sendMessage(ChatColor.GOLD + targetPlayer.getName() + " " + ChatColor.GREEN + "۞ EXP: " + PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_EXPAmount%"));
            player.sendMessage(ChatColor.GOLD + targetPlayer.getName() + " " + ChatColor.LIGHT_PURPLE + "❉ Pet Amount: " + PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_PetAmount%"));
            player.sendMessage(ChatColor.GOLD + targetPlayer.getName() + " " + ChatColor.AQUA + "❉ Pet EXP: " + PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_Pet_EXPAmount%"));
            player.sendMessage(ChatColor.GOLD + targetPlayer.getName() + " " + ChatColor.RED + "◎ AdventureCoins: " + PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Currency_AdventureCoins%"));
            player.sendMessage(ChatColor.GOLD + targetPlayer.getName() + " " + ChatColor.DARK_RED + "♦ MiningPass EXP: " + PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_MiningPassEXPAmount%"));

        }
    }

}

