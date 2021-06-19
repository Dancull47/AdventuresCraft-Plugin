package monzter.adventurescraft.plugin.network.Shared.Events;

import co.aikar.commands.BaseCommand;
import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobDeathEvent;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.beton.BetonPointsManager;
import monzter.adventurescraft.plugin.utilities.general.FullInventory;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class MythicMobs extends BaseCommand implements Listener {
    private final AdventuresCraft plugin;
    private final FullInventory fullInventory;
    private final BetonPointsManager betonPointsManager;
    private final SoundManager soundManager;



    public MythicMobs(AdventuresCraft plugin, FullInventory fullInventory, BetonPointsManager betonPointsManager, SoundManager soundManager) {
        this.plugin = plugin;
        this.fullInventory = fullInventory;
        this.betonPointsManager = betonPointsManager;
        this.soundManager = soundManager;
    }

    @EventHandler
    public void mobTrack(MythicMobDeathEvent event) {
        Player player = (Player) event.getKiller();
        if (player != null)
            switch (plugin.SERVER) {
                case "Prison":
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
                        case "GHASTLY":
                        case "BULBLIN":
                        case "BULLBO":
                        case "ENCHANTRESS":
                            for (AbstractEntity abstractEntity : event.getMob().getThreatTable().getAllThreatTargets())
                                if (abstractEntity.isPlayer()) {
                                    Player player1 = Bukkit.getPlayer(abstractEntity.getName());
                                    betonPointsManager.givePoint(player1, "bossReward." + event.getMobType().getInternalName().toUpperCase(), 1);
                                    player1.sendMessage(ChatColor.GREEN + "You helped defeat the " + event.getMob().getDisplayName() + ChatColor.GREEN + " and earned a " + ChatColor.GOLD + "reward" + ChatColor.GREEN + "!"
                                            + ChatColor.GREEN + "Claim your reward by using " + ChatColor.GOLD + "/bossdex" + ChatColor.GREEN + "!");
                                    player1.playSound(player1.getLocation(), Sound.ENTITY_VILLAGER_CELEBRATE, 1, 1);
                                }
                            break;
                    }
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
}