package monzter.adventurescraft.plugin.network.PrisonGamemode.cell.events;

import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.luckperms.PermissionLP;
import monzter.adventurescraft.plugin.utilities.mmoitems.MMOItemsGive;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import world.bentobox.bentobox.BentoBox;

public class JoinCell implements Listener {
    private final AdventuresCraft plugin;
    private final MMOItemsGive mmoItemsGive;
    private final PermissionLP permissionLP;
    private final BentoBox bentoBox;

    public JoinCell(AdventuresCraft plugin, MMOItemsGive mmoItemsGive, PermissionLP permissionLP, BentoBox bentoBox) {
        this.plugin = plugin;
        this.mmoItemsGive = mmoItemsGive;
        this.permissionLP = permissionLP;
        this.bentoBox = bentoBox;
    }

    @EventHandler
    private void onJoin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        }
}