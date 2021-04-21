package monzter.adventurescraft.plugin.commands;

import io.lumine.mythic.lib.api.item.NBTItem;
import me.clip.placeholderapi.PlaceholderAPI;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.event.dropTables.CommonPetEgg;
import monzter.adventurescraft.plugin.event.extras.Pet;
import monzter.adventurescraft.plugin.event.extras.PetEgg;
import monzter.adventurescraft.plugin.event.extras.StatsDisplay;
import monzter.adventurescraft.plugin.utilities.acUtils;
import net.Indyuce.mmoitems.ItemStats;
import net.Indyuce.mmoitems.MMOItems;
import net.Indyuce.mmoitems.api.item.mmoitem.MMOItem;
import net.Indyuce.mmoitems.manager.ItemManager;
import org.apache.commons.lang.WordUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Hatch implements CommandExecutor {
    private final AdventuresCraft plugin;

    public Hatch(AdventuresCraft plugin) {
        this.plugin = plugin;
    }
/*
*
*   Unable to compare itemStack, convert to ItemInHand, similar to equipping pets
*
*/
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = ((Player) sender).getPlayer();
            if (args.length < 1) {
                player.sendMessage(ChatColor.RED + "/Hatch <Egg>");
                return true;
            } else {
                int petEXPAmount = Integer.valueOf(PlaceholderAPI.setPlaceholders(player, "%ac_Stat_Pet_EXPAmount%"));
                switch (args[0].toUpperCase()) {
                    case "COMMON":
                        ItemStack item = MMOItems.plugin.getItem(MMOItems.plugin.getTypes().get("PET"), "PET_EGG");
                        player.getInventory().addItem(MMOItems.plugin.getItem(MMOItems.plugin.getTypes().get("PET"), "PET_EGG"));
                        if (player.getInventory().contains(MMOItems.plugin.getItem(MMOItems.plugin.getTypes().get("PET"), "PET_EGG"))){
                            if (hasPetEXP(player, args[0], petEXPAmount)){
                                player.sendMessage(ChatColor.GREEN + "You hatched a " + ChatColor.WHITE + "Pet Egg" + ChatColor.GREEN +"!");
                                player.getInventory().removeItem(item);
//                                execute console command "/mm items give %player% EGG1 1"
//                                execute console command "/q point %player% add items.PetExperience -%{_C}%"
                            }
                        }
                }
            }
        }
        return false;
    }

    public boolean hasItem(Player player, ItemStack item, String displayName){
        if (player.getInventory().contains(MMOItems.plugin.getItem(MMOItems.plugin.getTypes().get("PET"), "PET_EGG"))){
            return true;
        } else {
            player.sendMessage(ChatColor.RED + "You don't have a " + displayName + ChatColor.RED + " in your inventory!");
            return false;
        }
    }
    public boolean hasPetEXP(Player player, String rarity, int petEXP){
        switch (rarity){
            case "COMMON":
                if (petEXP >= PetEgg.COMMON.expToHatch){
                    return true;
                }
                player.sendMessage(ChatColor.RED + "You only have " + ChatColor.GOLD + petEXP + "/" + PetEgg.COMMON.getExpToHatch() + " " +
                        StatsDisplay.PET_EXPERIENCE_AMOUNT + ChatColor.RED + "!");
                return false;
            case "UNCOMMON":
                if (petEXP >= PetEgg.UNCOMMON.expToHatch){
                    return true;
                }
                return false;
            case "RARE":
                if (petEXP >= PetEgg.RARE.expToHatch){
                    return true;
                }
                return false;
            case "LEGENDARY":
                if (petEXP >= PetEgg.LEGENDARY.expToHatch){
                    return true;
                }
                return false;
            case "EXOTIC":
                if (petEXP >= PetEgg.EXOTIC.expToHatch){
                    return true;
                }
                return false;
            case "PHOENIX":
                if (petEXP >= PetEgg.PHOENIX.expToHatch){
                    return true;
                }
                return false;
            case "PHOENIX2":
                if (petEXP >= PetEgg.PHOENIX2.expToHatch){
                    return true;
                }
                return false;
            case "DRAGON":
                if (petEXP >= PetEgg.DRAGON.expToHatch){
                    return true;
                }
                return false;
            case "DRAGON2":
                if (petEXP >= PetEgg.DRAGON2.expToHatch){
                    return true;
                }
                return false;
        }
        return false;
    }
}

