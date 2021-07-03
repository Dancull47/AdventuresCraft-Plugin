package monzter.adventurescraft.plugin.network.Shared.Commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
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
import monzter.adventurescraft.plugin.utilities.general.ConsoleCommand;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import monzter.adventurescraft.plugin.utilities.luckperms.PermissionLP;
import monzter.adventurescraft.plugin.utilities.text.NumberFormat;
import net.Indyuce.mmoitems.MMOItems;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Ranks extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final SoundManager soundManager;
    private final GUIHelper guiHelper;
    private final ConsoleCommand consoleCommand;
    private final BetonPointsManager betonPointsManager;
    private final NumberFormat numberFormat;
    private final MMOItems mmoItems;
    private final PermissionLP permissionLP;


    final TextComponent donate = Component.text("You can donate to get Ranks from our")
            .color(NamedTextColor.GREEN)
            .append(Component.text(" Store", NamedTextColor.GOLD))
            .append(Component.text("!"))
            .append(Component.text(" <- CLICK HERE", NamedTextColor.GOLD, TextDecoration.BOLD))
            .hoverEvent(Component.text("Click to visit the Store!", NamedTextColor.GREEN))
            .clickEvent(ClickEvent.openUrl("https://store.adventurescraft.net/category/Rank"));

    public Ranks(AdventuresCraft plugin, SoundManager soundManager, GUIHelper guiHelper, ConsoleCommand consoleCommand, BetonPointsManager betonPointsManager, NumberFormat numberFormat, MMOItems mmoItems, PermissionLP permissionLP) {
        this.plugin = plugin;
        this.soundManager = soundManager;
        this.guiHelper = guiHelper;
        this.consoleCommand = consoleCommand;
        this.betonPointsManager = betonPointsManager;
        this.numberFormat = numberFormat;
        this.mmoItems = mmoItems;
        this.permissionLP = permissionLP;
    }

    @CommandAlias("rank|ranks")
    @CommandPermission("*")
    public void rank(CommandSender player, String playerName) {
        if (Bukkit.getPlayer(playerName) != null)
            rank(Bukkit.getPlayer(playerName));
    }

    @CommandAlias("rank|ranks")
    public void rank(Player player) {
        int length = 4;
        if (plugin.SERVER.equals("Lobby"))
            length = 3;
        ChestGui gui = new ChestGui(length, guiHelper.guiName("Ranks"));
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        OutlinePane background = new OutlinePane(0, 0, 9, length, Pane.Priority.LOWEST);
        StaticPane display = new StaticPane(0, 0, 9, length, Pane.Priority.LOW);


        background.addItem(new GuiItem(guiHelper.background(Material.YELLOW_STAINED_GLASS_PANE)));
        background.setRepeat(true);


        display.addItem(explorerRank(player), 3, 1);
        display.addItem(adventurerRank(player), 4, 1);
        display.addItem(conquererRank(player), 5, 1);

        if (!plugin.SERVER.equals("Lobby"))
            display.addItem(new GuiItem(guiHelper.backButton(), e -> player.performCommand("donationMenu")), 4, length - 1);

        gui.addPane(background);
        gui.addPane(display);
        gui.show(player);
    }


    private GuiItem explorerRank(Player player) {
        boolean hasPermission = false;
        if (player.hasPermission("Rank1"))
            hasPermission = true;
        final ItemStack adventureShop = new ItemStack(Material.IRON_SWORD);
        final ItemMeta adventureShopItemMeta = adventureShop.getItemMeta();
        if (hasPermission)
            adventureShopItemMeta.addEnchant(Enchantment.ARROW_FIRE, 1, true);
        adventureShopItemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        adventureShopItemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        if (hasPermission)
            adventureShopItemMeta.displayName(Component.text(ChatColor.GREEN + "Explorer Rank " + Prefix.PREFIX.getString() + ChatColor.GOLD + ChatColor.BOLD + "PURCHASED!"));
        else
            adventureShopItemMeta.displayName(Component.text(ChatColor.GREEN + "Explorer Rank " + Prefix.PREFIX.getString() + ChatColor.YELLOW + ChatColor.BOLD + "$4.99"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.WHITE + "[" + ChatColor.GREEN + "Explorer" + ChatColor.WHITE + "]" + ChatColor.WHITE + " Name-Tag Prefix");
        lore.add(Prefix.PREFIX.getString() + ChatColor.WHITE + "Access to occasional Maintenance");
        lore.add(Prefix.PREFIX.getString() + ChatColor.WHITE + "+250 " + ChatColor.RED + "AdventureCoins");
        lore.add("");
        lore.add(ChatColor.GOLD.toString() + ChatColor.BOLD + "ADVENTURE GAME MODE REWARDS:");
        lore.add(Prefix.PREFIX.getString() + ChatColor.WHITE + "+54 " + ChatColor.GREEN + "Bank Slots");
        lore.add("");
        lore.add(ChatColor.GOLD.toString() + ChatColor.BOLD + "PRISON GAME MODE REWARDS:");
        lore.add(Prefix.PREFIX.getString() + ChatColor.WHITE + "+54 " + ChatColor.GREEN + "Bank Slots");

        if (!hasPermission) {
            lore.add("");
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Visit Store");
        }
        if (hasPermission && !player.hasPermission("Explorer.AC.Claimed")) {
            lore.add("");
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Claim Rewards");
        }
        adventureShop.setItemMeta(adventureShopItemMeta);
        adventureShop.setLore(lore);

        return new GuiItem(adventureShop, e -> {
            if (!player.hasPermission("Rank1")) {
                player.sendMessage(donate);
            } else if (!player.hasPermission("Explorer.AC.Claimed")) {
                switch (plugin.SERVER) {
                    case "Adventure":
                    case "Home":
                    case "Prison":
                    case "Cell":
                        consoleCommand.consoleCommand("reward ac 250 " + player.getName());
                        consoleCommand.consoleCommand("reward bs 54 " + player.getName());
                        permissionLP.givePermission(player, "Explorer.AC.Claimed");
                        soundManager.playSound(player, Sound.ENTITY_VILLAGER_CELEBRATE, 1, 1);
                        break;
                    case "Lobby":
                        consoleCommand.consoleCommand("reward ac 250 " + player.getName());
                        permissionLP.givePermission(player, "Explorer.AC.Claimed");
                        soundManager.playSound(player, Sound.ENTITY_VILLAGER_CELEBRATE, 1, 1);
                        break;
                }
            } else {
                player.sendMessage(ChatColor.RED + "You already claimed your rewards!");
                soundManager.soundNo(player, 1);
            }
            player.closeInventory();
        });
    }

    private GuiItem adventurerRank(Player player) {
        boolean hasPermission = false;
        if (player.hasPermission("Rank2"))
            hasPermission = true;
        final ItemStack adventureShop = new ItemStack(Material.GOLDEN_SWORD);
        final ItemMeta adventureShopItemMeta = adventureShop.getItemMeta();
        if (hasPermission)
            adventureShopItemMeta.addEnchant(Enchantment.ARROW_FIRE, 1, true);
        adventureShopItemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        adventureShopItemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        if (hasPermission)
            adventureShopItemMeta.displayName(Component.text(ChatColor.BLUE + "Adventurer " + ChatColor.GREEN + "Rank " + Prefix.PREFIX.getString() + ChatColor.GOLD + ChatColor.BOLD + "PURCHASED!"));
        else
            adventureShopItemMeta.displayName(Component.text(ChatColor.BLUE + "Adventurer " + ChatColor.GREEN + "Rank " + Prefix.PREFIX.getString() + ChatColor.YELLOW + ChatColor.BOLD + "$9.99"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.WHITE + "[" + ChatColor.BLUE + "Adventurer" + ChatColor.WHITE + "]" + ChatColor.WHITE + " Name-Tag Prefix");
        lore.add(Prefix.PREFIX.getString() + ChatColor.WHITE + "Access to occasional Maintenance");
        lore.add(Prefix.PREFIX.getString() + ChatColor.WHITE + "+500 " + ChatColor.RED + "AdventureCoins");
        lore.add("");
        lore.add(ChatColor.GOLD.toString() + ChatColor.BOLD + "ADVENTURE GAME MODE REWARDS:");
        lore.add(Prefix.PREFIX.getString() + ChatColor.WHITE + "+108 " + ChatColor.GREEN + "Bank Slots");
        lore.add(Prefix.PREFIX.getString() + ChatColor.WHITE + "Access to " + ChatColor.GOLD + "/Sell");
        lore.add(Prefix.PREFIX.getString() + ChatColor.WHITE + "Ability to set " + ChatColor.GREEN + "Music " + ChatColor.WHITE + "within");
        lore.add(ChatColor.WHITE + "your " + ChatColor.GREEN + "Home " + ChatColor.WHITE + "by using " + ChatColor.GOLD + "/p Music");
        lore.add(ChatColor.WHITE + "");
        lore.add(ChatColor.GOLD.toString() + ChatColor.BOLD + "PRISON GAME MODE REWARDS:");
        lore.add(Prefix.PREFIX.getString() + ChatColor.WHITE + "+108 " + ChatColor.GREEN + "Bank Slots");
        lore.add(Prefix.PREFIX.getString() + ChatColor.WHITE + "Access to " + ChatColor.GOLD + "/Sell");
        if (!hasPermission) {
            lore.add("");
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Visit Store");
        }
        if (hasPermission && !player.hasPermission("Adventurer.AC.Claimed")) {
            lore.add("");
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Claim Rewards");
        }
        adventureShop.setItemMeta(adventureShopItemMeta);
        adventureShop.setLore(lore);

        return new GuiItem(adventureShop, e -> {
            if (!player.hasPermission("Rank2")) {
                player.sendMessage(donate);
            } else if (!player.hasPermission("Adventurer.AC.Claimed")) {
                switch (plugin.SERVER) {
                    case "Adventure":
                    case "Home":
                    case "Prison":
                    case "Cell":
                        consoleCommand.consoleCommand("reward ac 500 " + player.getName());
                        consoleCommand.consoleCommand("reward bs 108 " + player.getName());
                        permissionLP.givePermission(player, "Adventurer.AC.Claimed");
                        soundManager.playSound(player, Sound.ENTITY_VILLAGER_CELEBRATE, 1, 1);
                        break;
                    case "Lobby":
                        consoleCommand.consoleCommand("reward ac 500 " + player.getName());
                        permissionLP.givePermission(player, "Adventurer.AC.Claimed");
                        soundManager.playSound(player, Sound.ENTITY_VILLAGER_CELEBRATE, 1, 1);
                        break;
                }
            } else {
                player.sendMessage(ChatColor.RED + "You already claimed your rewards!");
                soundManager.soundNo(player, 1);
            }
            player.closeInventory();
        });
    }

    private GuiItem conquererRank(Player player) {
        boolean hasPermission = false;
        if (player.hasPermission("Rank3"))
            hasPermission = true;
        final ItemStack adventureShop = new ItemStack(Material.DIAMOND_SWORD);
        final ItemMeta adventureShopItemMeta = adventureShop.getItemMeta();
        if (hasPermission)
            adventureShopItemMeta.addEnchant(Enchantment.ARROW_FIRE, 1, true);
        adventureShopItemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        adventureShopItemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        if (hasPermission)
            adventureShopItemMeta.displayName(Component.text(ChatColor.RED + "Conquerer " + ChatColor.GREEN + "Rank " + Prefix.PREFIX.getString() + ChatColor.GOLD + ChatColor.BOLD + "PURCHASED!"));
        else
            adventureShopItemMeta.displayName(Component.text(ChatColor.RED + "Conquerer " + ChatColor.GREEN + "Rank " + Prefix.PREFIX.getString() + ChatColor.YELLOW + ChatColor.BOLD + "$19.99"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.WHITE + "[" + ChatColor.RED + "Conquerer" + ChatColor.WHITE + "]" + ChatColor.WHITE + " Name-Tag Prefix");
        lore.add(Prefix.PREFIX.getString() + ChatColor.WHITE + "Access to occasional Maintenance");
        lore.add(Prefix.PREFIX.getString() + ChatColor.WHITE + "+1,000 " + ChatColor.RED + "AdventureCoins");
        lore.add("");
        lore.add(ChatColor.GOLD.toString() + ChatColor.BOLD + "ADVENTURE GAME MODE REWARDS:");
        lore.add(Prefix.PREFIX.getString() + ChatColor.WHITE + "+162 " + ChatColor.GREEN + "Bank Slots");
        lore.add(Prefix.PREFIX.getString() + ChatColor.WHITE + "Access to " + ChatColor.GOLD + "/Sell");
        lore.add(Prefix.PREFIX.getString() + ChatColor.WHITE + "Access to many " + ChatColor.GREEN + "Vendors ");
        lore.add(ChatColor.WHITE + "from anywhere using " + ChatColor.GOLD + "/Shop");
        lore.add(Prefix.PREFIX.getString() + ChatColor.WHITE + "Access to " + ChatColor.GREEN + "Fast Travel");
        lore.add(ChatColor.GREEN + "Map " + ChatColor.WHITE + "using " + ChatColor.GOLD + "/Travel");
        lore.add(Prefix.PREFIX.getString() + ChatColor.WHITE + "Ability to set " + ChatColor.GREEN + "Music " + ChatColor.WHITE + "within");
        lore.add(ChatColor.WHITE + "your " + ChatColor.GREEN + "Home " + ChatColor.WHITE + "by using " + ChatColor.GOLD + "/p Music");
        lore.add(ChatColor.WHITE + "");
        lore.add(ChatColor.GOLD.toString() + ChatColor.BOLD + "PRISON GAME MODE REWARDS:");
        lore.add(Prefix.PREFIX.getString() + ChatColor.WHITE + "+162 " + ChatColor.GREEN + "Bank Slots");
        lore.add(Prefix.PREFIX.getString() + ChatColor.WHITE + "Access to " + ChatColor.GOLD + "/Sell");
        if (!hasPermission) {
            lore.add("");
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Visit Store");
        }
        if (hasPermission && !player.hasPermission("Conquerer.AC.Claimed")) {
            lore.add("");
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Claim Rewards");
        }
        adventureShop.setItemMeta(adventureShopItemMeta);
        adventureShop.setLore(lore);

        return new GuiItem(adventureShop, e -> {
            if (!player.hasPermission("Rank3")) {
                player.sendMessage(donate);
            } else if (!player.hasPermission("Conquerer.AC.Claimed")) {
                switch (plugin.SERVER) {
                    case "Adventure":
                    case "Home":
                    case "Prison":
                    case "Cell":
                        consoleCommand.consoleCommand("reward ac 1000 " + player.getName());
                        consoleCommand.consoleCommand("reward bs 162 " + player.getName());
                        permissionLP.givePermission(player, "Conquerer.AC.Claimed");
                        soundManager.playSound(player, Sound.ENTITY_VILLAGER_CELEBRATE, 1, 1);
                        break;
                    case "Lobby":
                        consoleCommand.consoleCommand("reward ac 1000 " + player.getName());
                        permissionLP.givePermission(player, "Conquerer.AC.Claimed");
                        soundManager.playSound(player, Sound.ENTITY_VILLAGER_CELEBRATE, 1, 1);
                        break;
                }
            } else {
                player.sendMessage(ChatColor.RED + "You already claimed your rewards!");
                soundManager.soundNo(player, 1);
            }
            player.closeInventory();
        });
    }
}

