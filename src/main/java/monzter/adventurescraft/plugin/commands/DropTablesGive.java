package monzter.adventurescraft.plugin.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import co.aikar.commands.bukkit.contexts.OnlinePlayer;
import com.vexsoftware.votifier.model.Vote;
import me.clip.placeholderapi.PlaceholderAPI;
import me.lucko.helper.random.RandomSelector;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.commands.dropTables.Eggs.*;
//import monzter.adventurescraft.plugin.event.TestInv;
import monzter.adventurescraft.plugin.commands.dropTables.Eggs.Lootbox;
import monzter.adventurescraft.plugin.commands.dropTables.LootLlama;
import monzter.adventurescraft.plugin.commands.dropTables.Lootboxes.*;
import monzter.adventurescraft.plugin.utilities.MMOItemsGiveItem;
import monzter.adventurescraft.plugin.utilities.Rarity;
import monzter.adventurescraft.plugin.utilities.SoundManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class DropTablesGive extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final MMOItemsGiveItem mmoItemsGiveItem;
    private final SoundManager soundManager;

    public DropTablesGive(AdventuresCraft plugin, MMOItemsGiveItem mmoItemsGiveItem, SoundManager soundManager) {
        this.plugin = plugin;
        this.mmoItemsGiveItem = mmoItemsGiveItem;
        this.soundManager = soundManager;
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
                    RandomSelector<PetEgg> commonPetEgg = RandomSelector.weighted((PetEgg.getEggs(Rarity.COMMON)));
                    PetEgg commonPetEggReward = commonPetEgg.pick();
                    giveReward(player.getPlayer(), commonPetEggReward.getDisplayName(), commonPetEggReward.getType(), commonPetEggReward.getId(), commonPetEggReward.getWeight());
                    break;
                case "EGGUNCOMMON":
                    RandomSelector<PetEgg> uncommonPetEgg = RandomSelector.weighted((PetEgg.getEggs(Rarity.UNCOMMON)));
                    PetEgg uncommonPetEggReward = uncommonPetEgg.pick();
                    giveReward(player.getPlayer(), uncommonPetEggReward.getDisplayName(), uncommonPetEggReward.getType(), uncommonPetEggReward.getId(), uncommonPetEggReward.getWeight());
                    break;
                case "EGGRARE":
                    RandomSelector<PetEgg> rarePetEgg = RandomSelector.weighted(PetEgg.getEggs(Rarity.RARE));
                    PetEgg rarePetEggReward = rarePetEgg.pick();
                    giveReward(player.getPlayer(), rarePetEggReward.getDisplayName(), rarePetEggReward.getType(), rarePetEggReward.getId(), rarePetEggReward.getWeight());
                    break;
                case "EGGLEGENDARY":
                    RandomSelector<PetEgg> legendaryPetEgg = RandomSelector.weighted(PetEgg.getEggs(Rarity.LEGENDARY));
                    PetEgg legendaryPetEggReward = legendaryPetEgg.pick();
                    giveReward(player.getPlayer(), legendaryPetEggReward.getDisplayName(), legendaryPetEggReward.getType(), legendaryPetEggReward.getId(), legendaryPetEggReward.getWeight());
                    break;
                case "EGGEXOTIC":
                    RandomSelector<PetEgg> exoticPetEgg = RandomSelector.weighted(PetEgg.getEggs(Rarity.EXOTIC));
                    PetEgg exoticPetEggReward = exoticPetEgg.pick();
                    giveReward(player.getPlayer(), exoticPetEggReward.getDisplayName(), exoticPetEggReward.getType(), exoticPetEggReward.getId(), exoticPetEggReward.getWeight());
                    break;
//                case "EGGMYTHICAL":
//                    RandomSelector<mythicalPetEgg> mythicalPetEgg = RandomSelector.weighted(Arrays.asList(mythicalPetEgg.values()));
//                    mythicalPetEgg mythicalPetEggReward = mythicalPetEgg.pick();
//                    giveReward(player.getPlayer(), mythicalPetEggReward.getDisplayName(), mythicalPetEggReward.getType(), mythicalPetEggReward.getId(), mythicalPetEggReward.getWeight());
//                    break;
//                case "EGGGODLY":
//                    RandomSelector<godlyPetEgg> godlyPetEgg = RandomSelector.weighted(Arrays.asList(godlyPetEgg.values()));
//                    godlyPetEgg godlyPetEggReward = godlyPetEgg.pick();
//                    giveReward(player.getPlayer(), godlyPetEggReward.getDisplayName(), godlyPetEggReward.getType(), godlyPetEggReward.getId(), godlyPetEggReward.getWeight());
//                    break;

                case "LOOTBOX":
                    RandomSelector<Lootbox> lootbox = RandomSelector.weighted(Lootbox.getLootbox(Rarity.COMMON));
                    Lootbox lootboxReward = lootbox.pick();
                    giveReward(player.getPlayer(), lootboxReward.getDisplayName(), lootboxReward.getType(), lootboxReward.getId(), lootboxReward.getWeight());
                    break;
                case "LOOTBOX2":
                    RandomSelector<Lootbox> lootbox2 = RandomSelector.weighted(Lootbox.getLootbox(Rarity.UNCOMMON));
                    Lootbox lootbox2Reward = lootbox2.pick();
                    giveReward(player.getPlayer(), lootbox2Reward.getDisplayName(), lootbox2Reward.getType(), lootbox2Reward.getId(), lootbox2Reward.getWeight());
                    break;
                case "LOOTBOX3":
                    RandomSelector<Lootbox> lootbox3 = RandomSelector.weighted(Lootbox.getLootbox(Rarity.RARE));
                    Lootbox lootbox3Reward = lootbox3.pick();
                    giveReward(player.getPlayer(), lootbox3Reward.getDisplayName(), lootbox3Reward.getType(), lootbox3Reward.getId(), lootbox3Reward.getWeight());
                    break;
                case "LOOTBOX4":
                    RandomSelector<Lootbox> lootbox4 = RandomSelector.weighted(Lootbox.getLootbox(Rarity.LEGENDARY));
                    Lootbox lootbox4Reward = lootbox4.pick();
                    giveReward(player.getPlayer(), lootbox4Reward.getDisplayName(), lootbox4Reward.getType(), lootbox4Reward.getId(), lootbox4Reward.getWeight());
                    break;
                case "LOOTBOX5":
                    RandomSelector<Lootbox> lootbox5 = RandomSelector.weighted(Lootbox.getLootbox(Rarity.EXOTIC));
                    Lootbox lootbox5Reward = lootbox5.pick();
                    giveReward(player.getPlayer(), lootbox5Reward.getDisplayName(), lootbox5Reward.getType(), lootbox5Reward.getId(), lootbox5Reward.getWeight());
                    break;
                case "LOOTBOX6":
                    RandomSelector<Lootbox> lootbox6 = RandomSelector.weighted(Lootbox.getLootbox(Rarity.MYTHICAL));
                    Lootbox lootbox6Reward = lootbox6.pick();
                    giveReward(player.getPlayer(), lootbox6Reward.getDisplayName(), lootbox6Reward.getType(), lootbox6Reward.getId(), lootbox6Reward.getWeight());
                    break;
                case "LOOTBOX7":
                    RandomSelector<Lootbox> lootbox7 = RandomSelector.weighted(Lootbox.getLootbox(Rarity.GODLY));
                    Lootbox lootbox7Reward = lootbox7.pick();
                    giveReward(player.getPlayer(), lootbox7Reward.getDisplayName(), lootbox7Reward.getType(), lootbox7Reward.getId(), lootbox7Reward.getWeight());
                    break;

//                case "VOTE":
//                    RandomSelector<Vote> vote = RandomSelector.weighted(Arrays.asList(Vote.values()));
//                    Vote voteReward = vote.pick();
//                    giveReward(player.getPlayer(), voteReward.getDisplayName(), voteReward.getType(), voteReward.id, voteReward.getWeight());
//                    break;
//
//                case "LLAMA":
//                    RandomSelector<LootLlama> lootLlama = RandomSelector.weighted(Arrays.asList(LootLlama.values()));
//                    LootLlama lootLlamaReward = lootLlama.pick();
//                    giveReward(player.getPlayer(), lootLlamaReward.getDisplayName(), lootLlamaReward.getType(), lootLlamaReward.id, lootLlamaReward.getWeight());
//                    break;
            }
        }
    }

    private void giveReward(Player player, String displayName, String rewardType, String rewardName, double chance) {
        double multipliedChance = chance*100;
        player.sendMessage(ChatColor.YELLOW.toString() + ChatColor.BOLD + "Rewards:");
        mmoItemsGiveItem.giveMMOItem(player, rewardType, rewardName);
        if (multipliedChance < 50 && multipliedChance >= 25) {
            player.sendMessage(displayName + " " + ChatColor.BLUE + ChatColor.BOLD + multipliedChance + "% RARE!");
            soundManager.playSound(player, Sound.ENTITY_WITCH_CELEBRATE, 1, 1);
        } else if (multipliedChance < 25 && multipliedChance >= 2) {
            player.sendMessage(displayName + " " + ChatColor.YELLOW + ChatColor.BOLD + multipliedChance + "% VERY RARE!");
            soundManager.playSound(player, Sound.ENTITY_VINDICATOR_CELEBRATE, 1, 1);
        } else if (multipliedChance < 2) {
            player.sendMessage(displayName + " " + ChatColor.DARK_RED + ChatColor.BOLD + multipliedChance + "% INSANE!!!");
            player.sendTitle(ChatColor.RED.toString() + ChatColor.BOLD + "INSANE!!!", displayName, 20, 100, 20);
            soundManager.playSound(player, Sound.ENTITY_RAVAGER_CELEBRATE, 1, 1);
            Bukkit.broadcastMessage(ChatColor.GOLD + player.getName() + ChatColor.GREEN + " just found a " + displayName + " " + ChatColor.DARK_RED + ChatColor.BOLD + multipliedChance + "% INSANE!!!");
        } else {
            player.sendMessage(displayName + " " + ChatColor.GREEN + multipliedChance + "%");
        }
    }
}

