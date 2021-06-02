package monzter.adventurescraft.plugin.network.PrisonGamemode.shared.GUIs.mainMenu.map.rankMap;

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

public class RankMap extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final SoundManager soundManager;
    private final GUIHelper guiHelper;
    private final ConsoleCommand consoleCommand;

    public RankMap(AdventuresCraft plugin, SoundManager soundManager, GUIHelper guiHelper, ConsoleCommand consoleCommand) {
        this.plugin = plugin;
        this.soundManager = soundManager;
        this.guiHelper = guiHelper;
        this.consoleCommand = consoleCommand;
    }

    @CommandAlias("RankMap|RankMines")
    public void RankMap(Player player) {

        ChestGui gui = new ChestGui(6, guiHelper.guiName("Rank Mines"));
        PaginatedPane page = new PaginatedPane(0, 0, 9, 6);
        OutlinePane background = new OutlinePane(0, 0, 9, 6, Pane.Priority.LOWEST);
        OutlinePane display = new OutlinePane(1, 1, 7, 4, Pane.Priority.LOW);
        OutlinePane display2 = new OutlinePane(1, 1, 7, 4, Pane.Priority.LOW);
        StaticPane back = new StaticPane(0, 5, 1, 1, Pane.Priority.HIGH);
        StaticPane backButton = new StaticPane(4, 5, 1, 1, Pane.Priority.HIGHEST);
        StaticPane forward = new StaticPane(8, 5, 1, 1, Pane.Priority.HIGH);

        gui.setOnGlobalClick(event -> event.setCancelled(true));

        page.addPane(0, background);
        page.addPane(0, display);
        page.addPane(1, background);
        page.addPane(1, display2);

        background.addItem(new GuiItem(guiHelper.background(Material.LIME_STAINED_GLASS_PANE)));
        background.setRepeat(true);

        backButton.addItem(new GuiItem(guiHelper.backButton(), e -> player.performCommand("map")), 0, 0);

        for (Ranks rank : Ranks.values()) {

            if (player.hasPermission("mines.tp." + rank.getName().toLowerCase())) {

                ItemStack rankIcon = new ItemStack(rank.getItemStack());
                ItemMeta rankIconItemMeta = rankIcon.getItemMeta();

                rankIconItemMeta.setDisplayName(ChatColor.GREEN + "Mine " + rank.getName());
                List<String> lore = new ArrayList<>();
                lore.add("");
                lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Travel");

                rankIcon.setItemMeta(rankIconItemMeta);
                rankIcon.setLore(lore);
                display.addItem(new GuiItem(rankIcon, e -> player.performCommand("warp " + rank.getName())));

            } else {
                ItemStack rankIcon = new ItemStack(Material.RED_STAINED_GLASS_PANE);;
                ItemMeta rankIconItemMeta = rankIcon.getItemMeta();

                rankIconItemMeta.setDisplayName(ChatColor.GREEN + "Mine " + rank.getName() + ChatColor.RED + ChatColor.BOLD + " Locked");
                List<String> lore = new ArrayList<>();
                lore.add("");
                lore.add(ChatColor.RED.toString() + ChatColor.BOLD + "LOCKED");
                lore.add(ChatColor.GRAY + "You must reach " + ChatColor.GREEN + "Rank " + rank.getName() + ChatColor.GRAY + "!");

                rankIcon.setItemMeta(rankIconItemMeta);
                rankIcon.setLore(lore);
                display.addItem(new GuiItem(rankIcon, e -> player.performCommand("warp " + rank.getName())));

            }
        }
        gui.addPane(backButton);
        gui.addPane(page);
        gui.addPane(back);
        gui.addPane(forward);
        gui.show(player);
    }

}

