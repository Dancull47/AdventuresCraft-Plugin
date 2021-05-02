package monzter.adventurescraft.plugin.utilities.luckperms;

import org.bukkit.entity.Player;

public interface PermissionLP {
    void givePermission(Player player, String permission);
    void takePermission(Player player, String permission);
}
