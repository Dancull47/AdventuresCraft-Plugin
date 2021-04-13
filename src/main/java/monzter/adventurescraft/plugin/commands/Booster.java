package monzter.adventurescraft.plugin.commands;

import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.Language;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

public class Booster implements CommandExecutor {
    private final AdventuresCraft plugin;

    public Booster(AdventuresCraft plugin) {
            this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            Player target = Bukkit.getPlayer(args[1]);
            if (args[1].contains("global")){
                globalBooster(args[0], Integer.valueOf(args[2]), Integer.valueOf(args[3]));
                return true;
            } else if (target != null){
                booster(args[0], target, Integer.valueOf(args[2]), Integer.valueOf(args[3]));
            }
        }
        return true;
    }

    private void booster(String boosterType, Player player, int boosterTier, int boosterDuration){
        double x =player.getLocation().getX();
        double y =player.getLocation().getY();
        double z =player.getLocation().getZ();
        String booster = null;
        Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "lp user " + player.getName() + " permission settemp " + boosterType.toLowerCase() + ".booster." + boosterTier + " true " + boosterDuration + "m");
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_VILLAGER_CELEBRATE, 1f, 2f);
        player.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, x,y+2,z, 5,.25,.25,.25);
        if (boosterType.contains("SELL")){
            booster = ChatColor.DARK_GREEN + "⛂ Sell Booster ";
        } else if (boosterType.contains("EXP")){
            booster = ChatColor.GREEN + "۞ EXP Booster ";
        }
        else if (boosterType.contains("PET_EXP")){
            booster = ChatColor.AQUA + "❉ Pet EXP Booster ";
        }
        else if (boosterType.contains("BLOCK")){
            booster = ChatColor.DARK_RED + "回 Block Multiplier Booster ";
        }
        else if (boosterType.contains("LUCK")){
            booster = ChatColor.YELLOW + "⚅ Luck Multiplier Booster ";
        }
        player.sendMessage(ChatColor.GREEN + "You've just activated a " + ChatColor.YELLOW + boosterTier + "x " + booster
                + ChatColor.GREEN + "for " + ChatColor.YELLOW + boosterDuration + " minutes" + ChatColor.GREEN + "!");

    }

    public void globalBooster(String boosterType, int boosterTier, int boosterDuration){
        for (Player player : plugin.getServer().getOnlinePlayers()){
            player.sendTitle(ChatColor.GOLD.toString() + ChatColor.BOLD + "GLOBAL BOOSTER", ChatColor.GREEN.toString() + ChatColor.BOLD +"ACTIVATED!", 10,70,20);
            player.sendMessage(ChatColor.GOLD + "A " + ChatColor.YELLOW + ChatColor.BOLD + "GLOBAL BOOSTER " + ChatColor.GOLD + "has been activated!");
            player.getWorld().playSound(player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LARGE_BLAST, 1f, 1f);
            player.getWorld().playSound(player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LARGE_BLAST_FAR, 1f, 1f);
            booster(boosterType, player, boosterTier, boosterDuration);
        }
    }
}

