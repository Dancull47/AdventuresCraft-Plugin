package monzter.adventurescraft.plugin.utilities.luckperms;

import org.bukkit.entity.Player;

import java.util.concurrent.TimeUnit;

public interface PermissionLP {
    void givePermission(Player player, String permission);
    void givePermission(Player player, String permission, String context);
    void takePermission(Player player, String permission);
    void giveTempPermission(Player player, String permission, int duration, String unit);
    void giveTempPermission(Player player, String permission, String context, int duration, TimeUnit unit);
    void takePermission(Player player, String permission, String context);

    }
