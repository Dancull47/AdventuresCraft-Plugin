package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.mainMenu;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Dependency;
import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import me.clip.placeholderapi.PlaceholderAPI;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.GUI.GUIHelper;
import monzter.adventurescraft.plugin.utilities.enums.Prefix;
import monzter.adventurescraft.plugin.utilities.enums.PrisonStatsDisplay;
import monzter.adventurescraft.plugin.utilities.general.ConsoleCommand;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
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

public class DonationMenu extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final SoundManager soundManager;
    private final GUIHelper guiHelper;
    private final ConsoleCommand consoleCommand;

    final TextComponent donate = Component.text("You can donate to get epic rewards from our")
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

    public DonationMenu(AdventuresCraft plugin, SoundManager soundManager, GUIHelper guiHelper, ConsoleCommand consoleCommand) {
        this.plugin = plugin;
        this.soundManager = soundManager;
        this.guiHelper = guiHelper;
        this.consoleCommand = consoleCommand;
    }

    @CommandAlias("donate|donationRewards|donationMenu")
    public void donate(Player player) {
        player.sendMessage(donate);

        ChestGui gui = new ChestGui(4, guiHelper.guiName("Donate"));
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        OutlinePane background = new OutlinePane(0, 0, 9, 4, Pane.Priority.LOWEST);
        StaticPane display = new StaticPane(0, 0, 9, 4, Pane.Priority.LOW);


        background.addItem(new GuiItem(guiHelper.background(Material.ORANGE_STAINED_GLASS_PANE)));
        background.setRepeat(true);


        display.addItem(new GuiItem(adventureCoins(player), e -> player.sendMessage(donate)), 2, 1);
        display.addItem(new GuiItem(adventureShop(player), e -> player.performCommand("DonationShop")), 4, 1);
        display.addItem(new GuiItem(miningPass(player), e -> {
            if (e.isLeftClick())
                player.sendMessage(miningPass);
            if (e.isRightClick())
                player.performCommand("miningPass");
        }), 6, 1);

        display.addItem(new GuiItem(guiHelper.backButton(), e -> player.performCommand("main")), 4, 3);

        gui.addPane(background);
        gui.addPane(display);
        gui.show(player);
    }


    private ItemStack adventureCoins(Player player) {
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
        lore.add(PrisonStatsDisplay.ADVENTURE_COINS.getName() + ChatColor.RED + ": " + parsePlaceholder(player, "ac_Currency_AdventureCoins"));
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Purchase");

        adventureCoins.setItemMeta(adventureCoinsItemMeta);
        adventureCoins.setLore(lore);

        return adventureCoins;
    }

    private ItemStack adventureShop(Player player) {
        final ItemStack adventureShop = new ItemStack(Material.CHEST);
        final ItemMeta adventureShopItemMeta = adventureShop.getItemMeta();

        adventureShopItemMeta.displayName(Component.text(ChatColor.GOLD + "Adventure Shop"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "Use your " + PrisonStatsDisplay.ADVENTURE_COINS.getName());
        lore.add(ChatColor.GRAY + "to purchase unique " + ChatColor.GREEN + "Items" + ChatColor.GRAY + "!");
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View");

        adventureShop.setItemMeta(adventureShopItemMeta);
        adventureShop.setLore(lore);

        return adventureShop;
    }

    private ItemStack miningPass(Player player) {
        final ItemStack miningPass = new ItemStack(Material.HOPPER_MINECART);
        final ItemMeta miningPassItemMeta = miningPass.getItemMeta();

        miningPassItemMeta.displayName(Component.text(ChatColor.YELLOW + "Mining Pass " + ChatColor.GRAY + "-" + ChatColor.GREEN + " $9.99"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "Get additional items and ways");
        lore.add(ChatColor.GRAY + "to level up your " + ChatColor.YELLOW + "Mining Pass" + ChatColor.GRAY + "!");
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Left-Click to Purchase");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Right-Click to View");

        miningPass.setItemMeta(miningPassItemMeta);
        miningPass.setLore(lore);

        return miningPass;
    }

    private String parsePlaceholder(Player player, String string) {
        return PlaceholderAPI.setPlaceholders(player, "%" + string + "%");
    }

}

