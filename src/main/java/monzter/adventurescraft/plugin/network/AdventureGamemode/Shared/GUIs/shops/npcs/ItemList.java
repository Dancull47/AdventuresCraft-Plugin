package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.shops.npcs;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public enum ItemList {
    ITEM1(Shops.FARMER, new ItemStack(Material.WHEAT_SEEDS), 2, 0, null, 64, null, null),
    ITEM2(Shops.FARMER, new ItemStack(Material.WHEAT), 2, 0, null, 64, null, null),
    ITEM3(Shops.FARMER, new ItemStack(Material.CARROT), 4, 0, null, 64, null, null),
    ITEM4(Shops.FARMER, new ItemStack(Material.POTATO), 4, 0, null, 64, null, null),
    ITEM5(Shops.FARMER, new ItemStack(Material.BEEF), 5, 0, null, 64, null, null),
    ITEM6(Shops.FARMER, new ItemStack(Material.LEATHER), 10, 0, null, 64, null, null),
    ITEM7(Shops.FARMER, new ItemStack(Material.PORKCHOP), 5, 0, null, 64, null, null),
    ITEM8(Shops.FARMER, new ItemStack(Material.MUTTON), 6, 0, null, 64, null, null),
    ITEM9(Shops.FARMER, new ItemStack(Material.WHITE_WOOL), 10, 0, null, 64, null, null),
    ITEM10(Shops.FARMER, new ItemStack(Material.CHICKEN), 8, 0, null, 64, null, null),
    ITEM11(Shops.FARMER, new ItemStack(Material.FEATHER), 5, 0, null, 64, null, null),
    ITEM12(Shops.FARMER, new ItemStack(Material.RABBIT), 6, 0, null, 64, null, null),
    ITEM13(Shops.FARMER, new ItemStack(Material.RABBIT_HIDE), 10, 0, null, 64, null, null),
    ITEM14(Shops.FARMER, new ItemStack(Material.RABBIT_FOOT), 10, 0, null, 64, null, null),
    ITEM15(Shops.FARMER, null, 10, 0, null, 1, "TOOL", "WOODEN_HOE"),

    MercenaryITEM1(Shops.MERCENARY, new ItemStack(Material.BONE), 20, 0, null, 64, null, null),
    MercenaryITEM2(Shops.MERCENARY, new ItemStack(Material.SPIDER_EYE), 35, 0, null, 64, null, null),
    MercenaryITEM3(Shops.MERCENARY, new ItemStack(Material.STRING), 30, 0, null, 64, null, null),
    MercenaryITEM4(Shops.MERCENARY, new ItemStack(Material.GUNPOWDER), 50, 0, null, 64, null, null),
    MercenaryITEM5(Shops.MERCENARY, new ItemStack(Material.ROTTEN_FLESH), 15, 0, null, 64, null, null),
    MercenaryITEM6(Shops.MERCENARY, new ItemStack(Material.ENDER_PEARL), 30, 0, null, 64, null, null),
    MercenaryITEM7(Shops.MERCENARY, new ItemStack(Material.MAGMA_CREAM), 20, 0, null, 64, null, null),
    MercenaryITEM8(Shops.MERCENARY, new ItemStack(Material.BLAZE_ROD), 25, 0, null, 64, null, null),
    MercenaryITEM9(Shops.MERCENARY, new ItemStack(Material.SLIME_BALL), 60, 0, null, 64, null, null),
    MercenaryITEM10(Shops.MERCENARY, new ItemStack(Material.ARROW), 20, 0, null, 64, null, null),
    MercenaryITEM11(Shops.MERCENARY, null, 250, 0, null, 1, "SWORD", "BASIC_SWORD"),

    LumberjackITEM1(Shops.LUMBERJACK, new ItemStack(Material.OAK_LOG), 8, 0, null, 64, null, null),
    LumberjackITEM2(Shops.LUMBERJACK, new ItemStack(Material.SPRUCE_LOG), 16, 0, null, 64, null, null),
    LumberjackITEM3(Shops.LUMBERJACK, new ItemStack(Material.DARK_OAK_LOG), 60, 0, null, 64, null, null),
    LumberjackITEM4(Shops.LUMBERJACK, new ItemStack(Material.BIRCH_LOG), 20, 0, null, 64, null, null),
    LumberjackITEM5(Shops.LUMBERJACK, new ItemStack(Material.ACACIA_LOG), 24, 0, null, 64, null, null),
    LumberjackITEM6(Shops.LUMBERJACK, new ItemStack(Material.JUNGLE_LOG), 26, 0, null, 64, null, null),
    LumberjackITEM7(Shops.LUMBERJACK, new ItemStack(Material.OAK_LEAVES), 26, 0, null, 64, null, null),
    LumberjackITEM8(Shops.LUMBERJACK, new ItemStack(Material.SPRUCE_LEAVES), 50, 0, null, 64, null, null),
    LumberjackITEM9(Shops.LUMBERJACK, new ItemStack(Material.DARK_OAK_LEAVES), 120, 0, null, 64, null, null),
    LumberjackITEM10(Shops.LUMBERJACK, new ItemStack(Material.BIRCH_LEAVES), 30, 0, null, 64, null, null),
    LumberjackITEM11(Shops.LUMBERJACK, new ItemStack(Material.ACACIA_LEAVES), 30, 0, null, 64, null, null),
    LumberjackITEM12(Shops.LUMBERJACK, new ItemStack(Material.JUNGLE_LEAVES), 30, 0, null, 64, null, null),
    LumberjackITEM13(Shops.LUMBERJACK, new ItemStack(Material.COCOA_BEANS), 10, 0, null, 64, null, null),
    LumberjackITEM14(Shops.LUMBERJACK, new ItemStack(Material.HONEYCOMB), 4, 0, null, 64, null, null),
    LumberjackITEM15(Shops.LUMBERJACK, new ItemStack(Material.RABBIT), 6, 0, null, 64, null, null),
    LumberjackITEM16(Shops.LUMBERJACK, new ItemStack(Material.RABBIT_HIDE), 10, 0, null, 64, null, null),
    LumberjackITEM17(Shops.LUMBERJACK, new ItemStack(Material.RABBIT_FOOT), 10, 0, null, 64, null, null),
    LumberjackITEM18(Shops.LUMBERJACK, null, 100, 0, null, 1, "TOOL", "WOODEN_AXE"),
    LumberjackITEM19(Shops.LUMBERJACK, null, 250, 0, null, 1, "TOOL", "STONE_AXE"),

    MiningITEM1(Shops.MINING, new ItemStack(Material.STONE), 6, 0, null, 64, null, null),
    MiningITEM2(Shops.MINING, new ItemStack(Material.COBBLESTONE), 10, 0, null, 64, null, null),
    MiningITEM3(Shops.MINING, new ItemStack(Material.COAL), 6, 0, null, 64, null, null),
    MiningITEM4(Shops.MINING, new ItemStack(Material.IRON_INGOT), 8, 0, null, 64, null, null),
    MiningITEM5(Shops.MINING, new ItemStack(Material.GOLD_INGOT), 10, 0, null, 64, null, null),
    MiningITEM6(Shops.MINING, new ItemStack(Material.REDSTONE), 6, 0, null, 64, null, null),
    MiningITEM7(Shops.MINING, new ItemStack(Material.LAPIS_LAZULI), 6, 0, null, 64, null, null),
    MiningITEM8(Shops.MINING, new ItemStack(Material.DIAMOND), 12, 0, null, 64, null, null),
    MiningITEM9(Shops.MINING, new ItemStack(Material.EMERALD), 7, 0, null, 64, null, null),
    MiningITEM10(Shops.MINING, new ItemStack(Material.SPIDER_EYE), 35, 0, null, 64, null, null),
    MiningITEM11(Shops.MINING, new ItemStack(Material.STRING), 30, 0, null, 64, null, null),
    MiningITEM12(Shops.MINING, new ItemStack(Material.BONE), 20, 0, null, 64, null, null),
    MiningITEM13(Shops.MINING, new ItemStack(Material.GUNPOWDER), 50, 0, null, 64, null, null),
    MiningITEM14(Shops.MINING, null, 100, 0, null, 1, "TOOL", "WOODEN_PICKAXE"),
    MiningITEM15(Shops.MINING, null, 250, 0, null, 1, "TOOL", "STONE_PICKAXE"),

    HellITEM1(Shops.DEMON, new ItemStack(Material.NETHERRACK), 10, 0, null, 64, null, null),
    HellITEM2(Shops.DEMON, new ItemStack(Material.NETHER_BRICKS), 21, 0, null, 64, null, null),
    HellITEM3(Shops.DEMON, new ItemStack(Material.SOUL_SAND), 15, 0, null, 64, null, null),
    HellITEM4(Shops.DEMON, new ItemStack(Material.GLOWSTONE), 15, 0, null, 64, null, null),
    HellITEM5(Shops.DEMON, new ItemStack(Material.NETHER_WART), 6, 0, null, 64, null, null),
    HellITEM6(Shops.DEMON, new ItemStack(Material.GLOWSTONE_DUST), 10, 0, null, 64, null, null),
    HellITEM7(Shops.DEMON, new ItemStack(Material.QUARTZ), 8, 0, null, 64, null, null),
    HellITEM8(Shops.DEMON, new ItemStack(Material.OBSIDIAN), 14, 0, null, 64, null, null),
    HellITEM9(Shops.DEMON, new ItemStack(Material.ROTTEN_FLESH), 14, 0, null, 64, null, null),
    HellITEM10(Shops.DEMON, new ItemStack(Material.BLAZE_ROD), 25, 0, null, 64, null, null),
    HellITEM11(Shops.DEMON, new ItemStack(Material.MAGMA_CREAM), 20, 0, null, 64, null, null),
    HellITEM12(Shops.DEMON, new ItemStack(Material.BONE), 20, 0, null, 64, null, null),

    LandscaperITEM1(Shops.LANDSCAPER, new ItemStack(Material.DIRT), 4, 0, null, 64, null, null),
    LandscaperITEM2(Shops.LANDSCAPER, new ItemStack(Material.PODZOL), 6, 0, null, 64, null, null),
    LandscaperITEM3(Shops.LANDSCAPER, new ItemStack(Material.MYCELIUM), 18, 0, null, 64, null, null),
    LandscaperITEM4(Shops.LANDSCAPER, new ItemStack(Material.SAND), 15, 0, null, 64, null, null),
    LandscaperITEM5(Shops.LANDSCAPER, new ItemStack(Material.RED_SAND), 18, 0, null, 64, null, null),
    LandscaperITEM6(Shops.LANDSCAPER, new ItemStack(Material.SANDSTONE), 19, 0, null, 64, null, null),
    LandscaperITEM7(Shops.LANDSCAPER, new ItemStack(Material.RED_SANDSTONE), 20, 0, null, 64, null, null),
    LandscaperITEM8(Shops.LANDSCAPER, new ItemStack(Material.GLASS), 50, 0, null, 64, null, null),
    LandscaperITEM9(Shops.LANDSCAPER, new ItemStack(Material.GRAVEL), 57, 0, null, 64, null, null),
    LandscaperITEM10(Shops.LANDSCAPER, new ItemStack(Material.DIORITE), 21, 0, null, 64, null, null),
    LandscaperITEM11(Shops.LANDSCAPER, new ItemStack(Material.TERRACOTTA), 18, 0, null, 64, null, null),
    LandscaperITEM12(Shops.LANDSCAPER, new ItemStack(Material.SEA_LANTERN), 20, 0, null, 64, null, null),
    LandscaperITEM13(Shops.LANDSCAPER, new ItemStack(Material.PRISMARINE), 26, 0, null, 64, null, null),
    LandscaperITEM14(Shops.LANDSCAPER, new ItemStack(Material.PRISMARINE_BRICKS), 28, 0, null, 64, null, null),
    LandscaperITEM15(Shops.LANDSCAPER, new ItemStack(Material.DARK_PRISMARINE), 28, 0, null, 64, null, null),
    LandscaperITEM16(Shops.LANDSCAPER, new ItemStack(Material.WHITE_WOOL), 15, 0, null, 64, null, null),

    EstateITEM1(Shops.ESTATE, new ItemStack(Material.PUMPKIN), 6, 0, null, 64, null, null),
    EstateITEM2(Shops.ESTATE, new ItemStack(Material.MELON), 10, 0, null, 64, null, null),
    EstateITEM3(Shops.ESTATE, new ItemStack(Material.MELON_SLICE), 2, 0, null, 64, null, null),
    EstateITEM4(Shops.ESTATE, new ItemStack(Material.SUGAR_CANE), 10, 0, null, 64, null, null),
    EstateITEM5(Shops.ESTATE, new ItemStack(Material.CACTUS), 24, 0, null, 64, null, null),
    EstateITEM6(Shops.ESTATE, new ItemStack(Material.BEETROOT), 14, 0, null, 64, null, null),
    EstateITEM7(Shops.ESTATE, new ItemStack(Material.RED_MUSHROOM), 6, 0, null, 64, null, null),
    EstateITEM8(Shops.ESTATE, new ItemStack(Material.BROWN_MUSHROOM), 6, 0, null, 64, null, null),

    JoyITEM1(Shops.JOY, null, 100, 0, null, 1, "CONSUMABLE", "JOY_SOUP"),
    JoyITEM2(Shops.JOY, null, 250, 0, null, 1, "CONSUMABLE", "JOY_SOUP2"),
    JoyITEM3(Shops.JOY, null, 500, 0, null, 1, "CONSUMABLE", "JOY_SOUP3"),
    JoyITEM4(Shops.JOY, null, 5_000, 0, null, 1, "BOW", "JOYS_BOW2"),

    CatLadyITEM1(Shops.CAT_LADY, new ItemStack(Material.LEAD), 25, 0, null, 64, null, null),

    EnchanterITEM1(Shops.ENCHANTER, null, 100, 10, null, 1, "ENCHANTMENT", "ENCHANTING_BOOK"),
    EnchanterITEM2(Shops.ENCHANTER, null, 250, 20, new String[] {"MATERIAL;ENCHANTED_PAPER;8"}, 1, "ENCHANTMENT", "ENCHANTING_BOOK2"),
    EnchanterITEM3(Shops.ENCHANTER, null, 500, 30, new String[] {"MATERIAL;ENCHANTED_PAPER;16"}, 1, "ENCHANTMENT", "ENCHANTING_BOOK3"),
    EnchanterITEM4(Shops.ENCHANTER, null, 1_000, 40, new String[] {"MATERIAL;ENCHANTED_PAPER;32"}, 1, "ENCHANTMENT", "ENCHANTING_BOOK4"),
    EnchanterITEM5(Shops.ENCHANTER, null, 1_500, 50, new String[] {"MATERIAL;ENCHANTED_PAPER;64"}, 1, "ENCHANTMENT", "ENCHANTING_BOOK5"),
    EnchanterITEM7(Shops.ENCHANTER, null, 150, 15, null, 1, "CONSUMABLE", "ENCHANTED_BOX"),
    EnchanterITEM8(Shops.ENCHANTER, null, 250, 25, null, 1, "CONSUMABLE", "ENCHANTED_BOX2"),
    EnchanterITEM9(Shops.ENCHANTER, null, 350, 35, null, 1, "CONSUMABLE", "ENCHANTED_BOX3"),
    EnchanterITEMF1(Shops.ENCHANTER, null, 0, 0, null, 0, null, null),
    EnchanterITEMF2(Shops.ENCHANTER, null, 0, 0, null, 0, null, null),
    EnchanterITEM10(Shops.ENCHANTER, null, 15, 0, new String[] {"MATERIAL;ENCHANTED_LAPIS;1"}, 1, "CONSUMABLE", "XP_BOTTLE"),
    EnchanterITEM11(Shops.ENCHANTER, null, 100, 0, new String[] {"MATERIAL;ENCHANTED_LAPIS;8"}, 1, "CONSUMABLE", "XP_BOTTLE2"),
    EnchanterITEM13(Shops.ENCHANTER, null, 500, 0, new String[] {"MATERIAL;ENCHANTED_LAPIS;16"}, 1, "CONSUMABLE", "XP_BOTTLE2"),

    ;


    private static final Map<Shops, List<ItemList>> SHOPS_LIST_MAP = new EnumMap<>(Shops.class);

    public static List<ItemList> getShop(Shops shops) {
        return SHOPS_LIST_MAP.computeIfAbsent(shops, key -> Arrays.stream(values())
                .filter(shop -> shop.getShop().equals(key))
                .collect(Collectors.toList()));
    }


    private Shops shop;
    private ItemStack itemStack;
    private int coinPrice;
    private int expPrice;
    private String[] itemPrice;
    private int maxPurchaseAmount;
    private String type;
    private String ID;

    ItemList(Shops shop, ItemStack itemStack, int price, int expPrice, String[] itemPrice, int maxPurchaseAmount, String type, String id) {
        this.shop = shop;
        this.itemStack = itemStack;
        this.coinPrice = price;
        this.expPrice = expPrice;
        this.itemPrice = itemPrice;
        this.maxPurchaseAmount = maxPurchaseAmount;
        this.type = type;
        this.ID = id;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public int getCoinPrice() {
        return coinPrice;
    }

    public int getExpPrice() {
        return expPrice;
    }

    public Material getMaterial() {
        return itemStack.getType();
    }

    public String getType() {
        return type;
    }

    public String getID() {
        return ID;
    }

    public Shops getShop() {
        return shop;
    }

    public int getMaxPurchaseAmount() {
        return maxPurchaseAmount;
    }

    public String[] getItemPrice() {
        return itemPrice;
    }
}
