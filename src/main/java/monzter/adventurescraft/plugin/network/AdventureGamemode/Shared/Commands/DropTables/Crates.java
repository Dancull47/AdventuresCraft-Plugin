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
    UNDEAD1(CrateList.UNDEAD, "SKIN", "HELL_BRINGER_SWORD", 1, .25),
    UNDEAD2(CrateList.UNDEAD, "SKIN", "RUSTED_SWORD", 1, .50),

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
