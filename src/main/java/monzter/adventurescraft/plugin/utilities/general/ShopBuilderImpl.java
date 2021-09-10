package monzter.adventurescraft.plugin.utilities.general;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.PaginatedPane;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.shops.npcs.ItemList;
import monzter.adventurescraft.plugin.utilities.GUI.GUIHelper;
import monzter.adventurescraft.plugin.utilities.enums.Prefix;
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

public class ShopBuilderImpl implements ShopBuilder {
    private final GUIHelper guiHelper;
    private final Economy economy;
    private final PurchaseUtils purchaseUtils;
    private final MMOItems mmoItems;
    private final NumberFormat numberFormat;

    public ShopBuilderImpl(GUIHelper guiHelper, Economy economy, PurchaseUtils purchaseUtils, MMOItems mmoItems, NumberFormat numberFormat) {
        this.guiHelper = guiHelper;
        this.economy = economy;
        this.purchaseUtils = purchaseUtils;
        this.mmoItems = mmoItems;
        this.numberFormat = numberFormat;
    }


    public void menuBase(ChestGui gui, List<ItemList> guiContents, Player player, String shop, int height,
                         Material backgroundColor) {
        menuBase(gui, guiContents, player, shop, height, 7, backgroundColor, 1, 1);
    }

    public void menuBase(ChestGui gui, List<ItemList> guiContents, Player player, String shop, int height,
                         Material backgroundColor, int displayX) {
        menuBase(gui, guiContents, player, shop, height, 7, backgroundColor, displayX, 1);
    }

    public void menuBase(ChestGui gui, List<ItemList> guiContents, Player player, String shop, int height,
                         Material backgroundColor, int displayX, int displayY) {
        menuBase(gui, guiContents, player, shop, height, 7, backgroundColor, displayX, displayY);
    }

    public void menuBase(ChestGui gui, List<ItemList> guiContents, Player player, String shop, int height, int width,
                         Material backgroundColor) {
        menuBase(gui, guiContents, player, shop, height, width, backgroundColor, 1, 1);
    }

    public void menuBase(ChestGui gui, List<ItemList> guiContents, Player player, String shop, int height, int length,
                         Material backgroundColor, int displayX) {
        menuBase(gui, guiContents, player, shop, height, length, backgroundColor, displayX, 1);
    }

    public void menuBase(ChestGui gui, List<ItemList> guiContents, Player player, String shop, int height, int length, Material backgroundColor, int displayX, int displayY) {
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        PaginatedPane page = new PaginatedPane(0, 0, 9, height);
        OutlinePane background = new OutlinePane(0, 0, 9, height, Pane.Priority.LOWEST);
        OutlinePane display = new OutlinePane(displayX, displayY, length, height, Pane.Priority.LOW);
        StaticPane sell = new StaticPane(0, 0, 9, height, Pane.Priority.LOW);

        page.addPane(0, background);
        page.addPane(0, display);
        page.addPane(0, sell);

        background.addItem(new GuiItem(guiHelper.background(backgroundColor)));
        background.setRepeat(true);

        for (ItemList item : guiContents)
            display.addItem(generateItem(player, item, shop, backgroundColor));

        sell.addItem(new GuiItem(guiHelper.itemCreator(Material.CAULDRON, ChatColor.GREEN + "Sell", new String[]{"", Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Sell"}), e -> player.performCommand("sell")), 4, height - 1);

        gui.addPane(page);
    }

    public void wanderingTraderBase(ChestGui gui, ItemList guiContents, Player player, int shopNumber) {
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        PaginatedPane page = new PaginatedPane(0, 0, 9, 3);
        OutlinePane background = new OutlinePane(0, 0, 9, 3, Pane.Priority.LOWEST);
        OutlinePane display = new OutlinePane(4, 1, 1, 3, Pane.Priority.LOW);
        StaticPane sell = new StaticPane(0, 0, 9, 3, Pane.Priority.LOW);

        page.addPane(0, background);
        page.addPane(0, display);
        page.addPane(0, sell);

        background.addItem(new GuiItem(guiHelper.background(Material.GREEN_STAINED_GLASS_PANE)));
        background.setRepeat(true);

        display.addItem(generateItem(player, guiContents, "WanderingTraderShop " + shopNumber, Material.GREEN_STAINED_GLASS_PANE));

        gui.addPane(page);
    }

    public GuiItem generateItem(Player player, ItemList itemList, String shop, Material backgroundColor) {
        if (itemList.getMaxPurchaseAmount() == 0 && itemList.getExpPrice() == 0 && itemList.getCoinPrice() == 0 && itemList.getItemStack() == null && itemList.getType() == null && itemList.getID() == null)
            return new GuiItem(guiHelper.background(backgroundColor));

        ItemStack itemStack;

        if (itemList.getType() == null)
            itemStack = new ItemStack(itemList.getItemStack());
        else
            itemStack = mmoItems.getItem(itemList.getType(), itemList.getID());

        final ItemMeta itemMeta = itemStack.getItemMeta();

        if (itemStack != null) {
            List<Component> lore = itemStack.lore();
            if (lore == null)
                lore = new ArrayList<>();
            lore.add(Component.empty());

            lore.add(Component.text(ChatColor.WHITE + "Price:"));
            if (itemList.getCoinPrice() > 0)
                lore.add(Component.text(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "- " + ChatColor.YELLOW + "⛂ " + numberFormat.numberFormat(itemList.getCoinPrice())));
            if (itemList.getExpPrice() > 0)
                lore.add(Component.text(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "- " + ChatColor.AQUA + "Ξ " + numberFormat.numberFormat(itemList.getExpPrice())));
            if (itemList.getItemPrice() != null)
                for (String itemPrice : itemList.getItemPrice()) {
                    String[] itemPriceSplit = itemPrice.split(";");
                    lore.add(Component.text(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "- " + ChatColor.GOLD + itemPriceSplit[2] + "x " + mmoItems.getItem(itemPriceSplit[0], itemPriceSplit[1]).getItemMeta().getDisplayName()));
                }

            if (itemPurchaseLoreCheck(player, itemList, 1) && itemList.getMaxPurchaseAmount() >= 1) {
                lore.add(Component.text(""));
                lore.add(Component.text(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Left-Click to Purchase " + ChatColor.GOLD + "1x"));
            }
            if (itemPurchaseLoreCheck(player, itemList, 16) && itemList.getMaxPurchaseAmount() >= 16)
                lore.add(Component.text(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Right-Click to Purchase " + ChatColor.GOLD + "16x " + ChatColor.YELLOW + "(⛂ " + numberFormat.numberFormat(itemList.getCoinPrice() * 16) + ")"));
            if (itemPurchaseLoreCheck(player, itemList, 32) && itemList.getMaxPurchaseAmount() >= 32)
                lore.add(Component.text(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Shift-Left-Click to Purchase " + ChatColor.GOLD + "32x " + ChatColor.YELLOW + "(⛂ " + numberFormat.numberFormat(itemList.getCoinPrice() * 32) + ")"));
            if (itemPurchaseLoreCheck(player, itemList, 64) && itemList.getMaxPurchaseAmount() >= 64)
                lore.add(Component.text(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Shift-Right-Click to Purchase " + ChatColor.GOLD + "64x " + ChatColor.YELLOW + "(⛂ " + numberFormat.numberFormat(itemList.getCoinPrice() * 64) + ")"));

            itemMeta.lore(lore);
            itemStack.setItemMeta(itemMeta);

            return new GuiItem(itemStack, e -> {
                if (itemList.getItemPrice() != null) {
                    if (e.isLeftClick() && !e.isShiftClick() && purchaseUtils.hasItem(player, itemList.getItemPrice(), 1))
                        purchaseUtils.purchase(player, itemList, 1);

                    if (e.isRightClick() && !e.isShiftClick() && economy.getBalance(player) >= itemList.getCoinPrice() * 16 && itemList.getMaxPurchaseAmount() >= 16
                            && purchaseUtils.hasItem(player, itemList.getItemPrice(), 16))
                        purchaseUtils.purchase(player, itemList, 16);

                    if (e.isLeftClick() && e.isShiftClick() && economy.getBalance(player) >= itemList.getCoinPrice() * 32 && itemList.getMaxPurchaseAmount() >= 32
                            && purchaseUtils.hasItem(player, itemList.getItemPrice(), 32))
                        purchaseUtils.purchase(player, itemList, 32);

                    if (e.isRightClick() && e.isShiftClick() && economy.getBalance(player) >= itemList.getCoinPrice() * 64 && itemList.getMaxPurchaseAmount() >= 64
                            && purchaseUtils.hasItem(player, itemList.getItemPrice(), 64))
                        purchaseUtils.purchase(player, itemList, 64);
                } else {
                    if (e.isLeftClick() && !e.isShiftClick())
                        purchaseUtils.purchase(player, itemList, 1);
                    if (e.isRightClick() && !e.isShiftClick() && economy.getBalance(player) >= itemList.getCoinPrice() * 16 && itemList.getMaxPurchaseAmount() >= 16)
                        purchaseUtils.purchase(player, itemList, 16);
                    if (e.isLeftClick() && e.isShiftClick() && economy.getBalance(player) >= itemList.getCoinPrice() * 32 && itemList.getMaxPurchaseAmount() >= 32)
                        purchaseUtils.purchase(player, itemList, 32);
                    if (e.isRightClick() && e.isShiftClick() && economy.getBalance(player) >= itemList.getCoinPrice() * 64 && itemList.getMaxPurchaseAmount() >= 64)
                        purchaseUtils.purchase(player, itemList, 64);
                }
                player.performCommand(shop + "Shop");
            });
        }
        return null;
    }

    //    public List<Component> itemPurchaseLore(Player player, ItemList itemList, int amount) {
//        List<Component> lore = new ArrayList<>();
//        if (economy.getBalance(player) >= (itemList.getCoinPrice()*amount))
//        if (itemList.getItemPrice() != null) {
//            if (purchaseUtils.hasItem(player, itemList.getItemPrice(), 1).size() >= itemList.getItemPrice().length) {
//                if (economy.getBalance(player) >= itemList.getCoinPrice() && player.getLevel() >= itemList.getExpPrice()) {
//                    lore.add(Component.text(""));
//                    lore.add(Component.text(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Left-Click to Purchase " + ChatColor.GOLD + "1x"));
//                    if (purchaseUtils.hasItem(player, itemList.getItemPrice(), 16).size() >= itemList.getItemPrice().length)
//                        if (economy.getBalance(player) >= itemList.getCoinPrice() * 16 && player.getLevel() >= itemList.getExpPrice() * 16 && itemList.getMaxPurchaseAmount() >= 16)
//                            lore.add(Component.text(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Right-Click to Purchase " + ChatColor.GOLD + "16x " + ChatColor.YELLOW + "(⛂ " + numberFormat.numberFormat(itemList.getCoinPrice() * 16) + ")"));
//                    if (purchaseUtils.hasItem(player, itemList.getItemPrice(), 32).size() >= itemList.getItemPrice().length)
//                        if (economy.getBalance(player) >= itemList.getCoinPrice() * 32 && player.getLevel() >= itemList.getExpPrice() * 32 && itemList.getMaxPurchaseAmount() >= 32)
//                            lore.add(Component.text(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Shift-Left-Click to Purchase " + ChatColor.GOLD + "32x " + ChatColor.YELLOW + "(⛂ " + numberFormat.numberFormat(itemList.getCoinPrice() * 32) + ")"));
//                    if (purchaseUtils.hasItem(player, itemList.getItemPrice(), 64).size() >= itemList.getItemPrice().length) {
//                        if (economy.getBalance(player) >= itemList.getCoinPrice() * 64 && player.getLevel() >= itemList.getExpPrice() * 64 && itemList.getMaxPurchaseAmount() >= 64)
//                            lore.add(Component.text(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Shift-Right-Click to Purchase " + ChatColor.GOLD + "64x " + ChatColor.YELLOW + "(⛂ " + numberFormat.numberFormat(itemList.getCoinPrice() * 64) + ")"));
//                    }
//                }
//            }
//        } else if (economy.getBalance(player) >= itemList.getCoinPrice() && player.getLevel() >= itemList.getExpPrice()) {
//            lore.add(Component.text(""));
//            lore.add(Component.text(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Left-Click to Purchase " + ChatColor.GOLD + "1x"));
//            if (economy.getBalance(player) >= itemList.getCoinPrice() * 16 && player.getLevel() >= itemList.getExpPrice() * 16 && itemList.getMaxPurchaseAmount() >= 16)
//                lore.add(Component.text(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Right-Click to Purchase " + ChatColor.GOLD + "16x " + ChatColor.YELLOW + "(⛂ " + numberFormat.numberFormat(itemList.getCoinPrice() * 16) + ")"));
//            if (economy.getBalance(player) >= itemList.getCoinPrice() * 32 && player.getLevel() >= itemList.getExpPrice() * 32 && itemList.getMaxPurchaseAmount() >= 32)
//                lore.add(Component.text(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Shift-Left-Click to Purchase " + ChatColor.GOLD + "32x " + ChatColor.YELLOW + "(⛂ " + numberFormat.numberFormat(itemList.getCoinPrice() * 32) + ")"));
//            if (economy.getBalance(player) >= itemList.getCoinPrice() * 64 && player.getLevel() >= itemList.getExpPrice() * 64 && itemList.getMaxPurchaseAmount() >= 64)
//                lore.add(Component.text(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Shift-Right-Click to Purchase " + ChatColor.GOLD + "64x " + ChatColor.YELLOW + "(⛂ " + numberFormat.numberFormat(itemList.getCoinPrice() * 64) + ")"));
//        }
//        return lore;
//    }
    public List<Component> itemPurchaseLore(Player player, ItemList itemList) {
        List<Component> lore = new ArrayList<>();
        lore.add(Component.text(""));
        if (itemPurchaseLoreCheck(player, itemList, 1) && itemList.getMaxPurchaseAmount() >= 1)
            lore.add(Component.text(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Left-Click to Purchase " + ChatColor.GOLD + "1x"));
        if (itemPurchaseLoreCheck(player, itemList, 16) && itemList.getMaxPurchaseAmount() >= 16)
            lore.add(Component.text(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Right-Click to Purchase " + ChatColor.GOLD + "16x " + ChatColor.YELLOW + "(⛂ " + numberFormat.numberFormat(itemList.getCoinPrice() * 16) + ")"));
        if (itemPurchaseLoreCheck(player, itemList, 32) && itemList.getMaxPurchaseAmount() >= 32)
            lore.add(Component.text(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Shift-Left-Click to Purchase " + ChatColor.GOLD + "32x " + ChatColor.YELLOW + "(⛂ " + numberFormat.numberFormat(itemList.getCoinPrice() * 32) + ")"));
        if (itemPurchaseLoreCheck(player, itemList, 64) && itemList.getMaxPurchaseAmount() >= 64)
            lore.add(Component.text(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Shift-Right-Click to Purchase " + ChatColor.GOLD + "64x " + ChatColor.YELLOW + "(⛂ " + numberFormat.numberFormat(itemList.getCoinPrice() * 64) + ")"));
        return lore;
    }

    public boolean itemPurchaseLoreCheck(Player player, ItemList itemList, int amount) {
        if (economy.getBalance(player) < (itemList.getCoinPrice() * amount))
            return false;
        else if (player.getLevel() < (itemList.getExpPrice() * amount))
            return false;
        else if (itemList.getItemPrice() != null && !purchaseUtils.hasItem(player, itemList.getItemPrice(), amount))
            return false;
        return true;
    }
//        if (economy.getBalance(player) >= itemList.getCoinPrice() && player.getLevel() >= itemList.getExpPrice()) {
//            lore.add(Component.text(""));
//            lore.add(Component.text(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Left-Click to Purchase " + ChatColor.GOLD + "1x"));
//            if (economy.getBalance(player) >= itemList.getCoinPrice() * 16 && player.getLevel() >= itemList.getExpPrice() * 16 && itemList.getMaxPurchaseAmount() >= 16)
//                lore.add(Component.text(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Right-Click to Purchase " + ChatColor.GOLD + "16x " + ChatColor.YELLOW + "(⛂ " + numberFormat.numberFormat(itemList.getCoinPrice() * 16) + ")"));
//            if (economy.getBalance(player) >= itemList.getCoinPrice() * 32 && player.getLevel() >= itemList.getExpPrice() * 32 && itemList.getMaxPurchaseAmount() >= 32)
//                lore.add(Component.text(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Shift-Left-Click to Purchase " + ChatColor.GOLD + "32x " + ChatColor.YELLOW + "(⛂ " + numberFormat.numberFormat(itemList.getCoinPrice() * 32) + ")"));
//            if (economy.getBalance(player) >= itemList.getCoinPrice() * 64 && player.getLevel() >= itemList.getExpPrice() * 64 && itemList.getMaxPurchaseAmount() >= 64)
//                lore.add(Component.text(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Shift-Right-Click to Purchase " + ChatColor.GOLD + "64x " + ChatColor.YELLOW + "(⛂ " + numberFormat.numberFormat(itemList.getCoinPrice() * 64) + ")"));
//        }
//        return lore;
//    }

    /*
     *
     *
     *       Tired creating one method for all...
     *
     * */

//    public void professionVendors(Player player, String guiName, Material backgroundColor, List<GuiItem> guiItemList) {
//
//        ChestGui gui = new ChestGui(3, guiHelper.guiName("Baker"));
//        gui.setOnGlobalClick(event -> event.setCancelled(true));
//
//        OutlinePane background = new OutlinePane(0, 0, 9, 3, Pane.Priority.LOWEST);
//        StaticPane display = new StaticPane(0, 0, 9, 3, Pane.Priority.LOW);
//
//
//        background.addItem(new GuiItem(guiHelper.background(Material.YELLOW_STAINED_GLASS_PANE)));
//        background.setRepeat(true);
//
//        for (GuiItem guiItem : guiItemList) {
//            display.addItem(guiItem);
//        }
//            display.addItem(new GuiItem(guiHelper.itemCreator(Material.MUSHROOM_STEW,
//                    ChatColor.GREEN + "Stew Shop", new String[]{"", Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View"}),
//                    e -> consoleCommand.consoleCommand("mi stations open stews " + player.getName())), 2, 1);
//        display.addItem(new GuiItem(guiHelper.itemCreator(Material.BREWING_STAND,
//                ChatColor.GREEN + "Material Shop", new String[]{"", Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View"}),
//                e -> consoleCommand.consoleCommand("mi stations open enchanted-materials " + player.getName())), 4, 1);
//        display.addItem(new GuiItem(guiHelper.itemCreator(Material.TOTEM_OF_UNDYING,
//                ChatColor.GREEN + "Totem Shop", new String[]{"", Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View"}),
//                e -> consoleCommand.consoleCommand("mi stations open totems " + player.getName())), 6, 1);
//
//        gui.addPane(background);
//        gui.addPane(display);
//        gui.show(player);
//    }
}
