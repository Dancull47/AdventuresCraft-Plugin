package monzter.adventurescraft.plugin.network.PrisonGamemode.shared.GUIs.mainMenu.quests;

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
import monzter.adventurescraft.plugin.network.PrisonGamemode.shared.GUIs.mainMenu.quests.enums.QuestArea;
import monzter.adventurescraft.plugin.network.PrisonGamemode.shared.GUIs.mainMenu.quests.enums.QuestGiver;
import monzter.adventurescraft.plugin.network.PrisonGamemode.shared.GUIs.mainMenu.quests.enums.Quests;
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

@CommandAlias("QuestAreaMenu")
public class QuestAreaMenu extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final SoundManager soundManager;
    private final GUIHelper guiHelper;
    private final ConsoleCommand consoleCommand;
    private final BetonTagManager betonTagManager;


    public QuestAreaMenu(AdventuresCraft plugin, SoundManager soundManager, GUIHelper guiHelper, ConsoleCommand consoleCommand, BetonTagManager betonTagManager) {
        this.plugin = plugin;
        this.soundManager = soundManager;
        this.guiHelper = guiHelper;
        this.consoleCommand = consoleCommand;
        this.betonTagManager = betonTagManager;
    }

    @CommandAlias("Town")
    public void town(Player player) {
        menuGenerator(player, QuestArea.TOWN, Material.BROWN_STAINED_GLASS_PANE);
    }
//
//    @CommandAlias("Farm")
//    public void farm(Player player) {
//        menuGenerator(player, QuestArea.FARM, Material.GREEN_STAINED_GLASS_PANE);
//    }
//
//    @CommandAlias("Forest")
//    public void forest(Player player) {
//        menuGenerator(player, QuestArea.FOREST, Material.GREEN_STAINED_GLASS_PANE);
//    }
//
//    @CommandAlias("Mine")
//    public void mine(Player player) {
//        menuGenerator(player, QuestArea.MINE, Material.GRAY_STAINED_GLASS_PANE);
//    }
//
//    @CommandAlias("Graveyard")
//    public void graveyard(Player player) {
//        menuGenerator(player, QuestArea.GRAVEYARD, Material.RED_STAINED_GLASS_PANE);
//    }
//
//    @CommandAlias("Courtyard")
//    public void courtyard(Player player) {
//        menuGenerator(player, QuestArea.COURTYARD, Material.RED_STAINED_GLASS_PANE);
//    }
//
//    @CommandAlias("Castle")
//    public void castle(Player player) {
//        menuGenerator(player, QuestArea.CASTLE, Material.RED_STAINED_GLASS_PANE);
//    }
//
//    @CommandAlias("Valley")
//    public void valley(Player player) {
//        menuGenerator(player, QuestArea.VALLEY, Material.GREEN_STAINED_GLASS_PANE);
//    }
//
//    @CommandAlias("Estate")
//    public void estate(Player player) {
//        menuGenerator(player, QuestArea.ESTATE, Material.GREEN_STAINED_GLASS_PANE);
//    }
//
//    @CommandAlias("GoblinTown|GoblinVillage")
//    public void goblinTown(Player player) {
//        menuGenerator(player, QuestArea.GOBLIN_TOWN, Material.RED_STAINED_GLASS_PANE);
//    }
//
//    @CommandAlias("SpiritGrounds")
//    public void spiritGrounds(Player player) {
//        menuGenerator(player, QuestArea.SPIRIT_GROUNDS, Material.PURPLE_STAINED_GLASS_PANE);
//    }
//
//    @CommandAlias("Hell")
//    public void hell(Player player) {
//        menuGenerator(player, QuestArea.HELL, Material.RED_STAINED_GLASS_PANE);
//    }
//
//    @CommandAlias("Void")
//    public void theVoid(Player player) {
//        menuGenerator(player, QuestArea.VOID, Material.PURPLE_STAINED_GLASS_PANE);
//    }

    public void menuGenerator(Player player, QuestArea questArea, Material backgroundColor) {
        int npcAmount = 0;
        int startX = 2;
        int startY = 1;
        int length = 5;
        int height = 3;
        for (QuestGiver questGiver : QuestGiver.values())
            if (questGiver.getArea() == questArea)
                npcAmount += 1;

        if (npcAmount == 1)
            startX = 4;
        else if (npcAmount == 2 || npcAmount == 3)
            startX = 3;
        else if (npcAmount == 4)
            startX = 2;
        else if (npcAmount == 5)
            startX = 1;
        else if (npcAmount == 6) {
            startX = 1;
            length = 6;
        }

        int questAmount = 0;
        for (Quests quests : Quests.values()) {
            if (quests.getQuestGiver().getArea() == questArea)
                questAmount++;
        }

        int completedQuests = 0;
        for (Quests quests : Quests.values()) {
            if (quests.getQuestGiver().getArea() == questArea) {
                String packageBuilder = "default-" + WordUtils.capitalizeFully(quests.getQuestGiver().getArea().name()) + "-" + WordUtils.capitalizeFully(quests.getQuestGiver().name() + ".");
//                plugin.getLogger().info(packageBuilder + quests.name() + "_COMPLETED");
                if (betonTagManager.hasTag(player, packageBuilder + quests.name() + "_COMPLETED"))
                    completedQuests++;
            }
        }


        ChestGui gui = new ChestGui(height + 1, guiHelper.guiName(WordUtils.capitalizeFully(questArea.name()) + " Quests " + completedQuests + "/" + questAmount));
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        OutlinePane background = new OutlinePane(0, 0, 9, height + 1, Pane.Priority.LOWEST);
        StaticPane display = new StaticPane(0, 0, 9, height + 1, Pane.Priority.LOW);
        OutlinePane main = new OutlinePane(startX, startY, length, height - 2, Pane.Priority.LOW);

        background.addItem(new GuiItem(guiHelper.background(backgroundColor)));
        background.setRepeat(true);
        for (QuestGiver questGiver : QuestGiver.values()) {
            if (questGiver.getArea() == questArea)
                main.addItem(itemGenerator(player, questGiver));
        }

        display.addItem(new GuiItem(guiHelper.backButton(), e -> player.performCommand("quests")), 4, height);

        gui.addPane(background);
        gui.addPane(display);
        gui.addPane(main);
        gui.show(player);
    }


    private GuiItem itemGenerator(Player player, QuestGiver questGiver) {
        final ItemStack item = new ItemStack(SkullCreator.itemFromBase64(questGiver.getHead()));
        final ItemMeta itemItemMeta = item.getItemMeta();

        int completedQuests = 0;
        for (Quests quests : Quests.values()) {
            if (quests.getQuestGiver() == questGiver) {
                String packageBuilder = "default-" + WordUtils.capitalizeFully(quests.getQuestGiver().getArea().name()) + "-" + WordUtils.capitalizeFully(quests.getQuestGiver().name() + ".");
                if (betonTagManager.hasTag(player, packageBuilder + quests.name() + "_COMPLETED"))
                    completedQuests++;
            }
        }

        int questAmount = 0;
        for (Quests quests : Quests.values()) {
            if (quests.getQuestGiver() == questGiver)
                questAmount++;
        }
        if (!questGiver.getJob())
            itemItemMeta.displayName(Component.text(ChatColor.GREEN + WordUtils.capitalizeFully(questGiver.name()) + " " + completedQuests + ChatColor.GREEN + "/" + questAmount));
        else
            itemItemMeta.displayName(Component.text(ChatColor.GOLD + WordUtils.capitalizeFully(questGiver.name())));

        List<String> lore = new ArrayList<>();
        lore.add("");
        if (!questGiver.getJob())
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View Quests");
        else
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View Jobs");

        item.setItemMeta(itemItemMeta);
        item.setLore(lore);

        return new GuiItem(item, e -> player.performCommand("QuestViewer " + questGiver.name().replace(" ", "")));
    }

    private String parsePlaceholder(Player player, String string) {
        return PlaceholderAPI.setPlaceholders(player, "%" + string + "%");
    }
}

