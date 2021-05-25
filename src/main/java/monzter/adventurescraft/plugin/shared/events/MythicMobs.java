package monzter.adventurescraft.plugin.shared.events;

import co.aikar.commands.BaseCommand;
import com.kirelcodes.miniaturepets.loader.PetLoader;
import com.kirelcodes.miniaturepets.pets.PetContainer;
import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobDeathEvent;
import me.clip.placeholderapi.PlaceholderAPI;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.general.FullInventory;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.Map;
import java.util.UUID;
import java.util.stream.Stream;

public class MythicMobs extends BaseCommand implements Listener {
    private final AdventuresCraft plugin;
    private final FullInventory fullInventory;


    public MythicMobs(AdventuresCraft plugin, FullInventory fullInventory) {
        this.plugin = plugin;
        this.fullInventory = fullInventory;
    }

//    @EventHandler
//    public void petEgg(MythicMobDeathEvent event) {
//        Player player = (Player) event.getKiller();
//        if (player != null) {
//            for (ItemStack item : event.getDrops()) {
//                final Map<Integer, ItemStack> map = player.getInventory().addItem(item);
//                if (!map.isEmpty()) {
//                    map.values().forEach(items -> player.getWorld().dropItemNaturally(player.getLocation(), items)
//                            .setMetadata("AntiLoot", new FixedMetadataValue(plugin, player.getUniqueId())));
//                }
//            }
//            event.getDrops().clear();
//        }
//    }
//
//    @EventHandler
//    public void onItemPickup(PlayerPickupItemEvent event) {
//        plugin.getLogger().info("Called");
//        Player player = event.getPlayer();
//        if (event.getItem().hasMetadata("AntiLoot")) {
//            if (!player.hasPermission("anti.loot.bypass")) {
//                if (!player.getUniqueId().toString().equals(event.getItem().getMetadata("AntiLoot").get(0).asString())) {
//                    player.sendMessage(ChatColor.RED + "This is not your loot!");
//                    event.setCancelled(true);
//                }
//            }
//        }
//    }
}