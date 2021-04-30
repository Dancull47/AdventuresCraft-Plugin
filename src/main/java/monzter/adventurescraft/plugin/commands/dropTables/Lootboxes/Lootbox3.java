package monzter.adventurescraft.plugin.commands.dropTables.Lootboxes;

import me.lucko.helper.random.Weighted;
import monzter.adventurescraft.plugin.utilities.acUtils;

public enum Lootbox3 implements Weighted {
    //        .5 = 50%
    MoneyVoucher3(acUtils.rare + "Money Voucher", "VOUCHER", "MONEY_VOUCHER3", .5, 1),
    EXPVoucher3(acUtils.rare + "EXP Voucher", "VOUCHER", "EXP_VOUCHER3", .5, 1),
    PetEXPVoucher3(acUtils.rare + "Pet EXP Voucher", "VOUCHER", "PET_EXP_VOUCHER3", .5, 1),


    BreakingGem2(acUtils.uncommon + "Breaking Gem", "GEM_STONE", "BREAKING_GEM2", .25, 1),
    BlockGem2(acUtils.uncommon + "Block Gem", "GEM_STONE", "BLOCK_GEM2", .25, 1),

    SellBooster2(acUtils.uncommon + "Sell Booster", "BOOSTER", "SELL_BOOSTER2", .15, 1),
    EXPBooster2(acUtils.uncommon + "EXP Booster", "BOOSTER", "EXP_BOOSTER2", .15, 1),
    PetEXPBooster2(acUtils.uncommon + "Pet EXP Booster", "BOOSTER", "PET_EXP_BOOSTER2", .15, 1),

    BreakingGem3(acUtils.rare + "Breaking Gem", "GEM_STONE", "BREAKING_GEM3", .15, 1),
    BlockGem3(acUtils.rare + "Block Gem", "GEM_STONE", "BLOCK_GEM3", .15, 1),

    PetEgg2(acUtils.uncommon + "Pet Egg", "PET", "PET_EGG2", .15, 1),
    PetEgg3(acUtils.rare + "Pet Egg", "PET", "PET_EGG3", .15, 1),

    BreakingGem4(acUtils.legendary + "Breaking Gem", "GEM_STONE", "BREAKING_GEM4", .05, 1),
    BlockGem4(acUtils.legendary + "Block Gem", "GEM_STONE", "BLOCK_GEM4", .05, 1),

    BreakingGem5(acUtils.legendary + "Breaking Gem", "GEM_STONE", "BREAKING_GEM5", .01, 1),
    BlockGem5(acUtils.exotic + "Block Gem", "GEM_STONE", "BLOCK_GEM5", .01, 1),
    PetEgg4(acUtils.exotic + "Pet Egg", "PET", "PET_EGG4", .01, 1);

    public final String displayName;
    public final String type;
    public final String id;
    public final double weight;
    public final int amount;

    Lootbox3(String displayName, String type, String id, double weight, int amount) {
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
