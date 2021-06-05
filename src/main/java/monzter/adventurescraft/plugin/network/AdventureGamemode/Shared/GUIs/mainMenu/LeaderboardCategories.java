package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.mainMenu;

import monzter.adventurescraft.plugin.utilities.enums.PrisonStatsDisplay;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum LeaderboardCategories {
    Money(PrisonStatsDisplay.MONEY_AMOUNT.getName(), new ItemStack(Material.SUNFLOWER), "Money", 5),
    Experience(PrisonStatsDisplay.EXPERIENCE_AMOUNT.getName(), new ItemStack(Material.GREEN_DYE), "EXP", 10),
    TotalBlocks(ChatColor.GOLD + "⛏ Blocks Mined", new ItemStack(Material.COBBLESTONE), "Blocks", 15),
    PetExperience(PrisonStatsDisplay.PET_EXPERIENCE_AMOUNT.getName(), new ItemStack(Material.BLUE_DYE), "PetEXP", 5),
    MiningPass(PrisonStatsDisplay.MINING_PASS_EXPERIENCE.getName(), new ItemStack(Material.HOPPER_MINECART), "MiningPass", 10),
    Prestige(ChatColor.BLUE + "Prestige", new ItemStack(Material.DIAMOND_PICKAXE), "Prestige", 15),
    ;
    private String name;
    private ItemStack itemStack;
    private String id;
    private int price;

    LeaderboardCategories(String name, ItemStack itemStack, String id, int price) {
        this.name = name;
        this.itemStack = itemStack;
        this.id = id;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public String getId() {
        return id;
    }
}
