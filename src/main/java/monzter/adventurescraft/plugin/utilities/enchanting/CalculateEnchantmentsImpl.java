package monzter.adventurescraft.plugin.utilities.enchanting;

import org.bukkit.OfflinePlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

import java.util.Map;

public class CalculateEnchantmentsImpl implements CalculateEnchantments {
    @Override
    public int calculateEnchantments(Player player, String enchantment) {
        if (player.getPlayer().getInventory().getItemInMainHand().getItemMeta() != null) {
            Map<Enchantment, Integer> enchantmentMap = player.getPlayer().getInventory().getItemInMainHand().getItemMeta().getEnchants();
            if (!enchantmentMap.isEmpty()) {
                if (enchantmentMap.containsKey(org.bukkit.enchantments.Enchantment.getByName(enchantment))) {
                    return enchantmentMap.get(Enchantment.getByName(enchantment));
                }
            }
        }
        return 0;
    }

    public int calculateEnchantments(OfflinePlayer player, String enchantment) {
        if (player.isOnline()) {
            if (player.getPlayer().getInventory().getItemInMainHand().getItemMeta() != null) {
                Map<Enchantment, Integer> enchantmentMap = player.getPlayer().getInventory().getItemInMainHand().getItemMeta().getEnchants();
                if (!enchantmentMap.isEmpty()) {
                    if (enchantmentMap.containsKey(org.bukkit.enchantments.Enchantment.getByName(enchantment))) {
                        return enchantmentMap.get(Enchantment.getByName(enchantment));
                    }
                }
            }
        }
        return 0;
    }
}
