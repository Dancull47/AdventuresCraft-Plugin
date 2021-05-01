package monzter.adventurescraft.plugin.utilities;

import org.bukkit.ChatColor;

public enum Rarity {
    COMMON(ChatColor.WHITE),
    UNCOMMON(ChatColor.DARK_GREEN),
    RARE(ChatColor.BLUE),
    LEGENDARY(ChatColor.DARK_PURPLE),
    EXOTIC(ChatColor.YELLOW),
    MYTHICAL(ChatColor.LIGHT_PURPLE),
    GODLY(ChatColor.RED);

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
