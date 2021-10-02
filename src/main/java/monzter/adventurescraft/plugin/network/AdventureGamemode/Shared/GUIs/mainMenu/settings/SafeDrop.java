package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.mainMenu.settings;

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
import monzter.adventurescraft.plugin.utilities.enums.Rarity;
import monzter.adventurescraft.plugin.utilities.general.ConsoleCommand;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import monzter.adventurescraft.plugin.utilities.luckperms.PermissionLP;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class SafeDrop extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final SoundManager soundManager;
    private final GUIHelper guiHelper;
    private final ConsoleCommand consoleCommand;
    private final PermissionLP permissionLP;
    private final String description = ChatColor.GRAY + "Prevents from being quickly dropped!";

    public SafeDrop(AdventuresCraft plugin, SoundManager soundManager, GUIHelper guiHelper, ConsoleCommand consoleCommand, PermissionLP permissionLP) {
        this.plugin = plugin;
        this.soundManager = soundManager;
        this.guiHelper = guiHelper;
        this.consoleCommand = consoleCommand;
        this.permissionLP = permissionLP;
    }

    @CommandAlias("safeDrop|safeDropMenu")
    public void setting(Player player) {
        ChestGui gui = new ChestGui(4, guiHelper.guiName("Safe AccountBound"));
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        OutlinePane background = new OutlinePane(0, 0, 9, 4, Pane.Priority.LOWEST);
        StaticPane display = new StaticPane(0, 0, 9, 4, Pane.Priority.LOW);


        background.addItem(new GuiItem(guiHelper.background(Material.YELLOW_STAINED_GLASS_PANE)));
        background.setRepeat(true);


        display.addItem(new GuiItem(common(player), e -> {
            if (player.hasPermission("DropPerm1"))
                permissionLP.takePermission(player, "DropPerm1");
            else
                permissionLP.givePermission(player, "DropPerm1");
            setting(player);
        }), 2, 1);
        display.addItem(new GuiItem(uncommon(player), e -> {
            if (player.hasPermission("DropPerm2"))
                permissionLP.takePermission(player, "DropPerm2");
            else
                permissionLP.givePermission(player, "DropPerm2");
            setting(player);
        }), 3, 1);
        display.addItem(new GuiItem(rare(player), e -> {
            if (player.hasPermission("DropPerm3"))
                permissionLP.takePermission(player, "DropPerm3");
            else
                permissionLP.givePermission(player, "DropPerm3");
            setting(player);
        }), 4, 1);
        display.addItem(new GuiItem(legendary(player), e -> {
            if (player.hasPermission("DropPerm4"))
                permissionLP.takePermission(player, "DropPerm4");
            else
                permissionLP.givePermission(player, "DropPerm4");
            setting(player);
        }), 5, 1);
        display.addItem(new GuiItem(exotic(player), e -> {
            if (player.hasPermission("DropPerm5"))
                permissionLP.takePermission(player, "DropPerm5");
            else
                permissionLP.givePermission(player, "DropPerm5");
            setting(player);
        }), 6, 1);

        display.addItem(new GuiItem(guiHelper.backButton(), e -> player.performCommand("setting")), 4, 3);

        gui.addPane(background);
        gui.addPane(display);
        gui.show(player);
    }


    private ItemStack common(Player player) {
        final ItemStack common = new ItemStack(Material.STICK);
        final ItemMeta commonItemMeta = common.getItemMeta();
        commonItemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        if (player.hasPermission("DropPerm1")) {
            commonItemMeta.displayName(Component.text(Rarity.COMMON.getColorString() + ChatColor.BOLD + "Common Item" + ChatColor.GREEN + " ON"));
        } else {
            commonItemMeta.displayName(Component.text(Rarity.COMMON.getColorString() + ChatColor.BOLD + "Common Item" + ChatColor.RED + " OFF"));
        }

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(description);
        lore.add("");
        if (player.hasPermission("DropPerm1")) {
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Disable");
        } else {
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Enable");
        }


        common.setItemMeta(commonItemMeta);
        common.setLore(lore);

        return common;
    }

    private ItemStack uncommon(Player player) {
        final ItemStack uncommon = new ItemStack(Material.WOODEN_SWORD);
        final ItemMeta uncommonItemMeta = uncommon.getItemMeta();
        uncommonItemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        if (player.hasPermission("DropPerm2")) {
            uncommonItemMeta.displayName(Component.text(Rarity.UNCOMMON.getColorString() + ChatColor.BOLD + "Uncommon Item" + ChatColor.GREEN + " ON"));
        } else {
            uncommonItemMeta.displayName(Component.text(Rarity.UNCOMMON.getColorString() + ChatColor.BOLD + "Uncommon Item" + ChatColor.RED + " OFF"));
        }

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(description);
        lore.add("");
        if (player.hasPermission("DropPerm2")) {
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Disable");
        } else {
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Enable");
        }


        uncommon.setItemMeta(uncommonItemMeta);
        uncommon.setLore(lore);

        return uncommon;
    }
    private ItemStack rare(Player player) {
        final ItemStack rare = new ItemStack(Material.STONE_SWORD);
        final ItemMeta rareItemMeta = rare.getItemMeta();
        rareItemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        if (player.hasPermission("DropPerm3")) {
            rareItemMeta.displayName(Component.text(Rarity.RARE.getColorString() + ChatColor.BOLD + "Rare Item" + ChatColor.GREEN + " ON"));
        } else {
            rareItemMeta.displayName(Component.text(Rarity.RARE.getColorString() + ChatColor.BOLD + "Rare Item" + ChatColor.RED + " OFF"));
        }

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(description);
        lore.add("");
        if (player.hasPermission("DropPerm3")) {
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Disable");
        } else {
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Enable");
        }


        rare.setItemMeta(rareItemMeta);
        rare.setLore(lore);

        return rare;
    }
    private ItemStack legendary(Player player) {
        final ItemStack legendary = new ItemStack(Material.IRON_SWORD);
        final ItemMeta legendaryItemMeta = legendary.getItemMeta();
        legendaryItemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        if (player.hasPermission("DropPerm4")) {
            legendaryItemMeta.displayName(Component.text(Rarity.LEGENDARY.getColorString() + ChatColor.BOLD + "Legendary Item" + ChatColor.GREEN + " ON"));
        } else {
            legendaryItemMeta.displayName(Component.text(Rarity.LEGENDARY.getColorString() + ChatColor.BOLD + "Legendary Item" + ChatColor.RED + " OFF"));
        }

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(description);
        lore.add("");
        if (player.hasPermission("DropPerm4")) {
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Disable");
        } else {
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Enable");
        }


        legendary.setItemMeta(legendaryItemMeta);
        legendary.setLore(lore);

        return legendary;
    }
    private ItemStack exotic(Player player) {
        final ItemStack exotic = new ItemStack(Material.GOLDEN_SWORD);
        final ItemMeta exoticItemMeta = exotic.getItemMeta();
        exoticItemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        if (player.hasPermission("DropPerm5")) {
            exoticItemMeta.displayName(Component.text(Rarity.EXOTIC.getColorString() + ChatColor.BOLD + "Exotic Item" + ChatColor.GREEN + " ON"));
        } else {
            exoticItemMeta.displayName(Component.text(Rarity.EXOTIC.getColorString() + ChatColor.BOLD + "Exotic Item" + ChatColor.RED + " OFF"));
        }

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(description);
        lore.add("");
        if (player.hasPermission("DropPerm5")) {
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Disable");
        } else {
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Enable");
        }


        exotic.setItemMeta(exoticItemMeta);
        exotic.setLore(lore);

        return exotic;
    }
}

