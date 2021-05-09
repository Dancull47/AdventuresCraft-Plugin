package monzter.adventurescraft.plugin.shared.events.extras;

import org.bukkit.ChatColor;

public enum StatsDisplay {
    HP(ChatColor.RED + "❤ HP"),
    MANA(ChatColor.AQUA + "⭐ Mana"),
    DAMAGE(ChatColor.RED + "✜ Damage"),
    SPEED(ChatColor.DARK_GREEN + "✤ Speed"),
    MINING_SPEED(ChatColor.GOLD + "⛏ Mining Speed"),
    MAX_WEIGHT(ChatColor.BLUE + "❂ Max Weight"),
    BLOCK_MULTIPLIER(ChatColor.DARK_RED + "回 Block Multiplier"),
    SELL_MULTIPLIER(ChatColor.DARK_GREEN + "⛂ Sell Multiplier"),
    LUCK_MULTIPLIER(ChatColor.YELLOW + "⚅ Luck Multiplier"),
    EXPERIENCE_MULTIPLIER(ChatColor.GREEN + "۞ Experience Multiplier"),
    PET_EXPERIENCE_MULTIPLIER(ChatColor.AQUA + "❉ Pet Experience Multiplier"),

    EXPERIENCE_AMOUNT(ChatColor.GREEN + "۞ Experience"),
    PET_EXPERIENCE_AMOUNT(ChatColor.AQUA + "❉ Pet Experience"),
    BATTLE_PASS_EXPERIENCE(ChatColor.DARK_PURPLE + "♦ Battle Pass Experience"),
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
