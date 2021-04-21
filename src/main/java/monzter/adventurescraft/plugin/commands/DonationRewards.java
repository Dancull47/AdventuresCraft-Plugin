package monzter.adventurescraft.plugin.commands;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import me.clip.placeholderapi.PlaceholderAPI;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.event.acUtils;
import monzter.adventurescraft.plugin.event.extras.DonationRewardList;
import monzter.adventurescraft.plugin.event.extras.StatsDisplay;
import net.Indyuce.mmocore.api.player.PlayerData;
import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class DonationRewards implements CommandExecutor {
    private final AdventuresCraft plugin;

    public DonationRewards(AdventuresCraft plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = ((Player) sender).getPlayer();
            int adventureCoins = Integer.valueOf(PlaceholderAPI.setPlaceholders(player, "%ac_Currency_AdventureCoins%"));
            if (args.length < 1) {
                player.sendMessage(ChatColor.RED + "/DonationRewards <Item>");
                return true;
            } else {
                if (!fullInventory(player)) {
                    for (DonationRewardList donationReward : DonationRewardList.values()) {
                        if (args[0].contains(donationReward.id)) {
                            switch (args[0]) {
                                case "PetSlot":
                                    if (priceCheck(player, adventureCoins, donationReward.price)) {
                                        if (!soldOut(player, donationReward.id, donationReward.displayName + "s")) {
                                            purchaseItem(player, donationReward.price, donationReward.displayName);
                                            donationPoint(player, donationReward.id);
                                            Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "q point " + player.getName() + " add items.MaxPetAmount 1");
                                            if (player.hasPermission("DONATE.PET.SLOT.1")) {
                                                acUtils.givePermission(player, "DONATE.PET.SLOT.2");
                                            } else {
                                                acUtils.givePermission(player, "DONATE.PET.SLOT.1");
                                            }
                                            return true;
                                        }
                                        break;
                                    }
                                    break;
                                case "UnlimitedWeight":
                                    if (priceCheck(player, adventureCoins, donationReward.price)) {
                                        if (!soldOut(player, donationReward.id, donationReward.displayName)) {
                                            purchaseItem(player, donationReward.price, donationReward.displayName);
                                            donationPoint(player, donationReward.id);
                                            Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "q point " + player.getName() + " add items.MaxWeight 999999999");
                                            acUtils.givePermission(player, "DONATE.UNLIMITED.WEIGHT");
                                            return true;
                                        }
                                    }
                                    break;
                                case "ExoticLootbox5":
                                    if (priceCheck(player, adventureCoins, donationReward.price)) {
                                        if (!soldOut(player, donationReward.id, donationReward.displayName)) {
                                            purchaseItem(player, donationReward.price, donationReward.displayName);
                                            donationPoint(player, donationReward.id);
                                            acUtils.giveMMOItem(player, "CONSUMABLE", "LOOTBOX5", 5);
                                            return true;
                                        }
                                    }
                                    break;
                                case "ExoticLootbox10":
                                    if (priceCheck(player, adventureCoins, donationReward.price)) {
                                        if (!soldOut(player, donationReward.id, donationReward.displayName)) {
                                            purchaseItem(player, donationReward.price, donationReward.displayName);
                                            donationPoint(player, donationReward.id);
                                            acUtils.giveMMOItem(player, "CONSUMABLE", "LOOTBOX5", 10);
                                            return true;
                                        }
                                    }
                                    break;
                                case "MythicalLootbox5":
                                    if (priceCheck(player, adventureCoins, donationReward.price)) {
                                        if (!soldOut(player, donationReward.id, donationReward.displayName)) {
                                            purchaseItem(player, donationReward.price, donationReward.displayName);
                                            donationPoint(player, donationReward.id);
                                            acUtils.giveMMOItem(player, "CONSUMABLE", "LOOTBOX6", 5);
                                            return true;
                                        }
                                    }
                                    break;
                                case "MythicalLootbox10":
                                    if (priceCheck(player, adventureCoins, donationReward.price)) {
                                        if (!soldOut(player, donationReward.id, donationReward.displayName)) {
                                            purchaseItem(player, donationReward.price, donationReward.displayName);
                                            donationPoint(player, donationReward.id);
                                            acUtils.giveMMOItem(player, "CONSUMABLE", "LOOTBOX6", 10);
                                            return true;
                                        }
                                    }
                                    break;
                                case "GodlyLootbox5":
                                    if (priceCheck(player, adventureCoins, donationReward.price)) {
                                        if (!soldOut(player, donationReward.id, donationReward.displayName)) {
                                            purchaseItem(player, donationReward.price, donationReward.displayName);
                                            donationPoint(player, donationReward.id);
                                            acUtils.giveMMOItem(player, "CONSUMABLE", "LOOTBOX7", 5);
                                            return true;
                                        }
                                    }
                                    break;
                                case "GodlyLootbox10":
                                    if (priceCheck(player, adventureCoins, donationReward.price)) {
                                        if (!soldOut(player, donationReward.id, donationReward.displayName)) {
                                            purchaseItem(player, donationReward.price, donationReward.displayName);
                                            donationPoint(player, donationReward.id);
                                            acUtils.giveMMOItem(player, "CONSUMABLE", "LOOTBOX7", 10);
                                            return true;
                                        }
                                    }
                                    break;
                                case "ExoticEgg5":
                                    if (priceCheck(player, adventureCoins, donationReward.price)) {
                                        if (!soldOut(player, donationReward.id, donationReward.displayName)) {
                                            purchaseItem(player, donationReward.price, donationReward.displayName);
                                            donationPoint(player, donationReward.id);
                                            acUtils.giveMMOItem(player, "PET", "PET_EGG5", 5);
                                            return true;
                                        }
                                    }
                                    break;
                                case "ExoticEgg10":
                                    if (priceCheck(player, adventureCoins, donationReward.price)) {
                                        if (!soldOut(player, donationReward.id, donationReward.displayName)) {
                                            purchaseItem(player, donationReward.price, donationReward.displayName);
                                            donationPoint(player, donationReward.id);
                                            acUtils.giveMMOItem(player, "PET", "PET_EGG5", 10);
                                            return true;
                                        }
                                    }
                                    break;
                                case "MythicalEgg5":
                                    if (priceCheck(player, adventureCoins, donationReward.price)) {
                                        if (!soldOut(player, donationReward.id, donationReward.displayName)) {
                                            purchaseItem(player, donationReward.price, donationReward.displayName);
                                            donationPoint(player, donationReward.id);
                                            acUtils.giveMMOItem(player, "PET", "PET_EGG6", 5);
                                            return true;
                                        }
                                    }
                                    break;
                                case "MythicalEgg10":
                                    if (priceCheck(player, adventureCoins, donationReward.price)) {
                                        if (!soldOut(player, donationReward.id, donationReward.displayName)) {
                                            purchaseItem(player, donationReward.price, donationReward.displayName);
                                            donationPoint(player, donationReward.id);
                                            acUtils.giveMMOItem(player, "PET", "PET_EGG6", 10);
                                            return true;
                                        }
                                    }
                                    break;
                                case "GodlyEgg5":
                                    if (priceCheck(player, adventureCoins, donationReward.price)) {
                                        if (!soldOut(player, donationReward.id, donationReward.displayName)) {
                                            purchaseItem(player, donationReward.price, donationReward.displayName);
                                            donationPoint(player, donationReward.id);
                                            acUtils.giveMMOItem(player, "PET", "PET_EGG7", 5);
                                            return true;
                                        }
                                    }
                                    break;
                                case "GodlyEgg10":
                                    if (priceCheck(player, adventureCoins, donationReward.price)) {
                                        if (!soldOut(player, donationReward.id, donationReward.displayName)) {
                                            purchaseItem(player, donationReward.price, donationReward.displayName);
                                            donationPoint(player, donationReward.id);
                                            acUtils.giveMMOItem(player, "PET", "PET_EGG7", 10);
                                            return true;
                                        }
                                    }
                                    break;
                                case "LegendaryDragonEgg5":
                                    if (priceCheck(player, adventureCoins, donationReward.price)) {
                                        if (!soldOut(player, donationReward.id, donationReward.displayName)) {
                                            purchaseItem(player, donationReward.price, donationReward.displayName);
                                            donationPoint(player, donationReward.id);
                                            acUtils.giveMMOItem(player, "PET", "DRAGON_EGG4", 5);
                                            return true;
                                        }
                                    }
                                    break;
                                case "LegendaryDragonEgg10":
                                    if (priceCheck(player, adventureCoins, donationReward.price)) {
                                        if (!soldOut(player, donationReward.id, donationReward.displayName)) {
                                            purchaseItem(player, donationReward.price, donationReward.displayName);
                                            donationPoint(player, donationReward.id);
                                            acUtils.giveMMOItem(player, "PET", "DRAGON_EGG4", 10);
                                            return true;
                                        }
                                    }
                                    break;
                                case "LegendaryPhoenixEgg5":
                                    if (priceCheck(player, adventureCoins, donationReward.price)) {
                                        if (!soldOut(player, donationReward.id, donationReward.displayName)) {
                                            purchaseItem(player, donationReward.price, donationReward.displayName);
                                            donationPoint(player, donationReward.id);
                                            acUtils.giveMMOItem(player, "PET", "PHOENIX_EGG4", 5);
                                            return true;
                                        }
                                    }
                                    break;
                                case "LegendaryPhoenixEgg10":
                                    if (priceCheck(player, adventureCoins, donationReward.price)) {
                                        if (!soldOut(player, donationReward.id, donationReward.displayName)) {
                                            purchaseItem(player, donationReward.price, donationReward.displayName);
                                            donationPoint(player, donationReward.id);
                                            acUtils.giveMMOItem(player, "PET", "PHOENIX_EGG4", 10);
                                            return true;
                                        }
                                    }
                                    break;
                            }
                        }
                    }

                }
            }
        }
        return false;
    }

    public boolean fullInventory(Player player) {
        if (player.getInventory().firstEmpty() == -1) {
            player.sendMessage(ChatColor.RED + "You're inventory is full! Try again once you have at least one available slot!");
            return true;
        }
        return false;
    }

    public boolean priceCheck(Player player, int coins, int cost) {
        if (coins >= cost) {
            return true;
        }
        player.sendMessage(ChatColor.RED + "This item costs " + ChatColor.GOLD + cost + ChatColor.RED
                + " and you only have " + ChatColor.GOLD + coins + " " + StatsDisplay.ADVENTURE_COINS.getName() + ChatColor.RED + "!");
        return false;
    }

    public boolean soldOut(Player player, String itemID, String itemName) {
        if (itemID.equals("PetSlot") && player.hasPermission("DONATE.PET.SLOT.2") ||
                itemID.equals("UnlimitedWeight") && player.hasPermission("DONATE.UNLIMITED.WEIGHT")) {
            player.sendMessage(ChatColor.RED + "You already purchased the max amount of " + ChatColor.GOLD + itemName + ChatColor.RED + " you can buy!");
            player.getWorld().playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1f, 2f);
            return true;
        }
        return false;
    }

    public void donationPoint(Player player, String itemName) {
        Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "q point " + player.getName() + " add donate." + itemName + " 1");
    }

    public void purchaseItem(Player player, int price, String itemName) {
        hologram(player);
        Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "q point " + player.getName() + " add items.AdventureCoin -" + price);
        player.sendMessage(ChatColor.GREEN + "You purchased " + ChatColor.GOLD + itemName + ChatColor.GREEN + ", thanks for supporting us!");
        player.sendMessage(ChatColor.GOLD.toString() + price + ChatColor.RED + " Adventure Coins " + ChatColor.GREEN + "have been deducted from your account!");
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_VILLAGER_CELEBRATE, 1f, 2f);
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LARGE_BLAST_FAR, 1f, 2f);
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LARGE_BLAST, 1f, 2f);
        player.getWorld().spawnParticle(Particle.VILLAGER_HAPPY,
                player.getLocation().getX(),
                player.getLocation().getY(),
                player.getLocation().getZ(), 5, .25, .25, .25);
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> {
            int coins = Integer.valueOf(PlaceholderAPI.setPlaceholders(player, "%ac_Currency_AdventureCoins%"));
            player.sendMessage(ChatColor.GREEN.toString() + "You now have " + ChatColor.GOLD + coins + " " + StatsDisplay.ADVENTURE_COINS.getName() + ChatColor.GREEN + " left!");
        }, 5L);
    }

    private void hologram(Player player) {
        final Hologram hologram = HologramsAPI.createHologram(plugin, player.getLocation().add(0.0, 2.0, 0.0));
        hologram.appendItemLine(new ItemStack(Material.BEACON));
        hologram.appendTextLine(ChatColor.RED.toString() + ChatColor.MAGIC + "|" + ChatColor.GOLD + ChatColor.BOLD + " DONATED " + ChatColor.RED.toString() + ChatColor.MAGIC + "|");
        new BukkitRunnable() {
            int ticks;
            @Override
            public void run() {
                ticks++;
                hologram.teleport(player.getLocation().add(0.0, 3.0, 0.0));
                if (ticks > 80) {
                    hologram.delete();
                    cancel();
                }
            }
        }.runTaskTimer(plugin, 1L, 1L);
    }

}

