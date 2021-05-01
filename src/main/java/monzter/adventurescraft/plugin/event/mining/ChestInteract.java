package monzter.adventurescraft.plugin.event.mining;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.util.Location;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import me.lucko.helper.random.RandomSelector;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.dropTables.Treasure;
import monzter.adventurescraft.plugin.utilities.enums.Rarity;
import monzter.adventurescraft.plugin.utilities.mmoitems.DropTablesDelivery;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class ChestInteract implements Listener {
    private final AdventuresCraft plugin;
    private final StateFlag prisonMineFlag;
    private final DropTablesDelivery dropTablesDelivery;

    public ChestInteract(AdventuresCraft plugin, StateFlag prisonMineFlag, DropTablesDelivery dropTablesDelivery) {
        this.plugin = plugin;
        this.prisonMineFlag = prisonMineFlag;
        this.dropTablesDelivery = dropTablesDelivery;
    }

    @EventHandler
    public void mine(PlayerInteractEvent event) {
        if (event.getClickedBlock() != null && event.getClickedBlock().getType().equals(Material.CHEST)) {
            final Player player = event.getPlayer();
            final RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
            final RegionQuery query = container.createQuery();
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
                RandomSelector<Treasure> commonTreasureChest = RandomSelector.weighted((Treasure.getTreasure(Rarity.COMMON)));
                Treasure commonTreasureReward = commonTreasureChest.pick();
                dropTablesDelivery.giveReward(player.getPlayer(), commonTreasureReward.getDisplayName(), commonTreasureReward.getType(), commonTreasureReward.getId(), commonTreasureReward.getWeight());
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