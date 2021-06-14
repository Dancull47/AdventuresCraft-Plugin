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
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import monzter.adventurescraft.plugin.utilities.mmoitems.DropTablesDelivery;
import monzter.adventurescraft.plugin.utilities.mmoitems.MMOItemsGive;
import net.Indyuce.mmoitems.MMOItems;

public class DropTablesGive extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final MMOItemsGive mmoItemsGive;
    private final SoundManager soundManager;
    private final DropTablesDelivery dropTablesDelivery;
    private final MMOItems mmoItems;


    public DropTablesGive(AdventuresCraft plugin, MMOItemsGive mmoItemsGive, SoundManager soundManager, DropTablesDelivery dropTablesDelivery, MMOItems mmoItems) {
        this.plugin = plugin;
        this.mmoItemsGive = mmoItemsGive;
        this.soundManager = soundManager;
        this.dropTablesDelivery = dropTablesDelivery;
        this.mmoItems = mmoItems;
    }

    @CommandAlias("DropTable")
    @CommandPermission("*")
    @CommandCompletion("*")
    private void dropTable(OnlinePlayer player, String table) {
        dropTable(player, table, 1);
    }

    @CommandAlias("DropTable")
    @CommandPermission("*")
    @CommandCompletion("* hellCrate|undeadCrate|professionCrate")
    private void dropTable(OnlinePlayer player, String table, int amount) {
        for (int i = 0; i < amount; i++) {
            switch (table.toUpperCase()) {
                case "HELL_BOX":
                case "HELLCRATE":
                    RandomSelector<Crates> hellCrate = RandomSelector.weighted((Crates.getCrates(CrateList.HELL)));
                    Crates hellCrateReward = hellCrate.pick();
                    dropTablesDelivery.giveReward(player.getPlayer(), mmoItems.getItem(hellCrateReward.getType(), hellCrateReward.getId()).getItemMeta().getDisplayName(), hellCrateReward.getType(), hellCrateReward.getId(), hellCrateReward.getWeight());
                    break;
                case "UNDEAD_BOX":
                case "UNDEADCRATE":
                    RandomSelector<Crates> undeadCrate = RandomSelector.weighted((Crates.getCrates(CrateList.UNDEAD)));
                    Crates undeadCrateReward = undeadCrate.pick();
                    dropTablesDelivery.giveReward(player.getPlayer(), mmoItems.getItem(undeadCrateReward.getType(), undeadCrateReward.getId()).getItemMeta().getDisplayName(), undeadCrateReward.getType(), undeadCrateReward.getId(), undeadCrateReward.getWeight());
                    break;
                case "PROFESSION_BOOSTER_BOX":
                case "PROFESSIONCRATE":
                    RandomSelector<Crates> professionCrate = RandomSelector.weighted((Crates.getCrates(CrateList.PROFESSION)));
                    Crates professionCrateReward = professionCrate.pick();
                    dropTablesDelivery.giveReward(player.getPlayer(), mmoItems.getItem(professionCrateReward.getType(), professionCrateReward.getId()).getItemMeta().getDisplayName(), professionCrateReward.getType(), professionCrateReward.getId(), professionCrateReward.getWeight());
                    break;
            }
        }
    }
}

