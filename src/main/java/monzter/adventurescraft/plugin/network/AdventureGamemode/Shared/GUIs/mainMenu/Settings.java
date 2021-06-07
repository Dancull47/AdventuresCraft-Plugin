package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.mainMenu;

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

    public Settings(AdventuresCraft plugin, SoundManager soundManager, GUIHelper guiHelper, ConsoleCommand consoleCommand, PermissionLP permissionLP) {
        this.plugin = plugin;
        this.soundManager = soundManager;
        this.guiHelper = guiHelper;
        this.consoleCommand = consoleCommand;
        this.permissionLP = permissionLP;
    }

    @CommandAlias("setting|settingMenu|settings")
    public void setting(Player player) {
        ChestGui gui = new ChestGui(5, guiHelper.guiName("Settings"));
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        OutlinePane background = new OutlinePane(0, 0, 9, 5, Pane.Priority.LOWEST);
        StaticPane display = new StaticPane(0, 0, 9, 5, Pane.Priority.LOW);


        background.addItem(new GuiItem(guiHelper.background(Material.GRAY_STAINED_GLASS_PANE)));
        background.setRepeat(true);


        display.addItem(new GuiItem(stuck(player), e -> player.performCommand("spawn")), 2, 1);
        display.addItem(new GuiItem(battleTag(player), e -> player.performCommand("BattleTags")), 3, 1);
        display.addItem(new GuiItem(questCompass(player), e -> {
            if (e.isLeftClick()) {
                player.performCommand("calebcompass show");
            } else if (e.isRightClick()) {
                player.performCommand("calebcompass hide");
            }
        }), 4, 1);
        display.addItem(new GuiItem(tips(player), e -> {
            if (player.hasPermission("TIPS"))
                permissionLP.takePermission(player, "TIPS");
            else
                permissionLP.givePermission(player, "TIPS");
            setting(player);
        }), 5, 1);
        display.addItem(new GuiItem(safeDrop(player), e -> player.performCommand("safeDrop")), 6, 1);

        display.addItem(new GuiItem(resourcePack(player), e -> {
            if (player.hasPermission("RP.DOWNLOAD"))
                permissionLP.takePermission(player, "RP.DOWNLOAD");
            else
                permissionLP.givePermission(player, "RP.DOWNLOAD");
            setting(player);

        }), 3, 2);

        display.addItem(new GuiItem(music(player), e -> {
            if (player.hasPermission("Music.ON"))
                permissionLP.takePermission(player, "Music.ON");
            else
                permissionLP.givePermission(player, "Music.ON");
            setting(player);

        }), 5, 2);

        display.addItem(new GuiItem(guiHelper.backButton(), e -> player.performCommand("main")), 4, 4);

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
        lore.add(ChatColor.GRAY + "Return to the " + ChatColor.YELLOW + "Town!");
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Travel");

        stuck.setItemMeta(stuckItemMeta);
        stuck.setLore(lore);

        return stuck;
    }

    private ItemStack battleTag(Player player) {
        final ItemStack battleTag = new ItemStack(Material.NAME_TAG);
        final ItemMeta battleTagItemMeta = battleTag.getItemMeta();

        battleTagItemMeta.displayName(Component.text(ChatColor.GREEN + "Battle Tags"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "You can earn " + ChatColor.YELLOW + "Battle Tags " + ChatColor.GRAY + "while");
        lore.add(ChatColor.YELLOW + "Adventuring " + ChatColor.GRAY + "and " + ChatColor.YELLOW + "earning Achievements" + ChatColor.GRAY + "!");
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View");

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
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View");

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
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Disable");
        } else {
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Enable");
        }

        tips.setItemMeta(tipsItemMeta);
        tips.setLore(lore);

        return tips;
    }

    private ItemStack questCompass(Player player) {
        final ItemStack tips = new ItemStack(Material.COMPASS);
        final ItemMeta tipsItemMeta = tips.getItemMeta();

        tipsItemMeta.displayName(Component.text(ChatColor.GREEN + "Quest Compass"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Left-Click to Enable");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Right-Click to Disable");

        tips.setItemMeta(tipsItemMeta);
        tips.setLore(lore);

        return tips;
    }

    private ItemStack resourcePack(Player player) {
        final ItemStack safeDrop = new ItemStack(Material.PAINTING);
        final ItemMeta safeDropItemMeta = safeDrop.getItemMeta();

        safeDropItemMeta.displayName(Component.text(ChatColor.GREEN + "Resource Pack"));
        if (player.hasPermission("RP.DOWNLOAD"))
            safeDropItemMeta.displayName(Component.text(ChatColor.GREEN + "Resource Pack" + ChatColor.DARK_GRAY + " - " + ChatColor.GREEN + "ON"));
        else
            safeDropItemMeta.displayName(Component.text(ChatColor.GREEN + "Resource Pack" + ChatColor.DARK_GRAY + " - " + ChatColor.RED + "OFF"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "Download the Resource Pack upon logging in!");
        lore.add("");
        if (player.hasPermission("RP.DOWNLOAD"))
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Disable");
        else
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Enable");

        safeDrop.setItemMeta(safeDropItemMeta);
        safeDrop.setLore(lore);

        return safeDrop;
    }

    private ItemStack music(Player player) {
        final ItemStack safeDrop = new ItemStack(Material.JUKEBOX);
        final ItemMeta safeDropItemMeta = safeDrop.getItemMeta();

        safeDropItemMeta.displayName(Component.text(ChatColor.GREEN + "Music"));
        if (player.hasPermission("Music.ON"))
            safeDropItemMeta.displayName(Component.text(ChatColor.GREEN + "Music" + ChatColor.DARK_GRAY + " - " + ChatColor.GREEN + "ON"));
        else
            safeDropItemMeta.displayName(Component.text(ChatColor.GREEN + "Music" + ChatColor.DARK_GRAY + " - " + ChatColor.RED + "OFF"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "Download the Music upon logging in!");
        lore.add("");
        if (player.hasPermission("Music.ON"))
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Disable");
        else
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Enable");

        safeDrop.setItemMeta(safeDropItemMeta);
        safeDrop.setLore(lore);

        return safeDrop;
    }

}

