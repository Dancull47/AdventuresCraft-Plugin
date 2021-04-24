package monzter.adventurescraft.plugin.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.Dependency;
import io.lumine.mythic.lib.api.item.NBTItem;
import me.clip.placeholderapi.PlaceholderAPI;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.event.extras.PetEgg;
import monzter.adventurescraft.plugin.event.extras.StatsDisplay;
import monzter.adventurescraft.plugin.utilities.acUtils;
import net.Indyuce.mmoitems.MMOItems;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class MineTeleport extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;

    public MineTeleport(AdventuresCraft plugin) {
        this.plugin = plugin;
    }

    String ranks = "zyxwvutsrqponmlkjlhgfedcba";

    @CommandAlias("mine")
    public void mineCommand(Player player) {
        for (int i = 0; i < ranks.length(); i++) {
            if (player.hasPermission("mines.tp." + ranks.charAt(i))) {
                player.performCommand("warp " + ranks.charAt(i));
                break;
            }
        }
    }

}

