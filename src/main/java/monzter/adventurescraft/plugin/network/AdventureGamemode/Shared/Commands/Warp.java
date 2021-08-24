package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.Commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.Dependency;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.network.AdventureGamemode.Enums.Warps;
import monzter.adventurescraft.plugin.utilities.luckperms.PermissionLP;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.jetbrains.annotations.NotNull;

public class Warp extends BaseCommand implements PluginMessageListener {

    @Dependency
    private final AdventuresCraft plugin;
    private final PermissionLP permissionLP;
    final TextComponent donate = Component.text("You must purchase the")
            .color(NamedTextColor.RED)
            .append(Component.text(" Conquerer ", NamedTextColor.RED, TextDecoration.BOLD))
            .append(Component.text("Rank to use this!", NamedTextColor.RED))
            .hoverEvent(Component.text("Click to visit the Store!", NamedTextColor.GREEN))
            .clickEvent(ClickEvent.openUrl("https://store.adventurescraft.net"));


    public Warp(AdventuresCraft plugin, PermissionLP permissionLP) {
        this.plugin = plugin;
        this.permissionLP = permissionLP;
        plugin.getServer().getMessenger().registerOutgoingPluginChannel(plugin, "BungeeCord");
        plugin.getServer().getMessenger().registerIncomingPluginChannel(plugin, "BungeeCord", this);
    }

    @CommandAlias("warp|travel")
    public void warpMenu(Player player) {
        player.performCommand("map");
    }

    @CommandAlias("warp|travel")
    @CommandCompletion("Town|Spawn|Farm|Forest|SpruceForest|DarkOakForest|BirchForest|AcaciaForest|JungleForest|HiveForest|" +
            "MineEntrance|CoalMine|GoldMine|RedstoneMine|LapisMine|DiamondMine|EmeraldMine|Graveyard|Courtyard|Castle|Morden|" +
            "Estate|GoblinTown|SpiritGrounds|Hell|LowerHell|Void|" +
            "Enchanter|Auction|Dracula|Reaper|VoidWither|VoidMagma|Ghastly|Bullbo")
    public void warp(Player player, String warpName) {
        Warps warps = warpExists(warpName);
        if (warps != null) {
            if (warps.getWarpPermissions() == null) {
                if (plugin.SERVER.equalsIgnoreCase("Adventure"))
                    sendToLocation(player, warps.getLocation(), warps.name().replace('_', ' '));
                if (plugin.SERVER.equalsIgnoreCase("Homes"))
                    sendToAdventure(player, warps.name().toUpperCase());
            } else if (hasPermission(player, warps)) {
                if (plugin.SERVER.equalsIgnoreCase("Adventure"))
                    sendToLocation(player, warps.getLocation(), warps.name().replace('_', ' '));
                if (plugin.SERVER.equalsIgnoreCase("Homes"))
                    sendToAdventure(player, warps.name().toUpperCase());
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

    //    If Player is on the Homes server
    private void sendToAdventure(Player player, String name) {
        permissionLP.givePermission(player, "HOME.WARP." + name);
        ByteArrayDataOutput b = ByteStreams.newDataOutput();
        try {
            b.writeUTF("Connect");
            b.writeUTF("Adventure");
        } catch (Exception e) {
        }
        player.sendPluginMessage(plugin, "BungeeCord", b.toByteArray());
    }

    private boolean hasPermission(Player player, Warps warps) {
        if (!warps.getWarpPermissions().contains("Rank3")) {
            for (String permission : warps.getWarpPermissions()) {
                if (player.hasPermission(permission))
                    return true;
            }
            player.sendMessage(ChatColor.RED + "You haven't unlocked this area yet!");
        } else {
            if (player.hasPermission("Rank3"))
                return true;
            player.sendMessage(donate);
        }
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

    @Override
    public void onPluginMessageReceived(@NotNull String s, @NotNull Player player, @NotNull byte[] bytes) {

    }
}

