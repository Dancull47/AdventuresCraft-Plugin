package monzter.adventurescraft.plugin.cell.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Dependency;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import monzter.adventurescraft.plugin.utilities.luckperms.PermissionLP;
import monzter.adventurescraft.plugin.utilities.mmoitems.MMOItemsGive;
import net.md_5.bungee.api.ChatMessageType;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.util.Set;

public class Warp extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final MMOItemsGive mmoItemsGive;
    private final SoundManager soundManager;
    private final PermissionLP permissionLP;
    private final YamlConfiguration warps;

    public Warp(AdventuresCraft plugin, MMOItemsGive mmoItemsGive, SoundManager soundManager, PermissionLP permissionLP, YamlConfiguration warps) {
        this.plugin = plugin;
        this.mmoItemsGive = mmoItemsGive;
        this.soundManager = soundManager;
        this.permissionLP = permissionLP;
        this.warps = warps;
    }

    @CommandAlias("Warp")
    private void warp(Player player, String warp) {
        Set<String> warpNames = warps.getKeys(false);
        for (String currentWarpName : warpNames) {
            if (currentWarpName.equalsIgnoreCase(warp)) {
                permissionLP.givePermission(player, "CELL.WARP." + warp);
                player.performCommand("server Prison");
            } else {
                player.sendMessage(ChatColor.RED + "That warp is invalid!");
                soundManager.soundNo(player, 1);
            }
        }

    }
}

