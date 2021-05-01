package monzter.adventurescraft.plugin.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Dependency;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.util.Location;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.LocationFlag;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import me.clip.placeholderapi.PlaceholderAPI;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.event.extras.WeightPrices;
import monzter.adventurescraft.plugin.utilities.vault.Economy;
import org.bukkit.*;
import org.bukkit.entity.Player;

public class Sell extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final LocationFlag sellLocationFlag;
    private final Economy economy;

    public Sell(AdventuresCraft plugin, LocationFlag sellLocationFlag, Economy economy) {
        this.plugin = plugin;
        this.sellLocationFlag = sellLocationFlag;
        this.economy = economy;
    }

    @CommandAlias("Sell|SellAll")
    private final void donate(Player player) {
        if (player.hasPermission("SELL.ALL")){
            sell(player);
        } else {
            final Location location = BukkitAdapter.adapt(player.getLocation());
            final RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
            final RegionQuery query = container.createQuery();
            final ApplicableRegionSet set = query.getApplicableRegions(location);
            if (!set.queryValue(WorldGuardPlugin.inst().wrapPlayer(player), sellLocationFlag).equals(null)) {
                player.teleport(BukkitAdapter.adapt(set.queryValue(WorldGuardPlugin.inst().wrapPlayer(player), sellLocationFlag)));
                sell(player);
            }
        }
    }


    private final void sell(Player player) {
        double counter = 0;
        for (WeightPrices material : WeightPrices.values()) {
            final int materialAmount = Integer.valueOf(PlaceholderAPI.setPlaceholders(player, "%betonquest_items:point." + material.material.toString() + ".amount%"));
            final double sellMultiplier = Double.valueOf(PlaceholderAPI.setPlaceholders(player, "%ac_Stat_SellMultiplier%"));
            if (materialAmount > 0) {
                final double calculation = (material.getPrice() * sellMultiplier) * materialAmount;
                counter += calculation;
                player.sendMessage(ChatColor.GREEN + "You sold " + ChatColor.GOLD + materialAmount + "x " + material.getMaterial().toString() + ChatColor.GREEN + " for $"
                        + ChatColor.YELLOW + calculation + ChatColor.GREEN + "!");
                Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "q point " + player.getName() + " del items." + material.toString());
                Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "q point " + player.getName() + " del items.Weight");
                economy.money(player, calculation);
            }
        }
        player.sendMessage(ChatColor.GREEN + "You made " + ChatColor.GOLD + counter + ChatColor.GREEN + "!");
    }
}

