package monzter.adventurescraft.plugin.utilities;

import org.bukkit.entity.Player;

public class NullMMOItemsGiveItem implements MMOItemsGiveItem {
    @Override
    public void giveMMOItem(Player player, String type, String id) {
    }

    @Override
    public void giveMMOItem(Player player, String type, String id, int amount) {
    }

    @Override
    public void giveMMOItem(Player player, String type, String id, int amount, boolean silent) {
    }
}
