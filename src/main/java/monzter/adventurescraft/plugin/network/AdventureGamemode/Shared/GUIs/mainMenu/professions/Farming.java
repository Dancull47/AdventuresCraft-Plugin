package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.mainMenu.professions;

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
import monzter.adventurescraft.plugin.utilities.enums.Prefix;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import monzter.adventurescraft.plugin.utilities.text.ProgressBar;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Farming extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final SoundManager soundManager;
    private final GUIHelper guiHelper;
    private final ProgressBar progressBar;
    private final String KNOWLEDGE = "  " + ChatColor.GOLD + ChatColor.BOLD + "KNOWLEDGE" + ChatColor.WHITE + ":";


    public Farming(AdventuresCraft plugin, SoundManager soundManager, GUIHelper guiHelper, ProgressBar progressBar) {
        this.plugin = plugin;
        this.soundManager = soundManager;
        this.guiHelper = guiHelper;
        this.progressBar = progressBar;
    }

    @CommandAlias("farming")
    public void profession(Player player) {

        ChestGui gui = new ChestGui(6, guiHelper.guiName("Farming"));
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        OutlinePane background = new OutlinePane(0, 0, 9, 6, Pane.Priority.LOWEST);
        StaticPane pane = new StaticPane(0, 0, 9, 6, Pane.Priority.LOW);
        OutlinePane display = new OutlinePane(2, 2, 5, 2, Pane.Priority.LOW);

        background.addItem(new GuiItem(guiHelper.background(Material.LIME_STAINED_GLASS_PANE)));
        background.setRepeat(true);

        int level = Integer.valueOf(parsePlaceholder(player, "mmocore_profession_Farming"));

        int i = 1;
        int amount = 0;
        for (FarmingLevels reward : FarmingLevels.values()) {
            ItemStack itemStack = new ItemStack(Material.WOODEN_HOE);
            final ItemMeta itemMeta = itemStack.getItemMeta();

            itemMeta.setDisplayName(ChatColor.YELLOW + "Farming Level " + i);

            List<String> lore = new ArrayList<>();
            lore.add(" ");
            lore.add(KNOWLEDGE);
            for (ItemStack itemStack1 : reward.getRewards()) {
                plugin.getLogger().info(itemStack1.getItemMeta().getDisplayName());
                lore.add("   " + Prefix.PREFIX.getString() + itemStack1.getItemMeta().getDisplayName());
                amount++;
            }
            amount = 0;

            if (level < Integer.valueOf(reward.getLevel())) {
                itemMeta.setDisplayName(ChatColor.GREEN + "Farming Level " + i + ChatColor.DARK_GRAY + " -" + ChatColor.RED + ChatColor.BOLD + " LOCKED");
                lore.add(" ");
                lore.add(ChatColor.RED.toString() + ChatColor.BOLD + "LOCKED");
            } else {
                itemStack.setType(Material.STONE_HOE);
                itemMeta.setDisplayName(ChatColor.GREEN + "Farming Level " + i + ChatColor.DARK_GRAY + " -" + ChatColor.GREEN + ChatColor.BOLD + " UNLOCKED");
                lore.add(" ");
                lore.add(ChatColor.GREEN.toString() + ChatColor.BOLD + "UNLOCKED");
            }

            itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

            itemMeta.setLore(lore);
            itemStack.setItemMeta(itemMeta);

            display.addItem(new GuiItem(itemStack));
            i++;
        }


        pane.addItem(new GuiItem(guiHelper.backButton(), e -> player.performCommand("professions")), 4, 5);
        pane.addItem(new GuiItem(farming(player)), 4, 1);

        gui.addPane(background);
        gui.addPane(display);
        gui.addPane(pane);
        gui.show(player);
    }

    private ItemStack farming(Player player) {
        int level = Integer.valueOf(parsePlaceholder(player, "mmocore_profession_Farming"));
        String name = ChatColor.GREEN + "Farming" + " Level " + ChatColor.GOLD + level;
        return guiHelper.itemCreator(Material.DIAMOND_HOE, name, new String[]{
                ChatColor.GRAY + "Help around the Farm, and",
                ChatColor.GRAY + "harvest crops to earn XP!",
                "",
                ChatColor.WHITE + "Progress to Level " + ChatColor.GOLD + Integer.valueOf(level + 1),
                progressBar.getProgressBar(Integer.valueOf(parsePlaceholder(player, "mmocore_profession_experience_Farming")), Integer.valueOf(parsePlaceholder(player, "mmocore_profession_next_level_Farming")), 13, '-', ChatColor.YELLOW, ChatColor.GRAY) + " " + ChatColor.GOLD + parsePlaceholder(player, "mmocore_profession_percent_Farming") + "%",
                ChatColor.GOLD + parsePlaceholder(player, "mmocore_profession_experience_Farming") + ChatColor.WHITE + " / " + ChatColor.GOLD + parsePlaceholder(player, "mmocore_profession_next_level_Farming") + ChatColor.GRAY + " EXP"
                });
    }

    private String parsePlaceholder(Player player, String string) {
        return PlaceholderAPI.setPlaceholders(player, "%" + string + "%");
    }

}

