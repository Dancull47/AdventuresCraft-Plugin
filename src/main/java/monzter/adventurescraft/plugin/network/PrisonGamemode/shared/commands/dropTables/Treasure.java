package monzter.adventurescraft.plugin.network.PrisonGamemode.shared.commands.dropTables;

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

public enum Treasure implements Weighted, ItemGenerator {
    // Common
    MoneyVoucher2C(Rarity.COMMON,Rarity.UNCOMMON,"Money Voucher", "VOUCHER", "MONEY_VOUCHER2", .5,1),
    EXPVoucher2C(Rarity.COMMON,Rarity.UNCOMMON,"EXP Voucher", "VOUCHER", "EXP_VOUCHER2", .5,1),
    PetEXPVoucher2C(Rarity.COMMON,Rarity.UNCOMMON,"Pet EXP Voucher", "VOUCHER", "PET_EXP_VOUCHER2", .5,1),

    MoneyVoucher3C(Rarity.COMMON,Rarity.RARE,"Money Voucher", "VOUCHER", "MONEY_VOUCHER3", .20,1),
    EXPVoucher3C(Rarity.COMMON,Rarity.RARE,"EXP Voucher", "VOUCHER", "EXP_VOUCHER3", .20,1),
    PetEXPVoucher3C(Rarity.COMMON,Rarity.RARE,"Pet EXP Voucher", "VOUCHER", "PET_EXP_VOUCHER3", .20,1),

    MoneyVoucher4C(Rarity.COMMON,Rarity.LEGENDARY,"Money Voucher", "VOUCHER", "MONEY_VOUCHER4", .05,1),
    EXPVoucher4C(Rarity.COMMON,Rarity.LEGENDARY,"EXP Voucher", "VOUCHER", "EXP_VOUCHER4", .05,1),
    PetEXPVoucher4C(Rarity.COMMON,Rarity.LEGENDARY,"Pet EXP Voucher", "VOUCHER", "PET_EXP_VOUCHER4", .05,1),

    MoneyVoucher5C(Rarity.COMMON,Rarity.LEGENDARY,"Money Voucher", "VOUCHER", "MONEY_VOUCHER5", .005,1),
    EXPVoucher5C(Rarity.COMMON,Rarity.LEGENDARY,"EXP Voucher", "VOUCHER", "EXP_VOUCHER5", .005,1),
    PetEXPVoucher5C(Rarity.COMMON,Rarity.LEGENDARY,"Pet EXP Voucher", "VOUCHER", "PET_EXP_VOUCHER5", .005,1);

    private static final Map<Rarity, List<Treasure>> RARITY_LISTS = new EnumMap<>(Rarity.class);

    public static List<Treasure> getTreasure(Rarity rarity) {
        return RARITY_LISTS.computeIfAbsent(rarity, key -> Arrays.stream(values())
                .filter(treasure -> treasure.getTreasureChestRarity() == key)
                .collect(Collectors.toList()));
    }

    private final String displayName;

    private final String type;
    private final Rarity treasureChestRarity;
    private final Rarity treasureRarity;
    private final String id;
    private final double weight;
    private final int amount;

    Treasure(Rarity treasureChestRarity, Rarity treasureRarity, String displayName, String type, String id, double weight, int amount) {
        this.treasureChestRarity = treasureChestRarity;
        this.treasureRarity = treasureRarity;
        this.id = id;
        this.type = type;
        this.displayName = treasureRarity.getColorString() + displayName;
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
        return treasureRarity;
    }

    public Rarity getTreasureChestRarity() {
        return treasureChestRarity;
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
