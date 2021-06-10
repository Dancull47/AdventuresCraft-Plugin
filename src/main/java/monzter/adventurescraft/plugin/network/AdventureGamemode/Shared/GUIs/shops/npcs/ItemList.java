package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.shops.npcs;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


enum ItemList {
    ITEM1(Shops.FARMER, new ItemStack(Material.WHEAT_SEEDS), 2, 64, null, null),
    ITEM2(Shops.FARMER, new ItemStack(Material.WHEAT), 2, 64, null, null),
    ITEM3(Shops.FARMER, new ItemStack(Material.CARROT), 4, 64, null, null),
    ITEM4(Shops.FARMER, new ItemStack(Material.POTATO), 4, 64, null, null),
    ITEM5(Shops.FARMER, new ItemStack(Material.BEEF), 5, 64, null, null),
    ITEM6(Shops.FARMER, new ItemStack(Material.LEATHER), 10, 64, null, null),
    ITEM7(Shops.FARMER, new ItemStack(Material.PORKCHOP), 5, 64, null, null),
    ITEM8(Shops.FARMER, new ItemStack(Material.MUTTON), 6, 64, null, null),
    ITEM9(Shops.FARMER, new ItemStack(Material.WHITE_WOOL), 10, 64, null, null),
    ITEM10(Shops.FARMER, new ItemStack(Material.CHICKEN), 8, 64, null, null),
    ITEM11(Shops.FARMER, new ItemStack(Material.FEATHER), 5, 64, null, null),
    ITEM12(Shops.FARMER, new ItemStack(Material.RABBIT), 6, 64, null, null),
    ITEM13(Shops.FARMER, new ItemStack(Material.RABBIT_HIDE), 10, 64, null, null),
    ITEM14(Shops.FARMER, new ItemStack(Material.RABBIT_FOOT), 10, 64, null, null),
    ITEM15(Shops.FARMER, null, 10, 1, "TOOL", "WOODEN_HOE"),

    MercenaryITEM1(Shops.MERCENARY, new ItemStack(Material.BONE), 20, 64, null, null),
    MercenaryITEM2(Shops.MERCENARY, new ItemStack(Material.SPIDER_EYE), 35, 64, null, null),
    MercenaryITEM3(Shops.MERCENARY, new ItemStack(Material.STRING), 30, 64, null, null),
    MercenaryITEM4(Shops.MERCENARY, new ItemStack(Material.GUNPOWDER), 50, 64, null, null),
    MercenaryITEM5(Shops.MERCENARY, new ItemStack(Material.ROTTEN_FLESH), 15, 64, null, null),
    MercenaryITEM6(Shops.MERCENARY, new ItemStack(Material.ENDER_PEARL), 30, 64, null, null),
    MercenaryITEM7(Shops.MERCENARY, new ItemStack(Material.MAGMA_CREAM), 20, 64, null, null),
    MercenaryITEM8(Shops.MERCENARY, new ItemStack(Material.BLAZE_ROD), 25, 64, null, null),
    MercenaryITEM9(Shops.MERCENARY, new ItemStack(Material.SLIME_BALL), 60, 64, null, null),
    MercenaryITEM10(Shops.MERCENARY, new ItemStack(Material.ARROW), 20, 64, null, null),
    MercenaryITEM11(Shops.MERCENARY, null, 250, 1, "SWORD", "BASIC_SWORD"),

    LumberjackITEM1(Shops.LUMBERJACK, new ItemStack(Material.OAK_LOG), 8, 64, null, null),
    LumberjackITEM2(Shops.LUMBERJACK, new ItemStack(Material.SPRUCE_LOG), 16, 64, null, null),
    LumberjackITEM3(Shops.LUMBERJACK, new ItemStack(Material.DARK_OAK_LOG), 60, 64, null, null),
    LumberjackITEM4(Shops.LUMBERJACK, new ItemStack(Material.BIRCH_LOG), 20, 64, null, null),
    LumberjackITEM5(Shops.LUMBERJACK, new ItemStack(Material.ACACIA_LOG), 24, 64, null, null),
    LumberjackITEM6(Shops.LUMBERJACK, new ItemStack(Material.JUNGLE_LOG), 26, 64, null, null),
    LumberjackITEM7(Shops.LUMBERJACK, new ItemStack(Material.OAK_LEAVES), 26, 64, null, null),
    LumberjackITEM8(Shops.LUMBERJACK, new ItemStack(Material.SPRUCE_LEAVES), 50, 64, null, null),
    LumberjackITEM9(Shops.LUMBERJACK, new ItemStack(Material.DARK_OAK_LEAVES), 120, 64, null, null),
    LumberjackITEM10(Shops.LUMBERJACK, new ItemStack(Material.BIRCH_LEAVES), 30, 64, null, null),
    LumberjackITEM11(Shops.LUMBERJACK, new ItemStack(Material.ACACIA_LEAVES), 30, 64, null, null),
    LumberjackITEM12(Shops.LUMBERJACK, new ItemStack(Material.JUNGLE_LEAVES), 30, 64, null, null),
    LumberjackITEM13(Shops.LUMBERJACK, new ItemStack(Material.COCOA_BEANS), 10, 64, null, null),
    LumberjackITEM14(Shops.LUMBERJACK, new ItemStack(Material.HONEYCOMB), 4, 64, null, null),
    LumberjackITEM15(Shops.LUMBERJACK, new ItemStack(Material.RABBIT), 6, 64, null, null),
    LumberjackITEM16(Shops.LUMBERJACK, new ItemStack(Material.RABBIT_HIDE), 10, 64, null, null),
    LumberjackITEM17(Shops.LUMBERJACK, new ItemStack(Material.RABBIT_FOOT), 10, 64, null, null),
    LumberjackITEM18(Shops.LUMBERJACK, null, 100, 1, "TOOL", "WOODEN_AXE"),
    LumberjackITEM19(Shops.LUMBERJACK, null, 250, 1, "TOOL", "STONE_AXE"),

    EstateITEM1(Shops.ESTATE, new ItemStack(Material.PUMPKIN), 6, 64, null, null),
    EstateITEM2(Shops.ESTATE, new ItemStack(Material.MELON), 10, 64, null, null),
    EstateITEM3(Shops.ESTATE, new ItemStack(Material.MELON_SLICE), 2, 64, null, null),
    EstateITEM4(Shops.ESTATE, new ItemStack(Material.SUGAR_CANE), 10, 64, null, null),
    EstateITEM5(Shops.ESTATE, new ItemStack(Material.CACTUS), 24, 64, null, null),
    EstateITEM6(Shops.ESTATE, new ItemStack(Material.BEETROOT), 14, 64, null, null),
    EstateITEM7(Shops.ESTATE, new ItemStack(Material.RED_MUSHROOM), 6, 64, null, null),
    EstateITEM8(Shops.ESTATE, new ItemStack(Material.BROWN_MUSHROOM), 6, 64, null, null),

    ;

    private static final Map<Shops, List<ItemList>> SHOPS_LIST_MAP = new EnumMap<>(Shops.class);

    public static List<ItemList> getShop(Shops shops) {
        return SHOPS_LIST_MAP.computeIfAbsent(shops, key -> Arrays.stream(values())
                .filter(shop -> shop.getShop().equals(key))
                .collect(Collectors.toList()));
    }

    private Shops shop;
    private ItemStack itemStack;
    private int price;
    private int maxPurchaseAmount;
    private String type;
    private String ID;

    ItemList(Shops shop, ItemStack itemStack, int price, int maxPurchaseAmount, String type, String id) {
        this.shop = shop;
        this.itemStack = itemStack;
        this.price = price;
        this.maxPurchaseAmount = maxPurchaseAmount;
        this.type = type;
        this.ID = id;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public int getPrice() {
        return price;
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
}
