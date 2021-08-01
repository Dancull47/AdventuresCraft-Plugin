package monzter.adventurescraft.plugin.network.PrisonGamemode.shared.commands.dropTables;

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

public enum LootLlama implements Weighted, ItemGenerator {
    //        .5 = 50%
    Lootbox(Rarity.RARE + "Lootbox", "CONSUMABLE", "LOOTBOX3", .70,1),
    MoneyVoucher(Rarity.RARE + "Money Voucher", "VOUCHER", "MONEY_VOUCHER3", .65,1),
    LlamaGemStone(Rarity.COMMON + "Zebra Gem", "GEM_STONE", "LLAMA_GEM", .60,1),
    LlamaGemStone2(Rarity.UNCOMMON + "Zebra Gem", "GEM_STONE", "LLAMA_GEM2", .50,1),
    EXPVoucher(Rarity.LEGENDARY + "EXP Voucher", "VOUCHER", "EXP_VOUCHER4", .45,1),
    Lootbox2(Rarity.LEGENDARY + "Lootbox", "CONSUMABLE", "LOOTBOX4", .4,1),
    LlamaGemStone3(Rarity.RARE + "Zebra Gem", "GEM_STONE", "LLAMA_GEM3", .4,1),
    Lootbox3(Rarity.EXOTIC + "Lootbox", "CONSUMABLE", "LOOTBOX5", .25,1),
    LlamaGemStone4(Rarity.LEGENDARY + "Zebra Gem", "GEM_STONE", "LLAMA_GEM4", .25,1),
    PetEXPVoucher(Rarity.LEGENDARY + "Pet EXP Voucher", "VOUCHER", "PET_EXP_VOUCHER5", .1,1),
    LlamaGemStone5(Rarity.EXOTIC + "Zebra Gem", "GEM_STONE", "LLAMA_GEM5", .05,1);


    public final String displayName;
    public final String type;
    public final String id;
    public final double weight;
    public final int amount;

    LootLlama(String displayName, String type, String id, double weight, int amount) {
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
