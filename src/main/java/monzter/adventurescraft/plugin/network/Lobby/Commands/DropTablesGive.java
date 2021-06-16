package monzter.adventurescraft.plugin.network.Lobby.Commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Dependency;
import co.aikar.commands.bukkit.contexts.OnlinePlayer;
import me.lucko.helper.random.RandomSelector;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import monzter.adventurescraft.plugin.utilities.luckperms.PermissionLP;
import monzter.adventurescraft.plugin.utilities.mmoitems.DropTablesDelivery;
import monzter.adventurescraft.plugin.utilities.mmoitems.MMOItemsGive;
import org.apache.commons.lang.WordUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class DropTablesGive extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final MMOItemsGive mmoItemsGive;
    private final SoundManager soundManager;
    private final DropTablesDelivery dropTablesDelivery;
    private final PermissionLP permissionLP;


    public DropTablesGive(AdventuresCraft plugin, MMOItemsGive mmoItemsGive, SoundManager soundManager, DropTablesDelivery dropTablesDelivery, PermissionLP permissionLP) {
        this.plugin = plugin;
        this.mmoItemsGive = mmoItemsGive;
        this.soundManager = soundManager;
        this.dropTablesDelivery = dropTablesDelivery;
        this.permissionLP = permissionLP;
    }

    @CommandAlias("DropTableViewer")
    @CommandPermission("*")
    @CommandCompletion("* trail")
    private void dropTable(OnlinePlayer player, String table) {
        dropTable(player, table, 1);
    }

    @CommandAlias("DropTableViewer")
    @CommandPermission("*")
    @CommandCompletion("* trail")
    private void dropTable(OnlinePlayer player, String table, int amount) {
        Player player1 = player.getPlayer();
        for (int i = 0; i < amount; i++) {
            switch (table.toUpperCase()) {
                case "TRAIL":
                    RandomSelector<TrailList> trailList = RandomSelector.weighted((TrailList.getTrail()));
                    TrailList trailListReward = trailList.pick();
                    String displayName = WordUtils.capitalizeFully(trailListReward.getName().replace('_', ' ')).replaceAll("\\d", "");
                    if (!player.getPlayer().hasPermission(trailListReward.getName())) {
                        permissionLP.givePermission(player1, trailListReward.getName());
                        final double multipliedChance = trailListReward.getWeight() * 100;
                        player1.sendMessage(ChatColor.YELLOW.toString() + ChatColor.BOLD + "Rewards:");
                        if (multipliedChance < 50 && multipliedChance >= 25) {
                            player1.sendMessage((displayName) + " " + ChatColor.BLUE + ChatColor.BOLD + multipliedChance + "% RARE!");
                            soundManager.playSound(player1, Sound.ENTITY_WITCH_CELEBRATE, 1, 1);
                        } else if (multipliedChance < 25 && multipliedChance >= 2) {
                            player1.sendMessage((displayName) + " " + ChatColor.YELLOW + ChatColor.BOLD + multipliedChance + "% VERY RARE!");
                            soundManager.playSound(player1, Sound.ENTITY_VINDICATOR_CELEBRATE, 1, 1);
                        } else if (multipliedChance < 2) {
                            player1.sendMessage((displayName) + " " + ChatColor.DARK_RED + ChatColor.BOLD + multipliedChance + "% INSANE!!!");
                            player1.sendTitle(ChatColor.RED.toString() + ChatColor.BOLD + "INSANE!!!", (displayName), 20, 100, 20);
                            soundManager.playSound(player1, Sound.ENTITY_RAVAGER_CELEBRATE, 1, 1);
                            Bukkit.broadcastMessage(ChatColor.GOLD + player1.getName() + ChatColor.GREEN + " just found a " + (displayName) + " " + ChatColor.DARK_RED + ChatColor.BOLD + multipliedChance + "% INSANE!!!");
                        } else {
                            player1.sendMessage((displayName) + " " + ChatColor.GREEN + multipliedChance + "%");
                        }
                    } else
                        dropTable(player, table, amount);
                    break;
            }
        }
    }
}

