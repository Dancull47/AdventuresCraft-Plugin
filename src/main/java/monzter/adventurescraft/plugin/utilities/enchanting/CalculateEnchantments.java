package monzter.adventurescraft.plugin.utilities.enchanting;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public interface CalculateEnchantments {
    int calculateEnchantments(Player player, String enchantment);
    int calculateEnchantments(OfflinePlayer player, String enchantment);
}
