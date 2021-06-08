package monzter.adventurescraft.plugin.utilities.general;

import io.lumine.mythic.lib.api.item.NBTItem;
import monzter.adventurescraft.plugin.utilities.text.NumberFormat;
import monzter.adventurescraft.plugin.utilities.vault.Economy;
import net.Indyuce.mmoitems.MMOItems;
import org.apache.commons.lang.WordUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PurchaseUtilsImpl implements PurchaseUtils {
    private final Economy economy;
    private final FullInventory fullInventory;
    private final SoundManager soundManager;
    private final NumberFormat numberFormat;
    private final MMOItems mmoItems;


    public PurchaseUtilsImpl(Economy economy, FullInventory fullInventory, SoundManager soundManager, NumberFormat numberFormat, MMOItems mmoItems) {
        this.economy = economy;
        this.fullInventory = fullInventory;
        this.soundManager = soundManager;
        this.numberFormat = numberFormat;
        this.mmoItems = mmoItems;
    }

    @Override
    public void purchase(Player player, ItemStack itemStack, int amount, double price) {
        if (economy.hasMoney(player, price)) {
            if (!fullInventory.fullInventory(player)) {
                economy.takeMoney(player, price * amount);
                String id = mmoItems.getID(NBTItem.get(itemStack));
                if (id == null) {
                    player.getInventory().addItem(new ItemStack(itemStack.getType(), amount));
                    player.sendMessage(ChatColor.GREEN + "You purchased " + ChatColor.GOLD + amount + "x " + ChatColor.YELLOW + WordUtils.capitalizeFully(itemStack.getType().toString().replace('_', ' ')) + ChatColor.GREEN + " for " + ChatColor.YELLOW + "⛂ " + numberFormat.numberFormat(price * amount) + ChatColor.GREEN + "!");
                } else {
                    player.getInventory().addItem(mmoItems.getItem(mmoItems.getType(NBTItem.get(itemStack)), id).asQuantity(amount));
                    player.sendMessage(ChatColor.GREEN + "You purchased " + ChatColor.GOLD + amount + "x " + ChatColor.YELLOW + mmoItems.getItem(mmoItems.getType(NBTItem.get(itemStack)), mmoItems.getID(NBTItem.get(itemStack))).getItemMeta().getDisplayName() + ChatColor.GREEN + " for " + ChatColor.YELLOW + "⛂ " + numberFormat.numberFormat(price * amount) + ChatColor.GREEN + "!");
                }
                soundManager.soundYes(player, 1);
            }
        }
    }
}
