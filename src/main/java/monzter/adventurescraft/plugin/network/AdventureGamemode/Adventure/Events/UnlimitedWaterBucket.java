package monzter.adventurescraft.plugin.network.AdventureGamemode.Adventure.Events;

import io.lumine.mythic.lib.api.item.NBTItem;
import monzter.adventurescraft.plugin.AdventuresCraft;
import net.Indyuce.mmoitems.MMOItems;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class UnlimitedWaterBucket implements Listener {
    private final AdventuresCraft plugin;
    private final MMOItems mmoItems;


    public UnlimitedWaterBucket(AdventuresCraft plugin, MMOItems mmoItems) {
        this.plugin = plugin;
        this.mmoItems = mmoItems;
    }

    @EventHandler
    public void unlimitedWaterBucket(PlayerInteractEvent event) {
        switch (plugin.SERVER) {
            case "Adventure":
            case "Home":
                Player player = event.getPlayer();
                if (event.getMaterial().equals(Material.WATER_BUCKET)) {
                    final ItemStack itemStack = event.getItem();
                    final NBTItem nbtItem = NBTItem.get(itemStack);
                    final String id = MMOItems.plugin.getID(nbtItem);
                    if (id != null && id.equals("UNLIMITED_WATER"))
                        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> player.getInventory().setItemInMainHand(mmoItems.getItem("MISCELLANEOUS", "UNLIMITED_WATER")), 1);
                }
        }
    }
}