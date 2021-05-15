package monzter.adventurescraft.plugin.shared.GUIs.mainMenu.donation.miningPass;

import net.Indyuce.mmoitems.MMOItems;
import org.bukkit.inventory.ItemStack;

public enum PremiumMiningPassLevels {
    Level1("P1", 500, new ItemStack[]{MMOItems.plugin.getItem("CONSUMABLE", "LOOTBOX3")}, new int[]{5}),
    Level2("P2", 1000, new ItemStack[]{MMOItems.plugin.getItem("VOUCHER", "EXP_VOUCHER3")}, new int[]{1}),
    Level3("P3", 2000, new ItemStack[]{MMOItems.plugin.getItem("BOOSTER", "SELL_BOOSTER3")}, new int[]{1}),
    Level4("P4", 3000, new ItemStack[]{MMOItems.plugin.getItem("VOUCHER", "MONEY_VOUCHER3")}, new int[]{1}),
    Level5("P5", 4000, new ItemStack[]{MMOItems.plugin.getItem("BOOSTER", "PET_EXP_BOOSTER3")}, new int[]{1}),
    Level6("P6", 5500, new ItemStack[]{MMOItems.plugin.getItem("CATALYST", "HAROLDS_LANTERN2")}, new int[]{1}),
    Level7("P7", 7000, new ItemStack[]{MMOItems.plugin.getItem("CONSUMABLE", "LOOTBOX4")}, new int[]{5}),
    Level8("P8", 8500, new ItemStack[]{MMOItems.plugin.getItem("BOOSTER", "EXP_BOOSTER3")}, new int[]{1}),
    Level9("P9", 10000, new ItemStack[]{MMOItems.plugin.getItem("VOUCHER", "PET_EXP_VOUCHER3")}, new int[]{1}),
    Level10("P10", 500, new ItemStack[]{MMOItems.plugin.getItem("BOOSTER", "LUCK_BOOSTER3")}, new int[]{1}),
    Level11("P11", 500, new ItemStack[]{MMOItems.plugin.getItem("CONSUMABLE", "LOOTBOX3")}, new int[]{5}),
    ;
    private String level;
    private int price;
    private ItemStack[] rewards;
    private int[] rewardAmount;

    PremiumMiningPassLevels(String level, int price, ItemStack[] rewards, int[] rewardAmount) {
        this.level = level;
        this.price = price;
        this.rewards = rewards;
        this.rewardAmount = rewardAmount;
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
}
