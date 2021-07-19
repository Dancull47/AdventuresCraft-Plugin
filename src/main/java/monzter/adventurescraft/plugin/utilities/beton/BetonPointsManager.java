package monzter.adventurescraft.plugin.utilities.beton;

import org.bukkit.entity.Player;
import pl.betoncraft.betonquest.Point;

import java.util.List;

public interface BetonPointsManager {
    void givePoint(Player player, String path, int amount);

    void takePoint(Player player, String path, int amount);

    void giveGlobalPoint(String path, int amount);

    void givePointEXP(Player player, int amount);

    void takePointEXP(Player player, int amount);

    void givePointPetEXP(Player player, int amount);

    void takePointPetEXP(Player player, int amount);

    void givePointMiningPass(Player player, int amount);

    void takePointMiningPass(Player player, int amount);

    void givePointWeight(Player player, int amount);

    void takePointWeight(Player player, int amount);

    void givePointACs(Player player, int amount);

    void takePointACs(Player player, int amount);

    public int getPoints(String pointCategory, List<Point> pointList);

    public int getPoints(Player player, String pointCategory);
}
