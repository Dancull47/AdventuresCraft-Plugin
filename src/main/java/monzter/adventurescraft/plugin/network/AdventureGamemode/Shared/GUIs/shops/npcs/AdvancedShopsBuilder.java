package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.shops.npcs;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Dependency;
import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.PaginatedPane;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.GUI.GUIHelper;
import monzter.adventurescraft.plugin.utilities.enums.Prefix;
import monzter.adventurescraft.plugin.utilities.general.ConsoleCommand;
import monzter.adventurescraft.plugin.utilities.general.FullInventory;
import monzter.adventurescraft.plugin.utilities.general.PurchaseUtils;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import monzter.adventurescraft.plugin.utilities.luckperms.PermissionLP;
import monzter.adventurescraft.plugin.utilities.mmoitems.MMOItemsGive;
import monzter.adventurescraft.plugin.utilities.text.NumberFormat;
import monzter.adventurescraft.plugin.utilities.vault.Economy;
import net.Indyuce.mmoitems.MMOItems;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class AdvancedShopsBuilder extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final SoundManager soundManager;
    private final GUIHelper guiHelper;
    private final ConsoleCommand consoleCommand;
    private final Economy economy;
    private final FullInventory fullInventory;
    private final MMOItemsGive mmoItemsGive;
    private final NumberFormat numberFormat;
    private final PurchaseUtils purchaseUtils;
    private final MMOItems mmoItems;
    private final PermissionLP permissionLP;


    public AdvancedShopsBuilder(AdventuresCraft plugin, SoundManager soundManager, GUIHelper guiHelper, ConsoleCommand consoleCommand, Economy economy, FullInventory fullInventory, MMOItemsGive mmoItemsGive, NumberFormat numberFormat, PurchaseUtils purchaseUtils, MMOItems mmoItems, PermissionLP permissionLP) {
        this.plugin = plugin;
        this.soundManager = soundManager;
        this.guiHelper = guiHelper;
        this.consoleCommand = consoleCommand;
        this.economy = economy;
        this.fullInventory = fullInventory;
        this.mmoItemsGive = mmoItemsGive;
        this.numberFormat = numberFormat;
        this.purchaseUtils = purchaseUtils;
        this.mmoItems = mmoItems;
        this.permissionLP = permissionLP;
    }

    @CommandAlias("EnchantingShop")
    @CommandPermission("shops")
    private void enchantingShop(Player player) {
        int height = 5;
        final ChestGui gui = new ChestGui(height, "Enchanting Shop");
        final List<AdvancedItemList> guiContents = AdvancedItemList.getShop(Shops.ENCHANTER);
        menuBase(gui, guiContents, player, "Enchanting", height, Material.PURPLE_STAINED_GLASS_PANE);
        gui.show(player);
    }

    private void menuBase(ChestGui gui, List<AdvancedItemList> guiContents, Player player, String shop, int height,
                          Material backgroundColor) {
        menuBase(gui, guiContents, player, shop, height, backgroundColor, 1, 1);
    }

    private void menuBase(ChestGui gui, List<AdvancedItemList> guiContents, Player player, String shop, int height,
                          Material backgroundColor, int displayX) {
        menuBase(gui, guiContents, player, shop, height, backgroundColor, displayX, 1);
    }

    private void menuBase(ChestGui gui, List<AdvancedItemList> guiContents, Player player, String shop, int height, Material backgroundColor, int displayX, int displayY) {
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        PaginatedPane page = new PaginatedPane(0, 0, 9, height);
        OutlinePane background = new OutlinePane(0, 0, 9, height, Pane.Priority.LOWEST);
        OutlinePane display = new OutlinePane(displayX, displayY, 7, height, Pane.Priority.LOW);
        StaticPane sell = new StaticPane(0, 0, 9, height, Pane.Priority.LOW);

        page.addPane(0, background);
        page.addPane(0, display);
        page.addPane(0, sell);

        background.addItem(new GuiItem(guiHelper.background(backgroundColor)));
        background.setRepeat(true);

        for (AdvancedItemList item : guiContents)
            display.addItem(generateItem(player, item, shop));

        sell.addItem(new GuiItem(guiHelper.itemCreator(Material.CAULDRON, ChatColor.GREEN + "Sell", new String[]{"", Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Sell"}), e -> player.performCommand("sell")), 4, height - 1);

        gui.addPane(page);
    }

    public GuiItem generateItem(Player player, AdvancedItemList itemList, String shop) {
        ItemStack itemStack;
        ItemStack cleanItem;

        if (itemList.getType() == null) {
            itemStack = new ItemStack(itemList.getItemStack());
            cleanItem = new ItemStack(itemList.getItemStack());
        } else {
            itemStack = mmoItems.getItem(itemList.getType(), itemList.getID());
            cleanItem = mmoItems.getItem(itemList.getType(), itemList.getID());
        }

        final ItemMeta itemMeta = itemStack.getItemMeta();

        if (itemStack != null) {
            List<Component> lore = itemStack.lore();
            if (lore == null) {
                lore = new ArrayList<>();
            }
            lore.add(Component.empty());
            lore.add(Component.text(ChatColor.WHITE + "Price: " + ChatColor.YELLOW + "⛂ " + numberFormat.numberFormat(itemList.getCoinPrice())));
            if (economy.getBalance(player) >= itemList.getCoinPrice()) {
                lore.add(Component.text(""));
                lore.add(Component.text(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Left-Click to Purchase " + ChatColor.GOLD + "1x"));
                if (economy.getBalance(player) >= itemList.getCoinPrice() * 16 && itemList.getMaxPurchaseAmount() >= 16)
                    lore.add(Component.text(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Right-Click to Purchase " + ChatColor.GOLD + "16x " + ChatColor.YELLOW + "(⛂ " + numberFormat.numberFormat(itemList.getCoinPrice() * 16) + ")"));
                if (economy.getBalance(player) >= itemList.getCoinPrice() * 32 && itemList.getMaxPurchaseAmount() >= 32)
                    lore.add(Component.text(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Shift-Left-Click to Purchase " + ChatColor.GOLD + "32x " + ChatColor.YELLOW + "(⛂ " + numberFormat.numberFormat(itemList.getCoinPrice() * 32) + ")"));
                if (economy.getBalance(player) >= itemList.getCoinPrice() * 64 && itemList.getMaxPurchaseAmount() >= 64)
                    lore.add(Component.text(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Shift-Right-Click to Purchase " + ChatColor.GOLD + "64x " + ChatColor.YELLOW + "(⛂ " + numberFormat.numberFormat(itemList.getCoinPrice() * 64) + ")"));
            }

            itemMeta.lore(lore);
            itemStack.setItemMeta(itemMeta);

            ItemStack finalStoredItem = cleanItem;
            return new GuiItem(itemStack, e -> {
                if (e.isLeftClick() && !e.isShiftClick())
                    purchaseUtils.purchase(player, finalStoredItem, 1, itemList.getCoinPrice());
                if (e.isRightClick() && !e.isShiftClick() && itemList.getMaxPurchaseAmount() >= 16)
                    purchaseUtils.purchase(player, finalStoredItem, 16, itemList.getCoinPrice());
                if (e.isLeftClick() && e.isShiftClick() && itemList.getMaxPurchaseAmount() >= 32)
                    purchaseUtils.purchase(player, finalStoredItem, 32, itemList.getCoinPrice());
                if (e.isRightClick() && e.isShiftClick() && itemList.getMaxPurchaseAmount() >= 64)
                    purchaseUtils.purchase(player, finalStoredItem, 64, itemList.getCoinPrice());
                player.performCommand(shop + "Shop");
            });
        }
        return null;
    }

}


