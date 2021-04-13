package monzter.adventurescraft.plugin.event;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.util.Location;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import me.clip.placeholderapi.PlaceholderAPI;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.event.extras.WeightPrices;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakMining implements Listener {
    private final AdventuresCraft plugin;
    private final StateFlag prisonMineFlag;

    public BlockBreakMining(AdventuresCraft plugin, StateFlag prisonMineFlag) {
        this.plugin = plugin;
        this.prisonMineFlag = prisonMineFlag;
    }

    @EventHandler
    public void mine(BlockBreakEvent event) {
        Player player = event.getPlayer();
        double enchantmentExperience = Double.valueOf(PlaceholderAPI.setPlaceholders(player, "%ac_Enchantment_Experience%"));
        double enchantmentPetExperience = Double.valueOf(PlaceholderAPI.setPlaceholders(player, "%ac_Enchantment_Pet_Experience%"));
        double enchantmentExplosive = Double.valueOf(PlaceholderAPI.setPlaceholders(player, "%ac_Enchantment_Explosive%"));
        double enchantmentExplosiveChance = Double.valueOf(PlaceholderAPI.setPlaceholders(player, "%ac_Enchantment_Explosive_Chance%"));
        double enchantmentLuck = Double.valueOf(PlaceholderAPI.setPlaceholders(player, "%ac_Enchantment_Luck%"));

        double currentWeight = Double.valueOf(PlaceholderAPI.setPlaceholders(player, "%ac_Stat_Weight%"));
        double maxWeight = Double.valueOf(PlaceholderAPI.setPlaceholders(player, "%ac_Stat_MaxWeight%"));
        int blockMultiplier = (int) Math.floor(Double.valueOf(PlaceholderAPI.setPlaceholders(player, "%ac_Stat_BlockMultiplier%")));

        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionQuery query = container.createQuery();
        final Location location = BukkitAdapter.adapt(event.getBlock().getLocation());
        event.setCancelled(true);
        event.setDropItems(false);
        event.setExpToDrop(0);
        if (query.testState(location, null, prisonMineFlag)) {
            plugin.getLogger().info("FLAG SET!");
            for (WeightPrices material : WeightPrices.values()) { // Loops over block list to validate block
                if (material.material.equals(event.getBlock().getType())) {
                    System.out.println("Type");
                    if (enchantmentExplosive < 1) {
                        System.out.println("Non Explosive");
                        System.out.println(blockMultiplier);
                        if ((material.weight * blockMultiplier) + currentWeight <= maxWeight) { // Checks if player can hold the block's weight
                            minedBlock(player, material.material, blockMultiplier, (material.weight * blockMultiplier));
                            event.setCancelled(false);
                        } else {
                            tooHeavy(player);
                        }
                    }
                }
            }
        }
    }

    private void minedBlock(Player player, Material material, int amount, int weight) {
        Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "q point " + player.getName() + " add items.TotalBlocks 1"); // Adds to Total Blocks
        Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "q point " + player.getName() + " add items.Weight " + weight); // Adds to current Weight
        Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "q point " + player.getName() + " add items." + material.toString() // Adds to individual item for Selling
                + " " + amount);
        Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "q point " + player.getName() + " add blocks." + material.toString() + " 1"); // Gives a point for each specific block mined, used for Achievements
        Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "q point " + player.getName() + " add items.TotalModifierBlocks " + amount); // I forget

    }

    private void tooHeavy(Player player) {
        player.sendMessage(ChatColor.RED + "You're too heavy, go sell your items by using "
                + ChatColor.YELLOW + "/Sell" + ChatColor.RED + "!");
    }
}
