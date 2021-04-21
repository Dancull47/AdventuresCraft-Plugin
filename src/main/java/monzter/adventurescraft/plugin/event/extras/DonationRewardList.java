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
    ExoticEgg5("ExoticEgg5", "5 Exotic Eggs", 350),
    ExoticEgg10("ExoticEgg10", "10 Exotic Eggs", 550),
    MythicalEgg5("MythicalEgg5", "5 Mythical Eggs", 750),
    MythicalEgg10("MythicalEgg10", "10 Mythical Eggs", 1300),
    GodlyEgg5("GodlyEgg5", "5 Godly Eggs", 1200),
    GodlyEgg10("GodlyEgg10", "10 Godly Eggs", 2250),
    LegendaryPhoenixEgg5("LegendaryPhoenixEgg5", "5 Legendary Phoenix Eggs", 1200),
    LegendaryPhoenixEgg10("LegendaryPhoenixEgg10", "10 Legendary Phoenix Eggs", 2250),
    LegendaryDragonEgg5("LegendaryDragonEgg5", "5 Legendary Dragon Eggs", 1200),
    LegendaryDragonEgg10("LegendaryDragonEgg10", "10 Legendary Dragon Eggs", 2250);

    public final String id;
    public final String displayName;
    public final int price;

    DonationRewardList(String id, String displayName, int price) {
        this.id = id;
        this.displayName = displayName;
        this.price = price;
    }
}
