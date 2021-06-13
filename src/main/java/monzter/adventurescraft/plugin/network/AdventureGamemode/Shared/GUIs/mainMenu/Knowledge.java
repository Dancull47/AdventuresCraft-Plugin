package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.mainMenu;

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
import monzter.adventurescraft.plugin.utilities.general.ShopOpener;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Knowledge extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final SoundManager soundManager;
    private final GUIHelper guiHelper;
    private final ShopOpener shopOpener;
    private final ConsoleCommand consoleCommand;


    public Knowledge(AdventuresCraft plugin, SoundManager soundManager, GUIHelper guiHelper, ShopOpener shopOpener, ConsoleCommand consoleCommand) {
        this.plugin = plugin;
        this.soundManager = soundManager;
        this.guiHelper = guiHelper;
        this.shopOpener = shopOpener;
        this.consoleCommand = consoleCommand;
    }

    @CommandAlias("knowledge")
    public void knowledge(Player player) {

        ChestGui gui = new ChestGui(5, guiHelper.guiName("Knowledge"));
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        OutlinePane background = new OutlinePane(0, 0, 9, 5, Pane.Priority.LOWEST);
        StaticPane display = new StaticPane(0, 0, 9, 5, Pane.Priority.LOW);

        background.addItem(new GuiItem(guiHelper.background(Material.BLUE_STAINED_GLASS_PANE)));
        background.setRepeat(true);

        display.addItem(new GuiItem(farming(player), e -> shopOpener.shopOpener(player, "FarmerShop")), 2, 1);
        display.addItem(new GuiItem(foraging(player), e -> shopOpener.shopOpener(player, "ForagerShop")), 3, 1);
        display.addItem(new GuiItem(mining(player), e -> consoleCommand.consoleCommand("mi stations open enchanted-materials " + player.getName())), 4, 1);
        display.addItem(new GuiItem(slayer(player), e -> shopOpener.shopOpener(player, "SlayerShop")), 5, 1);
        display.addItem(new GuiItem(enchanting(player), e -> shopOpener.shopOpener(player, "EnchanterShop")), 6, 1);

        display.addItem(new GuiItem(cooking(player), e -> shopOpener.shopOpener(player, "BakerShop")), 3, 2);
        display.addItem(new GuiItem(materials(player), e -> consoleCommand.consoleCommand("mi stations open enchanted-materials " + player.getName())), 4, 2);
        display.addItem(new GuiItem(spellforging(player), e -> shopOpener.shopOpener(player, "SpellforgingShop")), 5, 2);

        display.addItem(new GuiItem(guiHelper.backButton(), e -> player.performCommand("main")), 4, 4);

        gui.addPane(background);
        gui.addPane(display);
        gui.show(player);
    }

    private ItemStack farming(Player player) {
        return guiHelper.itemCreator(Material.DIAMOND_HOE, ChatColor.GREEN + "Farming Knowledge", new String[]{
                "",
                Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View"});
    }

    private ItemStack foraging(Player player) {
        return guiHelper.itemCreator(Material.DIAMOND_AXE, ChatColor.GREEN + "Foraging Knowledge", new String[]{
                "",
                Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View"});
    }

    private ItemStack mining(Player player) {
        return guiHelper.itemCreator(Material.DIAMOND_PICKAXE, ChatColor.GREEN + "Mining Knowledge", new String[]{
                "",
                Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View"});
    }

    private ItemStack slayer(Player player) {
        return guiHelper.itemCreator(Material.DIAMOND_SWORD, ChatColor.GREEN + "Slayer Knowledge", new String[]{
                "",
                Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View"});
    }

    private ItemStack enchanting(Player player) {
        return guiHelper.itemCreator(Material.ENCHANTED_BOOK, ChatColor.GREEN + "Enchanting Knowledge", new String[]{
                "",
                Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View"});
    }

    private ItemStack cooking(Player player) {
        return guiHelper.itemCreator(Material.MUSHROOM_STEW, ChatColor.GREEN + "Cooking Knowledge", new String[]{
                "",
                Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View"});
    }

    private ItemStack materials(Player player) {
        return guiHelper.itemCreator(Material.BONE, ChatColor.GREEN + "Material Knowledge", new String[]{
                "",
                Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View"});
    }

    private ItemStack spellforging(Player player) {
        return guiHelper.itemCreator(Material.PAPER, ChatColor.GREEN + "Spellforging Knowledge", new String[]{
                "",
                Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View"});
    }

}

