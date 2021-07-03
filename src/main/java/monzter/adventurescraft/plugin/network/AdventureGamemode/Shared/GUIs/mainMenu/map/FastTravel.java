package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.mainMenu.map;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Dependency;
import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import dev.dbassett.skullcreator.SkullCreator;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.GUI.GUIHelper;
import monzter.adventurescraft.plugin.utilities.enums.Prefix;
import monzter.adventurescraft.plugin.utilities.enums.RanksDisplay;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class FastTravel extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final GUIHelper guiHelper;
    private final Material LOCKED = Material.RED_STAINED_GLASS_PANE;
    private final String LOCKED_TEXT = ChatColor.DARK_GRAY + "- " + ChatColor.RED + ChatColor.BOLD + "LOCKED";
    private final String PERMISSION = "Rank3";
    private final String UNLOCK_METHOD = ChatColor.WHITE + "Rank Required: " + RanksDisplay.CONQUERER_WO_PREFIX.getName();


    public FastTravel(AdventuresCraft plugin, GUIHelper guiHelper) {
        this.plugin = plugin;
        this.guiHelper = guiHelper;
    }

    @CommandAlias("travel|fastTravel")
    public void map(Player player) {

        ChestGui gui = new ChestGui(5, guiHelper.guiName("Fast Map"));
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        OutlinePane background = new OutlinePane(0, 0, 9, 5, Pane.Priority.LOWEST);
        StaticPane display = new StaticPane(0, 0, 9, 5, Pane.Priority.LOW);


        background.addItem(new GuiItem(guiHelper.background(Material.PINK_STAINED_GLASS_PANE)));
        background.setRepeat(true);

        display.addItem(new GuiItem(enchanter(player), e -> player.performCommand("skript:warp Enchanter")), 1, 1);
        display.addItem(new GuiItem(auction(player), e -> player.performCommand("skript:warp Auction")), 2, 1);
        display.addItem(new GuiItem(dracula(player), e -> player.performCommand("skript:warp Dracula")), 3, 1);
        display.addItem(new GuiItem(reaper(player), e -> player.performCommand("skript:warp Reaper")), 4, 1);
        display.addItem(new GuiItem(morden(player), e -> player.performCommand("skript:warp Morden")), 5, 1);
        display.addItem(new GuiItem(voidWither(player), e -> player.performCommand("skript:warp voidWither")), 6, 1);
        display.addItem(new GuiItem(voidMagma(player), e -> player.performCommand("skript:warp voidMagma")), 7, 1);

        display.addItem(new GuiItem(ghastly(player), e -> player.performCommand("skript:warp Ghastly")), 1, 2);
        display.addItem(new GuiItem(voidBullbo(player), e -> player.performCommand("skript:warp voidBullbo")), 2, 2);

        display.addItem(new GuiItem(guiHelper.backButton(), e -> player.performCommand("map")), 4, 4);

        gui.addPane(background);
        gui.addPane(display);
        gui.show(player);
    }

    private ItemStack enchanter(Player player) {
        ItemStack enchanter = new ItemStack(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTc2MmExNWIwNDY5MmEyZTRiM2ZiMzY2M2JkNGI3ODQzNGRjZTE3MzJiOGViMWM3YTlmN2MwZmJmNmYifX19"));
        if (!player.hasPermission(PERMISSION))
            enchanter = new ItemStack(LOCKED);
        final ItemMeta enchanterItemMeta = enchanter.getItemMeta();

        enchanterItemMeta.displayName(Component.text(ChatColor.GREEN + "The Enchanter"));
        if (!player.hasPermission(PERMISSION))
            enchanterItemMeta.displayName(Component.text(ChatColor.GREEN + "The Enchanter " + LOCKED_TEXT));

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_GRAY + "/warp enchanter");
        lore.add("");
        lore.add(ChatColor.GRAY + "Improve your Armor, Tools,");
        lore.add(ChatColor.GRAY + "and Weapons at the " + ChatColor.DARK_PURPLE + "Enchanter" + ChatColor.GRAY + "!");
        lore.add("");
        if (!player.hasPermission(PERMISSION)) {
            lore.add(ChatColor.RED.toString() + ChatColor.BOLD + "LOCKED");
            lore.add(UNLOCK_METHOD);
        } else
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Travel");

        enchanter.setItemMeta(enchanterItemMeta);
        enchanter.setLore(lore);

        return enchanter;
    }

    private ItemStack auction(Player player) {
        ItemStack auction = new ItemStack(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDVjNmRjMmJiZjUxYzM2Y2ZjNzcxNDU4NWE2YTU2ODNlZjJiMTRkNDdkOGZmNzE0NjU0YTg5M2Y1ZGE2MjIifX19"));
        if (!player.hasPermission(PERMISSION))
            auction = new ItemStack(LOCKED);
        final ItemMeta auctionItemMeta = auction.getItemMeta();

        auctionItemMeta.displayName(Component.text(ChatColor.GREEN + "Auction House"));
        if (!player.hasPermission(PERMISSION))
            auctionItemMeta.displayName(Component.text(ChatColor.GREEN + "Auction House " + LOCKED_TEXT));

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_GRAY + "/warp auction");
        lore.add("");
        lore.add(ChatColor.GRAY + "Purchase Gear and other Items");
        lore.add(ChatColor.GRAY + "easily from the " + ChatColor.GREEN + "Auction House" + ChatColor.GRAY + "!");
        lore.add("");
        if (!player.hasPermission(PERMISSION)) {
            lore.add(ChatColor.RED.toString() + ChatColor.BOLD + "LOCKED");
            lore.add(UNLOCK_METHOD);
        } else
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Travel");

        auction.setItemMeta(auctionItemMeta);
        auction.setLore(lore);

        return auction;
    }
    private ItemStack dracula(Player player) {
        ItemStack dracula = new ItemStack(SkullCreator.itemFromBase64("ewogICJ0aW1lc3RhbXAiIDogMTU5MjIzOTY2MDgzMywKICAicHJvZmlsZUlkIiA6ICJiMGQ3MzJmZTAwZjc0MDdlOWU3Zjc0NjMwMWNkOThjYSIsCiAgInByb2ZpbGVOYW1lIiA6ICJPUHBscyIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS8xYzBjMDEyZTNjOWZiMGNkNDAxMTQ4MjIxYzA1NDQ5ZDRkNDg1ZWQxYmNkMTVlZGNmYTk1MjlkZGViMDdhMTliIgogICAgfQogIH0KfQ=="));
        if (!player.hasPermission(PERMISSION))
            dracula = new ItemStack(LOCKED);
        final ItemMeta draculaItemMeta = dracula.getItemMeta();

        draculaItemMeta.displayName(Component.text(ChatColor.GREEN + "Dracula Spawn"));
        if (!player.hasPermission(PERMISSION))
            draculaItemMeta.displayName(Component.text(ChatColor.GREEN + "Dracula Spawn " + LOCKED_TEXT));

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_GRAY + "/warp dracula");
        lore.add("");
        lore.add(ChatColor.GRAY + "Travel directly to where " + ChatColor.RED + "Dracula");
        lore.add(ChatColor.GRAY + "spawns inside the " + ChatColor.AQUA + "Diamond Mine" + ChatColor.GRAY + "!");
        lore.add("");
        if (!player.hasPermission(PERMISSION)) {
            lore.add(ChatColor.RED.toString() + ChatColor.BOLD + "LOCKED");
            lore.add(UNLOCK_METHOD);
        } else
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Travel");

        dracula.setItemMeta(draculaItemMeta);
        dracula.setLore(lore);

        return dracula;
    }
    private ItemStack reaper(Player player) {
        ItemStack reaper = new ItemStack(SkullCreator.itemFromBase64("ewogICJ0aW1lc3RhbXAiIDogMTU4Nzc0NjcyMTg3MiwKICAicHJvZmlsZUlkIiA6ICI4MmM2MDZjNWM2NTI0Yjc5OGI5MWExMmQzYTYxNjk3NyIsCiAgInByb2ZpbGVOYW1lIiA6ICJOb3ROb3RvcmlvdXNOZW1vIiwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzNjNjlhOWVhYjJiZDUwMDA5YTBkNTdhZDRjM2RmNGRmYTE2NWFiYTM0MDQxNGYxYzgzMjdmYzRlYTcyNDQ2NDEiCiAgICB9CiAgfQp9"));
        if (!player.hasPermission(PERMISSION))
            reaper = new ItemStack(LOCKED);
        final ItemMeta reaperItemMeta = reaper.getItemMeta();

        reaperItemMeta.displayName(Component.text(ChatColor.GREEN + "Reaper Spawn"));
        if (!player.hasPermission(PERMISSION))
            reaperItemMeta.displayName(Component.text(ChatColor.GREEN + "Reaper Spawn " + LOCKED_TEXT));

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_GRAY + "/warp reaper");
        lore.add("");
        lore.add(ChatColor.GRAY + "Travel directly to where the " + ChatColor.RED + "Reaper");
        lore.add(ChatColor.GRAY + "spawns inside the " + ChatColor.RED + "Graveyard" + ChatColor.GRAY + "!");
        lore.add("");
        if (!player.hasPermission(PERMISSION)) {
            lore.add(ChatColor.RED.toString() + ChatColor.BOLD + "LOCKED");
            lore.add(UNLOCK_METHOD);
        } else
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Travel");

        reaper.setItemMeta(reaperItemMeta);
        reaper.setLore(lore);

        return reaper;
    }
    private ItemStack morden(Player player) {
        ItemStack morden = new ItemStack(SkullCreator.itemFromBase64("ewogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJpZCIgOiAiNjgxNmRmYzIyZWU3NDY1MGE0ODNhNDljMzRmNjJmM2QiLAogICAgICAidHlwZSIgOiAiU0tJTiIsCiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2I4ZDVlMTgwZmJiZDc5ZGY1NWZkYjYzODIyMTU4YThmOTliMWU4YjI1Y2I0Y2JiYjUzZWQzNzMyNTExMGQ0NyIsCiAgICAgICJwcm9maWxlSWQiIDogIjIzZjFhNTlmNDY5YjQzZGRiZGI1MzdiZmVjMTA0NzFmIiwKICAgICAgInRleHR1cmVJZCIgOiAiM2I4ZDVlMTgwZmJiZDc5ZGY1NWZkYjYzODIyMTU4YThmOTliMWU4YjI1Y2I0Y2JiYjUzZWQzNzMyNTExMGQ0NyIKICAgIH0KICB9LAogICJza2luIiA6IHsKICAgICJpZCIgOiAiNjgxNmRmYzIyZWU3NDY1MGE0ODNhNDljMzRmNjJmM2QiLAogICAgInR5cGUiIDogIlNLSU4iLAogICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS8zYjhkNWUxODBmYmJkNzlkZjU1ZmRiNjM4MjIxNThhOGY5OWIxZThiMjVjYjRjYmJiNTNlZDM3MzI1MTEwZDQ3IiwKICAgICJwcm9maWxlSWQiIDogIjIzZjFhNTlmNDY5YjQzZGRiZGI1MzdiZmVjMTA0NzFmIiwKICAgICJ0ZXh0dXJlSWQiIDogIjNiOGQ1ZTE4MGZiYmQ3OWRmNTVmZGI2MzgyMjE1OGE4Zjk5YjFlOGIyNWNiNGNiYmI1M2VkMzczMjUxMTBkNDciCiAgfSwKICAiY2FwZSIgOiBudWxsCn0="));
        if (!player.hasPermission(PERMISSION))
            morden = new ItemStack(LOCKED);
        final ItemMeta mordenItemMeta = morden.getItemMeta();

        mordenItemMeta.displayName(Component.text(ChatColor.GREEN + "Morden Spawn"));
        if (!player.hasPermission(PERMISSION))
            mordenItemMeta.displayName(Component.text(ChatColor.GREEN + "Morden Spawn " + LOCKED_TEXT));

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_GRAY + "/warp morden");
        lore.add("");
        lore.add(ChatColor.GRAY + "Travel directly to where " + ChatColor.RED + "Morden");
        lore.add(ChatColor.GRAY + "spawns inside the " + ChatColor.RED + "Castle" + ChatColor.GRAY + "!");
        lore.add("");
        if (!player.hasPermission(PERMISSION)) {
            lore.add(ChatColor.RED.toString() + ChatColor.BOLD + "LOCKED");
            lore.add(UNLOCK_METHOD);
        } else
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Travel");

        morden.setItemMeta(mordenItemMeta);
        morden.setLore(lore);

        return morden;
    }
    private ItemStack voidWither(Player player) {
        ItemStack voidWither = new ItemStack(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2RmNzRlMzIzZWQ0MTQzNjk2NWY1YzU3ZGRmMjgxNWQ1MzMyZmU5OTllNjhmYmI5ZDZjZjVjOGJkNDEzOWYifX19"));
        if (!player.hasPermission(PERMISSION))
            voidWither = new ItemStack(LOCKED);
        final ItemMeta voidWitherItemMeta = voidWither.getItemMeta();

        voidWitherItemMeta.displayName(Component.text(ChatColor.GREEN + "Void Wither Spawn"));
        if (!player.hasPermission(PERMISSION))
            voidWitherItemMeta.displayName(Component.text(ChatColor.GREEN + "Void Wither Spawn " + LOCKED_TEXT));

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_GRAY + "/warp wither");
        lore.add("");
        lore.add(ChatColor.GRAY + "Travel directly to where the");
        lore.add(ChatColor.RED + "Void Wither " + ChatColor.GRAY + "spawns within " + ChatColor.RED + "Hell" + ChatColor.GRAY + "!");
        lore.add("");
        if (!player.hasPermission(PERMISSION)) {
            lore.add(ChatColor.RED.toString() + ChatColor.BOLD + "LOCKED");
            lore.add(UNLOCK_METHOD);
        } else
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Travel");

        voidWither.setItemMeta(voidWitherItemMeta);
        voidWither.setLore(lore);

        return voidWither;
    }
    private ItemStack voidMagma(Player player) {
        ItemStack voidMagma = new ItemStack(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDBkNTQ5ZjM2N2FiY2JlYTM5MjUyYmEyNzVmNWMzYzFjYjE2OTQ1ZmNmNDcyMTIzZWUyNmQ5YzlkZDQwZSJ9fX0="));
        if (!player.hasPermission(PERMISSION))
            voidMagma = new ItemStack(LOCKED);
        final ItemMeta voidMagmaItemMeta = voidMagma.getItemMeta();

        voidMagmaItemMeta.displayName(Component.text(ChatColor.GREEN + "Void Magma Spawn"));
        if (!player.hasPermission(PERMISSION))
            voidMagmaItemMeta.displayName(Component.text(ChatColor.GREEN + "Void Magma Spawn " + LOCKED_TEXT));

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_GRAY + "/warp magma");
        lore.add("");
        lore.add(ChatColor.GRAY + "Travel directly to where the");
        lore.add(ChatColor.RED + "Void Magma " + ChatColor.GRAY + "spawns within " + ChatColor.RED + "Hell" + ChatColor.GRAY + "!");
        lore.add("");
        if (!player.hasPermission(PERMISSION)) {
            lore.add(ChatColor.RED.toString() + ChatColor.BOLD + "LOCKED");
            lore.add(UNLOCK_METHOD);
        } else
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Travel");

        voidMagma.setItemMeta(voidMagmaItemMeta);
        voidMagma.setLore(lore);

        return voidMagma;
    }
    private ItemStack ghastly(Player player) {
        ItemStack ghastly = new ItemStack(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGI2YTcyMTM4ZDY5ZmJiZDJmZWEzZmEyNTFjYWJkODcxNTJlNGYxYzk3ZTVmOTg2YmY2ODU1NzFkYjNjYzAifX19"));
        if (!player.hasPermission(PERMISSION))
            ghastly = new ItemStack(LOCKED);
        final ItemMeta ghastlyItemMeta = ghastly.getItemMeta();

        ghastlyItemMeta.displayName(Component.text(ChatColor.GREEN + "Ghastly Spawn"));
        if (!player.hasPermission(PERMISSION))
            ghastlyItemMeta.displayName(Component.text(ChatColor.GREEN + "Ghastly Spawn " + LOCKED_TEXT));

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_GRAY + "/warp ghastly");
        lore.add("");
        lore.add(ChatColor.GRAY + "Travel directly to where");
        lore.add(ChatColor.RED + "Ghastly " + ChatColor.GRAY + "spawns within " + ChatColor.RED + "Hell" + ChatColor.GRAY + "!");
        lore.add("");
        if (!player.hasPermission(PERMISSION)) {
            lore.add(ChatColor.RED.toString() + ChatColor.BOLD + "LOCKED");
            lore.add(UNLOCK_METHOD);
        } else
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Travel");

        ghastly.setItemMeta(ghastlyItemMeta);
        ghastly.setLore(lore);

        return ghastly;
    }
    private ItemStack voidBullbo(Player player) {
        ItemStack voidBullbo = new ItemStack(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2QyMGJmNTJlYzM5MGEwNzk5Mjk5MTg0ZmM2NzhiZjg0Y2Y3MzJiYjFiZDc4ZmQxYzRiNDQxODU4ZjAyMzVhOCJ9fX0="));
        if (!player.hasPermission(PERMISSION))
            voidBullbo = new ItemStack(LOCKED);
        final ItemMeta voidBullboItemMeta = voidBullbo.getItemMeta();

        voidBullboItemMeta.displayName(Component.text(ChatColor.GREEN + "Void Bullbo & Bulblin Spawn"));
        if (!player.hasPermission(PERMISSION))
            voidBullboItemMeta.displayName(Component.text(ChatColor.GREEN + "Ghastly Spawn " + LOCKED_TEXT));

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_GRAY + "/warp bullbo");
        lore.add(ChatColor.DARK_GRAY + "/warp bulblin");
        lore.add("");
        lore.add(ChatColor.GRAY + "Travel directly to where");
        lore.add(ChatColor.RED + "Bullbo " + ChatColor.GRAY + "spawns within the " + ChatColor.DARK_PURPLE + "Void" + ChatColor.GRAY + "!");
        lore.add("");
        if (!player.hasPermission(PERMISSION)) {
            lore.add(ChatColor.RED.toString() + ChatColor.BOLD + "LOCKED");
            lore.add(UNLOCK_METHOD);
        } else
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Travel");

        voidBullbo.setItemMeta(voidBullboItemMeta);
        voidBullbo.setLore(lore);

        return voidBullbo;
    }
}

