package monzter.adventurescraft.plugin.event;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import com.vexsoftware.votifier.model.Vote;
import com.vexsoftware.votifier.model.VotifierEvent;
import me.clip.placeholderapi.PlaceholderAPI;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.event.extras.VoteRewardList;
import monzter.adventurescraft.plugin.utilities.ConsoleCommand;
import monzter.adventurescraft.plugin.utilities.MMOItemsGiveItem;
import monzter.adventurescraft.plugin.utilities.SoundManager;
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
    private final MMOItemsGiveItem mmoItemsGiveItem;
    private final SoundManager soundManager;
    private final HashMap<Player, Long> cooldown = new HashMap<>();
//    private final TextComponent vote = Component.text("Thanks for voting, claim your reward by using ")
//            .color(NamedTextColor.GREEN)
//            .append(Component.text("/Vote", NamedTextColor.GOLD))
//            .hoverEvent(Component.text("Click to claim rewards!", NamedTextColor.GREEN))
//            .clickEvent(ClickEvent.runCommand("/Vote"))
//            .append(Component.text("! You can vote again every 24 hours."));

    public Voting(AdventuresCraft plugin, ConsoleCommand consoleCommand, MMOItemsGiveItem mmoItemsGiveItem, SoundManager soundManager) {
        this.plugin = plugin;
        this.consoleCommand = consoleCommand;
        this.mmoItemsGiveItem = mmoItemsGiveItem;
        this.soundManager = soundManager;
    }

    @EventHandler
    public void vote(VotifierEvent event) {
//        String address = vote.getAddress();
//        System.out.println(address);
//        System.out.println(vote.toString());
//        System.out.println(serviceName);
        Vote vote = event.getVote();
        Player player = Bukkit.getPlayer(vote.getUsername());
        String serviceName = vote.getServiceName();
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

    public void voteAnnounce(String playerName) {
        Player player = Bukkit.getPlayer(playerName);
        if (player == null) {
            TextComponent voteAnnounceNull = Component.text(ChatColor.YELLOW + "Someone" + ChatColor.GREEN + " just voted and received awesome rewards! ")
                    .color(NamedTextColor.GREEN)
                    .hoverEvent(Component.text("Click to visit Voting Guide!", NamedTextColor.GREEN))
                    .clickEvent(ClickEvent.openUrl("https://www.adventurescraft.net/wiki/site/vote/"));
            Bukkit.getServer().broadcast(voteAnnounceNull, "");
        } else {
            TextComponent voteAnnounce = Component.text(ChatColor.YELLOW + player.getName() + ChatColor.GREEN + " just voted and received awesome rewards! ")
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
    public void voteClaimCommand(Player player, String arg) {
        Integer voteCoins = Integer.valueOf(PlaceholderAPI.setPlaceholders(player, "%ac_Currency_VotingCoins%"));
        for (VoteRewardList reward: VoteRewardList.values()){
            if (arg.equals(reward.getId())){
                if (voteCoins >= reward.getPrice()){
                    mmoItemsGiveItem.giveMMOItem(player, reward.getType(), reward.getId(), reward.getAmount());
                    consoleCommand.consoleCommand("q point " + player.getName() + " add items.Vote -" + reward.getPrice());
                    soundManager.soundYes(player,2);
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> {
                        player.sendMessage(ChatColor.GREEN + "Your purchase was successful and you now have " + ChatColor.GOLD + PlaceholderAPI.setPlaceholders(player, "%ac_Currency_VotingCoins%") + ChatColor.GREEN + " Vote Coins remaining!");
                    }, 5L);
                } else {
                    player.sendMessage(ChatColor.RED + "You only have " + ChatColor.GOLD + voteCoins + ChatColor.RED + "/" + ChatColor.GOLD + reward.getPrice() + ChatColor.RED + " Vote Coins!");
                    soundManager.soundNo(player, 1);
                }
            }
        }

        if (arg.isEmpty()) {
            Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "dm open Pets " + player.getName());

        }
    }
}