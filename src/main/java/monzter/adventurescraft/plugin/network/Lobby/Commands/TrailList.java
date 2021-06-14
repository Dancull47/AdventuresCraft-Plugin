package monzter.adventurescraft.plugin.network.Lobby.Commands;

import io.lumine.xikage.mythicmobs.MythicMobs;
import me.lucko.helper.random.Weighted;
import monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.Commands.DropTables.Crates;
import monzter.adventurescraft.plugin.network.PrisonGamemode.shared.dropTables.ItemGenerator;
import monzter.adventurescraft.plugin.utilities.enums.CrateList;
import monzter.adventurescraft.plugin.utilities.enums.Rarity;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.stream.Collectors;

public enum TrailList implements Weighted, ItemGenerator {
    FIRE1(Rarity.COMMON, "FIRE_TRAIL", "Trail Crate", .5),
    FIRE2(Rarity.COMMON, "FIRE_TRAIL2", "Trail Crate", .5),
    FIRE3(Rarity.COMMON, "FIRE_TRAIL3", "Trail Crate", .5),
    FIRE4(Rarity.COMMON, "FIRE_TRAIL4", "Trail Crate", .5),
    FIRE5(Rarity.COMMON, "FIRE_TRAIL5", "Trail Crate", .5),
    MUSIC1(Rarity.COMMON, "MUSIC_TRAIL", "Trail Crate", .5),
    MUSIC2(Rarity.COMMON, "MUSIC_TRAIL2", "Trail Crate", .5),
    MUSIC3(Rarity.COMMON, "MUSIC_TRAIL3", "Trail Crate", .5),
    MUSIC4(Rarity.COMMON, "MUSIC_TRAIL4", "Trail Crate", .5),
    MUSIC5(Rarity.COMMON, "MUSIC_TRAIL5", "Trail Crate", .5),
    PORTAL1(Rarity.COMMON, "PORTAL_TRAIL", "Trail Crate", .5),
    PORTAL2(Rarity.COMMON, "PORTAL_TRAIL2", "Trail Crate", .5),
    PORTAL3(Rarity.COMMON, "PORTAL_TRAIL3", "Trail Crate", .5),
    PORTAL4(Rarity.COMMON, "PORTAL_TRAIL4", "Trail Crate", .5),
    PORTAL5(Rarity.COMMON, "PORTAL_TRAIL5", "Trail Crate", .5),
    CRUMB1(Rarity.COMMON, "CRUMB_TRAIL", "Trail Crate", .5),
    CRUMB2(Rarity.COMMON, "CRUMB_TRAIL2", "Trail Crate", .5),
    CRUMB3(Rarity.COMMON, "CRUMB_TRAIL3", "Trail Crate", .5),
    CRUMB4(Rarity.COMMON, "CRUMB_TRAIL4", "Trail Crate", .5),
    CRUMB5(Rarity.COMMON, "CRUMB_TRAIL5", "Trail Crate", .5),
    ENCHANTED1(Rarity.COMMON, "ENCHANTED_TRAIL", "Trail Crate", .5),
    ENCHANTED2(Rarity.COMMON, "ENCHANTED_TRAIL2", "Trail Crate", .5),
    ENCHANTED3(Rarity.COMMON, "ENCHANTED_TRAIL3", "Trail Crate", .5),
    ENCHANTED4(Rarity.COMMON, "ENCHANTED_TRAIL4", "Trail Crate", .5),
    ENCHANTED5(Rarity.COMMON, "ENCHANTED_TRAIL5", "Trail Crate", .5),
    DUSTY1(Rarity.COMMON, "DUSTY_TRAIL", "Trail Crate", .5),
    DUSTY2(Rarity.COMMON, "DUSTY_TRAIL2", "Trail Crate", .5),
    DUSTY3(Rarity.COMMON, "DUSTY_TRAIL3", "Trail Crate", .5),
    DUSTY4(Rarity.COMMON, "DUSTY_TRAIL4", "Trail Crate", .5),
    DUSTY5(Rarity.COMMON, "DUSTY_TRAIL5", "Trail Crate", .5),

    SNEAKY1(Rarity.UNCOMMON, "SNEAKY_TRAIL", "Trail Crate", .30),
    SNEAKY2(Rarity.UNCOMMON, "SNEAKY_TRAIL2", "Trail Crate", .30),
    SNEAKY3(Rarity.UNCOMMON, "SNEAKY_TRAIL3", "Trail Crate", .30),
    SNEAKY4(Rarity.UNCOMMON, "SNEAKY_TRAIL4", "Trail Crate", .30),
    SNEAKY5(Rarity.UNCOMMON, "SNEAKY_TRAIL5", "Trail Crate", .30),
    DAMAGE1(Rarity.UNCOMMON, "DAMAGE_TRAIL", "Trail Crate", .30),
    DAMAGE2(Rarity.UNCOMMON, "DAMAGE_TRAIL2", "Trail Crate", .30),
    DAMAGE3(Rarity.UNCOMMON, "DAMAGE_TRAIL3", "Trail Crate", .30),
    DAMAGE4(Rarity.UNCOMMON, "DAMAGE_TRAIL4", "Trail Crate", .30),
    DAMAGE5(Rarity.UNCOMMON, "DAMAGE_TRAIL5", "Trail Crate", .30),
    SLIME1(Rarity.UNCOMMON, "SLIME_TRAIL", "Trail Crate", .30),
    SLIME2(Rarity.UNCOMMON, "SLIME_TRAIL2", "Trail Crate", .30),
    SLIME3(Rarity.UNCOMMON, "SLIME_TRAIL3", "Trail Crate", .30),
    SLIME4(Rarity.UNCOMMON, "SLIME_TRAIL4", "Trail Crate", .30),
    SLIME5(Rarity.UNCOMMON, "SLIME_TRAIL5", "Trail Crate", .30),
    REDSTONE1(Rarity.UNCOMMON, "REDSTONE_TRAIL", "Trail Crate", .30),
    REDSTONE2(Rarity.UNCOMMON, "REDSTONE_TRAIL2", "Trail Crate", .30),
    REDSTONE3(Rarity.UNCOMMON, "REDSTONE_TRAIL3", "Trail Crate", .30),
    REDSTONE4(Rarity.UNCOMMON, "REDSTONE_TRAIL4", "Trail Crate", .30),
    REDSTONE5(Rarity.UNCOMMON, "REDSTONE_TRAIL5", "Trail Crate", .30),
    DOLPHIN1(Rarity.UNCOMMON, "DOLPHIN_TRAIL", "Trail Crate", .30),
    DOLPHIN2(Rarity.UNCOMMON, "DOLPHIN_TRAIL2", "Trail Crate", .30),
    DOLPHIN3(Rarity.UNCOMMON, "DOLPHIN_TRAIL3", "Trail Crate", .30),
    DOLPHIN4(Rarity.UNCOMMON, "DOLPHIN_TRAIL4", "Trail Crate", .30),
    DOLPHIN5(Rarity.UNCOMMON, "DOLPHIN_TRAIL5", "Trail Crate", .30),

    SPLASH1(Rarity.RARE, "SPLASH_TRAIL", "Trail Crate", .20),
    SPLASH2(Rarity.RARE, "SPLASH_TRAIL2", "Trail Crate", .20),
    SPLASH3(Rarity.RARE, "SPLASH_TRAIL3", "Trail Crate", .20),
    SPLASH4(Rarity.RARE, "SPLASH_TRAIL4", "Trail Crate", .20),
    SPLASH5(Rarity.RARE, "SPLASH_TRAIL5", "Trail Crate", .20),
    STAR1(Rarity.RARE, "STAR_TRAIL", "Trail Crate", .20),
    STAR2(Rarity.RARE, "STAR_TRAIL2", "Trail Crate", .20),
    STAR3(Rarity.RARE, "STAR_TRAIL3", "Trail Crate", .20),
    STAR4(Rarity.RARE, "STAR_TRAIL4", "Trail Crate", .20),
    STAR5(Rarity.RARE, "STAR_TRAIL5", "Trail Crate", .20),
    SPELL1(Rarity.RARE, "SPELL_TRAIL", "Trail Crate", .20),
    SPELL2(Rarity.RARE, "SPELL_TRAIL2", "Trail Crate", .20),
    SPELL3(Rarity.RARE, "SPELL_TRAIL3", "Trail Crate", .20),
    SPELL4(Rarity.RARE, "SPELL_TRAIL4", "Trail Crate", .20),
    SPELL5(Rarity.RARE, "SPELL_TRAIL5", "Trail Crate", .20),
    LAVA1(Rarity.RARE, "LAVA_TRAIL", "Trail Crate", .20),
    LAVA2(Rarity.RARE, "LAVA_TRAIL2", "Trail Crate", .20),
    LAVA3(Rarity.RARE, "LAVA_TRAIL3", "Trail Crate", .20),
    LAVA4(Rarity.RARE, "LAVA_TRAIL4", "Trail Crate", .20),
    LAVA5(Rarity.RARE, "LAVA_TRAIL5", "Trail Crate", .20),

    BLIZZARD1(Rarity.LEGENDARY, "BLIZZARD_TRAIL", "Trail Crate", .10),
    BLIZZARD2(Rarity.LEGENDARY, "BLIZZARD_TRAIL2", "Trail Crate", .10),
    BLIZZARD3(Rarity.LEGENDARY, "BLIZZARD_TRAIL3", "Trail Crate", .10),
    BLIZZARD4(Rarity.LEGENDARY, "BLIZZARD_TRAIL4", "Trail Crate", .10),
    BLIZZARD5(Rarity.LEGENDARY, "BLIZZARD_TRAIL5", "Trail Crate", .10),
    FIREWORK1(Rarity.LEGENDARY, "FIREWORK_TRAIL", "Trail Crate", .10),
    FIREWORK2(Rarity.LEGENDARY, "FIREWORK_TRAIL2", "Trail Crate", .10),
    FIREWORK3(Rarity.LEGENDARY, "FIREWORK_TRAIL3", "Trail Crate", .10),
    FIREWORK4(Rarity.LEGENDARY, "FIREWORK_TRAIL4", "Trail Crate", .10),
    FIREWORK5(Rarity.LEGENDARY, "FIREWORK_TRAIL5", "Trail Crate", .10),
    SNOW1(Rarity.LEGENDARY, "SNOW_TRAIL", "Trail Crate", .10),
    SNOW2(Rarity.LEGENDARY, "SNOW_TRAIL2", "Trail Crate", .10),
    SNOW3(Rarity.LEGENDARY, "SNOW_TRAIL3", "Trail Crate", .10),
    SNOW4(Rarity.LEGENDARY, "SNOW_TRAIL4", "Trail Crate", .10),
    SNOW5(Rarity.LEGENDARY, "SNOW_TRAIL5", "Trail Crate", .10),
    SMOKEY1(Rarity.LEGENDARY, "SMOKEY_TRAIL", "Trail Crate", .10),
    SMOKEY2(Rarity.LEGENDARY, "SMOKEY_TRAIL2", "Trail Crate", .10),
    SMOKEY3(Rarity.LEGENDARY, "SMOKEY_TRAIL3", "Trail Crate", .10),
    SMOKEY4(Rarity.LEGENDARY, "SMOKEY_TRAIL4", "Trail Crate", .10),
    SMOKEY5(Rarity.LEGENDARY, "SMOKEY_TRAIL5", "Trail Crate", .10),
    HEART1(Rarity.LEGENDARY, "HEART_TRAIL", "Trail Crate", .10),
    HEART2(Rarity.LEGENDARY, "HEART_TRAIL2", "Trail Crate", .10),
    HEART3(Rarity.LEGENDARY, "HEART_TRAIL3", "Trail Crate", .10),
    HEART4(Rarity.LEGENDARY, "HEART_TRAIL4", "Trail Crate", .10),
    HEART5(Rarity.LEGENDARY, "HEART_TRAIL5", "Trail Crate", .10),
    ENDER_LIGHT1(Rarity.LEGENDARY, "ENDER_LIGHT_TRAIL", "Trail Crate", .10),
    ENDER_LIGHT2(Rarity.LEGENDARY, "ENDER_LIGHT_TRAIL2", "Trail Crate", .10),
    ENDER_LIGHT3(Rarity.LEGENDARY, "ENDER_LIGHT_TRAIL3", "Trail Crate", .10),
    ENDER_LIGHT4(Rarity.LEGENDARY, "ENDER_LIGHT_TRAIL4", "Trail Crate", .10),
    ENDER_LIGHT5(Rarity.LEGENDARY, "ENDER_LIGHT_TRAIL5", "Trail Crate", .10),

    LUCKY1(Rarity.EXOTIC, "LUCKY_TRAIL", "Trail Crate", .01),
    LUCKY2(Rarity.EXOTIC, "LUCKY_TRAIL2", "Trail Crate", .01),
    LUCKY3(Rarity.EXOTIC, "LUCKY_TRAIL3", "Trail Crate", .01),
    LUCKY4(Rarity.EXOTIC, "LUCKY_TRAIL4", "Trail Crate", .01),
    LUCKY5(Rarity.EXOTIC, "LUCKY_TRAIL5", "Trail Crate", .01),
    CLOUDY1(Rarity.EXOTIC, "CLOUDY_TRAIL", "Trail Crate", .01),
    CLOUDY2(Rarity.EXOTIC, "CLOUDY_TRAIL2", "Trail Crate", .01),
    CLOUDY3(Rarity.EXOTIC, "CLOUDY_TRAIL3", "Trail Crate", .01),
    CLOUDY4(Rarity.EXOTIC, "CLOUDY_TRAIL4", "Trail Crate", .01),
    CLOUDY5(Rarity.EXOTIC, "CLOUDY_TRAIL5", "Trail Crate", .01),
    SLASH1(Rarity.EXOTIC, "SLASH_TRAIL", "Trail Crate", .01),
    SLASH2(Rarity.EXOTIC, "SLASH_TRAIL2", "Trail Crate", .01),
    SLASH3(Rarity.EXOTIC, "SLASH_TRAIL3", "Trail Crate", .01),
    SLASH4(Rarity.EXOTIC, "SLASH_TRAIL4", "Trail Crate", .01),
    SLASH5(Rarity.EXOTIC, "SLASH_TRAIL5", "Trail Crate", .01),
    NAUTILUS1(Rarity.EXOTIC, "NAUTILUS_TRAIL", "Trail Crate", .01),
    NAUTILUS2(Rarity.EXOTIC, "NAUTILUS_TRAIL2", "Trail Crate", .01),
    NAUTILUS3(Rarity.EXOTIC, "NAUTILUS_TRAIL3", "Trail Crate", .01),
    NAUTILUS4(Rarity.EXOTIC, "NAUTILUS_TRAIL4", "Trail Crate", .01),
    NAUTILUS5(Rarity.EXOTIC, "NAUTILUS_TRAIL5", "Trail Crate", .01),
    EXPLOSION1(Rarity.EXOTIC, "EXPLOSION_TRAIL", "Trail Crate", .01),
    EXPLOSION2(Rarity.EXOTIC, "EXPLOSION_TRAIL2", "Trail Crate", .01),
    EXPLOSION3(Rarity.EXOTIC, "EXPLOSION_TRAIL3", "Trail Crate", .01),
    EXPLOSION4(Rarity.EXOTIC, "EXPLOSION_TRAIL4", "Trail Crate", .01),
    EXPLOSION5(Rarity.EXOTIC, "EXPLOSION_TRAIL5", "Trail Crate", .01),
    ;

    private Rarity rarity;
    private String name;
    private String unlockMethod;
    private double weight;

    TrailList(Rarity rarity, String name, String unlockMethod, double weight) {
        this.rarity = rarity;
        this.name = name;
        this.unlockMethod = unlockMethod;
        this.weight = weight;
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

    private static final Map<Rarity, List<TrailList>> RARITY_LISTS = new EnumMap<>(Rarity.class);

    public static List<TrailList> getTrail(Rarity rarity) {
        return RARITY_LISTS.computeIfAbsent(rarity, key -> Arrays.stream(values())
                .filter(trailList -> trailList.getRarity() == key)
                .collect(Collectors.toList()));
    }

    public static List<TrailList> getTrail() {
        List<TrailList> trailLists = new ArrayList<>();
        for (TrailList trailList : TrailList.values()) {
            System.out.println(trailList.getName());
            trailLists.add(trailList);
        }
        return trailLists;
    }
//    public static List<TrailList> getCrates(CrateList rarity) {
//        return RARITY_LISTS.computeIfAbsent(rarity, key -> Arrays.stream(values())
//                .filter(lootbox -> lootbox.getCrate() == key)
//                .collect(Collectors.toList()));
//    }


    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public ItemStack generateItem() {
        final ItemStack itemStack = MythicMobs.inst().getItemManager().getItemStack(name);
        if (itemStack != null) {
            List<Component> lore = itemStack.lore();
            if (lore == null) {
                lore = new ArrayList<>();
            } else if (!lore.isEmpty()) {
                lore.add(Component.empty());
            }
            lore.add(Component.text("CHANCE: " + weight * 10 + "%", NamedTextColor.GOLD, TextDecoration.BOLD));
            itemStack.lore(lore);
            return itemStack.asQuantity(1);
        }
        return null;
    }

    @Override
    public ItemStack generateItem(Player player) {
        return null;
    }

}
