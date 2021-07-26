package monzter.adventurescraft.plugin.network.PrisonGamemode.shared.GUIs.mainMenu.quests;

import org.bukkit.ChatColor;

public enum MiningPassJobs {
    //    Daily Set #1
    DIORITE_MINER(new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "250 Diorite Blocks" + ChatColor.WHITE + "!"}, 100, 250, true),
    POLISHED_ANDESITE_MINER(new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "250 Polished Andesite Blocks" + ChatColor.WHITE + "!"}, 100, 250, true),
    POLISHED_DIORITE_MINER(new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "250 Polished Diorite Blocks" + ChatColor.WHITE + "!"}, 100, 250, true),
    SMOOTH_STONE_MINER(new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "250 Smooth Stone Blocks" + ChatColor.WHITE + "!"}, 100, 250, true),
    POLISHED_GRANITE_MINER(new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "250 Polished Granite Blocks" + ChatColor.WHITE + "!"}, 100, 250, true),
    //    Daily Set #2
    COAL_FINDER(new String[]{ChatColor.WHITE + "Break " + ChatColor.GREEN + "250 Coal Ore" + ChatColor.WHITE + "!"}, 100, 250, true),
    IRON_FINDER(new String[]{ChatColor.WHITE + "Break " + ChatColor.GREEN + "250 Iron Ore" + ChatColor.WHITE + "!"}, 100, 250, true),
    GOLD_FINDER(new String[]{ChatColor.WHITE + "Break " + ChatColor.GREEN + "250 Gold Ore" + ChatColor.WHITE + "!"}, 100, 250, true),
    DIAMOND_FINDER(new String[]{ChatColor.WHITE + "Break " + ChatColor.GREEN + "250 Diamond Ore" + ChatColor.WHITE + "!"}, 100, 250, true),
    EMERALD_FINDER(new String[]{ChatColor.WHITE + "Break " + ChatColor.GREEN + "250 Emerald Ore" + ChatColor.WHITE + "!"}, 100, 250, true),
    //    Daily Set #3
    WOOD_WIELDER(new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "500 Blocks " + ChatColor.WHITE + "with a " + ChatColor.GREEN + "Wooden Pickaxe" + ChatColor.WHITE + "!"}, 100, 250, true),
    STONE_WIELDER(new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "500 Blocks " + ChatColor.WHITE + "with a " + ChatColor.GREEN + "Stone Pickaxe" + ChatColor.WHITE + "!"}, 100, 250, true),
    IRON_WIELDER(new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "500 Blocks " + ChatColor.WHITE + "with an " + ChatColor.GREEN + "Iron Pickaxe" + ChatColor.WHITE + "!"}, 100, 250, true),
    GOLD_WIELDER(new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "500 Blocks " + ChatColor.WHITE + "with a " + ChatColor.GREEN + "Golden Pickaxe" + ChatColor.WHITE + "!"}, 100, 250, true),
    DIAMOND_WIELDER(new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "500 Blocks " + ChatColor.WHITE + "with a " + ChatColor.GREEN + "Diamond Pickaxe" + ChatColor.WHITE + "!"}, 100, 250, true),
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
