package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs;

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
import monzter.adventurescraft.plugin.utilities.enums.AdventureStatsDisplay;
import monzter.adventurescraft.plugin.utilities.enums.Prefix;
import monzter.adventurescraft.plugin.utilities.enums.PrisonStatsDisplay;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class MainMenu extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final SoundManager soundManager;
    private final GUIHelper guiHelper;
    private final int MAX_QUESTS = 101;

    public MainMenu(AdventuresCraft plugin, SoundManager soundManager, GUIHelper guiHelper) {
        this.plugin = plugin;
        this.soundManager = soundManager;
        this.guiHelper = guiHelper;
    }

    @CommandAlias("Menu|MainMenu|Main")
    public void mainMenu(Player player) {

        ChestGui gui = new ChestGui(6, guiHelper.guiName("Main Menu"));
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        OutlinePane background = new OutlinePane(0, 0, 9, 6, Pane.Priority.LOWEST);
        StaticPane display = new StaticPane(0, 0, 9, 6, Pane.Priority.LOW);


        background.addItem(new GuiItem(guiHelper.background(Material.ORANGE_STAINED_GLASS_PANE)));
        background.setRepeat(true);

        display.addItem(new GuiItem(bossdex(player), e -> player.performCommand("bossdex")), 4, 0);

        display.addItem(new GuiItem(town(player), e -> player.performCommand("town")), 3, 1);
        display.addItem(new GuiItem(map(player), e -> player.performCommand("map")), 4, 1);
        display.addItem(new GuiItem(cell(player), e -> {
            if (e.isLeftClick())
                player.performCommand("home");
            else if (e.isRightClick())
                player.performCommand("home help 1");
        }), 5, 1);

        display.addItem(new GuiItem(reputation(player), e -> player.sendMessage(ChatColor.RED + "This is still under development!")), 2, 2);
        display.addItem(new GuiItem(professions(player), e -> player.performCommand("professions")), 3, 2);
        display.addItem(new GuiItem(profile(player), e -> player.performCommand("profiles")), 4, 2);
        display.addItem(new GuiItem(quests(player), e -> {
            if (e.isLeftClick())
                player.performCommand("quest");
            if (e.isRightClick())
                player.performCommand("journal");
        }), 5, 2);
        display.addItem(new GuiItem(wiki(player), e -> player.performCommand("wiki")), 6, 2);

        display.addItem(new GuiItem(knowledge(player), e -> player.performCommand("knowledge")), 2, 3);
        display.addItem(new GuiItem(crafting(player), e -> player.performCommand("craft")), 3, 3);
        display.addItem(new GuiItem(settings(player), e -> player.performCommand("settingmenu")), 4, 3);
        display.addItem(new GuiItem(bank(player), e -> player.performCommand("bank open")), 5, 3);
        display.addItem(new GuiItem(accessoryBag(player), e -> {
            if (plugin.SERVER.equals("Adventure"))
                player.performCommand("rpginv");
            else
                player.sendMessage(ChatColor.RED + "Sorry, this cannot be access within your Home!");
        }), 6, 3);

        display.addItem(new GuiItem(votingRewards(player), e -> player.performCommand("vote")), 0, 2);
        display.addItem(new GuiItem(dailyRewards(player), e -> player.performCommand("daily")), 0, 3);

        display.addItem(new GuiItem(resourceCollector(player), e -> player.performCommand("resourceCollector")), 3, 4);
        display.addItem(new GuiItem(donationPerks(player), e -> {
            if (e.isRightClick())
                player.performCommand("donate");
            if (e.isLeftClick())
                player.performCommand("donationmenu");
        }), 4, 4);
        display.addItem(new GuiItem(social(player), e -> player.performCommand("social")), 5, 4);


        gui.addPane(background);
        gui.addPane(display);
        gui.show(player);
    }


    private ItemStack bossdex(Player player) {
        final ItemStack bossdex = new ItemStack(Material.CLOCK);
        final ItemMeta bossdexItemMeta = bossdex.getItemMeta();

        bossdexItemMeta.displayName(Component.text(ChatColor.GREEN + "Bossdex"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "Track bosses all around the world.");
        lore.add(ChatColor.GRAY + "Browse their loot and when they spawn!");
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View");

        bossdex.setItemMeta(bossdexItemMeta);
        bossdex.setLore(lore);

        return bossdex;
    }

    private ItemStack town(Player player) {
        final ItemStack town = new ItemStack(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmUyY2M0MjAxNWU2Njc4ZjhmZDQ5Y2NjMDFmYmY3ODdmMWJhMmMzMmJjZjU1OWEwMTUzMzJmYzVkYjUwIn19fQ=="));
        final ItemMeta townItemMeta = town.getItemMeta();

        townItemMeta.displayName(Component.text(ChatColor.GREEN + "Town"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Travel");

        town.setItemMeta(townItemMeta);
        town.setLore(lore);

        return town;
    }

    private ItemStack map(Player player) {
        final ItemStack map = new ItemStack(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWE3NzlkZWUyYTZiMTk4OTcwYTEwZTcxYjJiNDc0ZjQ0N2RmYmUyZjEyMGZmMjIzN2M0N2ZkMTY3Y2Q0ZjFhNyJ9fX0="));
        final ItemMeta mapItemMeta = map.getItemMeta();

        mapItemMeta.displayName(Component.text(ChatColor.GREEN + "Map"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "Unlock new areas as you rank up,");
        lore.add(ChatColor.GRAY + "quickly travel to them using the " + ChatColor.GREEN + "Map" + ChatColor.GRAY + "!");
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Travel");

        map.setItemMeta(mapItemMeta);
        map.setLore(lore);

        return map;
    }

    private ItemStack cell(Player player) {
        final ItemStack cell = new ItemStack(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTI3MTgwOWJhOTFiNDJmYjQ4NzVhZjRiYTI5OGU1ZTU1ZjQ1ZWQ3MzcyMWJjZWE4NWE0NWRiOTI2Mjg1NzRmIn19fQ=="));
        final ItemMeta cellItemMeta = cell.getItemMeta();

        cellItemMeta.displayName(Component.text(ChatColor.GREEN + "Home"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Left-Click to Travel");
        if (plugin.SERVER.equals("Home")) {
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Right-Click to Manage");
        }

        cell.setItemMeta(cellItemMeta);
        cell.setLore(lore);

        return cell;
    }

    private ItemStack accessoryBag(Player player) {
        final ItemStack backpack = new ItemStack(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWJkZjhkNTNiZGI5MzJjMjIzYzYyN2JiYjhjMWUwYzVlMzUxYTYxNmNkODA1NjkyOWM2NmU2ZGNlNDQ0MzNkYiJ9fX0="));
        final ItemMeta backpackItemMeta = backpack.getItemMeta();

        backpackItemMeta.displayName(Component.text(ChatColor.GREEN + "Accessory Bag"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "Store your accessories while,");
        lore.add(ChatColor.GRAY + "still getting their effects!");
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View");

        backpack.setItemMeta(backpackItemMeta);
        backpack.setLore(lore);

        return backpack;
    }

    private ItemStack reputation(Player player) {
        final ItemStack reputation = new ItemStack(Material.IRON_HORSE_ARMOR);
        final ItemMeta reputationItemMeta = reputation.getItemMeta();
        reputationItemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        reputationItemMeta.displayName(Component.text(ChatColor.GREEN + "Reputation"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "View your current reputation");
        lore.add(ChatColor.GRAY + "in areas around our world!");
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View");

        reputation.setItemMeta(reputationItemMeta);
        reputation.setLore(lore);

        return reputation;
    }

    private ItemStack professions(Player player) {
        final ItemStack professions = new ItemStack(Material.DIAMOND_PICKAXE);
        final ItemMeta professionsItemMeta = professions.getItemMeta();
        professionsItemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        professionsItemMeta.displayName(Component.text(ChatColor.GREEN + "Professions"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "Take up a Profession to earn");
        lore.add(ChatColor.GRAY + "rewards for completing Tasks!");
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View");

        professions.setItemMeta(professionsItemMeta);
        professions.setLore(lore);

        return professions;
    }


    private ItemStack profile(Player player) {
        final ItemStack profile = new ItemStack(SkullCreator.itemFromUuid(player.getUniqueId()));
        final ItemMeta profileItemMeta = profile.getItemMeta();

        profileItemMeta.displayName(Component.text(ChatColor.GREEN + "Profile"));

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.WHITE + "Level: " + ChatColor.GRAY + parsePlaceholder(player, "mmocore_level") + ChatColor.DARK_GRAY + " [" + ChatColor.YELLOW + parsePlaceholder(player, "mmocore_experience") + "/" + parsePlaceholder(player, "mmocore_next_level") + "EXP" + ChatColor.DARK_GRAY + "]");
        lore.add("");
        lore.add(AdventureStatsDisplay.HP.getName() + ": " + parsePlaceholder(player, "mmocore_health") + "/" + parsePlaceholder(player, "mmocore_max_health"));
        lore.add(AdventureStatsDisplay.MANA.getName() + ": " + parsePlaceholder(player, "mmocore_mana") + "/" + parsePlaceholder(player, "mmocore_stat_max_mana"));
        lore.add(AdventureStatsDisplay.ARMOR.getName() + ": " + parsePlaceholder(player, "mmocore_stat_defense"));
        lore.add(AdventureStatsDisplay.SPEED.getName() + ": " + parsePlaceholder(player, "mmocore_stat_movement_speed"));
        lore.add(AdventureStatsDisplay.DAMAGE.getName() + ": " + parsePlaceholder(player, "mmocore_stat_attack_damage"));
        lore.add(AdventureStatsDisplay.ATTACK_SPEED.getName() + ": " + parsePlaceholder(player, "mmocore_stat_attack_damage"));
        lore.add(AdventureStatsDisplay.CRITICAL_CHANCE.getName() + ": " + parsePlaceholder(player, "mmocore_stat_critical_strike_chance"));
        lore.add(AdventureStatsDisplay.CRITICAL_DAMAGE.getName() + ": " + parsePlaceholder(player, "mmocore_stat_critical_strike_power"));
        lore.add("");
        lore.add(PrisonStatsDisplay.MONEY_AMOUNT.getName() + ": " + parsePlaceholder(player, "vault_eco_balance_commas"));
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View Profile");

        profile.setItemMeta(profileItemMeta);
        profile.setLore(lore);

        return profile;
    }

    private ItemStack quests(Player player) {
        final ItemStack quests = new ItemStack(Material.BOOK);
        final ItemMeta questsItemMeta = quests.getItemMeta();

        questsItemMeta.displayName(Component.text(ChatColor.GREEN + "Quests " + parsePlaceholder(player, "betonquest_default-Points:point.QuestTotal.amount") + "/" + MAX_QUESTS));
        questsItemMeta.addEnchant(Enchantment.DAMAGE_UNDEAD, 1, true);
        questsItemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "Explore the World and");
        lore.add(ChatColor.GRAY + "help others for rewards!");
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Left-Click to open Quest Menu");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Right-Click to get a Quest Journal");

        quests.setItemMeta(questsItemMeta);
        quests.setLore(lore);

        return quests;
    }

    private ItemStack wiki(Player player) {
        final ItemStack wiki = new ItemStack(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODNkODc0ZWI4YzRjNjk3YjNmODMyYmQ4NzQ0MjZmZGY2ZDIxYmFlMzM5ZjMxNzExMDgxZmRlNTk4MzgzODZlMSJ9fX0="));
        final ItemMeta wikiItemMeta = wiki.getItemMeta();

        wikiItemMeta.displayName(Component.text(ChatColor.GREEN + "Wiki"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "Want to view official information");
        lore.add(ChatColor.GRAY + "about everything within the game?");
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View");

        wiki.setItemMeta(wikiItemMeta);
        wiki.setLore(lore);

        return wiki;
    }

    private ItemStack leaderboards(Player player) {
        final ItemStack leaderboards = new ItemStack(Material.LADDER);
        final ItemMeta leaderboardsItemMeta = leaderboards.getItemMeta();

        leaderboardsItemMeta.displayName(Component.text(ChatColor.GREEN + "Leaderboards"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "Check out who the most");
        lore.add(ChatColor.GRAY + "dedicated " + ChatColor.GREEN + "Adventurers " + ChatColor.GRAY + "are!");
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View");

        leaderboards.setItemMeta(leaderboardsItemMeta);
        leaderboards.setLore(lore);

        return leaderboards;
    }

    private ItemStack knowledge(Player player) {
        final ItemStack knowledge = new ItemStack(Material.KNOWLEDGE_BOOK);
        final ItemMeta knowledgeItemMeta = knowledge.getItemMeta();

        knowledgeItemMeta.displayName(Component.text(ChatColor.GREEN + "Knowledge"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "View craftable items you unlocked,");
        lore.add(ChatColor.GRAY + "by leveling your Professions!");
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View");

        knowledge.setItemMeta(knowledgeItemMeta);
        knowledge.setLore(lore);

        return knowledge;
    }

    private ItemStack crafting(Player player) {
        final ItemStack crafting = new ItemStack(Material.CRAFTING_TABLE);
        final ItemMeta craftingItemMeta = crafting.getItemMeta();

        craftingItemMeta.displayName(Component.text(ChatColor.GREEN + "Crafting"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "Combine materials to create items!");
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Use");

        crafting.setItemMeta(craftingItemMeta);
        crafting.setLore(lore);

        return crafting;
    }

    private ItemStack settings(Player player) {
        final ItemStack settings = new ItemStack(Material.ANVIL);
        final ItemMeta settingsItemMeta = settings.getItemMeta();

        settingsItemMeta.displayName(Component.text(ChatColor.GREEN + "Settings"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "Improve your experience by");
        lore.add(ChatColor.GRAY + "making some adjustments!");
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View");

        settings.setItemMeta(settingsItemMeta);
        settings.setLore(lore);

        return settings;
    }

    private ItemStack bank(Player player) {
        final ItemStack bank = new ItemStack(Material.ENDER_CHEST);
        final ItemMeta bankItemMeta = bank.getItemMeta();

        bankItemMeta.displayName(Component.text(ChatColor.GREEN + "Bank"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "Store extra materials you're");
        lore.add(ChatColor.GRAY + "holding without needing to");
        lore.add(ChatColor.GRAY + "return back to your Home!");
        lore.add("");
        if (player.hasPermission("bank.open.command")) {
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Open");
        } else {
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Travel");
        }

        bank.setItemMeta(bankItemMeta);
        bank.setLore(lore);

        return bank;
    }

    private ItemStack votingRewards(Player player) {
        final ItemStack votingRewards = new ItemStack(Material.CLOCK);
        final ItemMeta votingRewardsItemMeta = votingRewards.getItemMeta();

        votingRewardsItemMeta.displayName(Component.text(ChatColor.GREEN + "Voting Rewards"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GREEN + "Vote " + ChatColor.GRAY + "every " + ChatColor.GREEN + "24 hours " + ChatColor.GRAY + "to receive");
        lore.add(ChatColor.GRAY + "a " + ChatColor.GREEN + "Vote Coin" + ChatColor.GRAY + ", which can be redeemed");
        lore.add(ChatColor.GRAY + "for special rewards from this Shop!");
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View Shop");

        votingRewards.setItemMeta(votingRewardsItemMeta);
        votingRewards.setLore(lore);

        return votingRewards;
    }

    private ItemStack dailyRewards(Player player) {
        final ItemStack votingRewards = new ItemStack(Material.EMERALD);
        final ItemMeta votingRewardsItemMeta = votingRewards.getItemMeta();

        votingRewardsItemMeta.displayName(Component.text(ChatColor.GREEN + "Daily Rewards"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "Login every " + ChatColor.GREEN + "24 hours " + ChatColor.GRAY + "to receive");
        lore.add(ChatColor.GRAY + "a " + ChatColor.GREEN + "Login Token" + ChatColor.GRAY + ", which can be redeemed");
        lore.add(ChatColor.GRAY + "for special rewards from this Shop!");
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View Shop");

        votingRewards.setItemMeta(votingRewardsItemMeta);
        votingRewards.setLore(lore);

        return votingRewards;
    }

    private ItemStack donationPerks(Player player) {
        final ItemStack donationPerks = new ItemStack(Material.SUNFLOWER);
        final ItemMeta donationPerksItemMeta = donationPerks.getItemMeta();

        donationPerksItemMeta.displayName(Component.text(ChatColor.GREEN + "Donation Perks"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "Support our Server to");
        lore.add(ChatColor.GRAY + "receive " + ChatColor.GREEN + "Perks " + ChatColor.GRAY + "& " + ChatColor.GREEN + "Rewards" + ChatColor.GRAY + "!");
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View Perks");

        donationPerks.setItemMeta(donationPerksItemMeta);
        donationPerks.setLore(lore);

        return donationPerks;
    }

    private ItemStack social(Player player) {
        final ItemStack social = new ItemStack(Material.TOTEM_OF_UNDYING);
        final ItemMeta socialItemMeta = social.getItemMeta();

        socialItemMeta.displayName(Component.text(ChatColor.GREEN + "Social"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "Add and join together in parties with");
        lore.add(ChatColor.GRAY + "other " + ChatColor.GREEN + "Adventurers " + ChatColor.GRAY + "you meet!");
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View");

        social.setItemMeta(socialItemMeta);
        social.setLore(lore);

        return social;
    }

    private ItemStack lobby(Player player) {
        final ItemStack lobby = new ItemStack(Material.GREEN_BED);
        final ItemMeta lobbyItemMeta = lobby.getItemMeta();

        lobbyItemMeta.displayName(Component.text(ChatColor.GREEN + "Lobby"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "Return to the previous server you");
        lore.add(ChatColor.GRAY + "were on to select another " + ChatColor.GREEN + "Gamemode" + ChatColor.GRAY + "!");
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Return to Lobby");

        lobby.setItemMeta(lobbyItemMeta);
        lobby.setLore(lore);

        return lobby;
    }

    private ItemStack resourceCollector(Player player) {
        final ItemStack bossdex = new ItemStack(Material.HOPPER);
        final ItemMeta bossdexItemMeta = bossdex.getItemMeta();

        bossdexItemMeta.displayName(Component.text(ChatColor.GREEN + "Resource Collector"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "Automatically deposit items you");
        lore.add(ChatColor.GRAY + "pickup into a virtual inventory,");
        lore.add(ChatColor.GRAY + "so your inventory doesn't get full!");
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View");

        bossdex.setItemMeta(bossdexItemMeta);
        bossdex.setLore(lore);

        return bossdex;
    }

    private String parsePlaceholder(Player player, String string) {
        return PlaceholderAPI.setPlaceholders(player, "%" + string + "%");
    }

}

