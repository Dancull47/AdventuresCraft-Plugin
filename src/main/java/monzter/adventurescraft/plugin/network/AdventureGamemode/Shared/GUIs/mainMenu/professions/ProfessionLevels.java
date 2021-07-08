package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.mainMenu.professions;

import monzter.adventurescraft.plugin.utilities.enums.Professions;
import net.Indyuce.mmoitems.MMOItems;
import org.bukkit.inventory.ItemStack;

public enum ProfessionLevels {
    Farming1(Professions.FARMING, "1", new ItemStack[]{MMOItems.plugin.getItem("ARMOR", "MELON_HAT3"), MMOItems.plugin.getItem("ACCESSORY", "POWERFUL_NECKLACE3")}),
    Farming2(Professions.FARMING, "2", new ItemStack[]{}),
    Farming3(Professions.FARMING, "3", new ItemStack[]{MMOItems.plugin.getItem("ACCESSORY", "DEEP_IMPACTOR3"), MMOItems.plugin.getItem("ACCESSORY", "LUCKY_STRIKER3")}),
    Farming4(Professions.FARMING, "4", new ItemStack[]{MMOItems.plugin.getItem("STAFF", "SEED_STAFF3"), MMOItems.plugin.getItem("ARMOR", "MELON_HAT4")}),
    Farming5(Professions.FARMING, "5", new ItemStack[]{MMOItems.plugin.getItem("ARMOR", "MELON_HAT5"), MMOItems.plugin.getItem("ACCESSORY", "LUCKY_STRIKER4"), MMOItems.plugin.getItem("MISCELLANEOUS", "UNLIMITED_WATER")}),
    Farming6(Professions.FARMING, "6", new ItemStack[]{}),
    Farming7(Professions.FARMING, "7", new ItemStack[]{MMOItems.plugin.getItem("ACCESSORY", "DEEP_IMPACTOR4"), MMOItems.plugin.getItem("ACCESSORY", "LUCKY_STRIKER5"), MMOItems.plugin.getItem("ACCESSORY", "POWERFUL_NECKLACE4")}),
    Farming8(Professions.FARMING, "8", new ItemStack[]{MMOItems.plugin.getItem("STAFF", "WHEAT_STAFF3")}),
    Farming9(Professions.FARMING, "9", new ItemStack[]{}),
    Farming10(Professions.FARMING, "10", new ItemStack[]{}),

    Slayer1(Professions.SLAYER, "1", new ItemStack[]{}),
    Slayer2(Professions.SLAYER, "2", new ItemStack[]{}),
    Slayer3(Professions.SLAYER, "3", new ItemStack[]{MMOItems.plugin.getItem("COMPANION", "PET_SKELETON3")}),
    Slayer4(Professions.SLAYER, "4", new ItemStack[]{MMOItems.plugin.getItem("COMPANION", "PET_SKELETONARCHER3")}),
    Slayer5(Professions.SLAYER, "5", new ItemStack[]{MMOItems.plugin.getItem("COMPANION", "PET_LOST_SOUL3")}),
    Slayer6(Professions.SLAYER, "6", new ItemStack[]{}),
    Slayer7(Professions.SLAYER, "7", new ItemStack[]{MMOItems.plugin.getItem("COMPANION", "PET_REAPER3")}),
    Slayer8(Professions.SLAYER, "8", new ItemStack[]{MMOItems.plugin.getItem("COMPANION", "PET_ALPHA_SOUL3")}),
    Slayer9(Professions.SLAYER, "9", new ItemStack[]{MMOItems.plugin.getItem("COMPANION", "PET_UNDEAD_CASTER3")}),
    Slayer10(Professions.SLAYER, "10", new ItemStack[]{MMOItems.plugin.getItem("COMPANION", "PET_MORDEN_THE_UNDEAD5")}),

    MINING1(Professions.MINING, "1", new ItemStack[]{MMOItems.plugin.getItem("GEM_STONE", "MANA_GEM")}),
    MINING2(Professions.MINING, "2", new ItemStack[]{MMOItems.plugin.getItem("CATALYST", "MINING_CATALYST3"), MMOItems.plugin.getItem("STEW", "MINERS_SNACK"), MMOItems.plugin.getItem("ARMOR", "GEM_HELMET3"), MMOItems.plugin.getItem("ARMOR", "GEM_CHESTPLATE3"), MMOItems.plugin.getItem("ARMOR", "GEM_LEGGINGS3"), MMOItems.plugin.getItem("ARMOR", "GEM_BOOTS3")}),
    MINING3(Professions.MINING, "3", new ItemStack[]{MMOItems.plugin.getItem("STEW", "MINERS_SNACK2"), MMOItems.plugin.getItem("CATALYST", "COAL_CATALYST3"), MMOItems.plugin.getItem("MATERIAL", "GEM_BASE")}),
    MINING4(Professions.MINING, "4", new ItemStack[]{MMOItems.plugin.getItem("CATALYST", "MINING_CATALYST4"), MMOItems.plugin.getItem("ARMOR", "GEM_HELMET4"), MMOItems.plugin.getItem("ARMOR", "GEM_CHESTPLATE4"), MMOItems.plugin.getItem("ARMOR", "GEM_LEGGINGS4"), MMOItems.plugin.getItem("ARMOR", "GEM_BOOTS4"), MMOItems.plugin.getItem("CATALYST", "IRON_CATALYST3")}),
    MINING5(Professions.MINING, "5", new ItemStack[]{MMOItems.plugin.getItem("STEW", "MINERS_SNACK3"), MMOItems.plugin.getItem("GEM_STONE", "MANA_GEM4"), MMOItems.plugin.getItem("GEM_STONE", "DAMAGE_GEM3"), MMOItems.plugin.getItem("CATALYST", "GOLD_CATALYST3")}),
    MINING6(Professions.MINING, "6", new ItemStack[]{MMOItems.plugin.getItem("MATERIAL", "GEM_BASE3"), MMOItems.plugin.getItem("ARMOR", "GEM_HELMET5"), MMOItems.plugin.getItem("ARMOR", "GEM_CHESTPLATE5"), MMOItems.plugin.getItem("ARMOR", "GEM_LEGGINGS5"), MMOItems.plugin.getItem("ARMOR", "GEM_BOOTS5")}),
    MINING7(Professions.MINING, "7", new ItemStack[]{MMOItems.plugin.getItem("GEM_STONE", "MANA_GEM5"), MMOItems.plugin.getItem("STEW", "MINERS_SNACK4"), MMOItems.plugin.getItem("GEM_STONE", "DAMAGE_GEM4"), MMOItems.plugin.getItem("CATALYST", "REDSTONE_CATALYST3")}),
    MINING8(Professions.MINING, "8", new ItemStack[]{MMOItems.plugin.getItem("CATALYST", "MINING_CATALYST5"), MMOItems.plugin.getItem("GEM_STONE", "DEFENSE_GEM3"), MMOItems.plugin.getItem("CATALYST", "LAPIS_CATALYST3")}),
    MINING9(Professions.MINING, "9", new ItemStack[]{MMOItems.plugin.getItem("MATERIAL", "GEM_BASE5"), MMOItems.plugin.getItem("GEM_STONE", "DAMAGE_GEM4"), MMOItems.plugin.getItem("GEM_STONE", "DEFENSE_GEM4"), MMOItems.plugin.getItem("GEM_STONE", "HEALTH_GEM3"), MMOItems.plugin.getItem("CATALYST", "DIAMOND_CATALYST3")}),
    MINING10(Professions.MINING, "10", new ItemStack[]{MMOItems.plugin.getItem("GEM_STONE", "HEALTH_GEM4"), MMOItems.plugin.getItem("CATALYST", "EMERALD_CATALYST3")}),

    FORAGING1(Professions.FORAGING, "1", new ItemStack[]{}),
    FORAGING2(Professions.FORAGING, "2", new ItemStack[]{}),
    FORAGING3(Professions.FORAGING, "3", new ItemStack[]{MMOItems.plugin.getItem("ACCESSORY", "RUNNING_SPRITE3"), MMOItems.plugin.getItem("ACCESSORY", "OAK_BARK")}),
    FORAGING4(Professions.FORAGING, "4", new ItemStack[]{MMOItems.plugin.getItem("ACCESSORY", "FAST_HITTER3"), MMOItems.plugin.getItem("DAGGER", "MACHETE3"), MMOItems.plugin.getItem("ACCESSORY", "SPEEDY_JUNGLE_WORKER2")}),
    FORAGING5(Professions.FORAGING, "5", new ItemStack[]{MMOItems.plugin.getItem("ARMOR", "LOG_HELMET4"), MMOItems.plugin.getItem("ARMOR", "LOG_CHESTPLATE4"), MMOItems.plugin.getItem("ARMOR", "LOG_LEGGINGS4"), MMOItems.plugin.getItem("ARMOR", "LOG_BOOTS4"), MMOItems.plugin.getItem("ACCESSORY", "ACACIA_HEART3"), MMOItems.plugin.getItem("ACCESSORY", "OAK_BARK2")}),
    FORAGING6(Professions.FORAGING, "6", new ItemStack[]{MMOItems.plugin.getItem("TOOL", "JACKS_BESTEST_AXE5"), MMOItems.plugin.getItem("ACCESSORY", "FAST_HITTER4"), MMOItems.plugin.getItem("ACCESSORY", "RUNNING_SPRITE4"), MMOItems.plugin.getItem("ACCESSORY", "SPEEDY_JUNGLE_WORKER3")}),
    FORAGING7(Professions.FORAGING, "7", new ItemStack[]{MMOItems.plugin.getItem("ACCESSORY", "ACACIA_HEART4"), MMOItems.plugin.getItem("ACCESSORY", "OAK_BARK3")}),
    FORAGING8(Professions.FORAGING, "8", new ItemStack[]{MMOItems.plugin.getItem("ACCESSORY", "RUNNING_SPRITE5"), MMOItems.plugin.getItem("ACCESSORY", "FAST_HITTER5"), MMOItems.plugin.getItem("ACCESSORY", "OAK_BARK4")}),
    FORAGING9(Professions.FORAGING, "9", new ItemStack[]{MMOItems.plugin.getItem("ACCESSORY", "ACACIA_HEART5")}),
    FORAGING10(Professions.FORAGING, "10", new ItemStack[]{MMOItems.plugin.getItem("ACCESSORY", "OAK_BARK5"), MMOItems.plugin.getItem("AXE", "RICH_AXE5")}),

    ENCHANTING1(Professions.ENCHANTING, "1", new ItemStack[]{MMOItems.plugin.getItem("ENCHANTMENT", "SHARPNESS"), MMOItems.plugin.getItem("ENCHANTMENT", "LIFE_STEAL"), MMOItems.plugin.getItem("ENCHANTMENT", "RELIEVE"), MMOItems.plugin.getItem("ENCHANTMENT", "REPLENISH")}),
    ENCHANTING2(Professions.ENCHANTING, "2", new ItemStack[]{MMOItems.plugin.getItem("ENCHANTMENT", "LIFE_STEAL2"), MMOItems.plugin.getItem("ENCHANTMENT", "IMPAIRMENT"), MMOItems.plugin.getItem("ENCHANTMENT", "DRAW"), MMOItems.plugin.getItem("ENCHANTMENT", "FORTUNE"), MMOItems.plugin.getItem("ENCHANTMENT", "EXPERIENCE")}),
    ENCHANTING3(Professions.ENCHANTING, "3", new ItemStack[]{MMOItems.plugin.getItem("ENCHANTMENT", "SHARPNESS2"), MMOItems.plugin.getItem("ENCHANTMENT", "RELIEVE2"), MMOItems.plugin.getItem("ENCHANTMENT", "REPLENISH2"), MMOItems.plugin.getItem("ENCHANTMENT", "IGNITE"), MMOItems.plugin.getItem("ENCHANTMENT", "COMBUST"), MMOItems.plugin.getItem("ENCHANTMENT", "POSTHUMOUS"), MMOItems.plugin.getItem("ENCHANTMENT", "CULING"), MMOItems.plugin.getItem("ENCHANTMENT", "REFLECT"), MMOItems.plugin.getItem("ENCHANTMENT", "YIELD")}),
    ENCHANTING4(Professions.ENCHANTING, "4", new ItemStack[]{MMOItems.plugin.getItem("ENCHANTMENT", "IMPAIRMENT2"), MMOItems.plugin.getItem("ENCHANTMENT", "IGNITE2"), MMOItems.plugin.getItem("ENCHANTMENT", "DRAW2"), MMOItems.plugin.getItem("ENCHANTMENT", "FORTUNE2"), MMOItems.plugin.getItem("ENCHANTMENT", "KAMIKAZE"), MMOItems.plugin.getItem("ENCHANTMENT", "FURTHERANCE")}),
    ENCHANTING5(Professions.ENCHANTING, "5", new ItemStack[]{MMOItems.plugin.getItem("ENCHANTMENT", "SHARPNESS3"), MMOItems.plugin.getItem("ENCHANTMENT", "LIFE_STEAL3"), MMOItems.plugin.getItem("ENCHANTMENT", "RELIEVE3"), MMOItems.plugin.getItem("ENCHANTMENT", "REPLENISH3"), MMOItems.plugin.getItem("ENCHANTMENT", "COMBUST2"), MMOItems.plugin.getItem("ENCHANTMENT", "EXPERIENCE2"), MMOItems.plugin.getItem("ENCHANTMENT", "YIELD2"), MMOItems.plugin.getItem("ENCHANTMENT", "STAGGER"), MMOItems.plugin.getItem("ENCHANTMENT", "GNASH")}),
    ENCHANTING6(Professions.ENCHANTING, "6", new ItemStack[]{MMOItems.plugin.getItem("ENCHANTMENT", "LIFE_STEAL4"), MMOItems.plugin.getItem("ENCHANTMENT", "FORTUNE3"), MMOItems.plugin.getItem("ENCHANTMENT", "IMPAIRMENT3"), MMOItems.plugin.getItem("ENCHANTMENT", "IGNITE3"), MMOItems.plugin.getItem("ENCHANTMENT", "DRAW3"), MMOItems.plugin.getItem("ENCHANTMENT", "POSTHUMOUS2"), MMOItems.plugin.getItem("ENCHANTMENT", "KAMIKAZE2"), MMOItems.plugin.getItem("ENCHANTMENT", "REFLECT2"), MMOItems.plugin.getItem("ENCHANTMENT", "CULING2"), MMOItems.plugin.getItem("ENCHANTMENT", "FURTHERANCE2")}),
    ENCHANTING7(Professions.ENCHANTING, "7", new ItemStack[]{MMOItems.plugin.getItem("ENCHANTMENT", "SHARPNESS4"), MMOItems.plugin.getItem("ENCHANTMENT", "RELIEVE4"), MMOItems.plugin.getItem("ENCHANTMENT", "REFLECT3"), MMOItems.plugin.getItem("ENCHANTMENT", "GNASH2")}),
    ENCHANTING8(Professions.ENCHANTING, "8", new ItemStack[]{MMOItems.plugin.getItem("ENCHANTMENT", "KAMIKAZE3"), MMOItems.plugin.getItem("ENCHANTMENT", "FURTHERANCE3")}),
    ENCHANTING9(Professions.ENCHANTING, "9", new ItemStack[]{MMOItems.plugin.getItem("ENCHANTMENT", "CULING"), MMOItems.plugin.getItem("ENCHANTMENT", "POSTHUMOUS")}),
    ENCHANTING10(Professions.ENCHANTING, "10", new ItemStack[]{MMOItems.plugin.getItem("ENCHANTMENT", "SHARPNESS5"), MMOItems.plugin.getItem("ENCHANTMENT", "REFLECT4"), MMOItems.plugin.getItem("ENCHANTMENT", "COMBUST3"), MMOItems.plugin.getItem("ENCHANTMENT", "GNASH3"), MMOItems.plugin.getItem("ENCHANTMENT", "STAGGER2")}),

    COOKING1(Professions.COOKING, "1", new ItemStack[]{MMOItems.plugin.getItem("STEW", "HEALING_STEW"), MMOItems.plugin.getItem("STEW", "MANA_STEW"), MMOItems.plugin.getItem("STEW", "REGEN_STEW"), MMOItems.plugin.getItem("STEW", "STRENGTH_STEW"), MMOItems.plugin.getItem("STEW", "SWIFT_STEW"), MMOItems.plugin.getItem("STEW", "JUMP_STEW")}),
    COOKING2(Professions.COOKING, "2", new ItemStack[]{MMOItems.plugin.getItem("STEW", "HEALING_STEW2"), MMOItems.plugin.getItem("STEW", "STRENGTH_STEW2"), MMOItems.plugin.getItem("TOTEM", "REGEN_TOTEM"), MMOItems.plugin.getItem("TOTEM", "SWIFT_TOTEM"), MMOItems.plugin.getItem("STEW", "MINERS_SNACK")}),
    COOKING3(Professions.COOKING, "3", new ItemStack[]{MMOItems.plugin.getItem("STEW", "HEALING_STEW3"), MMOItems.plugin.getItem("STEW", "MANA_STEW2"), MMOItems.plugin.getItem("STEW", "MINERS_SNACK2"), MMOItems.plugin.getItem("STEW", "REGEN_STEW2"), MMOItems.plugin.getItem("STEW", "SWIFT_STEW2"), MMOItems.plugin.getItem("TOTEM", "JUMP_TOTEM"), MMOItems.plugin.getItem("TOTEM", "STRENGTH_TOTEM")}),
    COOKING4(Professions.COOKING, "4", new ItemStack[]{MMOItems.plugin.getItem("STEW", "MANA_STEW3"), MMOItems.plugin.getItem("STEW", "MINERS_SNACK3"), MMOItems.plugin.getItem("TOTEM", "STRENGTH_TOTEM2"), MMOItems.plugin.getItem("TOTEM", "SWIFT_TOTEM2"), MMOItems.plugin.getItem("STEW", "JUMP_STEW2")}),
    COOKING5(Professions.COOKING, "5", new ItemStack[]{MMOItems.plugin.getItem("STEW", "MINERS_SNACK4"), MMOItems.plugin.getItem("TOTEM", "SWIFT_TOTEM3"), MMOItems.plugin.getItem("TOTEM", "REGEN_TOTEM2"), MMOItems.plugin.getItem("TOTEM", "JUMP_TOTEM2")}),
    COOKING6(Professions.COOKING, "6", new ItemStack[]{MMOItems.plugin.getItem("STEW", "REGEN_STEW3"), MMOItems.plugin.getItem("TOTEM", "STRENGTH_TOTEM3"), MMOItems.plugin.getItem("STEW", "SWIFT_STEW3")}),
    COOKING7(Professions.COOKING, "7", new ItemStack[]{MMOItems.plugin.getItem("TOTEM", "SWIFT_TOTEM4"), MMOItems.plugin.getItem("TOTEM", "JUMP_TOTEM3")}),
    COOKING8(Professions.COOKING, "8", new ItemStack[]{MMOItems.plugin.getItem("STEW", "STRENGTH_STEW3"), MMOItems.plugin.getItem("TOTEM", "REGEN_TOTEM3")}),
    COOKING9(Professions.COOKING, "9", new ItemStack[]{}),
    COOKING10(Professions.COOKING, "10", new ItemStack[]{}),

    SPELLFORGING1(Professions.SPELLFORGING, "1", new ItemStack[]{MMOItems.plugin.getItem("SPELL", "TARGETED_FIREBALL3"),
            MMOItems.plugin.getItem("WAND", "RESOLVE2"), MMOItems.plugin.getItem("WAND", "TARGETED_FIREBALL2"), MMOItems.plugin.getItem("WAND", "ARCANE_HAIL2"), MMOItems.plugin.getItem("WAND", "MAGMA_FISSURE2")}),
    SPELLFORGING2(Professions.SPELLFORGING, "2", new ItemStack[]{MMOItems.plugin.getItem("SPELL", "SPARKLE3"), MMOItems.plugin.getItem("SPELL", "BUNNY_MODE3"),
            MMOItems.plugin.getItem("WAND", "TARGETED_FIREBALL3"), MMOItems.plugin.getItem("WAND", "BUNNY_MODE2"), MMOItems.plugin.getItem("WAND", "BURNING_HANDS2"), MMOItems.plugin.getItem("WAND", "ICE_CRYSTAL2"), MMOItems.plugin.getItem("WAND", "CURSED_BEAM2")}),
    SPELLFORGING3(Professions.SPELLFORGING, "3", new ItemStack[]{MMOItems.plugin.getItem("SPELL", "ARCANE_RIFT3"), MMOItems.plugin.getItem("SPELL", "BURNING_HANDS3"), MMOItems.plugin.getItem("SPELL", "FREEZING_CURSE3"),
            MMOItems.plugin.getItem("WAND", "RESOLVE3"), MMOItems.plugin.getItem("WAND", "SPARKLE2"), MMOItems.plugin.getItem("WAND", "SHULKER_MISSILE2"), MMOItems.plugin.getItem("WAND", "ARCANE_HAIL3"), MMOItems.plugin.getItem("WAND", "CORRUPTED_FANGS2"), MMOItems.plugin.getItem("WAND", "BOUNCY_FIREBALL3"), MMOItems.plugin.getItem("WAND", "LIFE_ENDER3"), MMOItems.plugin.getItem("WAND", "CORRUPT2")}),
    SPELLFORGING4(Professions.SPELLFORGING, "4", new ItemStack[]{MMOItems.plugin.getItem("SPELL", "SHULKER_MISSILE3"), MMOItems.plugin.getItem("SPELL", "ARCANE_HAIL3"), MMOItems.plugin.getItem("SPELL", "FROZEN_AURA3"), MMOItems.plugin.getItem("SPELL", "MONITOR_MUTE3"),
            MMOItems.plugin.getItem("WAND", "MAGMA_FISSURE3"), MMOItems.plugin.getItem("WAND", "BLACK_HOLE2"), MMOItems.plugin.getItem("WAND", "CURSED_BEAM3"), MMOItems.plugin.getItem("WAND", "CORRUPT3"), MMOItems.plugin.getItem("WAND", "CONTAMINATION_RITUAL2")}),
    SPELLFORGING5(Professions.SPELLFORGING, "5", new ItemStack[]{MMOItems.plugin.getItem("SPELL", "MAGMA_FISSURE3"), MMOItems.plugin.getItem("SPELL", "ICE_CRYSTAL3"), MMOItems.plugin.getItem("SPELL", "CORRUPTED_FANGS3"),
            MMOItems.plugin.getItem("WAND", "SPARKLE3"), MMOItems.plugin.getItem("WAND", "ARCANE_RIFT3"), MMOItems.plugin.getItem("WAND", "BURNING_HANDS3"), MMOItems.plugin.getItem("WAND", "ICE_CRYSTAL3"), MMOItems.plugin.getItem("WAND", "CORRUPTED_FANGS3"), MMOItems.plugin.getItem("WAND", "LIFE_ENDER3"), MMOItems.plugin.getItem("WAND", "MONITOR_MUTE2")}),
    SPELLFORGING6(Professions.SPELLFORGING, "6", new ItemStack[]{MMOItems.plugin.getItem("SPELL", "BLACK_HOLE3"), MMOItems.plugin.getItem("SPELL", "BOUNCY_FIREBALL3"), MMOItems.plugin.getItem("SPELL", "CURSED_BEAM3"),
            MMOItems.plugin.getItem("WAND", "SHULKER_MISSILE3"), MMOItems.plugin.getItem("WAND", "BOUNCY_FIREBALL3")}),
    SPELLFORGING7(Professions.SPELLFORGING, "7", new ItemStack[]{MMOItems.plugin.getItem("SPELL", "LIFE_ENDER3"), MMOItems.plugin.getItem("SPELL", "CORRUPT3"),
            MMOItems.plugin.getItem("WAND", "FREEZING_CURSE3"), MMOItems.plugin.getItem("WAND", "FROZEN_AURA3"), MMOItems.plugin.getItem("WAND", "CONTAMINATION_RITUAL3"), MMOItems.plugin.getItem("WAND", "MONITOR_MUTE3")}),
    SPELLFORGING8(Professions.SPELLFORGING, "8", new ItemStack[]{}),
    SPELLFORGING9(Professions.SPELLFORGING, "9", new ItemStack[]{MMOItems.plugin.getItem("SPELL", "CONTAMINATION_RITUAL3")}),
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
