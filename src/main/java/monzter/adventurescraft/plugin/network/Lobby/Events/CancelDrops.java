package monzter.adventurescraft.plugin.network.Lobby.Events;

import monzter.adventurescraft.plugin.AdventuresCraft;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

public class CancelDrops implements Listener {
    private final AdventuresCraft plugin;

    public CancelDrops(AdventuresCraft plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    private void cancelDrop(PlayerDropItemEvent event) {
        final Player player = event.getPlayer();
        ItemStack itemStack = event.getItemDrop().getItemStack();
        if (itemStack.getType() != Material.COMPASS) {
            event.getItemDrop().remove();
            event.setCancelled(true);
        }
    }

    @EventHandler
    private void removeTrail(InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof Player) {
            Player player = (Player) event.getWhoClicked();
            if (player != null && event.getInventory().getHolder() != null && event.getInventory().getHolder().equals(player)) {
                if (event.getCurrentItem() != null)
                    switch (event.getSlot()) {
                        case 37:
                            player.sendMessage(ChatColor.RED + "You unequipped the " + event.getCurrentItem().getItemMeta().getDisplayName()
                                    + ChatColor.RED + "!");
                            player.getInventory().setItem(event.getSlot(), new ItemStack(Material.AIR));
                            event.setCancelled(true);
                            break;
                    }
            }
        }
    }
}
