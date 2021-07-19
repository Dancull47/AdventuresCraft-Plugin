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
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class GeneralCommands extends BaseCommand {

    private final TextComponent bankDeny = Component.text("You can only access your Bank at a nearby Enderchest! If you wish to use it from your Menu, purchase the ")
            .color(NamedTextColor.RED)
            .append(Component.text("Explorer Rank", NamedTextColor.GREEN))
            .hoverEvent(Component.text(NamedTextColor.GREEN + "Click to visit the " + NamedTextColor.GOLD + TextDecoration.BOLD + "STORE" + NamedTextColor.GREEN + "!"))
            .clickEvent(ClickEvent.openUrl("https://store.adventurescraft.net/category/Rank"))
            .append(Component.text("!", NamedTextColor.RED));


    @Dependency
    private final AdventuresCraft plugin;
    private final ConsoleCommand consoleCommand;
    private final SoundManager soundManager;


    public GeneralCommands(AdventuresCraft plugin, ConsoleCommand consoleCommand, SoundManager soundManager) {
        this.plugin = plugin;
        this.consoleCommand = consoleCommand;
        this.soundManager = soundManager;
    }

    @CommandAlias("bank|vault|ec|echest|enderchest")
    private void bankCommand(Player player) {
        if (player.hasPermission("bank.open.command")) {
            player.performCommand("banks open");
        } else {
            player.sendMessage(bankDeny);
            player.teleport(new Location(player.getWorld(), 1682.5, 28, 3832.5, 0.4f, 21.8f));
            soundManager.soundTeleport(player);
            player.closeInventory();
        }
    }

//    @CommandAlias("ActiveQuests")
//    private void activeQuestsCommand(Player player) {
//        consoleCommand.consoleCommand("rpgmenu open default-Menus-menu.active " + player.getName());
//    }
//
//    @CommandAlias("UnclaimedQuests")
//    private void unclaimedQuestsCommand(Player player) {
//        consoleCommand.consoleCommand("rpgmenu open default-Menus-menu.unclaimed " + player.getName());
//    }

    @CommandAlias("Tutorial")
    private void tutorial(Player player) {
        player.performCommand("warp Tutorial");
    }
}

