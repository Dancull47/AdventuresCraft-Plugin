package monzter.adventurescraft.plugin.commands;

import monzter.adventurescraft.plugin.Language;
import monzter.adventurescraft.plugin.AdventuresCraft;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {
    private final AdventuresCraft plugin;

    public Commands(AdventuresCraft plugin) {
            this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 1){
            sender.sendMessage(ChatColor.GREEN + "Captcha Plugin, created by "
                    + ChatColor.GOLD + "Monzter" + ChatColor.GREEN + "!");
            return true;
        }
        if (args[0].equalsIgnoreCase("reload")){
            if (sender.isOp() || sender.hasPermission(plugin.getConfig().getString("Admin-Permission", "CAPTCHA.ADMIN"))){
                sender.sendMessage(Language.TITLE.toString() + ChatColor.GREEN + "The plugin has been reloaded!");
                plugin.reloadConfig();
                plugin.loadLang();
                return true;
            }
        }
        if (args[0].equalsIgnoreCase("reset")){
            if (sender.isOp() || sender.hasPermission(plugin.getConfig().getString("Admin-Permission", "CAPTCHA.ADMIN"))){
                Player player = (Player) sender;
                if (args.length >= 2){
                    if (Bukkit.getPlayer(args[1]) != null){
                        Player target = Bukkit.getPlayer(args[1]);
                        plugin.getPermissions().playerRemove(target, plugin.getConfig().getString("Given-Permission", "CAPTCHA.VERIFIED"));
                        target.sendMessage(Language.TITLE.toString() + Language.RESET.toString().replace("%player%", target.getName()));
                    } else{
                        sender.sendMessage(Language.TITLE.toString() + Language.INVALID_PLAYER);
                    }
                } else {
                    plugin.getPermissions().playerRemove(player, plugin.getConfig().getString("Given-Permission", "CAPTCHA.VERIFIED"));
                    sender.sendMessage(Language.TITLE.toString() + Language.RESET.toString().replace("%player%", sender.getName()));
                }
                return true;
            }
        }

        return true;
    }
}

