package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.ClassSystem;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.bukkit.contexts.OnlinePlayer;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.beton.BetonPointsManager;
import monzter.adventurescraft.plugin.utilities.beton.BetonPointsManagerStatic;
import monzter.adventurescraft.plugin.utilities.beton.BetonTagManager;
import monzter.adventurescraft.plugin.utilities.text.NumberFormatStatic;
import org.apache.commons.lang.WordUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;
import java.util.List;

public class ProfessionHandler extends BaseCommand {
    private final AdventuresCraft plugin;
    private final BetonPointsManager betonPointsManager;
    private final BetonTagManager betonTagManager;
    public static DecimalFormat df = new DecimalFormat("#.##");

    public ProfessionHandler(AdventuresCraft plugin, BetonPointsManager betonPointsManager, BetonTagManager betonTagManager) {
        this.plugin = plugin;
        this.betonPointsManager = betonPointsManager;
        this.betonTagManager = betonTagManager;
    }

    @CommandAlias("giveEXP")
    @CommandPermission("*")
    private void giveEXP(OnlinePlayer onlinePlayer, String profession, int expAmount) {
        profession = profession.toUpperCase();
        Player player = onlinePlayer.getPlayer();
        int currentLevel = getLevel(onlinePlayer.getPlayer(), profession);
        int currentEXP = getEXP(onlinePlayer.getPlayer(), profession);
        List<EXPTable> levelUps = EXPTable.getLevelUps(Professions.valueOf(profession), currentLevel, currentEXP, expAmount);
        betonPointsManager.givePoint(player, "PROFESSION." + profession + "." + "AMOUNT", expAmount);
        if (!levelUps.isEmpty())
            for (EXPTable expTable : levelUps) {
                ProfessionLevelUpEvent purchaseEvent = new ProfessionLevelUpEvent(player, Professions.valueOf(profession), expTable.getLevel());
                Bukkit.getServer().getPluginManager().callEvent(purchaseEvent);
            }
    }

    @CommandAlias("getEXP")
    @CommandPermission("*")
    private void getEXPCommand(OnlinePlayer onlinePlayer, String profession) {
        Player player = onlinePlayer.getPlayer();
        int currentEXP = getEXP(onlinePlayer.getPlayer(), profession);
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aYour &e" + WordUtils.capitalizeFully(profession) + " &aEXP Amount is &6" + currentEXP));
    }

    @CommandAlias("getLevel")
    @CommandPermission("*")
    private void getLevelCommand(OnlinePlayer onlinePlayer, String profession) {
        Player player = onlinePlayer.getPlayer();
        int level = getLevel(onlinePlayer.getPlayer(), profession);
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aYour &e" + WordUtils.capitalizeFully(profession) + " &aLevel is &6" + level));
    }

    public static int getLevel(Player player, String profession) {
        profession = profession.toUpperCase();
        int currentEXP = getEXP(player, profession);
        List<EXPTable> expTable = EXPTable.getEXPTable(Professions.valueOf(profession));
        for (int i = expTable.size() - 1; i >= 0; i--)
            if (currentEXP >= expTable.get(i).getExpAmount())
                return expTable.get(i).getLevel();
        return 0;
    }

    public static int getNextLevelEXP(Player player, String profession) {
        profession = profession.toUpperCase();
        int currentLevel = getLevel(player, profession);
        for (EXPTable expTable : EXPTable.values())
            if (expTable.getProfession() == Professions.valueOf(profession))
                if (expTable.getLevel() == currentLevel + 1)
                    return expTable.getExpAmount();
        return 999_999_999;
    }

    public static String getNextLevelPercentage(Player player, String profession) {
        profession = profession.toUpperCase();
        int exp = getEXP(player, profession);
        int requiredEXP = getNextLevelEXP(player, profession);
        return df.format((exp / (float) requiredEXP) * 100);
    }

    public static String getNextLevelEXPFormatted(Player player, String profession) {
        profession = profession.toUpperCase();
        return NumberFormatStatic.numberFormat(getNextLevelEXP(player, profession));
    }

    public static int getEXP(Player player, String profession) {
        profession = profession.toUpperCase();
        return BetonPointsManagerStatic.getPoints(player, "PROFESSION." + profession + "." + "AMOUNT");
    }

    public static String getEXPFormatted(Player player, String profession) {
        profession = profession.toUpperCase();
        return NumberFormatStatic.numberFormat(getEXP(player, profession));
    }
}
