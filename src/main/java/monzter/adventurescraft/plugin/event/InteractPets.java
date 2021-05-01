package monzter.adventurescraft.plugin.event;

import io.lumine.mythic.lib.api.item.NBTItem;
import me.clip.placeholderapi.PlaceholderAPI;
import monzter.adventurescraft.plugin.AdventuresCraft;
import net.Indyuce.mmoitems.ItemStats;
import net.Indyuce.mmoitems.MMOItems;
import org.apache.commons.lang.WordUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.Set;

public class InteractPets implements Listener, CommandExecutor {
    private final AdventuresCraft plugin;
    private final YamlConfiguration petsFile;
    private final String[] tierList = new String[]{"COMMON", "UNCOMMON", "RARE", "LEGENDARY", "EXOTIC", "MYTHICAL", "GODLY", "GODLY_PHOENIX"};

    public InteractPets(AdventuresCraft plugin, YamlConfiguration petsFile) {
        this.plugin = plugin;
        this.petsFile = petsFile;
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
        Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "q point " + player.getName() + " add items.PetAmount " + "1");
        Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "lp user " + player.getName() + " permission set " + String.join(".", "PET", name, tier) + " true");
        Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "mpet pet " + name + " " + player.getName());
        player.sendMessage(ChatColor.GREEN + "You equipped a " + ChatColor.GOLD + WordUtils.capitalizeFully(name) + ChatColor.GREEN + " Pet!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = ((Player) sender).getPlayer();
            if (player.getInventory().firstEmpty() != -1 && args.length > 0) {
                String permission = String.join(".", "PET", args[0], args[1]).toUpperCase();
                if (player.hasPermission(permission)) {
                    player.sendMessage(ChatColor.GREEN + "Your " + WordUtils.capitalizeFully(args[0]) + " pet has been unequipped and put inside your inventory!");
                    Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "lp user " + player.getName() + " permission set " + permission + " false");
                    Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "q point " + player.getName() + " add items.PetAmount " + "-1");
                    player.performCommand("mpet remove");
                    String itemName = "PET_" + args[0];
                    switch (args[1].toUpperCase()) {
                        case "COMMON":
                            Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "mmoitems give PET " + itemName + " " + player.getName() + " 1 0 100 0 s");
                            break;
                        case "UNCOMMON":
                            Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "mmoitems give PET " + itemName + "2 " + player.getName() + " 1 0 100 0 s");
                            break;
                        case "RARE":
                            Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "mmoitems give PET " + itemName + "3 " + player.getName() + " 1 0 100 0 s");
                            break;
                        case "LEGENDARY":
                            Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "mmoitems give PET " + itemName + "4 " + player.getName() + " 1 0 100 0 s");
                            break;
                        case "EXOTIC":
                            Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "mmoitems give PET " + itemName + "5 " + player.getName() + " 1 0 100 0 s");
                            break;
                        case "MYTHICAL":
                            Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "mmoitems give PET " + itemName + "6 " + player.getName() + " 1 0 100 0 s");
                            break;
                        case "GODLY":
                            Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "mmoitems give PET " + itemName + "7 " + player.getName() + " 1 0 100 0 s");
                            break;
                        case "GODLY_PHOENIX":
                            Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "mmoitems give PET " + itemName + "8 " + player.getName() + " 1 0 100 0 s");
                            break;
                    }
                }
            }
        }
        return false;
    }
}
