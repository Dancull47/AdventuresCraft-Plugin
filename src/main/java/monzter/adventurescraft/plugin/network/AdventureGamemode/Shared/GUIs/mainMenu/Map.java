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
import me.clip.placeholderapi.PlaceholderAPI;
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

public class Map extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final GUIHelper guiHelper;

    public Map(AdventuresCraft plugin, GUIHelper guiHelper) {
        this.plugin = plugin;
        this.guiHelper = guiHelper;
    }

    @CommandAlias("map|maps")
    public void questMenu(Player player) {

        ChestGui gui = new ChestGui(5, guiHelper.guiName("Map"));
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        OutlinePane background = new OutlinePane(0, 0, 9, 5, Pane.Priority.LOWEST);
        StaticPane display = new StaticPane(0, 0, 9, 5, Pane.Priority.LOW);


        background.addItem(new GuiItem(guiHelper.background(Material.YELLOW_STAINED_GLASS_PANE)));
        background.setRepeat(true);

        display.addItem(new GuiItem(farm(), e -> player.performCommand("warp farm")), 1, 1);
        display.addItem(new GuiItem(forest(), e -> player.performCommand("warp forest")), 2, 1);
        display.addItem(new GuiItem(mines(), e -> player.performCommand("warp mines")), 3, 1);
        display.addItem(new GuiItem(graveyard(), e -> player.performCommand("warp graveyard")), 4, 1);
        display.addItem(new GuiItem(courtyard(), e -> player.performCommand("warp courtyard")), 5, 1);
        display.addItem(new GuiItem(castle(), e -> player.performCommand("warp castle")), 6, 1);
        display.addItem(new GuiItem(estate(), e -> player.performCommand("warp estate")), 7, 1);

        display.addItem(new GuiItem(goblinTown(), e -> player.performCommand("warp goblinTown")), 1, 2);
        display.addItem(new GuiItem(spiritGrounds(), e -> player.performCommand("warp spiritGrounds")), 2, 2);
        display.addItem(new GuiItem(hell(), e -> player.performCommand("warp hell")), 3, 2);
        display.addItem(new GuiItem(theVoid(), e -> player.performCommand("warp void")), 4, 2);

        display.addItem(new GuiItem(crates(player), e -> player.performCommand("warp crates")), 2, 4);
        display.addItem(new GuiItem(yard(player), e -> player.performCommand("warp yard")), 3, 4);
        display.addItem(new GuiItem(guiHelper.backButton(), e -> player.performCommand("main")), 4, 4);
        display.addItem(new GuiItem(pets(player), e -> player.performCommand("warp pets")), 5, 4);
        display.addItem(new GuiItem(blackMarket(player), e -> player.performCommand("warp shop")), 6, 4);

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
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Travel");

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

    private ItemStack courtyard() {
        final ItemStack courtyard = new ItemStack(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDE5ZmIyZTQ5NzAzYzZjYjk1MTE2YmUxNTM2M2M5ZDU2ODllZjIyOWE3NWM2NTVlZjU3NmJlMzYwZWMzY2JlYiJ9fX0="));
        final ItemMeta courtyardItemMeta = courtyard.getItemMeta();

        courtyardItemMeta.displayName(Component.text(ChatColor.GREEN + "The Courtyard"));

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_GRAY + "/warp courtyard");
        lore.add("");
        lore.add(ChatColor.GRAY + "Battle your way through powerful " + ChatColor.RED + "forces");
        lore.add(ChatColor.GRAY + "on your journey to reaching the " + ChatColor.RED + "Castle" + ChatColor.GRAY + "!");
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Travel");

        courtyard.setItemMeta(courtyardItemMeta);
        courtyard.setLore(lore);

        return courtyard;
    }

    private ItemStack castle() {
        final ItemStack castle = new ItemStack(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDljMTgzMmU0ZWY1YzRhZDljNTE5ZDE5NGIxOTg1MDMwZDI1NzkxNDMzNGFhZjI3NDVjOWRmZDYxMWQ2ZDYxZCJ9fX0=="));
        final ItemMeta castleItemMeta = castle.getItemMeta();

        castleItemMeta.displayName(Component.text(ChatColor.GREEN + "The Castle"));

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_GRAY + "/warp castle");
        lore.add("");
        lore.add(ChatColor.GRAY + "Help " + ChatColor.GREEN + "Klaus " + ChatColor.GRAY + "defeat " + ChatColor.RED + "Morden");
        lore.add(ChatColor.GRAY + "to restore the " + ChatColor.RED + "Courtyard" + ChatColor.GRAY + "!");
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Travel");

        castle.setItemMeta(castleItemMeta);
        castle.setLore(lore);

        return castle;
    }

    private ItemStack estate() {
        final ItemStack estate = new ItemStack(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzlkYmEyOWM4ODI4YTQ5MDliOTRhZWU0MmRkYTg4ZTgwNGM1YzJkOGZlZTcwODQ3ZmM2NTRjYzI3MGZmNWQzNiJ9fX0="));
        final ItemMeta estateItemMeta = estate.getItemMeta();

        estateItemMeta.displayName(Component.text(ChatColor.GREEN + "The Estate"));

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_GRAY + "/warp estate");
        lore.add("");
        lore.add(ChatColor.GRAY + "Fish, Farm, and defeat " + ChatColor.RED + "Goblins" + ChatColor.GRAY + "!");
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Travel");

        estate.setItemMeta(estateItemMeta);
        estate.setLore(lore);

        return estate;
    }

    private ItemStack goblinTown() {
        final ItemStack goblinTown = new ItemStack(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjZiOTcyZTMyZDc2MWIxOTI2MjZlNWQ2ZDAxZWRjMDk0OTQwOTEwMTAzY2VhNWUyZTJkMWYyMzFhZGI3NTVkNSJ9fX0="));
        final ItemMeta goblinTownItemMeta = goblinTown.getItemMeta();

        goblinTownItemMeta.displayName(Component.text(ChatColor.GREEN + "The goblinTown"));

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_GRAY + "/warp goblinTown");
        lore.add("");
        lore.add(ChatColor.GRAY + "Raid the " + ChatColor.RED + "Goblin Town" + ChatColor.GRAY + ",");
        lore.add(ChatColor.GRAY + "and free enslaved " + ChatColor.GREEN + "Villagers" + ChatColor.GRAY + "!");
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Travel");

        goblinTown.setItemMeta(goblinTownItemMeta);
        goblinTown.setLore(lore);

        return goblinTown;
    }

    private ItemStack spiritGrounds() {
        final ItemStack spiritGrounds = new ItemStack(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzI2NWQ5OWVlODgxYjc0MTQ2ZTBmMTk4MDkxMmQ0NzZmZmViYmEyOWUxNTQ5MDM4ZTFkOTQ4ZjQwMTQ0MjJlYiJ9fX0="));
        final ItemMeta spiritGroundsItemMeta = spiritGrounds.getItemMeta();

        spiritGroundsItemMeta.displayName(Component.text(ChatColor.GREEN + "Spirit Grounds"));

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_GRAY + "/warp spiritGrounds");
        lore.add("");
        lore.add(ChatColor.GRAY + "Pick Pumpkins and Melons,");
        lore.add(ChatColor.GRAY + "while aiding the Witch " + ChatColor.GREEN + "Haze" + ChatColor.GRAY + "!");
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Travel");

        spiritGrounds.setItemMeta(spiritGroundsItemMeta);
        spiritGrounds.setLore(lore);

        return spiritGrounds;
    }

    private ItemStack hell() {
        final ItemStack hell = new ItemStack(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDgzNTcxZmY1ODlmMWE1OWJiMDJiODA4MDBmYzczNjExNmUyN2MzZGNmOWVmZWJlZGU4Y2YxZmRkZSJ9fX0="));
        final ItemMeta hellItemMeta = hell.getItemMeta();

        hellItemMeta.displayName(Component.text(ChatColor.GREEN + "Hell"));

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_GRAY + "/warp hell");
        lore.add("");
        lore.add(ChatColor.GRAY + "Slay your way through " + ChatColor.RED + "Demons" + ChatColor.GRAY + ",");
        lore.add(ChatColor.GRAY + "and eventually defeat " + ChatColor.RED + "Ghastly" + ChatColor.GRAY + "!");
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Travel");

        hell.setItemMeta(hellItemMeta);
        hell.setLore(lore);

        return hell;
    }

    private ItemStack theVoid() {
        final ItemStack theVoid = new ItemStack(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjIwMWFlMWE4YTA0ZGY1MjY1NmY1ZTQ4MTNlMWZiY2Y5Nzg3N2RiYmZiYzQyNjhkMDQzMTZkNmY5Zjc1MyJ9fX0="));
        final ItemMeta voidItemMeta = theVoid.getItemMeta();

        voidItemMeta.displayName(Component.text(ChatColor.GREEN + "Void"));

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_GRAY + "/warp void");
        lore.add("");
        lore.add(ChatColor.GRAY + "Battle both " + ChatColor.RED + "Void Bulblin " + ChatColor.GRAY + "& " + ChatColor.RED + "Bullbo" + ChatColor.GRAY + ".");
        lore.add(ChatColor.GRAY + "Adventure through the endless Maze,");
        lore.add(ChatColor.GRAY + "and defeat the " + ChatColor.RED + "Void Enchantress" + ChatColor.GRAY + "!");
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Travel");

        theVoid.setItemMeta(voidItemMeta);
        theVoid.setLore(lore);

        return theVoid;
    }


    private ItemStack crates(Player player) {
        final ItemStack crates = new ItemStack(Material.CHEST);
        final ItemMeta cratesItemMeta = crates.getItemMeta();

        cratesItemMeta.displayName(Component.text(ChatColor.GREEN + "Crates"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "Checkout what is contained within");
        lore.add(ChatColor.GRAY + "the " + ChatColor.GREEN + "Crates" + ChatColor.GRAY + " found in our world!");
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Travel");

        crates.setItemMeta(cratesItemMeta);
        crates.setLore(lore);

        return crates;
    }

    private ItemStack yard(Player player) {
        final ItemStack yard = new ItemStack(Material.POLISHED_ANDESITE);
        final ItemMeta yardItemMeta = yard.getItemMeta();

        yardItemMeta.displayName(Component.text(ChatColor.GREEN + "Yard"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "Get harassed by other " + ChatColor.GOLD + "Prisoners" + ChatColor.GRAY + ",");
        lore.add(ChatColor.GRAY + "make purchases from the " + ChatColor.GREEN + "Smugglers" + ChatColor.GRAY + ",");
        lore.add(ChatColor.GRAY + "and hatch " + ChatColor.GREEN + "Pet Eggs" + ChatColor.GRAY + " with " + ChatColor.GREEN + "Sarah" + ChatColor.GRAY + "!");
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Travel");

        yard.setItemMeta(yardItemMeta);
        yard.setLore(lore);

        return yard;
    }

    private ItemStack pets(Player player) {
        final ItemStack pets = new ItemStack(SkullCreator.itemFromBase64("ewogICJ0aW1lc3RhbXAiIDogMTYxNTkwMTYzMzU0OSwKICAicHJvZmlsZUlkIiA6ICJhYTZhNDA5NjU4YTk0MDIwYmU3OGQwN2JkMzVlNTg5MyIsCiAgInByb2ZpbGVOYW1lIiA6ICJiejE0IiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2E5ZWJlNDk2OGIzMjk2NDcwM2RlMmM1NDNiZTI5NmRjZWNkNjkxNmRkZmE3NjM5NWY3N2RmZGJjNjdkMTQzODMiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ=="));
        final ItemMeta petsItemMeta = pets.getItemMeta();

        petsItemMeta.displayName(Component.text(ChatColor.GREEN + "Pets"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "Checkout the " + ChatColor.GREEN + "Pets " + ChatColor.GRAY + "currently in");
        lore.add(ChatColor.GRAY + "the " + ChatColor.GREEN + "Prison" + ChatColor.GRAY + ", and hatch " + ChatColor.GREEN + "Pet Eggs" + ChatColor.GRAY + "!");
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Travel");

        pets.setItemMeta(petsItemMeta);
        pets.setLore(lore);

        return pets;
    }

    private ItemStack blackMarket(Player player) {
        final ItemStack blackMarket = new ItemStack(Material.SUNFLOWER);
        final ItemMeta blackMarketItemMeta = blackMarket.getItemMeta();

        blackMarketItemMeta.displayName(Component.text(ChatColor.GREEN + "Black Market"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "Purchase a wide variety of " + ChatColor.GREEN + "Gear, Items, Buffs,");
        lore.add(ChatColor.GREEN + "Enchantments, " + ChatColor.GRAY + "and more from the " + ChatColor.YELLOW + "Vendors" + ChatColor.GRAY + "!");
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Travel");

        blackMarket.setItemMeta(blackMarketItemMeta);
        blackMarket.setLore(lore);

        return blackMarket;
    }


    private String parsePlaceholder(Player player, String string) {
        return PlaceholderAPI.setPlaceholders(player, "%" + string + "%");
    }

}

