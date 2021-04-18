package monzter.adventurescraft.plugin.event;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.util.Location;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import io.lumine.mythic.lib.api.item.NBTItem;
import me.clip.placeholderapi.PlaceholderAPI;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.event.extras.PetEgg;
import monzter.adventurescraft.plugin.event.extras.WeightPrices;
import net.Indyuce.mmoitems.MMOItems;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import javax.management.Query;

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
//        plugin.data.createPlayer(player, event.getBlock().getType().name(), 1);
//        plugin.data.setPointAmount(player.getUniqueId(), event.getBlock().getType().name(), 1);
        int enchantmentExperience = Integer.valueOf(PlaceholderAPI.setPlaceholders(player, "%ac_Enchantment_Experience%"));
        int enchantmentPetExperience = Integer.valueOf(PlaceholderAPI.setPlaceholders(player, "%ac_Enchantment_Pet_Experience%"));
        int enchantmentExplosive = Integer.valueOf(PlaceholderAPI.setPlaceholders(player, "%ac_Enchantment_Explosive%"));
        int enchantmentExplosiveChance = Integer.valueOf(PlaceholderAPI.setPlaceholders(player, "%ac_Enchantment_Explosive_Chance%"));
        int enchantmentLuck = Integer.valueOf(PlaceholderAPI.setPlaceholders(player, "%ac_Enchantment_Luck%"));

        double currentWeight = Double.valueOf(PlaceholderAPI.setPlaceholders(player, "%ac_Stat_Weight%"));
        double maxWeight = Double.valueOf(PlaceholderAPI.setPlaceholders(player, "%ac_Stat_MaxWeight%"));
        int blockMultiplier = (int) Math.floor(Double.valueOf(PlaceholderAPI.setPlaceholders(player, "%ac_Stat_BlockMultiplier%")));

        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionQuery query = container.createQuery();
        final Location location = BukkitAdapter.adapt(event.getBlock().getLocation());
        event.setCancelled(true);
        event.setDropItems(false);
        event.setExpToDrop(0);
        if (inRegion(query, location)) {
            plugin.getLogger().info("FLAG SET!");
            for (WeightPrices material : WeightPrices.values()) { // Loops over block list to validate block
                if (material.material.equals(event.getBlock().getType())) {
                    System.out.println("Type");
                    if (enchantmentExplosive < 1) {
                        System.out.println("Non Explosive");
                        System.out.println(blockMultiplier);
                        if ((material.weight * blockMultiplier) + currentWeight <= maxWeight) { // Checks if player can hold the block's weight
                            minedBlock(player, material.material, blockMultiplier, (material.weight * blockMultiplier));
                            enchantmentLuck(player, enchantmentLuck);
                            enchantmentExperience(player, enchantmentExperience);
                            enchantmentPetExperience(player, enchantmentPetExperience);
                            event.setCancelled(false);
                        } else {
                            tooHeavy(player);
                        }
                    } else {
//                        https://www.spigotmc.org/threads/getting-blocks-in-a-radius.60296/
                        int radius = enchantmentExplosive;
                        for (int x = radius; x > -radius; x--) {
                            for (int y = radius; y > -radius; y--) {
                                for (int z = radius; z > -radius; z--) {
//                        for (int x = 1; x < radius; x++){
                                    final Block newBlock = event.getBlock().getRelative(x, y, z);
                                    final Location newLocation = BukkitAdapter.adapt(newBlock.getLocation());
                                    System.out.println("Has Explosive");
                                    if (query.testState(newLocation, null, prisonMineFlag)) {
                                        System.out.println("Within Bounds");
                                        for (WeightPrices newMaterial : WeightPrices.values()) {
                                            System.out.println("Made it to Loop!");
                                            System.out.println(newBlock.getType());
                                            if (newMaterial.material.equals(newBlock.getType()) && newBlock.getType() != Material.AIR) {
                                                System.out.println("Found Block");
                                                System.out.println(newMaterial.weight);
                                                System.out.println(newMaterial.weight * blockMultiplier);
                                                if (((newMaterial.weight * blockMultiplier) + currentWeight) <= maxWeight) {
                                                    minedBlock(player, newMaterial.material, blockMultiplier, (newMaterial.weight * blockMultiplier));
                                                    newBlock.setType(Material.AIR);
                                                } else {
                                                    tooHeavy(player);
                                                }
                                            }
                                        }
                                    }
                                }
                            }

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

    private void enchantmentLuck(Player player, int enchantmentLevel) {
        if (enchantmentLevel > 0) {
            String luckMultiplier = PlaceholderAPI.setPlaceholders(player, "%ac_Stat_LuckMultiplier%");
            if (chanceCheck(.01 * Integer.valueOf(luckMultiplier))) {
                Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "mmoitems give CONSUMABLE LOOTBOX " + player.getName() + " 1");
            }
            if (chanceCheck(.066 * Integer.valueOf(luckMultiplier))) {
                Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "mmoitems give CONSUMABLE LOOTBOX2 " + player.getName() + " 1");
            }
            if (chanceCheck(.05 * Integer.valueOf(luckMultiplier))) {
                Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "mmoitems give CONSUMABLE LOOTBOX3 " + player.getName() + " 1");
            }
            if (chanceCheck(.04 * Integer.valueOf(luckMultiplier))) {
                Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "mmoitems give CONSUMABLE LOOTBOX4 " + player.getName() + " 1");
            }
            if (chanceCheck(.033 * Integer.valueOf(luckMultiplier))) {
                Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "mmoitems give CONSUMABLE LOOTBOX5 " + player.getName() + " 1");
            }
        }
    }

    private void enchantmentExperience(Player player, int enchantmentLevel) {
        if (enchantmentLevel > 0) {
            System.out.println("exp > 0");
            double expMultiplier = Double.valueOf(PlaceholderAPI.setPlaceholders(player, "%ac_Stat_EXPMultiplier%"));
            Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "q point " + player.getName() + " add items.Experience " + (int) expMultiplier);
            player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, .5f, 1f);
        } else {
            System.out.println("exp < 0");
            Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "q point " + player.getName() + " add items.Experience " + 0);
        }
    }

    private void enchantmentPetExperience(Player player, int enchantmentLevel) {
        if (player.getInventory().contains(Material.EGG)) {
            if (enchantmentLevel > 0) {
                System.out.println("petexp > 0");
                double petExpMultiplier = Double.valueOf(PlaceholderAPI.setPlaceholders(player, "%ac_Stat_Pet_EXPMultiplier%"));
                Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "q point " + player.getName() + " add items.PetExperience " + (int) petExpMultiplier);
            } else {
                System.out.println("petexp < 0");
                Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "q point " + player.getName() + " add items.PetExperience " + 0);
            }
        }
    }

    private void tooHeavy(Player player) {
        player.sendMessage(ChatColor.RED + "You're too heavy, go sell your items by using "
                + ChatColor.YELLOW + "/Sell" + ChatColor.RED + "!");
    }

    public boolean chanceCheck(double chance) {
//        .5 = 50%
        if (Math.random() <= chance) {
            return true;
        }
        return false;
    }

    private boolean inRegion(RegionQuery query, Location location) {
        if (query.testState(location, null, prisonMineFlag)) {
            return true;
        }
        return false;
    }
}
