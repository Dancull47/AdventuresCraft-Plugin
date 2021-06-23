package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.quests;

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

public enum Quests implements Weighted, ItemGenerator {
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

    ;

    private static final Map<CrateList, List<Quests>> RARITY_LISTS = new EnumMap<>(CrateList.class);

    public static List<Quests> getCrates(CrateList rarity) {
        return RARITY_LISTS.computeIfAbsent(rarity, key -> Arrays.stream(values())
                .filter(lootbox -> lootbox.getCrate() == key)
                .collect(Collectors.toList()));
    }

    private final CrateList crate;
    private final String type;
    private final String id;
    private final int amount;
    private final double weight;

    Quests(CrateList crate, String type, String id, int amount, double weight) {
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
        System.out.println(id);

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
