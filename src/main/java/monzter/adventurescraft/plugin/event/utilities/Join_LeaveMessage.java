package monzter.adventurescraft.plugin.event.utilities;

import monzter.adventurescraft.plugin.AdventuresCraft;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Join_LeaveMessage implements Listener {
    private AdventuresCraft plugin;

    public Join_LeaveMessage(AdventuresCraft plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        event.setJoinMessage("");
    }
    @EventHandler
    public void onKick(PlayerKickEvent event) {
        event.setLeaveMessage("");
    }
    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        event.setQuitMessage("");
    }
}