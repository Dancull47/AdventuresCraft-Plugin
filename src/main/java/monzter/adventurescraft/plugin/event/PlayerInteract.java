package monzter.adventurescraft.plugin.event;

import io.lumine.mythic.lib.api.item.NBTItem;
import me.clip.placeholderapi.PlaceholderAPI;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.event.extras.PetEggs;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerInteract implements Listener {
    private AdventuresCraft plugin;

    private final PetEggs[] EGGS = new PetEggs[]{
            new PetEggs("PET_EGG", 100),
            new PetEggs("PET_EGG2", 500),
            new PetEggs("PET_EGG3", 1000),
            new PetEggs("PET_EGG4", 1500),
            new PetEggs("PET_EGG5", 2000)
    };

    public PlayerInteract(AdventuresCraft plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void petEgg(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack itemStack = event.getItem();
        NBTItem nbtItem = NBTItem.get(itemStack);
        boolean hasType = nbtItem.hasType();
        //String tier = nbtItem.getString(ItemStats.TIER.getNBTPath());
        if (hasType) {
            String id = nbtItem.getString("MMOITEMS_ITEM_ID");
            for (PetEggs egg : EGGS) {
                if (egg.getName().equals(id)) {
                    String playerPetEXP = "%betonquest_items:point.PetExperience.amount%";
                    playerPetEXP = PlaceholderAPI.setPlaceholders(player, playerPetEXP);
                    if (Integer.valueOf(playerPetEXP) >= egg.getExpToHatch()) {
                        readyToHatch(player, Integer.valueOf(playerPetEXP));
                    } else {
                        notReadyToHatch(player, Integer.valueOf(playerPetEXP), egg.getExpToHatch());
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
