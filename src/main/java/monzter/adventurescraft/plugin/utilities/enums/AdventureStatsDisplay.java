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

    PROJECTILE_DAMAGE(ChatColor.WHITE + "Ӿ Projectile Damage"),
    KNOCKBACK_RESISTANCE(ChatColor.DARK_GRAY + "Ҵ Knockback Resistance"),
    SKILL_DAMAGE(ChatColor.GOLD + "ǂ Skill Damage"),
    MAGIC_DAMAGE(ChatColor.LIGHT_PURPLE + "Δ Magic Damage"),
    COOLDOWN_REDUCTION(ChatColor.GOLD + "Ψ Skill CooldownOld Reduction"),
    BONUS_EXPERIENCE(ChatColor.YELLOW + "Ж Bonus Experience"),

    UNDEAD_DAMAGE(ChatColor.DARK_GREEN + "☠ Undead Damage"),
    HELL_DAMAGE(ChatColor.RED + "☠ Hell Damage"),
    VOID_DAMAGE(ChatColor.DARK_PURPLE + "☠ Void Damage"),

    COINS(ChatColor.YELLOW + "⛂ Coins"),
    EXP(ChatColor.AQUA + "Ξ Experience"),

    FARMING(ChatColor.GREEN + "❦ Farming"),
    FORAGING(ChatColor.DARK_GREEN + " Foraging"),
    SLAYER(ChatColor.DARK_RED + "⚔ Slayer"),
    MINING(ChatColor.DARK_BLUE + "⛏ Mining"),
    ENCHANTING(ChatColor.DARK_PURPLE + "☄ Enchanting"),
    COOKING(ChatColor.GOLD + "♨ Cooking"),
    SPELLFORGING(ChatColor.LIGHT_PURPLE + "☆ Spellforging"),

    ;

    private final String name;

    AdventureStatsDisplay(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
