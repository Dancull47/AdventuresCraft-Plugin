package monzter.adventurescraft.plugin.event;

import monzter.adventurescraft.plugin.AdventuresCraft;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class ProjectileCancelArrowDrop implements Listener {
    private final AdventuresCraft plugin;
    private final boolean enabled;

    public ProjectileCancelArrowDrop(AdventuresCraft plugin) {
        enabled = plugin.getConfig().getBoolean("Arrow-Despawn", true);
        this.plugin = plugin;
    }

    @EventHandler
    public void arrowDespawn(ProjectileHitEvent event){
        if (event.getEntity().getType() == EntityType.ARROW){
            if (enabled){
                event.getEntity().remove();
            }
        }
    }
}
