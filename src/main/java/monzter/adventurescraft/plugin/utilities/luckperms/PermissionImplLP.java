package monzter.adventurescraft.plugin.utilities.luckperms;

import monzter.adventurescraft.plugin.AdventuresCraft;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class PermissionImplLP implements PermissionLP {
    private LuckPerms luckPerms;
    private AdventuresCraft plugin;

    public PermissionImplLP(LuckPerms luckPerms, AdventuresCraft plugin) {
        this.luckPerms = luckPerms;
        this.plugin = plugin;
    }

    @Override
    public void givePermission(Player player, String permission) {
        User user = luckPerms.getPlayerAdapter(Player.class).getUser(player);
        user.data().add(Node.builder(permission).build());
        luckPerms.getUserManager().saveUser(user);
        plugin.getLogger().info(permission + ChatColor.GREEN + " has been saved for " + ChatColor.YELLOW + player.getName() + ChatColor.GREEN + "!");
    }

    @Override
    public void takePermission(Player player, String permission) {
        User user = luckPerms.getPlayerAdapter(Player.class).getUser(player);
        user.data().remove(Node.builder(permission).build());
        luckPerms.getUserManager().saveUser(user);
        plugin.getLogger().info(permission + ChatColor.GREEN + " has been removed for " + ChatColor.YELLOW + player.getName() + ChatColor.GREEN + "!");
    }
}
