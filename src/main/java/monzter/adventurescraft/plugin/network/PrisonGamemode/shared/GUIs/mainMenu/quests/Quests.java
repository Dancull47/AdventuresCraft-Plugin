package monzter.adventurescraft.plugin.network.PrisonGamemode.shared.GUIs.mainMenu.quests;

import monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.quests.Professions;
import org.bukkit.ChatColor;

public enum Quests {
    NOVICE_ENCHANTER(QuestGiver.WIZARD, new String[]{ChatColor.WHITE + "Bring the Wizard " + ChatColor.GREEN + "192 Paper" + ChatColor.WHITE + "!"}, new String[]{"ENCHANTMENT ENCHANTING_BOOK2 1"}, 10, new String[]{Professions.ENCHANTING.name() + " 1"}, 10),
    ;

    private final QuestGiver questGiver;
    private final String[] questDescription;
    private final String[] rewardItems;
    private final int rewardEXP;
    private final String[] rewardProfessionEXP;
    private final int rewardMoney;

    Quests(QuestGiver questGiver, String[] questDescription, String[] rewardItems, int rewardEXP, String[] rewardProfessionEXP, int rewardMoney) {
        this.questGiver = questGiver;
        this.questDescription = questDescription;
        this.rewardItems = rewardItems;
        this.rewardEXP = rewardEXP;
        this.rewardProfessionEXP = rewardProfessionEXP;
        this.rewardMoney = rewardMoney;
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
}
