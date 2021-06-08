package monzter.adventurescraft.plugin.utilities.general;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface PurchaseUtils {
    void purchase(Player player, ItemStack itemStack, int amount, double price);
}
