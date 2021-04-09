package monzter.adventurescraft.plugin.event;

import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import monzter.adventurescraft.plugin.event.extras.PetEgg;
import org.bukkit.OfflinePlayer;

import java.text.DecimalFormat;

public class Placeholder extends PlaceholderExpansion {

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
                String maxWeightDefault = PlaceholderAPI.setPlaceholders(player, "%betonquest_items:point.MaxWeight.amount%");
                String maxWeightPet = PlaceholderAPI.setPlaceholders(player, "%ac_PetMaxWeight_VALUE%");
                String maxWeightMultiplier = PlaceholderAPI.setPlaceholders(player, "%ac_Stat_MaxWeightMultiplier%");
                return String.valueOf((Integer.valueOf(maxWeightDefault) + Integer.valueOf(maxWeightPet)) * Integer.valueOf(maxWeightMultiplier));

                // PETS
            default:
                return null;
        }
    }

    private String numberFormat(int expToHatch) {
        DecimalFormat format = new DecimalFormat("###,###,###");
        return format.format(expToHatch);
    }

}
