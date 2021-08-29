package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.Commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.enums.Prefix;
import monzter.adventurescraft.plugin.utilities.general.ConsoleCommand;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import monzter.adventurescraft.plugin.utilities.luckperms.PermissionLP;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class GeneralCommands extends BaseCommand implements Listener {
    private final AdventuresCraft plugin;
    private final ConsoleCommand consoleCommand;
    private final PermissionLP permissionLP;
    private final SoundManager soundManager;
    private final TextComponent resourcePack = Component.text("Are you wanting to see the Resource Pack, but not getting the popup when using /RP Download?")
            .color(NamedTextColor.GREEN)
            .hoverEvent(Component.text("Click to View Guide!", NamedTextColor.GREEN))
            .clickEvent(ClickEvent.openUrl("https://www.adventurescraft.net/wiki/site/resource-pack/"))
            .append(Component.text(" You need to enable your Resource Pack before joining, click here to find out how to!"));

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

    @CommandAlias("sell|sellall")
    private void sell(Player player) {
        consoleCommand.consoleCommand("ushop open " + player.getName());
    }

//    @CommandAlias("ActiveQuests")
//    private void activeQuestsCommand(Player player) {
//        consoleCommand.consoleCommand("rpgmenu open default-Menus-menu.active " + player.getName());
//    }
//
//    @CommandAlias("UnclaimedQuests")
//    private void unclaimedQuestsCommand(Player player) {
//        consoleCommand.consoleCommand("rpgmenu open default-Menus-menu.unclaimed " + player.getName());
//    }

//    @CommandAlias("Quests|Quest")
//    private void quests(Player player) {
//        consoleCommand.consoleCommand("dm open Quests " + player.getName());
//    }

    @CommandAlias("rp|resourcePack|texturePack")
    private void resourcePack(Player player) {
        player.sendMessage(ChatColor.GREEN.toString() + ChatColor.BOLD + "Resource Pack Commands");
        player.sendMessage(Prefix.PREFIX.getString() + ChatColor.YELLOW + "/RP Enable " + ChatColor.DARK_GRAY + "- " + ChatColor.GREEN + "Enable the Resource Pack.");
        player.sendMessage(Prefix.PREFIX.getString() + ChatColor.YELLOW + "/RP Disable " + ChatColor.DARK_GRAY + "- " + ChatColor.GREEN + "Disable the Resource Pack.");
        player.sendMessage(Prefix.PREFIX.getString() + ChatColor.YELLOW + "/RP Download " + ChatColor.DARK_GRAY + "- " + ChatColor.GREEN + "Download the Resource Pack.");
        player.sendMessage(resourcePack);
    }

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
    private void musicMessage(Player player) {
        player.sendMessage(ChatColor.GREEN.toString() + ChatColor.BOLD + "Music Commands");
        player.sendMessage(Prefix.PREFIX.getString() + ChatColor.YELLOW + "/Music Enable " + ChatColor.DARK_GRAY + "- " + ChatColor.GREEN + "Enable hearing the Music.");
        player.sendMessage(Prefix.PREFIX.getString() + ChatColor.YELLOW + "/Music Disable " + ChatColor.DARK_GRAY + "- " + ChatColor.GREEN + "Disable hearing the Music.");
        player.sendMessage(Prefix.PREFIX.getString() + ChatColor.YELLOW + "/Music Volume 0-100 " + ChatColor.DARK_GRAY + "- " + ChatColor.GREEN + "Adjust the volume of the Music.");
    }

    @CommandAlias("music")
    @CommandCompletion("enable|disable|on|off|play|start")
    private void music(Player player, String command) {
        switch (command) {
            case "enable":
            case "on":
            case "play":
            case "start":
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
        }
    }

    @CommandAlias("music")
    @CommandCompletion("volume 0|25|50|75|100")
    private void music(Player player, String command, int volume) {
        if (command.equals("volume")) {
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
        }
    }

    @CommandAlias("shops")
    @CommandCompletion("farming|foraging|mining|gems|slayer|cooking|wand|wands|hell|void|enchanted|material|enchantedMaterials")
    private void shop(Player player, String shop) {
        switch (shop.toLowerCase()) {
            case "farming":
            case "foraging":
            case "gems":
            case "slayer":
            case "wand":
            case "hell":
            case "void":
                consoleCommand.consoleCommand("mmoitems stations open " + shop + " " + player.getName());
                break;
            case "wands":
                consoleCommand.consoleCommand("mmoitems stations open " + "wand" + " " + player.getName());
                break;
            case "enchanted":
            case "material":
            case "materials":
            case "enchantedmaterial":
            case "enchantedmaterials":
                consoleCommand.consoleCommand("mmoitems stations open " + "enchanted-materials" + " " + player.getName());
                break;
            case "cooking":
                consoleCommand.consoleCommand("mmoitems stations open " + "stews" + " " + player.getName());
                break;
            case "mining":
                consoleCommand.consoleCommand("mmoitems stations open " + "miner-shop" + " " + player.getName());
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
