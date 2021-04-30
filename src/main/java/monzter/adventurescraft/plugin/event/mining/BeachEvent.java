package monzter.adventurescraft.plugin.event.mining;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.util.Location;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.acUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Random;

public class BeachEvent implements Listener {
    private static int blocksBroken = 0;
    private static int max = 0;
    private final AdventuresCraft plugin;
    private final org.bukkit.Location mobSpawnLocation = new org.bukkit.Location(Bukkit.getWorld("World"), 1180, 209, 2419);

    public BeachEvent(AdventuresCraft plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void beachTracker(BlockBreakEvent event) {
        if (max == 0) {
            max = generateMax();
        } else {
            Location location = BukkitAdapter.adapt(event.getBlock().getLocation());
            RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
            RegionQuery query = container.createQuery();
            ApplicableRegionSet set = query.getApplicableRegions(location);
            for (ProtectedRegion region : set) {
                if (region.getId().equals("mine_zone_beach")) {
                    blocksBroken++;
                    if (blocksBroken == max) {
                        eventChooser();
                        blocksBroken = 0;
                        max = generateMax();
                    }
                }
            }
        }
    }

    private void alert(String reward) {
        for (Player player : plugin.getServer().getOnlinePlayers()) {
            player.sendMessage(
                    Component.text("The ").color(NamedTextColor.GREEN)
                    .append(Component.text("Beach", NamedTextColor.YELLOW, TextDecoration.BOLD))
                    .hoverEvent(Component.text("Click to visit!", NamedTextColor.GREEN))
                    .clickEvent(ClickEvent.runCommand("/warp Beach"))
                    .append(Component.text(" has been dug up and a "))
                    .append(Component.text(reward))
                    .append(Component.text(" was found!")));
        }
    }
    private void eventChooser(){
        int event = new Random().nextInt(3);
        switch (event){
            case 0:
                booster();
                break;
            case 1:
                lootLLama();
                break;
            case 2:
                favorFish();
                break;
        }
    }

    private void booster(){
        acUtils.consoleCommand("randomBooster");
        alert(ChatColor.GOLD.toString() + ChatColor.BOLD + "GLOBAL BOOSTER");
    }
    private void lootLLama(){
        acUtils.spawnMob(mobSpawnLocation, "LOOT_LLAMA");
        alert(ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "LOOT LLAMA");
    }
    private void favorFish(){
        acUtils.spawnMob(mobSpawnLocation, "FAVOR_FISH");
        acUtils.spawnMob(mobSpawnLocation, "FAVOR_FISH");
        acUtils.spawnMob(mobSpawnLocation, "FAVOR_FISH2");
        alert(ChatColor.AQUA.toString() + ChatColor.BOLD + "FAVOR FISH");
    }

    public static int getMax() {
        return max;
    }

    public static int getBlocksBroken() {
        return blocksBroken;
    }

    private int[] maxList = new int[]{250, 500, 750, 1000, 1250, 1500, 1750, 2000};

    private int generateMax() {
        int randomDuration = new Random().nextInt(maxList.length);
        return maxList[randomDuration];
    }

}
