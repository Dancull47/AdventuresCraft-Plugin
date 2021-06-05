package monzter.adventurescraft.plugin.network.Shared.Commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Dependency;
import monzter.adventurescraft.plugin.AdventuresCraft;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.Map;

public class Debug extends BaseCommand implements Listener {

    @Dependency
    private final AdventuresCraft plugin;


    public Debug(AdventuresCraft plugin) {
        this.plugin = plugin;
    }

    @CommandAlias("debugMaterial")
    private void materialCommand(Player player) {
        player.sendMessage(player.getInventory().getItemInMainHand().getType().toString());
    }
    @CommandAlias("debugEnchant")
    private void materialCommand(Player player, String enchantment) {
        if (player.getPlayer().getInventory().getItemInMainHand().getItemMeta() != null) {
            Map<Enchantment, Integer> enchantmentMap = player.getPlayer().getInventory().getItemInMainHand().getItemMeta().getEnchants();
            if (!enchantmentMap.isEmpty()) {
                if (enchantmentMap.containsKey(org.bukkit.enchantments.Enchantment.getByName(enchantment))) {
                    player.sendMessage(enchantment);
                    player.sendMessage(String.valueOf(enchantmentMap.get(Enchantment.getByName(enchantment))));
                }
            }
        }
    }
}

