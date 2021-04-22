package monzter.adventurescraft.plugin.event;

import monzter.adventurescraft.plugin.AdventuresCraft;
import net.Indyuce.mmocore.api.player.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class Join implements Listener {
    private AdventuresCraft plugin;

    public Join(AdventuresCraft plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.setCollidable(true);
        if (player.getInventory().getItem(8).getType() == Material.WRITTEN_BOOK){
            ItemStack book = player.getInventory().getItem(8);
            if (book != null || book.getType() == Material.WRITTEN_BOOK) {
                player.openBook(book);
            }
        }
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> {
            PlayerData playerData = PlayerData.get(player.getUniqueId());
            player.setHealth(player.getMaxHealth());
            playerData.giveMana(999);
            if (player.getWorld().getName().equals("World")) {
                player.performCommand("spawn");
            } else if (player.getWorld().getName().equals("Homes")) {
                player.performCommand("home");
            }
        }, 20L);
        if (player.isOp()) { // OP Check
            if (!player.getAddress().getHostName().equals("c-68-80-205-205.hsd1.pa.comcast.net")) {
                player.setOp(false);
                player.sendMessage(ChatColor.DARK_RED + "Your OP has been removed!");
            }
        }
    }
}