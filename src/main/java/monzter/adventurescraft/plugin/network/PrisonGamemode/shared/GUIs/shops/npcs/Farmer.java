package monzter.adventurescraft.plugin.network.PrisonGamemode.shared.GUIs.shops.npcs;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Dependency;
import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.PaginatedPane;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.GUI.GUIHelper;
import monzter.adventurescraft.plugin.utilities.enums.Prefix;
import monzter.adventurescraft.plugin.utilities.general.ConsoleCommand;
import monzter.adventurescraft.plugin.utilities.general.FullInventory;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import monzter.adventurescraft.plugin.utilities.mmoitems.MMOItemsGive;
import monzter.adventurescraft.plugin.utilities.text.NumberFormat;
import monzter.adventurescraft.plugin.utilities.vault.Economy;
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


    public Farmer(AdventuresCraft plugin, SoundManager soundManager, GUIHelper guiHelper, ConsoleCommand consoleCommand, Economy economy, FullInventory fullInventory, MMOItemsGive mmoItemsGive, NumberFormat numberFormat, PurchaseUtils purchaseUtils) {
        this.plugin = plugin;
        this.soundManager = soundManager;
        this.guiHelper = guiHelper;
        this.consoleCommand = consoleCommand;
        this.economy = economy;
        this.fullInventory = fullInventory;
        this.mmoItemsGive = mmoItemsGive;
        this.numberFormat = numberFormat;
        this.purchaseUtils = purchaseUtils;
    }

    @CommandAlias("farmerShop")
    public void pets(Player player) {
        if (player.hasPermission("SHOPS")) {
            ChestGui gui = new ChestGui(4, guiHelper.guiName("Farmer Shop"));
            gui.setOnGlobalClick(event -> event.setCancelled(true));

            PaginatedPane page = new PaginatedPane(0, 0, 9, 4);
            OutlinePane background = new OutlinePane(0, 0, 9, 4, Pane.Priority.LOWEST);
            OutlinePane display = new OutlinePane(1, 1, 7, 4, Pane.Priority.LOW);

            page.addPane(0, background);
            page.addPane(0, display);

            background.addItem(new GuiItem(guiHelper.background(Material.YELLOW_STAINED_GLASS_PANE)));
            background.setRepeat(true);

            for (FarmerItemList farmer : FarmerItemList.values()) {
                ItemStack farmerItem = new ItemStack(farmer.getMaterial());
                final ItemMeta farmerItemItemMeta = farmerItem.getItemMeta();
                if (farmerItem != null) {
                    List<Component> lore = farmerItem.lore();
                    if (lore == null) {
                        lore = new ArrayList<>();
                    } else if (!lore.isEmpty()) {
                        lore.add(Component.empty());
                    }
                    lore.add(Component.text(ChatColor.WHITE + "Price: " + ChatColor.YELLOW + "⛂ " + numberFormat.numberFormat(farmer.getPrice())));
                    if (economy.getBalance(player) >= farmer.getPrice()) {
                        lore.add(Component.text(""));
                        lore.add(Component.text(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Left-Click to Purchase " + ChatColor.GOLD + "1x"));
                    }
                    if (economy.getBalance(player) >= farmer.getPrice() * 16)
                        lore.add(Component.text(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Right-Click to Purchase " + ChatColor.GOLD + "16x " + ChatColor.YELLOW + "(⛂ " + numberFormat.numberFormat(farmer.getPrice() * 16) + ")"));
                    if (economy.getBalance(player) >= farmer.getPrice() * 32)
                        lore.add(Component.text(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Shift-Left-Click to Purchase " + ChatColor.GOLD + "32x " + ChatColor.YELLOW + "(⛂ " + numberFormat.numberFormat(farmer.getPrice() * 32) + ")"));
                    if (economy.getBalance(player) >= farmer.getPrice() * 64)
                        lore.add(Component.text(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Shift-Right-Click to Purchase " + ChatColor.GOLD + "64x " + ChatColor.YELLOW + "(⛂ " + numberFormat.numberFormat(farmer.getPrice() * 64) + ")"));
                    farmerItemItemMeta.lore(lore);
                    farmerItem.setItemMeta(farmerItemItemMeta);
                    display.addItem(new GuiItem(farmerItem, e -> {
                        if (e.isLeftClick() && !e.isShiftClick())
                            purchaseUtils.purchase(player, farmer.getMaterial(), 1, farmer.getPrice());
                        if (e.isRightClick() && !e.isShiftClick())
                            purchaseUtils.purchase(player, farmer.getMaterial(), 16, farmer.getPrice());
                        if (e.isLeftClick() && e.isShiftClick())
                            purchaseUtils.purchase(player, farmer.getMaterial(), 32, farmer.getPrice());
                        if (e.isRightClick() && e.isShiftClick())
                            purchaseUtils.purchase(player, farmer.getMaterial(), 64, farmer.getPrice());
                    }));
                }
            }
            gui.addPane(background);
            gui.addPane(display);
            gui.show(player);
        }
    }
}

enum FarmerItemList {
    ITEM1(Material.WHEAT_SEEDS, 2),
    ITEM2(Material.WHEAT, 2),
    ITEM3(Material.CARROT, 4),
    ITEM4(Material.POTATO, 4),
    ITEM5(Material.BEEF, 5),
    ITEM6(Material.LEATHER, 10),
    ITEM7(Material.PORKCHOP, 5),
    ITEM8(Material.MUTTON, 6),
    ITEM9(Material.WHITE_WOOL, 10),
    ITEM10(Material.CHICKEN, 8),
    ITEM11(Material.FEATHER, 5),
    ITEM12(Material.RABBIT, 6),
    ITEM13(Material.RABBIT_HIDE, 10),
    ITEM14(Material.RABBIT_FOOT, 10),
    ;
    private Material material;
    private int price;

    FarmerItemList(Material material, int price) {
        this.material = material;
        this.price = price;
    }

    public Material getMaterial() {
        return material;
    }

    public int getPrice() {
        return price;
    }

}
