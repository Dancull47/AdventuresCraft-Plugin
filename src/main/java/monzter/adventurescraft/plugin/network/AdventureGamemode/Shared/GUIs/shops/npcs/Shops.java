package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.shops.npcs;

import monzter.adventurescraft.plugin.utilities.enums.Region;
import org.bukkit.Material;

public enum Shops {
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

    ENCHANTER("Enchanter", "Enchanter", Region.TOWN, Material.PURPLE_STAINED_GLASS_PANE),

    MINER("Miner", "Miner", Region.CAVERN, Material.BROWN_STAINED_GLASS_PANE),
    BLAST_FURNACE("Blast Furnace", "BlastFurnace", Region.CAVERN, Material.ORANGE_STAINED_GLASS_PANE),

    WANDERING_TRADER("Wandering Trader", "WanderingTrader", Region.TOWN, Material.GREEN_STAINED_GLASS_PANE),
    ;

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
