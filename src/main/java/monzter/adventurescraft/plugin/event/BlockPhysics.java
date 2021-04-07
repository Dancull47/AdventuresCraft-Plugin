package monzter.adventurescraft.plugin.event;

import monzter.adventurescraft.plugin.AdventuresCraft;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPhysicsEvent;

public class BlockPhysics implements Listener {
    AdventuresCraft plugin;

    public BlockPhysics(AdventuresCraft plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void cancelPhysics(BlockPhysicsEvent event) {
        String world = plugin.getServer().getWorlds().get(0).getName();
        if (!world.equals("Homes")) {
            event.setCancelled(true);
        }
    }
}
