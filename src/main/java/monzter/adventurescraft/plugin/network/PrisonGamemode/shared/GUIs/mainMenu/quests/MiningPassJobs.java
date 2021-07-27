package monzter.adventurescraft.plugin.network.PrisonGamemode.shared.GUIs.mainMenu.quests;

import org.bukkit.ChatColor;

public enum MiningPassJobs {
    //    Daily Set #1
    DIORITE_MINER(new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "250 Diorite Blocks" + ChatColor.WHITE + "!"}, 100, 100, "Daily"),
    POLISHED_ANDESITE_MINER(new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "250 Polished Andesite Blocks" + ChatColor.WHITE + "!"}, 100, 100, "Daily"),
    POLISHED_DIORITE_MINER(new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "250 Polished Diorite Blocks" + ChatColor.WHITE + "!"}, 100, 100, "Daily"),
    SMOOTH_STONE_MINER(new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "250 Smooth Stone Blocks" + ChatColor.WHITE + "!"}, 100, 100, "Daily"),
    POLISHED_GRANITE_MINER(new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "250 Polished Granite Blocks" + ChatColor.WHITE + "!"}, 100, 100, "Daily"),
    //    Daily Set #2
    COAL_FINDER(new String[]{ChatColor.WHITE + "Break " + ChatColor.GREEN + "250 Coal Ore" + ChatColor.WHITE + "!"}, 100, 100, "Daily"),
    IRON_FINDER(new String[]{ChatColor.WHITE + "Break " + ChatColor.GREEN + "250 Iron Ore" + ChatColor.WHITE + "!"}, 100, 100, "Daily"),
    GOLD_FINDER(new String[]{ChatColor.WHITE + "Break " + ChatColor.GREEN + "250 Gold Ore" + ChatColor.WHITE + "!"}, 100, 100, "Daily"),
    DIAMOND_FINDER(new String[]{ChatColor.WHITE + "Break " + ChatColor.GREEN + "250 Diamond Ore" + ChatColor.WHITE + "!"}, 100, 100, "Daily"),
    EMERALD_FINDER(new String[]{ChatColor.WHITE + "Break " + ChatColor.GREEN + "250 Emerald Ore" + ChatColor.WHITE + "!"}, 100, 100, "Daily"),
    //    Daily Set #3
    WOOD_WIELDER(new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "500 Blocks " + ChatColor.WHITE + "with a " + ChatColor.GREEN + "Wooden Pickaxe" + ChatColor.WHITE + "!"}, 100, 100, "Daily"),
    STONE_WIELDER(new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "500 Blocks " + ChatColor.WHITE + "with a " + ChatColor.GREEN + "Stone Pickaxe" + ChatColor.WHITE + "!"}, 100, 100, "Daily"),
    IRON_WIELDER(new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "500 Blocks " + ChatColor.WHITE + "with an " + ChatColor.GREEN + "Iron Pickaxe" + ChatColor.WHITE + "!"}, 100, 100, "Daily"),
    GOLD_WIELDER(new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "500 Blocks " + ChatColor.WHITE + "with a " + ChatColor.GREEN + "Golden Pickaxe" + ChatColor.WHITE + "!"}, 100, 100, "Daily"),
    DIAMOND_WIELDER(new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "500 Blocks " + ChatColor.WHITE + "with a " + ChatColor.GREEN + "Diamond Pickaxe" + ChatColor.WHITE + "!"}, 100, 100, "Daily"),

    //    Weekly Set #1
    ADVANCED_STONE_WIELDER(new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "5,000 Blocks " + ChatColor.WHITE + "with a " + ChatColor.GREEN + "Stone Pickaxe" + ChatColor.WHITE + "!"}, 1000, 500, "Weekly"),
    ADVANCED_IRON_WIELDER(new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "5,000 Blocks " + ChatColor.WHITE + "with an " + ChatColor.GREEN + "Iron Pickaxe" + ChatColor.WHITE + "!"}, 1000, 500, "Weekly"),
    ADVANCED_GOLD_WIELDER(new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "5,000 Blocks " + ChatColor.WHITE + "with a " + ChatColor.GREEN + "Golden Pickaxe" + ChatColor.WHITE + "!"}, 1000, 500, "Weekly"),
    ADVANCED_DIAMOND_WIELDER(new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "5,000 Blocks " + ChatColor.WHITE + "with a " + ChatColor.GREEN + "Diamond Pickaxe" + ChatColor.WHITE + "!"}, 1000, 500, "Weekly"),
    ADVANCED_NETHERITE_WIELDER(new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "5,000 Blocks " + ChatColor.WHITE + "with a " + ChatColor.GREEN + "Netherite Pickaxe" + ChatColor.WHITE + "!"}, 1000, 500, "Weekly"),
    //    Weekly Set #2
    COAL_APPRENTICE(new String[]{ChatColor.WHITE + "Break " + ChatColor.GREEN + "1,000 Coal Blocks" + ChatColor.WHITE + "!"}, 1000, 500, "Weekly"),
    IRON_APPRENTICE(new String[]{ChatColor.WHITE + "Break " + ChatColor.GREEN + "1,000 Iron Blocks" + ChatColor.WHITE + "!"}, 1000, 500, "Weekly"),
    REDSTONE_APPRENTICE(new String[]{ChatColor.WHITE + "Break " + ChatColor.GREEN + "1,000 Redstone Blocks" + ChatColor.WHITE + "!"}, 1000, 500, "Weekly"),
    LAPIS_APPRENTICE(new String[]{ChatColor.WHITE + "Break " + ChatColor.GREEN + "1,000 Lapis Blocks" + ChatColor.WHITE + "!"}, 1000, 500, "Weekly"),
    DIAMOND_APPRENTICE(new String[]{ChatColor.WHITE + "Break " + ChatColor.GREEN + "1,000 Diamond Blocks" + ChatColor.WHITE + "!"}, 1000, 500, "Weekly"),
    //    Weekly Set #3
    STONE_BREAKER(new String[]{ChatColor.WHITE + "Break " + ChatColor.GREEN + "2,500 Blocks " + ChatColor.WHITE + "which have " + ChatColor.GREEN + "Stone " + ChatColor.WHITE + "in its name!"}, 1000, 500, "Weekly"),
    BLOCK_BREAKER(new String[]{ChatColor.WHITE + "Break " + ChatColor.GREEN + "2,500 Blocks " + ChatColor.WHITE + "which have " + ChatColor.GREEN + "Block " + ChatColor.WHITE + "in its name!"}, 1000, 500, "Weekly"),
    CONCRETE_BREAKER(new String[]{ChatColor.WHITE + "Break " + ChatColor.GREEN + "2,500 Blocks " + ChatColor.WHITE + "which have " + ChatColor.GREEN + "Concrete " + ChatColor.WHITE + "in its name!"}, 1000, 500, "Weekly"),
    POLISHED_BREAKER(new String[]{ChatColor.WHITE + "Break " + ChatColor.GREEN + "2,500 Blocks " + ChatColor.WHITE + "which have " + ChatColor.GREEN + "Polished " + ChatColor.WHITE + "in its name!"}, 1000, 500, "Weekly"),
    ORE_BREAKER(new String[]{ChatColor.WHITE + "Break " + ChatColor.GREEN + "2,500 Blocks " + ChatColor.WHITE + "which have " + ChatColor.GREEN + "Ore " + ChatColor.WHITE + "in its name!"}, 1000, 500, "Weekly"),
    //    Weekly Set #4
    NIGHT_MINER(new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "5,000 Blocks " + ChatColor.WHITE + "while it's " + ChatColor.GREEN + "Night Time" + ChatColor.WHITE + "!"}, 1000, 500, "Weekly"),
    DAY_MINER(new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "5,000 Blocks " + ChatColor.WHITE + "while it's " + ChatColor.GREEN + "Day Time" + ChatColor.WHITE + "!"}, 1000, 500, "Weekly"),
    SUNNY_MINER(new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "5,000 Blocks " + ChatColor.WHITE + "while it's " + ChatColor.GREEN + "Sunny" + ChatColor.WHITE + "!"}, 1000, 500, "Weekly"),
    RAINY_MINER(new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "5,000 Blocks " + ChatColor.WHITE + "while it's " + ChatColor.GREEN + "Rainy" + ChatColor.WHITE + "!"}, 1000, 500, "Weekly"),
    STORM_MINER(new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "5,000 Blocks " + ChatColor.WHITE + "while it's " + ChatColor.GREEN + "Storming" + ChatColor.WHITE + "!"}, 1000, 500, "Weekly"),
    //    Weekly Set #5
    AE_MINES(new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "1,000 Blocks " + ChatColor.WHITE + "within " + ChatColor.GREEN + "Mines A-E" + ChatColor.WHITE + "!"}, 1000, 500, "Weekly"),
    FJ_MINES(new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "1,000 Blocks " + ChatColor.WHITE + "within " + ChatColor.GREEN + "Mines F-J" + ChatColor.WHITE + "!"}, 1000, 500, "Weekly"),
    KO_MINES(new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "1,000 Blocks " + ChatColor.WHITE + "within " + ChatColor.GREEN + "Mines K-O" + ChatColor.WHITE + "!"}, 1000, 500, "Weekly"),
    PT_MINES(new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "1,000 Blocks " + ChatColor.WHITE + "within " + ChatColor.GREEN + "Mines P-T" + ChatColor.WHITE + "!"}, 1000, 500, "Weekly"),
    UZ_MINES(new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "1,000 Blocks " + ChatColor.WHITE + "within " + ChatColor.GREEN + "Mines U-Z" + ChatColor.WHITE + "!"}, 1000, 500, "Weekly"),

    //    Bonus Set #1
    WOODEN_BEACH(new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "1,000 Blocks " + ChatColor.WHITE + "within the " + ChatColor.GREEN + "Beach Mine " + ChatColor.WHITE + "using a " + ChatColor.GREEN + "Wooden Shovel" + ChatColor.WHITE + "!"}, 1000, 500, "Bonus1"),
    STONE_BEACH(new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "1,000 Blocks " + ChatColor.WHITE + "within the " + ChatColor.GREEN + "Beach Mine " + ChatColor.WHITE + "using a " + ChatColor.GREEN + "Stone Shovel" + ChatColor.WHITE + "!"}, 1000, 500, "Bonus1"),
    IRON_BEACH(new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "1,000 Blocks " + ChatColor.WHITE + "within the " + ChatColor.GREEN + "Beach Mine " + ChatColor.WHITE + "using an " + ChatColor.GREEN + "Iron Shovel" + ChatColor.WHITE + "!"}, 1000, 500, "Bonus1"),
    GOLDEN_BEACH(new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "1,000 Blocks " + ChatColor.WHITE + "within the " + ChatColor.GREEN + "Beach Mine " + ChatColor.WHITE + "using a " + ChatColor.GREEN + "Golden Shovel" + ChatColor.WHITE + "!"}, 1000, 500, "Bonus1"),
    DIAMOND_BEACH(new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "1,000 Blocks " + ChatColor.WHITE + "within the " + ChatColor.GREEN + "Beach Mine " + ChatColor.WHITE + "using a " + ChatColor.GREEN + "Diamond Shovel" + ChatColor.WHITE + "!"}, 1000, 500, "Bonus1"),


    ;

    private final String[] questDescription;
    private final int rewardMoney;
    private final int rewardMiningPassExperience;
    private final String type;

    MiningPassJobs(String[] questDescription, int rewardMoney, int experience, String type) {
        this.questDescription = questDescription;
        this.rewardMoney = rewardMoney;
        this.rewardMiningPassExperience = experience;
        this.type = type;
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

    public String getType() {
        return type;
    }
}
