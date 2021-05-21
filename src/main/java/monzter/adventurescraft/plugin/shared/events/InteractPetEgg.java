package monzter.adventurescraft.plugin.shared.events;

import io.lumine.mythic.lib.api.item.NBTItem;
import me.clip.placeholderapi.PlaceholderAPI;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.enums.PetEggList;
import monzter.adventurescraft.plugin.utilities.text.NumberFormat;
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
    private final NumberFormat numberFormat;

    public InteractPetEgg(AdventuresCraft plugin, NumberFormat numberFormat) {
        this.plugin = plugin;
        this.numberFormat = numberFormat;
    }

    @EventHandler
    private void petEgg(PlayerInteractEvent event) {
        final Player player = event.getPlayer();
        final ItemStack itemStack = event.getItem();
        final NBTItem nbtItem = NBTItem.get(itemStack);
        final String id = MMOItems.plugin.getID(nbtItem);
        if (event.getHand() == EquipmentSlot.HAND && !player.getOpenInventory().equals(null)) {
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
        player.sendMessage(ChatColor.GREEN + "You have " + numberFormat.numberFormat(playerPetEXP) + ChatColor.LIGHT_PURPLE + " Pet EXP," + ChatColor.GREEN
                + " bring this egg to Sarah at" + ChatColor.YELLOW + " /warp Pets " + ChatColor.GREEN + "to hatch it!");
    }

    private void notReadyToHatch(Player player, int playerPetEXP, int amountToHatch) {
        player.sendMessage(ChatColor.GREEN + "You have " + ChatColor.YELLOW + numberFormat.numberFormat(playerPetEXP) + "/" + numberFormat.numberFormat(amountToHatch)
                + ChatColor.LIGHT_PURPLE + " Pet EXP" + ChatColor.GREEN + "!");
    }
}
