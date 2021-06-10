package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.shops.npcs;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


enum AdvancedItemList {
    ITEM1(Shops.ENCHANTER, null, 100, 10, 1, "TOOL", "WOODEN_HOE"),
    ITEM2(Shops.ENCHANTER, new ItemStack(Material.WHEAT), 2, 0, 64, null, null),
    ITEM3(Shops.ENCHANTER, new ItemStack(Material.CARROT), 4, 0, 64, null, null),
    ITEM4(Shops.ENCHANTER, new ItemStack(Material.POTATO), 4, 0, 64, null, null),
    ITEM5(Shops.ENCHANTER, new ItemStack(Material.BEEF), 5, 0, 64, null, null),
    ITEM6(Shops.ENCHANTER, new ItemStack(Material.LEATHER), 10, 0, 64, null, null),
    ITEM7(Shops.ENCHANTER, new ItemStack(Material.PORKCHOP), 5, 0, 64, null, null),
    ITEM8(Shops.ENCHANTER, new ItemStack(Material.MUTTON), 6, 0, 64, null, null),
    ITEM9(Shops.ENCHANTER, new ItemStack(Material.WHITE_WOOL), 10, 0, 64, null, null),
    ITEM10(Shops.ENCHANTER, new ItemStack(Material.CHICKEN), 8, 0, 64, null, null),
    ITEM11(Shops.ENCHANTER, new ItemStack(Material.FEATHER), 5, 0, 64, null, null),
    ITEM12(Shops.ENCHANTER, new ItemStack(Material.RABBIT), 6, 0, 64, null, null),
    ITEM13(Shops.ENCHANTER, new ItemStack(Material.RABBIT_HIDE), 10, 0, 64, null, null),
    ITEM14(Shops.ENCHANTER, new ItemStack(Material.RABBIT_FOOT), 10, 0, 64, null, null),
    ITEM15(Shops.ENCHANTER, null, 10, 0, 1, "TOOL", "WOODEN_HOE"),


    ;

    private static final Map<Shops, List<AdvancedItemList>> SHOPS_LIST_MAP = new EnumMap<>(Shops.class);

    public static List<AdvancedItemList> getShop(Shops shops) {
        return SHOPS_LIST_MAP.computeIfAbsent(shops, key -> Arrays.stream(values())
                .filter(shop -> shop.getShop().equals(key))
                .collect(Collectors.toList()));
    }

    private Shops shop;
    private ItemStack itemStack;
    private int coinPrice;
    private int expPrice;
    private int maxPurchaseAmount;
    private String type;
    private String ID;

    AdvancedItemList(Shops shop, ItemStack itemStack, int coinPrice, int expPrice, int maxPurchaseAmount, String type, String id) {
        this.shop = shop;
        this.itemStack = itemStack;
        this.coinPrice = coinPrice;
        this.expPrice = expPrice;
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
}
