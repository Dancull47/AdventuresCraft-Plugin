package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.mainMenu.Achievements.Enums;

import monzter.adventurescraft.plugin.network.AdventureGamemode.Enums.Bosses;
import org.bukkit.inventory.ItemStack;

public enum AchievementSubCategory {
    REAPER(AchievementCategory.BOSS, Bosses.REAPER.getItemStack()),
    ;

    private final AchievementCategory achievementCategory;
    private final ItemStack itemStack;


    AchievementSubCategory(AchievementCategory achievementCategory, ItemStack itemStack) {
        this.achievementCategory = achievementCategory;
        this.itemStack = itemStack;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public AchievementCategory getAchievementCategory() {
        return achievementCategory;
    }
}
