package monzter.adventurescraft.plugin.utilities.beton;

import org.bukkit.entity.Player;
import pl.betoncraft.betonquest.BetonQuest;
import pl.betoncraft.betonquest.Point;

import java.util.List;

public class BetonPointsManagerStatic {
    private final BetonQuest betonQuest;

    public BetonPointsManagerStatic(BetonQuest betonQuest) {
        this.betonQuest = betonQuest;
    }

    public static int getPoints(Player player, String pointCategory) {
        final List<Point> points = BetonQuest.getInstance().getPlayerData(player.getUniqueId().toString()).getPoints();
        for (final Point point : points)
            if (point.getCategory().equalsIgnoreCase(pointCategory))
                return point.getCount();
        return 0;
    }

}
