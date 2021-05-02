package monzter.adventurescraft.plugin.utilities.luckperms;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import org.bukkit.entity.Player;

public class PermissionImplLP implements PermissionLP {
    private LuckPerms luckPerms;

    public PermissionImplLP(LuckPerms luckPerms) {
        this.luckPerms = luckPerms;
    }

    @Override
    public void givePermission(Player player, String permission) {
        User user = luckPerms.getPlayerAdapter(Player.class).getUser(player);
        user.data().add(Node.builder(permission).build());
        luckPerms.getUserManager().saveUser(user);
    }

    @Override
    public void takePermission(Player player, String permission) {
        User user = luckPerms.getPlayerAdapter(Player.class).getUser(player);
        user.data().remove(Node.builder(permission).build());
        luckPerms.getUserManager().saveUser(user);
    }
}
