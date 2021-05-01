package monzter.adventurescraft.plugin.utilities.general;

import org.bukkit.entity.Player;

public interface ChanceCheck {
    boolean chanceCheck(double chance);
    boolean chanceCheck(double chance, Player player, int amount, String type, String id, String displayName);
    boolean chanceCheck(double chance, Player player, int amount, String type, String id, String displayName, boolean silent);
}
