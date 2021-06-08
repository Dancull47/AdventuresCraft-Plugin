package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.shops.npcs;

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
import monzter.adventurescraft.plugin.utilities.enums.Prefix;
import monzter.adventurescraft.plugin.utilities.general.ConsoleCommand;
import monzter.adventurescraft.plugin.utilities.general.FullInventory;
import monzter.adventurescraft.plugin.utilities.general.PurchaseUtils;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
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

public class Farmer extends BaseCommand {

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


    public Farmer(AdventuresCraft plugin, SoundManager soundManager, GUIHelper guiHelper, ConsoleCommand consoleCommand, Economy economy, FullInventory fullInventory, MMOItemsGive mmoItemsGive, NumberFormat numberFormat, PurchaseUtils purchaseUtils, MMOItems mmoItems) {
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
    }

    @CommandAlias("farmerShop")
    public void pets(Player player) {
        if (player.hasPermission("SHOPS")) {
            ChestGui gui = new ChestGui(5, guiHelper.guiName("Farmer Shop"));
            gui.setOnGlobalClick(event -> event.setCancelled(true));

            PaginatedPane page = new PaginatedPane(0, 0, 9, 5);
            OutlinePane background = new OutlinePane(0, 0, 9, 5, Pane.Priority.LOWEST);
            OutlinePane display = new OutlinePane(1, 1, 7, 5, Pane.Priority.LOW);
            StaticPane sell = new StaticPane(0, 0, 9, 5, Pane.Priority.LOW);

            page.addPane(0, background);
            page.addPane(0, display);
            page.addPane(0, sell);

            background.addItem(new GuiItem(guiHelper.background(Material.LIME_STAINED_GLASS_PANE)));
            background.setRepeat(true);

            ItemStack farmerItem;
            ItemStack storedItem;

            for (FarmerItemList farmer : FarmerItemList.values()) {

                if (farmer.getType() == null) {
                    farmerItem = new ItemStack(farmer.getItemStack());
                    storedItem = new ItemStack(farmer.getItemStack());
                } else {
                    farmerItem = mmoItems.getItem(farmer.getType(), farmer.getID());
                    storedItem = mmoItems.getItem(farmer.getType(), farmer.getID());
                }

                final ItemMeta farmerItemItemMeta = farmerItem.getItemMeta();
                if (farmerItem != null) {
                    List<Component> lore = farmerItem.lore();
                    if (lore == null) {
                        lore = new ArrayList<>();
                    }
                    lore.add(Component.empty());
                    lore.add(Component.text(ChatColor.WHITE + "Price: " + ChatColor.YELLOW + "⛂ " + numberFormat.numberFormat(farmer.getPrice())));
                    if (economy.getBalance(player) >= farmer.getPrice()) {
                        lore.add(Component.text(""));
                        lore.add(Component.text(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Left-Click to Purchase " + ChatColor.GOLD + "1x"));
                        if (economy.getBalance(player) >= farmer.getPrice() * 16)
                            lore.add(Component.text(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Right-Click to Purchase " + ChatColor.GOLD + "16x " + ChatColor.YELLOW + "(⛂ " + numberFormat.numberFormat(farmer.getPrice() * 16) + ")"));
                        if (economy.getBalance(player) >= farmer.getPrice() * 32)
                            lore.add(Component.text(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Shift-Left-Click to Purchase " + ChatColor.GOLD + "32x " + ChatColor.YELLOW + "(⛂ " + numberFormat.numberFormat(farmer.getPrice() * 32) + ")"));
                        if (economy.getBalance(player) >= farmer.getPrice() * 64)
                            lore.add(Component.text(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Shift-Right-Click to Purchase " + ChatColor.GOLD + "64x " + ChatColor.YELLOW + "(⛂ " + numberFormat.numberFormat(farmer.getPrice() * 64) + ")"));
                    }

                    farmerItemItemMeta.lore(lore);
                    farmerItem.setItemMeta(farmerItemItemMeta);

                    ItemStack finalStoredItem = storedItem;
                    display.addItem(new GuiItem(farmerItem, e -> {
                        if (e.isLeftClick() && !e.isShiftClick())
                            purchaseUtils.purchase(player, finalStoredItem, 1, farmer.getPrice());
                        if (e.isRightClick() && !e.isShiftClick())
                            purchaseUtils.purchase(player, finalStoredItem, 16, farmer.getPrice());
                        if (e.isLeftClick() && e.isShiftClick())
                            purchaseUtils.purchase(player, finalStoredItem, 32, farmer.getPrice());
                        if (e.isRightClick() && e.isShiftClick())
                            purchaseUtils.purchase(player, finalStoredItem, 64, farmer.getPrice());
                        player.performCommand("farmerShop");
                    }));
                }
            }

            sell.addItem(new GuiItem(guiHelper.itemCreator(Material.CAULDRON,ChatColor.GREEN + "Sell", new String[]{"", ""}), e -> player.performCommand("")), 4, 4);

            gui.addPane(background);
            gui.addPane(display);
            gui.addPane(sell);
            gui.show(player);
        }
    }
}


enum FarmerItemList {
    ITEM1(new ItemStack(Material.WHEAT_SEEDS), 2, null, null),
    ITEM2(new ItemStack(Material.WHEAT), 2, null, null),
    ITEM3(new ItemStack(Material.CARROT), 4, null, null),
    ITEM4(new ItemStack(Material.POTATO), 4, null, null),
    ITEM5(new ItemStack(Material.BEEF), 5, null, null),
    ITEM6(new ItemStack(Material.LEATHER), 10, null, null),
    ITEM7(new ItemStack(Material.PORKCHOP), 5, null, null),
    ITEM8(new ItemStack(Material.MUTTON), 6, null, null),
    ITEM9(new ItemStack(Material.WHITE_WOOL), 10, null, null),
    ITEM10(new ItemStack(Material.CHICKEN), 8, null, null),
    ITEM11(new ItemStack(Material.FEATHER), 5, null, null),
    ITEM12(new ItemStack(Material.RABBIT), 6, null, null),
    ITEM13(new ItemStack(Material.RABBIT_HIDE), 10, null, null),
    ITEM14(new ItemStack(Material.RABBIT_FOOT), 10, null, null),
    ITEM15(null, 10, "TOOL", "WOODEN_HOE"),
    ;
    private ItemStack itemStack;
    private int price;
    private String type;
    private String ID;

    FarmerItemList(ItemStack itemStack, int price, String type, String id) {
        this.itemStack = itemStack;
        this.price = price;
        this.type = type;
        this.ID = id;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public int getPrice() {
        return price;
    }

    public Material getMaterial() {
        return itemStack.getType();
    }

    public String getType() {
        return type;
    }

    public String getID() {
        return ID;
    }
}
