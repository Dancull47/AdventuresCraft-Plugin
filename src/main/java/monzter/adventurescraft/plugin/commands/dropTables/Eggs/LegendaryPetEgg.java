package monzter.adventurescraft.plugin.commands.dropTables.Eggs;

import me.lucko.helper.random.Weighted;
import monzter.adventurescraft.plugin.utilities.acUtils;

public enum LegendaryPetEgg implements Weighted {
    //        .5 = 50%
    GiraffePet3(acUtils.rare + "Giraffe Pet", "PET", "PET_GIRAFFE3", .50, 1),
    TurtlePet3(acUtils.rare + "Turtle Pet", "PET", "PET_TURTLE3", .50, 1),
    ElephantPet3(acUtils.rare + "Elephant Pet", "PET", "PET_ELEPHANT3", .50, 1),
    ChimpPet3(acUtils.rare + "Chimp Pet", "PET", "PET_CHIMP3", .50, 1),
    FrogPet3(acUtils.rare + "Frog Pet", "PET", "PET_FROG3", .50, 1),
    LionPet3(acUtils.rare + "Lion Pet", "PET", "PET_LION3", .50, 1),

    GiraffePet2(acUtils.uncommon + "Giraffe Pet", "PET", "PET_GIRAFFE2", .40, 1),
    TurtlePet2(acUtils.uncommon + "Turtle Pet", "PET", "PET_TURTLE2", .40, 1),
    ElephantPet2(acUtils.uncommon + "Elephant Pet", "PET", "PET_ELEPHANT2", .40, 1),
    ChimpPet2(acUtils.uncommon + "Chimp Pet", "PET", "PET_CHIMP2", .40, 1),
    FrogPet2(acUtils.uncommon + "Frog Pet", "PET", "PET_FROG2", .40, 1),
    LionPet2(acUtils.uncommon + "Lion Pet", "PET", "PET_LION2", .40, 1),

    PenguinPet3(acUtils.rare + "Penguin Pet", "PET", "PET_PENGUIN3", .25, 1),
    PandaPet3(acUtils.rare + "Panda Pet", "PET", "PET_PANDA3", .25, 1),

    PandaPet2(acUtils.uncommon + "Panda Pet", "PET", "PET_PANDA2", .20, 1),
    PenguinPet2(acUtils.uncommon + "Penguin Pet", "PET", "PET_PENGUIN2", .20, 1),

    GiraffePet4(acUtils.legendary + "Giraffe Pet", "PET", "PET_GIRAFFE4", .10, 1),
    TurtlePet4(acUtils.legendary + "Turtle Pet", "PET", "PET_TURTLE4", .10, 1),
    ElephantPet4(acUtils.legendary + "Elephant Pet", "PET", "PET_ELEPHANT4", .10, 1),
    ChimpPet4(acUtils.legendary + "Chimp Pet", "PET", "PET_CHIMP4", .10, 1),
    FrogPet4(acUtils.legendary + "Frog Pet", "PET", "PET_FROG4", .10, 1),
    LionPet4(acUtils.legendary + "Lion Pet", "PET", "PET_LION4", .10, 1),

    PenguinPet4(acUtils.legendary + "Penguin Pet", "PET", "PET_PENGUIN4", .05, 1),
    PandaPet4(acUtils.legendary + "Panda Pet", "PET", "PET_PANDA4", .05, 1),

    GiraffePet5(acUtils.exotic + "Giraffe Pet", "PET", "PET_GIRAFFE5", .01, 1),
    TurtlePet5(acUtils.exotic + "Turtle Pet", "PET", "PET_TURTLE5", .01, 1),
    ElephantPet5(acUtils.exotic + "Elephant Pet", "PET", "PET_ELEPHANT5", .01, 1),
    ChimpPet5(acUtils.exotic + "Chimp Pet", "PET", "PET_CHIMP5", .01, 1),
    FrogPet5(acUtils.exotic + "Frog Pet", "PET", "PET_FROG5", .01, 1),
    LionPet5(acUtils.exotic + "Lion Pet", "PET", "PET_LION5", .01, 1),

    PandaPet5(acUtils.exotic + "Panda Pet", "PET", "PET_PANDA5", .005, 1),
    PenguinPet5(acUtils.exotic + "Penguin Pet", "PET", "PET_PENGUIN5", .005, 1);

    public final String displayName;
    public final String type;
    public final String id;
    public final double weight;
    public final int amount;

    LegendaryPetEgg(String displayName, String type, String id, double weight, int amount) {
        this.id = id;
        this.type = type;
        this.displayName = displayName;
        this.weight = weight;
        this.amount = amount;
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
