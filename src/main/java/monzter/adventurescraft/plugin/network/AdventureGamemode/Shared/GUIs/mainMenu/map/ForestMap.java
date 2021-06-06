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

public class ForestMap extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final GUIHelper guiHelper;
    private final String LOCKED_TEXT = ChatColor.DARK_GRAY + "- " + ChatColor.RED + ChatColor.BOLD + "LOCKED";

    public ForestMap(AdventuresCraft plugin, GUIHelper guiHelper) {
        this.plugin = plugin;
        this.guiHelper = guiHelper;
    }

    @CommandAlias("forestMap|forestMaps")
    public void questMenu(Player player) {

        ChestGui gui = new ChestGui(4, guiHelper.guiName("Forest Map"));
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        OutlinePane background = new OutlinePane(0, 0, 9, 4, Pane.Priority.LOWEST);
        StaticPane display = new StaticPane(0, 0, 9, 4, Pane.Priority.LOW);


        background.addItem(new GuiItem(guiHelper.background(Material.GREEN_STAINED_GLASS_PANE)));
        background.setRepeat(true);

        display.addItem(new GuiItem(oakForest(), e -> player.performCommand("warp oakForest")), 1, 1);
        display.addItem(new GuiItem(spruceForest(player), e -> player.performCommand("warp spurceForest")), 2, 1);
        display.addItem(new GuiItem(darkOakForest(player), e -> player.performCommand("warp darkOakForest")), 3, 1);
        display.addItem(new GuiItem(birchForest(player), e -> player.performCommand("warp birchForest")), 4, 1);
        display.addItem(new GuiItem(acaciaForest(player), e -> player.performCommand("warp acaciaForest")), 5, 1);
        display.addItem(new GuiItem(jungleForest(player), e -> player.performCommand("warp jungleForest")), 6, 1);
        display.addItem(new GuiItem(hiveForest(player), e -> player.performCommand("warp hiveForest")), 7, 1);

        display.addItem(new GuiItem(guiHelper.backButton(), e -> player.performCommand("maps")), 4, 3);

        gui.addPane(background);
        gui.addPane(display);
        gui.show(player);
    }

    private ItemStack oakForest() {
        final ItemStack oakForest = new ItemStack(Material.OAK_LOG);
        final ItemMeta oakForestItemMeta = oakForest.getItemMeta();

        oakForestItemMeta.displayName(Component.text(ChatColor.GREEN + "Oak Forest"));

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_GRAY + "/warp oakForest");
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Travel");

        oakForest.setItemMeta(oakForestItemMeta);
        oakForest.setLore(lore);

        return oakForest;
    }

    private ItemStack spruceForest(Player player) {
        ItemStack spruceForest = new ItemStack(Material.SPRUCE_LOG);
        if (!player.hasPermission("warp.SpruceForest"))
            spruceForest = new ItemStack(Material.RED_STAINED_GLASS_PANE);

        final ItemMeta spruceForestItemMeta = spruceForest.getItemMeta();

        spruceForestItemMeta.displayName(Component.text(ChatColor.GREEN + "Spruce Forest "));
        if (!player.hasPermission("warp.SpruceForest"))
            spruceForestItemMeta.displayName(Component.text(spruceForestItemMeta.getDisplayName() + LOCKED_TEXT));

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_GRAY + "/warp spruceForest");
        lore.add("");
        if (!player.hasPermission("warp.SpruceForest")) {
            lore.add(ChatColor.RED.toString() + ChatColor.BOLD + "LOCKED");
            lore.add(ChatColor.GREEN + "Quest" + ChatColor.WHITE + ": " + ChatColor.YELLOW + "Spruce Timber");
        } else
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Travel");

        spruceForest.setItemMeta(spruceForestItemMeta);
        spruceForest.setLore(lore);

        return spruceForest;
    }

    private ItemStack darkOakForest(Player player) {
        ItemStack darkOakForest = new ItemStack(Material.DARK_OAK_LOG);
        if (!player.hasPermission("warp.DarkOakForest"))
            darkOakForest = new ItemStack(Material.RED_STAINED_GLASS_PANE);

        final ItemMeta darkOakForestItemMeta = darkOakForest.getItemMeta();

        darkOakForestItemMeta.displayName(Component.text(ChatColor.GREEN + "Dark Oak Forest "));
        if (!player.hasPermission("warp.DarkOakForest"))
            darkOakForestItemMeta.displayName(Component.text(darkOakForestItemMeta.getDisplayName() + LOCKED_TEXT));

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_GRAY + "/warp darkOakForest");
        lore.add("");
        if (!player.hasPermission("warp.DarkOakForest")) {
            lore.add(ChatColor.RED.toString() + ChatColor.BOLD + "LOCKED");
            lore.add(ChatColor.GREEN + "Quest" + ChatColor.WHITE + ": " + ChatColor.YELLOW + "Dark Oak Timber");
        } else
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Travel");

        darkOakForest.setItemMeta(darkOakForestItemMeta);
        darkOakForest.setLore(lore);

        return darkOakForest;
    }
    private ItemStack birchForest(Player player) {
        ItemStack birchForest = new ItemStack(Material.BIRCH_LOG);
        if (!player.hasPermission("warp.BirchForest"))
            birchForest = new ItemStack(Material.RED_STAINED_GLASS_PANE);

        final ItemMeta birchForestItemMeta = birchForest.getItemMeta();

        birchForestItemMeta.displayName(Component.text(ChatColor.GREEN + "Birch Forest "));
        if (!player.hasPermission("warp.BirchForest"))
            birchForestItemMeta.displayName(Component.text(birchForestItemMeta.getDisplayName() + LOCKED_TEXT));

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_GRAY + "/warp birchForest");
        lore.add("");
        if (!player.hasPermission("warp.BirchForest")) {
            lore.add(ChatColor.RED.toString() + ChatColor.BOLD + "LOCKED");
            lore.add(ChatColor.GREEN + "Quest" + ChatColor.WHITE + ": " + ChatColor.YELLOW + "Birch Timber");
        } else
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Travel");

        birchForest.setItemMeta(birchForestItemMeta);
        birchForest.setLore(lore);

        return birchForest;
    }
    private ItemStack acaciaForest(Player player) {
        ItemStack acaciaForest = new ItemStack(Material.ACACIA_LOG);
        if (!player.hasPermission("warp.AcaciaForest"))
            acaciaForest = new ItemStack(Material.RED_STAINED_GLASS_PANE);

        final ItemMeta acaciaForestItemMeta = acaciaForest.getItemMeta();

        acaciaForestItemMeta.displayName(Component.text(ChatColor.GREEN + "Acacia Forest "));
        if (!player.hasPermission("warp.AcaciaForest"))
            acaciaForestItemMeta.displayName(Component.text(acaciaForestItemMeta.getDisplayName() + LOCKED_TEXT));

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_GRAY + "/warp acaciaForest");
        lore.add("");
        if (!player.hasPermission("warp.AcaciaForest")) {
            lore.add(ChatColor.RED.toString() + ChatColor.BOLD + "LOCKED");
            lore.add(ChatColor.GREEN + "Quest" + ChatColor.WHITE + ": " + ChatColor.YELLOW + "Acacia Timber");
        } else
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Travel");

        acaciaForest.setItemMeta(acaciaForestItemMeta);
        acaciaForest.setLore(lore);

        return acaciaForest;
    }
    private ItemStack jungleForest(Player player) {
        ItemStack jungleForest = new ItemStack(Material.JUNGLE_LOG);
        if (!player.hasPermission("warp.JungleForest"))
            jungleForest = new ItemStack(Material.RED_STAINED_GLASS_PANE);

        final ItemMeta jungleForestItemMeta = jungleForest.getItemMeta();

        jungleForestItemMeta.displayName(Component.text(ChatColor.GREEN + "Jungle Forest "));
        if (!player.hasPermission("warp.JungleForest"))
            jungleForestItemMeta.displayName(Component.text(jungleForestItemMeta.getDisplayName() + LOCKED_TEXT));

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_GRAY + "/warp jungleForest");
        lore.add("");
        if (!player.hasPermission("warp.JungleForest")) {
            lore.add(ChatColor.RED.toString() + ChatColor.BOLD + "LOCKED");
            lore.add(ChatColor.GREEN + "Quest" + ChatColor.WHITE + ": " + ChatColor.YELLOW + "Jungle Timber");
        } else
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Travel");

        jungleForest.setItemMeta(jungleForestItemMeta);
        jungleForest.setLore(lore);

        return jungleForest;
    }
    private ItemStack hiveForest(Player player) {
        ItemStack hiveForest = new ItemStack(Material.BEE_NEST);
        if (!player.hasPermission("warp.HiveForest"))
            hiveForest = new ItemStack(Material.RED_STAINED_GLASS_PANE);

        final ItemMeta hiveForestItemMeta = hiveForest.getItemMeta();

        hiveForestItemMeta.displayName(Component.text(ChatColor.GREEN + "Hive "));
        if (!player.hasPermission("warp.HiveForest"))
            hiveForestItemMeta.displayName(Component.text(hiveForestItemMeta.getDisplayName() + LOCKED_TEXT));

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_GRAY + "/warp hive");
        lore.add("");
        if (!player.hasPermission("warp.HiveForest")) {
            lore.add(ChatColor.RED.toString() + ChatColor.BOLD + "LOCKED");
            lore.add(ChatColor.GREEN + "Quest" + ChatColor.WHITE + ": " + ChatColor.YELLOW + "Rescue Jack");
        } else
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Travel");

        hiveForest.setItemMeta(hiveForestItemMeta);
        hiveForest.setLore(lore);

        return hiveForest;
    }
}

