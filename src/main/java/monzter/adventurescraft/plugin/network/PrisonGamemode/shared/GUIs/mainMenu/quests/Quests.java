package monzter.adventurescraft.plugin.network.PrisonGamemode.shared.GUIs.mainMenu.quests;

import monzter.adventurescraft.plugin.utilities.enums.Professions;
import org.bukkit.ChatColor;

public enum Quests {
    UNDERSTANDING_EXPERIENCE(QuestGiver.WIZARD, new String[]{ChatColor.WHITE + "Bring the Wizard " + ChatColor.GREEN + "192 Paper" + ChatColor.WHITE + "!"}, new String[]{"CONSUMABLE EQUIPMENT_LOOTBOX 1"}, 10, new String[]{Professions.ENCHANTING.name() + " 1"}, 10, 3, 1),
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
