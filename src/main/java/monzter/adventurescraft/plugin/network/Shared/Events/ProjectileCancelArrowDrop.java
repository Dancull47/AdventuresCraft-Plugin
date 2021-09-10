package monzter.adventurescraft.plugin.network.Shared.Events;

import io.lumine.mythic.lib.api.item.NBTItem;
import monzter.adventurescraft.plugin.AdventuresCraft;
import net.Indyuce.mmoitems.MMOItems;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Trident;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;

public class ProjectileCancelArrowDrop implements Listener {
    private final AdventuresCraft plugin;

    public ProjectileCancelArrowDrop(AdventuresCraft plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void arrowDespawn(ProjectileHitEvent event) {
        if (event.getEntity().getType() == EntityType.ARROW)
            switch (plugin.SERVER) {
                case "Prison":
                case "Cell":
                case "Adventure":
                case "Home":
                    event.getEntity().remove();
                    break;
            }
    }

    @EventHandler
    public void tridentKeep(ProjectileLaunchEvent event) {
        if (event.getEntity() instanceof Trident && event.getEntity().getShooter() instanceof Player) {
            Player player = (Player) event.getEntity().getShooter();
            ItemStack itemStack = ((Trident) event.getEntity()).getItemStack();
            String mmoItemID = MMOItems.getID(NBTItem.get(itemStack));
            if (mmoItemID != null)
                player.getInventory().setItemInMainHand(itemStack);
        }
    }

    @EventHandler
    public void tridentDestroy(ProjectileHitEvent event) {
        if (event.getEntity() instanceof Trident && event.getEntity().getShooter() instanceof Player)
            event.getEntity().remove();
    }

}
