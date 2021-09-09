package monzter.adventurescraft.plugin.network.PrisonGamemode.shared.events;

import co.aikar.commands.BaseCommand;
import com.kirelcodes.miniaturepets.loader.PetLoader;
import com.kirelcodes.miniaturepets.pets.PetContainer;
import io.lumine.mythic.lib.api.item.NBTItem;
import me.clip.placeholderapi.PlaceholderAPI;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.beton.BetonPointsManager;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import monzter.adventurescraft.plugin.utilities.luckperms.PermissionLP;
import net.Indyuce.mmoitems.ItemStats;
import net.Indyuce.mmoitems.MMOItems;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.Set;

public class InteractPets extends BaseCommand implements Listener {
    private final AdventuresCraft plugin;
    private final YamlConfiguration petsFile;
    private final String[] tierList = new String[]{"COMMON", "UNCOMMON", "RARE", "LEGENDARY", "EXOTIC", "MYTHICAL", "GODLY", "GODLY_PHOENIX"};
    private final PermissionLP permissionLP;
    private final BetonPointsManager betonPointsManager;
    private final SoundManager soundManager;


    public InteractPets(AdventuresCraft plugin, YamlConfiguration petsFile, PermissionLP permissionLP, BetonPointsManager betonPointsManager, SoundManager soundManager) {
        this.plugin = plugin;
        this.petsFile = petsFile;
        this.permissionLP = permissionLP;
        this.betonPointsManager = betonPointsManager;
        this.soundManager = soundManager;
    }

    @EventHandler
    public void petEgg(PlayerInteractEvent event) {
        final Player player = event.getPlayer();
        final ItemStack itemStack = event.getItem();
        final NBTItem nbtItem = NBTItem.get(itemStack);
        final String tier = nbtItem.getString(ItemStats.TIER.getNBTPath());
        final String id = MMOItems.getID(nbtItem);
        if (event.getHand() == EquipmentSlot.HAND) {
            if (id != null) {
                final Set<String> petNames = petsFile.getKeys(false);
                for (String currentPetName : petNames) {
                    if (id.contains("PET_" + currentPetName)) {
                        if (maxPetCheck(player)) {
                            if (!hasPetEquipped(player, currentPetName, itemStack.getItemMeta().getDisplayName())) {
                                equipMPet(player, id);
                                equipPet(player, currentPetName, tier, itemStack.getItemMeta().getDisplayName());
                                player.getInventory().removeItem(event.getItem());
                            }
                        }
                    }
                }
            }
        }
    }

    private void equipMPet(Player player, String id) {
        id = id.replace("PET_", "");
        switch (id) {
            case "PHOENIX3":
            case "PHOENIX4":
                id = "BabyPhoenix";
                break;
            case "PHOENIX5":
                id = "JuvenilePhoenix";
                break;
            case "PHOENIX6":
                id = "Phoenix";
                break;
            case "PHOENIX7":
                id = "KingPhoenix";
                break;
            case "PHOENIX8":
                id = "WaterPhoenix";
                break;
            case "DRAGON3":
            case "DRAGON4":
                id = "BabyDragon";
                break;
            case "DRAGON5":
            case "DRAGON6":
                id = "JuvenileDragon";
                break;
            case "DRAGON7":
                id = "Dragon";
                break;
            case "GORILLA":
            case "GORILLA2":
            case "GORILLA3":
            case "GORILLA4":
            case "GORILLA5":
            case "GORILLA6":
            case "GORILLA7":
                id = "GORILLA";
                break;
            case "TURTLE":
            case "TURTLE2":
            case "TURTLE3":
            case "TURTLE4":
            case "TURTLE5":
            case "TURTLE6":
            case "TURTLE7":
                id = "TURTLE";
                break;
            case "ELEPHANT":
            case "ELEPHANT2":
            case "ELEPHANT3":
            case "ELEPHANT4":
            case "ELEPHANT5":
            case "ELEPHANT6":
            case "ELEPHANT7":
                id = "ELEPHANT";
                break;
            case "GIRAFFE":
            case "GIRAFFE2":
            case "GIRAFFE3":
            case "GIRAFFE4":
            case "GIRAFFE5":
            case "GIRAFFE6":
            case "GIRAFFE7":
                id = "GIRAFFE";
                break;
            case "PENGUIN":
            case "PENGUIN2":
            case "PENGUIN3":
            case "PENGUIN4":
            case "PENGUIN5":
            case "PENGUIN6":
            case "PENGUIN7":
                id = "PENGUIN";
                break;
            case "FROG":
            case "FROG2":
            case "FROG3":
            case "FROG4":
            case "FROG5":
            case "FROG6":
            case "FROG7":
                id = "FROG";
                break;
            case "LION":
            case "LION2":
            case "LION3":
            case "LION4":
            case "LION5":
            case "LION6":
            case "LION7":
                id = "LION";
                break;
            case "RED_PANDA":
            case "RED_PANDA2":
            case "RED_PANDA3":
            case "RED_PANDA4":
            case "RED_PANDA5":
            case "RED_PANDA6":
            case "RED_PANDA7":
                id = "REDPANDA";
                break;
        }
        PetContainer petContainer = PetLoader.getPet(id.toLowerCase());
        petContainer.spawnPet(player);
        soundManager.soundYes(player, 2);
    }

    public boolean maxPetCheck(Player player) {
        String petAmount = PlaceholderAPI.setPlaceholders(player, "%ac_Stat_PetAmount%");
        String maxPetAmount = PlaceholderAPI.setPlaceholders(player, "%ac_Stat_MaxPetAmount%");
        if (Integer.valueOf(petAmount) >= Integer.valueOf(maxPetAmount)) {
            player.sendMessage(ChatColor.RED + "You can only have " + ChatColor.GOLD + petAmount + ChatColor.RED + "/" + ChatColor.GOLD + maxPetAmount + ChatColor.RED + " Pets equipped at a time!");
            return false;
        } else {
            return true;
        }
    }

    public boolean hasPetEquipped(Player player, String pet, String petDisplayName) {
        for (String currentTier : tierList) {
            if (player.hasPermission(String.join(".", "PET", pet, currentTier))) {
                player.sendMessage(ChatColor.RED + "You already have a " + ChatColor.GOLD + petDisplayName + ChatColor.RED + " equipped!");
                return true;
            }
        }
        return false;
    }


    public void equipPet(Player player, String name, String tier, String displayName) {
        betonPointsManager.givePoint(player, "items.PetAmount", 1);
        permissionLP.givePermission(player, String.join(".", "PET", name, tier));
        player.sendMessage(ChatColor.GREEN + "You equipped a " + displayName + ChatColor.GREEN + " Pet!");
    }
}