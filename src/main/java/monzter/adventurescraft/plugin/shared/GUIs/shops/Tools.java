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
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Tools extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final SoundManager soundManager;
    private final GUIHelper guiHelper;
    private final ConsoleCommand consoleCommand;
    private final Economy economy;
    private final FullInventory fullInventory;
    private final MMOItemsGive mmoItemsGive;
    private final NumberFormat numberFormat;

    public Tools(AdventuresCraft plugin, SoundManager soundManager, GUIHelper guiHelper, ConsoleCommand consoleCommand, Economy economy, FullInventory fullInventory, MMOItemsGive mmoItemsGive, NumberFormat numberFormat) {
        this.plugin = plugin;
        this.soundManager = soundManager;
        this.guiHelper = guiHelper;
        this.consoleCommand = consoleCommand;
        this.economy = economy;
        this.fullInventory = fullInventory;
        this.mmoItemsGive = mmoItemsGive;
        this.numberFormat = numberFormat;
    }

    @CommandAlias("toolShop")
    public void pets(Player player) {
        if (player.hasPermission("SHOPS")) {
            ChestGui gui = new ChestGui(4, guiHelper.guiName("Tool Shop"));
            gui.setOnGlobalClick(event -> event.setCancelled(true));

            PaginatedPane page = new PaginatedPane(0, 0, 9, 4);
            OutlinePane background = new OutlinePane(0, 0, 9, 4, Pane.Priority.LOWEST);
            OutlinePane display = new OutlinePane(1, 1, 7, 4, Pane.Priority.LOW);

            page.addPane(0, background);
            page.addPane(0, display);

            background.addItem(new GuiItem(guiHelper.background(Material.GREEN_STAINED_GLASS_PANE)));
            background.setRepeat(true);

            for (ToolList tool : ToolList.values()) {
                ItemStack toolItem = MMOItems.plugin.getItem("TOOL", tool.getId());
                final ItemMeta toolItemItemMeta = toolItem.getItemMeta();
                if (toolItem != null) {
                    List<Component> lore = toolItem.lore();
                    if (lore == null) {
                        lore = new ArrayList<>();
                    } else if (!lore.isEmpty()) {
                        lore.add(Component.empty());
                    }
                    lore.add(Component.text(ChatColor.WHITE + "Price: " + ChatColor.YELLOW + "⛂ " + numberFormat.numberFormat(tool.getPrice())));
                    if (economy.getBalance(player) >= tool.getPrice()) {
                        lore.add(Component.text(""));
                        lore.add(Component.text(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Purchase"));
                    }
                    toolItemItemMeta.lore(lore);
                    toolItem.setItemMeta(toolItemItemMeta);
                    display.addItem(new GuiItem(toolItem, e -> purchase(player, tool.getId(), tool.getPrice())));
                }
            }
            gui.addPane(background);
            gui.addPane(display);
            gui.show(player);
        }
    }

    private void purchase(Player player, String itemID, int price) {
        if (economy.hasMoney(player, price)) {
            if (!fullInventory.fullInventory(player)) {
                economy.takeMoney(player, price);
                mmoItemsGive.giveMMOItem(player, "TOOL", itemID);
            }
        }
    }
}

enum ToolList {
    TOOL2("PRISONER_PICKAXE2", 15),
    TOOL3("PRISONER_PICKAXE3", 60),
    TOOL4("PRISONER_PICKAXE4", 240),
    TOOL5("PRISONER_PICKAXE5", 960),
    TOOL6("PRISONER_PICKAXE6", 3840),
    TOOL7("PRISONER_PICKAXE7", 15000),
    TOOL8("PRISONER_PICKAXE8", 61500),
    TOOL9("PRISONER_PICKAXE9", 246000),
    TOOL10("PRISONER_PICKAXE10", 1000000),
    TOOL11("PRISONER_PICKAXE11", 3900000),
    TOOL12("PRISONER_PICKAXE12", 15800000),
    TOOL13("PRISONER_PICKAXE13", 63000000),
    ;
    private String id;
    private int price;

    ToolList(String id, int price) {
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
