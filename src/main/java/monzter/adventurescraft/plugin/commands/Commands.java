package monzter.adventurescraft.plugin.commands;

import monzter.adventurescraft.plugin.Language;
import monzter.adventurescraft.plugin.AdventuresCraft;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

public class Commands implements CommandExecutor {
    private final AdventuresCraft plugin;

    public Commands(AdventuresCraft plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        switch (command.getName()) {
            case "Discord":
                final TextComponent textComponent = Component.text("Join our ")
                        .color(NamedTextColor.GREEN)
                        .append(Component.text("Discord", NamedTextColor.BLUE, TextDecoration.BOLD))
                        .hoverEvent(Component.text("Click to join the Discord!", NamedTextColor.GREEN))
                        .clickEvent(ClickEvent.openUrl("https://discord.com/invite/bw4DztR"))
                        .append(Component.text(" for"))
                        .append(Component.text(" Giveaways, Supports, and more", NamedTextColor.GOLD))
                        .append(Component.text("!"));
                sender.sendMessage(textComponent);
                return true;
            case "Bank":
                if (sender.hasPermission("bank.open.command")){
                    Player player = sender.getServer().getPlayer(sender.getName());
                    player.performCommand("banks open");
                } else{
                    final TextComponent textComponent2 = Component.text("You can only access your Bank at a nearby Enderchest! If you wish to use it in your Menu, purchase the ")
                            .color(NamedTextColor.RED)
                            .append(Component.text("Explorer Rank", NamedTextColor.GREEN))
                            .hoverEvent(Component.text(NamedTextColor.GREEN + "Click to visit the " + NamedTextColor.GOLD + TextDecoration.BOLD + "STORE" + NamedTextColor.GREEN + "!"))
                            .clickEvent(ClickEvent.openUrl("https://store.adventurescraft.net/category/Rank"))
                            .append(Component.text(NamedTextColor.RED + "!"));
                    sender.sendMessage(textComponent2);
                }
            default:
                return false;
        }
    }
}

