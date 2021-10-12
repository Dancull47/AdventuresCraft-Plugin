package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.mainMenu.Achievements.Enums;

import org.bukkit.ChatColor;

public enum AchievementList {

    REAPER_SLAYER_50(AchievementSubCategory.REAPER,
            new String[]{ChatColor.translateAlternateColorCodes('&', "&cReapers &fslayed &6%betonquest_mobs:point.REAPER.amount%&f/&650")},
            null, null, 100, null, 100),
    ;

    private final AchievementSubCategory achievementCategory;
    private final String[] questDescription;
    private final String[] rewardText;
    private final String[] rewardItems;
    private final int rewardMainEXP;
    private final String[] rewardProfessionEXP;
    private final int rewardMoney;


    AchievementList(AchievementSubCategory achievementCategory, String[] questDescription, String[] rewardText, String[] rewardItems, int rewardMainEXP, String[] rewardProfessionEXP, int rewardMoney) {
        this.achievementCategory = achievementCategory;
        this.questDescription = questDescription;
        this.rewardText = rewardText;
        this.rewardItems = rewardItems;
        this.rewardMainEXP = rewardMainEXP;
        this.rewardProfessionEXP = rewardProfessionEXP;
        this.rewardMoney = rewardMoney;
    }

    public String[] getRewardText() {
        return rewardText;
    }

    public String[] getRewardItems() {
        return rewardItems;
    }

    public int getRewardMainEXP() {
        return rewardMainEXP;
    }

    public String[] getRewardProfessionEXP() {
        return rewardProfessionEXP;
    }

    public int getRewardMoney() {
        return rewardMoney;
    }

    public String[] getQuestDescription() {
        return questDescription;
    }

    public AchievementSubCategory getAchievementCategory() {
        return achievementCategory;
    }
}
