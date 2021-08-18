package monzter.adventurescraft.plugin.network.PrisonGamemode.cell.events;

import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.luckperms.PermissionLP;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import world.bentobox.bentobox.BentoBox;

public class Join implements Listener {
    private final AdventuresCraft plugin;
    private final PermissionLP permissionLP;
    private final BentoBox bentoBox;


    public Join(AdventuresCraft plugin, PermissionLP permissionLP, BentoBox bentoBox) {
        this.plugin = plugin;
        this.permissionLP = permissionLP;
        this.bentoBox = bentoBox;
    }

    @EventHandler
    private void onJoin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        if (!bentoBox.getIslands().hasIsland(Bukkit.getWorld("Cell"), player.getUniqueId()))
            player.performCommand("cell");
    }
}