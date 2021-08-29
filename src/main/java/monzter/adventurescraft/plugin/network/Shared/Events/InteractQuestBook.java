package monzter.adventurescraft.plugin.network.Shared.Events;

import monzter.adventurescraft.plugin.AdventuresCraft;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.meta.BookMeta;

public class InteractQuestBook implements Listener {
    private final AdventuresCraft plugin;

    public InteractQuestBook(AdventuresCraft plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    private void questBook(PlayerInteractEvent event) {
        switch (plugin.SERVER) {
            case "Prison":
            case "Cell":
            case "Adventure":
            case "Home":
                final Player player = event.getPlayer();
                if (event.getHand() == EquipmentSlot.HAND && event.getHand() != EquipmentSlot.OFF_HAND && event.getAction() == Action.LEFT_CLICK_BLOCK &&
                        event.getItem() != null && event.getItem().getType() == Material.WRITTEN_BOOK) {
                    final BookMeta bookMeta = (BookMeta) event.getItem().getItemMeta();
                    if (bookMeta.getTitle() != null && bookMeta.getTitle().equals(ChatColor.WHITE + "Quest Journal")) {
                        if (!player.isSneaking())
                            player.performCommand("activeQuest");
                        else
                            player.performCommand("UnclaimedQuest");
                    }
                }
        }
    }
}
