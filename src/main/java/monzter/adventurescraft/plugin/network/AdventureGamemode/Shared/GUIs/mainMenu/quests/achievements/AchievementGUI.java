package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.mainMenu.quests.achievements;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Dependency;
import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.GUI.GUIHelper;
import monzter.adventurescraft.plugin.utilities.beton.BetonPointsManager;
import monzter.adventurescraft.plugin.utilities.general.ConsoleCommand;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import monzter.adventurescraft.plugin.utilities.luckperms.PermissionLP;
import monzter.adventurescraft.plugin.utilities.text.NumberFormat;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class AchievementGUI extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final SoundManager soundManager;
    private final GUIHelper guiHelper;
    private final NumberFormat numberFormat;
    private final BetonPointsManager betonPointsManager;
    private final PermissionLP permissionLP;
    private final ConsoleCommand consoleCommand;
    private final AchievementItemBuilder achievementGUIBuilder;

    public AchievementGUI(AdventuresCraft plugin, SoundManager soundManager, GUIHelper guiHelper, NumberFormat numberFormat, BetonPointsManager betonPointsManager, PermissionLP permissionLP, ConsoleCommand consoleCommand, AchievementItemBuilder achievementGUIBuilder) {
        this.plugin = plugin;
        this.soundManager = soundManager;
        this.guiHelper = guiHelper;
        this.numberFormat = numberFormat;
        this.betonPointsManager = betonPointsManager;
        this.permissionLP = permissionLP;
        this.consoleCommand = consoleCommand;
        this.achievementGUIBuilder = achievementGUIBuilder;
    }

    @CommandAlias("AchievementsMiner")
    public void miner(Player player) {
        ChestGui gui = new ChestGui(4, guiHelper.guiName("Miner Achievements"));
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        OutlinePane background = new OutlinePane(0, 0, 9, 4, Pane.Priority.LOWEST);
        OutlinePane display = new OutlinePane(2, 1, 5, 2, Pane.Priority.LOW);
        OutlinePane backButton = new OutlinePane(4, 3, 1, 1, Pane.Priority.LOW);


        background.addItem(new GuiItem(guiHelper.background(Material.GRAY_STAINED_GLASS_PANE)));
        background.setRepeat(true);

        for (Achievements achievement : Achievements.values())
            if (achievement.getGroup().equals("Miner"))
                display.addItem(achievementGUIBuilder.totalBlocks(player, achievement, achievement.getPlaceholder()));

        backButton.addItem(new GuiItem(guiHelper.backButton(), e -> player.performCommand("Achievement")));

        gui.addPane(background);
        gui.addPane(display);
        gui.addPane(backButton);
        gui.show(player);
    }

    @CommandAlias("AchievementsOre|AchievementOres|AchievementOre")
    public void ore(Player player) {
        ChestGui gui = new ChestGui(4, guiHelper.guiName("Ore Achievements"));
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        OutlinePane background = new OutlinePane(0, 0, 9, 4, Pane.Priority.LOWEST);
        OutlinePane display = new OutlinePane(2, 1, 5, 2, Pane.Priority.LOW);
        OutlinePane backButton = new OutlinePane(4, 3, 1, 1, Pane.Priority.LOW);


        background.addItem(new GuiItem(guiHelper.background(Material.GRAY_STAINED_GLASS_PANE)));
        background.setRepeat(true);

        for (Achievements achievement : Achievements.values())
            if (achievement.getGroup().equals("Ore"))
                display.addItem(achievementGUIBuilder.totalBlocks(player, achievement, achievement.getPlaceholder()));

        backButton.addItem(new GuiItem(guiHelper.backButton(), e -> player.performCommand("Achievement")));

        gui.addPane(background);
        gui.addPane(display);
        gui.addPane(backButton);
        gui.show(player);
    }

}

