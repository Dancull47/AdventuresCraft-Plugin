package monzter.adventurescraft.plugin.shared.dropTables;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface ItemGenerator {
    ItemStack generateItem();
    ItemStack generateItem(Player player);
}