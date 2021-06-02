package monzter.adventurescraft.plugin.network.PrisonGamemode.prison.events.mining;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.util.Location;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.general.ConsoleCommand;
import monzter.adventurescraft.plugin.utilities.mythicmobs.MythicMobsSpawn;
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
    private final AdventuresCraft plugin;
    private static int blocksBroken = 0;
    private static int max = 0;
    private final int[] maxList = new int[]{250, 500, 750, 1000, 1250, 1500, 1750, 2000};
    private final ConsoleCommand consoleCommand;
    private final MythicMobsSpawn mythicMobsSpawn;
    private final org.bukkit.Location mobSpawnLocation = new org.bukkit.Location(Bukkit.getWorld("World"), 1180, 209, 2419);


    public BeachEvent(AdventuresCraft plugin, ConsoleCommand consoleCommand, MythicMobsSpawn mythicMobsSpawn) {
        this.plugin = plugin;
        this.consoleCommand = consoleCommand;
        this.mythicMobsSpawn = mythicMobsSpawn;
    }

    @EventHandler
    private void beachTracker(BlockBreakEvent event) {
        if (max == 0) {
            max = generateMax();
        } else {
            final Location location = BukkitAdapter.adapt(event.getBlock().getLocation());
            final RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
            final RegionQuery query = container.createQuery();
            final ApplicableRegionSet set = query.getApplicableRegions(location);
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
        consoleCommand.consoleCommand("randomBooster");
        alert(ChatColor.GOLD.toString() + ChatColor.BOLD + "GLOBAL BOOSTER");
    }
    private void lootLLama(){
        mythicMobsSpawn.spawnMob(mobSpawnLocation, "LOOT_LLAMA");
        alert(ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "LOOT LLAMA");
    }

    private void favorFish(){
        mythicMobsSpawn.spawnMob(mobSpawnLocation, "FAVOR_FISH");
        mythicMobsSpawn.spawnMob(mobSpawnLocation, "FAVOR_FISH");
        mythicMobsSpawn.spawnMob(mobSpawnLocation, "FAVOR_FISH2");
        alert(ChatColor.AQUA.toString() + ChatColor.BOLD + "FAVOR FISH");
    }

    public static int getMax() {
        return max;
    }

    public static int getBlocksBroken() {
        return blocksBroken;
    }

    private final int generateMax() {
        int randomDuration = new Random().nextInt(maxList.length);
        return maxList[randomDuration];
    }

}
