package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.Commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.general.ConsoleCommand;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import monzter.adventurescraft.plugin.utilities.luckperms.PermissionLP;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class Boss extends BaseCommand implements Listener {
    private final AdventuresCraft plugin;
    private final ConsoleCommand consoleCommand;
    private final PermissionLP permissionLP;
    private final SoundManager soundManager;


    public Boss(AdventuresCraft plugin, ConsoleCommand consoleCommand, PermissionLP permissionLP, SoundManager soundManager) {
        this.plugin = plugin;
        this.consoleCommand = consoleCommand;
        this.permissionLP = permissionLP;
        this.soundManager = soundManager;
    }

    @CommandAlias("dryadRepeat")
    private void dryadRepeat(Player player) {
        repeat(player, "Dryad");
    }
    @CommandAlias("mordenRepeat")
    private void mordenRepeat(Player player) {
        repeat(player, "Morden");
    }

    private void repeat(Player player, String name) {
        String permission = name + ".Repeat";
        if (player.hasPermission(permission)) {
            permissionLP.takePermission(player, permission);
            player.sendMessage(ChatColor.RED + "Upon completing your next " + ChatColor.GOLD + name + ChatColor.RED + " summoning, the quest will not automatically be accepted!");
        } else {
            permissionLP.givePermission(player, permission);
            player.sendMessage(ChatColor.RED + "Upon completing your next " + ChatColor.GOLD + name + ChatColor.RED + " summoning, the quest will be automatically be accepted!");
        }
    }
}
