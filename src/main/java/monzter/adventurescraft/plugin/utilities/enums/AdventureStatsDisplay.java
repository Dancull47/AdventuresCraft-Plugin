package monzter.adventurescraft.plugin.utilities.enums;

import org.bukkit.ChatColor;

public enum AdventureStatsDisplay {
    HP(ChatColor.GREEN + "❤ HP"),
    MANA(ChatColor.AQUA + "⭐ Mana"),
    ARMOR(ChatColor.YELLOW + "⛨ Mana"),
    SPEED(ChatColor.DARK_GREEN + "✤ Speed"),
    DAMAGE(ChatColor.RED + "✜ Damage"),
    ATTACK_SPEED(ChatColor.DARK_AQUA + "⌭ Attack Speed"),
    CRITICAL_CHANCE(ChatColor.GOLD + "✧ Crit Chance"),
    CRITICAL_DAMAGE(ChatColor.DARK_RED + "✦ Crit Damage"),

    COINS(ChatColor.YELLOW + "⛂ Coins"),
    ;

    private final String name;

    AdventureStatsDisplay(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
