package monzter.adventurescraft.plugin.utilities.enums;

import org.bukkit.ChatColor;

public enum IslandRanks {
    COOP(ChatColor.DARK_GREEN + "Coop"),
    TRUSTED(ChatColor.DARK_AQUA + "Trusted"),
    MEMBER(ChatColor.AQUA + "Member"),
    SUBOWNER(ChatColor.GOLD + "Sub-Owner"),
    OWNER(ChatColor.RED + "Owner");

    private final String rank;

    IslandRanks(String rank) {
        this.rank = rank;
    }

}
