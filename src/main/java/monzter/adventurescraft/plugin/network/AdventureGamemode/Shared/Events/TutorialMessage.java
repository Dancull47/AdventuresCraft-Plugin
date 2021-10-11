package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.Events;

import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.Events.CustomEvents.TutorialEvent;
import monzter.adventurescraft.plugin.utilities.enums.Prefix;
import monzter.adventurescraft.plugin.utilities.general.Cooldown;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class TutorialMessage implements Listener {
    private final AdventuresCraft plugin;


    public TutorialMessage(AdventuresCraft plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void tutorialEvent(TutorialEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPermission(event.getTutorialMessage().name()))
            if (!Cooldown.isInCooldown(player.getUniqueId(), event.getTutorialMessage().name()))
                for (TutorialMessages message : TutorialMessages.values())
                    if (message.name().equals(event.getTutorialMessage().name())) {
                        player.sendMessage(Prefix.TUT_PREFIX.getComponent().append(event.getTutorialMessage().getComponent()));
                        message.disableMessage(player, message.name());
                        Cooldown cooldown = new Cooldown(player.getUniqueId(), event.getTutorialMessage().name(), 10);
                        cooldown.start();
                    }
    }
}
