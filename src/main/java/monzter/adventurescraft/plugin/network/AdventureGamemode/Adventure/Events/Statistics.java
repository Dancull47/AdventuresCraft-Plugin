package monzter.adventurescraft.plugin.network.AdventureGamemode.Adventure.Events;

import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobDeathEvent;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.beton.BetonPointsManager;
import net.Indyuce.mmocore.api.event.CustomBlockMineEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class Statistics implements Listener {
    private final AdventuresCraft plugin;
    private final BetonPointsManager betonPointsManager;

    public Statistics(AdventuresCraft plugin, BetonPointsManager betonPointsManager) {
        this.plugin = plugin;
        this.betonPointsManager = betonPointsManager;
    }


    @EventHandler
    public void blockBreak(CustomBlockMineEvent event) {
        switch (plugin.SERVER) {
//            Use this condition to prevent tracking block breaks in Home
            case "Adventure":
                if (event.getPlayer() != null)
                    if (!event.isCancelled())
                        betonPointsManager.givePoint(event.getPlayer(), "blocks." + event.getBlock().getType(), 1);
        }
    }

    @EventHandler
    public void mobTrack(MythicMobDeathEvent event) {
        Player player = (Player) event.getKiller();
        if (player != null) {
            betonPointsManager.givePoint(player, "mobs." + event.getMobType().getInternalName(), 1);
            if (event.getMob().getFaction() != null)
                betonPointsManager.givePoint(player, "faction." + event.getMob().getFaction().toUpperCase(), 1);
        }
    }
}