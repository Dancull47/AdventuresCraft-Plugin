package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.Commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import co.aikar.commands.bukkit.contexts.OnlinePlayer;
import me.clip.placeholderapi.PlaceholderAPI;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.beton.BetonPointsManager;
import monzter.adventurescraft.plugin.utilities.enums.AdventureStatsDisplay;
import monzter.adventurescraft.plugin.utilities.enums.PrisonStatsDisplay;
import monzter.adventurescraft.plugin.utilities.general.ConsoleCommand;
import monzter.adventurescraft.plugin.utilities.luckperms.PermissionLP;
import monzter.adventurescraft.plugin.utilities.mmoitems.MMOItemsGive;
import monzter.adventurescraft.plugin.utilities.text.NumberFormat;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class AdminCommands extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final MMOItemsGive mmoItemsGive;
    private final PermissionLP permissionLP;
    private final BetonPointsManager betonPointsManager;
    private final NumberFormat numberFormat;
    private final ConsoleCommand consoleCommand;


    public AdminCommands(AdventuresCraft plugin, MMOItemsGive mmoItemsGive, PermissionLP permissionLP, BetonPointsManager betonPointsManager, NumberFormat numberFormat, ConsoleCommand consoleCommand) {
        this.plugin = plugin;
        this.mmoItemsGive = mmoItemsGive;
        this.permissionLP = permissionLP;
        this.betonPointsManager = betonPointsManager;
        this.numberFormat = numberFormat;
        this.consoleCommand = consoleCommand;
    }

    @CommandAlias("reward")
    @CommandPermission("*")
    @Description("Reward stats to a Player")
    @CommandCompletion("experience|exp|ac|adventureCoin|adventureCoins|bs|bankSlots @nothing *")
    public void rewardCommand(String stat, int amount, OnlinePlayer targetPlayer) {
        switch (stat.toLowerCase()) {
            case "experience":
            case "exp":
                targetPlayer.getPlayer().sendMessage(ChatColor.GREEN + "You gained +" + ChatColor.GOLD + numberFormat.numberFormat(amount) + ChatColor.GREEN + " " + AdventureStatsDisplay.EXP.getName() + ChatColor.GREEN + "!");
                consoleCommand.consoleCommand("mmocore admin exp give " + targetPlayer.getPlayer().getName() + " main " + amount);
                break;
            case "ac":
            case "acs":
            case "adventureCoin":
            case "adventureCoins":
                targetPlayer.getPlayer().sendMessage(ChatColor.GREEN + "You gained +" + ChatColor.GOLD + numberFormat.numberFormat(amount) + ChatColor.GREEN + "x " + PrisonStatsDisplay.ADVENTURE_COINS.getName() + ChatColor.GREEN + "!");
                betonPointsManager.givePointACs(targetPlayer.player, amount);
                break;
            case "bankSlots":
            case "slots":
            case "bs":
                targetPlayer.getPlayer().sendMessage(ChatColor.GREEN + "You gained +" + ChatColor.GOLD + numberFormat.numberFormat(amount) + ChatColor.GREEN + "x " + ChatColor.GREEN + "Bank Slots" + ChatColor.GREEN + "!");
                consoleCommand.consoleCommand("bank admin slots add " + targetPlayer.getPlayer().getName() + " " + amount);
                break;
        }
    }

    @CommandAlias("StatsDebug")
    @CommandPermission("*")
    @CommandCompletion("*")
    public void statsDebug(OnlinePlayer targetPlayer) {
        Player player = targetPlayer.player;
        player.sendMessage(AdventureStatsDisplay.HP.getName() + ": " + parsePlaceholder(player, "mmocore_health") + "/" + parsePlaceholder(player, "mmocore_max_health"));
        player.sendMessage(AdventureStatsDisplay.MANA.getName() + ": " + parsePlaceholder(player, "mmocore_mana") + "/" + parsePlaceholder(player, "mmocore_stat_max_mana"));
        player.sendMessage(AdventureStatsDisplay.ARMOR.getName() + ": " + parsePlaceholder(player, "mmocore_stat_defense"));
        player.sendMessage(AdventureStatsDisplay.SPEED.getName() + ": " + parsePlaceholder(player, "mmocore_stat_movement_speed"));
        player.sendMessage(AdventureStatsDisplay.DAMAGE.getName() + ": " + parsePlaceholder(player, "mmocore_stat_attack_damage"));
        player.sendMessage(AdventureStatsDisplay.ATTACK_SPEED.getName() + ": " + parsePlaceholder(player, "mmocore_stat_attack_damage"));
        player.sendMessage(AdventureStatsDisplay.CRITICAL_CHANCE.getName() + ": " + parsePlaceholder(player, "mmocore_stat_critical_strike_chance"));
        player.sendMessage(AdventureStatsDisplay.CRITICAL_DAMAGE.getName() + ": " + parsePlaceholder(player, "mmocore_stat_critical_strike_power"));
        player.sendMessage(AdventureStatsDisplay.MAGIC_DAMAGE.getName() + ChatColor.WHITE + " = " + ChatColor.YELLOW + parsePlaceholder(player, "mmocore_stat_magic_damage"));
        player.sendMessage(AdventureStatsDisplay.SKILL_DAMAGE.getName() + ChatColor.WHITE + " = " + ChatColor.YELLOW + parsePlaceholder(player, "mmocore_stat_skill_damage"));
    }

    private String parsePlaceholder(Player player, String string) {
        return PlaceholderAPI.setPlaceholders(player, "%" + string + "%");
    }

}

