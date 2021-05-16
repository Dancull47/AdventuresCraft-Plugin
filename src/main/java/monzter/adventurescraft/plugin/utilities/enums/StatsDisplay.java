package monzter.adventurescraft.plugin.utilities.enums;

import org.bukkit.ChatColor;

public enum StatsDisplay {
    HP(ChatColor.GREEN + "❤ HP"),
    MANA(ChatColor.AQUA + "⭐ Mana"),
    DAMAGE(ChatColor.RED + "✜ Damage"),
    SPEED(ChatColor.DARK_GREEN + "✤ Speed"),
    ARMOR(ChatColor.YELLOW + "⛨ Armor"),
    MINING_SPEED(ChatColor.GOLD + "⛏ Mining Speed"),
    MAX_WEIGHT(ChatColor.BLUE + "❂ Max Weight"),
    MAX_WEIGHT_MULTIPLIER(ChatColor.DARK_PURPLE + "❂ Max Weight Multiplier"),
    BLOCK_MULTIPLIER(ChatColor.DARK_RED + "回 Block Multiplier"),
    SELL_MULTIPLIER(ChatColor.DARK_GREEN + "⛂ Sell Multiplier"),
    LUCK_MULTIPLIER(ChatColor.YELLOW + "⚅ Luck Multiplier"),
    EXPERIENCE_MULTIPLIER(ChatColor.GREEN + "۞ Experience Multiplier"),
    PET_EXPERIENCE_MULTIPLIER(ChatColor.AQUA + "❉ Pet Experience Multiplier"),

    MONEY_AMOUNT(ChatColor.YELLOW + "⛂ Money"),
    EXPERIENCE_AMOUNT(ChatColor.GREEN + "۞ Experience"),
    PET_EXPERIENCE_AMOUNT(ChatColor.AQUA + "❉ Pet Experience"),
    MINING_PASS_EXPERIENCE(ChatColor.YELLOW + "♦ Mining Pass Experience"),
    ADVENTURE_COINS(ChatColor.RED + "◎ Adventure Coins"),
    ;

    private final String name;

    StatsDisplay(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
