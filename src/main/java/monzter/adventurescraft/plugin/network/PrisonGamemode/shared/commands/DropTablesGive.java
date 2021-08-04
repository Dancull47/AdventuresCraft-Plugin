package monzter.adventurescraft.plugin.network.PrisonGamemode.shared.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Dependency;
import co.aikar.commands.bukkit.contexts.OnlinePlayer;
import me.clip.placeholderapi.PlaceholderAPI;
import me.lucko.helper.random.RandomSelector;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.network.PrisonGamemode.shared.commands.dropTables.DropTableTypes;
import monzter.adventurescraft.plugin.network.PrisonGamemode.shared.commands.dropTables.DropTables;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import monzter.adventurescraft.plugin.utilities.mmoitems.DropTablesDelivery;
import monzter.adventurescraft.plugin.utilities.mmoitems.MMOItemsGive;
import net.Indyuce.mmoitems.MMOItems;
import org.bukkit.ChatColor;

public class DropTablesGive extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final MMOItemsGive mmoItemsGive;
    private final SoundManager soundManager;
    private final DropTablesDelivery dropTablesDelivery;

    public DropTablesGive(AdventuresCraft plugin, MMOItemsGive mmoItemsGive, SoundManager soundManager, DropTablesDelivery dropTablesDelivery) {
        this.plugin = plugin;
        this.mmoItemsGive = mmoItemsGive;
        this.soundManager = soundManager;
        this.dropTablesDelivery = dropTablesDelivery;
    }

    @CommandAlias("DropTable")
    @CommandPermission("*")
    @CommandCompletion("* eggcommon|llama")
    private void dropTable(OnlinePlayer player, String table) {
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
    private void dropTable(OnlinePlayer player, String table, int amount) {
        final double luck = Double.valueOf(PlaceholderAPI.setPlaceholders(player.getPlayer(), "%ac_Stat_LuckMultiplier%"));
        player.getPlayer().sendMessage(ChatColor.YELLOW.toString() + ChatColor.BOLD + "Rewards:");
        for (int i = 0; i < amount; i++) {
            switch (table.toUpperCase()) {
                case "EGGCOMMON":
                    RandomSelector<DropTables> commonPetEgg = RandomSelector.weighted((DropTables.getEggs(DropTableTypes.COMMON_PET_EGG)));
                    DropTables commonDropTablesReward = commonPetEgg.pick();
                    dropTablesDelivery.giveReward(player.getPlayer(), MMOItems.plugin.getItem(commonDropTablesReward.getType(), commonDropTablesReward.getId()).getItemMeta().getDisplayName(), commonDropTablesReward.getType(), commonDropTablesReward.getId(), commonDropTablesReward.getWeight(), commonDropTablesReward.getAmount());
                    break;
                case "EGGUNCOMMON":
                    RandomSelector<DropTables> uncommonPetEgg = RandomSelector.weighted((DropTables.getEggs(DropTableTypes.UNCOMMON_PET_EGG)));
                    DropTables uncommonDropTablesReward = uncommonPetEgg.pick();
                    dropTablesDelivery.giveReward(player.getPlayer(), MMOItems.plugin.getItem(uncommonDropTablesReward.getType(), uncommonDropTablesReward.getId()).getItemMeta().getDisplayName(), uncommonDropTablesReward.getType(), uncommonDropTablesReward.getId(), uncommonDropTablesReward.getWeight(), uncommonDropTablesReward.getAmount());
                    break;
                case "EGGRARE":
                    RandomSelector<DropTables> rarePetEgg = RandomSelector.weighted(DropTables.getEggs(DropTableTypes.RARE_PET_EGG));
                    DropTables rareDropTablesReward = rarePetEgg.pick();
                    dropTablesDelivery.giveReward(player.getPlayer(), MMOItems.plugin.getItem(rareDropTablesReward.getType(), rareDropTablesReward.getId()).getItemMeta().getDisplayName(), rareDropTablesReward.getType(), rareDropTablesReward.getId(), rareDropTablesReward.getWeight(), rareDropTablesReward.getAmount());
                    break;
                case "EGGLEGENDARY":
                    RandomSelector<DropTables> legendaryPetEgg = RandomSelector.weighted(DropTables.getEggs(DropTableTypes.LEGENDARY_PET_EGG));
                    DropTables legendaryDropTablesReward = legendaryPetEgg.pick();
                    dropTablesDelivery.giveReward(player.getPlayer(), MMOItems.plugin.getItem(legendaryDropTablesReward.getType(), legendaryDropTablesReward.getId()).getItemMeta().getDisplayName(), legendaryDropTablesReward.getType(), legendaryDropTablesReward.getId(), legendaryDropTablesReward.getWeight(), legendaryDropTablesReward.getAmount());
                    break;
                case "EGGEXOTIC":
                    RandomSelector<DropTables> exoticPetEgg = RandomSelector.weighted(DropTables.getEggs(DropTableTypes.EXOTIC_PET_EGG));
                    DropTables exoticDropTablesReward = exoticPetEgg.pick();
                    dropTablesDelivery.giveReward(player.getPlayer(), MMOItems.plugin.getItem(exoticDropTablesReward.getType(), exoticDropTablesReward.getId()).getItemMeta().getDisplayName(), exoticDropTablesReward.getType(), exoticDropTablesReward.getId(), exoticDropTablesReward.getWeight(), exoticDropTablesReward.getAmount());
                    break;
                case "EGGMYTHICAL":
                    RandomSelector<DropTables> mythicalPetEgg = RandomSelector.weighted(DropTables.getEggs(DropTableTypes.MYTHICAL_PET_EGG));
                    DropTables mythicalDropTablesReward = mythicalPetEgg.pick();
                    dropTablesDelivery.giveReward(player.getPlayer(), MMOItems.plugin.getItem(mythicalDropTablesReward.getType(), mythicalDropTablesReward.getId()).getItemMeta().getDisplayName(), mythicalDropTablesReward.getType(), mythicalDropTablesReward.getId(), mythicalDropTablesReward.getWeight(), mythicalDropTablesReward.getAmount());
                    break;
                case "EGGGODLY":
                    RandomSelector<DropTables> godlyPetEgg = RandomSelector.weighted(DropTables.getEggs(DropTableTypes.GODLY_PET_EGG));
                    DropTables godlyDropTablesReward = godlyPetEgg.pick();
                    dropTablesDelivery.giveReward(player.getPlayer(), MMOItems.plugin.getItem(godlyDropTablesReward.getType(), godlyDropTablesReward.getId()).getItemMeta().getDisplayName(), godlyDropTablesReward.getType(), godlyDropTablesReward.getId(), godlyDropTablesReward.getWeight(), godlyDropTablesReward.getAmount());
                    break;

                case "LOOTBOX":
                    RandomSelector<DropTables> lootbox = RandomSelector.weighted(DropTables.getEggs(DropTableTypes.COMMON_LOOTBOX));
                    DropTables lootboxReward = lootbox.pick();
                    dropTablesDelivery.giveReward(player.getPlayer(), MMOItems.plugin.getItem(lootboxReward.getType(), lootboxReward.getId()).getItemMeta().getDisplayName(), lootboxReward.getType(), lootboxReward.getId(), lootboxReward.getWeight(), lootboxReward.getAmount());
                    break;
                case "LOOTBOX2":
                    RandomSelector<DropTables> lootbox2 = RandomSelector.weighted(DropTables.getEggs(DropTableTypes.UNCOMMON_LOOTBOX));
                    DropTables lootbox2Reward = lootbox2.pick();
                    dropTablesDelivery.giveReward(player.getPlayer(), MMOItems.plugin.getItem(lootbox2Reward.getType(), lootbox2Reward.getId()).getItemMeta().getDisplayName(), lootbox2Reward.getType(), lootbox2Reward.getId(), lootbox2Reward.getWeight(), lootbox2Reward.getAmount());
                    break;
                case "LOOTBOX3":
                    RandomSelector<DropTables> lootbox3 = RandomSelector.weighted(DropTables.getEggs(DropTableTypes.RARE_LOOTBOX));
                    DropTables lootbox3Reward = lootbox3.pick();
                    dropTablesDelivery.giveReward(player.getPlayer(), MMOItems.plugin.getItem(lootbox3Reward.getType(), lootbox3Reward.getId()).getItemMeta().getDisplayName(), lootbox3Reward.getType(), lootbox3Reward.getId(), lootbox3Reward.getWeight(), lootbox3Reward.getAmount());
                    break;
                case "LOOTBOX4":
                    RandomSelector<DropTables> lootbox4 = RandomSelector.weighted(DropTables.getEggs(DropTableTypes.LEGENDARY_LOOTBOX));
                    DropTables lootbox4Reward = lootbox4.pick();
                    dropTablesDelivery.giveReward(player.getPlayer(), MMOItems.plugin.getItem(lootbox4Reward.getType(), lootbox4Reward.getId()).getItemMeta().getDisplayName(), lootbox4Reward.getType(), lootbox4Reward.getId(), lootbox4Reward.getWeight(), lootbox4Reward.getAmount());
                    break;
                case "LOOTBOX5":
                    RandomSelector<DropTables> lootbox5 = RandomSelector.weighted(DropTables.getEggs(DropTableTypes.EXOTIC_LOOTBOX));
                    DropTables lootbox5Reward = lootbox5.pick();
                    dropTablesDelivery.giveReward(player.getPlayer(), MMOItems.plugin.getItem(lootbox5Reward.getType(), lootbox5Reward.getId()).getItemMeta().getDisplayName(), lootbox5Reward.getType(), lootbox5Reward.getId(), lootbox5Reward.getWeight(), lootbox5Reward.getAmount());
                    break;
                case "LOOTBOX6":
                    RandomSelector<DropTables> lootbox6 = RandomSelector.weighted(DropTables.getEggs(DropTableTypes.MYTHICAL_LOOTBOX));
                    DropTables lootbox6Reward = lootbox6.pick();
                    dropTablesDelivery.giveReward(player.getPlayer(), MMOItems.plugin.getItem(lootbox6Reward.getType(), lootbox6Reward.getId()).getItemMeta().getDisplayName(), lootbox6Reward.getType(), lootbox6Reward.getId(), lootbox6Reward.getWeight(), lootbox6Reward.getAmount());
                    break;
                case "LOOTBOX7":
                    RandomSelector<DropTables> lootbox7 = RandomSelector.weighted(DropTables.getEggs(DropTableTypes.GODLY_LOOTBOX));
                    DropTables lootbox7Reward = lootbox7.pick();
                    dropTablesDelivery.giveReward(player.getPlayer(), MMOItems.plugin.getItem(lootbox7Reward.getType(), lootbox7Reward.getId()).getItemMeta().getDisplayName(), lootbox7Reward.getType(), lootbox7Reward.getId(), lootbox7Reward.getWeight(), lootbox7Reward.getAmount());
                    break;

                case "VOTE":
                    RandomSelector<DropTables> vote = RandomSelector.weighted(DropTables.getEggs(DropTableTypes.VOTE));
                    DropTables voteReward = vote.pick();
                    dropTablesDelivery.giveReward(player.getPlayer(), MMOItems.plugin.getItem(voteReward.getType(), voteReward.getId()).getItemMeta().getDisplayName(), voteReward.getType(), voteReward.getId(), voteReward.getWeight(), voteReward.getAmount());
                    break;

                case "LLAMA":
                    RandomSelector<DropTables> lootLlama = RandomSelector.weighted(DropTables.getEggs(DropTableTypes.LLAMA));
                    DropTables lootLlamaReward = lootLlama.pick();
                    dropTablesDelivery.giveReward(player.getPlayer(), MMOItems.plugin.getItem(lootLlamaReward.getType(), lootLlamaReward.getId()).getItemMeta().getDisplayName(), lootLlamaReward.getType(), lootLlamaReward.getId(), lootLlamaReward.getWeight(), lootLlamaReward.getAmount());
                    break;
            }
        }
    }
}

