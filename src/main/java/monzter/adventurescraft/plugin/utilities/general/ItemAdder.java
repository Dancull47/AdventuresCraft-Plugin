package monzter.adventurescraft.plugin.utilities.general;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface ItemAdder {
    void itemAdder(Player player, ItemStack itemStack);
    void itemAdder(Player player, ItemStack[] itemStack);
}
