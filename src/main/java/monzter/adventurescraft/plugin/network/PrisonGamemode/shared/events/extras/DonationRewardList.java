package monzter.adventurescraft.plugin.network.PrisonGamemode.shared.events.extras;

public enum DonationRewardList {
    PetSlot(null, "RED_SHULKER_BOX", 1, "PetSlot", "Pet Slot", 750),
    UnlimitedWeight(null, "CHEST", 1, "UnlimitedWeight", "Unlimited Weight", 1250),
    ExoticLootbox5("CONSUMABLE", "LOOTBOX5", 5, "ExoticLootbox5", "5 Exotic Lootboxes", 350),
    ExoticLootbox10("CONSUMABLE", "LOOTBOX5", 10, "ExoticLootbox10", "10 Exotic Lootboxes", 650),
    MythicalLootbox5("CONSUMABLE", "LOOTBOX6", 5, "MythicalLootbox5", "5 Mythical Lootboxes", 750),
    MythicalLootbox10("CONSUMABLE", "LOOTBOX6", 10, "MythicalLootbox10", "10 Mythical Lootboxes", 1300),
    GodlyLootbox5("CONSUMABLE", "LOOTBOX7", 5, "GodlyLootbox5", "5 Godly Lootboxes", 1200),
    GodlyLootbox10("CONSUMABLE", "LOOTBOX7", 10, "GodlyLootbox10", "10 Godly Lootboxes", 2250),
    ExoticPetEgg5("PET", "PET_EGG5", 5, "ExoticPetEgg5", "5 Exotic Pet Eggs", 350),
    ExoticPetEgg10("PET", "PET_EGG5", 10, "ExoticPetEgg10", "10 Exotic Pet Eggs", 550),
    MythicalPetEgg5("PET", "PET_EGG6", 5, "MythicalPetEgg5", "5 Mythical Pet Eggs", 750),
    MythicalPetEgg10("PET", "PET_EGG6", 10, "MythicalPetEgg10", "10 Mythical Pet Eggs", 1300),
    GodlyPetEgg5("PET", "PET_EGG7", 5, "GodlyPetEgg5", "5 Godly Pet Eggs", 1200),
    GodlyPetEgg10("PET", "PET_EGG7", 10, "GodlyPetEgg10", "10 Godly Pet Eggs", 2250),
    LegendaryPhoenixPetEgg5("PET", "PHOENIX_EGG2", 5, "LegendaryPhoenixPetEgg5", "5 Legendary Phoenix Pet Eggs", 1200),
    LegendaryPhoenixPetEgg10("PET", "PHOENIX_EGG2", 10, "LegendaryPhoenixPetEgg10", "10 Legendary Phoenix Pet Eggs", 2250),
    LegendaryDragonPetEgg5("PET", "DRAGON_EGG2", 5, "LegendaryDragonPetEgg5", "5 Legendary Dragon Pet Eggs", 1200),
    LegendaryDragonPetEgg10("PET", "DRAGON_EGG2", 10, "LegendaryDragonPetEgg10", "10 Legendary Dragon Pet Eggs", 2250);

    public final String type;
    public final String itemID;
    public final int amount;
    public final String id;
    public final String displayName;
    public final int price;

    DonationRewardList(String type, String itemID, int amount, String id, String displayName, int price) {
        this.itemID = itemID;
        this.type = type;
        this.amount = amount;
        this.id = id;
        this.displayName = displayName;
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

    public String getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getPrice() {
        return price;
    }
}
