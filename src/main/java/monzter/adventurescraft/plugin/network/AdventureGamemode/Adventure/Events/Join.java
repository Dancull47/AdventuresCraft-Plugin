package monzter.adventurescraft.plugin.network.AdventureGamemode.Adventure.Events;

import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.network.AdventureGamemode.Enums.Warps;
import monzter.adventurescraft.plugin.utilities.luckperms.PermissionLP;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Join implements Listener {
    private final AdventuresCraft plugin;
    private final PermissionLP permissionLP;

    public Join(AdventuresCraft plugin, PermissionLP permissionLP) {
        this.plugin = plugin;
        this.permissionLP = permissionLP;
    }


    @EventHandler
    public void warp(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (!player.isOp())
            for (Warps warp : Warps.values())
                if (player.hasPermission("HOME.WARP." + warp.name().toUpperCase())) {
                    player.performCommand("warp " + warp.getWarpName());
                    break;
                }
    }
}