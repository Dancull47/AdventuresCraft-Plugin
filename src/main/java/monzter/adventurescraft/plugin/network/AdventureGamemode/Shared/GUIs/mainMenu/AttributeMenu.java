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
import monzter.adventurescraft.plugin.utilities.enums.AdventureStatsDisplay;
import monzter.adventurescraft.plugin.utilities.enums.Prefix;
import monzter.adventurescraft.plugin.utilities.general.ConsoleCommand;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class AttributeMenu extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final SoundManager soundManager;
    private final GUIHelper guiHelper;
    private final ConsoleCommand consoleCommand;


    public AttributeMenu(AdventuresCraft plugin, SoundManager soundManager, GUIHelper guiHelper, ConsoleCommand consoleCommand) {
        this.plugin = plugin;
        this.soundManager = soundManager;
        this.guiHelper = guiHelper;
        this.consoleCommand = consoleCommand;
    }

    @CommandAlias("Attributes|AttributesMenu")
    public void attributeMenu(Player player) {

        ChestGui gui = new ChestGui(6, guiHelper.guiName("Attributes"));
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        OutlinePane background = new OutlinePane(0, 0, 9, 6, Pane.Priority.LOWEST);
        StaticPane display = new StaticPane(0, 0, 9, 6, Pane.Priority.LOW);
        OutlinePane outline = new OutlinePane(1, 2, 7, 5, Pane.Priority.LOW);

        background.addItem(new GuiItem(guiHelper.background(Material.LIGHT_BLUE_STAINED_GLASS_PANE)));
        background.setRepeat(true);

        int unusedPoints = Integer.valueOf(parsePlaceholder(player, "mmocore_attribute_points"));
        int unusedReallocationPoints = Integer.valueOf(parsePlaceholder(player, "mmocore_attribute_reallocation_points"));

        display.addItem(wizardly(player, unusedPoints), 2, 1);
        display.addItem(guardian(player, unusedPoints), 4, 1);
        display.addItem(slasher(player, unusedPoints), 6, 1);

        display.addItem(resetAttribute(player, unusedReallocationPoints), 4, 3);


        display.addItem(new GuiItem(guiHelper.backButton(), e -> player.performCommand("main")), 4, 5);

        gui.addPane(background);
        gui.addPane(display);
        gui.addPane(outline);
        gui.show(player);
    }

    public static DecimalFormat df = new DecimalFormat("#.##");

    private GuiItem wizardly(Player player, int unusedPoints) {
        final ItemStack itemStack = new ItemStack(Material.DIAMOND_HOE);
        final ItemMeta itemMeta = itemStack.getItemMeta();
        final String attribute = "Wizardly";
        final int level = Integer.valueOf(parsePlaceholder(player, "mmocore_attribute_" + attribute));
        final int nextLevel = level + 1;
        final int maxLevel = 10;

        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        itemMeta.setDisplayName(ChatColor.DARK_PURPLE + attribute);

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7Points Spent: &6" + level + "&7/&6" + maxLevel));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7Unused Attribute Points: &a" + unusedPoints));
        lore.add("");
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7+&6" + (10 * nextLevel) + " " + AdventureStatsDisplay.HP.getName()));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7+&6" + (25 * nextLevel) + " " + AdventureStatsDisplay.MANA.getName()));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7+&6" + (15 * nextLevel) + " " + AdventureStatsDisplay.MAGIC_DAMAGE.getName()));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7+&6" + df.format(0.03 * nextLevel) + " " + AdventureStatsDisplay.HP.getName() + " Regeneration"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7+&6" + df.format(0.045 * nextLevel) + " " + AdventureStatsDisplay.MANA.getName() + " Regeneration"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7+&6" + (10 * nextLevel) + " " + AdventureStatsDisplay.ARMOR.getName()));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7+&6" + (1 * nextLevel) + " " + AdventureStatsDisplay.COOLDOWN_REDUCTION.getName()));
        lore.add("");

        if (level == maxLevel)
            lore.add(ChatColor.translateAlternateColorCodes('&', "&cAttribute Maxed out"));
        else if (unusedPoints == 0)
            lore.add(ChatColor.translateAlternateColorCodes('&', "&cYou have &e0 &6Attribute Points"));
        else if (unusedPoints > 0 && level < maxLevel)
            lore.add(ChatColor.translateAlternateColorCodes('&', Prefix.PREFIX.getString() + "&eClick to Level Up"));

        itemStack.setItemMeta(itemMeta);
        itemStack.setLore(lore);

        return new GuiItem(itemStack, event -> {
            if (level == maxLevel) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou maxed this Attribute out"));
                soundManager.soundNo(player, 1);
            } else if (unusedPoints == 0) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou have &e0 &6Attribute Points"));
                soundManager.soundNo(player, 1);
            } else if (unusedPoints > 0 && level < maxLevel) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Attribute &aleveled up!"));
                soundManager.soundPurchase(player, 2);
                consoleCommand.consoleCommand("mmocore admin attribute-points set " + player.getName() + " " + (unusedPoints - 1));
                consoleCommand.consoleCommand("mmocore admin attribute give " + player.getName() + " " + attribute + " 1");
                player.performCommand("AttributesMenu");
            }
        });
    }

    private GuiItem guardian(Player player, int unusedPoints) {
        final ItemStack itemStack = new ItemStack(Material.DIAMOND_CHESTPLATE);
        final ItemMeta itemMeta = itemStack.getItemMeta();
        final String attribute = "Guardian";
        final int level = Integer.valueOf(parsePlaceholder(player, "mmocore_attribute_" + attribute));
        final int nextLevel = level + 1;
        final int maxLevel = 10;

        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        itemMeta.setDisplayName(ChatColor.YELLOW + attribute);

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7Points Spent: &6" + level + "&7/&6" + maxLevel));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7Unused Attribute Points: &a" + unusedPoints));
        lore.add("");
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7+&6" + (25 * nextLevel) + " " + AdventureStatsDisplay.HP.getName()));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7+&6" + (10 * nextLevel) + " " + AdventureStatsDisplay.MANA.getName()));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7+&6" + (5 * nextLevel) + " " + AdventureStatsDisplay.DAMAGE.getName()));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7+&6" + df.format(0.045 * nextLevel) + " " + AdventureStatsDisplay.HP.getName() + " Regeneration"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7+&6" + df.format(0.03 * nextLevel) + " " + AdventureStatsDisplay.MANA.getName() + " Regeneration"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7+&6" + (20 * nextLevel) + " " + AdventureStatsDisplay.ARMOR.getName()));
        lore.add("");

        if (level == maxLevel)
            lore.add(ChatColor.translateAlternateColorCodes('&', "&cAttribute Maxed out"));
        else if (unusedPoints == 0)
            lore.add(ChatColor.translateAlternateColorCodes('&', "&cYou have &e0 &6Attribute Points"));
        else if (unusedPoints > 0 && level < maxLevel)
            lore.add(ChatColor.translateAlternateColorCodes('&', Prefix.PREFIX.getString() + "&eClick to Level Up"));

        itemStack.setItemMeta(itemMeta);
        itemStack.setLore(lore);

        return new GuiItem(itemStack, event -> {
            if (level == maxLevel) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou maxed this Attribute out"));
                soundManager.soundNo(player, 1);
            } else if (unusedPoints == 0) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou have &e0 &6Attribute Points"));
                soundManager.soundNo(player, 1);
            } else if (unusedPoints > 0 && level < maxLevel) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Attribute &aleveled up!"));
                soundManager.soundPurchase(player, 2);
                consoleCommand.consoleCommand("mmocore admin attribute-points set " + player.getName() + " " + (unusedPoints - 1));
                consoleCommand.consoleCommand("mmocore admin attribute give " + player.getName() + " " + attribute + " 1");
                player.performCommand("AttributesMenu");
            }
        });
    }

    private GuiItem slasher(Player player, int unusedPoints) {
        final ItemStack itemStack = new ItemStack(Material.DIAMOND_SWORD);
        final ItemMeta itemMeta = itemStack.getItemMeta();
        final String attribute = "Slasher";
        final int level = Integer.valueOf(parsePlaceholder(player, "mmocore_attribute_" + attribute));
        final int nextLevel = level + 1;
        final int maxLevel = 10;

        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        itemMeta.setDisplayName(ChatColor.RED + attribute);

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7Points Spent: &6" + level + "&7/&6" + maxLevel));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7Unused Attribute Points: &a" + unusedPoints));
        lore.add("");
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7+&6" + (15 * nextLevel) + " " + AdventureStatsDisplay.HP.getName()));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7+&6" + (15 * nextLevel) + " " + AdventureStatsDisplay.MANA.getName()));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7+&6" + (10 * nextLevel) + " " + AdventureStatsDisplay.DAMAGE.getName()));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7+&6" + df.format(0.03 * nextLevel) + " " + AdventureStatsDisplay.HP.getName() + " Regeneration"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7+&6" + df.format(0.03 * nextLevel) + " " + AdventureStatsDisplay.MANA.getName() + " Regeneration"));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7+&6" + (10 * nextLevel) + " " + AdventureStatsDisplay.ARMOR.getName()));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7+&6" + (1.5 * nextLevel) + " " + AdventureStatsDisplay.CRITICAL_CHANCE.getName()));
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7+&6" + (2.5 * nextLevel) + " " + AdventureStatsDisplay.CRITICAL_DAMAGE.getName()));
        lore.add("");

        if (level == maxLevel)
            lore.add(ChatColor.translateAlternateColorCodes('&', "&cAttribute Maxed out"));
        else if (unusedPoints == 0)
            lore.add(ChatColor.translateAlternateColorCodes('&', "&cYou have &e0 &6Attribute Points"));
        else if (unusedPoints > 0 && level < maxLevel)
            lore.add(ChatColor.translateAlternateColorCodes('&', Prefix.PREFIX.getString() + "&eClick to Level Up"));

        itemStack.setItemMeta(itemMeta);
        itemStack.setLore(lore);

        return new GuiItem(itemStack, event -> {
            if (level == maxLevel) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou maxed this Attribute out"));
                soundManager.soundNo(player, 1);
            } else if (unusedPoints == 0) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou have &e0 &6Attribute Points"));
                soundManager.soundNo(player, 1);
            } else if (unusedPoints > 0 && level < maxLevel) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Attribute &aleveled up!"));
                soundManager.soundPurchase(player, 2);
                consoleCommand.consoleCommand("mmocore admin attribute-points set " + player.getName() + " " + (unusedPoints - 1));
                consoleCommand.consoleCommand("mmocore admin attribute give " + player.getName() + " " + attribute + " 1");
                player.performCommand("AttributesMenu");
            }
        });
    }

    private GuiItem resetAttribute(Player player, int reallocationPoints) {
        final ItemStack itemStack = new ItemStack(Material.ANVIL);
        final ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        itemMeta.setDisplayName(ChatColor.GREEN + "Reallocate");

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.translateAlternateColorCodes('&', "&7Reallocation Points: &a" + reallocationPoints));
        lore.add("");
//        lore.add(ChatColor.translateAlternateColorCodes('&', "&7Points Spent: &6" + level + "&7/&6" + maxLevel));

        if (reallocationPoints == 0)
            lore.add(ChatColor.translateAlternateColorCodes('&', "&cYou have &e0 &6Reallocation Points"));
        else
            lore.add(ChatColor.translateAlternateColorCodes('&', Prefix.PREFIX.getString() + "&eClick to Reset Attributes"));

        itemStack.setItemMeta(itemMeta);
        itemStack.setLore(lore);

        return new GuiItem(itemStack, event -> {
            if (reallocationPoints == 0) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "cYou have &e0 &6Reallocation Points"));
                soundManager.soundNo(player, 1);
            } else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Attributes &ehave been reset!"));
                soundManager.soundPurchase(player, 2);
//                Set Player's Attribute Points to spent points + current points
//                consoleCommand.consoleCommand("mmocore admin attribute-points set " + player.getName() + " " + (unusedPoints - 1));
                consoleCommand.consoleCommand("mmocore admin attr-realloc-points set " + player.getName() + " " + (reallocationPoints - 1));
                player.performCommand("AttributesMenu");
            }
        });
    }

    private String parsePlaceholder(Player player, String string) {
        return PlaceholderAPI.setPlaceholders(player, "%" + string + "%");
    }

}

