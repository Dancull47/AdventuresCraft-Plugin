package monzter.adventurescraft.plugin.utilities.mmoitems;

import org.bukkit.entity.Player;

public class MMOItemsGiveNull implements MMOItemsGive {
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
