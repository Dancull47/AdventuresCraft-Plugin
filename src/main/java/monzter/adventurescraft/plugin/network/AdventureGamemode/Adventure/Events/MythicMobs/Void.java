package monzter.adventurescraft.plugin.network.AdventureGamemode.Adventure.Events.MythicMobs;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.util.Location;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.StringFlag;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobDeathEvent;
import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobDespawnEvent;
import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobSpawnEvent;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.general.ConsoleCommand;
import monzter.adventurescraft.plugin.utilities.general.Cooldown;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import monzter.adventurescraft.plugin.utilities.luckperms.PermissionLP;
import net.Indyuce.mmoitems.MMOItems;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.UUID;

import static org.bukkit.potion.PotionEffectType.DAMAGE_RESISTANCE;
import static org.bukkit.potion.PotionEffectType.REGENERATION;

public class Void implements Listener {
    private final AdventuresCraft plugin;
    private final SoundManager soundManager;
    private final PermissionLP permissionLP;
    private final ConsoleCommand consoleCommand;
    private final StringFlag displayNameFlag;
    private final MMOItems mmoItems;


    private final int duration = 20 * 8;

    public Void(AdventuresCraft plugin, SoundManager soundManager, PermissionLP permissionLP, ConsoleCommand consoleCommand, StringFlag displayNameFlag, MMOItems mmoItems) {
        this.plugin = plugin;
        this.soundManager = soundManager;
        this.permissionLP = permissionLP;
        this.consoleCommand = consoleCommand;
        this.displayNameFlag = displayNameFlag;
        this.mmoItems = mmoItems;
    }


    @EventHandler
    public void theVoid(PlayerInteractEvent event) {
        switch (plugin.SERVER) {
            case "Adventure":
                if (event.getClickedBlock() != null)
                    switch (event.getClickedBlock().getType()) {
                        case ACACIA_BUTTON:
                            potion(event.getPlayer(), PotionEffectType.INCREASE_DAMAGE);
                            break;
                        case WARPED_BUTTON:
                            potion(event.getPlayer(), PotionEffectType.SPEED);
                            break;
                        case CRIMSON_BUTTON:
                            potion(event.getPlayer(), REGENERATION);
                            break;
                        case DARK_OAK_BUTTON:
                            potion(event.getPlayer(), DAMAGE_RESISTANCE);
                            break;
                    }
        }
    }

    @EventHandler
    public void voidDamage(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        switch (plugin.SERVER) {
            case "Adventure":
                if (player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType().equals(Material.BEDROCK))
                    if (areaCheck(player, ChatColor.DARK_PURPLE + "Void Maze") || areaCheck(player, ChatColor.DARK_PURPLE + "Void")
                            || areaCheck(player, ChatColor.DARK_PURPLE + "Void Abyss")) {
                        if (!player.getInventory().getItemInOffHand().equals(mmoItems.getItem("CATALYST", "CORRUPTION_REVOLTER3"))
                                && !player.getInventory().getItemInOffHand().equals(mmoItems.getItem("CATALYST", "CORRUPTION_REVOLTER4"))) {
                            player.damage(player.getHealth() * .15);
                            if (!player.hasPotionEffect(PotionEffectType.POISON))
                                player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 20, 2));
                        }
                    }
        }
    }

    @EventHandler
    public void enchantressSpawn(MythicMobSpawnEvent event) {
        switch (event.getMobType().getInternalName()) {
            case "VOID_ENCHANTRESS":
                Cooldown cooldown = new Cooldown(UUID.fromString("123e4567-e89b-12d3-a456-556642440000"), "VOID_ENCHANTRESS", 600);
                cooldown.start();
                break;
            case "VOID_PORTAL":
                cooldown = new Cooldown(UUID.fromString("123e4567-e89b-12d3-a456-556642440000"), "VOID_PORTAL", 30);
                cooldown.start();
                break;
        }
    }

    @EventHandler
    public void enchantressDeath(MythicMobDeathEvent event) {
        switch (event.getMobType().getInternalName()) {
            case "VOID_ENCHANTRESS":
                Cooldown cooldown = new Cooldown(UUID.fromString("123e4567-e89b-12d3-a456-556642440000"), "VOID_ENCHANTRESS", 1);
                cooldown.start();
                break;
            case "VOID_PORTAL":
                cooldown = new Cooldown(UUID.fromString("123e4567-e89b-12d3-a456-556642440000"), "VOID_PORTAL", 1);
                cooldown.start();
                break;
        }
    }

    @EventHandler
    public void enchantressDeath(MythicMobDespawnEvent event) {
        switch (event.getMobType().getInternalName()) {
            case "VOID_ENCHANTRESS":
                Cooldown cooldown = new Cooldown(UUID.fromString("123e4567-e89b-12d3-a456-556642440000"), "VOID_ENCHANTRESS", 1);
                cooldown.start();
                break;
            case "VOID_PORTAL":
                cooldown = new Cooldown(UUID.fromString("123e4567-e89b-12d3-a456-556642440000"), "VOID_PORTAL", 1);
                cooldown.start();
                break;
        }
    }

    private void potion(Player player, PotionEffectType potionEffectType) {
        if (areaCheck(player, ChatColor.DARK_PURPLE + "Void Maze"))
            if (!player.hasPotionEffect(potionEffectType)) {
                if (potionEffectType.equals(DAMAGE_RESISTANCE) || potionEffectType.equals(REGENERATION))
                    player.addPotionEffect(new PotionEffect(potionEffectType, duration, 2));
                else
                    player.addPotionEffect(new PotionEffect(potionEffectType, duration, 5));
                soundManager.playSound(player, Sound.ENTITY_WITCH_DRINK, 1, 1);
            }
    }

    private boolean areaCheck(Player player, String area) {
        Location location = BukkitAdapter.adapt(player.getLocation());
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionQuery query = container.createQuery();
        ApplicableRegionSet set = query.getApplicableRegions(location);
        for (ProtectedRegion region : set)
            if (region.getFlag(displayNameFlag).equals(area))
                return true;
        return false;
    }
}