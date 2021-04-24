package monzter.adventurescraft.plugin.commands.dropTables;

import me.lucko.helper.random.Weighted;
import monzter.adventurescraft.plugin.utilities.acUtils;
import org.bukkit.ChatColor;

public enum CommonPetEgg implements Weighted {
    //        .5 = 50%
    GiraffePet(acUtils.common + "Giraffe Pet", "PET", "PET_GIRAFFE", .70),
    TurtlePet(acUtils.common + "Turtle Pet", "PET", "PET_TURTLE", .70),
    PenguinPet(acUtils.common + "Penguin Pet", "PET", "PET_PENGUIN", .70),
    ChimpPet(acUtils.common + "Chimp Pet", "PET", "PET_CHIMP", .70),
    PandaPet(acUtils.common + "Panda Pet", "PET", "PET_PANDA", .70),
    FrogPet(acUtils.common + "Frog Pet", "PET", "PET_FROG", .70),
    LionPet(acUtils.common + "Lion Pet", "PET", "PET_LION", .70),
    TurtlePet4(acUtils.legendary + "Turtle Pet", "PET", "PET_TURTLE4", .01),
    FrogPet4(acUtils.legendary + "Frog Pet", "PET", "PET_FROG4", .01),
    ChimpPet4(acUtils.legendary + "Chimp Pet", "PET", "PET_CHIMP4", .01),
    GiraffePet4(acUtils.legendary + "Giraffe Pet", "PET", "PET_GIRAFFE4", .01),
    LionPet4(acUtils.legendary + "Lion Pet", "PET", "PET_LION4", .01),
    PandaPet4(acUtils.legendary + "Panda Pet", "PET", "PET_PANDA4", .01),
    PenguinPet4(acUtils.legendary + "Penguin Pet", "PET", "PET_PENGUIN4", .01),
    ElephantPet4(acUtils.legendary + "Elephant Pet", "PET", "PET_ELEPHANT4", .01),
    TurtlePet2(acUtils.uncommon + "Turtle Pet", "PET", "PET_TURTLE2", .25),
    FrogPet2(acUtils.uncommon + "Frog Pet", "PET", "PET_FROG2", .25),
    ElephantPet2(acUtils.uncommon + "Elephant Pet", "PET", "PET_ELEPHANT2", .25),
    ChimpPet2(acUtils.uncommon + "Chimp Pet", "PET", "PET_CHIMP2", .25),
    GiraffePet2(acUtils.uncommon + "Giraffe Pet", "PET", "PET_GIRAFFE2", .25),
    PandaPet2(acUtils.uncommon + "Panda Pet", "PET", "PET_PANDA2", .25),
    LionPet2(acUtils.uncommon + "Lion Pet", "PET", "PET_LION2", .25),
    PenguinPet2(acUtils.uncommon + "Penguin Pet", "PET", "PET_PENGUIN2", .25),
    TurtlePet3(acUtils.rare + "Turtle Pet", "PET", "PET_TURTLE3", .04),
    LionPet3(acUtils.rare + "Lion Pet", "PET", "PET_LION3", .04),
    FrogPet3(acUtils.rare + "Frog Pet", "PET", "PET_FROG3", .04),
    ChimpPet3(acUtils.rare + "Chimp Pet", "PET", "PET_CHIMP3", .04),
    GiraffePet3(acUtils.rare + "Giraffe Pet", "PET", "PET_GIRAFFE3", .04),
    ElephantPet3(acUtils.rare + "Elephant Pet", "PET", "PET_ELEPHANT3", .04),
    PenguinPet3(acUtils.rare + "Penguin Pet", "PET", "PET_PENGUIN3", .04),
    PandaPet3(acUtils.rare + "Panda Pet", "PET", "PET_PANDA3", .04),
    ElephantPet(acUtils.common + "Elephant Pet", "PET", "PET_ELEPHANT", .70);

    public final String displayName;
    public final String type;
    public final String id;
    public final double weight;

    CommonPetEgg(String displayName, String type, String id, double weight) {
        this.id = id;
        this.type = type;
        this.displayName = displayName;
        this.weight = weight;
    }
    @Override
    public double getWeight() {
        return weight;
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }
}
