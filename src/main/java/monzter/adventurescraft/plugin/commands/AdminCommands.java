package monzter.adventurescraft.plugin.commands;

import me.clip.placeholderapi.PlaceholderAPI;
import monzter.adventurescraft.plugin.AdventuresCraft;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class AdminCommands implements CommandExecutor, @Nullable TabCompleter {
    private final AdventuresCraft plugin;
    private HashMap<UUID, Integer> codeAttempts = new HashMap<>();

    public AdminCommands(AdventuresCraft plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = ((Player) sender).getPlayer();
            if (player.isOp()) {
                switch (command.getName()) {
                    case "Stat":
                        if (args.length < 1) {
                            player.sendMessage(ChatColor.RED + "/Stat <Player> <Stat>");
                            return true;
                        } else {
                            Player targetPlayer = Bukkit.getPlayer(args[1]);
                            switch (args[0]) {
                                case "MaxWeightM":
                                    String statValue = PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_MaxWeightMultiplier%");
                                    checkStat(player, targetPlayer, args[0], statValue);
                                    return true;
                                case "BlockM":
                                    statValue = PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_BlockMultiplier%");
                                    checkStat(player, targetPlayer, args[0], statValue);
                                    return true;
                                case "SellM":
                                    statValue = PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_SellMultiplier%");
                                    checkStat(player, targetPlayer, args[0], statValue);
                                    return true;
                                case "LuckM":
                                    statValue = PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_LuckMultiplier%");
                                    checkStat(player, targetPlayer, args[0], statValue);
                                    return true;
                                case "EXPM":
                                    statValue = PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_EXPMultiplier%");
                                    checkStat(player, targetPlayer, args[0], statValue);
                                    return true;
                                case "PetEXPM":
                                    statValue = PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_Pet_EXPMultiplier%");
                                    checkStat(player, targetPlayer, args[0], statValue);
                                    return true;
                                case "MaxWeight":
                                    statValue = PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_MaxWeight%");
                                    checkStat(player, targetPlayer, args[0], statValue);
                                    return true;
                                case "Weight":
                                    statValue = PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_Weight%");
                                    checkStat(player, targetPlayer, args[0], statValue);
                                    return true;
                                case "EXP":
                                    statValue = PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_EXPAmount%");
                                    checkStat(player, targetPlayer, args[0], statValue);
                                    return true;
                                case "Pets":
                                    statValue = PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_PetAmount%");
                                    checkStat(player, targetPlayer, args[0], statValue);
                                    return true;
                                case "MaxPets":
                                    statValue = PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_MaxPetAmount%");
                                    checkStat(player, targetPlayer, args[0], statValue);
                                    return true;
                                case "AC":
                                    statValue = PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Currency_AdventureCoins%");
                                    checkStat(player, targetPlayer, args[0], statValue);
                                    return true;
                                case "BattlePass":
                                    statValue = PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_BattlePassEXPAmount%");
                                    checkStat(player, targetPlayer, args[0], statValue);
                                    return true;
                                case "All":
                                    checkAllStats(player, targetPlayer);
                                    return true;
                            }
                        }
                    case "PointsAdd":
                        if (args.length < 1) {
                            player.sendMessage(ChatColor.RED + "/PointsPointsAdd <Amount> <Material>");
                            return true;
                        } else if (args.length > 1) {
                            player.sendMessage(ChatColor.YELLOW + "You added +" + args[0] + " to " + args[1] + ChatColor.GREEN + "!");
                            plugin.data.createPlayer(player, args[1], Long.valueOf(args[0]));
                            plugin.data.setPointAmount(player.getUniqueId(), args[1], Long.valueOf(args[0]));
                            return true;
                        }
                        return true;
                    case "PointsAmount":
                        if (args.length < 1) {
                            player.sendMessage(ChatColor.RED + "/PointsAmount <Material>");
                            return true;
                        } else if (args.length > 1) {
                            player.sendMessage(ChatColor.GREEN + "You have mined " + plugin.data.getPointAmount(player.getUniqueId(), args[0]) + " " + ChatColor.COLOR_CHAR + args[0] + ChatColor.GREEN + "!");
                            return true;
                        }
                    case "RestartTime":
                        String restartTime = PlaceholderAPI.setPlaceholders(player, "%ac_Restart_formatted%");
                        String restartTimeSeconds = PlaceholderAPI.setPlaceholders(player, "%ac_Restart%");
                        player.sendMessage(ChatColor.GREEN + "There is " + ChatColor.GOLD + restartTime + ChatColor.GREEN + " until restart!");
                        player.sendMessage(ChatColor.GOLD + restartTimeSeconds + ChatColor.GREEN + " seconds!");
                }
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (sender.isOp()) {
            if (command.getName().toLowerCase().equals("stat")) {
                if (args.length == 1) {
                    List<String> arguments = new ArrayList<>(Arrays.asList("All", "PetEXP", "Weight", "MaxWeight", "EXP", "Pets", "MaxPets", "BattlePass", "AC", "PetEXPM",
                            "MaxWeightM", "BlockM", "SellM", "LuckM", "EXPM"));
                    return arguments;
                }
            }
        }
        return null;
    }

    public void checkStat(Player player, Player targetPlayer, String statName, String statValue) {
        player.sendMessage(ChatColor.GREEN + "You checked " + ChatColor.GOLD + targetPlayer.getName()
                + ChatColor.DARK_GREEN + " " + statName + ChatColor.GREEN + " they have " + statValue);
    }

    public void checkAllStats(Player player, Player targetPlayer) {
        if (!targetPlayer.isOnline()) {
            player.sendMessage(ChatColor.GOLD + targetPlayer.getName() + ChatColor.DARK_GREEN + "❂ Current Weight: " + PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_Weight%"));
            player.sendMessage(ChatColor.GOLD + targetPlayer.getName() + ChatColor.BLUE + "❂ Max Weight: " + PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_MaxWeight%"));
            player.sendMessage(ChatColor.GOLD + targetPlayer.getName() + ChatColor.DARK_PURPLE + "❂ Max Weight Multiplier: " + PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_MaxWeightMultiplier%"));
            player.sendMessage(ChatColor.GOLD + targetPlayer.getName() + ChatColor.DARK_GREEN + "⛂ Sell Multiplier: " + PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_SellMultiplier%"));
            player.sendMessage(ChatColor.GOLD + targetPlayer.getName() + ChatColor.YELLOW + "⚅ Luck Multiplier: " + PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_LuckMultiplier%"));
            player.sendMessage(ChatColor.GOLD + targetPlayer.getName() + ChatColor.GREEN + "۞ Exp Multiplier: " + PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_EXPMultiplier%"));
            player.sendMessage(ChatColor.GOLD + targetPlayer.getName() + ChatColor.AQUA + "❉ Pet EXP Multiplier: " + PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_Pet_EXPMultiplier%"));
            player.sendMessage(ChatColor.GOLD + targetPlayer.getName() + ChatColor.GREEN + "۞ EXP: " + PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_EXPAmount%"));
            player.sendMessage(ChatColor.GOLD + targetPlayer.getName() + ChatColor.LIGHT_PURPLE + "❉ Pet Amount: " + PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_PetAmount%"));
            player.sendMessage(ChatColor.GOLD + targetPlayer.getName() + ChatColor.AQUA + "❉ Pet EXP: " + PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_PetEXPAmount%"));
            player.sendMessage(ChatColor.GOLD + targetPlayer.getName() + ChatColor.RED + "◎ AdventureCoins: " + PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Currency_AdventureCoins%"));
            player.sendMessage(ChatColor.GOLD + targetPlayer.getName() + ChatColor.DARK_RED + "5♦ BattlePass EXP: " + PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_BattlePassEXPAmount%"));
        } else {
            player.sendMessage(ChatColor.GOLD + targetPlayer.getName() + ChatColor.GOLD + "⛏ Mining Speed: " + PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_MiningSpeed%"));
            player.sendMessage(ChatColor.GOLD + targetPlayer.getName() + ChatColor.DARK_GREEN + "❂ Current Weight: " + PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_Weight%"));
            player.sendMessage(ChatColor.GOLD + targetPlayer.getName() + ChatColor.BLUE + "❂ Max Weight: " + PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_MaxWeight%"));
            player.sendMessage(ChatColor.GOLD + targetPlayer.getName() + ChatColor.DARK_PURPLE + "❂ Max Weight Multiplier: " + PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_MaxWeightMultiplier%"));
            player.sendMessage(ChatColor.GOLD + targetPlayer.getName() + ChatColor.DARK_RED + "回 Block Multiplier: " + PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_BlockMultiplier%"));
            player.sendMessage(ChatColor.GOLD + targetPlayer.getName() + ChatColor.DARK_GREEN + "⛂ Sell Multiplier: " + PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_SellMultiplier%"));
            player.sendMessage(ChatColor.GOLD + targetPlayer.getName() + ChatColor.YELLOW + "⚅ Luck Multiplier: " + PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_LuckMultiplier%"));
            player.sendMessage(ChatColor.GOLD + targetPlayer.getName() + ChatColor.GREEN + "۞ Exp Multiplier: " + PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_EXPMultiplier%"));
            player.sendMessage(ChatColor.GOLD + targetPlayer.getName() + ChatColor.AQUA + "❉ Pet EXP Multiplier: " + PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_Pet_EXPMultiplier%"));
            player.sendMessage(ChatColor.GOLD + targetPlayer.getName() + ChatColor.GREEN + "۞ EXP: " + PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_EXPAmount%"));
            player.sendMessage(ChatColor.GOLD + targetPlayer.getName() + ChatColor.LIGHT_PURPLE + "❉ Pet Amount: " + PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_PetAmount%"));
            player.sendMessage(ChatColor.GOLD + targetPlayer.getName() + ChatColor.AQUA + "❉ Pet EXP: " + PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_Pet_EXPAmount%"));
            player.sendMessage(ChatColor.GOLD + targetPlayer.getName() + ChatColor.RED + "◎ AdventureCoins: " + PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Currency_AdventureCoins%"));
            player.sendMessage(ChatColor.GOLD + targetPlayer.getName() + ChatColor.DARK_RED + "5♦ BattlePass EXP: " + PlaceholderAPI.setPlaceholders(targetPlayer, "%ac_Stat_BattlePassEXPAmount%"));

        }
    }
}

