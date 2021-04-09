package monzter.adventurescraft.plugin.event;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.util.Location;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import me.clip.placeholderapi.PlaceholderAPI;
import monzter.adventurescraft.plugin.AdventuresCraft;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreak implements Listener {
    private final AdventuresCraft plugin;
    private final StateFlag prisonMineFlag;

    public BlockBreak(AdventuresCraft plugin, StateFlag prisonMineFlag) {
        this.plugin = plugin;
        this.prisonMineFlag = prisonMineFlag;
    }

    @EventHandler
    public void mine(BlockBreakEvent event) {
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionQuery query = container.createQuery();
        final Location location = BukkitAdapter.adapt(event.getBlock().getLocation());
        if (query.testState(location, null, prisonMineFlag)) {
            plugin.getLogger().info("FLAG SET!");
        }
    }

    public int maxWeightPlaceholder(Player player){
        String maxWeight = PlaceholderAPI.setPlaceholders(player, "%ac_Stat_MaxWeight%");
        return Integer.valueOf(maxWeight);
    }
}
