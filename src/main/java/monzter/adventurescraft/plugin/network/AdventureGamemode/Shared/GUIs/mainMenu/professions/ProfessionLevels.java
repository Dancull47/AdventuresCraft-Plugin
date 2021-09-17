package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.mainMenu.professions;

import monzter.adventurescraft.plugin.utilities.enums.Professions;
import monzter.adventurescraft.plugin.utilities.mmoitems.MMOItemsHelperImpl;
import org.bukkit.inventory.ItemStack;

public enum ProfessionLevels {
    Farming1(Professions.FARMING, "1", new ItemStack[]{MMOItemsHelperImpl.getItem("ARMOR", "MELON_HAT3"), MMOItemsHelperImpl.getItem("ACCESSORY", "POWERFUL_NECKLACE3")}),
    Farming2(Professions.FARMING, "2", new ItemStack[]{}),
    Farming3(Professions.FARMING, "3", new ItemStack[]{MMOItemsHelperImpl.getItem("ACCESSORY", "DEEP_IMPACTOR3"), MMOItemsHelperImpl.getItem("ACCESSORY", "LUCKY_STRIKER3")}),
    Farming4(Professions.FARMING, "4", new ItemStack[]{MMOItemsHelperImpl.getItem("STAFF", "SEED_STAFF3"), MMOItemsHelperImpl.getItem("ARMOR", "MELON_HAT4")}),
    Farming5(Professions.FARMING, "5", new ItemStack[]{MMOItemsHelperImpl.getItem("ARMOR", "MELON_HAT5"), MMOItemsHelperImpl.getItem("ACCESSORY", "LUCKY_STRIKER4"), MMOItemsHelperImpl.getItem("TOOL", "UNLIMITED_WATER")}),
    Farming6(Professions.FARMING, "6", new ItemStack[]{}),
    Farming7(Professions.FARMING, "7", new ItemStack[]{MMOItemsHelperImpl.getItem("ACCESSORY", "DEEP_IMPACTOR4"), MMOItemsHelperImpl.getItem("ACCESSORY", "LUCKY_STRIKER5"), MMOItemsHelperImpl.getItem("ACCESSORY", "POWERFUL_NECKLACE4")}),
    Farming8(Professions.FARMING, "8", new ItemStack[]{MMOItemsHelperImpl.getItem("STAFF", "WHEAT_STAFF3")}),
    Farming9(Professions.FARMING, "9", new ItemStack[]{}),
    Farming10(Professions.FARMING, "10", new ItemStack[]{}),

    Slayer1(Professions.SLAYER, "1", new ItemStack[]{}),
    Slayer2(Professions.SLAYER, "2", new ItemStack[]{}),
    Slayer3(Professions.SLAYER, "3", new ItemStack[]{MMOItemsHelperImpl.getItem("COMPANION", "PET_SKELETON3")}),
    Slayer4(Professions.SLAYER, "4", new ItemStack[]{MMOItemsHelperImpl.getItem("COMPANION", "PET_SKELETONARCHER3")}),
    Slayer5(Professions.SLAYER, "5", new ItemStack[]{MMOItemsHelperImpl.getItem("COMPANION", "PET_LOST_SOUL3")}),
    Slayer6(Professions.SLAYER, "6", new ItemStack[]{}),
    Slayer7(Professions.SLAYER, "7", new ItemStack[]{MMOItemsHelperImpl.getItem("COMPANION", "PET_REAPER3")}),
    Slayer8(Professions.SLAYER, "8", new ItemStack[]{MMOItemsHelperImpl.getItem("COMPANION", "PET_ALPHA_SOUL3")}),
    Slayer9(Professions.SLAYER, "9", new ItemStack[]{MMOItemsHelperImpl.getItem("COMPANION", "PET_UNDEAD_CASTER3")}),
    Slayer10(Professions.SLAYER, "10", new ItemStack[]{MMOItemsHelperImpl.getItem("COMPANION", "PET_MORDEN_THE_UNDEAD5")}),

    MINING1(Professions.MINING, "1", new ItemStack[]{MMOItemsHelperImpl.getItem("GEM_STONE", "MANA_GEM")}),
    MINING2(Professions.MINING, "2", new ItemStack[]{MMOItemsHelperImpl.getItem("CATALYST", "MINING_CATALYST3"), MMOItemsHelperImpl.getItem("STEW", "MINERS_SNACK"), MMOItemsHelperImpl.getItem("ARMOR", "GEM_HELMET3"), MMOItemsHelperImpl.getItem("ARMOR", "GEM_CHESTPLATE3"), MMOItemsHelperImpl.getItem("ARMOR", "GEM_LEGGINGS3"), MMOItemsHelperImpl.getItem("ARMOR", "GEM_BOOTS3")}),
    MINING3(Professions.MINING, "3", new ItemStack[]{MMOItemsHelperImpl.getItem("STEW", "MINERS_SNACK2"), MMOItemsHelperImpl.getItem("CATALYST", "COAL_CATALYST3"), MMOItemsHelperImpl.getItem("MATERIAL", "GEM_BASE")}),
    MINING4(Professions.MINING, "4", new ItemStack[]{MMOItemsHelperImpl.getItem("CATALYST", "MINING_CATALYST4"), MMOItemsHelperImpl.getItem("ARMOR", "GEM_HELMET4"), MMOItemsHelperImpl.getItem("ARMOR", "GEM_CHESTPLATE4"), MMOItemsHelperImpl.getItem("ARMOR", "GEM_LEGGINGS4"), MMOItemsHelperImpl.getItem("ARMOR", "GEM_BOOTS4"), MMOItemsHelperImpl.getItem("CATALYST", "IRON_CATALYST3")}),
    MINING5(Professions.MINING, "5", new ItemStack[]{MMOItemsHelperImpl.getItem("STEW", "MINERS_SNACK3"), MMOItemsHelperImpl.getItem("GEM_STONE", "MANA_GEM4"), MMOItemsHelperImpl.getItem("GEM_STONE", "DAMAGE_GEM3"), MMOItemsHelperImpl.getItem("CATALYST", "GOLD_CATALYST3")}),
    MINING6(Professions.MINING, "6", new ItemStack[]{MMOItemsHelperImpl.getItem("MATERIAL", "GEM_BASE3"), MMOItemsHelperImpl.getItem("ARMOR", "GEM_HELMET5"), MMOItemsHelperImpl.getItem("ARMOR", "GEM_CHESTPLATE5"), MMOItemsHelperImpl.getItem("ARMOR", "GEM_LEGGINGS5"), MMOItemsHelperImpl.getItem("ARMOR", "GEM_BOOTS5")}),
    MINING7(Professions.MINING, "7", new ItemStack[]{MMOItemsHelperImpl.getItem("GEM_STONE", "MANA_GEM5"), MMOItemsHelperImpl.getItem("STEW", "MINERS_SNACK4"), MMOItemsHelperImpl.getItem("GEM_STONE", "DAMAGE_GEM4"), MMOItemsHelperImpl.getItem("CATALYST", "REDSTONE_CATALYST3")}),
    MINING8(Professions.MINING, "8", new ItemStack[]{MMOItemsHelperImpl.getItem("CATALYST", "MINING_CATALYST5"), MMOItemsHelperImpl.getItem("GEM_STONE", "DEFENSE_GEM3"), MMOItemsHelperImpl.getItem("CATALYST", "LAPIS_CATALYST3")}),
    MINING9(Professions.MINING, "9", new ItemStack[]{MMOItemsHelperImpl.getItem("MATERIAL", "GEM_BASE5"), MMOItemsHelperImpl.getItem("GEM_STONE", "DAMAGE_GEM4"), MMOItemsHelperImpl.getItem("GEM_STONE", "DEFENSE_GEM4"), MMOItemsHelperImpl.getItem("GEM_STONE", "HEALTH_GEM3"), MMOItemsHelperImpl.getItem("CATALYST", "DIAMOND_CATALYST3")}),
    MINING10(Professions.MINING, "10", new ItemStack[]{MMOItemsHelperImpl.getItem("GEM_STONE", "HEALTH_GEM4"), MMOItemsHelperImpl.getItem("CATALYST", "EMERALD_CATALYST3")}),

    FORAGING1(Professions.FORAGING, "1", new ItemStack[]{}),
    FORAGING2(Professions.FORAGING, "2", new ItemStack[]{}),
    FORAGING3(Professions.FORAGING, "3", new ItemStack[]{MMOItemsHelperImpl.getItem("ACCESSORY", "RUNNING_SPRITE3"), MMOItemsHelperImpl.getItem("ACCESSORY", "OAK_BARK")}),
    FORAGING4(Professions.FORAGING, "4", new ItemStack[]{MMOItemsHelperImpl.getItem("ACCESSORY", "FAST_HITTER3"), MMOItemsHelperImpl.getItem("DAGGER", "MACHETE3"), MMOItemsHelperImpl.getItem("ACCESSORY", "SPEEDY_JUNGLE_WORKER2")}),
    FORAGING5(Professions.FORAGING, "5", new ItemStack[]{MMOItemsHelperImpl.getItem("ARMOR", "LOG_HELMET4"), MMOItemsHelperImpl.getItem("ARMOR", "LOG_CHESTPLATE4"), MMOItemsHelperImpl.getItem("ARMOR", "LOG_LEGGINGS4"), MMOItemsHelperImpl.getItem("ARMOR", "LOG_BOOTS4"), MMOItemsHelperImpl.getItem("ACCESSORY", "ACACIA_HEART3"), MMOItemsHelperImpl.getItem("ACCESSORY", "OAK_BARK2")}),
    FORAGING6(Professions.FORAGING, "6", new ItemStack[]{MMOItemsHelperImpl.getItem("TOOL", "JACKS_BESTEST_AXE5"), MMOItemsHelperImpl.getItem("ACCESSORY", "FAST_HITTER4"), MMOItemsHelperImpl.getItem("ACCESSORY", "RUNNING_SPRITE4"), MMOItemsHelperImpl.getItem("ACCESSORY", "SPEEDY_JUNGLE_WORKER3")}),
    FORAGING7(Professions.FORAGING, "7", new ItemStack[]{MMOItemsHelperImpl.getItem("ACCESSORY", "ACACIA_HEART4"), MMOItemsHelperImpl.getItem("ACCESSORY", "OAK_BARK3")}),
    FORAGING8(Professions.FORAGING, "8", new ItemStack[]{MMOItemsHelperImpl.getItem("ACCESSORY", "RUNNING_SPRITE5"), MMOItemsHelperImpl.getItem("ACCESSORY", "FAST_HITTER5"), MMOItemsHelperImpl.getItem("ACCESSORY", "OAK_BARK4")}),
    FORAGING9(Professions.FORAGING, "9", new ItemStack[]{MMOItemsHelperImpl.getItem("ACCESSORY", "ACACIA_HEART5")}),
    FORAGING10(Professions.FORAGING, "10", new ItemStack[]{MMOItemsHelperImpl.getItem("ACCESSORY", "OAK_BARK5"), MMOItemsHelperImpl.getItem("AXE", "RICH_AXE5")}),

    ENCHANTING1(Professions.ENCHANTING, "1", new ItemStack[]{MMOItemsHelperImpl.getItem("ENCHANTMENT", "SHARPNESS"), MMOItemsHelperImpl.getItem("ENCHANTMENT", "LIFE_STEAL"), MMOItemsHelperImpl.getItem("ENCHANTMENT", "RELIEVE"), MMOItemsHelperImpl.getItem("ENCHANTMENT", "REPLENISH")}),
    ENCHANTING2(Professions.ENCHANTING, "2", new ItemStack[]{MMOItemsHelperImpl.getItem("ENCHANTMENT", "LIFE_STEAL2"), MMOItemsHelperImpl.getItem("ENCHANTMENT", "IMPAIRMENT"), MMOItemsHelperImpl.getItem("ENCHANTMENT", "DRAW"), MMOItemsHelperImpl.getItem("ENCHANTMENT", "FORTUNE"), MMOItemsHelperImpl.getItem("ENCHANTMENT", "EXPERIENCE")}),
    ENCHANTING3(Professions.ENCHANTING, "3", new ItemStack[]{MMOItemsHelperImpl.getItem("ENCHANTMENT", "SHARPNESS2"), MMOItemsHelperImpl.getItem("ENCHANTMENT", "RELIEVE2"), MMOItemsHelperImpl.getItem("ENCHANTMENT", "REPLENISH2"), MMOItemsHelperImpl.getItem("ENCHANTMENT", "IGNITE"), MMOItemsHelperImpl.getItem("ENCHANTMENT", "COMBUST"), MMOItemsHelperImpl.getItem("ENCHANTMENT", "POSTHUMOUS"), MMOItemsHelperImpl.getItem("ENCHANTMENT", "CULING"), MMOItemsHelperImpl.getItem("ENCHANTMENT", "REFLECT"), MMOItemsHelperImpl.getItem("ENCHANTMENT", "YIELD")}),
    ENCHANTING4(Professions.ENCHANTING, "4", new ItemStack[]{MMOItemsHelperImpl.getItem("ENCHANTMENT", "IMPAIRMENT2"), MMOItemsHelperImpl.getItem("ENCHANTMENT", "IGNITE2"), MMOItemsHelperImpl.getItem("ENCHANTMENT", "DRAW2"), MMOItemsHelperImpl.getItem("ENCHANTMENT", "FORTUNE2"), MMOItemsHelperImpl.getItem("ENCHANTMENT", "KAMIKAZE"), MMOItemsHelperImpl.getItem("ENCHANTMENT", "FURTHERANCE")}),
    ENCHANTING5(Professions.ENCHANTING, "5", new ItemStack[]{MMOItemsHelperImpl.getItem("ENCHANTMENT", "SHARPNESS3"), MMOItemsHelperImpl.getItem("ENCHANTMENT", "LIFE_STEAL3"), MMOItemsHelperImpl.getItem("ENCHANTMENT", "RELIEVE3"), MMOItemsHelperImpl.getItem("ENCHANTMENT", "REPLENISH3"), MMOItemsHelperImpl.getItem("ENCHANTMENT", "COMBUST2"), MMOItemsHelperImpl.getItem("ENCHANTMENT", "EXPERIENCE2"), MMOItemsHelperImpl.getItem("ENCHANTMENT", "YIELD2"), MMOItemsHelperImpl.getItem("ENCHANTMENT", "STAGGER"), MMOItemsHelperImpl.getItem("ENCHANTMENT", "GNASH")}),
    ENCHANTING6(Professions.ENCHANTING, "6", new ItemStack[]{MMOItemsHelperImpl.getItem("ENCHANTMENT", "LIFE_STEAL4"), MMOItemsHelperImpl.getItem("ENCHANTMENT", "FORTUNE3"), MMOItemsHelperImpl.getItem("ENCHANTMENT", "IMPAIRMENT3"), MMOItemsHelperImpl.getItem("ENCHANTMENT", "IGNITE3"), MMOItemsHelperImpl.getItem("ENCHANTMENT", "DRAW3"), MMOItemsHelperImpl.getItem("ENCHANTMENT", "POSTHUMOUS2"), MMOItemsHelperImpl.getItem("ENCHANTMENT", "KAMIKAZE2"), MMOItemsHelperImpl.getItem("ENCHANTMENT", "REFLECT2"), MMOItemsHelperImpl.getItem("ENCHANTMENT", "CULING2"), MMOItemsHelperImpl.getItem("ENCHANTMENT", "FURTHERANCE2")}),
    ENCHANTING7(Professions.ENCHANTING, "7", new ItemStack[]{MMOItemsHelperImpl.getItem("ENCHANTMENT", "SHARPNESS4"), MMOItemsHelperImpl.getItem("ENCHANTMENT", "RELIEVE4"), MMOItemsHelperImpl.getItem("ENCHANTMENT", "REFLECT3"), MMOItemsHelperImpl.getItem("ENCHANTMENT", "GNASH2")}),
    ENCHANTING8(Professions.ENCHANTING, "8", new ItemStack[]{MMOItemsHelperImpl.getItem("ENCHANTMENT", "KAMIKAZE3"), MMOItemsHelperImpl.getItem("ENCHANTMENT", "FURTHERANCE3")}),
    ENCHANTING9(Professions.ENCHANTING, "9", new ItemStack[]{MMOItemsHelperImpl.getItem("ENCHANTMENT", "CULING"), MMOItemsHelperImpl.getItem("ENCHANTMENT", "POSTHUMOUS")}),
    ENCHANTING10(Professions.ENCHANTING, "10", new ItemStack[]{MMOItemsHelperImpl.getItem("ENCHANTMENT", "SHARPNESS5"), MMOItemsHelperImpl.getItem("ENCHANTMENT", "REFLECT4"), MMOItemsHelperImpl.getItem("ENCHANTMENT", "COMBUST3"), MMOItemsHelperImpl.getItem("ENCHANTMENT", "GNASH3"), MMOItemsHelperImpl.getItem("ENCHANTMENT", "STAGGER2")}),

    COOKING1(Professions.COOKING, "1", new ItemStack[]{MMOItemsHelperImpl.getItem("STEW", "HEALING_STEW"), MMOItemsHelperImpl.getItem("STEW", "MANA_STEW"), MMOItemsHelperImpl.getItem("STEW", "REGEN_STEW"), MMOItemsHelperImpl.getItem("STEW", "STRENGTH_STEW"), MMOItemsHelperImpl.getItem("STEW", "SWIFT_STEW"), MMOItemsHelperImpl.getItem("STEW", "JUMP_STEW")}),
    COOKING2(Professions.COOKING, "2", new ItemStack[]{MMOItemsHelperImpl.getItem("STEW", "HEALING_STEW2"), MMOItemsHelperImpl.getItem("STEW", "STRENGTH_STEW2"), MMOItemsHelperImpl.getItem("TOTEM", "REGEN_TOTEM"), MMOItemsHelperImpl.getItem("TOTEM", "SWIFT_TOTEM"), MMOItemsHelperImpl.getItem("STEW", "MINERS_SNACK")}),
    COOKING3(Professions.COOKING, "3", new ItemStack[]{MMOItemsHelperImpl.getItem("STEW", "HEALING_STEW3"), MMOItemsHelperImpl.getItem("STEW", "MANA_STEW2"), MMOItemsHelperImpl.getItem("STEW", "MINERS_SNACK2"), MMOItemsHelperImpl.getItem("STEW", "REGEN_STEW2"), MMOItemsHelperImpl.getItem("STEW", "SWIFT_STEW2"), MMOItemsHelperImpl.getItem("TOTEM", "JUMP_TOTEM"), MMOItemsHelperImpl.getItem("TOTEM", "STRENGTH_TOTEM")}),
    COOKING4(Professions.COOKING, "4", new ItemStack[]{MMOItemsHelperImpl.getItem("STEW", "MANA_STEW3"), MMOItemsHelperImpl.getItem("STEW", "MINERS_SNACK3"), MMOItemsHelperImpl.getItem("TOTEM", "STRENGTH_TOTEM2"), MMOItemsHelperImpl.getItem("TOTEM", "SWIFT_TOTEM2"), MMOItemsHelperImpl.getItem("STEW", "JUMP_STEW2")}),
    COOKING5(Professions.COOKING, "5", new ItemStack[]{MMOItemsHelperImpl.getItem("STEW", "MINERS_SNACK4"), MMOItemsHelperImpl.getItem("TOTEM", "SWIFT_TOTEM3"), MMOItemsHelperImpl.getItem("TOTEM", "REGEN_TOTEM2"), MMOItemsHelperImpl.getItem("TOTEM", "JUMP_TOTEM2")}),
    COOKING6(Professions.COOKING, "6", new ItemStack[]{MMOItemsHelperImpl.getItem("STEW", "REGEN_STEW3"), MMOItemsHelperImpl.getItem("TOTEM", "STRENGTH_TOTEM3"), MMOItemsHelperImpl.getItem("STEW", "SWIFT_STEW3")}),
    COOKING7(Professions.COOKING, "7", new ItemStack[]{MMOItemsHelperImpl.getItem("TOTEM", "SWIFT_TOTEM4"), MMOItemsHelperImpl.getItem("TOTEM", "JUMP_TOTEM3")}),
    COOKING8(Professions.COOKING, "8", new ItemStack[]{MMOItemsHelperImpl.getItem("STEW", "STRENGTH_STEW3"), MMOItemsHelperImpl.getItem("TOTEM", "REGEN_TOTEM3")}),
    COOKING9(Professions.COOKING, "9", new ItemStack[]{}),
    COOKING10(Professions.COOKING, "10", new ItemStack[]{}),

    SPELLFORGING1(Professions.SPELLFORGING, "1", new ItemStack[]{MMOItemsHelperImpl.getItem("SPELL", "TARGETED_FIREBALL3"),
            MMOItemsHelperImpl.getItem("WAND", "RESOLVE2"), MMOItemsHelperImpl.getItem("WAND", "TARGETED_FIREBALL2"), MMOItemsHelperImpl.getItem("WAND", "ARCANE_HAIL2"), MMOItemsHelperImpl.getItem("WAND", "MAGMA_FISSURE2")}),
    SPELLFORGING2(Professions.SPELLFORGING, "2", new ItemStack[]{MMOItemsHelperImpl.getItem("SPELL", "SPARKLE3"), MMOItemsHelperImpl.getItem("SPELL", "BUNNY_MODE3"),
            MMOItemsHelperImpl.getItem("WAND", "TARGETED_FIREBALL3"), MMOItemsHelperImpl.getItem("WAND", "BUNNY_MODE2"), MMOItemsHelperImpl.getItem("WAND", "BURNING_HANDS2"), MMOItemsHelperImpl.getItem("WAND", "ICE_CRYSTAL2"), MMOItemsHelperImpl.getItem("WAND", "CURSED_BEAM2")}),
    SPELLFORGING3(Professions.SPELLFORGING, "3", new ItemStack[]{MMOItemsHelperImpl.getItem("SPELL", "ARCANE_RIFT3"), MMOItemsHelperImpl.getItem("SPELL", "BURNING_HANDS3"), MMOItemsHelperImpl.getItem("SPELL", "FREEZING_CURSE3"),
            MMOItemsHelperImpl.getItem("WAND", "RESOLVE3"), MMOItemsHelperImpl.getItem("WAND", "SPARKLE2"), MMOItemsHelperImpl.getItem("WAND", "SHULKER_MISSILE2"), MMOItemsHelperImpl.getItem("WAND", "ARCANE_HAIL3"), MMOItemsHelperImpl.getItem("WAND", "CORRUPTED_FANGS2"), MMOItemsHelperImpl.getItem("WAND", "BOUNCY_FIREBALL3"), MMOItemsHelperImpl.getItem("WAND", "LIFE_ENDER3"), MMOItemsHelperImpl.getItem("WAND", "CORRUPT2")}),
    SPELLFORGING4(Professions.SPELLFORGING, "4", new ItemStack[]{MMOItemsHelperImpl.getItem("SPELL", "SHULKER_MISSILE3"), MMOItemsHelperImpl.getItem("SPELL", "ARCANE_HAIL3"), MMOItemsHelperImpl.getItem("SPELL", "FROZEN_AURA3"), MMOItemsHelperImpl.getItem("SPELL", "MONITOR_MUTE3"),
            MMOItemsHelperImpl.getItem("WAND", "MAGMA_FISSURE3"), MMOItemsHelperImpl.getItem("WAND", "BLACK_HOLE2"), MMOItemsHelperImpl.getItem("WAND", "CURSED_BEAM3"), MMOItemsHelperImpl.getItem("WAND", "CORRUPT3"), MMOItemsHelperImpl.getItem("WAND", "CONTAMINATION_RITUAL2")}),
    SPELLFORGING5(Professions.SPELLFORGING, "5", new ItemStack[]{MMOItemsHelperImpl.getItem("SPELL", "MAGMA_FISSURE3"), MMOItemsHelperImpl.getItem("SPELL", "ICE_CRYSTAL3"), MMOItemsHelperImpl.getItem("SPELL", "CORRUPTED_FANGS3"),
            MMOItemsHelperImpl.getItem("WAND", "SPARKLE3"), MMOItemsHelperImpl.getItem("WAND", "ARCANE_RIFT3"), MMOItemsHelperImpl.getItem("WAND", "BURNING_HANDS3"), MMOItemsHelperImpl.getItem("WAND", "ICE_CRYSTAL3"), MMOItemsHelperImpl.getItem("WAND", "CORRUPTED_FANGS3"), MMOItemsHelperImpl.getItem("WAND", "LIFE_ENDER3"), MMOItemsHelperImpl.getItem("WAND", "MONITOR_MUTE2")}),
    SPELLFORGING6(Professions.SPELLFORGING, "6", new ItemStack[]{MMOItemsHelperImpl.getItem("SPELL", "BLACK_HOLE3"), MMOItemsHelperImpl.getItem("SPELL", "BOUNCY_FIREBALL3"), MMOItemsHelperImpl.getItem("SPELL", "CURSED_BEAM3"),
            MMOItemsHelperImpl.getItem("WAND", "SHULKER_MISSILE3"), MMOItemsHelperImpl.getItem("WAND", "BOUNCY_FIREBALL3")}),
    SPELLFORGING7(Professions.SPELLFORGING, "7", new ItemStack[]{MMOItemsHelperImpl.getItem("SPELL", "LIFE_ENDER3"), MMOItemsHelperImpl.getItem("SPELL", "CORRUPT3"),
            MMOItemsHelperImpl.getItem("WAND", "FREEZING_CURSE3"), MMOItemsHelperImpl.getItem("WAND", "FROZEN_AURA3"), MMOItemsHelperImpl.getItem("WAND", "CONTAMINATION_RITUAL3"), MMOItemsHelperImpl.getItem("WAND", "MONITOR_MUTE3")}),
    SPELLFORGING8(Professions.SPELLFORGING, "8", new ItemStack[]{}),
    SPELLFORGING9(Professions.SPELLFORGING, "9", new ItemStack[]{MMOItemsHelperImpl.getItem("SPELL", "CONTAMINATION_RITUAL3")}),
    SPELLFORGING10(Professions.SPELLFORGING, "10", new ItemStack[]{}),

    ;
    private Professions profession;
    private String level;
    private ItemStack[] rewards;

    ProfessionLevels(Professions profession, String level, ItemStack[] rewards) {
        this.profession = profession;
        this.level = level;
        this.rewards = rewards;
    }

    public String getLevel() {
        return level;
    }

    public ItemStack[] getRewards() {
        return rewards;
    }

    public Professions getProfession() {
        return profession;
    }

}
