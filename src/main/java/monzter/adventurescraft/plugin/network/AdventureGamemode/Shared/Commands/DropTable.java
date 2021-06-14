package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.Commands;

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
import monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.Commands.DropTables.Crates;
import monzter.adventurescraft.plugin.network.PrisonGamemode.shared.dropTables.ItemGenerator;
import monzter.adventurescraft.plugin.utilities.GUI.GUIHelper;
import monzter.adventurescraft.plugin.utilities.enums.CrateList;
import monzter.adventurescraft.plugin.utilities.mmoitems.DropTablesDelivery;
import net.Indyuce.mmoitems.MMOItems;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.List;

@CommandAlias("DropTableViewer")
public class DropTable extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final GUIHelper guiHelper;
    private final DropTablesDelivery dropTablesDelivery;
    private final MMOItems mmoItems;


    public DropTable(AdventuresCraft plugin, GUIHelper guiHelper, DropTablesDelivery dropTablesDelivery, MMOItems mmoItems) {
        this.plugin = plugin;
        this.guiHelper = guiHelper;
        this.dropTablesDelivery = dropTablesDelivery;
        this.mmoItems = mmoItems;
    }

    @Subcommand("UndeadCrate|UNDEAD_BOX")
    private void undeadCrate(Player player) {
        final ChestGui gui = new ChestGui(6, "Undead Crate");
        final List<Crates> guiContents = Crates.getCrates(CrateList.UNDEAD);
        createMenu(gui, guiContents, Material.GREEN_STAINED_GLASS_PANE);
        gui.show(player);
    }

    @Subcommand("HellCrate|HELL_BOX")
    private void hellCrate(Player player) {
        final ChestGui gui = new ChestGui(6, "Hell Crate");
        final List<Crates> guiContents = Crates.getCrates(CrateList.HELL);
        createMenu(gui, guiContents, Material.RED_STAINED_GLASS_PANE);
        gui.show(player);
    }

    @Subcommand("ProfessionCrate|PROFESSION_BOOSTER_BOX")
    private void professionCrate(Player player) {
        final ChestGui gui = new ChestGui(6, "Profession Crate");
        final List<Crates> guiContents = Crates.getCrates(CrateList.PROFESSION);
        createMenu(gui, guiContents, Material.CYAN_STAINED_GLASS_PANE);
        gui.show(player);
    }

    private void createMenu(ChestGui gui, Collection<? extends ItemGenerator> guiContents, Material backgroundColor) {
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        PaginatedPane page = new PaginatedPane(0, 0, 9, 6);
        OutlinePane background = new OutlinePane(0, 0, 9, 6, Pane.Priority.LOWEST);
        OutlinePane display = new OutlinePane(1, 1, 7, 4, Pane.Priority.LOW);
        OutlinePane display2 = new OutlinePane(1, 1, 7, 4, Pane.Priority.LOW);
        StaticPane back = new StaticPane(0, 5, 1, 1, Pane.Priority.HIGH);
        StaticPane forward = new StaticPane(8, 5, 1, 1, Pane.Priority.HIGH);

        page.addPane(0, background);
        page.addPane(0, display);
        page.addPane(1, background);
        page.addPane(1, display2);

        background.addItem(new GuiItem(guiHelper.background(backgroundColor)));
        background.setRepeat(true);

        int i = 0;

        for (ItemGenerator item : guiContents) {
            if (i < 28) {
                display.addItem(new GuiItem(item.generateItem()));
                i++;
            } else {
                display2.addItem(new GuiItem(item.generateItem()));
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
    }
}

