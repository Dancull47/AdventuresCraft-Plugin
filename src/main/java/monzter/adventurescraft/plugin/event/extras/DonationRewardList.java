package monzter.adventurescraft.plugin.event.extras;

public enum DonationRewardList {
    PetSlot("PetSlot", "Pet Slot", 750),
    UnlimitedWeight("UnlimitedWeight", "Unlimited Weight", 1250),
    ExoticLootbox5("ExoticLootbox5", "5 Exotic Lootboxes", 350),
    ExoticLootbox10("ExoticLootbox10", "10 Exotic Lootboxes", 650),
    MythicalLootbox5("MythicalLootbox5", "5 Mythical Lootboxes", 750),
    MythicalLootbox10("MythicalLootbox10", "10 Mythical Lootboxes", 1300),
    GodlyLootbox5("GodlyLootbox5", "5 Godly Lootboxes", 1200),
    GodlyLootbox10("GodlyLootbox10", "10 Godly Lootboxes", 2250),
    ExoticPetEgg5("ExoticPetEgg5", "5 Exotic Pet Eggs", 350),
    ExoticPetEgg10("ExoticPetEgg10", "10 Exotic Pet Eggs", 550),
    MythicalPetEgg5("MythicalPetEgg5", "5 Mythical Pet Eggs", 750),
    MythicalPetEgg10("MythicalPetEgg10", "10 Mythical Pet Eggs", 1300),
    GodlyPetEgg5("GodlyPetEgg5", "5 Godly Pet Eggs", 1200),
    GodlyPetEgg10("GodlyPetEgg10", "10 Godly Pet Eggs", 2250),
    LegendaryPhoenixPetEgg5("LegendaryPhoenixPetEgg5", "5 Legendary Phoenix Pet Eggs", 1200),
    LegendaryPhoenixPetEgg10("LegendaryPhoenixPetEgg10", "10 Legendary Phoenix Pet Eggs", 2250),
    LegendaryDragonPetEgg5("LegendaryDragonPetEgg5", "5 Legendary Dragon Pet Eggs", 1200),
    LegendaryDragonPetEgg10("LegendaryDragonPetEgg10", "10 Legendary Dragon Pet Eggs", 2250);

    public final String id;
    public final String displayName;
    public final int price;

    DonationRewardList(String id, String displayName, int price) {
        this.id = id;
        this.displayName = displayName;
        this.price = price;
    }
}
