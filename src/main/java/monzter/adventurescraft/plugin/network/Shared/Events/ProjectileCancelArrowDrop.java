package monzter.adventurescraft.plugin.network.Shared.Events;

import monzter.adventurescraft.plugin.AdventuresCraft;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class ProjectileCancelArrowDrop implements Listener {
    private final AdventuresCraft plugin;

    public ProjectileCancelArrowDrop(AdventuresCraft plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void arrowDespawn(ProjectileHitEvent event) {
        if (event.getEntity().getType() == EntityType.ARROW)
            switch (plugin.SERVER) {
                case "Prison":
                case "Cell":
                case "Adventure":
                case "Home":
                    event.getEntity().remove();
                    break;
            }
    }
}
