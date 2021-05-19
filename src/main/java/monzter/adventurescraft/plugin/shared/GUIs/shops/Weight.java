package monzter.adventurescraft.plugin.shared.GUIs.shops;

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
import monzter.adventurescraft.plugin.utilities.beton.BetonPointsManager;
import monzter.adventurescraft.plugin.utilities.enums.Prefix;
import monzter.adventurescraft.plugin.utilities.general.ConsoleCommand;
import monzter.adventurescraft.plugin.utilities.general.FullInventory;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import monzter.adventurescraft.plugin.utilities.luckperms.PermissionLP;
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

public class Weight extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final SoundManager soundManager;
    private final GUIHelper guiHelper;
    private final ConsoleCommand consoleCommand;
    private final Economy economy;
    private final FullInventory fullInventory;
    private final MMOItemsGive mmoItemsGive;
    private final NumberFormat numberFormat;
    private final BetonPointsManager betonPointsManager;
    private final PermissionLP permissionLP;

    public Weight(AdventuresCraft plugin, SoundManager soundManager, GUIHelper guiHelper, ConsoleCommand consoleCommand, Economy economy, FullInventory fullInventory, MMOItemsGive mmoItemsGive, NumberFormat numberFormat, BetonPointsManager betonPointsManager, PermissionLP permissionLP) {
        this.plugin = plugin;
        this.soundManager = soundManager;
        this.guiHelper = guiHelper;
        this.consoleCommand = consoleCommand;
        this.economy = economy;
        this.fullInventory = fullInventory;
        this.mmoItemsGive = mmoItemsGive;
        this.numberFormat = numberFormat;
        this.betonPointsManager = betonPointsManager;
        this.permissionLP = permissionLP;
    }


    @CommandAlias("weightShop")
    public void pets(Player player) {
        if (player.hasPermission("SHOPS")) {

            ChestGui gui = new ChestGui(4, guiHelper.guiName("Weight Shop"));
            gui.setOnGlobalClick(event -> event.setCancelled(true));

            PaginatedPane page = new PaginatedPane(0, 0, 9, 4);
            OutlinePane background = new OutlinePane(0, 0, 9, 4, Pane.Priority.LOWEST);
            OutlinePane display = new OutlinePane(1, 1, 7, 4, Pane.Priority.LOW);

            page.addPane(0, background);
            page.addPane(0, display);

            background.addItem(new GuiItem(guiHelper.background(Material.GREEN_STAINED_GLASS_PANE)));
            background.setRepeat(true);

            for (WeightList weight : WeightList.values()) {
                if (player.hasPermission("WEIGHT.STORE.UPGRADE." + weight.getId() + ".PURCHASED")) {

                    ItemStack weightItem = new ItemStack(Material.ENCHANTED_BOOK);
                    final ItemMeta weightItemItemMeta = weightItem.getItemMeta();

                    weightItemItemMeta.setDisplayName(ChatColor.GOLD + "+" + numberFormat.numberFormat(weight.getWeight()) + " Max Weight Increase " + ChatColor.YELLOW + ChatColor.BOLD + "PURCHASED");

                    List<Component> lore = new ArrayList<>();
                    lore.add(Component.text(""));
                    lore.add(Component.text(ChatColor.GRAY + "Increase the amount of " + ChatColor.BLUE + "Weight"));
                    lore.add(Component.text(ChatColor.GRAY + "you can hold by " + ChatColor.GOLD + numberFormat.numberFormat(weight.getWeight()) + ChatColor.GRAY + "!"));
                    lore.add(Component.text(""));
                    lore.add(Component.text(ChatColor.WHITE + "Price: " + ChatColor.YELLOW + "⛂ " + numberFormat.numberFormat(weight.getPrice())));
                    weightItemItemMeta.lore(lore);
                    weightItem.setItemMeta(weightItemItemMeta);
                    display.addItem(new GuiItem(weightItem));
                } else {
                    ItemStack weightItem = new ItemStack(Material.BOOK);
                    final ItemMeta weightItemItemMeta = weightItem.getItemMeta();

                    weightItemItemMeta.setDisplayName(ChatColor.GOLD + "+" + numberFormat.numberFormat(weight.getWeight()) + " Max Weight Increase");

                    List<Component> lore = new ArrayList<>();
                    lore.add(Component.text(""));
                    lore.add(Component.text(ChatColor.GRAY + "Increase the amount of " + ChatColor.BLUE + "Weight"));
                    lore.add(Component.text(ChatColor.GRAY + "you can hold by " + ChatColor.GOLD + numberFormat.numberFormat(weight.getWeight()) + ChatColor.GRAY + "!"));
                    lore.add(Component.text(""));
                    lore.add(Component.text(ChatColor.WHITE + "Price: " + ChatColor.YELLOW + "⛂ " + numberFormat.numberFormat(weight.getPrice())));
                    if (economy.getBalance(player) >= weight.getPrice()) {
                        lore.add(Component.text(""));
                        lore.add(Component.text(Prefix.PREFIX.getPrefix() + ChatColor.YELLOW + "Click to Purchase"));
                    }
                    weightItemItemMeta.lore(lore);
                    weightItem.setItemMeta(weightItemItemMeta);
                    display.addItem(new GuiItem(weightItem, e -> purchase(player, weight.getWeight(), weight.getPrice(), weight.getId())));
                }
            }
            gui.addPane(background);
            gui.addPane(display);
            gui.show(player);
        }
    }

    private void purchase(Player player, int weight, int price, int weightID) {
        if (economy.hasMoney(player, price)) {
            if (!player.hasPermission("WEIGHT.STORE.UPGRADE." + weightID + ".PURCHASED")) {
                economy.takeMoney(player, price);
                betonPointsManager.givePoint(player, "items.MaxWeight", weight);
                permissionLP.givePermission(player, "WEIGHT.STORE.UPGRADE." + weightID + ".PURCHASED");
                pets(player);
            } else {
                player.sendMessage(ChatColor.RED + "You already purchased this!");
                soundManager.soundNo(player, 1);
            }
        }
    }
}

enum WeightList {
    Weight1(1, 25, 25),
    Weight2(2, 50, 100),
    Weight3(3, 100, 250),
    Weight4(4, 250, 500),
    Weight5(5, 500, 1000),
    Weight6(6, 1000, 5000),
    Weight7(7, 1250, 15000),
    Weight8(8, 1500, 61500),
    Weight9(9, 1750, 246000),
    Weight10(10, 2000, 1000000),
    Weight11(11, 2500, 3900000),
    Weight12(12, 3000, 15800000),
    Weight13(13, 4000, 63000000),
    ;

    private int id;
    private int weight;
    private int price;

    WeightList(int id, int weight, int price) {
        this.id = id;
        this.weight = weight;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public int getWeight() {
        return weight;
    }
}
