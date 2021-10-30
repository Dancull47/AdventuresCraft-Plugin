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
import monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.ClassSystem.ProfessionHandler;
import monzter.adventurescraft.plugin.utilities.general.Cooldown;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import pl.betoncraft.betonquest.BetonQuest;
import pl.betoncraft.betonquest.Point;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static monzter.adventurescraft.plugin.AdventuresCraft.restartTime;
import static monzter.adventurescraft.plugin.AdventuresCraft.restarting;

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
        final List<Point> points = BetonQuest.getInstance().getPlayerData(player.getUniqueId().toString()).getPoints();
        switch (identifier) {
            case "Location":
                return location(player);
            case "Currency_VotingCoins":
                return String.valueOf(getPoints("items.Vote", points));
            case "Void_Timer":
                return (Cooldown.getTimeLeftFormatted(UUID.fromString("123e4567-e89b-12d3-a456-556642440000"), "VOID_ENCHANTRESS"));
            case "Restart_Timer":
                long hours = TimeUnit.MILLISECONDS.toHours(restartTime - System.currentTimeMillis());
                long minutes = (TimeUnit.MILLISECONDS.toMinutes(restartTime - System.currentTimeMillis()) % 60);
                long seconds = (TimeUnit.MILLISECONDS.toSeconds(restartTime - System.currentTimeMillis()) % 60);
                return (hours + " hours and " + minutes + " Minutes and " + seconds + " seconds");
            case "Restarting":
                return String.valueOf(restarting);

            case "Profession_Level_Main":
                return String.valueOf(ProfessionHandler.getLevel(player.getPlayer(), "Main"));
            case "Profession_EXP_Main":
                return String.valueOf(ProfessionHandler.getEXP(player.getPlayer(), "Main"));
            case "Profession_EXP_Main_formatted":
                return ProfessionHandler.getEXPFormatted(player.getPlayer(), "Main");
            case "Profession_NEXT_EXP_Main_formatted":
                return ProfessionHandler.getNextLevelEXPFormatted(player.getPlayer(), "Main");
            case "Profession_NEXT_PERCENT_Main":
                return ProfessionHandler.getNextLevelPercentage(player.getPlayer(), "Main");

            case "Profession_Level_Farming":
                return String.valueOf(ProfessionHandler.getLevel(player.getPlayer(), "Farming"));
            case "Profession_EXP_Farming":
                return String.valueOf(ProfessionHandler.getEXP(player.getPlayer(), "Farming"));
            case "Profession_EXP_Farming_formatted":
                return ProfessionHandler.getEXPFormatted(player.getPlayer(), "Farming");
            case "Profession_NEXT_EXP_Farming_formatted":
                return ProfessionHandler.getNextLevelEXPFormatted(player.getPlayer(), "Farming");
            case "Profession_NEXT_PERCENT_Farming":
                return ProfessionHandler.getNextLevelPercentage(player.getPlayer(), "Farming");

            case "Profession_Level_Foraging":
                return String.valueOf(ProfessionHandler.getLevel(player.getPlayer(), "Foraging"));
            case "Profession_EXP_Foraging":
                return String.valueOf(ProfessionHandler.getEXP(player.getPlayer(), "Foraging"));
            case "Profession_EXP_Foraging_formatted":
                return ProfessionHandler.getEXPFormatted(player.getPlayer(), "Foraging");
            case "Profession_NEXT_EXP_Foraging_formatted":
                return ProfessionHandler.getNextLevelEXPFormatted(player.getPlayer(), "Foraging");
            case "Profession_NEXT_PERCENT_Foraging":
                return ProfessionHandler.getNextLevelPercentage(player.getPlayer(), "Foraging");

            case "Profession_Level_Mining":
                return String.valueOf(ProfessionHandler.getLevel(player.getPlayer(), "Mining"));
            case "Profession_EXP_Mining":
                return String.valueOf(ProfessionHandler.getEXP(player.getPlayer(), "Mining"));
            case "Profession_EXP_Mining_formatted":
                return ProfessionHandler.getEXPFormatted(player.getPlayer(), "Mining");
            case "Profession_NEXT_EXP_Mining_formatted":
                return ProfessionHandler.getNextLevelEXPFormatted(player.getPlayer(), "Mining");
            case "Profession_NEXT_PERCENT_Mining":
                return ProfessionHandler.getNextLevelPercentage(player.getPlayer(), "Mining");

            case "Profession_Level_Slayer":
                return String.valueOf(ProfessionHandler.getLevel(player.getPlayer(), "Slayer"));
            case "Profession_EXP_Slayer":
                return String.valueOf(ProfessionHandler.getEXP(player.getPlayer(), "Slayer"));
            case "Profession_EXP_Slayer_formatted":
                return ProfessionHandler.getEXPFormatted(player.getPlayer(), "Slayer");
            case "Profession_NEXT_EXP_Slayer_formatted":
                return ProfessionHandler.getNextLevelEXPFormatted(player.getPlayer(), "Slayer");
            case "Profession_NEXT_PERCENT_Slayer":
                return ProfessionHandler.getNextLevelPercentage(player.getPlayer(), "Slayer");

            case "Profession_Level_Enchanting":
                return String.valueOf(ProfessionHandler.getLevel(player.getPlayer(), "Enchanting"));
            case "Profession_EXP_Enchanting":
                return String.valueOf(ProfessionHandler.getEXP(player.getPlayer(), "Enchanting"));
            case "Profession_EXP_Enchanting_formatted":
                return ProfessionHandler.getEXPFormatted(player.getPlayer(), "Enchanting");
            case "Profession_NEXT_EXP_Enchanting_formatted":
                return ProfessionHandler.getNextLevelEXPFormatted(player.getPlayer(), "Enchanting");
            case "Profession_NEXT_PERCENT_Enchanting":
                return ProfessionHandler.getNextLevelPercentage(player.getPlayer(), "Enchanting");

            case "Profession_Level_Cooking":
                return String.valueOf(ProfessionHandler.getLevel(player.getPlayer(), "Cooking"));
            case "Profession_EXP_Cooking":
                return String.valueOf(ProfessionHandler.getEXP(player.getPlayer(), "Cooking"));
            case "Profession_EXP_Cooking_formatted":
                return ProfessionHandler.getEXPFormatted(player.getPlayer(), "Cooking");
            case "Profession_NEXT_EXP_Cooking_formatted":
                return ProfessionHandler.getNextLevelEXPFormatted(player.getPlayer(), "Cooking");
            case "Profession_NEXT_PERCENT_Cooking":
                return ProfessionHandler.getNextLevelPercentage(player.getPlayer(), "Cooking");

            case "Profession_Level_Spellforging":
                return String.valueOf(ProfessionHandler.getLevel(player.getPlayer(), "Spellforging"));
            case "Profession_EXP_Spellforging":
                return String.valueOf(ProfessionHandler.getEXP(player.getPlayer(), "Spellforging"));
            case "Profession_EXP_Spellforging_formatted":
                return ProfessionHandler.getEXPFormatted(player.getPlayer(), "Spellforging");
            case "Profession_NEXT_EXP_Spellforging_formatted":
                return ProfessionHandler.getNextLevelEXPFormatted(player.getPlayer(), "Spellforging");
            case "Profession_NEXT_PERCENT_Spellforging":
                return ProfessionHandler.getNextLevelPercentage(player.getPlayer(), "Spellforging");

            default:
                return null;
        }

    }

    public int getPoints(String pointCategory, List<Point> pointList) {
        for (final Point point : pointList)
            if (point.getCategory().equalsIgnoreCase(pointCategory))
                return point.getCount();
        return 0;
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
