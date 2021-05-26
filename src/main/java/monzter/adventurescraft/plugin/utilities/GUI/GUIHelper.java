package monzter.adventurescraft.plugin.utilities.GUI;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public interface GUIHelper {
    ItemStack background();
    ItemStack background(Material material);
    ItemStack backButton();
    ItemStack nextPageButton();
    ItemStack previousPageButton();
    ItemStack firstPageButton();
    ItemStack lastPageButton();
    String guiName(String name);
    ItemStack questInactive(String questName, String[] questDescription, String[] questRewards, String questGiver, String questGiverArea);
    ItemStack questActive(String questName, String[] questDescription, String[] questRewards, String questGiver, String questGiverArea);
    ItemStack questComplete(String questName, String[] questDescription, String[] questRewards, String questGiver, String questGiverArea);
    ItemStack questUnclaimed(String questName, String[] questDescription, String[] questRewards, String questGiver, String questGiverArea);
}
