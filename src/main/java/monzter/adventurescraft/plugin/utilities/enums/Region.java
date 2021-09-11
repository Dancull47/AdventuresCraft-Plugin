package monzter.adventurescraft.plugin.utilities.enums;

import org.bukkit.ChatColor;

public enum Region {
    TOWN(ChatColor.GREEN + "Town"),
    FARM(ChatColor.GREEN + "Farm"),
    CAVERN(ChatColor.GOLD + "Cavern"),
    ESTATE(ChatColor.GREEN + "Estate"),
    FOREST(ChatColor.DARK_GREEN + "Forest"),
    HELL(ChatColor.RED + "Hell"),

    ;

    private final String name;

    Region(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}