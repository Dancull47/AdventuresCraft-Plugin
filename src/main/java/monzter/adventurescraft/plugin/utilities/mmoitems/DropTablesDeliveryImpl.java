package monzter.adventurescraft.plugin.utilities.mmoitems;

import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class DropTablesDeliveryImpl implements DropTablesDelivery {
    private final MMOItemsGive mmoItemsGive;
    private final SoundManager soundManager;

    public DropTablesDeliveryImpl(MMOItemsGive mmoItemsGive, SoundManager soundManager) {
        this.mmoItemsGive = mmoItemsGive;
        this.soundManager = soundManager;
    }

    @Override
    public void giveReward(Player player, ItemStack itemStack, double chance) {
        final double multipliedChance = chance * 100;
        mmoItemsGive.giveMMOItem(player, itemStack);
        String displayName = itemStack.getItemMeta().getDisplayName();
        if (multipliedChance < 50 && multipliedChance >= 25) {
            player.sendMessage(displayName + " " + ChatColor.BLUE + ChatColor.BOLD + multipliedChance + "% RARE!");
            soundManager.playSound(player, Sound.ENTITY_WITCH_CELEBRATE, 1, 1);
        } else if (multipliedChance < 25 && multipliedChance >= 2) {
            player.sendMessage(displayName + " " + ChatColor.YELLOW + ChatColor.BOLD + multipliedChance + "% VERY RARE!");
            soundManager.playSound(player, Sound.ENTITY_VINDICATOR_CELEBRATE, 1, 1);
        } else if (multipliedChance < 2) {
            player.sendMessage(displayName + " " + ChatColor.DARK_RED + ChatColor.BOLD + multipliedChance + "% INSANE!!!");
            player.sendTitle(ChatColor.RED.toString() + ChatColor.BOLD + "INSANE!!!", displayName, 20, 100, 20);
            soundManager.playSound(player, Sound.ENTITY_RAVAGER_CELEBRATE, 1, 1);
            Bukkit.broadcastMessage(ChatColor.GOLD + player.getName() + ChatColor.GREEN + " just found a " + displayName + " " + ChatColor.DARK_RED + ChatColor.BOLD + multipliedChance + "% INSANE!!!");
        } else {
            player.sendMessage(displayName + " " + ChatColor.GREEN + multipliedChance + "%");
        }
    }
}
