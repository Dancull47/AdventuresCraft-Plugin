package monzter.adventurescraft.plugin.commands.dropTables.Lootboxes;

import me.lucko.helper.random.Weighted;
import monzter.adventurescraft.plugin.utilities.acUtils;

public enum Lootbox2 implements Weighted {
    //        .5 = 50%
    MoneyVoucher2(acUtils.uncommon + "Money Voucher", "VOUCHER", "MONEY_VOUCHER2", .5, 1),
    EXPVoucher2(acUtils.uncommon + "EXP Voucher", "VOUCHER", "EXP_VOUCHER2", .5, 1),
    PetEXPVoucher2(acUtils.uncommon + "Pet EXP Voucher", "VOUCHER", "PET_EXP_VOUCHER2", .5, 1),


    BreakingGem2(acUtils.uncommon + "Breaking Gem", "GEM_STONE", "BREAKING_GEM2", .30, 1),
    BlockGem2(acUtils.uncommon + "Block Gem", "GEM_STONE", "BLOCK_GEM2", .30, 1),

    PetEgg(acUtils.common + "Pet Egg", "PET", "PET_EGG", .2, 1),
    PetEgg2(acUtils.uncommon + "Pet Egg", "PET", "PET_EGG2", .2, 1),

    SellBooster(acUtils.common + "Sell Booster", "BOOSTER", "SELL_BOOSTER", .15, 1),
    EXPBooster(acUtils.common + "EXP Booster", "BOOSTER", "EXP_BOOSTER", .15, 1),
    PetEXPBooster(acUtils.common + "Pet EXP Booster", "BOOSTER", "PET_EXP_BOOSTER", .15, 1),


    BreakingGem3(acUtils.rare + "Breaking Gem", "GEM_STONE", "BREAKING_GEM3", .1, 1),
    BlockGem3(acUtils.rare + "Block Gem", "GEM_STONE", "BLOCK_GEM3", .1, 1),

    PetEgg3(acUtils.rare + "Pet Egg", "PET", "PET_EGG3", .02, 1),
    BreakingGem4(acUtils.legendary + "Breaking Gem", "GEM_STONE", "BREAKING_GEM4", .02, 1),
    BlockGem4(acUtils.legendary + "Block Gem", "GEM_STONE", "BLOCK_GEM4", .02, 1),

    PetEgg4(acUtils.legendary + "Pet Egg", "PET", "PET_EGG4", .002, 1);

    public final String displayName;
    public final String type;
    public final String id;
    public final double weight;
    public final int amount;

    Lootbox2(String displayName, String type, String id, double weight, int amount) {
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
