package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.ClassSystem;

import io.lumine.xikage.mythicmobs.api.bukkit.BukkitAPIHelper;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.beton.BetonPointsManager;
import monzter.adventurescraft.plugin.utilities.beton.BetonTagManager;
import monzter.adventurescraft.plugin.utilities.general.ConsoleCommand;
import monzter.adventurescraft.plugin.utilities.luckperms.PermissionLP;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.apache.commons.lang.WordUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class LevelUpListener implements Listener {
    private final AdventuresCraft plugin;
    private final BetonPointsManager betonPointsManager;
    private final BetonTagManager betonTagManager;
    private final ConsoleCommand consoleCommand;
    private final PermissionLP permissionLP;


    public LevelUpListener(AdventuresCraft plugin, BetonPointsManager betonPointsManager, BetonTagManager betonTagManager, ConsoleCommand consoleCommand, PermissionLP permissionLP) {
        this.plugin = plugin;
        this.betonPointsManager = betonPointsManager;
        this.betonTagManager = betonTagManager;
        this.consoleCommand = consoleCommand;
        this.permissionLP = permissionLP;
    }

    @EventHandler
    private void levelupEventListener(ProfessionLevelUpEvent event) {
        Player player = event.getPlayer();
        int level = event.getLevel();
        Professions profession = event.getProfession();
        BukkitAPIHelper bukkitAPIHelper = new BukkitAPIHelper();

        betonTagManager.giveTag(player, "PROFESSION." + profession + "." + "LEVEL." + level);
        permissionLP.givePermission(player, profession + "." + level);
        if (profession == Professions.MAIN) {
            player.sendMessage(MiniMessage.get().parse("<yellow><b>LEVEL UP!</b> <green>You reached Level <gold>" + level + "<green>!"));
            player.sendMessage(MiniMessage.get().parse("<click:suggest_command:/Attributes><hover:show_text:'<green>Click to view Attributes!'>" +
                    "<green>You earned <gold><b>1x Attribute Points</b><green>, which can be redeemed by using <gold>/Attributes<green>!"));
            consoleCommand.consoleCommand("mmocore admin attribute-points give " + player.getName() + " 1");
            bukkitAPIHelper.castSkill(player, "MMOCORE_LEVEL_UP_MAIN");
            switch (level) {
                case 5:
                    player.sendMessage(MiniMessage.get().parse("<gold><b>AREA UNLOCKED!</b> <green>You now have access to the <dark_purple>Void<green>!"));
                    break;
                case 10:
                    player.sendMessage(MiniMessage.get().parse("<click:suggest_command:/Attributes><hover:show_text:'<green>Click to view Attributes!'>" +
                            "<green>You earned <gold><b>1x Attribute Reallocation Token</b><green>, which can be used to reset your spent <gold>Attribute Points<green>!"));
                    consoleCommand.consoleCommand("mmocore admin attr-realloc-points give " + player.getName() + " 1");
                    break;
            }
        } else {
            player.sendMessage(MiniMessage.get().parse("<gold><b>PROFESSION LEVEL UP!</b> <green>You reached <yellow>" + WordUtils.capitalizeFully(profession.name()) + " Level <gold>" + level + "<green>!"));
            bukkitAPIHelper.castSkill(player, "MMOCORE_LEVEL_UP_PROFESSION");
        }
    }
}
