package monzter.adventurescraft.plugin.utilities.general;

import monzter.adventurescraft.plugin.utilities.mmoitems.MMOItemsGive;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ChanceCheckImpl implements ChanceCheck {
    private final MMOItemsGive mmoItemsGive;

    public ChanceCheckImpl(MMOItemsGive mmoItemsGive) {
        this.mmoItemsGive = mmoItemsGive;
    }

//    Formula for chance % = chance * 100
//    .01 = 1% & .10 = 10% & 1 = 100%

    @Override
    public boolean chanceCheck(double chance) {
        return Math.random() <= chance;
    }

    @Override
    public boolean chanceCheck(double chance, Player player, int amount, String type, String id, String displayName) {
        if (Math.random() <= chance) {
            mmoItemsGive.giveMMOItem(player, type, id, amount);
            player.sendMessage(ChatColor.YELLOW.toString() + ChatColor.BOLD + "Rewards:");
            player.sendMessage(displayName + " " + ChatColor.GOLD + chance * 10 + "%");
            return true;
        }
        return false;
    }

    @Override
    public boolean chanceCheck(double chance, Player player, int amount, String type, String id, String displayName, boolean silent) {
        if (Math.random() <= chance) {
            mmoItemsGive.giveMMOItem(player, type, id, amount, silent);
            player.sendMessage(ChatColor.YELLOW.toString() + ChatColor.BOLD + "Rewards:");
            player.sendMessage(displayName + " " + ChatColor.GOLD + chance + "%");
            return true;
        }
        return false;
    }
}
