package monzter.adventurescraft.plugin.commands;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.util.Location;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.LocationFlag;
import com.sk89q.worldguard.protection.flags.StringFlag;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import me.clip.placeholderapi.PlaceholderAPI;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.event.extras.WeightPrices;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Sell implements CommandExecutor {
    private final AdventuresCraft plugin;
    private final LocationFlag sellLocationFlag;


    public Sell(AdventuresCraft plugin, LocationFlag sellLocationFlag) {
        this.plugin = plugin;
        this.sellLocationFlag = sellLocationFlag;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = ((Player) sender).getPlayer();
            if (player.hasPermission("SELL.ALL")){
                sell(player);
            } else {
                Location location = BukkitAdapter.adapt(player.getLocation());
                RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
                RegionQuery query = container.createQuery();
                ApplicableRegionSet set = query.getApplicableRegions(location);
                if (!set.queryValue(WorldGuardPlugin.inst().wrapPlayer(player), sellLocationFlag).equals(null)) {
                    player.teleport(BukkitAdapter.adapt(set.queryValue(WorldGuardPlugin.inst().wrapPlayer(player), sellLocationFlag)));
                    sell(player);
                }
            }

            }
            return true;
        }

    private void sell(Player player) {
        double counter = 0;
        for (WeightPrices material : WeightPrices.values()) {
            int materialAmount = Integer.valueOf(PlaceholderAPI.setPlaceholders(player, "%betonquest_items:point." + material.material.toString() + ".amount%"));
            double sellMultiplier = Double.valueOf(PlaceholderAPI.setPlaceholders(player, "%ac_Stat_SellMultiplier%"));
            if (materialAmount > 0) {
                double calculation = (material.getPrice() * sellMultiplier) * materialAmount;
                counter += calculation;
                player.sendMessage(ChatColor.GREEN + "You sold " + ChatColor.GOLD + materialAmount + "x " + material.getMaterial().toString() + ChatColor.GREEN + " for $"
                        + ChatColor.YELLOW + calculation + ChatColor.GREEN + "!");
                Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "q point " + player.getName() + " dell items." + material.toString());
                Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "q point " + player.getName() + " dell items.Weight");
                plugin.money(player, calculation);
            }
        }
        player.sendMessage(ChatColor.GREEN + "You made " + ChatColor.GOLD + counter + ChatColor.GREEN + "!");
    }
}

