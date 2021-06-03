package monzter.adventurescraft.plugin.network.Shared.Events;

import co.aikar.commands.BaseCommand;
import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobDeathEvent;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.beton.BetonPointsManager;
import monzter.adventurescraft.plugin.utilities.general.FullInventory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class MythicMobs extends BaseCommand implements Listener {
    private final AdventuresCraft plugin;
    private final FullInventory fullInventory;
    private final BetonPointsManager betonPointsManager;


    public MythicMobs(AdventuresCraft plugin, FullInventory fullInventory, BetonPointsManager betonPointsManager) {
        this.plugin = plugin;
        this.fullInventory = fullInventory;
        this.betonPointsManager = betonPointsManager;
    }

    @EventHandler
    public void mobTrack(MythicMobDeathEvent event) {
        Player player = (Player) event.getKiller();
        switch (plugin.SERVER) {
            case "Prison":
            case "Adventure":
                betonPointsManager.givePoint(player, "mobs." + event.getMobType().getInternalName(), 1);
                betonPointsManager.givePoint(player, "faction." + event.getMob().getFaction(), 1);
                plugin.getLogger().info("We");

        }
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