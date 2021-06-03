package monzter.adventurescraft.plugin.network.AdventureGamemode.Adventure.Events;

import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.beton.BetonPointsManager;
import net.Indyuce.mmocore.api.event.CustomBlockMineEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class BlockBreak implements Listener {
    private final AdventuresCraft plugin;
    private final BetonPointsManager betonPointsManager;

    public BlockBreak(AdventuresCraft plugin, BetonPointsManager betonPointsManager) {
        this.plugin = plugin;
        this.betonPointsManager = betonPointsManager;
    }


    @EventHandler
    public void graveyard(CustomBlockMineEvent event) {
        switch (plugin.SERVER) {
            case "Adventure":
                if (event.getPlayer() != null)
                    betonPointsManager.givePoint(event.getPlayer(), "blocks." + event.getBlock().getType(), 1);
        }
    }
}