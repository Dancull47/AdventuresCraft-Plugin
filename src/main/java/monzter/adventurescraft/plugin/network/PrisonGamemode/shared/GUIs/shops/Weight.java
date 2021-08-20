package monzter.adventurescraft.plugin.network.PrisonGamemode.shared.GUIs.shops;

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
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
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
        if (player.hasPermission("SHOPS") || nearEntity(player)) {

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
                    display.addItem(new GuiItem(purchased(weight)));
                } else {
                    display.addItem(new GuiItem(notPurchased(weight, economy.getBalance(player)), e -> purchase(player, weight.getWeight(), weight.getPrice(), weight.getId())));
                }
            }
            gui.addPane(background);
            gui.addPane(display);
            gui.show(player);
        }
    }

    private ItemStack purchased(WeightList weight) {
        final ItemStack weightItem = new ItemStack(Material.ENCHANTED_BOOK);
        final ItemMeta weightItemItemMeta = weightItem.getItemMeta();

        weightItemItemMeta.setDisplayName(ChatColor.GOLD + "+" + numberFormat.numberFormat(weight.getWeight()) + " Max Weight Increase " + ChatColor.YELLOW + ChatColor.BOLD + "PURCHASED");

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "Increase the amount of " + ChatColor.BLUE + "Weight");
        lore.add(ChatColor.GRAY + "you can hold by " + ChatColor.GOLD + numberFormat.numberFormat(weight.getWeight()) + ChatColor.GRAY + "!");
        lore.add("");
        lore.add(ChatColor.WHITE + "Price: " + ChatColor.YELLOW + "⛂ " + numberFormat.numberFormat(weight.getPrice()));
        lore.add("");
        lore.add(ChatColor.YELLOW + ChatColor.BOLD.toString() + "PURCHASED");
        weightItemItemMeta.setLore(lore);
        weightItem.setItemMeta(weightItemItemMeta);
        return weightItem;
    }

    private ItemStack notPurchased(WeightList weight, double money) {
        ItemStack weightItem = new ItemStack(Material.BOOK);
        final ItemMeta weightItemItemMeta = weightItem.getItemMeta();

        weightItemItemMeta.setDisplayName(ChatColor.GOLD + "+" + numberFormat.numberFormat(weight.getWeight()) + " Max Weight Increase");

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "Increase the amount of " + ChatColor.BLUE + "Weight");
        lore.add(ChatColor.GRAY + "you can hold by " + ChatColor.GOLD + numberFormat.numberFormat(weight.getWeight()) + ChatColor.GRAY + "!");
        lore.add("");
        lore.add(ChatColor.WHITE + "Price: " + ChatColor.YELLOW + "⛂ " + numberFormat.numberFormat(weight.getPrice()));
        if (money >= weight.getPrice()) {
            weightItem.setType(Material.KNOWLEDGE_BOOK);
            lore.add("");
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Purchase");
        }
        weightItemItemMeta.setLore(lore);
        weightItem.setItemMeta(weightItemItemMeta);
        return weightItem;
    }


    private void purchase(Player player, int weight, int price, int weightID) {
        if (economy.hasMoney(player, price)) {
            if (!player.hasPermission("WEIGHT.STORE.UPGRADE." + weightID + ".PURCHASED")) {
                economy.takeMoney(player, price);
                betonPointsManager.givePoint(player, "items.MaxWeight", weight);
                permissionLP.givePermission(player, "WEIGHT.STORE.UPGRADE." + weightID + ".PURCHASED");
                player.performCommand("weightShop");
            } else {
                player.sendMessage(ChatColor.RED + "You already purchased this!");
                soundManager.soundNo(player, 1);
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
