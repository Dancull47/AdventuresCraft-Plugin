package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.mainMenu.resourceCollector;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Dependency;
import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.PaginatedPane;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import dev.dbassett.skullcreator.SkullCreator;
import io.lumine.mythic.lib.api.item.NBTItem;
import io.lumine.mythic.utils.Schedulers;
import me.clip.placeholderapi.PlaceholderAPI;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.Events.Pickup;
import monzter.adventurescraft.plugin.utilities.GUI.GUIHelper;
import monzter.adventurescraft.plugin.utilities.beton.BetonPointsManager;
import monzter.adventurescraft.plugin.utilities.enums.Prefix;
import monzter.adventurescraft.plugin.utilities.general.ConsoleCommand;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import monzter.adventurescraft.plugin.utilities.luckperms.PermissionLP;
import net.Indyuce.mmoitems.MMOItems;
import net.kyori.adventure.text.Component;
import org.apache.commons.lang.WordUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class Farming extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final SoundManager soundManager;
    private final GUIHelper guiHelper;
    private final ConsoleCommand consoleCommand;
    private final Pickup pickup;
    private final BetonPointsManager betonPointsManager;
    private final PermissionLP permissionLP;
    private final MMOItems mmoItems;


    public Farming(AdventuresCraft plugin, SoundManager soundManager, GUIHelper guiHelper, ConsoleCommand consoleCommand, Pickup pickup, BetonPointsManager betonPointsManager, PermissionLP permissionLP, MMOItems mmoItems) {
        this.plugin = plugin;
        this.soundManager = soundManager;
        this.guiHelper = guiHelper;
        this.consoleCommand = consoleCommand;
        this.pickup = pickup;
        this.betonPointsManager = betonPointsManager;
        this.permissionLP = permissionLP;
        this.mmoItems = mmoItems;
    }

    private final ItemStack backgroundItem = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
    private final ItemMeta backgroundItemMeta = backgroundItem.getItemMeta();
    private final ItemStack previousPageItem = new ItemStack(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODY1MmUyYjkzNmNhODAyNmJkMjg2NTFkN2M5ZjI4MTlkMmU5MjM2OTc3MzRkMThkZmRiMTM1NTBmOGZkYWQ1ZiJ9fX0="));
    private final ItemMeta previousPageItemMeta = previousPageItem.getItemMeta();
    private final ItemStack nextPageItem = new ItemStack(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmEzYjhmNjgxZGFhZDhiZjQzNmNhZThkYTNmZTgxMzFmNjJhMTYyYWI4MWFmNjM5YzNlMDY0NGFhNmFiYWMyZiJ9fX0="));
    private final ItemMeta nextPageItemMeta = nextPageItem.getItemMeta();

    @CommandAlias("Farming|Farmings")
    private void createMenu(Player player) {
        final ChestGui gui = new ChestGui(6, "Farming");
        backgroundItemMeta.displayName(Component.text(" "));
        previousPageItemMeta.displayName(Component.text(ChatColor.GREEN + "Previous Page"));
        nextPageItemMeta.displayName(Component.text(ChatColor.GREEN + "Next Page"));
        backgroundItem.setItemMeta(backgroundItemMeta);
        previousPageItem.setItemMeta(previousPageItemMeta);
        nextPageItem.setItemMeta(nextPageItemMeta);

        gui.setOnGlobalClick(event -> event.setCancelled(true));

        PaginatedPane page = new PaginatedPane(0, 0, 9, 6);
        OutlinePane background = new OutlinePane(0, 0, 9, 6, Pane.Priority.LOWEST);
        OutlinePane display = new OutlinePane(1, 1, 7, 4, Pane.Priority.LOW);
        OutlinePane display2 = new OutlinePane(1, 1, 7, 4, Pane.Priority.LOW);
        StaticPane forward = new StaticPane(8, 5, 1, 1, Pane.Priority.HIGH);
        StaticPane back = new StaticPane(0, 5, 1, 1, Pane.Priority.HIGH);
        StaticPane backButton = new StaticPane(4, 5, 1, 1, Pane.Priority.HIGHEST);

        page.addPane(0, background);
        page.addPane(0, display);
        page.addPane(0, backButton);
        page.addPane(1, background);
        page.addPane(1, display2);
        page.addPane(1, backButton);

        background.addItem(new GuiItem(backgroundItem));
        background.setRepeat(true);

        int i = 0;

        for (ItemStack item : pickup.MINING_MATERIALS) {
            if (i < 28) {
                display.addItem(itemCreator(player, item));
                i++;
            } else {
                display2.addItem(itemCreator(player, item));
            }
        }

        if (!display2.getItems().isEmpty()) {
            back.addItem(new GuiItem((previousPageItem), event -> {
                page.setPage(page.getPage() - 1);
                if (page.getPage() == 0) {
                    back.setVisible(false);
                }
                forward.setVisible(true);
                gui.update();
            }), 0, 0);
            back.setVisible(false);
            forward.addItem(new GuiItem((nextPageItem), event -> {
                page.setPage(page.getPage() + 1);
                if (page.getPage() == page.getPages() - 1) {
                    forward.setVisible(false);
                }
                back.setVisible(true);
                gui.update();
            }), 0, 0);
        }

        backButton.addItem(new GuiItem(guiHelper.backButton(), e -> player.performCommand("main")), 0, 0);

        gui.addPane(page);
        gui.addPane(back);
        gui.addPane(forward);
        gui.addPane(backButton);
        gui.show(player);
    }

    int amount;
    String permission;

    private GuiItem itemCreator(Player player, ItemStack itemStack) {
        final ItemStack forest = itemStack;
        final ItemMeta forestItemMeta = forest.getItemMeta();

        String mmoItemID = mmoItems.getID(NBTItem.get(itemStack));
        if (mmoItemID == null) {
            permission = itemStack.getType().toString() + ".COLLECT";
            forestItemMeta.displayName(Component.text(ChatColor.GREEN + WordUtils.capitalizeFully(itemStack.getType().toString().replace("_", " ")) + ChatColor.DARK_GRAY + " - "));
            if (player.hasPermission(permission))
                forestItemMeta.displayName(Component.text(ChatColor.GREEN + WordUtils.capitalizeFully(itemStack.getType().toString().replace("_", " ")) + ChatColor.DARK_GRAY + " - " + ChatColor.GREEN + "ON"));
            else
                forestItemMeta.displayName(Component.text(ChatColor.GREEN + WordUtils.capitalizeFully(itemStack.getType().toString().replace("_", " ")) + ChatColor.DARK_GRAY + " - " + ChatColor.RED + "OFF"));
        } else {
            permission = mmoItemID + ".COLLECT";
            forestItemMeta.displayName(Component.text(ChatColor.GREEN + itemStack.getItemMeta().getDisplayName() + ChatColor.DARK_GRAY + " - "));
            if (player.hasPermission(permission))
                forestItemMeta.displayName(Component.text(ChatColor.GREEN + itemStack.getItemMeta().getDisplayName() + ChatColor.DARK_GRAY + " - " + ChatColor.GREEN + "ON"));
            else
                forestItemMeta.displayName(Component.text(ChatColor.GREEN + itemStack.getItemMeta().getDisplayName() + ChatColor.DARK_GRAY + " - " + ChatColor.RED + "OFF"));
        }
        if (mmoItemID == null)
            amount = Integer.valueOf(parsePlaceholder(player, "betonquest_items:point." + itemStack.getType().toString() + ".amount"));
        else
            amount = Integer.valueOf(parsePlaceholder(player, "betonquest_items:point." + mmoItemID + ".amount"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "You have " + ChatColor.GOLD + amount + ChatColor.GRAY + " stored!");
        lore.add("");
        if (amount >= 1)
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Left-Click to Withdraw 1");
        if (amount >= 64)
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Right-Click to Withdraw 64");
        if (amount >= 512)
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Shift-Right-Click to Withdraw 512");
        if (player.hasPermission(permission))
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Shift-Left-Click to " + ChatColor.RED + "Disable Auto-Pickup");
        else
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Shift-Left-Click to " + ChatColor.GREEN + "Enable Auto-Pickup");

        forest.setItemMeta(forestItemMeta);
        forest.setLore(lore);

        return new GuiItem(forest, e -> {
            if (mmoItemID == null)
                amount = Integer.valueOf(parsePlaceholder(player, "betonquest_items:point." + itemStack.getType().toString() + ".amount"));
            else
                amount = Integer.valueOf(parsePlaceholder(player, "betonquest_items:point." + mmoItemID + ".amount"));

            if (e.isShiftClick() && e.isLeftClick() && player.hasPermission(permission))
                permissionLP.takePermission(player, permission);
            else if (e.isShiftClick() && e.isLeftClick() && !player.hasPermission(permission))
                permissionLP.givePermission(player, permission);

            plugin.getLogger().info(e.getClick().toString());
            plugin.getLogger().info(String.valueOf(amount));

            if (e.isShiftClick() && e.isRightClick() && amount >= 512)
                withdraw(player, itemStack, 512);
            if (e.isLeftClick() && amount >= 1) {
                withdraw(player, itemStack, 1);
            }
            if (e.isRightClick() && amount >= 64)
                withdraw(player, itemStack, 64);
            player.performCommand("Farmings");

        });

    }

    private String parsePlaceholder(Player player, String string) {
        return PlaceholderAPI.setPlaceholders(player, "%" + string + "%");
    }

    int playerAmount;

    private void withdraw(Player player, ItemStack itemStack, int withdrawAmount) {
        String mmoItemID = mmoItems.getID(NBTItem.get(itemStack));
        if (mmoItemID == null)
            playerAmount = Integer.valueOf(parsePlaceholder(player, "betonquest_items:point." + itemStack.getType().toString() + ".amount"));
        else
            playerAmount = Integer.valueOf(parsePlaceholder(player, "betonquest_items:point." + mmoItemID + ".amount"));
        plugin.getLogger().info(String.valueOf(playerAmount));

        if (playerAmount >= withdrawAmount) {
            betonPointsManager.takePoint(player, "items." + itemStack.getType().toString(), withdrawAmount);

            Schedulers.sync().runLater(new BukkitRunnable() {
                @Override
                public void run() {
                    if (mmoItemID == null)
                        playerAmount = Integer.valueOf(parsePlaceholder(player, "betonquest_items:point." + itemStack.getType().toString() + ".amount"));
                    else
                        playerAmount = Integer.valueOf(parsePlaceholder(player, "betonquest_items:point." + mmoItemID + ".amount"));

                    if (mmoItemID == null)
                        player.sendMessage(ChatColor.GREEN + "You withdrew " + ChatColor.GOLD + withdrawAmount + " " + WordUtils.capitalizeFully(itemStack.getType().toString().replace("_", " ")) + ChatColor.GREEN + " and have " + ChatColor.YELLOW + playerAmount + ChatColor.GREEN + " left!");
                    else
                        playerAmount = Integer.valueOf(parsePlaceholder(player, "betonquest_items:point." + mmoItemID + ".amount"));
                    player.sendMessage(ChatColor.GREEN + "You withdrew " + ChatColor.GOLD + withdrawAmount + " " + itemStack.getItemMeta().getDisplayName() + ChatColor.GREEN + " and have " + ChatColor.YELLOW + playerAmount + ChatColor.GREEN + " left!");
                }
            }, 1);
        }


    }
}

