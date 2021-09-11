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
    ITEM1(Shops.FARMER, 64, new ItemStack(Material.WHEAT_SEEDS), 2, null),
    ITEM2(Shops.FARMER, 64, new ItemStack(Material.WHEAT), 2, null),
    ITEM3(Shops.FARMER, 64, new ItemStack(Material.CARROT), 4, null),
    ITEM4(Shops.FARMER, 64, new ItemStack(Material.POTATO), 4, null),
    ITEM5(Shops.FARMER, 64, new ItemStack(Material.BEEF), 5, null),
    ITEM6(Shops.FARMER, 64, new ItemStack(Material.LEATHER), 10, null),
    ITEM7(Shops.FARMER, 64, new ItemStack(Material.PORKCHOP), 5, null),
    ITEM8(Shops.FARMER, 64, new ItemStack(Material.MUTTON), 6, null),
    ITEM9(Shops.FARMER, 64, new ItemStack(Material.WHITE_WOOL), 10, null),
    ITEM10(Shops.FARMER, 64, new ItemStack(Material.CHICKEN), 8, null),
    ITEM11(Shops.FARMER, 64, new ItemStack(Material.FEATHER), 5, null),
    ITEM12(Shops.FARMER, 64, new ItemStack(Material.RABBIT), 6, null),
    ITEM13(Shops.FARMER, 64, new ItemStack(Material.RABBIT_HIDE), 10, null),
    ITEM14(Shops.FARMER, 64, new ItemStack(Material.RABBIT_FOOT), 10, null),
    ITEM15(Shops.FARMER, 1, MMOItems.plugin.getItem("TOOL", "WOODEN_HOE"), 10, null),

    MercenaryITEM1(Shops.MERCENARY, 64, new ItemStack(Material.BONE), 20, null),
    MercenaryITEM2(Shops.MERCENARY, 64, new ItemStack(Material.SPIDER_EYE), 35, null),
    MercenaryITEM3(Shops.MERCENARY, 64, new ItemStack(Material.STRING), 30, null),
    MercenaryITEM4(Shops.MERCENARY, 64, new ItemStack(Material.GUNPOWDER), 50, null),
    MercenaryITEM5(Shops.MERCENARY, 64, new ItemStack(Material.ROTTEN_FLESH), 15, null),
    MercenaryITEM6(Shops.MERCENARY, 64, new ItemStack(Material.ENDER_PEARL), 30, null),
    MercenaryITEM7(Shops.MERCENARY, 64, new ItemStack(Material.MAGMA_CREAM), 20, null),
    MercenaryITEM8(Shops.MERCENARY, 64, new ItemStack(Material.BLAZE_ROD), 25, null),
    MercenaryITEM9(Shops.MERCENARY, 64, new ItemStack(Material.SLIME_BALL), 60, null),
    MercenaryITEM10(Shops.MERCENARY, 64, new ItemStack(Material.ARROW), 20, null),
    MercenaryITEM11(Shops.MERCENARY, 1, MMOItems.plugin.getItem("SWORD", "BASIC_SWORD"), 250, null),

    LumberjackITEM1(Shops.LUMBERJACK, 64, new ItemStack(Material.OAK_LOG), 8, null),
    LumberjackITEM2(Shops.LUMBERJACK, 64, new ItemStack(Material.SPRUCE_LOG), 16, null),
    LumberjackITEM3(Shops.LUMBERJACK, 64, new ItemStack(Material.DARK_OAK_LOG), 60, null),
    LumberjackITEM4(Shops.LUMBERJACK, 64, new ItemStack(Material.BIRCH_LOG), 20, null),
    LumberjackITEM5(Shops.LUMBERJACK, 64, new ItemStack(Material.ACACIA_LOG), 24, null),
    LumberjackITEM6(Shops.LUMBERJACK, 64, new ItemStack(Material.JUNGLE_LOG), 26, null),
    LumberjackITEM7(Shops.LUMBERJACK, 64, new ItemStack(Material.OAK_LEAVES), 26, null),
    LumberjackITEM8(Shops.LUMBERJACK, 64, new ItemStack(Material.SPRUCE_LEAVES), 50, null),
    LumberjackITEM9(Shops.LUMBERJACK, 64, new ItemStack(Material.DARK_OAK_LEAVES), 120, null),
    LumberjackITEM10(Shops.LUMBERJACK, 64, new ItemStack(Material.BIRCH_LEAVES), 30, null),
    LumberjackITEM11(Shops.LUMBERJACK, 64, new ItemStack(Material.ACACIA_LEAVES), 30, null),
    LumberjackITEM12(Shops.LUMBERJACK, 64, new ItemStack(Material.JUNGLE_LEAVES), 30, null),
    LumberjackITEM13(Shops.LUMBERJACK, 64, new ItemStack(Material.COCOA_BEANS), 10, null),
    LumberjackITEM14(Shops.LUMBERJACK, 64, new ItemStack(Material.HONEYCOMB), 4, null),
    LumberjackITEM15(Shops.LUMBERJACK, 64, new ItemStack(Material.RABBIT), 6, null),
    LumberjackITEM16(Shops.LUMBERJACK, 64, new ItemStack(Material.RABBIT_HIDE), 10, null),
    LumberjackITEM17(Shops.LUMBERJACK, 64, new ItemStack(Material.RABBIT_FOOT), 10, null),
    LumberjackITEM18(Shops.LUMBERJACK, 1, MMOItems.plugin.getItem("TOOL", "WOODEN_AXE"), 100, null),
    LumberjackITEM19(Shops.LUMBERJACK, 1, MMOItems.plugin.getItem("TOOL", "STONE_AXE"), 250, null),

    MiningITEM1(Shops.MINING, 64, new ItemStack(Material.STONE), 6, null),
    MiningITEM2(Shops.MINING, 64, new ItemStack(Material.COBBLESTONE), 10, null),
    MiningITEM3(Shops.MINING, 64, new ItemStack(Material.COAL), 6, null),
    MiningITEM4(Shops.MINING, 64, new ItemStack(Material.IRON_INGOT), 8, null),
    MiningITEM5(Shops.MINING, 64, new ItemStack(Material.GOLD_INGOT), 10, null),
    MiningITEM6(Shops.MINING, 64, new ItemStack(Material.REDSTONE), 6, null),
    MiningITEM7(Shops.MINING, 64, new ItemStack(Material.LAPIS_LAZULI), 6, null),
    MiningITEM8(Shops.MINING, 64, new ItemStack(Material.DIAMOND), 12, null),
    MiningITEM9(Shops.MINING, 64, new ItemStack(Material.EMERALD), 7, null),
    MiningITEM10(Shops.MINING, 64, new ItemStack(Material.SPIDER_EYE), 35, null),
    MiningITEM11(Shops.MINING, 64, new ItemStack(Material.STRING), 30, null),
    MiningITEM12(Shops.MINING, 64, new ItemStack(Material.BONE), 20, null),
    MiningITEM13(Shops.MINING, 64, new ItemStack(Material.GUNPOWDER), 50, null),
    MiningITEM14(Shops.MINING, 1, MMOItems.plugin.getItem("TOOL", "WOODEN_PICKAXE"), 100, null),
    MiningITEM15(Shops.MINING, 1, MMOItems.plugin.getItem("TOOL", "STONE_PICKAXE"), 250, null),

    HellITEM1(Shops.DEMON, 64, new ItemStack(Material.NETHERRACK), 10, null),
    HellITEM2(Shops.DEMON, 64, new ItemStack(Material.NETHER_BRICKS), 21, null),
    HellITEM3(Shops.DEMON, 64, new ItemStack(Material.SOUL_SAND), 15, null),
    HellITEM4(Shops.DEMON, 64, new ItemStack(Material.GLOWSTONE), 15, null),
    HellITEM5(Shops.DEMON, 64, new ItemStack(Material.NETHER_WART), 6, null),
    HellITEM6(Shops.DEMON, 64, new ItemStack(Material.GLOWSTONE_DUST), 10, null),
    HellITEM7(Shops.DEMON, 64, new ItemStack(Material.QUARTZ), 8, null),
    HellITEM8(Shops.DEMON, 64, new ItemStack(Material.OBSIDIAN), 14, null),
    HellITEM9(Shops.DEMON, 64, new ItemStack(Material.ROTTEN_FLESH), 14, null),
    HellITEM10(Shops.DEMON, 64, new ItemStack(Material.BLAZE_ROD), 25, null),
    HellITEM11(Shops.DEMON, 64, new ItemStack(Material.MAGMA_CREAM), 20, null),
    HellITEM12(Shops.DEMON, 64, new ItemStack(Material.BONE), 20, null),

    LandscaperITEM1(Shops.LANDSCAPER, 64, new ItemStack(Material.DIRT), 4, null),
    LandscaperITEM2(Shops.LANDSCAPER, 64, new ItemStack(Material.PODZOL), 6, null),
    LandscaperITEM3(Shops.LANDSCAPER, 64, new ItemStack(Material.MYCELIUM), 18, null),
    LandscaperITEM4(Shops.LANDSCAPER, 64, new ItemStack(Material.SAND), 15, null),
    LandscaperITEM5(Shops.LANDSCAPER, 64, new ItemStack(Material.RED_SAND), 18, null),
    LandscaperITEM6(Shops.LANDSCAPER, 64, new ItemStack(Material.SANDSTONE), 19, null),
    LandscaperITEM7(Shops.LANDSCAPER, 64, new ItemStack(Material.RED_SANDSTONE), 20, null),
    LandscaperITEM8(Shops.LANDSCAPER, 64, new ItemStack(Material.GLASS), 50, null),
    LandscaperITEM9(Shops.LANDSCAPER, 64, new ItemStack(Material.GRAVEL), 57, null),
    LandscaperITEM10(Shops.LANDSCAPER, 64, new ItemStack(Material.DIORITE), 21, null),
    LandscaperITEM11(Shops.LANDSCAPER, 64, new ItemStack(Material.TERRACOTTA), 18, null),
    LandscaperITEM12(Shops.LANDSCAPER, 64, new ItemStack(Material.SEA_LANTERN), 20, null),
    LandscaperITEM13(Shops.LANDSCAPER, 64, new ItemStack(Material.PRISMARINE), 26, null),
    LandscaperITEM14(Shops.LANDSCAPER, 64, new ItemStack(Material.PRISMARINE_BRICKS), 28, null),
    LandscaperITEM15(Shops.LANDSCAPER, 64, new ItemStack(Material.DARK_PRISMARINE), 28, null),
    LandscaperITEM16(Shops.LANDSCAPER, 64, new ItemStack(Material.WHITE_WOOL), 15, null),

    EstateITEM1(Shops.ESTATE, 64, new ItemStack(Material.PUMPKIN), 6, null),
    EstateITEM2(Shops.ESTATE, 64, new ItemStack(Material.MELON), 10, null),
    EstateITEM3(Shops.ESTATE, 64, new ItemStack(Material.MELON_SLICE), 2, null),
    EstateITEM4(Shops.ESTATE, 64, new ItemStack(Material.SUGAR_CANE), 10, null),
    EstateITEM5(Shops.ESTATE, 64, new ItemStack(Material.CACTUS), 24, null),
    EstateITEM6(Shops.ESTATE, 64, new ItemStack(Material.BEETROOT), 14, null),
    EstateITEM7(Shops.ESTATE, 64, new ItemStack(Material.RED_MUSHROOM), 6, null),
    EstateITEM8(Shops.ESTATE, 64, new ItemStack(Material.BROWN_MUSHROOM), 6, null),

    JoyITEM1(Shops.JOY, 1, MMOItems.plugin.getItem("CONSUMABLE", "JOY_SOUP"), 100, null),
    JoyITEM2(Shops.JOY, 1, MMOItems.plugin.getItem("CONSUMABLE", "JOY_SOUP3"), 250, null),
    JoyITEM3(Shops.JOY, 1, MMOItems.plugin.getItem("CONSUMABLE", "JOY_SOUP4"), 500, null),
    JoyITEM4(Shops.JOY, 1, MMOItems.plugin.getItem("BOW", "JOYS_BOW2"), 5_000, null),

    CatLadyITEM1(Shops.CAT_LADY, 64, new ItemStack(Material.LEAD), 25, null),

    EnchanterITEM1(Shops.ENCHANTER, 1, MMOItems.plugin.getItem("ENCHANTMENT", "ENCHANTING_BOOK"), 100, null),
    EnchanterITEM2(Shops.ENCHANTER, 1, MMOItems.plugin.getItem("ENCHANTMENT", "ENCHANTING_BOOK2"), 250, new ItemStack[]{MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_PAPER").asQuantity(8)}),
    EnchanterITEM3(Shops.ENCHANTER, 1, MMOItems.plugin.getItem("ENCHANTMENT", "ENCHANTING_BOOK3"), 500, new ItemStack[]{MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_PAPER").asQuantity(16)}),
    EnchanterITEM4(Shops.ENCHANTER, 1, MMOItems.plugin.getItem("ENCHANTMENT", "ENCHANTING_BOOK4"), 1_000, new ItemStack[]{MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_PAPER").asQuantity(32)}),
    EnchanterITEM5(Shops.ENCHANTER, 1, MMOItems.plugin.getItem("ENCHANTMENT", "ENCHANTING_BOOK5"), 1_500, new ItemStack[]{MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_PAPER").asQuantity(64)}),
    EnchanterITEM7(Shops.ENCHANTER, 1, MMOItems.plugin.getItem("CONSUMABLE", "ENCHANTED_BOX"), 150, null),
    EnchanterITEM8(Shops.ENCHANTER, 1, MMOItems.plugin.getItem("CONSUMABLE", "ENCHANTED_BOX2"), 250, null),
    EnchanterITEM9(Shops.ENCHANTER, 1, MMOItems.plugin.getItem("CONSUMABLE", "ENCHANTED_BOX3"), 350, null),
    EnchanterITEMF1(Shops.ENCHANTER, 0, null, 0, null),
    EnchanterITEMF2(Shops.ENCHANTER, 0, null, 0, null),
    EnchanterITEM10(Shops.ENCHANTER, 1, MMOItems.plugin.getItem("CONSUMABLE", "XP_BOTTLE"), 15, new ItemStack[]{MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_LAPIS").asQuantity(1)}),
    EnchanterITEM11(Shops.ENCHANTER, 1, MMOItems.plugin.getItem("CONSUMABLE", "XP_BOTTLE2"), 100, new ItemStack[]{MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_LAPIS").asQuantity(8)}),
    EnchanterITEM13(Shops.ENCHANTER, 1, MMOItems.plugin.getItem("CONSUMABLE", "XP_BOTTLE3"), 500, new ItemStack[]{MMOItems.plugin.getItem("MATERIAL", "ENCHANTED_LAPIS").asQuantity(16)}),

    WANDERING_TRADER1(Shops.WANDERING_TRADER, 64, new ItemStack(Material.COAL), 0, new ItemStack[]{MMOItems.plugin.getItem("MATERIAL", "COAL_SHARD").asQuantity(8)}),
    WANDERING_TRADER2(Shops.WANDERING_TRADER, 64, new ItemStack(Material.IRON_INGOT), 0, new ItemStack[]{MMOItems.plugin.getItem("MATERIAL", "IRON_SHARD").asQuantity(8)}),
    WANDERING_TRADER3(Shops.WANDERING_TRADER, 64, new ItemStack(Material.GOLD_INGOT), 0, new ItemStack[]{MMOItems.plugin.getItem("MATERIAL", "GOLD_SHARD").asQuantity(8)}),
    WANDERING_TRADER4(Shops.WANDERING_TRADER, 64, new ItemStack(Material.DIAMOND), 0, new ItemStack[]{MMOItems.plugin.getItem("MATERIAL", "DIAMOND_SHARD").asQuantity(8)}),
    WANDERING_TRADER5(Shops.WANDERING_TRADER, 64, new ItemStack(Material.EMERALD), 0, new ItemStack[]{MMOItems.plugin.getItem("MATERIAL", "EMERALD_SHARD").asQuantity(8)}),

    MINER1(Shops.MINER, 1, MMOItems.plugin.getItem("TOOL", "MINERS_PICKAXE"), 0, new ItemStack[]{MMOItems.plugin.getItem("TOOL", "WOODEN_PICKAXE").asQuantity(1), new ItemStack(Material.STONE).asQuantity(64)}),

    BLAST_FURNACE1(Shops.BLAST_FURNACE, 64, new ItemStack(Material.COAL), 0, new ItemStack[]{MMOItems.plugin.getItem("MATERIAL", "COAL_SHARD").asQuantity(9)}),
    BLAST_FURNACE2(Shops.BLAST_FURNACE, 64, new ItemStack(Material.IRON_INGOT), 0, new ItemStack[]{MMOItems.plugin.getItem("MATERIAL", "IRON_SHARD").asQuantity(9)}),
    BLAST_FURNACE3(Shops.BLAST_FURNACE, 64, new ItemStack(Material.GOLD_INGOT), 0, new ItemStack[]{MMOItems.plugin.getItem("MATERIAL", "GOLD_SHARD").asQuantity(9)}),
    BLAST_FURNACE4(Shops.BLAST_FURNACE, 64, new ItemStack(Material.DIAMOND), 0, new ItemStack[]{MMOItems.plugin.getItem("MATERIAL", "DIAMOND_SHARD").asQuantity(9)}),
    BLAST_FURNACE5(Shops.BLAST_FURNACE, 64, new ItemStack(Material.EMERALD), 0, new ItemStack[]{MMOItems.plugin.getItem("MATERIAL", "EMERALD_SHARD").asQuantity(9)}),

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

    ItemList(Shops shop, int maxPurchaseAmount, ItemStack itemStack, int price, ItemStack[] itemPrice) {
        this.shop = shop;
        this.maxPurchaseAmount = maxPurchaseAmount;
        this.itemStack = itemStack;
        this.coinPrice = price;
        this.itemPrice = itemPrice;
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
}
