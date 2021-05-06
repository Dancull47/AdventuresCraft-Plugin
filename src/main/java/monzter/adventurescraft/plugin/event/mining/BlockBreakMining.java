package monzter.adventurescraft.plugin.event.mining;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.util.Location;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import me.clip.placeholderapi.PlaceholderAPI;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.event.extras.WeightPrices;
import monzter.adventurescraft.plugin.utilities.beton.BetonPointsManager;
import monzter.adventurescraft.plugin.utilities.bukkit.ConsoleCommand;
import monzter.adventurescraft.plugin.utilities.bukkit.SoundManager;
import monzter.adventurescraft.plugin.utilities.general.ChanceCheck;
import monzter.adventurescraft.plugin.utilities.mmoitems.MMOItemsGive;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.tags.CustomItemTagContainer;
import org.bukkit.inventory.meta.tags.ItemTagType;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BlockBreakMining implements Listener {
    private static ChanceCheck chanceCheck;
    private static SoundManager soundManager;
    private static ConsoleCommand consoleCommand;
    private static MMOItemsGive mmoItemsGive;
    private static BetonPointsManager betonPointsManager;
    private final AdventuresCraft plugin;
    private static StateFlag prisonMineFlag;

    private final static Material[] blocks = new Material[]{Material.SEA_LANTERN, Material.GREEN_STAINED_GLASS, Material.BLUE_STAINED_GLASS,
            Material.YELLOW_STAINED_GLASS, Material.RED_STAINED_GLASS, Material.ORANGE_STAINED_GLASS, Material.DIAMOND_BLOCK,
            Material.DIAMOND_ORE, Material.EMERALD_BLOCK, Material.EMERALD_ORE, Material.REDSTONE_BLOCK, Material.REDSTONE_ORE};

    public BlockBreakMining(AdventuresCraft plugin, StateFlag prisonMineFlag, SoundManager soundManager, ChanceCheck chanceCheck, ConsoleCommand consoleCommand, MMOItemsGive mmoItemsGive, BetonPointsManager betonPointsManager) {
        this.plugin = plugin;
        this.prisonMineFlag = prisonMineFlag;
        this.soundManager = soundManager;
        this.chanceCheck = chanceCheck;
        this.consoleCommand = consoleCommand;
        this.mmoItemsGive = mmoItemsGive;
        this.betonPointsManager = betonPointsManager;
    }

    @EventHandler
    private void mine(BlockBreakEvent event) {
        Player player = event.getPlayer();
//        plugin.data.savePlayer(player, event.getBlock().getType().name(), 1);
//        plugin.data.setPointAmount(player.getUniqueId(), event.getBlock().getType().name(), 1);
        final RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        final RegionQuery query = container.createQuery();
        final Location location = BukkitAdapter.adapt(event.getBlock().getLocation());
        if (inRegion(query, location)) {
            final int enchantmentExplosive = Integer.valueOf(PlaceholderAPI.setPlaceholders(player, "%ac_Enchantment_Explosive%"));
            if (enchantmentExplosive < 1) {
                player.sendMessage("Explosive Passed");
                event.setDropItems(false);
                event.setExpToDrop(0);
                final double currentWeight = Double.valueOf(PlaceholderAPI.setPlaceholders(player, "%ac_Stat_Weight%"));
                final double maxWeight = Double.valueOf(PlaceholderAPI.setPlaceholders(player, "%ac_Stat_MaxWeight%"));
                final int blockMultiplier = (int) Math.floor(Double.valueOf(PlaceholderAPI.setPlaceholders(player, "%ac_Stat_BlockMultiplier%")));
                for (WeightPrices material : WeightPrices.values()) { // Loops over block list to validate block
                    if (material.material.equals(event.getBlock().getType())) {
                        if ((material.weight * blockMultiplier) + currentWeight <= maxWeight) { // Checks if player can hold the block's weight
                            minedBlock(player, material.material, blockMultiplier, (material.weight * blockMultiplier));
                            enchantmentLuck(player);
                            enchantmentExperience(player);
                            enchantmentPetExperience(player);
                            enchantmentTreasurer(player);
                            enchantmentRandomizer(player);
                            enchantmentMidasTouch(player);
                            statTrack(player);
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
        betonPointsManager.givePoint(player, "items.TotalBlocks", 1); // Adds to Total Blocks
        betonPointsManager.givePointWeight(player, weight); // Adds to current Weight
        betonPointsManager.givePoint(player, "items." + material.toString(), amount); // Adds to individual item for Sell
        betonPointsManager.givePoint(player, "blocks." + material.toString(), 1); // Gives a point for each specific block mined, used for Achievements
        betonPointsManager.givePoint(player, "items.TotalModifierBlocks", amount); // I forget
    }

    public static void enchantmentLuck(Player player) {
        double luckMultiplier = Double.valueOf(PlaceholderAPI.setPlaceholders(player, "%ac_Stat_LuckMultiplier%")) * .25;
        if (chanceCheck.chanceCheck(.005 * luckMultiplier)) {
            mmoItemsGive.giveMMOItem(player, "CONSUMABLE", "LOOTBOX5");
        }
        if (chanceCheck.chanceCheck(.008 * luckMultiplier)) {
            mmoItemsGive.giveMMOItem(player, "CONSUMABLE", "LOOTBOX4");
        }
        if (chanceCheck.chanceCheck(.01 * luckMultiplier)) {
            mmoItemsGive.giveMMOItem(player, "CONSUMABLE", "LOOTBOX3");
        }
        if (chanceCheck.chanceCheck(.03 * luckMultiplier)) {
            mmoItemsGive.giveMMOItem(player, "CONSUMABLE", "LOOTBOX2");
        }
        if (chanceCheck.chanceCheck(.05 * luckMultiplier)) {
            mmoItemsGive.giveMMOItem(player, "CONSUMABLE", "LOOTBOX");
        }
    }

    public static void enchantmentExperience(Player player) {
        double expMultiplier = Double.valueOf(PlaceholderAPI.setPlaceholders(player, "%ac_Stat_EXPMultiplier%"));
        betonPointsManager.givePointEXP(player, (int) expMultiplier);
        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, .5f, 1f);
    }

    public static void enchantmentPetExperience(Player player) {
        double petExpMultiplier = Double.valueOf(PlaceholderAPI.setPlaceholders(player, "%ac_Stat_Pet_EXPMultiplier%"));
        betonPointsManager.givePointPetEXP(player, (int) petExpMultiplier);
    }

    public static void enchantmentTreasurer(Player player) {
        double enchantmentLevel = Double.valueOf(PlaceholderAPI.setPlaceholders(player, "%ac_Enchantment_Treasurer%"));
        if (enchantmentLevel > 0) {
            RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
            RegionQuery query = container.createQuery();
            final org.bukkit.Location originalLocation = new org.bukkit.Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY() - 1, player.getLocation().getZ());
            Location convertedLocation = BukkitAdapter.adapt(originalLocation);
            if (inRegion(query, convertedLocation)) {
                if (chanceCheck.chanceCheck(.0025 + (enchantmentLevel * .0005))) {
                    originalLocation.getBlock().setType(Material.CHEST);
                    soundManager.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                    player.sendMessage(ChatColor.GREEN + "You found a hidden" + ChatColor.GOLD + " Treasure Chest" + ChatColor.GREEN + "!");
                }
            }
        }
    }

    public static void enchantmentMidasTouch(Player player) {
        double enchantmentLevel = Double.valueOf(PlaceholderAPI.setPlaceholders(player, "%ac_Enchantment_Midas_Touch%"));
        if (enchantmentLevel > 0) {
            Random r = new Random();
            int result = r.nextInt(250000 - 50000) + 10;
            if (chanceCheck.chanceCheck(.0025 + (enchantmentLevel * .0005))) {
                consoleCommand.consoleCommand("money give " + player.getName() + " " + result);
                soundManager.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
            }
        }
    }

    public static void enchantmentRandomizer(Player player) {
        double enchantmentLevel = Double.valueOf(PlaceholderAPI.setPlaceholders(player, "%ac_Enchantment_Randomizer%"));
        if (enchantmentLevel > 0) {
            RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
            RegionQuery query = container.createQuery();
            final org.bukkit.Location originalLocation = new org.bukkit.Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY() + 2, player.getLocation().getZ());
            Location convertedLocation = BukkitAdapter.adapt(player.getEyeLocation());
            if (inRegion(query, convertedLocation)) {
                if (chanceCheck.chanceCheck(.0025 + (enchantmentLevel * .0005))) {
//                    player.getEyeLocation().getBlock().setType(getRandom(blocks));
                    originalLocation.getBlock().setType(getRandom(blocks));
                    soundManager.playSound(player, Sound.ENTITY_ARROW_HIT, 1, 2);
                    final String message = "Randomized";
                    final StringBuilder sb = new StringBuilder();
                    final Random random = new Random();
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

        public void statTrack(Player player) {
        ItemStack itemStack = player.getInventory().getItemInMainHand();
        double enchantmentLevel = Double.valueOf(PlaceholderAPI.setPlaceholders(player, "%ac_Enchantment_Stat_Tracker%"));
        if (enchantmentLevel > 0) {
            if (itemStack != null) {
                final NamespacedKey key = new NamespacedKey(plugin, "stat-track");
                final ItemMeta itemMeta = itemStack.getItemMeta();
                PersistentDataContainer tagContainer = itemMeta.getPersistentDataContainer();
                List<Component> lore = itemStack.lore();
                if (tagContainer.has(key, PersistentDataType.INTEGER)) {
                    int foundValue = tagContainer.get(key, PersistentDataType.INTEGER);
                    lore.set(lore.size() - 3, Component.text(ChatColor.GREEN + "Blocks Mined: " + ChatColor.YELLOW + Integer.valueOf(foundValue + 1)));
                    itemMeta.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, Integer.valueOf(1 + foundValue));
                } else {
                    itemMeta.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, 1);
                    lore.add(lore.size() - 2, Component.text(""));
                    lore.add(lore.size() - 2, Component.text(ChatColor.GREEN + "Blocks Mined: " + ChatColor.YELLOW + 1));
                }
                itemStack.setItemMeta(itemMeta);
                itemStack.lore(lore);
            }
        }
    }

    private void tooHeavy(Player player) {
        player.sendMessage(ChatColor.RED + "You're too heavy, go sell your items by using "
                + ChatColor.YELLOW + "/Sell" + ChatColor.RED + "!");
        soundManager.soundNo(player, 1);
    }

    private static boolean inRegion(RegionQuery query, Location location) {
        if (query.testState(location, null, prisonMineFlag)) {
            return true;
        }
        return false;
    }

    private static Material getRandom(Material[] array) {
        return array[new Random().nextInt(array.length)];
    }


}
