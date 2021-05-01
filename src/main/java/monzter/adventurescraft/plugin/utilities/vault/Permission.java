package monzter.adventurescraft.plugin.utilities.vault;

import org.bukkit.entity.Player;

public interface Permission {
    void givePermission(Player player, String permission);
    void takePermission(Player player, String permission);
}
