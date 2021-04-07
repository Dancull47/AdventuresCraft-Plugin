package monzter.adventurescraft.plugin.event;

import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;

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

        // %example_placeholder2%
        if(identifier.equals("PetEgg1")){
            return "placeholder2 works";
        }

        // We return null if an invalid placeholder (f.e. %example_placeholder3%)
        // was provided
        return null;
    }
}
