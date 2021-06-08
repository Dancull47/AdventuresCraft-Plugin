package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.shops.npcs;

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
import monzter.adventurescraft.plugin.utilities.general.PurchaseUtils;
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

public class Mercenary extends BaseCommand {

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


    public Mercenary(AdventuresCraft plugin, SoundManager soundManager, GUIHelper guiHelper, ConsoleCommand consoleCommand, Economy economy, FullInventory fullInventory, MMOItemsGive mmoItemsGive, NumberFormat numberFormat, PurchaseUtils purchaseUtils) {
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

    @CommandAlias("mercenaryShop")
    public void pets(Player player) {
        if (player.hasPermission("SHOPS")) {
            ChestGui gui = new ChestGui(4, guiHelper.guiName("Mercenary Shop"));
            gui.setOnGlobalClick(event -> event.setCancelled(true));

            PaginatedPane page = new PaginatedPane(0, 0, 9, 4);
            OutlinePane background = new OutlinePane(0, 0, 9, 4, Pane.Priority.LOWEST);
            OutlinePane display = new OutlinePane(1, 1, 7, 4, Pane.Priority.LOW);

            page.addPane(0, background);
            page.addPane(0, display);

            background.addItem(new GuiItem(guiHelper.background(Material.RED_STAINED_GLASS_PANE)));
            background.setRepeat(true);

            for (MercenaryItemList mercenary : MercenaryItemList.values()) {
                ItemStack mercenaryItem = new ItemStack(mercenary.getMaterial());
                final ItemMeta mercenaryItemItemMeta = mercenaryItem.getItemMeta();
                if (mercenaryItem != null) {
                    List<Component> lore = mercenaryItem.lore();
                    if (lore == null) {
                        lore = new ArrayList<>();
                    } else if (!lore.isEmpty()) {
                        lore.add(Component.empty());
                    }
                    lore.add(Component.text(ChatColor.WHITE + "Price: " + ChatColor.YELLOW + "⛂ " + numberFormat.numberFormat(mercenary.getPrice())));
                    if (economy.getBalance(player) >= mercenary.getPrice()) {
                        lore.add(Component.text(""));
                        lore.add(Component.text(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Left-Click to Purchase " + ChatColor.GOLD + "1x"));
                    }
                    if (economy.getBalance(player) >= mercenary.getPrice() * 16)
                        lore.add(Component.text(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Right-Click to Purchase " + ChatColor.GOLD + "16x " + ChatColor.YELLOW + "(⛂ " + numberFormat.numberFormat(mercenary.getPrice() * 16) + ")"));
                    if (economy.getBalance(player) >= mercenary.getPrice() * 32)
                        lore.add(Component.text(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Shift-Left-Click to Purchase " + ChatColor.GOLD + "32x " + ChatColor.YELLOW + "(⛂ " + numberFormat.numberFormat(mercenary.getPrice() * 32) + ")"));
                    if (economy.getBalance(player) >= mercenary.getPrice() * 64)
                        lore.add(Component.text(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Shift-Right-Click to Purchase " + ChatColor.GOLD + "64x " + ChatColor.YELLOW + "(⛂ " + numberFormat.numberFormat(mercenary.getPrice() * 64) + ")"));
                    mercenaryItemItemMeta.lore(lore);
                    mercenaryItem.setItemMeta(mercenaryItemItemMeta);
                    display.addItem(new GuiItem(mercenaryItem, e -> {
//                        if (e.isLeftClick() && !e.isShiftClick())
//                            purchaseUtils.purchase(player, mercenary.getMaterial(), 1, mercenary.getPrice());
//                        if (e.isRightClick() && !e.isShiftClick())
//                            purchaseUtils.purchase(player, mercenary.getMaterial(), 16, mercenary.getPrice());
//                        if (e.isLeftClick() && e.isShiftClick())
//                            purchaseUtils.purchase(player, mercenary.getMaterial(), 32, mercenary.getPrice());
//                        if (e.isRightClick() && e.isShiftClick())
//                            purchaseUtils.purchase(player, mercenary.getMaterial(), 64, mercenary.getPrice());
                    }));
                }
            }
            gui.addPane(background);
            gui.addPane(display);
            gui.show(player);
        }
    }
}

enum MercenaryItemList {
    ITEM1(Material.BONE, 20),
    ITEM2(Material.SPIDER_EYE, 35),
    ITEM3(Material.STRING, 30),
    ITEM4(Material.GUNPOWDER, 50),
    ITEM5(Material.ROTTEN_FLESH, 15),
    ITEM6(Material.ENDER_PEARL, 30),
    ITEM7(Material.MAGMA_CREAM, 20),
    ITEM8(Material.BLAZE_ROD, 25),
    ITEM9(Material.SLIME_BALL, 60),
    ITEM10(Material.ARROW, 20),
    ;
    private Material material;
    private int price;

    MercenaryItemList(Material material, int price) {
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
