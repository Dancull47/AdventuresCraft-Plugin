package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.Events;

import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.Events.CustomEvents.TutorialEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class TutorialMessage implements Listener {
    private final AdventuresCraft plugin;


    public TutorialMessage(AdventuresCraft plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void pickup(TutorialEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPermission(event.getTutorialMessage().name()))
            for (TutorialMessages message : TutorialMessages.values())
                if (message.name().equals(event.getTutorialMessage().name())) {
                    player.sendMessage(event.getTutorialMessage().getComponent());
                    message.disableMessage(player, message.name());
                }
    }
}
