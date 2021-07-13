package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.Events;

import io.lumine.mythic.lib.api.item.NBTItem;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.beton.BetonPointsManager;
import monzter.adventurescraft.plugin.utilities.general.FullInventory;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import net.Indyuce.mmoitems.MMOItems;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAttemptPickupItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

public class Drop implements Listener {
    private final AdventuresCraft plugin;
    private final FullInventory fullInventory;
    private final SoundManager soundManager;
    private final BetonPointsManager betonPointsManager;


    public Drop(AdventuresCraft plugin, FullInventory fullInventory, SoundManager soundManager, BetonPointsManager betonPointsManager) {
        this.plugin = plugin;
        this.fullInventory = fullInventory;
        this.soundManager = soundManager;
        this.betonPointsManager = betonPointsManager;
    }

    /*
     *
     *   Could create a refund Store with these points
     *
     * */

    @EventHandler
    public void pickup(PlayerAttemptPickupItemEvent event) {
        switch (plugin.SERVER) {
            case "Adventure":
            case "Home":
                Player player = event.getPlayer();
                if (event.getItem().getItemStack().getLore() != null)
                    for (String lore : event.getItem().getItemStack().getLore())
                        if (lore.contains(ChatColor.DARK_RED.toString() + ChatColor.BOLD + "ACCOUNT BOUND")) {
                            event.setCancelled(true);
                            event.getItem().remove();
                            soundManager.soundNo(player, 1);
                            player.sendMessage(ChatColor.RED + "This item cannot be picked up and has been deleted!");
                        }
        }
    }

    @EventHandler
    public void drop(PlayerDropItemEvent event) {
        switch (plugin.SERVER) {
            case "Adventure":
            case "Home":
                Player player = event.getPlayer();
                for (String lore : event.getItemDrop().getItemStack().getLore()) {
                    if (lore.contains(ChatColor.DARK_RED.toString() + ChatColor.BOLD + "ACCOUNT BOUND")) {
                        if (player.getInventory().firstEmpty() > -1) {
                            event.setCancelled(true);
                            soundManager.soundNo(player, 1);
                            player.sendMessage(ChatColor.RED + "This item cannot be dropped!");
                        } else {
                            player.sendMessage(ChatColor.RED + "Your inventory was full and this item cannot be dropped, so it has been destroyed!");
                            betonPointsManager.givePoint(player, "refund." + MMOItems.plugin.getID(NBTItem.get(event.getItemDrop().getItemStack())), 1);
                            soundManager.soundNo(player, 2);
                            event.getItemDrop().remove();
                        }
                    }
                }
        }
    }
}