package monzter.adventurescraft.plugin.network.AdventureGamemode.Adventure.Events;

import monzter.adventurescraft.plugin.AdventuresCraft;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class FireDamage implements Listener {
    private final AdventuresCraft plugin;


    public FireDamage(AdventuresCraft plugin) {
        this.plugin = plugin;
    }


    @EventHandler
    public void fireDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (event.getCause().equals(EntityDamageEvent.DamageCause.FIRE) ||
                    event.getCause().equals(EntityDamageEvent.DamageCause.LAVA) ||
                    event.getCause().equals(EntityDamageEvent.DamageCause.FIRE_TICK))
                player.damage(player.getHealth() * .1);
        }
    }
}
