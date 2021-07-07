package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.mainMenu;

public enum DailyShopList {
    ENGRAM("MATERIAL", "ENGRAM1", 1, 1),
    MAGICAL_BOX("CONSUMABLE", "MAGICAL_BOX5", 4, 2),
    ENCHANTED_MATERIALS("ENCHANTED_MATERIALS", "PUFFERFISH", 16, 3),
    COINS25_000("COINS25_000", "SUNFLOWER", 25_000, 4),
    PROFESSION_BOOSTER_BOX("CONSUMABLE", "PROFESSION_BOOSTER_BOX5", 1, 5),
    ENGRAM2("MATERIAL", "ENGRAM2", 1, 6),
    COMING_SOON("COMING_SOON", "RED_STAINED_GLASS_PANE", 1, 999),
    ;

    public final String type;
    public final String itemID;
    public final int amount;
    public final int price;

    DailyShopList(String type, String itemID, int amount, int price) {
        this.itemID = itemID;
        this.type = type;
        this.amount = amount;
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public String getItemID() {
        return itemID;
    }

    public int getAmount() {
        return amount;
    }

    public int getPrice() {
        return price;
    }
}
