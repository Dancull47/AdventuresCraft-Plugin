package monzter.adventurescraft.plugin.utilities.enums;

import org.bukkit.ChatColor;

public enum Rarity {
    COMMON(ChatColor.WHITE),
    UNCOMMON(ChatColor.DARK_GREEN),
    RARE(ChatColor.BLUE),
    LEGENDARY(ChatColor.DARK_PURPLE),
    EXOTIC(ChatColor.YELLOW),
    MYTHICAL(ChatColor.LIGHT_PURPLE),
    GODLY(ChatColor.RED),

    PHOENIX(ChatColor.BLUE),
    PHOENIX2(ChatColor.DARK_PURPLE),

    DRAGON(ChatColor.BLUE),
    DRAGON2(ChatColor.DARK_PURPLE);

    private final ChatColor color;

    Rarity(ChatColor color) {
        this.color = color;
    }
    public String getColorString(){
        return getColor().toString();
    }

    public ChatColor getColor() {
        return color;
    }
}
