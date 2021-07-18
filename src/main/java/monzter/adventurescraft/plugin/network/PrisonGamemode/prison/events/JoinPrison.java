package monzter.adventurescraft.plugin.network.PrisonGamemode.prison.events;

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
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
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
            .append(Component.text(" to learn about our Prison and receive rewards!"));

    public JoinPrison(AdventuresCraft plugin, MMOItemsGive mmoItemsGive, PermissionLP permissionLP, YamlConfiguration warps) {
        this.plugin = plugin;
        this.mmoItemsGive = mmoItemsGive;
        this.permissionLP = permissionLP;
        this.warps = warps;
    }

    BossBar bossBar = Bukkit.createBossBar(ChatColor.WHITE + "Complete the " + ChatColor.GREEN + "Tutorial " + ChatColor.WHITE + "by using "
            + ChatColor.GREEN + "/Tutorial " + ChatColor.WHITE + "for a " + ChatColor.GREEN + "reward" + ChatColor.WHITE + "!", BarColor.GREEN, BarStyle.SOLID);

    @EventHandler
    private void onJoin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        if (!player.hasPermission("mines.tp.m")) {
            player.sendMessage(mining);
        }
        if (player.hasPermission("cmi.hologram.tutorial_pickaxe")) {
            player.sendMessage(tutorial);
            bossBar.addPlayer(player);
            Schedulers.sync().runLater(() -> bossBar.removePlayer(player), 1200);
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