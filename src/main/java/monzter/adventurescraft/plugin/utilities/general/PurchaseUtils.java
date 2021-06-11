package monzter.adventurescraft.plugin.utilities.general;

import monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.shops.npcs.ItemList;
import org.bukkit.entity.Player;

public interface PurchaseUtils {
    void purchase(Player player, ItemList itemList, int amount);
//    void purchase(Player player, ItemStack itemStack, int amount, double coinPrice);
//    void purchase(Player player, ItemStack itemStack, int amount, double coinPrice, int expPrice);
}
