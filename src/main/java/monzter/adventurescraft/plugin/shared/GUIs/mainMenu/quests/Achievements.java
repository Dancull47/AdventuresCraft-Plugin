package monzter.adventurescraft.plugin.shared.GUIs.mainMenu.quests;

import net.Indyuce.mmoitems.MMOItems;
import org.bukkit.inventory.ItemStack;

public enum Achievements {
    Level1("1", 1_000, new ItemStack[]{null}, new int[]{0}, new String[]{"exp", "miningpassexp"}, new int[]{1_000, 100}),
    Level2("2", 2_500, new ItemStack[]{null}, new int[]{0}, new String[]{"exp", "miningpassexp"}, new int[]{2_500, 250}),
    Level3("3", 5_000, new ItemStack[]{null}, new int[]{0}, new String[]{"exp", "miningpassexp"}, new int[]{5_000, 500}),
    Level4("4", 7_500, new ItemStack[]{null}, new int[]{0}, new String[]{"exp", "miningpassexp"}, new int[]{7_500, 500}),
    Level5("5", 10_000, new ItemStack[]{null}, new int[]{0}, new String[]{"exp", "miningpassexp"}, new int[]{10_000, 1_000}),
    ;
    private String level;
    private int price;
    private ItemStack[] rewards;
    private int[] rewardAmount;
    private String[] currencyRewards;
    private int[] currencyRewardsAmount;

    Achievements(String level, int price, ItemStack[] rewards, int[] rewardAmount, String[] currencyRewards, int[] currencyRewardsAmount) {
        this.level = level;
        this.price = price;
        this.rewards = rewards;
        this.rewardAmount = rewardAmount;
        this.currencyRewards = currencyRewards;
        this.currencyRewardsAmount = currencyRewardsAmount;
    }

    public String getLevel() {
        return level;
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
}
