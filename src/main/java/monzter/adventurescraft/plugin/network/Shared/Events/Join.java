package monzter.adventurescraft.plugin.network.Shared.Events;

import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.luckperms.PermissionLP;
import net.Indyuce.mmocore.api.player.PlayerData;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.util.ArrayList;

public class Join implements Listener {
    private final AdventuresCraft plugin;
    private final PermissionLP permissionLP;


    public Join(AdventuresCraft plugin, PermissionLP permissionLP) {
        this.plugin = plugin;
        this.permissionLP = permissionLP;
    }

    final TextComponent page1 = Component.text("AdventuresCraft \n", NamedTextColor.RED, TextDecoration.BOLD)
            .append(Component.text("September 19th:", NamedTextColor.BLACK)
                    .decoration(TextDecoration.BOLD, false))

            .append(Component.text("\n\n"))
            .append(Component.text("- ", NamedTextColor.BLACK))
            .append(Component.text("Quests Redone", NamedTextColor.GOLD)
                    .decoration(TextDecoration.BOLD, false))
            .append(Component.text("\n"))
            .append(Component.text("Every single quest has been improved!", NamedTextColor.BLACK)
                    .decoration(TextDecoration.BOLD, false))

            .append(Component.text("\n\n"))
            .append(Component.text("- ", NamedTextColor.BLACK))
            .append(Component.text("The Cavern", NamedTextColor.GOLD)
                    .decoration(TextDecoration.BOLD, false))
            .append(Component.text("\n"))
            .append(Component.text("A new area has been introduced to the world!", NamedTextColor.BLACK)
                    .decoration(TextDecoration.BOLD, false))

            .append(Component.text("\n\n"))
            .append(Component.text("Patch Notes within our ", NamedTextColor.DARK_GRAY)
                    .decoration(TextDecoration.BOLD, false))
            .append(Component.text("Discord", NamedTextColor.BLUE, TextDecoration.BOLD)
                    .hoverEvent(Component.text("Click to join Discord!", NamedTextColor.YELLOW))
                    .clickEvent(ClickEvent.openUrl("https://discord.gg/bw4DztR")));

    final TextComponent page2 = Component.text("  Important Links  ", NamedTextColor.RED, TextDecoration.BOLD)
            .append(Component.text("----------------", NamedTextColor.BLACK))
            .append(Component.text("\n\n"))
            .append(Component.text("Discord", NamedTextColor.BLUE, TextDecoration.BOLD)
                    .hoverEvent(Component.text("Click to join Discord!", NamedTextColor.YELLOW))
                    .clickEvent(ClickEvent.openUrl("https://discord.gg/bw4DztR")))
            .append(Component.text("\n"))
            .append(Component.text("Get Support, Trade, View Updates, and much more in the Discord!", NamedTextColor.BLACK, TextDecoration.BOLD)
                    .decoration(TextDecoration.BOLD, false))
            .append(Component.text("\n\n"))
            .append(Component.text("Website", NamedTextColor.GREEN, TextDecoration.BOLD)
                    .hoverEvent(Component.text("Click to visit our Website!", NamedTextColor.YELLOW))
                    .clickEvent(ClickEvent.openUrl("https://www.adventurescraft.net/")))
            .append(Component.text("\n"))
            .append(Component.text("Store", NamedTextColor.DARK_GREEN, TextDecoration.BOLD)
                    .hoverEvent(Component.text("Click to visit our Store!", NamedTextColor.YELLOW))
                    .clickEvent(ClickEvent.openUrl("https://store.adventurescraft.net/")))
            .append(Component.text("\n"))
            .append(Component.text("Wiki", NamedTextColor.GOLD, TextDecoration.BOLD)
                    .hoverEvent(Component.text("Click to visit our Wiki!", NamedTextColor.YELLOW))
                    .clickEvent(ClickEvent.openUrl("https://www.adventurescraft.net/wiki/site/")))
            .append(Component.text("\n"))
            .append(Component.text("Rules", NamedTextColor.RED, TextDecoration.BOLD)
                    .hoverEvent(Component.text("Click to view our Rules!", NamedTextColor.YELLOW))
                    .clickEvent(ClickEvent.openUrl("https://www.adventurescraft.net/html/rules.html")));


    @EventHandler
    private void onJoin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();

        player.setCollidable(true);
        player.setGameMode(GameMode.SURVIVAL);

        event.joinMessage(Component.text(""));

        switch (plugin.SERVER) {
            case "Prison":
            case "Cell":
            case "Adventure":
            case "Home":
                openBook(player);
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> {
                    PlayerData playerData = PlayerData.get(player.getUniqueId());
                    playerData.giveMana(999);
                }, 20L);
                break;
            case "Lobby":
                player.sendMessage(ChatColor.GREEN + "Welcome back " + ChatColor.GOLD + player.getName() + ChatColor.GREEN + "!");
                break;
        }

        switch (plugin.SERVER) {
            case "Adventure":
            case "Home":
                if (player.hasPermission("RP.DOWNLOAD")) {
                    player.performCommand("rp download");
                    player.sendMessage(ChatColor.GREEN + "The " + ChatColor.GOLD + "Resource Pack Prompt " + ChatColor.GREEN + "has been sent!");
                } else
                    player.sendMessage(ChatColor.RED + "We recommend using the " + ChatColor.GOLD + "Resource Pack " + ChatColor.RED + "for an enhanced experience! You can enable it by using " + ChatColor.GOLD + "/RP Enable" + ChatColor.GREEN + "!");
                if (!player.hasPermission("Flint.Talked"))
                    if (player.getWorld().getName().equals("Spawn"))
                        player.sendMessage(ChatColor.GREEN + "Welcome, speak with " + ChatColor.GOLD + "Flint " + ChatColor.GREEN + "in-front of you!");
                break;
        }

        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> {
            player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
            switch (plugin.SERVER) {
                case "Prison":
                case "Adventure":
                case "Lobby":
                    player.performCommand("spawn");
                    break;
                case "Cell":
                case "Home":
                    player.performCommand("home");
                    break;
            }
        }, 20L);

//        if (player.isOp())
//            if (!player.getAddress().getHostName().equals("***REMOVED***") || !player.getAddress().getHostName().equals("127.0.0.1 ")) {
//                player.setOp(false);
//                player.sendMessage(ChatColor.DARK_RED + "Your OP has been removed!");
//            }
    }

    @EventHandler
    public void onKick(PlayerKickEvent event) {
        event.leaveMessage(Component.text(""));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        event.quitMessage(Component.text(""));
    }


    private void openBook(Player player) {
        ItemStack book = new ItemStack(Material.WRITTEN_BOOK, 1);
        BookMeta bm = (BookMeta) book.getItemMeta();
        bm.setAuthor("Adventurer");
        bm.setTitle("AdventuresCraft");
        ArrayList<Component> pages = new ArrayList<>();

        pages.add(page1);
        pages.add(page2);

        bm.pages(pages);
        book.setItemMeta(bm);

        final ItemStack old = player.getInventory().getItem(8);
        player.getInventory().setItem(8, book);
        player.openBook(book);
        player.getInventory().setItem(8, old);

//        if (player.getInventory() != null && player.getInventory().getItem(8).getType() != null)
//            if (player.getInventory().getItem(8).getType() == Material.WRITTEN_BOOK) {
//                final ItemStack book = player.getInventory().getItem(8);
//                if (book != null || book.getType() == Material.WRITTEN_BOOK)
//                    player.openBook(book);
//            }
    }
}