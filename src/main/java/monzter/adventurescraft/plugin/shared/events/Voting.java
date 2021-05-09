package monzter.adventurescraft.plugin.shared.events;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import com.vexsoftware.votifier.model.Vote;
import com.vexsoftware.votifier.model.VotifierEvent;
import me.clip.placeholderapi.PlaceholderAPI;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.shared.events.extras.VoteRewardList;
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

import java.util.HashMap;

//https://github.com/NuVotifier/NuVotifier/wiki/Developer-Documentation
public class Voting extends BaseCommand implements Listener {
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
        switch (serviceName) {
            case "minestatus.net":
            case "MinecraftServers.org":
            case "MCSL":
            case "Minecraft-MP.com":
//                voteReward(player, vote.getUsername());
                voteAnnounce(vote.getUsername());
                consoleCommand.consoleCommand("VoteGive " + player.getName() + " " + serviceName);
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

    @CommandAlias("VoteClaim")
    private void voteClaimCommand(Player player, String arg) {
        final Integer voteCoins = Integer.valueOf(PlaceholderAPI.setPlaceholders(player, "%ac_Currency_VotingCoins%"));
        for (VoteRewardList reward : VoteRewardList.values()) {
            if (arg.equals(reward.getId())) {
                if (voteCoins >= reward.getPrice()) {
                    mmoItemsGive.giveMMOItem(player, reward.getType(), reward.getId(), reward.getAmount());
                    betonPointsManager.takePoint(player, "items.Vote", reward.getPrice());
                    soundManager.soundYes(player, 2);
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> {
                        player.sendMessage(ChatColor.GREEN + "Your purchase was successful and you now have " + ChatColor.GOLD + PlaceholderAPI.setPlaceholders(player, "%ac_Currency_VotingCoins%") + ChatColor.GREEN + " Vote Coins remaining!");
                    }, 5L);
                } else {
                    player.sendMessage(ChatColor.RED + "You only have " + ChatColor.GOLD + voteCoins + ChatColor.RED + "/" + ChatColor.GOLD + reward.getPrice() + ChatColor.RED + " Vote Coins!");
                    soundManager.soundNo(player, 1);
                }
            }
        }
    }
}