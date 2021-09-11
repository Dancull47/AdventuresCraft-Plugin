package monzter.adventurescraft.plugin.utilities.general;

import monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.mainMenu.donation.DonationItemList;
import monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.shops.npcs.ItemList;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface PurchaseUtils {
    void purchase(Player player, ItemList itemList, int amount);
    void purchase(Player player, DonationItemList itemList, int amount);
    boolean hasItem(Player player, ItemStack[] items, int purchaseAmount);
}
