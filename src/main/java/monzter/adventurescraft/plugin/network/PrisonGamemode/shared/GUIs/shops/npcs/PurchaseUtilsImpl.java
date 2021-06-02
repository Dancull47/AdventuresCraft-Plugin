package monzter.adventurescraft.plugin.network.PrisonGamemode.shared.GUIs.shops.npcs;

import monzter.adventurescraft.plugin.utilities.general.FullInventory;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import monzter.adventurescraft.plugin.utilities.text.NumberFormat;
import monzter.adventurescraft.plugin.utilities.vault.Economy;
import org.apache.commons.lang.WordUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PurchaseUtilsImpl implements PurchaseUtils {
    private final Economy economy;
    private final FullInventory fullInventory;
    private final SoundManager soundManager;
    private final NumberFormat numberFormat;

    public PurchaseUtilsImpl(Economy economy, FullInventory fullInventory, SoundManager soundManager, NumberFormat numberFormat) {
        this.economy = economy;
        this.fullInventory = fullInventory;
        this.soundManager = soundManager;
        this.numberFormat = numberFormat;
    }

    @Override
    public void purchase(Player player, Material material, int amount, double price) {
        if (economy.hasMoney(player, price)) {
            if (!fullInventory.fullInventory(player)) {
                economy.takeMoney(player, price * amount);
                player.getInventory().addItem(new ItemStack(material).asQuantity(amount));
                soundManager.soundYes(player, 1);
                player.sendMessage(ChatColor.GREEN + "You purchased " + ChatColor.GOLD + amount + "x " + ChatColor.YELLOW + WordUtils.capitalizeFully(material.toString()) + ChatColor.GREEN + " for " + ChatColor.YELLOW + "⛂ " + numberFormat.numberFormat(price*amount) + ChatColor.GREEN + "!");
            }
        }
    }
}
