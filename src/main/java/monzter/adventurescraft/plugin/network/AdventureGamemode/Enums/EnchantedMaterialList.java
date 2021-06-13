package monzter.adventurescraft.plugin.network.AdventureGamemode.Enums;

public enum EnchantedMaterialList {
    COBBLESTONE("ENCHANTED_COBBLESTONE"),
    COAL("ENCHANTED_COAL"),
    IRON("ENCHANTED_IRON"),
    GOLD("ENCHANTED_GOLD"),
    REDSTONE("ENCHANTED_REDSTONE"),
    LAPIS("ENCHANTED_LAPIS"),
    DIAMOND("ENCHANTED_DIAMOND"),
    EMERALD("ENCHANTED_EMERALD"),
    QUARTZ("ENCHANTED_QUARTZ"),
    GLOWSTONEDUST("ENCHANTED_GLOWSTONEDUST"),
    GLOWSTONE("ENCHANTED_GLOWSTONE"),
    NETHERRACK("ENCHANTED_NETHERRACK"),
    NETHERWART("ENCHANTED_NETHERWART"),
    OBSIDIAN("ENCHANTED_OBSIDIAN"),
    ENDSTONE("ENCHANTED_ENDSTONE"),
    BLAZE_ROD("ENCHANTED_BLAZE_ROD"),
    MAGMA_CREAM("ENCHANTED_MAGMA_CREAM"),
    GHAST_TEAR("ENCHANTED_GHAST_TEAR"),
    ROTTEN_FLESH("ENCHANTED_ROTTEN_FLESH"),
    BONE("ENCHANTED_BONE"),
    ARROW("ENCHANTED_ARROW"),
    SPIDER_EYE("ENCHANTED_SPIDER_EYE"),
    STRING("ENCHANTED_STRING"),
    GUNPOWDER("ENCHANTED_GUNPOWDER"),
    ENDERPEARL("ENCHANTED_ENDERPEARL"),
    SLIME_BALL("ENCHANTED_SLIME_BALL"),
    LEATHER("ENCHANTED_LEATHER"),
    BEEF("ENCHANTED_BEEF"),
    STEAK("ENCHANTED_STEAK"),
    RABBIT("ENCHANTED_RABBIT"),
    COOKED_RABBIT("ENCHANTED_COOKED_RABBIT"),
    RABBIT_HIDE("ENCHANTED_RABBIT_HIDE"),
    RABBIT_FOOT("ENCHANTED_RABBIT_FOOT"),
    FEATHER("ENCHANTED_FEATHER"),
    CHICKEN("ENCHANTED_CHICKEN"),
    COOKED_CHICKEN("ENCHANTED_COOKED_CHICKEN"),
    EGG("ENCHANTED_EGG"),
    PORKCHOP("ENCHANTED_PORKCHOP"),
    COOKED_PORKCHOP("ENCHANTED_COOKED_PORKCHOP"),
    MUTTON("ENCHANTED_MUTTON"),
    COOKED_MUTTON("ENCHANTED_COOKED_MUTTON"),
    WOOL("ENCHANTED_WOOL"),
    SEED("ENCHANTED_SEED"),
    WHEAT("ENCHANTED_WHEAT"),
    CARROT("ENCHANTED_CARROT"),
    POTATO("ENCHANTED_POTATO"),
    MELON("ENCHANTED_MELON"),
    PUMPKIN("ENCHANTED_PUMPKIN"),
    SUGAR_CANE("ENCHANTED_SUGAR_CANE"),
    SUGAR("ENCHANTED_SUGAR"),
    PAPER("ENCHANTED_PAPER"),
    RED_MUSHROOM("ENCHANTED_RED_MUSHROOM"),
    BROWN_MUSHROOM("ENCHANTED_BROWN_MUSHROOM"),
    CACTUS("ENCHANTED_CACTUS"),
    BEETROOT("ENCHANTED_BEETROOT"),
    OAK_LOG("ENCHANTED_OAK_LOG"),
    BIRCH_LOG("ENCHANTED_BIRCH_LOG"),
    DARK_OAK_LOG("ENCHANTED_DARK_OAK_LOG"),
    ACACIA_LOG("ENCHANTED_ACACIA_LOG"),
    JUNGLE_LOG("ENCHANTED_JUNGLE_LOG"),
    STICK("ENCHANTED_STICK"),
    AZURE_BLUET("ENCHANTED_AZURE_BLUET"),
    CORN_FLOWER("ENCHANTED_CORN_FLOWER"),
    CRIMSON_FUNGUS("ENCHANTED_CRIMSON_FUNGUS"),
    TUBE_CORAL("ENCHANTED_TUBE_CORAL"),
    SHULKER_SHELL("ENCHANTED_SHULKER_SHELL"),
    ALLIUM("ENCHANTED_ALLIUM"),
    ROSE_BUSH("ENCHANTED_ROSE_BUSH"),
    PUFFER_FISH("ENCHANTED_PUFFER_FISH"),
    WITHER_ROSE("ENCHANTED_WITHER_ROSE"),
    CHORUS_FRUIT("ENCHANTED_CHORUS_FRUIT"),
    CHORUS_SEED("ENCHANTED_CHORUS_SEED"),
    ;

    public final String ID;

    EnchantedMaterialList(String id) {
        ID = id;
    }

    public String getID() {
        return ID;
    }
}
