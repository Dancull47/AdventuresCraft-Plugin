package monzter.adventurescraft.plugin.shared.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import co.aikar.commands.bukkit.contexts.OnlinePlayer;
import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import me.clip.placeholderapi.PlaceholderAPI;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.enums.StatsDisplay;
import monzter.adventurescraft.plugin.utilities.mmoitems.MMOItemsGive;
import monzter.adventurescraft.plugin.utilities.vault.Economy;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

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

