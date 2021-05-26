package monzter.adventurescraft.plugin.shared.GUIs.mainMenu.map.prestigeMap;

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
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class PrestigeMap extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final SoundManager soundManager;
    private final GUIHelper guiHelper;
    private final ConsoleCommand consoleCommand;

    public PrestigeMap(AdventuresCraft plugin, SoundManager soundManager, GUIHelper guiHelper, ConsoleCommand consoleCommand) {
        this.plugin = plugin;
        this.soundManager = soundManager;
        this.guiHelper = guiHelper;
        this.consoleCommand = consoleCommand;
    }

    @CommandAlias("PrestigeMap|PrestigeMines")
    public void prestigeMap(Player player) {

        ChestGui gui = new ChestGui(6, guiHelper.guiName("Prestige Mines"));
        PaginatedPane page = new PaginatedPane(0, 0, 9, 6);
        OutlinePane background = new OutlinePane(0, 0, 9, 6, Pane.Priority.LOWEST);
        OutlinePane display = new OutlinePane(2, 1, 6, 4, Pane.Priority.LOW);
        OutlinePane display2 = new OutlinePane(2, 1, 6, 4, Pane.Priority.LOW);
        StaticPane back = new StaticPane(0, 5, 1, 1, Pane.Priority.HIGH);
        StaticPane backButton = new StaticPane(4, 5, 1, 1, Pane.Priority.HIGHEST);
        StaticPane forward = new StaticPane(8, 5, 1, 1, Pane.Priority.HIGH);

        gui.setOnGlobalClick(event -> event.setCancelled(true));

        page.addPane(0, background);
        page.addPane(0, display);
        page.addPane(1, background);
        page.addPane(1, display2);

        background.addItem(new GuiItem(guiHelper.background(Material.LIGHT_BLUE_STAINED_GLASS_PANE)));
        background.setRepeat(true);

        backButton.addItem(new GuiItem(guiHelper.backButton(), e -> player.performCommand("map")), 0, 0);

        for (Prestiges prestige : Prestiges.values()) {

            if (player.hasPermission("mines.tp.p" + prestige.getName())) {

                ItemStack prestigeIcon = new ItemStack(prestige.getItemStack());
                ItemMeta prestigeIconItemMeta = prestigeIcon.getItemMeta();

                prestigeIconItemMeta.setDisplayName(ChatColor.GREEN + "Prestige Mine " + prestige.getName());
                List<String> lore = new ArrayList<>();
                lore.add("");
                lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Travel");

                prestigeIcon.setItemMeta(prestigeIconItemMeta);
                prestigeIcon.setLore(lore);
                display.addItem(new GuiItem(prestigeIcon, e -> player.performCommand("warp " + prestige.getName())));

            } else {
                ItemStack prestigeIcon = new ItemStack(Material.RED_STAINED_GLASS_PANE);;
                ItemMeta prestigeIconItemMeta = prestigeIcon.getItemMeta();

                prestigeIconItemMeta.setDisplayName(ChatColor.GREEN + "Prestige Mine " + prestige.getName() + ChatColor.RED + ChatColor.BOLD + " Locked");
                List<String> lore = new ArrayList<>();
                lore.add("");
                lore.add(ChatColor.RED.toString() + ChatColor.BOLD + "LOCKED");
                lore.add(ChatColor.GRAY + "You must reach " + ChatColor.BLUE + "Prestige " + prestige.getName() + ChatColor.GRAY + "!");

                prestigeIcon.setItemMeta(prestigeIconItemMeta);
                prestigeIcon.setLore(lore);
                display.addItem(new GuiItem(prestigeIcon, e -> player.performCommand("warp prestige" + prestige.getName())));

            }
        }
        gui.addPane(backButton);
        gui.addPane(page);
        gui.addPane(back);
        gui.addPane(forward);
        gui.show(player);
    }

}

