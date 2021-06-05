package monzter.adventurescraft.plugin.utilities.general;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class ItemAdderImpl implements ItemAdder {

    @Override
    public void itemAdder(Player player, ItemStack itemStack) {
        if (!itemStack.getType().equals(Material.AIR)) {
            final Map<Integer, ItemStack> map = player.getInventory().addItem(itemStack);
            if (!map.isEmpty()) {
                map.values().forEach(items -> player.getWorld().dropItemNaturally(player.getLocation(), items));
            }
        }
    }

    @Override
    public void itemAdder(Player player, ItemStack[] itemStack) {
        for (ItemStack item : itemStack) {
            if (!item.getType().equals(Material.AIR)) {
                final Map<Integer, ItemStack> map = player.getInventory().addItem(item);
                if (!map.isEmpty()) {
                    map.values().forEach(items -> player.getWorld().dropItemNaturally(player.getLocation(), items));
                }
            }
        }
    }
}
