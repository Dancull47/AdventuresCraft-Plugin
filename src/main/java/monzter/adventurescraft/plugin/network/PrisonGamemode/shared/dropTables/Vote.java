package monzter.adventurescraft.plugin.network.PrisonGamemode.shared.dropTables;

import me.lucko.helper.random.Weighted;
import monzter.adventurescraft.plugin.utilities.enums.Rarity;
import net.Indyuce.mmoitems.MMOItems;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public enum Vote implements Weighted, ItemGenerator {
    //        .5 = 50%
    MoneyVoucher2(Rarity.UNCOMMON + "Money Voucher", "VOUCHER", "MONEY_VOUCHER2", .5,1),
    EXPVoucher2(Rarity.UNCOMMON + "EXP Voucher", "VOUCHER", "EXP_VOUCHER2", .5,1),
    PetEXPVoucher2(Rarity.UNCOMMON + "Pet EXP Voucher", "VOUCHER", "PET_EXP_VOUCHER2", .5,1),

    SellBooster2(Rarity.UNCOMMON + "Sell Booster", "BOOSTER", "SELL_BOOSTER2", .15,1),
    EXPBooster2(Rarity.UNCOMMON + "EXP Booster", "BOOSTER", "EXP_BOOSTER2", .15,1),
    PetEXPBooster2(Rarity.UNCOMMON + "Pet EXP Booster", "BOOSTER", "PET_EXP_BOOSTER2", .15,1),

    PetEgg2(Rarity.UNCOMMON + "Pet Egg", "PET", "PET_EGG2", .15,1),

    PetEgg3(Rarity.RARE + "Pet Egg", "PET", "PET_EGG3", .1,1),

    PetEgg4(Rarity.LEGENDARY + "Pet Egg", "PET", "PET_EGG4", .05,1),

    LootBox5(Rarity.EXOTIC + "LootBox", "CONSUMABLE", "CONSUMABLE_LOOTBOX5", .01,1);


    public final String displayName;
    public final String type;
    public final String id;
    public final double weight;
    public final int amount;

    Vote(String displayName, String type, String id, double weight, int amount) {
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

    public String getDisplayName() {
        return displayName;
    }
    public int getAmount() {
        return amount;
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
