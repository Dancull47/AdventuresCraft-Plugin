package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.mainMenu.resourceCollector;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Dependency;
import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.PaginatedPane;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import io.lumine.mythic.lib.api.item.NBTItem;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.GUI.GUIHelper;
import monzter.adventurescraft.plugin.utilities.GUI.GUIHelperImplStatic;
import monzter.adventurescraft.plugin.utilities.beton.BetonPointsManager;
import monzter.adventurescraft.plugin.utilities.enums.Prefix;
import monzter.adventurescraft.plugin.utilities.general.FullInventory;
import monzter.adventurescraft.plugin.utilities.general.ItemAdder;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import monzter.adventurescraft.plugin.utilities.luckperms.PermissionLP;
import monzter.adventurescraft.plugin.utilities.text.NumberFormat;
import monzter.adventurescraft.plugin.utilities.text.ProgressBar;
import org.apache.commons.lang.WordUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResourceCollector extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final SoundManager soundManager;
    private final GUIHelper guiHelper;
    private final ProgressBar progressBar;
    private final BetonPointsManager betonPointsManager;
    private final NumberFormat numberFormat;
    private final PermissionLP permissionLP;
    private final ItemAdder itemAdder;
    private final FullInventory fullInventory;


    public ResourceCollector(AdventuresCraft plugin, SoundManager soundManager, GUIHelper guiHelper, ProgressBar progressBar, BetonPointsManager betonPointsManager, NumberFormat numberFormat, PermissionLP permissionLP, ItemAdder itemAdder, FullInventory fullInventory) {
        this.plugin = plugin;
        this.soundManager = soundManager;
        this.guiHelper = guiHelper;
        this.progressBar = progressBar;
        this.betonPointsManager = betonPointsManager;
        this.numberFormat = numberFormat;
        this.permissionLP = permissionLP;
        this.itemAdder = itemAdder;
        this.fullInventory = fullInventory;
    }

    @CommandAlias("resourceCollector|resources")
    public void resourceCategoryMenu(Player player) {

        ChestGui gui = new ChestGui(4, guiHelper.guiName("Resource Collector"));
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        OutlinePane background = new OutlinePane(0, 0, 9, 4, Pane.Priority.LOWEST);
        StaticPane display = new StaticPane(0, 0, 9, 4, Pane.Priority.LOW);
        OutlinePane outlinePane = new OutlinePane(2, 1, 7, 2, Pane.Priority.LOW);

        background.addItem(new GuiItem(guiHelper.background(Material.PINK_STAINED_GLASS_PANE)));
        background.setRepeat(true);

        for (DisplayItems item : DisplayItems.values())
            outlinePane.addItem(item.getGuiItem());

        display.addItem(new GuiItem(guiHelper.backButton(), e -> player.performCommand("main")), 4, 3);

        gui.addPane(background);
        gui.addPane(outlinePane);
        gui.addPane(display);
        gui.show(player);
    }

    enum DisplayItems {
        FARMING(new GuiItem(GUIHelperImplStatic.itemCreator(Material.DIAMOND_HOE, ChatColor.GOLD + "Farming Resources",
                new String[]{"", Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View"}, false),
                e -> Bukkit.getPlayer(e.getWhoClicked().getUniqueId()).performCommand("ResourceCollector Farming"))),
        FORAGING(new GuiItem(GUIHelperImplStatic.itemCreator(Material.DIAMOND_AXE, ChatColor.GOLD + "Foraging Resources",
                new String[]{"", Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View"}, false),
                e -> Bukkit.getPlayer(e.getWhoClicked().getUniqueId()).performCommand("ResourceCollector Foraging"))),
        MINING(new GuiItem(GUIHelperImplStatic.itemCreator(Material.DIAMOND_PICKAXE, ChatColor.GOLD + "Mining Resources",
                new String[]{"", Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View"}, false),
                e -> Bukkit.getPlayer(e.getWhoClicked().getUniqueId()).performCommand("ResourceCollector Mining"))),
        COMBAT(new GuiItem(GUIHelperImplStatic.itemCreator(Material.DIAMOND_SWORD, ChatColor.GOLD + "Combat Resources",
                new String[]{"", Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View"}, false),
                e -> Bukkit.getPlayer(e.getWhoClicked().getUniqueId()).performCommand("ResourceCollector Combat"))),
        ;

        public GuiItem guiItem;

        DisplayItems(GuiItem guiItem) {
            this.guiItem = guiItem;
        }

        public GuiItem getGuiItem() {
            return guiItem;
        }
    }

    List<String> categories = new ArrayList<>(Arrays.asList("COMBAT", "MINING", "FARMING", "FORAGING"));

    @CommandAlias("resourceCollector|resources")
    @CommandCompletion("Combat")
    public void resourceMenu(Player player, String category, @Default("0") int pageNumber) {
        if (!categories.contains(category.toUpperCase())) {
            resourceCategoryMenu(player);
            return;
        }
        String shopCommand = "resourceCollector " + category;
        int level = 1;
        if (player.hasPermission("COLLECTOR.LEVEL." + category.toUpperCase() + "." + "5"))
            level = 5;
        else if (player.hasPermission("COLLECTOR.LEVEL." + category.toUpperCase() + "." + "4"))
            level = 4;
        else if (player.hasPermission("COLLECTOR.LEVEL." + category.toUpperCase() + "." + "3"))
            level = 3;
        else if (player.hasPermission("COLLECTOR.LEVEL." + category.toUpperCase() + "." + "2"))
            level = 2;

        final List<Resources> guiContents = Resources.getResources(category);
        final ChestGui gui = new ChestGui(guiHelper.heightCalc(guiContents.size()), guiHelper.guiName(WordUtils.capitalizeFully(category + " Resources")));
        int height = guiHelper.heightCalc(guiContents.size());
        int length = 7;
        int displayX = guiHelper.displayXCalc(guiContents.size());
        int displayY = 1;

        gui.setOnGlobalClick(event -> event.setCancelled(true));

        PaginatedPane page = new PaginatedPane(0, 0, 9, height);
        OutlinePane background = new OutlinePane(0, 0, 9, height, Pane.Priority.LOWEST);
        OutlinePane display = new OutlinePane(displayX, displayY, length, height, Pane.Priority.LOW);
        StaticPane sell = new StaticPane(0, 0, 9, height, Pane.Priority.LOW);
        StaticPane forward = new StaticPane(8, 3, 1, 1, Pane.Priority.HIGH);
        StaticPane back = new StaticPane(0, 3, 1, 1, Pane.Priority.HIGH);

        page.addPane(0, background);
        page.addPane(0, display);
        page.addPane(0, sell);

        background.addItem(new GuiItem(guiHelper.background(Material.RED_STAINED_GLASS_PANE)));
        background.setRepeat(true);
        sell.addItem(new GuiItem(guiHelper.backButton(), e -> player.performCommand("ResourceCollector")), 4, height - 1);
        int i = 0;
        for (Resources item : guiContents) {
            if (pageNumber == 0 && i < 28)
                display.addItem(generateItem(player, item, shopCommand, 0, level));
            else if (pageNumber == 1 && i >= 28 && i < 56)
                display.addItem(generateItem(player, item, shopCommand, 1, level));
            else if (pageNumber == 2 && i >= 56)
                display.addItem(generateItem(player, item, shopCommand, 2, level));
            i++;
        }

//        Back button
        switch (pageNumber) {
            case 1:
                back.addItem(new GuiItem((guiHelper.backButton()), event -> player.performCommand(shopCommand + " 0")), 0, 0);
                break;
            case 2:
                back.addItem(new GuiItem((guiHelper.backButton()), event -> player.performCommand(shopCommand + " 1")), 0, 0);
                break;
        }
//        Forward button
        if (pageNumber == 0 && guiContents.size() > 27)
            forward.addItem(new GuiItem((guiHelper.nextPageButton()), event -> player.performCommand(shopCommand + " 1")), 0, 0);
        else if (pageNumber == 1 && guiContents.size() > 55)
            forward.addItem(new GuiItem((guiHelper.nextPageButton()), event -> player.performCommand(shopCommand + " 2")), 0, 0);

        gui.addPane(page);
        gui.addPane(back);
        gui.addPane(forward);
        gui.show(player);
    }

    public GuiItem generateItem(Player player, Resources resources, String shopCommand, int page, int level) {
        int amount;
        boolean enabled;
        String permission;
        String point;
        int max = 10_000;
        if (level == 5)
            max = 100_000;
        else if (level == 4)
            max = 75_000;
        else if (level == 3)
            max = 50_000;
        else if (level == 2)
            max = 25_000;

        ItemStack itemStack = new ItemStack(resources.getItemStack());
        final ItemMeta itemMeta = itemStack.getItemMeta();
        String displayName = itemMeta.getDisplayName();
        String item;
        if (displayName.isEmpty())
            displayName = ChatColor.WHITE + WordUtils.capitalizeFully(itemStack.getType().toString().replace('_', ' '));
        NBTItem nbtItem = NBTItem.get(itemStack);
        if (nbtItem.hasType())
            item = nbtItem.getString("MMOITEMS_ITEM_ID");
        else
            item = itemStack.getType().toString();
        point = "items." + item;
        permission = "COLLECTOR." + item;

        amount = betonPointsManager.getPoints(player, point);
        enabled = player.hasPermission(permission);

        if (enabled)
            itemMeta.setDisplayName(displayName + ChatColor.DARK_GRAY + " - " + ChatColor.GREEN + ChatColor.BOLD + "ON");
        else
            itemMeta.setDisplayName(displayName + ChatColor.DARK_GRAY + " - " + ChatColor.RED + ChatColor.BOLD + "OFF");

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "You have " + ChatColor.GOLD + numberFormat.numberFormat(amount) + ChatColor.GRAY + " stored!");
        lore.add(progressBar.getProgressBar(amount, max, 17, '-', ChatColor.RED, ChatColor.WHITE));
        lore.add("");
        if (amount >= 1)
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Left-Click to Withdraw 1");
        if (amount >= 64)
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Right-Click to Withdraw 64");
        if (amount >= 512)
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Shift-Right-Click to Withdraw 512");

        if (enabled)
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Shift-Left-Click to " + ChatColor.RED + "Disable Auto-Pickup");
        else
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Shift-Left-Click to " + ChatColor.GREEN + "Enable Auto-Pickup");

        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        int finalAmount = amount;
        return new GuiItem(itemStack, e -> {
            if (e.isShiftClick() && e.isLeftClick() && player.hasPermission(permission))
                permissionLP.takePermission(player, permission);
            else if (e.isShiftClick() && e.isLeftClick() && !player.hasPermission(permission))
                permissionLP.givePermission(player, permission);

            if (!e.isShiftClick() && e.isLeftClick() && finalAmount >= 1)
                withdraw(player, resources.getItemStack(), 1, point);
            if (!e.isShiftClick() && e.isRightClick() && finalAmount >= 64)
                withdraw(player, resources.getItemStack(), 64, point);
            if (e.isShiftClick() && e.isRightClick() && finalAmount >= 512)
                withdraw(player, resources.getItemStack(), 512, point);
            player.performCommand(shopCommand + " " + page);
        });
    }

    private void withdraw(Player player, ItemStack itemStack, int withdrawAmount, String point) {
        int playerAmount = betonPointsManager.getPoints(player, point);
        if (withdrawAmount == 1 && !fullInventory.emptySlots(player, 1))
            return;
        else if (withdrawAmount == 64 && !fullInventory.emptySlots(player, 1))
            return;
        else if (withdrawAmount == 512 && !fullInventory.emptySlots(player, 8))
            return;

        if (playerAmount >= withdrawAmount) {
            betonPointsManager.takePoint(player, point, withdrawAmount);
            itemAdder.itemAdder(player, itemStack.asQuantity(withdrawAmount));
            player.sendMessage(ChatColor.GREEN + "You withdrew " + ChatColor.GOLD + withdrawAmount + " " + WordUtils.capitalizeFully(itemStack.getType().toString().replace("_", " ")) + ChatColor.GREEN + " and have " + ChatColor.YELLOW + (betonPointsManager.getPoints(player, point)) + ChatColor.GREEN + " left!");
        }
    }


}

