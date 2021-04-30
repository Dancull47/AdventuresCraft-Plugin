package monzter.adventurescraft.plugin.commands.dropTables.Lootboxes;

import me.lucko.helper.random.Weighted;
import monzter.adventurescraft.plugin.utilities.acUtils;

public enum Lootbox4 implements Weighted {
    //        .5 = 50%
    MoneyVoucher4(acUtils.legendary + "Money Voucher", "VOUCHER", "MONEY_VOUCHER4", .5, 1),
    EXPVoucher4(acUtils.legendary + "EXP Voucher", "VOUCHER", "EXP_VOUCHER4", .5, 1),
    PetEXPVoucher4(acUtils.legendary + "Pet EXP Voucher", "VOUCHER", "PET_EXP_VOUCHER4", .5, 1),

    PetEgg3(acUtils.rare + "Pet Egg", "PET", "PET_EGG3", .25, 1),

    BreakingGem4(acUtils.legendary + "Breaking Gem", "GEM_STONE", "BREAKING_GEM4", .2, 1),

    SellBooster3(acUtils.rare + "Sell Booster", "BOOSTER", "SELL_BOOSTER3", .15, 1),
    EXPBooster3(acUtils.rare + "EXP Booster", "BOOSTER", "EXP_BOOSTER3", .15, 1),
    PetEXPBooster3(acUtils.rare + "Pet EXP Booster", "BOOSTER", "PET_EXP_BOOSTER3", .15, 1),

    BreakingGem5(acUtils.exotic + "Breaking Gem", "GEM_STONE", "BREAKING_GEM5", .1, 1),

    BlockGem4(acUtils.legendary + "Block Gem", "GEM_STONE", "BLOCK_GEM4", .075, 1),

    PetEgg4(acUtils.legendary + "Pet Egg", "PET", "PET_EGG4", .05, 1),

    BreakingGem6(acUtils.mythical + "Breaking Gem", "GEM_STONE", "BREAKING_GEM6", .03, 1),

    BlockGem5(acUtils.exotic + "Block Gem", "GEM_STONE", "BLOCK_GEM5", .02, 1),

    PetEgg5(acUtils.exotic + "Pet Egg", "PET", "PET_EGG5", .01, 1),
    BlockGem6(acUtils.mythical + "Block Gem", "GEM_STONE", "BLOCK_GEM6", .01, 1),
    LootBox4(acUtils.legendary + "Lootbox", "CONSUMABLE", "CONSUMABLE_LOOTBOX4", .01, 1),

    LootBox5(acUtils.exotic + "Lootbox", "CONSUMABLE", "CONSUMABLE_LOOTBOX5", .001, 1);

    public final String displayName;
    public final String type;
    public final String id;
    public final double weight;
    public final int amount;

    Lootbox4(String displayName, String type, String id, double weight, int amount) {
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
