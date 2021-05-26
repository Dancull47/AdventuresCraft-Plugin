package monzter.adventurescraft.plugin.utilities.general;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

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
}
