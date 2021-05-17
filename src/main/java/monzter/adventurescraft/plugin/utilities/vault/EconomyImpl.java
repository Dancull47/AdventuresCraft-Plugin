package monzter.adventurescraft.plugin.utilities.vault;

import monzter.adventurescraft.plugin.AdventuresCraft;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class EconomyImpl implements Economy {

    private final net.milkbowl.vault.economy.Economy econ;
    private final AdventuresCraft plugin;

    public EconomyImpl(net.milkbowl.vault.economy.Economy econ, AdventuresCraft plugin) {
        this.econ = econ;
        this.plugin = plugin;
    }

    @Override
    public void money(Player player, double amount) {
        EconomyResponse r = econ.depositPlayer(player, amount);
        if (!r.transactionSuccess()) {
            player.sendMessage(ChatColor.RED + "An error occurred while trying to give you money, report to Admins!");
            plugin.getLogger().info(ChatColor.RED + "An error occurred while sending " + amount + " to " + player);
        }
    }
    @Override
    public double getBalance(Player player) {
        return econ.getBalance(player);
    }
}
