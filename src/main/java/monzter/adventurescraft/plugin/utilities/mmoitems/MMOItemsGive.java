package monzter.adventurescraft.plugin.utilities.mmoitems;

import org.bukkit.entity.Player;

public interface MMOItemsGive {
    void giveMMOItem(Player player, String type, String id);
    void giveMMOItem(Player player, String type, String id, int amount);
    void giveMMOItem(Player player, String type, String id, int amount, boolean silent);
}
