package monzter.adventurescraft.plugin.utilities.general;

import org.bukkit.entity.Player;

public interface SoundManager {
    void playSound(Player player, org.bukkit.Sound sound, float volume, float pitch);
    void soundNo(Player player, float pitch);
    void soundYes(Player player, float pitch);
    void soundYes2(Player player, float pitch);
    void soundPurchase(Player player, float pitch);
    void soundTeleport(Player player);
}
