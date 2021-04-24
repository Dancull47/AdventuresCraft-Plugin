package monzter.adventurescraft.plugin.event.mining;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.util.Location;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import me.clip.placeholderapi.PlaceholderAPI;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.event.extras.WeightPrices;
import monzter.adventurescraft.plugin.utilities.acUtils;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class ChestInteract implements Listener {
    private final AdventuresCraft plugin;
    private final StateFlag prisonMineFlag;

    public ChestInteract(AdventuresCraft plugin, StateFlag prisonMineFlag) {
        this.plugin = plugin;
        this.prisonMineFlag = prisonMineFlag;
    }

    @EventHandler
    public void mine(PlayerInteractEvent event) {
        if (event.getClickedBlock() != null && event.getClickedBlock().getType().equals(Material.CHEST)) {
            Player player = event.getPlayer();
            RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
            RegionQuery query = container.createQuery();
            final Location location = BukkitAdapter.adapt(event.getClickedBlock().getLocation());
            if (inRegion(query, location)) {
                event.getClickedBlock().setType(Material.AIR);
                event.getClickedBlock().getLocation().createExplosion(1, false, false);
                player.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, event.getClickedBlock().getLocation().getX(),
                        event.getClickedBlock().getLocation().getY(),
                        event.getClickedBlock().getLocation().getZ(),
                        10, .75, .75, .75);
                player.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, event.getClickedBlock().getLocation().getX(),
                        event.getClickedBlock().getLocation().getY(),
                        event.getClickedBlock().getLocation().getZ(),
                        10, .5, .5, .5);
                acUtils.giveMMDropTable(player, "Treasure");
            }
        }
    }

    private boolean inRegion(RegionQuery query, Location location) {
        if (query.testState(location, null, prisonMineFlag)) {
            return true;
        }
        return false;
    }


}