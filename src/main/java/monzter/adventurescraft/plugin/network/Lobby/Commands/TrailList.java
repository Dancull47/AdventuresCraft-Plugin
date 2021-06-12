package monzter.adventurescraft.plugin.network.Lobby.Commands;

import monzter.adventurescraft.plugin.utilities.enums.Rarity;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public enum TrailList {
    FIRE1(Rarity.COMMON, "FIRE_TRAIL", "Trail Crate"),
    FIRE2(Rarity.COMMON, "FIRE_TRAIL2", "Trail Crate"),
    FIRE3(Rarity.COMMON, "FIRE_TRAIL3", "Trail Crate"),
    FIRE4(Rarity.COMMON, "FIRE_TRAIL4", "Trail Crate"),
    FIRE5(Rarity.COMMON, "FIRE_TRAIL5", "Trail Crate"),
    MUSIC1(Rarity.COMMON, "MUSIC_TRAIL", "Trail Crate"),
    MUSIC2(Rarity.COMMON, "MUSIC_TRAIL2", "Trail Crate"),
    MUSIC3(Rarity.COMMON, "MUSIC_TRAIL3", "Trail Crate"),
    MUSIC4(Rarity.COMMON, "MUSIC_TRAIL4", "Trail Crate"),
    MUSIC5(Rarity.COMMON, "MUSIC_TRAIL5", "Trail Crate"),
    PORTAL1(Rarity.COMMON, "PORTAL_TRAIL", "Trail Crate"),
    PORTAL2(Rarity.COMMON, "PORTAL_TRAIL2", "Trail Crate"),
    PORTAL3(Rarity.COMMON, "PORTAL_TRAIL3", "Trail Crate"),
    PORTAL4(Rarity.COMMON, "PORTAL_TRAIL4", "Trail Crate"),
    PORTAL5(Rarity.COMMON, "PORTAL_TRAIL5", "Trail Crate"),
    CRUMB1(Rarity.COMMON, "CRUMB_TRAIL", "Trail Crate"),
    CRUMB2(Rarity.COMMON, "CRUMB_TRAIL2", "Trail Crate"),
    CRUMB3(Rarity.COMMON, "CRUMB_TRAIL3", "Trail Crate"),
    CRUMB4(Rarity.COMMON, "CRUMB_TRAIL4", "Trail Crate"),
    CRUMB5(Rarity.COMMON, "CRUMB_TRAIL5", "Trail Crate"),

    DAMAGE1(Rarity.UNCOMMON, "DAMAGE_TRAIL", "Trail Crate"),
    DAMAGE2(Rarity.UNCOMMON, "DAMAGE_TRAIL2", "Trail Crate"),
    DAMAGE3(Rarity.UNCOMMON, "DAMAGE_TRAIL3", "Trail Crate"),
    DAMAGE4(Rarity.UNCOMMON, "DAMAGE_TRAIL4", "Trail Crate"),
    DAMAGE5(Rarity.UNCOMMON, "DAMAGE_TRAIL5", "Trail Crate"),
    SLIME1(Rarity.UNCOMMON, "SLIME_TRAIL", "Trail Crate"),
    SLIME2(Rarity.UNCOMMON, "SLIME_TRAIL2", "Trail Crate"),
    SLIME3(Rarity.UNCOMMON, "SLIME_TRAIL3", "Trail Crate"),
    SLIME4(Rarity.UNCOMMON, "SLIME_TRAIL4", "Trail Crate"),
    SLIME5(Rarity.UNCOMMON, "SLIME_TRAIL5", "Trail Crate"),
    REDSTONE1(Rarity.UNCOMMON, "REDSTONE_TRAIL", "Trail Crate"),
    REDSTONE2(Rarity.UNCOMMON, "REDSTONE_TRAIL2", "Trail Crate"),
    REDSTONE3(Rarity.UNCOMMON, "REDSTONE_TRAIL3", "Trail Crate"),
    REDSTONE4(Rarity.UNCOMMON, "REDSTONE_TRAIL4", "Trail Crate"),
    REDSTONE5(Rarity.UNCOMMON, "REDSTONE_TRAIL5", "Trail Crate"),

    SPLASH1(Rarity.RARE, "SPLASH_TRAIL", "Trail Crate"),
    SPLASH2(Rarity.RARE, "SPLASH_TRAIL2", "Trail Crate"),
    SPLASH3(Rarity.RARE, "SPLASH_TRAIL3", "Trail Crate"),
    SPLASH4(Rarity.RARE, "SPLASH_TRAIL4", "Trail Crate"),
    SPLASH5(Rarity.RARE, "SPLASH_TRAIL5", "Trail Crate"),
    STAR1(Rarity.RARE, "STAR_TRAIL", "Trail Crate"),
    STAR2(Rarity.RARE, "STAR_TRAIL2", "Trail Crate"),
    STAR3(Rarity.RARE, "STAR_TRAIL3", "Trail Crate"),
    STAR4(Rarity.RARE, "STAR_TRAIL4", "Trail Crate"),
    STAR5(Rarity.RARE, "STAR_TRAIL5", "Trail Crate"),
    SPELL1(Rarity.RARE, "SPELL_TRAIL", "Trail Crate"),
    SPELL2(Rarity.RARE, "SPELL_TRAIL2", "Trail Crate"),
    SPELL3(Rarity.RARE, "SPELL_TRAIL3", "Trail Crate"),
    SPELL4(Rarity.RARE, "SPELL_TRAIL4", "Trail Crate"),
    SPELL5(Rarity.RARE, "SPELL_TRAIL5", "Trail Crate"),

    BLIZZARD1(Rarity.LEGENDARY, "BLIZZARD_TRAIL", "Trail Crate"),
    BLIZZARD2(Rarity.LEGENDARY, "BLIZZARD_TRAIL2", "Trail Crate"),
    BLIZZARD3(Rarity.LEGENDARY, "BLIZZARD_TRAIL3", "Trail Crate"),
    BLIZZARD4(Rarity.LEGENDARY, "BLIZZARD_TRAIL4", "Trail Crate"),
    BLIZZARD5(Rarity.LEGENDARY, "BLIZZARD_TRAIL5", "Trail Crate"),
    SNOW1(Rarity.LEGENDARY, "SNOW_TRAIL", "Trail Crate"),
    SNOW2(Rarity.LEGENDARY, "SNOW_TRAIL2", "Trail Crate"),
    SNOW3(Rarity.LEGENDARY, "SNOW_TRAIL3", "Trail Crate"),
    SNOW4(Rarity.LEGENDARY, "SNOW_TRAIL4", "Trail Crate"),
    SNOW5(Rarity.LEGENDARY, "SNOW_TRAIL5", "Trail Crate"),
    SMOKEY1(Rarity.LEGENDARY, "SMOKEY_TRAIL", "Trail Crate"),
    SMOKEY2(Rarity.LEGENDARY, "SMOKEY_TRAIL2", "Trail Crate"),
    SMOKEY3(Rarity.LEGENDARY, "SMOKEY_TRAIL3", "Trail Crate"),
    SMOKEY4(Rarity.LEGENDARY, "SMOKEY_TRAIL4", "Trail Crate"),
    SMOKEY5(Rarity.LEGENDARY, "SMOKEY_TRAIL5", "Trail Crate"),

    LUCKY1(Rarity.EXOTIC, "LUCKY_TRAIL", "Trail Crate"),
    LUCKY2(Rarity.EXOTIC, "LUCKY_TRAIL2", "Trail Crate"),
    LUCKY3(Rarity.EXOTIC, "LUCKY_TRAIL3", "Trail Crate"),
    LUCKY4(Rarity.EXOTIC, "LUCKY_TRAIL4", "Trail Crate"),
    LUCKY5(Rarity.EXOTIC, "LUCKY_TRAIL5", "Trail Crate"),
    CLOUDY1(Rarity.EXOTIC, "CLOUDY_TRAIL", "Trail Crate"),
    CLOUDY2(Rarity.EXOTIC, "CLOUDY_TRAIL2", "Trail Crate"),
    CLOUDY3(Rarity.EXOTIC, "CLOUDY_TRAIL3", "Trail Crate"),
    CLOUDY4(Rarity.EXOTIC, "CLOUDY_TRAIL4", "Trail Crate"),
    CLOUDY5(Rarity.EXOTIC, "CLOUDY_TRAIL5", "Trail Crate"),
    SLASH1(Rarity.EXOTIC, "SLASH_TRAIL", "Trail Crate"),
    SLASH2(Rarity.EXOTIC, "SLASH_TRAIL2", "Trail Crate"),
    SLASH3(Rarity.EXOTIC, "SLASH_TRAIL3", "Trail Crate"),
    SLASH4(Rarity.EXOTIC, "SLASH_TRAIL4", "Trail Crate"),
    SLASH5(Rarity.EXOTIC, "SLASH_TRAIL5", "Trail Crate"),
    ;

    private Rarity rarity;
    private String name;
    private String unlockMethod;

    TrailList(Rarity rarity, String name, String unlockMethod) {
        this.rarity = rarity;
        this.name = name;
        this.unlockMethod = unlockMethod;
    }

    public String getName() {
        return name;
    }

    public String getUnlockMethod() {
        return unlockMethod;
    }

    public Rarity getRarity() {
        return rarity;
    }

    private static final Map<Rarity, List<TrailList>> RARITY_LIST_MAP = new EnumMap<>(Rarity.class);

    public static List<TrailList> getShop(Rarity rarity) {
        return RARITY_LIST_MAP.computeIfAbsent(rarity, key -> Arrays.stream(values())
                .filter(shop -> shop.getRarity().equals(key))
                .collect(Collectors.toList()));
    }

}
