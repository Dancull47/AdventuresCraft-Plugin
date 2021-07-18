package monzter.adventurescraft.plugin.network.PrisonGamemode.shared.GUIs.mainMenu.quests;

import monzter.adventurescraft.plugin.utilities.enums.PrisonStatsDisplay;
import monzter.adventurescraft.plugin.utilities.enums.Professions;
import org.bukkit.ChatColor;

public enum Quests {
    UNDERSTANDING_EXPERIENCE(QuestGiver.WIZARD,
            new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "100 Blocks" + ChatColor.WHITE + " to earn " +
                    PrisonStatsDisplay.EXPERIENCE_AMOUNT.getName() + ChatColor.WHITE + "!"},
            new String[]{"VOUCHER EXP_VOUCHER 1"}, 0, new String[]{Professions.ENCHANTING.name() + " 250"}, 0, 0, 0),
    NEW_ENCHANTER(QuestGiver.WIZARD,
            new String[]{ChatColor.WHITE + "Consume the " + ChatColor.BOLD + "EXP Voucher" + ChatColor.WHITE + " given to",
                    ChatColor.WHITE + "you from the previous quest. ",
                    "",
                    ChatColor.WHITE + "Interact with the " + ChatColor.DARK_PURPLE + "Enchanter" + ChatColor.WHITE + " while",
                    ChatColor.WHITE + "holding a " + ChatColor.BOLD + "Tool" + ChatColor.WHITE + ", then purchase",
                    ChatColor.WHITE + "the " + ChatColor.GREEN + "Experience " + ChatColor.DARK_PURPLE + "Enchantment" + ChatColor.WHITE + "!"},
            new String[]{"VOUCHER EXP_VOUCHER 1"}, 0, new String[]{Professions.ENCHANTING.name() + " 250"}, 0, 0, 0),
    ;

    private final QuestGiver questGiver;
    private final String[] questDescription;
    private final String[] rewardItems;
    private final int rewardEXP;
    private final String[] rewardProfessionEXP;
    private final int rewardMoney;
    private final int rewardExperience;
    private final int rewardPetExperience;


    Quests(QuestGiver questGiver, String[] questDescription, String[] rewardItems, int rewardEXP, String[] rewardProfessionEXP, int rewardMoney, int experience, int petExperience) {
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