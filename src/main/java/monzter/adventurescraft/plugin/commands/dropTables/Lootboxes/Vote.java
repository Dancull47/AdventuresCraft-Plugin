package monzter.adventurescraft.plugin.commands.dropTables.Lootboxes;

import me.lucko.helper.random.Weighted;
import monzter.adventurescraft.plugin.utilities.acUtils;

public enum Vote implements Weighted {
    //        .5 = 50%
    MoneyVoucher2(acUtils.uncommon + "Money Voucher", "VOUCHER", "MONEY_VOUCHER2", .5, 1),
    EXPVoucher2(acUtils.uncommon + "EXP Voucher", "VOUCHER", "EXP_VOUCHER2", .5, 1),
    PetEXPVoucher2(acUtils.uncommon + "Pet EXP Voucher", "VOUCHER", "PET_EXP_VOUCHER2", .5, 1),

    SellBooster2(acUtils.uncommon + "Sell Booster", "BOOSTER", "SELL_BOOSTER2", .15, 1),
    EXPBooster2(acUtils.uncommon + "EXP Booster", "BOOSTER", "EXP_BOOSTER2", .15, 1),
    PetEXPBooster2(acUtils.uncommon + "Pet EXP Booster", "BOOSTER", "PET_EXP_BOOSTER2", .15, 1),

    PetEgg2(acUtils.uncommon + "Pet Egg", "PET", "PET_EGG2", .15, 1),

    PetEgg3(acUtils.rare + "Pet Egg", "PET", "PET_EGG3", .1, 1),

    PetEgg4(acUtils.legendary + "Pet Egg", "PET", "PET_EGG4", .05, 1),

    LootBox5(acUtils.exotic + "LootBox", "CONSUMABLE", "CONSUMABLE_LOOTBOX5", .01, 1);

    public final String displayName;
    public final String type;
    public final String id;
    public final double weight;
    public final int amount;

    Vote(String displayName, String type, String id, double weight, int amount) {
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
