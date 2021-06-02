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
import io.lumine.mythicenchants.MythicEnchants;
import monzter.adventurescraft.plugin.network.PrisonGamemode.cell.commands.CellDisplayGUI;
import monzter.adventurescraft.plugin.network.PrisonGamemode.cell.commands.CellFlagsGUI;
import monzter.adventurescraft.plugin.network.PrisonGamemode.cell.commands.Warp;
import monzter.adventurescraft.plugin.network.PrisonGamemode.cell.events.JoinCell;
import monzter.adventurescraft.plugin.network.PrisonGamemode.prison.events.Xur;
import monzter.adventurescraft.plugin.network.PrisonGamemode.shared.GUIs.MainMenu;
import monzter.adventurescraft.plugin.network.PrisonGamemode.shared.GUIs.mainMenu.ProfileMenu;
import monzter.adventurescraft.plugin.network.PrisonGamemode.shared.GUIs.mainMenu.*;
import monzter.adventurescraft.plugin.network.PrisonGamemode.shared.GUIs.mainMenu.donation.DonationShop;
import monzter.adventurescraft.plugin.network.PrisonGamemode.shared.GUIs.mainMenu.jobs.Jobs;
import monzter.adventurescraft.plugin.network.PrisonGamemode.shared.GUIs.mainMenu.map.prestigeMap.PrestigeMap;
import monzter.adventurescraft.plugin.network.PrisonGamemode.shared.GUIs.mainMenu.map.rankMap.RankMap;
import monzter.adventurescraft.plugin.network.PrisonGamemode.shared.GUIs.mainMenu.quests.Achivements;
import monzter.adventurescraft.plugin.network.PrisonGamemode.shared.GUIs.mainMenu.quests.achievements.AchievementItemBuilder;
import monzter.adventurescraft.plugin.network.PrisonGamemode.shared.GUIs.mainMenu.quests.achievements.AchievementGUI;
import monzter.adventurescraft.plugin.network.PrisonGamemode.shared.GUIs.mainMenu.quests.jobs.YardJobs;
import monzter.adventurescraft.plugin.network.PrisonGamemode.shared.GUIs.mainMenu.quests.jobs.yardJobs.Dan;
import monzter.adventurescraft.plugin.network.PrisonGamemode.shared.GUIs.mainMenu.quests.jobs.yardJobs.Lester;
import monzter.adventurescraft.plugin.network.PrisonGamemode.shared.GUIs.mainMenu.settings.SafeDrop;
import monzter.adventurescraft.plugin.network.PrisonGamemode.shared.GUIs.quests.Yard;
import monzter.adventurescraft.plugin.network.PrisonGamemode.shared.GUIs.quests.yard.Enchanter;
import monzter.adventurescraft.plugin.network.PrisonGamemode.shared.GUIs.quests.yard.Finubar;
import monzter.adventurescraft.plugin.network.PrisonGamemode.shared.GUIs.quests.yard.Joy;
import monzter.adventurescraft.plugin.network.PrisonGamemode.shared.GUIs.shops.Armor;
import monzter.adventurescraft.plugin.network.PrisonGamemode.shared.GUIs.shops.Tools;
import monzter.adventurescraft.plugin.network.PrisonGamemode.shared.GUIs.shops.npcs.Mercenary;
import monzter.adventurescraft.plugin.network.PrisonGamemode.shared.GUIs.shops.Weight;
import monzter.adventurescraft.plugin.network.PrisonGamemode.shared.GUIs.shops.npcs.PurchaseUtils;
import monzter.adventurescraft.plugin.network.PrisonGamemode.shared.GUIs.shops.npcs.PurchaseUtilsImpl;
import monzter.adventurescraft.plugin.network.PrisonGamemode.shared.commands.*;
import monzter.adventurescraft.plugin.network.PrisonGamemode.prison.commands.Prison.Hatching;
import monzter.adventurescraft.plugin.network.PrisonGamemode.prison.commands.Prison.MineTeleport;
import monzter.adventurescraft.plugin.network.PrisonGamemode.shared.events.*;
import monzter.adventurescraft.plugin.network.PrisonGamemode.shared.events.extras.Pet;
import monzter.adventurescraft.plugin.network.PrisonGamemode.shared.events.extras.Stats;
import monzter.adventurescraft.plugin.network.PrisonGamemode.prison.events.JoinPrison;
import monzter.adventurescraft.plugin.network.PrisonGamemode.prison.events.Tutorial;
import monzter.adventurescraft.plugin.network.PrisonGamemode.prison.commands.Sell;
import monzter.adventurescraft.plugin.network.PrisonGamemode.prison.commands.Warps;
import monzter.adventurescraft.plugin.network.PrisonGamemode.prison.events.mining.BeachEvent;
import monzter.adventurescraft.plugin.network.PrisonGamemode.prison.events.mining.BlockBreakMining;
import monzter.adventurescraft.plugin.network.PrisonGamemode.prison.events.mining.ChestInteract;
import monzter.adventurescraft.plugin.network.PrisonGamemode.prison.utilities.BlockPhysics;
import monzter.adventurescraft.plugin.network.PrisonGamemode.shared.events.utilities.Join_LeaveMessage;
import monzter.adventurescraft.plugin.network.PrisonGamemode.prison.utilities.ProjectileCancelArrowDrop;
import monzter.adventurescraft.plugin.network.PrisonGamemode.prison.utilities.mapBarrier;
import monzter.adventurescraft.plugin.mySQL.MySQL;
import monzter.adventurescraft.plugin.mySQL.SQLGetter;
import monzter.adventurescraft.plugin.network.Shared.Events.AntiDrop;
import monzter.adventurescraft.plugin.network.Shared.Events.BlockInteractions;
import monzter.adventurescraft.plugin.network.Shared.Events.Join;
import monzter.adventurescraft.plugin.utilities.GUI.GUIHelper;
import monzter.adventurescraft.plugin.utilities.GUI.GUIHelperImpl;
import monzter.adventurescraft.plugin.utilities.beton.*;
import monzter.adventurescraft.plugin.utilities.enchanting.CalculateEnchantments;
import monzter.adventurescraft.plugin.utilities.enchanting.CalculateEnchantmentsImpl;
import monzter.adventurescraft.plugin.utilities.general.*;
import monzter.adventurescraft.plugin.utilities.luckperms.PermissionImplLP;
import monzter.adventurescraft.plugin.utilities.luckperms.PermissionLP;
import monzter.adventurescraft.plugin.utilities.mmoitems.*;
import monzter.adventurescraft.plugin.utilities.mythicmobs.MythicMobSpawnImpl;
import monzter.adventurescraft.plugin.utilities.mythicmobs.MythicMobsSpawn;
import monzter.adventurescraft.plugin.utilities.text.NumberFormat;
import monzter.adventurescraft.plugin.utilities.text.NumberFormatImpl;
import monzter.adventurescraft.plugin.utilities.vault.Economy;
import monzter.adventurescraft.plugin.utilities.vault.EconomyImpl;
import monzter.adventurescraft.plugin.utilities.vault.Permission;
import monzter.adventurescraft.plugin.utilities.vault.PermissionImpl;
import net.Indyuce.mmoitems.MMOItems;
import net.Indyuce.mmoitems.comp.mythicenchants.MythicEnchantsSupport;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.luckperms.api.LuckPermsProvider;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import pl.betoncraft.betonquest.BetonQuest;
import world.bentobox.bentobox.BentoBox;

import java.io.File;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;

public class AdventuresCraft extends JavaPlugin implements Listener {
    public static final String TITLE = ChatColor.RED + "[" + ChatColor.GOLD + "AdventuresCraft" + ChatColor.RED + "] ";
    public MySQL SQL;
    public SQLGetter data;
    private static net.milkbowl.vault.permission.Permission perms = null;
    private static net.milkbowl.vault.economy.Economy econ = null;
    private StateFlag prisonMineFlag;
    private StringFlag displayNameFlag;
    private LocationFlag sellLocationFlag;
    private long restartTime;
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
    private PurchaseUtils purchaseUtils;
    private AchievementItemBuilder achievementGUIBuilder;
    private MythicEnchantsSupport mythicEnchantsSupport;
    private Xur xur;
    public final String CONTEXT = this.getConfig().getString("Context").toLowerCase();

    @Override
    public void onLoad() {
        if (getConfig().getString("Server").equals("Prison")) {
            try {
                prisonMineFlag = registerStateFlag();
                displayNameFlag = registerStringFlag();
                sellLocationFlag = registerLocationFlag();
            } catch (IllegalStateException e) {
                getLogger().log(Level.SEVERE, TITLE + ChatColor.RED + "Failed to register Region Flag!" + "\n");
                this.setEnabled(false);
            }
        }
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();
        manager = new PaperCommandManager(this);
//        restartTime = System.currentTimeMillis() + 21600000;
        setupPermissions();

        initializeDependencies();

        switch (getConfig().getString("Server")) {
            case "Prison":
                prisonLoad();
                prisonShared();
                break;
            case "Cell":
                cellLoad();
                prisonShared();
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
        getLogger().info(TITLE + ChatColor.GREEN + "has shut down!");
//        Bukkit.getServer().shutdown();
//        SQL.disconnect();
    }

    private void prisonLoad() {
//        Commands
        manager.registerCommand(new MineTeleport(this));
        manager.registerCommand(new Sell(this, sellLocationFlag, economy, numberFormat, soundManager));
        getCommand("Warp").setExecutor(new Warps(this, loadWarps()));
        getCommand("Warp").setTabCompleter(new Warps(this, loadWarps()));
//        Events
        Bukkit.getServer().getPluginManager().registerEvents(new BlockPhysics(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new Tutorial(this, mmoItemsGive, permissionLP), this);
        Bukkit.getServer().getPluginManager().registerEvents(new JoinPrison(this, mmoItemsGive, permissionLP, loadWarps()), this);
        Bukkit.getServer().getPluginManager().registerEvents(new ChestInteract(this, prisonMineFlag, dropTablesDelivery), this);
        Bukkit.getServer().getPluginManager().registerEvents(new BlockBreakMining(this, prisonMineFlag, soundManager, chanceCheck, consoleCommand, mmoItemsGive, betonPointsManager, economy, numberFormat), this);
        Bukkit.getServer().getPluginManager().registerEvents(new mapBarrier(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new BeachEvent(this, consoleCommand, mythicMobsSpawn), this);
        Bukkit.getServer().getPluginManager().registerEvents(new Xur(this, soundManager), this);
        xur = new Xur(this, soundManager);
        xur.onEnable();
    }

    private void cellLoad() {
//        Commands
        manager.registerCommand(new Warp(this, mmoItemsGive, soundManager, permissionLP, loadWarps()));
//        GUIs
        manager.registerCommand(new CellFlagsGUI(this, soundManager, BentoBox.getInstance()));
        manager.registerCommand(new CellDisplayGUI(this, soundManager, BentoBox.getInstance(), guiHelper));
//        Events
        Bukkit.getServer().getPluginManager().registerEvents(new JoinCell(this, mmoItemsGive, permissionLP, BentoBox.getInstance()), this);
    }

    private void networkShared() {
//        Commands
        manager.registerCommand(new monzter.adventurescraft.plugin.network.Shared.Commands.GeneralCommands(this, consoleCommand, soundManager));
        manager.registerCommand(new monzter.adventurescraft.plugin.network.Shared.Commands.Social(this, consoleCommand, soundManager));
//        Events
        Bukkit.getServer().getPluginManager().registerEvents(new Join(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new AntiDrop(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new BlockInteractions(this, soundManager, permissionLP), this);
    }

    private void prisonShared() {
        getLogger().info("Prison / Cell Shared Loaded");
        prisonTipMessages();
//        Placeholder
        new Placeholder(this, perms, numberFormat, loadPets(), displayNameFlag, restartTime, economy, calculateEnchantments).register();
//        Events
        Bukkit.getServer().getPluginManager().registerEvents(new JoinShared(this, mmoItemsGive, permissionLP), this);
        Bukkit.getServer().getPluginManager().registerEvents(new Join_LeaveMessage(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new Join_LeaveMessage(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new ProjectileCancelArrowDrop(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new MythicMobs(this, fullInventory), this);
        Bukkit.getServer().getPluginManager().registerEvents(new InteractPetEgg(this, numberFormat), this);
        Bukkit.getServer().getPluginManager().registerEvents(new InteractPets(this, loadPetsConfig(), permissionLP, betonPointsManager, soundManager), this);
        Bukkit.getServer().getPluginManager().registerEvents(new InteractQuestBook(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new Voting(this, consoleCommand, mmoItemsGive, soundManager, betonPointsManager), this);
//        Main GUIs
        manager.registerCommand(new MainMenu(this, soundManager, guiHelper));
        manager.registerCommand(new ProfileMenu(this, soundManager, guiHelper));
        manager.registerCommand(new Map(this, soundManager, guiHelper, consoleCommand));
        manager.registerCommand(new RankMap(this, soundManager, guiHelper, consoleCommand));
        manager.registerCommand(new PrestigeMap(this, soundManager, guiHelper, consoleCommand));
        manager.registerCommand(new Leaderboards(this, soundManager, guiHelper, consoleCommand));
        manager.registerCommand(new VoteRewards(this, soundManager, guiHelper, consoleCommand));
        manager.registerCommand(new Pets(this, soundManager, guiHelper, consoleCommand, loadPetsConfig(), mmoItemsGive, permissionLP, betonPointsManager, fullInventory));
        manager.registerCommand(new DonationMenu(this, soundManager, guiHelper, consoleCommand));
        manager.registerCommand(new DonationShop(this, soundManager, guiHelper, consoleCommand, numberFormat));
        manager.registerCommand(new Settings(this, soundManager, guiHelper, consoleCommand, permissionLP));
        manager.registerCommand(new Social(this, soundManager, guiHelper, consoleCommand, permissionLP));
        manager.registerCommand(new Quests(this, soundManager, guiHelper, consoleCommand));
        manager.registerCommand(new SafeDrop(this, soundManager, guiHelper, consoleCommand, permissionLP));
        manager.registerCommand(new monzter.adventurescraft.plugin.network.PrisonGamemode.shared.GUIs.mainMenu.donation.MiningPass(this, soundManager, guiHelper, consoleCommand, numberFormat, fullInventory, permissionLP, betonPointsManager));
        manager.registerCommand(new Backpack(this, soundManager, guiHelper, consoleCommand));
        manager.registerCommand(new DropTablesView(this, guiHelper));
//        Shop GUIs
        manager.registerCommand(new Armor(this, soundManager, guiHelper, consoleCommand, economy, fullInventory, mmoItemsGive, numberFormat));
        manager.registerCommand(new Weight(this, soundManager, guiHelper, consoleCommand, economy, fullInventory, mmoItemsGive, numberFormat, betonPointsManager, permissionLP));
        manager.registerCommand(new Mercenary(this, soundManager, guiHelper, consoleCommand, economy, fullInventory, mmoItemsGive, numberFormat, purchaseUtils));
        manager.registerCommand(new Armor(this, soundManager, guiHelper, consoleCommand, economy, fullInventory, mmoItemsGive, numberFormat));
        manager.registerCommand(new monzter.adventurescraft.plugin.network.PrisonGamemode.shared.GUIs.shops.Hatching(this, soundManager, guiHelper, consoleCommand, economy, fullInventory, mmoItemsGive, numberFormat));
        manager.registerCommand(new Tools(this, soundManager, guiHelper, consoleCommand, economy, fullInventory, mmoItemsGive, numberFormat));
        manager.registerCommand(new monzter.adventurescraft.plugin.network.PrisonGamemode.shared.GUIs.shops.Enchanting(this, soundManager, guiHelper, consoleCommand, economy, fullInventory, mmoItemsGive, numberFormat, calculateEnchantments));
        manager.registerCommand(new Enchanter(this, guiHelper, betonTagManager));
//        Quest GUIs
        manager.registerCommand(new Lester(this, guiHelper, betonTagManager, betonPointsManager, numberFormat));
        manager.registerCommand(new Dan(this, guiHelper, betonTagManager, betonPointsManager, numberFormat));
        manager.registerCommand(new Joy(this, guiHelper, betonTagManager));
        manager.registerCommand(new Finubar(this, guiHelper, betonTagManager));
        manager.registerCommand(new Jobs(this, soundManager, guiHelper, consoleCommand));
        manager.registerCommand(new Achivements(this, soundManager, guiHelper, numberFormat, betonPointsManager));
        manager.registerCommand(new AchievementGUI(this, soundManager, guiHelper, numberFormat, betonPointsManager, permissionLP, consoleCommand, achievementGUIBuilder));
        manager.registerCommand(new Yard(this, guiHelper));
        manager.registerCommand(new YardJobs(this, guiHelper, consoleCommand));
//        Commands
        manager.registerCommand(new AdminCommands(this, mmoItemsGive, permissionLP, betonPointsManager, numberFormat));
        manager.registerCommand(new GeneralCommands(this, consoleCommand, soundManager));
        manager.registerCommand(new Security(this));
        manager.registerCommand(new Donate(this, mmoItemsGive, soundManager, permission));
        manager.registerCommand(new Giveaways(this, mmoItemsGive, soundManager, permission));
        manager.registerCommand(new Hatching(this, soundManager, consoleCommand, dropTablesDelivery, numberFormat));
        manager.registerCommand(new DropTablesGive(this, mmoItemsGive, soundManager, dropTablesDelivery));
        manager.registerCommand(new Voting(this, consoleCommand, mmoItemsGive, soundManager, betonPointsManager));
        manager.registerCommand(new Enchanting(this, numberFormat, soundManager, consoleCommand, (MythicEnchants) Bukkit.getPluginManager().getPlugin("MythicEnchants"), betonPointsManager, calculateEnchantments));
        manager.registerCommand(new InteractPets(this, loadPetsConfig(), permissionLP, betonPointsManager, soundManager));
        manager.registerCommand(new MoneyMultiplier(economy, this, mmoItemsGive));
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
        guiHelper = new GUIHelperImpl(numberFormat);
        purchaseUtils = new PurchaseUtilsImpl(economy, fullInventory, soundManager, numberFormat);
        calculateEnchantments = new CalculateEnchantmentsImpl();

        final Plugin betonQuest = Bukkit.getPluginManager().getPlugin("BetonQuest");
        if (betonQuest != null) {
            betonPointsManager = new BetonPointsManagerImpl((BetonQuest) betonQuest);
            betonTagManager = new BetonTagManagerImpl((BetonQuest) betonQuest);
        }
        final Plugin mmoItems = Bukkit.getPluginManager().getPlugin("MMOItems");
        if (mmoItems == null) {
            getLogger().log(Level.WARNING, "MMOItems not found!");
            mmoItemsGive = new MMOItemsGiveNull();
        } else {
            mmoItemsGive = new MMOItemsGiveImpl((MMOItems) mmoItems, soundManager);
        }
        dropTablesDelivery = new DropTablesDeliveryImpl(mmoItemsGive, soundManager);
        chanceCheck = new ChanceCheckImpl(mmoItemsGive);
        achievementGUIBuilder = new AchievementItemBuilder(this, soundManager, guiHelper, numberFormat, betonPointsManager, permissionLP, consoleCommand);
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

    private Set<Pet> loadPets() {
        File petsFile = new File(getDataFolder(), "pets.yml");
        if (!petsFile.exists()) {
            saveResource("pets.yml", false);
        }

        Set<Pet> petSet = new HashSet<>();

        YamlConfiguration petConfig = YamlConfiguration.loadConfiguration(petsFile);
        Set<String> petNames = petConfig.getKeys(false);
        for (String currentPetName : petNames) {
            if (!petConfig.isConfigurationSection(currentPetName)) {
                getLogger().log(Level.WARNING, TITLE + ChatColor.RED + "Cannot find Pet Rarity Config section with key: '" + currentPetName + "'.");
                continue;
            }
            ConfigurationSection rarityConfigSection = petConfig.getConfigurationSection(currentPetName);
            Set<String> rarities = rarityConfigSection.getKeys(false);
            for (String currentRarity : rarities) {
                if (!rarityConfigSection.isConfigurationSection(currentRarity)) {
                    getLogger().log(Level.WARNING, TITLE + ChatColor.RED + "Cannot find Pet Stat Config section with key: '" + currentPetName + "." + currentRarity + "'.");
                    continue;
                }
                Pet.Builder pet = Pet.builder()
                        .setName(currentPetName)
                        .setRarity(currentRarity)
                        .setPermissionPrefix("PET");
                ConfigurationSection statsConfigSection = rarityConfigSection.getConfigurationSection(currentRarity);
                Set<String> stats = statsConfigSection.getKeys(false);
                for (String currentStat : stats) {
                    if (!statsConfigSection.isDouble(currentStat) && !statsConfigSection.isInt(currentStat) && !statsConfigSection.isLong(currentStat)) {
                        getLogger().log(Level.WARNING, TITLE + ChatColor.RED + "Cannot find Pet Stat value with key: '" + currentPetName + "." + currentRarity + "." + currentStat + "'.");
                        continue;
                    }
                    try {
                        Stats statType = Stats.valueOf(currentStat);
                        double statsValue = statsConfigSection.getDouble(currentStat);
                        pet.addStat(statType, statsValue);
                    } catch (IllegalArgumentException e) {
                        getLogger().log(Level.WARNING, TITLE + ChatColor.RED + "Cannot find Pet Stat type with key: '" + currentPetName + "." + currentRarity + "." + currentStat + "'.");
                    }
                }
                petSet.add(pet.build());
            }
        }
        return petSet;
    }

    private YamlConfiguration loadWarps() {
        File warpsFile = new File(getDataFolder(), "warps.yml");
        if (!warpsFile.exists()) {
            saveResource("warps.yml", false);
        }
        YamlConfiguration warpConfig = YamlConfiguration.loadConfiguration(warpsFile);
        return warpConfig;
    }

    private YamlConfiguration loadPetsConfig() {
        File petsFile = new File(getDataFolder(), "pets.yml");
        if (!petsFile.exists()) {
            saveResource("pets.yml", false);
        }
        YamlConfiguration petsConfig = YamlConfiguration.loadConfiguration(petsFile);
        return petsConfig;
    }

    public File getWarpsFile() {
        return new File(getDataFolder(), "warps.yml");
    }

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

//    public void createTable() {
//        String sql = "CREATE TABLE IF NOT EXISTS adventuresCraft (Player varchar(200), dailyLogin double);";
//// prepare the statement to be executed
//        try {
//            PreparedStatement stmt = connection.prepareStatement(sql);
//            // use executeUpdate() to update the databases table.
//            stmt.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    //https://www.spigotmc.org/wiki/mysql-database-integration-with-your-plugin/
//    //https://www.spigotmc.org/threads/using-mysql-with-java-basic-tutorial.222291/
//    public void addToDB(Player player) {
//        String sql = "UPDATE adventuresCraft SET dailyLogin=? WHERE Player=?";
//        try {
//            PreparedStatement stmt = connection.prepareStatement(sql);
//            stmt.setString(2, player.getUniqueId().toString());
//            stmt.setLong(1, Instant.now().getEpochSecond());
//            int update = stmt.executeUpdate();
//            if (update == 0) {
//                sql = "INSERT INTO adventuresCraft (Player, dailyLogin) VALUES (?, ?);";
//                stmt = connection.prepareStatement(sql);
//                stmt.setString(1, player.getUniqueId().toString());
//                stmt.setLong(2, Instant.now().getEpochSecond());
//                stmt.execute();
//                stmt.close();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    public void checkFromDB(Player player) {
//        String sql = "SELECT dailyLogin FROM adventuresCraft WHERE Player=?";
//        PreparedStatement stmt = null;
//        try {
//            stmt = connection.prepareStatement(sql);
//            stmt.setString(1, player.getUniqueId().toString()); // Set first "?" to query string
//            ResultSet results = stmt.executeQuery();
//            if (!results.next()) {
//                System.out.println("Failed");
//            } else {
//                System.out.println(results.getString("dailyLogin"));
//                System.out.println("Success");
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }

//    public void restart() {
//        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, () -> {
//            Bukkit.broadcastMessage(ChatColor.RED + "---------------------------------");
//            Bukkit.broadcastMessage(ChatColor.YELLOW + "Server will be restarting in 10 minutes!");
//            Bukkit.broadcastMessage(ChatColor.RED + "---------------------------------");
//            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, () -> {
//                Bukkit.broadcastMessage(ChatColor.RED + "---------------------------------");
//                Bukkit.broadcastMessage(ChatColor.YELLOW + "Server will be restarting in 5 minutes!");
//                Bukkit.broadcastMessage(ChatColor.RED + "---------------------------------");
//                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, () -> {
//                    Bukkit.broadcastMessage(ChatColor.RED + "---------------------------------");
//                    Bukkit.broadcastMessage(ChatColor.GOLD + "Server will be restarting in 3 minutes!");
//                    Bukkit.broadcastMessage(ChatColor.RED + "---------------------------------");
//                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, () -> {
//                        Bukkit.broadcastMessage(ChatColor.RED + "---------------------------------");
//                        Bukkit.broadcastMessage(ChatColor.DARK_RED + "Server is now restarting!");
//                        Bukkit.broadcastMessage(ChatColor.RED + "---------------------------------");
//                        this.setEnabled(false);
//                    }, 3600L);
//                }, 2400L);
//            }, 6000L);
//        }, 420000L);
//    }

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
                        .append(Component.text(" Yard", NamedTextColor.GOLD))
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
                        .hoverEvent(Component.text("Click to return to the Yard!", NamedTextColor.GREEN))
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
}