package monzter.adventurescraft.plugin.network.PrisonGamemode.shared.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.luckperms.PermissionLP;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class Warp extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final PermissionLP permissionLP;


    public Warp(AdventuresCraft plugin, PermissionLP permissionLP) {
        this.plugin = plugin;
        this.permissionLP = permissionLP;
    }

    @CommandAlias("warp|travel")
    public void warpMenu(Player player) {
        player.performCommand("map");
    }

    @CommandAlias("warp|travel")
    @CommandCompletion("Town|Spawn|MineA|MineB|MineC|MineD|MineE|MineF|MineG|MineH|MineI|MineJ|MineK|MineL|MineM|MineN|MineO|MineP|MineQ|MineR|MineS|MineT|MineU|MineV|MineW|MineX|MineY|MineZ|" +
            "Crates|Enchanter|Enchanting|Pets|PetShop|Enchanting")
    public void warp(Player player, String warpName) {
        Warps warps = warpExists(warpName);
        if (warps != null) {
            if (warps.getWarpPermissions() == null) {
                if (plugin.SERVER.equalsIgnoreCase("Prison"))
                    sendToLocation(player, warps.getLocation(), warps.name().replace('_', ' '));
                if (plugin.SERVER.equalsIgnoreCase("Cell"))
                    sendToPrison(player, warps.name().toUpperCase());
            } else if (hasPermission(player, warps)) {
                if (plugin.SERVER.equalsIgnoreCase("Prison"))
                    sendToLocation(player, warps.getLocation(), warps.name().replace('_', ' '));
                if (plugin.SERVER.equalsIgnoreCase("Cell"))
                    sendToPrison(player, warps.name().toUpperCase());
            }
        } else {
            player.sendMessage(ChatColor.YELLOW + warpName + ChatColor.RED + " doesn't exist!");
            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1f, 1);
        }
    }

    private void sendToLocation(Player player, Location location, String name) {
        player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1f, 1);
        player.sendMessage(ChatColor.GREEN + "You've traveled to " + ChatColor.YELLOW + name + ChatColor.GREEN + "!");
        player.teleport(location);
    }

    //    If Player is on the Cell server
    private void sendToPrison(Player player, String name) {
        permissionLP.givePermission(player, "CELL.WARP." + name);
        player.performCommand("server Prison");
    }

    private boolean hasPermission(Player player, Warps warps) {
        for (String permission : warps.getWarpPermissions()) {
            if (player.hasPermission(permission))
                return true;
        }
        player.sendMessage(ChatColor.RED + "You haven't unlocked this area yet!");
        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1f, 1);
        return false;
    }

    private Warps warpExists(String warp) {
        for (Warps warps : Warps.values())
            for (String name : warps.getWarpNames())
                if (name.equalsIgnoreCase(warp))
                    return warps;
        return null;
    }
}

