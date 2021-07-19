package monzter.adventurescraft.plugin.utilities.beton;

import org.bukkit.entity.Player;
import pl.betoncraft.betonquest.BetonQuest;
import pl.betoncraft.betonquest.Point;

import java.util.List;

public class BetonPointsManagerImpl implements BetonPointsManager {
    private final BetonQuest betonQuest;

    public BetonPointsManagerImpl(BetonQuest betonQuest) {
        this.betonQuest = betonQuest;
    }

    @Override
    public void givePoint(Player player, String path, int amount) {
        betonQuest.getPlayerData(player.getUniqueId().toString()).modifyPoints(path, amount);
    }

    @Override
    public void takePoint(Player player, String path, int amount) {
        betonQuest.getPlayerData(player.getUniqueId().toString()).modifyPoints(path, -amount);
    }

    @Override
    public void giveGlobalPoint(String path, int amount) {
        betonQuest.getGlobalData().modifyPoints(path, amount);
    }

    @Override
    public void givePointEXP(Player player, int amount) {
        givePoint(player, "items.Experience", amount);
    }

    @Override
    public void takePointEXP(Player player, int amount) {
        takePoint(player, "items.Experience", amount);
    }

    @Override
    public void givePointPetEXP(Player player, int amount) {
        givePoint(player, "items.PetExperience", amount);
    }

    @Override
    public void takePointPetEXP(Player player, int amount) {
        takePoint(player, "items.PetExperience", amount);
    }

    @Override
    public void givePointMiningPass(Player player, int amount) {
        givePoint(player, "miningPass.EXP", amount);
    }

    @Override
    public void takePointMiningPass(Player player, int amount) {
        takePoint(player, "miningPass.EXP", amount);
    }

    @Override
    public void givePointWeight(Player player, int amount) {
        givePoint(player, "items.Weight", amount);
    }

    @Override
    public void takePointWeight(Player player, int amount) {
        takePoint(player, "items.Weight", amount);
    }

    @Override
    public void givePointACs(Player player, int amount) {
        givePoint(player, "items.AdventureCoin", amount);
    }

    @Override
    public void takePointACs(Player player, int amount) {
        takePoint(player, "items.AdventureCoin", amount);
    }

    @Override
    public int getPoints(String pointCategory, List<Point> pointList) {
        for (final Point point : pointList)
            if (point.getCategory().equalsIgnoreCase(pointCategory))
                return point.getCount();
        return 0;
    }

    @Override
    public int getPoints(Player player, String pointCategory) {
        final List<Point> points = BetonQuest.getInstance().getPlayerData(player.getUniqueId().toString()).getPoints();
        for (final Point point : points)
            if (point.getCategory().equalsIgnoreCase(pointCategory))
                return point.getCount();
        return 0;
    }

}
