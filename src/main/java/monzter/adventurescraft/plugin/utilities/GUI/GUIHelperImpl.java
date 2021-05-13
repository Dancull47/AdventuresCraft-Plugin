package monzter.adventurescraft.plugin.utilities.GUI;

import dev.dbassett.skullcreator.SkullCreator;
import net.kyori.adventure.text.Component;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GUIHelperImpl implements GUIHelper {

    @Override
    public ItemStack background() {
        final ItemStack backgroundItem = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        final ItemMeta backgroundItemMeta = backgroundItem.getItemMeta();

        backgroundItemMeta.displayName(Component.text(" "));
        backgroundItem.setItemMeta(backgroundItemMeta);
        return backgroundItem;
    }

    @Override
    public ItemStack background(Material material) {
        final ItemStack backgroundItem = new ItemStack(material);
        final ItemMeta backgroundItemMeta = backgroundItem.getItemMeta();

        backgroundItemMeta.displayName(Component.text(" "));
        backgroundItem.setItemMeta(backgroundItemMeta);
        return backgroundItem;
    }

    @Override
    public ItemStack backButton() {
        final ItemStack backButton = new ItemStack(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODY1MmUyYjkzNmNhODAyNmJkMjg2NTFkN2M5ZjI4MTlkMmU5MjM2OTc3MzRkMThkZmRiMTM1NTBmOGZkYWQ1ZiJ9fX0="));
        final ItemMeta backButtonItemMeta = backButton.getItemMeta();

        backButtonItemMeta.displayName(Component.text(" "));
        backButton.setItemMeta(backButtonItemMeta);
        return backButton;
    }

    @Override
    public String guiName(String name) {
        if (name.length() > 21)
            System.out.println("Your GUI '" + name + "' found within " + Thread.currentThread().getStackTrace()[2].getClassName().substring(31) + " is longer than what the inventory can NEATLY contain.");
        return
                ChatColor.WHITE.toString() + ChatColor.BOLD + "»" +
                        ChatColor.GRAY + ChatColor.BOLD + "» " +
                        ChatColor.DARK_GRAY + name +
                        ChatColor.GRAY + ChatColor.BOLD + " «" +
                        ChatColor.WHITE + ChatColor.BOLD + "«";
    }
}
