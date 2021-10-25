package monzter.adventurescraft.plugin.network.AdventureGamemode.Adventure.Events.MythicMobs;

import monzter.adventurescraft.plugin.AdventuresCraft;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.HashMap;
import java.util.UUID;

public class DamageTracker implements Listener {

    private final AdventuresCraft plugin;
    public static HashMap<UUID, Double> lastDamage = new HashMap<>();

//    Used with the <caster.last_attack_damage> placeholder, but can be used for other things!

    public DamageTracker(AdventuresCraft plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void w(EntityDamageByEntityEvent event) {
        if (lastDamage.containsKey(event.getDamager().getUniqueId()))
            lastDamage.replace(event.getDamager().getUniqueId(), event.getFinalDamage());
        else
            lastDamage.put(event.getDamager().getUniqueId(), event.getFinalDamage());
    }
}
