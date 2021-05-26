package monzter.adventurescraft.plugin.utilities.beton;

import org.bukkit.entity.Player;
import pl.betoncraft.betonquest.BetonQuest;

import java.util.List;

public class BetonTagManagerImpl implements BetonTagManager {
    private final BetonQuest betonQuest;

    public BetonTagManagerImpl(BetonQuest betonQuest) {
        this.betonQuest = betonQuest;
    }

    @Override
    public boolean hasTag(Player player, String tag) {
        List<String> tags = betonQuest.getPlayerData(player.getUniqueId().toString()).getTags();
        for (String specificTag : tags)
            if (specificTag.equals(tag))
                return true;
        return false;
    }
}
