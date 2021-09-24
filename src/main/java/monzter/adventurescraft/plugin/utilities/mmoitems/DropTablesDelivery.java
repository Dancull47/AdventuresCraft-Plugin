package monzter.adventurescraft.plugin.utilities.mmoitems;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface DropTablesDelivery {
    void giveReward(Player player, ItemStack itemStack, double chance);
}
