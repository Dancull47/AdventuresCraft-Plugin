package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.mainMenu.Achievements;

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
import monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.mainMenu.Achievements.Enums.AchievementCategory;
import monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.mainMenu.Achievements.Enums.AchievementList;
import monzter.adventurescraft.plugin.utilities.GUI.GUIHelper;
import monzter.adventurescraft.plugin.utilities.beton.BetonTagManager;
import monzter.adventurescraft.plugin.utilities.enums.Prefix;
import monzter.adventurescraft.plugin.utilities.general.ConsoleCommand;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import net.kyori.adventure.text.Component;
import org.apache.commons.lang.WordUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class AchievementMenu extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final SoundManager soundManager;
    private final GUIHelper guiHelper;
    private final ConsoleCommand consoleCommand;
    private final BetonTagManager betonTagManager;


    public AchievementMenu(AdventuresCraft plugin, SoundManager soundManager, GUIHelper guiHelper, ConsoleCommand consoleCommand, BetonTagManager betonTagManager) {
        this.plugin = plugin;
        this.soundManager = soundManager;
        this.guiHelper = guiHelper;
        this.consoleCommand = consoleCommand;
        this.betonTagManager = betonTagManager;
    }

    @CommandAlias("Achievements")
    public void questMenu(Player player) {

        int completedQuests = 0;
        for (AchievementList achievement : AchievementList.values()) {
            if (betonTagManager.hasTag(player, "ACHIEVEMENT." + achievement.name() + "_COMPLETED"))
                completedQuests++;
        }
        int questAmount = 0;
        for (AchievementList achievement : AchievementList.values()) {
            questAmount++;
        }

        ChestGui gui = new ChestGui(5, guiHelper.guiName("Achievements " + completedQuests + "/" + questAmount));
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        OutlinePane background = new OutlinePane(0, 0, 9, 5, Pane.Priority.LOWEST);
        StaticPane display = new StaticPane(0, 0, 9, 5, Pane.Priority.LOW);
        OutlinePane main = new OutlinePane(1, 1, 7, 3, Pane.Priority.LOW);


        background.addItem(new GuiItem(guiHelper.background(Material.YELLOW_STAINED_GLASS_PANE)));
        background.setRepeat(true);

        for (AchievementCategory achievementCategory : AchievementCategory.values())
            main.addItem(itemGenerator(player, achievementCategory));

        display.addItem(new GuiItem(guiHelper.backButton(), e -> player.performCommand("main")), 4, 4);

        gui.addPane(background);
        gui.addPane(display);
        gui.addPane(main);
        gui.show(player);
    }


    private GuiItem itemGenerator(Player player, AchievementCategory achievementCategory) {
        final ItemStack item = guiHelper.itemCreator(achievementCategory.getItemStack());
        final ItemMeta itemItemMeta = item.getItemMeta();

        int questAmount = 0;
        for (AchievementList achievement : AchievementList.values()) {
            if (achievement.getAchievementCategory().getAchievementCategory() == achievementCategory)
                questAmount++;
        }

        int completedachievement = 0;
        for (AchievementList achievement : AchievementList.values()) {
            if (achievement.getAchievementCategory().getAchievementCategory() == achievementCategory) {
                if (betonTagManager.hasTag(player, "ACHIEVEMENT." + achievement.name() + "_COMPLETED"))
                    completedachievement++;
            }
        }

        itemItemMeta.displayName(Component.text(ChatColor.GREEN + WordUtils.capitalizeFully(achievementCategory.name().replace('_', ' ')) + " " + completedachievement + ChatColor.GREEN + "/" + questAmount));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View");

        item.setItemMeta(itemItemMeta);
        item.setLore(lore);

        return new GuiItem(item, e -> player.performCommand("achievementCategoryMenu " + achievementCategory.name().replace("_", "")));
    }

    private String parsePlaceholder(Player player, String string) {
        return PlaceholderAPI.setPlaceholders(player, "%" + string + "%");
    }
}

