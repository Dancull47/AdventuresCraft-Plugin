package monzter.adventurescraft.plugin.network.PrisonGamemode.shared.dropTables;

import me.lucko.helper.random.Weighted;
import monzter.adventurescraft.plugin.utilities.enums.Rarity;
import net.Indyuce.mmoitems.MMOItems;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.stream.Collectors;

public enum Lootbox implements Weighted, ItemGenerator {
    // Common
    MoneyVoucherC(Rarity.COMMON,Rarity.COMMON,"Money Voucher", "VOUCHER", "MONEY_VOUCHER", .5, 1),
    EXPVoucherC(Rarity.COMMON,Rarity.COMMON,"EXP Voucher", "VOUCHER", "EXP_VOUCHER", .5, 1),
    PetEXPVoucherC(Rarity.COMMON,Rarity.COMMON,"Pet EXP Voucher", "VOUCHER", "PET_EXP_VOUCHER", .5, 1),
    BreakingGemC(Rarity.COMMON,Rarity.COMMON,"Breaking Gem", "GEM_STONE", "BREAKING_GEM", .5, 1),
    BlockGemC(Rarity.COMMON,Rarity.COMMON,"Block Gem", "GEM_STONE", "BLOCK_GEM", .5, 1),

    BreakingGem2C(Rarity.COMMON,Rarity.UNCOMMON,"Breaking Gem", "GEM_STONE", "BREAKING_GEM2", .25, 1),
    BlockGem2C(Rarity.COMMON,Rarity.UNCOMMON,"Block Gem", "GEM_STONE", "BLOCK_GEM2", .25, 1),

    PetEggC(Rarity.COMMON,Rarity.COMMON,"Pet Egg", "PET", "PET_EGG", .2, 1),

    SellBoosterC(Rarity.COMMON,Rarity.COMMON,"Sell Booster", "BOOSTER", "SELL_BOOSTER", .1, 1),
    EXPBoosterC(Rarity.COMMON,Rarity.COMMON,"EXP Booster", "BOOSTER", "EXP_BOOSTER", .1, 1),
    PetEXPBoosterC(Rarity.COMMON,Rarity.COMMON,"Pet EXP Booster", "BOOSTER", "PET_EXP_BOOSTER", .1, 1),
    PetEgg2C(Rarity.COMMON,Rarity.UNCOMMON,"Pet Egg", "PET", "PET_EGG2", .1, 1),

    BreakingGem3C(Rarity.COMMON,Rarity.RARE,"Breaking Gem", "GEM_STONE", "BREAKING_GEM3", .05, 1),
    BlockGem3C(Rarity.COMMON,Rarity.RARE,"Block Gem", "GEM_STONE", "BLOCK_GEM3", .05, 1),

    PetEgg3C(Rarity.COMMON,Rarity.RARE,"Pet Egg", "PET", "PET_EGG3", .01, 1),
    //  UNCOMMON
    MoneyVoucher2U(Rarity.UNCOMMON,Rarity.UNCOMMON,"Money Voucher", "VOUCHER", "MONEY_VOUCHER2", .5, 1),
    EXPVoucher2U(Rarity.UNCOMMON,Rarity.UNCOMMON,"EXP Voucher", "VOUCHER", "EXP_VOUCHER2", .5, 1),
    PetEXPVoucher2U(Rarity.UNCOMMON,Rarity.UNCOMMON,"Pet EXP Voucher", "VOUCHER", "PET_EXP_VOUCHER2", .5, 1),


    BreakingGem2U(Rarity.UNCOMMON,Rarity.UNCOMMON,"Breaking Gem", "GEM_STONE", "BREAKING_GEM2", .30, 1),
    BlockGem2U(Rarity.UNCOMMON,Rarity.UNCOMMON,"Block Gem", "GEM_STONE", "BLOCK_GEM2", .30, 1),

    PetEggU(Rarity.UNCOMMON,Rarity.COMMON,"Pet Egg", "PET", "PET_EGG", .2, 1),
    PetEgg2U(Rarity.UNCOMMON,Rarity.UNCOMMON,"Pet Egg", "PET", "PET_EGG2", .2, 1),

    SellBoosterU(Rarity.UNCOMMON,Rarity.COMMON,"Sell Booster", "BOOSTER", "SELL_BOOSTER", .15, 1),
    EXPBoosterU(Rarity.UNCOMMON,Rarity.COMMON,"EXP Booster", "BOOSTER", "EXP_BOOSTER", .15, 1),
    PetEXPBoosterU(Rarity.UNCOMMON,Rarity.COMMON,"Pet EXP Booster", "BOOSTER", "PET_EXP_BOOSTER", .15, 1),


    BreakingGem3U(Rarity.UNCOMMON,Rarity.RARE,"Breaking Gem", "GEM_STONE", "BREAKING_GEM3", .1, 1),
    BlockGem3U(Rarity.UNCOMMON,Rarity.RARE,"Block Gem", "GEM_STONE", "BLOCK_GEM3", .1, 1),

    PetEgg3U(Rarity.UNCOMMON,Rarity.RARE,"Pet Egg", "PET", "PET_EGG3", .02, 1),
    BreakingGem4U(Rarity.UNCOMMON,Rarity.LEGENDARY,"Breaking Gem", "GEM_STONE", "BREAKING_GEM4", .02, 1),
    BlockGem4U(Rarity.UNCOMMON,Rarity.LEGENDARY,"Block Gem", "GEM_STONE", "BLOCK_GEM4", .02, 1),

    PetEgg4U(Rarity.UNCOMMON,Rarity.LEGENDARY,"Pet Egg", "PET", "PET_EGG4", .002, 1),
    //  RARE
    MoneyVoucher3R(Rarity.RARE,Rarity.RARE,"Money Voucher", "VOUCHER", "MONEY_VOUCHER3", .5, 1),
    EXPVoucher3R(Rarity.RARE,Rarity.RARE,"EXP Voucher", "VOUCHER", "EXP_VOUCHER3", .5, 1),
    PetEXPVoucher3R(Rarity.RARE,Rarity.RARE,"Pet EXP Voucher", "VOUCHER", "PET_EXP_VOUCHER3", .5, 1),


    BreakingGem2R(Rarity.RARE,Rarity.UNCOMMON,"Breaking Gem", "GEM_STONE", "BREAKING_GEM2", .25, 1),
    BlockGem2R(Rarity.RARE,Rarity.UNCOMMON,"Block Gem", "GEM_STONE", "BLOCK_GEM2", .25, 1),

    SellBooster2R(Rarity.RARE,Rarity.UNCOMMON,"Sell Booster", "BOOSTER", "SELL_BOOSTER2", .15, 1),
    EXPBooster2R(Rarity.RARE,Rarity.UNCOMMON,"EXP Booster", "BOOSTER", "EXP_BOOSTER2", .15, 1),
    PetEXPBooster2R(Rarity.RARE,Rarity.UNCOMMON,"Pet EXP Booster", "BOOSTER", "PET_EXP_BOOSTER2", .15, 1),

    BreakingGem3R(Rarity.RARE,Rarity.RARE,"Breaking Gem", "GEM_STONE", "BREAKING_GEM3", .15, 1),
    BlockGem3R(Rarity.RARE,Rarity.RARE,"Block Gem", "GEM_STONE", "BLOCK_GEM3", .15, 1),

    PetEgg2R(Rarity.RARE,Rarity.UNCOMMON,"Pet Egg", "PET", "PET_EGG2", .15, 1),
    PetEgg3R(Rarity.RARE,Rarity.RARE,"Pet Egg", "PET", "PET_EGG3", .15, 1),

    BreakingGem4R(Rarity.RARE,Rarity.LEGENDARY,"Breaking Gem", "GEM_STONE", "BREAKING_GEM4", .05, 1),
    BlockGem4R(Rarity.RARE,Rarity.LEGENDARY,"Block Gem", "GEM_STONE", "BLOCK_GEM4", .05, 1),

    BreakingGem5R(Rarity.RARE,Rarity.LEGENDARY,"Breaking Gem", "GEM_STONE", "BREAKING_GEM5", .01, 1),
    BlockGem5R(Rarity.RARE,Rarity.EXOTIC,"Block Gem", "GEM_STONE", "BLOCK_GEM5", .01, 1),
    PetEgg4R(Rarity.RARE,Rarity.EXOTIC,"Pet Egg", "PET", "PET_EGG4", .01, 1),
    // LEGENDARY
    MoneyVoucher4L(Rarity.LEGENDARY,Rarity.LEGENDARY,"Money Voucher", "VOUCHER", "MONEY_VOUCHER4", .5, 1),
    EXPVoucher4L(Rarity.LEGENDARY,Rarity.LEGENDARY,"EXP Voucher", "VOUCHER", "EXP_VOUCHER4", .5, 1),
    PetEXPVoucher4L(Rarity.LEGENDARY,Rarity.LEGENDARY,"Pet EXP Voucher", "VOUCHER", "PET_EXP_VOUCHER4", .5, 1),

    PetEgg3L(Rarity.LEGENDARY,Rarity.RARE,"Pet Egg", "PET", "PET_EGG3", .25, 1),

    BreakingGem4L(Rarity.LEGENDARY,Rarity.LEGENDARY,"Breaking Gem", "GEM_STONE", "BREAKING_GEM4", .2, 1),

    SellBooster3L(Rarity.LEGENDARY,Rarity.RARE,"Sell Booster", "BOOSTER", "SELL_BOOSTER3", .15, 1),
    EXPBooster3L(Rarity.LEGENDARY,Rarity.RARE,"EXP Booster", "BOOSTER", "EXP_BOOSTER3", .15, 1),
    PetEXPBooster3L(Rarity.LEGENDARY,Rarity.RARE,"Pet EXP Booster", "BOOSTER", "PET_EXP_BOOSTER3", .15, 1),

    BreakingGem5L(Rarity.LEGENDARY,Rarity.EXOTIC,"Breaking Gem", "GEM_STONE", "BREAKING_GEM5", .1, 1),

    BlockGem4L(Rarity.LEGENDARY,Rarity.LEGENDARY,"Block Gem", "GEM_STONE", "BLOCK_GEM4", .075, 1),

    PetEgg4L(Rarity.LEGENDARY,Rarity.LEGENDARY,"Pet Egg", "PET", "PET_EGG4", .05, 1),

    BreakingGem6L(Rarity.LEGENDARY,Rarity.MYTHICAL,"Breaking Gem", "GEM_STONE", "BREAKING_GEM6", .03, 1),

    BlockGem5L(Rarity.LEGENDARY,Rarity.EXOTIC,"Block Gem", "GEM_STONE", "BLOCK_GEM5", .02, 1),

    PetEgg5L(Rarity.LEGENDARY,Rarity.EXOTIC,"Pet Egg", "PET", "PET_EGG5", .01, 1),
    BlockGem6L(Rarity.LEGENDARY,Rarity.MYTHICAL,"Block Gem", "GEM_STONE", "BLOCK_GEM6", .01, 1),
    LootBox4L(Rarity.LEGENDARY,Rarity.LEGENDARY,"Lootbox", "CONSUMABLE", "LOOTBOX4", .01, 1),

    LootBox5L(Rarity.LEGENDARY,Rarity.EXOTIC,"Lootbox", "CONSUMABLE", "LOOTBOX5", .001, 1),
    //  EXOTIC
    MoneyVoucher5E(Rarity.EXOTIC,Rarity.EXOTIC,"Money Voucher", "VOUCHER", "MONEY_VOUCHER5", .5, 1),
    EXPVoucher5E(Rarity.EXOTIC,Rarity.EXOTIC,"EXP Voucher", "VOUCHER", "EXP_VOUCHER5", .5, 1),
    PetEXPVoucher5E(Rarity.EXOTIC,Rarity.EXOTIC,"Pet EXP Voucher", "VOUCHER", "PET_EXP_VOUCHER5", .5, 1),

    PetEgg3E(Rarity.EXOTIC,Rarity.RARE,"Pet Egg", "PET", "PET_EGG3", .25, 1),

    BreakingGem5E(Rarity.EXOTIC,Rarity.EXOTIC,"Breaking Gem", "GEM_STONE", "BREAKING_GEM5", .25, 1),
    BlockGem5E(Rarity.EXOTIC,Rarity.EXOTIC,"Block Gem", "GEM_STONE", "BLOCK_GEM5", .25, 1),

    SellBooster4E(Rarity.EXOTIC,Rarity.LEGENDARY,"Sell Booster", "BOOSTER", "SELL_BOOSTER4", .15, 1),
    EXPBooster4E(Rarity.EXOTIC,Rarity.LEGENDARY,"EXP Booster", "BOOSTER", "EXP_BOOSTER4", .15, 1),
    PetEXPBooster4E(Rarity.EXOTIC,Rarity.LEGENDARY,"Pet EXP Booster", "BOOSTER", "PET_EXP_BOOSTER4", .15, 1),

    PetEgg4E(Rarity.EXOTIC,Rarity.LEGENDARY,"Pet Egg", "PET", "PET_EGG4", .15, 1),

    BreakingGem6E(Rarity.EXOTIC,Rarity.MYTHICAL,"Breaking Gem", "GEM_STONE", "BREAKING_GEM6", .15, 1),
    BlockGem6E(Rarity.EXOTIC,Rarity.MYTHICAL,"Block Gem", "GEM_STONE", "BLOCK_GEM6", .15, 1),

    PetEgg5E(Rarity.EXOTIC,Rarity.EXOTIC,"Pet Egg", "PET", "PET_EGG5", .05, 1),

    LootBox5E(Rarity.EXOTIC,Rarity.EXOTIC,"Lootbox", "CONSUMABLE", "LOOTBOX5", .05, 5),

    BreakingGem7E(Rarity.EXOTIC,Rarity.GODLY,"Breaking Gem", "GEM_STONE", "BREAKING_GEM7", .01, 1),
    BlockGem7E(Rarity.EXOTIC,Rarity.GODLY,"Block Gem", "GEM_STONE", "BLOCK_GEM7", .01, 1),

    LootBox6E(Rarity.EXOTIC,Rarity.MYTHICAL,"Lootbox", "CONSUMABLE", "LOOTBOX6", .002, 1),

    LootBox7E(Rarity.EXOTIC,Rarity.GODLY,"Lootbox", "CONSUMABLE", "LOOTBOX7", .0001, 1),
    // MYTHICAL
    MoneyVoucher6M(Rarity.MYTHICAL,Rarity.MYTHICAL,"Money Voucher", "VOUCHER", "MONEY_VOUCHER6", .3, 1),
    EXPVoucher6M(Rarity.MYTHICAL,Rarity.MYTHICAL,"EXP Voucher", "VOUCHER", "EXP_VOUCHER6", .3, 1),
    PetEXPVoucher6M(Rarity.MYTHICAL,Rarity.MYTHICAL,"Pet EXP Voucher", "VOUCHER", "PET_EXP_VOUCHER6", .3, 1),

    SellBooster5M(Rarity.MYTHICAL,Rarity.EXOTIC,"Sell Booster", "BOOSTER", "SELL_BOOSTER5", .3, 1),
    EXPBooster5M(Rarity.MYTHICAL,Rarity.EXOTIC,"EXP Booster", "BOOSTER", "EXP_BOOSTER5", .3, 1),
    PetEXPBooster5M(Rarity.MYTHICAL,Rarity.EXOTIC,"Pet EXP Booster", "BOOSTER", "PET_EXP_BOOSTER5", .3, 1),

    PetEgg4M(Rarity.MYTHICAL,Rarity.LEGENDARY,"Pet Egg", "PET", "PET_EGG4", .25, 1),

    PetEgg5M(Rarity.MYTHICAL,Rarity.EXOTIC,"Pet Egg", "PET", "PET_EGG5", .15, 1),

    LootBox6M(Rarity.MYTHICAL,Rarity.MYTHICAL,"Lootbox", "CONSUMABLE", "LOOTBOX6", .15, 1),

    LootBox7M(Rarity.MYTHICAL,Rarity.GODLY,"Lootbox", "CONSUMABLE", "LOOTBOX7", .1, 1),
    // GODLY
    SellBooster6G(Rarity.GODLY,Rarity.MYTHICAL,"Sell Booster", "BOOSTER", "SELL_BOOSTER6", .3, 1),
    EXPBooster6G(Rarity.GODLY,Rarity.MYTHICAL,"EXP Booster", "BOOSTER", "EXP_BOOSTER6", .3, 1),
    PetEXPBooster6G(Rarity.GODLY,Rarity.MYTHICAL,"Pet EXP Booster", "BOOSTER", "PET_EXP_BOOSTER6", .3, 1),

    PetEgg4G(Rarity.GODLY,Rarity.LEGENDARY,"Pet Egg", "PET", "PET_EGG4", .3, 1),

    PetEgg5G(Rarity.GODLY,Rarity.EXOTIC,"Pet Egg", "PET", "PET_EGG5", .2, 1),

    LootBox6G(Rarity.GODLY,Rarity.MYTHICAL,"Lootbox", "CONSUMABLE", "LOOTBOX6", .15, 3),

    LootBox7G(Rarity.GODLY,Rarity.GODLY,"Lootbox", "CONSUMABLE", "LOOTBOX7", .1, 2),

    PetEgg6G(Rarity.GODLY,Rarity.RARE,"Pet Egg", "PET", "PET_EGG6", .01, 1),
    PetEgg7G(Rarity.GODLY,Rarity.RARE,"Pet Egg", "PET", "PET_EGG7", .01,1);
    
    private static final Map<Rarity, List<Lootbox>> RARITY_LISTS = new EnumMap<>(Rarity.class);

    public static List<Lootbox> getLootbox(Rarity rarity) {
        return RARITY_LISTS.computeIfAbsent(rarity, key -> Arrays.stream(values())
                .filter(lootbox -> lootbox.getLootboxRarity() == key)
                .collect(Collectors.toList()));
    }

    private final String displayName;

    private final String type;
    private final Rarity lootboxRarity;
    private final Rarity lootRarity;
    private final String id;
    private final double weight;
    private final int amount;

    Lootbox(Rarity lootboxRarity, Rarity lootRarity, String displayName, String type, String id, double weight, int amount) {
        this.lootboxRarity = lootboxRarity;
        this.lootRarity = lootRarity;
        this.id = id;
        this.type = type;
        this.displayName = lootRarity.getColorString() + displayName;
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

    public int getAmount() {
        return amount;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Rarity getPetRarity() {
        return lootRarity;
    }

    public Rarity getLootboxRarity() {
        return lootboxRarity;
    }

    @Override
    public ItemStack generateItem() {
        final ItemStack itemStack = MMOItems.plugin.getItem(type, id);
        if (itemStack != null) {
            List<Component> lore = itemStack.lore();
            if (lore == null) {
                lore = new ArrayList<>();
            } else if (!lore.isEmpty()) {
                lore.add(Component.empty());
            }
            lore.add(Component.text("CHANCE: " + weight * 10 + "%", NamedTextColor.GOLD, TextDecoration.BOLD));
            itemStack.lore(lore);
            return itemStack.asQuantity(amount);
        }
        return null;
    }

    @Override
    public ItemStack generateItem(Player player) {
        return null;
    }
}
