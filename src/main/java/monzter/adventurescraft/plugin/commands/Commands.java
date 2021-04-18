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
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.*;

public class Commands implements CommandExecutor, @Nullable TabCompleter {
    private final AdventuresCraft plugin;
    private HashMap<UUID, Integer> codeAttempts = new HashMap<>();

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
                    return true;
                case "Pet":
                    if (args.length < 1) {
                        Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "dm open Pets " + player.getName());
                    } else if (args[0].toLowerCase().contains("summon")) {
                        player.performCommand("mpet open");
                    } else if (args[0].toLowerCase().contains("equip")) {
                        Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "dm open Pets " + player.getName());
                    }
                    return true;
                case "Spawn":
                    sendToSpawn(player);
                    return true;
                case "ActiveQuests":
                    Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "rpgmenu open default-Menus-menu.active " + player.getName());
                    return true;
                case "UnclaimedQuests":
                    Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "rpgmenu open default-Menus-menu.unclaimed " + player.getName());
                    return true;
                case "Quest":
                    Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "dm open Quests " + player.getName());
                    return true;
                default:
                    return false;
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (command.getName().toLowerCase().equals("pet")) {
            if (args.length == 1) {
                List<String> arguments = new ArrayList<>(Arrays.asList("Summon", "Equipped"));
                return arguments;
            }
        }
        return null;
    }

    public void sendToSpawn(Player player) {
        if (player.getWorld().getName().equals("World")) {
            player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1f, 1);
            player.sendMessage(ChatColor.GREEN + "You've traveled to the " + ChatColor.YELLOW + "Yard" + ChatColor.GREEN + "!");
            player.teleport(new Location(player.getWorld(), 1181.5, 202, 1603.5, 89.8f, -0.7f));
        }
    }
}

