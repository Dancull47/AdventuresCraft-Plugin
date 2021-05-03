package monzter.adventurescraft.plugin.event;

import io.lumine.mythic.lib.api.item.NBTItem;
import me.clip.placeholderapi.PlaceholderAPI;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.event.extras.PetEggList;
import net.Indyuce.mmoitems.MMOItems;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class InteractPetEgg implements Listener {
    private final AdventuresCraft plugin;

    public InteractPetEgg(AdventuresCraft plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    private void petEgg(PlayerInteractEvent event) {
        final Player player = event.getPlayer();
        final ItemStack itemStack = event.getItem();
        final NBTItem nbtItem = NBTItem.get(itemStack);
        //String tier = nbtItem.getString(ItemStats.TIER.getNBTPath());
        final String id = MMOItems.plugin.getID(nbtItem); // Checks if it's an MMOItem and returns its name or null if not
        if (event.getHand() == EquipmentSlot.HAND) {
            if (id != null) {
                for (PetEggList egg : PetEggList.values()) {
                    if (egg.name.equals(id)) {
                        final String playerPetEXP = PlaceholderAPI.setPlaceholders(player, "%betonquest_items:point.PetExperience.amount%");
                        if (Integer.valueOf(playerPetEXP) >= egg.expToHatch) {
                            readyToHatch(player, Integer.valueOf(playerPetEXP));
                        } else {
                            notReadyToHatch(player, Integer.valueOf(playerPetEXP), egg.expToHatch);
                        }
                    }
                }
            }
        }
    }

    private void readyToHatch(Player player, int playerPetEXP) {
        player.sendMessage(ChatColor.GREEN + "You have " + playerPetEXP + ChatColor.LIGHT_PURPLE + " Pet EXP," + ChatColor.GREEN
                + " bring this egg to Sarah at" + ChatColor.YELLOW + " /warp Pets " + ChatColor.GREEN + "to hatch it!");
    }

    private void notReadyToHatch(Player player, int playerPetEXP, int amountToHatch) {
        player.sendMessage(ChatColor.GREEN + "You have " + ChatColor.YELLOW + playerPetEXP + "/" + amountToHatch
                + ChatColor.LIGHT_PURPLE + " Pet EXP" + ChatColor.GREEN + "!");
    }
}
