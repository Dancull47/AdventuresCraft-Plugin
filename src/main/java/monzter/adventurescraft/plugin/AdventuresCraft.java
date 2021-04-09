package monzter.adventurescraft.plugin;

import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.flags.Flag;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.flags.registry.FlagConflictException;
import com.sk89q.worldguard.protection.flags.registry.FlagRegistry;
import monzter.adventurescraft.plugin.commands.Commands;
import monzter.adventurescraft.plugin.event.*;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import scala.language;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;

public class AdventuresCraft extends JavaPlugin implements Listener {
    private static Permission perms = null;
    public static YamlConfiguration LANGUAGE;
    public static File LANGUAGE_FILE;
    private StateFlag prisonMineFlag;

    @Override
    public void onLoad(){
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
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerChat(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new ProjectileHit(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerDropItem(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerInteract(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new BlockPhysics(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new BlockBreak(this, prisonMineFlag), this);
        getCommand("Captcha").setExecutor(new Commands(this));
        saveDefaultConfig();
        new Placeholder().register();
        getLogger().info(Language.TITLE.toString() + ChatColor.GREEN + "has started!");

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") == null)
            getLogger().log(Level.WARNING, "PlaceholderAPI is NOT installed!");
    }


    @Override
    public void onDisable() {
        getLogger().info(Language.TITLE.toString() + ChatColor.GREEN + "has shut down!");
    }


    private void setupPermissions() {
        perms = getServer().getServicesManager().getRegistration(Permission.class).getProvider();
    }

    public Permission getPermissions() {
        return perms;
    }


    public void loadLang() throws IOException {
        File language = new File(getDataFolder(), "language.yml");
        if (!language.exists()) {
                getDataFolder().mkdir();
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
}