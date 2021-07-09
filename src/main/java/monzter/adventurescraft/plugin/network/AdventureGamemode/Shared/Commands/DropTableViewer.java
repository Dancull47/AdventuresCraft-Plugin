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
public class DropTableViewer extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final GUIHelper guiHelper;
    private final DropTablesDelivery dropTablesDelivery;
    private final MMOItems mmoItems;


    public DropTableViewer(AdventuresCraft plugin, GUIHelper guiHelper, DropTablesDelivery dropTablesDelivery, MMOItems mmoItems) {
        this.plugin = plugin;
        this.guiHelper = guiHelper;
        this.dropTablesDelivery = dropTablesDelivery;
        this.mmoItems = mmoItems;
    }

    int height = 6;

    @Subcommand("UndeadCrate|UNDEAD_BOX|UNDEAD_BOX5")
    private void undeadCrate(Player player) {
        height = 6;
        final ChestGui gui = new ChestGui(height, guiHelper.guiName("Undead Crate"));
        final List<Crates> guiContents = Crates.getCrates(CrateList.UNDEAD);
        createMenu(player, gui, guiContents, Material.GREEN_STAINED_GLASS_PANE, height, 1, false);
        gui.show(player);
    }

    @Subcommand("HellCrate|HELL_BOX|HELL_BOX5")
    private void hellCrate(Player player) {
        height = 6;
        final ChestGui gui = new ChestGui(height, guiHelper.guiName("Hell Crate"));
        final List<Crates> guiContents = Crates.getCrates(CrateList.HELL);
        createMenu(player, gui, guiContents, Material.RED_STAINED_GLASS_PANE, height, 1, false);
        gui.show(player);
    }

    @Subcommand("ProfessionCrate|PROFESSION_BOOSTER_BOX|PROFESSION_BOOSTER_BOX5")
    private void professionCrate(Player player) {
        height = 6;
        final ChestGui gui = new ChestGui(height, guiHelper.guiName("Profession Crate"));
        final List<Crates> guiContents = Crates.getCrates(CrateList.PROFESSION);
        createMenu(player, gui, guiContents, Material.CYAN_STAINED_GLASS_PANE, height, 1, false);
        gui.show(player);
    }

    @Subcommand("MagicalCrate|MAGICAL_BOX")
    private void magicalBox(Player player) {
        height = 6;
        final ChestGui gui = new ChestGui(height, guiHelper.guiName("Magical Box"));
        final List<Crates> guiContents = Crates.getCrates(CrateList.MAGICAL);
        createMenu(player, gui, guiContents, Material.PURPLE_STAINED_GLASS_PANE, height, 1, false);
        gui.show(player);
    }

    @Subcommand("BorgCrate|BORGS_BOX")
    private void borgBox(Player player) {
        height = 5;
        final ChestGui gui = new ChestGui(height, guiHelper.guiName("Borg's Box"));
        final List<Crates> guiContents = Crates.getCrates(CrateList.BORG);
        createMenu(player, gui, guiContents, Material.RED_STAINED_GLASS_PANE, height, 1, false);
        gui.show(player);
    }

    @Subcommand("ENCHANTED_BOX")
    private void enchantedBox(Player player) {
        height = 3;
        final ChestGui gui = new ChestGui(height, guiHelper.guiName("Enchanted Box"));
        final List<Crates> guiContents = Crates.getCrates(CrateList.ENCHANTED_BOX);
        createMenu(player, gui, guiContents, Material.PURPLE_STAINED_GLASS_PANE, height, 2, false);
        gui.show(player);
    }

    @Subcommand("ENCHANTED_BOX2")
    private void enchantedBox2(Player player) {
        height = 3;
        final ChestGui gui = new ChestGui(height, guiHelper.guiName("Enchanted Box"));
        final List<Crates> guiContents = Crates.getCrates(CrateList.ENCHANTED_BOX2);
        createMenu(player, gui, guiContents, Material.PURPLE_STAINED_GLASS_PANE, height, 2, false);
        gui.show(player);
    }

    @Subcommand("ENCHANTED_BOX3")
    private void enchantedBox3(Player player) {
        height = 3;
        final ChestGui gui = new ChestGui(height, guiHelper.guiName("Enchanted Box"));
        final List<Crates> guiContents = Crates.getCrates(CrateList.ENCHANTED_BOX3);
        createMenu(player, gui, guiContents, Material.PURPLE_STAINED_GLASS_PANE, height, 3, false);
        gui.show(player);
    }

    @Subcommand("Reaper")
    private void reaper(Player player) {
        height = 4;
        final ChestGui gui = new ChestGui(height, guiHelper.guiName("Reaper"));
        final List<Crates> guiContents = Crates.getCrates(CrateList.REAPER);
        createMenu(player, gui, guiContents, Material.RED_STAINED_GLASS_PANE, height, 2, true);
        gui.show(player);
    }

    @Subcommand("Morden|MordenTheUndead")
    private void morden(Player player) {
        height = 5;
        final ChestGui gui = new ChestGui(height, guiHelper.guiName("Morden"));
        final List<Crates> guiContents = Crates.getCrates(CrateList.MORDEN);
        createMenu(player, gui, guiContents, Material.RED_STAINED_GLASS_PANE, height, 1, true);
        gui.show(player);
    }

    @Subcommand("Dracula|VoidDracula")
    private void voidDracula(Player player) {
        height = 5;
        final ChestGui gui = new ChestGui(height, guiHelper.guiName("Dracula"));
        final List<Crates> guiContents = Crates.getCrates(CrateList.VOID_DRACULA);
        createMenu(player, gui, guiContents, Material.RED_STAINED_GLASS_PANE, height, 1, true);
        gui.show(player);
    }

    @Subcommand("Dryad")
    private void dryad(Player player) {
        height = 4;
        final ChestGui gui = new ChestGui(height, guiHelper.guiName("Dryad"));
        final List<Crates> guiContents = Crates.getCrates(CrateList.DRYAD);
        createMenu(player, gui, guiContents, Material.RED_STAINED_GLASS_PANE, height, 1, true);
        gui.show(player);
    }

    @Subcommand("GoblinChief")
    private void goblinChief(Player player) {
        height = 4;
        final ChestGui gui = new ChestGui(height, guiHelper.guiName("Goblin Chief"));
        final List<Crates> guiContents = Crates.getCrates(CrateList.GOBLIN_CHIEF);
        createMenu(player, gui, guiContents, Material.RED_STAINED_GLASS_PANE, height, 2, true);
        gui.show(player);
    }

    @Subcommand("VoidWither")
    private void voidWither(Player player) {
        height = 5;
        final ChestGui gui = new ChestGui(height, guiHelper.guiName("Void Wither"));
        final List<Crates> guiContents = Crates.getCrates(CrateList.VOID_WITHER);
        createMenu(player, gui, guiContents, Material.RED_STAINED_GLASS_PANE, height, 1, true);
        gui.show(player);
    }

    @Subcommand("VoidMagma")
    private void voidMagma(Player player) {
        height = 4;
        final ChestGui gui = new ChestGui(height, guiHelper.guiName("Void Magma"));
        final List<Crates> guiContents = Crates.getCrates(CrateList.VOID_MAGMA);
        createMenu(player, gui, guiContents, Material.RED_STAINED_GLASS_PANE, height, 2, true);
        gui.show(player);
    }

    @Subcommand("Ghastly")
    private void ghastly(Player player) {
        height = 4;
        final ChestGui gui = new ChestGui(height, guiHelper.guiName("Ghastly"));
        final List<Crates> guiContents = Crates.getCrates(CrateList.GHASTLY);
        createMenu(player, gui, guiContents, Material.RED_STAINED_GLASS_PANE, height, 1, true);
        gui.show(player);
    }

    @Subcommand("Bulblin|VoidBulblin")
    private void bulblin(Player player) {
        height = 5;
        final ChestGui gui = new ChestGui(height, guiHelper.guiName("Bulblin"));
        final List<Crates> guiContents = Crates.getCrates(CrateList.BULBLIN);
        createMenu(player, gui, guiContents, Material.RED_STAINED_GLASS_PANE, height, 1, true);
        gui.show(player);
    }

    @Subcommand("Bullbo|VoidBullbo")
    private void bullbo(Player player) {
        height = 5;
        final ChestGui gui = new ChestGui(height, guiHelper.guiName("Bullbo"));
        final List<Crates> guiContents = Crates.getCrates(CrateList.BULLBO);
        createMenu(player, gui, guiContents, Material.RED_STAINED_GLASS_PANE, height, 1, true);
        gui.show(player);
    }

    @Subcommand("Enchantress|VoidEnchantress")
    private void enchantress(Player player) {
        height = 5;
        final ChestGui gui = new ChestGui(height, guiHelper.guiName("Enchantress"));
        final List<Crates> guiContents = Crates.getCrates(CrateList.ENCHANTRESS);
        createMenu(player, gui, guiContents, Material.RED_STAINED_GLASS_PANE, height, 1, true);
        gui.show(player);
    }

    private void createMenu(Player player, ChestGui gui, Collection<? extends ItemGenerator> guiContents, Material backgroundColor) {
        createMenu(player, gui, guiContents, backgroundColor, 6, 1);
    }

    private void createMenu(Player player, ChestGui gui, Collection<? extends ItemGenerator> guiContents, Material backgroundColor, int height, int startX) {
        createMenu(player, gui, guiContents, backgroundColor, 6, 1, true);
    }

    //  BROKEN
    private void createMenu(Player player, ChestGui gui, Collection<? extends ItemGenerator> guiContents, Material backgroundColor, int height, int startX, boolean bossdex) {
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        PaginatedPane page = new PaginatedPane(0, 0, 9, height);
        OutlinePane background = new OutlinePane(0, 0, 9, height, Pane.Priority.LOWEST);
        OutlinePane display = new OutlinePane(startX, 1, 7, 4, Pane.Priority.LOW);
        OutlinePane display2 = new OutlinePane(startX, 1, 7, 4, Pane.Priority.LOW);
        StaticPane back = new StaticPane(0, height - 1, 1, 1, Pane.Priority.HIGH);
        StaticPane forward = new StaticPane(8, height - 1, 1, 1, Pane.Priority.HIGH);
        StaticPane backButton = new StaticPane(4, height - 1, 1, 1, Pane.Priority.HIGHEST);

        page.addPane(0, background);
        page.addPane(0, display);
        page.addPane(0, backButton);
        page.addPane(1, background);
        page.addPane(1, display2);
        page.addPane(1, backButton);

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

        if (bossdex)
            backButton.addItem(new GuiItem(guiHelper.backButton(), e -> player.performCommand("bossdex")), 0, 0);

        gui.addPane(page);
        gui.addPane(back);
        gui.addPane(forward);
    }
}

