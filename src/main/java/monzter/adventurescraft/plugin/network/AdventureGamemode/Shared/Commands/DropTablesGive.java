package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.Commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Dependency;
import co.aikar.commands.bukkit.contexts.OnlinePlayer;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.Commands.DropTables.DropTables;
import monzter.adventurescraft.plugin.utilities.enums.CrateList;
import monzter.adventurescraft.plugin.utilities.general.ChanceCheck;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import monzter.adventurescraft.plugin.utilities.mmoitems.DropTablesDelivery;
import monzter.adventurescraft.plugin.utilities.mmoitems.MMOItemsGive;
import net.Indyuce.mmoitems.MMOItems;
import org.bukkit.ChatColor;

import java.util.Random;

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
    @CommandCompletion("* hellCrate|undeadCrate|professionCrate|magicalCrate|borgsCrate|ENCHANTED_BOX|ENCHANTED_BOX2|ENCHANTED_BOX3|reaper|morden|VOID_DRACULA|dryad|GOBLIN_CHIEF|VOID_WITHER|VOID_MAGMA|ghastly|bulblin|bullbo|enchantress")
    private void dropTable(OnlinePlayer player, String table, int amount) {
        player.getPlayer().sendMessage(ChatColor.YELLOW.toString() + ChatColor.BOLD + "Rewards:");
        for (int i = 0; i < amount; i++) {
            switch (table.toUpperCase()) {
//                case "HELL_BOX":
//                case "HELL_BOX5":
//                case "HELLCRATE":
//                    RandomSelector<DropTables> hellCrate = RandomSelector.weighted((DropTables.getCrates(CrateList.HELL)));
//                    DropTables hellCrateReward = hellCrate.pick();
//                    dropTablesDelivery.giveReward(player.getPlayer(), mmoItems.getItem(hellCrateReward.getType(), hellCrateReward.getId()).getItemMeta().getDisplayName(), hellCrateReward.getType(), hellCrateReward.getId(), hellCrateReward.getWeight(), hellCrateReward.getAmount());
//                    break;
//                case "UNDEAD_BOX":
//                case "UNDEAD_BOX5":
//                case "UNDEADCRATE":
//                    RandomSelector<DropTables> undeadCrate = RandomSelector.weighted((DropTables.getCrates(CrateList.UNDEAD)));
//                    DropTables undeadCrateReward = undeadCrate.pick();
//                    dropTablesDelivery.giveReward(player.getPlayer(), mmoItems.getItem(undeadCrateReward.getType(), undeadCrateReward.getId()).getItemMeta().getDisplayName(), undeadCrateReward.getType(), undeadCrateReward.getId(), undeadCrateReward.getWeight(), undeadCrateReward.getAmount());
//                    break;
//                case "PROFESSION_BOOSTER_BOX":
//                case "PROFESSION_BOOSTER_BOX5":
//                case "PROFESSIONCRATE":
//                    RandomSelector<DropTables> professionCrate = RandomSelector.weighted((DropTables.getCrates(CrateList.PROFESSION)));
//                    DropTables professionCrateReward = professionCrate.pick();
//                    dropTablesDelivery.giveReward(player.getPlayer(), mmoItems.getItem(professionCrateReward.getType(), professionCrateReward.getId()).getItemMeta().getDisplayName(), professionCrateReward.getType(), professionCrateReward.getId(), professionCrateReward.getWeight(), professionCrateReward.getAmount());
//                    break;
//                case "MAGICALCRATE":
//                case "MAGICAL_BOX":
//                case "MAGICAL_BOX5":
//                    RandomSelector<DropTables> magicalCrate = RandomSelector.weighted((DropTables.getCrates(CrateList.MAGICAL)));
//                    DropTables magicalCrateReward = magicalCrate.pick();
//                    dropTablesDelivery.giveReward(player.getPlayer(), mmoItems.getItem(magicalCrateReward.getType(), magicalCrateReward.getId()).getItemMeta().getDisplayName(), magicalCrateReward.getType(), magicalCrateReward.getId(), magicalCrateReward.getWeight(), magicalCrateReward.getAmount());
//                    break;
//                case "BORGCRATE":
//                case "BORGSCRATE":
//                case "BORGS_BOX":
//                case "BORGS_BOX5":
//                    RandomSelector<DropTables> borgCrate = RandomSelector.weighted((DropTables.getCrates(CrateList.BORG)));
//                    DropTables borgCrateReward = borgCrate.pick();
//                    dropTablesDelivery.giveReward(player.getPlayer(), mmoItems.getItem(borgCrateReward.getType(), borgCrateReward.getId()).getItemMeta().getDisplayName(), borgCrateReward.getType(), borgCrateReward.getId(), borgCrateReward.getWeight(), borgCrateReward.getAmount());
//                    break;
//                case "ENCHANTED_BOX":
//                    RandomSelector<DropTables> enchantedCrate = RandomSelector.weighted((DropTables.getCrates(CrateList.ENCHANTED_BOX)));
//                    DropTables enchantedCrateReward = enchantedCrate.pick();
//                    dropTablesDelivery.giveReward(player.getPlayer(), mmoItems.getItem(enchantedCrateReward.getType(), enchantedCrateReward.getId()).getItemMeta().getDisplayName(), enchantedCrateReward.getType(), enchantedCrateReward.getId(), enchantedCrateReward.getWeight(), enchantedCrateReward.getAmount());
//                    break;
//                case "ENCHANTED_BOX2":
//                    RandomSelector<DropTables> enchantedCrate2 = RandomSelector.weighted((DropTables.getCrates(CrateList.ENCHANTED_BOX2)));
//                    DropTables enchantedCrate2Reward = enchantedCrate2.pick();
//                    dropTablesDelivery.giveReward(player.getPlayer(), mmoItems.getItem(enchantedCrate2Reward.getType(), enchantedCrate2Reward.getId()).getItemMeta().getDisplayName(), enchantedCrate2Reward.getType(), enchantedCrate2Reward.getId(), enchantedCrate2Reward.getWeight(), enchantedCrate2Reward.getAmount());
//                    break;
//                case "ENCHANTED_BOX3":
//                    RandomSelector<DropTables> enchantedCrate3 = RandomSelector.weighted((DropTables.getCrates(CrateList.ENCHANTED_BOX3)));
//                    DropTables enchantedCrate3Reward = enchantedCrate3.pick();
//                    dropTablesDelivery.giveReward(player.getPlayer(), mmoItems.getItem(enchantedCrate3Reward.getType(), enchantedCrate3Reward.getId()).getItemMeta().getDisplayName(), enchantedCrate3Reward.getType(), enchantedCrate3Reward.getId(), enchantedCrate3Reward.getWeight(), enchantedCrate3Reward.getAmount());
//                    break;
                case "REAPER":
                    for (DropTables dropTables : DropTables.getCrates(CrateList.REAPER))
                        if (chanceCheck.chanceCheck(dropTables.getWeight()))
                            dropTablesDelivery.giveReward(player.getPlayer(), dropTables.generateItem(), dropTables.getWeight());
                    break;
                case "MORDEN":
                case "MORDEN_THE_UNDEAD":
                    mmoItemsGive.giveMMOItem(player.getPlayer(), "MATERIAL", "MORDEN_FLESH", new Random().nextInt(4));
                    mmoItemsGive.giveMMOItem(player.getPlayer(), "MATERIAL", "ENCHANTED_BONE", new Random().nextInt(4));
                    for (DropTables dropTables : DropTables.getCrates(CrateList.MORDEN))
                        if (chanceCheck.chanceCheck(dropTables.getWeight()))
                            dropTablesDelivery.giveReward(player.getPlayer(), dropTables.generateItem(), dropTables.getWeight());
                    break;
                case "VOID_DRACULA":
                    for (DropTables dropTables : DropTables.getCrates(CrateList.VOID_DRACULA))
                        if (chanceCheck.chanceCheck(dropTables.getWeight()))
                            dropTablesDelivery.giveReward(player.getPlayer(), dropTables.generateItem(), dropTables.getWeight());
                    break;
                case "DRYAD":
                    mmoItemsGive.giveMMOItem(player.getPlayer(), "MATERIAL", "ENCHANTED_OAK_LOG", new Random().nextInt(2));
                    mmoItemsGive.giveMMOItem(player.getPlayer(), "MATERIAL", "ENCHANTED_DARK_OAK_LOG", new Random().nextInt(2));
                    mmoItemsGive.giveMMOItem(player.getPlayer(), "MATERIAL", "ENCHANTED_JUNGLE_LOG", new Random().nextInt(2));
                    mmoItemsGive.giveMMOItem(player.getPlayer(), "MATERIAL", "ENCHANTED_ACACIA_LOG", new Random().nextInt(2));
                    mmoItemsGive.giveMMOItem(player.getPlayer(), "MATERIAL", "ENCHANTED_BIRCH_LOG", new Random().nextInt(2));
                    mmoItemsGive.giveMMOItem(player.getPlayer(), "MATERIAL", "ENCHANTED_SPRUCE_LOG", new Random().nextInt(2));
                    for (DropTables dropTables : DropTables.getCrates(CrateList.DRYAD))
                        if (chanceCheck.chanceCheck(dropTables.getWeight()))
                            dropTablesDelivery.giveReward(player.getPlayer(), dropTables.generateItem(), dropTables.getWeight());
                    break;
                case "GOBLIN_CHIEF":
                    for (DropTables dropTables : DropTables.getCrates(CrateList.GOBLIN_CHIEF))
                        if (chanceCheck.chanceCheck(dropTables.getWeight()))
                            dropTablesDelivery.giveReward(player.getPlayer(), dropTables.generateItem(), dropTables.getWeight());
                    break;
                case "VOID_WITHER":
                    for (DropTables dropTables : DropTables.getCrates(CrateList.VOID_WITHER))
                        if (chanceCheck.chanceCheck(dropTables.getWeight()))
                            dropTablesDelivery.giveReward(player.getPlayer(), dropTables.generateItem(), dropTables.getWeight());
                    break;
                case "VOID_MAGMA":
                    mmoItemsGive.giveMMOItem(player.getPlayer(), "MATERIAL", "MAGMA_COIN", new Random().nextInt(3));
                    for (DropTables dropTables : DropTables.getCrates(CrateList.VOID_MAGMA))
                        if (chanceCheck.chanceCheck(dropTables.getWeight()))
                            dropTablesDelivery.giveReward(player.getPlayer(), dropTables.generateItem(), dropTables.getWeight());
                    break;
                case "GHASTLY":
                    mmoItemsGive.giveMMOItem(player.getPlayer(), "QUEST", "GHASTLY_TEAR3", 1);
                    for (DropTables dropTables : DropTables.getCrates(CrateList.GHASTLY))
                        if (chanceCheck.chanceCheck(dropTables.getWeight()))
                            dropTablesDelivery.giveReward(player.getPlayer(), dropTables.generateItem(), dropTables.getWeight());
                    break;
                case "VOID_BULBLIN":
                    for (DropTables dropTables : DropTables.getCrates(CrateList.BULBLIN))
                        if (chanceCheck.chanceCheck(dropTables.getWeight()))
                            dropTablesDelivery.giveReward(player.getPlayer(), dropTables.generateItem(), dropTables.getWeight());
                    break;
                case "VOID_BULLBO":
                    for (DropTables dropTables : DropTables.getCrates(CrateList.BULLBO))
                        if (chanceCheck.chanceCheck(dropTables.getWeight()))
                            dropTablesDelivery.giveReward(player.getPlayer(), dropTables.generateItem(), dropTables.getWeight());
                    break;
                case "VOID_ENCHANTRESS":
                    mmoItemsGive.giveMMOItem(player.getPlayer(), "MATERIAL", "ENCHANTED_OBSIDIAN", new Random().nextInt(3));
                    for (DropTables dropTables : DropTables.getCrates(CrateList.ENCHANTRESS))
                        if (chanceCheck.chanceCheck(dropTables.getWeight()))
                            dropTablesDelivery.giveReward(player.getPlayer(), dropTables.generateItem(), dropTables.getWeight());
                    break;
            }
        }
    }
}

