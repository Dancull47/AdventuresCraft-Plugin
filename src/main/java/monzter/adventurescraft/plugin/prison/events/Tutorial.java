package monzter.adventurescraft.plugin.prison.events;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import com.gmail.filoghost.holographicdisplays.api.VisibilityManager;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.luckperms.PermissionLP;
import monzter.adventurescraft.plugin.utilities.mmoitems.MMOItemsGive;
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

    private final Location pickaxe = new Location(Bukkit.getWorld("World"), 1990, 176, 1593);
    private final Location gem = new Location(Bukkit.getWorld("World"), 1998, 175, 1564);

    @EventHandler
    public void click(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getClickedBlock() != null) {
            if (event.getClickedBlock().getLocation().serialize().equals(pickaxe.serialize())) {
                if (player.hasPermission("cmi.hologram.tutorial_pickaxe")) {
                    mmoItemsGive.giveMMOItem(player, "TOOL", "TUTORIAL_PICKAXE");
                    permissionLP.takePermission(player, "cmi.hologram.tutorial_pickaxe");
                }
            } else if (event.getClickedBlock().getLocation().serialize().equals(gem.serialize())) {
                if (player.hasPermission("cmi.hologram.tutorial_gem")) {
                    mmoItemsGive.giveMMOItem(player, "GEM_STONE", "TUTORIAL_GEM");
                    permissionLP.takePermission(player, "cmi.hologram.tutorial_gem");
                }
            }
        }
    }

    public void spawnHolo(Location location) {
        Hologram hologram = HologramsAPI.createHologram(plugin, location);
        VisibilityManager visibilityManager = hologram.getVisibilityManager();
        visibilityManager.setVisibleByDefault(true);


    }
}
