package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.ClassSystem;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ProfessionLevelUpEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private final Player player;
    private Professions profession;
    private final int level;


    public ProfessionLevelUpEvent(Player player, Professions profession, int level) {
        this.player = player;
        this.profession = profession;
        this.level = level;
    }

    public Player getPlayer() {
        return player;
    }

    public int getLevel() {
        return level;
    }

    public Professions getProfession() {
        return profession;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

}
