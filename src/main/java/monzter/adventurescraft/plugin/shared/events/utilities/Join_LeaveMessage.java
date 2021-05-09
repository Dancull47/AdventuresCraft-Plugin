package monzter.adventurescraft.plugin.shared.events.utilities;

import monzter.adventurescraft.plugin.AdventuresCraft;
import net.kyori.adventure.text.Component;
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
        event.joinMessage(Component.text(""));
    }
    @EventHandler
    public void onKick(PlayerKickEvent event) {
        event.leaveMessage(Component.text(""));
    }
    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        event.quitMessage(Component.text(""));
    }
}