package monzter.adventurescraft.plugin.network.Shared.Events;

import co.aikar.commands.BaseCommand;
import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobDeathEvent;
import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.beton.BetonPointsManager;
import monzter.adventurescraft.plugin.utilities.general.ChanceCheck;
import monzter.adventurescraft.plugin.utilities.general.FullInventory;
import monzter.adventurescraft.plugin.utilities.general.ItemAdder;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import net.Indyuce.mmoitems.MMOItems;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class MythicMobs extends BaseCommand implements Listener {
    private final AdventuresCraft plugin;
    private final FullInventory fullInventory;
    private final BetonPointsManager betonPointsManager;
    private final SoundManager soundManager;
    private final ChanceCheck chanceCheck;
    private final ItemAdder itemAdder;


    public MythicMobs(AdventuresCraft plugin, FullInventory fullInventory, BetonPointsManager betonPointsManager, SoundManager soundManager, ChanceCheck chanceCheck, ItemAdder itemAdder) {
        this.plugin = plugin;
        this.fullInventory = fullInventory;
        this.betonPointsManager = betonPointsManager;
        this.soundManager = soundManager;
        this.chanceCheck = chanceCheck;
        this.itemAdder = itemAdder;
    }

    @EventHandler
    public void mobTrack(MythicMobDeathEvent event) {
        Player player = (Player) event.getKiller();
        Location location = event.getEntity().getLocation();
        if (player != null)
            switch (plugin.SERVER) {
                case "Prison":
                    betonPointsManager.givePoint(player, "mobs." + event.getMobType().getInternalName(), 1);
                    betonPointsManager.givePoint(player, "faction." + event.getMob().getFaction().toUpperCase(), 1);
                    break;
                case "Adventure":
                    betonPointsManager.givePoint(player, "mobs." + event.getMobType().getInternalName(), 1);
                    betonPointsManager.givePoint(player, "faction." + event.getMob().getFaction().toUpperCase(), 1);
                    switch (event.getMobType().getInternalName()) {
                        case "REAPER":
                        case "MORDEN_THE_UNDEAD":
                        case "VOID_DRACULA":
                        case "DRYAD":
                        case "GOBLIN_CHIEF":
                        case "VOID_WITHER":
                        case "VOID_MAGMA":
                        case "VOID_BULBLIN":
                        case "VOID_BULLBO":
                        case "VOID_ENCHANTRESS":
                            for (AbstractEntity abstractEntity : event.getMob().getThreatTable().getAllThreatTargets())
                                if (abstractEntity.isPlayer()) {
                                    Player player1 = Bukkit.getPlayer(abstractEntity.getName());
                                    betonPointsManager.givePoint(player1, "bossReward." + event.getMobType().getInternalName().toUpperCase(), 1);
                                    player1.sendMessage(ChatColor.GREEN + "You helped defeat the " + event.getMob().getDisplayName() + ChatColor.GREEN + "! "
                                            + ChatColor.GREEN + "Claim your reward by using " + ChatColor.GOLD + ChatColor.BOLD + "/Bossdex" + ChatColor.GREEN + "!");
                                    player1.playSound(player1.getLocation(), Sound.ENTITY_VILLAGER_CELEBRATE, 1, 1);
                                }
                            break;
                    }

                    switch (event.getMobType().getInternalName()) {
                        /*
                         *   Graveyard
                         */
                        case "UNDEAD_SKELETON":
                            undeadSkeleton(location, event.getMob(), player);
                            giveItem(location, Material.BONE, 2);
                            giveItem(location, MMOItems.plugin.getItem("MATERIAL", "BONE_FRAGMENT"), 2);
                            if (chanceCheck.chanceCheck(.001))
                                rareItem(event.getMob(), MMOItems.plugin.getItem("COMPANION", "PET_SKELETON3"), player);
                            break;
                        case "UNDEAD_ARCHER":
                            undeadSkeleton(location, event.getMob(), player);
                            giveItem(location, Material.BONE, 2);
                            giveItem(location, Material.ARROW, 3);
                            giveItem(location, MMOItems.plugin.getItem("MATERIAL", "BONE_FRAGMENT"), 3);
                            if (chanceCheck.chanceCheck(.001))
                                rareItem(event.getMob(), MMOItems.plugin.getItem("COMPANION", "PET_SKELETONARCHER3"), player);
                            break;
                        case "LOST_SOUL":
                            if (chanceCheck.chanceCheck(.33))
                                giveItem(event.getEntity().getLocation(), MMOItems.plugin.getItem("CONSUMABLE", "SOUL4"), 1);
                            if (chanceCheck.chanceCheck(.25))
                                giveItem(event.getEntity().getLocation(), MMOItems.plugin.getItem("MATERIAL", "LOST_SOUL"), 1);
                            break;
                        case "REAPER":
                            giveItem(event.getEntity().getLocation(), MMOItems.plugin.getItem("MATERIAL", "BONE_FRAGMENT"), 15);
                            giveItem(event.getEntity().getLocation(), Material.BONE, 25);
                            break;
                        /*
                         *   Courtyard
                         */
                        case "UNDEAD_SKELETON2":
                            undeadSkeleton(location, event.getMob(), player);
                            giveItem(location, Material.BONE, 5);
                            giveItem(location, MMOItems.plugin.getItem("MATERIAL", "BONE_FRAGMENT"), 5);
                            if (chanceCheck.chanceCheck(.005))
                                rareItem(event.getMob(), MMOItems.plugin.getItem("COMPANION", "PET_SKELETON3"), player);
                            break;
                        case "UNDEAD_ARCHER2":
                            undeadSkeleton(location, event.getMob(), player);
                            giveItem(location, Material.BONE, 5);
                            giveItem(location, Material.ARROW, 5);
                            giveItem(location, MMOItems.plugin.getItem("MATERIAL", "BONE_FRAGMENT"), 5);
                            if (chanceCheck.chanceCheck(.005))
                                rareItem(event.getMob(), MMOItems.plugin.getItem("COMPANION", "PET_SKELETONARCHER3"), player);
                            else if (chanceCheck.chanceCheck(.005))
                                rareItem(event.getMob(), MMOItems.plugin.getItem("BOW", "UNDEAD_BOW4ut"), player);
                            break;
                        case "ALPHA_SOUL":
                            giveItem(location, MMOItems.plugin.getItem("MATERIAL", "ALPHA_SOUL"), 1);
                            if (chanceCheck.chanceCheck(.005))
                                rareItem(event.getMob(), MMOItems.plugin.getItem("COMPANION", "PET_ALPHA_SOUL3"), player);
                            if (chanceCheck.chanceCheck(.66))
                                rareItem(event.getMob(), MMOItems.plugin.getItem("CONSUMABLE", "SOUL4"), player);
                            if (chanceCheck.chanceCheck(.50))
                                rareItem(event.getMob(), MMOItems.plugin.getItem("MATERIAL", "LOST_SOUL"), player);
                            break;
                        case "UNDEAD_SPIDER":
                            giveItem(location, Material.STRING, 3);
                            giveItem(location, Material.SPIDER_EYE, 3);
                            giveItem(location, MMOItems.plugin.getItem("MATERIAL", "BONE_FRAGMENT"), 4);
                            if (chanceCheck.chanceCheck(.001))
                                rareItem(event.getMob(), MMOItems.plugin.getItem("COMPANION", "PET_UNDEAD_SPIDER3"), player);
                            break;
                        case "UNDEAD_CASTER":
                            giveItem(location, Material.BONE, 4);
                            giveItem(location, MMOItems.plugin.getItem("MATERIAL", "BONE_FRAGMENT"), 5);
                            if (chanceCheck.chanceCheck(.001))
                                rareItem(event.getMob(), MMOItems.plugin.getItem("COMPANION", "PET_UNDEAD_CASTER3"), player);
                            if (chanceCheck.chanceCheck(.2))
                                rareItem(event.getMob(), MMOItems.plugin.getItem("CONSUMABLE", "MAGICAL_ESSENSE2"), player);
                            break;
                        /*
                         *   Castle
                         */
                        case "UNDEAD_WARRIOR":
                            giveItem(location, Material.BONE, 5);
                            giveItem(location, MMOItems.plugin.getItem("MATERIAL", "BONE_FRAGMENT"), 5);
                            break;
                        /*
                         *   Farm
                         */
                        case "CUCCO":
                            giveItem(location, Material.FEATHER, 3);
                            giveItem(location, Material.CHICKEN, 3);
                            break;
                        /*
                         *   Estate
                         */
                        case "BABY_GOBLIN":
                            giveItem(location, Material.ROTTEN_FLESH, 3);
                            if (chanceCheck.chanceCheck(.01))
                                rareItem(event.getMob(), MMOItems.plugin.getItem("HAMMER", "GOBLIN_HAMMER3"), player);
                            break;
                        case "BABY_GOBLIN2":
                        case "GOBLIN":
                            giveItem(location, Material.ROTTEN_FLESH, 4);
                            if (chanceCheck.chanceCheck(.0125))
                                rareItem(event.getMob(), MMOItems.plugin.getItem("HAMMER", "GOBLIN_HAMMER3"), player);
                            break;
                        case "GOBLIN2":
                            giveItem(location, Material.ROTTEN_FLESH, 5);
                            if (chanceCheck.chanceCheck(.015))
                                rareItem(event.getMob(), MMOItems.plugin.getItem("HAMMER", "GOBLIN_HAMMER3"), player);
                            break;
                        case "ARCHER_GOBLIN":
                            giveItem(location, Material.ROTTEN_FLESH, 4);
                            giveItem(location, Material.ARROW, 3);
                            if (chanceCheck.chanceCheck(.015))
                                rareItem(event.getMob(), MMOItems.plugin.getItem("HAMMER", "GOBLIN_HAMMER3"), player);
                            break;
                        /*
                         *   Spirit Grounds
                         */
                        case "SPIRIT_BULL":
                            giveItem(location, Material.RED_MUSHROOM, 3);
                            giveItem(location, Material.BROWN_MUSHROOM, 2);
                            if (chanceCheck.chanceCheck(.25))
                                giveItem(location, Material.MELON_SLICE, 4);
                            else if (chanceCheck.chanceCheck(.25))
                                giveItem(location, Material.PUMPKIN, 2);
                            break;
                        case "SPIRIT_SPIDER":
                            giveItem(location, Material.SPIDER_EYE, 2);
                            giveItem(location, Material.STRING, 2);
                            if (chanceCheck.chanceCheck(.25))
                                giveItem(location, Material.MELON_SLICE, 4);
                            else if (chanceCheck.chanceCheck(.25))
                                giveItem(location, Material.PUMPKIN, 2);
                            else if (chanceCheck.chanceCheck(.25))
                                giveItem(location, Material.RED_MUSHROOM, 2);
                            else if (chanceCheck.chanceCheck(.25))
                                giveItem(location, Material.BROWN_MUSHROOM, 2);
                            break;
                        case "SPIRIT_WITCH":
                            if (chanceCheck.chanceCheck(.25))
                                giveItem(location, Material.GLASS_BOTTLE, 4);
                            else if (chanceCheck.chanceCheck(.25))
                                giveItem(location, Material.BLAZE_ROD, 2);
                            else if (chanceCheck.chanceCheck(.25))
                                giveItem(location, Material.FERMENTED_SPIDER_EYE, 2);
                            else if (chanceCheck.chanceCheck(.25))
                                giveItem(location, Material.GHAST_TEAR, 2);
                            break;
                        /*
                         *   Forest
                         */
                        case "BEE1":
                            forestDropTable(location);
                            giveItem(location, Material.HONEYCOMB, 2);
                            if (chanceCheck.chanceCheck(.005))
                                rareItem(event.getMob(), MMOItems.plugin.getItem("ARMOR", "BEE_WINGS2"), player);
                            break;
                        case "HARE":
                            forestDropTable(location);
                            if (chanceCheck.chanceCheck(.33))
                                giveItem(location, Material.RABBIT_HIDE, 2);
                            else if (chanceCheck.chanceCheck(.33))
                                giveItem(location, Material.RABBIT, 2);
                            else if (chanceCheck.chanceCheck(.33))
                                giveItem(location, Material.RABBIT_FOOT, 2);
                            break;
                        case "WILD_CAT":
                        case "DRYAD_HARE":
                            forestDropTable(location);
                            break;
                        case "WASP":
                            forestDropTable(location);
                            giveItem(location, Material.HONEYCOMB, 4);
                            if (chanceCheck.chanceCheck(.005))
                                rareItem(event.getMob(), MMOItems.plugin.getItem("ARMOR", "BEE_WINGS2"), player);
                            break;
                        case "QUEEN_BEE":
                            forestDropTable(location);
                            giveItem(location, Material.HONEYCOMB, 5);
                            if (chanceCheck.chanceCheck(.0075))
                                rareItem(event.getMob(), MMOItems.plugin.getItem("ARMOR", "BEE_WINGS3"), player);
                            break;
                        /*
                         *   Mine
                         */
                        case "ANGRY_BAT":
                            giveItem(location, Material.IRON_INGOT, 1);
                            giveItem(location, Material.COAL, 2);
                            break;
                        case "ANGRY_SPIDER":
                            giveItem(location, Material.IRON_INGOT, 1);
                            giveItem(location, Material.GOLD_INGOT, 2);
                            giveItem(location, Material.COAL, 1);
                            giveItem(location, Material.SPIDER_EYE, 2);
                            giveItem(location, Material.STRING, 2);
                            break;

                        case "VOID_BAT":
                            mineDropTable(location, event.getMob(), player);
                            giveItem(location, Material.REDSTONE, 2);
                            giveItem(location, Material.LAPIS_LAZULI, 2);
                            break;
                        case "VOID_BOOMER":
                            mineDropTable(location, event.getMob(), player);
                            voidBoomer(location, event.getMob(), player);
                            giveItem(location, Material.REDSTONE, 2);
                            giveItem(location, Material.LAPIS_LAZULI, 2);
                            giveItem(location, Material.GUNPOWDER, 3);
                            break;
                        case "VOID_SPIDER":
                            mineDropTable(location, event.getMob(), player);
                            giveItem(location, Material.REDSTONE, 2);
                            giveItem(location, Material.LAPIS_LAZULI, 2);
                            giveItem(location, Material.SPIDER_EYE, 3);
                            giveItem(location, Material.STRING, 3);
                            break;
                        case "VOID_VAMPIRE":
                            mineDropTable(location, event.getMob(), player);
                            voidVampire(location, event.getMob(), player);
                            giveItem(location, Material.REDSTONE, 2);
                            giveItem(location, Material.LAPIS_LAZULI, 2);
                            giveItem(location, Material.BONE, 3);
                            break;
                        case "VOID_ANT":
                            giveItem(location, Material.EMERALD, 1);
                            giveItem(location, Material.DIAMOND, 1);
                            break;
                        case "VOID_VAMPIRE2":
                            mineDropTable(location, event.getMob(), player);
                            voidVampire(location, event.getMob(), player);
                            giveItem(location, Material.EMERALD, 2);
                            giveItem(location, Material.DIAMOND, 2);
                            giveItem(location, Material.BONE, 3);
                            break;
                        case "VOID_DRACULA":
                            mineDropTable(location, event.getMob(), player);
                            voidVampire(location, event.getMob(), player);
                            giveItem(location, Material.EMERALD, 5);
                            giveItem(location, Material.DIAMOND, 5);
                            giveItem(location, Material.BONE, 5);
                            break;
                        /*
                         *   Hell
                         */
                        case "VOID_PIGMAN":
                            hellDropTable(location, event.getMob(), player);
                            voidPigman(location, event.getMob(), player);
                            giveItem(location, Material.ROTTEN_FLESH, 4);
                            break;
                        case "VOID_DEMON":
                            hellDropTable(location, event.getMob(), player);
                            voidDemon(location, event.getMob(), player);
                            giveItem(location, Material.ROTTEN_FLESH, 4);
                            break;
                        case "VOID_NECROMANCER":
                            hellDropTable(location, event.getMob(), player);
                            voidNecromancer(location, event.getMob(), player);
                            giveItem(location, Material.ROTTEN_FLESH, 4);
                            break;
                        case "VOID_BLAZE":
                            hellDropTable(location, event.getMob(), player);
                            voidBlaze(location, event.getMob(), player);
                            giveItem(location, Material.BLAZE_ROD, 3);
                            break;
                        case "VOID_PROTECTOR":
                            hellDropTable(location, event.getMob(), player);
                            giveItem(location, Material.IRON_INGOT, 3);
                            break;
                        case "VOID_HEALER":
                            hellDropTable(location, event.getMob(), player);
                            voidHealer(location, event.getMob(), player);
                            giveItem(location, Material.BONE, 3);
                            break;

                        case "VOID_MAGMA":
                        case "VOID_MAGMA2":
                        case "VOID_MAGMA3":
                            voidMagma(location, event.getMob(), player);
                            hellDropTable(location, event.getMob(), player);
                            giveItem(location, Material.MAGMA_CREAM, 4);
                            break;
                        case "VOID_SKELETON":
                            hellDropTable(location, event.getMob(), player);
                            giveItem(location, Material.BONE, 4);
                            break;
                        case "VOID_PIGMAN2":
                            hellDropTable(location, event.getMob(), player);
                            voidPigman(location, event.getMob(), player);
                            giveItem(location, Material.ROTTEN_FLESH, 5);
                            break;
                        case "VOID_NECROMANCER2":
                            hellDropTable(location, event.getMob(), player);
                            voidNecromancer(location, event.getMob(), player);
                            giveItem(location, Material.BONE, 4);
                            break;

                        /*
                         *   Void
                         */
                        case "VOID_WORSHIPER":
                            voidDropTable(location, event.getMob(), player);
                            voidWorshiper(location, event.getMob(), player);
                            giveItem(location, Material.ENDER_PEARL, 4);
                            break;
                        case "VOID_SOURCE":
                            voidDropTable(location, event.getMob(), player);
                            voidSource(location, event.getMob(), player);
                            giveItem(location, Material.SHULKER_SHELL, 4);
                            break;
                        case "VOID_THRALL":
                            voidDropTable(location, event.getMob(), player);
                            voidThrall(location, event.getMob(), player);
                            giveItem(location, Material.OBSIDIAN, 3);
                            break;
                        case "VOID_ASSASSIN":
                            voidDropTable(location, event.getMob(), player);
                            voidAssassin(location, event.getMob(), player);
                            giveItem(location, MMOItems.plugin.getItem("MATERIAL", "CHORUS_SEED"), 3);
                            giveItem(location, Material.OBSIDIAN, 2);
                            break;
                        case "VOID_VEIN":
                            voidDropTable(location, event.getMob(), player);
                            giveItem(location, Material.WITHER_ROSE, 5);
                            break;

                        case "VOID_MONITOR":
                            voidDropTable(location, event.getMob(), player);
                            voidMonitor(location, event.getMob(), player);
                            giveItem(location, Material.OBSIDIAN, 3);
                            break;
                        case "VOID_MEGA_BOOMER":
                            voidDropTable(location, event.getMob(), player);
                            voidMegaBoomer(location, event.getMob(), player);
                            giveItem(location, Material.GUNPOWDER, 5);
                            break;
                        case "VOID_CHAMPION":
                            voidDropTable(location, event.getMob(), player);
                            voidChampion(location, event.getMob(), player);
                            giveItem(location, Material.NETHERITE_INGOT, 5);
                            break;

                        case "VOID_BOSS_DEFENDER":
                            voidDropTable(location, event.getMob(), player);
                            voidDefender(location, event.getMob(), player);
                            giveItem(location, Material.OBSIDIAN, 5);
                            break;
                        case "VOID_BOSS_DEFENDER2":
                            voidDropTable(location, event.getMob(), player);
                            voidDefender(location, event.getMob(), player);
                            giveItem(location, Material.NETHERITE_INGOT, 3);
                            break;
                        case "VOID_BOSS_DEFENDER3":
                            voidDropTable(location, event.getMob(), player);
                            voidDefender(location, event.getMob(), player);
                            giveItem(location, Material.NETHERITE_INGOT, 3);
                            giveItem(location, Material.OBSIDIAN, 5);
                            break;
                        case "VOID_BOSS_DEFENDER4":
                            voidDropTable(location, event.getMob(), player);
                            voidDefender(location, event.getMob(), player);
                            giveItem(location, Material.ENDER_PEARL, 3);
                            break;

                        /*
                         *   Castle
                         */
                    }
                    if (chanceCheck.chanceCheck(.001))
                        rareItem(event.getMob(), MMOItems.plugin.getItem("CONSUMABLE", "BORGS_BOX5"), player);
                    else if (chanceCheck.chanceCheck(.025))
                        rareItem(event.getMob(), MMOItems.plugin.getItem("CONSUMABLE", "MAGICAL_BOX5"), player);
                    else if (chanceCheck.chanceCheck(.0095))
                        rareItem(event.getMob(), MMOItems.plugin.getItem("MATERIAL", "ENGRAM1"), player);
                    else if (chanceCheck.chanceCheck(.00075))
                        rareItem(event.getMob(), MMOItems.plugin.getItem("MATERIAL", "ENGRAM2"), player);
                    break;
            }
    }

    //    @EventHandler
//    public void petEgg(MythicMobDeathEvent event) {
//        Player player = (Player) event.getKiller();
//        if (player != null) {
//            for (ItemStack item : event.getDrops()) {
//                final Map<Integer, ItemStack> map = player.getInventory().addItem(item);
//                if (!map.isEmpty()) {
//                    map.values().forEach(items -> player.getWorld().dropItemNaturally(player.getLocation(), items)
//                            .setMetadata("AntiLoot", new FixedMetadataValue(plugin, player.getUniqueId())));
//                }
//            }
//            event.getDrops().clear();
//        }
//    }
//
//    @EventHandler
//    public void onItemPickup(PlayerPickupItemEvent event) {
//        plugin.getLogger().info("Called");
//        Player player = event.getPlayer();
//        if (event.getItem().hasMetadata("AntiLoot")) {
//            if (!player.hasPermission("anti.loot.bypass")) {
//                if (!player.getUniqueId().toString().equals(event.getItem().getMetadata("AntiLoot").get(0).asString())) {
//                    player.sendMessage(ChatColor.RED + "This is not your loot!");
//                    event.setCancelled(true);
//                }
//            }
//        }
//    }
    private void giveItem(Location location, Material material, int max) {
        giveItem(location, material, 0, max);
    }

    private void giveItem(Location location, Material material, int min, int max) {
        if (min == 0) {
            int amount = new Random().nextInt(max + 1);
            if (amount > 0)
                location.getWorld().dropItem(location, new ItemStack(material).asQuantity(amount));
        }
    }

    private void giveItem(Location location, ItemStack itemStack, int max) {
        giveItem(location, itemStack, 0, max);
    }

    private void giveItem(Location location, ItemStack itemStack, int min, int max) {
        if (min == 0) {
            int amount = new Random().nextInt(max + 1);
            if (amount > 0)
                location.getWorld().dropItem(location, itemStack.asQuantity(amount));
        }
    }

    private void rareItem(ActiveMob activeMob, ItemStack itemStack, Player winner) {
        itemAdder.itemAdder(winner, itemStack);
        winner.sendMessage(ChatColor.GREEN + "You just got a " + itemStack.getItemMeta().getDisplayName() + ChatColor.GREEN + "!");
        soundManager.playSound(winner, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 2);
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            for (Player player : Bukkit.getOnlinePlayers())
                if (player.getLocation().distance(BukkitAdapter.adapt(activeMob.getLocation())) <= 50) {
                    player.sendMessage(ChatColor.GREEN + winner.getName() + ChatColor.GREEN + " just got really lucky because a " + activeMob.getDisplayName() + ChatColor.GREEN + " just dropped a " + itemStack.getItemMeta().getDisplayName() + ChatColor.GREEN + "! ");
                }
        });
    }

    /*
     *
     * DROP TABLES
     *
     * */

    private void forestDropTable(Location location) {
        if (chanceCheck.chanceCheck(.15))
            giveItem(location, Material.JUNGLE_LOG, 2);
        else if (chanceCheck.chanceCheck(.15))
            giveItem(location, Material.OAK_LOG, 2);
        else if (chanceCheck.chanceCheck(.15))
            giveItem(location, Material.DARK_OAK_LOG, 2);
        else if (chanceCheck.chanceCheck(.15))
            giveItem(location, Material.SPRUCE_LOG, 2);
        else if (chanceCheck.chanceCheck(.15))
            giveItem(location, Material.BIRCH_LOG, 2);
        else if (chanceCheck.chanceCheck(.15))
            giveItem(location, Material.ACACIA_LOG, 2);
    }

    private void mineDropTable(Location location, ActiveMob activeMob, Player player) {
        if (chanceCheck.chanceCheck(.01))
            rareItem(activeMob, MMOItems.plugin.getItem("CONSUMABLE", "HEART"), player);
        else if (chanceCheck.chanceCheck(.02))
            rareItem(activeMob, MMOItems.plugin.getItem("GEM_STONE", "VOID_GEM"), player);
        else if (chanceCheck.chanceCheck(.01))
            rareItem(activeMob, MMOItems.plugin.getItem("GEM_STONE", "VOID_GEM2"), player);
        else if (chanceCheck.chanceCheck(.0085))
            rareItem(activeMob, MMOItems.plugin.getItem("GEM_STONE", "VOID_GEM3"), player);
    }

    private void hellDropTable(Location location, ActiveMob activeMob, Player player) {
        if (chanceCheck.chanceCheck(.005))
            rareItem(activeMob, MMOItems.plugin.getItem("GEM_STONE", "HEATED_GEM3"), player);
        else if (chanceCheck.chanceCheck(.0075))
            rareItem(activeMob, MMOItems.plugin.getItem("GEM_STONE", "HEATED_GEM2"), player);
        else if (chanceCheck.chanceCheck(.01))
            rareItem(activeMob, MMOItems.plugin.getItem("GEM_STONE", "HEATED_GEM"), player);
        else if (chanceCheck.chanceCheck(.01))
            rareItem(activeMob, MMOItems.plugin.getItem("CATALYST", "FIRE_CATALYST4"), player);
        else if (chanceCheck.chanceCheck(.25))
            giveItem(location, MMOItems.plugin.getItem("MATERIAL", "VOID_SHARD"), 3);
    }

    private void voidDropTable(Location location, ActiveMob activeMob, Player player) {
        if (chanceCheck.chanceCheck(.005))
            rareItem(activeMob, MMOItems.plugin.getItem("GEM_STONE", "VOID_GEM3"), player);
        else if (chanceCheck.chanceCheck(.0075))
            rareItem(activeMob, MMOItems.plugin.getItem("GEM_STONE", "VOID_GEM2"), player);
        else if (chanceCheck.chanceCheck(.01))
            rareItem(activeMob, MMOItems.plugin.getItem("GEM_STONE", "VOID_GEM"), player);
        else if (chanceCheck.chanceCheck(.25))
            giveItem(location, MMOItems.plugin.getItem("MATERIAL", "VOID_SHARD"), 5);
    }

//

    private void undeadSkeleton(Location location, ActiveMob activeMob, Player player) {
        if (chanceCheck.chanceCheck(.005))
            rareItem(activeMob, MMOItems.plugin.getItem("ARMOR", "SKELETON_SKULL3"), player);
        else if (chanceCheck.chanceCheck(.005))
            rareItem(activeMob, MMOItems.plugin.getItem("ARMOR", "SKELETON_CHESTPLATE3"), player);
        else if (chanceCheck.chanceCheck(.005))
            rareItem(activeMob, MMOItems.plugin.getItem("ARMOR", "SKELETON_LEGS3"), player);
        else if (chanceCheck.chanceCheck(.005))
            rareItem(activeMob, MMOItems.plugin.getItem("ARMOR", "SKELETON_FEET3"), player);
        else if (chanceCheck.chanceCheck(.0045))
            rareItem(activeMob, MMOItems.plugin.getItem("BOW", "UNDEAD_BOW3"), player);
        else if (chanceCheck.chanceCheck(.0035))
            rareItem(activeMob, MMOItems.plugin.getItem("AXE", "BONE_AXE3"), player);
        else if (chanceCheck.chanceCheck(.0025))
            rareItem(activeMob, MMOItems.plugin.getItem("DAGGER", "BONE_DAGGER3"), player);
    }

    private void voidBoomer(Location location, ActiveMob activeMob, Player player) {
        if (chanceCheck.chanceCheck(.02))
            rareItem(activeMob, MMOItems.plugin.getItem("SPELL", "BLACK_HOLE2").asQuantity(2), player);
        else if (chanceCheck.chanceCheck(.01))
            rareItem(activeMob, MMOItems.plugin.getItem("SPELL", "BLACK_HOLE3").asQuantity(2), player);
    }

    private void voidVampire(Location location, ActiveMob activeMob, Player player) {
        if (chanceCheck.chanceCheck(.01))
            rareItem(activeMob, MMOItems.plugin.getItem("DAGGER", "STEAK2"), player);
        else if (chanceCheck.chanceCheck(.50))
            rareItem(activeMob, MMOItems.plugin.getItem("QUEST", "VAMPIRE_FANG2"), player);
    }

    private void voidBlaze(Location location, ActiveMob activeMob, Player player) {
        if (chanceCheck.chanceCheck(.005))
            rareItem(activeMob, MMOItems.plugin.getItem("ARMOR", "BLAZE_CHEST4"), player);
        else if (chanceCheck.chanceCheck(.0075))
            rareItem(activeMob, MMOItems.plugin.getItem("DAGGER", "BLAZE_ARM3"), player);
        else if (chanceCheck.chanceCheck(.01))
            rareItem(activeMob, MMOItems.plugin.getItem("SPELL", "CURSED_BEAM2"), player);
        else if (chanceCheck.chanceCheck(.02))
            rareItem(activeMob, MMOItems.plugin.getItem("SPELL", "CURSED_BEAM3"), player);
    }

    private void voidNecromancer(Location location, ActiveMob activeMob, Player player) {
        if (chanceCheck.chanceCheck(.005))
            rareItem(activeMob, MMOItems.plugin.getItem("STAFF", "NECROMANCER_STAFF2"), player);
        else if (chanceCheck.chanceCheck(.01))
            rareItem(activeMob, MMOItems.plugin.getItem("CONSUMABLE", "MAGICAL_ESSENSE3"), player);
    }

    private void voidHealer(Location location, ActiveMob activeMob, Player player) {
        if (chanceCheck.chanceCheck(.01))
            rareItem(activeMob, MMOItems.plugin.getItem("ARMOR", "HEALER_HEAD4"), player);
    }

    private void voidDemon(Location location, ActiveMob activeMob, Player player) {
        if (chanceCheck.chanceCheck(.0075))
            rareItem(activeMob, MMOItems.plugin.getItem("ARMOR", "DEMON_HEAD4"), player);
        else if (chanceCheck.chanceCheck(.20))
            rareItem(activeMob, MMOItems.plugin.getItem("QUEST", "DEMON_CLAW"), player);
    }

    private void voidPigman(Location location, ActiveMob activeMob, Player player) {
        if (chanceCheck.chanceCheck(.0075))
            rareItem(activeMob, MMOItems.plugin.getItem("ARMOR", "PIGMAN_HEAD3"), player);
    }

    private void voidMagma(Location location, ActiveMob activeMob, Player player) {
        if (chanceCheck.chanceCheck(.01))
            rareItem(activeMob, MMOItems.plugin.getItem("SPELL", "MAGMA_FISSURE2"), player);
        else if (chanceCheck.chanceCheck(.02))
            rareItem(activeMob, MMOItems.plugin.getItem("SPELL", "MAGMA_FISSURE3"), player);
    }

    private void voidWorshiper(Location location, ActiveMob activeMob, Player player) {
        if (chanceCheck.chanceCheck(.01))
            rareItem(activeMob, MMOItems.plugin.getItem("SPELL", "ENDER_METEOR2"), player);
        else if (chanceCheck.chanceCheck(.02))
            rareItem(activeMob, MMOItems.plugin.getItem("SPELL", "ENDER_METEOR3"), player);
        else if (chanceCheck.chanceCheck(.5))
            rareItem(activeMob, MMOItems.plugin.getItem("QUEST", "VOID_DNA"), player);
    }

    private void voidSource(Location location, ActiveMob activeMob, Player player) {
        if (chanceCheck.chanceCheck(.01))
            rareItem(activeMob, MMOItems.plugin.getItem("SPELL", "SHULKER_MISSILE2"), player);
        else if (chanceCheck.chanceCheck(.02))
            rareItem(activeMob, MMOItems.plugin.getItem("SPELL", "SHULKER_MISSILE3"), player);
    }

    private void voidThrall(Location location, ActiveMob activeMob, Player player) {
        if (chanceCheck.chanceCheck(.01))
            rareItem(activeMob, MMOItems.plugin.getItem("SPELL", "LIFE_ENDER2"), player);
        else if (chanceCheck.chanceCheck(.02))
            rareItem(activeMob, MMOItems.plugin.getItem("SPELL", "LIFE_ENDER3"), player);
        else if (chanceCheck.chanceCheck(.5))
            rareItem(activeMob, MMOItems.plugin.getItem("MATERIAL", "THRALL_EGG"), player);
    }

    private void voidAssassin(Location location, ActiveMob activeMob, Player player) {
        if (chanceCheck.chanceCheck(.01))
            rareItem(activeMob, MMOItems.plugin.getItem("DAGGER", "SHADOW_DAGGER3"), player);
        else if (chanceCheck.chanceCheck(.5))
            rareItem(activeMob, MMOItems.plugin.getItem("MATERIAL", "SHADOW_ESSENCE"), player);
    }


    private void voidMonitor(Location location, ActiveMob activeMob, Player player) {
        if (chanceCheck.chanceCheck(.01))
            rareItem(activeMob, MMOItems.plugin.getItem("SPELL", "MONITOR_MUTE2"), player);
        else if (chanceCheck.chanceCheck(.02))
            rareItem(activeMob, MMOItems.plugin.getItem("SPELL", "MONITOR_MUTE3"), player);
    }

    private void voidMegaBoomer(Location location, ActiveMob activeMob, Player player) {
        if (chanceCheck.chanceCheck(.01))
            rareItem(activeMob, MMOItems.plugin.getItem("SPELL", "CURSED_BEAM2"), player);
        else if (chanceCheck.chanceCheck(.02))
            rareItem(activeMob, MMOItems.plugin.getItem("SPELL", "CURSED_BEAM3"), player);
    }

    private void voidChampion(Location location, ActiveMob activeMob, Player player) {
        if (chanceCheck.chanceCheck(.01))
            rareItem(activeMob, MMOItems.plugin.getItem("SPELL", "ENDER_METEOR2"), player);
        else if (chanceCheck.chanceCheck(.02))
            rareItem(activeMob, MMOItems.plugin.getItem("SPELL", "ENDER_METEOR3"), player);
    }


    private void voidDefender(Location location, ActiveMob activeMob, Player player) {
        if (chanceCheck.chanceCheck(.1))
            rareItem(activeMob, MMOItems.plugin.getItem("MATERIAL", "DEFENDER_FLESH"), player);
    }

}