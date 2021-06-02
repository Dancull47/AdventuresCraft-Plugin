package monzter.adventurescraft.plugin.network.Shared.Events;

import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.luckperms.PermissionLP;
import monzter.adventurescraft.plugin.utilities.mmoitems.MMOItemsGive;
import net.Indyuce.mmocore.api.player.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
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

        switch (plugin.getConfig().getString("Server")) {
            case "Prison":
            case "Cell":
            case "Adventure":
            case "Home":
                openBook(player);
                break;
            case "Lobby":
                player.sendMessage(ChatColor.GREEN + "Welcome back " + ChatColor.GOLD + player.getName() + ChatColor.GREEN + "!");
                break;
        }

        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> {

            player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());

            switch (plugin.getConfig().getString("Server")) {
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