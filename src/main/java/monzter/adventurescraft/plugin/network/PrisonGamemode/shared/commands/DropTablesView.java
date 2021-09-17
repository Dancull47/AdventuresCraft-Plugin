package monzter.adventurescraft.plugin.network.PrisonGamemode.shared.commands;

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
import monzter.adventurescraft.plugin.network.PrisonGamemode.shared.commands.dropTables.DropTableTypes;
import monzter.adventurescraft.plugin.network.PrisonGamemode.shared.commands.dropTables.DropTables;
import monzter.adventurescraft.plugin.utilities.general.ItemGenerator;
import monzter.adventurescraft.plugin.utilities.GUI.GUIHelper;
import monzter.adventurescraft.plugin.utilities.enums.WeightPrices;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
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

    @Subcommand("backpack")
    private void backpack(Player player) {
        final ChestGui gui = new ChestGui(6, guiHelper.guiName("Backpack"));
        final List<WeightPrices> guiContents = Arrays.asList(WeightPrices.values());
        createMenu(gui, guiContents, player);
        gui.show(player);
    }

    @Subcommand("CommonPetEgg")
    private void commonPetEgg(Player player) {
        final ChestGui gui = new ChestGui(6, guiHelper.guiName("Common Pet Egg"));
        final List<DropTables> guiContents = DropTables.getEggs(DropTableTypes.COMMON_PET_EGG);
        createMenu(gui, guiContents, Material.WHITE_STAINED_GLASS_PANE);
        gui.show(player);
    }

    @Subcommand("UncommonPetEgg")
    private void uncommonPetEgg(Player player) {
        final ChestGui gui = new ChestGui(6, guiHelper.guiName("Uncommon Pet Egg"));
        final List<DropTables> guiContents = DropTables.getEggs(DropTableTypes.UNCOMMON_PET_EGG);
        createMenu(gui, guiContents, Material.GREEN_STAINED_GLASS_PANE);
        gui.show(player);
    }

    @Subcommand("RarePetEgg")
    private void rarePetEgg(Player player) {
        final ChestGui gui = new ChestGui(6, guiHelper.guiName("Rare Pet Egg"));
        final List<DropTables> guiContents = DropTables.getEggs(DropTableTypes.RARE_PET_EGG);
        createMenu(gui, guiContents, Material.BLUE_STAINED_GLASS_PANE);
        gui.show(player);
    }

    @Subcommand("LegendaryPetEgg")
    private void legendaryPetEgg(Player player) {
        final ChestGui gui = new ChestGui(6, guiHelper.guiName("Legendary Pet Egg"));
        final List<DropTables> guiContents = DropTables.getEggs(DropTableTypes.LEGENDARY_PET_EGG);
        createMenu(gui, guiContents, Material.PURPLE_STAINED_GLASS_PANE);
        gui.show(player);
    }

    @Subcommand("ExoticPetEgg")
    private void exoticPetEgg(Player player) {
        final ChestGui gui = new ChestGui(6, guiHelper.guiName("Exotic Pet Egg"));
        final List<DropTables> guiContents = DropTables.getEggs(DropTableTypes.EXOTIC_PET_EGG);
        createMenu(gui, guiContents, Material.YELLOW_STAINED_GLASS_PANE);
        gui.show(player);
    }

    @Subcommand("MythicalPetEgg")
    private void mythicalPetEgg(Player player) {
        final ChestGui gui = new ChestGui(6, guiHelper.guiName("Mythical Pet Egg"));
        final List<DropTables> guiContents = DropTables.getEggs(DropTableTypes.MYTHICAL_PET_EGG);
        createMenu(gui, guiContents, Material.PINK_STAINED_GLASS_PANE);
        gui.show(player);
    }

    @Subcommand("GodlyPetEgg")
    private void godlyPetEgg(Player player) {
        final ChestGui gui = new ChestGui(6, guiHelper.guiName("Godly Pet Egg"));
        final List<DropTables> guiContents = DropTables.getEggs(DropTableTypes.GODLY_PET_EGG);
        createMenu(gui, guiContents, Material.RED_STAINED_GLASS_PANE);
        gui.show(player);
    }

    @Subcommand("PhoenixPetEgg")
    private void phoenixPetEgg(Player player) {
        final ChestGui gui = new ChestGui(6, guiHelper.guiName("Phoenix Pet Egg"));
        final List<DropTables> guiContents = DropTables.getEggs(DropTableTypes.PHOENIX_PET_EGG);
        createMenu(gui, guiContents, Material.ORANGE_STAINED_GLASS_PANE);
        gui.show(player);
    }

    @Subcommand("PhoenixPetEgg2|Phoenix2PetEgg")
    private void phoenixPetEgg2(Player player) {
        final ChestGui gui = new ChestGui(6, guiHelper.guiName("Phoenix Pet Egg"));
        final List<DropTables> guiContents = DropTables.getEggs(DropTableTypes.PHOENIX_PET_EGG2);
        createMenu(gui, guiContents, Material.ORANGE_STAINED_GLASS_PANE);
        gui.show(player);
    }

    @Subcommand("DragonPetEgg")
    private void dragonPetEgg(Player player) {
        final ChestGui gui = new ChestGui(6, guiHelper.guiName("Dragon Pet Egg"));
        final List<DropTables> guiContents = DropTables.getEggs(DropTableTypes.DRAGON_PET_EGG);
        createMenu(gui, guiContents, Material.PURPLE_STAINED_GLASS_PANE);
        gui.show(player);
    }

    @Subcommand("DragonPetEgg2|Dragon2PetEgg")
    private void dragonPetEgg2(Player player) {
        final ChestGui gui = new ChestGui(6, guiHelper.guiName("Dragon Pet Egg"));
        final List<DropTables> guiContents = DropTables.getEggs(DropTableTypes.DRAGON_PET_EGG2);
        createMenu(gui, guiContents, Material.PURPLE_STAINED_GLASS_PANE);
        gui.show(player);
    }

    @Subcommand("Lootbox")
    private void lootbox(Player player) {
        final ChestGui gui = new ChestGui(6, guiHelper.guiName("Common Lootbox"));
        final List<DropTables> guiContents = DropTables.getEggs(DropTableTypes.COMMON_LOOTBOX);
        createMenu(gui, guiContents, Material.WHITE_STAINED_GLASS_PANE);
        gui.show(player);
    }

    @Subcommand("Lootbox2")
    private void lootbox2(Player player) {
        final ChestGui gui = new ChestGui(6, guiHelper.guiName("Uncommon Lootbox"));
        final List<DropTables> guiContents = DropTables.getEggs(DropTableTypes.UNCOMMON_LOOTBOX);
        createMenu(gui, guiContents, Material.GREEN_STAINED_GLASS_PANE);
        gui.show(player);
    }

    @Subcommand("Lootbox3")
    private void lootbox3(Player player) {
        final ChestGui gui = new ChestGui(6, guiHelper.guiName("Rare Lootbox"));
        final List<DropTables> guiContents = DropTables.getEggs(DropTableTypes.RARE_LOOTBOX);
        createMenu(gui, guiContents, Material.BLUE_STAINED_GLASS_PANE);
        gui.show(player);
    }

    @Subcommand("Lootbox4")
    private void lootbox4(Player player) {
        final ChestGui gui = new ChestGui(6, guiHelper.guiName("Legendary Lootbox"));
        final List<DropTables> guiContents = DropTables.getEggs(DropTableTypes.LEGENDARY_LOOTBOX);
        createMenu(gui, guiContents, Material.PURPLE_STAINED_GLASS_PANE);
        gui.show(player);
    }

    @Subcommand("Lootbox5")
    private void lootbox5(Player player) {
        final ChestGui gui = new ChestGui(6, guiHelper.guiName("Exotic Lootbox"));
        final List<DropTables> guiContents = DropTables.getEggs(DropTableTypes.EXOTIC_LOOTBOX);
        createMenu(gui, guiContents, Material.YELLOW_STAINED_GLASS_PANE);
        gui.show(player);
    }

    @Subcommand("Lootbox6")
    private void lootbox6(Player player) {
        final ChestGui gui = new ChestGui(6, guiHelper.guiName("Mythical Lootbox"));
        final List<DropTables> guiContents = DropTables.getEggs(DropTableTypes.MYTHICAL_LOOTBOX);
        createMenu(gui, guiContents, Material.PINK_STAINED_GLASS_PANE);
        gui.show(player);
    }

    @Subcommand("Lootbox7")
    private void lootbox7(Player player) {
        final ChestGui gui = new ChestGui(6, guiHelper.guiName("Godly Lootbox"));
        final List<DropTables> guiContents = DropTables.getEggs(DropTableTypes.GODLY_LOOTBOX);
        createMenu(gui, guiContents, Material.RED_STAINED_GLASS_PANE);
        gui.show(player);
    }

    @Subcommand("Llama")
    private void llama(Player player) {
        final ChestGui gui = new ChestGui(6, guiHelper.guiName("Llama"));
        final List<DropTables> guiContents = DropTables.getEggs(DropTableTypes.LLAMA);
        createMenu(gui, guiContents, Material.PINK_STAINED_GLASS_PANE);
        gui.show(player);
    }

    private void createMenu(ChestGui gui, Collection<? extends ItemGenerator> guiContents) {
        createMenu(gui, guiContents, Material.BLACK_STAINED_GLASS_PANE);
    }

    private void createMenu(ChestGui gui, Collection<? extends ItemGenerator> guiContents, Material backgroundColor) {
        ItemStack backgroundItem = guiHelper.background(backgroundColor);
        ItemStack previousPageItem = guiHelper.previousPageButton();
        ItemStack nextPageItem = guiHelper.nextPageButton();

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

        background.addItem(new GuiItem(backgroundItem));
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
            back.addItem(new GuiItem((previousPageItem), event -> {
                page.setPage(page.getPage() - 1);
                if (page.getPage() == 0) {
                    back.setVisible(false);
                }
                forward.setVisible(true);
                gui.update();
            }), 0, 0);
            back.setVisible(false);
            forward.addItem(new GuiItem((nextPageItem), event -> {
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

    private void createMenu(ChestGui gui, Collection<? extends ItemGenerator> guiContents, Player player) {
        createMenu(gui, guiContents, player, "", Material.BLACK_STAINED_GLASS_PANE);
    }

    private void createMenu(ChestGui gui, Collection<? extends ItemGenerator> guiContents, Player player, String guiName, Material backgroundColor) {
        ItemStack backgroundItem = guiHelper.background(backgroundColor);
        ItemStack previousPageItem = guiHelper.previousPageButton();
        ItemStack nextPageItem = guiHelper.nextPageButton();

        gui.setOnGlobalClick(event -> event.setCancelled(true));

        PaginatedPane page = new PaginatedPane(0, 0, 9, 6);
        OutlinePane background = new OutlinePane(0, 0, 9, 6, Pane.Priority.LOWEST);
        OutlinePane display = new OutlinePane(1, 1, 7, 4, Pane.Priority.LOW);
        OutlinePane display2 = new OutlinePane(1, 1, 7, 4, Pane.Priority.LOW);
        StaticPane back = new StaticPane(0, 5, 1, 1, Pane.Priority.HIGH);
        StaticPane forward = new StaticPane(8, 5, 1, 1, Pane.Priority.HIGH);
        StaticPane backButton = new StaticPane(4, 5, 1, 1, Pane.Priority.HIGHEST);

        page.addPane(0, background);
        page.addPane(0, display);
        page.addPane(1, background);
        page.addPane(1, display2);

        background.addItem(new GuiItem(backgroundItem));
        background.setRepeat(true);

        int i = 0;

        for (ItemGenerator item : guiContents) {
            if (i < 28) {
                display.addItem(new GuiItem(item.generateItem(player)));
                i++;
            } else {
                display2.addItem(new GuiItem(item.generateItem(player)));
            }
        }

        if (!display2.getItems().isEmpty()) {
            back.addItem(new GuiItem((previousPageItem), event -> {
                page.setPage(page.getPage() - 1);
                if (page.getPage() == 0) {
                    back.setVisible(false);
                }
                forward.setVisible(true);
                gui.update();
            }), 0, 0);
            back.setVisible(false);
            forward.addItem(new GuiItem((nextPageItem), event -> {
                page.setPage(page.getPage() + 1);
                if (page.getPage() == page.getPages() - 1) {
                    forward.setVisible(false);
                }
                back.setVisible(true);
                gui.update();
            }), 0, 0);
        }
//        Doesnt work, but good idea
        if (guiName.contains("Pet Egg"))
            backButton.addItem(new GuiItem(guiHelper.backButton(), e -> player.performCommand("hatchingshop")), 0, 0);

        gui.addPane(page);
        gui.addPane(back);
        gui.addPane(backButton);
        gui.addPane(forward);
    }
}

