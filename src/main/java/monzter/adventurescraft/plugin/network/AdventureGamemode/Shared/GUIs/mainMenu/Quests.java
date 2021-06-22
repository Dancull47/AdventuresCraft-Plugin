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
import monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.quests.QuestArea;
import monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.quests.QuestGiver;
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

    @CommandAlias("Quests|Quest|Questsss")
    public void questMenu(Player player) {
        int i = 0;
        for (QuestGiver questGiver : QuestGiver.values())
            i += questGiver.getQuestAmount();

        ChestGui gui = new ChestGui(5, guiHelper.guiName("Quests " + parsePlaceholder(player, "betonquest_default-Points:point.QuestTotal.amount") + "/" + i));
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        OutlinePane background = new OutlinePane(0, 0, 9, 5, Pane.Priority.LOWEST);
        StaticPane display = new StaticPane(0, 0, 9, 5, Pane.Priority.LOW);
        OutlinePane main = new OutlinePane(1, 1, 7, 3, Pane.Priority.LOW);


        background.addItem(new GuiItem(guiHelper.background(Material.GREEN_STAINED_GLASS_PANE)));
        background.setRepeat(true);

        for (QuestArea questArea : QuestArea.values())
            main.addItem(itemGenerator(player, questArea));

//        main.addItem(new GuiItem(town(player), e -> player.performCommand("QuestMenu graveyard")));
//        main.addItem(new GuiItem(farm(player), e -> player.performCommand("QuestMenu graveyard")));
//        main.addItem(new GuiItem(forest(player), e -> player.performCommand("QuestMenu graveyard")));
//        main.addItem(new GuiItem(mines(player), e -> player.performCommand("QuestMenu graveyard")));
//        main.addItem(new GuiItem(graveyard(player), e -> player.performCommand("QuestMenu graveyard")));
//        main.addItem(new GuiItem(courtyard(player), e -> player.performCommand("QuestMenu courtyard")));
//        main.addItem(new GuiItem(castle(player), e -> player.performCommand("QuestMenu castle")));
//        main.addItem(new GuiItem(valley(player), e -> player.performCommand("QuestMenu valley")));
//        main.addItem(new GuiItem(estate(player), e -> player.performCommand("QuestMenu estate")));
//        main.addItem(new GuiItem(goblinTown(player), e -> player.performCommand("QuestMenu goblinTown")));
//        main.addItem(new GuiItem(spiritGrounds(player), e -> player.performCommand("QuestMenu spiritGrounds")));
//        main.addItem(new GuiItem(hell(player), e -> player.performCommand("QuestMenu hell")));
//        main.addItem(new GuiItem(theVoid(player), e -> player.performCommand("QuestMenu void")));

        display.addItem(new GuiItem(achievements(player), e -> player.performCommand("QuestMenu Active")), 2, 4);
        display.addItem(new GuiItem(activeQuests(player), e -> player.performCommand("QuestMenu Active")), 3, 4);
        display.addItem(new GuiItem(guiHelper.backButton(), e -> player.performCommand("main")), 4, 4);
        display.addItem(new GuiItem(unclaimedQuests(player), e -> player.performCommand("fastTravel")), 5, 4);
        display.addItem(new GuiItem(jobs(player), e -> player.performCommand("fastTravel")), 6, 4);

        gui.addPane(background);
        gui.addPane(display);
        gui.addPane(main);
        gui.show(player);
    }


    private GuiItem itemGenerator(Player player, QuestArea questArea) {
        int questAmount = 0;
        final ItemStack item = new ItemStack(SkullCreator.itemFromBase64(questArea.getHead()));
        final ItemMeta itemItemMeta = item.getItemMeta();

        for (QuestGiver questGiver : QuestGiver.values())
            if (questGiver.getArea() == questArea)
                questAmount += questGiver.getQuestAmount();

        itemItemMeta.displayName(Component.text(ChatColor.GREEN + questArea.getName() + " " + parsePlaceholder(player, "betonquest_default-Points:point." + questArea.getName().replace(" ", "").replace("GoblinTown", "GoblinVillage") + ".amount") + ChatColor.GREEN + "/" + questAmount));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View Quests");

        item.setItemMeta(itemItemMeta);
        item.setLore(lore);

        return new GuiItem(item, e -> player.performCommand("questmenu " + questArea.getName().replace(" ", "").replace("GoblinTown", "GoblinVillage")));
    }


    private ItemStack town(Player player) {
        final ItemStack farm = new ItemStack(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmUyY2M0MjAxNWU2Njc4ZjhmZDQ5Y2NjMDFmYmY3ODdmMWJhMmMzMmJjZjU1OWEwMTUzMzJmYzVkYjUwIn19fQ=="));
        final ItemMeta farmItemMeta = farm.getItemMeta();

        farmItemMeta.displayName(Component.text(ChatColor.GREEN + "The Town " + parsePlaceholder(player, "betonquest_default-Points:point.Town.amount") + ChatColor.GREEN + "/" + "9"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View Quests");

        farm.setItemMeta(farmItemMeta);
        farm.setLore(lore);

        return farm;
    }

    private ItemStack farm(Player player) {
        final ItemStack farm = new ItemStack(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWFmMzI4Yzg3YjA2ODUwOWFjYTk4MzRlZmFjZTE5NzcwNWZlNWQ0ZjA4NzE3MzFiN2IyMWNkOTliOWZkZGMifX19"));
        final ItemMeta farmItemMeta = farm.getItemMeta();

        farmItemMeta.displayName(Component.text(ChatColor.GREEN + "The Farm " + parsePlaceholder(player, "betonquest_default-Points:point.Farm.amount") + ChatColor.GREEN + "/" + "7"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View Quests");

        farm.setItemMeta(farmItemMeta);
        farm.setLore(lore);

        return farm;
    }

    private ItemStack forest(Player player) {
        final ItemStack forest = new ItemStack(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjBjZDEzMjIzYThkOWMxNzNjZWRjZTZjNGJlYmViYTA2YTI0YTFiYTI3NWRkM2ViNWM3OTMzZjlhNzRiYTAxMSJ9fX0="));
        final ItemMeta forestItemMeta = forest.getItemMeta();

        forestItemMeta.displayName(Component.text(ChatColor.GREEN + "The Forest " + parsePlaceholder(player, "betonquest_default-Points:point.Forest.amount") + ChatColor.GREEN + "/" + "14"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Left-Click to View Quests");

        forest.setItemMeta(forestItemMeta);
        forest.setLore(lore);

        return forest;
    }

    private ItemStack mines(Player player) {
        final ItemStack mines = new ItemStack(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzQyMDcwYWNjODE0YmM5NDZlNTk4NzllYzdkYTQ1ZGU5ODRkM2VlOWExNTkzOTNkZWZiNTk4NTNhYmUzYjYifX19"));
        final ItemMeta minesItemMeta = mines.getItemMeta();

        minesItemMeta.displayName(Component.text(ChatColor.GREEN + "The Mines " + parsePlaceholder(player, "betonquest_default-Points:point.Mine.amount") + ChatColor.GREEN + "/" + "26"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Left-Click to View Quests");

        mines.setItemMeta(minesItemMeta);
        mines.setLore(lore);

        return mines;
    }

    private ItemStack graveyard(Player player) {
        final ItemStack graveyard = new ItemStack(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2YzYmU2NDAxNjczNmJlNDRiMWQ1YTVmM2FkYWI2ZDRjMDQzNzgyYzE3ZGQyOWMzYjhjNGNiNmU3M2Y5ODk4In19fQ=="));
        final ItemMeta graveyardItemMeta = graveyard.getItemMeta();

        graveyardItemMeta.displayName(Component.text(ChatColor.GREEN + "The Graveyard " + parsePlaceholder(player, "betonquest_default-Points:point.Graveyard.amount") + ChatColor.GREEN + "/" + "5"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View Quests");

        graveyard.setItemMeta(graveyardItemMeta);
        graveyard.setLore(lore);

        return graveyard;
    }

    private ItemStack courtyard(Player player) {
        ItemStack courtyard = new ItemStack(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDE5ZmIyZTQ5NzAzYzZjYjk1MTE2YmUxNTM2M2M5ZDU2ODllZjIyOWE3NWM2NTVlZjU3NmJlMzYwZWMzY2JlYiJ9fX0="));
        final ItemMeta courtyardItemMeta = courtyard.getItemMeta();

        courtyardItemMeta.displayName(Component.text(ChatColor.GREEN + "The Courtyard " + parsePlaceholder(player, "betonquest_default-Points:point.Courtyard.amount") + ChatColor.GREEN + "/" + "5"));
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View Quests");

        courtyard.setItemMeta(courtyardItemMeta);
        courtyard.setLore(lore);

        return courtyard;
    }

    private ItemStack castle(Player player) {
        ItemStack castle = new ItemStack(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDljMTgzMmU0ZWY1YzRhZDljNTE5ZDE5NGIxOTg1MDMwZDI1NzkxNDMzNGFhZjI3NDVjOWRmZDYxMWQ2ZDYxZCJ9fX0=="));
        final ItemMeta castleItemMeta = castle.getItemMeta();

        castleItemMeta.displayName(Component.text(ChatColor.GREEN + "The Castle " + parsePlaceholder(player, "betonquest_default-Points:point.Castle.amount") + ChatColor.GREEN + "/" + "1"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View Quests");

        castle.setItemMeta(castleItemMeta);
        castle.setLore(lore);

        return castle;
    }

    private ItemStack valley(Player player) {
        ItemStack estate = new ItemStack(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTZhNjA1MWY3ZjZmNDM5ZDhmMjE0YzIzNGU4ZTJjNDc3NjMwMDUyNDMyZTQyNjA3ZjA0MDRiODQwYjUzY2VhYiJ9fX0="));
        final ItemMeta estateItemMeta = estate.getItemMeta();

        estateItemMeta.displayName(Component.text(ChatColor.GREEN + "The Valley " + parsePlaceholder(player, "betonquest_default-Points:point.Valley.amount") + ChatColor.GREEN + "/" + "1"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View Quests");

        estate.setItemMeta(estateItemMeta);
        estate.setLore(lore);

        return estate;
    }

    private ItemStack estate(Player player) {
        ItemStack estate = new ItemStack(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzlkYmEyOWM4ODI4YTQ5MDliOTRhZWU0MmRkYTg4ZTgwNGM1YzJkOGZlZTcwODQ3ZmM2NTRjYzI3MGZmNWQzNiJ9fX0="));
        final ItemMeta estateItemMeta = estate.getItemMeta();

        estateItemMeta.displayName(Component.text(ChatColor.GREEN + "The Estate " + parsePlaceholder(player, "betonquest_default-Points:point.Estate.amount") + ChatColor.GREEN + "/" + "3"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View Quests");

        estate.setItemMeta(estateItemMeta);
        estate.setLore(lore);

        return estate;
    }

    private ItemStack goblinTown(Player player) {
        ItemStack goblinTown = new ItemStack(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjZiOTcyZTMyZDc2MWIxOTI2MjZlNWQ2ZDAxZWRjMDk0OTQwOTEwMTAzY2VhNWUyZTJkMWYyMzFhZGI3NTVkNSJ9fX0="));
        final ItemMeta goblinTownItemMeta = goblinTown.getItemMeta();

        goblinTownItemMeta.displayName(Component.text(ChatColor.GREEN + "The Goblin Town " + parsePlaceholder(player, "betonquest_default-Points:point.GoblinVillage.amount") + ChatColor.GREEN + "/" + "3"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View Quests");

        goblinTown.setItemMeta(goblinTownItemMeta);
        goblinTown.setLore(lore);

        return goblinTown;
    }

    private ItemStack spiritGrounds(Player player) {
        ItemStack spiritGrounds = new ItemStack(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzI2NWQ5OWVlODgxYjc0MTQ2ZTBmMTk4MDkxMmQ0NzZmZmViYmEyOWUxNTQ5MDM4ZTFkOTQ4ZjQwMTQ0MjJlYiJ9fX0="));
        final ItemMeta spiritGroundsItemMeta = spiritGrounds.getItemMeta();

        spiritGroundsItemMeta.displayName(Component.text(ChatColor.GREEN + "Spirit Grounds " + parsePlaceholder(player, "betonquest_default-Points:point.SpiritGrounds.amount") + ChatColor.GREEN + "/" + "3"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View Quests");

        spiritGrounds.setItemMeta(spiritGroundsItemMeta);
        spiritGrounds.setLore(lore);

        return spiritGrounds;
    }

    private ItemStack hell(Player player) {
        ItemStack hell = new ItemStack(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDgzNTcxZmY1ODlmMWE1OWJiMDJiODA4MDBmYzczNjExNmUyN2MzZGNmOWVmZWJlZGU4Y2YxZmRkZSJ9fX0="));
        final ItemMeta hellItemMeta = hell.getItemMeta();

        hellItemMeta.displayName(Component.text(ChatColor.GREEN + "Hell " + parsePlaceholder(player, "betonquest_default-Points:point.Hell.amount") + ChatColor.GREEN + "/" + "7"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View Quests");

        hell.setItemMeta(hellItemMeta);
        hell.setLore(lore);

        return hell;
    }

    private ItemStack theVoid(Player player) {
        ItemStack theVoid = new ItemStack(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjIwMWFlMWE4YTA0ZGY1MjY1NmY1ZTQ4MTNlMWZiY2Y5Nzg3N2RiYmZiYzQyNjhkMDQzMTZkNmY5Zjc1MyJ9fX0="));
        final ItemMeta voidItemMeta = theVoid.getItemMeta();

        voidItemMeta.displayName(Component.text(ChatColor.GREEN + "Void " + parsePlaceholder(player, "betonquest_default-Points:point.Void.amount") + ChatColor.GREEN + "/" + "17"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View Quests");

        theVoid.setItemMeta(voidItemMeta);
        theVoid.setLore(lore);

        return theVoid;
    }


    private ItemStack activeQuests(Player player) {
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

