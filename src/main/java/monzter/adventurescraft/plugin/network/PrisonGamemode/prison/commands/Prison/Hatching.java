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
import monzter.adventurescraft.plugin.network.PrisonGamemode.shared.dropTables.PetEgg;
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
                        player.sendMessage(ChatColor.GREEN + "You hatched a " + Rarity.COMMON + "Pet Egg" + ChatColor.GREEN + "!");
                        hatch(player, PetEggList.COMMON.expToHatch);
                        RandomSelector<PetEgg> commonPetEgg = RandomSelector.weighted((PetEgg.getEggs(Rarity.COMMON)));
                        PetEgg commonPetEggListReward = commonPetEgg.pick();
                        dropTablesDelivery.giveReward(player.getPlayer(), commonPetEggListReward.getDisplayName(), commonPetEggListReward.getType(), commonPetEggListReward.getId(), commonPetEggListReward.getWeight());
                        hatchingHologram(MMOItems.plugin.getItem(commonPetEggListReward.getType(), commonPetEggListReward.getId()), player);
                        break;
                    }
                }
                break;
            case "UNCOMMON":
                if (hasPetEXP(player, egg.toUpperCase(), petEXPAmount)) {
                    if (hasItem(player, "PET_EGG2", Rarity.UNCOMMON + "Pet Egg")) {
                        player.sendMessage(ChatColor.GREEN + "You hatched a " + Rarity.UNCOMMON + "Pet Egg" + ChatColor.GREEN + "!");
                        hatch(player, PetEggList.UNCOMMON.expToHatch);
                        RandomSelector<PetEgg> uncommonPetEgg = RandomSelector.weighted((PetEgg.getEggs(Rarity.UNCOMMON)));
                        PetEgg uncommonPetEggListReward = uncommonPetEgg.pick();
                        dropTablesDelivery.giveReward(player.getPlayer(), uncommonPetEggListReward.getDisplayName(), uncommonPetEggListReward.getType(), uncommonPetEggListReward.getId(), uncommonPetEggListReward.getWeight());
                        hatchingHologram(MMOItems.plugin.getItem(uncommonPetEggListReward.getType(), uncommonPetEggListReward.getId()), player);
                        break;
                    }
                }
                break;
            case "RARE":
                if (hasPetEXP(player, egg.toUpperCase(), petEXPAmount)) {
                    if (hasItem(player, "PET_EGG3", Rarity.RARE + "Pet Egg")) {
                        player.sendMessage(ChatColor.GREEN + "You hatched a " + Rarity.RARE + "Pet Egg" + ChatColor.GREEN + "!");
                        hatch(player, PetEggList.RARE.expToHatch);
                        RandomSelector<PetEgg> rarePetEgg = RandomSelector.weighted((PetEgg.getEggs(Rarity.RARE)));
                        PetEgg rarePetEggListReward = rarePetEgg.pick();
                        dropTablesDelivery.giveReward(player.getPlayer(), rarePetEggListReward.getDisplayName(), rarePetEggListReward.getType(), rarePetEggListReward.getId(), rarePetEggListReward.getWeight());
                        hatchingHologram(MMOItems.plugin.getItem(rarePetEggListReward.getType(), rarePetEggListReward.getId()), player);
                        break;
                    }
                }
                break;
            case "LEGENDARY":
                if (hasPetEXP(player, egg.toUpperCase(), petEXPAmount)) {
                    if (hasItem(player, "PET_EGG4", Rarity.LEGENDARY + "Pet Egg")) {
                        player.sendMessage(ChatColor.GREEN + "You hatched a " + Rarity.LEGENDARY + "Pet Egg" + ChatColor.GREEN + "!");
                        hatch(player, PetEggList.LEGENDARY.expToHatch);
                        RandomSelector<PetEgg> legendaryPetEgg = RandomSelector.weighted((PetEgg.getEggs(Rarity.LEGENDARY)));
                        PetEgg legendaryPetEggListReward = legendaryPetEgg.pick();
                        dropTablesDelivery.giveReward(player.getPlayer(), legendaryPetEggListReward.getDisplayName(), legendaryPetEggListReward.getType(), legendaryPetEggListReward.getId(), legendaryPetEggListReward.getWeight());
                        hatchingHologram(MMOItems.plugin.getItem(legendaryPetEggListReward.getType(), legendaryPetEggListReward.getId()), player);
                        break;
                    }
                }
                break;
            case "EXOTIC":
                if (hasPetEXP(player, egg.toUpperCase(), petEXPAmount)) {
                    if (hasItem(player, "PET_EGG5", Rarity.EXOTIC + "Pet Egg")) {
                        player.sendMessage(ChatColor.GREEN + "You hatched a " + Rarity.EXOTIC + "Pet Egg" + ChatColor.GREEN + "!");
                        hatch(player, PetEggList.EXOTIC.expToHatch);
                        RandomSelector<PetEgg> exoticPetEgg = RandomSelector.weighted((PetEgg.getEggs(Rarity.EXOTIC)));
                        PetEgg exoticPetEggListReward = exoticPetEgg.pick();
                        dropTablesDelivery.giveReward(player.getPlayer(), exoticPetEggListReward.getDisplayName(), exoticPetEggListReward.getType(), exoticPetEggListReward.getId(), exoticPetEggListReward.getWeight());
                        hatchingHologram(MMOItems.plugin.getItem(exoticPetEggListReward.getType(), exoticPetEggListReward.getId()), player);
                        break;
                    }
                }
                break;
            case "MYTHICAL":
                if (hasPetEXP(player, egg.toUpperCase(), petEXPAmount)) {
                    if (hasItem(player, "PET_EGG6", Rarity.MYTHICAL + "Pet Egg")) {
                        player.sendMessage(ChatColor.GREEN + "You hatched a " + Rarity.MYTHICAL + "Pet Egg" + ChatColor.GREEN + "!");
                        hatch(player, PetEggList.MYTHICAL.expToHatch);
                        RandomSelector<PetEgg> mythicalPetEgg = RandomSelector.weighted((PetEgg.getEggs(Rarity.MYTHICAL)));
                        PetEgg mythicalPetEggListReward = mythicalPetEgg.pick();
                        dropTablesDelivery.giveReward(player.getPlayer(), mythicalPetEggListReward.getDisplayName(), mythicalPetEggListReward.getType(), mythicalPetEggListReward.getId(), mythicalPetEggListReward.getWeight());
                        hatchingHologram(MMOItems.plugin.getItem(mythicalPetEggListReward.getType(), mythicalPetEggListReward.getId()), player);
                        break;
                    }
                }
                break;
            case "GODLY":
                if (hasPetEXP(player, egg.toUpperCase(), petEXPAmount)) {
                    if (hasItem(player, "PET_EGG7", Rarity.GODLY + "Pet Egg")) {
                        player.sendMessage(ChatColor.GREEN + "You hatched a " + Rarity.GODLY + "Pet Egg" + ChatColor.GREEN + "!");
                        hatch(player, PetEggList.GODLY.expToHatch);
                        RandomSelector<PetEgg> godlyPetEgg = RandomSelector.weighted((PetEgg.getEggs(Rarity.GODLY)));
                        PetEgg godlyPetEggListReward = godlyPetEgg.pick();
                        dropTablesDelivery.giveReward(player.getPlayer(), godlyPetEggListReward.getDisplayName(), godlyPetEggListReward.getType(), godlyPetEggListReward.getId(), godlyPetEggListReward.getWeight());
                        hatchingHologram(MMOItems.plugin.getItem(godlyPetEggListReward.getType(), godlyPetEggListReward.getId()), player);
                        break;
                    }
                }
                break;
            case "PHOENIX":
                if (hasPetEXP(player, egg.toUpperCase(), petEXPAmount)) {
                    if (hasItem(player, "PHOENIX_EGG", Rarity.RARE + "Phoenix Pet Egg")) {
                        player.sendMessage(ChatColor.GREEN + "You hatched a " + Rarity.RARE + "Phoenix Pet Egg" + ChatColor.GREEN + "!");
                        hatch(player, PetEggList.PHOENIX.expToHatch);
                        RandomSelector<PetEgg> phoenixPetEgg = RandomSelector.weighted((PetEgg.getEggs(Rarity.PHOENIX)));
                        PetEgg phoenixPetEggListReward = phoenixPetEgg.pick();
                        dropTablesDelivery.giveReward(player.getPlayer(), phoenixPetEggListReward.getDisplayName(), phoenixPetEggListReward.getType(), phoenixPetEggListReward.getId(), phoenixPetEggListReward.getWeight());
                        hatchingHologram(MMOItems.plugin.getItem(phoenixPetEggListReward.getType(), phoenixPetEggListReward.getId()), player);
                        break;
                    }
                }
                break;
            case "PHOENIX2":
                if (hasPetEXP(player, egg.toUpperCase(), petEXPAmount)) {
                    if (hasItem(player, "PHOENIX_EGG2", Rarity.LEGENDARY + "Phoenix Pet Egg")) {
                        player.sendMessage(ChatColor.GREEN + "You hatched a " + Rarity.LEGENDARY + "Phoenix Pet Egg" + ChatColor.GREEN + "!");
                        hatch(player, PetEggList.PHOENIX2.expToHatch);
                        RandomSelector<PetEgg> phoenix2PetEgg = RandomSelector.weighted((PetEgg.getEggs(Rarity.PHOENIX2)));
                        PetEgg phoenix2PetEggListReward = phoenix2PetEgg.pick();
                        dropTablesDelivery.giveReward(player.getPlayer(), phoenix2PetEggListReward.getDisplayName(), phoenix2PetEggListReward.getType(), phoenix2PetEggListReward.getId(), phoenix2PetEggListReward.getWeight());
                        hatchingHologram(MMOItems.plugin.getItem(phoenix2PetEggListReward.getType(), phoenix2PetEggListReward.getId()), player);
                        break;
                    }
                }
                break;
            case "DRAGON":
                if (hasPetEXP(player, egg.toUpperCase(), petEXPAmount)) {
                    if (hasItem(player, "DRAGON_EGG", Rarity.RARE + "Dragon Pet Egg")) {
                        player.sendMessage(ChatColor.GREEN + "You hatched a " + Rarity.RARE + "Dragon Pet Egg" + ChatColor.GREEN + "!");
                        hatch(player, PetEggList.DRAGON.expToHatch);
                        RandomSelector<PetEgg> dragonPetEgg = RandomSelector.weighted((PetEgg.getEggs(Rarity.DRAGON)));
                        PetEgg dragonPetEggListReward = dragonPetEgg.pick();
                        dropTablesDelivery.giveReward(player.getPlayer(), dragonPetEggListReward.getDisplayName(), dragonPetEggListReward.getType(), dragonPetEggListReward.getId(), dragonPetEggListReward.getWeight());
                        hatchingHologram(MMOItems.plugin.getItem(dragonPetEggListReward.getType(), dragonPetEggListReward.getId()), player);
                        break;
                    }
                }
                break;
            case "DRAGON2":
                if (hasPetEXP(player, egg.toUpperCase(), petEXPAmount)) {
                    if (hasItem(player, "DRAGON_EGG2", Rarity.LEGENDARY + "Dragon Pet Egg")) {
                        player.sendMessage(ChatColor.GREEN + "You hatched a " + Rarity.LEGENDARY + "Dragon Pet Egg" + ChatColor.GREEN + "!");
                        hatch(player, PetEggList.DRAGON2.expToHatch);
                        RandomSelector<PetEgg> dragon2PetEgg = RandomSelector.weighted((PetEgg.getEggs(Rarity.DRAGON)));
                        PetEgg dragon2PetEggListReward = dragon2PetEgg.pick();
                        dropTablesDelivery.giveReward(player.getPlayer(), dragon2PetEggListReward.getDisplayName(), dragon2PetEggListReward.getType(), dragon2PetEggListReward.getId(), dragon2PetEggListReward.getWeight());
                        hatchingHologram(MMOItems.plugin.getItem(dragon2PetEggListReward.getType(), dragon2PetEggListReward.getId()), player);
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

        loc.createExplosion(4, false, false);


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

