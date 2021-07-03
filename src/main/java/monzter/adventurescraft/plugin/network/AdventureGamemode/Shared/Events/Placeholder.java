package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.Events;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.util.Location;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.StringFlag;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import monzter.adventurescraft.plugin.AdventuresCraft;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class Placeholder extends PlaceholderExpansion {

    private final AdventuresCraft plugin;
    private final StringFlag displayNameFlag;

    public Placeholder(AdventuresCraft plugin, StringFlag displayNameFlag) {
        this.plugin = plugin;
        this.displayNameFlag = displayNameFlag;
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public String getAuthor() {
        return "Monzter";
    }

    @Override
    public String getIdentifier() {
        return "ac";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }

    /**
     * This is the method called when a placeholder with our identifier
     * is found and needs a value.
     * <br>We specify the value identifier in this method.
     * <br>Since version 2.9.1 can you use OfflinePlayers in your requests.
     *
     * @param player     A {@link OfflinePlayer OfflinePlayer}.
     * @param identifier A String containing the identifier/value.
     * @return Possibly-null String of the requested identifier.
     */
    @Override
    public String onRequest(OfflinePlayer player, String identifier) {
        switch (identifier) {
            case "Location":
                return location(player);
            default:
                return null;
        }
    }

    private String location(OfflinePlayer player) {
        if (player.getPlayer().isOnline()) {
            Player player1 = player.getPlayer();
            Location location = BukkitAdapter.adapt(player1.getLocation());
            RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
            RegionQuery query = container.createQuery();
            ApplicableRegionSet set = query.getApplicableRegions(location);
            if (set.queryValue(WorldGuardPlugin.inst().wrapPlayer(player1), displayNameFlag) == null) {
                return "Unknown!";
            } else {
                return set.queryValue(WorldGuardPlugin.inst().wrapPlayer(player1), displayNameFlag);
            }
        }
        return null;
    }
}
