package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.Events;

import dev.dbassett.skullcreator.SkullCreator;
import monzter.adventurescraft.plugin.AdventuresCraft;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemSlotLock implements Listener {
    private final AdventuresCraft plugin;


    public ItemSlotLock(AdventuresCraft plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void join(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.getInventory().setItem(8, itemStack());
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> player.getInventory().setItem(8, itemStack()), 20L);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void click(InventoryClickEvent event) {
        if (event.getSlot() == 8 || event.getHotbarButton() == 8) {
            Player player = (Player) event.getWhoClicked();
            player.performCommand("Main");
            event.setCancelled(true);
        }
    }

//    @EventHandler(priority = EventPriority.LOWEST)
//    public void click(InventoryCloseEvent event) {
//        Player player = (Player) event.getPlayer();
//        if (player != null && event.getInventory().getHolder() == player) {
//            plugin.getLogger().info(event.getInventory().getItem(1).getItemMeta().getDisplayName());
//            if (event.getInventory().getItem(1).getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Menu"))
//                event.getInventory().getItem(1).setAmount(0);
//        }
//    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void moveToAnotherInventory(InventoryMoveItemEvent event) {
        if (event.getItem() != null && event.getItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Menu")) {
            Player player = (Player) event.getInitiator().getViewers().get(0);
            player.performCommand("Main");
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void interact(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getItem() != null && event.getItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Menu")) {
            player.performCommand("Main");
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void drop(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        if (event.getItemDrop().getItemStack() != null && event.getItemDrop().getItemStack().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Menu")) {
            player.sendMessage(ChatColor.RED + "You cannot drop this item!");
            event.setCancelled(true);
        }
    }

    public ItemStack itemStack() {
        ItemStack itemStack = SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjFkZDRmZTRhNDI5YWJkNjY1ZGZkYjNlMjEzMjFkNmVmYTZhNmI1ZTdiOTU2ZGI5YzVkNTljOWVmYWIyNSJ9fX0=");
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.GREEN + "Menu");
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}