package monzter.adventurescraft.plugin.utilities;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.logging.Logger;

public class VaultHandler implements EconomyFacade, PermissionsFacade {

    private final Economy econ;
    private final Permission perm;
    private final Logger logger;

    public VaultHandler(Economy econ, Permission permission, Logger logger) {
        this.econ = econ;
        this.perm = permission;
        this.logger = logger;
    }

    @Override
    public void money(Player player, double amount) {
        EconomyResponse r = econ.depositPlayer(player, amount);
        if (!r.transactionSuccess()) {
            player.sendMessage(ChatColor.RED + "An error occurred while trying to give you money, report to Admins!");
            logger.info(ChatColor.RED + "An error occurred while sending " + amount + " to " + player);
        }
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
