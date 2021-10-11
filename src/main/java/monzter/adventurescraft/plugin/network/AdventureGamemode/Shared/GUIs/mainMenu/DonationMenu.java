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
import monzter.adventurescraft.plugin.utilities.beton.BetonPointsManager;
import monzter.adventurescraft.plugin.utilities.enums.Prefix;
import monzter.adventurescraft.plugin.utilities.enums.PrisonStatsDisplay;
import monzter.adventurescraft.plugin.utilities.general.ConsoleCommand;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import monzter.adventurescraft.plugin.utilities.text.NumberFormat;
import net.Indyuce.mmoitems.MMOItems;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.betoncraft.betonquest.BetonQuest;

import java.util.ArrayList;
import java.util.List;

public class DonationMenu extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final SoundManager soundManager;
    private final GUIHelper guiHelper;
    private final ConsoleCommand consoleCommand;
    private final BetonPointsManager betonPointsManager;
    private final NumberFormat numberFormat;
    private final MMOItems mmoItems;


    final TextComponent donate = Component.text("You can donate to get epic rewards from our")
            .color(NamedTextColor.GREEN)
            .append(Component.text(" Store", NamedTextColor.GOLD))
            .append(Component.text("!"))
            .append(Component.text(" <- CLICK HERE", NamedTextColor.GOLD, TextDecoration.BOLD))
            .hoverEvent(Component.text("Click to visit the Store!", NamedTextColor.GREEN))
            .clickEvent(ClickEvent.openUrl("https://store.adventurescraft.net"));

    public DonationMenu(AdventuresCraft plugin, SoundManager soundManager, GUIHelper guiHelper, ConsoleCommand consoleCommand, BetonPointsManager betonPointsManager, NumberFormat numberFormat, MMOItems mmoItems) {
        this.plugin = plugin;
        this.soundManager = soundManager;
        this.guiHelper = guiHelper;
        this.consoleCommand = consoleCommand;
        this.betonPointsManager = betonPointsManager;
        this.numberFormat = numberFormat;
        this.mmoItems = mmoItems;
    }

    @CommandAlias("donate|donationRewards|donationMenu")
    public void donate(Player player) {
        player.sendMessage(donate);

        ChestGui gui = new ChestGui(4, guiHelper.guiName("Donate"));
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        OutlinePane background = new OutlinePane(0, 0, 9, 4, Pane.Priority.LOWEST);
        StaticPane display = new StaticPane(0, 0, 9, 4, Pane.Priority.LOW);


        background.addItem(new GuiItem(guiHelper.background(Material.LIGHT_BLUE_STAINED_GLASS_PANE)));
        background.setRepeat(true);


        display.addItem(new GuiItem(adventureCoins(player), e -> player.sendMessage(donate)), 2, 1);
        display.addItem(new GuiItem(crates(player), e -> player.performCommand("crateshop")), 3, 1);
        display.addItem(new GuiItem(skins(player), e -> player.performCommand("skinshop")), 4, 1);
        display.addItem(new GuiItem(boosters(player), e -> player.performCommand("boosterShop")), 5, 1);
        display.addItem(new GuiItem(ranks(player), e -> player.performCommand("ranks")), 6, 1);

        display.addItem(new GuiItem(guiHelper.backButton(), e -> player.performCommand("main")), 4, 3);

        gui.addPane(background);
        gui.addPane(display);
        gui.show(player);
    }


    private ItemStack adventureCoins(Player player) {
        int balance = betonPointsManager.getPoints("items.AdventureCoin", BetonQuest.getInstance().getPlayerData(player.getUniqueId().toString()).getPoints());
        final ItemStack adventureCoins = new ItemStack(Material.SUNFLOWER);
        final ItemMeta adventureCoinsItemMeta = adventureCoins.getItemMeta();

        adventureCoinsItemMeta.displayName(Component.text(ChatColor.RED + "Adventure Coins"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "This is our currency which can be purchased");
        lore.add(ChatColor.GRAY + "from our " + ChatColor.GOLD + ChatColor.BOLD + "Store " + ChatColor.GRAY + "and used in-game for " + ChatColor.GREEN + "Rewards" + ChatColor.GRAY + "!");
        lore.add("");
        lore.add(ChatColor.GRAY + "The currency is NOT a physical item,");
        lore.add(ChatColor.GRAY + "and can not be traded to others.");
        lore.add("");
        lore.add(PrisonStatsDisplay.ADVENTURE_COINS.getName() + ChatColor.RED + ": " + numberFormat.numberFormat(balance));
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Purchase");

        adventureCoins.setItemMeta(adventureCoinsItemMeta);
        adventureCoins.setLore(lore);

        return adventureCoins;
    }

    private ItemStack crates(Player player) {
        final ItemStack adventureShop = new ItemStack(Material.CHEST);
        final ItemMeta adventureShopItemMeta = adventureShop.getItemMeta();

        adventureShopItemMeta.displayName(Component.text(ChatColor.GOLD + "DropTables"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "Use your " + PrisonStatsDisplay.ADVENTURE_COINS.getName());
        lore.add(ChatColor.GRAY + "to purchase " + ChatColor.GREEN + "DropTables " + ChatColor.GRAY + "which ");
        lore.add(ChatColor.GRAY + "hold " + ChatColor.GREEN + "Skins " + ChatColor.GRAY + "or " + ChatColor.BLUE + "Boosters" + ChatColor.GRAY + "!");
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View");

        adventureShop.setItemMeta(adventureShopItemMeta);
        adventureShop.setLore(lore);

        return adventureShop;
    }

    private ItemStack skins(Player player) {
        final ItemStack adventureShop = mmoItems.getItem("SKIN", "WORLD_SPLITTER_SWORD5_2");
        final ItemMeta adventureShopItemMeta = adventureShop.getItemMeta();

        adventureShopItemMeta.displayName(Component.text(ChatColor.GOLD + "Skins"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "Use your " + PrisonStatsDisplay.ADVENTURE_COINS.getName() + ChatColor.GRAY + " to");
        lore.add(ChatColor.GRAY + "purchase " + ChatColor.GREEN + "Skins " + ChatColor.GRAY + "for your " + ChatColor.GOLD + "Gear" + ChatColor.GRAY + "!");
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View");

        adventureShop.setItemMeta(adventureShopItemMeta);
        adventureShop.setLore(lore);

        return adventureShop;
    }

    private ItemStack boosters(Player player) {
        final ItemStack adventureShop = new ItemStack(Material.POTION);
        final ItemMeta adventureShopItemMeta = adventureShop.getItemMeta();
        adventureShopItemMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);

        adventureShopItemMeta.displayName(Component.text(ChatColor.GOLD + "Boosters"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "Use your " + PrisonStatsDisplay.ADVENTURE_COINS.getName() + ChatColor.GRAY + " to purchase");
        lore.add(ChatColor.GREEN + "Boosters " + ChatColor.GRAY + "for the entire Server!");
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View");

        adventureShop.setItemMeta(adventureShopItemMeta);
        adventureShop.setLore(lore);

        return adventureShop;
    }

    private ItemStack ranks(Player player) {
        final ItemStack adventureShop = new ItemStack(Material.NAME_TAG);
        final ItemMeta adventureShopItemMeta = adventureShop.getItemMeta();

        adventureShopItemMeta.displayName(Component.text(ChatColor.GOLD + "Ranks"));

        String rank = ChatColor.GRAY + "Default";
        if (player.hasPermission("Alpha.Rank"))
            rank = ChatColor.YELLOW + "Alpha";
        if (player.hasPermission("Rank1"))
            rank = ChatColor.GREEN + "Explorer";
        if (player.hasPermission("Rank2"))
            rank = ChatColor.BLUE + "Adventurer";
        if (player.hasPermission("Rank3"))
            rank = ChatColor.RED + "Conquerer";
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.WHITE + "Current Rank: " + rank);
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View");

        adventureShop.setItemMeta(adventureShopItemMeta);
        adventureShop.setLore(lore);

        return adventureShop;
    }

}

