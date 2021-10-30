package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.mainMenu;

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
import monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.ClassSystem.ProfessionHandler;
import monzter.adventurescraft.plugin.utilities.GUI.GUIHelper;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import monzter.adventurescraft.plugin.utilities.text.ProgressBar;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Professions extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final SoundManager soundManager;
    private final GUIHelper guiHelper;
    private final ProgressBar progressBar;


    public Professions(AdventuresCraft plugin, SoundManager soundManager, GUIHelper guiHelper, ProgressBar progressBar) {
        this.plugin = plugin;
        this.soundManager = soundManager;
        this.guiHelper = guiHelper;
        this.progressBar = progressBar;
    }

    @CommandAlias("profession|professions")
    public void profession(Player player) {

        ChestGui gui = new ChestGui(5, guiHelper.guiName("Professions"));
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        OutlinePane background = new OutlinePane(0, 0, 9, 5, Pane.Priority.LOWEST);
        StaticPane display = new StaticPane(0, 0, 9, 5, Pane.Priority.LOW);

        background.addItem(new GuiItem(guiHelper.background(Material.ORANGE_STAINED_GLASS_PANE)));
        background.setRepeat(true);

        display.addItem(new GuiItem(farming(player)), 2, 1);
        display.addItem(new GuiItem(foraging(player)), 3, 1);
        display.addItem(new GuiItem(mining(player)), 4, 1);
        display.addItem(new GuiItem(slayer(player)), 5, 1);
        display.addItem(new GuiItem(enchanting(player)), 6, 1);

        display.addItem(new GuiItem(cooking(player)), 3, 2);
        display.addItem(new GuiItem(spellforging(player)), 4, 2);

        display.addItem(new GuiItem(guiHelper.backButton(), e -> player.performCommand("main")), 4, 4);

        gui.addPane(background);
        gui.addPane(display);
        gui.show(player);
    }

    String profession;

    private ItemStack farming(Player player) {
        profession = "Farming";
        int level = ProfessionHandler.getLevel(player, profession);
        String name = ChatColor.GREEN + profession + " Level " + ChatColor.GOLD + level;
        return guiHelper.itemCreator(getMaterial(level, "HOE"), name, new String[]{
                ChatColor.GRAY + "Help around the Farm, and",
                ChatColor.GRAY + "harvest crops to earn XP!",
                "",
                ChatColor.WHITE + "Progress to Level " + ChatColor.GOLD + Integer.valueOf(level + 1),
                progressBar.getProgressBar(ProfessionHandler.getEXP(player, profession), ProfessionHandler.getNextLevelEXP(player, profession), 13, '-', ChatColor.YELLOW, ChatColor.GRAY) + " " + ChatColor.GOLD + ProfessionHandler.getNextLevelPercentage(player, profession) + "%",
//                "",
//                Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View"
        });
    }

    private ItemStack foraging(Player player) {
        profession = "Foraging";
        int level = ProfessionHandler.getLevel(player, profession);
        String name = ChatColor.GREEN + profession + " Level " + ChatColor.GOLD + level;
        return guiHelper.itemCreator(getMaterial(level, "AXE"), name, new String[]{
                ChatColor.GRAY + "Chop down trees and help",
                ChatColor.GRAY + "in the Forest to earn XP!",
                "",
                ChatColor.WHITE + "Progress to Level " + ChatColor.GOLD + Integer.valueOf(level + 1),
                progressBar.getProgressBar(ProfessionHandler.getEXP(player, profession), ProfessionHandler.getNextLevelEXP(player, profession), 13, '-', ChatColor.YELLOW, ChatColor.GRAY) + " " + ChatColor.GOLD + ProfessionHandler.getNextLevelPercentage(player, profession) + "%",
//                "",
//                Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View"
        });
    }

    private ItemStack mining(Player player) {
        profession = "Mining";
        int level = ProfessionHandler.getLevel(player, profession);
        String name = ChatColor.GREEN + profession + " Level " + ChatColor.GOLD + level;
        return guiHelper.itemCreator(getMaterial(level, "PICKAXE"), name, new String[]{
                ChatColor.GRAY + "Adventure through the Mine and",
                ChatColor.GRAY + "excavate rare minerals to earn XP!",
                "",
                ChatColor.WHITE + "Progress to Level " + ChatColor.GOLD + Integer.valueOf(level + 1),
                progressBar.getProgressBar(ProfessionHandler.getEXP(player, profession), ProfessionHandler.getNextLevelEXP(player, profession), 13, '-', ChatColor.YELLOW, ChatColor.GRAY) + " " + ChatColor.GOLD + ProfessionHandler.getNextLevelPercentage(player, profession) + "%",
//                "",
//                Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View"
        });
    }

    private ItemStack slayer(Player player) {
        profession = "Slayer";
        int level = ProfessionHandler.getLevel(player, profession);
        String name = ChatColor.GREEN + profession + " Level " + ChatColor.GOLD + level;
        return guiHelper.itemCreator(getMaterial(level, "SWORD"), name, new String[]{
                ChatColor.GRAY + "Help around the world by",
                ChatColor.GRAY + "defeating monsters to earn XP!",
                "",
                ChatColor.WHITE + "Progress to Level " + ChatColor.GOLD + Integer.valueOf(level + 1),
                progressBar.getProgressBar(ProfessionHandler.getEXP(player, profession), ProfessionHandler.getNextLevelEXP(player, profession), 13, '-', ChatColor.YELLOW, ChatColor.GRAY) + " " + ChatColor.GOLD + ProfessionHandler.getNextLevelPercentage(player, profession) + "%",
//                "",
//                Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View"
        });
    }

    private ItemStack enchanting(Player player) {
        profession = "Enchanting";
        int level = ProfessionHandler.getLevel(player, profession);
        String name = ChatColor.GREEN + profession + " Level " + ChatColor.GOLD + level;
        return guiHelper.itemCreator(Material.BOOK, name, new String[]{
                ChatColor.GRAY + "Improve your gear at the",
                ChatColor.GRAY + "Enchanter to earn XP!",
                "",
                ChatColor.WHITE + "Progress to Level " + ChatColor.GOLD + Integer.valueOf(level + 1),
                progressBar.getProgressBar(ProfessionHandler.getEXP(player, profession), ProfessionHandler.getNextLevelEXP(player, profession), 13, '-', ChatColor.YELLOW, ChatColor.GRAY) + " " + ChatColor.GOLD + ProfessionHandler.getNextLevelPercentage(player, profession) + "%",
//                "",
//                Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View"
        });
    }

    private ItemStack cooking(Player player) {
        profession = "Cooking";
        int level = ProfessionHandler.getLevel(player, profession);
        String name = ChatColor.GREEN + profession + " Level " + ChatColor.GOLD + level;
        return guiHelper.itemCreator(Material.MUSHROOM_STEW, name, new String[]{
                ChatColor.GRAY + "Cook Stews and Totems with",
                ChatColor.GRAY + "the Chef to earn XP!",
                "",
                ChatColor.WHITE + "Progress to Level " + ChatColor.GOLD + Integer.valueOf(level + 1),
                progressBar.getProgressBar(ProfessionHandler.getEXP(player, profession), ProfessionHandler.getNextLevelEXP(player, profession), 13, '-', ChatColor.YELLOW, ChatColor.GRAY) + " " + ChatColor.GOLD + ProfessionHandler.getNextLevelPercentage(player, profession) + "%",
//                "",
//                Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View"
        });
    }

    private ItemStack spellforging(Player player) {
        profession = "Spellforging";
        int level = ProfessionHandler.getLevel(player, profession);
        String name = ChatColor.GREEN + profession + " Level " + ChatColor.GOLD + level;
        return guiHelper.itemCreator(Material.PAPER, name, new String[]{
                ChatColor.GRAY + "Forge Spells & Wands with",
                ChatColor.GRAY + "the Warlock to earn XP!",
                "",
                ChatColor.WHITE + "Progress to Level " + ChatColor.GOLD + Integer.valueOf(level + 1),
                progressBar.getProgressBar(ProfessionHandler.getEXP(player, profession), ProfessionHandler.getNextLevelEXP(player, profession), 13, '-', ChatColor.YELLOW, ChatColor.GRAY) + " " + ChatColor.GOLD + ProfessionHandler.getNextLevelPercentage(player, profession) + "%",
//                "",
//                Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View"
        });
    }

    private Material getMaterial(int level, String material) {
        switch (level) {
            case 0:
            case 1:
            case 2:
                return Material.valueOf("WOODEN_" + material);
            case 3:
            case 4:
                return Material.valueOf("STONE_" + material);
            case 5:
            case 6:
                return Material.valueOf("IRON_" + material);
            case 7:
            case 8:
                return Material.valueOf("GOLDEN_" + material);
            case 9:
            case 10:
                return Material.valueOf("DIAMOND_" + material);
        }
        return Material.BEDROCK;
    }

    private String parsePlaceholder(Player player, String string) {
        return PlaceholderAPI.setPlaceholders(player, "%" + string + "%");
    }

}

