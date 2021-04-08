package monzter.adventurescraft.plugin.event;

import monzter.adventurescraft.plugin.AdventuresCraft;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.util.Vector;

public class BlockBreak implements Listener {
    AdventuresCraft plugin;

    public BlockBreak(AdventuresCraft plugin) {
        this.plugin = plugin;
    }

    public static void mine(BlockBreakEvent event){
        if ()
    }
    public boolean withinRegion(Block block, String region){
        return withinRegion(block.getLocation(), region);
    }
    public boolean withinRegion(Location loc, String region){
        Vector v = toVector(loc);
    }
}
