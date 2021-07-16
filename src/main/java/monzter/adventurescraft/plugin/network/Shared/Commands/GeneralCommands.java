package monzter.adventurescraft.plugin.network.Shared.Commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Dependency;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.general.ConsoleCommand;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

public class GeneralCommands extends BaseCommand implements Listener, PluginMessageListener {

    private final TextComponent discord = Component.text("Join our ")
            .color(NamedTextColor.GREEN)
            .append(Component.text("Discord", NamedTextColor.BLUE, TextDecoration.BOLD))
            .hoverEvent(Component.text("Click to join the Discord!", NamedTextColor.GREEN))
            .clickEvent(ClickEvent.openUrl("https://discord.com/invite/bw4DztR"))
            .append(Component.text(" for"))
            .append(Component.text(" Giveaways, Support, and more", NamedTextColor.GOLD))
            .append(Component.text("!"));
    private final TextComponent website = Component.text("You can checkout our ")
            .color(NamedTextColor.GREEN)
            .append(Component.text("Website ", NamedTextColor.GOLD))
            .append(Component.text("for more info about our Server!"))
            .hoverEvent(Component.text("Click to visit the Website!", NamedTextColor.GREEN))
            .clickEvent(ClickEvent.openUrl("http://adventurescraft.net"));
    final TextComponent wiki = Component.text("You can view official information about our Gamemodes on our ")
            .append(Component.text("Wiki", NamedTextColor.GOLD))
            .append(Component.text("!"))
            .hoverEvent(Component.text("Click to visit the Wiki!", NamedTextColor.GREEN))
            .clickEvent(ClickEvent.openUrl("https://www.adventurescraft.net/wiki/site/"))
            .color(NamedTextColor.GRAY);


    @Dependency
    private final AdventuresCraft plugin;
    private final ConsoleCommand consoleCommand;
    private final SoundManager soundManager;


    public GeneralCommands(AdventuresCraft plugin, ConsoleCommand consoleCommand, SoundManager soundManager) {
        this.plugin = plugin;
        this.consoleCommand = consoleCommand;
        this.soundManager = soundManager;
        plugin.getServer().getMessenger().registerOutgoingPluginChannel(plugin, "BungeeCord");
        plugin.getServer().getMessenger().registerIncomingPluginChannel(plugin, "BungeeCord", this);

    }

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
    }

    @CommandAlias("Lobby|Hub")
    private void lobbyCommand(Player player) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);
        try {
            out.writeUTF("Connect");
            out.writeUTF("Lobby");
        } catch (Exception e) {
            player.sendMessage(ChatColor.RED + "Error travelling to the Lobby! Report this to Monzter#4951 on Discord!");
            return;
        }
        player.sendPluginMessage(plugin, "BungeeCord", b.toByteArray());
    }

    @CommandAlias("Prison")
    private void prisonCommand(Player player) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);
        try {
            out.writeUTF("Connect");
            out.writeUTF("Prison");
        } catch (Exception e) {
            player.sendMessage(ChatColor.RED + "Error travelling to the Prison! Report this to Monzter#4951 on Discord!");
            return;
        }
        player.sendPluginMessage(plugin, "BungeeCord", b.toByteArray());
    }

    @CommandAlias("Cell")
    private void cellCommand(Player player) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);
        try {
            out.writeUTF("Connect");
            out.writeUTF("Cell");
        } catch (Exception e) {
            player.sendMessage(ChatColor.RED + "Error travelling to the Cell! Report this to Monzter#4951 on Discord!");
            return;
        }
        player.sendPluginMessage(plugin, "BungeeCord", b.toByteArray());
    }

    @CommandAlias("Adventure")
    private void adventureCommand(Player player) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);
        try {
            out.writeUTF("Connect");
            out.writeUTF("Live2");
        } catch (Exception e) {
            player.sendMessage(ChatColor.RED + "Error travelling to the Live2! Report this to Monzter#4951 on Discord!");
            return;
        }
        player.sendPluginMessage(plugin, "BungeeCord", b.toByteArray());
    }

    @CommandAlias("Home")
    private void homeCommand(Player player) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);
        try {
            out.writeUTF("Connect");
            out.writeUTF("Home");
        } catch (Exception e) {
            player.sendMessage(ChatColor.RED + "Error travelling to the Home! Report this to Monzter#4951 on Discord!");
            return;
        }
        player.sendPluginMessage(plugin, "BungeeCord", b.toByteArray());
    }

    @CommandAlias("Test")
    private void testCommand(Player player) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);
        try {
            out.writeUTF("Connect");
            out.writeUTF("Test");
        } catch (Exception e) {
            player.sendMessage(ChatColor.RED + "Error travelling to the Test! Report this to Monzter#4951 on Discord!");
            return;
        }
        player.sendPluginMessage(plugin, "BungeeCord", b.toByteArray());
    }

    @CommandAlias("discord")
    private void discordCommand(Player player) {
        player.sendMessage(discord);
    }

    @CommandAlias("wiki")
    private void wikiCommand(Player player) {
        player.sendMessage(wiki);
    }

    @CommandAlias("website")
    private void websiteCommand(Player player) {
        player.sendMessage(website);
    }

    @CommandAlias("spawn|yard|town")
    private void spawnCommand(Player player) {
        sendToSpawn(player);
    }

    @EventHandler
    public void preProcessCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        if (event.getMessage().equalsIgnoreCase("/plugins"))
            if (!player.isOp()) {
                event.setCancelled(true);
                player.sendMessage(ChatColor.RED + "You don't have access to this silly!");
            }
    }

    private void sendToSpawn(Player player) {
        switch (plugin.SERVER) {
            case "Prison":
                soundManager.soundTeleport(player);
                player.sendMessage(ChatColor.GREEN + "You've traveled to the " + ChatColor.YELLOW + "Town" + ChatColor.GREEN + "!");
                player.teleport(new Location(player.getWorld(), 1680.5, 28, 3824.5, 90.5f, 0.0f));
                break;
            case "Cell":
            case "Home":
                player.performCommand("warp Spawn");
                break;
            case "Adventure":
                soundManager.soundTeleport(player);
                player.sendMessage(ChatColor.GREEN + "You've traveled to the " + ChatColor.YELLOW + "Town" + ChatColor.GREEN + "!");
                player.teleport(new Location(player.getWorld(), 30.5, 17, -2.5, 89f, 0.0f));
                break;
            case "Lobby":
                soundManager.soundTeleport(player);
                player.sendMessage(ChatColor.GREEN + "You've traveled to the " + ChatColor.YELLOW + "spawn" + ChatColor.GREEN + "!");
                player.teleport(new Location(player.getWorld(), 0.5, 143, 0.5, 92.4f, 3.5f));
                break;
        }
    }

}

