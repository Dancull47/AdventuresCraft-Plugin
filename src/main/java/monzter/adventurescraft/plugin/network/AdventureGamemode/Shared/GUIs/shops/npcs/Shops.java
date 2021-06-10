package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.shops.npcs;

public enum Shops {
    FARMER("Farmer"),
    MERCENARY("Mercenary"),
    LUMBERJACK("Lumberjack"),
    LANDSCAPER("Landscaper"),
    DEMON("Demon"),
    JOY("Joy"),
    WIZARD("Wizard"),
    ESTATE("Estate"),
    CAT_LADY("Cat Lady"),
    ;

    public final String name;

    Shops(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
