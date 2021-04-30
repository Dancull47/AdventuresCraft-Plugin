package monzter.adventurescraft.plugin.commands.dropTables.Lootboxes;

import me.lucko.helper.random.Weighted;
import monzter.adventurescraft.plugin.utilities.acUtils;

public enum Lootbox implements Weighted {
    //        .5 = 50%
    MoneyVoucher(acUtils.common + "Money Voucher", "VOUCHER", "MONEY_VOUCHER", .5, 1),
    EXPVoucher(acUtils.common + "EXP Voucher", "VOUCHER", "EXP_VOUCHER", .5, 1),
    PetEXPVoucher(acUtils.common + "Pet EXP Voucher", "VOUCHER", "PET_EXP_VOUCHER", .5, 1),
    BreakingGem(acUtils.common + "Breaking Gem", "GEM_STONE", "BREAKING_GEM", .5, 1),
    BlockGem(acUtils.common + "Block Gem", "GEM_STONE", "BLOCK_GEM", .5, 1),

    BreakingGem2(acUtils.uncommon + "Breaking Gem", "GEM_STONE", "BREAKING_GEM2", .25, 1),
    BlockGem2(acUtils.uncommon + "Block Gem", "GEM_STONE", "BLOCK_GEM2", .25, 1),

    PetEgg(acUtils.common + "Pet Egg", "PET", "PET_EGG", .2, 1),

    SellBooster(acUtils.common + "Sell Booster", "BOOSTER", "SELL_BOOSTER", .1, 1),
    EXPBooster(acUtils.common + "EXP Booster", "BOOSTER", "EXP_BOOSTER", .1, 1),
    PetEXPBooster(acUtils.common + "Pet EXP Booster", "BOOSTER", "PET_EXP_BOOSTER", .1, 1),
    PetEgg2(acUtils.uncommon + "Pet Egg", "PET", "PET_EGG2", .1, 1),

    BreakingGem3(acUtils.rare + "Breaking Gem", "GEM_STONE", "BREAKING_GEM3", .05, 1),
    BlockGem3(acUtils.rare + "Block Gem", "GEM_STONE", "BLOCK_GEM3", .05, 1),

    PetEgg3(acUtils.rare + "Pet Egg", "PET", "PET_EGG3", .01, 1);

    public final String displayName;
    public final String type;
    public final String id;
    public final double weight;
    public final int amount;

    Lootbox(String displayName, String type, String id, double weight, int amount) {
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
