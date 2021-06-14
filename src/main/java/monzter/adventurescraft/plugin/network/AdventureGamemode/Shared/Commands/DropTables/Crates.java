package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.Commands.DropTables;

import me.lucko.helper.random.Weighted;
import monzter.adventurescraft.plugin.network.PrisonGamemode.shared.dropTables.ItemGenerator;
import monzter.adventurescraft.plugin.utilities.enums.CrateList;
import net.Indyuce.mmoitems.MMOItems;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.stream.Collectors;

public enum Crates implements Weighted, ItemGenerator {
    HELL2(CrateList.HELL, "SKIN", "RUSTED_SWORD3", 1, .20),
    HELL8(CrateList.HELL, "SKIN", "RUSTED_BATTLE_AXE3", 1, .20),
    HELL9(CrateList.HELL, "SKIN", "MAGMA_BATTLE_AXE3", 1, .20),
    HELL11(CrateList.HELL, "SKIN", "MAGMA_STAFF3", 1, .20),
    HELL12(CrateList.HELL, "SKIN", "RUSTED_STAFF3", 1, .20),
    HELL13(CrateList.HELL, "SKIN", "RUSTED_STAFF3_2", 1, .20),
    HELL1(CrateList.HELL, "SKIN", "HELL_BRINGER_SWORD4", 1, .10),
    HELL3(CrateList.HELL, "SKIN", "MAGMA_BLADE4", 1, .10),
    HELL6(CrateList.HELL, "SKIN", "HELL_BRINGER_BATTLE_AXE4", 1, .10),
    HELL7(CrateList.HELL, "SKIN", "HELL_BRINGER_BATTLE_AXE4_2", 1, .10),
    HELL10(CrateList.HELL, "SKIN", "SOUL_CONSUMING_BATTLE_AXE4", 1, .10),
    HELL14(CrateList.HELL, "SKIN", "HELL_FLAME_STAFF4", 1, .10),
    HELL15(CrateList.HELL, "SKIN", "HELL_FLAME_STAFF4_2", 1, .10),
    HELL4(CrateList.HELL, "SKIN", "WORLD_SPLITTER_SWORD5", 1, .05),
    HELL5(CrateList.HELL, "SKIN", "WORLD_SPLITTER_SWORD5_2", 1, .05),
    HELL16(CrateList.HELL, "SKIN", "HELL_TELEKINESIS_STAFF5", 1, .05),
    HELL17(CrateList.HELL, "SKIN", "HELL_TELEKINESIS_STAFF5_2", 1, .05),

    UNDEAD2(CrateList.UNDEAD, "SKIN", "GREEN_UNDEAD_SWORD3", 1, .20),
    UNDEAD8(CrateList.UNDEAD, "SKIN", "BLUE_UNDEAD_SWORD3", 1, .20),
    UNDEAD9(CrateList.UNDEAD, "SKIN", "BROWN_UNDEAD_SWORD3", 1, .20),
    UNDEAD11(CrateList.UNDEAD, "SKIN", "YELLOW_UNDEAD_SWORD3", 1, .20),
    UNDEAD12(CrateList.UNDEAD, "SKIN", "RED_UNDEAD_SWORD3", 1, .20),
    UNDEAD13(CrateList.UNDEAD, "SKIN", "SHORT_GREEN_UNDEAD_SWORD3", 1, .20),
    UNDEAD1(CrateList.UNDEAD, "SKIN", "SHORT_BLUE_UNDEAD_SWORD3", 1, .20),
    UNDEAD3(CrateList.UNDEAD, "SKIN", "SHORT_BROWN_UNDEAD_SWORD3", 1, .20),
    UNDEAD6(CrateList.UNDEAD, "SKIN", "SHORT_YELLOW_UNDEAD_SWORD3", 1, .20),
    UNDEAD7(CrateList.UNDEAD, "SKIN", "SHORT_PURPLE_UNDEAD_SWORD3", 1, .20),
    UNDEAD10(CrateList.UNDEAD, "SKIN", "SHORT_RED_UNDEAD_SWORD3", 1, .20),
    UNDEAD19(CrateList.UNDEAD, "SKIN", "GRAVE_DIGGER_BATTLE_AXE3", 1, .20),
    UNDEAD20(CrateList.UNDEAD, "SKIN", "DEATHLY_BATTLE_AXE3", 1, .20),
    UNDEAD5(CrateList.UNDEAD, "SKIN", "UNDEAD_BRINGER_STAFF3", 1, .20),
    UNDEAD14(CrateList.UNDEAD, "SKIN", "UNDEAD_SOUL_SPLITTER_SWORD4", 1, .10),
    UNDEAD15(CrateList.UNDEAD, "SKIN", "UNDEAD_SWORD_OF_DARKNESS4", 1, .10),
    UNDEAD4(CrateList.UNDEAD, "SKIN", "UNDEAD_SERPENT_STAFF4", 1, .10),
    UNDEAD16(CrateList.UNDEAD, "SKIN", "UNDEAD_SPRINKLER_STAFF4", 1, .10),
    UNDEAD18(CrateList.UNDEAD, "SKIN", "UNDEAD_BATTLE_AXE4", 1, .10),
    UNDEAD17(CrateList.UNDEAD, "SKIN", "UNDEAD_WATCHER_STAFF5", 1, .10),
    UNDEAD21(CrateList.UNDEAD, "SKIN", "HOLY_RISER_BLADE5", 1, .05),
    UNDEAD22(CrateList.UNDEAD, "SKIN", "HEAVENS_GATES_BATTLE_AXE5", 1, .05),
    UNDEAD23(CrateList.UNDEAD, "SKIN", "UNDEAD_SCYTHE5", 1, .05),

    FARMINGBoostersITEM1(CrateList.PROFESSION, "BOOSTER", "FARMING_BOOSTER1", 1, .25),
    MININGBoostersITEM1(CrateList.PROFESSION, "BOOSTER", "MINING_BOOSTER1", 1, .25),
    FORAGINGBoostersITEM1(CrateList.PROFESSION, "BOOSTER", "FORAGING_BOOSTER1", 1, .25),
    SLAYERBoostersITEM1(CrateList.PROFESSION, "BOOSTER", "SLAYER_BOOSTER1", 1, .25),
    ENCHANTINGBoostersITEM1(CrateList.PROFESSION, "BOOSTER", "ENCHANTING_BOOSTER1", 1, .25),
    SPELLFORGINGBoostersITEM1(CrateList.PROFESSION, "BOOSTER", "SPELLFORGING_BOOSTER1", 1, .25),
    COOKINGBoostersITEM1(CrateList.PROFESSION, "BOOSTER", "COOKING_BOOSTER1", 1, .25),
    FARMINGBoostersITEM2(CrateList.PROFESSION, "BOOSTER", "FARMING_BOOSTER2", 1, .20),
    MININGBoostersITEM2(CrateList.PROFESSION, "BOOSTER", "MINING_BOOSTER2", 1, .20),
    FORAGINGBoostersITEM2(CrateList.PROFESSION, "BOOSTER", "FORAGING_BOOSTER2", 1, .20),
    SLAYERBoostersITEM2(CrateList.PROFESSION, "BOOSTER", "SLAYER_BOOSTER2", 1, .20),
    ENCHANTINGBoostersITEM2(CrateList.PROFESSION, "BOOSTER", "ENCHANTING_BOOSTER2", 1, .20),
    SPELLFORGINGBoostersITEM2(CrateList.PROFESSION, "BOOSTER", "SPELLFORGING_BOOSTER2", 1, .20),
    COOKINGBoostersITEM2(CrateList.PROFESSION, "BOOSTER", "COOKING_BOOSTER2", 1, .20),
    FARMINGBoostersITEM3(CrateList.PROFESSION, "BOOSTER", "FARMING_BOOSTER3", 1, .15),
    MININGBoostersITEM3(CrateList.PROFESSION, "BOOSTER", "MINING_BOOSTER3", 1, .15),
    FORAGINGBoostersITEM3(CrateList.PROFESSION, "BOOSTER", "FORAGING_BOOSTER3", 1, .15),
    SLAYERBoostersITEM3(CrateList.PROFESSION, "BOOSTER", "SLAYER_BOOSTER3", 1, .15),
    ENCHANTINGBoostersITEM3(CrateList.PROFESSION, "BOOSTER", "ENCHANTING_BOOSTER3", 1, .15),
    SPELLFORGINGBoostersITEM3(CrateList.PROFESSION, "BOOSTER", "SPELLFORGING_BOOSTER3", 1, .15),
    COOKINGBoostersITEM3(CrateList.PROFESSION, "BOOSTER", "COOKING_BOOSTER3", 1, .15),
    FARMINGBoostersITEM4(CrateList.PROFESSION, "BOOSTER", "FARMING_BOOSTER4", 1, .10),
    MININGBoostersITEM4(CrateList.PROFESSION, "BOOSTER", "MINING_BOOSTER4", 1, .10),
    FORAGINGBoostersITEM4(CrateList.PROFESSION, "BOOSTER", "FORAGING_BOOSTER4", 1, .10),
    SLAYERBoostersITEM4(CrateList.PROFESSION, "BOOSTER", "SLAYER_BOOSTER4", 1, .10),
    ENCHANTINGBoostersITEM4(CrateList.PROFESSION, "BOOSTER", "ENCHANTING_BOOSTER4", 1, .10),
    SPELLFORGINGBoostersITEM4(CrateList.PROFESSION, "BOOSTER", "SPELLFORGING_BOOSTER4", 1, .10),
    COOKINGBoostersITEM4(CrateList.PROFESSION, "BOOSTER", "COOKING_BOOSTER4", 1, .10),
    FARMINGBoostersITEM5(CrateList.PROFESSION, "BOOSTER", "FARMING_BOOSTER5", 1, .05),
    MININGBoostersITEM5(CrateList.PROFESSION, "BOOSTER", "MINING_BOOSTER5", 1, .05),
    FORAGINGBoostersITEM5(CrateList.PROFESSION, "BOOSTER", "FORAGING_BOOSTER5", 1, .05),
    SLAYERBoostersITEM5(CrateList.PROFESSION, "BOOSTER", "SLAYER_BOOSTER5", 1, .05),
    ENCHANTINGBoostersITEM5(CrateList.PROFESSION, "BOOSTER", "ENCHANTING_BOOSTER5", 1, .05),
    SPELLFORGINGBoostersITEM5(CrateList.PROFESSION, "BOOSTER", "SPELLFORGING_BOOSTER5", 1, .05),
    COOKINGBoostersITEM5(CrateList.PROFESSION, "BOOSTER", "COOKING_BOOSTER5", 1, .05),


    ;

    private static final Map<CrateList, List<Crates>> RARITY_LISTS = new EnumMap<>(CrateList.class);

    public static List<Crates> getCrates(CrateList rarity) {
        return RARITY_LISTS.computeIfAbsent(rarity, key -> Arrays.stream(values())
                .filter(lootbox -> lootbox.getCrate() == key)
                .collect(Collectors.toList()));
    }

    private final CrateList crate;
    private final String type;
    private final String id;
    private final int amount;
    private final double weight;

    Crates(CrateList crate, String type, String id, int amount, double weight) {
        this.crate = crate;
        this.id = id;
        this.type = type;
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

    public CrateList getCrate() {
        return crate;
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
            lore.add(Component.text("CHANCE: " + weight * 100 + "%", NamedTextColor.GOLD, TextDecoration.BOLD));
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
