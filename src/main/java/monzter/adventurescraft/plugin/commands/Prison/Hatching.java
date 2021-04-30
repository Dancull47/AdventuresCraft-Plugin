package monzter.adventurescraft.plugin.commands.Prison;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.Dependency;
import io.lumine.mythic.lib.api.item.NBTItem;
import me.clip.placeholderapi.PlaceholderAPI;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.event.extras.PetEgg;
import monzter.adventurescraft.plugin.event.extras.StatsDisplay;
import monzter.adventurescraft.plugin.utilities.acUtils;
import net.Indyuce.mmoitems.MMOItems;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Hatching extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;

    public Hatching(AdventuresCraft plugin) {
        this.plugin = plugin;
    }

    @CommandAlias("hatch")
    @CommandCompletion("Common|Uncommon|Rare|Legendary|Exotic|Phoenix|Phoenix2|Dragon|Dragon2")
    public void hatchCommand(Player player, String egg) {
        int petEXPAmount = Integer.valueOf(PlaceholderAPI.setPlaceholders(player, "%ac_Stat_Pet_EXPAmount%"));
        switch (egg.toUpperCase()) {
            case "COMMON":
                if (hasPetEXP(player, egg.toUpperCase(), petEXPAmount)) {
                    if (hasItem(player, "PET_EGG", acUtils.common + "Pet Egg")) {
                        player.sendMessage(ChatColor.GREEN + "You hatched a " + acUtils.common + "Pet Egg" + ChatColor.GREEN + "!");
                        hatch(player, PetEgg.COMMON.expToHatch);
                        acUtils.consoleCommand("droptable " + player.getName() + " EGGCOMMON");
                        break;
                    }
                }
                break;
            case "UNCOMMON":
                if (hasPetEXP(player, egg.toUpperCase(), petEXPAmount)) {
                    if (hasItem(player, "PET_EGG2", acUtils.uncommon + "Pet Egg")) {
                        player.sendMessage(ChatColor.GREEN + "You hatched a " + acUtils.uncommon + "Pet Egg" + ChatColor.GREEN + "!");
                        hatch(player, PetEgg.UNCOMMON.expToHatch);
                        acUtils.giveDropTable(player, "EGG2");
                        break;
                    }
                }
                break;
            case "RARE":
                if (hasPetEXP(player, egg.toUpperCase(), petEXPAmount)) {
                    if (hasItem(player, "PET_EGG3", acUtils.rare + "Pet Egg")) {
                        player.sendMessage(ChatColor.GREEN + "You hatched a " + acUtils.rare + "Pet Egg" + ChatColor.GREEN + "!");
                        hatch(player, PetEgg.RARE.expToHatch);
                        acUtils.giveDropTable(player, "EGG3");
                        break;
                    }
                }
                break;
            case "LEGENDARY":
                if (hasPetEXP(player, egg.toUpperCase(), petEXPAmount)) {
                    if (hasItem(player, "PET_EGG4", acUtils.legendary + "Pet Egg")) {
                        player.sendMessage(ChatColor.GREEN + "You hatched a " + acUtils.legendary + "Pet Egg" + ChatColor.GREEN + "!");
                        hatch(player, PetEgg.LEGENDARY.expToHatch);
                        acUtils.giveDropTable(player, "EGG4");
                        break;
                    }
                }
                break;
            case "EXOTIC":
                if (hasPetEXP(player, egg.toUpperCase(), petEXPAmount)) {
                    if (hasItem(player, "PET_EGG5", acUtils.exotic + "Pet Egg")) {
                        player.sendMessage(ChatColor.GREEN + "You hatched a " + acUtils.exotic + "Pet Egg" + ChatColor.GREEN + "!");
                        hatch(player, PetEgg.EXOTIC.expToHatch);
                        acUtils.giveDropTable(player, "EGG5");
                        break;
                    }
                }
                break;
            case "PHOENIX":
                if (hasPetEXP(player, egg.toUpperCase(), petEXPAmount)) {
                    if (hasItem(player, "PHOENIX_EGG", acUtils.rare + "Phoenix Pet Egg")) {
                        player.sendMessage(ChatColor.GREEN + "You hatched a " + acUtils.rare + "Phoenix Pet Egg" + ChatColor.GREEN + "!");
                        hatch(player, PetEgg.PHOENIX.expToHatch);
                        acUtils.giveDropTable(player, "PHOENIX_EGG");
                        break;
                    }
                }
                break;
            case "PHOENIX2":
                if (hasPetEXP(player, egg.toUpperCase(), petEXPAmount)) {
                    if (hasItem(player, "PHOENIX_EGG2", acUtils.legendary + "Phoenix Pet Egg")) {
                        player.sendMessage(ChatColor.GREEN + "You hatched a " + acUtils.legendary + "Phoenix Pet Egg" + ChatColor.GREEN + "!");
                        hatch(player, PetEgg.PHOENIX2.expToHatch);
                        acUtils.giveDropTable(player, "PHOENIX_EGG2");
                        break;
                    }
                }
                break;
            case "DRAGON":
                if (hasPetEXP(player, egg.toUpperCase(), petEXPAmount)) {
                    if (hasItem(player, "DRAGON_EGG", acUtils.rare + "Dragon Pet Egg")) {
                        player.sendMessage(ChatColor.GREEN + "You hatched a " + acUtils.rare + "Dragon Pet Egg" + ChatColor.GREEN + "!");
                        hatch(player, PetEgg.DRAGON.expToHatch);
                        acUtils.giveDropTable(player, "DRAGON_EGG");
                        break;
                    }
                }
                break;
            case "DRAGON2":
                if (hasPetEXP(player, egg.toUpperCase(), petEXPAmount)) {
                    if (hasItem(player, "DRAGON_EGG2", acUtils.legendary + "Dragon Pet Egg")) {
                        player.sendMessage(ChatColor.GREEN + "You hatched a " + acUtils.legendary + "Dragon Pet Egg" + ChatColor.GREEN + "!");
                        hatch(player, PetEgg.DRAGON2.expToHatch);
                        acUtils.giveDropTable(player, "DRAGON_EGG2");
                        break;
                    }
                }
        }
    }

    public void hatch(Player player, int exp) {
        Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "q point " + player.getName() + " add items.PetExperience " + "-" + exp);
        player.sendMessage(ChatColor.GOLD.toString() + exp + " " + StatsDisplay.PET_EXPERIENCE_AMOUNT.getName() + ChatColor.GREEN + " has been deducted from your account!");
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_TURTLE_EGG_HATCH, 1f, 2f);
    }

    public boolean hasItem(Player player, String itemID, String displayName) {
        for (ItemStack currentItem : player.getInventory().getContents()) {
            NBTItem nbtItem = NBTItem.get(currentItem);
            if (MMOItems.plugin.getID(nbtItem) != null && MMOItems.plugin.getID(nbtItem).equals(itemID)) {
                player.getInventory().removeItem(nbtItem.getItem());
                return true;
            }
        }
        player.sendMessage(ChatColor.RED + "You don't have a " + displayName + ChatColor.RED + " in your inventory!");
        acUtils.soundNo(player, 1);
        return false;
    }

    public boolean hasPetEXP(Player player, String rarity, int petEXP) {
        switch (rarity) {
            case "COMMON":
                if (petEXP >= PetEgg.COMMON.expToHatch) {
                    return true;
                }
                player.sendMessage(ChatColor.RED + "You only have " + ChatColor.GOLD + petEXP + "/" + PetEgg.COMMON.getExpToHatch() + " " +
                        StatsDisplay.PET_EXPERIENCE_AMOUNT.getName() + ChatColor.RED + "!");
                acUtils.soundNo(player, 1);
                break;
            case "UNCOMMON":
                if (petEXP >= PetEgg.UNCOMMON.expToHatch) {
                    return true;
                }
                player.sendMessage(ChatColor.RED + "You only have " + ChatColor.GOLD + petEXP + "/" + PetEgg.UNCOMMON.getExpToHatch() + " " +
                        StatsDisplay.PET_EXPERIENCE_AMOUNT.getName() + ChatColor.RED + "!");
                acUtils.soundNo(player, 1);
                break;
            case "RARE":
                if (petEXP >= PetEgg.RARE.expToHatch) {
                    return true;
                }
                player.sendMessage(ChatColor.RED + "You only have " + ChatColor.GOLD + petEXP + "/" + PetEgg.RARE.getExpToHatch() + " " +
                        StatsDisplay.PET_EXPERIENCE_AMOUNT.getName() + ChatColor.RED + "!");
                acUtils.soundNo(player, 1);
                break;
            case "LEGENDARY":
                if (petEXP >= PetEgg.LEGENDARY.expToHatch) {
                    return true;
                }
                player.sendMessage(ChatColor.RED + "You only have " + ChatColor.GOLD + petEXP + "/" + PetEgg.LEGENDARY.getExpToHatch() + " " +
                        StatsDisplay.PET_EXPERIENCE_AMOUNT.getName() + ChatColor.RED + "!");
                acUtils.soundNo(player, 1);
                break;
            case "EXOTIC":
                if (petEXP >= PetEgg.EXOTIC.expToHatch) {
                    return true;
                }
                player.sendMessage(ChatColor.RED + "You only have " + ChatColor.GOLD + petEXP + "/" + PetEgg.EXOTIC.getExpToHatch() + " " +
                        StatsDisplay.PET_EXPERIENCE_AMOUNT.getName() + ChatColor.RED + "!");
                acUtils.soundNo(player, 1);
                break;
            case "PHOENIX":
                if (petEXP >= PetEgg.PHOENIX.expToHatch) {
                    return true;
                }
                player.sendMessage(ChatColor.RED + "You only have " + ChatColor.GOLD + petEXP + "/" + PetEgg.PHOENIX.getExpToHatch() + " " +
                        StatsDisplay.PET_EXPERIENCE_AMOUNT.getName() + ChatColor.RED + "!");
                acUtils.soundNo(player, 1);
                break;
            case "PHOENIX2":
                if (petEXP >= PetEgg.PHOENIX2.expToHatch) {
                    return true;
                }
                player.sendMessage(ChatColor.RED + "You only have " + ChatColor.GOLD + petEXP + "/" + PetEgg.PHOENIX2.getExpToHatch() + " " +
                        StatsDisplay.PET_EXPERIENCE_AMOUNT.getName() + ChatColor.RED + "!");
                acUtils.soundNo(player, 1);
                break;
            case "DRAGON":
                if (petEXP >= PetEgg.DRAGON.expToHatch) {
                    return true;
                }
                player.sendMessage(ChatColor.RED + "You only have " + ChatColor.GOLD + petEXP + "/" + PetEgg.DRAGON.getExpToHatch() + " " +
                        StatsDisplay.PET_EXPERIENCE_AMOUNT.getName() + ChatColor.RED + "!");
                acUtils.soundNo(player, 1);
                break;
            case "DRAGON2":
                if (petEXP >= PetEgg.DRAGON2.expToHatch) {
                    return true;
                }
                player.sendMessage(ChatColor.RED + "You only have " + ChatColor.GOLD + petEXP + "/" + PetEgg.DRAGON2.getExpToHatch() + " " +
                        StatsDisplay.PET_EXPERIENCE_AMOUNT.getName() + ChatColor.RED + "!");
                acUtils.soundNo(player, 1);
                break;
        }
        return false;
    }

}

