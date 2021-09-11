package monzter.adventurescraft.plugin.utilities.general;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class PurchaseEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private final Player player;
    private final ItemStack itemPurchased;
    private final int amountPurchased;


    public PurchaseEvent(Player player, ItemStack itemPurchased, int amountPurchased) {
        this.player = player;
        this.itemPurchased = itemPurchased;
        this.amountPurchased = amountPurchased;
    }

    public Player getPlayer() {
        return player;
    }

    public ItemStack getItemPurchased() {
        return itemPurchased;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

}
