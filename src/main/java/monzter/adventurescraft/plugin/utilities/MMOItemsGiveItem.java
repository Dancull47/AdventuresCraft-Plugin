package monzter.adventurescraft.plugin.utilities;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface MMOItemsGiveItem {
    void giveMMOItem(Player player, String type, String id);
    void giveMMOItem(Player player, String type, String id, int amount);
    void giveMMOItem(Player player, String type, String id, int amount, boolean silent);
}
