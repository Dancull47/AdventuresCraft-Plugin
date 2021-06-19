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

    REAPER(ChatColor.RED, "Reaper"),
    MORDEN(ChatColor.RED, "Morden the Undead"),
    VOID_DRACULA(ChatColor.DARK_PURPLE, "Void Dracula"),
    DRYAD(ChatColor.RED, "Dryad"),
    GOBLIN_CHIEF(ChatColor.RED, "Goblin Chief"),
    VOID_WITHER(ChatColor.DARK_PURPLE, "Void Wither"),
    VOID_MAGMA(ChatColor.DARK_PURPLE, "Void Magma"),
    GHASTLY(ChatColor.RED, "Ghastly"),
    BULBLIN(ChatColor.DARK_PURPLE, "Void Bulblin"),
    BULLBO(ChatColor.DARK_PURPLE, "Void Bullbo"),
    ENCHANTRESS(ChatColor.DARK_PURPLE, "Enchantress"),
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
