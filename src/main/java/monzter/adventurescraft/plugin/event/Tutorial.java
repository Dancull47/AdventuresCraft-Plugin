package monzter.adventurescraft.plugin.event;

import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.luckperms.PermissionLP;
import monzter.adventurescraft.plugin.utilities.mmoitems.MMOItemsGive;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class Tutorial implements Listener {
    private AdventuresCraft plugin;
    private MMOItemsGive mmoItemsGive;
    private PermissionLP permissionLP;

    public Tutorial(AdventuresCraft plugin, MMOItemsGive mmoItemsGive, PermissionLP permissionLP) {
        this.plugin = plugin;
        this.mmoItemsGive = mmoItemsGive;
        this.permissionLP = permissionLP;
    }

    private final Location location = new Location(Bukkit.getWorld("World"), 1990, 176, 1593);

    @EventHandler
    public void click(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getClickedBlock().getLocation().serialize().equals(location.serialize())) {
            if (!player.hasPermission("TUTORIAL.1")) {
                mmoItemsGive.giveMMOItem(player, "TOOL", "TUTORIAL_PICKAXE");
                permissionLP.givePermission(player, "TUTORIAL.1");
            }
        }
    }
}
