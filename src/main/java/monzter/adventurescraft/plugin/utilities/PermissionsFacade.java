package monzter.adventurescraft.plugin.utilities;

import org.bukkit.entity.Player;

public interface PermissionsFacade {
    void givePermission(Player player, String permission);
    void takePermission(Player player, String permission);
}
