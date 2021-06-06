package monzter.adventurescraft.plugin.utilities.enums;

import org.bukkit.ChatColor;

public enum RanksDisplay {
    EXPLORER(ChatColor.WHITE + "[" + ChatColor.GREEN + "Explorer" + ChatColor.WHITE + "]"),
    EXPLORER_WO_PREFIX(ChatColor.GREEN + "Explorer"),
    ADVENTURER(ChatColor.WHITE + "[" + ChatColor.BLUE + "Adventurer" + ChatColor.WHITE + "]"),
    ADVENTURER_WO_PREFIX(ChatColor.BLUE + "Adventurer"),
    CONQUERER(ChatColor.WHITE + "[" + ChatColor.RED + "Conquerer" + ChatColor.WHITE + "]"),
    CONQUERER_WO_PREFIX(ChatColor.RED + "Conquerer"),


    ;

    private final String name;

    RanksDisplay(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
