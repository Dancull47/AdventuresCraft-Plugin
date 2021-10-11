package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.mainMenu.resourceCollector;

import monzter.adventurescraft.plugin.utilities.mmoitems.MMOItemsHelperImpl;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public enum Resources {
    ROTTEN_FLESH(new ItemStack(Material.ROTTEN_FLESH), "COMBAT"),
    BONE(new ItemStack(Material.BONE), "COMBAT"),
    ARROW(new ItemStack(Material.ARROW), "COMBAT"),
    BONE_FRAG(MMOItemsHelperImpl.getItem("MATERIAL", "BONE_FRAGMENT"), "COMBAT"),
    SPIDER_EYE(new ItemStack(Material.SPIDER_EYE), "COMBAT"),
    STRING(new ItemStack(Material.STRING), "COMBAT"),
    GUNPOWDER(new ItemStack(Material.GUNPOWDER), "COMBAT"),
    SLIME_BALL(new ItemStack(Material.SLIME_BALL), "COMBAT"),
    ENDER_PEARL(new ItemStack(Material.ENDER_PEARL), "COMBAT"),
    GHAST_TEAR(new ItemStack(Material.GHAST_TEAR), "COMBAT"),
    MAGMA_CREAM(new ItemStack(Material.MAGMA_CREAM), "COMBAT"),
    BLAZE_ROD(new ItemStack(Material.BLAZE_ROD), "COMBAT"),

    STONE(new ItemStack(Material.STONE), "MINING"),
    COBBLESTONE(new ItemStack(Material.COBBLESTONE), "MINING"),
    COAL(new ItemStack(Material.COAL), "MINING"),
    IRON_INGOT(new ItemStack(Material.IRON_INGOT), "MINING"),
    GOLD_INGOT(new ItemStack(Material.GOLD_INGOT), "MINING"),
    REDSTONE(new ItemStack(Material.REDSTONE), "MINING"),
    LAPIS_LAZULI(new ItemStack(Material.LAPIS_LAZULI), "MINING"),
    DIAMOND(new ItemStack(Material.DIAMOND), "MINING"),
    EMERALD(new ItemStack(Material.EMERALD), "MINING"),
    END_STONE(new ItemStack(Material.END_STONE), "MINING"),
    OBSIDIAN(new ItemStack(Material.OBSIDIAN), "MINING"),

    OAK_LOG(new ItemStack(Material.OAK_LOG), "FORAGING"),
    SPRUCE_LOG(new ItemStack(Material.SPRUCE_LOG), "FORAGING"),
    DARK_OAK_LOG(new ItemStack(Material.DARK_OAK_LOG), "FORAGING"),
    BIRCH_LOG(new ItemStack(Material.BIRCH_LOG), "FORAGING"),
    ACACIA_LOG(new ItemStack(Material.ACACIA_LOG), "FORAGING"),
    JUNGLE_LOG(new ItemStack(Material.JUNGLE_LOG), "FORAGING"),
    HONEYCOMB(new ItemStack(Material.HONEYCOMB), "FORAGING"),

    WHEAT_SEEDS(new ItemStack(Material.WHEAT_SEEDS), "FARMING"),
    WHEAT(new ItemStack(Material.WHEAT), "FARMING"),
    CARROT(new ItemStack(Material.CARROT), "FARMING"),
    POTATO(new ItemStack(Material.POTATO), "FARMING"),
    SUGAR_CANE(new ItemStack(Material.SUGAR_CANE), "FARMING"),
    BEETROOT(new ItemStack(Material.BEETROOT), "FARMING"),
    MELON_SLICE(new ItemStack(Material.MELON_SLICE), "FARMING"),
    PUMPKIN(new ItemStack(Material.PUMPKIN), "FARMING"),
    RED_MUSHROOM(new ItemStack(Material.RED_MUSHROOM), "FARMING"),
    BROWN_MUSHROOM(new ItemStack(Material.BROWN_MUSHROOM), "FARMING"),
    ;

    public ItemStack itemStack;
    public String category;

    Resources(ItemStack itemStack, String category) {
        this.itemStack = itemStack;
        this.category = category;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public String getCategory() {
        return category;
    }

    public static List<Resources> getResources(String type) {
        List list = new ArrayList();
        for (Resources shop : Resources.values()) {
            if (shop.getCategory().equalsIgnoreCase(type)) {
                list.add(shop);
            }
        }
        return list;
    }

}
