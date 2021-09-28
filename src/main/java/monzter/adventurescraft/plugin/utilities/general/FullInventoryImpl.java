package monzter.adventurescraft.plugin.utilities.general;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class FullInventoryImpl implements FullInventory {
    private final SoundManager soundManager;

    public FullInventoryImpl(SoundManager soundManager) {
        this.soundManager = soundManager;
    }


    @Override
    public boolean fullInventory(Player player) {
        if (player.getInventory().firstEmpty() == -1) {
            player.sendMessage(ChatColor.RED + "You're inventory is full! Try again once you have at least one available slot!");
            soundManager.soundNo(player, 1);
            return true;
        }
        return false;
    }

    @Override
    public boolean emptySlots(Player player, int slots) {
        if (emptySlots(player) >= slots)
            return true;
        player.sendMessage(ChatColor.RED + "You only have " + ChatColor.GOLD + emptySlots(player) + ChatColor.RED + "/" + ChatColor.GOLD + slots + ChatColor.RED + " available!");
        soundManager.soundNo(player, 1);
        return false;
    }

    @Override
    public int emptySlots(Player player) {
        int i = 0;
        for (ItemStack itemStack : player.getInventory().getStorageContents()) {
            if (itemStack == null)
                i++;
        }
        return i;
    }
}
