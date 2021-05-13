package monzter.adventurescraft.plugin.shared.GUIs.mainMenu;

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
import monzter.adventurescraft.plugin.utilities.GUI.GUIHelper;
import monzter.adventurescraft.plugin.utilities.enums.Prefix;
import monzter.adventurescraft.plugin.utilities.general.ConsoleCommand;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import net.kyori.adventure.text.Component;
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

    public Quests(AdventuresCraft plugin, SoundManager soundManager, GUIHelper guiHelper, ConsoleCommand consoleCommand) {
        this.plugin = plugin;
        this.soundManager = soundManager;
        this.guiHelper = guiHelper;
        this.consoleCommand = consoleCommand;
    }

    @CommandAlias("Quests|Quest")
    public void questMenu(Player player) {

        ChestGui gui = new ChestGui(4, guiHelper.guiName("Quests " + parsePlaceholder(player, "betonquest_default-Points:point.QuestTotal.amount")));
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        OutlinePane background = new OutlinePane(0, 0, 9, 4, Pane.Priority.LOWEST);
        StaticPane display = new StaticPane(0, 0, 9, 4, Pane.Priority.LOW);


        background.addItem(new GuiItem(guiHelper.background(Material.GREEN_STAINED_GLASS_PANE)));
        background.setRepeat(true);

        display.addItem(new GuiItem(yard(player), e -> player.performCommand("yardQuests")), 4, 1);

        display.addItem(new GuiItem(activeQuests(player), e -> consoleCommand.consoleCommand("rpgmenu open default-Menus-menu.active " + player.getName())), 2, 3);
        display.addItem(new GuiItem(unclaimedQuests(player), e -> consoleCommand.consoleCommand("rpgmenu open default-Menus-menu.unclaimed " + player.getName())), 3, 3);
        display.addItem(new GuiItem(guiHelper.backButton(), e -> player.performCommand("main")), 4, 3);
        display.addItem(new GuiItem(jobs(player), e -> player.performCommand("jobs")), 5, 3);
        display.addItem(new GuiItem(achievements(player), e -> player.performCommand("achievements")), 6, 3);

        gui.addPane(background);
        gui.addPane(display);
        gui.show(player);
    }


    private ItemStack yard(Player player) {
        final ItemStack yard = new ItemStack(Material.POLISHED_ANDESITE);
        final ItemMeta yardItemMeta = yard.getItemMeta();

        yardItemMeta.displayName(Component.text(ChatColor.GREEN + "Yard " + parsePlaceholder(player, "betonquest_default-Points:point.Yard.amount") + ChatColor.GREEN + "/" + "4"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(Prefix.PREFIX.getPrefix() + ChatColor.YELLOW + "Click to View Quests");

        yard.setItemMeta(yardItemMeta);
        yard.setLore(lore);

        return yard;
    }

    private ItemStack activeQuests(Player player) {
        final ItemStack activeQuests = new ItemStack(Material.BOOK);
        final ItemMeta activeQuestsItemMeta = activeQuests.getItemMeta();

        activeQuestsItemMeta.displayName(Component.text(ChatColor.GREEN + "Active Quests"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "View your currently active quests!");
        lore.add("");
        lore.add(Prefix.PREFIX.getPrefix() + ChatColor.YELLOW + "Click to View Active Quests");

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
        lore.add(Prefix.PREFIX.getPrefix() + ChatColor.YELLOW + "Click to View Unclaimed Rewards");

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
        lore.add(Prefix.PREFIX.getPrefix() + ChatColor.YELLOW + "Click to View Jobs");

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
        lore.add(Prefix.PREFIX.getPrefix() + ChatColor.YELLOW + "Click to View Achievements");

        achievements.setItemMeta(achievementsItemMeta);
        achievements.setLore(lore);

        return achievements;
    }


    private String parsePlaceholder(Player player, String string) {
        return PlaceholderAPI.setPlaceholders(player, "%" + string + "%");
    }

}

