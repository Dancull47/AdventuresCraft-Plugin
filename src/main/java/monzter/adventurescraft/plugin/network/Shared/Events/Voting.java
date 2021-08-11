package monzter.adventurescraft.plugin.network.Shared.Events;

import com.vexsoftware.votifier.model.Vote;
import com.vexsoftware.votifier.model.VotifierEvent;
import me.vagdedes.mysql.database.MySQL;
import me.vagdedes.mysql.database.SQL;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.beton.BetonPointsManager;
import monzter.adventurescraft.plugin.utilities.general.ConsoleCommand;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import monzter.adventurescraft.plugin.utilities.mmoitems.MMOItemsGive;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

//https://github.com/NuVotifier/NuVotifier/wiki/Developer-Documentation
public class Voting implements Listener {
    private final AdventuresCraft plugin;
    private final ConsoleCommand consoleCommand;
    private final MMOItemsGive mmoItemsGive;
    private final SoundManager soundManager;
    private final BetonPointsManager betonPointsManager;
    private final HashMap<Player, Long> cooldown = new HashMap<>();
//    private final TextComponent vote = Component.text("Thanks for voting, claim your reward by using ")
//            .color(NamedTextColor.GREEN)
//            .append(Component.text("/Vote", NamedTextColor.GOLD))
//            .hoverEvent(Component.text("Click to claim rewards!", NamedTextColor.GREEN))
//            .clickEvent(ClickEvent.runCommand("/Vote"))
//            .append(Component.text("! You can vote again every 24 hours."));

    public Voting(AdventuresCraft plugin, ConsoleCommand consoleCommand, MMOItemsGive mmoItemsGive, SoundManager soundManager, BetonPointsManager betonPointsManager) {
        this.plugin = plugin;
        this.consoleCommand = consoleCommand;
        this.mmoItemsGive = mmoItemsGive;
        this.soundManager = soundManager;
        this.betonPointsManager = betonPointsManager;
    }

    @EventHandler
    private void vote(VotifierEvent event) {
//        String address = vote.getAddress();
//        System.out.println(address);
//        System.out.println(vote.toString());
//        System.out.println(serviceName);
        final Vote vote = event.getVote();
        final Player player = Bukkit.getPlayer(vote.getUsername());
        final String serviceName = vote.getServiceName();
        switch (plugin.SERVER) {
            case "Adventure":
            case "Home":
            case "Prison":
            case "Cell":
                switch (serviceName) {
                    case "minestatus.net":
                    case "MinecraftServers.org":
                    case "MCSL":
                    case "Minecraft-MP.com":
//                voteReward(player, vote.getUsername());
                        voteAnnounce(vote.getUsername());
                        if (exists(player.getUniqueId().toString(), serviceName)) {
                            Timestamp timeOfLastVote = (Timestamp) SQL.get("last_vote", new String[]{"uuid = " + "'" + player.getUniqueId() + "'", "vote_site = " + "'" + serviceName + "'"}, "ac_votes");
                            player.sendMessage(timeOfLastVote + "time of last");
                            Instant instant = Instant.now().minus(24, ChronoUnit.HOURS);
                            player.sendMessage(Timestamp.from(instant) + " ago");
                            int comparison = timeOfLastVote.compareTo(Timestamp.from(instant));
                            if (comparison <= 0) {
                                SQL.set("last_vote", Timestamp.valueOf(LocalDateTime.now()), new String[]{"uuid = " + "'" + player.getUniqueId() + "'", "vote_site = " + "'" + serviceName + "'"}, "ac_votes");
                            } else {
                                player.sendMessage(ChatColor.RED + "You must wait " + ChatColor.GOLD + time(timeOfLastVote) + ChatColor.RED + " until voting again!");
                            }
                            plugin.getLogger().info("Exists");
                        } else {
                            plugin.getLogger().info("Doesn't exist!");
                            SQL.insertData("uuid, vote_site, last_vote", "'" + player.getUniqueId() + "'" + ", '" + serviceName + "', '" + Timestamp.valueOf(LocalDateTime.now()) + "'", "ac_votes");
                        }
//                        consoleCommand.consoleCommand("VoteGive " + player.getName() + " " + serviceName);
                        break;
                }
                break;
        }
    }


//    public void voteReward(Player player, String name) {
//        if (player != null && player.isOnline()) {
//            player.sendMessage(vote);
//            player.playSound(player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LARGE_BLAST_FAR, .5f, 1f);
//        }
//        Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "q point " + name + " add items.Vote " + "1");
//        voteAnnounce(name);
//    }

    private void voteAnnounce(String playerName) {
        final Player player = Bukkit.getPlayer(playerName);
        if (player == null) {
            final TextComponent voteAnnounceNull = Component.text(ChatColor.YELLOW + "Someone" + ChatColor.GREEN + " just voted and received awesome rewards! ")
                    .color(NamedTextColor.GREEN)
                    .hoverEvent(Component.text("Click to visit Voting Guide!", NamedTextColor.GREEN))
                    .clickEvent(ClickEvent.openUrl("https://www.adventurescraft.net/wiki/site/vote/"));
            Bukkit.getServer().broadcast(voteAnnounceNull, "");
        } else {
            final TextComponent voteAnnounce = Component.text(ChatColor.YELLOW + player.getName() + ChatColor.GREEN + " just voted and received awesome rewards! ")
                    .color(NamedTextColor.GREEN)
                    .hoverEvent(Component.text("Click to visit Voting Guide!", NamedTextColor.GREEN))
                    .clickEvent(ClickEvent.openUrl("https://www.adventurescraft.net/wiki/site/vote/"));
            if (cooldown.containsKey(player)) {
                if ((cooldown.get(player)) < (System.currentTimeMillis())) {
                    cooldown.put(player, (System.currentTimeMillis() + (300 * 1000)));
                    Bukkit.getServer().broadcast(voteAnnounce, "");
                }
            } else {
                cooldown.put(player, (System.currentTimeMillis() + (300 * 1000)));
                Bukkit.getServer().broadcast(voteAnnounce, "");
            }
        }
    }

    public boolean exists(String uuid, String site) {
        try {
            ResultSet rs = MySQL.query("SELECT * FROM " + "ac_votes" + " WHERE " + "uuid " + "= " + "'" + uuid + "'" + " AND vote_site = " + "'" + site + "'" + " ;");
            if (rs.next()) {
                return true;
            }
        } catch (Exception var4) {
        }

        return false;
    }

    public String time(Timestamp timestamp) {
        Long timeUntil = (timestamp.getTime() + 86_400_000) - System.currentTimeMillis();
        long hours = TimeUnit.MILLISECONDS.toHours(timeUntil);
        timeUntil -= TimeUnit.HOURS.toMillis(hours);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(timeUntil);
        timeUntil -= TimeUnit.MINUTES.toMillis(minutes);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(timeUntil);
        StringBuilder sb = new StringBuilder();
        sb.append(hours);
        if (hours > 1) {
            sb.append(" Hours ");
        } else if (hours == 1) {
            sb.append(" Hour ");
        }
        sb.append(minutes);
        if (minutes > 1) {
            sb.append(" Minutes ");
        } else if (minutes == 1) {
            sb.append(" Minute ");
        }
        if (hours < 1 && seconds > 1) {
            sb.append(seconds);
            sb.append(" Seconds ");
        } else if (hours < 1 && seconds < 1) {
            sb.append(seconds);
            sb.append(" Second ");
        }
        if (hours < 1 && minutes < 1 && seconds < 1) {
            sb.append("Ready");
        }
//        plugin.getLogger().info("Hours " + hours + "Minutes " + minutes + "Seconds " + seconds);
        return (sb.toString());
    }

}