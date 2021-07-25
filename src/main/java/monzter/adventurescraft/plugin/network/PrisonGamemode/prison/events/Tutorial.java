package monzter.adventurescraft.plugin.network.PrisonGamemode.prison.events;

import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.general.AreaCheck;
import monzter.adventurescraft.plugin.utilities.luckperms.PermissionLP;
import monzter.adventurescraft.plugin.utilities.mmoitems.MMOItemsGive;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class Tutorial implements Listener {
    private AdventuresCraft plugin;
    private MMOItemsGive mmoItemsGive;
    private PermissionLP permissionLP;
    private AreaCheck areaCheck;

    public Tutorial(AdventuresCraft plugin, MMOItemsGive mmoItemsGive, PermissionLP permissionLP, AreaCheck areaCheck) {
        this.plugin = plugin;
        this.mmoItemsGive = mmoItemsGive;
        this.permissionLP = permissionLP;
        this.areaCheck = areaCheck;
    }

    @EventHandler
    public void click(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getClickedBlock() != null && areaCheck.areaCheck(player, ChatColor.GREEN + "Tutorial")) {
            if (event.getClickedBlock().getType() == Material.CRAFTING_TABLE) {
                if (player.hasPermission("cmi.hologram.tutorial_pickaxe")) {
                    mmoItemsGive.giveMMOItem(player, "TOOL", "TUTORIAL_PICKAXE");
                    permissionLP.takePermission(player, "cmi.hologram.tutorial_pickaxe");
                }
            } else if (event.getClickedBlock().getType() == Material.LECTERN) {
                if (player.hasPermission("cmi.hologram.tutorial_gem")) {
                    mmoItemsGive.giveMMOItem(player, "GEM_STONE", "TUTORIAL_GEM");
                    permissionLP.takePermission(player, "cmi.hologram.tutorial_gem");
                }
            }
        }

    }
}
