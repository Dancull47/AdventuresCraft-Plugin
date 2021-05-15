package monzter.adventurescraft.plugin.shared.GUIs.mainMenu.donation.miningPass;

import net.Indyuce.mmoitems.MMOItems;
import org.bukkit.inventory.ItemStack;

public enum MiningPassLevels {
    Level1("1", 500, new ItemStack[]{MMOItems.plugin.getItem("CONSUMABLE", "LOOTBOX"), MMOItems.plugin.getItem("CONSUMABLE", "LOOTBOX2")}, new int[]{1,5}),
    Level2("1", 500, new ItemStack[]{MMOItems.plugin.getItem("CONSUMABLE", "LOOTBOX"), MMOItems.plugin.getItem("CONSUMABLE", "LOOTBOX2")}, new int[]{1,5}),
    Level3("1", 500, new ItemStack[]{MMOItems.plugin.getItem("CONSUMABLE", "LOOTBOX"), MMOItems.plugin.getItem("CONSUMABLE", "LOOTBOX2")}, new int[]{1,5}),
    Level4("1", 500, new ItemStack[]{MMOItems.plugin.getItem("CONSUMABLE", "LOOTBOX"), MMOItems.plugin.getItem("CONSUMABLE", "LOOTBOX2")}, new int[]{1,5}),
    Level5("1", 500, new ItemStack[]{MMOItems.plugin.getItem("CONSUMABLE", "LOOTBOX"), MMOItems.plugin.getItem("CONSUMABLE", "LOOTBOX2")}, new int[]{1,5}),
    Level6("1", 500, new ItemStack[]{MMOItems.plugin.getItem("CONSUMABLE", "LOOTBOX"), MMOItems.plugin.getItem("CONSUMABLE", "LOOTBOX2")}, new int[]{1,5}),
    Level7("1", 500, new ItemStack[]{MMOItems.plugin.getItem("CONSUMABLE", "LOOTBOX"), MMOItems.plugin.getItem("CONSUMABLE", "LOOTBOX2")}, new int[]{1,5}),
    Level8("1", 500, new ItemStack[]{MMOItems.plugin.getItem("CONSUMABLE", "LOOTBOX"), MMOItems.plugin.getItem("CONSUMABLE", "LOOTBOX2")}, new int[]{1,5}),
    Level9("1", 500, new ItemStack[]{MMOItems.plugin.getItem("CONSUMABLE", "LOOTBOX"), MMOItems.plugin.getItem("CONSUMABLE", "LOOTBOX2")}, new int[]{1,5}),
    Level10("1", 500, new ItemStack[]{MMOItems.plugin.getItem("CONSUMABLE", "LOOTBOX"), MMOItems.plugin.getItem("CONSUMABLE", "LOOTBOX2")}, new int[]{1,5}),
    Level11("1", 500, new ItemStack[]{MMOItems.plugin.getItem("CONSUMABLE", "LOOTBOX"), MMOItems.plugin.getItem("CONSUMABLE", "LOOTBOX2")}, new int[]{1,5}),
    ;
    private String name;
    private int price;
    private ItemStack[] rewards;
    private int[] rewardAmount;

    MiningPassLevels(String name, int price, ItemStack[] rewards, int[] rewardAmount) {
        this.name = name;
        this.price = price;
        this.rewards = rewards;
        this.rewardAmount = rewardAmount;
    }

    public String getName() {
        return name;
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
