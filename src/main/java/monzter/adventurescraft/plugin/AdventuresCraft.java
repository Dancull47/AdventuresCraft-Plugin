package monzter.adventurescraft.plugin;

import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.flags.Flag;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.flags.registry.FlagConflictException;
import com.sk89q.worldguard.protection.flags.registry.FlagRegistry;
import monzter.adventurescraft.plugin.commands.*;
import monzter.adventurescraft.plugin.event.*;
import monzter.adventurescraft.plugin.event.extras.Pet;
import monzter.adventurescraft.plugin.event.extras.Stats;
import monzter.adventurescraft.plugin.mySQL.MySQL;
import monzter.adventurescraft.plugin.mySQL.SQLGetter;
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
import java.util.Set;
import java.util.logging.Level;

public class AdventuresCraft extends JavaPlugin implements Listener {
    public MySQL SQL;
    public SQLGetter data;
    private static Permission perms = null;
    private static Economy econ = null;
    public static YamlConfiguration LANGUAGE;
    public static File LANGUAGE_FILE;
    private StateFlag prisonMineFlag;

    @Override
    public void onLoad() {
//        if (!getDataFolder().mkdir()) {
//            getLogger().log(Level.SEVERE, Language.TITLE.toString() + ChatColor.RED + "Failed to create Plugin Folder!!!" + "\n"
//                    + Language.TITLE + ChatColor.RED + "Check your file permissions!!");
//            this.setEnabled(false);
//            return;
//        }
        try {
            prisonMineFlag = registerFlag();
        } catch (IllegalStateException e) {
            getLogger().log(Level.SEVERE, Language.TITLE.toString() + ChatColor.RED + "Failed to register Region Flag!" + "\n"
                    + Language.TITLE + ChatColor.RED + "Report this stack trace to Monzter#4951 on Discord!", e);
            this.setEnabled(false);
        }
    }

    @Override
    public void onEnable() {
        this.SQL = new MySQL(this);
        this.data = new SQLGetter(this);
        SQL.connect();
        if (!setupEconomy()) {
            getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            this.setEnabled(false);
            return;
        }
        try {
            loadLang();
        } catch (IOException e) {
            getLogger().log(Level.SEVERE, Language.TITLE.toString() + ChatColor.RED + "Couldn't create language file." + "\n"
                    + Language.TITLE + ChatColor.RED + "This is a fatal error. Now disabling", e);
            this.setEnabled(false);
            return;
        }
        setupPermissions();
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
        Bukkit.getServer().getPluginManager().registerEvents(new ProjectileCancelArrowDrop(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new AntiDrop(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new InteractPetEgg(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new InteractBlockActions(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new BlockPhysics(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new Join(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new BlockBreakMining(this, prisonMineFlag), this);
        getCommand("Login").setExecutor(new Security(this));
        getCommand("Spawn").setExecutor(new Commands(this));
        getCommand("ActiveQuest").setExecutor(new Commands(this));
        getCommand("UnclaimedQuest").setExecutor(new Commands(this));
        getCommand("Quest").setExecutor(new Commands(this));
        getCommand("Pet").setExecutor(new Commands(this));
        getCommand("Pet").setTabCompleter(new Commands(this));
        getCommand("Discord").setExecutor(new Commands(this));
        getCommand("Bank").setExecutor(new Commands(this));
        getCommand("BattlePass").setExecutor(new BattlePass(this));
        getCommand("Booster").setExecutor(new Booster(this));
        getCommand("Sell").setExecutor(new Sell(this));
        getCommand("Mine").setExecutor(new Mine(this));
        getCommand("Warp").setExecutor(new Warps(this, loadWarps()));
        getCommand("Warp").setTabCompleter(new Warps(this, loadWarps()));
        saveDefaultConfig();
        new Placeholder(this, perms, loadPets()).register();
        getLogger().info(Language.TITLE.toString() + ChatColor.GREEN + "has started!");

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") == null)
            getLogger().log(Level.WARNING, "PlaceholderAPI is NOT installed!");
        if (Bukkit.getPluginManager().getPlugin("HolographicDisplays") == null)
            getLogger().log(Level.WARNING, "HolographicDisplays is NOT installed!");
    }


    @Override
    public void onDisable() {
        getLogger().info(Language.TITLE.toString() + ChatColor.GREEN + "has shut down!");
        SQL.disconnect();
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

    public Permission getPermissions() {
        return perms;
    }

    public static Economy getEconomy() {
        return econ;
    }


    public void loadLang() throws IOException {
        File language = new File(getDataFolder(), "language.yml");
        if (!language.exists()) {
            language.createNewFile();
            InputStream defConfigStream = this.getResource("language.yml");
            if (defConfigStream != null) {
                YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(language);
                defConfig.save(language);
                Language.setFile(defConfig);
            }
        }
        YamlConfiguration conf = YamlConfiguration.loadConfiguration(language);
        for (Language item : Language.values()) {
            if (conf.getString(item.getPath()) == null) {
                conf.set(item.getPath(), item.getDefault());
            }
        }
        Language.setFile(conf);
        this.LANGUAGE = conf;
        this.LANGUAGE_FILE = language;
        try {
            conf.save(getLangFile());
        } catch (IOException e) {
            getLogger().log(Level.WARNING, Language.TITLE.toString() + ChatColor.RED + "Failed to save lang.yml." + "\n"
                    + Language.TITLE + ChatColor.RED + "Report this stack trace to Monzter#4951 on Discord!", e);
        }
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
                getLogger().log(Level.WARNING, Language.TITLE.toString() + ChatColor.RED + "Cannot find Pet Rarity Config section with key: '" + currentPetName + "'.");
                continue;
            }
            ConfigurationSection rarityConfigSection = petConfig.getConfigurationSection(currentPetName);
            Set<String> rarities = rarityConfigSection.getKeys(false);
            for (String currentRarity : rarities) {
                if (!rarityConfigSection.isConfigurationSection(currentRarity)) {
                    getLogger().log(Level.WARNING, Language.TITLE.toString() + ChatColor.RED + "Cannot find Pet Stat Config section with key: '" + currentPetName + "." + currentRarity + "'.");
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
                        getLogger().log(Level.WARNING, Language.TITLE.toString() + ChatColor.RED + "Cannot find Pet Stat value with key: '" + currentPetName + "." + currentRarity + "." + currentStat + "'.");
                        continue;
                    }
                    try {
                        Stats statType = Stats.valueOf(currentStat);
                        double statsValue = statsConfigSection.getDouble(currentStat);
                        pet.addStat(statType, statsValue);
                    } catch (IllegalArgumentException e) {
                        getLogger().log(Level.WARNING, Language.TITLE.toString() + ChatColor.RED + "Cannot find Pet Stat type with key: '" + currentPetName + "." + currentRarity + "." + currentStat + "'.");
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

        YamlConfiguration petConfig = YamlConfiguration.loadConfiguration(warpsFile);
        return petConfig;
    }

    public File getWarpsFile() {
        return new File(getDataFolder(), "warps.yml");
    }

    public YamlConfiguration getLanguage() {
        return LANGUAGE;
    }

    public File getLangFile() {
        return LANGUAGE_FILE;
    }

    /**
     * This will register our custom World-Guard flag "prison-mine".
     * <br>https://worldguard.enginehub.org/en/latest/developer/regions/custom-flags/
     * <br>Flag Types
     * <br>https://worldguard.enginehub.org/en/latest/regions/flags/
     */
    public StateFlag registerFlag() {
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
}