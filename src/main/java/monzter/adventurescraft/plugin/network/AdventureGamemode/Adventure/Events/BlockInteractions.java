package monzter.adventurescraft.plugin.network.AdventureGamemode.Adventure.Events;

import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.general.ConsoleCommand;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import monzter.adventurescraft.plugin.utilities.luckperms.PermissionLP;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class BlockInteractions implements Listener {
    private final AdventuresCraft plugin;
    private final SoundManager soundManager;
    private final PermissionLP permissionLP;
    private final ConsoleCommand consoleCommand;

    private final Location location = new Location(Bukkit.getWorld("Spawn"), -158.0, 16.0, 145.0, 0.0f, 0.0f);
    private final Location woolCleanerLocation = new Location(Bukkit.getWorld("Spawn"), -541.0, 25.0, -542.0, 0.0f, 0.0f);

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
                        event.getPlayer().performCommand("AreaShop Graveyard");
        }
    }

    @EventHandler
    public void woolCleaner(PlayerInteractEvent event) {
        switch (plugin.SERVER) {
            case "Adventure":
                if (event.getClickedBlock() != null && event.getClickedBlock().getType().equals(Material.CAULDRON))
                    if (event.getClickedBlock().getLocation().equals(woolCleanerLocation) && event.getPlayer().getInventory().getItemInMainHand().getType() != null) {
                        ItemStack mainHand = event.getPlayer().getInventory().getItemInMainHand();
                        Player player = event.getPlayer();
                        if (Tag.WOOL.isTagged(mainHand.getType()) && mainHand.getType() != Material.WHITE_WOOL) {
                            player.getInventory().setItemInMainHand(new ItemStack(Material.WHITE_WOOL, mainHand.getAmount()));
                            player.sendMessage(ChatColor.GREEN + "Your wool has been cleaned!");
                            soundManager.playSound(player, Sound.ITEM_BUCKET_EMPTY, 1, 1);
                        } else if (mainHand.getType() == Material.WHITE_WOOL) {
                            player.sendMessage(ChatColor.RED + "Your wool is already cleaned!");
                            soundManager.soundNo(player, 1);
                        }

                    }
        }
    }

    @EventHandler
    public void loomControl(PlayerInteractEvent event) {
        switch (plugin.SERVER) {
            case "Adventure":
            case "Home":
                if (event.getClickedBlock() != null && event.getClickedBlock().getType().equals(Material.LOOM))
                    if (!event.getPlayer().hasPermission("LOOM.UNLOCKED")) {
                        event.getPlayer().sendMessage(ChatColor.RED + "You must help " + ChatColor.GREEN + "Sandrah " + ChatColor.RED + "within the " + ChatColor.GREEN + "Estate " +
                                ChatColor.RED + "to use a " + ChatColor.GOLD + "Loom" + ChatColor.GREEN + "!");
                        soundManager.soundNo(event.getPlayer(), 1);
                        event.setCancelled(true);
                    }
        }
    }
}