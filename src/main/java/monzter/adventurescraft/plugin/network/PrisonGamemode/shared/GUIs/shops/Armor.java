package monzter.adventurescraft.plugin.network.PrisonGamemode.shared.GUIs.shops;

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
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import monzter.adventurescraft.plugin.utilities.mmoitems.MMOItemsGive;
import monzter.adventurescraft.plugin.utilities.text.NumberFormat;
import monzter.adventurescraft.plugin.utilities.vault.Economy;
import net.Indyuce.mmoitems.MMOItems;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Armor extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final SoundManager soundManager;
    private final GUIHelper guiHelper;
    private final ConsoleCommand consoleCommand;
    private final Economy economy;
    private final FullInventory fullInventory;
    private final MMOItemsGive mmoItemsGive;
    private final NumberFormat numberFormat;

    public Armor(AdventuresCraft plugin, SoundManager soundManager, GUIHelper guiHelper, ConsoleCommand consoleCommand, Economy economy, FullInventory fullInventory, MMOItemsGive mmoItemsGive, NumberFormat numberFormat) {
        this.plugin = plugin;
        this.soundManager = soundManager;
        this.guiHelper = guiHelper;
        this.consoleCommand = consoleCommand;
        this.economy = economy;
        this.fullInventory = fullInventory;
        this.mmoItemsGive = mmoItemsGive;
        this.numberFormat = numberFormat;
    }

    @CommandAlias("armorShop")
    public void pets(Player player) {
        if (player.hasPermission("SHOPS") || nearEntity(player)) {
            ChestGui gui = new ChestGui(6, guiHelper.guiName("Armor Shop"));
            gui.setOnGlobalClick(event -> event.setCancelled(true));

            PaginatedPane page = new PaginatedPane(0, 0, 9, 6);
            OutlinePane background = new OutlinePane(0, 0, 9, 6, Pane.Priority.LOWEST);
            OutlinePane display = new OutlinePane(1, 1, 7, 6, Pane.Priority.LOW);
            OutlinePane display2 = new OutlinePane(1, 1, 4, 6, Pane.Priority.LOW);
            StaticPane back = new StaticPane(0, 5, 1, 1, Pane.Priority.HIGH);
            StaticPane forward = new StaticPane(8, 5, 1, 1, Pane.Priority.HIGH);

            page.addPane(0, background);
            page.addPane(0, display);
            page.addPane(1, background);
            page.addPane(1, display2);

            background.addItem(new GuiItem(guiHelper.background(Material.GREEN_STAINED_GLASS_PANE)));
            background.setRepeat(true);

            int i = 0;
            for (ArmorList armor : ArmorList.values()) {
                ItemStack armorItem = MMOItems.plugin.getItem("ARMOR", armor.getId());
                final ItemMeta armorItemItemMeta = armorItem.getItemMeta();
                if (armorItem != null) {
                    List<Component> lore = armorItem.lore();
                    if (lore == null) {
                        lore = new ArrayList<>();
                    } else if (!lore.isEmpty()) {
                        lore.add(Component.empty());
                    }
                    lore.add(Component.text(ChatColor.WHITE + "Price: " + ChatColor.YELLOW + "⛂ " + numberFormat.numberFormat(armor.getPrice())));
                    if (economy.getBalance(player) >= armor.getPrice()) {
                        lore.add(Component.text(""));
                        lore.add(Component.text(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Purchase"));
                    }
                    armorItemItemMeta.lore(lore);
                    armorItem.setItemMeta(armorItemItemMeta);

                    if (i < 28) {
                        display.addItem(new GuiItem(armorItem, e -> purchase(player, armor.getId(), armor.getPrice())));
                        i++;
                    } else {
                        display2.addItem(new GuiItem(armorItem, e -> purchase(player, armor.getId(), armor.getPrice())));
                    }

                }
            }

            if (!display2.getItems().isEmpty()) {
                back.addItem(new GuiItem((guiHelper.previousPageButton()), event -> {
                    page.setPage(page.getPage() - 1);
                    if (page.getPage() == 0) {
                        back.setVisible(false);
                    }
                    forward.setVisible(true);
                    gui.update();
                }), 0, 0);
                back.setVisible(false);
                forward.addItem(new GuiItem((guiHelper.nextPageButton()), event -> {
                    page.setPage(page.getPage() + 1);
                    if (page.getPage() == page.getPages() - 1) {
                        forward.setVisible(false);
                    }
                    back.setVisible(true);
                    gui.update();
                }), 0, 0);
            }

            gui.addPane(page);
            gui.addPane(back);
            gui.addPane(forward);
            gui.show(player);
        }
    }

    private void purchase(Player player, String itemID, int price) {
        if (economy.hasMoney(player, price)) {
            if (!fullInventory.fullInventory(player)) {
                economy.takeMoney(player, price);
                mmoItemsGive.giveMMOItem(player, "ARMOR", itemID);
            }
        }
    }
    private boolean nearEntity(Player player) {
        for (Entity entity : player.getNearbyEntities(3, 3, 3)) {
            if (entity.hasMetadata("NPC"))
                return true;
        }
        soundManager.soundNo(player, 1);
        player.sendMessage(ChatColor.RED + "You must be near the " + ChatColor.GOLD + "Vendor " + ChatColor.RED + "or purchase the " + ChatColor.BOLD + "Conquerer " + ChatColor.RED + "from our Store!");
        return false;
    }

}

enum ArmorList {
    HELMET1("PRISONER_HAT", 150),
    HELMET2("PRISONER_HAT2", 250),
    HELMET3("PRISONER_HAT3", 500),
    HELMET4("PRISONER_HAT4", 1000),
    HELMET5("PRISONER_HAT5", 5000),
    HELMET6("PRISONER_HAT6", 10000),
    HELMET7("PRISONER_HAT7", 20000),

    CHESTPLATE1("PRISONER_CHESTPLATE", 250),
    CHESTPLATE2("PRISONER_CHESTPLATE2", 500),
    CHESTPLATE3("PRISONER_CHESTPLATE3", 1000),
    CHESTPLATE4("PRISONER_CHESTPLATE4", 2000),
    CHESTPLATE5("PRISONER_CHESTPLATE5", 10000),
    CHESTPLATE6("PRISONER_CHESTPLATE6", 20000),
    CHESTPLATE7("PRISONER_CHESTPLATE7", 100000),

    LEGGINGS1("PRISONER_LEGGINGS", 200),
    LEGGINGS2("PRISONER_LEGGINGS2", 400),
    LEGGINGS3("PRISONER_LEGGINGS3", 800),
    LEGGINGS4("PRISONER_LEGGINGS4", 1600),
    LEGGINGS5("PRISONER_LEGGINGS5", 8000),
    LEGGINGS6("PRISONER_LEGGINGS6", 16000),
    LEGGINGS7("PRISONER_LEGGINGS7", 1500000),

    SHOES1("PRISONER_SHOES", 100),
    SHOES2("PRISONER_SHOES2", 200),
    SHOES3("PRISONER_SHOES3", 400),
    SHOES4("PRISONER_SHOES4", 800),
    SHOES5("PRISONER_SHOES5", 4000),
    SHOES6("PRISONER_SHOES6", 8000),
    SHOES7("PRISONER_SHOES7", 125000),

    HELMET8("PRISONER_HAT8", 200000),
    CHESTPLATE8("PRISONER_CHESTPLATE8", 300000),
    LEGGINGS8("PRISONER_LEGGINGS8", 250000),
    SHOES8("PRISONER_SHOES8", 175000),
    HELMET9("PRISONER_HAT9", 1750000),
    CHESTPLATE9("PRISONER_CHESTPLATE9", 2500000),
    LEGGINGS9("PRISONER_LEGGINGS9", 2000000),
    SHOES9("PRISONER_SHOES9", 1500000),

    ;
    private String id;
    private int price;

    ArmorList(String id, int price) {
        this.id = id;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

}
