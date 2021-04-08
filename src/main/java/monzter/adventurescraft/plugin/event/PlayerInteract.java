package monzter.adventurescraft.plugin.event;

import io.lumine.mythic.lib.api.item.NBTItem;
import me.clip.placeholderapi.PlaceholderAPI;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.event.extras.PetEgg;
import net.Indyuce.mmoitems.MMOItems;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerInteract implements Listener {
    private AdventuresCraft plugin;
    public PlayerInteract(AdventuresCraft plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void petEgg(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack itemStack = event.getItem();
        NBTItem nbtItem = NBTItem.get(itemStack);
        //String tier = nbtItem.getString(ItemStats.TIER.getNBTPath());
        String id = MMOItems.plugin.getID(nbtItem); // Checks if it's an MMOItem and returns its name or null if not
        if (id != null) {
            for (PetEgg egg : PetEgg.values()) {
                if (egg.name.equals(id)) {
                    String playerPetEXP = "%betonquest_items:point.PetExperience.amount%";
                    playerPetEXP = PlaceholderAPI.setPlaceholders(player, playerPetEXP);
                    if (Integer.valueOf(playerPetEXP) >= egg.expToHatch) {
                        readyToHatch(player, Integer.valueOf(playerPetEXP));
                    } else {
                        notReadyToHatch(player, Integer.valueOf(playerPetEXP), egg.expToHatch);
                    }
                }
            }
        }
    }

    public static void readyToHatch(Player player, int playerPetEXP) {
        player.sendMessage(ChatColor.GREEN + "You have " + playerPetEXP + ChatColor.LIGHT_PURPLE + " Pet EXP," + ChatColor.GREEN
                + " bring this egg to Sarah at" + ChatColor.YELLOW + " /warp Pets " + ChatColor.GREEN + "to hatch it!");
    }

    public static void notReadyToHatch(Player player, int playerPetEXP, int amountToHatch) {
        player.sendMessage(ChatColor.GREEN + "You have " + ChatColor.YELLOW + playerPetEXP + "/" + amountToHatch
                + ChatColor.LIGHT_PURPLE + " Pet EXP" + ChatColor.GREEN + "!");
    }
}
