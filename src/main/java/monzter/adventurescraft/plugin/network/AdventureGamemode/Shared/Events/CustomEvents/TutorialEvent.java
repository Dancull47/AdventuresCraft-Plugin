package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.Events.CustomEvents;

import monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.Events.TutorialMessages;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class TutorialEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private final Player player;
    private final TutorialMessages tutorialMessage;


    public TutorialEvent(Player player, TutorialMessages tutorialMessage) {
        this.player = player;
        this.tutorialMessage = tutorialMessage;
    }

    public Player getPlayer() {
        return player;
    }

    public TutorialMessages getTutorialMessage() {
        return tutorialMessage;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

}
