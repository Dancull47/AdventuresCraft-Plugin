package monzter.adventurescraft.plugin.utilities.vault;

import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import monzter.adventurescraft.plugin.utilities.text.NumberFormat;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class EconomyImpl implements Economy {

    private final net.milkbowl.vault.economy.Economy econ;
    private final AdventuresCraft plugin;
    private final NumberFormat numberFormat;
    private final SoundManager soundManager;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());

    public EconomyImpl(net.milkbowl.vault.economy.Economy econ, AdventuresCraft plugin, NumberFormat numberFormat, SoundManager soundManager) {
        this.econ = econ;
        this.plugin = plugin;
        this.numberFormat = numberFormat;
        this.soundManager = soundManager;
    }

    @Override
    public void giveMoney(Player player, double amount) {
        EconomyResponse r = econ.depositPlayer(player, amount);
        player.sendMessage(ChatColor.GREEN + "You received " + ChatColor.YELLOW + "⛂ " + numberFormat.numberFormat(amount) + ChatColor.GREEN + "!");
        if (!r.transactionSuccess()) {
            player.sendMessage(ChatColor.RED + "An error occurred while trying to give you money, report to Admins!" + dateFormat.format(timestamp));
            plugin.getLogger().info(ChatColor.RED + "An error occurred while sending " + numberFormat.numberFormat(amount) + " to " + player);
        }
    }

    @Override
    public void takeMoney(Player player, double amount) {
        EconomyResponse r = econ.withdrawPlayer(player, amount);
        player.sendMessage(ChatColor.YELLOW + "⛂ " + numberFormat.numberFormat(amount) + ChatColor.RED + " has been deducted from your account!");
        if (!r.transactionSuccess()) {
            player.sendMessage(ChatColor.RED + "An error occurred while trying to give you money, report to Admins!" + dateFormat.format(timestamp));
            plugin.getLogger().info(ChatColor.RED + "An error occurred while sending " + numberFormat.numberFormat(amount) + " to " + player);
        }
    }

    @Override
    public double getBalance(Player player) {
        return econ.getBalance(player);
    }

    @Override
    public boolean hasMoney(Player player, int price) {
        double balance = econ.getBalance(player);
        if (balance >= price)
            return true;
        else {
            player.sendMessage(ChatColor.RED + "You only have " + ChatColor.YELLOW + "⛂ " + numberFormat.numberFormat(balance) + ChatColor.RED + " and this costs " + ChatColor.YELLOW + "⛂ " + numberFormat.numberFormat(price) + ChatColor.RED + "!");
            soundManager.soundNo(player, 1);
            return false;
        }
    }

    @Override
    public boolean hasMoney(Player player, double price) {
        double balance = econ.getBalance(player);
        if (balance >= price)
            return true;
        else {
            player.sendMessage(ChatColor.RED + "You only have " + ChatColor.YELLOW + "⛂ " + numberFormat.numberFormat(balance) + ChatColor.RED + " and this costs " + ChatColor.YELLOW + "⛂ " + numberFormat.numberFormat(price) + ChatColor.RED + "!");
            soundManager.soundNo(player, 1);
            return false;
        }
    }

}
