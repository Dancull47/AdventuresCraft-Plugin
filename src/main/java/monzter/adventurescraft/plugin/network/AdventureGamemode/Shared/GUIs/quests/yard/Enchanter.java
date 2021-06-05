package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.quests.yard;

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
import monzter.adventurescraft.plugin.utilities.enums.PrisonStatsDisplay;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class Enchanter extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final GUIHelper guiHelper;
    private final BetonTagManager betonTagManager;
    final String quester = "Enchanter";
    final String questerLocation = "Town";


    public Enchanter(AdventuresCraft plugin, GUIHelper guiHelper, BetonTagManager betonTagManager) {
        this.plugin = plugin;
        this.guiHelper = guiHelper;
        this.betonTagManager = betonTagManager;
    }

    @CommandAlias("enchanterQuests")
    public void questMenu(Player player) {
        ChestGui gui = new ChestGui(4, guiHelper.guiName("Enchanter's Quests " + parsePlaceholder(player, "betonquest_default-Points:point.Enchanter.amount")));
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        OutlinePane background = new OutlinePane(0, 0, 9, 4, Pane.Priority.LOWEST);
        StaticPane display = new StaticPane(0, 0, 9, 4, Pane.Priority.LOW);


        background.addItem(new GuiItem(guiHelper.background(Material.PURPLE_STAINED_GLASS_PANE)));
        background.setRepeat(true);

        display.addItem(quest1(player), 4, 1);

        display.addItem(new GuiItem(guiHelper.backButton(), e -> player.performCommand("YardQuests")), 4, 3);

        gui.addPane(background);
        gui.addPane(display);
        gui.show(player);
    }



    private GuiItem quest1(Player player) {
        final String name = "Understanding Experience";
        final String[] description = new String[]{ChatColor.WHITE + "Mine 32 blocks"};
        final String[] rewards = new String[]{"100 " + PrisonStatsDisplay.EXPERIENCE_AMOUNT.getName(), "100 " + PrisonStatsDisplay.MONEY_AMOUNT.getName()};
        if (!betonTagManager.hasTag(player, "default-Yard-Enchanter.q1_part1"))
            return new GuiItem(guiHelper.questInactive(name, description, rewards, quester, questerLocation));
        if (betonTagManager.hasTag(player, "default-Yard-Enchanter.q1_part1") && !betonTagManager.hasTag(player, "default-Yard-Enchanter.q1_completed"))
            return new GuiItem(guiHelper.questActive(name, description, rewards, quester, questerLocation));
        if (betonTagManager.hasTag(player, "default-Yard-Enchanter.q1_completed"))
            return new GuiItem(guiHelper.questComplete(name, description, rewards, quester, questerLocation));
        return null;
    }


    private String parsePlaceholder(Player player, String string) {
        return PlaceholderAPI.setPlaceholders(player, "%" + string + "%");
    }

}

