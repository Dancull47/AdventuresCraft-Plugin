package monzter.adventurescraft.plugin.network.PrisonGamemode.shared.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Dependency;
import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import me.clip.placeholderapi.PlaceholderAPI;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.network.PrisonGamemode.shared.events.extras.DonationRewardList;
import monzter.adventurescraft.plugin.utilities.enums.PrisonStatsDisplay;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import monzter.adventurescraft.plugin.utilities.mmoitems.MMOItemsGive;
import monzter.adventurescraft.plugin.utilities.vault.Permission;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class Donate extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final MMOItemsGive mmoItemsGive;
    private final SoundManager soundManager;
    private final Permission permission;

    public Donate(AdventuresCraft plugin, MMOItemsGive mmoItemsGive, SoundManager soundManager, Permission permission) {
        this.plugin = plugin;
        this.mmoItemsGive = mmoItemsGive;
        this.soundManager = soundManager;
        this.permission = permission;
    }

    @CommandAlias("DonationRewards|DonationReward")
    private void donate(Player player, String reward) {
        final int adventureCoins = Integer.valueOf(PlaceholderAPI.setPlaceholders(player, "%ac_Currency_AdventureCoins%"));
        if (!fullInventory(player)) {
            for (DonationRewardList donationReward : DonationRewardList.values()) {
                if (reward.contains(donationReward.id)) {
                    switch (reward) {
                        case "PetSlot":
                            if (priceCheck(player, adventureCoins, donationReward.price)) {
                                if (!soldOut(player, donationReward.id, donationReward.displayName + "s")) {
                                    purchaseItem(player, donationReward.price, donationReward.displayName);
                                    donationPoint(player, donationReward.id);
                                    Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "q point " + player.getName() + " add items.MaxPetAmount 1");
                                    if (player.hasPermission("DONATE.PET.SLOT.1")) {
                                        permission.givePermission(player, "DONATE.PET.SLOT.2");
                                    } else {
                                        permission.givePermission(player, "DONATE.PET.SLOT.1");
                                    }
                                    break;
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
                                    permission.givePermission(player, "DONATE.UNLIMITED.WEIGHT");
                                    break;
                                }
                            }
                            break;
                        case "ExoticLootbox5":
                            if (priceCheck(player, adventureCoins, donationReward.price)) {
                                if (!soldOut(player, donationReward.id, donationReward.displayName)) {
                                    purchaseItem(player, donationReward.price, donationReward.displayName);
                                    donationPoint(player, donationReward.id);
                                    mmoItemsGive.giveMMOItem(player, "CONSUMABLE", "LOOTBOX5", 5);
                                    break;
                                }
                            }
                            break;
                        case "ExoticLootbox10":
                            if (priceCheck(player, adventureCoins, donationReward.price)) {
                                if (!soldOut(player, donationReward.id, donationReward.displayName)) {
                                    purchaseItem(player, donationReward.price, donationReward.displayName);
                                    donationPoint(player, donationReward.id);
                                    mmoItemsGive.giveMMOItem(player, "CONSUMABLE", "LOOTBOX5", 10);
                                    break;
                                }
                            }
                            break;
                        case "MythicalLootbox5":
                            if (priceCheck(player, adventureCoins, donationReward.price)) {
                                if (!soldOut(player, donationReward.id, donationReward.displayName)) {
                                    purchaseItem(player, donationReward.price, donationReward.displayName);
                                    donationPoint(player, donationReward.id);
                                    mmoItemsGive.giveMMOItem(player, "CONSUMABLE", "LOOTBOX6", 5);
                                    break;
                                }
                            }
                            break;
                        case "MythicalLootbox10":
                            if (priceCheck(player, adventureCoins, donationReward.price)) {
                                if (!soldOut(player, donationReward.id, donationReward.displayName)) {
                                    purchaseItem(player, donationReward.price, donationReward.displayName);
                                    donationPoint(player, donationReward.id);
                                    mmoItemsGive.giveMMOItem(player, "CONSUMABLE", "LOOTBOX6", 10);
                                    break;
                                }
                            }
                            break;
                        case "GodlyLootbox5":
                            if (priceCheck(player, adventureCoins, donationReward.price)) {
                                if (!soldOut(player, donationReward.id, donationReward.displayName)) {
                                    purchaseItem(player, donationReward.price, donationReward.displayName);
                                    donationPoint(player, donationReward.id);
                                    mmoItemsGive.giveMMOItem(player, "CONSUMABLE", "LOOTBOX7", 5);
                                    break;
                                }
                            }
                            break;
                        case "GodlyLootbox10":
                            if (priceCheck(player, adventureCoins, donationReward.price)) {
                                if (!soldOut(player, donationReward.id, donationReward.displayName)) {
                                    purchaseItem(player, donationReward.price, donationReward.displayName);
                                    donationPoint(player, donationReward.id);
                                    mmoItemsGive.giveMMOItem(player, "CONSUMABLE", "LOOTBOX7", 10);
                                    break;
                                }
                            }
                            break;
                        case "ExoticPetEgg5":
                            if (priceCheck(player, adventureCoins, donationReward.price)) {
                                if (!soldOut(player, donationReward.id, donationReward.displayName)) {
                                    purchaseItem(player, donationReward.price, donationReward.displayName);
                                    donationPoint(player, donationReward.id);
                                    mmoItemsGive.giveMMOItem(player, "PET", "PET_EGG5", 5);
                                    break;
                                }
                            }
                            break;
                        case "ExoticPetEgg10":
                            if (priceCheck(player, adventureCoins, donationReward.price)) {
                                if (!soldOut(player, donationReward.id, donationReward.displayName)) {
                                    purchaseItem(player, donationReward.price, donationReward.displayName);
                                    donationPoint(player, donationReward.id);
                                    mmoItemsGive.giveMMOItem(player, "PET", "PET_EGG5", 10);
                                    break;
                                }
                            }
                            break;
                        case "MythicalPetEgg5":
                            if (priceCheck(player, adventureCoins, donationReward.price)) {
                                if (!soldOut(player, donationReward.id, donationReward.displayName)) {
                                    purchaseItem(player, donationReward.price, donationReward.displayName);
                                    donationPoint(player, donationReward.id);
                                    mmoItemsGive.giveMMOItem(player, "PET", "PET_EGG6", 5);
                                    break;
                                }
                            }
                            break;
                        case "MythicalPetEgg10":
                            if (priceCheck(player, adventureCoins, donationReward.price)) {
                                if (!soldOut(player, donationReward.id, donationReward.displayName)) {
                                    purchaseItem(player, donationReward.price, donationReward.displayName);
                                    donationPoint(player, donationReward.id);
                                    mmoItemsGive.giveMMOItem(player, "PET", "PET_EGG6", 10);
                                    break;
                                }
                            }
                            break;
                        case "GodlyPetEgg5":
                            if (priceCheck(player, adventureCoins, donationReward.price)) {
                                if (!soldOut(player, donationReward.id, donationReward.displayName)) {
                                    purchaseItem(player, donationReward.price, donationReward.displayName);
                                    donationPoint(player, donationReward.id);
                                    mmoItemsGive.giveMMOItem(player, "PET", "PET_EGG7", 5);
                                    break;
                                }
                            }
                            break;
                        case "GodlyPetEgg10":
                            if (priceCheck(player, adventureCoins, donationReward.price)) {
                                if (!soldOut(player, donationReward.id, donationReward.displayName)) {
                                    purchaseItem(player, donationReward.price, donationReward.displayName);
                                    donationPoint(player, donationReward.id);
                                    mmoItemsGive.giveMMOItem(player, "PET", "PET_EGG7", 10);
                                    break;
                                }
                            }
                            break;
                        case "LegendaryDragonPetEgg5":
                            if (priceCheck(player, adventureCoins, donationReward.price)) {
                                if (!soldOut(player, donationReward.id, donationReward.displayName)) {
                                    purchaseItem(player, donationReward.price, donationReward.displayName);
                                    donationPoint(player, donationReward.id);
                                    mmoItemsGive.giveMMOItem(player, "PET", "DRAGON_EGG4", 5);
                                    break;
                                }
                            }
                            break;
                        case "LegendaryDragonPetEgg10":
                            if (priceCheck(player, adventureCoins, donationReward.price)) {
                                if (!soldOut(player, donationReward.id, donationReward.displayName)) {
                                    purchaseItem(player, donationReward.price, donationReward.displayName);
                                    donationPoint(player, donationReward.id);
                                    mmoItemsGive.giveMMOItem(player, "PET", "DRAGON_EGG4", 10);
                                    break;
                                }
                            }
                            break;
                        case "LegendaryPhoenixPetEgg5":
                            if (priceCheck(player, adventureCoins, donationReward.price)) {
                                if (!soldOut(player, donationReward.id, donationReward.displayName)) {
                                    purchaseItem(player, donationReward.price, donationReward.displayName);
                                    donationPoint(player, donationReward.id);
                                    mmoItemsGive.giveMMOItem(player, "PET", "PHOENIX_EGG4", 5);
                                    break;
                                }
                            }
                            break;
                        case "LegendaryPhoenixPetEgg10":
                            if (priceCheck(player, adventureCoins, donationReward.price)) {
                                if (!soldOut(player, donationReward.id, donationReward.displayName)) {
                                    purchaseItem(player, donationReward.price, donationReward.displayName);
                                    donationPoint(player, donationReward.id);
                                    mmoItemsGive.giveMMOItem(player, "PET", "PHOENIX_EGG4", 10);
                                    break;
                                }
                            }
                            break;
                    }
                }
            }
        }
    }


    private boolean fullInventory(Player player) {
        if (player.getInventory().firstEmpty() == -1) {
            player.sendMessage(ChatColor.RED + "You're inventory is full! Try again once you have at least one available slot!");
            soundManager.playSound(player, Sound.BLOCK_NOTE_BLOCK_PLING, 1f ,1f);
            return true;
        }
        return false;
    }

    private boolean priceCheck(Player player, int coins, int cost) {
        if (coins >= cost) {
            return true;
        }
        player.sendMessage(ChatColor.RED + "This item costs " + ChatColor.GOLD + cost + ChatColor.RED
                + " and you only have " + ChatColor.GOLD + coins + " " + PrisonStatsDisplay.ADVENTURE_COINS.getName() + ChatColor.RED + "!");
        return false;
    }

    private boolean soldOut(Player player, String itemID, String itemName) {
        if (itemID.equals("PetSlot") && player.hasPermission("DONATE.PET.SLOT.2") ||
                itemID.equals("UnlimitedWeight") && player.hasPermission("DONATE.UNLIMITED.WEIGHT")) {
            player.sendMessage(ChatColor.RED + "You already purchased the max amount of " + ChatColor.GOLD + itemName + ChatColor.RED + " you can buy!");
            player.getWorld().playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1f, 2f);
            return true;
        }
        return false;
    }

    private void donationPoint(Player player, String itemName) {
        Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "q point " + player.getName() + " add donate." + itemName + " 1");
    }

    private void purchaseItem(Player player, int price, String itemName) {
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
            String coins = PlaceholderAPI.setPlaceholders(player, "%ac_Currency_AdventureCoins_formatted%");
            player.sendMessage(ChatColor.GREEN.toString() + "You now have " + ChatColor.GOLD + coins + " " + PrisonStatsDisplay.ADVENTURE_COINS.getName() + ChatColor.GREEN + " left!");
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

