package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.mainMenu.Achievements.Enums;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum AchievementCategory {
    BOSS(new ItemStack(Material.ZOMBIE_HEAD)),
    COMABT(new ItemStack(Material.NETHERITE_SWORD)),
    ;

    private final ItemStack itemStack;


    AchievementCategory(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }
}
