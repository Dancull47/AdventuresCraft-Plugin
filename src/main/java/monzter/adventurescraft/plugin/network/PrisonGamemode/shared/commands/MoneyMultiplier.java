package monzter.adventurescraft.plugin.network.PrisonGamemode.shared.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.mmoitems.MMOItemsGive;
import monzter.adventurescraft.plugin.utilities.vault.Economy;
import org.bukkit.entity.Player;

public class MoneyMultiplier extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final Economy economy;
    private final MMOItemsGive mmoItemsGive;

    public MoneyMultiplier(Economy economy, AdventuresCraft plugin, MMOItemsGive mmoItemsGive) {
        this.economy = economy;
        this.plugin = plugin;
        this.mmoItemsGive = mmoItemsGive;
    }

    @CommandAlias("moneyMultiplier")
    @CommandPermission("*")
    public void statCommand(Player player, double amount) {
        double balance = economy.getBalance(player);
        economy.giveMoney(player, balance * amount);
    }
}

