package monzter.adventurescraft.plugin.commands.dropTables.Lootboxes;

import me.lucko.helper.random.Weighted;
import monzter.adventurescraft.plugin.utilities.acUtils;

public enum Lootbox6 implements Weighted {
    //        .5 = 50%
    MoneyVoucher6(acUtils.mythical + "Money Voucher", "VOUCHER", "MONEY_VOUCHER6", .3, 1),
    EXPVoucher6(acUtils.mythical + "EXP Voucher", "VOUCHER", "EXP_VOUCHER6", .3, 1),
    PetEXPVoucher6(acUtils.mythical + "Pet EXP Voucher", "VOUCHER", "PET_EXP_VOUCHER6", .3, 1),

    SellBooster5(acUtils.exotic + "Sell Booster", "BOOSTER", "SELL_BOOSTER5", .3, 1),
    EXPBooster5(acUtils.exotic + "EXP Booster", "BOOSTER", "EXP_BOOSTER5", .3, 1),
    PetEXPBooster5(acUtils.exotic + "Pet EXP Booster", "BOOSTER", "PET_EXP_BOOSTER5", .3, 1),

    PetEgg4(acUtils.legendary + "Pet Egg", "PET", "PET_EGG4", .25, 1),

    PetEgg5(acUtils.exotic + "Pet Egg", "PET", "PET_EGG5", .15, 1),

    LootBox6(acUtils.mythical + "Lootbox", "CONSUMABLE", "CONSUMABLE_LOOTBOX6", .15, 1),

    LootBox7(acUtils.godly + "Lootbox", "CONSUMABLE", "CONSUMABLE_LOOTBOX7", .1, 1);

    public final String displayName;
    public final String type;
    public final String id;
    public final double weight;
    public final int amount;

    Lootbox6(String displayName, String type, String id, double weight, int amount) {
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
