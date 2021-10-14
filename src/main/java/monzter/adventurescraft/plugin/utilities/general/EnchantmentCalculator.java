package monzter.adventurescraft.plugin.utilities.general;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;

public class EnchantmentCalculator {

    public static int calculateEnchantments(Player player, String enchantment) {
        if (player != null) {
            ItemMeta meta = player.getInventory().getItemInMainHand().getItemMeta();
            if (meta != null && meta.hasEnchants())
                for (Enchantment enchant : meta.getEnchants().keySet()) {
                    if (enchant.getKey().getKey().equalsIgnoreCase(enchantment))
                        return meta.getEnchants().get(enchant);
                }
        }
        return 0;
    }
}
