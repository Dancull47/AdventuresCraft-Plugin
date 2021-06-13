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
import monzter.adventurescraft.plugin.network.AdventureGamemode.Enums.EnchantedMaterialList;
import monzter.adventurescraft.plugin.utilities.GUI.GUIHelper;
import monzter.adventurescraft.plugin.utilities.beton.BetonPointsManager;
import monzter.adventurescraft.plugin.utilities.enums.Prefix;
import monzter.adventurescraft.plugin.utilities.general.ConsoleCommand;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import monzter.adventurescraft.plugin.utilities.mmoitems.MMOItemsGive;
import monzter.adventurescraft.plugin.utilities.text.NumberFormat;
import monzter.adventurescraft.plugin.utilities.vault.Economy;
import net.Indyuce.mmoitems.MMOItems;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class VoteShop extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final SoundManager soundManager;
    private final GUIHelper guiHelper;
    private final ConsoleCommand consoleCommand;
    private final NumberFormat numberFormat;
    private final MMOItemsGive mmoItemsGive;
    private final BetonPointsManager betonPointsManager;
    private final Economy economy;
    final TextComponent vote = Component.text("You can")
            .color(NamedTextColor.GREEN)
            .append(Component.text(" Vote ", NamedTextColor.GOLD))
            .hoverEvent(Component.text("Click to visit Voting Guide!", NamedTextColor.GREEN))
            .clickEvent(ClickEvent.openUrl("https://www.adventurescraft.net/wiki/site/vote/"))
            .append(Component.text("for our Server, to receive awesome rewards every day!"));


    public VoteShop(AdventuresCraft plugin, SoundManager soundManager, GUIHelper guiHelper, ConsoleCommand consoleCommand, NumberFormat numberFormat, MMOItemsGive mmoItemsGive, BetonPointsManager betonPointsManager, Economy economy) {
        this.plugin = plugin;
        this.soundManager = soundManager;
        this.guiHelper = guiHelper;
        this.consoleCommand = consoleCommand;
        this.numberFormat = numberFormat;
        this.mmoItemsGive = mmoItemsGive;
        this.betonPointsManager = betonPointsManager;
        this.economy = economy;
    }

    @CommandAlias("vote|voteShop|voting|voteReward")
    public void daily(Player player) {
        int dailyPoints = Integer.valueOf(PlaceholderAPI.setPlaceholders(player, "%betonquest_items:point.Vote.amount%"));
        player.sendMessage(vote);
        ChestGui gui = new ChestGui(4, guiHelper.guiName("Voting Rewards"));
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        OutlinePane background = new OutlinePane(0, 0, 9, 4, Pane.Priority.LOWEST);
        OutlinePane display = new OutlinePane(4, 1, 7, 4, Pane.Priority.LOW);
        StaticPane staticPane = new StaticPane(0, 0, 7, 4, Pane.Priority.LOW);

        background.addItem(new GuiItem(guiHelper.background(Material.LIME_STAINED_GLASS_PANE)));
        background.setRepeat(true);

        staticPane.addItem(new GuiItem(guiHelper.backButton(), e -> player.performCommand("main")), 4, 3);
        staticPane.addItem(new GuiItem(voting(player), e -> player.performCommand("vote")), 4, 0);

        display.addItem(new GuiItem(reward(player), e -> {
            if (dailyPoints >= 1) {
                reward(player);
                takePoints(player, 1);
                player.performCommand("voteReward");
            } else {
                player.sendMessage(ChatColor.RED + "You only have " + ChatColor.GOLD + dailyPoints + ChatColor.RED + "/" + ChatColor.GOLD + 1 + ChatColor.GREEN + " Vote Coins" + ChatColor.RED + "!");
                soundManager.soundNo(player, 1);
            }
        }));

        gui.addPane(background);
        gui.addPane(display);
        gui.addPane(staticPane);
        gui.show(player);
    }

    private ItemStack reward(Player player) {
        int dailyPoints = Integer.valueOf(PlaceholderAPI.setPlaceholders(player, "%betonquest_items:point.Vote.amount%"));
        final ItemStack voting = new ItemStack(Material.REDSTONE);
        final ItemMeta votingItemMeta = voting.getItemMeta();
        voting.setAmount(8);
        votingItemMeta.displayName(Component.text(ChatColor.GOLD + "8x " + ChatColor.BLUE + "Enchanted Materials"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.WHITE + "Price: " + ChatColor.GOLD + "1" + ChatColor.GREEN + " Vote Coin");
        if (dailyPoints >= 1) {
            lore.add(" ");
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Left-Click to Purchase");
        }
        voting.setItemMeta(votingItemMeta);
        voting.setLore(lore);

        return voting;

    }

    private ItemStack voting(Player player) {
        final ItemStack voting = new ItemStack(Material.OAK_SIGN);
        final ItemMeta votingItemMeta = voting.getItemMeta();

        votingItemMeta.displayName(Component.text(ChatColor.GREEN + "Voting"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GREEN + "Vote " + ChatColor.GRAY + "every " + ChatColor.GREEN + "24 hours " + ChatColor.GRAY + "to receive");
        lore.add(ChatColor.GRAY + "a " + ChatColor.GREEN + "Vote Coin" + ChatColor.GRAY + ", which can be redeemed");
        lore.add(ChatColor.GRAY + "for special rewards from this Shop!");
        lore.add("");
        lore.add(ChatColor.GOLD + "Voting Site #1: " + ChatColor.GREEN + parsePlaceholder(player, "custom_vote1"));
        lore.add(ChatColor.GOLD + "Voting Site #2: " + ChatColor.GREEN + parsePlaceholder(player, "custom_vote2"));
        lore.add(ChatColor.GOLD + "Voting Site #3: " + ChatColor.GREEN + parsePlaceholder(player, "custom_vote3"));
        lore.add(ChatColor.GOLD + "Voting Site #4: " + ChatColor.GREEN + parsePlaceholder(player, "custom_vote4"));
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Vote");

        voting.setItemMeta(votingItemMeta);
        voting.setLore(lore);

        return voting;
    }

    private void takePoints(Player player, int amount) {
        player.sendMessage(ChatColor.GOLD.toString() + amount + ChatColor.GREEN + " Daily Token" + ChatColor.RED + " has been deducted from your account!");
        betonPointsManager.takePoint(player, "items.Login", amount);
    }

    Random random = new Random();

    private void reward(Player player, DailyShopList dailyShopList) {
        if (MMOItems.plugin.getItem(dailyShopList.type, dailyShopList.getItemID()) != null &&
                !dailyShopList.itemID.equals("ENCHANTED_PUFFERFISH"))
            mmoItemsGive.giveMMOItem(player, dailyShopList.type, dailyShopList.getItemID(), dailyShopList.amount);
        switch (dailyShopList.getType()) {
            case "COINS25_000":
                economy.giveMoney(player, dailyShopList.amount);
                break;
            case "ENCHANTED_MATERIALS":
                mmoItemsGive.giveMMOItem(player, "MATERIAL", EnchantedMaterialList.values()[random.nextInt(EnchantedMaterialList.values().length)].getID());
        }

    }

    private String parsePlaceholder(Player player, String string) {
        return PlaceholderAPI.setPlaceholders(player, "%" + string + "%");
    }

}

