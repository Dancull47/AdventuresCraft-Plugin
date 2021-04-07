package monzter.adventurescraft.plugin.event;

import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import monzter.adventurescraft.plugin.event.extras.PetEgg;
import org.bukkit.OfflinePlayer;

import java.text.DecimalFormat;

public class Placeholder extends PlaceholderExpansion {

    @Override
    public boolean canRegister(){
        return true;
    }

    @Override
    public String getAuthor(){
        return "Monzter";
    }
    @Override
    public String getIdentifier(){
        return "ac";
    }
    @Override
    public String getVersion(){
        return "1.0.0";
    }

    /**
     * This is the method called when a placeholder with our identifier
     * is found and needs a value.
     * <br>We specify the value identifier in this method.
     * <br>Since version 2.9.1 can you use OfflinePlayers in your requests.
     *
     * @param  player
     *         A {@link org.bukkit.OfflinePlayer OfflinePlayer}.
     * @param  identifier
     *         A String containing the identifier/value.
     *
     * @return Possibly-null String of the requested identifier.
     */
    @Override
    public String onRequest(OfflinePlayer player, String identifier){

        // %example_placeholder1%
        if(identifier.equals("PetExp") || identifier.equals("PetExperience")){
            String playerPetEXP = "%betonquest_items:point.PetExperience.amount%";
            playerPetEXP = PlaceholderAPI.setPlaceholders(player, playerPetEXP);
            return playerPetEXP;
        }

        if(identifier.equals("PetEgg")){
            return String.valueOf(PetEgg.COMMON.expToHatch);
        }
        if(identifier.equals("PetEgg_formatted")){
            return numberFormat(PetEgg.COMMON.expToHatch);
        }
        if(identifier.equals("PetEgg2")){
            return String.valueOf(PetEgg.UNCOMMON.expToHatch);
        }
        if(identifier.equals("PetEgg2_formatted")){
            return numberFormat(PetEgg.UNCOMMON.expToHatch);
        }
        if(identifier.equals("PetEgg3")){
            return String.valueOf(PetEgg.RARE.expToHatch);
        }
        if(identifier.equals("PetEgg3_formatted")){
            return numberFormat(PetEgg.RARE.expToHatch);
        }
        if(identifier.equals("PetEgg4")){
            return String.valueOf(PetEgg.LEGENDARY.expToHatch);
        }
        if(identifier.equals("PetEgg4_formatted")){
            return numberFormat(PetEgg.LEGENDARY.expToHatch);
        }
        if(identifier.equals("PetEgg5")){
            return String.valueOf(PetEgg.EXOTIC.expToHatch);
        }
        if(identifier.equals("PetEgg5_formatted")){
            return numberFormat(PetEgg.EXOTIC.expToHatch);
        }
        if(identifier.equals("PetEggPhoenix")){
            return String.valueOf(PetEgg.PHOENIX.expToHatch);
        }
        if(identifier.equals("PetEggPhoenix_formatted")){
            return numberFormat(PetEgg.PHOENIX.expToHatch);
        }
        if(identifier.equals("PetEggPhoenix2")){
            return String.valueOf(PetEgg.PHOENIX2.expToHatch);
        }
        if(identifier.equals("PetEggPhoenix2_formatted")){
            return numberFormat(PetEgg.PHOENIX2.expToHatch);
        }
        if(identifier.equals("PetEggDragon")){
            return String.valueOf(PetEgg.DRAGON.expToHatch);
        }
        if(identifier.equals("PetEggDragon_formatted")){
            return numberFormat(PetEgg.DRAGON.expToHatch);
        }
        if(identifier.equals("PetEggDragon2")){
            return String.valueOf(PetEgg.DRAGON2.expToHatch);
        }
        if(identifier.equals("PetEggDragon2_formatted")){
            return numberFormat(PetEgg.DRAGON2.expToHatch);
        }

        // We return null if an invalid placeholder (f.e. %example_placeholder3%)
        // was provided
        return null;
    }

    public String numberFormat(int expToHatch){
        DecimalFormat format = new DecimalFormat("###,###,###");
        return format.format(expToHatch);
    }

}
