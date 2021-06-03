package monzter.adventurescraft.plugin.network.Shared.Commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.Dependency;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.general.ConsoleCommand;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Social extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;


    public Social(AdventuresCraft plugin) {
        this.plugin = plugin;
    }

    @CommandAlias("Friends|F")
    @CommandCompletion("list|msg|add|accept|deny|remove|jump|settings|block|unblock|blocklist")
    private void friendCommand(Player player) {
        player.performCommand("Friend");
    }
    @CommandAlias("Party|P")
    @CommandCompletion("join|chat|invite|kick|list|leave|leader")
    private void partyCommand(Player player) {
        player.performCommand("Party");
    }
}

