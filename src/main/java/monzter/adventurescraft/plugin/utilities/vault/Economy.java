package monzter.adventurescraft.plugin.utilities.vault;

import org.bukkit.entity.Player;

public interface Economy {
    void takeMoney(Player player, double amount);

    void giveMoney(Player player, double amount);

    double getBalance(Player player);

    boolean hasMoney(Player player, int price);
    boolean hasMoney(Player player, double price);
    }
