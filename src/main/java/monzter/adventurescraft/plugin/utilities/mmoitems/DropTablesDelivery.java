package monzter.adventurescraft.plugin.utilities.mmoitems;

import org.bukkit.entity.Player;

public interface DropTablesDelivery {
    void giveReward(Player player, String displayName, String rewardType, String rewardName, double chance, int amount);
}
