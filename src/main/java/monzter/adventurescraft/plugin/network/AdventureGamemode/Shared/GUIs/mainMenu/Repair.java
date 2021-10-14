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
import monzter.adventurescraft.plugin.utilities.GUI.GUIHelper;
import monzter.adventurescraft.plugin.utilities.beton.BetonTagManager;
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
import java.util.Arrays;
import java.util.List;

public class Repair extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final SoundManager soundManager;
    private final GUIHelper guiHelper;
    private final ConsoleCommand consoleCommand;
    private final BetonTagManager betonTagManager;
    private final List<Material> categories = Arrays.asList(Material.END_STONE);


    public Repair(AdventuresCraft plugin, SoundManager soundManager, GUIHelper guiHelper, ConsoleCommand consoleCommand, BetonTagManager betonTagManager) {
        this.plugin = plugin;
        this.soundManager = soundManager;
        this.guiHelper = guiHelper;
        this.consoleCommand = consoleCommand;
        this.betonTagManager = betonTagManager;
    }

    @CommandAlias("Repair")
    public void questMenu(Player player) {

        ChestGui gui = new ChestGui(5, guiHelper.guiName("Repair Station"));

        OutlinePane background = new OutlinePane(0, 0, 9, 5, Pane.Priority.LOWEST);
        StaticPane display = new StaticPane(0, 0, 9, 5, Pane.Priority.LOW);
        OutlinePane main = new OutlinePane(4, 1, 1, 1, Pane.Priority.LOW);

        display.setOnClick(event -> event.setCancelled(true));
        background.setOnClick(event -> event.setCancelled(true));

        background.addItem(new GuiItem(guiHelper.background(Material.GREEN_STAINED_GLASS_PANE)));
        background.setRepeat(true);

        display.addItem(new GuiItem(new ItemStack(Material.AIR)), 4, 1);

        main.setOnClick(inventoryClickEvent -> {
            if (inventoryClickEvent.getCurrentItem() != null && inventoryClickEvent.getCurrentItem().getType() != Material.AIR) {
                player.getInventory().addItem(inventoryClickEvent.getCurrentItem());
                inventoryClickEvent.setCurrentItem(new ItemStack(Material.AIR));
            }
        });

        gui.setOnClose(inventoryCloseEvent -> {
            if (inventoryCloseEvent.getInventory().getItem(13) != null && inventoryCloseEvent.getInventory().getItem(13).getType() != Material.AIR)
                player.getInventory().addItem(inventoryCloseEvent.getInventory().getItem(13));
        });

        gui.setOnBottomClick(inventoryClickEvent -> {
            if (inventoryClickEvent.getCurrentItem() != null)
                if (inventoryClickEvent.getCurrentItem().getType().name().endsWith("_HEAD") || inventoryClickEvent.getCurrentItem().getType().name().endsWith("_HELMET") ||
                        inventoryClickEvent.getCurrentItem().getType().name().endsWith("_CHESTPLATE") ||
                        inventoryClickEvent.getCurrentItem().getType().name().endsWith("_LEGGINGS") ||
                        inventoryClickEvent.getCurrentItem().getType().name().endsWith("_BOOTS")) {
                    if (inventoryClickEvent.getInventory().getItem(13) == null || inventoryClickEvent.getInventory().getItem(13).getType() == Material.AIR) {
                        System.out.println("went");
                        main.addItem(new GuiItem(inventoryClickEvent.getCurrentItem()));
                        gui.addPane(main);
                        gui.update();
                    }
                }
        });

        display.addItem(new GuiItem(activeQuests(), e -> player.performCommand("activequests")), 3, 4);
        display.addItem(new GuiItem(guiHelper.backButton(), e -> player.performCommand("main")), 4, 4);
        display.addItem(new GuiItem(unclaimedQuests(player), e -> player.performCommand("unclaimedquests")), 5, 4);

        gui.addPane(background);
        gui.addPane(display);
        gui.addPane(main);
        gui.show(player);
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


    private String parsePlaceholder(Player player, String string) {
        return PlaceholderAPI.setPlaceholders(player, "%" + string + "%");
    }


}

