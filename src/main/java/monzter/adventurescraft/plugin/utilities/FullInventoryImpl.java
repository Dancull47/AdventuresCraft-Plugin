package monzter.adventurescraft.plugin.utilities;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class FullInventoryImpl implements FullInventory {

    @Override
    public boolean fullInventory(Player player) {
        if (player.getInventory().firstEmpty() == -1) {
            player.sendMessage(ChatColor.RED + "You're inventory is full! Try again once you have at least one available slot!");
            return true;
        }
        return false;
    }
}
