package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.Commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.enums.Prefix;
import monzter.adventurescraft.plugin.utilities.general.ConsoleCommand;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import monzter.adventurescraft.plugin.utilities.luckperms.PermissionLP;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.jetbrains.annotations.NotNull;

@CommandAlias("home|h")
public class HomeCommands extends BaseCommand implements Listener, PluginMessageListener {
    private final AdventuresCraft plugin;
    private final ConsoleCommand consoleCommand;
    private final PermissionLP permissionLP;
    private final SoundManager soundManager;

    public HomeCommands(AdventuresCraft plugin, ConsoleCommand consoleCommand, PermissionLP permissionLP, SoundManager soundManager) {
        this.plugin = plugin;
        this.consoleCommand = consoleCommand;
        this.permissionLP = permissionLP;
        this.soundManager = soundManager;
        plugin.getServer().getMessenger().registerOutgoingPluginChannel(plugin, "BungeeCord");
        plugin.getServer().getMessenger().registerIncomingPluginChannel(plugin, "BungeeCord", this);
    }

    @Default
    private void home(Player player) {
        if (plugin.SERVER.equals("Adventure")) {
            ByteArrayDataOutput b = ByteStreams.newDataOutput();
            try {
                b.writeUTF("Connect");
                b.writeUTF("adventureHomes");
            } catch (Exception e) {
                player.sendMessage(ChatColor.RED + "Error travelling to the Home! Report this to Monzter#4951 on Discord!");
                return;
            }
            player.sendPluginMessage(plugin, "BungeeCord", b.toByteArray());
        } else {
            player.performCommand("plot home");
            player.performCommand("home help 1");
        }
    }

    @Subcommand("help")
    @CommandCompletion("1|2|3")
    private void home(Player player, @Optional Integer helpPage) {
        if (helpPage == null || helpPage == 1) {
            player.sendMessage(ChatColor.GREEN.toString() + ChatColor.BOLD + "Home Commands");
            player.sendMessage(Prefix.PREFIX.getString() + ChatColor.YELLOW + "/Home " + ChatColor.DARK_GRAY + "- " + ChatColor.GREEN + "Return Home and see useful commands!");
            player.sendMessage(Prefix.PREFIX.getString() + ChatColor.YELLOW + "/Home Visit <Name> " + ChatColor.DARK_GRAY + "- " + ChatColor.GREEN + "Visit another Adventurer's Home!");
            player.sendMessage(Prefix.PREFIX.getString() + ChatColor.YELLOW + "/Home Chat " + ChatColor.DARK_GRAY + "- " + ChatColor.GREEN + "Change your Chat to only Members of your Home.");
            player.sendMessage(Prefix.PREFIX.getString() + ChatColor.YELLOW + "/Home Delete " + ChatColor.DARK_GRAY + "- " + ChatColor.GREEN + "Delete your entire Home (USE WITH CAUTION)!");
            player.sendMessage(ChatColor.YELLOW + "----------Use /Home Help <1-3>----------");
        } else if (helpPage == 2) {
            player.sendMessage(ChatColor.GREEN.toString() + ChatColor.BOLD + "Home Commands");
            player.sendMessage(Prefix.PREFIX.getString() + ChatColor.YELLOW + "/Home Ban <Name> " + ChatColor.DARK_GRAY + "- " + ChatColor.GREEN + "Prevent an Adventurer from entering your Home!");
            player.sendMessage(Prefix.PREFIX.getString() + ChatColor.YELLOW + "/Home Unban <Name> " + ChatColor.DARK_GRAY + "- " + ChatColor.GREEN + "Allows a Banned Adventurer back into your Home!");
            player.sendMessage(Prefix.PREFIX.getString() + ChatColor.YELLOW + "/Home Set " + ChatColor.DARK_GRAY + "- " + ChatColor.GREEN + "Change the position of /Home.");
            player.sendMessage(Prefix.PREFIX.getString() + ChatColor.YELLOW + "/Home SetOwner " + ChatColor.DARK_GRAY + "- " + ChatColor.GREEN + "Sets a new Owner of your Home (USE WITH CAUTION)!");
            player.sendMessage(ChatColor.YELLOW + "----------Use /Home Help <2-3>----------");
        } else if (helpPage == 3) {
            player.sendMessage(ChatColor.GREEN.toString() + ChatColor.BOLD + "Home Commands");
            player.sendMessage(Prefix.PREFIX.getString() + ChatColor.YELLOW + "/Home Add <Name> " + ChatColor.DARK_GRAY + "- " + ChatColor.GREEN + "Allow an Adventurer to build in the Home while the Owner is online.");
            player.sendMessage(Prefix.PREFIX.getString() + ChatColor.YELLOW + "/Home Unadd <Name> " + ChatColor.DARK_GRAY + "- " + ChatColor.GREEN + "Removes an Adventurer who was given permission to build while the Owner was online.");
            player.sendMessage(Prefix.PREFIX.getString() + ChatColor.YELLOW + "/Home Trust <Name> " + ChatColor.DARK_GRAY + "- " + ChatColor.GREEN + "Allow the Adventurer to build in the Home at all times!");
            player.sendMessage(Prefix.PREFIX.getString() + ChatColor.YELLOW + "/Home Untrust <Name> " + ChatColor.DARK_GRAY + "- " + ChatColor.GREEN + "Prevents the Adventurer from building in the Home at all times!");
            player.sendMessage(ChatColor.YELLOW + "----------Use /Home Help <3-3>----------");
        }
    }

    @Subcommand("Visit")
    private void home(Player player, OfflinePlayer targetPlayer) {
        if (targetPlayer.hasPlayedBefore())
            player.performCommand("plot visit " + targetPlayer.getPlayer().getName());
        else {
            player.sendMessage(ChatColor.RED + "That player has not played before!");
            soundManager.soundNo(player, 1);
        }
    }

    @Subcommand("Chat")
    private void homeChat(Player player) {
        player.performCommand("plot toggle chat");
    }

    @Subcommand("Delete")
    private void homeDelete(Player player) {
        player.performCommand("plot delete");
    }

    @Subcommand("Kick|Remove")
    private void homeKick(Player player, OfflinePlayer targetPlayer) {
        if (targetPlayer.hasPlayedBefore())
            player.performCommand("plot kick " + targetPlayer.getPlayer().getName());
        else {
            player.sendMessage(ChatColor.RED + "That player has not played before!");
            soundManager.soundNo(player, 1);
        }
    }

    @Subcommand("Ban")
    private void homeBan(Player player, OfflinePlayer targetPlayer) {
        if (targetPlayer.hasPlayedBefore())
            player.performCommand("plot ban " + targetPlayer.getPlayer().getName());
        else {
            player.sendMessage(ChatColor.RED + "That player has not played before!");
            soundManager.soundNo(player, 1);
        }
    }

    @Subcommand("UnBan")
    private void homeUnBan(Player player, OfflinePlayer targetPlayer) {
        if (targetPlayer.hasPlayedBefore())
            player.performCommand("plot unban " + targetPlayer.getPlayer().getName());
        else {
            player.sendMessage(ChatColor.RED + "That player has not played before!");
            soundManager.soundNo(player, 1);
        }
    }

    @Subcommand("set|setHome")
    private void homeSet(Player player) {
        player.performCommand("plot setHome");
    }

    @Subcommand("SetOwner")
    private void homeSetOwner(Player player, OfflinePlayer targetPlayer) {
        if (targetPlayer.hasPlayedBefore())
            player.performCommand("plot setOwner " + targetPlayer.getPlayer().getName());
        else {
            player.sendMessage(ChatColor.RED + "That player has not played before!");
            soundManager.soundNo(player, 1);
        }
    }

    @Subcommand("Add")
    private void homeAdd(Player player, OfflinePlayer targetPlayer) {
        if (targetPlayer.hasPlayedBefore())
            player.performCommand("plot Add " + targetPlayer.getPlayer().getName());
        else {
            player.sendMessage(ChatColor.RED + "That player has not played before!");
            soundManager.soundNo(player, 1);
        }
    }

    @Subcommand("UnAdd")
    private void homeUnAdd(Player player, OfflinePlayer targetPlayer) {
        if (targetPlayer.hasPlayedBefore())
            player.performCommand("plot UnAdd " + targetPlayer.getPlayer().getName());
        else {
            player.sendMessage(ChatColor.RED + "That player has not played before!");
            soundManager.soundNo(player, 1);
        }
    }

    @Subcommand("Trust")
    private void homeTrust(Player player, OfflinePlayer targetPlayer) {
        if (targetPlayer.hasPlayedBefore())
            player.performCommand("plot Trust " + targetPlayer.getPlayer().getName());
        else {
            player.sendMessage(ChatColor.RED + "That player has not played before!");
            soundManager.soundNo(player, 1);
        }
    }

    @Subcommand("UnTrust")
    private void homeUnTrust(Player player, OfflinePlayer targetPlayer) {
        if (targetPlayer.hasPlayedBefore())
            player.performCommand("plot UnTrust " + targetPlayer.getPlayer().getName());
        else {
            player.sendMessage(ChatColor.RED + "That player has not played before!");
            soundManager.soundNo(player, 1);
        }
    }

    @Subcommand("Info")
    private void homeInfo(Player player) {
        player.performCommand("plot info");
    }

    @Subcommand("Confirm")
    private void homeConfirm(Player player) {
        player.performCommand("plot confirm");
    }

    @Override
    public void onPluginMessageReceived(@NotNull String s, @NotNull Player player, @NotNull byte[] bytes) {
    }
}
