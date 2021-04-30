package monzter.adventurescraft.plugin.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import co.aikar.commands.bukkit.contexts.OnlinePlayer;
import me.clip.placeholderapi.PlaceholderAPI;
import me.lucko.helper.random.RandomSelector;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.commands.dropTables.Eggs.*;
//import monzter.adventurescraft.plugin.event.TestInv;
import monzter.adventurescraft.plugin.commands.dropTables.LootLlama;
import monzter.adventurescraft.plugin.commands.dropTables.Lootboxes.*;
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
    @CommandCompletion("* eggcommon|llama")
    public void dropTable(OnlinePlayer player, String table) {
        dropTable(player, table, 1);
    }
/*
*
*       Could create a "Inventory Full" system, give player points, run CLAIM command to redeem points?
*
*/
    @CommandAlias("DropTable")
    @CommandPermission("*")
    @CommandCompletion("* eggcommon|egguncommon|eggrare|egglegendary|eggexotic|lootbox|lootbox2|lootbox3|lootbox4|lootbox5|lootbox6|lootbox7|llama|vote")
    public void dropTable(OnlinePlayer player, String table, int amount) {
        double luck = Double.valueOf(PlaceholderAPI.setPlaceholders(player.getPlayer(), "%ac_Stat_LuckMultiplier%"));
        for (int i = 0; i < amount; i++) {
            switch (table.toUpperCase()) {
                case "EGGCOMMON":
                    RandomSelector<CommonPetEgg> commonPetEgg = RandomSelector.weighted(Arrays.asList(CommonPetEgg.values()));
                    CommonPetEgg commonPetEggReward = commonPetEgg.pick();
                    giveReward(player.getPlayer(), commonPetEggReward.displayName, commonPetEggReward.type, commonPetEggReward.id, commonPetEggReward.getWeight());
                    break;
                case "EGGUNCOMMON":
                    RandomSelector<UncommonPetEgg> uncommonPetEgg = RandomSelector.weighted(Arrays.asList(UncommonPetEgg.values()));
                    UncommonPetEgg uncommonPetEggReward = uncommonPetEgg.pick();
                    giveReward(player.getPlayer(), uncommonPetEggReward.displayName, uncommonPetEggReward.type, uncommonPetEggReward.id, uncommonPetEggReward.getWeight());
                    break;
                case "EGGRARE":
                    RandomSelector<RarePetEgg> rarePetEgg = RandomSelector.weighted(Arrays.asList(RarePetEgg.values()));
                    RarePetEgg rarePetEggReward = rarePetEgg.pick();
                    giveReward(player.getPlayer(), rarePetEggReward.displayName, rarePetEggReward.type, rarePetEggReward.id, rarePetEggReward.getWeight());
                    break;
                case "EGGLEGENDARY":
                    RandomSelector<LegendaryPetEgg> legendaryPetEgg = RandomSelector.weighted(Arrays.asList(LegendaryPetEgg.values()));
                    LegendaryPetEgg legendaryPetEggReward = legendaryPetEgg.pick();
                    giveReward(player.getPlayer(), legendaryPetEggReward.displayName, legendaryPetEggReward.type, legendaryPetEggReward.id, legendaryPetEggReward.getWeight());
                    break;
                case "EGGEXOTIC":
                    RandomSelector<ExoticPetEgg> exoticPetEgg = RandomSelector.weighted(Arrays.asList(ExoticPetEgg.values()));
                    ExoticPetEgg exoticPetEggReward = exoticPetEgg.pick();
                    giveReward(player.getPlayer(), exoticPetEggReward.displayName, exoticPetEggReward.type, exoticPetEggReward.id, exoticPetEggReward.getWeight());
                    break;
//                case "EGGMYTHICAL":
//                    RandomSelector<mythicalPetEgg> mythicalPetEgg = RandomSelector.weighted(Arrays.asList(mythicalPetEgg.values()));
//                    mythicalPetEgg mythicalPetEggReward = mythicalPetEgg.pick();
//                    giveReward(player.getPlayer(), mythicalPetEggReward.displayName, mythicalPetEggReward.type, mythicalPetEggReward.id, mythicalPetEggReward.getWeight());
//                    break;
//                case "EGGGODLY":
//                    RandomSelector<godlyPetEgg> godlyPetEgg = RandomSelector.weighted(Arrays.asList(godlyPetEgg.values()));
//                    godlyPetEgg godlyPetEggReward = godlyPetEgg.pick();
//                    giveReward(player.getPlayer(), godlyPetEggReward.displayName, godlyPetEggReward.type, godlyPetEggReward.id, godlyPetEggReward.getWeight());
//                    break;

                case "LOOTBOX":
                    RandomSelector<Lootbox> lootbox = RandomSelector.weighted(Arrays.asList(Lootbox.values()));
                    Lootbox lootboxReward = lootbox.pick();
                    giveReward(player.getPlayer(), lootboxReward.displayName, lootboxReward.type, lootboxReward.id, lootboxReward.getWeight());
                    break;
                case "LOOTBOX2":
                    RandomSelector<Lootbox2> lootbox2 = RandomSelector.weighted(Arrays.asList(Lootbox2.values()));
                    Lootbox2 lootbox2Reward = lootbox2.pick();
                    giveReward(player.getPlayer(), lootbox2Reward.displayName, lootbox2Reward.type, lootbox2Reward.id, lootbox2Reward.getWeight());
                    break;
                case "LOOTBOX3":
                    RandomSelector<Lootbox3> lootbox3 = RandomSelector.weighted(Arrays.asList(Lootbox3.values()));
                    Lootbox3 lootbox3Reward = lootbox3.pick();
                    giveReward(player.getPlayer(), lootbox3Reward.displayName, lootbox3Reward.type, lootbox3Reward.id, lootbox3Reward.getWeight());
                    break;
                case "LOOTBOX4":
                    RandomSelector<Lootbox4> lootbox4 = RandomSelector.weighted(Arrays.asList(Lootbox4.values()));
                    Lootbox4 lootbox4Reward = lootbox4.pick();
                    giveReward(player.getPlayer(), lootbox4Reward.displayName, lootbox4Reward.type, lootbox4Reward.id, lootbox4Reward.getWeight());
                    break;
                case "LOOTBOX5":
                    RandomSelector<Lootbox5> lootbox5 = RandomSelector.weighted(Arrays.asList(Lootbox5.values()));
                    Lootbox5 lootbox5Reward = lootbox5.pick();
                    giveReward(player.getPlayer(), lootbox5Reward.displayName, lootbox5Reward.type, lootbox5Reward.id, lootbox5Reward.getWeight());
                    break;
                case "LOOTBOX6":
                    RandomSelector<Lootbox6> lootbox6 = RandomSelector.weighted(Arrays.asList(Lootbox6.values()));
                    Lootbox6 lootbox6Reward = lootbox6.pick();
                    giveReward(player.getPlayer(), lootbox6Reward.displayName, lootbox6Reward.type, lootbox6Reward.id, lootbox6Reward.getWeight());
                    break;
                case "LOOTBOX7":
                    RandomSelector<Lootbox7> lootbox7 = RandomSelector.weighted(Arrays.asList(Lootbox7.values()));
                    Lootbox7 lootbox7Reward = lootbox7.pick();
                    giveReward(player.getPlayer(), lootbox7Reward.displayName, lootbox7Reward.type, lootbox7Reward.id, lootbox7Reward.getWeight());
                    break;

                case "VOTE":
                    RandomSelector<Vote> vote = RandomSelector.weighted(Arrays.asList(Vote.values()));
                    Vote voteReward = vote.pick();
                    giveReward(player.getPlayer(), voteReward.displayName, voteReward.type, voteReward.id, voteReward.getWeight());
                    break;

                case "LLAMA":
                    RandomSelector<LootLlama> lootLlama = RandomSelector.weighted(Arrays.asList(LootLlama.values()));
                    LootLlama lootLlamaReward = lootLlama.pick();
                    giveReward(player.getPlayer(), lootLlamaReward.displayName, lootLlamaReward.type, lootLlamaReward.id, lootLlamaReward.getWeight());
                    break;
            }
        }
    }

    private void giveReward(Player player, String displayName, String rewardType, String rewardName, double chance) {
        double multipliedChance = chance*100;
        player.sendMessage(ChatColor.YELLOW.toString() + ChatColor.BOLD + "Rewards:");
        acUtils.giveMMOItem(player, rewardType, rewardName);
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

