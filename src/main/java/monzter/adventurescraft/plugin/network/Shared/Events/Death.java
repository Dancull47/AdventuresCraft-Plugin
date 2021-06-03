package monzter.adventurescraft.plugin.network.Shared.Events;

import monzter.adventurescraft.plugin.AdventuresCraft;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class Death implements Listener {
    private final AdventuresCraft plugin;

    public Death(AdventuresCraft plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    private void onDeath(PlayerDeathEvent event) {
        final Player player = event.getEntity();
        player.spigot().respawn();
    }
}
