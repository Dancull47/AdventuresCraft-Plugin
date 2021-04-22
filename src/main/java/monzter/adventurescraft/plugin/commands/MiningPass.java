package monzter.adventurescraft.plugin.commands;

import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.acUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MiningPass implements CommandExecutor {
    private final AdventuresCraft plugin;

    public MiningPass(AdventuresCraft plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender != null) {
            Player player = ((Player) sender).getPlayer();
            if (args.length < 1) {
                if (!sender.hasPermission("MININGPASS.PURCHASED"))
                    player.sendMessage(
                            Component.text("You can purchase the")
                            .color(NamedTextColor.GREEN)
                            .append(Component.text(" Mining Pass ", NamedTextColor.YELLOW, TextDecoration.BOLD))
                            .hoverEvent(Component.text("Click to visit the Store!", NamedTextColor.GREEN))
                            .clickEvent(ClickEvent.openUrl("https://store.adventurescraft.net"))
                            .append(Component.text("for additional rewards!")));
                acUtils.consoleCommand("dm open MiningPass " + player.getName());
                acUtils.playSound(player, Sound.BLOCK_ENDER_CHEST_OPEN, 1, 2);
                return true;
            }
        }
        return false;
    }
}

