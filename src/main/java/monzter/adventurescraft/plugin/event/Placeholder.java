package monzter.adventurescraft.plugin.event;

import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.event.extras.Pet;
import monzter.adventurescraft.plugin.event.extras.PetEgg;
import monzter.adventurescraft.plugin.event.extras.Stats;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;
import java.util.*;
import java.util.logging.Level;

public class Placeholder extends PlaceholderExpansion {

    private final AdventuresCraft plugin;
    private final Permission permission;
    private final Set<Pet> pets;

    public Placeholder(AdventuresCraft plugin, Permission permission, Set<Pet> pets) {
        this.plugin = plugin;
        this.permission = permission;
        this.pets = pets;
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public String getAuthor() {
        return "Monzter";
    }

    @Override
    public String getIdentifier() {
        return "ac";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }

    /**
     * This is the method called when a placeholder with our identifier
     * is found and needs a value.
     * <br>We specify the value identifier in this method.
     * <br>Since version 2.9.1 can you use OfflinePlayers in your requests.
     *
     * @param player     A {@link org.bukkit.OfflinePlayer OfflinePlayer}.
     * @param identifier A String containing the identifier/value.
     * @return Possibly-null String of the requested identifier.
     */
    @Override
    public String onRequest(OfflinePlayer player, String identifier) {

        switch (identifier) {


            case "PetExp":
            case "PetExperience":
                String playerPetEXP = "%betonquest_items:point.PetExperience.amount%";
                playerPetEXP = PlaceholderAPI.setPlaceholders(player, playerPetEXP);
                return playerPetEXP;

            case "PetEgg":
                return String.valueOf(PetEgg.COMMON.expToHatch);
            case "PetEgg_formatted":
                return numberFormat(PetEgg.COMMON.expToHatch);
            case "PetEgg2":
                return String.valueOf(PetEgg.UNCOMMON.expToHatch);
            case "PetEgg2_formatted":
                return numberFormat(PetEgg.UNCOMMON.expToHatch);
            case "PetEgg3":
                return String.valueOf(PetEgg.RARE.expToHatch);
            case "PetEgg3_formatted":
                return numberFormat(PetEgg.RARE.expToHatch);
            case "PetEgg4":
                return String.valueOf(PetEgg.LEGENDARY.expToHatch);
            case "PetEgg4_formatted":
                return numberFormat(PetEgg.LEGENDARY.expToHatch);
            case "PetEgg5":
                return String.valueOf(PetEgg.EXOTIC.expToHatch);
            case "PetEgg5_formatted":
                return numberFormat(PetEgg.EXOTIC.expToHatch);
            case "PetEggPhoenix":
                return String.valueOf(PetEgg.PHOENIX.expToHatch);
            case "PetEggPhoenix_formatted":
                return numberFormat(PetEgg.PHOENIX.expToHatch);
            case "PetEggPhoenix2":
                return String.valueOf(PetEgg.PHOENIX2.expToHatch);
            case "PetEggPhoenix2_formatted":
                return numberFormat(PetEgg.PHOENIX2.expToHatch);
            case "PetEggDragon":
                return String.valueOf(PetEgg.DRAGON.expToHatch);
            case "PetEggDragon_formatted":
                return numberFormat(PetEgg.DRAGON.expToHatch);
            case "PetEggDragon2":
                return String.valueOf(PetEgg.DRAGON2.expToHatch);
            case "PetEggDragon2_formatted":
                return numberFormat(PetEgg.DRAGON2.expToHatch);

            // STATS
            case "Stat_MaxWeight":
                return calculateStats(player, "%betonquest_items:point.MaxWeight.amount%", "%ac_Stat_MaxWeightMultiplier%");

            case "Stat_MaxWeightMultiplier":
                return String.valueOf(calculatePetStats(player, Stats.MAX_WEIGHT_MULTIPLIER));

            case "Stat_BlockMultiplier":
                String blockMultiplierDefault = PlaceholderAPI.setPlaceholders(player, "%mmoitems_stat_stamina_regeneration%");
                return String.valueOf(calculatePetStats(player, Stats.BLOCK_MULTIPLIER) + Integer.valueOf(blockMultiplierDefault) + calculateBoosterStats(player, "block"));

            // PETS
            default:
                return null;
        }
    }

    private String numberFormat(int number) {
        DecimalFormat format = new DecimalFormat("###,###,###");
        return format.format(number);
    }

    private double calculatePetStats(OfflinePlayer player, Stats petStat) {
        double statSum = 0;
        for (Pet pet : pets) {
            if (hasPermission(player, pet.getPermission())) {
                OptionalDouble stat = pet.getStat(petStat);
                if (stat.isPresent()) {
                    statSum += stat.getAsDouble();
                }
            }
        }
        if (statSum > 0) {
            return statSum;
        } else {
            return petStat.getDefaultValue();
        }
    }

    private String calculateStats(OfflinePlayer player, String placeholder, String placeholder2) {
        String stat = PlaceholderAPI.setPlaceholders(player, placeholder);
        String stat2 = PlaceholderAPI.setPlaceholders(player, placeholder2);
        return String.valueOf(Double.valueOf(stat + stat2));
    }

    private String calculateStats(Player player, String placeholder, String placeholder2, String placeholder3) {
        String stat = PlaceholderAPI.setPlaceholders(player, placeholder);
        String stat2 = PlaceholderAPI.setPlaceholders(player, placeholder2);
        String stat3 = PlaceholderAPI.setPlaceholders(player, placeholder3);
        return String.valueOf(Double.valueOf(stat + stat2 + stat3));
    }

    private String calculateStats(Player player, String placeholder, String placeholder2, String placeholder3, String placeholder4) {
        String stat = PlaceholderAPI.setPlaceholders(player, placeholder);
        String stat2 = PlaceholderAPI.setPlaceholders(player, placeholder2);
        String stat3 = PlaceholderAPI.setPlaceholders(player, placeholder3);
        String stat4 = PlaceholderAPI.setPlaceholders(player, placeholder4);
        return String.valueOf(Double.valueOf(stat + stat2 + stat3 + stat4));
    }

    private int calculateBoosterStats(OfflinePlayer player, String booster) {
        for (int i = 10; i > 0; i--) {
            if (player.getPlayer().hasPermission(booster + ".booster." + i)) {
                return i;
            }
        }
        return 0;
    }

    private boolean hasPermission(OfflinePlayer player, String permission) {
        if (player.isOnline()) {
            return player.getPlayer().hasPermission(permission);
        } else {
            return this.permission.playerHas(plugin.getServer().getWorlds().get(0).getName(), player, permission);
        }
    }
}
