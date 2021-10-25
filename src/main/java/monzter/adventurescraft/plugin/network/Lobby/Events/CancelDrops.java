package monzter.adventurescraft.plugin.network.Lobby.Events;

import monzter.adventurescraft.plugin.AdventuresCraft;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
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
}
