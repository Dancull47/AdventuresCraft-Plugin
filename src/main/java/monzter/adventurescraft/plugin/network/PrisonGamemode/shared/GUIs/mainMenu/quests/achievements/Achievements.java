package monzter.adventurescraft.plugin.network.PrisonGamemode.shared.GUIs.mainMenu.quests.achievements;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum Achievements {
    Miner1("Miner", "Rookie Miner", "betonquest_items:point.TotalBlocks.amount", "MINER.1", "Blocks Mined: ", Material.STONE_PICKAXE, 1_000, new ItemStack[]{null}, new int[]{0}, new String[]{"exp", "miningpassexp"}, new int[]{1_000, 100}),
    Miner2("Miner", "Apprentice Miner", "betonquest_items:point.TotalBlocks.amount", "MINER.2", "Blocks Mined: ", Material.IRON_PICKAXE, 2_500, new ItemStack[]{null}, new int[]{0}, new String[]{"exp", "miningpassexp"}, new int[]{2_500, 250}),
    Miner3("Miner", "Journeyman Miner", "betonquest_items:point.TotalBlocks.amount", "MINER.3", "Blocks Mined: ", Material.GOLDEN_PICKAXE, 5_000, new ItemStack[]{null}, new int[]{0}, new String[]{"exp", "miningpassexp"}, new int[]{5_000, 500}),
    Miner4("Miner", "Master Miner", "betonquest_items:point.TotalBlocks.amount", "MINER.4", "Blocks Mined: ", Material.DIAMOND_PICKAXE, 10_000, new ItemStack[]{null}, new int[]{0}, new String[]{"exp", "miningpassexp"}, new int[]{7_500, 750}),
    Miner5("Miner", "Ascended Miner", "betonquest_items:point.TotalBlocks.amount", "MINER.5", "Blocks Mined: ", Material.NETHERITE_PICKAXE, 20_000, new ItemStack[]{null}, new int[]{0}, new String[]{"exp", "miningpassexp"}, new int[]{10_000, 1_000}),

    Ore1("Ore","Rookie Ores", "ac_Achievement_Ores", "ORES.1", "Ores Mined: ", Material.IRON_ORE, 500, new ItemStack[]{null}, new int[]{0}, new String[]{"exp", "miningpassexp"}, new int[]{500, 50}),
    Ore2("Ore","Apprentice Ores", "ac_Achievement_Ores", "ORES.2", "Ores Mined: ", Material.GOLD_ORE, 1_250, new ItemStack[]{null}, new int[]{0}, new String[]{"exp", "miningpassexp"}, new int[]{1_250, 125}),
    Ore3("Ore","Journeyman Ores", "ac_Achievement_Ores", "ORES.3", "Ores Mined: ", Material.REDSTONE_ORE, 2_500, new ItemStack[]{null}, new int[]{0}, new String[]{"exp", "miningpassexp"}, new int[]{2_500, 250}),
    Ore4("Ore","Master Ores", "ac_Achievement_Ores", "ORES.4", "Ores Mined: ", Material.LAPIS_ORE, 5_000, new ItemStack[]{null}, new int[]{0}, new String[]{"exp", "miningpassexp"}, new int[]{5_000, 500}),
    Ore5("Ore","Ascended Ores", "ac_Achievement_Ores", "ORES.5", "Ores Mined: ", Material.DIAMOND_ORE, 7_500, new ItemStack[]{null}, new int[]{0}, new String[]{"exp", "miningpassexp"}, new int[]{7_500, 750}),
//  MMOItems.plugin.getItem("ARMOR", "PRISONER_HAT")
    ;
    private String group;
    private String name;
    private String placeholder;
    private String permission;
    private String objective;
    private Material material;
    private int price;
    private ItemStack[] rewards;
    private int[] rewardAmount;
    private String[] currencyRewards;
    private int[] currencyRewardsAmount;

    Achievements(String group, String name, String placeholder, String permission, String objective, Material material, int price, ItemStack[] rewards, int[] rewardAmount, String[] currencyRewards, int[] currencyRewardsAmount) {
        this.group = group;
        this.name = name;
        this.placeholder = placeholder;
        this.permission = permission;
        this.objective = objective;
        this.material = material;
        this.price = price;
        this.rewards = rewards;
        this.rewardAmount = rewardAmount;
        this.currencyRewards = currencyRewards;
        this.currencyRewardsAmount = currencyRewardsAmount;
    }

    public String getName() {
        return name;
    }

    public String getObjective() {
        return objective;
    }

    public int getPrice() {
        return price;
    }

    public ItemStack[] getRewards() {
        return rewards;
    }

    public int[] getRewardAmount() {
        return rewardAmount;
    }

    public String[] getCurrencyRewards() {
        return currencyRewards;
    }

    public int[] getCurrencyRewardsAmount() {
        return currencyRewardsAmount;
    }

    public Material getMaterial() {
        return material;
    }

    public String getPermission() {
        return permission;
    }

    public String getGroup() {
        return group;
    }

    public String getPlaceholder() {
        return placeholder;
    }
}

