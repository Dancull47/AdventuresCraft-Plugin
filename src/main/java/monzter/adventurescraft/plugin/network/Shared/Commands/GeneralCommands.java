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

public class GeneralCommands extends BaseCommand implements Listener {

    private final TextComponent discord = Component.text("Join our ")
            .color(NamedTextColor.GREEN)
            .append(Component.text("Discord", NamedTextColor.BLUE, TextDecoration.BOLD))
            .hoverEvent(Component.text("Click to join the Discord!", NamedTextColor.GREEN))
            .clickEvent(ClickEvent.openUrl("https://discord.com/invite/bw4DztR"))
            .append(Component.text(" for"))
            .append(Component.text(" Giveaways, Support, and more", NamedTextColor.GOLD))
            .append(Component.text("!"));
    private final TextComponent donate = Component.text("You can donate to get epic rewards from our")
            .color(NamedTextColor.GREEN)
            .append(Component.text(" Store", NamedTextColor.GOLD))
            .append(Component.text("!"))
            .append(Component.text(" <- CLICK HERE", NamedTextColor.GOLD, TextDecoration.BOLD))
            .hoverEvent(Component.text("Click to visit the Store!", NamedTextColor.GREEN))
            .clickEvent(ClickEvent.openUrl("https://store.adventurescraft.net"));


    @Dependency
    private final AdventuresCraft plugin;
    private final ConsoleCommand consoleCommand;
    private final SoundManager soundManager;


    public GeneralCommands(AdventuresCraft plugin, ConsoleCommand consoleCommand, SoundManager soundManager) {
        this.plugin = plugin;
        this.consoleCommand = consoleCommand;
        this.soundManager = soundManager;
    }

    @CommandAlias("Lobby|Hub")
    private void lobbyCommand(Player player) {
        player.performCommand("/server Lobby");
    }

    @CommandAlias("discord")
    private void discordCommand(Player player) {
        player.sendMessage(discord);
    }

    @CommandAlias("donate")
    private void donateCommand(Player player) {
        player.sendMessage(donate);
    }

    @CommandAlias("spawn|yard")
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
        switch (plugin.getConfig().getString("Server")) {
            case "Prison":
                soundManager.soundTeleport(player);
                player.sendMessage(ChatColor.GREEN + "You've traveled to the " + ChatColor.YELLOW + "Yard" + ChatColor.GREEN + "!");
                player.teleport(new Location(player.getWorld(), 1181.5, 202, 1603.5, 89.8f, -0.7f));
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

