package monzter.adventurescraft.plugin.network.PrisonGamemode.prison.events.mining;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.util.Location;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import me.clip.placeholderapi.PlaceholderAPI;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.beton.BetonPointsManager;
import monzter.adventurescraft.plugin.utilities.enums.Enchantments;
import monzter.adventurescraft.plugin.utilities.enums.WeightPrices;
import monzter.adventurescraft.plugin.utilities.general.ChanceCheck;
import monzter.adventurescraft.plugin.utilities.general.ConsoleCommand;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import monzter.adventurescraft.plugin.utilities.mmoitems.MMOItemsGive;
import monzter.adventurescraft.plugin.utilities.text.NumberFormat;
import monzter.adventurescraft.plugin.utilities.vault.Economy;
import net.kyori.adventure.text.Component;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;
import java.util.Random;

public class BlockBreakMining implements Listener {
    private final ChanceCheck chanceCheck;
    private final SoundManager soundManager;
    private final ConsoleCommand consoleCommand;
    private final MMOItemsGive mmoItemsGive;
    private final BetonPointsManager betonPointsManager;
    private final AdventuresCraft plugin;
    private final StateFlag prisonMineFlag;
    private final Economy economy;
    private final NumberFormat numberFormat;


    private final Material[] blocks = new Material[]{Material.SEA_LANTERN, Material.GREEN_STAINED_GLASS, Material.BLUE_STAINED_GLASS,
            Material.YELLOW_STAINED_GLASS, Material.RED_STAINED_GLASS, Material.ORANGE_STAINED_GLASS, Material.DIAMOND_BLOCK,
            Material.DIAMOND_ORE, Material.EMERALD_BLOCK, Material.EMERALD_ORE, Material.REDSTONE_BLOCK, Material.REDSTONE_ORE};
    private static Material block = Material.STONE;
    private static int max = 0;
    private static int blocksBroken = 0;
    private final int[] maxList = new int[]{5_000, 10_000, 15_000, 25_000, 30_000, 45_000, 50_000, 75_000, 100_000, 125_000};

    public BlockBreakMining(AdventuresCraft plugin, StateFlag prisonMineFlag, SoundManager soundManager, ChanceCheck chanceCheck, ConsoleCommand consoleCommand, MMOItemsGive mmoItemsGive, BetonPointsManager betonPointsManager, Economy economy, NumberFormat numberFormat) {
        this.plugin = plugin;
        this.prisonMineFlag = prisonMineFlag;
        this.soundManager = soundManager;
        this.chanceCheck = chanceCheck;
        this.consoleCommand = consoleCommand;
        this.mmoItemsGive = mmoItemsGive;
        this.betonPointsManager = betonPointsManager;
        this.economy = economy;
        this.numberFormat = numberFormat;
    }

    @EventHandler
    private void mine(BlockBreakEvent event) {
        Player player = event.getPlayer();
        final RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        final RegionQuery query = container.createQuery();
        final Location location = BukkitAdapter.adapt(event.getBlock().getLocation());
        if (inRegion(query, location)) {
            final double currentWeight = Double.valueOf(PlaceholderAPI.setPlaceholders(player, "%ac_Stat_Weight%"));
            final double maxWeight = Double.valueOf(PlaceholderAPI.setPlaceholders(player, "%ac_Stat_MaxWeight%"));
            final int blockMultiplier = (int) Math.floor(Double.valueOf(PlaceholderAPI.setPlaceholders(player, "%ac_Stat_BlockMultiplier%")));
            final int enchantmentExplosive = Integer.valueOf(PlaceholderAPI.setPlaceholders(player, "%ac_Enchantment_Explosive%"));
            event.setDropItems(false);
            event.setExpToDrop(0);
            for (WeightPrices material : WeightPrices.values()) { // Loops over block list to validate block
                if (material.getMaterial().equals(event.getBlock().getType())) {
                    if ((material.getWeight() * blockMultiplier) + currentWeight <= maxWeight) { // Checks if player can hold the block's weight
                        minedBlock(player, material.material, blockMultiplier, (int) (material.getWeight() * blockMultiplier), false);
                        lootboxes(player);
                        enchantmentExperience(player);
                        enchantmentPetExperience(player);
                        enchantmentTreasurer(player);
                        enchantmentRandomizer(player);
                        enchantmentMidasTouch(player);
                        statTrack(player);
                        event.setCancelled(false);
                        if (max == 0) {
                            max = generateMax();
                            block = generateMaterial();
                        } else if (event.getBlock().getType() == block) {
                            blocksBroken++;
                            if (blocksBroken == max) {
                                consoleCommand.consoleCommand("randomBooster");
                                blocksBroken = 0;
                                max = generateMax();
                                block = generateMaterial();
                                for (Player targetPlayer : Bukkit.getOnlinePlayers()) {
                                    targetPlayer.sendMessage(ChatColor.DARK_RED.toString() + ChatColor.BOLD + "✦ " + ChatColor.RED + "Gala's " + ChatColor.GOLD + ChatColor.BOLD + "Team Task " + ChatColor.GREEN + "has been completed!");
                                    soundManager.playSound(player, Sound.ENTITY_EVOKER_CELEBRATE, 1, 2);
                                }
                            }
                        }
                    } else {
                        tooHeavy(player);
                        event.setCancelled(true);
                    }
                }
            }
            if (enchantmentExplosive > 0) {
                final int enchantmentExplosiveChance = Integer.valueOf(PlaceholderAPI.setPlaceholders(player, "%ac_Enchantment_Explosive_Chance%"));
                if (chanceCheck.chanceCheck(enchantmentExplosiveChance * Enchantments.ExplosiveChance.getIncrease())) {
                    double weight = 0;
                    for (int x = 1; x <= enchantmentExplosive; x++) {
                        final Block newBlock = event.getBlock().getRelative(x, 0, 0);
                        final Location newLocation = BukkitAdapter.adapt(newBlock.getLocation());
                        if (query.testState(newLocation, null, prisonMineFlag)) {
                            for (WeightPrices materialList : WeightPrices.values()) {
                                if (materialList.getMaterial().equals(newBlock.getType())) {
                                    weight += materialList.getWeight();
                                    if (((weight * blockMultiplier) + currentWeight) <= maxWeight) {
                                        minedBlock(player, materialList.getMaterial(), blockMultiplier, (int) (materialList.getWeight() * blockMultiplier), true);
                                        newBlock.setType(Material.AIR);
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void minedBlock(Player player, Material material, int amount, int weight, boolean explosive) {
        if (!explosive)
            betonPointsManager.givePoint(player, "items.TotalModifierBlocks", amount); // I forget, but I think this is meant for non-explosive
        betonPointsManager.givePoint(player, "items.TotalBlocks", 1); // Adds to Total Blocks for analytics & Achievements
        betonPointsManager.givePointWeight(player, weight); // Adds to current Weight
        betonPointsManager.givePoint(player, "items." + material, amount); // Adds to individual item for Sell
        betonPointsManager.givePoint(player, "blocks." + material, 1); // Gives a point for each specific block mined, used for Achievements
    }

    public void lootboxes(Player player) {
        double enchantmentLevel = Double.valueOf(PlaceholderAPI.setPlaceholders(player, "%ac_Enchantment_Luck%"));
        if (chanceCheck.chanceCheck(0.0020))
            mmoItemsGive.giveMMOItem(player, "CONSUMABLE", "LOOTBOX5");
        if (chanceCheck.chanceCheck(0.005))
            mmoItemsGive.giveMMOItem(player, "CONSUMABLE", "LOOTBOX4");
        if (chanceCheck.chanceCheck(0.0075))
            mmoItemsGive.giveMMOItem(player, "CONSUMABLE", "LOOTBOX3");
        if (chanceCheck.chanceCheck(0.0095))
            mmoItemsGive.giveMMOItem(player, "CONSUMABLE", "LOOTBOX2");
        if (chanceCheck.chanceCheck(.01))
            mmoItemsGive.giveMMOItem(player, "CONSUMABLE", "LOOTBOX");
//        Enchantment
        if (chanceCheck.chanceCheck((Enchantments.Luck.getIncrease() * enchantmentLevel) - .0080))
            mmoItemsGive.giveMMOItem(player, "CONSUMABLE", "LOOTBOX5");
        if (chanceCheck.chanceCheck((Enchantments.Luck.getIncrease() * enchantmentLevel) - .005))
            mmoItemsGive.giveMMOItem(player, "CONSUMABLE", "LOOTBOX4");
        if (chanceCheck.chanceCheck((Enchantments.Luck.getIncrease() * enchantmentLevel) - .0025))
            mmoItemsGive.giveMMOItem(player, "CONSUMABLE", "LOOTBOX3");
        if (chanceCheck.chanceCheck((Enchantments.Luck.getIncrease() * enchantmentLevel) - .0005))
            mmoItemsGive.giveMMOItem(player, "CONSUMABLE", "LOOTBOX2");
        if (chanceCheck.chanceCheck((Enchantments.Luck.getIncrease() * enchantmentLevel)))
            mmoItemsGive.giveMMOItem(player, "CONSUMABLE", "LOOTBOX");
    }

    public void enchantmentExperience(Player player) {
        double expMultiplier = Double.valueOf(PlaceholderAPI.setPlaceholders(player, "%ac_Stat_EXPMultiplier%"));
        betonPointsManager.givePointEXP(player, (int) expMultiplier);
        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, .5f, 1f);
    }

    public void enchantmentPetExperience(Player player) {
        if (hasEgg(player)) {
            double petExpMultiplier = Double.valueOf(PlaceholderAPI.setPlaceholders(player, "%ac_Stat_Pet_EXPMultiplier%"));
            betonPointsManager.givePointPetEXP(player, (int) petExpMultiplier);
        }
    }

    public void enchantmentTreasurer(Player player) {
        double enchantmentLevel = Double.valueOf(PlaceholderAPI.setPlaceholders(player, "%ac_Enchantment_Treasurer%"));
        if (enchantmentLevel > 0) {
            RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
            RegionQuery query = container.createQuery();
            final org.bukkit.Location originalLocation = new org.bukkit.Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY() - 1, player.getLocation().getZ());
            Location convertedLocation = BukkitAdapter.adapt(originalLocation);
            if (inRegion(query, convertedLocation)) {
                if (chanceCheck.chanceCheck(Enchantments.Treasurer.getIncrease() * enchantmentLevel)) {
                    originalLocation.getBlock().setType(Material.CHEST);
                    soundManager.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                    player.sendMessage(ChatColor.GREEN + "You found a hidden" + ChatColor.GOLD + " Treasure Chest" + ChatColor.GREEN + "!");
                }
            }
        }
    }

    public void enchantmentMidasTouch(Player player) {
        double enchantmentLevel = Double.valueOf(PlaceholderAPI.setPlaceholders(player, "%ac_Enchantment_Midas_Touch%"));
        if (enchantmentLevel > 0) {
            Random r = new Random();
            int result = r.nextInt(250000 - 50000) + 10;
            if (chanceCheck.chanceCheck(Enchantments.MidasTouch.getIncrease() * enchantmentLevel)) {
                economy.giveMoney(player, result);
                soundManager.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
            }
        }
    }

    public void enchantmentRandomizer(Player player) {
        double enchantmentLevel = Double.valueOf(PlaceholderAPI.setPlaceholders(player, "%ac_Enchantment_Randomizer%"));
        if (enchantmentLevel > 0) {
            RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
            RegionQuery query = container.createQuery();
            final org.bukkit.Location originalLocation = new org.bukkit.Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY() + 2, player.getLocation().getZ());
            Location convertedLocation = BukkitAdapter.adapt(player.getEyeLocation());
            if (inRegion(query, convertedLocation)) {
                if (chanceCheck.chanceCheck(Enchantments.Randomizer.getIncrease() * enchantmentLevel)) {
//                    player.getEyeLocation().getBlock().setType(getRandom(blocks));
                    originalLocation.getBlock().setType(getRandom(blocks));
                    soundManager.playSound(player, Sound.ENTITY_ARROW_HIT, 1, 2);
                    final String message = "Randomized";
                    final StringBuilder sb = new StringBuilder();
                    final Random random = new Random();
                    for (Character character : message.toCharArray()) {
                        sb.append(ChatColor.getByChar(Integer.toHexString(random.nextInt(message.length()))));
                        sb.append(character);
                    }
                    player.sendMessage(ChatColor.GREEN + "The block above you has been " + sb + ChatColor.GREEN + "!");
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

    private boolean inRegion(RegionQuery query, Location location) {
        if (query.testState(location, null, prisonMineFlag)) {
            return true;
        }
        return false;
    }

    private Material getRandom(Material[] array) {
        return array[new Random().nextInt(array.length)];
    }

    private final int generateMax() {
        int randomDuration = new Random().nextInt(maxList.length);
        return maxList[randomDuration];
    }

    private final Material generateMaterial() {
        int randomMaterial = new Random().nextInt(WeightPrices.values().length);
        final List<WeightPrices> VALUES = List.of(WeightPrices.values());
        return VALUES.get(randomMaterial).getMaterial();
    }

    public static Material getBlock() {
        return block;
    }

    public static int getMax() {
        return max;
    }

    public static int getBlocksBroken() {
        return blocksBroken;
    }

    private boolean hasEgg(Player player) {
        for (ItemStack item : player.getInventory()) {
            if (item != null)
                switch (item.getType()) {
                    case EGG:
                    case DRAGON_EGG:
                    case BLAZE_SPAWN_EGG:
                    case PLAYER_HEAD:
                        return true;
                }
        }
        return false;
    }
}
