package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs;

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
import monzter.adventurescraft.plugin.utilities.GUI.GUIHelperImplStatic;
import monzter.adventurescraft.plugin.utilities.enums.AdventureStatsDisplay;
import monzter.adventurescraft.plugin.utilities.enums.Prefix;
import monzter.adventurescraft.plugin.utilities.enums.PrisonStatsDisplay;
import monzter.adventurescraft.plugin.utilities.general.ConsoleCommand;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class MainMenu extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final SoundManager soundManager;
    private final GUIHelper guiHelper;
    private final ConsoleCommand consoleCommand;

    private final int MAX_QUESTS = 101;

    public MainMenu(AdventuresCraft plugin, SoundManager soundManager, GUIHelper guiHelper, ConsoleCommand consoleCommand) {
        this.plugin = plugin;
        this.soundManager = soundManager;
        this.guiHelper = guiHelper;
        this.consoleCommand = consoleCommand;
    }

    @CommandAlias("Menu|MainMenu|Main")
    public void mainMenu(Player player) {

        ChestGui gui = new ChestGui(6, guiHelper.guiName("Main Menu"));
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        OutlinePane background = new OutlinePane(0, 0, 9, 6, Pane.Priority.LOWEST);
        StaticPane display = new StaticPane(0, 0, 9, 6, Pane.Priority.LOW);


        background.addItem(new GuiItem(guiHelper.background(Material.ORANGE_STAINED_GLASS_PANE)));
        background.setRepeat(true);

        for (Items item : Items.values()) {
            if (item.name().equalsIgnoreCase("HOME")) {
                ArrayList<String> loreArray = new ArrayList<String>();
                for (String lore : item.getGuiItem().getItem().getLore()) {
                    if (lore.equalsIgnoreCase(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Travel") && !player.getWorld().getName().equalsIgnoreCase("Spawn")) {
                        lore = Prefix.PREFIX.getString() + ChatColor.YELLOW + "Left-Click to Travel";
                        loreArray.add(lore);
                        loreArray.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Right-Click to Manage");
                        break;
                    }
                    loreArray.add(lore);
                }
                item.getGuiItem().getItem().setLore(loreArray);
            } else if (item.name().equalsIgnoreCase("PROFILE")) {
                String[] lore = new String[]{
                        ChatColor.WHITE + "Level: " + ChatColor.GRAY + parsePlaceholder(player, "mmocore_level") + ChatColor.DARK_GRAY + " [" + ChatColor.YELLOW + parsePlaceholder(player, "mmocore_experience") + "/" + parsePlaceholder(player, "mmocore_next_level") + "EXP" + ChatColor.DARK_GRAY + "]",
                        "",
                        AdventureStatsDisplay.HP.getName() + ": " + parsePlaceholder(player, "mmocore_health") + "/" + parsePlaceholder(player, "mmocore_max_health"),
                        AdventureStatsDisplay.MANA.getName() + ": " + parsePlaceholder(player, "mmocore_mana") + "/" + parsePlaceholder(player, "mmocore_stat_max_mana"),
                        AdventureStatsDisplay.ARMOR.getName() + ": " + parsePlaceholder(player, "mmocore_stat_defense"),
                        AdventureStatsDisplay.SPEED.getName() + ": " + parsePlaceholder(player, "mmocore_stat_movement_speed"),
                        AdventureStatsDisplay.DAMAGE.getName() + ": " + parsePlaceholder(player, "mmocore_stat_attack_damage"),
                        AdventureStatsDisplay.ATTACK_SPEED.getName() + ": " + parsePlaceholder(player, "mmocore_stat_attack_damage"),
                        AdventureStatsDisplay.CRITICAL_CHANCE.getName() + ": " + parsePlaceholder(player, "mmocore_stat_critical_strike_chance"),
                        AdventureStatsDisplay.CRITICAL_DAMAGE.getName() + ": " + parsePlaceholder(player, "mmocore_stat_critical_strike_power"),
                        "",
                        PrisonStatsDisplay.MONEY_AMOUNT.getName() + ": " + parsePlaceholder(player, "vault_eco_balance_commas"),
                        "",
                        Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View Profile"};
                item.setGuiItem(new GuiItem(guiHelper.itemCreator(player.getUniqueId(), ChatColor.GREEN + "Profile", lore), e -> player.performCommand("Profile")));
            }
            display.addItem(item.getGuiItem(), item.getxPosition(), item.getyPosition());
        }
        gui.addPane(background);
        gui.addPane(display);
        gui.show(player);
    }

    private String parsePlaceholder(Player player, String string) {
        return PlaceholderAPI.setPlaceholders(player, "%" + string + "%");
    }

    enum Items {
        BOSSDEX(new GuiItem(GUIHelperImplStatic.itemCreator(Material.CLOCK, ChatColor.GREEN + "Bossdex",
                new String[]{"",
                        ChatColor.translateAlternateColorCodes('&', "&aTrack&7 and &aanalyze &7all the"),
                        ChatColor.translateAlternateColorCodes('&', "&4Bosses &7around the &aworld&7!"),
                        "",
                        Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View"}, false),
                e -> Bukkit.getPlayer(e.getWhoClicked().getUniqueId()).performCommand("Bossdex")), 4, 0),
        TOWN(new GuiItem(GUIHelperImplStatic.itemCreator("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmUyY2M0MjAxNWU2Njc4ZjhmZDQ5Y2NjMDFmYmY3ODdmMWJhMmMzMmJjZjU1OWEwMTUzMzJmYzVkYjUwIn19fQ==", ChatColor.YELLOW + "Town",
                new String[]{"",
                        Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Travel"}, false),
                e -> Bukkit.getPlayer(e.getWhoClicked().getUniqueId()).performCommand("Spawn")), 3, 1),
        MAP(new GuiItem(GUIHelperImplStatic.itemCreator("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWE3NzlkZWUyYTZiMTk4OTcwYTEwZTcxYjJiNDc0ZjQ0N2RmYmUyZjEyMGZmMjIzN2M0N2ZkMTY3Y2Q0ZjFhNyJ9fX0=", ChatColor.YELLOW + "Map",
                new String[]{"",
                        ChatColor.translateAlternateColorCodes('&', "&7Quickly &atravel &7to &aareas"),
                        ChatColor.translateAlternateColorCodes('&', "&7around the &aworld&7!"),
                        "",
                        Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View"}, false),
                e -> Bukkit.getPlayer(e.getWhoClicked().getUniqueId()).performCommand("Map")), 4, 1),
        HOME(new GuiItem(GUIHelperImplStatic.itemCreator("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTI3MTgwOWJhOTFiNDJmYjQ4NzVhZjRiYTI5OGU1ZTU1ZjQ1ZWQ3MzcyMWJjZWE4NWE0NWRiOTI2Mjg1NzRmIn19fQ==", ChatColor.YELLOW + "Home",
                new String[]{"",
                        ChatColor.translateAlternateColorCodes('&', "&aStore &7your items and unleash your"),
                        ChatColor.translateAlternateColorCodes('&', "&acreativity &7within your &aHome&7!"),
                        "",
                        Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Travel"}, false),
                e -> {
                    if (e.isRightClick() && !e.getWhoClicked().getWorld().getName().equalsIgnoreCase("Spawn"))
                        Bukkit.getPlayer(e.getWhoClicked().getUniqueId()).performCommand("Home Help 1");
                    else
                        Bukkit.getPlayer(e.getWhoClicked().getUniqueId()).performCommand("Home");
                }), 5, 1),

        PROFESSIONS(new GuiItem(GUIHelperImplStatic.itemCreator(Material.DIAMOND_PICKAXE, ChatColor.GREEN + "Professions",
                new String[]{"",
                        ChatColor.translateAlternateColorCodes('&', "&7Take up &aProfessions &7to gain access"),
                        ChatColor.translateAlternateColorCodes('&', "&7to improved &aGear &7& &aEquipment&7! "),
                        "",
                        Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View"}, false),
                e -> Bukkit.getPlayer(e.getWhoClicked().getUniqueId()).performCommand("Professions")), 3, 2),
        PROFILE(null, 4, 2),
        QUESTS(new GuiItem(GUIHelperImplStatic.itemCreator(Material.BOOK, ChatColor.GREEN + "Quests",
                new String[]{"",
                        ChatColor.translateAlternateColorCodes('&', "&7Coqnuer the &aworld &7by helping"),
                        ChatColor.translateAlternateColorCodes('&', "&aTownsfolk &7while earning &arewards&7! "),
                        "",
                        Prefix.PREFIX.getString() + ChatColor.YELLOW + "Left-Click to View",
                        Prefix.PREFIX.getString() + ChatColor.YELLOW + "Right-Click to get a Quest Journal"}, false),
                e -> {
                    if (e.isLeftClick())
                        Bukkit.getPlayer(e.getWhoClicked().getUniqueId()).performCommand("quest");
                    else if (e.isRightClick())
                        Bukkit.getPlayer(e.getWhoClicked().getUniqueId()).performCommand("journal");
                }), 5, 2),
        //    LEADERBOARDS(new GuiItem(GUIHelperImplStatic.itemCreator(Material.LADDER, ChatColor.GREEN + "Leaderboards",
//            new String[]{"",
//                    ChatColor.translateAlternateColorCodes('&', "&7Discord the most dedicated"),
//                    ChatColor.translateAlternateColorCodes('&', "&aAdventurers &7around the &aworld&7!"),
//                    "",
//                    Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View"}, false),
//            e -> Bukkit.getPlayer(e.getWhoClicked().getUniqueId()).sendMessage(ChatColor.RED + "Coming soon!")), 5, 2),

        //    DAILY(new GuiItem(GUIHelperImplStatic.itemCreator(Material.EMERALD, ChatColor.GREEN + "Daily Reward",
//            new String[]{"",
//                    ChatColor.translateAlternateColorCodes('&', "&7Return every &a24 Hours &7to receive"),
//                    ChatColor.translateAlternateColorCodes('&', "&7a &aDaily Coin&7, which can be redeemed"),
//                    ChatColor.translateAlternateColorCodes('&', "&7for &aspecial rewards &7from this shop!"),
//                    "",
//                    Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View"}, false),
//            e -> Bukkit.getPlayer(e.getWhoClicked().getUniqueId()).performCommand("Daily")), 0, 3),
        VOTE(new GuiItem(GUIHelperImplStatic.itemCreator(Material.CLOCK, ChatColor.GREEN + "Voting Rewards",
                new String[]{"",
                        ChatColor.translateAlternateColorCodes('&', "&aVote&7 every &a24 Hours &7to receive"),
                        ChatColor.translateAlternateColorCodes('&', "&aVote Coins&7, which can be redeemed"),
                        ChatColor.translateAlternateColorCodes('&', "&7for &aspecial rewards&7 from this shop!"),
                        "",
                        Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View"}, false),
                e -> Bukkit.getPlayer(e.getWhoClicked().getUniqueId()).performCommand("Vote")), 0, 3),
        KNOWLEDGE(new GuiItem(GUIHelperImplStatic.itemCreator(Material.KNOWLEDGE_BOOK, ChatColor.GREEN + "Knowledge",
                new String[]{"",
                        ChatColor.translateAlternateColorCodes('&', "&7View &acraftable &7items you learned"),
                        ChatColor.translateAlternateColorCodes('&', "&7though your &aadventures &7& &aprofessions&7!"),
                        "",
                        Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View"}, false),
                e -> Bukkit.getPlayer(e.getWhoClicked().getUniqueId()).performCommand("shop")), 2, 3),
        CRAFTING(new GuiItem(GUIHelperImplStatic.itemCreator(Material.CRAFTING_TABLE, ChatColor.GREEN + "Crafting",
                new String[]{"",
                        ChatColor.translateAlternateColorCodes('&', "&7Combine &amaterials &7to create &aitems&7!"),
                        "",
                        Prefix.PREFIX.getString() + ChatColor.YELLOW + "Left-Click to Open Crafting Table",
                        Prefix.PREFIX.getString() + ChatColor.YELLOW + "Right-Click to Open Knowledge Crafting"}, false),
                e -> {
                    if (e.isLeftClick())
                        Bukkit.getPlayer(e.getWhoClicked().getUniqueId()).performCommand("craft");
                    else if (e.isRightClick())
                        Bukkit.getPlayer(e.getWhoClicked().getUniqueId()).performCommand("materials");
                }), 3, 3),
        RESOURCE_COLLECTOR(new GuiItem(GUIHelperImplStatic.itemCreator(Material.CAULDRON, ChatColor.GREEN + "Resource Collector",
                new String[]{"",
                        ChatColor.translateAlternateColorCodes('&', "&7Automatically deposit items you"),
                        ChatColor.translateAlternateColorCodes('&', "&7pickup into a &avirtual inventory&7,"),
                        ChatColor.translateAlternateColorCodes('&', "&7so your inventory doesn't get full!"),
                        "",
                        Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View"}, false),
                e -> Bukkit.getPlayer(e.getWhoClicked().getUniqueId()).performCommand("resourceCollector")), 4, 3),
        BANK(new GuiItem(GUIHelperImplStatic.itemCreator(Material.ENDER_CHEST, ChatColor.GREEN + "Bank",
                new String[]{"",
                        ChatColor.translateAlternateColorCodes('&', "&7Store extra &aItems &7you're"),
                        ChatColor.translateAlternateColorCodes('&', "&7holding without needing to"),
                        ChatColor.translateAlternateColorCodes('&', "&7return back to your &aHome&7!"),
                        "",
                        Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Access"}, false),
                e -> Bukkit.getPlayer(e.getWhoClicked().getUniqueId()).performCommand("Bank Open")), 5, 3),
        ACCESSORY_BAG(new GuiItem(GUIHelperImplStatic.itemCreator("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWJkZjhkNTNiZGI5MzJjMjIzYzYyN2JiYjhjMWUwYzVlMzUxYTYxNmNkODA1NjkyOWM2NmU2ZGNlNDQ0MzNkYiJ9fX0=", ChatColor.GREEN + "Accessory Bag",
                new String[]{"",
                        ChatColor.translateAlternateColorCodes('&', "&7Store your &aaccessories &7while"),
                        ChatColor.translateAlternateColorCodes('&', "&7still gaining their &aeffects&7!"),
                        "",
                        Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View"}, false),
                e -> Bukkit.getPlayer(e.getWhoClicked().getUniqueId()).performCommand("rpginv")), 6, 3),

        DONATION(new GuiItem(GUIHelperImplStatic.itemCreator(Material.SUNFLOWER, ChatColor.GOLD + "Donation Perks",
                new String[]{"",
                        ChatColor.translateAlternateColorCodes('&', "&7Support our &aworld &7to"),
                        ChatColor.translateAlternateColorCodes('&', "&7receive &aperks &7& &arewards &77!"),
                        "",
                        Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View"}, false),
                e -> Bukkit.getPlayer(e.getWhoClicked().getUniqueId()).performCommand("donate")), 3, 4),
        SETTING(new GuiItem(GUIHelperImplStatic.itemCreator(Material.ANVIL, ChatColor.GREEN + "Settings",
                new String[]{"",
                        ChatColor.translateAlternateColorCodes('&', "&7Improve your experience by"),
                        ChatColor.translateAlternateColorCodes('&', "&7adjusting your &asettings&7!"),
                        "",
                        Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View"}, false),
                e -> Bukkit.getPlayer(e.getWhoClicked().getUniqueId()).performCommand("settings")), 4, 4),
        SOCIAL(new GuiItem(GUIHelperImplStatic.itemCreator(Material.TOTEM_OF_UNDYING, ChatColor.GOLD + "Social",
                new String[]{"",
                        ChatColor.translateAlternateColorCodes('&', "&aAdd &7and &aParty up &7with"),
                        ChatColor.translateAlternateColorCodes('&', "&7other &aAdventurers &7you meet!"),
                        "",
                        Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View"}, false),
                e -> Bukkit.getPlayer(e.getWhoClicked().getUniqueId()).performCommand("social")), 5, 4),

        WIKI(new GuiItem(GUIHelperImplStatic.itemCreator("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODNkODc0ZWI4YzRjNjk3YjNmODMyYmQ4NzQ0MjZmZGY2ZDIxYmFlMzM5ZjMxNzExMDgxZmRlNTk4MzgzODZlMSJ9fX0=", ChatColor.GOLD + "Wiki",
                new String[]{"",
                        ChatColor.translateAlternateColorCodes('&', "&7Learn in-depth information"),
                        ChatColor.translateAlternateColorCodes('&', "&7about everything in our &aworld&7!"),
                        "",
                        Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View"}, false),
                e -> Bukkit.getPlayer(e.getWhoClicked().getUniqueId()).performCommand("Wiki")), 4, 5),

        ;
        public GuiItem guiItem;
        public int xPosition;
        public int yPosition;

        Items(GuiItem guiItem, int xPosition, int yPosition) {
            this.guiItem = guiItem;
            this.xPosition = xPosition;
            this.yPosition = yPosition;
        }

        public GuiItem getGuiItem() {
            return guiItem;
        }

        public int getxPosition() {
            return xPosition;
        }

        public int getyPosition() {
            return yPosition;
        }

        public void setGuiItem(GuiItem guiItem) {
            this.guiItem = guiItem;
        }
    }
}
