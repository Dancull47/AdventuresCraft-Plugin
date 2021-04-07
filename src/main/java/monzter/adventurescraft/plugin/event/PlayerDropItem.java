package monzter.adventurescraft.plugin.event;

import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.event.extras.DropTier;
import net.Indyuce.mmoitems.MMOItems;
import net.Indyuce.mmoitems.api.ItemTier;
import net.Indyuce.mmoitems.api.item.mmoitem.LiveMMOItem;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

import java.time.Instant;

public class PlayerDropItem implements Listener {
    private final AdventuresCraft plugin;
    private final DropTier[] TIERS = new DropTier[]{
            new DropTier("COMMON", "1", 2),
            new DropTier("UNCOMMON", "2", 2),
            new DropTier("RARE", "3", 3),
            new DropTier("LEGENDARY", "4", 3),
            new DropTier("EXOTIC", "5", 4)
    };

    private static final int TIMEOUT = 5;

    public PlayerDropItem(AdventuresCraft plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void cancelDrop(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        ItemStack droppedItem = event.getItemDrop().getItemStack();
        ItemTier tier = MMOItems.plugin.getTiers().findTier(new LiveMMOItem(droppedItem));
        for (DropTier dropTier : TIERS) {
            if (dropTier.getTier() == tier && player.hasPermission(dropTier.getPermission())) {
                if (!player.hasMetadata("LAST_DROP"))
                    player.setMetadata("LAST_DROP", new FixedMetadataValue(MMOItems.plugin, Instant.now().getEpochSecond()));

                long lastDrop = player.getMetadata("LAST_DROP").get(0).asLong();

                if (!player.hasMetadata("DROP_TIMES") || Instant.now().getEpochSecond() - lastDrop > TIMEOUT) {
                    player.setMetadata("DROP_TIMES", new FixedMetadataValue(plugin, 0));
                    player.setMetadata("LAST_DROP", new FixedMetadataValue(MMOItems.plugin, Instant.now().getEpochSecond()));
                }
                int times = player.getMetadata("DROP_TIMES").get(0).asInt() + 1;

                if (times > dropTier.getMax() || player.getInventory().firstEmpty() == -1) {
                    player.setMetadata("DROP_TIMES", new FixedMetadataValue(plugin, 0));
                } else {
                    event.setCancelled(true);
                    player.sendMessage(ChatColor.RED + "You've been stopped from dropping this item! (" + times + "/" + dropTier.getMax() + ")");
                    player.setMetadata("DROP_TIMES", new FixedMetadataValue(plugin, times));
                }

                player.setMetadata("LAST_DROP", new FixedMetadataValue(plugin, Instant.now().getEpochSecond()));
            }
        }
    }
}
