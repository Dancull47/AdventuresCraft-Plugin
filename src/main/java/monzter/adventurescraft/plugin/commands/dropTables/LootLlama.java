package monzter.adventurescraft.plugin.commands.dropTables;

import me.lucko.helper.random.Weighted;
import monzter.adventurescraft.plugin.utilities.Rarity;

public enum LootLlama implements Weighted {
    //        .5 = 50%
    Lootbox(Rarity.RARE + "Lootbox", "CONSUMABLE", "LOOTBOX3", .70),
    MoneyVoucher(Rarity.RARE + "Money Voucher", "VOUCHER", "MONEY_VOUCHER3", .65),
    LlamaGemStone(Rarity.COMMON + "Zebra Gem", "GEM_STONE", "LLAMA_GEM", .60),
    LlamaGemStone2(Rarity.UNCOMMON + "Zebra Gem", "GEM_STONE", "LLAMA_GEM2", .50),
    EXPVoucher(Rarity.LEGENDARY + "EXP Voucher", "VOUCHER", "EXP_VOUCHER4", .45),
    Lootbox2(Rarity.LEGENDARY + "Lootbox", "CONSUMABLE", "LOOTBOX4", .4),
    LlamaGemStone3(Rarity.RARE + "Zebra Gem", "GEM_STONE", "LLAMA_GEM3", .4),
    Lootbox3(Rarity.EXOTIC + "Lootbox", "CONSUMABLE", "LOOTBOX5", .25),
    LlamaGemStone4(Rarity.LEGENDARY + "Zebra Gem", "GEM_STONE", "LLAMA_GEM4", .25),
    PetEXPVoucher(Rarity.LEGENDARY + "Pet EXP Voucher", "VOUCHER", "PET_EXP_VOUCHER5", .1),
    LlamaGemStone5(Rarity.EXOTIC + "Zebra Gem", "GEM_STONE", "LLAMA_GEM5", .05);


    public final String displayName;
    public final String type;
    public final String id;
    public final double weight;

    LootLlama(String displayName, String type, String id, double weight) {
        this.id = id;
        this.type = type;
        this.displayName = displayName;
        this.weight = weight;
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
