package monzter.adventurescraft.plugin.utilities.general.Purchase;

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
import org.apache.commons.lang.WordUtils;
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

    public void menuBase(ChestGui gui, List<ItemList> guiContents, Player player, String shopCommand, Material backgroundColor) {
        int height = heightCalc(guiContents.size());
        int length = 7;
        int displayX = displayXCalc(guiContents.size());
        int displayY = 1;

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
            display.addItem(generateItem(player, item, shopCommand));

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

        display.addItem(generateItem(player, guiContents, "WanderingTraderShop " + shopNumber));

        gui.addPane(page);
    }

    public GuiItem generateItem(Player player, ItemList itemList, String shopCommand) {
        ItemStack itemStack = new ItemStack(itemList.getItemStack());
        final ItemMeta itemMeta = itemStack.getItemMeta();

        if (itemStack != null) {
            List<Component> lore = itemStack.lore();
            if (lore == null)
                lore = new ArrayList<>();
            lore.add(Component.empty());

            if (itemList.getProfessionLevel() != null) {
                lore.add(Component.text(ChatColor.GOLD.toString() + ChatColor.BOLD + "Requirements:"));
                for (String professionString : itemList.getProfessionLevel()) {
                    String[] professionLevelSplit = professionString.split(",");
                    String profession = professionLevelSplit[0];
                    int professionLevel = Integer.valueOf(professionLevelSplit[1]);
                    if (!purchaseUtils.professionCheck(player, professionString))
                        lore.add(Component.text(ChatColor.RED.toString() + ChatColor.BOLD + "✖ " + ChatColor.YELLOW + WordUtils.capitalizeFully(profession) + " Level " + professionLevel));
                    else
                        lore.add(Component.text(ChatColor.GREEN.toString() + ChatColor.BOLD + "✓ " + ChatColor.YELLOW + WordUtils.capitalizeFully(profession) + " Level " + professionLevel));
                }
                lore.add(Component.empty());
            }

            lore.add(Component.text(ChatColor.GOLD.toString() + ChatColor.BOLD + "Price:"));
            if (itemList.getCoinPrice() > 0)
                lore.add(Component.text(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "- " + ChatColor.YELLOW + "⛂ " + numberFormat.numberFormat(itemList.getCoinPrice())));
            if (itemList.getItemPrice() != null)
                for (ItemStack itemPrice : itemList.getItemPrice()) {
                    String displayName = itemPrice.getItemMeta().getDisplayName();
                    if (displayName.isEmpty())
                        displayName = itemPrice.getI18NDisplayName();
                    if (purchaseUtils.hasItem(player, new ItemStack[]{(itemPrice)}, 1))
                        lore.add(Component.text(ChatColor.GREEN.toString() + ChatColor.BOLD + "✓ " + ChatColor.GOLD + itemPrice.getAmount() + "x " + displayName));
                    else
                        lore.add(Component.text(ChatColor.RED.toString() + ChatColor.BOLD + "✖ " + ChatColor.GOLD + itemPrice.getAmount() + "x " + displayName));

                }

            if (itemPurchaseLoreCheck(player, itemList, 1) && itemList.getMaxPurchaseAmount() >= 1) {
                lore.add(Component.text(""));
                lore.add(Component.text(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Left-Click to Purchase " + ChatColor.GOLD + "1x"));
            }
            if (itemPurchaseLoreCheck(player, itemList, 16) && itemList.getMaxPurchaseAmount() >= 16)
                lore.add(Component.text(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Right-Click to Purchase " + ChatColor.GOLD + "16x"));
            if (itemPurchaseLoreCheck(player, itemList, 32) && itemList.getMaxPurchaseAmount() >= 32)
                lore.add(Component.text(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Shift-Left-Click to Purchase " + ChatColor.GOLD + "32x"));
            if (itemPurchaseLoreCheck(player, itemList, 64) && itemList.getMaxPurchaseAmount() >= 64)
                lore.add(Component.text(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Shift-Right-Click to Purchase " + ChatColor.GOLD + "64x"));

            itemMeta.lore(lore);
            itemStack.setItemMeta(itemMeta);

            return new GuiItem(itemStack, e -> {
                if (itemList.getItemPrice() != null) {
                    if (e.isLeftClick() && !e.isShiftClick() && economy.getBalance(player) >= itemList.getCoinPrice()
                            && purchaseUtils.hasItem(player, itemList.getItemPrice(), 1))
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
                    if (e.isLeftClick() && !e.isShiftClick() && economy.getBalance(player) >= itemList.getCoinPrice())
                        purchaseUtils.purchase(player, itemList, 1);
                    if (e.isRightClick() && !e.isShiftClick() && economy.getBalance(player) >= itemList.getCoinPrice() * 16 && itemList.getMaxPurchaseAmount() >= 16)
                        purchaseUtils.purchase(player, itemList, 16);
                    if (e.isLeftClick() && e.isShiftClick() && economy.getBalance(player) >= itemList.getCoinPrice() * 32 && itemList.getMaxPurchaseAmount() >= 32)
                        purchaseUtils.purchase(player, itemList, 32);
                    if (e.isRightClick() && e.isShiftClick() && economy.getBalance(player) >= itemList.getCoinPrice() * 64 && itemList.getMaxPurchaseAmount() >= 64)
                        purchaseUtils.purchase(player, itemList, 64);
                }
                player.performCommand(shopCommand);
            });
        }
        return null;
    }

    public boolean itemPurchaseLoreCheck(Player player, ItemList itemList, int amount) {
        if (economy.getBalance(player) < (itemList.getCoinPrice() * amount))
            return false;
        if (itemList.getProfessionLevel() != null)
            for (String profession : itemList.getProfessionLevel())
                if (!purchaseUtils.professionCheck(player, profession))
                    return false;
        if (itemList.getItemPrice() != null && !purchaseUtils.hasItem(player, itemList.getItemPrice(), amount))
            return false;
        return true;
    }

    public int displayXCalc(int size) {
        switch (size) {
            case 1:
                return 4;
            case 2:
            case 3:
                return 3;
            case 4:
            case 5:
                return 2;
            case 6:
            case 7:
                return 1;
        }
        return 1;
    }

    public int heightCalc(int size) {
        switch (size) {
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
                return 5;
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
                return 6;
        }
        return 4;
    }
}
