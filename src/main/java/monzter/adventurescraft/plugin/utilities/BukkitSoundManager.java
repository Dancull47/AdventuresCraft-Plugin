package monzter.adventurescraft.plugin.utilities;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class BukkitSoundManager implements SoundManager {
    @Override
    public void playSound(Player player, Sound sound, float volume, float pitch) {
        player.getWorld().playSound(player.getLocation(), sound, volume, pitch);
    }

    @Override
    public void soundNo(Player player, float pitch) {
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1f, pitch);
    }

    @Override
    public void soundYes(Player player, float pitch) {
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES, 1f, pitch);
    }

    @Override
    public void soundYes2(Player player, float pitch) {
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_WANDERING_TRADER_YES, 1f, pitch);
    }

    @Override
    public void soundPurchase(Player player, float pitch) {
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, pitch);
    }
}
