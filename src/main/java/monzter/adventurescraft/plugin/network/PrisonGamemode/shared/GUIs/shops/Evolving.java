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
import monzter.adventurescraft.plugin.utilities.general.ItemAdder;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import monzter.adventurescraft.plugin.utilities.mmoitems.MMOItemsGive;
import monzter.adventurescraft.plugin.utilities.text.NumberFormat;
import monzter.adventurescraft.plugin.utilities.vault.Economy;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Evolving extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final SoundManager soundManager;
    private final GUIHelper guiHelper;
    private final ConsoleCommand consoleCommand;
    private final Economy economy;
    private final FullInventory fullInventory;
    private final MMOItemsGive mmoItemsGive;
    private final NumberFormat numberFormat;
    private final ItemAdder itemAdder;


    public Evolving(AdventuresCraft plugin, SoundManager soundManager, GUIHelper guiHelper, ConsoleCommand consoleCommand, Economy economy, FullInventory fullInventory, MMOItemsGive mmoItemsGive, NumberFormat numberFormat, ItemAdder itemAdder) {
        this.plugin = plugin;
        this.soundManager = soundManager;
        this.guiHelper = guiHelper;
        this.consoleCommand = consoleCommand;
        this.economy = economy;
        this.fullInventory = fullInventory;
        this.mmoItemsGive = mmoItemsGive;
        this.numberFormat = numberFormat;
        this.itemAdder = itemAdder;
    }

    @CommandAlias("evolve")
    public void pets(Player player) {
        if (player.hasPermission("SHOPS")) {

            ChestGui gui = new ChestGui(6, guiHelper.guiName("Evolve Shop"));

            gui.setOnTopClick(event -> {
                plugin.getLogger().info(event.getSlot() + "e");
                if (event.getSlot() != 12 && event.getSlot() != 13 && event.getSlot() != 14)
                    event.setCancelled(true);
            });

            PaginatedPane page = new PaginatedPane(0, 0, 9, 6);
            OutlinePane background = new OutlinePane(0, 0, 9, 6, Pane.Priority.LOWEST);
            StaticPane air = new StaticPane(3, 1, 3, 1, Pane.Priority.LOWEST);
            StaticPane display = new StaticPane(0, 0, 9, 5, Pane.Priority.LOW);

            page.addPane(0, background);
            page.addPane(0, air);
            page.addPane(0, display);

            background.addItem(new GuiItem(guiHelper.background(Material.PINK_STAINED_GLASS_PANE)));
            background.setRepeat(true);

//                display.addItem(new GuiItem(statTracker(exp, calculateEnchantments.calculateEnchantments(player, "Stat Tracker") + 1), e -> {
//                    player.performCommand("enchantment Stat_Tracker");
//                    pets(player);
//                }), 3, 2);

            air.addItem(new GuiItem(new ItemStack(Material.AIR)), 0, 0);
            air.addItem(new GuiItem(new ItemStack(Material.AIR)), 1, 0);
            air.addItem(new GuiItem(new ItemStack(Material.AIR)), 2, 0);

            display.addItem(new GuiItem(evolveButtonInactive(player), e -> player.sendMessage("Inactive")), 4, 3);

            gui.setOnTopClick(event -> {
//                if (!event.getInventory().getItem(12).equals(new ItemStack(Material.AIR)))
                if (event.getSlot() == 12) {
                    display.addItem(new GuiItem(evolveButtonActive(player), e -> player.sendMessage("Test")), 4, 3);
                    plugin.getLogger().info("True");
                    air.addItem(new GuiItem(event.getInventory().getItem(12)), 0, 0);
                    air.addItem(new GuiItem(event.getInventory().getItem(13)), 1, 0);
                    air.addItem(new GuiItem(event.getInventory().getItem(14)), 2, 0);
                    gui.update();
                }
            });

            gui.setOnClose(e -> {
                if (!e.getInventory().getItem(12).equals(new ItemStack(Material.AIR)))
                    itemAdder.itemAdder(player, e.getInventory().getItem(12));
                if (!e.getInventory().getItem(13).equals(new ItemStack(Material.AIR)))
                    itemAdder.itemAdder(player, e.getInventory().getItem(13));
                if (!e.getInventory().getItem(14).equals(new ItemStack(Material.AIR)))
                    itemAdder.itemAdder(player, e.getInventory().getItem(14));
            });
            gui.addPane(page);
            gui.show(player);
        }
    }

    private ItemStack evolveButtonInactive(Player player) {
        ItemStack hatchingItem = new ItemStack(Material.RED_DYE);
        final ItemMeta hatchingItemItemMeta = hatchingItem.getItemMeta();

        hatchingItemItemMeta.setDisplayName(ChatColor.RED + "Evolve Pet");

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.WHITE + "Insert 3 of the same rarity");
        lore.add(ChatColor.WHITE + "Pets into the Evolver,");
        lore.add(ChatColor.WHITE + "to evolve them");
        lore.add(ChatColor.WHITE + "into a stronger Pet!");


        hatchingItemItemMeta.setLore(lore);
        hatchingItem.setItemMeta(hatchingItemItemMeta);

        return hatchingItem;
    }

    private ItemStack evolveButtonActive(Player player) {
        ItemStack hatchingItem = new ItemStack(Material.GREEN_DYE);
        final ItemMeta hatchingItemItemMeta = hatchingItem.getItemMeta();

        hatchingItemItemMeta.setDisplayName(ChatColor.RED + "Evolve Pet");

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.WHITE + "Combine your 3 Pets");
        lore.add(ChatColor.WHITE + "into a strong one!");
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Evolve");


        hatchingItemItemMeta.setLore(lore);
        hatchingItem.setItemMeta(hatchingItemItemMeta);

        return hatchingItem;
    }

}