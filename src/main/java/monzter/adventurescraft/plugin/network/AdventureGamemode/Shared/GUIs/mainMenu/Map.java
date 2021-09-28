package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.mainMenu;

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

public class Map extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final GUIHelper guiHelper;
    private final Material LOCKED = Material.RED_STAINED_GLASS_PANE;
    private final String LOCKED_TEXT = ChatColor.DARK_GRAY + "- " + ChatColor.RED + ChatColor.BOLD + "LOCKED";


    public Map(AdventuresCraft plugin, GUIHelper guiHelper) {
        this.plugin = plugin;
        this.guiHelper = guiHelper;
    }

    @CommandAlias("map|maps")
    public void map(Player player) {

        ChestGui gui = new ChestGui(5, guiHelper.guiName("Map"));
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        OutlinePane background = new OutlinePane(0, 0, 9, 5, Pane.Priority.LOWEST);
        StaticPane display = new StaticPane(0, 0, 9, 5, Pane.Priority.LOW);


        background.addItem(new GuiItem(guiHelper.background(Material.LIGHT_BLUE_STAINED_GLASS_PANE)));
        background.setRepeat(true);

        display.addItem(new GuiItem(farm(), e -> player.performCommand("warp farm")), 1, 1);
        display.addItem(new GuiItem(forest(), e -> {
            if (e.isLeftClick())
                player.performCommand("warp forest");
            else if (e.isRightClick())
                player.performCommand("forestMap");
        }), 2, 1);
        display.addItem(new GuiItem(mines(), e -> {
            if (e.isLeftClick())
                player.performCommand("warp mines");
            else if (e.isRightClick())
                player.performCommand("mineMap");
        }), 3, 1);
        display.addItem(new GuiItem(graveyard(), e -> player.performCommand("warp graveyard")), 4, 1);
        display.addItem(new GuiItem(courtyard(player), e -> player.performCommand("warp courtyard")), 5, 1);
        display.addItem(new GuiItem(castle(player), e -> player.performCommand("warp castle")), 6, 1);
        display.addItem(new GuiItem(estate(player), e -> player.performCommand("warp estate")), 7, 1);

        display.addItem(new GuiItem(goblinTown(player), e -> player.performCommand("warp goblinTown")), 1, 2);
        display.addItem(new GuiItem(spiritGrounds(player), e -> player.performCommand("warp spiritGrounds")), 2, 2);
        display.addItem(new GuiItem(hell(player), e -> {
            if (e.isLeftClick())
                player.performCommand("warp hell");
            else if (e.isRightClick())
                player.performCommand("warp hellBottom");
        }), 3, 2);
        display.addItem(new GuiItem(theVoid(player), e -> player.performCommand("warp void")), 4, 2);

        display.addItem(new GuiItem(townHall(player), e -> player.performCommand("warp townHall")), 3, 4);
        display.addItem(new GuiItem(guiHelper.backButton(), e -> player.performCommand("main")), 4, 4);
        display.addItem(new GuiItem(fastTravel(player), e -> player.performCommand("fastTravel")), 5, 4);

        gui.addPane(background);
        gui.addPane(display);
        gui.show(player);
    }

    private ItemStack farm() {
        final ItemStack farm = new ItemStack(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWFmMzI4Yzg3YjA2ODUwOWFjYTk4MzRlZmFjZTE5NzcwNWZlNWQ0ZjA4NzE3MzFiN2IyMWNkOTliOWZkZGMifX19"));
        final ItemMeta farmItemMeta = farm.getItemMeta();

        farmItemMeta.displayName(Component.text(ChatColor.GREEN + "The Farm"));

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_GRAY + "/warp farm");
        lore.add("");
        lore.add(ChatColor.GRAY + "Harvest the crop fields");
        lore.add(ChatColor.GRAY + "and help " + ChatColor.GREEN + "Billy " + ChatColor.GRAY + "& " + ChatColor.GREEN + "Mandy" + ChatColor.GRAY + "!");
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Travel");

        farm.setItemMeta(farmItemMeta);
        farm.setLore(lore);

        return farm;
    }

    private ItemStack forest() {
        final ItemStack forest = new ItemStack(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjBjZDEzMjIzYThkOWMxNzNjZWRjZTZjNGJlYmViYTA2YTI0YTFiYTI3NWRkM2ViNWM3OTMzZjlhNzRiYTAxMSJ9fX0="));
        final ItemMeta forestItemMeta = forest.getItemMeta();

        forestItemMeta.displayName(Component.text(ChatColor.GREEN + "The Forest"));

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_GRAY + "/warp forest");
        lore.add("");
        lore.add(ChatColor.GRAY + "Chop wood while fighting");
        lore.add(ChatColor.GRAY + "your way through " + ChatColor.RED + "Bees" + ChatColor.GRAY + ",");
        lore.add(ChatColor.GRAY + "and uncover the lurking " + ChatColor.RED + "Dryad" + ChatColor.GRAY + "!");
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Left-Click to Travel");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Right-Click to View Forest Map");

        forest.setItemMeta(forestItemMeta);
        forest.setLore(lore);

        return forest;
    }

    private ItemStack mines() {
        final ItemStack mines = new ItemStack(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzQyMDcwYWNjODE0YmM5NDZlNTk4NzllYzdkYTQ1ZGU5ODRkM2VlOWExNTkzOTNkZWZiNTk4NTNhYmUzYjYifX19"));
        final ItemMeta minesItemMeta = mines.getItemMeta();

        minesItemMeta.displayName(Component.text(ChatColor.GREEN + "The Mines"));

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_GRAY + "/warp mines");
        lore.add("");
        lore.add(ChatColor.GRAY + "Excavate through the Mine,");
        lore.add(ChatColor.GRAY + "battling many corrupted " + ChatColor.RED + "enemies");
        lore.add(ChatColor.GRAY + "in search for " + ChatColor.BLUE + "rare " + ChatColor.GRAY + "materials!");
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Left-Click to Travel");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Right-Click to View Mine Map");

        mines.setItemMeta(minesItemMeta);
        mines.setLore(lore);

        return mines;
    }

    private ItemStack graveyard() {
        final ItemStack graveyard = new ItemStack(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2YzYmU2NDAxNjczNmJlNDRiMWQ1YTVmM2FkYWI2ZDRjMDQzNzgyYzE3ZGQyOWMzYjhjNGNiNmU3M2Y5ODk4In19fQ=="));
        final ItemMeta graveyardItemMeta = graveyard.getItemMeta();

        graveyardItemMeta.displayName(Component.text(ChatColor.GREEN + "The Graveyard"));

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_GRAY + "/warp graveyard");
        lore.add("");
        lore.add(ChatColor.GRAY + "Slay through the " + ChatColor.RED + "Undead " + ChatColor.GRAY + "on");
        lore.add(ChatColor.GRAY + "your way to the " + ChatColor.RED + "Courtyard" + ChatColor.GRAY + "!");
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Travel");

        graveyard.setItemMeta(graveyardItemMeta);
        graveyard.setLore(lore);

        return graveyard;
    }

    private ItemStack courtyard(Player player) {
        ItemStack courtyard = new ItemStack(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDE5ZmIyZTQ5NzAzYzZjYjk1MTE2YmUxNTM2M2M5ZDU2ODllZjIyOWE3NWM2NTVlZjU3NmJlMzYwZWMzY2JlYiJ9fX0="));
        if (!player.hasPermission("warp.courtyard"))
            courtyard = new ItemStack(LOCKED);
        final ItemMeta courtyardItemMeta = courtyard.getItemMeta();

        courtyardItemMeta.displayName(Component.text(ChatColor.GREEN + "The Courtyard"));
        if (!player.hasPermission("warp.courtyard"))
            courtyardItemMeta.displayName(Component.text(ChatColor.GREEN + "The Courtyard " + LOCKED_TEXT));

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_GRAY + "/warp courtyard");
        lore.add("");
        lore.add(ChatColor.GRAY + "Battle your way through powerful " + ChatColor.RED + "forces");
        lore.add(ChatColor.GRAY + "on your journey to reaching the " + ChatColor.RED + "Castle" + ChatColor.GRAY + "!");
        lore.add("");
        if (!player.hasPermission("warp.courtyard")) {
            lore.add(ChatColor.RED.toString() + ChatColor.BOLD + "LOCKED");
            lore.add(ChatColor.GREEN + "Quest" + ChatColor.WHITE + ": " + ChatColor.YELLOW + "Approaching the Castle");
        } else
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Travel");

        courtyard.setItemMeta(courtyardItemMeta);
        courtyard.setLore(lore);

        return courtyard;
    }

    private ItemStack castle(Player player) {
        ItemStack castle = new ItemStack(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDljMTgzMmU0ZWY1YzRhZDljNTE5ZDE5NGIxOTg1MDMwZDI1NzkxNDMzNGFhZjI3NDVjOWRmZDYxMWQ2ZDYxZCJ9fX0=="));
        if (!player.hasPermission("warp.castle"))
            castle = new ItemStack(LOCKED);
        final ItemMeta castleItemMeta = castle.getItemMeta();

        castleItemMeta.displayName(Component.text(ChatColor.GREEN + "The Castle"));
        if (!player.hasPermission("warp.castle"))
            castleItemMeta.displayName(Component.text(ChatColor.GREEN + "The Castle " + LOCKED_TEXT));

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_GRAY + "/warp castle");
        lore.add("");
        lore.add(ChatColor.GRAY + "Help " + ChatColor.GREEN + "Klaus " + ChatColor.GRAY + "defeat " + ChatColor.RED + "Morden");
        lore.add(ChatColor.GRAY + "to restore the " + ChatColor.RED + "Courtyard" + ChatColor.GRAY + "!");
        lore.add("");
        if (!player.hasPermission("warp.castle")) {
            lore.add(ChatColor.RED.toString() + ChatColor.BOLD + "LOCKED");
            lore.add(ChatColor.GREEN + "Quest" + ChatColor.WHITE + ": " + ChatColor.YELLOW + "The Village Hero");
        } else
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Travel");

        castle.setItemMeta(castleItemMeta);
        castle.setLore(lore);

        return castle;
    }

    private ItemStack estate(Player player) {
        ItemStack estate = new ItemStack(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzlkYmEyOWM4ODI4YTQ5MDliOTRhZWU0MmRkYTg4ZTgwNGM1YzJkOGZlZTcwODQ3ZmM2NTRjYzI3MGZmNWQzNiJ9fX0="));
        if (!player.hasPermission("warp.Estate"))
            estate = new ItemStack(LOCKED);

        final ItemMeta estateItemMeta = estate.getItemMeta();

        estateItemMeta.displayName(Component.text(ChatColor.GREEN + "The Estate"));
        if (!player.hasPermission("warp.Estate"))
            estateItemMeta.displayName(Component.text(ChatColor.GREEN + "The Estate " + LOCKED_TEXT));

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_GRAY + "/warp estate");
        lore.add("");
        lore.add(ChatColor.GRAY + "Fish, Farm, and defeat " + ChatColor.RED + "Goblins" + ChatColor.GRAY + "!");
        lore.add("");
        if (!player.hasPermission("warp.Estate")) {
            lore.add(ChatColor.RED.toString() + ChatColor.BOLD + "LOCKED");
            lore.add(ChatColor.GREEN + "Quest" + ChatColor.WHITE + ": " + ChatColor.YELLOW + "Conquering the Valley");
        } else
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Travel");

        estate.setItemMeta(estateItemMeta);
        estate.setLore(lore);

        return estate;
    }

    private ItemStack goblinTown(Player player) {
        ItemStack goblinTown = new ItemStack(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjZiOTcyZTMyZDc2MWIxOTI2MjZlNWQ2ZDAxZWRjMDk0OTQwOTEwMTAzY2VhNWUyZTJkMWYyMzFhZGI3NTVkNSJ9fX0="));
        if (!player.hasPermission("warp.goblintown"))
            goblinTown = new ItemStack(LOCKED);
        final ItemMeta goblinTownItemMeta = goblinTown.getItemMeta();

        goblinTownItemMeta.displayName(Component.text(ChatColor.GREEN + "The Goblin Town"));
        if (!player.hasPermission("warp.goblintown"))
            goblinTownItemMeta.displayName(Component.text(ChatColor.GREEN + "The Goblin Town " + LOCKED_TEXT));

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_GRAY + "/warp goblinTown");
        lore.add("");
        lore.add(ChatColor.GRAY + "Raid the " + ChatColor.RED + "Goblin Town" + ChatColor.GRAY + ",");
        lore.add(ChatColor.GRAY + "and free enslaved " + ChatColor.GREEN + "Villagers" + ChatColor.GRAY + "!");
        lore.add("");
        if (!player.hasPermission("warp.goblintown")) {
            lore.add(ChatColor.RED.toString() + ChatColor.BOLD + "LOCKED");
            lore.add(ChatColor.GREEN + "Quest" + ChatColor.WHITE + ": " + ChatColor.YELLOW + "Prison Break");
        } else
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Travel");

        goblinTown.setItemMeta(goblinTownItemMeta);
        goblinTown.setLore(lore);

        return goblinTown;
    }

    private ItemStack spiritGrounds(Player player) {
        ItemStack spiritGrounds = new ItemStack(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzI2NWQ5OWVlODgxYjc0MTQ2ZTBmMTk4MDkxMmQ0NzZmZmViYmEyOWUxNTQ5MDM4ZTFkOTQ4ZjQwMTQ0MjJlYiJ9fX0="));
        if (!player.hasPermission("warp.spiritgrounds"))
            spiritGrounds = new ItemStack(LOCKED);
        final ItemMeta spiritGroundsItemMeta = spiritGrounds.getItemMeta();

        spiritGroundsItemMeta.displayName(Component.text(ChatColor.GREEN + "Spirit Grounds"));
        if (!player.hasPermission("warp.spiritgrounds"))
            spiritGroundsItemMeta.displayName(Component.text(ChatColor.GREEN + "Spirit Grounds " + LOCKED_TEXT));

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_GRAY + "/warp spiritGrounds");
        lore.add("");
        lore.add(ChatColor.GRAY + "Pick Pumpkins and Melons,");
        lore.add(ChatColor.GRAY + "while aiding the Witch " + ChatColor.GREEN + "Hazel" + ChatColor.GRAY + "!");
        lore.add("");
        if (!player.hasPermission("warp.spiritgrounds")) {
            lore.add(ChatColor.RED.toString() + ChatColor.BOLD + "LOCKED");
            lore.add(ChatColor.GREEN + "Quest" + ChatColor.WHITE + ": " + ChatColor.YELLOW + "Hunting a Witch");
        } else
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Travel");

        spiritGrounds.setItemMeta(spiritGroundsItemMeta);
        spiritGrounds.setLore(lore);

        return spiritGrounds;
    }

    private ItemStack hell(Player player) {
        ItemStack hell = new ItemStack(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDgzNTcxZmY1ODlmMWE1OWJiMDJiODA4MDBmYzczNjExNmUyN2MzZGNmOWVmZWJlZGU4Y2YxZmRkZSJ9fX0="));
        if (!player.hasPermission("cmi.command.portal.hell"))
            hell = new ItemStack(LOCKED);

        final ItemMeta hellItemMeta = hell.getItemMeta();

        hellItemMeta.displayName(Component.text(ChatColor.GREEN + "Hell"));
        if (!player.hasPermission("cmi.command.portal.hell"))
            hellItemMeta.displayName(Component.text(ChatColor.GREEN + "Hell " + LOCKED_TEXT));

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_GRAY + "/warp hell");
        lore.add("");
        lore.add(ChatColor.GRAY + "Slay your way through " + ChatColor.RED + "Demons" + ChatColor.GRAY + ",");
        lore.add(ChatColor.GRAY + "and eventually defeat " + ChatColor.RED + "Ghastly" + ChatColor.GRAY + "!");
        lore.add("");
        if (!player.hasPermission("cmi.command.portal.hell")) {
            lore.add(ChatColor.RED.toString() + ChatColor.BOLD + "LOCKED");
            lore.add(ChatColor.GREEN + "Level" + ChatColor.WHITE + ": " + ChatColor.YELLOW + "5");
        } else if (player.hasPermission("cmi.command.portal.hell") && !player.hasPermission("warp.hellbottom")) {
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Travel");
        } else {
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Left-Click to Travel to Upper Hell");
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Right-Click to Travel to Lower Hell");
        }

        hell.setItemMeta(hellItemMeta);
        hell.setLore(lore);

        return hell;
    }

    private ItemStack theVoid(Player player) {
        ItemStack theVoid = new ItemStack(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjIwMWFlMWE4YTA0ZGY1MjY1NmY1ZTQ4MTNlMWZiY2Y5Nzg3N2RiYmZiYzQyNjhkMDQzMTZkNmY5Zjc1MyJ9fX0="));
        if (!player.hasPermission("cmi.command.portal.void"))
            theVoid = new ItemStack(LOCKED);
        final ItemMeta voidItemMeta = theVoid.getItemMeta();

        voidItemMeta.displayName(Component.text(ChatColor.GREEN + "Void"));
        if (!player.hasPermission("cmi.command.portal.void"))
            voidItemMeta.displayName(Component.text(ChatColor.GREEN + "Void " + LOCKED_TEXT));

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_GRAY + "/warp void");
        lore.add("");
        lore.add(ChatColor.GRAY + "Battle both " + ChatColor.RED + "Void Bulblin " + ChatColor.GRAY + "& " + ChatColor.RED + "Bullbo" + ChatColor.GRAY + ".");
        lore.add(ChatColor.GRAY + "Adventure through the endless Maze,");
        lore.add(ChatColor.GRAY + "and defeat the " + ChatColor.RED + "Void Enchantress" + ChatColor.GRAY + "!");
        lore.add("");
        if (!player.hasPermission("cmi.command.portal.void")) {
            lore.add(ChatColor.RED.toString() + ChatColor.BOLD + "LOCKED");
            lore.add(ChatColor.GREEN + "Level" + ChatColor.WHITE + ": " + ChatColor.YELLOW + "10");
        } else
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Travel");

        theVoid.setItemMeta(voidItemMeta);
        theVoid.setLore(lore);

        return theVoid;
    }

    private ItemStack fastTravel(Player player) {
        final ItemStack fastTravel = new ItemStack(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjc3ZTNkMGY3ZGQ2NmEyNjFjZjk2MmFhMGMxMzMzYjQ5YmZjZjM2MzlmYWFlZWIxNzRkNTk1NzU3ZGY2MTEifX19"));
        final ItemMeta fastTravelItemMeta = fastTravel.getItemMeta();

        fastTravelItemMeta.displayName(Component.text(ChatColor.GREEN + "Fast Travel"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "Quickly travel to more");
        lore.add(ChatColor.GRAY + "convenient locations around");
        lore.add(ChatColor.GRAY + "the map, by becoming a " + RanksDisplay.CONQUERER_WO_PREFIX.getName() + ChatColor.GRAY + "!");
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View");

        fastTravel.setItemMeta(fastTravelItemMeta);
        fastTravel.setLore(lore);

        return fastTravel;
    }

    private ItemStack townHall(Player player) {
        final ItemStack townHall = new ItemStack(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWZiYTRmYmFjNjkyNWEzZWRlYTYxODczM2EyYzczYTI1MDhiNzNkNTQ1NWMyMzc2M2E2NTliYmY4YTMwZjljYSJ9fX0="));
        final ItemMeta townHallItemMeta = townHall.getItemMeta();

        townHallItemMeta.displayName(Component.text(ChatColor.GREEN + "Town Hall"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "The heart of the Town with");
        lore.add(ChatColor.GRAY + "many " + ChatColor.YELLOW + "Vendors " + ChatColor.GRAY + "to buy from!");
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Travel");

        townHall.setItemMeta(townHallItemMeta);
        townHall.setLore(lore);

        return townHall;
    }
}
