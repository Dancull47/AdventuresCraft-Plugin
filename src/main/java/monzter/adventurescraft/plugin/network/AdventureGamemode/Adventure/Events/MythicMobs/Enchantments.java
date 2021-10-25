package monzter.adventurescraft.plugin.network.AdventureGamemode.Adventure.Events.MythicMobs;

import monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.mainMenu.Enchant;
import monzter.adventurescraft.plugin.utilities.beton.BetonPointsManager;
import monzter.adventurescraft.plugin.utilities.general.EnchantmentCalculator;
import monzter.adventurescraft.plugin.utilities.vault.Economy;
import net.Indyuce.mmocore.api.event.CustomBlockMineEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class Enchantments implements Listener {

    private final Economy economy;
    private final BetonPointsManager betonPointsManager;


    public Enchantments(Economy economy, BetonPointsManager betonPointsManager) {
        this.economy = economy;
        this.betonPointsManager = betonPointsManager;
    }

    @EventHandler
    private void coined(CustomBlockMineEvent event) {
        Player player = event.getPlayer();
        int coinedEnchantmentLevel = EnchantmentCalculator.calculateEnchantments(player, "Coined");
        int experienceEnchantmentLevel = EnchantmentCalculator.calculateEnchantments(player, "Experience");
        if (!event.isCancelled()) {
            if (coinedEnchantmentLevel > 0)
                economy.giveMoney(player, coinedEnchantmentLevel * Enchant.Enchantments.COINED.getIncreasePerLevel(), true);
            if (experienceEnchantmentLevel > 0)
                betonPointsManager.givePoint(player, "EXP.EXP", (int) Math.round(experienceEnchantmentLevel * Enchant.Enchantments.EXPERIENCE.getIncreasePerLevel()));
        }
    }
}
