package monzter.adventurescraft.plugin.event.dropTables;

import monzter.adventurescraft.plugin.utilities.acUtils;
import org.bukkit.ChatColor;

public enum CommonPetEgg {
    //        .5 = 50%
    TurtlePet4(acUtils.legendary + "Turtle Pet", "PET", "PET_TURTLE4", .01),
    TurtlePet3(acUtils.rare + "Turtle Pet", "PET", "PET_TURTLE3", .04),
    TurtlePet2(acUtils.uncommon + "Turtle Pet", "PET", "PET_TURTLE2", .25),
    TurtlePet(acUtils.common + "Turtle Pet", "PET", "PET_TURTLE", .70);

    public final String displayName;
    public final String type;
    public final String id;
    public final double chance;

    CommonPetEgg(String displayName, String type, String id, double chance) {
        this.id = id;
        this.type = type;
        this.displayName = displayName;
        this.chance = chance;
    }
}
