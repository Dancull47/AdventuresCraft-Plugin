package monzter.adventurescraft.plugin.network.PrisonGamemode.shared.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.Arrays;
import java.util.List;

public enum Warps {
    Town(Arrays.asList("Town", "Spawn", "Hub"), null, new Location(Bukkit.getWorld("World"), 1680.5, 28.5, 3824.5, 90.5F, 0F)),
    Tutorial(Arrays.asList("Tutorial"), null, new Location(Bukkit.getWorld("World"), 1993.5, 176.5, 1593.5, 89.8F, 4.9F)),
    Mine_A(Arrays.asList("MineA", "A"), Arrays.asList("mines.tp.a"), new Location(Bukkit.getWorld("World"), 1480.5, 29, 3844.5, 135.4F, 2.4F)),
    Mine_B(Arrays.asList("MineB", "B"), Arrays.asList("mines.tp.b"), new Location(Bukkit.getWorld("World"), -368.5, 170, 786.5, 90.6F, -0.8F)),
    Mine_C(Arrays.asList("MineC", "C"), Arrays.asList("mines.tp.c"), new Location(Bukkit.getWorld("World"), -829.5, 167, 774.5, -89.6F, 3.0F)),
    Mine_D(Arrays.asList("MineD", "D"), Arrays.asList("mines.tp.d"), new Location(Bukkit.getWorld("World"), -1351.5, 169, 788.5, 89.6f, -0.3F)),
    Mine_E(Arrays.asList("MineE", "E"), Arrays.asList("mines.tp.e"), new Location(Bukkit.getWorld("World"), -1106.5, 160, 777.5, -89.6F, 3.0F)),
    Mine_F(Arrays.asList("MineF", "F"), Arrays.asList("mines.tp.f"), new Location(Bukkit.getWorld("World"), 143.5, 201, 783.5, 90.6F, 4.4F)),
    Mine_G(Arrays.asList("MineG", "G"), Arrays.asList("mines.tp.g"), new Location(Bukkit.getWorld("World"), -1788.5, 173, 787.5, 89.6F, -0.3F)),
    Mine_H(Arrays.asList("MineH", "H"), Arrays.asList("mines.tp.h"), new Location(Bukkit.getWorld("World"), -2709.5, 172, 792.5, 179.6F, 0.9F)),
    Mine_I(Arrays.asList("MineI", "I"), Arrays.asList("mines.tp.i"), new Location(Bukkit.getWorld("World"), 16.5, 192, 782.5, 90.6F, 4.4F)),
    Mine_J(Arrays.asList("MineJ", "J"), Arrays.asList("mines.tp.j"), new Location(Bukkit.getWorld("World"), -967.5, 175, 780.5, -89.6F, 3.0F)),
    Mine_K(Arrays.asList("MineK", "K"), Arrays.asList("mines.tp.k"), new Location(Bukkit.getWorld("World"), -233.5, 169, 778.5, 90.6F, -0.8F)),
    Mine_L(Arrays.asList("MineL", "L"), Arrays.asList("mines.tp.l"), new Location(Bukkit.getWorld("World"), -1641.5, 162, 786.5, 89.6F, -0.3F)),
    Mine_M(Arrays.asList("MineM", "M"), Arrays.asList("mines.tp.m"), new Location(Bukkit.getWorld("World"), -3032.5, 192, 769.5, -90.1F, 2.0F)),
    Mine_N(Arrays.asList("MineN", "N"), Arrays.asList("mines.tp.n"), new Location(Bukkit.getWorld("World"), -1502.5, 166, 787.5, 89.6F, -0.3F)),
    Mine_O(Arrays.asList("MineO", "O"), Arrays.asList("mines.tp.o"), new Location(Bukkit.getWorld("World"), -1248.5, 180, 786.5, -90.2F, 2.2F)),
    Mine_P(Arrays.asList("MineP", "P"), Arrays.asList("mines.tp.p"), new Location(Bukkit.getWorld("World"), -515.5, 175, 787.5, 90.6F, -0.8F)),
    Mine_Q(Arrays.asList("MineQ", "Q"), Arrays.asList("mines.tp.q"), new Location(Bukkit.getWorld("World"), -1957.5, 170, 758.5, -89.6F, 0.5F)),
    Mine_R(Arrays.asList("MineR", "R"), Arrays.asList("mines.tp.r"), new Location(Bukkit.getWorld("World"), -3190.5, 192, 770.5, -90.1F, 2.0F)),
    Mine_S(Arrays.asList("MineS", "S"), Arrays.asList("mines.tp.s"), new Location(Bukkit.getWorld("World"), -659.5, 168, 781.5, 90.6F, -0.8F)),
    Mine_T(Arrays.asList("MineT", "T"), Arrays.asList("mines.tp.t"), new Location(Bukkit.getWorld("World"), -2874.5, 189, 770.5, -90.1F, 2.0F)),
    Mine_U(Arrays.asList("MineU", "U"), Arrays.asList("mines.tp.u"), new Location(Bukkit.getWorld("World"), -2564.5, 172, 786.5, 179.6F, 0.9F)),
    Mine_V(Arrays.asList("MineV", "V"), Arrays.asList("mines.tp.v"), new Location(Bukkit.getWorld("World"), -2257.5, 160, 770.5, -89.6F, 0.5F)),
    Mine_W(Arrays.asList("MineW", "W"), Arrays.asList("mines.tp.w"), new Location(Bukkit.getWorld("World"), -2397.5, 172, 803.5, 179.6F, 0.9F)),
    Mine_X(Arrays.asList("MineX", "X"), Arrays.asList("mines.tp.x"), new Location(Bukkit.getWorld("World"), -103.5, 175, 778.5, 90.6F, 4.4F)),
    Mine_Y(Arrays.asList("MineY", "Y"), Arrays.asList("mines.tp.y"), new Location(Bukkit.getWorld("World"), -2110.5, 175, 766.5, -89.6F, 0.5F)),
    Mine_Z(Arrays.asList("MineZ", "Z"), Arrays.asList("mines.tp.z"), new Location(Bukkit.getWorld("World"), -3348.5, 192, 770.5, -90.1f, 2.0F)),
    Enchanting_Library(Arrays.asList("Enchanting", "Enchanter", "Enchant", "EnchantingLibrary"), null, new Location(Bukkit.getWorld("World"), 1654.5, 28, 3805.5, 180F, 5.2F)),
    Pet_Shop(Arrays.asList("Pet", "Pets", "PetShop"), null, new Location(Bukkit.getWorld("World"), 1637.5, 28, 3836.5, 1.2F, -0.6F)),
    Crates(Arrays.asList("Crates", "Crate"), null, new Location(Bukkit.getWorld("World"), 1692.5, 28.5, 3785.5, -90F, 1F)),

    Beach(Arrays.asList("Beach"), null, new Location(Bukkit.getWorld("World"), 1180.5, 209.5, 2458.5, -180F, 0F)),
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
