package monzter.adventurescraft.plugin.event;

import monzter.adventurescraft.plugin.AdventuresCraft;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class ProjectileHit implements Listener {
    private final AdventuresCraft plugin;

    public ProjectileHit(AdventuresCraft plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void arrowDespawn(ProjectileHitEvent event){
        if (event.getEntity().getType() == EntityType.ARROW){
            event.getEntity().remove();
        }
    }
}
