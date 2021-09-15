package monzter.adventurescraft.plugin.utilities.general;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.util.Location;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.StringFlag;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import org.bukkit.entity.Player;

public class ForceOPImpl implements ForceOP {

    @Override
    public void forceOP(Player player, String command) {
        try {
            player.setOp(true);
            player.performCommand(command);
        } finally {
            player.setOp(false);
        }
    }
}
