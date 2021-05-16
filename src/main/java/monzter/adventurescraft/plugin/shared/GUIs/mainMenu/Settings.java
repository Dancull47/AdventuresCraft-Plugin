package monzter.adventurescraft.plugin.shared.GUIs.mainMenu;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Dependency;
import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.GUI.GUIHelper;
import monzter.adventurescraft.plugin.utilities.enums.Prefix;
import monzter.adventurescraft.plugin.utilities.general.ConsoleCommand;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import monzter.adventurescraft.plugin.utilities.luckperms.PermissionLP;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Settings extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final SoundManager soundManager;
    private final GUIHelper guiHelper;
    private final ConsoleCommand consoleCommand;
    private final PermissionLP permissionLP;

    final TextComponent setting = Component.text("You can setting to get epic rewards from our")
            .color(NamedTextColor.GREEN)
            .append(Component.text(" Store", NamedTextColor.GOLD))
            .append(Component.text("!"))
            .append(Component.text(" <- CLICK HERE", NamedTextColor.GOLD, TextDecoration.BOLD))
            .hoverEvent(Component.text("Click to visit the Store!", NamedTextColor.GREEN))
            .clickEvent(ClickEvent.openUrl("https://store.adventurescraft.net"));

    final TextComponent miningPass = Component.text("Earn ")
            .color(NamedTextColor.GREEN)
            .append(Component.text(" Special Rewards ", NamedTextColor.GOLD))
            .append(Component.text("while mining through the Prison!"))
            .append(Component.text(" <- CLICK HERE", NamedTextColor.GOLD, TextDecoration.BOLD))
            .hoverEvent(Component.text("Click to visit the Store's Battle Pass!", NamedTextColor.GREEN))
            .clickEvent(ClickEvent.openUrl("https://store.adventurescraft.net"));

    public Settings(AdventuresCraft plugin, SoundManager soundManager, GUIHelper guiHelper, ConsoleCommand consoleCommand, PermissionLP permissionLP) {
        this.plugin = plugin;
        this.soundManager = soundManager;
        this.guiHelper = guiHelper;
        this.consoleCommand = consoleCommand;
        this.permissionLP = permissionLP;
    }

    @CommandAlias("setting|settingMenu|settings")
    public void setting(Player player) {
        ChestGui gui = new ChestGui(4, guiHelper.guiName("Settings"));
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        OutlinePane background = new OutlinePane(0, 0, 9, 4, Pane.Priority.LOWEST);
        StaticPane display = new StaticPane(0, 0, 9, 4, Pane.Priority.LOW);


        background.addItem(new GuiItem(guiHelper.background(Material.ORANGE_STAINED_GLASS_PANE)));
        background.setRepeat(true);


        display.addItem(new GuiItem(stuck(player), e -> player.performCommand("warp Yard")), 2, 1);
        display.addItem(new GuiItem(battleTag(player), e -> player.performCommand("BattleTags")), 3, 1);
        display.addItem(new GuiItem(tips(player), e -> {
            if (player.hasPermission("TIPS"))
                permissionLP.takePermission(player, "TIPS");
            else
                permissionLP.givePermission(player, "TIPS");
            setting(player);
        }), 5, 1);
        display.addItem(new GuiItem(safeDrop(player), e -> player.performCommand("safeDrop")), 6, 1);

        display.addItem(new GuiItem(guiHelper.backButton(), e -> player.performCommand("main")), 4, 3);

        gui.addPane(background);
        gui.addPane(display);
        gui.show(player);
    }


    private ItemStack stuck(Player player) {
        final ItemStack stuck = new ItemStack(Material.OAK_DOOR);
        final ItemMeta stuckItemMeta = stuck.getItemMeta();

        stuckItemMeta.displayName(Component.text(ChatColor.RED + "I'm Stuck"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "Return to the " + ChatColor.YELLOW + "Yard!");
        lore.add("");
        lore.add(Prefix.PREFIX.getPrefix() + ChatColor.YELLOW + "Click to Travel");

        stuck.setItemMeta(stuckItemMeta);
        stuck.setLore(lore);

        return stuck;
    }

    private ItemStack battleTag(Player player) {
        final ItemStack battleTag = new ItemStack(Material.NAME_TAG);
        final ItemMeta battleTagItemMeta = battleTag.getItemMeta();

        battleTagItemMeta.displayName(Component.text(ChatColor.GREEN + "Explorer Tags"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "You can earn " + ChatColor.YELLOW + "Explorer Tags " + ChatColor.GRAY + "while");
        lore.add(ChatColor.YELLOW + "Exploring " + ChatColor.GRAY + "and " + ChatColor.YELLOW + "earning Achievements" + ChatColor.GRAY + "!");
        lore.add("");
        lore.add(Prefix.PREFIX.getPrefix() + ChatColor.YELLOW + "Click to View");

        battleTag.setItemMeta(battleTagItemMeta);
        battleTag.setLore(lore);

        return battleTag;
    }

    private ItemStack safeDrop(Player player) {
        final ItemStack safeDrop = new ItemStack(Material.BUCKET);
        final ItemMeta safeDropItemMeta = safeDrop.getItemMeta();

        safeDropItemMeta.displayName(Component.text(ChatColor.GREEN + "Safe Drop"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "Manage your " + ChatColor.YELLOW + "Safe Drop" + ChatColor.GRAY + "!");
        lore.add("");
        lore.add(Prefix.PREFIX.getPrefix() + ChatColor.YELLOW + "Click to View");

        safeDrop.setItemMeta(safeDropItemMeta);
        safeDrop.setLore(lore);

        return safeDrop;
    }

    private ItemStack tips(Player player) {
        final ItemStack tips = new ItemStack(Material.OAK_SIGN);
        final ItemMeta tipsItemMeta = tips.getItemMeta();

        if (player.hasPermission("TIPS")) {
            tipsItemMeta.displayName(Component.text(ChatColor.GREEN + "Tips" + ChatColor.GREEN + " ON"));
        } else {
            tipsItemMeta.displayName(Component.text(ChatColor.GREEN + "Tips" + ChatColor.RED + " OFF"));
        }

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "Receive " + ChatColor.GREEN + "Tips" + ChatColor.GRAY + " occasionally in Chat.");
        lore.add("");
        if (player.hasPermission("TIPS")) {
            lore.add(Prefix.PREFIX.getPrefix() + ChatColor.YELLOW + "Click to Disable");
        } else {
            lore.add(Prefix.PREFIX.getPrefix() + ChatColor.YELLOW + "Click to Enable");
        }

        tips.setItemMeta(tipsItemMeta);
        tips.setLore(lore);

        return tips;
    }
}

