package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.mainMenu.donation;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Dependency;
import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.PaginatedPane;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.GUI.GUIHelper;
import monzter.adventurescraft.plugin.utilities.beton.BetonPointsManager;
import monzter.adventurescraft.plugin.utilities.enums.Prefix;
import monzter.adventurescraft.plugin.utilities.enums.PrisonStatsDisplay;
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
import pl.betoncraft.betonquest.BetonQuest;

import java.util.ArrayList;
import java.util.List;

public class DonationShopsBuilder extends BaseCommand {

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
    private final BetonPointsManager betonPointsManager;


    public DonationShopsBuilder(AdventuresCraft plugin, SoundManager soundManager, GUIHelper guiHelper, ConsoleCommand consoleCommand, Economy economy, FullInventory fullInventory, MMOItemsGive mmoItemsGive, NumberFormat numberFormat, PurchaseUtils purchaseUtils, MMOItems mmoItems, PermissionLP permissionLP, BetonPointsManager betonPointsManager) {
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
        this.betonPointsManager = betonPointsManager;
    }

    @CommandAlias("skins|skinShop")
    private void miningShop(Player player) {
        int height = 4;
        final ChestGui gui = new ChestGui(height, guiHelper.guiName("Skin Shop"));
        final List<DonationItemList> guiContents = DonationItemList.getShop(DonationShops.SKINS);
        menuBase(gui, guiContents, player, "skin", height, Material.YELLOW_STAINED_GLASS_PANE, 2);
        gui.show(player);
    }

    @CommandAlias("boosters|boosterShop")
    private void boosterShop(Player player) {
        int height = 6;
        final ChestGui gui = new ChestGui(height, guiHelper.guiName("Booster Shop"));
        final List<DonationItemList> guiContents = DonationItemList.getShop(DonationShops.BOOSTERS);
        menuBase(gui, guiContents, player, "booster", height, Material.RED_STAINED_GLASS_PANE, 2, 1);
        gui.show(player);
    }

    @CommandAlias("crates|crateShop")
    private void crateShop(Player player) {
        int height = 3;
        final ChestGui gui = new ChestGui(height, guiHelper.guiName("Crate Shop"));
        final List<DonationItemList> guiContents = DonationItemList.getShop(DonationShops.CRATES);
        menuBase(gui, guiContents, player, "crate", height, Material.PINK_STAINED_GLASS_PANE, 3, 1);
        gui.show(player);
    }

    private void menuBase(ChestGui gui, List<DonationItemList> guiContents, Player player, String shop, int height,
                          Material backgroundColor) {
        menuBase(gui, guiContents, player, shop, height, 7, backgroundColor, 1, 1);
    }

    private void menuBase(ChestGui gui, List<DonationItemList> guiContents, Player player, String shop, int height,
                          Material backgroundColor, int displayX) {
        menuBase(gui, guiContents, player, shop, height, 7, backgroundColor, displayX, 1);
    }

    private void menuBase(ChestGui gui, List<DonationItemList> guiContents, Player player, String shop, int height,
                          Material backgroundColor, int displayX, int displayY) {
        menuBase(gui, guiContents, player, shop, height, 7, backgroundColor, displayX, displayY);
    }

    private void menuBase(ChestGui gui, List<DonationItemList> guiContents, Player player, String shop, int height, int width,
                          Material backgroundColor) {
        menuBase(gui, guiContents, player, shop, height, width, backgroundColor, 1, 1);
    }

    private void menuBase(ChestGui gui, List<DonationItemList> guiContents, Player player, String shop, int height, int length,
                          Material backgroundColor, int displayX) {
        menuBase(gui, guiContents, player, shop, height, length, backgroundColor, displayX, 1);
    }

    private void menuBase(ChestGui gui, List<DonationItemList> guiContents, Player player, String shop, int height, int length, Material backgroundColor, int displayX, int displayY) {
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        PaginatedPane page = new PaginatedPane(0, 0, 9, height);
        OutlinePane background = new OutlinePane(0, 0, 9, height, Pane.Priority.LOWEST);
        OutlinePane display = new OutlinePane(displayX, displayY, 5, height, Pane.Priority.LOW);
        OutlinePane display2 = new OutlinePane(displayX, displayY, 5, height, Pane.Priority.LOW);
        StaticPane backButton = new StaticPane(0, 0, 9, height, Pane.Priority.LOW);
        StaticPane forwardButton = new StaticPane(0, 0, 9, height, Pane.Priority.LOW);

        page.addPane(0, background);
        page.addPane(0, display);
        page.addPane(0, forwardButton);
        page.addPane(1, background);
        page.addPane(1, display2);
        page.addPane(1, backButton);

        background.addItem(new GuiItem(guiHelper.background(backgroundColor)));
        background.setRepeat(true);

        int i = 0;
        for (DonationItemList item : guiContents) {
            if (i < 20)
                display.addItem(generateItem(player, item, shop, backgroundColor));
            else
                display2.addItem(generateItem(player, item, shop, backgroundColor));
            i++;
        }

        if (!display2.getItems().isEmpty()) {
            backButton.addItem(new GuiItem((guiHelper.previousPageButton()), event -> {
                page.setPage(page.getPage() - 1);
                if (page.getPage() == 0) {
                    backButton.setVisible(false);
                }
                forwardButton.setVisible(true);
                gui.update();
            }), 0, height - 1);
            backButton.setVisible(false);
            forwardButton.addItem(new GuiItem((guiHelper.nextPageButton()), event -> {
                page.setPage(page.getPage() + 1);
                if (page.getPage() == page.getPages() - 1) {
                    forwardButton.setVisible(false);
                }
                backButton.setVisible(true);
                gui.update();
            }), 8, height - 1);
        }

        gui.addPane(page);
    }

    public GuiItem generateItem(Player player, DonationItemList donationItemList, String shop, Material backgroundColor) {
        if (donationItemList.getMaxPurchaseAmount() == 0 && donationItemList.getCoinPrice() == 0 && donationItemList.getType() == null && donationItemList.getID() == null)
            return new GuiItem(guiHelper.background(backgroundColor));

        ItemStack itemStack;
        itemStack = mmoItems.getItem(donationItemList.getType(), donationItemList.getID());
        final ItemMeta itemMeta = itemStack.getItemMeta();

        List<Component> lore = itemStack.lore();
        if (lore == null)
            lore = new ArrayList<>();
        lore.add(Component.empty());
        lore.add(Component.text(ChatColor.WHITE + "Price: " + PrisonStatsDisplay.ADVENTURE_COINS.getName() + " " + numberFormat.numberFormat(donationItemList.getCoinPrice())));
        for (Component itemLore : itemLore(player, donationItemList))
            lore.add(itemLore);

        itemMeta.lore(lore);
        itemStack.setItemMeta(itemMeta);

        return new GuiItem(itemStack, event -> {
            int balance = betonPointsManager.getPoints("items.AdventureCoin", BetonQuest.getInstance().getPlayerData(player.getUniqueId().toString()).getPoints());
            if (!donationItemList.getShop().equals(DonationShops.CRATES)) {
                if (event.isLeftClick() && !event.isShiftClick() && balance >= donationItemList.getCoinPrice())
                    purchaseUtils.purchase(player, donationItemList, 1);
                if (event.isRightClick() && !event.isShiftClick() && balance >= donationItemList.getCoinPrice() * 16 && donationItemList.getMaxPurchaseAmount() >= 16)
                    purchaseUtils.purchase(player, donationItemList, 16);
                if (event.isLeftClick() && event.isShiftClick() && balance >= donationItemList.getCoinPrice() * 32 && donationItemList.getMaxPurchaseAmount() >= 32)
                    purchaseUtils.purchase(player, donationItemList, 32);
                if (event.isRightClick() && event.isShiftClick() && balance >= donationItemList.getCoinPrice() * 64 && donationItemList.getMaxPurchaseAmount() >= 64)
                    purchaseUtils.purchase(player, donationItemList, 64);
                player.performCommand(shop + "Shop");
            } else {
                if (event.isLeftClick() && !event.isShiftClick()) {
                    purchaseUtils.purchase(player, donationItemList, 1);
                    player.performCommand(shop + "Shop");
                }
                if (event.isRightClick() && !event.isShiftClick())
                    player.performCommand("dropTableViewer " + donationItemList.getID());
            }
        });
    }

    private List<Component> itemLore(Player player, DonationItemList donationItemList) {
        List<Component> lore = new ArrayList<>();
        int balance = betonPointsManager.getPoints("items.AdventureCoin", BetonQuest.getInstance().getPlayerData(player.getUniqueId().toString()).getPoints());
        if (!donationItemList.getShop().equals(DonationShops.CRATES)) {
            if (balance >= donationItemList.getCoinPrice() && donationItemList.getMaxPurchaseAmount() > 0) {
                lore.add(Component.text(""));
                lore.add(Component.text(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Left-Click to Purchase " + ChatColor.GOLD + "1x"));
                if (balance >= donationItemList.getCoinPrice() * 16 && donationItemList.getMaxPurchaseAmount() >= 16)
                    lore.add(Component.text(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Right-Click to Purchase " + ChatColor.GOLD + "16x " + ChatColor.RED + "(◎ " + numberFormat.numberFormat(donationItemList.getCoinPrice() * 16) + ")"));
                if (balance >= donationItemList.getCoinPrice() * 32 && donationItemList.getMaxPurchaseAmount() >= 32)
                    lore.add(Component.text(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Shift-Left-Click to Purchase " + ChatColor.GOLD + "32x " + ChatColor.RED + "(◎ " + numberFormat.numberFormat(donationItemList.getCoinPrice() * 32) + ")"));
                if (balance >= donationItemList.getCoinPrice() * 64 && donationItemList.getMaxPurchaseAmount() >= 64)
                    lore.add(Component.text(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Shift-Right-Click to Purchase " + ChatColor.GOLD + "64x " + ChatColor.RED + "(◎ " + numberFormat.numberFormat(donationItemList.getCoinPrice() * 64) + ")"));
            }
        } else {
            lore.add(Component.text(""));
            if (balance >= donationItemList.getCoinPrice() && donationItemList.getMaxPurchaseAmount() > 0)
                lore.add(Component.text(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Left-Click to Purchase " + ChatColor.GOLD + "1x"));
            lore.add(Component.text(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Right-Click to View Loot Table"));
        }
        return lore;
    }
}



