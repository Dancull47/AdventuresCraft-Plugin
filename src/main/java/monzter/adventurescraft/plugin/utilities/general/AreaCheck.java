package monzter.adventurescraft.plugin.utilities.general;

import org.bukkit.entity.Player;

public interface AreaCheck {
    boolean areaCheck(Player Player, String location);

    public String getAreaName(Player player);
}
