package monzter.adventurescraft.plugin.utilities.luckperms;

import monzter.adventurescraft.plugin.AdventuresCraft;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.context.DefaultContextKeys;
import net.luckperms.api.event.EventBus;
import net.luckperms.api.event.node.NodeAddEvent;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.concurrent.TimeUnit;

public class PermissionImplLP implements PermissionLP {
    private LuckPerms luckPerms;
    private AdventuresCraft plugin;
    private final String CONTEXT;

    public PermissionImplLP(LuckPerms luckPerms, AdventuresCraft plugin) {
        this.luckPerms = luckPerms;
        this.plugin = plugin;
        EventBus eventBus = LuckPermsProvider.get().getEventBus();
        eventBus.subscribe(plugin, NodeAddEvent.class, this::onNodeAdd);
        CONTEXT = plugin.getConfig().getString("Context").toLowerCase();
    }


    @Override
    public void givePermission(Player player, String permission) {
        givePermission(player, permission, CONTEXT);
    }

    @Override
    public void givePermission(Player player, String permission, String context) {
        User user = luckPerms.getPlayerAdapter(Player.class).getUser(player);
        this.plugin.getServer().getScheduler().runTask(this.plugin, () -> {
            user.data().add(Node.builder(permission).withContext(DefaultContextKeys.SERVER_KEY, context).value(true).build());
            luckPerms.getUserManager().saveUser(user);
            plugin.getLogger().info(permission + ChatColor.GREEN + " has been saved for " + ChatColor.YELLOW + player.getName() + ChatColor.GREEN + "!");
        });
    }

    @Override
    public void giveTempPermission(Player player, String permission, int duration, String unit) {
        switch (unit) {
            case "m":
            case "min":
            case "minute":
            case "minutes":
                giveTempPermission(player, permission, CONTEXT, duration, TimeUnit.MINUTES);
                break;
            case "h":
            case "hr":
            case "hour":
            case "hours":
                giveTempPermission(player, permission, CONTEXT, duration, TimeUnit.HOURS);
                break;
        }
    }

    @Override
    public void giveTempPermission(Player player, String permission, String context, int duration, TimeUnit unit) {
        User user = luckPerms.getPlayerAdapter(Player.class).getUser(player);
        this.plugin.getServer().getScheduler().runTask(this.plugin, () -> {
            user.data().add(Node.builder(permission).expiry(duration, unit).withContext(DefaultContextKeys.SERVER_KEY, context).value(true).build());
            luckPerms.getUserManager().saveUser(user);
            plugin.getLogger().info(permission + ChatColor.GREEN + " has been saved for " + ChatColor.YELLOW + player.getName() + ChatColor.GREEN + "!");
        });
    }

    @Override
    public void takePermission(Player player, String permission) {
        takePermission(player, permission, CONTEXT);
    }

    @Override
    public void takePermission(Player player, String permission, String context) {
        User user = luckPerms.getPlayerAdapter(Player.class).getUser(player);
        this.plugin.getServer().getScheduler().runTask(this.plugin, () -> {
            user.data().add(Node.builder(permission).withContext(DefaultContextKeys.SERVER_KEY, context).value(false).build());
            luckPerms.getUserManager().saveUser(user);
            plugin.getLogger().info(permission + ChatColor.GREEN + " has been removed for " + ChatColor.YELLOW + player.getName() + ChatColor.GREEN + "!");
        });
    }

    public void onNodeAdd(NodeAddEvent event) {
        User target = (User) event.getTarget();
        Node node = event.getNode();

        this.plugin.getServer().getScheduler().runTask(this.plugin, () -> {
            if (node.getContexts().isEmpty()) {
                target.data().remove(node);
                if (node.getValue())
                    givePermission(Bukkit.getPlayer(target.getUniqueId()), node.getKey(), "prison");
                else
                    takePermission(Bukkit.getPlayer(target.getUniqueId()), node.getKey(), "prison");
            }
        });
    }

}
