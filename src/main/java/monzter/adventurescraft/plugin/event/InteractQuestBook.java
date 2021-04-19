package monzter.adventurescraft.plugin.event;

import monzter.adventurescraft.plugin.AdventuresCraft;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class InteractQuestBook implements Listener {
    private AdventuresCraft plugin;

    public InteractQuestBook(AdventuresCraft plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void questBook(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        player.sendMessage("Event fired!");
        ItemStack itemStack = event.getItem();
        if (event.getHand() == EquipmentSlot.HAND) {
            player.sendMessage("Hand");
            if (itemStack != null) {
                player.sendMessage("Not Null");
                player.sendMessage(itemStack.getItemMeta().displayName().toString());
                if (itemStack.getItemMeta().getDisplayName().equals("Quest Journal")){
                    player.sendMessage("Book Opened!");
                    Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "rpgmenu open default-Menus-menu.active " + player.getName());
                }
            }
        }
    }
}
