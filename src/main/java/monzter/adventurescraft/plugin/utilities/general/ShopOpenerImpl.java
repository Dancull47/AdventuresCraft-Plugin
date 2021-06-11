package monzter.adventurescraft.plugin.utilities.general;

import monzter.adventurescraft.plugin.utilities.luckperms.PermissionLP;
import org.bukkit.entity.Player;

public class ShopOpenerImpl implements ShopOpener {
    PermissionLP permissionLP;

    public ShopOpenerImpl(PermissionLP permissionLP) {
        this.permissionLP = permissionLP;
    }

    @Override
    public void shopOpener(Player player, String shop) {
//        !player.hasPermission("Rank3") &&
        if (!player.hasPermission("SHOPS")) {
            try {
                permissionLP.givePermission(player, "SHOPS");
                player.performCommand("enchantmentShop");
            } finally {
                permissionLP.takePermission(player, "SHOPS");
            }
        }
        player.performCommand(shop);
    }
}
