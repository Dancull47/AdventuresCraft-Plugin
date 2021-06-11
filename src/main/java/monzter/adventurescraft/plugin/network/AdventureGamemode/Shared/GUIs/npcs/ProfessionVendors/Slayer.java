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

public class Slayer extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final SoundManager soundManager;
    private final GUIHelper guiHelper;
    private final ConsoleCommand consoleCommand;

    public Slayer(AdventuresCraft plugin, SoundManager soundManager, GUIHelper guiHelper, ConsoleCommand consoleCommand) {
        this.plugin = plugin;
        this.soundManager = soundManager;
        this.guiHelper = guiHelper;
        this.consoleCommand = consoleCommand;
    }

    @CommandAlias("SlayerShop")
    @CommandPermission("*")
    public void mainMenu(Player player) {

        ChestGui gui = new ChestGui(3, guiHelper.guiName("Slayer"));
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        OutlinePane background = new OutlinePane(0, 0, 9, 3, Pane.Priority.LOWEST);
        StaticPane display = new StaticPane(0, 0, 9, 3, Pane.Priority.LOW);


        background.addItem(new GuiItem(guiHelper.background(Material.RED_STAINED_GLASS_PANE)));
        background.setRepeat(true);

        display.addItem(new GuiItem(guiHelper.itemCreator(Material.NETHERITE_SWORD,
                ChatColor.GREEN + "Weapon Shop", new String[]{"", Prefix.PREFIX.getString() + ChatColor.YELLOW + "Coming soon..."})), 2, 1);
        display.addItem(new GuiItem(guiHelper.itemCreator(Material.BLAZE_POWDER,
                ChatColor.GREEN + "Material Shop", new String[]{"", Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View"}),
                e -> consoleCommand.consoleCommand("mi stations open enchanted-materials " + player.getName())), 4, 1);
        display.addItem(new GuiItem(guiHelper.itemCreator("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTY3ZTE4NjAyZTAzMDM1YWQ2ODk2N2NlMDkwMjM1ZDg5OTY2NjNmYjllYTQ3NTc4ZDNhN2ViYmM0MmE1Y2NmOSJ9fX0=",
                ChatColor.GREEN + "Companion Shop", new String[]{"", Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View"}),
                e -> consoleCommand.consoleCommand("mi stations open slayer " + player.getName())), 6, 1);

        gui.addPane(background);
        gui.addPane(display);
        gui.show(player);
    }
}

