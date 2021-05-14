package monzter.adventurescraft.plugin.shared.GUIs.mainMenu;

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
import monzter.adventurescraft.plugin.utilities.general.ConsoleCommand;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import net.kyori.adventure.text.Component;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Map extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final SoundManager soundManager;
    private final GUIHelper guiHelper;
    private final ConsoleCommand consoleCommand;

    public Map(AdventuresCraft plugin, SoundManager soundManager, GUIHelper guiHelper, ConsoleCommand consoleCommand) {
        this.plugin = plugin;
        this.soundManager = soundManager;
        this.guiHelper = guiHelper;
        this.consoleCommand = consoleCommand;
    }

    @CommandAlias("map")
    public void questMenu(Player player) {

        ChestGui gui = new ChestGui(4, guiHelper.guiName("Map"));
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        OutlinePane background = new OutlinePane(0, 0, 9, 4, Pane.Priority.LOWEST);
        StaticPane display = new StaticPane(0, 0, 9, 4, Pane.Priority.LOW);


        background.addItem(new GuiItem(guiHelper.background(Material.YELLOW_STAINED_GLASS_PANE)));
        background.setRepeat(true);

        display.addItem(new GuiItem(rankMines(player), e -> player.performCommand("RankMines")), 3, 1);
        display.addItem(new GuiItem(prestigeMines(player), e -> player.performCommand("PrestigeMines")), 4, 1);
        display.addItem(new GuiItem(eventMines(player), e -> player.performCommand("EventMines")), 5, 1);

        display.addItem(new GuiItem(crates(player), e -> player.performCommand("warp crates")), 2, 3);
        display.addItem(new GuiItem(yard(player), e -> player.performCommand("warp yard")), 3, 3);
        display.addItem(new GuiItem(guiHelper.backButton(), e -> player.performCommand("main")), 4, 3);
        display.addItem(new GuiItem(pets(player), e -> player.performCommand("warp pets")), 5, 3);
        display.addItem(new GuiItem(blackMarket(player), e -> player.performCommand("warp shop")), 6, 3);

        gui.addPane(background);
        gui.addPane(display);
        gui.show(player);
    }

    private ItemStack rankMines(Player player) {
        final ItemStack rankMines = new ItemStack(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTY3ZDgxM2FlN2ZmZTViZTk1MWE0ZjQxZjJhYTYxOWE1ZTM4OTRlODVlYTVkNDk4NmY4NDk0OWM2M2Q3NjcyZSJ9fX0="));
        final ItemMeta rankMinesItemMeta = rankMines.getItemMeta();

        rankMinesItemMeta.displayName(Component.text(ChatColor.GREEN + "Rank Mines"));

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_GRAY + "/RankMines");
        lore.add("");
        lore.add(ChatColor.GRAY + "Travel through new  " + ChatColor.GREEN + "Rank Mines " + ChatColor.GRAY + "as");
        lore.add(ChatColor.GRAY + "you progress through the " + ChatColor.GREEN + "Ranks" + ChatColor.GRAY + "!");
        lore.add("");
        lore.add(Prefix.PREFIX.getPrefix() + ChatColor.YELLOW + "Click to Travel");

        rankMines.setItemMeta(rankMinesItemMeta);
        rankMines.setLore(lore);

        return rankMines;
    }

    private ItemStack prestigeMines(Player player) {
        final ItemStack prestigeMines = new ItemStack(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzFhOTQ2M2ZkM2M0MzNkNWUxZDlmZWM2ZDVkNGIwOWE4M2E5NzBiMGI3NGRkNTQ2Y2U2N2E3MzM0OGNhYWIifX19"));
        final ItemMeta prestigeMinesItemMeta = prestigeMines.getItemMeta();

        prestigeMinesItemMeta.displayName(Component.text(ChatColor.GREEN + "Prestige Mines"));

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_GRAY + "/PrestigeMines");
        lore.add("");
        lore.add(ChatColor.GRAY + "Travel through new  " + ChatColor.BLUE + "Prestige Mines " + ChatColor.GRAY + "which");
        lore.add(ChatColor.GRAY + "offer higher yielding " + ChatColor.GOLD + "Resources" + ChatColor.GRAY + "!");
        lore.add("");
        lore.add(Prefix.PREFIX.getPrefix() + ChatColor.YELLOW + "Click to Travel");

        prestigeMines.setItemMeta(prestigeMinesItemMeta);
        prestigeMines.setLore(lore);

        return prestigeMines;
    }

    private ItemStack eventMines(Player player) {
        final ItemStack eventMines = new ItemStack(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTY0ZTI5NGVhYWI0ODZlYWIzZDQ5YWM5NWFjNmM3ZGYxYmEyM2RiN2Y1N2UwODJmOGMyMDNiNThiM2JhZThiYSJ9fX0="));
        final ItemMeta eventMinesItemMeta = eventMines.getItemMeta();

        eventMinesItemMeta.displayName(Component.text(ChatColor.LIGHT_PURPLE + "Event Mine"));

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_GRAY + "/EventMine");
        lore.add("");
        lore.add(ChatColor.GRAY + "Explore new " + ChatColor.LIGHT_PURPLE + "Event Mines " + ChatColor.GRAY + "which");
        lore.add(ChatColor.GRAY + "are constantly changing each week!");
        lore.add("");
        lore.add(ChatColor.WHITE.toString() + ChatColor.BOLD + "Active Event: " + ChatColor.GREEN + ChatColor.BOLD + "Beach Mine " + ChatColor.RED + ChatColor.MAGIC + "|");
        lore.add("");
        lore.add(Prefix.PREFIX.getPrefix() + ChatColor.YELLOW + "Click to Travel");

        eventMines.setItemMeta(eventMinesItemMeta);
        eventMines.setLore(lore);

        return eventMines;
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
        lore.add(Prefix.PREFIX.getPrefix() + ChatColor.YELLOW + "Click to Travel");

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
        lore.add(Prefix.PREFIX.getPrefix() + ChatColor.YELLOW + "Click to Travel");

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
        lore.add(Prefix.PREFIX.getPrefix() + ChatColor.YELLOW + "Click to Travel");

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
        lore.add(Prefix.PREFIX.getPrefix() + ChatColor.YELLOW + "Click to Travel");

        blackMarket.setItemMeta(blackMarketItemMeta);
        blackMarket.setLore(lore);

        return blackMarket;
    }


    private String parsePlaceholder(Player player, String string) {
        return PlaceholderAPI.setPlaceholders(player, "%" + string + "%");
    }

}

