package monzter.adventurescraft.plugin.network.Shared.Events;

import io.lumine.mythic.utils.Schedulers;
import monzter.adventurescraft.plugin.AdventuresCraft;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class Death implements Listener {
    private final AdventuresCraft plugin;

    public Death(AdventuresCraft plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    private void onDeath(PlayerDeathEvent event) {
        final Player player = event.getEntity();
        event.setDeathMessage("");
        Schedulers.sync().runLater(new BukkitRunnable() {
            @Override
            public void run() {
                if (player.getHealth() == 0)
                    player.spigot().respawn();
            }
        }, 1);
    }
}
