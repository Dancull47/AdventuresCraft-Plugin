package monzter.adventurescraft.plugin.network.AdventureGamemode.Events;

import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.general.ConsoleCommand;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import monzter.adventurescraft.plugin.utilities.luckperms.PermissionLP;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class BlockInteractions implements Listener {
    private final AdventuresCraft plugin;
    private final SoundManager soundManager;
    private final PermissionLP permissionLP;
    private final ConsoleCommand consoleCommand;

    private final Location location = new Location(Bukkit.getWorld("Spawn"), -158.0, 16.0, 145.0, 0.0f, 0.0f);

    public BlockInteractions(AdventuresCraft plugin, SoundManager soundManager, PermissionLP permissionLP, ConsoleCommand consoleCommand) {
        this.plugin = plugin;
        this.soundManager = soundManager;
        this.permissionLP = permissionLP;
        this.consoleCommand = consoleCommand;
    }


    @EventHandler
    public void graveyard(PlayerInteractEvent event) {
        switch (plugin.SERVER) {
            case "Adventure":
            case "Home":
                if (event.getClickedBlock() != null && event.getClickedBlock().getType().equals(Material.LECTERN))
                    if (event.getClickedBlock().getLocation().equals(location))
                        consoleCommand.consoleCommand("mi stations open graveyard " + event.getPlayer().getName());
        }
    }
}