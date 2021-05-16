package monzter.adventurescraft.plugin.shared.GUIs.mainMenu.donation.miningPass;

import net.Indyuce.mmoitems.MMOItems;
import org.bukkit.inventory.ItemStack;

public enum MiningPassLevels {
    Level1("1", 500, new ItemStack[]{MMOItems.plugin.getItem("CONSUMABLE", "LOOTBOX3")}, new int[]{5}, false, 0),
    Level2("2", 1000, new ItemStack[]{MMOItems.plugin.getItem("VOUCHER", "EXP_VOUCHER3")}, new int[]{1}, false, 0),
    Level3("3", 2000, new ItemStack[]{MMOItems.plugin.getItem("BOOSTER", "SELL_BOOSTER3")}, new int[]{1}, false, 0),
    Level4("4", 3000, new ItemStack[]{MMOItems.plugin.getItem("VOUCHER", "MONEY_VOUCHER3")}, new int[]{1}, false, 0),
    Level5("5", 4000, new ItemStack[]{MMOItems.plugin.getItem("BOOSTER", "PET_EXP_BOOSTER3")}, new int[]{1}, false, 0),
    Level6("6", 5500, new ItemStack[]{MMOItems.plugin.getItem("CATALYST", "HAROLDS_LANTERN2")}, new int[]{1}, false, 0),
    Level7("7", 7000, new ItemStack[]{MMOItems.plugin.getItem("CONSUMABLE", "LOOTBOX4")}, new int[]{5}, false, 0),
    Level8("8", 8500, new ItemStack[]{MMOItems.plugin.getItem("BOOSTER", "EXP_BOOSTER3")}, new int[]{1}, false, 0),
    Level9("9", 10000, new ItemStack[]{MMOItems.plugin.getItem("VOUCHER", "PET_EXP_VOUCHER3")}, new int[]{1}, false, 0),
    Level10("10", 11000, new ItemStack[]{MMOItems.plugin.getItem("BOOSTER", "LUCK_BOOSTER3")}, new int[]{1}, true, 250),
    Level11("11", 12000, new ItemStack[]{MMOItems.plugin.getItem("CONSUMABLE", "LOOTBOX3")}, new int[]{5}, false, 0),
    ;
    private String level;
    private int price;
    private ItemStack[] rewards;
    private int[] rewardAmount;
    private boolean adventureCoinReward;
    private int coins;

    MiningPassLevels(String level, int price, ItemStack[] rewards, int[] rewardAmount, boolean adventureCoinReward, int coins) {
        this.level = level;
        this.price = price;
        this.rewards = rewards;
        this.rewardAmount = rewardAmount;
        this.adventureCoinReward = adventureCoinReward;
        this.coins = coins;
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

    public boolean isAdventureCoinReward() {
        return adventureCoinReward;
    }

    public int getCoins() {
        return coins;
    }
}
