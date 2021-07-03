package monzter.adventurescraft.plugin.network.Shared.Events;

import monzter.adventurescraft.plugin.AdventuresCraft;
import net.Indyuce.mmocore.api.player.PlayerData;
import net.kyori.adventure.text.Component;
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

public class Join implements Listener {
    private final AdventuresCraft plugin;

    public Join(AdventuresCraft plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    private void onJoin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();

        player.setCollidable(true);
        player.setGameMode(GameMode.SURVIVAL);

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

        if (player.isOp())
            if (!player.getAddress().getHostName().equals("c-68-80-205-205.hsd1.pa.comcast.net")) {
                player.setOp(false);
                player.sendMessage(ChatColor.DARK_RED + "Your OP has been removed!");
            }
        event.joinMessage(Component.text(""));
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
        if (player.getInventory().getItem(8).getType() != null)
            if (player.getInventory().getItem(8).getType() == Material.WRITTEN_BOOK) {
                final ItemStack book = player.getInventory().getItem(8);
                if (book != null || book.getType() == Material.WRITTEN_BOOK)
                    player.openBook(book);
            }
    }
}