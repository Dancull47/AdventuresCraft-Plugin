package monzter.adventurescraft.plugin.utilities.general;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.meta.ItemMeta;

public class EnchantmentCalculator {

    public static int calculateEnchantments(Player player, String enchantment) {
        return calculateEnchantments(player, enchantment, EquipmentSlot.HAND);
    }

    public static int calculateEnchantments(Player player, String enchantment, EquipmentSlot equipmentSlot) {
        if (player != null) {
            if (player.getInventory().getItem(equipmentSlot) != null) {
                ItemMeta meta = player.getInventory().getItem(equipmentSlot).getItemMeta();
                if (meta != null && meta.hasEnchants())
                    for (Enchantment enchant : meta.getEnchants().keySet()) {
                        if (enchant.getKey().getKey().equalsIgnoreCase(enchantment))
                            return meta.getEnchants().get(enchant);
                    }
            }
        }
        return 0;
    }
}
