package monzter.adventurescraft.plugin.network.PrisonGamemode.prison.events;

import io.lumine.mythic.utils.Schedulers;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.enums.Prefix;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import net.citizensnpcs.api.CitizensAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;

public class Xur implements Listener {
    private AdventuresCraft plugin;
    private final SoundManager soundManager;


    public Xur(AdventuresCraft plugin, SoundManager soundManager) {
        this.plugin = plugin;
        this.soundManager = soundManager;
    }

    ZoneId timeZone = ZoneId.of("America/New_York");
    LocalDate today = LocalDate.now(timeZone);
    DayOfWeek currentDay = today.getDayOfWeek();
    LocalTime currentTime = LocalTime.now(timeZone);
    LocalTime current = LocalTime.parse(currentTime.toString());
    LocalTime startTime = LocalTime.parse("16:00:00"); // 4PM EST
    LocalTime leaveTime = LocalTime.parse("19:00:00"); // 7PM EST

    Location location = new Location(Bukkit.getWorld("World"), 1163.5, 202, 1585.5);

    //                plugin.getLogger().info("Is spawned");
//
//                plugin.getLogger().info(currentTime + " Current Time");

    public void onEnable() {
        Schedulers.sync().runRepeating(task -> {
            if (CitizensAPI.getNPCRegistry().getById(60).isSpawned()) {
                if (currentDay.equals(DayOfWeek.SUNDAY)) {
                    if (current.compareTo(leaveTime) >= 0) {
                        CitizensAPI.getNPCRegistry().getById(60).despawn();
                        task.close();
                        plugin.getLogger().info("Xur task closed, spawned Sunday, past leaving time (He's now despawned.)");
                    }
                } else if (currentDay.equals(DayOfWeek.FRIDAY) || currentDay.equals(DayOfWeek.SATURDAY)){
                    task.close();
                    plugin.getLogger().info("Xur task closed, spawned Friday or Saturday.");
                } else {
                    CitizensAPI.getNPCRegistry().getById(60).despawn();
                    task.close();
                    plugin.getLogger().info("Xur task closed, spawned (He's now despawned.)");
                }
            } else {
                if (currentDay.equals(DayOfWeek.FRIDAY)) {
                    if (current.compareTo(startTime) >= 0) {
                        spawnXur();
                        task.close();
                        plugin.getLogger().info("Xur task closed, it's Friday and past starting time (He just spawned!)");
                    }
                } else {
                    task.close();
                    plugin.getLogger().info("Xur task closed, it's not Friday.");
                }
            }

        }, 20, 2400);
    }

    private void spawnXur() {
        CitizensAPI.getNPCRegistry().getById(60).spawn(location);
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(Prefix.PREFIX.getString() + ChatColor.DARK_PURPLE + "Rux " + ChatColor.GREEN + "has arrived in the " + ChatColor.YELLOW + "Yard" + ChatColor.GREEN + "!");
            soundManager.playSound(player, Sound.BLOCK_BELL_USE, 1, 1);
        }
    }


}
