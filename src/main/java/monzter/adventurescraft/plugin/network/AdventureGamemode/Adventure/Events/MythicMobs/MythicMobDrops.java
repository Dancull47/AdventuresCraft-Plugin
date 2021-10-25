package monzter.adventurescraft.plugin.network.AdventureGamemode.Adventure.Events.MythicMobs;

import io.lumine.xikage.mythicmobs.MythicMobs;
import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobDeathEvent;
import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.general.ChanceCheck;
import monzter.adventurescraft.plugin.utilities.general.ItemAdder;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import monzter.adventurescraft.plugin.utilities.mmoitems.MMOItemsHelperImpl;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class MythicMobDrops implements Listener {
    private final AdventuresCraft plugin;
    private final ChanceCheck chanceCheck;
    private final ItemAdder itemAdder;
    private final SoundManager soundManager;


    public MythicMobDrops(AdventuresCraft plugin, ChanceCheck chanceCheck, ItemAdder itemAdder, SoundManager soundManager) {
        this.plugin = plugin;
        this.chanceCheck = chanceCheck;
        this.itemAdder = itemAdder;
        this.soundManager = soundManager;
    }

    @EventHandler
    public void mobDrops(MythicMobDeathEvent event) {
        Player killer = Bukkit.getPlayer(event.getKiller().getUniqueId());
        if (killer == null)
            killer = Bukkit.getPlayer(MythicMobs.inst().getMobManager().getMythicMobInstance(event.getKiller()).getParent().getEntity().getUniqueId());
        if (killer == null)
            return;

        Location location = event.getEntity().getLocation();
        if (killer != null) {
            switch (event.getMobType().getInternalName()) {
                /*
                 *   Graveyard
                 */
                case "UNDEAD_SKELETON":
                    giveItem(location, Material.BONE, 2);
                    giveItem(location, MMOItemsHelperImpl.getItem("MATERIAL", "BONE_FRAGMENT"), 2);
                    if (chanceCheck.chanceCheck(.001))
                        rareItem(event.getMob(), MMOItemsHelperImpl.getItem("COMPANION", "PET_SKELETON3"), killer);
                    break;
                case "UNDEAD_ARCHER":
                    giveItem(location, Material.BONE, 2);
                    giveItem(location, Material.ARROW, 3);
                    giveItem(location, MMOItemsHelperImpl.getItem("MATERIAL", "BONE_FRAGMENT"), 3);
                    if (chanceCheck.chanceCheck(.001))
                        rareItem(event.getMob(), MMOItemsHelperImpl.getItem("COMPANION", "PET_SKELETONARCHER3"), killer);
                    break;
                case "LOST_SOUL":
                    if (chanceCheck.chanceCheck(.33))
                        giveItem(event.getEntity().getLocation(), MMOItemsHelperImpl.getItem("CONSUMABLE", "SOUL4"), 1);
                    if (chanceCheck.chanceCheck(.25))
                        giveItem(event.getEntity().getLocation(), MMOItemsHelperImpl.getItem("MATERIAL", "LOST_SOUL"), 1);
                    break;
                case "REAPER":
                    giveItem(event.getEntity().getLocation(), MMOItemsHelperImpl.getItem("MATERIAL", "BONE_FRAGMENT"), 15);
                    giveItem(event.getEntity().getLocation(), Material.BONE, 25);
                    break;
                /*
                 *   Courtyard
                 */
                case "UNDEAD_SKELETON2":
                    undeadSkeleton(location, event.getMob(), killer);
                    giveItem(location, Material.BONE, 5);
                    giveItem(location, MMOItemsHelperImpl.getItem("MATERIAL", "BONE_FRAGMENT"), 5);
                    if (chanceCheck.chanceCheck(.005))
                        rareItem(event.getMob(), MMOItemsHelperImpl.getItem("COMPANION", "PET_SKELETON3"), killer);
                    break;
                case "UNDEAD_ARCHER2":
                    undeadSkeleton(location, event.getMob(), killer);
                    giveItem(location, Material.BONE, 5);
                    giveItem(location, Material.ARROW, 5);
                    giveItem(location, MMOItemsHelperImpl.getItem("MATERIAL", "BONE_FRAGMENT"), 5);
                    if (chanceCheck.chanceCheck(.005))
                        rareItem(event.getMob(), MMOItemsHelperImpl.getItem("COMPANION", "PET_SKELETONARCHER3"), killer);
                    else if (chanceCheck.chanceCheck(.005))
                        rareItem(event.getMob(), MMOItemsHelperImpl.getItem("BOW", "UNDEAD_BOW4ut"), killer);
                    break;
                case "ALPHA_SOUL":
                    giveItem(location, MMOItemsHelperImpl.getItem("MATERIAL", "ALPHA_SOUL"), 1);
                    if (chanceCheck.chanceCheck(.005))
                        rareItem(event.getMob(), MMOItemsHelperImpl.getItem("COMPANION", "PET_ALPHA_SOUL3"), killer);
                    if (chanceCheck.chanceCheck(.66))
                        rareItem(event.getMob(), MMOItemsHelperImpl.getItem("CONSUMABLE", "SOUL4"), killer);
                    if (chanceCheck.chanceCheck(.50))
                        rareItem(event.getMob(), MMOItemsHelperImpl.getItem("MATERIAL", "LOST_SOUL"), killer);
                    break;
                case "UNDEAD_SPIDER":
                    giveItem(location, Material.STRING, 3);
                    giveItem(location, Material.SPIDER_EYE, 3);
                    giveItem(location, MMOItemsHelperImpl.getItem("MATERIAL", "BONE_FRAGMENT"), 4);
                    if (chanceCheck.chanceCheck(.001))
                        rareItem(event.getMob(), MMOItemsHelperImpl.getItem("COMPANION", "PET_UNDEAD_SPIDER3"), killer);
                    break;
                case "UNDEAD_CASTER":
                    giveItem(location, Material.BONE, 4);
                    giveItem(location, MMOItemsHelperImpl.getItem("MATERIAL", "BONE_FRAGMENT"), 5);
                    if (chanceCheck.chanceCheck(.001))
                        rareItem(event.getMob(), MMOItemsHelperImpl.getItem("COMPANION", "PET_UNDEAD_CASTER3"), killer);
                    if (chanceCheck.chanceCheck(.2))
                        rareItem(event.getMob(), MMOItemsHelperImpl.getItem("CONSUMABLE", "MAGICAL_ESSENCE2"), killer);
                    if (chanceCheck.chanceCheck(.5))
                        rareItem(event.getMob(), MMOItemsHelperImpl.getItem("QUEST", "MAGICAL_FRAGMENT"), killer);
                    break;
                /*
                 *   Castle
                 */
                case "UNDEAD_WARRIOR":
                    giveItem(location, Material.BONE, 5);
                    giveItem(location, MMOItemsHelperImpl.getItem("MATERIAL", "BONE_FRAGMENT"), 5);
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
                        rareItem(event.getMob(), MMOItemsHelperImpl.getItem("HAMMER", "GOBLIN_HAMMER3"), killer);
                    break;
                case "BABY_GOBLIN2":
                case "GOBLIN":
                    giveItem(location, Material.ROTTEN_FLESH, 4);
                    if (chanceCheck.chanceCheck(.0125))
                        rareItem(event.getMob(), MMOItemsHelperImpl.getItem("HAMMER", "GOBLIN_HAMMER3"), killer);
                    break;
                case "GOBLIN2":
                    giveItem(location, Material.ROTTEN_FLESH, 5);
                    if (chanceCheck.chanceCheck(.015))
                        rareItem(event.getMob(), MMOItemsHelperImpl.getItem("HAMMER", "GOBLIN_HAMMER3"), killer);
                    break;
                case "ARCHER_GOBLIN":
                    giveItem(location, Material.ROTTEN_FLESH, 4);
                    giveItem(location, Material.ARROW, 3);
                    if (chanceCheck.chanceCheck(.015))
                        rareItem(event.getMob(), MMOItemsHelperImpl.getItem("HAMMER", "GOBLIN_HAMMER3"), killer);
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
                        rareItem(event.getMob(), MMOItemsHelperImpl.getItem("ARMOR", "BEE_WINGS2"), killer);
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
                    if (chanceCheck.chanceCheck(.0075))
                        rareItem(event.getMob(), MMOItemsHelperImpl.getItem("ARMOR", "BEE_WINGS2"), killer);
                    break;
                case "QUEEN_BEE":
                    forestDropTable(location);
                    giveItem(location, Material.HONEYCOMB, 5);
                    if (chanceCheck.chanceCheck(.01))
                        rareItem(event.getMob(), MMOItemsHelperImpl.getItem("ARMOR", "BEE_WINGS3"), killer);
                    break;
                /*
                 *   Cavern
                 */
                case "PILLAGER1":
                    if (chanceCheck.chanceCheck(.01))
                        rareItem(event.getMob(), MMOItemsHelperImpl.getItem("BOW", "PILLAGER_CROSSBOW2"), killer);
                    break;
                case "VINDICATOR1":
                    if (chanceCheck.chanceCheck(.01))
                        rareItem(event.getMob(), MMOItemsHelperImpl.getItem("AXE", "VINDICATOR_AXE2"), killer);
                    break;
                case "DROWNED1":
                    if (chanceCheck.chanceCheck(.01))
                        rareItem(event.getMob(), MMOItemsHelperImpl.getItem("TRIDENT", "DROWNED_TRIDENT2"), killer);
                    break;
                case "DOLPHIN1":
                    giveItem(location, Material.COD, 2);
                    break;
                case "TURTLE2":
                    giveItem(location, Material.SCUTE, 2);
                    break;

                case "ANGRY_SPIDER":
                    giveItem(location, Material.IRON_INGOT, 1);
                    giveItem(location, Material.GOLD_INGOT, 2);
                    giveItem(location, Material.COAL, 1);
                    giveItem(location, Material.SPIDER_EYE, 2);
                    giveItem(location, Material.STRING, 2);
                    break;

                case "VOID_BAT":
                    mineDropTable(location, event.getMob(), killer);
                    giveItem(location, Material.REDSTONE, 2);
                    giveItem(location, Material.LAPIS_LAZULI, 2);
                    break;
                case "VOID_BOOMER":
                    mineDropTable(location, event.getMob(), killer);
                    voidBoomer(location, event.getMob(), killer);
                    giveItem(location, Material.REDSTONE, 2);
                    giveItem(location, Material.LAPIS_LAZULI, 2);
                    giveItem(location, Material.GUNPOWDER, 3);
                    break;
                case "VOID_SPIDER":
                    mineDropTable(location, event.getMob(), killer);
                    giveItem(location, Material.REDSTONE, 2);
                    giveItem(location, Material.LAPIS_LAZULI, 2);
                    giveItem(location, Material.SPIDER_EYE, 3);
                    giveItem(location, Material.STRING, 3);
                    break;
                case "VOID_VAMPIRE":
                    mineDropTable(location, event.getMob(), killer);
                    voidVampire(location, event.getMob(), killer);
                    giveItem(location, Material.REDSTONE, 2);
                    giveItem(location, Material.LAPIS_LAZULI, 2);
                    giveItem(location, Material.BONE, 3);
                    break;
                case "VOID_ANT":
                    giveItem(location, Material.EMERALD, 1);
                    giveItem(location, Material.DIAMOND, 1);
                    break;
                case "VOID_VAMPIRE2":
                    mineDropTable(location, event.getMob(), killer);
                    voidVampire(location, event.getMob(), killer);
                    giveItem(location, Material.EMERALD, 2);
                    giveItem(location, Material.DIAMOND, 2);
                    giveItem(location, Material.BONE, 3);
                    break;
                case "VOID_DRACULA":
                    mineDropTable(location, event.getMob(), killer);
                    voidVampire(location, event.getMob(), killer);
                    giveItem(location, Material.EMERALD, 5);
                    giveItem(location, Material.DIAMOND, 5);
                    giveItem(location, Material.BONE, 5);
                    break;
                /*
                 *   Hell
                 */
                case "VOID_PIGMAN":
                    hellDropTable(location, event.getMob(), killer);
                    voidPigman(location, event.getMob(), killer);
                    giveItem(location, Material.ROTTEN_FLESH, 4);
                    break;
                case "VOID_DEMON":
                    hellDropTable(location, event.getMob(), killer);
                    voidDemon(location, event.getMob(), killer);
                    giveItem(location, Material.ROTTEN_FLESH, 4);
                    break;
                case "VOID_NECROMANCER":
                    hellDropTable(location, event.getMob(), killer);
                    voidNecromancer(location, event.getMob(), killer);
                    giveItem(location, Material.ROTTEN_FLESH, 4);
                    break;
                case "VOID_BLAZE":
                    hellDropTable(location, event.getMob(), killer);
                    voidBlaze(location, event.getMob(), killer);
                    giveItem(location, Material.BLAZE_ROD, 3);
                    break;
                case "VOID_PROTECTOR":
                    hellDropTable(location, event.getMob(), killer);
                    giveItem(location, Material.IRON_INGOT, 3);
                    break;
                case "VOID_HEALER":
                    hellDropTable(location, event.getMob(), killer);
                    voidHealer(location, event.getMob(), killer);
                    giveItem(location, Material.BONE, 3);
                    break;

                case "VOID_MAGMA":
                case "VOID_MAGMA2":
                case "VOID_MAGMA3":
                    voidMagma(location, event.getMob(), killer);
                    hellDropTable(location, event.getMob(), killer);
                    giveItem(location, Material.MAGMA_CREAM, 4);
                    break;
                case "VOID_SKELETON":
                    hellDropTable(location, event.getMob(), killer);
                    giveItem(location, Material.BONE, 4);
                    break;
                case "VOID_PIGMAN2":
                    hellDropTable(location, event.getMob(), killer);
                    voidPigman(location, event.getMob(), killer);
                    giveItem(location, Material.ROTTEN_FLESH, 5);
                    break;
                case "VOID_NECROMANCER2":
                    hellDropTable(location, event.getMob(), killer);
                    voidNecromancer(location, event.getMob(), killer);
                    giveItem(location, Material.BONE, 4);
                    break;

                /*
                 *   Void
                 */
                case "VOID_WORSHIPER":
                    voidDropTable(location, event.getMob(), killer);
                    voidWorshiper(location, event.getMob(), killer);
                    giveItem(location, Material.ENDER_PEARL, 4);
                    break;
                case "VOID_SOURCE":
                    voidDropTable(location, event.getMob(), killer);
                    voidSource(location, event.getMob(), killer);
                    giveItem(location, Material.SHULKER_SHELL, 4);
                    break;
                case "VOID_THRALL":
                    voidDropTable(location, event.getMob(), killer);
                    voidThrall(location, event.getMob(), killer);
                    giveItem(location, Material.OBSIDIAN, 3);
                    break;
                case "VOID_ASSASSIN":
                    voidDropTable(location, event.getMob(), killer);
                    voidAssassin(location, event.getMob(), killer);
                    giveItem(location, MMOItemsHelperImpl.getItem("MATERIAL", "CHORUS_SEED"), 3);
                    giveItem(location, Material.OBSIDIAN, 2);
                    break;
                case "VOID_VEIN":
                    voidDropTable(location, event.getMob(), killer);
                    giveItem(location, Material.WITHER_ROSE, 5);
                    break;

                case "VOID_MONITOR":
                    voidDropTable(location, event.getMob(), killer);
                    voidMonitor(location, event.getMob(), killer);
                    giveItem(location, Material.OBSIDIAN, 2);
                    break;
                case "VOID_MEGA_BOOMER":
                    voidDropTable(location, event.getMob(), killer);
                    voidMegaBoomer(location, event.getMob(), killer);
                    giveItem(location, Material.GUNPOWDER, 5);
                    break;
                case "VOID_CHAMPION":
                    voidDropTable(location, event.getMob(), killer);
                    voidChampion(location, event.getMob(), killer);
                    break;

                case "VOID_BOSS_DEFENDER":
                case "VOID_BOSS_DEFENDER3":
                    voidDropTable(location, event.getMob(), killer);
                    voidDefender(location, event.getMob(), killer);
                    giveItem(location, Material.OBSIDIAN, 5);
                    break;
                case "VOID_BOSS_DEFENDER2":
                    voidDropTable(location, event.getMob(), killer);
                    voidDefender(location, event.getMob(), killer);
                    break;
                case "VOID_BOSS_DEFENDER4":
                    voidDropTable(location, event.getMob(), killer);
                    voidDefender(location, event.getMob(), killer);
                    giveItem(location, Material.ENDER_PEARL, 3);
                    break;
            }
            if (chanceCheck.chanceCheck(.001))
                rareItem(event.getMob(), MMOItemsHelperImpl.getItem("CONSUMABLE", "BORGS_BOX5"), killer);
            else if (chanceCheck.chanceCheck(.025))
                rareItem(event.getMob(), MMOItemsHelperImpl.getItem("CONSUMABLE", "MAGICAL_BOX5"), killer);
            else if (chanceCheck.chanceCheck(.0095))
                rareItem(event.getMob(), MMOItemsHelperImpl.getItem("MATERIAL", "ENGRAM1"), killer);
            else if (chanceCheck.chanceCheck(.00075))
                rareItem(event.getMob(), MMOItemsHelperImpl.getItem("MATERIAL", "ENGRAM2"), killer);
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
            rareItem(activeMob, MMOItemsHelperImpl.getItem("CONSUMABLE", "HEART"), player);
        else if (chanceCheck.chanceCheck(.02))
            rareItem(activeMob, MMOItemsHelperImpl.getItem("GEM_STONE", "VOID_GEM"), player);
        else if (chanceCheck.chanceCheck(.01))
            rareItem(activeMob, MMOItemsHelperImpl.getItem("GEM_STONE", "VOID_GEM2"), player);
        else if (chanceCheck.chanceCheck(.0085))
            rareItem(activeMob, MMOItemsHelperImpl.getItem("GEM_STONE", "VOID_GEM3"), player);
    }

    private void hellDropTable(Location location, ActiveMob activeMob, Player player) {
        if (chanceCheck.chanceCheck(.005))
            rareItem(activeMob, MMOItemsHelperImpl.getItem("GEM_STONE", "HEATED_GEM3"), player);
        else if (chanceCheck.chanceCheck(.0075))
            rareItem(activeMob, MMOItemsHelperImpl.getItem("GEM_STONE", "HEATED_GEM2"), player);
        else if (chanceCheck.chanceCheck(.01))
            rareItem(activeMob, MMOItemsHelperImpl.getItem("GEM_STONE", "HEATED_GEM"), player);
        else if (chanceCheck.chanceCheck(.01))
            rareItem(activeMob, MMOItemsHelperImpl.getItem("CATALYST", "FIRE_CATALYST4"), player);
        else if (chanceCheck.chanceCheck(.25))
            giveItem(location, MMOItemsHelperImpl.getItem("MATERIAL", "VOID_SHARD"), 3);
    }

    private void voidDropTable(Location location, ActiveMob activeMob, Player player) {
        if (chanceCheck.chanceCheck(.005))
            rareItem(activeMob, MMOItemsHelperImpl.getItem("GEM_STONE", "VOID_GEM3"), player);
        else if (chanceCheck.chanceCheck(.0075))
            rareItem(activeMob, MMOItemsHelperImpl.getItem("GEM_STONE", "VOID_GEM2"), player);
        else if (chanceCheck.chanceCheck(.01))
            rareItem(activeMob, MMOItemsHelperImpl.getItem("GEM_STONE", "VOID_GEM"), player);
        else if (chanceCheck.chanceCheck(.25))
            giveItem(location, MMOItemsHelperImpl.getItem("MATERIAL", "VOID_SHARD"), 5);
    }

//

    private void undeadSkeleton(Location location, ActiveMob activeMob, Player player) {
        if (chanceCheck.chanceCheck(.005))
            rareItem(activeMob, MMOItemsHelperImpl.getItem("ARMOR", "SKELETON_SKULL3"), player);
        else if (chanceCheck.chanceCheck(.005))
            rareItem(activeMob, MMOItemsHelperImpl.getItem("ARMOR", "SKELETON_CHESTPLATE3"), player);
        else if (chanceCheck.chanceCheck(.005))
            rareItem(activeMob, MMOItemsHelperImpl.getItem("ARMOR", "SKELETON_LEGS3"), player);
        else if (chanceCheck.chanceCheck(.005))
            rareItem(activeMob, MMOItemsHelperImpl.getItem("ARMOR", "SKELETON_FEET3"), player);
        else if (chanceCheck.chanceCheck(.0045))
            rareItem(activeMob, MMOItemsHelperImpl.getItem("BOW", "UNDEAD_BOW3"), player);
        else if (chanceCheck.chanceCheck(.0035))
            rareItem(activeMob, MMOItemsHelperImpl.getItem("AXE", "BONE_AXE3"), player);
    }

    private void voidBoomer(Location location, ActiveMob activeMob, Player player) {
        if (chanceCheck.chanceCheck(.02))
            rareItem(activeMob, MMOItemsHelperImpl.getItem("SPELL", "BLACK_HOLE2").asQuantity(2), player);
        else if (chanceCheck.chanceCheck(.01))
            rareItem(activeMob, MMOItemsHelperImpl.getItem("SPELL", "BLACK_HOLE3").asQuantity(2), player);
    }

    private void voidVampire(Location location, ActiveMob activeMob, Player player) {
        if (chanceCheck.chanceCheck(.01))
            rareItem(activeMob, MMOItemsHelperImpl.getItem("DAGGER", "STEAK2"), player);
        else if (chanceCheck.chanceCheck(.50))
            rareItem(activeMob, MMOItemsHelperImpl.getItem("QUEST", "VAMPIRE_FANG2"), player);
    }

    private void voidBlaze(Location location, ActiveMob activeMob, Player player) {
        if (chanceCheck.chanceCheck(.005))
            rareItem(activeMob, MMOItemsHelperImpl.getItem("ARMOR", "BLAZE_CHEST4"), player);
        else if (chanceCheck.chanceCheck(.0075))
            rareItem(activeMob, MMOItemsHelperImpl.getItem("DAGGER", "BLAZE_ARM3"), player);
        else if (chanceCheck.chanceCheck(.01))
            rareItem(activeMob, MMOItemsHelperImpl.getItem("SPELL", "CURSED_BEAM2"), player);
        else if (chanceCheck.chanceCheck(.02))
            rareItem(activeMob, MMOItemsHelperImpl.getItem("SPELL", "CURSED_BEAM3"), player);
    }

    private void voidNecromancer(Location location, ActiveMob activeMob, Player player) {
        if (chanceCheck.chanceCheck(.005))
            rareItem(activeMob, MMOItemsHelperImpl.getItem("STAFF", "NECROMANCER_STAFF2"), player);
        else if (chanceCheck.chanceCheck(.01))
            rareItem(activeMob, MMOItemsHelperImpl.getItem("CONSUMABLE", "MAGICAL_ESSENCE3"), player);
    }

    private void voidHealer(Location location, ActiveMob activeMob, Player player) {
        if (chanceCheck.chanceCheck(.01))
            rareItem(activeMob, MMOItemsHelperImpl.getItem("ARMOR", "HEALER_HEAD4"), player);
    }

    private void voidDemon(Location location, ActiveMob activeMob, Player player) {
        if (chanceCheck.chanceCheck(.0075))
            rareItem(activeMob, MMOItemsHelperImpl.getItem("ARMOR", "DEMON_HEAD4"), player);
        else if (chanceCheck.chanceCheck(.20))
            rareItem(activeMob, MMOItemsHelperImpl.getItem("QUEST", "DEMON_CLAW"), player);
    }

    private void voidPigman(Location location, ActiveMob activeMob, Player player) {
        if (chanceCheck.chanceCheck(.0075))
            rareItem(activeMob, MMOItemsHelperImpl.getItem("ARMOR", "PIGMAN_HEAD3"), player);
    }

    private void voidMagma(Location location, ActiveMob activeMob, Player player) {
        if (chanceCheck.chanceCheck(.01))
            rareItem(activeMob, MMOItemsHelperImpl.getItem("SPELL", "MAGMA_FISSURE2"), player);
        else if (chanceCheck.chanceCheck(.02))
            rareItem(activeMob, MMOItemsHelperImpl.getItem("SPELL", "MAGMA_FISSURE3"), player);
    }

    private void voidWorshiper(Location location, ActiveMob activeMob, Player player) {
        if (chanceCheck.chanceCheck(.01))
            rareItem(activeMob, MMOItemsHelperImpl.getItem("SPELL", "ENDER_METEOR2"), player);
        else if (chanceCheck.chanceCheck(.02))
            rareItem(activeMob, MMOItemsHelperImpl.getItem("SPELL", "ENDER_METEOR3"), player);
        else if (chanceCheck.chanceCheck(.5))
            rareItem(activeMob, MMOItemsHelperImpl.getItem("QUEST", "VOID_DNA"), player);
    }

    private void voidSource(Location location, ActiveMob activeMob, Player player) {
        if (chanceCheck.chanceCheck(.01))
            rareItem(activeMob, MMOItemsHelperImpl.getItem("SPELL", "SHULKER_MISSILE2"), player);
        else if (chanceCheck.chanceCheck(.02))
            rareItem(activeMob, MMOItemsHelperImpl.getItem("SPELL", "SHULKER_MISSILE3"), player);
    }

    private void voidThrall(Location location, ActiveMob activeMob, Player player) {
        if (chanceCheck.chanceCheck(.01))
            rareItem(activeMob, MMOItemsHelperImpl.getItem("SPELL", "LIFE_ENDER2"), player);
        else if (chanceCheck.chanceCheck(.02))
            rareItem(activeMob, MMOItemsHelperImpl.getItem("SPELL", "LIFE_ENDER3"), player);
        else if (chanceCheck.chanceCheck(.5))
            rareItem(activeMob, MMOItemsHelperImpl.getItem("MATERIAL", "THRALL_EGG"), player);
    }

    private void voidAssassin(Location location, ActiveMob activeMob, Player player) {
        if (chanceCheck.chanceCheck(.01))
            rareItem(activeMob, MMOItemsHelperImpl.getItem("DAGGER", "SHADOW_DAGGER3"), player);
        else if (chanceCheck.chanceCheck(.5))
            rareItem(activeMob, MMOItemsHelperImpl.getItem("MATERIAL", "SHADOW_ESSENCE"), player);
    }


    private void voidMonitor(Location location, ActiveMob activeMob, Player player) {
        if (chanceCheck.chanceCheck(.01))
            rareItem(activeMob, MMOItemsHelperImpl.getItem("SPELL", "MONITOR_MUTE2"), player);
        else if (chanceCheck.chanceCheck(.02))
            rareItem(activeMob, MMOItemsHelperImpl.getItem("SPELL", "MONITOR_MUTE3"), player);
    }

    private void voidMegaBoomer(Location location, ActiveMob activeMob, Player player) {
        if (chanceCheck.chanceCheck(.01))
            rareItem(activeMob, MMOItemsHelperImpl.getItem("SPELL", "CURSED_BEAM2"), player);
        else if (chanceCheck.chanceCheck(.02))
            rareItem(activeMob, MMOItemsHelperImpl.getItem("SPELL", "CURSED_BEAM3"), player);
    }

    private void voidChampion(Location location, ActiveMob activeMob, Player player) {
        if (chanceCheck.chanceCheck(.01))
            rareItem(activeMob, MMOItemsHelperImpl.getItem("SPELL", "ENDER_METEOR2"), player);
        else if (chanceCheck.chanceCheck(.02))
            rareItem(activeMob, MMOItemsHelperImpl.getItem("SPELL", "ENDER_METEOR3"), player);
    }


    private void voidDefender(Location location, ActiveMob activeMob, Player player) {
        if (chanceCheck.chanceCheck(.1))
            rareItem(activeMob, MMOItemsHelperImpl.getItem("MATERIAL", "DEFENDER_FLESH"), player);
    }
}