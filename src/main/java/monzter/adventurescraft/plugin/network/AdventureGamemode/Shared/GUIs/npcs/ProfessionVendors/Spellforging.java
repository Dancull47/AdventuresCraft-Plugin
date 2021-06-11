package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.npcs.ProfessionVendors;

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
import monzter.adventurescraft.plugin.utilities.enums.Prefix;
import monzter.adventurescraft.plugin.utilities.general.ConsoleCommand;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class Spellforging extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final SoundManager soundManager;
    private final GUIHelper guiHelper;
    private final ConsoleCommand consoleCommand;

    public Spellforging(AdventuresCraft plugin, SoundManager soundManager, GUIHelper guiHelper, ConsoleCommand consoleCommand) {
        this.plugin = plugin;
        this.soundManager = soundManager;
        this.guiHelper = guiHelper;
        this.consoleCommand = consoleCommand;
    }

    @CommandAlias("SpellforgingShop")
    @CommandPermission("*")
    public void mainMenu(Player player) {

        ChestGui gui = new ChestGui(3, guiHelper.guiName("Spellforging"));
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        OutlinePane background = new OutlinePane(0, 0, 9, 3, Pane.Priority.LOWEST);
        StaticPane display = new StaticPane(0, 0, 9, 3, Pane.Priority.LOW);


        background.addItem(new GuiItem(guiHelper.background(Material.LIGHT_BLUE_STAINED_GLASS_PANE)));
        background.setRepeat(true);

        display.addItem(new GuiItem(guiHelper.itemCreator(Material.PAPER,
                ChatColor.GREEN + "Spell Shop", new String[]{"", Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View"}),
                e -> consoleCommand.consoleCommand("mi stations open spells " + player.getName())), 2, 1);
        display.addItem(new GuiItem(guiHelper.itemCreator(Material.WHEAT,
                ChatColor.GREEN + "Material Shop", new String[]{"", Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View"}),
                e -> consoleCommand.consoleCommand("mi stations open enchanted-materials " + player.getName())), 4, 1);
        display.addItem(new GuiItem(guiHelper.itemCreator(Material.STICK,
                ChatColor.GREEN + "Wand Shop", new String[]{"", Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View"}),
                e -> consoleCommand.consoleCommand("mi stations open wand " + player.getName())), 6, 1);

        gui.addPane(background);
        gui.addPane(display);
        gui.show(player);
    }
}

