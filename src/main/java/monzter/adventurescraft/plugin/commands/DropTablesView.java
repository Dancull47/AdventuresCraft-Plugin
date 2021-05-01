package monzter.adventurescraft.plugin.commands;

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
import monzter.adventurescraft.plugin.dropTables.ItemGenerator;
import monzter.adventurescraft.plugin.dropTables.LootLlama;
import monzter.adventurescraft.plugin.dropTables.Lootbox;
import monzter.adventurescraft.plugin.dropTables.PetEgg;
import monzter.adventurescraft.plugin.event.extras.WeightPrices;
import monzter.adventurescraft.plugin.utilities.enums.Rarity;
import net.Indyuce.mmoitems.MMOItems;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@CommandAlias("DropTableViewer")
public class DropTablesView extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;

    public DropTablesView(AdventuresCraft plugin) {
        this.plugin = plugin;
    }

    private final ItemStack backgroundItem = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
    private final ItemMeta backgroundItemMeta = backgroundItem.getItemMeta();
    private final ItemStack previousPageItem = new ItemStack(Material.ARROW);
    private final ItemMeta previousPageItemMeta = previousPageItem.getItemMeta();
    private final ItemStack nextPageItem = new ItemStack(Material.ARROW);
    private final ItemMeta nextPageItemMeta = nextPageItem.getItemMeta();

    @Subcommand("backpack")
    private void backpack(Player player) {
        final ChestGui gui = new ChestGui(6, "Backpack");
        final List<WeightPrices> guiContents = Arrays.asList(WeightPrices.values());
        createMenu(gui, guiContents, player);
        gui.show(player);
    }
    @Subcommand("CommonPetEgg")
    private void commonPetEgg(Player player) {
        final ChestGui gui = new ChestGui(6, "Common Pet Egg");
        final List<PetEgg> guiContents = PetEgg.getEggs(Rarity.COMMON);
        createMenu(gui, guiContents);
        gui.show(player);
    }
    @Subcommand("UncommonPetEgg")
    private void uncommonPetEgg(Player player) {
        final ChestGui gui = new ChestGui(6, "Uncommon Pet Egg");
        final List<PetEgg> guiContents = PetEgg.getEggs(Rarity.UNCOMMON);
        createMenu(gui, guiContents);
        gui.show(player);
    }
    @Subcommand("RarePetEgg")
    private void rarePetEgg(Player player) {
        final ChestGui gui = new ChestGui(6, "Rare Pet Egg");
        final List<PetEgg> guiContents = PetEgg.getEggs(Rarity.RARE);
        createMenu(gui, guiContents);
        gui.show(player);
    }
    @Subcommand("LegendaryPetEgg")
    private void legendaryPetEgg(Player player) {
        final ChestGui gui = new ChestGui(6, "Legendary Pet Egg");
        final List<PetEgg> guiContents = PetEgg.getEggs(Rarity.LEGENDARY);
        createMenu(gui, guiContents);
        gui.show(player);
    }
    @Subcommand("ExoticPetEgg")
    private void exoticPetEgg(Player player) {
        final ChestGui gui = new ChestGui(6, "Exotic Pet Egg");
        final List<PetEgg> guiContents = PetEgg.getEggs(Rarity.EXOTIC);
        createMenu(gui, guiContents);
        gui.show(player);
    }
    @Subcommand("Lootbox")
    private void Lootbox(Player player) {
        final ChestGui gui = new ChestGui(6, "Lootbox");
        final List<Lootbox> guiContents = Lootbox.getLootbox(Rarity.COMMON);
        createMenu(gui, guiContents);
        gui.show(player);
    }

    private void createMenu(ChestGui gui, Collection<? extends ItemGenerator> guiContents) {
        backgroundItemMeta.setDisplayName(" ");
        previousPageItemMeta.setDisplayName(ChatColor.GREEN + "Previous Page");
        nextPageItemMeta.setDisplayName(ChatColor.GREEN + "Next Page");
        backgroundItem.setItemMeta(backgroundItemMeta);
        previousPageItem.setItemMeta(previousPageItemMeta);
        nextPageItem.setItemMeta(nextPageItemMeta);

        gui.setOnGlobalClick(event -> event.setCancelled(true));

        PaginatedPane page = new PaginatedPane(0, 0, 9, 6);
        OutlinePane background = new OutlinePane(0, 0, 9, 6, Pane.Priority.LOWEST);
        OutlinePane display = new OutlinePane(1, 1, 7, 4, Pane.Priority.LOW);
        OutlinePane display2 = new OutlinePane(1, 1, 7, 4, Pane.Priority.LOW);
        StaticPane pageSelection = new StaticPane(0, 0, 9, 6);
        StaticPane back = new StaticPane(0, 5, 1, 1, Pane.Priority.HIGH);
        StaticPane forward = new StaticPane(8, 5, 1, 1, Pane.Priority.HIGH);

        page.addPane(0, background);
        page.addPane(0, display);
        page.addPane(1, background);
        page.addPane(1, display2);


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


        background.addItem(new GuiItem(backgroundItem));
        background.setRepeat(true);

        pageSelection.addItem(new GuiItem(new ItemStack(Material.ARROW)), 0, 6);

        int i = 0;

        for (ItemGenerator item : guiContents) {
            if (i < 28) {
                display.addItem(new GuiItem(item.generateItem()));
                i++;
            } else {
                display2.addItem(new GuiItem(item.generateItem()));
            }
        }
        gui.addPane(page);
        gui.addPane(back);
        gui.addPane(forward);
    }
    private void createMenu(ChestGui gui, Collection<? extends ItemGenerator> guiContents, Player player) {
        backgroundItemMeta.setDisplayName(" ");
        previousPageItemMeta.setDisplayName(ChatColor.GREEN + "Previous Page");
        nextPageItemMeta.setDisplayName(ChatColor.GREEN + "Next Page");
        backgroundItem.setItemMeta(backgroundItemMeta);
        previousPageItem.setItemMeta(previousPageItemMeta);
        nextPageItem.setItemMeta(nextPageItemMeta);

        gui.setOnGlobalClick(event -> event.setCancelled(true));

        PaginatedPane page = new PaginatedPane(0, 0, 9, 6);
        OutlinePane background = new OutlinePane(0, 0, 9, 6, Pane.Priority.LOWEST);
        OutlinePane display = new OutlinePane(1, 1, 7, 4, Pane.Priority.LOW);
        OutlinePane display2 = new OutlinePane(1, 1, 7, 4, Pane.Priority.LOW);
        StaticPane pageSelection = new StaticPane(0, 0, 9, 6);
        StaticPane back = new StaticPane(0, 5, 1, 1, Pane.Priority.HIGH);
        StaticPane forward = new StaticPane(8, 5, 1, 1, Pane.Priority.HIGH);

        page.addPane(0, background);
        page.addPane(0, display);
        page.addPane(1, background);
        page.addPane(1, display2);


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


        background.addItem(new GuiItem(backgroundItem));
        background.setRepeat(true);

        pageSelection.addItem(new GuiItem(new ItemStack(Material.ARROW)), 0, 6);

        int i = 0;

        for (ItemGenerator item : guiContents) {
            if (i < 28) {
                display.addItem(new GuiItem(item.generateItem(player)));
                i++;
            } else {
                display2.addItem(new GuiItem(item.generateItem(player)));
            }
        }
        gui.addPane(page);
        gui.addPane(back);
        gui.addPane(forward);
    }

    @Subcommand("Llama")
    private void llama(Player player) {
        backgroundItemMeta.setDisplayName(" ");
        previousPageItemMeta.setDisplayName(ChatColor.GREEN + "Previous Page");
        nextPageItemMeta.setDisplayName(ChatColor.GREEN + "Next Page");
        backgroundItem.setItemMeta(backgroundItemMeta);

        ChestGui gui = new ChestGui(6, "Loot Llama Loot Table");
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        PaginatedPane page = new PaginatedPane(0, 0, 9, 6);
        OutlinePane background = new OutlinePane(0, 0, 9, 6, Pane.Priority.LOWEST);
        OutlinePane display = new OutlinePane(1, 1, 7, 4, Pane.Priority.LOW);

        page.addPane(0, background);
        page.addPane(0, display);

        background.addItem(new GuiItem(backgroundItem));
        background.setRepeat(true);

        for (LootLlama item : LootLlama.values()) {
            final ItemStack itemStack = MMOItems.plugin.getItem(item.type, item.id);
            List<String> lore = itemStack.getLore();
            lore.add("");
            lore.add(ChatColor.GOLD.toString() + ChatColor.BOLD + "CHANCE: " + item.weight * 10 + "%");
            itemStack.setLore(lore);
            display.addItem(new GuiItem(itemStack));
        }
        gui.addPane(page);
        gui.show(player);
    }

}

