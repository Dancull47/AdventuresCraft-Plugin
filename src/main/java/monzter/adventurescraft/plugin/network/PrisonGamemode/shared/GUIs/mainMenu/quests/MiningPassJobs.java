package monzter.adventurescraft.plugin.network.PrisonGamemode.shared.GUIs.mainMenu.quests;

import org.bukkit.ChatColor;

public enum MiningPassJobs {
    //    Daily Set #1
    DIORITE_MINER(new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "%betonquest_default-Town-Dan:point.DIORITE.amount%/250 Diorite Blocks" + ChatColor.WHITE + "!"}, 50000, 300, "Daily"),
    POLISHED_ANDESITE_MINER(new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "%betonquest_default-Town-Dan:point.POLISHED_ANDESITE.amount%/250 Polished Andesite Blocks" + ChatColor.WHITE + "!"}, 50000, 300, "Daily"),
    POLISHED_DIORITE_MINER(new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "%betonquest_default-Town-Dan:point.POLISHED_DIORITE.amount%/250 Polished Diorite Blocks" + ChatColor.WHITE + "!"}, 50000, 300, "Daily"),
    SMOOTH_STONE_MINER(new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "%betonquest_default-Town-Dan:point.SMOOTH_STONE.amount%/250 Smooth Stone Blocks" + ChatColor.WHITE + "!"}, 50000, 300, "Daily"),
    POLISHED_GRANITE_MINER(new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "%betonquest_default-Town-Dan:point.POLISHED_GRANITE.amount%/250 Polished Granite Blocks" + ChatColor.WHITE + "!"}, 50000, 300, "Daily"),
    //    Daily Set #2
    COAL_FINDER(new String[]{ChatColor.WHITE + "Break " + ChatColor.GREEN + "%betonquest_default-Town-Dan:point.COAL_ORE.amount%/250 Coal Ore" + ChatColor.WHITE + "!"}, 50000, 300, "Daily"),
    IRON_FINDER(new String[]{ChatColor.WHITE + "Break " + ChatColor.GREEN + "%betonquest_default-Town-Dan:point.IRON_ORE.amount%/250 Iron Ore" + ChatColor.WHITE + "!"}, 50000, 300, "Daily"),
    GOLD_FINDER(new String[]{ChatColor.WHITE + "Break " + ChatColor.GREEN + "%betonquest_default-Town-Dan:point.GOLD_ORE.amount%/250 Gold Ore" + ChatColor.WHITE + "!"}, 50000, 300, "Daily"),
    DIAMOND_FINDER(new String[]{ChatColor.WHITE + "Break " + ChatColor.GREEN + "%betonquest_default-Town-Dan:point.DIAMOND_ORE.amount%/250 Diamond Ore" + ChatColor.WHITE + "!"}, 50000, 300, "Daily"),
    EMERALD_FINDER(new String[]{ChatColor.WHITE + "Break " + ChatColor.GREEN + "%betonquest_default-Town-Dan:point.EMERALD_ORE.amount%/250 Emerald Ore" + ChatColor.WHITE + "!"}, 50000, 300, "Daily"),
    //    Daily Set #3
    WOOD_WIELDER(new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "%betonquest_default-Town-Dan:point.WOOD_WIELDER.amount%/500 Blocks " + ChatColor.WHITE + "with a " + ChatColor.GREEN + "Wooden Pickaxe" + ChatColor.WHITE + "!"}, 50000, 300, "Daily"),
    STONE_WIELDER(new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "%betonquest_default-Town-Dan:point.STONE_WIELDER.amount%/500 Blocks " + ChatColor.WHITE + "with a " + ChatColor.GREEN + "Stone Pickaxe" + ChatColor.WHITE + "!"}, 50000, 300, "Daily"),
    IRON_WIELDER(new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "%betonquest_default-Town-Dan:point.IRON_WIELDER.amount%/500 Blocks " + ChatColor.WHITE + "with an " + ChatColor.GREEN + "Iron Pickaxe" + ChatColor.WHITE + "!"}, 50000, 300, "Daily"),
    GOLD_WIELDER(new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "%betonquest_default-Town-Dan:point.GOLD_WIELDER.amount%/500 Blocks " + ChatColor.WHITE + "with a " + ChatColor.GREEN + "Golden Pickaxe" + ChatColor.WHITE + "!"}, 50000, 300, "Daily"),
    DIAMOND_WIELDER(new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "%betonquest_default-Town-Dan:point.DIAMOND_WIELDER.amount%/500 Blocks " + ChatColor.WHITE + "with a " + ChatColor.GREEN + "Diamond Pickaxe" + ChatColor.WHITE + "!"}, 50000, 300, "Daily"),

    //    Weekly Set #1
    ADVANCED_STONE_WIELDER(new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "%betonquest_default-Town-Dan:point.ADVANCED_STONE_WIELDER.amount%/5,000 Blocks " + ChatColor.WHITE + "with a " + ChatColor.GREEN + "Stone Pickaxe" + ChatColor.WHITE + "!"}, 1_000_000, 1000, "Weekly"),
    ADVANCED_IRON_WIELDER(new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "%betonquest_default-Town-Dan:point.ADVANCED_IRON_WIELDER.amount%/5,000 Blocks " + ChatColor.WHITE + "with an " + ChatColor.GREEN + "Iron Pickaxe" + ChatColor.WHITE + "!"}, 1_000_000, 1000, "Weekly"),
    ADVANCED_GOLD_WIELDER(new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "%betonquest_default-Town-Dan:point.ADVANCED_GOLD_WIELDER.amount%/5,000 Blocks " + ChatColor.WHITE + "with a " + ChatColor.GREEN + "Golden Pickaxe" + ChatColor.WHITE + "!"}, 1_000_000, 1000, "Weekly"),
    ADVANCED_DIAMOND_WIELDER(new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "%betonquest_default-Town-Dan:point.ADVANCED_DIAMOND_WIELDER.amount%/5,000 Blocks " + ChatColor.WHITE + "with a " + ChatColor.GREEN + "Diamond Pickaxe" + ChatColor.WHITE + "!"}, 1_000_000, 1000, "Weekly"),
    ADVANCED_NETHERITE_WIELDER(new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "%betonquest_default-Town-Dan:point.ADVANCED_NETHERITE_WIELDER.amount%/5,000 Blocks " + ChatColor.WHITE + "with a " + ChatColor.GREEN + "Netherite Pickaxe" + ChatColor.WHITE + "!"}, 1_000_000, 1000, "Weekly"),
    //    Weekly Set #2
    COAL_APPRENTICE(new String[]{ChatColor.WHITE + "Break " + ChatColor.GREEN + "%betonquest_default-Town-Dan:point.COAL_APPRENTICE.amount%/1,000 Coal Blocks" + ChatColor.WHITE + "!"}, 1_000_000, 1000, "Weekly"),
    IRON_APPRENTICE(new String[]{ChatColor.WHITE + "Break " + ChatColor.GREEN + "%betonquest_default-Town-Dan:point.IRON_APPRENTICE.amount%/1,000 Iron Blocks" + ChatColor.WHITE + "!"}, 1_000_000, 1000, "Weekly"),
    REDSTONE_APPRENTICE(new String[]{ChatColor.WHITE + "Break " + ChatColor.GREEN + "%betonquest_default-Town-Dan:point.REDSTONE_APPRENTICE.amount%/1,000 Redstone Blocks" + ChatColor.WHITE + "!"}, 1_000_000, 1000, "Weekly"),
    LAPIS_APPRENTICE(new String[]{ChatColor.WHITE + "Break " + ChatColor.GREEN + "%betonquest_default-Town-Dan:point.LAPIS_APPRENTICE.amount%/1,000 Lapis Blocks" + ChatColor.WHITE + "!"}, 1_000_000, 1000, "Weekly"),
    DIAMOND_APPRENTICE(new String[]{ChatColor.WHITE + "Break " + ChatColor.GREEN + "%betonquest_default-Town-Dan:point.DIAMOND_APPRENTICE.amount%/1,000 Diamond Blocks" + ChatColor.WHITE + "!"}, 1_000_000, 1000, "Weekly"),
    //    Weekly Set #3
    STONE_BREAKER(new String[]{ChatColor.WHITE + "Break " + ChatColor.GREEN + "%betonquest_default-Town-Dan:point.STONE_BREAKER.amount%/2,500 Blocks " + ChatColor.WHITE + "which have " + ChatColor.GREEN + "Stone " + ChatColor.WHITE + "in its name!"}, 1_000_000, 1000, "Weekly"),
    BLOCK_BREAKER(new String[]{ChatColor.WHITE + "Break " + ChatColor.GREEN + "%betonquest_default-Town-Dan:point.BLOCK_BREAKER.amount%/2,500 Blocks " + ChatColor.WHITE + "which have " + ChatColor.GREEN + "Block " + ChatColor.WHITE + "in its name!"}, 1_000_000, 1000, "Weekly"),
    CONCRETE_BREAKER(new String[]{ChatColor.WHITE + "Break " + ChatColor.GREEN + "%betonquest_default-Town-Dan:point.CONCRETE_BREAKER.amount%/2,500 Blocks " + ChatColor.WHITE + "which have " + ChatColor.GREEN + "Concrete " + ChatColor.WHITE + "in its name!"}, 1_000_000, 1000, "Weekly"),
    POLISHED_BREAKER(new String[]{ChatColor.WHITE + "Break " + ChatColor.GREEN + "%betonquest_default-Town-Dan:point.POLISHED_BREAKER.amount%/2,500 Blocks " + ChatColor.WHITE + "which have " + ChatColor.GREEN + "Polished " + ChatColor.WHITE + "in its name!"}, 1_000_000, 1000, "Weekly"),
    ORE_BREAKER(new String[]{ChatColor.WHITE + "Break " + ChatColor.GREEN + "%betonquest_default-Town-Dan:point.ORE_BREAKER.amount%/2,500 Blocks " + ChatColor.WHITE + "which have " + ChatColor.GREEN + "Ore " + ChatColor.WHITE + "in its name!"}, 1_000_000, 1000, "Weekly"),
    //    Weekly Set #4
    NIGHT_MINER(new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "%betonquest_default-Town-Dan:point.NIGHT_MINER.amount%/5,000 Blocks " + ChatColor.WHITE + "while it's " + ChatColor.GREEN + "Night Time" + ChatColor.WHITE + "!"}, 1_000_000, 1000, "Weekly"),
    DAY_MINER(new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "%betonquest_default-Town-Dan:point.DAY_MINER.amount%/5,000 Blocks " + ChatColor.WHITE + "while it's " + ChatColor.GREEN + "Day Time" + ChatColor.WHITE + "!"}, 1_000_000, 1000, "Weekly"),
    SUNNY_MINER(new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "%betonquest_default-Town-Dan:point.SUNNY_MINER.amount%/5,000 Blocks " + ChatColor.WHITE + "while it's " + ChatColor.GREEN + "Sunny" + ChatColor.WHITE + "!"}, 1_000_000, 1000, "Weekly"),
    RAINY_MINER(new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "%betonquest_default-Town-Dan:point.RAINY_MINER.amount%/5,000 Blocks " + ChatColor.WHITE + "while it's " + ChatColor.GREEN + "Rainy" + ChatColor.WHITE + "!"}, 1_000_000, 1000, "Weekly"),
    STORM_MINER(new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "%betonquest_default-Town-Dan:point.STORM_MINER.amount%/5,000 Blocks " + ChatColor.WHITE + "while it's " + ChatColor.GREEN + "Storming" + ChatColor.WHITE + "!"}, 1_000_000, 1000, "Weekly"),
    //    Weekly Set #5
    AE_MINES(new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "%betonquest_default-Town-Dan:point.AE_MINES.amount%/1,000 Blocks " + ChatColor.WHITE + "within " + ChatColor.GREEN + "Mines A-E" + ChatColor.WHITE + "!"}, 1_000_000, 1000, "Weekly"),
    FJ_MINES(new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "%betonquest_default-Town-Dan:point.FJ_MINES.amount%/1,000 Blocks " + ChatColor.WHITE + "within " + ChatColor.GREEN + "Mines F-J" + ChatColor.WHITE + "!"}, 1_000_000, 1000, "Weekly"),
    KO_MINES(new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "%betonquest_default-Town-Dan:point.KO_MINES.amount%/1,000 Blocks " + ChatColor.WHITE + "within " + ChatColor.GREEN + "Mines K-O" + ChatColor.WHITE + "!"}, 1_000_000, 1000, "Weekly"),
    PT_MINES(new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "%betonquest_default-Town-Dan:point.PT_MINES.amount%/1,000 Blocks " + ChatColor.WHITE + "within " + ChatColor.GREEN + "Mines P-T" + ChatColor.WHITE + "!"}, 1_000_000, 1000, "Weekly"),
    UZ_MINES(new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "%betonquest_default-Town-Dan:point.UZ_MINES.amount%/1,000 Blocks " + ChatColor.WHITE + "within " + ChatColor.GREEN + "Mines U-Z" + ChatColor.WHITE + "!"}, 1_000_000, 1000, "Weekly"),

    //    Bonus Set #1
    WOODEN_BEACH(new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "%betonquest_default-Town-Dan:point.WOODEN_BEACH.amount%/1,000 Blocks " + ChatColor.WHITE + "within the " + ChatColor.GREEN + "Beach Mine " + ChatColor.WHITE + "using a " + ChatColor.GREEN + "Wooden Shovel" + ChatColor.WHITE + "!"}, 100_000, 300, "Bonus1"),
    STONE_BEACH(new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "%betonquest_default-Town-Dan:point.STONE_BEACH.amount%/1,000 Blocks " + ChatColor.WHITE + "within the " + ChatColor.GREEN + "Beach Mine " + ChatColor.WHITE + "using a " + ChatColor.GREEN + "Stone Shovel" + ChatColor.WHITE + "!"}, 100_000, 300, "Bonus1"),
    IRON_BEACH(new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "%betonquest_default-Town-Dan:point.IRON_BEACH.amount%/1,000 Blocks " + ChatColor.WHITE + "within the " + ChatColor.GREEN + "Beach Mine " + ChatColor.WHITE + "using an " + ChatColor.GREEN + "Iron Shovel" + ChatColor.WHITE + "!"}, 100_000, 300, "Bonus1"),
    GOLDEN_BEACH(new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "%betonquest_default-Town-Dan:point.GOLDEN_BEACH.amount%/1,000 Blocks " + ChatColor.WHITE + "within the " + ChatColor.GREEN + "Beach Mine " + ChatColor.WHITE + "using a " + ChatColor.GREEN + "Golden Shovel" + ChatColor.WHITE + "!"}, 100_000, 300, "Bonus1"),
    DIAMOND_BEACH(new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "%betonquest_default-Town-Dan:point.DIAMOND_BEACH.amount%/1,000 Blocks " + ChatColor.WHITE + "within the " + ChatColor.GREEN + "Beach Mine " + ChatColor.WHITE + "using a " + ChatColor.GREEN + "Diamond Shovel" + ChatColor.WHITE + "!"}, 100_000, 300, "Bonus1"),
    //    Bonus Set #2
    ORANGE_SHATTER(new String[]{ChatColor.WHITE + "Break " + ChatColor.GREEN + "%betonquest_default-Town-Dan:point.ORANGE_SHATTER.amount%/100 Orange Glass Blocks" + ChatColor.WHITE + "!"}, 100_000, 300, "Bonus2"),
    RED_SHATTER(new String[]{ChatColor.WHITE + "Break " + ChatColor.GREEN + "%betonquest_default-Town-Dan:point.RED_SHATTER.amount%/100 Red Glass Blocks" + ChatColor.WHITE + "!"}, 100_000, 300, "Bonus2"),
    YELLOW_SHATTER(new String[]{ChatColor.WHITE + "Break " + ChatColor.GREEN + "%betonquest_default-Town-Dan:point.YELLOW_SHATTER.amount%/100 Yellow Glass Blocks" + ChatColor.WHITE + "!"}, 100_000, 300, "Bonus2"),
    BLUE_SHATTER(new String[]{ChatColor.WHITE + "Break " + ChatColor.GREEN + "%betonquest_default-Town-Dan:point.BLUE_SHATTER.amount%/100 Blue Glass Blocks" + ChatColor.WHITE + "!"}, 100_000, 300, "Bonus2"),
    GREEN_SHATTER(new String[]{ChatColor.WHITE + "Break " + ChatColor.GREEN + "%betonquest_default-Town-Dan:point.GREEN_SHATTER.amount%/100 Green Glass Blocks" + ChatColor.WHITE + "!"}, 100_000, 300, "Bonus2"),
    //    Bonus Set #3
    PRIMITIVE_MINER(new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "%betonquest_default-Town-Dan:point.PRIMITIVE_MINER.amount%/1,000 Blocks" + ChatColor.WHITE + "!"}, 100_000, 300, "Bonus3"),
    APPRENTICE_MINER(new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "%betonquest_default-Town-Dan:point.APPRENTICE_MINER.amount%/2,500 Blocks" + ChatColor.WHITE + "!"}, 100_000, 300, "Bonus3"),
    JOURNEYMAN_MINER(new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "%betonquest_default-Town-Dan:point.JOURNEYMAN_MINER.amount%/5,000 Blocks" + ChatColor.WHITE + "!"}, 100_000, 300, "Bonus3"),
    MASTER_MINER(new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "%betonquest_default-Town-Dan:point.MASTER_MINER.amount%/7,500 Blocks" + ChatColor.WHITE + "!"}, 100_000, 300, "Bonus3"),
    ASCENDED_MINER(new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "%betonquest_default-Town-Dan:point.ASCENDED_MINER.amount%/10,000 Blocks" + ChatColor.WHITE + "!"}, 100_000, 300, "Bonus3"),
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
