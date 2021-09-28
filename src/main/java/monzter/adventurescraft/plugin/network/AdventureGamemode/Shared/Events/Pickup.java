package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.Events;

import io.lumine.mythic.lib.api.item.NBTItem;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.mainMenu.resourceCollector.Resources;
import monzter.adventurescraft.plugin.utilities.beton.BetonPointsManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAttemptPickupItemEvent;

public class Pickup implements Listener {
    private final AdventuresCraft plugin;
    private final BetonPointsManager betonPointsManager;

    public Pickup(AdventuresCraft plugin, BetonPointsManager betonPointsManager) {
        this.plugin = plugin;
        this.betonPointsManager = betonPointsManager;
    }


    @EventHandler
    public void pickup(PlayerAttemptPickupItemEvent event) {
        Player player = event.getPlayer();
        String item;
        NBTItem nbtItem = NBTItem.get(event.getItem().getItemStack());
        if (nbtItem.hasType())
            item = nbtItem.getString("MMOITEMS_ITEM_ID");
        else
            item = event.getItem().getItemStack().getType().toString();
        for (Resources resource : Resources.values())
            if (resource.getItemStack().getType() == event.getItem().getItemStack().getType()) {
                if (NBTItem.get(resource.itemStack).hasType() && item.equals(NBTItem.get(resource.itemStack).getString("MMOITEMS_ITEM_ID")) ||
                        !NBTItem.get(resource.itemStack).hasType() && !nbtItem.hasType())
                    if (player.hasPermission("COLLECTOR." + item)) {
                        int level = 1;
                        if (player.hasPermission("COLLECTOR.LEVEL." + resource.getCategory().toUpperCase() + "." + "5"))
                            level = 5;
                        else if (player.hasPermission("COLLECTOR.LEVEL." + resource.getCategory().toUpperCase() + "." + "4"))
                            level = 4;
                        else if (player.hasPermission("COLLECTOR.LEVEL." + resource.getCategory().toUpperCase() + "." + "3"))
                            level = 3;
                        else if (player.hasPermission("COLLECTOR.LEVEL." + resource.getCategory().toUpperCase() + "." + "2"))
                            level = 2;

                        if (maxCalc(level) >= betonPointsManager.getPoints(player, "items." + item)) {
                            betonPointsManager.givePoint(player, "items." + item, event.getItem().getItemStack().getAmount());
                            event.getItem().remove();
                            event.setCancelled(true);
                            break;
                        }
                    }
            }
    }

    private int maxCalc(int level) {
        int max = 10_000;
        if (level == 5)
            max = 100_000;
        else if (level == 4)
            max = 75_000;
        else if (level == 3)
            max = 50_000;
        else if (level == 2)
            max = 25_000;
        return max;
    }
}