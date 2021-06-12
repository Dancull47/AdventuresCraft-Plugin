package monzter.adventurescraft.plugin.utilities.enums;

import org.bukkit.ChatColor;

public enum Rarity {
    COMMON(ChatColor.WHITE, "Common"),
    UNCOMMON(ChatColor.DARK_GREEN, "Uncommon"),
    RARE(ChatColor.BLUE, "Rare"),
    LEGENDARY(ChatColor.DARK_PURPLE, "Legendary"),
    EXOTIC(ChatColor.YELLOW, "Exotic"),
    MYTHICAL(ChatColor.LIGHT_PURPLE, "Mythical"),
    GODLY(ChatColor.RED, "Godly"),

    PHOENIX(ChatColor.BLUE, null),
    PHOENIX2(ChatColor.DARK_PURPLE, null),

    DRAGON(ChatColor.BLUE, null),
    DRAGON2(ChatColor.DARK_PURPLE, null);

    private final ChatColor color;
    private final String name;

    Rarity(ChatColor color, String name) {
        this.color = color;
        this.name = name;
    }
    public String getColorString(){
        return getColor().toString();
    }

    public ChatColor getColor() {
        return color;
    }

    public String getName() {
        return name;
    }
}
