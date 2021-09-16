package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.shops.npcs;

import monzter.adventurescraft.plugin.utilities.enums.Region;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public enum Shops {
    //    Vendors
    FARMER("Farmer", "Farmer", Region.FARM, Material.GREEN_STAINED_GLASS_PANE),
    MERCENARY("Mercenary", "Mercenary", Region.TOWN, Material.RED_STAINED_GLASS_PANE),
    LUMBERJACK("Lumberjack", "Lumberjack", Region.FOREST, Material.GREEN_STAINED_GLASS_PANE),
    MINING("Mining", "Mining", Region.TOWN, Material.BLUE_STAINED_GLASS_PANE),
    LANDSCAPER("Landscaper", "Landscaper", Region.TOWN, Material.GREEN_STAINED_GLASS_PANE),
    DEMON("Demon", "Demon", Region.HELL, Material.RED_STAINED_GLASS_PANE),
    JOY("Joy", "Joy", Region.TOWN, Material.PINK_STAINED_GLASS_PANE),
    WIZARD("Wizard", "Wizard", Region.TOWN, Material.PURPLE_STAINED_GLASS_PANE),
    ESTATE("Estate", "Estate", Region.ESTATE, Material.GREEN_STAINED_GLASS_PANE),
    CAT_LADY("Cat Lady", "CatLady", Region.FOREST, Material.YELLOW_STAINED_GLASS_PANE),

    //    Stations
    FARMING_ACCESSORIES("Farming Accessories", "FarmingAccessories", Region.FARM, Material.GREEN_STAINED_GLASS_PANE),
    FARMING_ARMOR("Farming Armor", "FarmingArmor", Region.FARM, Material.GREEN_STAINED_GLASS_PANE),
    FARMING_TOOLS("Farming Tools", "FarmingTools", Region.FARM, Material.GREEN_STAINED_GLASS_PANE),
    FARMING_WEAPONS("Farming Weapons", "FarmingWeapons", Region.FARM, Material.GREEN_STAINED_GLASS_PANE),

    FORAGING_ACCESSORIES("Foraging Accessories", "ForagingAccessories", Region.FOREST, Material.GREEN_STAINED_GLASS_PANE),
    FORAGING_ARMOR("Foraging Armor", "ForagingArmor", Region.FOREST, Material.GREEN_STAINED_GLASS_PANE),
    FORAGING_CATALYSTS("Foraging Catalyst", "ForagingCatalysts", Region.FOREST, Material.GREEN_STAINED_GLASS_PANE),
    FORAGING_CONSUMABLES("Foraging Consumables", "ForagingConsumables", Region.FOREST, Material.GREEN_STAINED_GLASS_PANE),
    FORAGING_TOOLS("Foraging Tools", "ForagingTools", Region.FOREST, Material.GREEN_STAINED_GLASS_PANE),
    FORAGING_UPGRADES("Foraging Upgrades", "ForagingUpgrades", Region.FOREST, Material.GREEN_STAINED_GLASS_PANE),
    FORAGING_WEAPONS("Foraging Weapons", "ForagingWeapons", Region.FOREST, Material.GREEN_STAINED_GLASS_PANE),

    VOID_ACCESSORIES("Void Accessories", "VoidAccessories", Region.VOID, Material.PURPLE_STAINED_GLASS_PANE),
    VOID_ARMOR("Void Armor", "VoidArmor", Region.VOID, Material.PURPLE_STAINED_GLASS_PANE),
    VOID_CATALYSTS("Void Catalyst", "VoidCatalysts", Region.VOID, Material.PURPLE_STAINED_GLASS_PANE),
    VOID_CONSUMABLES("Void Consumables", "VoidConsumables", Region.VOID, Material.PURPLE_STAINED_GLASS_PANE),
    VOID_GEM_STONES("Void Gem Stones", "VoidGemStones", Region.VOID, Material.PURPLE_STAINED_GLASS_PANE),
    VOID_MATERIALS("Void Materials", "VoidMaterials", Region.VOID, Material.PURPLE_STAINED_GLASS_PANE),
    VOID_MOUNTS("Void Mounts", "VoidMounts", Region.VOID, Material.PURPLE_STAINED_GLASS_PANE),
    VOID_WEAPONS("Void Weapons", "VoidWeapons", Region.VOID, Material.PURPLE_STAINED_GLASS_PANE),

    ENCHANTER("Enchanter", "Enchanter", Region.TOWN, Material.PURPLE_STAINED_GLASS_PANE),

    MINER("Miner", "Miner", Region.CAVERN, Material.BROWN_STAINED_GLASS_PANE),
    BLAST_FURNACE("Blast Furnace", "BlastFurnace", Region.CAVERN, Material.ORANGE_STAINED_GLASS_PANE),

    WANDERING_TRADER("Wandering Trader", "WanderingTrader", Region.TOWN, Material.GREEN_STAINED_GLASS_PANE),
    ;

    public static List<Shops> getShop(String area) {
        List list = new ArrayList();
        for (Shops shop: Shops.values()) {
            if (shop.name().contains(area.toUpperCase()))
                list.add(shop);
        }
        return list;
    }


    public final String title;
    public final String command;
    public final Region region;
    public final Material backgroundMaterial;

    Shops(String title, String command, Region region, Material backgroundMaterial) {
        this.title = title;
        this.command = command;
        this.region = region;
        this.backgroundMaterial = backgroundMaterial;
    }

    public String getTitle() {
        return title;
    }

    public String getCommand() {
        return command;
    }

    public String getName() {
        return title;
    }

    public Region getArea() {
        return region;
    }

    public Material getBackgroundMaterial() {
        return backgroundMaterial;
    }
}
