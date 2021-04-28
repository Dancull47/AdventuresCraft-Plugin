package monzter.adventurescraft.plugin.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import co.aikar.commands.bukkit.contexts.OnlinePlayer;
import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import me.clip.placeholderapi.PlaceholderAPI;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.acUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class GeneralCommands extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;

    public GeneralCommands(AdventuresCraft plugin) {
        this.plugin = plugin;
    }

    @CommandAlias("vote")
    public void voteCommand(Player player) {
        final TextComponent vote = Component.text("You can")
                .color(NamedTextColor.GREEN)
                .append(Component.text(" Vote ", NamedTextColor.GOLD))
                .hoverEvent(Component.text("Click to visit Voting Guide!", NamedTextColor.GREEN))
                .clickEvent(ClickEvent.openUrl("https://www.adventurescraft.net/wiki/site/vote/"))
                .append(Component.text("for our Server, to receive awesome rewards every day!"));
        player.sendMessage(vote);
        Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "dm open Vote " + player.getName());
    }
    @CommandAlias("discord")
    public void discordCommand(Player player) {
        final TextComponent discord = Component.text("Join our ")
                .color(NamedTextColor.GREEN)
                .append(Component.text("Discord", NamedTextColor.BLUE, TextDecoration.BOLD))
                .hoverEvent(Component.text("Click to join the Discord!", NamedTextColor.GREEN))
                .clickEvent(ClickEvent.openUrl("https://discord.com/invite/bw4DztR"))
                .append(Component.text(" for"))
                .append(Component.text(" Giveaways, Support, and more", NamedTextColor.GOLD))
                .append(Component.text("!"));
        player.sendMessage(discord);
    }
    @CommandAlias("bank")
    public void bankCommand(Player player) {
        if (player.hasPermission("bank.open.command")) {
            player.performCommand("banks open");
        } else {
            final TextComponent bankDeny = Component.text("You can only access your Bank at a nearby Enderchest! If you wish to use it in your Menu, purchase the ")
                    .color(NamedTextColor.RED)
                    .append(Component.text("Explorer Rank", NamedTextColor.GREEN))
                    .hoverEvent(Component.text(NamedTextColor.GREEN + "Click to visit the " + NamedTextColor.GOLD + TextDecoration.BOLD + "STORE" + NamedTextColor.GREEN + "!"))
                    .clickEvent(ClickEvent.openUrl("https://store.adventurescraft.net/category/Rank"))
                    .append(Component.text(NamedTextColor.RED + "!"));
            player.sendMessage(bankDeny);
        }
    }
    @CommandAlias("pet")
    @CommandCompletion("summon|equip")
    public void petCommand(Player player, String arg) {
        if (arg.isEmpty()) {
            Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "dm open Pets " + player.getName());
        } else if (arg.toLowerCase().contains("summon")) {
            player.performCommand("mpet open");
        } else if (arg.toLowerCase().contains("equip")) {
            Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "dm open Pets " + player.getName());
        }
    }
    @CommandAlias("spawn")
    public void spawnCommand(Player player) {
        sendToSpawn(player);
    }
    @CommandAlias("ActiveQuests")
    public void activeQuestsCommand(Player player) {
        acUtils.consoleCommand("rpgmenu open default-Menus-menu.active " + player.getName());
    }
    @CommandAlias("UnclaimedQuests")
    public void unclaimedQuestsCommand(Player player) {
        acUtils.consoleCommand("rpgmenu open default-Menus-menu.unclaimed " + player.getName());
    }
    @CommandAlias("Quest")
    public void questCommand(Player player) {
        acUtils.consoleCommand("dm open Quests " + player.getName());
    }


    public void sendToSpawn(Player player) {
        if (player.getWorld().getName().equals("World")) {
            player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1f, 1);
            player.sendMessage(ChatColor.GREEN + "You've traveled to the " + ChatColor.YELLOW + "Yard" + ChatColor.GREEN + "!");
            player.teleport(new Location(player.getWorld(), 1181.5, 202, 1603.5, 89.8f, -0.7f));
        }
    }
}
