package monzter.adventurescraft.plugin.event;

import monzter.adventurescraft.plugin.AdventuresCraft;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public class InteractQuestBook implements Listener {
    private AdventuresCraft plugin;

    public InteractQuestBook(AdventuresCraft plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void questBook(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack itemStack = event.getItem();
        if (event.getHand() == EquipmentSlot.HAND) {
            if (itemStack != null && itemStack.getType().equals(Material.WRITTEN_BOOK)) {
                BookMeta bookMeta = (BookMeta) itemStack.getItemMeta();
                if (bookMeta.getTitle() != null) {
                    if (bookMeta.getTitle().equals("Quest Journal")) {
                        player.sendMessage("Book Opened!");
                        Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "rpgmenu open default-Menus-menu.active " + player.getName());
                    }
                }
            }
        }
    }
}