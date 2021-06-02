package monzter.adventurescraft.plugin.network.PrisonGamemode.shared.GUIs.mainMenu.donation.miningPass;

import net.Indyuce.mmoitems.MMOItems;
import org.bukkit.inventory.ItemStack;

public enum MiningPassLevels {
    Level1("1", 500, new ItemStack[]{MMOItems.plugin.getItem("CONSUMABLE", "LOOTBOX2")}, new int[]{5}, false, 0),
    Level2("2", 1000, new ItemStack[]{MMOItems.plugin.getItem("VOUCHER", "EXP_VOUCHER2")}, new int[]{1}, false, 0),
    Level3("3", 2000, new ItemStack[]{MMOItems.plugin.getItem("BOOSTER", "SELL_BOOSTER2")}, new int[]{1}, false, 0),
    Level4("4", 3000, new ItemStack[]{MMOItems.plugin.getItem("VOUCHER", "MONEY_VOUCHER2")}, new int[]{1}, false, 0),
    Level5("5", 4000, new ItemStack[]{MMOItems.plugin.getItem("BOOSTER", "PET_EXP_BOOSTER2")}, new int[]{1}, false, 0),
    Level6("6", 5500, new ItemStack[]{MMOItems.plugin.getItem("CATALYST", "HAROLDS_LANTERN")}, new int[]{1}, false, 0),
    Level7("7", 7000, new ItemStack[]{MMOItems.plugin.getItem("CONSUMABLE", "LOOTBOX3")}, new int[]{5}, false, 0),
    Level8("8", 8500, new ItemStack[]{MMOItems.plugin.getItem("BOOSTER", "EXP_BOOSTER2")}, new int[]{1}, false, 0),
    Level9("9", 10000, new ItemStack[]{MMOItems.plugin.getItem("VOUCHER", "PET_EXP_VOUCHER2")}, new int[]{1}, false, 0),
    Level10("10", 11500, new ItemStack[]{MMOItems.plugin.getItem("BOOSTER", "LUCK_BOOSTER2")}, new int[]{1}, true, 60),
    Level11("11", 13000, new ItemStack[]{MMOItems.plugin.getItem("VOUCHER", "MONEY_MULTIPLIER")}, new int[]{1}, false, 0),
    Level12("12", 14500, new ItemStack[]{MMOItems.plugin.getItem("PET", "PET_EGG2")}, new int[]{1}, false, 0),
    Level13("13", 16000, new ItemStack[]{MMOItems.plugin.getItem("VOUCHER", "MONEY_VOUCHER2")}, new int[]{1}, false, 0),
    Level14("14", 17500, new ItemStack[]{MMOItems.plugin.getItem("BOOSTER", "BLOCK_BOOSTER2")}, new int[]{1}, false, 0),
    Level15("15", 19000, new ItemStack[]{MMOItems.plugin.getItem("ACCESSORY", "PRISONER_TOOTH")}, new int[]{1}, false, 0),
    Level16("16", 20500, new ItemStack[]{MMOItems.plugin.getItem("CONSUMABLE", "LOOTBOX3")}, new int[]{10}, false, 0),
    Level17("17", 22000, new ItemStack[]{MMOItems.plugin.getItem("BOOSTER", "PET_EXP_BOOSTER3")}, new int[]{1}, false, 0),
    Level18("18", 23500, new ItemStack[]{MMOItems.plugin.getItem("VOUCHER", "EXP_VOUCHER3")}, new int[]{1}, false, 0),
    Level19("19", 25000, new ItemStack[]{MMOItems.plugin.getItem("VOUCHER", "MONEY_VOUCHER3")}, new int[]{1}, false, 0),
    Level20("20", 26500, new ItemStack[]{MMOItems.plugin.getItem("BOOSTER", "BLOCK_BOOSTER3")}, new int[]{1}, true, 60),
    Level21("21", 28000, new ItemStack[]{MMOItems.plugin.getItem("BOOSTER", "EXP_BOOSTER3")}, new int[]{1}, false, 0),
    Level22("22", 29500, new ItemStack[]{MMOItems.plugin.getItem("VOUCHER", "MONEY_MULTIPLIER2")}, new int[]{1}, false, 0),
    Level23("23", 31000, new ItemStack[]{MMOItems.plugin.getItem("PET", "PET_EGG3")}, new int[]{1}, false, 0),
    Level24("24", 32500, new ItemStack[]{MMOItems.plugin.getItem("CONSUMABLE", "LOOTBOX4")}, new int[]{10}, false, 0),
    Level25("25", 34000, new ItemStack[]{MMOItems.plugin.getItem("TOOL", "HAROLDS_PICKAXE2")}, new int[]{1}, false, 0),
    Level26("26", 35500, new ItemStack[]{MMOItems.plugin.getItem("BOOSTER", "PET_EXP_BOOSTER3")}, new int[]{1}, false, 0),
    Level27("27", 37000, new ItemStack[]{MMOItems.plugin.getItem("VOUCHER", "EXP_VOUCHER3")}, new int[]{1}, false, 0),
    Level28("28", 38500, new ItemStack[]{MMOItems.plugin.getItem("BOOSTER", "SELL_BOOSTER3")}, new int[]{1}, false, 0),
    Level29("29", 40000, new ItemStack[]{MMOItems.plugin.getItem("VOUCHER", "MONEY_VOUCHER3")}, new int[]{1}, false, 0),
    Level30("30", 41500, new ItemStack[]{MMOItems.plugin.getItem("BOOSTER", "PET_EXP_BOOSTER3")}, new int[]{1}, true, 60),
    Level31("31", 43000, new ItemStack[]{MMOItems.plugin.getItem("BOOSTER", "EXP_BOOSTER3")}, new int[]{1}, false, 0),
    Level32("32", 44500, new ItemStack[]{MMOItems.plugin.getItem("VOUCHER", "PET_EXP_VOUCHER3")}, new int[]{1}, false, 0),
    Level33("33", 46000, new ItemStack[]{MMOItems.plugin.getItem("BOOSTER", "LUCK_BOOSTER3")}, new int[]{1}, false, 0),
    Level34("34", 47500, new ItemStack[]{MMOItems.plugin.getItem("VOUCHER", "MONEY_MULTIPLIER3")}, new int[]{1}, false, 0),
    Level35("35", 49000, new ItemStack[]{MMOItems.plugin.getItem("PET", "PET_EGG4")}, new int[]{1}, false, 0),
    Level36("36", 50500, new ItemStack[]{MMOItems.plugin.getItem("CONSUMABLE", "LOOTBOX4")}, new int[]{5}, false, 0),
    Level37("37", 52000, new ItemStack[]{MMOItems.plugin.getItem("BOOSTER", "PET_EXP_BOOSTER4")}, new int[]{1}, false, 0),
    Level38("38", 53500, new ItemStack[]{MMOItems.plugin.getItem("VOUCHER", "EXP_VOUCHER4")}, new int[]{1}, false, 0),
    Level39("39", 55000, new ItemStack[]{MMOItems.plugin.getItem("VOUCHER", "MONEY_VOUCHER4")}, new int[]{1}, false, 0),
    Level40("40", 56500, new ItemStack[]{MMOItems.plugin.getItem("BOOSTER", "BLOCK_BOOSTER4")}, new int[]{1}, true, 60),
    Level41("41", 58000, new ItemStack[]{MMOItems.plugin.getItem("BOOSTER", "EXP_BOOSTER4")}, new int[]{1}, false, 0),
    Level42("42", 59500, new ItemStack[]{MMOItems.plugin.getItem("VOUCHER", "MONEY_MULTIPLIER4")}, new int[]{1}, false, 0),
    Level43("43", 61000, new ItemStack[]{MMOItems.plugin.getItem("BOOSTER", "SELL_BOOSTER4")}, new int[]{1}, false, 0),
    Level44("44", 62500, new ItemStack[]{MMOItems.plugin.getItem("BOOSTER", "LUCK_BOOSTER4")}, new int[]{1}, false, 0),
    Level45("45", 64000, new ItemStack[]{MMOItems.plugin.getItem("BOOSTER", "PET_EXP_BOOSTER4")}, new int[]{1}, false, 0),
    Level46("46", 65500, new ItemStack[]{MMOItems.plugin.getItem("PET", "PET_EGG5")}, new int[]{1}, false, 0),
    Level47("47", 67000, new ItemStack[]{MMOItems.plugin.getItem("PET", "PHOENIX_EGG")}, new int[]{1}, false, 0),
    Level48("48", 68500, new ItemStack[]{MMOItems.plugin.getItem("CONSUMABLE", "LOOTBOX5")}, new int[]{5}, false, 0),
    Level49("49", 70000, new ItemStack[]{MMOItems.plugin.getItem("PET", "DRAGON_EGG")}, new int[]{1}, false, 0),
    Level50("50", 71500, new ItemStack[]{MMOItems.plugin.getItem("ARMOR", "ASCENDANT_MINER_HEAD")}, new int[]{1}, true, 60),
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
