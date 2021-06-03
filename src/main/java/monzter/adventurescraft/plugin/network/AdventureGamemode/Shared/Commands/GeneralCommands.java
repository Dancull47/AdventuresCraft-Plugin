package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.Commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.general.ConsoleCommand;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import monzter.adventurescraft.plugin.utilities.luckperms.PermissionLP;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class GeneralCommands extends BaseCommand implements Listener {
    private final AdventuresCraft plugin;
    private final ConsoleCommand consoleCommand;
    private final PermissionLP permissionLP;
    private final SoundManager soundManager;


    public GeneralCommands(AdventuresCraft plugin, ConsoleCommand consoleCommand, PermissionLP permissionLP, SoundManager soundManager) {
        this.plugin = plugin;
        this.consoleCommand = consoleCommand;
        this.permissionLP = permissionLP;
        this.soundManager = soundManager;
    }

    @CommandAlias("profileViewer|pv")
    @CommandCompletion("*")
    private void activeQuestsCommand(Player player, Player targetPlayer) {
        if (targetPlayer != null)
            player.performCommand("playerprofiles:playerinformation view " + targetPlayer);
        else {
            player.sendMessage(ChatColor.RED + "Player not found!");
            soundManager.soundNo(player, 1);
        }
    }

    @CommandAlias("ActiveQuests")
    private void activeQuestsCommand(Player player) {
        consoleCommand.consoleCommand("rpgmenu open default-Menus-menu.active " + player.getName());
    }

    @CommandAlias("UnclaimedQuests")
    private void unclaimedQuestsCommand(Player player) {
        consoleCommand.consoleCommand("rpgmenu open default-Menus-menu.unclaimed " + player.getName());
    }

    @CommandAlias("Quests|Quest")
    private void quests(Player player) {
        consoleCommand.consoleCommand("dm open Quests " + player.getName());
    }

    //  Cannot download Resource Pack???
    @CommandAlias("rp|resourcePack|texturePack")
    @CommandCompletion("enable|disable|download")
    private void resourcePack(Player player, String command) {
        switch (command) {
            case "enable":
                if (!player.hasPermission("RP.DOWNLOAD")) {
                    player.sendMessage(ChatColor.GREEN + "Resource Pack has been enabled! Use /RP Download or re-login to get it!");
                    soundManager.soundYes(player, 1);
                    permissionLP.givePermission(player, "RP.DOWNLOAD");
                } else {
                    player.sendMessage(ChatColor.RED + "Resource Pack is already enabled! Use /RP Download or re-login to get it!");
                    soundManager.soundNo(player, 1);
                }
                break;
            case "disable":
                if (player.hasPermission("RP.DOWNLOAD")) {
                    player.sendMessage(ChatColor.GREEN + "Resource Pack has been disabled!");
                    soundManager.soundYes(player, 1);
                    permissionLP.takePermission(player, "RP.DOWNLOAD");
                } else {
                    player.sendMessage(ChatColor.RED + "Resource Pack is already disabled!");
                    soundManager.soundNo(player, 1);
                }
                break;
            case "download":
                player.sendMessage(ChatColor.GREEN + "Resource Pack is now being downloaded!");
                soundManager.soundYes(player, 1);
                player.setResourcePack("https://www.dropbox.com/s/9ame51dgtjdmadq/AdventuresCraft3.zip?dl=1", hexStringToByteArray("f2e3f6cbda5745e5a08fbb43e3ea881de9f8c30b"));
                Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
                    if (!player.hasResourcePack())
                        player.sendMessage(ChatColor.RED + "It looks like you don't have the resource pack Enabled! Adjust your settings before joining the Server!");
                }, 200);
                break;
        }
    }

    @CommandAlias("music")
    @CommandCompletion("enable|disable|on|off|play|start|volume 0|25|50|75|100")
    private void music(Player player, String command, int volume) {
        switch (command) {
            case "enable":
            case "on":
                player.sendMessage(ChatColor.GREEN + "You will now hear Music!");
                soundManager.soundYes(player, 1);
                permissionLP.givePermission(player, "Music.ON");
                player.performCommand("anbs song play");
                break;
            case "disable":
            case "off":
                player.sendMessage(ChatColor.RED + "You will no longer hear Music!");
                soundManager.soundNo(player, 1);
                permissionLP.givePermission(player, "Music.OFF");
                player.performCommand("anbs song stop");
                break;
            case "volume":
                if (volume <= 100 && volume >= 0) {
                    player.sendMessage(ChatColor.GREEN + "Your Music volume has been adjusted to " + ChatColor.GOLD + volume + ChatColor.GREEN + "!");
                    soundManager.soundYes(player, 1);
                    player.performCommand("anbs song volume " + volume);
                } else if (volume < 0) {
                    player.sendMessage(ChatColor.RED + "Your volume must be at least 0!");
                    soundManager.soundNo(player, 1);
                } else if (volume > 100) {
                    player.sendMessage(ChatColor.RED + "Your volume cannot be louder than 100!");
                    soundManager.soundNo(player, 1);
                }
                break;
        }
    }

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }


}
