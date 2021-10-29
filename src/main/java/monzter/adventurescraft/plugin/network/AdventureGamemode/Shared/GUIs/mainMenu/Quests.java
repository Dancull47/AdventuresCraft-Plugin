package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.mainMenu;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Dependency;
import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import dev.dbassett.skullcreator.SkullCreator;
import me.clip.placeholderapi.PlaceholderAPI;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.mainMenu.quests.enums.QuestArea;
import monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.mainMenu.quests.enums.QuestList;
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

public class Quests extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final SoundManager soundManager;
    private final GUIHelper guiHelper;
    private final ConsoleCommand consoleCommand;
    private final BetonTagManager betonTagManager;


    public Quests(AdventuresCraft plugin, SoundManager soundManager, GUIHelper guiHelper, ConsoleCommand consoleCommand, BetonTagManager betonTagManager) {
        this.plugin = plugin;
        this.soundManager = soundManager;
        this.guiHelper = guiHelper;
        this.consoleCommand = consoleCommand;
        this.betonTagManager = betonTagManager;
    }

    @CommandAlias("ActiveQuests|activeQuest")
    public void active(Player player) {
        guiHelper.activeQuestMenu(player);
    }
    @CommandAlias("UnclaimedQuests|UnclaimedQuest")
    public void unclaimed(Player player) {
        guiHelper.unclaimedQuestMenu(player);
    }


    @CommandAlias("Quests|Quest")
    public void questMenu(Player player) {

        int completedQuests = 0;
        for (QuestList quests : QuestList.values()) {
            String packageBuilder = "default-" + WordUtils.capitalizeFully(quests.getQuestGiver().getArea().name().replace('_',' ')).replace(' ', '_') + "-" + WordUtils.capitalizeFully(quests.getQuestGiver().name() + ".");
//                plugin.getLogger().info(packageBuilder + quests.name() + "_COMPLETED");
            if (betonTagManager.hasTag(player, packageBuilder + quests.name() + "_COMPLETED"))
                completedQuests++;
        }
        int questAmount = 0;
        for (QuestList quests : QuestList.values()) {
            questAmount++;
        }

        ChestGui gui = new ChestGui(5, guiHelper.guiName("Quests " + completedQuests + "/" + questAmount));
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        OutlinePane background = new OutlinePane(0, 0, 9, 5, Pane.Priority.LOWEST);
        StaticPane display = new StaticPane(0, 0, 9, 5, Pane.Priority.LOW);
        OutlinePane main = new OutlinePane(1, 1, 7, 3, Pane.Priority.LOW);


        background.addItem(new GuiItem(guiHelper.background(Material.GREEN_STAINED_GLASS_PANE)));
        background.setRepeat(true);

        for (QuestArea questArea : QuestArea.values())
            main.addItem(itemGenerator(player, questArea));

        display.addItem(new GuiItem(activeQuests(), e -> player.performCommand("activequests")), 3, 4);
        display.addItem(new GuiItem(guiHelper.backButton(), e -> player.performCommand("main")), 4, 4);
        display.addItem(new GuiItem(unclaimedQuests(player), e -> player.performCommand("unclaimedquests")), 5, 4);

        gui.addPane(background);
        gui.addPane(display);
        gui.addPane(main);
        gui.show(player);
    }


    private GuiItem itemGenerator(Player player, QuestArea questArea) {
        final ItemStack item = new ItemStack(SkullCreator.itemFromBase64(questArea.getHead()));
        final ItemMeta itemItemMeta = item.getItemMeta();

        int questAmount = 0;
        for (QuestList quests : QuestList.values()) {
            if (quests.getQuestGiver().getArea() == questArea)
                questAmount++;
        }

        int completedQuests = 0;
        for (QuestList quests : QuestList.values()) {
            if (quests.getQuestGiver().getArea() == questArea) {
                String packageBuilder = "default-" + WordUtils.capitalizeFully(quests.getQuestGiver().getArea().name().replace('_',' ')).replace(' ', '_') + "-" + WordUtils.capitalizeFully(quests.getQuestGiver().name() + ".");
                if (betonTagManager.hasTag(player, packageBuilder + quests.name() + "_COMPLETED"))
                    completedQuests++;
            }
        }

        itemItemMeta.displayName(Component.text(ChatColor.GREEN + WordUtils.capitalizeFully(questArea.name().replace('_', ' ')) + " " + completedQuests + ChatColor.GREEN + "/" + questAmount));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View Quests");

        item.setItemMeta(itemItemMeta);
        item.setLore(lore);

        return new GuiItem(item, e -> player.performCommand("QuestAreaMenu " + questArea.name().replace("_", "")));
    }

    private ItemStack activeQuests() {
        final ItemStack activeQuests = new ItemStack(Material.BOOK);
        final ItemMeta activeQuestsItemMeta = activeQuests.getItemMeta();

        activeQuestsItemMeta.displayName(Component.text(ChatColor.GREEN + "Active Quests"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "View your currently active quests!");
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View Active Quests");

        activeQuests.setItemMeta(activeQuestsItemMeta);
        activeQuests.setLore(lore);

        return activeQuests;
    }

    private ItemStack unclaimedQuests(Player player) {
        final ItemStack unclaimedQuests = new ItemStack(Material.ENCHANTED_BOOK);
        final ItemMeta unclaimedQuestsItemMeta = unclaimedQuests.getItemMeta();

        unclaimedQuestsItemMeta.displayName(Component.text(ChatColor.GREEN + "Unclaimed Quests"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "View your unclaimed quest rewards!");
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View Unclaimed Rewards");

        unclaimedQuests.setItemMeta(unclaimedQuestsItemMeta);
        unclaimedQuests.setLore(lore);

        return unclaimedQuests;
    }


    private ItemStack jobs(Player player) {
        final ItemStack jobs = new ItemStack(Material.CYAN_BANNER);
        final ItemMeta jobsItemMeta = jobs.getItemMeta();

        jobsItemMeta.displayName(Component.text(ChatColor.GREEN + "Jobs"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "Some " + ChatColor.GREEN + "NPCS " + ChatColor.GRAY + "around the world give " + ChatColor.GOLD + "Jobs " + ChatColor.GRAY + "which");
        lore.add(ChatColor.GRAY + "can be repeated each day or more often!");
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View Jobs");

        jobs.setItemMeta(jobsItemMeta);
        jobs.setLore(lore);

        return jobs;
    }

    private ItemStack achievements(Player player) {
        final ItemStack achievements = new ItemStack(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzQ5MzEzMDUzN2ZjNGQzNThjZGIzODdjOWRiMDgwODg4NDZiOGJlNTRmMWMxMWMyNTZhMzdlYjRjNjM4YzAifX19"));
        final ItemMeta achievementsItemMeta = achievements.getItemMeta();

        achievementsItemMeta.displayName(Component.text(ChatColor.GREEN + "Achievements"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "Earn special rewards for");
        lore.add(ChatColor.GRAY + "accomplishments while mining!");
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View Achievements");

        achievements.setItemMeta(achievementsItemMeta);
        achievements.setLore(lore);

        return achievements;
    }


    private String parsePlaceholder(Player player, String string) {
        return PlaceholderAPI.setPlaceholders(player, "%" + string + "%");
    }
}

