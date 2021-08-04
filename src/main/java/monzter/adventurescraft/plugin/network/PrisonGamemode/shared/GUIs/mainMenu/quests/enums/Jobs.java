package monzter.adventurescraft.plugin.network.PrisonGamemode.shared.GUIs.mainMenu.quests.enums;

import monzter.adventurescraft.plugin.utilities.enums.Professions;
import org.bukkit.ChatColor;

public enum Jobs {
    ROCK_HITTER(QuestGiver.MERLE,
            new String[]{ChatColor.WHITE + "Break " + ChatColor.GREEN + "%betonquest_default-Town-Merle:point.ROCK_HITTER.amount%/250 Cobblestone Blocks" + ChatColor.WHITE + "!"},
            null, 0, new String[]{Professions.ENCHANTING.name() + " 100"}, 0, 250, 0),
    STONE_AGE(QuestGiver.MERLE,
            new String[]{ChatColor.WHITE + "Break " + ChatColor.GREEN + "%betonquest_default-Town-Merle:point.STONE_AGE.amount%/500 Blocks" + ChatColor.WHITE + " with a " + ChatColor.GREEN + "Stone Pickaxe" + ChatColor.WHITE + "!"},
            null, 0, new String[]{Professions.ENCHANTING.name() + " 100"}, 0, 500, 0),

    BLUE_SUBSTANCE(QuestGiver.MERLE,
            new String[]{ChatColor.WHITE + "Break " + ChatColor.GREEN + "%betonquest_default-Town-Merle:point.BLUE_SUBSTANCE.amount%/500 Lapis Ore" + ChatColor.WHITE + "!"},
            null, 0, new String[]{Professions.ENCHANTING.name() + " 250"}, 0, 1000, 0),
    RED_SUBSTANCE(QuestGiver.MERLE,
            new String[]{ChatColor.WHITE + "Break " + ChatColor.GREEN + "%betonquest_default-Town-Merle:point.RED_SUBSTANCE.amount%/500 Redstone Ore" + ChatColor.WHITE + "!"},
            null, 0, new String[]{Professions.ENCHANTING.name() + " 250"}, 0, 1000, 0),

    PURPUR_HARDNESS(QuestGiver.MERLE,
            new String[]{ChatColor.WHITE + "Break " + ChatColor.GREEN + "%betonquest_default-Town-Merle:point.PURPUR_HARDNESS.amount%/250 Purpur Blocks" + ChatColor.WHITE + "!"},
            null, 0, new String[]{Professions.ENCHANTING.name() + " 500"}, 0, 5000, 0),
    QUARTZ_HARDNESS(QuestGiver.MERLE,
            new String[]{ChatColor.WHITE + "Break " + ChatColor.GREEN + "%betonquest_default-Town-Merle:point.QUARTZ_HARDNESS.amount%/250 Quartz Blocks" + ChatColor.WHITE + "!"},
            null, 0, new String[]{Professions.ENCHANTING.name() + " 500"}, 0, 5000, 0),

    IRON_AGE(QuestGiver.MERLE,
            new String[]{ChatColor.WHITE + "Break " + ChatColor.GREEN + "%betonquest_default-Town-Merle:point.IRON_AGE.amount%/750 Blocks" + ChatColor.WHITE + " with an " + ChatColor.GREEN + "Iron Pickaxe" + ChatColor.WHITE + "!"},
            null, 0, new String[]{Professions.ENCHANTING.name() + " 250"}, 0, 1500, 0),
    GOLDEN_AGE(QuestGiver.MERLE,
            new String[]{ChatColor.WHITE + "Break " + ChatColor.GREEN + "%betonquest_default-Town-Merle:point.GOLDEN_AGE.amount%/750 Blocks" + ChatColor.WHITE + " with a " + ChatColor.GREEN + "Golden Pickaxe" + ChatColor.WHITE + "!"},
            null, 0, new String[]{Professions.ENCHANTING.name() + " 250"}, 0, 1500, 0),

    DIAMOND_AGE(QuestGiver.MERLE,
            new String[]{ChatColor.WHITE + "Break " + ChatColor.GREEN + "%betonquest_default-Town-Merle:point.DIAMOND_AGE.amount%/1,000 Blocks" + ChatColor.WHITE + " with a " + ChatColor.GREEN + "Diamond Pickaxe" + ChatColor.WHITE + "!"},
            null, 0, new String[]{Professions.ENCHANTING.name() + " 300"}, 0, 2000, 0),
    NETHERITE_AGE(QuestGiver.MERLE,
            new String[]{ChatColor.WHITE + "Break " + ChatColor.GREEN + "%betonquest_default-Town-Merle:point.NETHERITE_AGE.amount%/1,000 Blocks" + ChatColor.WHITE + " with a " + ChatColor.GREEN + "Netherite Pickaxe" + ChatColor.WHITE + "!"},
            null, 0, new String[]{Professions.ENCHANTING.name() + " 300"}, 0, 2000, 0),
    ;

    private final QuestGiver questGiver;
    private final String[] questDescription;
    private final String[] rewardItems;
    private final int rewardEXP;
    private final String[] rewardProfessionEXP;
    private final int rewardMoney;
    private final int rewardExperience;
    private final int rewardPetExperience;


    Jobs(QuestGiver questGiver, String[] questDescription, String[] rewardItems, int rewardEXP, String[] rewardProfessionEXP, int rewardMoney, int experience, int petExperience) {
        this.questGiver = questGiver;
        this.questDescription = questDescription;
        this.rewardItems = rewardItems;
        this.rewardEXP = rewardEXP;
        this.rewardProfessionEXP = rewardProfessionEXP;
        this.rewardMoney = rewardMoney;
        this.rewardExperience = experience;
        this.rewardPetExperience = petExperience;
    }

    public QuestGiver getQuestGiver() {
        return questGiver;
    }

    public String[] getRewardItems() {
        return rewardItems;
    }

    public int getRewardEXP() {
        return rewardEXP;
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

    public int getRewardExperience() {
        return rewardExperience;
    }

    public int getRewardPetExperience() {
        return rewardPetExperience;
    }
}
