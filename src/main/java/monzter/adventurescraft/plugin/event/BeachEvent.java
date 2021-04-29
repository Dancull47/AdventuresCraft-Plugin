package monzter.adventurescraft.plugin.event;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.util.Location;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import io.lumine.xikage.mythicmobs.MythicMobs;
import io.lumine.xikage.mythicmobs.mobs.MythicMob;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.acUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.Random;

public class BeachEvent implements Listener {
    private static int blocksBroken = 0;
    private static int max = 0;
    private final AdventuresCraft plugin;
    org.bukkit.Location spawnLocationWarden = new org.bukkit.Location(Bukkit.getWorld("World"), 1180, 209, 2419);

    public BeachEvent(AdventuresCraft plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void mapBarrier(BlockBreakEvent event) {
        if (max == 0) {
            max = generateMax();
        } else {
            Player player = event.getPlayer();
            Location location = BukkitAdapter.adapt(player.getLocation());
            RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
            RegionQuery query = container.createQuery();
            ApplicableRegionSet set = query.getApplicableRegions(location);
            for (ProtectedRegion region : set) {
//                plugin.getLogger().info(region.getId());
                if (region.getId().equals("mine_zone_beach")) {
//                    plugin.getLogger().info("Point Added");
                    blocksBroken++;
                    if (blocksBroken == max) {
                        if (max == 5){
                            acUtils.spawnMob(spawnLocationWarden, "WARDEN");
                        }
//                        plugin.getLogger().info("Max Reached!");
                        blocksBroken = 0;
                        max = generateMax();
//                        plugin.getLogger().info("Point Reset!");
                    }
                }
            }
        }
    }

    public static int getMax() {
        return max;
    }

    public static int getBlocksBroken() {
        return blocksBroken;
    }

    private int[] maxList = new int[]{5, 10, 15, 25, 30};

    private int generateMax() {
        int randomDuration = new Random().nextInt(maxList.length);
//        plugin.getLogger().info("New Max: " + maxList[randomDuration]);
        return maxList[randomDuration];
    }
}
