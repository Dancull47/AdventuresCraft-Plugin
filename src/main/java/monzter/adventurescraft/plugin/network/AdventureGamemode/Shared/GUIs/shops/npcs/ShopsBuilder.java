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

public class ShopsBuilder extends BaseCommand {

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


    public ShopsBuilder(AdventuresCraft plugin, SoundManager soundManager, GUIHelper guiHelper, ConsoleCommand consoleCommand, Economy economy, FullInventory fullInventory, MMOItemsGive mmoItemsGive, NumberFormat numberFormat, PurchaseUtils purchaseUtils, MMOItems mmoItems, PermissionLP permissionLP) {
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

    @CommandAlias("MiningShop")
    @CommandPermission("SHOPS")
    private void miningShop(Player player) {
        int height = 5;
        final ChestGui gui = new ChestGui(height, guiHelper.guiName("Mining Shop"));
        final List<ItemList> guiContents = ItemList.getShop(Shops.MINING);
        menuBase(gui, guiContents, player, "Mining", height, Material.BLACK_STAINED_GLASS_PANE);
        gui.show(player);
    }

    @CommandAlias("FarmShop")
    @CommandPermission("SHOPS")
    private void farmShop(Player player) {
        int height = 5;
        final ChestGui gui = new ChestGui(height, guiHelper.guiName("Farm Shop"));
        final List<ItemList> guiContents = ItemList.getShop(Shops.FARMER);
        menuBase(gui, guiContents, player, "Farm", height, Material.LIME_STAINED_GLASS_PANE);
        gui.show(player);
    }

    @CommandAlias("ForestShop|LumberjackShop")
    @CommandPermission("SHOPS")
    private void lumberjackShop(Player player) {
        int height = 6;
        final ChestGui gui = new ChestGui(height, guiHelper.guiName("Lumberjack Shop"));
        final List<ItemList> guiContents = ItemList.getShop(Shops.LUMBERJACK);
        menuBase(gui, guiContents, player, "Lumberjack", height, Material.GREEN_STAINED_GLASS_PANE);
        gui.show(player);
    }

    @CommandAlias("MercenaryShop")
    @CommandPermission("SHOPS")
    private void mercenaryShop(Player player) {
        int height = 5;
        final ChestGui gui = new ChestGui(height, guiHelper.guiName("Mercenary Shop"));
        final List<ItemList> guiContents = ItemList.getShop(Shops.MERCENARY);
        menuBase(gui, guiContents, player, "Mercenary", height, Material.RED_STAINED_GLASS_PANE);
        gui.show(player);
    }

    @CommandAlias("EstateShop|Farm2Shop")
    @CommandPermission("SHOPS")
    private void estateShop(Player player) {
        int height = 5;
        final ChestGui gui = new ChestGui(height, guiHelper.guiName("Estate Shop"));
        final List<ItemList> guiContents = ItemList.getShop(Shops.ESTATE);
        menuBase(gui, guiContents, player, "Estate", height, Material.GREEN_STAINED_GLASS_PANE);
        gui.show(player);
    }

    @CommandAlias("LandscapeShop|LandscaperShop")
    @CommandPermission("SHOPS")
    private void landscapeShop(Player player) {
        int height = 6;
        final ChestGui gui = new ChestGui(height, guiHelper.guiName("Landscaper Shop"));
        final List<ItemList> guiContents = ItemList.getShop(Shops.LANDSCAPER);
        menuBase(gui, guiContents, player, "Landscaper", height, Material.LIGHT_BLUE_STAINED_GLASS_PANE);
        gui.show(player);
    }

    @CommandAlias("JoyShop")
    @CommandPermission("SHOPS")
    private void joyShop(Player player) {
        int height = 4;
        final ChestGui gui = new ChestGui(height, guiHelper.guiName("Joy Shop"));
        final List<ItemList> guiContents = ItemList.getShop(Shops.JOY);
        menuBase(gui, guiContents, player, "Joy", height, Material.PINK_STAINED_GLASS_PANE, 2);
        gui.show(player);
    }

    @CommandAlias("CatLadyShop")
    @CommandPermission("SHOPS")
    private void catLadyShop(Player player) {
        int height = 4;
        final ChestGui gui = new ChestGui(height, guiHelper.guiName("Cat Lady Shop"));
        final List<ItemList> guiContents = ItemList.getShop(Shops.CAT_LADY);
        menuBase(gui, guiContents, player, "CatLady", height, Material.BROWN_STAINED_GLASS_PANE, 4);
        gui.show(player);
    }

    @CommandAlias("HellShop|DemonShop")
    @CommandPermission("SHOPS")
    private void demonShop(Player player) {
        int height = 5;
        final ChestGui gui = new ChestGui(height, guiHelper.guiName("Demon Shop"));
        final List<ItemList> guiContents = ItemList.getShop(Shops.DEMON);
        menuBase(gui, guiContents, player, "Demon", height, Material.RED_STAINED_GLASS_PANE);
        gui.show(player);
    }

    @CommandAlias("EnchantingShop")
    @CommandPermission("SHOPS")
    private void enchantingShop(Player player) {
        int height = 6;
        int length = 5;
        int x = 2;
        int y = 1;
        final ChestGui gui = new ChestGui(height, guiHelper.guiName("Enchanting Shop"));
        final List<ItemList> guiContents = ItemList.getShop(Shops.ENCHANTER);
        menuBase(gui, guiContents, player, "Enchanting", height, length, Material.PURPLE_STAINED_GLASS_PANE, x, y);
        gui.show(player);
    }

    private void menuBase(ChestGui gui, List<ItemList> guiContents, Player player, String shop, int height,
                          Material backgroundColor) {
        menuBase(gui, guiContents, player, shop, height, 7, backgroundColor, 1, 1);
    }

    private void menuBase(ChestGui gui, List<ItemList> guiContents, Player player, String shop, int height,
                          Material backgroundColor, int displayX) {
        menuBase(gui, guiContents, player, shop, height, 7, backgroundColor, displayX, 1);
    }

    private void menuBase(ChestGui gui, List<ItemList> guiContents, Player player, String shop, int height,
                          Material backgroundColor, int displayX, int displayY) {
        menuBase(gui, guiContents, player, shop, height, 7, backgroundColor, displayX, displayY);
    }

    private void menuBase(ChestGui gui, List<ItemList> guiContents, Player player, String shop, int height, int width,
                          Material backgroundColor) {
        menuBase(gui, guiContents, player, shop, height, width, backgroundColor, 1, 1);
    }

    private void menuBase(ChestGui gui, List<ItemList> guiContents, Player player, String shop, int height, int length,
                          Material backgroundColor, int displayX) {
        menuBase(gui, guiContents, player, shop, height, length, backgroundColor, displayX, 1);
    }

    private void menuBase(ChestGui gui, List<ItemList> guiContents, Player player, String shop, int height, int length, Material backgroundColor, int displayX, int displayY) {
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

            if (itemList.getCoinPrice() > 0 && itemList.getExpPrice() > 0 && itemList.getItemPrice() != null) {
                lore.add(Component.text(ChatColor.WHITE + "Price:"));
                lore.add(Component.text(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "- " + ChatColor.YELLOW + "⛂ " + numberFormat.numberFormat(itemList.getCoinPrice())));
                lore.add(Component.text(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "- " + ChatColor.AQUA + "Ξ " + numberFormat.numberFormat(itemList.getExpPrice())));
                for (String itemPrice : itemList.getItemPrice()) {
                    String[] itemPriceSplit = itemPrice.split(";");
                    lore.add(Component.text(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "- " + ChatColor.GOLD + itemPriceSplit[2] + "x " + mmoItems.getItem(itemPriceSplit[0], itemPriceSplit[1]).getItemMeta().getDisplayName()));
                }
            } else if (itemList.getCoinPrice() <= 0 && itemList.getExpPrice() > 0 && itemList.getItemPrice() != null) {
                lore.add(Component.text(ChatColor.WHITE + "Price:"));
                lore.add(Component.text(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "- " + ChatColor.AQUA + "Ξ " + numberFormat.numberFormat(itemList.getExpPrice())));
                for (String itemPrice : itemList.getItemPrice()) {
                    String[] itemPriceSplit = itemPrice.split(";");
                    lore.add(Component.text(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "- " + ChatColor.GOLD + itemPriceSplit[2] + "x " + mmoItems.getItem(itemPriceSplit[0], itemPriceSplit[1]).getItemMeta().getDisplayName()));
                }
            } else if (itemList.getCoinPrice() > 0 && itemList.getExpPrice() <= 0 && itemList.getItemPrice() != null) {
                lore.add(Component.text(ChatColor.WHITE + "Price:"));
                lore.add(Component.text(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "- " + ChatColor.YELLOW + "⛂ " + numberFormat.numberFormat(itemList.getCoinPrice())));
                for (String itemPrice : itemList.getItemPrice()) {
                    String[] itemPriceSplit = itemPrice.split(";");
                    lore.add(Component.text(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "- " + ChatColor.GOLD + itemPriceSplit[2] + "x " + mmoItems.getItem(itemPriceSplit[0], itemPriceSplit[1]).getItemMeta().getDisplayName()));
                }
            } else if (itemList.getCoinPrice() > 0 && itemList.getExpPrice() > 0 && itemList.getItemPrice() == null) {
                lore.add(Component.text(ChatColor.WHITE + "Price:"));
                lore.add(Component.text(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "- " + ChatColor.YELLOW + "⛂ " + numberFormat.numberFormat(itemList.getCoinPrice())));
                lore.add(Component.text(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "- " + ChatColor.AQUA + "Ξ " + numberFormat.numberFormat(itemList.getExpPrice())));
            } else if (itemList.getCoinPrice() > 0 && itemList.getExpPrice() <= 0 && itemList.getItemPrice() == null) {
                lore.add(Component.text(ChatColor.WHITE + "Price: " + ChatColor.YELLOW + "⛂ " + numberFormat.numberFormat(itemList.getCoinPrice())));
            } else if (itemList.getCoinPrice() <= 0 && itemList.getExpPrice() > 0 && itemList.getItemPrice() == null) {
                lore.add(Component.text(ChatColor.WHITE + "Price: " + ChatColor.AQUA + "Ξ " + numberFormat.numberFormat(itemList.getExpPrice())));
            } else if (itemList.getCoinPrice() <= 0 && itemList.getExpPrice() <= 0 && itemList.getItemPrice() != null) {
                lore.add(Component.text(ChatColor.WHITE + "Price:"));
                for (String itemPrice : itemList.getItemPrice()) {
                    String[] itemPriceSplit = itemPrice.split(";");
                    lore.add(Component.text(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "- " + ChatColor.GOLD + itemPriceSplit[2] + "x " + mmoItems.getItem(itemPriceSplit[0], itemPriceSplit[1]).getItemMeta().getDisplayName()));
                }

            }

            for (Component textComponent : itemLore(player, itemList))
                lore.add(textComponent);
            itemMeta.lore(lore);
            itemStack.setItemMeta(itemMeta);

            return new GuiItem(itemStack, e -> {
                if (itemList.getItemPrice() != null) {
                    if (e.isLeftClick() && !e.isShiftClick()
                            && purchaseUtils.hasItem(player, itemList.getItemPrice(), 1).size() >= itemList.getItemPrice().length)
                        purchaseUtils.purchase(player, itemList, 1);

                    if (e.isRightClick() && !e.isShiftClick() && economy.getBalance(player) >= itemList.getCoinPrice() * 16 && itemList.getMaxPurchaseAmount() >= 16
                            && purchaseUtils.hasItem(player, itemList.getItemPrice(), 16).size() >= itemList.getItemPrice().length)
                        purchaseUtils.purchase(player, itemList, 16);

                    if (e.isLeftClick() && e.isShiftClick() && economy.getBalance(player) >= itemList.getCoinPrice() * 32 && itemList.getMaxPurchaseAmount() >= 32
                            && purchaseUtils.hasItem(player, itemList.getItemPrice(), 32).size() >= itemList.getItemPrice().length)
                        purchaseUtils.purchase(player, itemList, 32);

                    if (e.isRightClick() && e.isShiftClick() && economy.getBalance(player) >= itemList.getCoinPrice() * 64 && itemList.getMaxPurchaseAmount() >= 64
                            && purchaseUtils.hasItem(player, itemList.getItemPrice(), 64).size() >= itemList.getItemPrice().length)
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

    private List<Component> itemLore(Player player, ItemList itemList) {
        List<Component> lore = new ArrayList<>();
        if (itemList.getItemPrice() != null) {
            if (purchaseUtils.hasItem(player, itemList.getItemPrice(), 1).size() >= itemList.getItemPrice().length) {
                if (economy.getBalance(player) >= itemList.getCoinPrice() && player.getLevel() >= itemList.getExpPrice()) {
                    lore.add(Component.text(""));
                    lore.add(Component.text(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Left-Click to Purchase " + ChatColor.GOLD + "1x"));
                    if (purchaseUtils.hasItem(player, itemList.getItemPrice(), 16).size() >= itemList.getItemPrice().length)
                        if (economy.getBalance(player) >= itemList.getCoinPrice() * 16 && player.getLevel() >= itemList.getExpPrice() * 16 && itemList.getMaxPurchaseAmount() >= 16)
                            lore.add(Component.text(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Right-Click to Purchase " + ChatColor.GOLD + "16x " + ChatColor.YELLOW + "(⛂ " + numberFormat.numberFormat(itemList.getCoinPrice() * 16) + ")"));
                    if (purchaseUtils.hasItem(player, itemList.getItemPrice(), 32).size() >= itemList.getItemPrice().length)
                        if (economy.getBalance(player) >= itemList.getCoinPrice() * 32 && player.getLevel() >= itemList.getExpPrice() * 32 && itemList.getMaxPurchaseAmount() >= 32)
                            lore.add(Component.text(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Shift-Left-Click to Purchase " + ChatColor.GOLD + "32x " + ChatColor.YELLOW + "(⛂ " + numberFormat.numberFormat(itemList.getCoinPrice() * 32) + ")"));
                    if (purchaseUtils.hasItem(player, itemList.getItemPrice(), 64).size() >= itemList.getItemPrice().length) {
                        if (economy.getBalance(player) >= itemList.getCoinPrice() * 64 && player.getLevel() >= itemList.getExpPrice() * 64 && itemList.getMaxPurchaseAmount() >= 64)
                            lore.add(Component.text(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Shift-Right-Click to Purchase " + ChatColor.GOLD + "64x " + ChatColor.YELLOW + "(⛂ " + numberFormat.numberFormat(itemList.getCoinPrice() * 64) + ")"));
                    }
                }
            }
        } else if (economy.getBalance(player) >= itemList.getCoinPrice() && player.getLevel() >= itemList.getExpPrice()) {
            lore.add(Component.text(""));
            lore.add(Component.text(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Left-Click to Purchase " + ChatColor.GOLD + "1x"));
            if (economy.getBalance(player) >= itemList.getCoinPrice() * 16 && player.getLevel() >= itemList.getExpPrice() * 16 && itemList.getMaxPurchaseAmount() >= 16)
                lore.add(Component.text(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Right-Click to Purchase " + ChatColor.GOLD + "16x " + ChatColor.YELLOW + "(⛂ " + numberFormat.numberFormat(itemList.getCoinPrice() * 16) + ")"));
            if (economy.getBalance(player) >= itemList.getCoinPrice() * 32 && player.getLevel() >= itemList.getExpPrice() * 32 && itemList.getMaxPurchaseAmount() >= 32)
                lore.add(Component.text(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Shift-Left-Click to Purchase " + ChatColor.GOLD + "32x " + ChatColor.YELLOW + "(⛂ " + numberFormat.numberFormat(itemList.getCoinPrice() * 32) + ")"));
            if (economy.getBalance(player) >= itemList.getCoinPrice() * 64 && player.getLevel() >= itemList.getExpPrice() * 64 && itemList.getMaxPurchaseAmount() >= 64)
                lore.add(Component.text(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Shift-Right-Click to Purchase " + ChatColor.GOLD + "64x " + ChatColor.YELLOW + "(⛂ " + numberFormat.numberFormat(itemList.getCoinPrice() * 64) + ")"));
        }
        return lore;
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
}



