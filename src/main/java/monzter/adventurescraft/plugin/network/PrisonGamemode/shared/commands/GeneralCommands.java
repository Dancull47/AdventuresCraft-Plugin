package monzter.adventurescraft.plugin.network.PrisonGamemode.shared.commands;

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

public class GeneralCommands extends BaseCommand {

    private final TextComponent bankDeny = Component.text("You can only access your Bank at a nearby Enderchest! If you wish to use it from your Menu, purchase the ")
            .color(NamedTextColor.RED)
            .append(Component.text("Explorer Rank", NamedTextColor.GREEN))
            .hoverEvent(Component.text(NamedTextColor.GREEN + "Click to visit the " + NamedTextColor.GOLD + TextDecoration.BOLD + "STORE" + NamedTextColor.GREEN + "!"))
            .clickEvent(ClickEvent.openUrl("https://store.adventurescraft.net/category/Rank"))
            .append(Component.text("!", NamedTextColor.RED));
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

    @CommandAlias("bank|vault|ec|echest|enderchest")
    private void bankCommand(Player player) {
        if (player.hasPermission("bank.open.command")) {
            player.performCommand("banks open");
        } else {
            player.sendMessage(bankDeny);
            player.teleport(new Location(player.getWorld(), 1168.5, 202, 1606.6, -88.5f, -36.1f));
            soundManager.soundTeleport(player);
            player.closeInventory();
        }
    }

    @CommandAlias("spawn|yard")
    private void spawnCommand(Player player) {
        sendToSpawn(player);
    }

    @CommandAlias("ActiveQuests")
    private void activeQuestsCommand(Player player) {
        consoleCommand.consoleCommand("rpgmenu open default-Menus-menu.active " + player.getName());
    }

    @CommandAlias("UnclaimedQuests")
    private void unclaimedQuestsCommand(Player player) {
        consoleCommand.consoleCommand("rpgmenu open default-Menus-menu.unclaimed " + player.getName());
    }

    private void sendToSpawn(Player player) {
        if (player.getWorld().getName().equals("World")) {
            soundManager.soundTeleport(player);
            player.sendMessage(ChatColor.GREEN + "You've traveled to the " + ChatColor.YELLOW + "Yard" + ChatColor.GREEN + "!");
            player.teleport(new Location(player.getWorld(), 1181.5, 202, 1603.5, 89.8f, -0.7f));
        }
    }
}
