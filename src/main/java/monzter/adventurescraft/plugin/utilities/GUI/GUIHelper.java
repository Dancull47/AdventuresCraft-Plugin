package monzter.adventurescraft.plugin.utilities.GUI;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public interface GUIHelper {
    ItemStack background();
    ItemStack background(Material material);
    ItemStack backButton();
    String guiName(String name);
}
