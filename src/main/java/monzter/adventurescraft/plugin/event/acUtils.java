package monzter.adventurescraft.plugin.event;

import monzter.adventurescraft.plugin.AdventuresCraft;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class acUtils {
    private static Bukkit plugin;

    public static void givePermission(Player player, String permission){
        Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "lp user " + player.getName() + " permission set " + permission + " true");
        plugin.getLogger().info(ChatColor.GREEN + "The " + ChatColor.GOLD + permission + ChatColor.GREEN
                + " permission has been ADDED TO " + ChatColor.GOLD + player.getName() + ChatColor.GREEN + "!");
    }
    public static void takePermission(Player player, String permission){
        Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "lp user " + player.getName() + " permission set " + permission + " false");
        plugin.getLogger().info(ChatColor.GREEN + "The " + ChatColor.GOLD + permission + ChatColor.GREEN
                + " permission has been TAKEN FROM " + ChatColor.GOLD + player.getName() + ChatColor.GREEN + "!");
    }
    public static void giveMMOItem(Player player, String type, String id){
        Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "mmoitems give " + type + " " + id + " " + player.getName() + " 1 0 100 0");
    }
    public static void giveMMOItem(Player player, String type, String id, int amount){
        Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "mmoitems give " + type + " " + id + " " + player.getName() + " " + amount + " 0 100 0");
    }
    public static void giveMMOItem(Player player, String type, String id, boolean silent){
        Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "mmoitems give " + type + " " + id + " " + player.getName() + " 1 0 100 0 s");
    }
    public static void giveMMOItem(Player player, String type, String id, int amount, boolean silent){
        Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "mmoitems give " + type + " " + id + " " + player.getName() + " " + amount + " 0 100 0 s");
    }
}
