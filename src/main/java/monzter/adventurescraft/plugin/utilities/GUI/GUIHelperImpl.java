package monzter.adventurescraft.plugin.utilities.GUI;

import dev.dbassett.skullcreator.SkullCreator;
import monzter.adventurescraft.plugin.utilities.enums.Prefix;
import net.kyori.adventure.text.Component;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

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

        backButtonItemMeta.displayName(Component.text(ChatColor.GREEN + "Go Back"));
        backButton.setItemMeta(backButtonItemMeta);
        return backButton;
    }

    @Override
    public ItemStack nextPageButton() {
        final ItemStack nextPageItem = new ItemStack(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmEzYjhmNjgxZGFhZDhiZjQzNmNhZThkYTNmZTgxMzFmNjJhMTYyYWI4MWFmNjM5YzNlMDY0NGFhNmFiYWMyZiJ9fX0="));
        final ItemMeta nextPageItemMeta = nextPageItem.getItemMeta();

        nextPageItemMeta.displayName(Component.text(ChatColor.GREEN + "Next Page"));
        nextPageItem.setItemMeta(nextPageItemMeta);
        return nextPageItem;
    }

    @Override
    public ItemStack previousPageButton() {
        final ItemStack previousPageItem = new ItemStack(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODY1MmUyYjkzNmNhODAyNmJkMjg2NTFkN2M5ZjI4MTlkMmU5MjM2OTc3MzRkMThkZmRiMTM1NTBmOGZkYWQ1ZiJ9fX0="));
        final ItemMeta previousPageItemMeta = previousPageItem.getItemMeta();

        previousPageItemMeta.displayName(Component.text(ChatColor.GREEN + "Previous Page"));
        previousPageItem.setItemMeta(previousPageItemMeta);
        return previousPageItem;
    }

    @Override
    public ItemStack firstPageButton() {
        final ItemStack firstPageItem = new ItemStack(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODE2ZWEzNGE2YTZlYzVjMDUxZTY5MzJmMWM0NzFiNzAxMmIyOThkMzhkMTc5ZjFiNDg3YzQxM2Y1MTk1OWNkNCJ9fX0="));
        final ItemMeta firstPageItemMeta = firstPageItem.getItemMeta();

        firstPageItemMeta.displayName(Component.text(ChatColor.GREEN + "First Page"));
        firstPageItem.setItemMeta(firstPageItemMeta);
        return firstPageItem;
    }

    @Override
    public ItemStack lastPageButton() {
        final ItemStack lastPageItem = new ItemStack(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWM5ZWM3MWMxMDY4ZWM2ZTAzZDJjOTI4N2Y5ZGE5MTkzNjM5ZjNhNjM1ZTJmYmQ1ZDg3YzJmYWJlNjQ5OSJ9fX0="));
        final ItemMeta lastPageItemMeta = lastPageItem.getItemMeta();

        lastPageItemMeta.displayName(Component.text(ChatColor.GREEN + "Last Page"));
        lastPageItem.setItemMeta(lastPageItemMeta);
        return lastPageItem;
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

    @Override
    public ItemStack questInactive(String questName, String[] questDescription, String[] questRewards, String questGiver, String questGiverArea) {
        ItemStack inactive = new ItemStack(Material.PAPER);
        final ItemMeta inactiveItemMeta = inactive.getItemMeta();

        inactiveItemMeta.displayName(Component.text(ChatColor.RED.toString() + ChatColor.BOLD + "[Inactive] " + ChatColor.WHITE + ChatColor.BOLD + questName));

        List<String> lore = new ArrayList<>();
        lore.add("");
        for (String lore2 : questDescription)
            lore.add(lore2);
        lore.add("");
        lore.add(ChatColor.YELLOW.toString() + ChatColor.BOLD + "Rewards:");
        for (String reward : questRewards)
            lore.add(Prefix.PREFIX.getPrefix() + ChatColor.WHITE + reward);
        lore.add("");
        lore.add(ChatColor.GREEN + "Given by " + ChatColor.GOLD + questGiver + ChatColor.GREEN + " in the " + ChatColor.YELLOW + questGiverArea + ChatColor.GREEN + "!");

        inactive.setItemMeta(inactiveItemMeta);
        inactive.setLore(lore);

        return inactive;
    }

    @Override
    public ItemStack questActive(String questName, String[] questDescription, String[] questRewards, String questGiver, String questGiverArea) {
        ItemStack active = new ItemStack(Material.BOOK);
        final ItemMeta activeItemMeta = active.getItemMeta();

        activeItemMeta.displayName(Component.text(ChatColor.GOLD.toString() + ChatColor.BOLD + "[Active] " + ChatColor.WHITE + ChatColor.BOLD + questName));

        List<String> lore = new ArrayList<>();
        lore.add("");
        for (String lore2 : questDescription)
            lore.add(lore2);
        lore.add("");
        lore.add(ChatColor.YELLOW.toString() + ChatColor.BOLD + "Rewards:");
        for (String reward : questRewards)
            lore.add(Prefix.PREFIX.getPrefix() + ChatColor.WHITE + reward);
        lore.add("");
        lore.add(ChatColor.GREEN + "Given by " + ChatColor.GOLD + questGiver + ChatColor.GREEN + " in the " + ChatColor.YELLOW + questGiverArea + ChatColor.GREEN + "!");

        active.setItemMeta(activeItemMeta);
        active.setLore(lore);

        return active;
    }

    @Override
    public ItemStack questComplete(String questName, String[] questDescription, String[] questRewards, String questGiver, String questGiverArea) {
        ItemStack complete = new ItemStack(Material.ENCHANTED_BOOK);
        final ItemMeta completeItemMeta = complete.getItemMeta();

        completeItemMeta.displayName(Component.text(ChatColor.GREEN.toString() + ChatColor.BOLD + "[Complete] " + ChatColor.WHITE + ChatColor.BOLD + questName));

        List<String> lore = new ArrayList<>();
        lore.add("");
        for (String lore2 : questDescription)
            lore.add(lore2);
        lore.add("");
        lore.add(ChatColor.YELLOW.toString() + ChatColor.BOLD + "Rewards:");
        for (String reward : questRewards)
            lore.add(Prefix.PREFIX.getPrefix() + ChatColor.WHITE + reward);
        lore.add("");
        lore.add(ChatColor.GREEN + "Given by " + ChatColor.GOLD + questGiver + ChatColor.GREEN + " in the " + ChatColor.YELLOW + questGiverArea + ChatColor.GREEN + "!");

        complete.setItemMeta(completeItemMeta);
        complete.setLore(lore);

        return complete;
    }

    @Override
    public ItemStack questUnclaimed(String questName, String[] questDescription, String[] questRewards, String questGiver, String questGiverArea) {
        ItemStack unclaimed = new ItemStack(Material.ENCHANTED_BOOK);
        final ItemMeta unclaimedItemMeta = unclaimed.getItemMeta();

        unclaimedItemMeta.displayName(Component.text(ChatColor.GOLD.toString() + ChatColor.BOLD + "[Unclaimed] " + ChatColor.WHITE + ChatColor.BOLD + questName));

        List<String> lore = new ArrayList<>();
        lore.add("");
        for (String lore2 : questDescription)
            lore.add(lore2);
        lore.add("");
        lore.add(ChatColor.YELLOW.toString() + ChatColor.BOLD + "Rewards:");
        for (String reward : questRewards)
            lore.add(Prefix.PREFIX.getPrefix() + ChatColor.WHITE + reward);
        lore.add("");
        lore.add(ChatColor.GREEN + "Given by " + ChatColor.GOLD + questGiver + ChatColor.GREEN + " in the " + ChatColor.YELLOW + questGiverArea + ChatColor.GREEN + "!");
        lore.add("");
        lore.add(Prefix.PREFIX.getPrefix() + ChatColor.YELLOW + "Click to Claim Rewards");

        unclaimed.setItemMeta(unclaimedItemMeta);
        unclaimed.setLore(lore);

        return unclaimed;
    }

}
