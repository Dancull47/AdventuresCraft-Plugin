package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.shops.npcs;

import net.Indyuce.mmoitems.MMOItems;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public enum ItemList {
    ITEM1(Shops.FARMER, 64, new ItemStack(Material.WHEAT_SEEDS), 2, null, null),
    ITEM2(Shops.FARMER, 64, new ItemStack(Material.WHEAT), 2, null, null),
    ITEM3(Shops.FARMER, 64, new ItemStack(Material.CARROT), 4, null, null),
    ITEM4(Shops.FARMER, 64, new ItemStack(Material.POTATO), 4, null, null),
    ITEM5(Shops.FARMER, 64, new ItemStack(Material.BEEF), 5, null, null),
    ITEM6(Shops.FARMER, 64, new ItemStack(Material.LEATHER), 10, null, null),
    ITEM7(Shops.FARMER, 64, new ItemStack(Material.PORKCHOP), 5, null, null),
    ITEM8(Shops.FARMER, 64, new ItemStack(Material.MUTTON), 6, null, null),
    ITEM9(Shops.FARMER, 64, new ItemStack(Material.WHITE_WOOL), 10, null, null),
    ITEM10(Shops.FARMER, 64, new ItemStack(Material.CHICKEN), 8, null, null),
    ITEM11(Shops.FARMER, 64, new ItemStack(Material.FEATHER), 5, null, null),
    ITEM12(Shops.FARMER, 64, new ItemStack(Material.RABBIT), 6, null, null),
    ITEM13(Shops.FARMER, 64, new ItemStack(Material.RABBIT_HIDE), 10, null, null),
    ITEM14(Shops.FARMER, 64, new ItemStack(Material.RABBIT_FOOT), 10, null, null),
    ITEM15(Shops.FARMER, 1, MMOItems.plugin.getItem("TOOL", "WOODEN_HOE"), 10, null, null),

    MercenaryITEM1(Shops.MERCENARY, 64, new ItemStack(Material.BONE), 20, null, null),
    MercenaryITEM2(Shops.MERCENARY, 64, new ItemStack(Material.SPIDER_EYE), 35, null, null),
    MercenaryITEM3(Shops.MERCENARY, 64, new ItemStack(Material.STRING), 30, null, null),
    MercenaryITEM4(Shops.MERCENARY, 64, new ItemStack(Material.GUNPOWDER), 50, null, null),
    MercenaryITEM5(Shops.MERCENARY, 64, new ItemStack(Material.ROTTEN_FLESH), 15, null, null),
    MercenaryITEM6(Shops.MERCENARY, 64, new ItemStack(Material.ENDER_PEARL), 30, null, null),
    MercenaryITEM7(Shops.MERCENARY, 64, new ItemStack(Material.MAGMA_CREAM), 20, null, null),
    MercenaryITEM8(Shops.MERCENARY, 64, new ItemStack(Material.BLAZE_ROD), 25, null, null),
    MercenaryITEM9(Shops.MERCENARY, 64, new ItemStack(Material.SLIME_BALL), 60, null, null),
    MercenaryITEM10(Shops.MERCENARY, 64, new ItemStack(Material.ARROW), 20, null, null),
    MercenaryITEM11(Shops.MERCENARY, 1, MMOItems.plugin.getItem("SWORD", "BASIC_SWORD"), 250, null, null),

    LumberjackITEM1(Shops.LUMBERJACK, 64, new ItemStack(Material.OAK_LOG), 8, null, null),
    LumberjackITEM2(Shops.LUMBERJACK, 64, new ItemStack(Material.SPRUCE_LOG), 16, null, null),
    LumberjackITEM3(Shops.LUMBERJACK, 64, new ItemStack(Material.DARK_OAK_LOG), 60, null, null),
    LumberjackITEM4(Shops.LUMBERJACK, 64, new ItemStack(Material.BIRCH_LOG), 20, null, null),
    LumberjackITEM5(Shops.LUMBERJACK, 64, new ItemStack(Material.ACACIA_LOG), 24, null, null),
    LumberjackITEM6(Shops.LUMBERJACK, 64, new ItemStack(Material.JUNGLE_LOG), 26, null, null),
    LumberjackITEM7(Shops.LUMBERJACK, 64, new ItemStack(Material.OAK_LEAVES), 26, null, null),
    LumberjackITEM8(Shops.LUMBERJACK, 64, new ItemStack(Material.SPRUCE_LEAVES), 50, null, null),
    LumberjackITEM9(Shops.LUMBERJACK, 64, new ItemStack(Material.DARK_OAK_LEAVES), 120, null, null),
    LumberjackITEM10(Shops.LUMBERJACK, 64, new ItemStack(Material.BIRCH_LEAVES), 30, null, null),
    LumberjackITEM11(Shops.LUMBERJACK, 64, new ItemStack(Material.ACACIA_LEAVES), 30, null, null),
    LumberjackITEM12(Shops.LUMBERJACK, 64, new ItemStack(Material.JUNGLE_LEAVES), 30, null, null),
    LumberjackITEM13(Shops.LUMBERJACK, 64, new ItemStack(Material.COCOA_BEANS), 10, null, null),
    LumberjackITEM14(Shops.LUMBERJACK, 64, new ItemStack(Material.HONEYCOMB), 4, null, null),
    LumberjackITEM15(Shops.LUMBERJACK, 64, new ItemStack(Material.RABBIT), 6, null, null),
    LumberjackITEM16(Shops.LUMBERJACK, 64, new ItemStack(Material.RABBIT_HIDE), 10, null, null),
    LumberjackITEM17(Shops.LUMBERJACK, 64, new ItemStack(Material.RABBIT_FOOT), 10, null, null),
    LumberjackITEM18(Shops.LUMBERJACK, 1, MMOItems.plugin.getItem("TOOL", "WOODEN_AXE"), 100, null, null),
    LumberjackITEM19(Shops.LUMBERJACK, 1, MMOItems.plugin.getItem("TOOL", "STONE_AXE"), 250, null, null),

    MiningITEM1(Shops.MINING, 64, new ItemStack(Material.STONE), 6, null, null),
    MiningITEM2(Shops.MINING, 64, new ItemStack(Material.COBBLESTONE), 10, null, null),
    MiningITEM3(Shops.MINING, 64, new ItemStack(Material.COAL), 6, null, null),
    MiningITEM4(Shops.MINING, 64, new ItemStack(Material.IRON_INGOT), 8, null, null),
    MiningITEM5(Shops.MINING, 64, new ItemStack(Material.GOLD_INGOT), 10, null, null),
    MiningITEM6(Shops.MINING, 64, new ItemStack(Material.REDSTONE), 6, null, null),
    MiningITEM7(Shops.MINING, 64, new ItemStack(Material.LAPIS_LAZULI), 6, null, null),
    MiningITEM8(Shops.MINING, 64, new ItemStack(Material.DIAMOND), 12, null, null),
    MiningITEM9(Shops.MINING, 64, new ItemStack(Material.EMERALD), 7, null, null),
    MiningITEM10(Shops.MINING, 64, new ItemStack(Material.SPIDER_EYE), 35, null, null),
    MiningITEM11(Shops.MINING, 64, new ItemStack(Material.STRING), 30, null, null),
    MiningITEM12(Shops.MINING, 64, new ItemStack(Material.BONE), 20, null, null),
    MiningITEM13(Shops.MINING, 64, new ItemStack(Material.GUNPOWDER), 50, null, null),
    MiningITEM14(Shops.MINING, 1, MMOItems.plugin.getItem("TOOL", "WOODEN_PICKAXE"), 100, null, null),
    MiningITEM15(Shops.MINING, 1, MMOItems.plugin.getItem("TOOL", "STONE_PICKAXE"), 250, null, null),

    HellITEM1(Shops.DEMON, 64, new ItemStack(Material.NETHERRACK), 10, null, null),
    HellITEM2(Shops.DEMON, 64, new ItemStack(Material.NETHER_BRICKS), 21, null, null),
    HellITEM3(Shops.DEMON, 64, new ItemStack(Material.SOUL_SAND), 15, null, null),
    HellITEM4(Shops.DEMON, 64, new ItemStack(Material.GLOWSTONE), 15, null, null),
    HellITEM5(Shops.DEMON, 64, new ItemStack(Material.NETHER_WART), 6, null, null),
    HellITEM6(Shops.DEMON, 64, new ItemStack(Material.GLOWSTONE_DUST), 10, null, null),
    HellITEM7(Shops.DEMON, 64, new ItemStack(Material.QUARTZ), 8, null, null),
    HellITEM8(Shops.DEMON, 64, new ItemStack(Material.OBSIDIAN), 14, null, null),
    HellITEM9(Shops.DEMON, 64, new ItemStack(Material.ROTTEN_FLESH), 14, null, null),
    HellITEM10(Shops.DEMON, 64, new ItemStack(Material.BLAZE_ROD), 25, null, null),
    HellITEM11(Shops.DEMON, 64, new ItemStack(Material.MAGMA_CREAM), 20, null, null),
    HellITEM12(Shops.DEMON, 64, new ItemStack(Material.BONE), 20, null, null),

    LandscaperITEM1(Shops.LANDSCAPER, 64, new ItemStack(Material.DIRT), 4, null, null),
    LandscaperITEM2(Shops.LANDSCAPER, 64, new ItemStack(Material.PODZOL), 6, null, null),
    LandscaperITEM3(Shops.LANDSCAPER, 64, new ItemStack(Material.MYCELIUM), 18, null, null),
    LandscaperITEM4(Shops.LANDSCAPER, 64, new ItemStack(Material.SAND), 15, null, null),
    LandscaperITEM5(Shops.LANDSCAPER, 64, new ItemStack(Material.RED_SAND), 18, null, null),
    LandscaperITEM6(Shops.LANDSCAPER, 64, new ItemStack(Material.SANDSTONE), 19, null, null),
    LandscaperITEM7(Shops.LANDSCAPER, 64, new ItemStack(Material.RED_SANDSTONE), 20, null, null),
    LandscaperITEM8(Shops.LANDSCAPER, 64, new ItemStack(Material.GLASS), 50, null, null),
    LandscaperITEM9(Shops.LANDSCAPER, 64, new ItemStack(Material.GRAVEL), 57, null, null),
    LandscaperITEM10(Shops.LANDSCAPER, 64, new ItemStack(Material.DIORITE), 21, null, null),
    LandscaperITEM11(Shops.LANDSCAPER, 64, new ItemStack(Material.TERRACOTTA), 18, null, null),
    LandscaperITEM12(Shops.LANDSCAPER, 64, new ItemStack(Material.SEA_LANTERN), 20, null, null),
    LandscaperITEM13(Shops.LANDSCAPER, 64, new ItemStack(Material.PRISMARINE), 26, null, null),
    LandscaperITEM14(Shops.LANDSCAPER, 64, new ItemStack(Material.PRISMARINE_BRICKS), 28, null, null),
    LandscaperITEM15(Shops.LANDSCAPER, 64, new ItemStack(Material.DARK_PRISMARINE), 28, null, null),
    LandscaperITEM16(Shops.LANDSCAPER, 64, new ItemStack(Material.WHITE_WOOL), 15, null, null),

    EstateITEM1(Shops.ESTATE, 64, new ItemStack(Material.PUMPKIN), 6, null, null),
    EstateITEM2(Shops.ESTATE, 64, new ItemStack(Material.MELON), 10, null, null),
    EstateITEM3(Shops.ESTATE, 64, new ItemStack(Material.MELON_SLICE), 2, null, null),
    EstateITEM4(Shops.ESTATE, 64, new ItemStack(Material.SUGAR_CANE), 10, null, null),
    EstateITEM5(Shops.ESTATE, 64, new ItemStack(Material.CACTUS), 24, null, null),
    EstateITEM6(Shops.ESTATE, 64, new ItemStack(Material.BEETROOT), 14, null, null),
    EstateITEM7(Shops.ESTATE, 64, new ItemStack(Material.RED_MUSHROOM), 6, null, null),
    EstateITEM8(Shops.ESTATE, 64, new ItemStack(Material.BROWN_MUSHROOM), 6, null, null),

    JoyITEM1(Shops.JOY, 1, MMOItems.plugin.getItem("CONSUMABLE", "JOY_SOUP"), 100, null, null),
    JoyITEM2(Shops.JOY, 1, MMOItems.plugin.getItem("CONSUMABLE", "JOY_SOUP3"), 250, null, null),
    JoyITEM3(Shops.JOY, 1, MMOItems.plugin.getItem("CONSUMABLE", "JOY_SOUP4"), 500, null, null),
    JoyITEM4(Shops.JOY, 1, MMOItems.plugin.getItem("BOW", "JOYS_BOW2"), 5_000, null, null),

    CatLadyITEM1(Shops.CAT_LADY, 64, new ItemStack(Material.LEAD), 25, null, null),

    EnchanterITEM1(Shops.ENCHANTER, 1, MMOItems.plugin.getItem("ENCHANTMENT", "ENCHANTING_BOOK"), 100, null, null),
    EnchanterITEM2(Shops.ENCHANTER, 1, MMOItems.plugin.getItem("ENCHANTMENT", "ENCHANTING_BOOK2"), 250, new ItemStack[]{MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_PAPER").asQuantity(8)}, null),
    EnchanterITEM3(Shops.ENCHANTER, 1, MMOItems.plugin.getItem("ENCHANTMENT", "ENCHANTING_BOOK3"), 500, new ItemStack[]{MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_PAPER").asQuantity(16)}, null),
    EnchanterITEM4(Shops.ENCHANTER, 1, MMOItems.plugin.getItem("ENCHANTMENT", "ENCHANTING_BOOK4"), 1_000, new ItemStack[]{MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_PAPER").asQuantity(32)}, null),
    EnchanterITEM5(Shops.ENCHANTER, 1, MMOItems.plugin.getItem("ENCHANTMENT", "ENCHANTING_BOOK5"), 1_500, new ItemStack[]{MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_PAPER").asQuantity(64)}, null),
    EnchanterITEM7(Shops.ENCHANTER, 1, MMOItems.plugin.getItem("CONSUMABLE", "ENCHANTED_BOX"), 150, null, null),
    EnchanterITEM8(Shops.ENCHANTER, 1, MMOItems.plugin.getItem("CONSUMABLE", "ENCHANTED_BOX2"), 250, null, null),
    EnchanterITEM9(Shops.ENCHANTER, 1, MMOItems.plugin.getItem("CONSUMABLE", "ENCHANTED_BOX3"), 350, null, null),
    EnchanterITEMF1(Shops.ENCHANTER, 0, null, 0, null, null),
    EnchanterITEMF2(Shops.ENCHANTER, 0, null, 0, null, null),
    EnchanterITEM10(Shops.ENCHANTER, 1, MMOItems.plugin.getItem("CONSUMABLE", "XP_BOTTLE"), 15, new ItemStack[]{MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_LAPIS").asQuantity(1)}, null),
    EnchanterITEM11(Shops.ENCHANTER, 1, MMOItems.plugin.getItem("CONSUMABLE", "XP_BOTTLE2"), 100, new ItemStack[]{MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_LAPIS").asQuantity(8)}, null),
    EnchanterITEM13(Shops.ENCHANTER, 1, MMOItems.plugin.getItem("CONSUMABLE", "XP_BOTTLE3"), 500, new ItemStack[]{MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_LAPIS").asQuantity(16)}, null),

    WANDERING_TRADER1(Shops.WANDERING_TRADER, 64, new ItemStack(Material.COAL), 0, new ItemStack[]{MMOItems.plugin.getItem("MATERIAL", "COAL_SHARD").asQuantity(8)}, null),
    WANDERING_TRADER2(Shops.WANDERING_TRADER, 64, new ItemStack(Material.IRON_INGOT), 0, new ItemStack[]{MMOItems.plugin.getItem("MATERIAL", "IRON_SHARD").asQuantity(8)}, null),
    WANDERING_TRADER3(Shops.WANDERING_TRADER, 64, new ItemStack(Material.GOLD_INGOT), 0, new ItemStack[]{MMOItems.plugin.getItem("MATERIAL", "GOLD_SHARD").asQuantity(8)}, null),
    WANDERING_TRADER4(Shops.WANDERING_TRADER, 64, new ItemStack(Material.DIAMOND), 0, new ItemStack[]{MMOItems.plugin.getItem("MATERIAL", "DIAMOND_SHARD").asQuantity(8)}, null),
    WANDERING_TRADER5(Shops.WANDERING_TRADER, 64, new ItemStack(Material.EMERALD), 0, new ItemStack[]{MMOItems.plugin.getItem("MATERIAL", "EMERALD_SHARD").asQuantity(8)}, null),

    MINER1(Shops.MINER, 1, MMOItems.plugin.getItem("TOOL", "MINERS_PICKAXE"), 0, new ItemStack[]{MMOItems.plugin.getItem("TOOL", "WOODEN_PICKAXE").asQuantity(1), new ItemStack(Material.STONE).asQuantity(64)}, null),

    FARMING_ACCESSORIES1(Shops.FARMING_ACCESSORIES, 1, MMOItems.plugin.getItem("ACCESSORY", "POWERFUL_NECKLACE3"),
            0, new ItemStack[]{MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_CARROT").asQuantity(9)},
            new String[]{"FARMING,2"}),
    FARMING_ACCESSORIES2(Shops.FARMING_ACCESSORIES, 1, MMOItems.plugin.getItem("ACCESSORY", "POWERFUL_NECKLACE4"),
            0, new ItemStack[]{MMOItems.plugin.getItem("ACCESSORY", "POWERFUL_NECKLACE3").asQuantity(1), MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_CARROT").asQuantity(32)},
            new String[]{"FARMING,5"}),
    FARMING_ACCESSORIES3(Shops.FARMING_ACCESSORIES, 1, MMOItems.plugin.getItem("ACCESSORY", "POWERFUL_NECKLACE5"),
            0, new ItemStack[]{MMOItems.plugin.getItem("ACCESSORY", "POWERFUL_NECKLACE4").asQuantity(1), MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_CARROT").asQuantity(64)},
            new String[]{"FARMING,7"}),
    FARMING_ACCESSORIES4(Shops.FARMING_ACCESSORIES, 1, MMOItems.plugin.getItem("ACCESSORY", "LUCKY_STRIKER3"),
            0, new ItemStack[]{MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_POTATO").asQuantity(9)},
            new String[]{"FARMING,2"}),
    FARMING_ACCESSORIES5(Shops.FARMING_ACCESSORIES, 1, MMOItems.plugin.getItem("ACCESSORY", "LUCKY_STRIKER4"),
            0, new ItemStack[]{MMOItems.plugin.getItem("ACCESSORY", "LUCKY_STRIKER3").asQuantity(1), MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_POTATO").asQuantity(32)},
            new String[]{"FARMING,5"}),
    FARMING_ACCESSORIES6(Shops.FARMING_ACCESSORIES, 1, MMOItems.plugin.getItem("ACCESSORY", "LUCKY_STRIKER5"),
            0, new ItemStack[]{MMOItems.plugin.getItem("ACCESSORY", "LUCKY_STRIKER4").asQuantity(1), MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_POTATO").asQuantity(64)},
            new String[]{"FARMING,7"}),
    //    Move to Estate since it's pumpkins
    FARMING_ACCESSORIES7(Shops.FARMING_ACCESSORIES, 1, MMOItems.plugin.getItem("ACCESSORY", "DEEP_IMPACTOR3"),
            0, new ItemStack[]{MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_PUMPKIN").asQuantity(9)},
            new String[]{"FARMING,2"}),
    FARMING_ACCESSORIES8(Shops.FARMING_ACCESSORIES, 1, MMOItems.plugin.getItem("ACCESSORY", "DEEP_IMPACTOR4"),
            0, new ItemStack[]{MMOItems.plugin.getItem("ACCESSORY", "DEEP_IMPACTOR3").asQuantity(1), MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_PUMPKIN").asQuantity(32)},
            new String[]{"FARMING,5"}),
    FARMING_ACCESSORIES9(Shops.FARMING_ACCESSORIES, 1, MMOItems.plugin.getItem("ACCESSORY", "DEEP_IMPACTOR5"),
            0, new ItemStack[]{MMOItems.plugin.getItem("ACCESSORY", "DEEP_IMPACTOR4").asQuantity(1), MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_PUMPKIN").asQuantity(64)},
            new String[]{"FARMING,7"}),
    //    Move to Estate since it's Melons
    FARMING_ARMOR1(Shops.FARMING_ARMOR, 1, MMOItems.plugin.getItem("ARMOR", "MELON_HAT3"),
            0, new ItemStack[]{MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_MELON").asQuantity(5)},
            new String[]{"FARMING,2"}),
    FARMING_ARMOR2(Shops.FARMING_ARMOR, 1, MMOItems.plugin.getItem("ARMOR", "MELON_HAT4"),
            0, new ItemStack[]{MMOItems.plugin.getItem("ARMOR", "MELON_HAT3").asQuantity(1), MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_MELON").asQuantity(50)},
            new String[]{"FARMING,4"}),
    FARMING_ARMOR3(Shops.FARMING_ARMOR, 1, MMOItems.plugin.getItem("ARMOR", "MELON_HAT5"),
            0, new ItemStack[]{MMOItems.plugin.getItem("ARMOR", "MELON_HAT4").asQuantity(1), MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_MELON").asQuantity(100)},
            new String[]{"FARMING,5"}),

    FARMING_TOOLS1(Shops.FARMING_TOOLS, 1, MMOItems.plugin.getItem("TOOL", "UNLIMITED_WATER"),
            0, new ItemStack[]{MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_SUGAR_CANE").asQuantity(64), MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_IRON").asQuantity(3)},
            new String[]{"FARMING,5"}),

    FARMING_WEAPONS1(Shops.FARMING_WEAPONS, 1, MMOItems.plugin.getItem("STAFF", "SEED_STAFF3"),
            0, new ItemStack[]{MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_SEED").asQuantity(16), MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_WHEAT").asQuantity(8)},
            new String[]{"FARMING,4"}),
    FARMING_WEAPONS2(Shops.FARMING_WEAPONS, 1, MMOItems.plugin.getItem("STAFF", "WHEAT_STAFF3"),
            0, new ItemStack[]{MMOItems.plugin.getItem("STAFF", "SEED_STAFF3").asQuantity(1), MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_SEED").asQuantity(16), MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_WHEAT").asQuantity(32)},
            new String[]{"FARMING,6"}),

    //  FORAGING
    FORAGING_ARMOR1(Shops.FORAGING_ARMOR, 1, MMOItems.plugin.getItem("ARMOR", "LOG_HELMET4"),
            0, new ItemStack[]{MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_OAK_LOG").asQuantity(32), MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_BIRCH_LOG").asQuantity(32), MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_DARK_OAK_LOG").asQuantity(32), MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_JUNGLE_LOG").asQuantity(32), MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_ACACIA_LOG").asQuantity(32), MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_SPRUCE_LOG").asQuantity(32)},
            new String[]{"FORAGING,5"}),
    FORAGING_ARMOR2(Shops.FORAGING_ARMOR, 1, MMOItems.plugin.getItem("ARMOR", "LOG_CHESTPLATE4"),
            0, new ItemStack[]{MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_OAK_LOG").asQuantity(64), MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_BIRCH_LOG").asQuantity(64), MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_DARK_OAK_LOG").asQuantity(64), MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_JUNGLE_LOG").asQuantity(64), MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_ACACIA_LOG").asQuantity(64), MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_SPRUCE_LOG").asQuantity(64)},
            new String[]{"FORAGING,5"}),
    FORAGING_ARMOR3(Shops.FORAGING_ARMOR, 1, MMOItems.plugin.getItem("ARMOR", "LOG_LEGGINGS4"),
            0, new ItemStack[]{MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_OAK_LOG").asQuantity(48), MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_BIRCH_LOG").asQuantity(48), MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_DARK_OAK_LOG").asQuantity(48), MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_JUNGLE_LOG").asQuantity(48), MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_ACACIA_LOG").asQuantity(48), MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_SPRUCE_LOG").asQuantity(48)},
            new String[]{"FORAGING,5"}),
    FORAGING_ARMOR4(Shops.FORAGING_ARMOR, 1, MMOItems.plugin.getItem("ARMOR", "LOG_BOOTS4"),
            0, new ItemStack[]{MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_OAK_LOG").asQuantity(16), MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_BIRCH_LOG").asQuantity(16), MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_DARK_OAK_LOG").asQuantity(16), MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_JUNGLE_LOG").asQuantity(16), MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_ACACIA_LOG").asQuantity(16), MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_SPRUCE_LOG").asQuantity(16)},
            new String[]{"FORAGING,5"}),

    FORAGING_CATALYSTS1(Shops.FORAGING_CATALYSTS, 1, MMOItems.plugin.getItem("CATALYST", "WOOD_CATALYST4"),
            0, new ItemStack[]{MMOItems.plugin.getItem("QUEST", "DRYAD_BARK5").asQuantity(1), MMOItems.plugin.getItem("CATALYST", "WOOD_CATALYST3").asQuantity(1), MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_OAK_LOG").asQuantity(16), MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_BIRCH_LOG").asQuantity(16), MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_DARK_OAK_LOG").asQuantity(16), MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_JUNGLE_LOG").asQuantity(16), MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_ACACIA_LOG").asQuantity(16), MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_SPRUCE_LOG").asQuantity(16)},
            new String[]{"FORAGING,9"}),

    FORAGING_CONSUMABLES1(Shops.FORAGING_CONSUMABLES, 1, MMOItems.plugin.getItem("CONSUMABLE", "DRYAD_SUMMONER2"),
            0, new ItemStack[]{new ItemStack(Material.OAK_LOG).asQuantity(64),new ItemStack(Material.SPRUCE_LOG).asQuantity(64),new ItemStack(Material.DARK_OAK_LOG).asQuantity(64),new ItemStack(Material.BIRCH_LOG).asQuantity(64),new ItemStack(Material.ACACIA_LOG).asQuantity(64),new ItemStack(Material.JUNGLE_LOG).asQuantity(64),},
            new String[]{"FORAGING,5"}),

    FORAGING_ACCESSORIES1(Shops.FORAGING_ACCESSORIES, 1, MMOItems.plugin.getItem("ACCESSORY", "OAK_BARK"),
            0, new ItemStack[]{MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_OAK_LOG").asQuantity(9)},
            new String[]{"FORAGING,3"}),
    FORAGING_ACCESSORIES2(Shops.FORAGING_ACCESSORIES, 1, MMOItems.plugin.getItem("ACCESSORY", "OAK_BARK2"),
            0, new ItemStack[]{MMOItems.plugin.getItem("ACCESSORY", "OAK_BARK").asQuantity(1), MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_OAK_LOG").asQuantity(18)},
            new String[]{"FORAGING,5"}),
    FORAGING_ACCESSORIES3(Shops.FORAGING_ACCESSORIES, 1, MMOItems.plugin.getItem("ACCESSORY", "OAK_BARK3"),
            0, new ItemStack[]{MMOItems.plugin.getItem("ACCESSORY", "OAK_BARK2").asQuantity(1), MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_OAK_LOG").asQuantity(36)},
            new String[]{"FORAGING,7"}),
    FORAGING_ACCESSORIES4(Shops.FORAGING_ACCESSORIES, 1, MMOItems.plugin.getItem("ACCESSORY", "OAK_BARK4"),
            0, new ItemStack[]{MMOItems.plugin.getItem("ACCESSORY", "OAK_BARK3").asQuantity(1), MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_OAK_LOG").asQuantity(64)},
            new String[]{"FORAGING,8"}),
    FORAGING_ACCESSORIES5(Shops.FORAGING_ACCESSORIES, 1, MMOItems.plugin.getItem("ACCESSORY", "OAK_BARK5"),
            0, new ItemStack[]{MMOItems.plugin.getItem("ACCESSORY", "OAK_BARK4").asQuantity(1), MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_OAK_LOG").asQuantity(128)},
            new String[]{"FORAGING,10"}),
    FORAGING_ACCESSORIES6(Shops.FORAGING_ACCESSORIES, 1, MMOItems.plugin.getItem("ACCESSORY", "RUNNING_SPRITE3"),
            0, new ItemStack[]{MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_BIRCH_LOG").asQuantity(18)},
            new String[]{"FORAGING,4"}),
    FORAGING_ACCESSORIES7(Shops.FORAGING_ACCESSORIES, 1, MMOItems.plugin.getItem("ACCESSORY", "RUNNING_SPRITE4"),
            0, new ItemStack[]{MMOItems.plugin.getItem("ACCESSORY", "RUNNING_SPRITE3").asQuantity(1), MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_BIRCH_LOG").asQuantity(64)},
            new String[]{"FORAGING,7"}),
    FORAGING_ACCESSORIES8(Shops.FORAGING_ACCESSORIES, 1, MMOItems.plugin.getItem("ACCESSORY", "RUNNING_SPRITE5"),
            0, new ItemStack[]{MMOItems.plugin.getItem("ACCESSORY", "RUNNING_SPRITE4").asQuantity(1), MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_BIRCH_LOG").asQuantity(128)},
            new String[]{"FORAGING,8"}),
    FORAGING_ACCESSORIES9(Shops.FORAGING_ACCESSORIES, 1, MMOItems.plugin.getItem("ACCESSORY", "SPEEDY_JUNGLE_WORKER2"),
            0, new ItemStack[]{MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_JUNGLE_LOG").asQuantity(18)},
            new String[]{"FORAGING,2"}),
    FORAGING_ACCESSORIES10(Shops.FORAGING_ACCESSORIES, 1, MMOItems.plugin.getItem("ACCESSORY", "SPEEDY_JUNGLE_WORKER3"),
            0, new ItemStack[]{MMOItems.plugin.getItem("ACCESSORY", "SPEEDY_JUNGLE_WORKER2").asQuantity(1), MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_JUNGLE_LOG").asQuantity(36)},
            new String[]{"FORAGING,4"}),
    FORAGING_ACCESSORIES11(Shops.FORAGING_ACCESSORIES, 1, MMOItems.plugin.getItem("ACCESSORY", "FAST_HITTER3"),
            0, new ItemStack[]{MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_SPRUCE_LOG").asQuantity(18)},
            new String[]{"FORAGING,4"}),
    FORAGING_ACCESSORIES12(Shops.FORAGING_ACCESSORIES, 1, MMOItems.plugin.getItem("ACCESSORY", "FAST_HITTER4"),
            0, new ItemStack[]{MMOItems.plugin.getItem("ACCESSORY", "FAST_HITTER3").asQuantity(1), MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_SPRUCE_LOG").asQuantity(64)},
            new String[]{"FORAGING,6"}),
    FORAGING_ACCESSORIES13(Shops.FORAGING_ACCESSORIES, 1, MMOItems.plugin.getItem("ACCESSORY", "FAST_HITTER5"),
            0, new ItemStack[]{MMOItems.plugin.getItem("ACCESSORY", "FAST_HITTER4").asQuantity(1), MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_SPRUCE_LOG").asQuantity(128)},
            new String[]{"FORAGING,8"}),
    FORAGING_ACCESSORIES14(Shops.FORAGING_ACCESSORIES, 1, MMOItems.plugin.getItem("ACCESSORY", "ACACIA_HEART3"),
            0, new ItemStack[]{MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_ACACIA_LOG").asQuantity(18)},
            new String[]{"FORAGING,5"}),
    FORAGING_ACCESSORIES15(Shops.FORAGING_ACCESSORIES, 1, MMOItems.plugin.getItem("ACCESSORY", "ACACIA_HEART4"),
            0, new ItemStack[]{MMOItems.plugin.getItem("ACCESSORY", "ACACIA_HEART3").asQuantity(1), MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_ACACIA_LOG").asQuantity(64)},
            new String[]{"FORAGING,7"}),
    FORAGING_ACCESSORIES16(Shops.FORAGING_ACCESSORIES, 1, MMOItems.plugin.getItem("ACCESSORY", "ACACIA_HEART5"),
            0, new ItemStack[]{MMOItems.plugin.getItem("ACCESSORY", "ACACIA_HEART4").asQuantity(1), MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_ACACIA_LOG").asQuantity(128)},
            new String[]{"FORAGING,9"}),

    FORAGING_WEAPONS0(Shops.FORAGING_WEAPONS, 1, MMOItems.plugin.getItem("DAGGER", "MACHETE3"),
            0, new ItemStack[]{MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_JUNGLE_LOG").asQuantity(320)},
            new String[]{"FORAGING,4"}),
    FORAGING_WEAPONS2(Shops.FORAGING_WEAPONS, 1, MMOItems.plugin.getItem("AXE", "CARVING_AXE5"),
            0, new ItemStack[]{MMOItems.plugin.getItem("QUEST", "DRYAD_BARK5").asQuantity(1), MMOItems.plugin.getItem("AXE", "CARVING_AXE4").asQuantity(1)},
            new String[]{"FORAGING,4"}),
    FORAGING_WEAPONS1(Shops.FORAGING_WEAPONS, 1, MMOItems.plugin.getItem("WAND", "DRYAD_STRONG_VINE5"),
            0, new ItemStack[]{MMOItems.plugin.getItem("QUEST", "DRYAD_BARK5").asQuantity(1), MMOItems.plugin.getItem("WAND", "DRYAD_WEAK_VINE4").asQuantity(1)},
            new String[]{"FORAGING,5"}),
    FORAGING_WEAPONS3(Shops.FORAGING_WEAPONS, 1, MMOItems.plugin.getItem("WAND", "ROOTING3"),
            0, new ItemStack[]{MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_OAK_LOG").asQuantity(320), MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_BIRCH_LOG").asQuantity(320), MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_DARK_OAK_LOG").asQuantity(320), MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_JUNGLE_LOG").asQuantity(320), MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_ACACIA_LOG").asQuantity(320), MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_SPRUCE_LOG").asQuantity(320)},
            new String[]{"FORAGING,6"}),


    BLAST_FURNACE1(Shops.BLAST_FURNACE, 64, new ItemStack(Material.COAL),
            0, new ItemStack[]{MMOItems.plugin.getItem("MATERIAL", "COAL_SHARD").asQuantity(9)}, null),
    BLAST_FURNACE2(Shops.BLAST_FURNACE, 64, new ItemStack(Material.IRON_INGOT),
            0, new ItemStack[]{MMOItems.plugin.getItem("MATERIAL", "IRON_SHARD").asQuantity(9)}, null),
    BLAST_FURNACE3(Shops.BLAST_FURNACE, 64, new ItemStack(Material.GOLD_INGOT),
            0, new ItemStack[]{MMOItems.plugin.getItem("MATERIAL", "GOLD_SHARD").asQuantity(9)}, null),
    BLAST_FURNACE4(Shops.BLAST_FURNACE, 64, new ItemStack(Material.DIAMOND),
            0, new ItemStack[]{MMOItems.plugin.getItem("MATERIAL", "DIAMOND_SHARD").asQuantity(9)}, null),
    BLAST_FURNACE5(Shops.BLAST_FURNACE, 64, new ItemStack(Material.EMERALD),
            0, new ItemStack[]{MMOItems.plugin.getItem("MATERIAL", "EMERALD_SHARD").asQuantity(9)}, null),

    ;


    private static final Map<Shops, List<ItemList>> SHOPS_LIST_MAP = new EnumMap<>(Shops.class);

    public static List<ItemList> getShop(Shops shops) {
        return SHOPS_LIST_MAP.computeIfAbsent(shops, key -> Arrays.stream(values())
                .filter(shop -> shop.getShop().equals(key))
                .collect(Collectors.toList()));
    }


    private Shops shop;
    private int maxPurchaseAmount;
    private ItemStack itemStack;
    private int coinPrice;
    private ItemStack[] itemPrice;
    private String[] professionLevel;

    ItemList(Shops shop, int maxPurchaseAmount, ItemStack itemStack, int price, ItemStack[] itemPrice, String[] professionLevel) {
        this.shop = shop;
        this.maxPurchaseAmount = maxPurchaseAmount;
        this.itemStack = itemStack;
        this.coinPrice = price;
        this.itemPrice = itemPrice;
        this.professionLevel = professionLevel;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public int getCoinPrice() {
        return coinPrice;
    }

    public Material getMaterial() {
        return itemStack.getType();
    }

    public Shops getShop() {
        return shop;
    }

    public int getMaxPurchaseAmount() {
        return maxPurchaseAmount;
    }

    public ItemStack[] getItemPrice() {
        return itemPrice;
    }

    public String[] getProfessionLevel() {
        return professionLevel;
    }
}
