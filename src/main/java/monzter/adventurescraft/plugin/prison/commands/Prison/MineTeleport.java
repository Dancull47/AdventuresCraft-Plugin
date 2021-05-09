package monzter.adventurescraft.plugin.prison.commands.Prison;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Dependency;
import monzter.adventurescraft.plugin.AdventuresCraft;
import org.bukkit.entity.Player;

public class MineTeleport extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;

    public MineTeleport(AdventuresCraft plugin) {
        this.plugin = plugin;
    }

    String ranks = "zyxwvutsrqponmlkjlhgfedcba";

    @CommandAlias("mine")
    public void mineCommand(Player player) {
        for (int i = 0; i < ranks.length(); i++) {
            if (player.hasPermission("mines.tp." + ranks.charAt(i))) {
                player.performCommand("warp " + ranks.charAt(i));
                break;
            }
        }
    }

}

