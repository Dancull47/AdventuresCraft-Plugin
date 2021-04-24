package monzter.adventurescraft.plugin.event.mining;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
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
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BlockBreakMining implements Listener {
    private AdventuresCraft plugin;
    private static StateFlag prisonMineFlag;
    private static Material[] blocks = new Material[]{Material.SEA_LANTERN, Material.GREEN_STAINED_GLASS, Material.BLUE_STAINED_GLASS,
            Material.YELLOW_STAINED_GLASS, Material.RED_STAINED_GLASS, Material.ORANGE_STAINED_GLASS, Material.DIAMOND_BLOCK,
            Material.DIAMOND_ORE, Material.EMERALD_BLOCK, Material.EMERALD_ORE, Material.REDSTONE_BLOCK, Material.REDSTONE_ORE};

    public BlockBreakMining(AdventuresCraft plugin, StateFlag prisonMineFlag) {
        this.plugin = plugin;
        this.prisonMineFlag = prisonMineFlag;
    }

    @EventHandler
    public void mine(BlockBreakEvent event) {
        Player player = event.getPlayer();
//        plugin.data.savePlayer(player, event.getBlock().getType().name(), 1);
//        plugin.data.setPointAmount(player.getUniqueId(), event.getBlock().getType().name(), 1);
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionQuery query = container.createQuery();
        final Location location = BukkitAdapter.adapt(event.getBlock().getLocation());
        if (inRegion(query, location)) {
            int enchantmentExplosive = Integer.valueOf(PlaceholderAPI.setPlaceholders(player, "%ac_Enchantment_Explosive%"));
            if (enchantmentExplosive < 1) {
                player.sendMessage("Explosive Passed");
                event.setDropItems(false);
                event.setExpToDrop(0);
                double currentWeight = Double.valueOf(PlaceholderAPI.setPlaceholders(player, "%ac_Stat_Weight%"));
                double maxWeight = Double.valueOf(PlaceholderAPI.setPlaceholders(player, "%ac_Stat_MaxWeight%"));
                int blockMultiplier = (int) Math.floor(Double.valueOf(PlaceholderAPI.setPlaceholders(player, "%ac_Stat_BlockMultiplier%")));

                for (WeightPrices material : WeightPrices.values()) { // Loops over block list to validate block
                    if (material.material.equals(event.getBlock().getType())) {
                        if ((material.weight * blockMultiplier) + currentWeight <= maxWeight) { // Checks if player can hold the block's weight
                            minedBlock(player, material.material, blockMultiplier, (material.weight * blockMultiplier));
                            enchantmentLuck(player);
                            enchantmentExperience(player);
                            enchantmentPetExperience(player);
                            enchantmentTreasurer(player);
                            enchantmentRandomizer(player);
                            event.setCancelled(false);
                        } else {
                            tooHeavy(player);
                            event.setCancelled(true);
                        }
                    }/* else {
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
                    }*/
                }
            }

        }
    }

    private void minedBlock(Player player, Material material, int amount, int weight) {
        Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "q point " + player.getName() + " add items.TotalBlocks 1"); // Adds to Total Blocks
        Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "q point " + player.getName() + " add items.Weight " + weight); // Adds to current Weight
        Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "q point " + player.getName() + " add items." + material.toString() // Adds to individual item for Sell
                + " " + amount);
        Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "q point " + player.getName() + " add blocks." + material.toString() + " 1"); // Gives a point for each specific block mined, used for Achievements
        Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "q point " + player.getName() + " add items.TotalModifierBlocks " + amount); // I forget

    }

    public static void enchantmentLuck(Player player) {
        double luckMultiplier = Double.valueOf(PlaceholderAPI.setPlaceholders(player, "%ac_Stat_LuckMultiplier%")) * .25;
        if (acUtils.chanceCheck(.005 * luckMultiplier)) {
            Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "mmoitems give CONSUMABLE LOOTBOX5 " + player.getName() + " 1");
        }
        if (acUtils.chanceCheck(.008 * luckMultiplier)) {
            Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "mmoitems give CONSUMABLE LOOTBOX4 " + player.getName() + " 1");
        }
        if (acUtils.chanceCheck(.01 * luckMultiplier)) {
            Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "mmoitems give CONSUMABLE LOOTBOX3 " + player.getName() + " 1");
        }
        if (acUtils.chanceCheck(.03 * luckMultiplier)) {
            Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "mmoitems give CONSUMABLE LOOTBOX2 " + player.getName() + " 1");
        }
        if (acUtils.chanceCheck(.05 * luckMultiplier)) {
            Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "mmoitems give CONSUMABLE LOOTBOX " + player.getName() + " 1");
        }
    }

    public static void enchantmentExperience(Player player) {
        double expMultiplier = Double.valueOf(PlaceholderAPI.setPlaceholders(player, "%ac_Stat_EXPMultiplier%"));
        Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "q point " + player.getName() + " add items.Experience " + (int) expMultiplier);
        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, .5f, 1f);
    }

    public static void enchantmentPetExperience(Player player) {
        double petExpMultiplier = Double.valueOf(PlaceholderAPI.setPlaceholders(player, "%ac_Stat_Pet_EXPMultiplier%"));
        Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "q point " + player.getName() + " add items.PetExperience " + (int) petExpMultiplier);
    }

    public static void enchantmentTreasurer(Player player) {
        double enchantmentLevel = Double.valueOf(PlaceholderAPI.setPlaceholders(player, "%ac_Enchantment_Treasurer%"));
        if (enchantmentLevel > 0) {
            RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
            RegionQuery query = container.createQuery();
            final org.bukkit.Location originalLocation = new org.bukkit.Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY() - 1, player.getLocation().getZ());
            Location convertedLocation = BukkitAdapter.adapt(originalLocation);
            if (inRegion(query, convertedLocation)) {
                if (acUtils.chanceCheck(.0025 * enchantmentLevel)) {
                    originalLocation.getBlock().setType(Material.CHEST);
                    acUtils.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                    player.sendMessage(ChatColor.GREEN + "You found a hidden" + ChatColor.GOLD + " Treasure Chest" + ChatColor.GREEN + "!");
                }
            }
        }
    }

    public static void enchantmentRandomizer(Player player) {
        double enchantmentLevel = Double.valueOf(PlaceholderAPI.setPlaceholders(player, "%ac_Enchantment_Randomizer%"));
        if (enchantmentLevel > 0) {
            RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
            RegionQuery query = container.createQuery();
            final org.bukkit.Location originalLocation = new org.bukkit.Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY()+2, player.getLocation().getZ());
            Location convertedLocation = BukkitAdapter.adapt(player.getEyeLocation());
            if (inRegion(query, convertedLocation)) {
                if (acUtils.chanceCheck(.0025 + enchantmentLevel)) {
//                    player.getEyeLocation().getBlock().setType(getRandom(blocks));
                    originalLocation.getBlock().setType(getRandom(blocks));
                    acUtils.playSound(player, Sound.ENTITY_ARROW_HIT, 1, 2);
                    String message = "Randomized";
                    StringBuilder sb = new StringBuilder();
                    Random random = new Random();
                    for (Character character : message.toCharArray()) {
                        sb.append(ChatColor.getByChar(Integer.toHexString(random
                                .nextInt(message.length()))));
                        sb.append(character);
                    }
                    player.sendMessage(ChatColor.GREEN + "The block above you has been " + sb.toString() + ChatColor.GREEN + "!");
                }
            }
        }
    }

    private void tooHeavy(Player player) {
        player.sendMessage(ChatColor.RED + "You're too heavy, go sell your items by using "
                + ChatColor.YELLOW + "/Sell" + ChatColor.RED + "!");
        acUtils.soundNo(player, 1);
    }

    private static boolean inRegion(RegionQuery query, Location location) {
        if (query.testState(location, null, prisonMineFlag)) {
            return true;
        }
        return false;
    }

    public static Material getRandom(Material[] array) {
        return array[new Random().nextInt(array.length)];
    }


}
