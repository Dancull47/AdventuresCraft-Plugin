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

public class VoidEnchantress extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final SoundManager soundManager;
    private final GUIHelper guiHelper;
    private final ConsoleCommand consoleCommand;
    private final MMOItems mmoItems;


    public VoidEnchantress(AdventuresCraft plugin, SoundManager soundManager, GUIHelper guiHelper, ConsoleCommand consoleCommand, MMOItems mmoItems) {
        this.plugin = plugin;
        this.soundManager = soundManager;
        this.guiHelper = guiHelper;
        this.consoleCommand = consoleCommand;
        this.mmoItems = mmoItems;
    }

    @CommandAlias("VoidEnchantressDisplay")
    @CommandPermission("*")
    public void mainMenu(Player player) {

        ChestGui gui = new ChestGui(6, guiHelper.guiName("Void Enchantress"));
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        OutlinePane background = new OutlinePane(0, 0, 9, 6, Pane.Priority.LOWEST);
        StaticPane display = new StaticPane(0, 0, 9, 6, Pane.Priority.LOW);


        background.addItem(new GuiItem(guiHelper.background(Material.PURPLE_STAINED_GLASS_PANE)));
        background.setRepeat(true);

        display.addItem(new GuiItem(mmoItems.getItem("ARMOR", "ENCHANTRESS_HEAD5")), 4, 1);
        display.addItem(new GuiItem(mmoItems.getItem("ARMOR", "ENCHANTRESS_CHEST5")), 4, 2);
        display.addItem(new GuiItem(mmoItems.getItem("STAFF", "ENCHANTRESS_STAFF5")), 5, 2);
        display.addItem(new GuiItem(mmoItems.getItem("ARMOR", "ENCHANTRESS_LEGS5")), 4, 3);
        display.addItem(new GuiItem(mmoItems.getItem("ARMOR", "ENCHANTRESS_BOOTS5")), 4, 4);

        gui.addPane(background);
        gui.addPane(display);
        gui.show(player);
    }
}

