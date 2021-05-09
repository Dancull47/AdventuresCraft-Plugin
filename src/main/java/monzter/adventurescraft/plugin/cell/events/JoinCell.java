package monzter.adventurescraft.plugin.cell.events;

import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.luckperms.PermissionLP;
import monzter.adventurescraft.plugin.utilities.mmoitems.MMOItemsGive;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import world.bentobox.bentobox.BentoBox;
import world.bentobox.bentobox.database.objects.Island;
import world.bentobox.bentobox.lists.Flags;

import java.util.Optional;

public class JoinCell implements Listener {
    private final AdventuresCraft plugin;
    private final MMOItemsGive mmoItemsGive;
    private final PermissionLP permissionLP;
    private final BentoBox bentoBox;

    public JoinCell(AdventuresCraft plugin, MMOItemsGive mmoItemsGive, PermissionLP permissionLP, BentoBox bentoBox) {
        this.plugin = plugin;
        this.mmoItemsGive = mmoItemsGive;
        this.permissionLP = permissionLP;
        this.bentoBox = bentoBox;
    }

    @EventHandler
    private void onJoin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        }
}