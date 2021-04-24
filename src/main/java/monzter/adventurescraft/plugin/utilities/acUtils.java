package monzter.adventurescraft.plugin.utilities;

import monzter.adventurescraft.plugin.AdventuresCraft;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class acUtils {
    private static Bukkit plugin;
    public static String common = ChatColor.WHITE.toString();
    public static String uncommon = ChatColor.DARK_GREEN.toString();
    public static String rare = ChatColor.BLUE.toString();
    public static String legendary = ChatColor.DARK_PURPLE.toString();
    public static String exotic = ChatColor.YELLOW.toString();
    public static String mythical = ChatColor.LIGHT_PURPLE.toString();
    public static String godly = ChatColor.RED.toString();


    public static void consoleCommand(String command){
        Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), command);
    }
    public static void givePermission(Player player, String permission) {
        Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "lp user " + player.getName() + " permission set " + permission + " true");
        plugin.getLogger().info(ChatColor.GREEN + "The " + ChatColor.GOLD + permission + ChatColor.GREEN
                + " permission has been ADDED TO " + ChatColor.GOLD + player.getName() + ChatColor.GREEN + "!");
    }

    public static void takePermission(Player player, String permission) {
        Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "lp user " + player.getName() + " permission set " + permission + " false");
        plugin.getLogger().info(ChatColor.GREEN + "The " + ChatColor.GOLD + permission + ChatColor.GREEN
                + " permission has been TAKEN FROM " + ChatColor.GOLD + player.getName() + ChatColor.GREEN + "!");
    }


    public static void giveMMOItem(Player player, String type, String id) {
        Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "mmoitems give " + type + " " + id + " " + player.getName() + " 1 0 100 0");
    }

    public static void giveMMOItem(Player player, String type, String id, int amount) {
        Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "mmoitems give " + type + " " + id + " " + player.getName() + " " + amount + " 0 100 0");
    }

    public static void giveMMOItem(Player player, String type, String id, boolean silent) {
        Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "mmoitems give " + type + " " + id + " " + player.getName() + " 1 0 100 0 s");
    }

    public static void giveMMOItem(Player player, String type, String id, int amount, boolean silent) {
        Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "mmoitems give " + type + " " + id + " " + player.getName() + " " + amount + " 0 100 0 s");
    }
    public static void giveMMDropTable(Player player, String tableName) {
        Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "mm items give " + player.getName() + " " + tableName + " 1");
    }
    public static void giveMMDropTable(Player player, String tableName, int amount) {
        Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "mm items give " + player.getName() + " " + tableName + " " + amount);
    }


    public static boolean fullInventory(Player player) {
        if (player.getInventory().firstEmpty() == -1) {
            player.sendMessage(ChatColor.RED + "You're inventory is full! Try again once you have at least one available slot!");
            return true;
        }
        return false;
    }

    public static void giveDropTable(Player player, String name){
        Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "mm items give " + player.getName() + " " + name + " 1");
    }
    public static void giveDropTable(Player player, String name, int amount){
        Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "mm items give " + player.getName() + " " + name + " " + amount);
    }

    public static boolean chanceCheck(double chance) {
//        .5 = 50%
        if (Math.random() <= chance) {
            return true;
        }
        return false;
    }
    public static boolean chanceCheck(double chance, Player player, int amount, String type, String id, String displayName) {
        if (Math.random() <= chance) {
            acUtils.giveMMOItem(player, type, id, amount);
            player.sendMessage(ChatColor.YELLOW.toString() + ChatColor.BOLD + "Rewards:");
            player.sendMessage(displayName + " " + ChatColor.GOLD + chance*10 + "%");
            return true;
        }
        return false;
    }
    public static boolean chanceCheck(double chance, Player player, int amount, String type, String id, String displayName, boolean silent) {
        if (Math.random() <= chance) {
            acUtils.giveMMOItem(player, type, id, amount, silent);
            player.sendMessage(ChatColor.YELLOW.toString() + ChatColor.BOLD + "Rewards:");
            player.sendMessage(displayName + " " + ChatColor.GOLD + chance + "%");
            return true;
        }
        return false;
    }

    //SOUNDS
    public static void playSound(Player player, Sound sound, float volume, float pitch){
        player.getWorld().playSound(player.getLocation(), sound, volume, pitch);

    }
    public static void soundNo(Player player, float pitch){
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1f, pitch);
    }
    public static void soundYes(Player player, float pitch){
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES, 1f, pitch);
    }
    public static void soundYes2(Player player, float pitch){
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_WANDERING_TRADER_YES, 1f, pitch);
    }
    public static void soundPurchase(Player player, float pitch){
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, pitch);
    }
}
