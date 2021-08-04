package monzter.adventurescraft.plugin.network.PrisonGamemode.shared.GUIs.mainMenu.quests.enums;

import monzter.adventurescraft.plugin.utilities.enums.PrisonStatsDisplay;
import monzter.adventurescraft.plugin.utilities.enums.Professions;
import org.bukkit.ChatColor;

public enum Quests {
    UNDERSTANDING_EXPERIENCE(QuestGiver.WIZARD,
            new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "%betonquest_default-Town-Wizard:point.Mine1.amount%/100 Blocks" + ChatColor.WHITE + " to earn " +
                    PrisonStatsDisplay.EXPERIENCE_AMOUNT.getName() + ChatColor.WHITE + "!"},
            new String[]{"VOUCHER EXP_VOUCHER 1"}, 0, new String[]{Professions.ENCHANTING.name() + " 250"}, 0, 0, 0),
    NEW_ENCHANTER(QuestGiver.WIZARD,
            new String[]{ChatColor.WHITE + "Consume the " + ChatColor.BOLD + "EXP Voucher" + ChatColor.WHITE + " given to",
                    ChatColor.WHITE + "you from the previous quest. ",
                    "",
                    ChatColor.WHITE + "Interact with the " + ChatColor.DARK_PURPLE + "Enchanter" + ChatColor.WHITE + " while",
                    ChatColor.WHITE + "holding a " + ChatColor.BOLD + "Tool" + ChatColor.WHITE + ", then purchase",
                    ChatColor.WHITE + "the " + ChatColor.GREEN + "Experience " + ChatColor.DARK_PURPLE + "Enchantment" + ChatColor.WHITE + "!"},
            new String[]{"BOOSTER EXP_BOOSTER 1"}, 0, new String[]{Professions.ENCHANTING.name() + " 100"}, 0, 0, 0),
    ENCHANTING_PIONEER(QuestGiver.WIZARD,
            new String[]{ChatColor.WHITE + "Speak with " + ChatColor.GREEN + "Merle" + ChatColor.WHITE + " outside of the",
                    ChatColor.DARK_PURPLE + "Enchanting Library " + ChatColor.WHITE + "to further increase",
                    ChatColor.WHITE + "your " + ChatColor.DARK_PURPLE + "Enchanting Profession" + ChatColor.WHITE + "!"},
            null, 0, new String[]{Professions.ENCHANTING.name() + " 100"}, 0, 0, 0),

    EGG_EXERCISE(QuestGiver.JOY,
            new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "%betonquest_default-Town-Joy:point.Mine1.amount%/100 Blocks" + ChatColor.WHITE + " with the " + ChatColor.BOLD + "Pet Egg" + ChatColor.WHITE + " inside",
                    ChatColor.WHITE + "your inventory to earn 100 " + PrisonStatsDisplay.PET_EXPERIENCE_AMOUNT.getName() + ChatColor.WHITE + "!"},
            null, 0, new String[]{Professions.COMPANIONSHIP.name() + " 100"}, 0, 0, 0),
    HATCHING_AN_EGG(QuestGiver.JOY,
            new String[]{ChatColor.WHITE + "Return to the " + ChatColor.GOLD + "Pet Shop" + ChatColor.WHITE + " and speak with " + ChatColor.GREEN + "Sarah" + ChatColor.WHITE + " to",
                    ChatColor.WHITE + "hatch the Pet Egg, with your 100 " + PrisonStatsDisplay.PET_EXPERIENCE_AMOUNT.getName() + ChatColor.WHITE + "!"},
            null, 0, new String[]{Professions.COMPANIONSHIP.name() + " 250"}, 0, 0, 0),
    EVOLUTION(QuestGiver.JOY,
            new String[]{ChatColor.WHITE + "Interact with the " + ChatColor.GOLD + "Evolver" + ChatColor.WHITE + " behind the",
                    ChatColor.WHITE + "desk, to learn more about " + ChatColor.GREEN + "Evolving Pets" + ChatColor.WHITE + "!"},
            null, 0, new String[]{Professions.COMPANIONSHIP.name() + " 250"}, 0, 0, 0),

    WATER_CRYSTAL(QuestGiver.ASH,
            new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "%betonquest_default-Town-Ash:point.LAPIS1.amount%/64 Lapis Blocks" + ChatColor.WHITE + ", ",
                    ChatColor.GREEN + "%betonquest_default-Town-Ash:point.BLUE_GLASS1.amount%/32 Blue Stained Glass Blocks" + ChatColor.WHITE + "," + ChatColor.WHITE + " and",
                    ChatColor.GREEN + "%betonquest_default-Town-Ash:point.SEA_LANTERN1.amount%/16 Sea Lanterns " + ChatColor.WHITE + "while the",
                    ChatColor.DARK_PURPLE + "Fractured Crystal " + ChatColor.WHITE + "is within your inventory!"},
            new String[]{"MATERIAL WATER_CRYSTAL5 1"}, 0, null, 100_000_000, 0, 500_000),
    KINGS_CROWN(QuestGiver.ASH,
            new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "%betonquest_default-Town-Ash:point.GOLD1.amount%/64 Gold Blocks" + ChatColor.WHITE + ", ",
                    ChatColor.GREEN + "%betonquest_default-Town-Ash:point.EMERALD1.amount%/32 Emerald Blocks" + ChatColor.WHITE + "," + ChatColor.WHITE + " and",
                    ChatColor.GREEN + "%betonquest_default-Town-Ash:point.STAINED_GLASS1.amount%/16 Stained Glass Blocks " + ChatColor.WHITE + "while the",
                    ChatColor.DARK_PURPLE + "Broken Crown " + ChatColor.WHITE + "is within your inventory!"},
            new String[]{"MATERIAL PHOENIX_CROWN5 1"}, 0, null, 100_000_000, 0, 500_000),

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
