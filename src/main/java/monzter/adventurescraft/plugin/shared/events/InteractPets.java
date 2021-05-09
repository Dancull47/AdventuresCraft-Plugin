package monzter.adventurescraft.plugin.shared.events;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import io.lumine.mythic.lib.api.item.NBTItem;
import me.clip.placeholderapi.PlaceholderAPI;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.beton.BetonPointsManager;
import monzter.adventurescraft.plugin.utilities.luckperms.PermissionLP;
import monzter.adventurescraft.plugin.utilities.mmoitems.MMOItemsGive;
import net.Indyuce.mmoitems.ItemStats;
import net.Indyuce.mmoitems.MMOItems;
import org.apache.commons.lang.WordUtils;
import org.bukkit.Bukkit;
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
    private final MMOItemsGive mmoItemsGive;
    private final PermissionLP permissionLP;
    private final BetonPointsManager betonPointsManager;

    public InteractPets(AdventuresCraft plugin, YamlConfiguration petsFile, MMOItemsGive mmoItemsGive, PermissionLP permissionLP, BetonPointsManager betonPointsManager) {
        this.plugin = plugin;
        this.petsFile = petsFile;
        this.mmoItemsGive = mmoItemsGive;
        this.permissionLP = permissionLP;
        this.betonPointsManager = betonPointsManager;
    }

    @EventHandler
    public void petEgg(PlayerInteractEvent event) {
        final Player player = event.getPlayer();
        final ItemStack itemStack = event.getItem();
        final NBTItem nbtItem = NBTItem.get(itemStack);
        final String tier = nbtItem.getString(ItemStats.TIER.getNBTPath());
        final String id = MMOItems.plugin.getID(nbtItem); // Checks if it's an MMOItem and returns its name or null if not
        if (event.getHand() == EquipmentSlot.HAND) {
            if (id != null) {
                final Set<String> petNames = petsFile.getKeys(false);
                for (String currentPetName : petNames) {
                    if (id.contains("PET_" + currentPetName)) {
                        if (maxPetCheck(player)) {
                            if (!hasPetEquipped(player, currentPetName)) {
                                equipPet(player, currentPetName, tier);
                                player.getInventory().removeItem(event.getItem());
                            }
                        }
                    }
                }
            }
        }
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

    public boolean hasPetEquipped(Player player, String pet) {
        for (String currentTier : tierList) {
            if (player.hasPermission(String.join(".", "PET", pet, currentTier))) {
                player.sendMessage(ChatColor.RED + "You already have a " + ChatColor.GOLD + WordUtils.capitalizeFully(pet) + ChatColor.RED + " equipped!");
                return true;
            }
        }
        return false;
    }


    public void equipPet(Player player, String name, String tier) {
        betonPointsManager.givePoint(player, "items.PetAmount", 1);
        permissionLP.givePermission(player, String.join(".", "PET", name, tier));
        Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "mpet pet " + name + " " + player.getName());
        player.sendMessage(ChatColor.GREEN + "You equipped a " + ChatColor.GOLD + WordUtils.capitalizeFully(name) + ChatColor.GREEN + " Pet!");
    }

    @CommandAlias("petunequip")
    private void voteCommand(Player player, String petName, String tier) {
            if (player.getInventory().firstEmpty() != -1) {
                String permission = String.join(".", "PET", petName, tier).toUpperCase();
                if (player.hasPermission(permission)) {
                    player.sendMessage(ChatColor.GREEN + "Your " + WordUtils.capitalizeFully(petName) + " pet has been unequipped and put inside your inventory!");
                    permissionLP.takePermission(player, permission);
                    betonPointsManager.takePoint(player, "items.PetAmount", 1);
                    player.performCommand("mpet remove");
                    String itemName = "PET_" + petName;
                    switch (tier.toUpperCase()) {
                        case "COMMON":
                            mmoItemsGive.giveMMOItem(player, "PET", itemName, 1, true);
                            break;
                        case "UNCOMMON":
                            mmoItemsGive.giveMMOItem(player, "PET", itemName+"2", 1, true);
                            break;
                        case "RARE":
                            mmoItemsGive.giveMMOItem(player, "PET", itemName+"3", 1, true);
                            break;
                        case "LEGENDARY":
                            mmoItemsGive.giveMMOItem(player, "PET", itemName+"4", 1, true);
                            break;
                        case "EXOTIC":
                            mmoItemsGive.giveMMOItem(player, "PET", itemName+"5", 1, true);
                            break;
                        case "MYTHICAL":
                            mmoItemsGive.giveMMOItem(player, "PET", itemName+"6", 1, true);
                            break;
                        case "GODLY":
                            mmoItemsGive.giveMMOItem(player, "PET", itemName+"7", 1, true);
                            break;
                        case "GODLY_PHOENIX":
                            mmoItemsGive.giveMMOItem(player, "PET", itemName+"8", 1, true);
                            break;
                    }
                }
            }
        }
    }