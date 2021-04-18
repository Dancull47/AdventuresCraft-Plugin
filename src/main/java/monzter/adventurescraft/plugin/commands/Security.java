package monzter.adventurescraft.plugin.commands;

import monzter.adventurescraft.plugin.AdventuresCraft;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class Security implements CommandExecutor {
    private final AdventuresCraft plugin;
    private HashMap<UUID, Integer> codeAttempts = new HashMap<>();

    public Security(AdventuresCraft plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = ((Player) sender).getPlayer();
            if (player.getUniqueId().toString().equals("03c15276-ecfe-49bf-b20c-c4c20b13cb87")) {
                if (args[0].equals("3659")) {
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
            if (player.getUniqueId().toString().equals("f9f9bc0a-f478-4567-9bfe-c4c78ae94e69")) {
                if (args[0].equals("9999")) {
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
            return true;
        }
        return false;
    }
}
