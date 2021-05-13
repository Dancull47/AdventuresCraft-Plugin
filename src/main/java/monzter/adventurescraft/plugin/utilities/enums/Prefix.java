package monzter.adventurescraft.plugin.utilities.enums;

import org.bukkit.ChatColor;

public enum Prefix {
    PREFIX(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "» ");

    private final String prefix;

    Prefix(String prefix) {
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }
}
