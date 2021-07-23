package monzter.adventurescraft.plugin.network.AdventureGamemode.Adventure.Events;

import io.lumine.mythic.lib.api.item.NBTItem;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.general.ConsoleCommand;
import monzter.adventurescraft.plugin.utilities.general.FullInventory;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import monzter.adventurescraft.plugin.utilities.luckperms.PermissionLP;
import net.Indyuce.mmoitems.MMOItems;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class PlayerInteractLootboxes implements Listener {
    private final AdventuresCraft plugin;
    private final SoundManager soundManager;
    private final PermissionLP permissionLP;
    private final ConsoleCommand consoleCommand;
    private final FullInventory fullInventory;


    public PlayerInteractLootboxes(AdventuresCraft plugin, SoundManager soundManager, PermissionLP permissionLP, ConsoleCommand consoleCommand, FullInventory fullInventory) {
        this.plugin = plugin;
        this.soundManager = soundManager;
        this.permissionLP = permissionLP;
        this.consoleCommand = consoleCommand;
        this.fullInventory = fullInventory;
    }


    @EventHandler(priority = EventPriority.LOWEST) // Makes your method Highest priority
    private void petEgg(PlayerInteractEvent event) {
        final Player player = event.getPlayer();
        final ItemStack itemStack = event.getItem();
        final NBTItem nbtItem = NBTItem.get(itemStack);
        final String id = MMOItems.plugin.getID(nbtItem);
        if (event.getHand() == EquipmentSlot.HAND) {
            if (id != null)
                switch (id) {
                    case "BORGS_BOX5":
                    case "MAGICAL_BOX5":
                    case "PROFESSION_BOOSTER_BOX5":
                    case "UNDEAD_BOX5":
                    case "HELL_BOX5":
                    case "ENCHANTED_BOX":
                    case "ENCHANTED_BOX2":
                    case "ENCHANTED_BOX3":
                    case "ENCHANTED_BOX4":
                        if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK)
                            player.performCommand("dropTableViewer " + id);
                        else {
                            if (!fullInventory.fullInventory(player)) {
                                player.getInventory().setItemInMainHand(player.getInventory().getItemInMainHand().asQuantity(player.getInventory().getItemInMainHand().getAmount() - 1));
                                consoleCommand.consoleCommand("dropTable " + player.getName() + " " + id + " " + 1);
                            } else
                                player.getInventory().setItemInMainHand(player.getInventory().getItemInMainHand().asQuantity(player.getInventory().getItemInMainHand().getAmount()));
                        }
                        break;
                }
        }
    }
}