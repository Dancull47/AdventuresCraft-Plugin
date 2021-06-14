package monzter.adventurescraft.plugin.utilities.enums;

import org.bukkit.ChatColor;

public enum CrateList {
    UNDEAD(ChatColor.DARK_GREEN, "Undead"),
    HELL(ChatColor.DARK_RED, "Hell"),
    PROFESSION(ChatColor.BLUE, "Profession"),

    MAGICAL(ChatColor.DARK_PURPLE, "Magical"),
    BORG(ChatColor.RED, "Borg"),
    ENCHANTED_BOX(ChatColor.WHITE, "Enchanted Box"),
    ENCHANTED_BOX2(ChatColor.DARK_GREEN, "Enchanted Box"),
    ENCHANTED_BOX3(ChatColor.BLUE, "Enchanted Box"),
    ;

    private final ChatColor color;
    private final String name;

    CrateList(ChatColor color, String name) {
        this.color = color;
        this.name = name;
    }

    public String getColorString() {
        return getColor().toString();
    }

    public ChatColor getColor() {
        return color;
    }

    public String getName() {
        return name;
    }
}
