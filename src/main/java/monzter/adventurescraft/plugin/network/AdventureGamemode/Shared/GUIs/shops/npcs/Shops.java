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
    FARMING_ACCESSORIES("Farming Accessories", "FarmingAccessories", null, Material.GREEN_STAINED_GLASS_PANE),
    FARMING_ARMOR("Farming Armor", "FarmingArmor", null, Material.GREEN_STAINED_GLASS_PANE),
    FARMING_TOOLS("Farming Tools", "FarmingTools", null, Material.GREEN_STAINED_GLASS_PANE),
    FARMING_WEAPONS("Farming Weapons", "FarmingWeapons", null, Material.GREEN_STAINED_GLASS_PANE),

    COURTYARD_ARMOR("Courtyard Armor", "CourtyardArmor", null, Material.RED_STAINED_GLASS_PANE),
    COURTYARD_CONSUMABLES("Courtyard Consumables", "CourtyardConsumables", null, Material.RED_STAINED_GLASS_PANE),
    COURTYARD_GEM_STONES("Courtyard Gem Stones", "CourtyardGemStones", null, Material.RED_STAINED_GLASS_PANE),
    COURTYARD_MATERIALS("Courtyard Materials", "CourtyardMaterials", null, Material.RED_STAINED_GLASS_PANE),
    COURTYARD_WEAPONS("Courtyard Weapons", "CourtyardWeapons", null, Material.RED_STAINED_GLASS_PANE),

    CASTLE_ARMOR("Castle Armor", "CastleArmor", null, Material.RED_STAINED_GLASS_PANE),
    CASTLE_CATALYSTS("Castle Catalysts", "CastleCatalysts", null, Material.RED_STAINED_GLASS_PANE),
    CASTLE_CONSUMABLES("Castle Consumables", "CastleConsumables", null, Material.RED_STAINED_GLASS_PANE),
    CASTLE_GEM_STONES("Castle Gem Stones", "CastleGemStones", null, Material.RED_STAINED_GLASS_PANE),
    CASTLE_MATERIALS("Castle Materials", "CastleMaterials", null, Material.RED_STAINED_GLASS_PANE),
    CASTLE_WEAPONS("Castle Weapons", "CastleWeapons", null, Material.RED_STAINED_GLASS_PANE),

    GRAVEYARD_UPGRADES("Graveyard Upgrades", "GraveyardUpgrades", null, Material.RED_STAINED_GLASS_PANE),
    GRAVEYARD_WEAPONS("Graveyard Weapons", "GraveyardWeapons", null, Material.RED_STAINED_GLASS_PANE),

    FORAGING_ACCESSORIES("Foraging Accessories", "ForagingAccessories", null, Material.GREEN_STAINED_GLASS_PANE),
    FORAGING_ARMOR("Foraging Armor", "ForagingArmor", null, Material.GREEN_STAINED_GLASS_PANE),
    FORAGING_CATALYSTS("Foraging Catalyst", "ForagingCatalysts", null, Material.GREEN_STAINED_GLASS_PANE),
    FORAGING_CONSUMABLES("Foraging Consumables", "ForagingConsumables", null, Material.GREEN_STAINED_GLASS_PANE),
    FORAGING_TOOLS("Foraging Tools", "ForagingTools", null, Material.GREEN_STAINED_GLASS_PANE),
    FORAGING_UPGRADES("Foraging Upgrades", "ForagingUpgrades", null, Material.GREEN_STAINED_GLASS_PANE),
    FORAGING_WEAPONS("Foraging Weapons", "ForagingWeapons", null, Material.GREEN_STAINED_GLASS_PANE),

    LUMBERJACK_TOOLS("Lumberjack's Tools", "LumberjackTools", null, Material.GREEN_STAINED_GLASS_PANE),

    VOID_ACCESSORIES("Void Accessories", "VoidAccessories", null, Material.PURPLE_STAINED_GLASS_PANE),
    VOID_ARMOR("Void Armor", "VoidArmor", null, Material.PURPLE_STAINED_GLASS_PANE),
    VOID_CATALYSTS("Void Catalyst", "VoidCatalysts", null, Material.PURPLE_STAINED_GLASS_PANE),
    VOID_CONSUMABLES("Void Consumables", "VoidConsumables", null, Material.PURPLE_STAINED_GLASS_PANE),
    VOID_GEM_STONES("Void Gem Stones", "VoidGemStones", null, Material.PURPLE_STAINED_GLASS_PANE),
    VOID_MATERIALS("Void Materials", "VoidMaterials", null, Material.PURPLE_STAINED_GLASS_PANE),
    VOID_MOUNTS("Void Mounts", "VoidMounts", null, Material.PURPLE_STAINED_GLASS_PANE),
    VOID_WEAPONS("Void Weapons", "VoidWeapons", null, Material.PURPLE_STAINED_GLASS_PANE),

    ENCHANTER("Enchanter", "Enchanter", null, Material.PURPLE_STAINED_GLASS_PANE),

    MATERIALS("Material", "Material", null, Material.GREEN_STAINED_GLASS_PANE),
    MATERIALS_FARMING("Farming Materials", "Farming", null, Material.GREEN_STAINED_GLASS_PANE),
    MATERIALS_FORAGING("Foraging Materials", "Foraging", null, Material.GREEN_STAINED_GLASS_PANE),
    MATERIALS_MINING("Mining Materials", "Mining", null, Material.BLUE_STAINED_GLASS_PANE),
    MATERIALS_COMBAT("Combat Materials", "Combat", null, Material.RED_STAINED_GLASS_PANE),

    MINER("Miner", "Miner", Region.CAVERN, Material.BROWN_STAINED_GLASS_PANE),
    BLAST_FURNACE("Blast Furnace", "BlastFurnace", Region.CAVERN, Material.ORANGE_STAINED_GLASS_PANE),

    WANDERING_TRADER("Wandering Trader", "WanderingTrader", Region.TOWN, Material.GREEN_STAINED_GLASS_PANE),
    ;

    public static List<Shops> getShop(String area) {
        List list = new ArrayList();
        for (Shops shop: Shops.values()) {
            if (shop.name().contains(area.toUpperCase()+"_"))
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
