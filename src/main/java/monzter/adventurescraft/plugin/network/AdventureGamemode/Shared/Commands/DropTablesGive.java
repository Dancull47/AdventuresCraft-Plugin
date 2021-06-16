package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.Commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Dependency;
import co.aikar.commands.bukkit.contexts.OnlinePlayer;
import me.lucko.helper.random.RandomSelector;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.Commands.DropTables.Crates;
import monzter.adventurescraft.plugin.utilities.enums.CrateList;
import monzter.adventurescraft.plugin.utilities.general.ChanceCheck;
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
    private final MMOItems mmoItems;
    private final ChanceCheck chanceCheck;


    public DropTablesGive(AdventuresCraft plugin, MMOItemsGive mmoItemsGive, SoundManager soundManager, DropTablesDelivery dropTablesDelivery, MMOItems mmoItems, ChanceCheck chanceCheck) {
        this.plugin = plugin;
        this.mmoItemsGive = mmoItemsGive;
        this.soundManager = soundManager;
        this.dropTablesDelivery = dropTablesDelivery;
        this.mmoItems = mmoItems;
        this.chanceCheck = chanceCheck;
    }

    @CommandAlias("DropTable")
    @CommandPermission("*")
    @CommandCompletion("*")
    private void dropTable(OnlinePlayer player, String table) {
        dropTable(player, table, 1);
    }

    @CommandAlias("DropTable")
    @CommandPermission("*")
    @CommandCompletion("* hellCrate|undeadCrate|professionCrate|magicalCrate|borgsCrate|ENCHANTED_BOX|ENCHANTED_BOX2|ENCHANTED_BOX3")
    private void dropTable(OnlinePlayer player, String table, int amount) {
        player.getPlayer().sendMessage(ChatColor.YELLOW.toString() + ChatColor.BOLD + "Rewards:");
        for (int i = 0; i < amount; i++) {
            switch (table.toUpperCase()) {
                case "HELL_BOX":
                case "HELLCRATE":
                    RandomSelector<Crates> hellCrate = RandomSelector.weighted((Crates.getCrates(CrateList.HELL)));
                    Crates hellCrateReward = hellCrate.pick();
                    dropTablesDelivery.giveReward(player.getPlayer(), mmoItems.getItem(hellCrateReward.getType(), hellCrateReward.getId()).getItemMeta().getDisplayName(), hellCrateReward.getType(), hellCrateReward.getId(), hellCrateReward.getWeight(), hellCrateReward.getAmount());
                    break;
                case "UNDEAD_BOX":
                case "UNDEADCRATE":
                    RandomSelector<Crates> undeadCrate = RandomSelector.weighted((Crates.getCrates(CrateList.UNDEAD)));
                    Crates undeadCrateReward = undeadCrate.pick();
                    dropTablesDelivery.giveReward(player.getPlayer(), mmoItems.getItem(undeadCrateReward.getType(), undeadCrateReward.getId()).getItemMeta().getDisplayName(), undeadCrateReward.getType(), undeadCrateReward.getId(), undeadCrateReward.getWeight(), undeadCrateReward.getAmount());
                    break;
                case "PROFESSION_BOOSTER_BOX":
                case "PROFESSIONCRATE":
                    RandomSelector<Crates> professionCrate = RandomSelector.weighted((Crates.getCrates(CrateList.PROFESSION)));
                    Crates professionCrateReward = professionCrate.pick();
                    dropTablesDelivery.giveReward(player.getPlayer(), mmoItems.getItem(professionCrateReward.getType(), professionCrateReward.getId()).getItemMeta().getDisplayName(), professionCrateReward.getType(), professionCrateReward.getId(), professionCrateReward.getWeight(), professionCrateReward.getAmount());
                    break;
                case "MAGICALCRATE":
                case "MAGICAL_BOX":
                    RandomSelector<Crates> magicalCrate = RandomSelector.weighted((Crates.getCrates(CrateList.MAGICAL)));
                    Crates magicalCrateReward = magicalCrate.pick();
                    dropTablesDelivery.giveReward(player.getPlayer(), mmoItems.getItem(magicalCrateReward.getType(), magicalCrateReward.getId()).getItemMeta().getDisplayName(), magicalCrateReward.getType(), magicalCrateReward.getId(), magicalCrateReward.getWeight(), magicalCrateReward.getAmount());
                    break;
                case "BORGCRATE":
                case "BORGSCRATE":
                case "BORGS_BOX":
                    RandomSelector<Crates> borgCrate = RandomSelector.weighted((Crates.getCrates(CrateList.BORG)));
                    Crates borgCrateReward = borgCrate.pick();
                    dropTablesDelivery.giveReward(player.getPlayer(), mmoItems.getItem(borgCrateReward.getType(), borgCrateReward.getId()).getItemMeta().getDisplayName(), borgCrateReward.getType(), borgCrateReward.getId(), borgCrateReward.getWeight(), borgCrateReward.getAmount());
                    break;
                case "ENCHANTED_BOX":
                    RandomSelector<Crates> enchantedCrate = RandomSelector.weighted((Crates.getCrates(CrateList.ENCHANTED_BOX)));
                    Crates enchantedCrateReward = enchantedCrate.pick();
                    dropTablesDelivery.giveReward(player.getPlayer(), mmoItems.getItem(enchantedCrateReward.getType(), enchantedCrateReward.getId()).getItemMeta().getDisplayName(), enchantedCrateReward.getType(), enchantedCrateReward.getId(), enchantedCrateReward.getWeight(), enchantedCrateReward.getAmount());
                    break;
                case "ENCHANTED_BOX2":
                    RandomSelector<Crates> enchantedCrate2 = RandomSelector.weighted((Crates.getCrates(CrateList.ENCHANTED_BOX2)));
                    Crates enchantedCrate2Reward = enchantedCrate2.pick();
                    dropTablesDelivery.giveReward(player.getPlayer(), mmoItems.getItem(enchantedCrate2Reward.getType(), enchantedCrate2Reward.getId()).getItemMeta().getDisplayName(), enchantedCrate2Reward.getType(), enchantedCrate2Reward.getId(), enchantedCrate2Reward.getWeight(), enchantedCrate2Reward.getAmount());
                    break;
                case "ENCHANTED_BOX3":
                    RandomSelector<Crates> enchantedCrate3 = RandomSelector.weighted((Crates.getCrates(CrateList.ENCHANTED_BOX3)));
                    Crates enchantedCrate3Reward = enchantedCrate3.pick();
                    dropTablesDelivery.giveReward(player.getPlayer(), mmoItems.getItem(enchantedCrate3Reward.getType(), enchantedCrate3Reward.getId()).getItemMeta().getDisplayName(), enchantedCrate3Reward.getType(), enchantedCrate3Reward.getId(), enchantedCrate3Reward.getWeight(), enchantedCrate3Reward.getAmount());
                    break;
                case "REAPER":
                    for (Crates crates : Crates.getCrates(CrateList.REAPER))
                        if (chanceCheck.chanceCheck(crates.getWeight()))
                            dropTablesDelivery.giveReward(player.getPlayer(), mmoItems.getItem(crates.getType(), crates.getId()).getItemMeta().getDisplayName(), crates.getType(), crates.getId(), crates.getWeight(), crates.getAmount());
                    break;
                case "MORDEN":
                    for (Crates crates : Crates.getCrates(CrateList.MORDEN))
                        if (chanceCheck.chanceCheck(crates.getWeight()))
                            dropTablesDelivery.giveReward(player.getPlayer(), mmoItems.getItem(crates.getType(), crates.getId()).getItemMeta().getDisplayName(), crates.getType(), crates.getId(), crates.getWeight(), crates.getAmount());
                    break;
            }
        }
    }
}

