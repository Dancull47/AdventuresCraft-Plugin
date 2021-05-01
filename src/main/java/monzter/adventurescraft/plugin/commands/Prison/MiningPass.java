package monzter.adventurescraft.plugin.commands.Prison;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Dependency;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.bukkit.ConsoleCommand;
import monzter.adventurescraft.plugin.utilities.bukkit.SoundManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class MiningPass extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final ConsoleCommand consoleCommand;
    private final SoundManager soundManager;

    public MiningPass(AdventuresCraft plugin, ConsoleCommand consoleCommand, SoundManager soundManager) {
        this.plugin = plugin;
        this.consoleCommand = consoleCommand;
        this.soundManager = soundManager;
    }

    @CommandAlias("miningpass")
    public void statCommand(Player player) {
            if (!player.hasPermission("MININGPASS.PURCHASED"))
                player.sendMessage(
                        Component.text("You can purchase the")
                                .color(NamedTextColor.GREEN)
                                .append(Component.text(" Mining Pass ", NamedTextColor.YELLOW, TextDecoration.BOLD))
                                .hoverEvent(Component.text("Click to visit the Store!", NamedTextColor.GREEN))
                                .clickEvent(ClickEvent.openUrl("https://store.adventurescraft.net"))
                                .append(Component.text("for additional rewards!")));
        consoleCommand.consoleCommand("dm open MiningPass " + player.getName());
            soundManager.playSound(player, Sound.BLOCK_ENDER_CHEST_OPEN, 1, 2);
        }
}

