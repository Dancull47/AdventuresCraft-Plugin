package monzter.adventurescraft.plugin.network.PrisonGamemode.shared.GUIs.mainMenu.quests;

import org.bukkit.ChatColor;

public enum MiningPassJobs {
    DIORITE_MINER(new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "250 Diorite Blocks" + ChatColor.WHITE + "!"}, 250, 100, true),
    POLISHED_ANDESITE_MINER(new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "250 Polished Andesite Blocks" + ChatColor.WHITE + "!"}, 250, 100, true),
    POLISHED_DIORITE_MINER(new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "250 Polished Diorite Blocks" + ChatColor.WHITE + "!"}, 250, 100, true),
    SMOOTH_STONE_MINER(new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "250 Smooth Stone Blocks" + ChatColor.WHITE + "!"}, 250, 100, true),
    POLISHED_GRANITE_MINER(new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "250 Polished Granite Blocks" + ChatColor.WHITE + "!"}, 250, 100, true),

    ROCK_HITTER(new String[]{ChatColor.WHITE + "Break " + ChatColor.GREEN + "250 Coal Ore" + ChatColor.WHITE + "!"}, 250, 100, true),
    ;

    private final String[] questDescription;
    private final int rewardMoney;
    private final int rewardMiningPassExperience;
    private final boolean daily;


    MiningPassJobs(String[] questDescription, int rewardMoney, int experience, boolean daily) {
        this.questDescription = questDescription;
        this.rewardMoney = rewardMoney;
        this.rewardMiningPassExperience = experience;
        this.daily = daily;
    }

    public int getRewardMoney() {
        return rewardMoney;
    }

    public String[] getQuestDescription() {
        return questDescription;
    }

    public int getRewardMiningPassExperience() {
        return rewardMiningPassExperience;
    }

    public boolean isDaily() {
        return daily;
    }
}
