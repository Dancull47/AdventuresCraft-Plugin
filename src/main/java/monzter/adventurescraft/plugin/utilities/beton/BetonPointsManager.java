package monzter.adventurescraft.plugin.utilities.beton;

import org.bukkit.entity.Player;

public interface BetonPointsManager {
    void givePoint(Player player, String path, int amount);

    void giveGlobalPoint(String path, int amount);

    void givePointEXP(Player player, int amount);

    void givePointPetEXP(Player player, int amount);

    void givePointMiningPass(Player player, int amount);

    void givePointWeight(Player player, int amount);

    void givePointACs(Player player, int amount);
}
