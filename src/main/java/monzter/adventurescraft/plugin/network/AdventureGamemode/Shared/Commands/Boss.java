package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.Commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.beton.BetonTagManager;
import monzter.adventurescraft.plugin.utilities.general.ConsoleCommand;
import monzter.adventurescraft.plugin.utilities.general.ItemAdder;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import monzter.adventurescraft.plugin.utilities.luckperms.PermissionLP;
import net.Indyuce.mmoitems.MMOItems;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class Boss extends BaseCommand implements Listener {
    private final AdventuresCraft plugin;
    private final ConsoleCommand consoleCommand;
    private final PermissionLP permissionLP;
    private final SoundManager soundManager;
    private final BetonTagManager betonTagManager;
    private final ItemAdder itemAdder;


    public Boss(AdventuresCraft plugin, ConsoleCommand consoleCommand, PermissionLP permissionLP, SoundManager soundManager, BetonTagManager betonTagManager, ItemAdder itemAdder) {
        this.plugin = plugin;
        this.consoleCommand = consoleCommand;
        this.permissionLP = permissionLP;
        this.soundManager = soundManager;
        this.betonTagManager = betonTagManager;
        this.itemAdder = itemAdder;
    }

    @CommandAlias("MordenRepeat")
    @CommandPermission("*")
    private void mordenRepeat(Player player) {
        if (!betonTagManager.hasTag(player, "default-Castle-Klaus.SUMMONING_MORDEN_STARTED"))
            consoleCommand.consoleCommand("q event " + player.getName() + " default-Castle-Klaus.Q1_START");
        else {
            player.sendMessage(ChatColor.RED + "This quest is already active!");
            soundManager.soundNo(player, 1);
            Bukkit.getScheduler().runTaskLater(plugin, () -> itemAdder.itemAdder(player, MMOItems.plugin.getItem("CONSUMABLE", "MORDEN_SUMMONER2")), 1);
        }
    }
    @CommandAlias("DryadRepeat")
    @CommandPermission("*")
    private void dryadRepeat(Player player) {
        if (!betonTagManager.hasTag(player, "default-Forest-Jack.OAK_DRYAD_DESTROYER_STARTED") && !betonTagManager.hasTag(player, "default-Forest-Jack.SPRUCE_DRYAD_DESTROYER_STARTED") &&
                !betonTagManager.hasTag(player, "default-Forest-Jack.DARK_OAK_DRYAD_DESTROYER_STARTED") && !betonTagManager.hasTag(player, "default-Forest-Jack.BIRCH_DRYAD_DESTROYER_STARTED") &&
                !betonTagManager.hasTag(player, "default-Forest-Jack.ACACIA_DRYAD_DESTROYER_STARTED") && !betonTagManager.hasTag(player, "default-Forest-Jack.JUNGLE_DRYAD_DESTROYER_STARTED"))
            consoleCommand.consoleCommand("q event " + player.getName() + " default-Forest-Jack.QUEST_START");
        else {
            player.sendMessage(ChatColor.RED + "This quest is already active!");
            soundManager.soundNo(player, 1);
            Bukkit.getScheduler().runTaskLater(plugin, () -> itemAdder.itemAdder(player, MMOItems.plugin.getItem("CONSUMABLE", "DRYAD_SUMMONER2")), 1);
        }
    }
}
