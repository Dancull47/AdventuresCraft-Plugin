package monzter.adventurescraft.plugin.network.AdventureGamemode.Adventure.Events;

import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.mainMenu.Enchant;
import monzter.adventurescraft.plugin.utilities.beton.BetonPointsManager;
import monzter.adventurescraft.plugin.utilities.beton.BetonTagManager;
import monzter.adventurescraft.plugin.utilities.enums.Region;
import monzter.adventurescraft.plugin.utilities.general.*;
import monzter.adventurescraft.plugin.utilities.mmoitems.MMOItemsHelperImpl;
import net.Indyuce.mmocore.api.event.CustomBlockMineEvent;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class BlockBreakSystem implements Listener {
    private final AdventuresCraft plugin;
    private final SoundManager soundManager;
    private final AreaCheck areaCheck;
    private final ItemAdder itemAdder;
    private final ChanceCheck chanceCheck;
    private final ConsoleCommand consoleCommand;
    private final BetonPointsManager betonPointsManager;
    private final BetonTagManager betonTagManager;


    public BlockBreakSystem(AdventuresCraft plugin, SoundManager soundManager, AreaCheck areaCheck, ItemAdder itemAdder, ChanceCheck chanceCheck, ConsoleCommand consoleCommand, BetonPointsManager betonPointsManager, BetonTagManager betonTagManager) {
        this.plugin = plugin;
        this.soundManager = soundManager;
        this.areaCheck = areaCheck;
        this.itemAdder = itemAdder;
        this.chanceCheck = chanceCheck;
        this.consoleCommand = consoleCommand;
        this.betonPointsManager = betonPointsManager;
        this.betonTagManager = betonTagManager;
    }


    @EventHandler
    public void blockSystem(CustomBlockMineEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        List<ItemStack> dropsList = new ArrayList<>();
        int fortuneLevel = EnchantmentCalculator.calculateEnchantments(player, "FORTUNE");
        int yieldLevel = EnchantmentCalculator.calculateEnchantments(player, "YIELD");
        int experienceLevel = EnchantmentCalculator.calculateEnchantments(player, "EXPERIENCE");
        event.setDrops(dropsList);
        if (!event.isCancelled())
            for (Blocks blocks : Blocks.values())
                if (block.getType() == blocks.getMaterial()) {
                    if (blocks.getWhitelistRegions() == null || areaCheck.areaCheck(player, blocks.getWhitelistRegions()))
                        if (blocks.getBlacklistRegions() == null || !areaCheck.areaCheck(player, blocks.getBlacklistRegions())) {
                            int loop = 0;
                            for (ItemStack itemRewards : blocks.itemStacks) {
                                double rareChance = blocks.getChance()[loop];
                                if (rareChance < 1 && fortuneLevel > 0)
                                    rareChance = rareChance * (((fortuneLevel * Enchant.Enchantments.FORTUNE.getIncreasePerLevel()) / 100) + 1);
                                if (rareChance >= 1 || chanceCheck.chanceCheck(rareChance)) {
                                    int randomNum = ThreadLocalRandom.current().nextInt(blocks.getMinimums()[loop], itemRewards.getAmount() + 1);
                                    if (yieldLevel > 0)
                                        if (chanceCheck.chanceCheck((((yieldLevel * Enchant.Enchantments.YIELD.getIncreasePerLevel()))))) {
                                            soundManager.playSound(player, Sound.BLOCK_BEEHIVE_SHEAR, 1, 2);
                                            randomNum++;
                                        }
                                    if (betonTagManager.hasTag(player, "default-Tutorial-Reese.RESOURCE_COLLECTOR_COMPLETED"))
                                        itemAdder.itemAdder(player, itemRewards.asQuantity(randomNum));
                                    else
                                        itemAdder.itemDropper(player, itemRewards.asQuantity(randomNum));
                                }
                                loop++;
                            }
                            if (blocks.getProfessionEXP() != null)
                                for (String profession : blocks.getProfessionEXP()) {
                                    String[] split = profession.split(",");
                                    consoleCommand.consoleCommand("mmocore admin exp give " + player.getName() + " " + split[0] + " " + split[1]);
                                }
                            if (blocks.getExp() > 0)
                                betonPointsManager.givePoint(player, "EXP.EXP", (int) Math.round(experienceLevel * Enchant.Enchantments.EXPERIENCE.getIncreasePerLevel() + blocks.getExp()));
                        }
                }
    }

    /*
     *  Material = which block mined to trigger this
     *  ItemStacks[] = reward (amount matters as it's the max which will be given)
     *  Minimums[] = minimum amount of the item will be given. In order of the ItemStack[]
     *  Chance[] = chance of the reward being given. Also in order of the ItemStack[]
     *  WhitelistRegions[] = regions which will receive this "drop table" (NULL = Global/Universal, so all regions will get it)
     *  BlacklistRegions[] = regions which will NOT receive this "drop table" (Null = no blacklist.) This is mostly made for blacklisting Global Drops in certain areas.
     * */

    public enum Blocks {
        //        Mining
        STONE(Material.STONE, new ItemStack[]{new ItemStack(Material.STONE, 1), MMOItemsHelperImpl.getItem("MATERIAL", "ENCHANTED_COBBLESTONE", 1)},
                new int[]{1, 1}, new double[]{1, .0034}, null, null, new String[]{"MINING,1"}, 1),
        COBBLESTONE(Material.COBBLESTONE, new ItemStack[]{new ItemStack(Material.COBBLESTONE, 1), MMOItemsHelperImpl.getItem("MATERIAL", "ENCHANTED_COBBLESTONE", 1)},
                new int[]{1, 1}, new double[]{1, .0034}, null, null, new String[]{"MINING,1"}, 1),
        END_STONE(Material.END_STONE, new ItemStack[]{new ItemStack(Material.END_STONE, 1), MMOItemsHelperImpl.getItem("MATERIAL", "ENCHANTED_END_STONE", 1)},
                new int[]{1, 1}, new double[]{1, .0034}, null, null, new String[]{"MINING,2"}, 5),
        OBSIDIAN(Material.OBSIDIAN, new ItemStack[]{new ItemStack(Material.OBSIDIAN, 1), MMOItemsHelperImpl.getItem("MATERIAL", "ENCHANTED_OBSIDIAN", 1)},
                new int[]{1, 1}, new double[]{1, .0034}, null, null, new String[]{"MINING,8"}, 5),
        COAL_ORE(Material.COAL_ORE, new ItemStack[]{new ItemStack(Material.COAL, 1), MMOItemsHelperImpl.getItem("MATERIAL", "ENCHANTED_COAL", 1)},
                new int[]{1, 1}, new double[]{1, .0034}, null, null, new String[]{"MINING,2"}, 5),
        IRON_ORE(Material.IRON_ORE, new ItemStack[]{new ItemStack(Material.IRON_INGOT, 1), MMOItemsHelperImpl.getItem("MATERIAL", "ENCHANTED_IRON_INGOT", 1)},
                new int[]{1, 1}, new double[]{1, .0034}, null, null, new String[]{"MINING,2"}, 5),
        GOLD_ORE(Material.GOLD_ORE, new ItemStack[]{new ItemStack(Material.GOLD_INGOT, 1), MMOItemsHelperImpl.getItem("MATERIAL", "ENCHANTED_GOLD_INGOT", 1)},
                new int[]{1, 1}, new double[]{1, .0034}, null, null, new String[]{"MINING,3"}, 5),
        REDSTONE_ORE(Material.REDSTONE_ORE, new ItemStack[]{new ItemStack(Material.REDSTONE, 1), MMOItemsHelperImpl.getItem("MATERIAL", "ENCHANTED_REDSTONE", 1)},
                new int[]{1, 1}, new double[]{1, .0034}, null, null, new String[]{"MINING,3"}, 5),
        LAPIS_ORE(Material.LAPIS_ORE, new ItemStack[]{new ItemStack(Material.LAPIS_LAZULI, 1), MMOItemsHelperImpl.getItem("MATERIAL", "ENCHANTED_LAPIS_LAZULI", 1)},
                new int[]{1, 1}, new double[]{1, .0034}, null, null, new String[]{"MINING,3"}, 5),
        DIAMOND_ORE(Material.DIAMOND_ORE, new ItemStack[]{new ItemStack(Material.DIAMOND, 1), MMOItemsHelperImpl.getItem("MATERIAL", "ENCHANTED_DIAMOND", 1)},
                new int[]{1, 1}, new double[]{1, .0034}, null, null, new String[]{"MINING,4"}, 5),
        EMERALD_ORE(Material.EMERALD_ORE, new ItemStack[]{new ItemStack(Material.EMERALD, 1), MMOItemsHelperImpl.getItem("MATERIAL", "ENCHANTED_EMERALD", 1)},
                new int[]{1, 1}, new double[]{1, .0034}, null, null, new String[]{"MINING,5"}, 5),
        COAL_BLOCK(Material.COAL_BLOCK, new ItemStack[]{new ItemStack(Material.COAL, 4), MMOItemsHelperImpl.getItem("MATERIAL", "ENCHANTED_COAL", 2)},
                new int[]{2, 1}, new double[]{1, .0034}, null, null, new String[]{"MINING,3"}, 5),
        IRON_BLOCK(Material.IRON_BLOCK, new ItemStack[]{new ItemStack(Material.IRON_INGOT, 4), MMOItemsHelperImpl.getItem("MATERIAL", "ENCHANTED_IRON_INGOT", 2)},
                new int[]{2, 1}, new double[]{1, .0034}, null, null, new String[]{"MINING,3"}, 5),
        GOLD_BLOCK(Material.GOLD_BLOCK, new ItemStack[]{new ItemStack(Material.GOLD_INGOT, 4), MMOItemsHelperImpl.getItem("MATERIAL", "ENCHANTED_GOLD_INGOT", 2)},
                new int[]{2, 1}, new double[]{1, .0034}, null, null, new String[]{"MINING,4"}, 5),
        REDSTONE_BLOCK(Material.REDSTONE_BLOCK, new ItemStack[]{new ItemStack(Material.REDSTONE, 4), MMOItemsHelperImpl.getItem("MATERIAL", "ENCHANTED_REDSTONE", 2)},
                new int[]{2, 1}, new double[]{1, .0034}, null, null, new String[]{"MINING,4"}, 5),
        LAPIS_BLOCK(Material.LAPIS_BLOCK, new ItemStack[]{new ItemStack(Material.LAPIS_LAZULI, 4), MMOItemsHelperImpl.getItem("MATERIAL", "ENCHANTED_LAPIS_LAZULI", 2)},
                new int[]{2, 1}, new double[]{1, .0034}, null, null, new String[]{"MINING,4"}, 5),
        DIAMOND_BLOCK(Material.DIAMOND_BLOCK, new ItemStack[]{new ItemStack(Material.DIAMOND, 4), MMOItemsHelperImpl.getItem("MATERIAL", "ENCHANTED_DIAMOND", 2)},
                new int[]{2, 1}, new double[]{1, .0034}, null, null, new String[]{"MINING,5"}, 5),
        EMERALD_BLOCK(Material.EMERALD_BLOCK, new ItemStack[]{new ItemStack(Material.EMERALD, 4), MMOItemsHelperImpl.getItem("MATERIAL", "ENCHANTED_EMERALD", 2)},
                new int[]{2, 1}, new double[]{1, .0034}, null, null, new String[]{"MINING,6"}, 5),
        VOID_SHROOM(Material.BROWN_MUSHROOM, new ItemStack[]{MMOItemsHelperImpl.getItem("QUEST", "VOID_SHROOM", 1)},
                new int[]{1, 1}, new double[]{1, .0034}, new String[]{Region.VOID.getName()}, null, new String[]{"MINING,1"}, 5),
        CHORUS_FLOWER(Material.CHORUS_FLOWER, new ItemStack[]{MMOItemsHelperImpl.getItem("MATERIAL", "CHORUS_SEED", 2), MMOItemsHelperImpl.getItem("MATERIAL", "ENCHANTED_CHORUS_FRUIT", 1)},
                new int[]{1, 1}, new double[]{1, .0034}, null, null, new String[]{"MINING,2"}, 5),

        //        Foraging
        OAK_LOG(Material.OAK_LOG, new ItemStack[]{new ItemStack(Material.OAK_LOG, 1), MMOItemsHelperImpl.getItem("MATERIAL", "ENCHANTED_OAK_LOG", 1)},
                new int[]{1, 1}, new double[]{1, .0034}, null, null, new String[]{"FORAGING,1"}, 5),
        SPRUCE_LOG(Material.SPRUCE_LOG, new ItemStack[]{new ItemStack(Material.SPRUCE_LOG, 1), MMOItemsHelperImpl.getItem("MATERIAL", "ENCHANTED_SPRUCE_LOG", 1)},
                new int[]{1, 1}, new double[]{1, .0034}, null, null, new String[]{"FORAGING,1"}, 5),
        DARK_OAK_LOG(Material.DARK_OAK_LOG, new ItemStack[]{new ItemStack(Material.DARK_OAK_LOG, 1), MMOItemsHelperImpl.getItem("MATERIAL", "ENCHANTED_DARK_OAK_LOG", 1)},
                new int[]{1, 1}, new double[]{1, .0034}, null, null, new String[]{"FORAGING,1"}, 5),
        BIRCH_LOG(Material.BIRCH_LOG, new ItemStack[]{new ItemStack(Material.BIRCH_LOG, 1), MMOItemsHelperImpl.getItem("MATERIAL", "ENCHANTED_BIRCH_LOG", 1)},
                new int[]{1, 1}, new double[]{1, .0034}, null, null, new String[]{"FORAGING,1"}, 5),
        ACACIA_LOG(Material.ACACIA_LOG, new ItemStack[]{new ItemStack(Material.ACACIA_LOG, 1), MMOItemsHelperImpl.getItem("MATERIAL", "ENCHANTED_ACACIA_LOG", 1)},
                new int[]{1, 1}, new double[]{1, .0034}, null, null, new String[]{"FORAGING,1"}, 5),
        JUNGLE_LOG(Material.JUNGLE_LOG, new ItemStack[]{new ItemStack(Material.JUNGLE_LOG, 1), MMOItemsHelperImpl.getItem("MATERIAL", "ENCHANTED_JUNGLE_LOG", 1)},
                new int[]{1, 1}, new double[]{1, .0034}, null, null, new String[]{"FORAGING,1"}, 5),
        OAK_LEAVES(Material.OAK_LEAVES, new ItemStack[]{new ItemStack(Material.OAK_LEAVES, 1)},
                new int[]{1}, new double[]{1}, null, null, new String[]{"FORAGING,1"}, 5),
        SPRUCE_LEAVES(Material.SPRUCE_LEAVES, new ItemStack[]{new ItemStack(Material.SPRUCE_LEAVES, 1)},
                new int[]{1}, new double[]{1}, null, null, new String[]{"FORAGING,1"}, 5),
        DARK_OAK_LEAVES(Material.DARK_OAK_LEAVES, new ItemStack[]{new ItemStack(Material.DARK_OAK_LEAVES, 1)},
                new int[]{1}, new double[]{1}, null, null, new String[]{"FORAGING,1"}, 5),
        BIRCH_LEAVES(Material.BIRCH_LEAVES, new ItemStack[]{new ItemStack(Material.BIRCH_LEAVES, 1)},
                new int[]{1}, new double[]{1}, null, null, new String[]{"FORAGING,1"}, 5),
        ACACIA_LEAVES(Material.ACACIA_LEAVES, new ItemStack[]{new ItemStack(Material.ACACIA_LEAVES, 1)},
                new int[]{1}, new double[]{1}, null, null, new String[]{"FORAGING,1"}, 5),
        JUNGLE_LEAVES(Material.JUNGLE_LEAVES, new ItemStack[]{new ItemStack(Material.JUNGLE_LEAVES, 1)},
                new int[]{1}, new double[]{1}, null, null, new String[]{"FORAGING,1"}, 5),
        BEE_NEST(Material.BEE_NEST, new ItemStack[]{new ItemStack(Material.HONEYCOMB, 1)},
                new int[]{1}, new double[]{1}, null, null, new String[]{"FORAGING,1"}, 5),
        COCOA(Material.COCOA, new ItemStack[]{new ItemStack(Material.COCOA_BEANS, 1), MMOItemsHelperImpl.getItem("MATERIAL", "ENCHANTED_COCOA_BEANS", 1)},
                new int[]{1, 1}, new double[]{1, .0034}, null, null, new String[]{"FARMING,3"}, 5),
        //  Farming
        TALL_GRASS(Material.TALL_GRASS, new ItemStack[]{new ItemStack(Material.GRASS, 1), MMOItemsHelperImpl.getItem("MATERIAL", "ENCHANTED_GRASS", 1)},
                new int[]{1, 1}, new double[]{1, .0034}, null, null, new String[]{"FARMING,1"}, 0),
        GRASS(Material.GRASS, new ItemStack[]{new ItemStack(Material.GRASS, 1), MMOItemsHelperImpl.getItem("MATERIAL", "ENCHANTED_GRASS", 1)},
                new int[]{1, 1}, new double[]{1, .0034}, null, null, new String[]{"FARMING,1"}, 0),
        FERN(Material.FERN, new ItemStack[]{new ItemStack(Material.GRASS, 1), MMOItemsHelperImpl.getItem("MATERIAL", "ENCHANTED_GRASS", 1)},
                new int[]{1, 1}, new double[]{1, .0034}, null, null, new String[]{"FARMING,1"}, 0),
        WHEAT(Material.WHEAT, new ItemStack[]{new ItemStack(Material.WHEAT, 1), new ItemStack(Material.WHEAT_SEEDS, 1), MMOItemsHelperImpl.getItem("MATERIAL", "ENCHANTED_WHEAT", 1), MMOItemsHelperImpl.getItem("MATERIAL", "ENCHANTED_WHEAT_SEEDS", 1)},
                new int[]{1, 1, 1, 1}, new double[]{1, 1, .0034, .0034}, null, null, new String[]{"FARMING,1"}, 0),
        CARROTS(Material.CARROTS, new ItemStack[]{new ItemStack(Material.CARROT, 1), MMOItemsHelperImpl.getItem("MATERIAL", "ENCHANTED_CARROT", 1)},
                new int[]{1, 1}, new double[]{1, .0034}, null, null, new String[]{"FARMING,2"}, 0),
        POTATOES(Material.POTATOES, new ItemStack[]{new ItemStack(Material.POTATO, 1), MMOItemsHelperImpl.getItem("MATERIAL", "ENCHANTED_POTATO", 1)},
                new int[]{1, 1}, new double[]{1, .0034}, null, null, new String[]{"FARMING,2"}, 0),
        SUGAR_CANE(Material.SUGAR_CANE, new ItemStack[]{new ItemStack(Material.SUGAR_CANE, 1), MMOItemsHelperImpl.getItem("MATERIAL", "ENCHANTED_SUGAR_CANE", 1)},
                new int[]{1, 1}, new double[]{1, .0034}, null, null, new String[]{"FARMING,2"}, 0),
        CACTUS(Material.CACTUS, new ItemStack[]{new ItemStack(Material.CACTUS, 1), MMOItemsHelperImpl.getItem("MATERIAL", "ENCHANTED_CACTUS", 1)},
                new int[]{1, 1}, new double[]{1, .0034}, null, null, new String[]{"FARMING,3"}, 0),
        MELON(Material.MELON, new ItemStack[]{new ItemStack(Material.MELON_SLICE, 1), MMOItemsHelperImpl.getItem("MATERIAL", "ENCHANTED_MELON_SLICE", 1)},
                new int[]{1, 1}, new double[]{1, .0034}, null, null, new String[]{"FARMING,2"}, 0),
        PUMPKIN(Material.PUMPKIN, new ItemStack[]{new ItemStack(Material.PUMPKIN, 1), MMOItemsHelperImpl.getItem("MATERIAL", "ENCHANTED_PUMPKIN", 1)},
                new int[]{1, 1}, new double[]{1, .0034}, null, null, new String[]{"FARMING,2"}, 0),
        BEETROOTS(Material.BEETROOTS, new ItemStack[]{new ItemStack(Material.BEETROOT, 1), MMOItemsHelperImpl.getItem("MATERIAL", "ENCHANTED_BEETROOT", 1)},
                new int[]{1, 1}, new double[]{1, .0034}, null, null, new String[]{"FARMING,2"}, 0),
        BROWN_MUSHROOM(Material.BROWN_MUSHROOM, new ItemStack[]{new ItemStack(Material.BROWN_MUSHROOM, 1), MMOItemsHelperImpl.getItem("MATERIAL", "ENCHANTED_BROWN_MUSHROOM", 1)},
                new int[]{1, 1}, new double[]{1, .0034}, null, null, new String[]{"FARMING,3"}, 0),
        BROWN_MUSHROOM_BLOCK(Material.BROWN_MUSHROOM_BLOCK, new ItemStack[]{new ItemStack(Material.BROWN_MUSHROOM, 2), MMOItemsHelperImpl.getItem("MATERIAL", "ENCHANTED_BROWN_MUSHROOM", 1)},
                new int[]{1, 1}, new double[]{1, .0034}, null, null, new String[]{"FARMING,3"}, 0),
        RED_MUSHROOM(Material.RED_MUSHROOM, new ItemStack[]{new ItemStack(Material.RED_MUSHROOM, 1), MMOItemsHelperImpl.getItem("MATERIAL", "ENCHANTED_RED_MUSHROOM", 1)},
                new int[]{1, 1}, new double[]{1, .0034}, null, null, new String[]{"FARMING,3"}, 0),
        RED_MUSHROOM_BLOCK(Material.RED_MUSHROOM_BLOCK, new ItemStack[]{new ItemStack(Material.RED_MUSHROOM, 2), MMOItemsHelperImpl.getItem("MATERIAL", "ENCHANTED_RED_MUSHROOM", 1)},
                new int[]{1, 1}, new double[]{1, .0034}, null, null, new String[]{"FARMING,3"}, 0),
        MUSHROOM_STEM(Material.MUSHROOM_STEM, new ItemStack[]{new ItemStack(Material.RED_MUSHROOM, 2), new ItemStack(Material.BROWN_MUSHROOM, 2), MMOItemsHelperImpl.getItem("MATERIAL", "ENCHANTED_RED_MUSHROOM", 1), MMOItemsHelperImpl.getItem("MATERIAL", "ENCHANTED_BROWN_MUSHROOM", 1)},
                new int[]{1, 1, 1, 1}, new double[]{.5, .5, .0034, .0034}, null, null, new String[]{"FARMING,2"}, 0),
        CHAIN(Material.CHAIN, null, null, null, null, null, null, 0),
        FIRE_CORAL(Material.FIRE_CORAL, null, null, null, null, null, null, 0),
        SEAGRASS(Material.SEAGRASS, new ItemStack[]{new ItemStack(Material.SEAGRASS, 1)},
                new int[]{1}, new double[]{1}, null, null, new String[]{"FARMING,1"}, 0),

//        STONE2(Material.STONE, new ItemStack[]{new ItemStack(Material.OBSIDIAN, 1)},
//                new int[]{1, 2}, new double[]{1}, new String[]{Region.VOID.getName()}, new String[]{Region.FOREST.getName()}),
//        STONE3(Material.STONE, new ItemStack[]{new ItemStack(Material.DIRT, 1)},
//                new int[]{1, 2}, new double[]{1}, new String[]{Region.FOREST.getName()}, new String[]{Region.VOID.getName()}),
        ;

        private final Material material;
        private final ItemStack[] itemStacks;
        private final int[] minimums;
        private final double[] chance;
        private final String[] whitelistRegions;
        private final String[] blacklistRegions;
        private final String[] professionEXP;
        private final int exp;


        Blocks(Material material, ItemStack[] itemStacks, int[] minimums, double[] chance, String[] whitelistRegions, String[] blacklistRegions, String[] professionEXP, int exp) {
            this.material = material;
            this.itemStacks = itemStacks;
            this.minimums = minimums;
            this.chance = chance;
            this.whitelistRegions = whitelistRegions;
            this.blacklistRegions = blacklistRegions;
            this.professionEXP = professionEXP;
            this.exp = exp;
        }

        public Material getMaterial() {
            return material;
        }

        public double[] getChance() {
            return chance;
        }

        public ItemStack[] getItemStacks() {
            return itemStacks;
        }

        public int[] getMinimums() {
            return minimums;
        }

        public String[] getProfessionEXP() {
            return professionEXP;
        }

        public int getExp() {
            return exp;
        }

        public String[] getWhitelistRegions() {
            return whitelistRegions;
        }

        public String[] getBlacklistRegions() {
            return blacklistRegions;
        }
    }
}