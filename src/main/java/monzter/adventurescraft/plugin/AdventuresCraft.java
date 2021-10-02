package monzter.adventurescraft.plugin;

import co.aikar.commands.PaperCommandManager;
import com.comphenix.protocol.ProtocolManager;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.flags.Flag;
import com.sk89q.worldguard.protection.flags.LocationFlag;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.flags.StringFlag;
import com.sk89q.worldguard.protection.flags.registry.FlagConflictException;
import com.sk89q.worldguard.protection.flags.registry.FlagRegistry;
import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicConditionLoadEvent;
import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMechanicLoadEvent;
import io.lumine.xikage.mythicmobs.skills.SkillCondition;
import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
import monzter.adventure.regions.plugin.AdventureRegions;
import monzter.adventurescraft.plugin.network.AdventureGamemode.Adventure.Events.*;
import monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.Commands.DropTableViewer;
import monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.Commands.HomeCommands;
import monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.Events.*;
import monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.mainMenu.Bossdex;
import monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.mainMenu.Professions;
import monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.mainMenu.donation.DonationShopsBuilder;
import monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.mainMenu.professions.ProfessionBuilder;
import monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.mainMenu.quests.achievements.AchievementItemBuilder;
import monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.mainMenu.resourceCollector.ResourceCollector;
import monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.npcs.Jenny;
import monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.npcs.LiftOperator;
import monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.npcs.Weatherman;
import monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.shops.npcs.ShopsBuilder;
import monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.MythicMobRegisters.Conditions.*;
import monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.MythicMobRegisters.Mechanics.ModifierDamageMechanic;
import monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.MythicMobRegisters.Mechanics.VoidMythicMobSkills;
import monzter.adventurescraft.plugin.network.Lobby.Commands.Trails;
import monzter.adventurescraft.plugin.network.Lobby.Events.CancelDrops;
import monzter.adventurescraft.plugin.network.Shared.Commands.Ranks;
import monzter.adventurescraft.plugin.network.Shared.Events.BlockInteractions;
import monzter.adventurescraft.plugin.network.Shared.Events.Join;
import monzter.adventurescraft.plugin.network.Shared.Events.*;
import monzter.adventurescraft.plugin.utilities.GUI.GUIHelper;
import monzter.adventurescraft.plugin.utilities.GUI.GUIHelperImpl;
import monzter.adventurescraft.plugin.utilities.GUI.GUIHelperImplStatic;
import monzter.adventurescraft.plugin.utilities.beton.BetonPointsManager;
import monzter.adventurescraft.plugin.utilities.beton.BetonPointsManagerImpl;
import monzter.adventurescraft.plugin.utilities.beton.BetonTagManager;
import monzter.adventurescraft.plugin.utilities.beton.BetonTagManagerImpl;
import monzter.adventurescraft.plugin.utilities.enchanting.CalculateEnchantments;
import monzter.adventurescraft.plugin.utilities.enchanting.CalculateEnchantmentsImpl;
import monzter.adventurescraft.plugin.utilities.general.*;
import monzter.adventurescraft.plugin.utilities.general.Purchase.PurchaseUtils;
import monzter.adventurescraft.plugin.utilities.general.Purchase.PurchaseUtilsImpl;
import monzter.adventurescraft.plugin.utilities.general.Purchase.ShopBuilder;
import monzter.adventurescraft.plugin.utilities.general.Purchase.ShopBuilderImpl;
import monzter.adventurescraft.plugin.utilities.luckperms.PermissionImplLP;
import monzter.adventurescraft.plugin.utilities.luckperms.PermissionLP;
import monzter.adventurescraft.plugin.utilities.mmoitems.DropTablesDelivery;
import monzter.adventurescraft.plugin.utilities.mmoitems.DropTablesDeliveryImpl;
import monzter.adventurescraft.plugin.utilities.mmoitems.MMOItemsGive;
import monzter.adventurescraft.plugin.utilities.mmoitems.MMOItemsGiveImpl;
import monzter.adventurescraft.plugin.utilities.mythicmobs.MythicMobSpawnImpl;
import monzter.adventurescraft.plugin.utilities.mythicmobs.MythicMobsSpawn;
import monzter.adventurescraft.plugin.utilities.text.NumberFormat;
import monzter.adventurescraft.plugin.utilities.text.NumberFormatImpl;
import monzter.adventurescraft.plugin.utilities.text.ProgressBar;
import monzter.adventurescraft.plugin.utilities.text.ProgressBarImpl;
import monzter.adventurescraft.plugin.utilities.vault.Economy;
import monzter.adventurescraft.plugin.utilities.vault.EconomyImpl;
import monzter.adventurescraft.plugin.utilities.vault.Permission;
import monzter.adventurescraft.plugin.utilities.vault.PermissionImpl;
import net.Indyuce.mmoitems.MMOItems;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.luckperms.api.LuckPermsProvider;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import pl.betoncraft.betonquest.BetonQuest;

import java.util.Random;
import java.util.logging.Level;

public class AdventuresCraft extends JavaPlugin implements Listener {
    public static final String TITLE = ChatColor.RED + "[" + ChatColor.GOLD + "AdventuresCraft" + ChatColor.RED + "] ";
    private static net.milkbowl.vault.permission.Permission perms = null;
    private static net.milkbowl.vault.economy.Economy econ = null;
    public static long restartTime;
    public static boolean restarting = false;
    private SoundManager soundManager;
    private BetonPointsManager betonPointsManager;
    private BetonTagManager betonTagManager;
    private MMOItemsGive mmoItemsGive;
    private FullInventory fullInventory;
    private ConsoleCommand consoleCommand;
    private Permission permission;
    private NumberFormat numberFormat;
    private ChanceCheck chanceCheck;
    private MythicMobsSpawn mythicMobsSpawn;
    private Economy economy;
    private DropTablesDelivery dropTablesDelivery;
    private PermissionLP permissionLP;
    private CalculateEnchantments calculateEnchantments;
    private ProtocolManager protocolManager;
    private PaperCommandManager manager;
    private GUIHelper guiHelper;
    private GUIHelperImplStatic GUIHelperImplStatic;
    private PurchaseUtils purchaseUtils;
    private ItemAdder itemAdder;
    private AreaCheck areaCheck;
    private AchievementItemBuilder achievementGUIBuilder;
    private ProgressBar progressBar;
    private ShopOpener shopOpener;
    //    private Xur xur;
    public static Plugin plugin;
    public final String CONTEXT = this.getConfig().getString("Context").toLowerCase();
    public final String SERVER = this.getConfig().getString("Server");
    private ShopBuilder shopBuilder;

    @Override
    public void onEnable() {
//        if (MySQL.isConnected())
//            getLogger().info("Adventures is connected to MySQL!");
        saveDefaultConfig();
        manager = new PaperCommandManager(this);
        restart();
        setupPermissions();

        initializeDependencies();

        switch (SERVER) {
            case "Lobby":
                lobbyLoad();
                break;
//            case "Prison":
//                prisonLoad();
//                prisonShared();
//                break;
//            case "Cell":
//                cellLoad();
//                prisonShared();
//                break;
            case "Adventure":
                adventureLoad();
                adventureShared();
                break;
            case "Home":
                homeLoad();
                adventureShared();
                break;
            default:
                getLogger().info(getConfig().getString("Server" + " Loaded!"));
        }
        networkShared();

        if (!setupEconomy()) {
            getLogger().severe(String.format("[%s] - Disabled due to no Economy dependency found!", getDescription().getName()));
            this.setEnabled(false);
            return;
        }


        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") == null) {
            getLogger().log(Level.WARNING, "PlaceholderAPI is NOT installed!");
        }
        if (Bukkit.getPluginManager().getPlugin("HolographicDisplays") == null) {
            getLogger().log(Level.WARNING, "HolographicDisplays is NOT installed!");
        }
        getLogger().info(TITLE + ChatColor.GREEN + "has started!");

    }


    @Override
    public void onDisable() {
//        if (!MySQL.isConnected())
//            getLogger().info("Adventures has disconnected to MySQL!");
        getLogger().info(TITLE + ChatColor.GREEN + "has shut down!");
    }

    private void networkShared() {
//        Commands
        manager.registerCommand(new monzter.adventurescraft.plugin.network.Shared.Commands.GeneralCommands(this, consoleCommand, soundManager));
        manager.registerCommand(new monzter.adventurescraft.plugin.network.Shared.Commands.ServerSelect(this, soundManager, guiHelper));
        manager.registerCommand(new monzter.adventurescraft.plugin.network.Shared.Commands.Debug(this));
//        Events
        Bukkit.getServer().getPluginManager().registerEvents(new MapBarrier(this), this);

        Bukkit.getServer().getPluginManager().registerEvents(new monzter.adventurescraft.plugin.network.Shared.Commands.GeneralCommands(this, consoleCommand, soundManager), this);
        Bukkit.getServer().getPluginManager().registerEvents(new BlockPhysics(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new Death(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new Join(this, permissionLP), this);
        Bukkit.getServer().getPluginManager().registerEvents(new AntiDrop(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new Enchant(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new BlockInteractions(this, soundManager, permissionLP, consoleCommand, shopOpener), this);
        Bukkit.getServer().getPluginManager().registerEvents(new MythicMobs(this, fullInventory, betonPointsManager, soundManager, chanceCheck, itemAdder), this);
        Bukkit.getServer().getPluginManager().registerEvents(new InteractQuestBook(this), this);
//        Bukkit.getServer().getPluginManager().registerEvents(new Voting(this, consoleCommand, mmoItemsGive, soundManager, betonPointsManager), this);
        manager.registerCommand(new Ranks(this, soundManager, guiHelper, consoleCommand, betonPointsManager, numberFormat, (MMOItems) Bukkit.getPluginManager().getPlugin("MMOItems"), permissionLP));
    }


    private void adventureLoad() {
//        Commands
//        Events
        Bukkit.getServer().getPluginManager().registerEvents(new monzter.adventurescraft.plugin.network.AdventureGamemode.Adventure.Events.Enchantments(this, calculateEnchantments, itemAdder), this);
        Bukkit.getServer().getPluginManager().registerEvents(new monzter.adventurescraft.plugin.network.AdventureGamemode.Adventure.Events.BlockInteractions(this, soundManager, permissionLP, consoleCommand), this);
        Bukkit.getServer().getPluginManager().registerEvents(new monzter.adventurescraft.plugin.network.AdventureGamemode.Adventure.Events.Void(this, soundManager, permissionLP, consoleCommand, AdventureRegions.getInstance().displayNameFlag, (MMOItems) Bukkit.getPluginManager().getPlugin("MMOItems")), this);
        Bukkit.getServer().getPluginManager().registerEvents(new Statistics(this, betonPointsManager), this);
        Bukkit.getServer().getPluginManager().registerEvents(new monzter.adventurescraft.plugin.network.AdventureGamemode.Adventure.Events.UnlimitedWaterBucket(this, (MMOItems) Bukkit.getPluginManager().getPlugin("MMOItems")), this);
        Bukkit.getServer().getPluginManager().registerEvents(new monzter.adventurescraft.plugin.network.AdventureGamemode.Adventure.Events.Join(this, permissionLP), this);
        Bukkit.getServer().getPluginManager().registerEvents(new MythicMobCaptureQuests(this, soundManager, permissionLP, consoleCommand), this);
        Bukkit.getServer().getPluginManager().registerEvents(new MythicMobDrops(this, chanceCheck, itemAdder, soundManager), this);
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
    }

    private void homeLoad() {
//        Commands
//        Events
        Bukkit.getServer().getPluginManager().registerEvents(new monzter.adventurescraft.plugin.network.AdventureGamemode.Home.Events.Join(this, permissionLP), this);
    }

    private void adventureShared() {
//        Placeholder
        new monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.Events.Placeholder(this, AdventureRegions.getInstance().displayNameFlag).register();
//        Commands
        manager.registerCommand(new HomeCommands(this, consoleCommand, permissionLP, soundManager));
        manager.registerCommand(new monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.Commands.GeneralCommands(this, consoleCommand, permissionLP, soundManager));
        manager.registerCommand(new monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.Commands.AdminCommands(this, mmoItemsGive, permissionLP, betonPointsManager, numberFormat, consoleCommand));
        manager.registerCommand(new monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.Commands.Boss(this, consoleCommand, permissionLP, soundManager, betonTagManager, itemAdder));
        manager.registerCommand(new monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.Commands.Warp(this, permissionLP));
//        Events
        Bukkit.getServer().getPluginManager().registerEvents(new FireDamage(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new ItemSlotLock(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new AccountBound(this, fullInventory, soundManager, betonPointsManager), this);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerInteractLootboxes(this, soundManager, permissionLP, consoleCommand, fullInventory), this);
        Bukkit.getServer().getPluginManager().registerEvents(new Catalysts(this, calculateEnchantments, itemAdder, areaCheck, (MMOItems) Bukkit.getPluginManager().getPlugin("MMOItems"), chanceCheck), this);
        Bukkit.getServer().getPluginManager().registerEvents(new Pickup(this, betonPointsManager), this);
        Bukkit.getServer().getPluginManager().registerEvents(new TutorialMessage(this), this);
//        Main GUIs
        manager.registerCommand(new monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.MainMenu(this, soundManager, guiHelper, consoleCommand));
        manager.registerCommand(new monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.mainMenu.Map(this, guiHelper));
        manager.registerCommand(new monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.mainMenu.map.MineMap(this, guiHelper));
        manager.registerCommand(new monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.mainMenu.map.ForestMap(this, guiHelper));
        manager.registerCommand(new monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.mainMenu.map.FastTravel(this, guiHelper));
        manager.registerCommand(new monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.mainMenu.ProfileMenu(this, soundManager, guiHelper));
        manager.registerCommand(new monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.mainMenu.Settings(this, soundManager, guiHelper, consoleCommand, permissionLP));
        manager.registerCommand(new Professions(this, soundManager, guiHelper, progressBar));
        manager.registerCommand(new ResourceCollector(this, soundManager, guiHelper, progressBar, betonPointsManager, numberFormat, permissionLP, itemAdder, fullInventory));
        manager.registerCommand(new monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.mainMenu.DonationMenu(this, soundManager, guiHelper, consoleCommand, betonPointsManager, numberFormat, (MMOItems) Bukkit.getPluginManager().getPlugin("MMOItems")));
        manager.registerCommand(new monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.mainMenu.Social(this, soundManager, guiHelper, consoleCommand, permissionLP));
        manager.registerCommand(new monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.mainMenu.settings.SafeDrop(this, soundManager, guiHelper, consoleCommand, permissionLP));

        manager.registerCommand(new ProfessionBuilder(this, soundManager, guiHelper, progressBar, numberFormat));

        manager.registerCommand(new monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.mainMenu.quests.QuestAreaMenu(this, soundManager, guiHelper, consoleCommand, betonTagManager));
        manager.registerCommand(new monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.mainMenu.Quests(this, soundManager, guiHelper, consoleCommand, betonTagManager));
        manager.registerCommand(new monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.mainMenu.quests.NPCQuestsDisplay(this, soundManager, guiHelper, consoleCommand, (MMOItems) Bukkit.getPluginManager().getPlugin("MMOItems"), betonTagManager, fullInventory, itemAdder, betonPointsManager, economy));

//          NPC GUIs
        manager.registerCommand(new LiftOperator(this, soundManager, guiHelper, consoleCommand));
        manager.registerCommand(new Jenny(this, soundManager, guiHelper, consoleCommand));
        manager.registerCommand(new Weatherman(this, soundManager, guiHelper, consoleCommand, economy));
//        TOWN HALL DISPLAY GUIs
        manager.registerCommand(new monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.npcs.TownHallDisplay.Farmer(this, soundManager, guiHelper, consoleCommand, (MMOItems) Bukkit.getPluginManager().getPlugin("MMOItems")));
        manager.registerCommand(new monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.npcs.TownHallDisplay.UndeadHunter(this, soundManager, guiHelper, consoleCommand, (MMOItems) Bukkit.getPluginManager().getPlugin("MMOItems")));
        manager.registerCommand(new monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.npcs.TownHallDisplay.Undead(this, soundManager, guiHelper, consoleCommand, (MMOItems) Bukkit.getPluginManager().getPlugin("MMOItems")));
        manager.registerCommand(new monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.npcs.TownHallDisplay.Demon(this, soundManager, guiHelper, consoleCommand, (MMOItems) Bukkit.getPluginManager().getPlugin("MMOItems")));
        manager.registerCommand(new monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.npcs.TownHallDisplay.Bullbo(this, soundManager, guiHelper, consoleCommand, (MMOItems) Bukkit.getPluginManager().getPlugin("MMOItems")));
        manager.registerCommand(new monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.npcs.TownHallDisplay.VoidEnchantress(this, soundManager, guiHelper, consoleCommand, (MMOItems) Bukkit.getPluginManager().getPlugin("MMOItems")));
//        Shop GUIs
        manager.registerCommand(new monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.mainMenu.VoteRewards(this, soundManager, guiHelper, mmoItemsGive, betonPointsManager));
        manager.registerCommand(new monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.shops.Enchanting(this, guiHelper, shopOpener, consoleCommand, soundManager));
        manager.registerCommand(new monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.mainMenu.DailyShop(this, soundManager, guiHelper, consoleCommand, numberFormat, mmoItemsGive, betonPointsManager, economy));
        manager.registerCommand(new ShopsBuilder(this, soundManager, guiHelper, consoleCommand, economy, fullInventory, mmoItemsGive, numberFormat, purchaseUtils, (MMOItems) Bukkit.getPluginManager().getPlugin("MMOItems"), permissionLP, AdventureRegions.getInstance().displayNameFlag, shopBuilder));
        manager.registerCommand(new DonationShopsBuilder(this, soundManager, guiHelper, consoleCommand, economy, fullInventory, mmoItemsGive, numberFormat, purchaseUtils, (MMOItems) Bukkit.getPluginManager().getPlugin("MMOItems"), permissionLP, betonPointsManager));
        manager.registerCommand(new DropTableViewer(this, guiHelper, dropTablesDelivery, (MMOItems) Bukkit.getPluginManager().getPlugin("MMOItems")));
        manager.registerCommand(new monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.Commands.DropTablesGive(this, mmoItemsGive, soundManager, dropTablesDelivery, (MMOItems) Bukkit.getPluginManager().getPlugin("MMOItems"), chanceCheck));
        manager.registerCommand(new Bossdex(this, guiHelper, (MMOItems) Bukkit.getPluginManager().getPlugin("MMOItems"), betonPointsManager, consoleCommand));

    }

//    private void prisonLoad() {
////        Commands
//        manager.registerCommand(new MineTeleport(this));
//        manager.registerCommand(new Sell(this, AdventureRegions.getInstance().sellLocationFlag, economy, numberFormat, soundManager));
////        Events
//        Bukkit.getServer().getPluginManager().registerEvents(new Tutorial(this, mmoItemsGive, permissionLP, areaCheck), this);
//        Bukkit.getServer().getPluginManager().registerEvents(new JoinPrison(this, mmoItemsGive, permissionLP), this);
//        Bukkit.getServer().getPluginManager().registerEvents(new ChestInteract(this, AdventureRegions.getInstance().prisonMineFlag, dropTablesDelivery), this);
//        Bukkit.getServer().getPluginManager().registerEvents(new BlockBreakMining(this, AdventureRegions.getInstance().prisonMineFlag, soundManager, chanceCheck, consoleCommand, mmoItemsGive, betonPointsManager, economy, numberFormat), this);
//        Bukkit.getServer().getPluginManager().registerEvents(new BeachEvent(this, consoleCommand, mythicMobsSpawn), this);
//        Bukkit.getServer().getPluginManager().registerEvents(new Xur(this, soundManager), this);
//        xur = new Xur(this, soundManager);
//        xur.onEnable();
//    }
//
//    private void cellLoad() {
////        GUIs
//        manager.registerCommand(new CellFlagsGUI(this, soundManager, BentoBox.getInstance(), guiHelper));
//        manager.registerCommand(new CellDisplayGUI(this, soundManager, BentoBox.getInstance(), guiHelper));
//        Bukkit.getServer().getPluginManager().registerEvents(new monzter.adventurescraft.plugin.network.PrisonGamemode.cell.events.Join(this, permissionLP, BentoBox.getInstance()), this);
////        Event
//    }
//
//    private void prisonShared() {
//        getLogger().info("Prison / Cell Shared Loaded");
//        prisonTipMessages();
////        Placeholder
//        new Placeholder(this, perms, numberFormat, loadPets(), AdventureRegions.getInstance().displayNameFlag, restartTime, economy, BentoBox.getInstance(), calculateEnchantments).register();
////        Events
//        Bukkit.getServer().getPluginManager().registerEvents(new ProjectileCancelArrowDrop(this), this);
//        Bukkit.getServer().getPluginManager().registerEvents(new InteractPetEgg(this, numberFormat), this);
//        Bukkit.getServer().getPluginManager().registerEvents(new InteractPets(this, loadPetsConfig(), permissionLP, betonPointsManager, soundManager), this);
////        Main GUIs
//        manager.registerCommand(new MainMenu(this, soundManager, guiHelper, betonTagManager));
//        manager.registerCommand(new ProfileMenu(this, soundManager, guiHelper));
//        manager.registerCommand(new Map(this, soundManager, guiHelper, consoleCommand));
//        manager.registerCommand(new RankMap(this, soundManager, guiHelper, consoleCommand));
//        manager.registerCommand(new PrestigeMap(this, soundManager, guiHelper, consoleCommand));
//        manager.registerCommand(new Leaderboards(this, soundManager, guiHelper, consoleCommand));
//        manager.registerCommand(new Pets(this, soundManager, guiHelper, consoleCommand, loadPetsConfig(), mmoItemsGive, permissionLP, betonPointsManager, fullInventory));
//        manager.registerCommand(new DonationMenu(this, soundManager, guiHelper, consoleCommand));
//        manager.registerCommand(new DonationShop(this, soundManager, guiHelper, consoleCommand, numberFormat));
//        manager.registerCommand(new Settings(this, soundManager, guiHelper, consoleCommand, permissionLP));
//        manager.registerCommand(new Social(this, soundManager, guiHelper, consoleCommand, permissionLP));
//        manager.registerCommand(new Quests(this, soundManager, guiHelper, consoleCommand, betonTagManager));
//        manager.registerCommand(new NPCQuestsDisplay(this, soundManager, guiHelper, consoleCommand, (MMOItems) Bukkit.getPluginManager().getPlugin("MMOItems"), betonTagManager, fullInventory, itemAdder, betonPointsManager, economy));
//        manager.registerCommand(new QuestAreaMenu(this, soundManager, guiHelper, consoleCommand, betonTagManager));
//        manager.registerCommand(new SafeDrop(this, soundManager, guiHelper, consoleCommand, permissionLP));
//        manager.registerCommand(new monzter.adventurescraft.plugin.network.PrisonGamemode.shared.GUIs.mainMenu.donation.MiningPass(this, soundManager, guiHelper, consoleCommand, numberFormat, fullInventory, permissionLP, betonPointsManager));
//        manager.registerCommand(new Backpack(this, soundManager, guiHelper, consoleCommand));
//        manager.registerCommand(new DropTablesView(this, guiHelper));
////        Shop GUIs
//        manager.registerCommand(new Armor(this, soundManager, guiHelper, consoleCommand, economy, fullInventory, mmoItemsGive, numberFormat));
//        manager.registerCommand(new Weight(this, soundManager, guiHelper, consoleCommand, economy, fullInventory, mmoItemsGive, numberFormat, betonPointsManager, permissionLP));
//        manager.registerCommand(new Armor(this, soundManager, guiHelper, consoleCommand, economy, fullInventory, mmoItemsGive, numberFormat));
//        manager.registerCommand(new monzter.adventurescraft.plugin.network.PrisonGamemode.shared.GUIs.shops.Hatching(this, soundManager, guiHelper, consoleCommand, economy, fullInventory, mmoItemsGive, numberFormat));
//        manager.registerCommand(new Tools(this, soundManager, guiHelper, consoleCommand, economy, fullInventory, mmoItemsGive, numberFormat));
//        manager.registerCommand(new monzter.adventurescraft.plugin.network.PrisonGamemode.shared.GUIs.shops.Enchanting(this, soundManager, guiHelper, consoleCommand, economy, fullInventory, mmoItemsGive, numberFormat, calculateEnchantments));
//        manager.registerCommand(new VoteRewards(this, soundManager, guiHelper, consoleCommand, mmoItemsGive, betonPointsManager));
////        Quest GUIs
//        manager.registerCommand(new Achivements(this, soundManager, guiHelper, numberFormat, betonPointsManager));
//        manager.registerCommand(new AchievementGUI(this, soundManager, guiHelper, numberFormat, betonPointsManager, permissionLP, consoleCommand, achievementGUIBuilder));
////        Commands
//        manager.registerCommand(new AdminCommands(this, mmoItemsGive, permissionLP, betonPointsManager, numberFormat, economy));
//        manager.registerCommand(new GeneralCommands(this, consoleCommand, soundManager));
//        manager.registerCommand(new Security(this));
//        manager.registerCommand(new Donate(this, mmoItemsGive, soundManager, permission));
//        manager.registerCommand(new Giveaways(this, mmoItemsGive, soundManager, permission));
//        manager.registerCommand(new Hatching(this, soundManager, consoleCommand, dropTablesDelivery, numberFormat));
//        manager.registerCommand(new DropTablesGive(this, mmoItemsGive, soundManager, dropTablesDelivery));
//        manager.registerCommand(new Enchanting(this, numberFormat, soundManager, consoleCommand, (MythicEnchants) Bukkit.getPluginManager().getPlugin("MythicEnchants"), betonPointsManager, calculateEnchantments));
//        manager.registerCommand(new InteractPets(this, loadPetsConfig(), permissionLP, betonPointsManager, soundManager));
//        manager.registerCommand(new MoneyMultiplier(economy, this, mmoItemsGive));
//        manager.registerCommand(new monzter.adventurescraft.plugin.network.PrisonGamemode.shared.commands.Warp(this, permissionLP));
//    }

    private void lobbyLoad() {
//        Commands
        manager.registerCommand(new Trails(this, soundManager, guiHelper));
        manager.registerCommand(new monzter.adventurescraft.plugin.network.Lobby.Commands.DropTablesGive(this, mmoItemsGive, soundManager, dropTablesDelivery, permissionLP));
        manager.registerCommand(new monzter.adventurescraft.plugin.network.Lobby.Commands.DropTablesView(this, guiHelper));
//        Events
        Bukkit.getServer().getPluginManager().registerEvents(new CancelDrops(this), this);
    }

    private void initializeDependencies() {
        RegisteredServiceProvider<net.milkbowl.vault.economy.Economy> rsp = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        numberFormat = new NumberFormatImpl();
        soundManager = new SoundManagerImpl();
        fullInventory = new FullInventoryImpl(soundManager);
        economy = new EconomyImpl(rsp.getProvider(), this, numberFormat, soundManager);
        permissionLP = new PermissionImplLP(LuckPermsProvider.get(), this);
        permission = new PermissionImpl(perms, getLogger());
        consoleCommand = new ConsoleCommandImpl(getServer());
        mythicMobsSpawn = new MythicMobSpawnImpl();
        itemAdder = new ItemAdderImpl();
        areaCheck = new AreaCheckImpl(AdventureRegions.getInstance().displayNameFlag);
        calculateEnchantments = new CalculateEnchantmentsImpl();
        shopOpener = new ShopOpenerImpl(permissionLP);

        final Plugin betonQuest = Bukkit.getPluginManager().getPlugin("BetonQuest");
        if (betonQuest != null) {
            betonPointsManager = new BetonPointsManagerImpl((BetonQuest) betonQuest);
            betonTagManager = new BetonTagManagerImpl((BetonQuest) betonQuest);
        }
        final Plugin mmoItems = Bukkit.getPluginManager().getPlugin("MMOItems");
        if (mmoItems != null)
            mmoItemsGive = new MMOItemsGiveImpl((MMOItems) mmoItems, soundManager);
        guiHelper = new GUIHelperImpl(numberFormat, betonTagManager, betonPointsManager, fullInventory, itemAdder, (MMOItems) Bukkit.getPluginManager().getPlugin("MMOItems"), consoleCommand, economy, soundManager);
        GUIHelperImplStatic = new GUIHelperImplStatic(numberFormat, betonTagManager, betonPointsManager, fullInventory, itemAdder, (MMOItems) Bukkit.getPluginManager().getPlugin("MMOItems"), consoleCommand, economy, soundManager);
        dropTablesDelivery = new DropTablesDeliveryImpl(mmoItemsGive, soundManager);
        purchaseUtils = new PurchaseUtilsImpl(economy, fullInventory, soundManager, numberFormat, (MMOItems) Bukkit.getPluginManager().getPlugin("MMOItems"), betonPointsManager, shopBuilder);
        chanceCheck = new ChanceCheckImpl(mmoItemsGive);
        achievementGUIBuilder = new AchievementItemBuilder(this, soundManager, guiHelper, numberFormat, betonPointsManager, permissionLP, consoleCommand);
        progressBar = new ProgressBarImpl();
        shopBuilder = new ShopBuilderImpl(guiHelper, economy, purchaseUtils, (MMOItems) Bukkit.getPluginManager().getPlugin("MMOItems"), numberFormat);
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<net.milkbowl.vault.economy.Economy> rsp = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    private void setupPermissions() {
        perms = getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class).getProvider();
    }

//    private Set<Pet> loadPets() {
//        File petsFile = new File(getDataFolder(), "pets.yml");
//        if (!petsFile.exists()) {
//            saveResource("pets.yml", false);
//        }
//
//        Set<Pet> petSet = new HashSet<>();
//
//        YamlConfiguration petConfig = YamlConfiguration.loadConfiguration(petsFile);
//        Set<String> petNames = petConfig.getKeys(false);
//        for (String currentPetName : petNames) {
//            if (!petConfig.isConfigurationSection(currentPetName)) {
//                getLogger().log(Level.WARNING, TITLE + ChatColor.RED + "Cannot find Pet Rarity Config section with key: '" + currentPetName + "'.");
//                continue;
//            }
//            ConfigurationSection rarityConfigSection = petConfig.getConfigurationSection(currentPetName);
//            Set<String> rarities = rarityConfigSection.getKeys(false);
//            for (String currentRarity : rarities) {
//                if (!rarityConfigSection.isConfigurationSection(currentRarity)) {
//                    getLogger().log(Level.WARNING, TITLE + ChatColor.RED + "Cannot find Pet Stat Config section with key: '" + currentPetName + "." + currentRarity + "'.");
//                    continue;
//                }
//                Pet.Builder pet = Pet.builder()
//                        .setName(currentPetName)
//                        .setRarity(currentRarity)
//                        .setPermissionPrefix("PET");
//                ConfigurationSection statsConfigSection = rarityConfigSection.getConfigurationSection(currentRarity);
//                Set<String> stats = statsConfigSection.getKeys(false);
//                for (String currentStat : stats) {
//                    if (!statsConfigSection.isDouble(currentStat) && !statsConfigSection.isInt(currentStat) && !statsConfigSection.isLong(currentStat)) {
//                        getLogger().log(Level.WARNING, TITLE + ChatColor.RED + "Cannot find Pet Stat value with key: '" + currentPetName + "." + currentRarity + "." + currentStat + "'.");
//                        continue;
//                    }
//                    try {
//                        Stats statType = Stats.valueOf(currentStat);
//                        double statsValue = statsConfigSection.getDouble(currentStat);
//                        pet.addStat(statType, statsValue);
//                    } catch (IllegalArgumentException e) {
//                        getLogger().log(Level.WARNING, TITLE + ChatColor.RED + "Cannot find Pet Stat type with key: '" + currentPetName + "." + currentRarity + "." + currentStat + "'.");
//                    }
//                }
//                petSet.add(pet.build());
//            }
//        }
//        return petSet;
//    }
//
//    private YamlConfiguration loadPetsConfig() {
//        File petsFile = new File(getDataFolder(), "pets.yml");
//        if (!petsFile.exists()) {
//            saveResource("pets.yml", false);
//        }
//        YamlConfiguration petsConfig = YamlConfiguration.loadConfiguration(petsFile);
//        return petsConfig;
//    }

    /**
     * This will register our custom World-Guard flag "prison-mine".
     * <br>https://worldguard.enginehub.org/en/latest/developer/regions/custom-flags/
     * <br>Flag Types
     * <br>https://worldguard.enginehub.org/en/latest/regions/flags/
     */
    public StateFlag registerStateFlag() {
        FlagRegistry registry = WorldGuard.getInstance().getFlagRegistry();
        try {
            StateFlag flag = new StateFlag("prison-mine", false);
            registry.register(flag);
            return flag;
        } catch (FlagConflictException e) {
            Flag<?> existing = registry.get("prison-mine");
            if (existing instanceof StateFlag) {
                return (StateFlag) existing;
            } else {
                throw new IllegalStateException();
            }
        }
    }

    public StringFlag registerStringFlag() {
        FlagRegistry registry = WorldGuard.getInstance().getFlagRegistry();
        try {
            StringFlag stringFlag = new StringFlag("region-display-name");
            registry.register(stringFlag);
            return stringFlag;
        } catch (FlagConflictException e) {
            Flag<?> existing = registry.get("region-display-name");
            if (existing instanceof StateFlag) {
                return (StringFlag) existing;
            } else {
                throw new IllegalStateException();
            }
        }
    }

    public LocationFlag registerLocationFlag() {
        FlagRegistry registry = WorldGuard.getInstance().getFlagRegistry();
        try {
            LocationFlag LocationFlag = new LocationFlag("sell-location");
            registry.register(LocationFlag);
            return LocationFlag;
        } catch (FlagConflictException e) {
            Flag<?> existing = registry.get("sell-location");
            if (existing instanceof StateFlag) {
                return (LocationFlag) existing;
            } else {
                throw new IllegalStateException();
            }
        }
    }

    private void prisonTipMessages() {
        String prefix = ChatColor.DARK_GRAY + "[" + ChatColor.YELLOW + "♦" + ChatColor.DARK_GRAY + "] " + ChatColor.RESET;
        TextComponent[] tipList = new TextComponent[]{
                Component.text(prefix + "Join our ")
                        .color(NamedTextColor.GREEN)
                        .append(Component.text("Discord", NamedTextColor.BLUE, TextDecoration.BOLD))
                        .hoverEvent(Component.text("Click to join the Discord!", NamedTextColor.GREEN))
                        .clickEvent(ClickEvent.openUrl("https://discord.com/invite/bw4DztR"))
                        .append(Component.text(" for"))
                        .append(Component.text(" Giveaways, Support, and more", NamedTextColor.GOLD))
                        .append(Component.text("!")),
                Component.text(prefix + "You can view all ")
                        .color(NamedTextColor.GREEN)
                        .append(Component.text("Quests & Jobs", NamedTextColor.GOLD))
                        .hoverEvent(Component.text("Click to view Quests & Jobs!", NamedTextColor.GREEN))
                        .clickEvent(ClickEvent.suggestCommand("Quests"))
                        .append(Component.text(" by using"))
                        .append(Component.text(" /Quests", NamedTextColor.GOLD))
                        .append(Component.text("!")),
                Component.text(prefix + "You can view your currently ")
                        .color(NamedTextColor.GREEN)
                        .append(Component.text("Active Quests & Jobs", NamedTextColor.GOLD))
                        .hoverEvent(Component.text("Click to view Active Quests & Jobs!", NamedTextColor.GREEN))
                        .clickEvent(ClickEvent.suggestCommand("/ActiveQuests"))
                        .append(Component.text(" by using"))
                        .append(Component.text(" /ActiveQuests", NamedTextColor.GOLD))
                        .append(Component.text("!")),
                Component.text(prefix + "You can find unclaimed ")
                        .color(NamedTextColor.GREEN)
                        .append(Component.text("Quest & Job Rewards", NamedTextColor.GOLD))
                        .hoverEvent(Component.text("Click to view unclaimed Quest & Job Rewards!", NamedTextColor.GREEN))
                        .clickEvent(ClickEvent.suggestCommand("/UnclaimedQuests"))
                        .append(Component.text(" by using"))
                        .append(Component.text(" /UnclaimedQuests", NamedTextColor.GOLD))
                        .append(Component.text("!")),
                Component.text(prefix + "Many")
                        .color(NamedTextColor.GREEN)
                        .append(Component.text(" Jobs ", NamedTextColor.GOLD))
                        .append(Component.text("are repeatable tasks you can get from folks within the"))
                        .append(Component.text(" Town", NamedTextColor.GOLD))
                        .append(Component.text("!")),
                Component.text(prefix + "Your ")
                        .color(NamedTextColor.GREEN)
                        .append(Component.text("Quest Journal", NamedTextColor.GOLD))
                        .append(Component.text(" is meant to guide you on each quest!")),
                Component.text(prefix + "View your")
                        .color(NamedTextColor.GREEN)
                        .append(Component.text(" Quest Menu ", NamedTextColor.GOLD))
                        .append(Component.text("to track your quest progress by using "))
                        .append(Component.text("/Quests", NamedTextColor.GOLD))
                        .hoverEvent(Component.text("Click to view Quests and Jobs!", NamedTextColor.GREEN))
                        .clickEvent(ClickEvent.suggestCommand("/Quests"))
                        .append(Component.text("!")),
                Component.text(prefix + "You can donate to get epic rewards from our")
                        .color(NamedTextColor.GREEN)
                        .append(Component.text(" Store", NamedTextColor.GOLD))
                        .append(Component.text("!"))
                        .hoverEvent(Component.text("Click to visit the Store!", NamedTextColor.GREEN))
                        .clickEvent(ClickEvent.openUrl("https://store.adventurescraft.net"))
                        .append(Component.text("!")),
                Component.text(prefix + "It's recommended to play on at least")
                        .color(NamedTextColor.GREEN)
                        .append(Component.text(" 1.15.2+ ", NamedTextColor.GOLD))
                        .append(Component.text("as older versions lack certain features and may no longer be supported!")),
                Component.text(prefix + "Use")
                        .color(NamedTextColor.GREEN)
                        .append(Component.text(" /Spawn ", NamedTextColor.GOLD))
                        .hoverEvent(Component.text("Click to return to the Town!", NamedTextColor.GREEN))
                        .clickEvent(ClickEvent.suggestCommand("/Spawn"))
                        .append(Component.text("if you're stuck!")),
                Component.text(prefix + "You can")
                        .color(NamedTextColor.GREEN)
                        .append(Component.text(" Vote ", NamedTextColor.GOLD))
                        .hoverEvent(Component.text("Click to visit Voting Guide!", NamedTextColor.GREEN))
                        .clickEvent(ClickEvent.openUrl("https://www.adventurescraft.net/wiki/site/vote/"))
                        .append(Component.text("for our Server, to receive awesome rewards every day!")),
                Component.text(prefix + "You can add others to your")
                        .color(NamedTextColor.GREEN)
                        .append(Component.text(" Friends List ", NamedTextColor.GOLD))
                        .hoverEvent(Component.text("Click to add friend!", NamedTextColor.GREEN))
                        .clickEvent(ClickEvent.suggestCommand("/friend add "))
                        .append(Component.text("by using"))
                        .append(Component.text(" /friend add <Name>", NamedTextColor.GOLD))
                        .append(Component.text("!")),
                Component.text(prefix + "You can invite others to your")
                        .color(NamedTextColor.GREEN)
                        .append(Component.text(" Party ", NamedTextColor.GOLD))
                        .hoverEvent(Component.text("Click to invite Player!", NamedTextColor.GREEN))
                        .clickEvent(ClickEvent.suggestCommand("/party invite "))
                        .append(Component.text("by using"))
                        .append(Component.text(" /party invite <Name>", NamedTextColor.GOLD))
                        .append(Component.text("!")),
                Component.text(prefix + "Use")
                        .color(NamedTextColor.GREEN)
                        .append(Component.text(" /Home ", NamedTextColor.GOLD))
                        .hoverEvent(Component.text("Click to return Home!", NamedTextColor.GREEN))
                        .clickEvent(ClickEvent.suggestCommand("/home"))
                        .append(Component.text("to return back to your private house!")),
        };
        getServer().getScheduler().scheduleSyncRepeatingTask(this, () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.hasPermission("tips")) {
                    int lastTips = new Random().nextInt(tipList.length);
                    player.sendMessage(tipList[lastTips]);
                }
            }
        }, 0L, 20 * 90);

    }

    @EventHandler
    public void onMythicMechanicLoad(MythicMechanicLoadEvent event) {
        switch (event.getMechanicName().toUpperCase()) {
            case "BLINDER":
                SkillMechanic blinder = new VoidMythicMobSkills(event.getConfig(), (MMOItems) Bukkit.getPluginManager().getPlugin("MMOItems"));
                event.register(blinder);
                this.getLogger().info(ChatColor.YELLOW + "BLINDER registered!");
                break;
            case "DISMOUNT":
                SkillMechanic dismount = new Mount(event.getConfig());
                event.register(dismount);
                this.getLogger().info(ChatColor.YELLOW + "DISMOUNT registered!");
                break;
            case "CUSTOMDAMAGE":
                event.register(new ModifierDamageMechanic(event.getMechanicName(), event.getConfig()));
                this.getLogger().info(ChatColor.YELLOW + "CUSTOMDAMAGE registered!");
                break;
        }
    }

    @EventHandler
    public void onMythicConditionLoad(MythicConditionLoadEvent event) {
        switch (event.getConditionName().toUpperCase()) {
            case "LEASH":
                SkillCondition leash = new LeashCondition(event.getConfig());
                event.register(leash);
                this.getLogger().info(ChatColor.GREEN + "LEASH registered!");
                break;
            case "SKILLCAST":
                event.register(new SkillCastCondition(event.getConfig()));
                this.getLogger().info(ChatColor.GREEN + "SKILLCAST registered!");
                break;
//            case "MANA":
//                SkillCondition manaCondition = new ManaCondition(event.getConfig());
//                event.register(manaCondition);
//                this.getLogger().info(ChatColor.GREEN + "MANA registered!");
//                break;
//            case "COOLDOWN":
//                SkillCondition cooldownCondition = new CooldownCondition(event.getConfig());
//                event.register(cooldownCondition);
//                this.getLogger().info(ChatColor.GREEN + "COOLDOWN registered!");
//                break;
        }
    }

    public void restart() {
        restartTime = System.currentTimeMillis() + 21600000;
        Bukkit.getScheduler().runTaskLater(this, () -> {
            broadcastRestart("minutes", 10);
            restarting = true;
        }, 20 * 21000);
        Bukkit.getScheduler().runTaskLater(this, () -> broadcastRestart("minutes", 5), 20 * 21300);
        Bukkit.getScheduler().runTaskLater(this, () -> broadcastRestart("minutes", 4), 20 * 21360);
        Bukkit.getScheduler().runTaskLater(this, () -> broadcastRestart("minutes", 3), 20 * 21420);
        Bukkit.getScheduler().runTaskLater(this, () -> broadcastRestart("minutes", 2), 20 * 21480);
        Bukkit.getScheduler().runTaskLater(this, () -> broadcastRestart("minutes", 1), 20 * 21540);

        Bukkit.getScheduler().runTaskLater(this, () -> broadcastRestart("seconds", 30), 20 * 21570);
        Bukkit.getScheduler().runTaskLater(this, () -> broadcastRestart("seconds", 15), 20 * 21585);
        Bukkit.getScheduler().runTaskLater(this, () -> broadcastRestart("seconds", 10), 20 * 21590);
        Bukkit.getScheduler().runTaskLater(this, () -> broadcastRestart("seconds", 5), 20 * 21595);
        Bukkit.getScheduler().runTaskLater(this, () -> broadcastRestart("seconds", 4), 20 * 21596);
        Bukkit.getScheduler().runTaskLater(this, () -> broadcastRestart("seconds", 3), 20 * 21597);
        Bukkit.getScheduler().runTaskLater(this, () -> broadcastRestart("seconds", 2), 20 * 21598);
        Bukkit.getScheduler().runTaskLater(this, () -> {
            broadcastRestart("seconds", 1);
            consoleCommand.consoleCommand("mm mobs killall");
            consoleCommand.consoleCommand("killall -named");
        }, 20 * 21599);
        Bukkit.getScheduler().runTaskLater(this, () -> Bukkit.getServer().shutdown(), 20 * 21600);
    }

    public void broadcastRestart(String timeUnit, int time) {
        Bukkit.getServer().broadcast(Component.text("---------------------------------", NamedTextColor.RED));
        Bukkit.getServer().broadcast(Component.text("Server will be " + "restarting " + "in " + time + " " + timeUnit + "!", NamedTextColor.YELLOW));
        Bukkit.getServer().broadcast(Component.text("---------------------------------", NamedTextColor.RED));
    }
}