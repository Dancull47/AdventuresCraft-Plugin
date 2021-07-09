package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.mainMenu.donation;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public enum DonationItemList {

    SkinsITEM1(DonationShops.SKINS, 200, 1, "SKIN", "GREEN_UNDEAD_SWORD3"),
    SkinsITEM2(DonationShops.SKINS, 200, 1, "SKIN", "BLUE_UNDEAD_SWORD3"),
    SkinsITEM3(DonationShops.SKINS, 200, 1, "SKIN", "BROWN_UNDEAD_SWORD3"),
    SkinsITEM4(DonationShops.SKINS, 200, 1, "SKIN", "YELLOW_UNDEAD_SWORD3"),
    SkinsITEM5(DonationShops.SKINS, 200, 1, "SKIN", "RED_UNDEAD_SWORD3"),
    SkinsITEM6(DonationShops.SKINS, 400, 1, "SKIN", "UNDEAD_SOUL_SPLITTER_SWORD4"),
    SkinsITEM7(DonationShops.SKINS, 400, 1, "SKIN", "UNDEAD_SERPENT_STAFF4"),
    SkinsITEM8(DonationShops.SKINS, 1_000, 1, "SKIN", "HOLY_RISER_BLADE5"),

    FARMINGBoostersITEM1(DonationShops.BOOSTERS, 100, 1, "BOOSTER", "FARMING_BOOSTER1"),
    FARMINGBoostersITEM2(DonationShops.BOOSTERS, 200, 1, "BOOSTER", "FARMING_BOOSTER2"),
    FARMINGBoostersITEM3(DonationShops.BOOSTERS, 300, 1, "BOOSTER", "FARMING_BOOSTER3"),
    FARMINGBoostersITEM4(DonationShops.BOOSTERS, 400, 1, "BOOSTER", "FARMING_BOOSTER4"),
    FARMINGBoostersITEM5(DonationShops.BOOSTERS, 500, 1, "BOOSTER", "FARMING_BOOSTER5"),
    MININGBoostersITEM1(DonationShops.BOOSTERS, 100, 1, "BOOSTER", "MINING_BOOSTER1"),
    MININGBoostersITEM2(DonationShops.BOOSTERS, 200, 1, "BOOSTER", "MINING_BOOSTER2"),
    MININGBoostersITEM3(DonationShops.BOOSTERS, 300, 1, "BOOSTER", "MINING_BOOSTER3"),
    MININGBoostersITEM4(DonationShops.BOOSTERS, 400, 1, "BOOSTER", "MINING_BOOSTER4"),
    MININGBoostersITEM5(DonationShops.BOOSTERS, 500, 1, "BOOSTER", "MINING_BOOSTER5"),
    FORAGINGBoostersITEM1(DonationShops.BOOSTERS, 100, 1, "BOOSTER", "FORAGING_BOOSTER1"),
    FORAGINGBoostersITEM2(DonationShops.BOOSTERS, 200, 1, "BOOSTER", "FORAGING_BOOSTER2"),
    FORAGINGBoostersITEM3(DonationShops.BOOSTERS, 300, 1, "BOOSTER", "FORAGING_BOOSTER3"),
    FORAGINGBoostersITEM4(DonationShops.BOOSTERS, 400, 1, "BOOSTER", "FORAGING_BOOSTER4"),
    FORAGINGBoostersITEM5(DonationShops.BOOSTERS, 500, 1, "BOOSTER", "FORAGING_BOOSTER5"),
    SLAYERBoostersITEM1(DonationShops.BOOSTERS, 100, 1, "BOOSTER", "SLAYER_BOOSTER1"),
    SLAYERBoostersITEM2(DonationShops.BOOSTERS, 200, 1, "BOOSTER", "SLAYER_BOOSTER2"),
    SLAYERBoostersITEM3(DonationShops.BOOSTERS, 300, 1, "BOOSTER", "SLAYER_BOOSTER3"),
    SLAYERBoostersITEM4(DonationShops.BOOSTERS, 400, 1, "BOOSTER", "SLAYER_BOOSTER4"),
    SLAYERBoostersITEM5(DonationShops.BOOSTERS, 500, 1, "BOOSTER", "SLAYER_BOOSTER5"),
    ENCHANTINGBoostersITEM1(DonationShops.BOOSTERS, 100, 1, "BOOSTER", "ENCHANTING_BOOSTER1"),
    ENCHANTINGBoostersITEM2(DonationShops.BOOSTERS, 200, 1, "BOOSTER", "ENCHANTING_BOOSTER2"),
    ENCHANTINGBoostersITEM3(DonationShops.BOOSTERS, 300, 1, "BOOSTER", "ENCHANTING_BOOSTER3"),
    ENCHANTINGBoostersITEM4(DonationShops.BOOSTERS, 400, 1, "BOOSTER", "ENCHANTING_BOOSTER4"),
    ENCHANTINGBoostersITEM5(DonationShops.BOOSTERS, 500, 1, "BOOSTER", "ENCHANTING_BOOSTER5"),
    SPELLFORGINGBoostersITEM1(DonationShops.BOOSTERS, 100, 1, "BOOSTER", "SPELLFORGING_BOOSTER1"),
    SPELLFORGINGBoostersITEM2(DonationShops.BOOSTERS, 200, 1, "BOOSTER", "SPELLFORGING_BOOSTER2"),
    SPELLFORGINGBoostersITEM3(DonationShops.BOOSTERS, 300, 1, "BOOSTER", "SPELLFORGING_BOOSTER3"),
    SPELLFORGINGBoostersITEM4(DonationShops.BOOSTERS, 400, 1, "BOOSTER", "SPELLFORGING_BOOSTER4"),
    SPELLFORGINGBoostersITEM5(DonationShops.BOOSTERS, 500, 1, "BOOSTER", "SPELLFORGING_BOOSTER5"),
    COOKINGBoostersITEM1(DonationShops.BOOSTERS, 100, 1, "BOOSTER", "COOKING_BOOSTER1"),
    COOKINGBoostersITEM2(DonationShops.BOOSTERS, 200, 1, "BOOSTER", "COOKING_BOOSTER2"),
    COOKINGBoostersITEM3(DonationShops.BOOSTERS, 300, 1, "BOOSTER", "COOKING_BOOSTER3"),
    COOKINGBoostersITEM4(DonationShops.BOOSTERS, 400, 1, "BOOSTER", "COOKING_BOOSTER4"),
    COOKINGBoostersITEM5(DonationShops.BOOSTERS, 500, 1, "BOOSTER", "COOKING_BOOSTER5"),

    CratesITEM1(DonationShops.CRATES, 100, 64, "CONSUMABLE", "HELL_BOX5"),
    CratesITEM2(DonationShops.CRATES, 100, 64, "CONSUMABLE", "UNDEAD_BOX5"),
    CratesITEM3(DonationShops.CRATES, 100, 64, "CONSUMABLE", "PROFESSION_BOOSTER_BOX5"),
    ;


    private static final Map<DonationShops, List<DonationItemList>> SHOPS_LIST_MAP = new EnumMap<>(DonationShops.class);

    public static List<DonationItemList> getShop(DonationShops shops) {
        return SHOPS_LIST_MAP.computeIfAbsent(shops, key -> Arrays.stream(values())
                .filter(shop -> shop.getShop().equals(key))
                .collect(Collectors.toList()));
    }


    private DonationShops shop;
    private int acPrice;
    private int maxPurchaseAmount;
    private String type;
    private String ID;

    DonationItemList(DonationShops shop, int price, int maxPurchaseAmount, String type, String id) {
        this.shop = shop;
        this.acPrice = price;
        this.maxPurchaseAmount = maxPurchaseAmount;
        this.type = type;
        this.ID = id;
    }

    public int getAcPrice() {
        return acPrice;
    }

    public String getType() {
        return type;
    }

    public String getID() {
        return ID;
    }

    public DonationShops getShop() {
        return shop;
    }

    public int getMaxPurchaseAmount() {
        return maxPurchaseAmount;
    }
}
