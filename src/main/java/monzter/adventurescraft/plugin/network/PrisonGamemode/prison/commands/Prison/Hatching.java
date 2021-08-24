package monzter.adventurescraft.plugin.network.PrisonGamemode.prison.commands.Prison;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.Dependency;
import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import io.lumine.mythic.lib.api.item.NBTItem;
import me.clip.placeholderapi.PlaceholderAPI;
import me.lucko.helper.Events;
import me.lucko.helper.random.RandomSelector;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.network.PrisonGamemode.shared.commands.dropTables.DropTableTypes;
import monzter.adventurescraft.plugin.network.PrisonGamemode.shared.commands.dropTables.DropTables;
import monzter.adventurescraft.plugin.utilities.enums.PetEggList;
import monzter.adventurescraft.plugin.utilities.enums.PrisonStatsDisplay;
import monzter.adventurescraft.plugin.utilities.general.ConsoleCommand;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import monzter.adventurescraft.plugin.utilities.enums.Rarity;
import monzter.adventurescraft.plugin.utilities.mmoitems.DropTablesDelivery;
import monzter.adventurescraft.plugin.utilities.text.NumberFormat;
import net.Indyuce.mmoitems.MMOItems;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class Hatching extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final SoundManager soundManager;
    private final ConsoleCommand consoleCommand;
    private final DropTablesDelivery dropTablesDelivery;
    private final NumberFormat numberFormat;

    public Hatching(AdventuresCraft plugin, SoundManager soundManager, ConsoleCommand consoleCommand, DropTablesDelivery dropTablesDelivery, NumberFormat numberFormat) {
        this.plugin = plugin;
        this.soundManager = soundManager;
        this.consoleCommand = consoleCommand;
        this.dropTablesDelivery = dropTablesDelivery;
        this.numberFormat = numberFormat;
    }

    @CommandAlias("hatch")
    @CommandCompletion("Common|Uncommon|Rare|Legendary|Exotic|Phoenix|Phoenix2|Dragon|Dragon2")
    public void hatchCommand(Player player, String egg) {
        int petEXPAmount = Integer.valueOf(PlaceholderAPI.setPlaceholders(player, "%ac_Stat_Pet_EXPAmount%"));
        switch (egg.toUpperCase()) {
            case "COMMON":
                if (hasPetEXP(player, egg.toUpperCase(), petEXPAmount)) {
                    if (hasItem(player, "PET_EGG", Rarity.COMMON + "Pet Egg")) {
                        player.sendMessage(ChatColor.GREEN + "You hatched a " + Rarity.COMMON.getName() + " Pet Egg" + ChatColor.GREEN + "!");
                        hatch(player, PetEggList.COMMON.expToHatch);
                        RandomSelector<DropTables> commonPetEgg = RandomSelector.weighted((DropTables.getEggs(DropTableTypes.COMMON_PET_EGG)));
                        DropTables commonDropTablesListReward = commonPetEgg.pick();
                        dropTablesDelivery.giveReward(player.getPlayer(), MMOItems.plugin.getItem(commonDropTablesListReward.getType(), commonDropTablesListReward.getId()).getItemMeta().getDisplayName(), commonDropTablesListReward.getType(), commonDropTablesListReward.getId(), commonDropTablesListReward.getWeight(), commonDropTablesListReward.getAmount());
                        hatchingHologram(MMOItems.plugin.getItem(commonDropTablesListReward.getType(), commonDropTablesListReward.getId()), player);
                        break;
                    }
                }
                break;
            case "UNCOMMON":
                if (hasPetEXP(player, egg.toUpperCase(), petEXPAmount)) {
                    if (hasItem(player, "PET_EGG2", Rarity.UNCOMMON + "Pet Egg")) {
                        player.sendMessage(ChatColor.GREEN + "You hatched a " + Rarity.UNCOMMON.getName() + " Pet Egg" + ChatColor.GREEN + "!");
                        hatch(player, PetEggList.UNCOMMON.expToHatch);
                        RandomSelector<DropTables> uncommonPetEgg = RandomSelector.weighted((DropTables.getEggs(DropTableTypes.UNCOMMON_PET_EGG)));
                        DropTables uncommonDropTablesListReward = uncommonPetEgg.pick();
                        dropTablesDelivery.giveReward(player.getPlayer(), MMOItems.plugin.getItem(uncommonDropTablesListReward.getType(), uncommonDropTablesListReward.getId()).getItemMeta().getDisplayName(), uncommonDropTablesListReward.getType(), uncommonDropTablesListReward.getId(), uncommonDropTablesListReward.getWeight(), uncommonDropTablesListReward.getAmount());
                        hatchingHologram(MMOItems.plugin.getItem(uncommonDropTablesListReward.getType(), uncommonDropTablesListReward.getId()), player);
                        break;
                    }
                }
                break;
            case "RARE":
                if (hasPetEXP(player, egg.toUpperCase(), petEXPAmount)) {
                    if (hasItem(player, "PET_EGG3", Rarity.RARE + "Pet Egg")) {
                        player.sendMessage(ChatColor.GREEN + "You hatched a " + Rarity.RARE.getName() + " Pet Egg" + ChatColor.GREEN + "!");
                        hatch(player, PetEggList.RARE.expToHatch);
                        RandomSelector<DropTables> rarePetEgg = RandomSelector.weighted((DropTables.getEggs(DropTableTypes.RARE_PET_EGG)));
                        DropTables rareDropTablesListReward = rarePetEgg.pick();
                        dropTablesDelivery.giveReward(player.getPlayer(), MMOItems.plugin.getItem(rareDropTablesListReward.getType(), rareDropTablesListReward.getId()).getItemMeta().getDisplayName(), rareDropTablesListReward.getType(), rareDropTablesListReward.getId(), rareDropTablesListReward.getWeight(), rareDropTablesListReward.getAmount());
                        hatchingHologram(MMOItems.plugin.getItem(rareDropTablesListReward.getType(), rareDropTablesListReward.getId()), player);
                        break;
                    }
                }
                break;
            case "LEGENDARY":
                if (hasPetEXP(player, egg.toUpperCase(), petEXPAmount)) {
                    if (hasItem(player, "PET_EGG4", Rarity.LEGENDARY + "Pet Egg")) {
                        player.sendMessage(ChatColor.GREEN + "You hatched a " + Rarity.LEGENDARY.getName() + " Pet Egg" + ChatColor.GREEN + "!");
                        hatch(player, PetEggList.LEGENDARY.expToHatch);
                        RandomSelector<DropTables> legendaryPetEgg = RandomSelector.weighted((DropTables.getEggs(DropTableTypes.LEGENDARY_PET_EGG)));
                        DropTables legendaryDropTablesListReward = legendaryPetEgg.pick();
                        dropTablesDelivery.giveReward(player.getPlayer(), MMOItems.plugin.getItem(legendaryDropTablesListReward.getType(), legendaryDropTablesListReward.getId()).getItemMeta().getDisplayName(), legendaryDropTablesListReward.getType(), legendaryDropTablesListReward.getId(), legendaryDropTablesListReward.getWeight(), legendaryDropTablesListReward.getAmount());
                        hatchingHologram(MMOItems.plugin.getItem(legendaryDropTablesListReward.getType(), legendaryDropTablesListReward.getId()), player);
                        break;
                    }
                }
                break;
            case "EXOTIC":
                if (hasPetEXP(player, egg.toUpperCase(), petEXPAmount)) {
                    if (hasItem(player, "PET_EGG5", Rarity.EXOTIC + "Pet Egg")) {
                        player.sendMessage(ChatColor.GREEN + "You hatched a " + Rarity.EXOTIC.getName() + " Pet Egg" + ChatColor.GREEN + "!");
                        hatch(player, PetEggList.EXOTIC.expToHatch);
                        RandomSelector<DropTables> exoticPetEgg = RandomSelector.weighted((DropTables.getEggs(DropTableTypes.EXOTIC_PET_EGG)));
                        DropTables exoticDropTablesListReward = exoticPetEgg.pick();
                        dropTablesDelivery.giveReward(player.getPlayer(), MMOItems.plugin.getItem(exoticDropTablesListReward.getType(), exoticDropTablesListReward.getId()).getItemMeta().getDisplayName(), exoticDropTablesListReward.getType(), exoticDropTablesListReward.getId(), exoticDropTablesListReward.getWeight(), exoticDropTablesListReward.getAmount());
                        hatchingHologram(MMOItems.plugin.getItem(exoticDropTablesListReward.getType(), exoticDropTablesListReward.getId()), player);
                        break;
                    }
                }
                break;
            case "MYTHICAL":
                if (hasPetEXP(player, egg.toUpperCase(), petEXPAmount)) {
                    if (hasItem(player, "PET_EGG6", Rarity.MYTHICAL + "Pet Egg")) {
                        player.sendMessage(ChatColor.GREEN + "You hatched a " + Rarity.MYTHICAL.getName() + " Pet Egg" + ChatColor.GREEN + "!");
                        hatch(player, PetEggList.MYTHICAL.expToHatch);
                        RandomSelector<DropTables> mythicalPetEgg = RandomSelector.weighted((DropTables.getEggs(DropTableTypes.MYTHICAL_PET_EGG)));
                        DropTables mythicalDropTablesListReward = mythicalPetEgg.pick();
                        dropTablesDelivery.giveReward(player.getPlayer(), MMOItems.plugin.getItem(mythicalDropTablesListReward.getType(), mythicalDropTablesListReward.getId()).getItemMeta().getDisplayName(), mythicalDropTablesListReward.getType(), mythicalDropTablesListReward.getId(), mythicalDropTablesListReward.getWeight(), mythicalDropTablesListReward.getAmount());
                        hatchingHologram(MMOItems.plugin.getItem(mythicalDropTablesListReward.getType(), mythicalDropTablesListReward.getId()), player);
                        break;
                    }
                }
                break;
            case "GODLY":
                if (hasPetEXP(player, egg.toUpperCase(), petEXPAmount)) {
                    if (hasItem(player, "PET_EGG7", Rarity.GODLY + "Pet Egg")) {
                        player.sendMessage(ChatColor.GREEN + "You hatched a " + Rarity.GODLY.getName() + " Pet Egg" + ChatColor.GREEN + "!");
                        hatch(player, PetEggList.GODLY.expToHatch);
                        RandomSelector<DropTables> godlyPetEgg = RandomSelector.weighted((DropTables.getEggs(DropTableTypes.GODLY_PET_EGG)));
                        DropTables godlyDropTablesListReward = godlyPetEgg.pick();
                        dropTablesDelivery.giveReward(player.getPlayer(), MMOItems.plugin.getItem(godlyDropTablesListReward.getType(), godlyDropTablesListReward.getId()).getItemMeta().getDisplayName(), godlyDropTablesListReward.getType(), godlyDropTablesListReward.getId(), godlyDropTablesListReward.getWeight(), godlyDropTablesListReward.getAmount());
                        hatchingHologram(MMOItems.plugin.getItem(godlyDropTablesListReward.getType(), godlyDropTablesListReward.getId()), player);
                        break;
                    }
                }
                break;
            case "PHOENIX":
                if (hasPetEXP(player, egg.toUpperCase(), petEXPAmount)) {
                    if (hasItem(player, "PHOENIX_EGG", Rarity.RARE + "Phoenix Pet Egg")) {
                        player.sendMessage(ChatColor.GREEN + "You hatched a " + Rarity.RARE.getName() + " Phoenix Pet Egg" + ChatColor.GREEN + "!");
                        hatch(player, PetEggList.PHOENIX.expToHatch);
                        RandomSelector<DropTables> phoenixPetEgg = RandomSelector.weighted((DropTables.getEggs(DropTableTypes.PHOENIX_PET_EGG)));
                        DropTables phoenixDropTablesListReward = phoenixPetEgg.pick();
                        dropTablesDelivery.giveReward(player.getPlayer(), MMOItems.plugin.getItem(phoenixDropTablesListReward.getType(), phoenixDropTablesListReward.getId()).getItemMeta().getDisplayName(), phoenixDropTablesListReward.getType(), phoenixDropTablesListReward.getId(), phoenixDropTablesListReward.getWeight(), phoenixDropTablesListReward.getAmount());
                        hatchingHologram(MMOItems.plugin.getItem(phoenixDropTablesListReward.getType(), phoenixDropTablesListReward.getId()), player);
                        break;
                    }
                }
                break;
            case "PHOENIX2":
                if (hasPetEXP(player, egg.toUpperCase(), petEXPAmount)) {
                    if (hasItem(player, "PHOENIX_EGG2", Rarity.LEGENDARY + "Phoenix Pet Egg")) {
                        player.sendMessage(ChatColor.GREEN + "You hatched a " + Rarity.LEGENDARY.getName() + " Phoenix Pet Egg" + ChatColor.GREEN + "!");
                        hatch(player, PetEggList.PHOENIX2.expToHatch);
                        RandomSelector<DropTables> phoenix2PetEgg = RandomSelector.weighted((DropTables.getEggs(DropTableTypes.PHOENIX_PET_EGG2)));
                        DropTables phoenix2DropTablesListReward = phoenix2PetEgg.pick();
                        dropTablesDelivery.giveReward(player.getPlayer(), MMOItems.plugin.getItem(phoenix2DropTablesListReward.getType(), phoenix2DropTablesListReward.getId()).getItemMeta().getDisplayName(), phoenix2DropTablesListReward.getType(), phoenix2DropTablesListReward.getId(), phoenix2DropTablesListReward.getWeight(), phoenix2DropTablesListReward.getAmount());
                        hatchingHologram(MMOItems.plugin.getItem(phoenix2DropTablesListReward.getType(), phoenix2DropTablesListReward.getId()), player);
                        break;
                    }
                }
                break;
            case "DRAGON":
                if (hasPetEXP(player, egg.toUpperCase(), petEXPAmount)) {
                    if (hasItem(player, "DRAGON_EGG", Rarity.RARE + "Dragon Pet Egg")) {
                        player.sendMessage(ChatColor.GREEN + "You hatched a " + Rarity.RARE.getName() + " Dragon Pet Egg" + ChatColor.GREEN + "!");
                        hatch(player, PetEggList.DRAGON.expToHatch);
                        RandomSelector<DropTables> dragonPetEgg = RandomSelector.weighted((DropTables.getEggs(DropTableTypes.DRAGON_PET_EGG)));
                        DropTables dragonDropTablesListReward = dragonPetEgg.pick();
                        dropTablesDelivery.giveReward(player.getPlayer(), MMOItems.plugin.getItem(dragonDropTablesListReward.getType(), dragonDropTablesListReward.getId()).getItemMeta().getDisplayName(), dragonDropTablesListReward.getType(), dragonDropTablesListReward.getId(), dragonDropTablesListReward.getWeight(), dragonDropTablesListReward.getAmount());
                        hatchingHologram(MMOItems.plugin.getItem(dragonDropTablesListReward.getType(), dragonDropTablesListReward.getId()), player);
                        break;
                    }
                }
                break;
            case "DRAGON2":
                if (hasPetEXP(player, egg.toUpperCase(), petEXPAmount)) {
                    if (hasItem(player, "DRAGON_EGG2", Rarity.LEGENDARY + "Dragon Pet Egg")) {
                        player.sendMessage(ChatColor.GREEN + "You hatched a " + Rarity.LEGENDARY.getName() + " Dragon Pet Egg" + ChatColor.GREEN + "!");
                        hatch(player, PetEggList.DRAGON2.expToHatch);
                        RandomSelector<DropTables> dragon2PetEgg = RandomSelector.weighted((DropTables.getEggs(DropTableTypes.DRAGON_PET_EGG2)));
                        DropTables dragon2DropTablesListReward = dragon2PetEgg.pick();
                        dropTablesDelivery.giveReward(player.getPlayer(), MMOItems.plugin.getItem(dragon2DropTablesListReward.getType(), dragon2DropTablesListReward.getId()).getItemMeta().getDisplayName(), dragon2DropTablesListReward.getType(), dragon2DropTablesListReward.getId(), dragon2DropTablesListReward.getWeight(), dragon2DropTablesListReward.getAmount());
                        hatchingHologram(MMOItems.plugin.getItem(dragon2DropTablesListReward.getType(), dragon2DropTablesListReward.getId()), player);
                        break;
                    }
                }
        }
    }

    public void hatch(Player player, int exp) {
        Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "q point " + player.getName() + " add items.PetExperience " + "-" + exp);
        player.sendMessage(ChatColor.GOLD.toString() + exp + " " + PrisonStatsDisplay.PET_EXPERIENCE_AMOUNT.getName() + ChatColor.GREEN + " has been deducted from your account!");
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_TURTLE_EGG_HATCH, 1f, 2f);
    }

    public boolean hasItem(Player player, String itemID, String displayName) {
        for (ItemStack currentItem : player.getInventory().getContents()) {
            NBTItem nbtItem = NBTItem.get(currentItem);
            if (MMOItems.plugin.getID(nbtItem) != null && MMOItems.plugin.getID(nbtItem).equals(itemID)) {
                player.getInventory().removeItem(nbtItem.getItem());
                return true;
            }
        }
        player.sendMessage(ChatColor.RED + "You don't have a " + MMOItems.plugin.getItem("PET", itemID).getItemMeta().getDisplayName() + ChatColor.RED + " in your inventory!");
        soundManager.soundNo(player, 1);
        return false;
    }

    public boolean hasPetEXP(Player player, String rarity, int petEXP) {
        switch (rarity) {
            case "COMMON":
                if (petEXP >= PetEggList.COMMON.expToHatch) {
                    return true;
                }
                player.sendMessage(ChatColor.RED + "You only have " + ChatColor.GOLD + numberFormat.numberFormat(petEXP) + "/" + numberFormat.numberFormat(PetEggList.COMMON.getExpToHatch()) + " " +
                        PrisonStatsDisplay.PET_EXPERIENCE_AMOUNT.getName() + ChatColor.RED + "!");
                soundManager.soundNo(player, 1);
                break;
            case "UNCOMMON":
                if (petEXP >= PetEggList.UNCOMMON.expToHatch) {
                    return true;
                }
                player.sendMessage(ChatColor.RED + "You only have " + ChatColor.GOLD + numberFormat.numberFormat(petEXP) + "/" + numberFormat.numberFormat(PetEggList.UNCOMMON.getExpToHatch()) + " " +
                        PrisonStatsDisplay.PET_EXPERIENCE_AMOUNT.getName() + ChatColor.RED + "!");
                soundManager.soundNo(player, 1);
                break;
            case "RARE":
                if (petEXP >= PetEggList.RARE.expToHatch) {
                    return true;
                }
                player.sendMessage(ChatColor.RED + "You only have " + ChatColor.GOLD + numberFormat.numberFormat(petEXP) + "/" + numberFormat.numberFormat(PetEggList.RARE.getExpToHatch()) + " " +
                        PrisonStatsDisplay.PET_EXPERIENCE_AMOUNT.getName() + ChatColor.RED + "!");
                soundManager.soundNo(player, 1);
                break;
            case "LEGENDARY":
                if (petEXP >= PetEggList.LEGENDARY.expToHatch) {
                    return true;
                }
                player.sendMessage(ChatColor.RED + "You only have " + ChatColor.GOLD + numberFormat.numberFormat(petEXP) + "/" + numberFormat.numberFormat(PetEggList.LEGENDARY.getExpToHatch()) + " " +
                        PrisonStatsDisplay.PET_EXPERIENCE_AMOUNT.getName() + ChatColor.RED + "!");
                soundManager.soundNo(player, 1);
                break;
            case "EXOTIC":
                if (petEXP >= PetEggList.EXOTIC.expToHatch) {
                    return true;
                }
                player.sendMessage(ChatColor.RED + "You only have " + ChatColor.GOLD + numberFormat.numberFormat(petEXP) + "/" + numberFormat.numberFormat(PetEggList.EXOTIC.getExpToHatch()) + " " +
                        PrisonStatsDisplay.PET_EXPERIENCE_AMOUNT.getName() + ChatColor.RED + "!");
                soundManager.soundNo(player, 1);
                break;
            case "PHOENIX":
                if (petEXP >= PetEggList.PHOENIX.expToHatch) {
                    return true;
                }
                player.sendMessage(ChatColor.RED + "You only have " + ChatColor.GOLD + numberFormat.numberFormat(petEXP) + "/" + numberFormat.numberFormat(PetEggList.PHOENIX.getExpToHatch()) + " " +
                        PrisonStatsDisplay.PET_EXPERIENCE_AMOUNT.getName() + ChatColor.RED + "!");
                soundManager.soundNo(player, 1);
                break;
            case "PHOENIX2":
                if (petEXP >= PetEggList.PHOENIX2.expToHatch) {
                    return true;
                }
                player.sendMessage(ChatColor.RED + "You only have " + ChatColor.GOLD + numberFormat.numberFormat(petEXP) + "/" + numberFormat.numberFormat(PetEggList.PHOENIX2.getExpToHatch()) + " " +
                        PrisonStatsDisplay.PET_EXPERIENCE_AMOUNT.getName() + ChatColor.RED + "!");
                soundManager.soundNo(player, 1);
                break;
            case "DRAGON":
                if (petEXP >= PetEggList.DRAGON.expToHatch) {
                    return true;
                }
                player.sendMessage(ChatColor.RED + "You only have " + ChatColor.GOLD + numberFormat.numberFormat(petEXP) + "/" + numberFormat.numberFormat(PetEggList.DRAGON.getExpToHatch()) + " " +
                        PrisonStatsDisplay.PET_EXPERIENCE_AMOUNT.getName() + ChatColor.RED + "!");
                soundManager.soundNo(player, 1);
                break;
            case "DRAGON2":
                if (petEXP >= PetEggList.DRAGON2.expToHatch) {
                    return true;
                }
                player.sendMessage(ChatColor.RED + "You only have " + ChatColor.GOLD + numberFormat.numberFormat(petEXP) + "/" + numberFormat.numberFormat(PetEggList.DRAGON2.getExpToHatch()) + " " +
                        PrisonStatsDisplay.PET_EXPERIENCE_AMOUNT.getName() + ChatColor.RED + "!");
                soundManager.soundNo(player, 1);
                break;
        }
        return false;
    }

    private void hatchingHologram(ItemStack itemStack, Player player) {
        Location loc = player.getLocation().add(
                player.getLocation().getDirection().multiply(2).getX(),
                player.getLocation().getDirection().getY() + 2,
                player.getLocation().getDirection().multiply(2).getZ());
        final Hologram hologram = HologramsAPI.createHologram(plugin, loc);

        Events.subscribe(EntityDamageByBlockEvent.class, EventPriority.LOWEST)
                .expireAfter(1)
                .handler(e -> {
                    if (e.getCause().equals(EntityDamageEvent.DamageCause.BLOCK_EXPLOSION))
                        if (e.getDamager() == null)
                            e.setCancelled(true);
                });

        loc.createExplosion(1, false, false);


        hologram.appendTextLine(itemStack.getItemMeta().getDisplayName() + " Pet");
        hologram.appendItemLine(itemStack);
        new BukkitRunnable() {
            int ticksRun;

            @Override
            public void run() {
                ticksRun++;
                if (ticksRun > 100) {
                    hologram.delete();
                    cancel();
                }
            }
        }.runTaskTimer(plugin, 1L, 1L);
    }
}

