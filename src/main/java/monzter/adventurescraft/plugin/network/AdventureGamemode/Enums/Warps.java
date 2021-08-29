package monzter.adventurescraft.plugin.network.AdventureGamemode.Enums;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.Arrays;
import java.util.List;

public enum Warps {
    Tutorial(Arrays.asList("Tutorial"), null, new Location(Bukkit.getWorld("Spawn"), -145.5, 17, 3198.5, 90, 0)),
    Town(Arrays.asList("Town", "Spawn", "Hub"), null, new Location(Bukkit.getWorld("Spawn"), 30.5, 17, -2.5, 90, 0)),
    Town_Hall(Arrays.asList("TownHall"), null, new Location(Bukkit.getWorld("Spawn"), -80, 17, -51, 89, 6.4f)),

    Farm(Arrays.asList("Farm"), null, new Location(Bukkit.getWorld("Spawn"), -70, 16, -382, 114F, 0)),
    Forest(Arrays.asList("Forest"), null, new Location(Bukkit.getWorld("Spawn"), 282.39, 16, -33.7, -90F, 0F)),
    Spruce_Forest(Arrays.asList("ForestSpruce", "SpruceForest", "Spruce"), Arrays.asList("warp.SpruceForest"), new Location(Bukkit.getWorld("Spawn"), 320.38, 22.38, -38.5, -100F, -3.6F)),
    Dark_Oak_Forest(Arrays.asList("ForestDarkOak", "DarkOakForest", "DarkOak"), Arrays.asList("warp.DarkOakForest"), new Location(Bukkit.getWorld("Spawn"), 375.48, 26.38, -48.5, -90F, -10F)),
    Birch_Forest(Arrays.asList("ForestBirch", "BirchForest", "Birch"), Arrays.asList("warp.BirchForest"), new Location(Bukkit.getWorld("Spawn"), 419.14, 36.38, -30.2, -90F, -10F)),
    Acacia_Forest(Arrays.asList("ForestAcacia", "AcaciaForest", "Acacia"), Arrays.asList("warp.AcaciaForest"), new Location(Bukkit.getWorld("Spawn"), 473.39, 44.38, -50.2, -90F, -10F)),
    Jungle_Forest(Arrays.asList("ForestJungle", "JungleForest", "Jungle"), Arrays.asList("warp.JungleForest"), new Location(Bukkit.getWorld("Spawn"), 520.43, 54.36, -50.4, -95F, -10F)),
    Hive_Forest(Arrays.asList("ForestHive", "HiveForest", "Hive"), Arrays.asList("warp.HiveForest"), new Location(Bukkit.getWorld("Spawn"), 569, 63.36, -47.8, -85F, 0F)),

    Mine_Entrance(Arrays.asList("MineEntrance","Mines"), null, new Location(Bukkit.getWorld("Spawn"), -75.48, 34.38, -160.7, 180F, 0F)),
    Coal_Mine(Arrays.asList("CoalMine", "Coal"), null, new Location(Bukkit.getWorld("Spawn"), -41.48, 33.38, -891.8, 180, 0F)),
    Gold_Mine(Arrays.asList("GoldMine", "Gold"), Arrays.asList("warp.GoldMine", "warp.goldmine"), new Location(Bukkit.getWorld("Spawn"), -98.36, 4.05, -958.5, -3.25F, -.83F)),
    Redstone_Mine(Arrays.asList("RedstoneMine", "Redstone"), Arrays.asList("warp.RedstoneMine", "warp.redstonemine"), new Location(Bukkit.getWorld("Spawn"), -69.63, 44.0, -1011.69, 180F, 0F)),
    Lapis_Mine(Arrays.asList("LapisMine", "Lapis"), Arrays.asList("warp.LapisMine", "warp.lapismine"), new Location(Bukkit.getWorld("Spawn"), -69.5, 16.0, -1012.8, 180F, 0F)),
    Diamond_Mine(Arrays.asList("DiamondMine", "Diamond"), Arrays.asList("warp.DiamondMine", "warp.diamondmine"), new Location(Bukkit.getWorld("Spawn"), -41.46, 7.0, -1169.73, 180F, 0F)),
    Emerald_Mine(Arrays.asList("EmeraldMine", "Emerald"), Arrays.asList("warp.EmeraldMine", "warp.emeraldmine"), new Location(Bukkit.getWorld("Spawn"), -97.43, 7.06, -1164.65, 180F, 0F)),

    Graveyard(Arrays.asList("Graveyard"), null, new Location(Bukkit.getWorld("Spawn"), -140.9, 17, 48.34, 45F, 0F)),
    Courtyard(Arrays.asList("Courtyard"), Arrays.asList("warp.Courtyard"), new Location(Bukkit.getWorld("Spawn"), -111.5, 16, 248.82, 0F, 0F)),
    Castle(Arrays.asList("Castle"), Arrays.asList("warp.Castle"), new Location(Bukkit.getWorld("Spawn"), -85.2, 17, 434.51, -90, 0F)),
    Morden(Arrays.asList("Morden"), Arrays.asList("warp.Morden"), new Location(Bukkit.getWorld("Spawn"), -66.2, 18, 434.51, -90, 0F)),

    Estate(Arrays.asList("Estate"), Arrays.asList("warp.Estate"), new Location(Bukkit.getWorld("Spawn"), -673.38, 26, -491.57, 0F, 0F)),
    Goblin_Town(Arrays.asList("GoblinTown"), Arrays.asList("warp.GoblinTown","warp.goblintown"), new Location(Bukkit.getWorld("Spawn"), -894.63, 32, -396.59, -15F, 0F)),
    Spirit_Grounds(Arrays.asList("SpiritGrounds"), Arrays.asList("warp.SpiritGrounds","warp.spiritgrounds"), new Location(Bukkit.getWorld("Spawn"), -892.26, 32, -509.90, 180F, 0F)),

    Hell(Arrays.asList("Hell"), Arrays.asList("warp.Hell","cmi.command.portal.hell"), new Location(Bukkit.getWorld("Spawn"), -146.96, 48, -1153.16, 180, 0)),
    Lower_Hell(Arrays.asList("Hell2", "HellBottom","LowerHell","HellLower"), Arrays.asList("warp.Hell2","warp.hellbottom"), new Location(Bukkit.getWorld("Spawn"), -230, 12, -1260.16, 0, 0)),

    Void(Arrays.asList("Void"), Arrays.asList("warp.Void","cmi.command.portal.void"), new Location(Bukkit.getWorld("Spawn"), -217.6, 36, -1107.9, 0F, 0F)),
    Void_Maze(Arrays.asList("VoidMaze"), Arrays.asList("warp.VoidMaze","warp.Void2"), new Location(Bukkit.getWorld("Spawn"), -193.5, 13, -826.5, 0F, 0)),
    Void_Abyss(Arrays.asList("VoidAbyss"), Arrays.asList("warp.VoidAbyss"), new Location(Bukkit.getWorld("Spawn"), -71.5, 25, -762.5, -90F, 0)),

    Enchanter(Arrays.asList("Enchant","Enchanting","Enchanter","Wizard","FastEnchant"), Arrays.asList("Rank3"), new Location(Bukkit.getWorld("Spawn"), -45.54, 15, -13.03, 0, 0)),
    Auction(Arrays.asList("Auction","FastAuction"), Arrays.asList("Rank3"), new Location(Bukkit.getWorld("Spawn"), -38.5, 16, -48.12, 180, 0)),
    Dracula(Arrays.asList("Dracula","FastDracula"), Arrays.asList("Rank3"), new Location(Bukkit.getWorld("Spawn"), -91.5, 24, -1247.41, 0, 0)),
    Reaper(Arrays.asList("Reaper","FastReaper"), Arrays.asList("Rank3"), new Location(Bukkit.getWorld("Spawn"), -99.27, 15, 176.49, -90, 0)),
    Void_Wither(Arrays.asList("VoidWither","FastVoidWither"), Arrays.asList("Rank3"), new Location(Bukkit.getWorld("Spawn"), -237.37, 50, -1239.88, 0, 0)),
    Void_Magma(Arrays.asList("VoidMagma","FastVoidMagma"), Arrays.asList("Rank3"), new Location(Bukkit.getWorld("Spawn"), -161.74, 29, -1224.26, -150, 0)),
    Ghastly(Arrays.asList("Ghastly"), Arrays.asList("Rank3"), new Location(Bukkit.getWorld("Spawn"), -144.5, 12, -1167.5, 115, 0)),
    Bullbo(Arrays.asList("Bullbo"), Arrays.asList("Rank3"), new Location(Bukkit.getWorld("Spawn"), -176.91, 31, -1018.05, 0, 0)),
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

    public String getWarpName() {
        return warpNames.get(0);
    }

    public List<String> getWarpPermissions() {
        return warpPermissions;
    }

    public Location getLocation() {
        return location;
    }
}
