package monzter.adventurescraft.plugin.commands.dropTables.Lootboxes;

import me.lucko.helper.random.Weighted;
import monzter.adventurescraft.plugin.utilities.acUtils;

public enum Lootbox7 implements Weighted {
    //        .5 = 50%
    SellBooster6(acUtils.mythical + "Sell Booster", "BOOSTER", "SELL_BOOSTER6", .3, 1),
    EXPBooster6(acUtils.mythical + "EXP Booster", "BOOSTER", "EXP_BOOSTER6", .3, 1),
    PetEXPBooster6(acUtils.mythical + "Pet EXP Booster", "BOOSTER", "PET_EXP_BOOSTER6", .3, 1),

    PetEgg4(acUtils.legendary + "Pet Egg", "PET", "PET_EGG4", .3, 1),

    PetEgg5(acUtils.exotic + "Pet Egg", "PET", "PET_EGG5", .2, 1),

    LootBox6(acUtils.mythical + "Lootbox", "CONSUMABLE", "CONSUMABLE_LOOTBOX6", .15, 3),

    LootBox7(acUtils.godly + "Lootbox", "CONSUMABLE", "CONSUMABLE_LOOTBOX7", .1, 2),

    PetEgg6(acUtils.rare + "Pet Egg", "PET", "PET_EGG6", .01, 1),
    PetEgg7(acUtils.rare + "Pet Egg", "PET", "PET_EGG5", .01,1);

    public final String displayName;
    public final String type;
    public final String id;
    public final double weight;
    public final int amount;

    Lootbox7(String displayName, String type, String id, double weight, int amount) {
        this.id = id;
        this.type = type;
        this.displayName = displayName;
        this.weight = weight;
        this.amount = amount;
    }
    @Override
    public double getWeight() {
        return weight;
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }
}
