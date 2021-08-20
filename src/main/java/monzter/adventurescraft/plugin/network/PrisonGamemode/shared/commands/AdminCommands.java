package monzter.adventurescraft.plugin.network.PrisonGamemode.shared.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import co.aikar.commands.bukkit.contexts.OnlinePlayer;
import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import me.clip.placeholderapi.PlaceholderAPI;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.beton.BetonPointsManager;
import monzter.adventurescraft.plugin.utilities.enums.PrisonStatsDisplay;
import monzter.adventurescraft.plugin.utilities.luckperms.PermissionLP;
import monzter.adventurescraft.plugin.utilities.mmoitems.MMOItemsGive;
import monzter.adventurescraft.plugin.utilities.text.NumberFormat;
import monzter.adventurescraft.plugin.utilities.vault.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class AdminCommands extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final MMOItemsGive mmoItemsGive;
    private final PermissionLP permissionLP;
    private final BetonPointsManager betonPointsManager;
    private final NumberFormat numberFormat;
    private final Economy economy;


    public AdminCommands(AdventuresCraft plugin, MMOItemsGive mmoItemsGive, PermissionLP permissionLP, BetonPointsManager betonPointsManager, NumberFormat numberFormat, Economy economy) {
        this.plugin = plugin;
        this.mmoItemsGive = mmoItemsGive;
        this.permissionLP = permissionLP;
        this.betonPointsManager = betonPointsManager;
        this.numberFormat = numberFormat;
        this.economy = economy;
    }

    @CommandAlias("stat")
    @CommandPermission("*")
    @Description("Check stats of a player")
    @CommandCompletion("* maxweightm|blockm|sellm|luckm|expm|petexpm|maxweight|weight|exp|pets|maxpets|ac|miningpass|all")
    public void statCommand(Player player, OnlinePlayer targetPlayer, String stat) {
        statsCommand(player, stat, targetPlayer.getPlayer());
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

    @CommandAlias("reward")
    @CommandPermission("*")
    @Description("Reward stats to a Player")
    @CommandCompletion("petexperience|petexp|experience|exp|miningpass|ac|adventureCoin|adventureCoins|money|coins @nothing *")
    public void rewardCommand(String stat, int amount, OnlinePlayer targetPlayer) {
        switch (stat.toLowerCase()) {
            case "petexperience":
            case "petexp":
                targetPlayer.getPlayer().sendMessage(ChatColor.GREEN + "You gained +" + ChatColor.GOLD + numberFormat.numberFormat(amount) + ChatColor.GREEN + "x " + PrisonStatsDisplay.PET_EXPERIENCE_AMOUNT.getName() + ChatColor.GREEN + "!");
                betonPointsManager.givePointPetEXP(targetPlayer.player, amount);
                break;
            case "experience":
            case "exp":
                targetPlayer.getPlayer().sendMessage(ChatColor.GREEN + "You gained +" + ChatColor.GOLD + numberFormat.numberFormat(amount) + ChatColor.GREEN + "x " + PrisonStatsDisplay.EXPERIENCE_AMOUNT.getName() + ChatColor.GREEN + "!");
                betonPointsManager.givePointEXP(targetPlayer.player, amount);
                break;
            case "miningpass":
                targetPlayer.getPlayer().sendMessage(ChatColor.GREEN + "You gained +" + ChatColor.GOLD + numberFormat.numberFormat(amount) + ChatColor.GREEN + "x " + PrisonStatsDisplay.MINING_PASS_EXPERIENCE.getName() + ChatColor.GREEN + "!");
                betonPointsManager.givePointMiningPass(targetPlayer.player, amount);
                break;
            case "ac":
            case "acs":
            case "adventureCoin":
            case "adventureCoins":
                targetPlayer.getPlayer().sendMessage(ChatColor.GREEN + "You gained +" + ChatColor.GOLD + numberFormat.numberFormat(amount) + ChatColor.GREEN + "x " + PrisonStatsDisplay.ADVENTURE_COINS.getName() + ChatColor.GREEN + "!");
                betonPointsManager.givePointACs(targetPlayer.player, amount);
                break;
            case "money":
            case "coins":
            case "coin":
                economy.giveMoney(targetPlayer.player, amount);
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

    @CommandAlias("adminReset")
    @CommandPermission("*")
    @Description("Resets Player for testing")
    @CommandCompletion("*")
    public void reset(Player player, OnlinePlayer onlinePlayer) {
        player.performCommand("mmocore admin reset all " + onlinePlayer.getPlayer().getName());
        player.performCommand("ranks set rank " + onlinePlayer.getPlayer().getName() + " A default");
        player.performCommand("lp user " + onlinePlayer.getPlayer().getName() + " clear");
        player.performCommand("bq purge " + onlinePlayer.getPlayer().getName());
        player.performCommand("money set " + onlinePlayer.getPlayer().getName() + " 0");
        player.sendMessage(ChatColor.GREEN + "You have reset " + ChatColor.GOLD + onlinePlayer.getPlayer().getName() + "'s " + ChatColor.GREEN + "Stats!");
        onlinePlayer.getPlayer().sendMessage(ChatColor.RED + "Your Stats have been reset by " + ChatColor.GOLD + player.getName() + ChatColor.RED + "!");
    }

    private void booster(String boosterType, Player player, int boosterTier, int boosterDuration) {
        double x = player.getLocation().getX();
        double y = player.getLocation().getY();
        double z = player.getLocation().getZ();
        hologram(player);
        permissionLP.giveTempPermission(player, boosterType.toLowerCase() + ".booster." + boosterTier, boosterDuration, "m");
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
            return PrisonStatsDisplay.SELL_MULTIPLIER.getName();
        } else if (boosterType.contains("EXP")) {
            return PrisonStatsDisplay.EXPERIENCE_AMOUNT.getName();
        } else if (boosterType.contains("PET_EXP")) {
            return PrisonStatsDisplay.PET_EXPERIENCE_AMOUNT.getName();
        } else if (boosterType.contains("BLOCK")) {
            return PrisonStatsDisplay.BLOCK_MULTIPLIER.getName();
        } else if (boosterType.contains("LUCK")) {
            return PrisonStatsDisplay.LUCK_MULTIPLIER.getName();
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
            player.sendMessage(ChatColor.GOLD + targetPlayer.getName() + " " + PrisonStatsDisplay.MAX_WEIGHT.getName() + ": " + PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_MaxWeight%"));
            player.sendMessage(ChatColor.GOLD + targetPlayer.getName() + " " + PrisonStatsDisplay.MAX_WEIGHT_MULTIPLIER.getName() + ": " + PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_MaxWeightMultiplier%"));
            player.sendMessage(ChatColor.GOLD + targetPlayer.getName() + " " + PrisonStatsDisplay.BLOCK_MULTIPLIER.getName() + ": " + PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_BlockMultiplier%"));
            player.sendMessage(ChatColor.GOLD + targetPlayer.getName() + " " + PrisonStatsDisplay.SELL_MULTIPLIER.getName() + ": " + PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_SellMultiplier%"));
            player.sendMessage(ChatColor.GOLD + targetPlayer.getName() + " " + PrisonStatsDisplay.LUCK_MULTIPLIER.getName() + ": " + PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_LuckMultiplier%"));
            player.sendMessage(ChatColor.GOLD + targetPlayer.getName() + " " + PrisonStatsDisplay.EXPERIENCE_MULTIPLIER.getName() + ": " + PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_EXPMultiplier%"));
            player.sendMessage(ChatColor.GOLD + targetPlayer.getName() + " " + PrisonStatsDisplay.PET_EXPERIENCE_MULTIPLIER.getName() + ": " + PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_Pet_EXPMultiplier%"));
            player.sendMessage(ChatColor.GOLD + targetPlayer.getName() + " " + PrisonStatsDisplay.EXPERIENCE_AMOUNT.getName() + ": " + PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_EXPAmount%"));
            player.sendMessage(ChatColor.GOLD + targetPlayer.getName() + " " + ChatColor.LIGHT_PURPLE + "❉ Pet Amount: " + PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_PetAmount%"));
            player.sendMessage(ChatColor.GOLD + targetPlayer.getName() + " " + PrisonStatsDisplay.PET_EXPERIENCE_AMOUNT.getName() + ": " + PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_PetEXPAmount%"));
            player.sendMessage(ChatColor.GOLD + targetPlayer.getName() + " " + PrisonStatsDisplay.ADVENTURE_COINS.getName() + ": " + PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Currency_AdventureCoins%"));
            player.sendMessage(ChatColor.GOLD + targetPlayer.getName() + " " + PrisonStatsDisplay.MINING_PASS_EXPERIENCE.getName() + ": " + PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_MiningPassEXPAmount%"));
        } else {
            player.sendMessage(ChatColor.GOLD + targetPlayer.getName() + " " + PrisonStatsDisplay.MINING_SPEED.getName() + ": " + PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_MiningSpeed%"));
            player.sendMessage(ChatColor.GOLD + targetPlayer.getName() + " " + ChatColor.DARK_GREEN + "❂ Current Weight: " + PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_Weight%"));
            player.sendMessage(ChatColor.GOLD + targetPlayer.getName() + " " + PrisonStatsDisplay.MAX_WEIGHT.getName() + ": " + PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_MaxWeight%"));
            player.sendMessage(ChatColor.GOLD + targetPlayer.getName() + " " + PrisonStatsDisplay.MAX_WEIGHT_MULTIPLIER.getName() + ": " + PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_MaxWeightMultiplier%"));
            player.sendMessage(ChatColor.GOLD + targetPlayer.getName() + " " + PrisonStatsDisplay.BLOCK_MULTIPLIER.getName() + ": " + PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_BlockMultiplier%"));
            player.sendMessage(ChatColor.GOLD + targetPlayer.getName() + " " + PrisonStatsDisplay.SELL_MULTIPLIER.getName() + ": " + PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_SellMultiplier%"));
            player.sendMessage(ChatColor.GOLD + targetPlayer.getName() + " " + PrisonStatsDisplay.LUCK_MULTIPLIER.getName() + ": " + PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_LuckMultiplier%"));
            player.sendMessage(ChatColor.GOLD + targetPlayer.getName() + " " + PrisonStatsDisplay.EXPERIENCE_MULTIPLIER.getName() + ": " + PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_EXPMultiplier%"));
            player.sendMessage(ChatColor.GOLD + targetPlayer.getName() + " " + PrisonStatsDisplay.PET_EXPERIENCE_MULTIPLIER.getName() + ": " + PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_Pet_EXPMultiplier%"));
            player.sendMessage(ChatColor.GOLD + targetPlayer.getName() + " " + PrisonStatsDisplay.EXPERIENCE_AMOUNT.getName() + ": " + PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_EXPAmount%"));
            player.sendMessage(ChatColor.GOLD + targetPlayer.getName() + " " + ChatColor.LIGHT_PURPLE + "❉ Pet Amount: " + PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_PetAmount%"));
            player.sendMessage(ChatColor.GOLD + targetPlayer.getName() + " " + PrisonStatsDisplay.PET_EXPERIENCE_AMOUNT.getName() + ": " + PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_PetEXPAmount%"));
            player.sendMessage(ChatColor.GOLD + targetPlayer.getName() + " " + PrisonStatsDisplay.ADVENTURE_COINS.getName() + ": " + PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Currency_AdventureCoins%"));
            player.sendMessage(ChatColor.GOLD + targetPlayer.getName() + " " + PrisonStatsDisplay.MINING_PASS_EXPERIENCE.getName() + ": " + PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_MiningPassEXPAmount%"));
        }
    }

}

