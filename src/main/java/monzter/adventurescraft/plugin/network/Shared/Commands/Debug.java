package monzter.adventurescraft.plugin.network.Shared.Commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Dependency;
import monzter.adventurescraft.plugin.AdventuresCraft;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

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
}

