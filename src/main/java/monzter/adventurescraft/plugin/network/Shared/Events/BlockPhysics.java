package monzter.adventurescraft.plugin.network.Shared.Events;

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
        switch (plugin.getConfig().getString("Server")) {
            case "Prison":
            case "Adventure":
                event.setCancelled(true);
                break;
        }
    }
}
