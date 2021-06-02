package monzter.adventurescraft.plugin.network.PrisonGamemode.shared.GUIs.mainMenu;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Dependency;
import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
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
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Leaderboards extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final SoundManager soundManager;
    private final GUIHelper guiHelper;
    private final ConsoleCommand consoleCommand;

    public Leaderboards(AdventuresCraft plugin, SoundManager soundManager, GUIHelper guiHelper, ConsoleCommand consoleCommand) {
        this.plugin = plugin;
        this.soundManager = soundManager;
        this.guiHelper = guiHelper;
        this.consoleCommand = consoleCommand;
    }

    @CommandAlias("leaderboards|leaderboard")
    public void leaderboards(Player player) {

        ChestGui gui = new ChestGui(5, guiHelper.guiName("Leaderboards"));
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        OutlinePane background = new OutlinePane(0, 0, 9, 5, Pane.Priority.LOWEST);
        StaticPane display = new StaticPane(0, 0, 9, 5, Pane.Priority.LOW);

        int i = 0;
        for (LeaderboardCategories item : LeaderboardCategories.values()) {
            ItemStack leaderboards = item.getItemStack();
            final ItemMeta leaderboardsItemMeta = leaderboards.getItemMeta();

            leaderboardsItemMeta.setDisplayName(item.getName() + ": " + ChatColor.GOLD + "Leaderboards");

            List<String> lore = new ArrayList<>();
            lore.add("");
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Left-Click to View All-Time");
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Right-Click to View Daily");
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Left-Shift-Click to View Weekly");
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Right-Shift-Click to View Monthly");

            leaderboards.setItemMeta(leaderboardsItemMeta);
            leaderboards.setLore(lore);

            switch (i) {
                case 0:
                    display.addItem(new GuiItem(leaderboards, e -> action(e.getClick(), player, item.getId())), 2, 1);
                    break;
                case 1:
                    display.addItem(new GuiItem(leaderboards, e -> action(e.getClick(), player, item.getId())), 3, 1);
                    break;
                case 2:
                    display.addItem(new GuiItem(leaderboards, e -> action(e.getClick(), player, item.getId())), 4, 1);
                    break;
                case 3:
                    display.addItem(new GuiItem(leaderboards, e -> action(e.getClick(), player, item.getId())), 5, 1);
                    break;
                case 4:
                    display.addItem(new GuiItem(leaderboards, e -> action(e.getClick(), player, item.getId())), 6, 1);
                    break;
                case 5:
                    display.addItem(new GuiItem(leaderboards, e -> action(e.getClick(), player, item.getId())), 4, 2);
                    break;
            }
            i++;
        }

        background.addItem(new GuiItem(guiHelper.background(Material.YELLOW_STAINED_GLASS_PANE)));
        background.setRepeat(true);

        display.addItem(new GuiItem(guiHelper.backButton(), e -> player.performCommand("main")), 4, 4);

        gui.addPane(background);
        gui.addPane(display);
        gui.show(player);
    }

    private void action(ClickType clickType, Player player, String leaderboard) {
        switch (clickType){
            case LEFT:
                player.chat("/top" + leaderboard);
                break;
            case RIGHT:
                player.chat("/top" + leaderboard + "daily");
                break;
            case SHIFT_LEFT:
                player.chat("/top" + leaderboard + "weekly");
                break;
            case SHIFT_RIGHT:
                player.chat("/top" + leaderboard + "monthly");
                break;
        }
    }
}

