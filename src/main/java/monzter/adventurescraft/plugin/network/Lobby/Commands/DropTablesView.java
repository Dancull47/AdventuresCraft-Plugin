package monzter.adventurescraft.plugin.network.Lobby.Commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Dependency;
import co.aikar.commands.annotation.Subcommand;
import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.PaginatedPane;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.network.PrisonGamemode.shared.commands.dropTables.ItemGenerator;
import monzter.adventurescraft.plugin.utilities.GUI.GUIHelper;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.List;

@CommandAlias("DropTableViewer")
public class DropTablesView extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final GUIHelper guiHelper;


    public DropTablesView(AdventuresCraft plugin, GUIHelper guiHelper) {
        this.plugin = plugin;
        this.guiHelper = guiHelper;
    }


    @Subcommand("trailBox")
    private void backpack(Player player) {
        final ChestGui gui = new ChestGui(6, "Trail Box");
        final List<TrailList> guiContents = TrailList.getTrail();
        createMenu(gui, guiContents);
        gui.show(player);
    }


    private void createMenu(ChestGui gui, Collection<? extends ItemGenerator> guiContents) {
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        PaginatedPane page = new PaginatedPane(0, 0, 9, 6);
        OutlinePane background = new OutlinePane(0, 0, 9, 6, Pane.Priority.LOWEST);
        OutlinePane display = new OutlinePane(2, 1, 5, 4, Pane.Priority.LOW);
        OutlinePane display2 = new OutlinePane(2, 1, 5, 4, Pane.Priority.LOW);
        OutlinePane display3 = new OutlinePane(2, 1, 5, 4, Pane.Priority.LOW);
        OutlinePane display4 = new OutlinePane(2, 1, 5, 4, Pane.Priority.LOW);
        OutlinePane display5 = new OutlinePane(2, 1, 5, 4, Pane.Priority.LOW);
        OutlinePane display6 = new OutlinePane(2, 1, 5, 4, Pane.Priority.LOW);
        OutlinePane display7 = new OutlinePane(2, 1, 5, 4, Pane.Priority.LOW);
//        OutlinePane display8 = new OutlinePane(2, 1, 5, 4, Pane.Priority.LOW);
        StaticPane back = new StaticPane(0, 5, 1, 1, Pane.Priority.HIGH);
        StaticPane forward = new StaticPane(8, 5, 1, 1, Pane.Priority.HIGH);

        page.addPane(0, background);
        page.addPane(0, display);
        page.addPane(1, background);
        page.addPane(1, display2);
        page.addPane(2, background);
        page.addPane(2, display3);
        page.addPane(3, background);
        page.addPane(3, display4);
        page.addPane(4, background);
        page.addPane(4, display5);
        page.addPane(5, background);
        page.addPane(5, display6);
        page.addPane(6, background);
        page.addPane(6, display7);
//        page.addPane(7, background);
//        page.addPane(7, display8);

        background.addItem(new GuiItem(guiHelper.background(Material.PURPLE_STAINED_GLASS_PANE)));
        background.setRepeat(true);

        int i = 0;

        for (ItemGenerator item : guiContents) {
            if (i < 20) {
                display.addItem(new GuiItem(item.generateItem()));
            } else if (i >= 20 && i < 40) {
                display2.addItem(new GuiItem(item.generateItem()));
            } else if (i >= 40 && i < 60) {
                display3.addItem(new GuiItem(item.generateItem()));
            } else if (i >= 60 && i < 80) {
                display4.addItem(new GuiItem(item.generateItem()));
            } else if (i >= 80 && i < 100) {
                display5.addItem(new GuiItem(item.generateItem()));
            } else if (i >= 100 && i < 120) {
                display6.addItem(new GuiItem(item.generateItem()));
            } else if (i >= 120 && i < 140) {
                display7.addItem(new GuiItem(item.generateItem()));
//            } else if (i >= 140 && i < 160) {
//                display8.addItem(new GuiItem(item.generateItem()));
            }
            i++;
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
    }
}

