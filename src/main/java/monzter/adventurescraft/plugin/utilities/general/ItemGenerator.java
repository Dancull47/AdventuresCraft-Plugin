package monzter.adventurescraft.plugin.utilities.general;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface ItemGenerator {
    ItemStack generateItem();
    ItemStack generateItem(Player player);
}
