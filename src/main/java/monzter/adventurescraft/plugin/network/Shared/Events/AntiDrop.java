package monzter.adventurescraft.plugin.network.Shared.Events;

import io.lumine.mythic.lib.api.item.NBTItem;
import monzter.adventurescraft.plugin.AdventuresCraft;
import net.Indyuce.mmoitems.MMOItems;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

import java.time.Instant;

public class AntiDrop implements Listener {
    private final AdventuresCraft plugin;
    private static final int TIMEOUT = 5;

    public AntiDrop(AdventuresCraft plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    private void cancelDrop(PlayerDropItemEvent event) {
        final Player player = event.getPlayer();
        final ItemStack droppedItem = event.getItemDrop().getItemStack();
        final NBTItem nbtItem = NBTItem.get(droppedItem);
        if (nbtItem.hasType()) {
            System.out.println(nbtItem.getNBTCompound("MMOITEMS_TIER"));
//  Fox getMNTCompound
            for (Tiers tier : Tiers.values()) {
                if (player.hasPermission("ANTIDROP." + tier.name()) && nbtItem.getNBTCompound("MMOITEMS_TIER").equals(tier.name())) {
                    if (!player.hasMetadata("LAST_DROP"))
                        player.setMetadata("LAST_DROP", new FixedMetadataValue(plugin, Instant.now().getEpochSecond()));

                    final long lastDrop = player.getMetadata("LAST_DROP").get(0).asLong();

                    if (!player.hasMetadata("DROP_TIMES") || Instant.now().getEpochSecond() - lastDrop > TIMEOUT) {
                        player.setMetadata("DROP_TIMES", new FixedMetadataValue(plugin, 0));
                        player.setMetadata("LAST_DROP", new FixedMetadataValue(MMOItems.plugin, Instant.now().getEpochSecond()));
                    }
                    final int times = player.getMetadata("DROP_TIMES").get(0).asInt() + 1;

                    if (times > tier.dropAmount || player.getInventory().firstEmpty() == -1) {
                        player.setMetadata("DROP_TIMES", new FixedMetadataValue(plugin, 0));
                    } else {
                        event.setCancelled(true);
                        player.sendMessage(ChatColor.RED + "You've been stopped from dropping this item! (" + times + "/" + tier.dropAmount + ")");
                        player.setMetadata("DROP_TIMES", new FixedMetadataValue(plugin, times));
                    }

                    player.setMetadata("LAST_DROP", new FixedMetadataValue(plugin, Instant.now().getEpochSecond()));
                }
            }
        }
    }

    public enum Tiers {
        COMMON(1),
        UNCOMMON(2),
        RARE(3),
        LEGENDARY(4),
        EXOTIC(5),
        ;
        public final int dropAmount;

        Tiers(int dropAmount) {
            this.dropAmount = dropAmount;
        }
    }
}
