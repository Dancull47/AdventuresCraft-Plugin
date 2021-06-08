package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.mainMenu.professions;

import net.Indyuce.mmoitems.MMOItems;
import org.bukkit.inventory.ItemStack;

public enum FarmingLevels {
    Level1("1", new ItemStack[]{MMOItems.plugin.getItem("ARMOR", "MELON_HAT"), MMOItems.plugin.getItem("ACCESSORY", "POWERFUL_NECKLACE")}),
    Level2("2", new ItemStack[]{}),
    Level3("3", new ItemStack[]{MMOItems.plugin.getItem("ACCESSORY", "DEEP_IMPACTOR"), MMOItems.plugin.getItem("ACCESSORY", "LUCKY_STRIKER")}),
    Level4("4", new ItemStack[]{MMOItems.plugin.getItem("STAFF", "SEED_STAFF"), MMOItems.plugin.getItem("ARMOR", "MELON_HAT2")}),
    Level5("5", new ItemStack[]{MMOItems.plugin.getItem("ARMOR", "MELON_HAT3"), MMOItems.plugin.getItem("ACCESSORY", "LUCKY_STRIKER2"), MMOItems.plugin.getItem("MISCELLANEOUS", "UNLIMITED_WATER")}),
    Level6("6", new ItemStack[]{}),
    Level7("7", new ItemStack[]{MMOItems.plugin.getItem("ACCESSORY", "DEEP_IMPACTOR2"), MMOItems.plugin.getItem("ACCESSORY", "LUCKY_STRIKER3"), MMOItems.plugin.getItem("ACCESSORY", "POWERFUL_NECKLACE2")}),
    Level8("8", new ItemStack[]{MMOItems.plugin.getItem("STAFF", "WHEAT_STAFF")}),
    Level9("9", new ItemStack[]{}),
    Level10("10", new ItemStack[]{}),
    ;
    private String level;
    private ItemStack[] rewards;

    FarmingLevels(String level, ItemStack[] rewards) {
        this.level = level;
        this.rewards = rewards;
    }

    public String getLevel() {
        return level;
    }

    public ItemStack[] getRewards() {
        return rewards;
    }
}
