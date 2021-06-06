package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.mainMenu.map;

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
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class MineMap extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final GUIHelper guiHelper;
    private final String LOCKED_TEXT = ChatColor.DARK_GRAY + "- " + ChatColor.RED + ChatColor.BOLD + "LOCKED";

    public MineMap(AdventuresCraft plugin, GUIHelper guiHelper) {
        this.plugin = plugin;
        this.guiHelper = guiHelper;
    }

    @CommandAlias("mineMap|mineMaps")
    public void questMenu(Player player) {

        ChestGui gui = new ChestGui(4, guiHelper.guiName("Mine Map"));
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        OutlinePane background = new OutlinePane(0, 0, 9, 4, Pane.Priority.LOWEST);
        StaticPane display = new StaticPane(0, 0, 9, 4, Pane.Priority.LOW);


        background.addItem(new GuiItem(guiHelper.background(Material.GRAY_STAINED_GLASS_PANE)));
        background.setRepeat(true);

        display.addItem(new GuiItem(mineEntrance(), e -> player.performCommand("warp mineEntrance")), 1, 1);
        display.addItem(new GuiItem(coalMine(), e -> player.performCommand("warp coalMine")), 2, 1);
        display.addItem(new GuiItem(goldMine(player), e -> player.performCommand("warp goldMine")), 3, 1);
        display.addItem(new GuiItem(redStoneMine(player), e -> player.performCommand("warp redstoneMine")), 4, 1);
        display.addItem(new GuiItem(lapisMine(player), e -> player.performCommand("warp lapisMine")), 5, 1);
        display.addItem(new GuiItem(diamondMine(player), e -> player.performCommand("warp diamondMine")), 6, 1);
        display.addItem(new GuiItem(emeraldMine(player), e -> player.performCommand("warp emeraldMine")), 7, 1);

        display.addItem(new GuiItem(guiHelper.backButton(), e -> player.performCommand("maps")), 4, 3);

        gui.addPane(background);
        gui.addPane(display);
        gui.show(player);
    }

    private ItemStack mineEntrance() {
        final ItemStack coalMine = new ItemStack(Material.MINECART);
        final ItemMeta coalMineItemMeta = coalMine.getItemMeta();

        coalMineItemMeta.displayName(Component.text(ChatColor.GREEN + "Mine Entrance"));

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_GRAY + "/warp mineEntrance");
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Travel");

        coalMine.setItemMeta(coalMineItemMeta);
        coalMine.setLore(lore);

        return coalMine;
    }

    private ItemStack coalMine() {
        final ItemStack coalMine = new ItemStack(Material.COAL);
        final ItemMeta coalMineItemMeta = coalMine.getItemMeta();

        coalMineItemMeta.displayName(Component.text(ChatColor.GREEN + "Coal Mine"));

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_GRAY + "/warp coalMine");
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Travel");

        coalMine.setItemMeta(coalMineItemMeta);
        coalMine.setLore(lore);

        return coalMine;
    }

    private ItemStack goldMine(Player player) {
        ItemStack goldMine = new ItemStack(Material.GOLD_INGOT);
        if (!player.hasPermission("warp.goldmine"))
            goldMine = new ItemStack(Material.RED_STAINED_GLASS_PANE);

        final ItemMeta goldMineItemMeta = goldMine.getItemMeta();

        goldMineItemMeta.displayName(Component.text(ChatColor.GREEN + "Gold Mine "));
        if (!player.hasPermission("warp.goldmine"))
            goldMineItemMeta.displayName(Component.text(goldMineItemMeta.getDisplayName() + LOCKED_TEXT));

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_GRAY + "/warp goldMine");
        lore.add("");
        if (!player.hasPermission("warp.goldmine")) {
            lore.add(ChatColor.RED.toString() + ChatColor.BOLD + "LOCKED");
            lore.add(ChatColor.GREEN + "Quest" + ChatColor.WHITE + ": " + ChatColor.YELLOW + "Meet Morry");
        } else
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Travel");

        goldMine.setItemMeta(goldMineItemMeta);
        goldMine.setLore(lore);

        return goldMine;
    }
    private ItemStack redStoneMine(Player player) {
        ItemStack redStoneMine = new ItemStack(Material.REDSTONE);
        if (!player.hasPermission("warp.redstonemine"))
            redStoneMine = new ItemStack(Material.RED_STAINED_GLASS_PANE);

        final ItemMeta redStoneMineItemMeta = redStoneMine.getItemMeta();

        redStoneMineItemMeta.displayName(Component.text(ChatColor.GREEN + "Redstone Mine "));
        if (!player.hasPermission("warp.redstonemine"))
            redStoneMineItemMeta.displayName(Component.text(redStoneMineItemMeta.getDisplayName() + LOCKED_TEXT));

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_GRAY + "/warp redStoneMine");
        lore.add("");
        if (!player.hasPermission("warp.redstonemine")) {
            lore.add(ChatColor.RED.toString() + ChatColor.BOLD + "LOCKED");
            lore.add(ChatColor.GREEN + "Quest" + ChatColor.WHITE + ": " + ChatColor.YELLOW + "Travel to Redstone");
        } else
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Travel");

        redStoneMine.setItemMeta(redStoneMineItemMeta);
        redStoneMine.setLore(lore);

        return redStoneMine;
    }
    private ItemStack lapisMine(Player player) {
        ItemStack lapisMine = new ItemStack(Material.LAPIS_LAZULI);
        if (!player.hasPermission("warp.lapismine"))
            lapisMine = new ItemStack(Material.RED_STAINED_GLASS_PANE);

        final ItemMeta lapisMineItemMeta = lapisMine.getItemMeta();

        lapisMineItemMeta.displayName(Component.text(ChatColor.GREEN + "Lapis Mine "));
        if (!player.hasPermission("warp.lapismine"))
            lapisMineItemMeta.displayName(Component.text(lapisMineItemMeta.getDisplayName() + LOCKED_TEXT));

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_GRAY + "/warp lapisMine");
        lore.add("");
        if (!player.hasPermission("warp.lapismine")) {
            lore.add(ChatColor.RED.toString() + ChatColor.BOLD + "LOCKED");
            lore.add(ChatColor.GREEN + "Quest" + ChatColor.WHITE + ": " + ChatColor.YELLOW + "Down the Blue Path");
        } else
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Travel");

        lapisMine.setItemMeta(lapisMineItemMeta);
        lapisMine.setLore(lore);

        return lapisMine;
    }
    private ItemStack diamondMine(Player player) {
        ItemStack diamondMine = new ItemStack(Material.DIAMOND);
        if (!player.hasPermission("warp.diamondmine"))
            diamondMine = new ItemStack(Material.RED_STAINED_GLASS_PANE);

        final ItemMeta diamondMineItemMeta = diamondMine.getItemMeta();

        diamondMineItemMeta.displayName(Component.text(ChatColor.GREEN + "Diamond Mine "));
        if (!player.hasPermission("warp.diamondmine"))
            diamondMineItemMeta.displayName(Component.text(diamondMineItemMeta.getDisplayName() + LOCKED_TEXT));

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_GRAY + "/warp diamondMine");
        lore.add("");
        if (!player.hasPermission("warp.diamondmine")) {
            lore.add(ChatColor.RED.toString() + ChatColor.BOLD + "LOCKED");
            lore.add(ChatColor.GREEN + "Quest" + ChatColor.WHITE + ": " + ChatColor.YELLOW + "Shiny Hard Blue Rocks");
        } else
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Travel");

        diamondMine.setItemMeta(diamondMineItemMeta);
        diamondMine.setLore(lore);

        return diamondMine;
    }
    private ItemStack emeraldMine(Player player) {
        ItemStack emeraldMine = new ItemStack(Material.EMERALD);
        if (!player.hasPermission("warp.emeraldmine"))
            emeraldMine = new ItemStack(Material.RED_STAINED_GLASS_PANE);

        final ItemMeta emeraldMineItemMeta = emeraldMine.getItemMeta();

        emeraldMineItemMeta.displayName(Component.text(ChatColor.GREEN + "Emerald Mine "));
        if (!player.hasPermission("warp.emeraldmine"))
            emeraldMineItemMeta.displayName(Component.text(emeraldMineItemMeta.getDisplayName() + LOCKED_TEXT));

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_GRAY + "/warp emeraldMine");
        lore.add("");
        if (!player.hasPermission("warp.emeraldmine")) {
            lore.add(ChatColor.RED.toString() + ChatColor.BOLD + "LOCKED");
            lore.add(ChatColor.GREEN + "Quest" + ChatColor.WHITE + ": " + ChatColor.YELLOW + "Meet Smith");
        } else
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Travel");

        emeraldMine.setItemMeta(emeraldMineItemMeta);
        emeraldMine.setLore(lore);

        return emeraldMine;
    }
}

