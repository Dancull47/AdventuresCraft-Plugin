package monzter.adventurescraft.plugin;

import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.flags.Flag;
import com.sk89q.worldguard.protection.flags.LocationFlag;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.flags.StringFlag;
import com.sk89q.worldguard.protection.flags.registry.FlagConflictException;
import com.sk89q.worldguard.protection.flags.registry.FlagRegistry;
import monzter.adventurescraft.plugin.commands.*;
import monzter.adventurescraft.plugin.event.*;
import monzter.adventurescraft.plugin.event.extras.Pet;
import monzter.adventurescraft.plugin.event.extras.Stats;
import monzter.adventurescraft.plugin.mySQL.MySQL;
import monzter.adventurescraft.plugin.mySQL.SQLGetter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;

public class AdventuresCraft extends JavaPlugin implements Listener {
    public static final String TITLE = ChatColor.RED + "[" + ChatColor.GOLD + "AdventuresCraft" + ChatColor.RED + "]";
    public MySQL SQL;
    public SQLGetter data;
    private static Permission perms = null;
    private static Economy econ = null;
    public static YamlConfiguration LANGUAGE;
    public static File LANGUAGE_FILE;
    private StateFlag prisonMineFlag;
    private StringFlag displayNameFlag;
    private LocationFlag sellLocationFlag;
    private long restartTime;

    @Override
    public void onLoad() {
        try {
            prisonMineFlag = registerStateFlag();
            displayNameFlag = registerStringFlag();
            sellLocationFlag = registerLocationFlag();
        } catch (IllegalStateException e) {
            getLogger().log(Level.SEVERE, TITLE + ChatColor.RED + "Failed to register Region Flag!" + "\n"
                    + TITLE + ChatColor.RED + "Report this stack trace to Monzter#4951 on Discord!", e);
            this.setEnabled(false);
        }
    }

    @Override
    public void onEnable() {
//        this.SQL = new MySQL(this);
//        this.data = new SQLGetter(this, SQL);
        restartTime = System.currentTimeMillis() + 21600000;
        restart();
        tipsMessage();
        if (!setupEconomy()) {
            getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            this.setEnabled(false);
            return;
        }
        setupPermissions();
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
        Bukkit.getServer().getPluginManager().registerEvents(new ProjectileCancelArrowDrop(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new AntiDrop(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new InteractPetEgg(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new InteractPets(this, loadPetsConfig()), this);
        Bukkit.getServer().getPluginManager().registerEvents(new InteractBlockActions(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new BlockPhysics(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new Join(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new Join_LeaveMessage(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new BlockBreakMining(this, prisonMineFlag), this);
        Bukkit.getServer().getPluginManager().registerEvents(new Regions(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new InteractQuestBook(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new Voting(this), this);
        getCommand("PetUnequip").setExecutor(new InteractPets(this, loadPetsConfig()));
        getCommand("Hatch").setExecutor(new Hatch(this));
//        getCommand("DropTables").setExecutor(new DropTables(this));
        getCommand("Login").setExecutor(new Security(this));
        getCommand("Reward").setExecutor(new AdminCommands(this));
        getCommand("DonationRewards").setExecutor(new DonationRewards(this));
        getCommand("PointsAdd").setExecutor(new AdminCommands(this));
        getCommand("PointsAmount").setExecutor(new AdminCommands(this));
        getCommand("Stat").setExecutor(new AdminCommands(this));
        getCommand("RestartTime").setExecutor(new AdminCommands(this));
        getCommand("Vote").setExecutor(new Commands(this));
        getCommand("Spawn").setExecutor(new Commands(this));
        getCommand("ActiveQuest").setExecutor(new Commands(this));
        getCommand("UnclaimedQuest").setExecutor(new Commands(this));
        getCommand("Quest").setExecutor(new Commands(this));
        getCommand("Pet").setExecutor(new Commands(this));
        getCommand("Pet").setTabCompleter(new Commands(this));
        getCommand("Hatch").setTabCompleter(new Commands(this));
        getCommand("Discord").setExecutor(new Commands(this));
        getCommand("Bank").setExecutor(new Commands(this));
        getCommand("BattlePass").setExecutor(new BattlePass(this));
        getCommand("Booster").setExecutor(new Booster(this));
        getCommand("Sell").setExecutor(new Sell(this, sellLocationFlag));
        getCommand("Mine").setExecutor(new Mine(this));
        getCommand("Warp").setExecutor(new Warps(this, loadWarps()));
        getCommand("Warp").setTabCompleter(new Warps(this, loadWarps()));
        saveDefaultConfig();
        new Placeholder(this, perms, loadPets(), displayNameFlag, restartTime).register();
        getLogger().info(TITLE + ChatColor.GREEN + "has started!");

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") == null)
            getLogger().log(Level.WARNING, "PlaceholderAPI is NOT installed!");
        if (Bukkit.getPluginManager().getPlugin("HolographicDisplays") == null)
            getLogger().log(Level.WARNING, "HolographicDisplays is NOT installed!");
    }


    @Override
    public void onDisable() {
        getLogger().info(TITLE + ChatColor.GREEN + "has shut down!");
//        SQL.disconnect();
    }


    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    public void money(Player player, double amount) {
        EconomyResponse r = econ.depositPlayer(player, amount);
        if (!r.transactionSuccess()) {
            player.sendMessage(ChatColor.RED + "An error occurred while trying to give you money, report to Admins!");
            getLogger().info(ChatColor.RED + "An error occurred while sending " + amount + " to " + player);
        }
    }

    private void setupPermissions() {
        perms = getServer().getServicesManager().getRegistration(Permission.class).getProvider();
    }

//    public void loadLang() throws IOException {
//        File language = new File(getDataFolder(), "language.yml");
//        if (!language.exists()) {
//            language.createNewFile();
//            InputStream defConfigStream = this.getResource("language.yml");
//            if (defConfigStream != null) {
//                YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(language);
//                defConfig.save(language);
//                Language.setFile(defConfig);
//            }
//        }
//        YamlConfiguration conf = YamlConfiguration.loadConfiguration(language);
//        for (Language item : Language.values()) {
//            if (conf.getString(item.getPath()) == null) {
//                conf.set(item.getPath(), item.getDefault());
//            }
//        }
//        Language.setFile(conf);
//        this.LANGUAGE = conf;
//        this.LANGUAGE_FILE = language;
//        try {
//            conf.save(getLangFile());
//        } catch (IOException e) {
//            getLogger().log(Level.WARNING, TITLE + ChatColor.RED + "Failed to save lang.yml." + "\n"
//                    + TITLE + ChatColor.RED + "Report this stack trace to Monzter#4951 on Discord!", e);
//        }
//    }

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
            Flag<?> existing = registry.get("region-display-name");
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

    public void restart() {
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, () -> {
            Bukkit.broadcastMessage(ChatColor.RED + "---------------------------------");
            Bukkit.broadcastMessage(ChatColor.YELLOW + "Server will be restarting in 10 minutes!");
            Bukkit.broadcastMessage(ChatColor.RED + "---------------------------------");
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, () -> {
                Bukkit.broadcastMessage(ChatColor.RED + "---------------------------------");
                Bukkit.broadcastMessage(ChatColor.YELLOW + "Server will be restarting in 5 minutes!");
                Bukkit.broadcastMessage(ChatColor.RED + "---------------------------------");
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, () -> {
                    Bukkit.broadcastMessage(ChatColor.RED + "---------------------------------");
                    Bukkit.broadcastMessage(ChatColor.GOLD + "Server will be restarting in 3 minutes!");
                    Bukkit.broadcastMessage(ChatColor.RED + "---------------------------------");
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, () -> {
                        Bukkit.broadcastMessage(ChatColor.RED + "---------------------------------");
                        Bukkit.broadcastMessage(ChatColor.DARK_RED + "Server is now restarting!");
                        Bukkit.broadcastMessage(ChatColor.RED + "---------------------------------");
                        this.setEnabled(false);
                    }, 3600L);
                }, 2400L);
            }, 6000L);
        }, 420000L);
    }

    public void tipsMessage(){
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
            for (Player player : Bukkit.getOnlinePlayers()){
                if (player.hasPermission("tips")){
                    int lastTips = new Random().nextInt(tipList.length);
                    player.sendMessage(tipList[lastTips]);
                }
            }
        }, 0L, 20 * 90);

    }
}