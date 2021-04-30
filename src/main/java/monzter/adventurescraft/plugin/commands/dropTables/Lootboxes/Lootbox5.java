package monzter.adventurescraft.plugin.commands.dropTables.Lootboxes;

import me.lucko.helper.random.Weighted;
import monzter.adventurescraft.plugin.utilities.acUtils;

public enum Lootbox5 implements Weighted {
    //        .5 = 50%
    MoneyVoucher5(acUtils.exotic + "Money Voucher", "VOUCHER", "MONEY_VOUCHER5", .5, 1),
    EXPVoucher5(acUtils.exotic + "EXP Voucher", "VOUCHER", "EXP_VOUCHER5", .5, 1),
    PetEXPVoucher5(acUtils.exotic + "Pet EXP Voucher", "VOUCHER", "PET_EXP_VOUCHER5", .5, 1),

    PetEgg3(acUtils.rare + "Pet Egg", "PET", "PET_EGG3", .25, 1),

    BreakingGem5(acUtils.exotic + "Breaking Gem", "GEM_STONE", "BREAKING_GEM5", .25, 1),
    BlockGem5(acUtils.exotic + "Block Gem", "GEM_STONE", "BLOCK_GEM5", .25, 1),

    SellBooster4(acUtils.legendary + "Sell Booster", "BOOSTER", "SELL_BOOSTER4", .15, 1),
    EXPBooster4(acUtils.legendary + "EXP Booster", "BOOSTER", "EXP_BOOSTER4", .15, 1),
    PetEXPBooster4(acUtils.legendary + "Pet EXP Booster", "BOOSTER", "PET_EXP_BOOSTER4", .15, 1),

    PetEgg4(acUtils.legendary + "Pet Egg", "PET", "PET_EGG4", .15, 1),

    BreakingGem6(acUtils.mythical + "Breaking Gem", "GEM_STONE", "BREAKING_GEM6", .15, 1),
    BlockGem6(acUtils.mythical + "Block Gem", "GEM_STONE", "BLOCK_GEM6", .15, 1),

    PetEgg5(acUtils.exotic + "Pet Egg", "PET", "PET_EGG5", .05, 1),

    LootBox5(acUtils.exotic + "Lootbox", "CONSUMABLE", "CONSUMABLE_LOOTBOX5", .05, 5),

    BreakingGem7(acUtils.godly + "Breaking Gem", "GEM_STONE", "BREAKING_GEM7", .01, 1),
    BlockGem7(acUtils.godly + "Block Gem", "GEM_STONE", "BLOCK_GEM7", .01, 1),

    LootBox6(acUtils.mythical + "Lootbox", "CONSUMABLE", "CONSUMABLE_LOOTBOX6", .002, 1),

    LootBox7(acUtils.godly + "Lootbox", "CONSUMABLE", "CONSUMABLE_LOOTBOX7", .0001, 1);

    public final String displayName;
    public final String type;
    public final String id;
    public final double weight;
    public final int amount;

    Lootbox5(String displayName, String type, String id, double weight, int amount) {
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
