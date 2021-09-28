package monzter.adventurescraft.plugin.utilities.general;

import org.bukkit.entity.Player;

public interface FullInventory {
    boolean fullInventory(Player player);

    boolean emptySlots(Player player, int slots);

    int emptySlots(Player player);
}
