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
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import monzter.adventurescraft.plugin.utilities.text.ProgressBar;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ResourceCollector extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final SoundManager soundManager;
    private final GUIHelper guiHelper;
    private final ProgressBar progressBar;


    public ResourceCollector(AdventuresCraft plugin, SoundManager soundManager, GUIHelper guiHelper, ProgressBar progressBar) {
        this.plugin = plugin;
        this.soundManager = soundManager;
        this.guiHelper = guiHelper;
        this.progressBar = progressBar;
    }

    @CommandAlias("resourceCollector|resources")
    public void profession(Player player) {

        ChestGui gui = new ChestGui(4, guiHelper.guiName("Resource Collector"));
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        OutlinePane background = new OutlinePane(0, 0, 9, 4, Pane.Priority.LOWEST);
        StaticPane display = new StaticPane(0, 0, 9, 4, Pane.Priority.LOW);

        background.addItem(new GuiItem(guiHelper.background(Material.PINK_STAINED_GLASS_PANE)));
        background.setRepeat(true);

        display.addItem(new GuiItem(farming(player), e -> player.performCommand("farmingResources")), 2, 1);
        display.addItem(new GuiItem(foraging(player), e -> player.performCommand("foragingResources")), 3, 1);
        display.addItem(new GuiItem(mining(player), e -> player.performCommand("miningResources")), 5, 1);
        display.addItem(new GuiItem(slayer(player), e -> player.performCommand("slayerResources")), 6, 1);


        display.addItem(new GuiItem(guiHelper.backButton(), e -> player.performCommand("main")), 4, 3);

        gui.addPane(background);
        gui.addPane(display);
        gui.show(player);
    }

    String profession;

    private ItemStack farming(Player player) {
        profession = "Farming";
        String name = ChatColor.GREEN + profession + " Resources";
        return guiHelper.itemCreator(Material.DIAMOND_HOE, name, new String[]{
                "",
                Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View"});
    }

    private ItemStack foraging(Player player) {
        profession = "Foraging";
        String name = ChatColor.GREEN + profession + " Resources";
        return guiHelper.itemCreator(Material.DIAMOND_AXE, name, new String[]{
                "",
                Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View"});
    }

    private ItemStack mining(Player player) {
        profession = "Mining";
        String name = ChatColor.GREEN + profession + " Resources";
        return guiHelper.itemCreator(Material.DIAMOND_PICKAXE, name, new String[]{
                "",
                Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View"});
    }

    private ItemStack slayer(Player player) {
        profession = "Slayer";
        String name = ChatColor.GREEN + profession + " Resources";
        return guiHelper.itemCreator(Material.DIAMOND_SWORD, name, new String[]{
                "",
                Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View"});
    }

}

