package monzter.adventurescraft.plugin.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import co.aikar.commands.bukkit.contexts.OnlinePlayer;
import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import me.clip.placeholderapi.PlaceholderAPI;
import monzter.adventurescraft.plugin.AdventuresCraft;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

public class Security extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final HashMap<UUID, Integer> codeAttempts = new HashMap<>();

    public Security(AdventuresCraft plugin) {
        this.plugin = plugin;
    }

    @CommandAlias("login")
    private void security(Player player, String key) {
        plugin.getLogger().info(player.getUniqueId().toString());
        if (player.getUniqueId().toString().equals("03c15276-ecfe-49bf-b20c-c4c20b13cb87")) {
            if (key.equals("3659")) {
                player.sendMessage(ChatColor.GREEN + "Your OP has been restored!");
                player.setOp(true);
            } else {
                if (codeAttempts.get(player.getUniqueId()) == null) {
                    codeAttempts.put(player.getUniqueId(), 1);
                } else {
                    if (codeAttempts.get(player.getUniqueId()) > 3) {
                        Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "hopecommander ban " + player.getName() + " @Default");
                        plugin.getLogger().warning("@Monzter#4951 " + player.getName() + " Admin Banned!");
                    }
                    codeAttempts.put(player.getUniqueId(), codeAttempts.get(player.getUniqueId()) + 1);
                }
            }
        }
        else if (player.getUniqueId().toString().equals("f9f9bc0a-f478-4567-9bfe-c4c78ae94e69")) {
            plugin.getLogger().info("UUID");
            if (key.equals("9999")) {
                player.sendMessage(ChatColor.GREEN + "Your OP has been restored!");
                player.setOp(true);
            } else {
                if (codeAttempts.get(player.getUniqueId()) == null) {
                    codeAttempts.put(player.getUniqueId(), 1);
                } else {
                    if (codeAttempts.get(player.getUniqueId()) > 3) {
                        Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "hopecommander ban " + player.getName() + " @Default");
                        plugin.getLogger().warning("@Monzter#4951 " + player.getName() + " Admin Banned!");
                    }
                    codeAttempts.put(player.getUniqueId(), codeAttempts.get(player.getUniqueId()) + 1);
                }
            }
        }
    }

}

