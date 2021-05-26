package monzter.adventurescraft.plugin.shared.GUIs.quests.yard;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Dependency;
import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import me.clip.placeholderapi.PlaceholderAPI;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.GUI.GUIHelper;
import monzter.adventurescraft.plugin.utilities.beton.BetonTagManager;
import monzter.adventurescraft.plugin.utilities.enums.StatsDisplay;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class Joy extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final GUIHelper guiHelper;
    private final BetonTagManager betonTagManager;
    final String quester = "Joy";
    final String questerLocation = "Town";


    public Joy(AdventuresCraft plugin, GUIHelper guiHelper, BetonTagManager betonTagManager) {
        this.plugin = plugin;
        this.guiHelper = guiHelper;
        this.betonTagManager = betonTagManager;
    }

    @CommandAlias("joyQuests")
    public void questMenu(Player player) {
        ChestGui gui = new ChestGui(4, guiHelper.guiName("Joy's Quests " + parsePlaceholder(player, "betonquest_default-Points:point.Joy.amount")));
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        OutlinePane background = new OutlinePane(0, 0, 9, 4, Pane.Priority.LOWEST);
        StaticPane display = new StaticPane(0, 0, 9, 4, Pane.Priority.LOW);


        background.addItem(new GuiItem(guiHelper.background(Material.PINK_STAINED_GLASS_PANE)));
        background.setRepeat(true);

        display.addItem(quest1(player), 4, 1);

        display.addItem(new GuiItem(guiHelper.backButton(), e -> player.performCommand("YardQuests")), 4, 3);

        gui.addPane(background);
        gui.addPane(display);
        gui.show(player);
    }



    private GuiItem quest1(Player player) {
        final String name = "Hatching an Egg";
        final String[] description = new String[]{ChatColor.WHITE + "Mine 50 Blocks with the", ChatColor.WHITE + "Pet Egg in your inventory"};
        final String[] rewards = new String[]{"Pet Egg", "50 " + StatsDisplay.PET_EXPERIENCE_AMOUNT.getName(), "100 " + StatsDisplay.MONEY_AMOUNT.getName()};
        if (!betonTagManager.hasTag(player, "default-Yard-Joy.q1_part1"))
            return new GuiItem(guiHelper.questInactive(name, description, rewards, quester, questerLocation));
        if (betonTagManager.hasTag(player, "default-Yard-Joy.q1_part1") && !betonTagManager.hasTag(player, "default-Yard-Joy.q1_completed"))
            return new GuiItem(guiHelper.questActive(name, description, rewards, quester, questerLocation));
        if (betonTagManager.hasTag(player, "default-Yard-Joy.q1_completed"))
            return new GuiItem(guiHelper.questComplete(name, description, rewards, quester, questerLocation));
        return null;
    }


    private String parsePlaceholder(Player player, String string) {
        return PlaceholderAPI.setPlaceholders(player, "%" + string + "%");
    }

}

