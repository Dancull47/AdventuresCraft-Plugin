package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.npcs.TownHallDisplay;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Dependency;
import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.GUI.GUIHelper;
import monzter.adventurescraft.plugin.utilities.general.ConsoleCommand;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import net.Indyuce.mmoitems.MMOItems;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class Farmer extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final SoundManager soundManager;
    private final GUIHelper guiHelper;
    private final ConsoleCommand consoleCommand;
    private final MMOItems mmoItems;


    public Farmer(AdventuresCraft plugin, SoundManager soundManager, GUIHelper guiHelper, ConsoleCommand consoleCommand, MMOItems mmoItems) {
        this.plugin = plugin;
        this.soundManager = soundManager;
        this.guiHelper = guiHelper;
        this.consoleCommand = consoleCommand;
        this.mmoItems = mmoItems;
    }

    @CommandAlias("FarmerDisplay")
    @CommandPermission("*")
    public void mainMenu(Player player) {

        ChestGui gui = new ChestGui(6, guiHelper.guiName("Farmer"));
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        OutlinePane background = new OutlinePane(0, 0, 9, 6, Pane.Priority.LOWEST);
        StaticPane display = new StaticPane(0, 0, 9, 6, Pane.Priority.LOW);


        background.addItem(new GuiItem(guiHelper.background(Material.GREEN_STAINED_GLASS_PANE)));
        background.setRepeat(true);

        display.addItem(new GuiItem(mmoItems.getItem("ARMOR", "FARMER_HAT")), 4, 1);
        display.addItem(new GuiItem(mmoItems.getItem("ARMOR", "FARMER_SHIRT")), 4, 2);
        display.addItem(new GuiItem(mmoItems.getItem("MUSKET", "FARMER_RIFLE")), 5, 2);
        display.addItem(new GuiItem(mmoItems.getItem("ARMOR", "FARMER_PANTS")), 4, 3);
        display.addItem(new GuiItem(mmoItems.getItem("ARMOR", "FARMER_SHOES")), 4, 4);

        gui.addPane(background);
        gui.addPane(display);
        gui.show(player);
    }
}

