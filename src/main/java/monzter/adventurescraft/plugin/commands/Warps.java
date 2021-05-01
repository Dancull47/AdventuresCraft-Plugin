package monzter.adventurescraft.plugin.commands;

import com.sk89q.worldedit.util.YAMLConfiguration;
import me.clip.placeholderapi.PlaceholderAPI;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.event.extras.WeightPrices;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.*;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.enginehub.piston.CommandManager;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Warps implements TabExecutor {
    private final AdventuresCraft plugin;
    private final YamlConfiguration warps;

    public Warps(AdventuresCraft plugin, YamlConfiguration warps) {
        this.plugin = plugin;
        this.warps = warps;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Location location = null;
        String name = null;
        String permission = null;
        String lockedMessage = null;
        if (sender instanceof Player) {
            Player player = ((Player) sender).getPlayer();
            if (args.length == 0) {
                Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "dm open Map " + player.getName()); // Adds to Total Blocks
            } else if (args[0].toLowerCase().equals("setwarp")) {
                if (player.isOp()) {
                    switch (args.length) {
                        default:
                            player.sendMessage(ChatColor.RED + "/Warp setwarp <ID> <NAME> [<PERMISSION>] [<LOCKED-MESSAGE>]");
                            break;
                        case 3:
                            String id = args[1];
                            name = args[2];
                            location = player.getLocation();
                            setWarp(player, id, name, location);
                            break;
                        case 5:
                            id = args[1];
                            name = args[2];
                            location = player.getLocation();
                            permission = args[3];
                            lockedMessage = args[4];
                            setWarp(player, id, name, permission, lockedMessage, location);
                            break;
                    }
                }
            } else {
                Set<String> warpNames = warps.getKeys(false);
                for (String currentWarpName : warpNames) {
                    if (args[0].equalsIgnoreCase(currentWarpName)) {
                        final ConfigurationSection warpKeysSection = warps.getConfigurationSection(currentWarpName);
                        final Set<String> warpKeys = warpKeysSection.getKeys(false);
                        for (String currentWarpKey : warpKeys) {
                            if (currentWarpKey.contains("Name")) {
                                name = warpKeysSection.getString(currentWarpKey);
                            }
                            if (currentWarpKey.contains("Permission")) {
                                permission = warpKeysSection.getString(currentWarpKey);
                            }
                            if (currentWarpKey.contains("LockedMessage")) {
                                lockedMessage = warpKeysSection.getString(currentWarpKey);
                            }
                            if (currentWarpKey.contains("Location")) {
                                location = warpKeysSection.getLocation(currentWarpKey);
                            }
                        }
                        if (location != null && permission != null && lockedMessage != null) {
                            sendToLocation(player, location, name, permission, lockedMessage);
                        }
                        if (location != null && permission == null) {
                            sendToLocation(player, location, name);
                        }
//                    } else{
//                        player.sendMessage(ChatColor.RED + "That area does not exist!");
                    }
                }
            }
        }
        return false;
    }

    private final void sendToLocation(Player player, Location location, String name) {
        player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1f, 1);
        player.sendMessage(ChatColor.GREEN + "You've traveled to " + ChatColor.YELLOW + name + ChatColor.GREEN + "!");
        player.teleport(location);
    }

    private final void sendToLocation(Player player, Location location, String name, String permission, String lockedMessage) {
        if (player.hasPermission(permission)) {
            sendToLocation(player, location, name);
        } else {
            if (lockedMessage.equals("default")) {
                player.sendMessage(ChatColor.RED + "You haven't unlocked this area yet!");
                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1f, 1);
            } else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', lockedMessage));
            }
        }
    }

    private final void setWarp(Player player, String id, String name, Location location) {
        setWarp(player, id, name, "", "", location);
    }

    private final void setWarp(Player player, String id, String name, String permission, String lockedMessage, Location location) {
        warps.set(id, id);
        warps.set(id + ".Name", name.replace("-", " "));
        if (!permission.isEmpty()) {
            warps.set(id + ".Permission", permission);
            warps.set(id + ".LockedMessage", lockedMessage.replace("-", " "));
        }
        warps.set(id + ".Location", location);
        player.sendMessage(ChatColor.GOLD + name.replace("-", " ") + ChatColor.GREEN + " warp has been set!");
        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_CELEBRATE, 1f, 1);
        try {
            warps.save(plugin.getWarpsFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            String permission = null;
            final List<String> warpArray = new ArrayList<>();
            final Set<String> warpNames = warps.getKeys(false);
            for (String currentWarpName : warpNames) {
                if (args[0].length() < 1 || currentWarpName.toLowerCase().contains(args[0].toLowerCase())) {
                    final ConfigurationSection warpKeysSection = warps.getConfigurationSection(currentWarpName);
                    final Set<String> warpKeys = warpKeysSection.getKeys(false);
                    for (String currentWarpKey : warpKeys) {
                        if (currentWarpKey.contains("Permission")) {
                            permission = warpKeysSection.getString(currentWarpKey);
                        }
                    }
                    if (permission != null && sender.hasPermission(permission)) {
                        warpArray.add(currentWarpName);
                    } else if (permission == null) {
                        warpArray.add(currentWarpName);
                    }
                }
            }
            return warpArray;
        }
        return null;
    }
}