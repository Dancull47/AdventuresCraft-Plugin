package monzter.adventurescraft.plugin.utilities.mmoitems;

import io.lumine.mythic.lib.api.item.NBTItem;
import net.Indyuce.mmoitems.MMOItems;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class MMOItemsHelperImpl {

    public static ItemStack getItem(String type, String ID) {
        return getItem(type, ID, 1);
    }

    public static ItemStack getItem(String type, String ID, int amount) {
        ItemStack itemStack = MMOItems.plugin.getItem(type, ID);
        NBTItem nbtItem = NBTItem.get(itemStack);
        if (!nbtItem.hasType()) {
            System.out.println(ChatColor.RED + "Unable to generate MMOItem " + type + " " + ID);
            System.out.println(ChatColor.RED.toString() + Thread.currentThread().getStackTrace()[2]);
            return MMOItems.plugin.getItem("MATERIAL", "NULL");
        }
        return itemStack.asQuantity(amount);
    }

    public static ItemStack[] getItem(String[] itemStrings) {
        List<ItemStack> itemStackList = new ArrayList<>();
        for (String itemString : itemStrings) {
            String[] itemStringSplit = itemString.split(",");
            if (itemStringSplit.length == 3) {
                String type = itemStringSplit[0];
                String ID = itemStringSplit[1];
                int amount = Integer.valueOf(itemStringSplit[2]);
                itemStackList.add(getItem(type, ID, amount));
            } else {
                Material material = Material.valueOf(itemStringSplit[0]);
                int amount = Integer.valueOf(itemStringSplit[1]);
                itemStackList.add(new ItemStack(material).asQuantity(amount));
            }
        }
        ItemStack[] itemStacks = new ItemStack[itemStackList.size()];
        itemStacks = itemStackList.toArray(itemStacks);
        return itemStacks;
    }
}
