package monzter.adventurescraft.plugin.network.Shared.Events;

import net.Indyuce.mmoitems.MMOItems;
import net.Indyuce.mmoitems.api.ItemTier;

public class DropTier {
    private final ItemTier tier;
    private final String permission;
    private final int max;

    public DropTier(String tier, String permission, int max) {
        this(MMOItems.plugin.getTiers().get(tier), permission, max);
    }

    public DropTier(ItemTier tier, String permission, int max) {
        this.permission = permission;
        this.tier = tier;
        this.max = max;
    }

    public ItemTier getTier() {
        return tier;
    }

    public String getPermission() {
        return permission;
    }

    public int getMax() {
        return max;
    }
}

