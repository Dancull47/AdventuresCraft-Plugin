package monzter.adventurescraft.plugin.utilities;

import io.lumine.mythic.lib.api.item.NBTItem;
import io.lumine.mythic.lib.api.util.SmartGive;
import io.lumine.xikage.mythicmobs.MythicMobs;
import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
import io.lumine.xikage.mythicmobs.mobs.MythicMob;
import monzter.adventurescraft.plugin.AdventuresCraft;
import net.Indyuce.mmoitems.ItemStats;
import net.Indyuce.mmoitems.MMOItems;
import net.Indyuce.mmoitems.api.item.mmoitem.MMOItem;
import net.Indyuce.mmoitems.manager.ItemManager;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.betoncraft.betonquest.BetonQuest;
import pl.betoncraft.betonquest.database.PlayerData;

import java.text.DecimalFormat;

public class acUtils {
    private static Bukkit plugin;
    public static Economy econ = null;
    public static String common = ChatColor.WHITE.toString();
    public static String uncommon = ChatColor.DARK_GREEN.toString();
    public static String rare = ChatColor.BLUE.toString();
    public static String legendary = ChatColor.DARK_PURPLE.toString();
    public static String exotic = ChatColor.YELLOW.toString();
    public static String mythical = ChatColor.LIGHT_PURPLE.toString();
    public static String godly = ChatColor.RED.toString();

    public static void money(Player player, double amount) {
        EconomyResponse r = econ.depositPlayer(player, amount);
        if (!r.transactionSuccess()) {
            player.sendMessage(ChatColor.RED + "An error occurred while trying to give you money, report to Admins!");
            plugin.getLogger().info(ChatColor.RED + "An error occurred while sending " + amount + " to " + player);
        }
    }

    public static void spawnMob(Location location, String mobName) {
        MythicMob mob = MythicMobs.inst().getMobManager().getMythicMob(mobName);
        mob.spawn(BukkitAdapter.adapt(location), 0).getEntity().getBukkitEntity();
        plugin.getLogger().info("Method Called");

    }

    public static void givePoint(Player player, String path, int amount) {
        BetonQuest.getInstance().getPlayerData(player.getUniqueId().toString()).modifyPoints(path, amount);
    }

    public static void giveGlobalPoint(String path, int amount) {
        BetonQuest.getInstance().getGlobalData().modifyPoints(path, amount);
    }

    public static void givePointEXP(Player player, int amount) {
        givePoint(player, "items.Experience", amount);
    }

    public static void givePointPetEXP(Player player, int amount) {
        givePoint(player, "items.PetExperience", amount);
    }

    public static void givePointMiningPass(Player player, int amount) {
        givePoint(player, "miningPass.EXP", amount);
    }

    public static void givePointWeight(Player player, int amount) {
        givePoint(player, "items.Weight", amount);
    }

    public static void givePointACs(Player player, int amount) {
        givePoint(player, "items.AdventureCoin", amount);
    }


    public static void consoleCommand(String command) {
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
        giveMMOItem(player, type, id, 1);
    }

    public static void giveMMOItem(Player player, String type, String id, int amount) {
        giveMMOItem(player, type, id, amount, false);
    }

    public static void giveMMOItem(Player player, String type, String id, int amount, boolean silent) {
        final ItemStack itemStack = MMOItems.plugin.getItem(type, id).asQuantity(amount);
        new SmartGive(player).give(itemStack);
        if (!silent) {
            player.sendMessage(ChatColor.YELLOW + "You received " + ChatColor.GOLD + amount + ChatColor.YELLOW + "x " + itemStack.getItemMeta().getDisplayName() + ChatColor.YELLOW + "!");
            playSound(player, Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
        }
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

    public static void giveDropTable(Player player, String name) {
        Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "mm items give " + player.getName() + " " + name + " 1");
    }

    public static void giveDropTable(Player player, String name, int amount) {
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
            player.sendMessage(displayName + " " + ChatColor.GOLD + chance * 10 + "%");
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
    public static void playSound(Player player, Sound sound, float volume, float pitch) {
        player.getWorld().playSound(player.getLocation(), sound, volume, pitch);

    }

    public static void soundNo(Player player, float pitch) {
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1f, pitch);
    }

    public static void soundYes(Player player, float pitch) {
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES, 1f, pitch);
    }

    public static void soundYes2(Player player, float pitch) {
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_WANDERING_TRADER_YES, 1f, pitch);
    }

    public static void soundPurchase(Player player, float pitch) {
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, pitch);
    }


    public static String numberFormat(int number) {
        DecimalFormat format = new DecimalFormat("###,###,###");
        return format.format(number);
    }

}
