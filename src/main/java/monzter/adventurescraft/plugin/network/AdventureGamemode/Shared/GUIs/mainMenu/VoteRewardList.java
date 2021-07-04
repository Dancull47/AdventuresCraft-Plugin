package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.mainMenu;

public enum VoteRewardList {
    RareLootbox5("MATERIAL", "ENGRAM1", 2, 1),
    LegendaryLootbox5("MATERIAL", "ENGRAM2", 5, 1),
    ExoticLootbox5("MATERIAL", "ENGRAM3", 20, 1),
    ;

    public final String type;
    public final String id;
    public final int price;
    public final int amount;

    VoteRewardList(String type, String id, int price, int amount) {
        this.type = type;
        this.id = id;
        this.price = price;
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }
}
