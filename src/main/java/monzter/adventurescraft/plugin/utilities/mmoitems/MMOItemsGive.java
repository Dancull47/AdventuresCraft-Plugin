package monzter.adventurescraft.plugin.utilities.mmoitems;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface MMOItemsGive {
    void giveMMOItem(Player player, ItemStack itemStack);
    void giveMMOItem(Player player, String type, String id);
    void giveMMOItem(Player player, String type, String id, int amount);
    void giveMMOItem(Player player, String type, String id, int amount, boolean silent);
    void giveMMOItem(Player player, ItemStack itemStack, boolean silent);
    void giveMMOItem(Player player, ItemStack itemStack, int amount, boolean silent);
}
