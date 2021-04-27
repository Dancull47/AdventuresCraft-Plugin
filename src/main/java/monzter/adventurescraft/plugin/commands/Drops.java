package monzter.adventurescraft.plugin.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import co.aikar.commands.bukkit.contexts.OnlinePlayer;
import me.clip.placeholderapi.PlaceholderAPI;
import me.lucko.helper.random.RandomSelector;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.commands.dropTables.CommonPetEgg;
//import monzter.adventurescraft.plugin.event.TestInv;
import monzter.adventurescraft.plugin.utilities.acUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class Drops extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;

    public Drops(AdventuresCraft plugin) {
        this.plugin = plugin;
    }

    @CommandAlias("DropTable")
    @CommandPermission("*")
    public void dropTable(OnlinePlayer player, String table) {
        dropTable(player, table, 1);
    }

    @CommandAlias("DropTable")
    @CommandPermission("*")
    @CommandCompletion("* eggcommon")
    public void dropTable(OnlinePlayer player, String table, int amount) {
        double luck = Double.valueOf(PlaceholderAPI.setPlaceholders(player.getPlayer(), "%ac_Stat_LuckMultiplier%"));
        for (int i = 0; i < amount; i++) {
            switch (table.toUpperCase()) {
                case "EGGCOMMON":
                    RandomSelector<CommonPetEgg> selector = RandomSelector.weighted(Arrays.asList(CommonPetEgg.values()));
                    CommonPetEgg reward = selector.pick();
                    giveReward(player.getPlayer(), reward.displayName, reward.type, reward.id, reward.getWeight());
                    break;
//                case "TEST":
//                    TestInv gui = new TestInv(player);
//                    gui.open();
            }
        }
    }

    private void giveReward(Player player, String displayName, String rewardType, String rewardName, double chance) {
        double multipliedChance = chance*100;
        player.sendMessage(ChatColor.YELLOW.toString() + ChatColor.BOLD + "Rewards:");
        acUtils.giveMMOItem(player, rewardType, rewardName, true);
        if (multipliedChance < 50 && multipliedChance >= 25) {
            player.sendMessage(displayName + " " + ChatColor.BLUE + ChatColor.BOLD + multipliedChance + "% RARE!");
            acUtils.playSound(player, Sound.ENTITY_WITCH_CELEBRATE, 1, 1);
        } else if (multipliedChance < 25 && multipliedChance >= 2) {
            player.sendMessage(displayName + " " + ChatColor.YELLOW + ChatColor.BOLD + multipliedChance + "% VERY RARE!");
            acUtils.playSound(player, Sound.ENTITY_VINDICATOR_CELEBRATE, 1, 1);
        } else if (multipliedChance < 2) {
            player.sendMessage(displayName + " " + ChatColor.DARK_RED + ChatColor.BOLD + multipliedChance + "% INSANE!!!");
            player.sendTitle(ChatColor.RED.toString() + ChatColor.BOLD + "INSANE!!!", displayName, 20, 100, 20);
            acUtils.playSound(player, Sound.ENTITY_RAVAGER_CELEBRATE, 1, 1);
            Bukkit.broadcastMessage(ChatColor.GOLD + player.getName() + ChatColor.GREEN + " just found a " + displayName + " " + ChatColor.DARK_RED + ChatColor.BOLD + multipliedChance + "% INSANE!!!");
        } else {
            player.sendMessage(displayName + " " + ChatColor.GREEN + multipliedChance + "%");
        }
    }
}

