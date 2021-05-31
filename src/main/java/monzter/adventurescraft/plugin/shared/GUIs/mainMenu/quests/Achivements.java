package monzter.adventurescraft.plugin.shared.GUIs.mainMenu.quests;

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
import monzter.adventurescraft.plugin.shared.GUIs.mainMenu.quests.achievements.Achievements;
import monzter.adventurescraft.plugin.utilities.GUI.GUIHelper;
import monzter.adventurescraft.plugin.utilities.beton.BetonPointsManager;
import monzter.adventurescraft.plugin.utilities.enums.Prefix;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import monzter.adventurescraft.plugin.utilities.text.NumberFormat;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Achivements extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final SoundManager soundManager;
    private final GUIHelper guiHelper;
    private final NumberFormat numberFormat;
    private final BetonPointsManager betonPointsManager;
    private final String REWARD = "  " + ChatColor.GOLD + ChatColor.BOLD + "REWARDS" + ChatColor.WHITE + ":";
    final TextComponent miningPass = Component.text("You haven't purchased the ")
            .color(NamedTextColor.RED)
            .append(Component.text("Premium ", NamedTextColor.GOLD, TextDecoration.BOLD))
            .append(Component.text("Mining Pass ", NamedTextColor.YELLOW, TextDecoration.BOLD))
            .append(Component.text("which is required to claim this reward!"))
            .append(Component.text(" <- CLICK HERE TO PURCHASE", NamedTextColor.GOLD, TextDecoration.BOLD))
            .hoverEvent(Component.text("Click to visit the Store's Battle Pass!", NamedTextColor.GREEN))
            .clickEvent(ClickEvent.openUrl("https://store.adventurescraft.net"));

    public Achivements(AdventuresCraft plugin, SoundManager soundManager, GUIHelper guiHelper, NumberFormat numberFormat, BetonPointsManager betonPointsManager) {
        this.plugin = plugin;
        this.soundManager = soundManager;
        this.guiHelper = guiHelper;
        this.numberFormat = numberFormat;
        this.betonPointsManager = betonPointsManager;
    }

    @CommandAlias("Achievements|Achievement")
    public void donate(Player player) {
        donate(player, 0);
    }

    public void donate(Player player, int openPage) {
        ChestGui gui = new ChestGui(4, guiHelper.guiName("Achievements"));
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        OutlinePane background = new OutlinePane(0, 0, 9, 4, Pane.Priority.LOWEST);
        StaticPane display = new StaticPane(0, 0, 9, 4, Pane.Priority.LOW);


        background.addItem(new GuiItem(guiHelper.background(Material.GREEN_STAINED_GLASS_PANE)));
        background.setRepeat(true);

        display.addItem(totalBlocks(player), 3, 1);
        display.addItem(ores(player), 5, 1);

        display.addItem(new GuiItem(guiHelper.backButton(), e -> player.performCommand("quests")), 4, 3);

        gui.addPane(background);
        gui.addPane(display);
        gui.show(player);
    }

    private GuiItem totalBlocks(Player player) {
        int totalBlocks = Integer.valueOf(parsePlaceholder(player, "betonquest_items:point.TotalBlocks.amount"));
        Material material = Material.WOODEN_PICKAXE;
        String name = ChatColor.GOLD + "Noob Miner";
        String[] description = new String[]{ChatColor.WHITE + "Blocks Mined: " + ChatColor.GREEN + numberFormat.numberFormat(totalBlocks)};
        for (Achievements achievement : Achievements.values()) {
            if (achievement.getGroup().equals("Miner"))
                if (totalBlocks >= achievement.getPrice()) {
                    material = achievement.getMaterial();
                    name = ChatColor.GOLD + achievement.getName();
                }
        }
        return new GuiItem(item(material, name, description), e -> player.performCommand("AchievementsMiner"));
    }

    private GuiItem ores(Player player) {
        int total = Integer.valueOf(parsePlaceholder(player, "ac_Achievement_Ores"));

        Material material = Material.COAL_ORE;
        String name = ChatColor.GOLD + "Noob Ores";
        String[] description = new String[]{ChatColor.WHITE + "Ores Mined: " + ChatColor.GREEN + numberFormat.numberFormat(total)};
        for (Achievements achievement : Achievements.values()) {
            if (achievement.getGroup().equals("Ore"))
                if (total >= achievement.getPrice()) {
                    material = achievement.getMaterial();
                    name = ChatColor.GOLD + achievement.getName();
                }
        }
        return new GuiItem(item(material, name, description), e -> player.performCommand("AchievementOres"));
    }

    public ItemStack item(Material material, String displayName, String[] itemLore) {
        ItemStack complete = new ItemStack(material);
        final ItemMeta completeItemMeta = complete.getItemMeta();

        completeItemMeta.displayName(Component.text(displayName));

        List<String> lore = new ArrayList<>();
        lore.add("");
        for (String lore2 : itemLore)
            lore.add(lore2);
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View Rewards");

        completeItemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        complete.setItemMeta(completeItemMeta);
        complete.setLore(lore);

        return complete;
    }

    private String parsePlaceholder(Player player, String string) {
        return PlaceholderAPI.setPlaceholders(player, "%" + string + "%");
    }
}
