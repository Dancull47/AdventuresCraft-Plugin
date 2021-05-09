package monzter.adventurescraft.plugin.shared.events.extras;

public enum VoteRewardList {
    RareLootbox5("CONSUMABLE", "LOOTBOX3", 5, 5),
    LegendaryLootbox5("CONSUMABLE", "LOOTBOX4", 10, 5),
    ExoticLootbox5("CONSUMABLE", "LOOTBOX5", 15, 5),
    RareEgg5("PET", "PET_EGG3", 5, 5),
    LegendaryEgg5("PET", "PET_EGG4", 10, 5),
    ExoticEgg5("PET", "PET_EGG5", 15, 5);

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
