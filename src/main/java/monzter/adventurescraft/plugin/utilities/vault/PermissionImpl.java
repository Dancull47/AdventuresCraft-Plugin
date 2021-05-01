package monzter.adventurescraft.plugin.utilities.vault;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.logging.Logger;

public class PermissionImpl implements Permission {

    private final net.milkbowl.vault.permission.Permission perm;
    private final Logger logger;

    public PermissionImpl(net.milkbowl.vault.permission.Permission permission, Logger logger) {
        this.perm = permission;
        this.logger = logger;
    }

    @Override
    public void givePermission(Player player, String permission) {
        perm.playerAdd(player, permission);
        logger.info(ChatColor.GREEN + "The " + ChatColor.GOLD + permission + ChatColor.GREEN
                + " permission has been ADDED TO " + ChatColor.GOLD + player.getName() + ChatColor.GREEN + "!");
    }

    @Override
    public void takePermission(Player player, String permission) {
        perm.playerRemove(player, permission);
        logger.info(ChatColor.GREEN + "The " + ChatColor.GOLD + permission + ChatColor.GREEN
                + " permission has been TAKEN FROM " + ChatColor.GOLD + player.getName() + ChatColor.GREEN + "!");
    }

}
