package monzter.adventurescraft.plugin.network.PrisonGamemode.shared.commands.dropTables;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface ItemGenerator {
    ItemStack generateItem();
    ItemStack generateItem(Player player);
}
