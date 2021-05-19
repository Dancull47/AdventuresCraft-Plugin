package monzter.adventurescraft.plugin.prison.events;

import io.lumine.mythic.utils.Schedulers;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.luckperms.PermissionLP;
import monzter.adventurescraft.plugin.utilities.mmoitems.MMOItemsGive;
import net.craftersland.data.bridge.PD;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Set;

public class JoinPrison implements Listener {
    private final AdventuresCraft plugin;
    private final MMOItemsGive mmoItemsGive;
    private final PermissionLP permissionLP;
    private int tries = 0;
    private final YamlConfiguration warps;

    private final TextComponent mining = Component.text("You can start mining by using ")
            .color(NamedTextColor.GREEN)
            .append(Component.text("/Mine", NamedTextColor.GOLD, TextDecoration.BOLD))
            .hoverEvent(Component.text("Click to visit your latest Mine!", NamedTextColor.GREEN))
            .clickEvent(ClickEvent.runCommand("/Mine"))
            .append(Component.text("!"));
    private final TextComponent tutorial = Component.text("You should checkout our ")
            .color(NamedTextColor.GREEN)
            .append(Component.text("Tutorial", NamedTextColor.GOLD, TextDecoration.BOLD))
            .hoverEvent(Component.text("Click to visit the Tutorial!", NamedTextColor.GREEN))
            .clickEvent(ClickEvent.runCommand("/warp Tutorial"))
            .append(Component.text(" to learn about all the unique features of our Prison!"));

    public JoinPrison(AdventuresCraft plugin, MMOItemsGive mmoItemsGive, PermissionLP permissionLP, YamlConfiguration warps) {
        this.plugin = plugin;
        this.mmoItemsGive = mmoItemsGive;
        this.permissionLP = permissionLP;
        this.warps = warps;
    }

    @EventHandler
    private void onJoin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        if (!player.hasPermission("mines.tp.m")) {
            player.sendMessage(mining);
        }
        if (!player.hasPermission("cmi.hologram.tutorial_pickaxe")) {
            player.sendMessage(tutorial);
        }
        if (!player.hasPermission("KIT.RECEIVED")) {
            Schedulers.sync().runRepeating(task -> {
                if (PD.api.isInventoryArmorSyncComplete(player)) {
                    permissionLP.givePermission(player, "KIT.RECEIVED");
                    mmoItemsGive.giveMMOItem(player, "TOOL", "PRISONER_PICKAXE");
                    mmoItemsGive.giveMMOItem(player, "ARMOR", "PRISONER_HAT");
                    mmoItemsGive.giveMMOItem(player, "ARMOR", "PRISONER_CHESTPLATE");
                    mmoItemsGive.giveMMOItem(player, "ARMOR", "PRISONER_LEGGINGS");
                    mmoItemsGive.giveMMOItem(player, "ARMOR", "PRISONER_SHOES");
                    task.terminate();
                }
                if (tries == 10) {
                    player.sendMessage(ChatColor.RED + "We were unable to give your starting Miner Items. Please rejoin the server and we will try again! If you have tried this multiple times, please PM an Admin on Discord, or ask for help in chat!");
                    task.terminate();
                }
                tries++;
            }, 20, 60);
        }

        if (!player.isOp()) {
            Set<String> warpNames = warps.getKeys(false);
            for (String currentWarpName : warpNames) {
                if (player.hasPermission("CELL.WARP." + currentWarpName)) {
                    player.performCommand("warp " + currentWarpName);
                    break;
                }
            }
        }
    }
}