package monzter.adventurescraft.plugin.commands;

import monzter.adventurescraft.plugin.AdventuresCraft;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Mine implements CommandExecutor {
    private final AdventuresCraft plugin;

    public Mine(AdventuresCraft plugin) {
        this.plugin = plugin;
    }
    String ranks = "zyxwvutsrqponmlkjlhgfedcba";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = ((Player) sender).getPlayer();
            for (int i = 0; i < ranks.length(); i++) {
                if (player.hasPermission("mines.tp." + ranks.charAt(i))){
                    player.performCommand("warp " + ranks.charAt(i));
                    break;
                }
            }
        }
        return false;
    }
}

