package monzter.adventurescraft.plugin.network.AdventureGamemode.Home.Events;

import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.luckperms.PermissionLP;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Join implements Listener {
    private final AdventuresCraft plugin;
    private final PermissionLP permissionLP;


    public Join(AdventuresCraft plugin, PermissionLP permissionLP) {
        this.plugin = plugin;
        this.permissionLP = permissionLP;
    }


    @EventHandler
    public void join(PlayerJoinEvent event) {
        switch (plugin.SERVER) {
            case "Home":
                Player player = event.getPlayer();
                if (!player.hasPermission("Home.Claimed")) {
                    player.sendMessage(ChatColor.RED + "It seems like you don't have a " + ChatColor.GOLD + "Home " + ChatColor.RED + "yet! We're getting you one now, but it'll take a few seconds!");
                    player.performCommand("p auto");
                    permissionLP.givePermission(player, "Home.Claimed");
                }

        }
    }
}