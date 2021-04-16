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
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class Commands implements CommandExecutor, @Nullable TabCompleter {
    private final AdventuresCraft plugin;

    public Commands(AdventuresCraft plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = ((Player) sender).getPlayer();
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
                    player.sendMessage(textComponent);
                    return true;
                case "Bank":
                    if (player.hasPermission("bank.open.command")) {
                        player.performCommand("banks open");
                    } else {
                        final TextComponent textComponent2 = Component.text("You can only access your Bank at a nearby Enderchest! If you wish to use it in your Menu, purchase the ")
                                .color(NamedTextColor.RED)
                                .append(Component.text("Explorer Rank", NamedTextColor.GREEN))
                                .hoverEvent(Component.text(NamedTextColor.GREEN + "Click to visit the " + NamedTextColor.GOLD + TextDecoration.BOLD + "STORE" + NamedTextColor.GREEN + "!"))
                                .clickEvent(ClickEvent.openUrl("https://store.adventurescraft.net/category/Rank"))
                                .append(Component.text(NamedTextColor.RED + "!"));
                        player.sendMessage(textComponent2);
                    }
                    // BROKEN
                case "Pet":
                    if (args.length < 1) {
                        System.out.println("Test");
                        Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "dm open Pets " + player.getName());
                    } else if (args[0].toLowerCase().contains("summon")) {
                        player.performCommand("mpet open");
                    } else if (args[0].toLowerCase().contains("equip")) {
                        Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "dm open Pets " + player.getName());
                    }
                default:
                    return false;
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            List<String> commands = new ArrayList<>();
            List<String> arg1 = Arrays.asList("Summon", "SummonMenu", "Equip", "Equipped", "EquippedMenu");
            for (String typeChars : arg1) {
                if (args[0].length() < 1 || typeChars.toLowerCase().contains(args[0].toLowerCase())) {
                    commands.add(typeChars);
                }
            }
            return commands;
        }
        return null;
    }
}

