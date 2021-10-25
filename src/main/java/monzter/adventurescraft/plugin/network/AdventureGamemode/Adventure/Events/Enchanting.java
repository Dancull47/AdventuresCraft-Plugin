package monzter.adventurescraft.plugin.network.AdventureGamemode.Adventure.Events;

import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.mainMenu.Enchant;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import net.Indyuce.mmoitems.api.event.ItemBuildEvent;
import org.apache.commons.lang.WordUtils;
import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Enchanting implements Listener {
    private final AdventuresCraft plugin;
    private final SoundManager soundManager;


    public Enchanting(AdventuresCraft plugin, SoundManager soundManager) {
        this.plugin = plugin;
        this.soundManager = soundManager;
    }


    //    This doesn't work since MythicEnchants doesn't fire this Event
    @EventHandler
    public void enchant(EnchantItemEvent event) {
        enchantLore(event.getItem());
    }

    @EventHandler
    public void itemGenerate(ItemBuildEvent event) {
        enchantLore(event.getItemStack());
    }

    public static void enchantLore(ItemStack itemStack) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemStack.setItemMeta(itemMeta);
        boolean hasEnchants = hasEnchants(itemMeta.getLore());
        if (hasEnchants)
            clearEnchantLore(itemStack);
        if (itemMeta.getEnchants().size() > 0 && itemMeta.getEnchants().get(Enchantment.DURABILITY) < 1)
            addEnchantLore(itemStack);
    }

    private static void clearEnchantLore(ItemStack itemStack) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        int enchantLine = enchantmentLine(itemMeta.getLore());
        List<String> lore = new ArrayList<>();
        int i = 0;
        for (String originalLore : itemMeta.getLore()) {
            if (i < enchantLine)
                lore.add(originalLore);
            i++;
        }
        lore.add(itemMeta.getLore().get(itemMeta.getLore().size() - 1));
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
    }

    private static void addEnchantLore(ItemStack itemStack) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> lore = new ArrayList<>();
        List<String> enchantmentLine = enchantmentLines(itemStack);

        for (int i = 0; i < itemMeta.getLore().size(); i++) {
            lore.add(itemMeta.getLore().get(i));
            if (i == itemMeta.getLore().size() - 2) {
                lore.add(ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "Enchantments:");
                for (String enchantLine : enchantmentLine)
                    lore.add(enchantLine);
                lore.add("");
            }
        }
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
    }

    private static String enchantmentColor(String enchantment) {
        for (Enchant.Enchantments enchant : Enchant.Enchantments.values())
            if (enchant.name().equalsIgnoreCase(enchantment))
                return enchant.getColor() + WordUtils.capitalizeFully(enchant.name().toUpperCase().replace('_', ' '));
        return "";
    }

    private static List<String> enchantmentLines(ItemStack itemStack) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> enchantmentLine = new ArrayList<>();
        List<String> finalEnchantmentLine = new ArrayList<>();
        String helperLine = "";
        String enchantment;

        int a = 0;
        for (Enchantment enchant : itemMeta.getEnchants().keySet()) {
            enchantment = enchantmentColor(enchant.getName());
            if (a == itemMeta.getEnchants().size() - 1)
                enchantmentLine.add(enchantment + " " + itemMeta.getEnchants().get(enchant));
            else if (itemMeta.getEnchants().size() > 1)
                enchantmentLine.add(enchantment + " " + itemMeta.getEnchants().get(enchant) + ", ");
            a++;
        }

        /*
         *
         * Loops through Enchantment Line
         * If only 1 Enchantment, returns just that Enchantment
         *
         * Else it will then set HelperLine to first Enchantment
         * then add 1 to i
         * This will repeat either 2 MORE TIMES (3 total)
         * OR until Tracker = the amount of Enchantments
         * then it'll add that String (helperLine) to the List
         *
         * */


        int i = 0;
        int tracker = 0;
        for (String line : enchantmentLine) {

            if (enchantmentLine.size() == 1)
                return enchantmentLine;

            if (helperLine.isEmpty())
                helperLine = line;
            else if (i < 2)
                helperLine = helperLine + line;

            if (i == 1 || tracker == enchantmentLine.size() - 1)
                finalEnchantmentLine.add(helperLine);

            if (i == 1) {
                i = 0;
                helperLine = "";
            } else
                i++;
            tracker++;
        }
        return finalEnchantmentLine;
    }


    private static boolean hasEnchants(List<String> lore) {
        for (String itemLore : lore)
            if (itemLore.contains(ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "Enchantments:"))
                return true;
        return false;
    }

    public static int enchantmentLine(List<String> lore) {
        int i = 0;
        for (String itemLore : lore) {
            if (itemLore.contains(ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "Enchantments:"))
                return i;
            i++;
        }
        return 0;
    }
}