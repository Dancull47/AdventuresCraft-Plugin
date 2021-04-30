package monzter.adventurescraft.plugin.commands.Prison;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import co.aikar.commands.bukkit.contexts.OnlinePlayer;
import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import me.clip.placeholderapi.PlaceholderAPI;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.acUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class MiningPass extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;

    public MiningPass(AdventuresCraft plugin) {
        this.plugin = plugin;
    }

    @CommandAlias("miningpass")
    public void statCommand(Player player) {
            if (!player.hasPermission("MININGPASS.PURCHASED"))
                player.sendMessage(
                        Component.text("You can purchase the")
                                .color(NamedTextColor.GREEN)
                                .append(Component.text(" Mining Pass ", NamedTextColor.YELLOW, TextDecoration.BOLD))
                                .hoverEvent(Component.text("Click to visit the Store!", NamedTextColor.GREEN))
                                .clickEvent(ClickEvent.openUrl("https://store.adventurescraft.net"))
                                .append(Component.text("for additional rewards!")));
            acUtils.consoleCommand("dm open MiningPass " + player.getName());
            acUtils.playSound(player, Sound.BLOCK_ENDER_CHEST_OPEN, 1, 2);
        }
}

