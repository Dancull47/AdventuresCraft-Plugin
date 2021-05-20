package monzter.adventurescraft.plugin.shared.GUIs.shops.npcs;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public interface PurchaseUtils {
    void purchase(Player player, Material material, int amount, double price);
}
