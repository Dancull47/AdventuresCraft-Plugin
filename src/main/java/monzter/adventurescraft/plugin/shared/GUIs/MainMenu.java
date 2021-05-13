package monzter.adventurescraft.plugin.shared.GUIs;

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
import monzter.adventurescraft.plugin.utilities.enums.StatsDisplay;
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
    private final int MAX_QUESTS = 4;

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

        display.addItem(new GuiItem(yard(player), e -> player.performCommand("yard")), 3, 1);
        display.addItem(new GuiItem(map(player), e -> player.performCommand("map")), 4, 1);
        display.addItem(new GuiItem(cell(player), e -> {
            if (e.isLeftClick())
                player.performCommand("cellManager");
            if (e.isRightClick())
                player.performCommand("home");
        }), 5, 1);

        display.addItem(new GuiItem(backpack(player), e -> player.performCommand("backpack")), 2, 2);
        display.addItem(new GuiItem(prestiges(player), e -> player.performCommand("prestiges")), 3, 2);
        display.addItem(new GuiItem(profile(player), e -> player.performCommand("profile")), 4, 2);
        display.addItem(new GuiItem(quests(player), e -> {
            if (e.isLeftClick())
                player.performCommand("quest");
            if (e.isRightClick())
                player.performCommand("journal");
        }), 5, 2);
        display.addItem(new GuiItem(miningPass(player), e -> player.performCommand("miningpass")), 6, 2);
        display.addItem(new GuiItem(leaderboards(player), e -> player.performCommand("leaderboards")), 7, 2);

        display.addItem(new GuiItem(pets(player), e -> player.performCommand("pets")), 2, 3);
        display.addItem(new GuiItem(crafting(player), e -> player.performCommand("craft")), 3, 3);
        display.addItem(new GuiItem(settings(player), e -> player.performCommand("settings")), 4, 3);
        display.addItem(new GuiItem(bank(player), e -> player.performCommand("bank")), 5, 3);
        display.addItem(new GuiItem(accessoryBag(player), e -> player.performCommand("rpgmenu")), 6, 3);

        display.addItem(new GuiItem(votingRewards(player), e -> player.performCommand("vote")), 3, 4);
        display.addItem(new GuiItem(donationPerks(player), e -> player.performCommand("donate")), 4, 4);
        display.addItem(new GuiItem(social(player), e -> player.performCommand("social")), 5, 4);

        display.addItem(new GuiItem(lobby(player), e -> player.performCommand("lobby")), 8, 5);

        gui.addPane(background);
        gui.addPane(display);
        gui.show(player);
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

    private ItemStack map(Player player) {
        final ItemStack map = new ItemStack(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWE3NzlkZWUyYTZiMTk4OTcwYTEwZTcxYjJiNDc0ZjQ0N2RmYmUyZjEyMGZmMjIzN2M0N2ZkMTY3Y2Q0ZjFhNyJ9fX0="));
        final ItemMeta mapItemMeta = map.getItemMeta();

        mapItemMeta.displayName(Component.text(ChatColor.GREEN + "Map"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "Unlock new areas as you rank up,");
        lore.add(ChatColor.GRAY + "quickly travel to them using the " + ChatColor.GREEN + "Map" + ChatColor.GRAY + "!");
        lore.add("");
        lore.add(Prefix.PREFIX.getPrefix() + ChatColor.YELLOW + "Click to Travel");

        map.setItemMeta(mapItemMeta);
        map.setLore(lore);

        return map;
    }

    private ItemStack cell(Player player) {
        final ItemStack cell = new ItemStack(Material.IRON_BARS);
        final ItemMeta cellItemMeta = cell.getItemMeta();

        cellItemMeta.displayName(Component.text(ChatColor.GREEN + "Cell"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "Store your belongings");
        lore.add(ChatColor.GRAY + "within your " + ChatColor.GREEN + "Cell" + ChatColor.GRAY + ", which can");
        lore.add(ChatColor.GRAY + "be shared with others!");
        lore.add("");
        lore.add(Prefix.PREFIX.getPrefix() + ChatColor.YELLOW + "Left-Click to Travel");
        if (plugin.getConfig().getString("Server").equals("Cell")) {
            lore.add(Prefix.PREFIX.getPrefix() + ChatColor.YELLOW + "Right-Click to Manage");
        }

        cell.setItemMeta(cellItemMeta);
        cell.setLore(lore);

        return cell;
    }

    private ItemStack backpack(Player player) {
        final ItemStack backpack = new ItemStack(Material.CHEST);
        final ItemMeta backpackItemMeta = backpack.getItemMeta();

        backpackItemMeta.displayName(Component.text(ChatColor.GREEN + "Backpack"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "Examine the resources currently");
        lore.add(ChatColor.GRAY + "stored within your backpack");
        lore.add(ChatColor.GRAY + "and information about them!!");
        lore.add("");
        lore.add(Prefix.PREFIX.getPrefix() + ChatColor.YELLOW + "Click to View");

        backpack.setItemMeta(backpackItemMeta);
        backpack.setLore(lore);

        return backpack;
    }

    private ItemStack prestiges(Player player) {
        final ItemStack prestiges = new ItemStack(Material.DIAMOND_PICKAXE);
        final ItemMeta prestigesItemMeta = prestiges.getItemMeta();
        prestigesItemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        prestigesItemMeta.displayName(Component.text(ChatColor.GREEN + "Prestiges"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.WHITE + "Rank: " + parsePlaceholder(player, "rank_value"));
        lore.add("");
        lore.add(ChatColor.GRAY + "Increase your rank by mining");
        lore.add(ChatColor.GRAY + "and selling the loot you find!");
        lore.add("");
        lore.add(Prefix.PREFIX.getPrefix() + ChatColor.YELLOW + "Click to View");

        prestiges.setItemMeta(prestigesItemMeta);
        prestiges.setLore(lore);

        return prestiges;
    }


    private ItemStack profile(Player player) {
        final ItemStack profile = new ItemStack(SkullCreator.itemFromUuid(player.getUniqueId()));
        final ItemMeta profileItemMeta = profile.getItemMeta();

        profileItemMeta.displayName(Component.text(ChatColor.GREEN + "Profile"));

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.WHITE + "Rank: " + parsePlaceholder(player, "rank_value"));
        lore.add("");
        lore.add(StatsDisplay.HP.getName() + ": " + parsePlaceholder(player, "mmocore_health") + "/" + parsePlaceholder(player, "mmocore_max_health"));
        lore.add(StatsDisplay.MANA.getName() + ": " + parsePlaceholder(player, "mmocore_mana") + "/" + parsePlaceholder(player, "mmocore_stat_max_mana"));
        lore.add(StatsDisplay.DAMAGE.getName() + ": " + parsePlaceholder(player, "mmocore_stat_attack_damage"));
        lore.add(StatsDisplay.SPEED.getName() + ": " + parsePlaceholder(player, "mmocore_stat_movement_speed"));
        lore.add("");
        lore.add(StatsDisplay.MINING_SPEED.getName() + ": " + parsePlaceholder(player, "mmoitems_stat_faction_damage_breakingspeed"));
        lore.add(StatsDisplay.MAX_WEIGHT.getName() + ": " + parsePlaceholder(player, "ac_Stat_MaxWeight") + ChatColor.BLUE +
                " (" + ChatColor.DARK_PURPLE + parsePlaceholder(player, "ac_Stat_MaxWeightMultiplier") + ChatColor.BLUE + ")");
        lore.add(StatsDisplay.BLOCK_MULTIPLIER.getName() + ": " + parsePlaceholder(player, "ac_Stat_BlockMultiplier") + "x");
        lore.add(StatsDisplay.SELL_MULTIPLIER.getName() + ": " + parsePlaceholder(player, "ac_Stat_SellMultiplier") + "x");
        lore.add(StatsDisplay.LUCK_MULTIPLIER.getName() + ": " + parsePlaceholder(player, "ac_Stat_LuckMultiplier") + "x");
        lore.add(StatsDisplay.EXPERIENCE_MULTIPLIER.getName() + ": " + parsePlaceholder(player, "ac_Stat_EXPMultiplier") + "x");
        lore.add(StatsDisplay.PET_EXPERIENCE_MULTIPLIER.getName() + ": " + parsePlaceholder(player, "ac_Stat_Pet_EXPMultiplier") + "x");
        lore.add("");
        lore.add(StatsDisplay.MONEY_AMOUNT.getName() + ": " + parsePlaceholder(player, "vault_eco_balance_commas"));
        lore.add(StatsDisplay.EXPERIENCE_AMOUNT.getName() + ": " + parsePlaceholder(player, "ac_Stat_EXPAmount_formatted"));
        lore.add(StatsDisplay.PET_EXPERIENCE_AMOUNT.getName() + ": " + parsePlaceholder(player, "ac_Stat_Pet_EXPAmount_formatted"));
        lore.add(StatsDisplay.MINING_PASS_EXPERIENCE.getName() + ": " + parsePlaceholder(player, "ac_Stat_MiningPassEXPAmount_formatted"));
        lore.add(StatsDisplay.ADVENTURE_COINS.getName() + ": " + parsePlaceholder(player, "ac_Currency_AdventureCoins_formatted"));
        lore.add("");
        lore.add(Prefix.PREFIX.getPrefix() + ChatColor.YELLOW + "Left-Click to View Profile");

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
        lore.add(ChatColor.GRAY + "Explore the Prison and");
        lore.add(ChatColor.GRAY + "help others for rewards!");
        lore.add("");
        lore.add(Prefix.PREFIX.getPrefix() + ChatColor.YELLOW + "Left-Click to open Quest Menu");
        lore.add(Prefix.PREFIX.getPrefix() + ChatColor.YELLOW + "Right-Click to get a Quest Journal");

        quests.setItemMeta(questsItemMeta);
        quests.setLore(lore);

        return quests;
    }

    private ItemStack miningPass(Player player) {
        final ItemStack miningPass = new ItemStack(Material.HOPPER_MINECART);
        final ItemMeta miningPassItemMeta = miningPass.getItemMeta();

        miningPassItemMeta.displayName(Component.text(ChatColor.GREEN + "Mining Pass"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "Complete challenges to level up your");
        lore.add(ChatColor.GREEN + "Mining Pass " + ChatColor.GRAY + "earning rewards in return!");
        lore.add("");
        lore.add(Prefix.PREFIX.getPrefix() + ChatColor.YELLOW + "Click to View");

        miningPass.setItemMeta(miningPassItemMeta);
        miningPass.setLore(lore);

        return miningPass;
    }

    private ItemStack leaderboards(Player player) {
        final ItemStack leaderboards = new ItemStack(Material.LADDER);
        final ItemMeta leaderboardsItemMeta = leaderboards.getItemMeta();

        leaderboardsItemMeta.displayName(Component.text(ChatColor.GREEN + "Leaderboards"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "Check out who the most");
        lore.add(ChatColor.GRAY + "dedicated " + ChatColor.GREEN + "Leaderboards " + ChatColor.GRAY + "are!");
        lore.add("");
        lore.add(Prefix.PREFIX.getPrefix() + ChatColor.YELLOW + "Click to View");

        leaderboards.setItemMeta(leaderboardsItemMeta);
        leaderboards.setLore(lore);

        return leaderboards;
    }

    private ItemStack pets(Player player) {
        final ItemStack pets = new ItemStack(SkullCreator.itemFromBase64("ewogICJ0aW1lc3RhbXAiIDogMTYxNTkwMTYzMzU0OSwKICAicHJvZmlsZUlkIiA6ICJhYTZhNDA5NjU4YTk0MDIwYmU3OGQwN2JkMzVlNTg5MyIsCiAgInByb2ZpbGVOYW1lIiA6ICJiejE0IiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2E5ZWJlNDk2OGIzMjk2NDcwM2RlMmM1NDNiZTI5NmRjZWNkNjkxNmRkZmE3NjM5NWY3N2RmZGJjNjdkMTQzODMiLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfQogIH0KfQ=="));
        final ItemMeta petsItemMeta = pets.getItemMeta();

        petsItemMeta.displayName(Component.text(ChatColor.GREEN + "Pets " + ChatColor.DARK_GRAY + ChatColor.YELLOW + parsePlaceholder(player, "ac_Stat_PetAmount") + ChatColor.GRAY + "/" + ChatColor.YELLOW + parsePlaceholder(player, "ac_Stat_MaxPetAmount")));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "Equip " + ChatColor.GREEN + "Pets" + ChatColor.GRAY + " to gain increased");
        lore.add(ChatColor.GRAY + "stats and keep you company,");
        lore.add(ChatColor.GRAY + "while locked inside the Prison!");
        lore.add("");
        if (Integer.valueOf(parsePlaceholder(player, "ac_Stat_PetAmount")) > 0) {
            lore.add(Prefix.PREFIX.getPrefix() + ChatColor.YELLOW + "Left-Click to View Equipped Pets");
            lore.add(Prefix.PREFIX.getPrefix() + ChatColor.YELLOW + "Right-Click to View Summoning Menu");
        }
        lore.add(Prefix.PREFIX.getPrefix() + ChatColor.YELLOW + "Shift-Right-Click to Visit Pet Shop");

        pets.setItemMeta(petsItemMeta);
        pets.setLore(lore);

        return pets;
    }

    private ItemStack crafting(Player player) {
        final ItemStack crafting = new ItemStack(Material.CRAFTING_TABLE);
        final ItemMeta craftingItemMeta = crafting.getItemMeta();

        craftingItemMeta.displayName(Component.text(ChatColor.GREEN + "Crafting"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "Combine materials to create items!");
        lore.add("");
        lore.add(Prefix.PREFIX.getPrefix() + ChatColor.YELLOW + "Click to Use");

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
        lore.add(Prefix.PREFIX.getPrefix() + ChatColor.YELLOW + "Click to View");

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
        lore.add(ChatColor.GRAY + "return back to your Cell!");
        lore.add("");
        if (player.hasPermission("bank.open.command")) {
            lore.add(Prefix.PREFIX.getPrefix() + ChatColor.YELLOW + "Click to Open");
        } else {
            lore.add(Prefix.PREFIX.getPrefix() + ChatColor.YELLOW + "Click to Travel To Nearest");
        }

        bank.setItemMeta(bankItemMeta);
        bank.setLore(lore);

        return bank;
    }

    private ItemStack accessoryBag(Player player) {
        final ItemStack accessoryBag = new ItemStack(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWJkZjhkNTNiZGI5MzJjMjIzYzYyN2JiYjhjMWUwYzVlMzUxYTYxNmNkODA1NjkyOWM2NmU2ZGNlNDQ0MzNkYiJ9fX0="));
        final ItemMeta accessoryBagItemMeta = accessoryBag.getItemMeta();

        accessoryBagItemMeta.displayName(Component.text(ChatColor.GREEN + "Accessory Bag"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "Store your accessories while,");
        lore.add(ChatColor.GRAY + "still receiving their effects!");
        lore.add("");
        lore.add(Prefix.PREFIX.getPrefix() + ChatColor.YELLOW + "Click to View");

        accessoryBag.setItemMeta(accessoryBagItemMeta);
        accessoryBag.setLore(lore);

        return accessoryBag;
    }

    private ItemStack votingRewards(Player player) {
        final ItemStack votingRewards = new ItemStack(Material.CLOCK);
        final ItemMeta votingRewardsItemMeta = votingRewards.getItemMeta();

        votingRewardsItemMeta.displayName(Component.text(ChatColor.GREEN + "Voting Rewards"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GREEN + "Vote " +  ChatColor.GRAY + "every " + ChatColor.GREEN + "24 hours " +  ChatColor.GRAY + "to receive");
        lore.add(ChatColor.GRAY + "a " + ChatColor.GREEN + "Vote Coin" +  ChatColor.GRAY + ", which can be redeemed");
        lore.add(ChatColor.GRAY + "for special rewards from this Shop!");
        lore.add("");
        lore.add(Prefix.PREFIX.getPrefix() + ChatColor.YELLOW + "Click to View Shop");

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
        lore.add(ChatColor.GRAY + "receive " + ChatColor.GREEN + "Perks " +  ChatColor.GRAY + "& " + ChatColor.GREEN + "Rewards" + ChatColor.GRAY + "!");
        lore.add("");
        lore.add(Prefix.PREFIX.getPrefix() + ChatColor.YELLOW + "Left-Click to View Perks");
        lore.add(Prefix.PREFIX.getPrefix() + ChatColor.YELLOW + "Right-Click to View Store");

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
        lore.add(ChatColor.GRAY + "other " + ChatColor.GREEN + "Prisoners " +  ChatColor.GRAY + "you meet!");
        lore.add("");
        lore.add(Prefix.PREFIX.getPrefix() + ChatColor.YELLOW + "Click to View");

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
        lore.add(ChatColor.GRAY + "were on to select another " + ChatColor.GREEN + "Gamemode" +  ChatColor.GRAY + "!");
        lore.add("");
        lore.add(Prefix.PREFIX.getPrefix() + ChatColor.YELLOW + "Click to Return to Lobby");

        lobby.setItemMeta(lobbyItemMeta);
        lobby.setLore(lore);

        return lobby;
    }


    private String parsePlaceholder(Player player, String string) {
        return PlaceholderAPI.setPlaceholders(player, "%" + string + "%");
    }

}

