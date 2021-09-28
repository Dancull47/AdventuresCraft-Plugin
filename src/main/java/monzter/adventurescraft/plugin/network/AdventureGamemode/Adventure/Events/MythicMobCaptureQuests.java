package monzter.adventurescraft.plugin.network.AdventureGamemode.Adventure.Events;

import io.lumine.xikage.mythicmobs.MythicMobs;
import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.general.ConsoleCommand;
import monzter.adventurescraft.plugin.utilities.general.CooldownOld;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import monzter.adventurescraft.plugin.utilities.luckperms.PermissionLP;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;

import java.util.concurrent.ThreadLocalRandom;

public class MythicMobCaptureQuests implements Listener {
    private final AdventuresCraft plugin;
    private final SoundManager soundManager;
    private final PermissionLP permissionLP;
    private final ConsoleCommand consoleCommand;


    public MythicMobCaptureQuests(AdventuresCraft plugin, SoundManager soundManager, PermissionLP permissionLP, ConsoleCommand consoleCommand) {
        this.plugin = plugin;
        this.soundManager = soundManager;
        this.permissionLP = permissionLP;
        this.consoleCommand = consoleCommand;
    }


    @EventHandler
    public void clickChicken(PlayerInteractEntityEvent event) {
        if (event.getRightClicked() != null && event.getRightClicked().getType() == EntityType.CHICKEN && MythicMobs.inst().getMobManager().isActiveMob(BukkitAdapter.adapt(event.getRightClicked()))
                && MythicMobs.inst().getMobManager().getMythicMobInstance(event.getRightClicked()).getType().getInternalName().equals("CUCCO") &&
                event.getHand() == EquipmentSlot.HAND && !CooldownOld.isInCooldown(event.getPlayer().getUniqueId(), "Cucco")) {
            ActiveMob activeMob = MythicMobs.inst().getMobManager().getMythicMobInstance(event.getRightClicked());
            Player player = event.getPlayer();
            int cooldownSeconds = ThreadLocalRandom.current().nextInt(3, 7);
            CooldownOld cooldown = new CooldownOld(player.getUniqueId(), "Cucco", cooldownSeconds);
            cooldown.start();
            activeMob.setOwner(player.getUniqueId());
            player.addPassenger(event.getRightClicked());
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> {
                if (activeMob != null && !activeMob.isDead() && event.getRightClicked().isInsideVehicle()) {
                    player.removePassenger(event.getRightClicked());
                    player.sendMessage(ChatColor.RED + "The " + ChatColor.GREEN + "Cucco " + ChatColor.RED + "jumped out of your hands!");
                    soundManager.playSound(player, Sound.ENTITY_CHICKEN_HURT, 1, 1);
                }
            }, 20 * cooldownSeconds);
        }
    }

    @EventHandler
    public void clickOcelot(PlayerInteractEntityEvent event) {
        if (event.getRightClicked() != null && event.getRightClicked().getType() == EntityType.OCELOT && event.getHand() == EquipmentSlot.HAND && event.getPlayer().getInventory().getItemInMainHand().getType() == Material.LEAD &&
                MythicMobs.inst().getMobManager().isActiveMob(BukkitAdapter.adapt(event.getRightClicked())) &&
                MythicMobs.inst().getMobManager().getMythicMobInstance(event.getRightClicked()).getType().getInternalName().equals("WILD_CAT") &&
                !CooldownOld.isInCooldown(event.getPlayer().getUniqueId(), "WILD_CAT")) {
            Player player = event.getPlayer();
            if (((LivingEntity) event.getRightClicked()).isLeashed()) {
                player.sendMessage(ChatColor.RED + "This " + ChatColor.GREEN + "Cat " + ChatColor.RED + "is already leashed by someone else!");
            } else {
                ActiveMob activeMob = MythicMobs.inst().getMobManager().getMythicMobInstance(event.getRightClicked());
                int cooldownSeconds = ThreadLocalRandom.current().nextInt(3, 9);
                CooldownOld cooldown = new CooldownOld(player.getUniqueId(), "WILD_CAT", cooldownSeconds);
                cooldown.start();
                activeMob.setOwner(player.getUniqueId());
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> {
                    if (activeMob != null && !activeMob.isDead() && ((LivingEntity) event.getRightClicked()).isLeashed()) {
                        ((LivingEntity) event.getRightClicked()).setLeashHolder(null);
                        player.sendMessage(ChatColor.RED + "The " + ChatColor.GREEN + "Cat " + ChatColor.RED + "broke the leash!");
                        soundManager.playSound(player, Sound.ENTITY_LEASH_KNOT_BREAK, 1, 1);
                    }
                }, 20 * cooldownSeconds);
            }
        }
    }

}