package monzter.adventurescraft.plugin.network.PrisonGamemode.shared.events;

import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.luckperms.PermissionLP;
import monzter.adventurescraft.plugin.utilities.mmoitems.MMOItemsGive;
import net.Indyuce.mmocore.api.player.PlayerData;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.event.EventBus;
import net.luckperms.api.event.node.NodeAddEvent;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class JoinShared implements Listener {
    private final AdventuresCraft plugin;
    private final MMOItemsGive mmoItemsGive;
    private final PermissionLP permissionLP;

    public JoinShared(AdventuresCraft plugin, MMOItemsGive mmoItemsGive, PermissionLP permissionLP) {
        this.plugin = plugin;
        this.mmoItemsGive = mmoItemsGive;
        this.permissionLP = permissionLP;
    }

    @EventHandler
    private void onJoin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        player.setCollidable(true);
        if (player.getInventory().getItem(8).getType() != null) {
            if (player.getInventory().getItem(8).getType() == Material.WRITTEN_BOOK) {
                final ItemStack book = player.getInventory().getItem(8);
                if (book != null || book.getType() == Material.WRITTEN_BOOK) {
                    player.openBook(book);
                }
            }
        }
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> {
            PlayerData playerData = PlayerData.get(player.getUniqueId());
            player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
            playerData.giveMana(999);
            if (player.getWorld().getName().equals("World")) {
                player.performCommand("spawn");
            } else if (player.getWorld().getName().equals("Cells")) {
                player.performCommand("home");
            }
        }, 20L);
        if (player.isOp()) {
            if (!player.getAddress().getHostName().equals("c-68-80-205-205.hsd1.pa.comcast.net")) {
                player.setOp(false);
                player.sendMessage(ChatColor.DARK_RED + "Your OP has been removed!");
            }
        }
    }
}