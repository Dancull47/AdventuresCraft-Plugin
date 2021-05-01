package monzter.adventurescraft.plugin.utilities;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ChanceCheckImpl implements ChanceCheck {
    private final MMOItemsGiveItem mmoItemsGiveItem;

    public ChanceCheckImpl(MMOItemsGiveItem mmoItemsGiveItem) {
        this.mmoItemsGiveItem = mmoItemsGiveItem;
    }

    @Override
    public boolean chanceCheck(double chance) {
        return Math.random() <= chance;
    }

    @Override
    public boolean chanceCheck(double chance, Player player, int amount, String type, String id, String displayName) {
        if (Math.random() <= chance) {
            mmoItemsGiveItem.giveMMOItem(player, type, id, amount);
            player.sendMessage(ChatColor.YELLOW.toString() + ChatColor.BOLD + "Rewards:");
            player.sendMessage(displayName + " " + ChatColor.GOLD + chance * 10 + "%");
            return true;
        }
        return false;
    }

    @Override
    public boolean chanceCheck(double chance, Player player, int amount, String type, String id, String displayName, boolean silent) {
        if (Math.random() <= chance) {
            mmoItemsGiveItem.giveMMOItem(player, type, id, amount, silent);
            player.sendMessage(ChatColor.YELLOW.toString() + ChatColor.BOLD + "Rewards:");
            player.sendMessage(displayName + " " + ChatColor.GOLD + chance + "%");
            return true;
        }
        return false;
    }
}
