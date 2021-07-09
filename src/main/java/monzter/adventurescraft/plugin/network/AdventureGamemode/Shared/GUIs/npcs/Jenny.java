package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.npcs;

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

public class Jenny extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final SoundManager soundManager;
    private final GUIHelper guiHelper;
    private final ConsoleCommand consoleCommand;

    public Jenny(AdventuresCraft plugin, SoundManager soundManager, GUIHelper guiHelper, ConsoleCommand consoleCommand) {
        this.plugin = plugin;
        this.soundManager = soundManager;
        this.guiHelper = guiHelper;
        this.consoleCommand = consoleCommand;
    }

    @CommandAlias("Jenny")
    @CommandPermission("*")
    public void mainMenu(Player player) {

        ChestGui gui = new ChestGui(3, guiHelper.guiName("Jenny"));
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        OutlinePane background = new OutlinePane(0, 0, 9, 3, Pane.Priority.LOWEST);
        StaticPane display = new StaticPane(0, 0, 9, 3, Pane.Priority.LOW);


        background.addItem(new GuiItem(guiHelper.background(Material.PINK_STAINED_GLASS_PANE)));
        background.setRepeat(true);

        display.addItem(new GuiItem(guiHelper.itemCreator("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTI3MTgwOWJhOTFiNDJmYjQ4NzVhZjRiYTI5OGU1ZTU1ZjQ1ZWQ3MzcyMWJjZWE4NWE0NWRiOTI2Mjg1NzRmIn19fQ==",
                ChatColor.GREEN + "Home", new String[]{"", ChatColor.GRAY + "Visit your home where you", ChatColor.GRAY + "can store your extra materials", ChatColor.GRAY + "and hangout with " + ChatColor.GREEN + "Freinds" + ChatColor.GRAY + "!",
                        "", Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Travel"}),
                e -> player.performCommand("home")), 4, 1);
//        display.addItem(new GuiItem(guiHelper.itemCreator("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmUzZjUwYmE2MmNiZGEzZWNmNTQ3OWI2MmZlZGViZDYxZDc2NTg5NzcxY2MxOTI4NmJmMjc0NWNkNzFlNDdjNiJ9fX0=",
//                ChatColor.GREEN + "Home Guide", new String[]{"", ChatColor.GRAY + "Learn how you can", ChatColor.GRAY + "moderate your house to", ChatColor.GRAY + "prevent unwanted company!",
//                        "", Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View"}),
//                e -> player.performCommand("homeGuide")), 5, 1);

        gui.addPane(background);
        gui.addPane(display);
        gui.show(player);
    }
}

