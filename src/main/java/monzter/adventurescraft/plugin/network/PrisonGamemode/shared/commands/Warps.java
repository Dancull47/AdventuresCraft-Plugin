package monzter.adventurescraft.plugin.network.PrisonGamemode.shared.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.Arrays;
import java.util.List;

public enum Warps {
    Town(Arrays.asList("Town", "Spawn"), null, new Location(Bukkit.getWorld("World"), 1680.5, 28.5, 3824.5, 90.5F, 0F)),
    Mine_A(Arrays.asList("MineA", "A"), null, new Location(Bukkit.getWorld("World"), 1098.5, 198, 1603.5, 8.2F, 90F)),
    Mine_B(Arrays.asList("MineB", "B"), Arrays.asList("mines.tp.b"), new Location(Bukkit.getWorld("World"), -368.5, 170, 786.5, -0.8F, 90.6F)),
    ;

    public final List<String> warpNames;
    public final List<String> warpPermissions;
    public final Location location;

    Warps(List<String> warpNames, List<String> warpPermissions, Location location) {
        this.warpNames = warpNames;
        this.warpPermissions = warpPermissions;
        this.location = location;
    }

    public List<String> getWarpNames() {
        return warpNames;
    }

    public List<String> getWarpPermissions() {
        return warpPermissions;
    }

    public Location getLocation() {
        return location;
    }
}
