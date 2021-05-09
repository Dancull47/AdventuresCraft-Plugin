package monzter.adventurescraft.plugin.prison.commands;

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
import monzter.adventurescraft.plugin.shared.events.extras.WeightPrices;
import monzter.adventurescraft.plugin.utilities.bukkit.SoundManager;
import monzter.adventurescraft.plugin.utilities.text.NumberFormat;
import monzter.adventurescraft.plugin.utilities.vault.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class Sell extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final LocationFlag sellLocationFlag;
    private final Economy economy;
    private final NumberFormat numberFormat;
    private final SoundManager soundManager;
    private final org.bukkit.Location defaultSellLocation = new org.bukkit.Location(Bukkit.getWorld("World"), 1174, 200.5, 1610, 42.8F, 7.7F);

    public Sell(AdventuresCraft plugin, LocationFlag sellLocationFlag, Economy economy, NumberFormat numberFormat, SoundManager soundManager) {
        this.plugin = plugin;
        this.sellLocationFlag = sellLocationFlag;
        this.economy = economy;
        this.numberFormat = numberFormat;
        this.soundManager = soundManager;
    }

    @CommandAlias("Sell|SellAll")
    private void donate(Player player) {
        if (!player.hasPermission("SELL.ALL")) {
            final Location location = BukkitAdapter.adapt(player.getLocation());
            final RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
            final RegionQuery query = container.createQuery();
            final ApplicableRegionSet set = query.getApplicableRegions(location);
            if (!set.queryValue(WorldGuardPlugin.inst().wrapPlayer(player), sellLocationFlag).equals(null)) {
                player.teleport(BukkitAdapter.adapt(set.queryValue(WorldGuardPlugin.inst().wrapPlayer(player), sellLocationFlag)));
            } else {
                player.teleport(defaultSellLocation);
            }
        }
        sell(player);
    }


    private void sell(Player player) {
        double counter = 0;
        for (WeightPrices material : WeightPrices.values()) {
            final int materialAmount = Integer.valueOf(PlaceholderAPI.setPlaceholders(player, "%betonquest_items:point." + material.material.toString() + ".amount%"));
            final double sellMultiplier = Double.valueOf(PlaceholderAPI.setPlaceholders(player, "%ac_Stat_SellMultiplier%"));
            if (materialAmount > 0) {
                final double calculation = (material.getPrice() * sellMultiplier) * materialAmount;
                counter += calculation;
                player.sendMessage(ChatColor.GREEN + "You sold " + ChatColor.GOLD + materialAmount + "x " + material.getName() + ChatColor.GREEN + " for "
                        + ChatColor.YELLOW + "⛂ "+  numberFormat.numberFormat(calculation) + ChatColor.GREEN + "!");
                Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "q point " + player.getName() + " del items." + material.toString());
                Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "q point " + player.getName() + " del items.Weight");
                soundManager.playSound(player, Sound.ENTITY_ARROW_HIT_PLAYER, 1, 1);
                economy.money(player, calculation);
            }
        }
        player.sendMessage(ChatColor.GREEN + "You made " + ChatColor.YELLOW + "⛂ " + numberFormat.numberFormat(counter) + ChatColor.GREEN + "!");
    }
}

