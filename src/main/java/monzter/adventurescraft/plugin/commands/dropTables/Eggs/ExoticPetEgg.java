package monzter.adventurescraft.plugin.commands.dropTables.Eggs;

import me.lucko.helper.random.Weighted;
import monzter.adventurescraft.plugin.utilities.acUtils;

public enum ExoticPetEgg implements Weighted {
    //        .5 = 50%
    GiraffePet3(acUtils.rare + "Giraffe Pet", "PET", "PET_GIRAFFE3", .70, 1),
    TurtlePet3(acUtils.rare + "Turtle Pet", "PET", "PET_TURTLE3", .70, 1),
    ElephantPet3(acUtils.rare + "Elephant Pet", "PET", "PET_ELEPHANT3", .70, 1),
    ChimpPet3(acUtils.rare + "Chimp Pet", "PET", "PET_CHIMP3", .70, 1),
    FrogPet3(acUtils.rare + "Frog Pet", "PET", "PET_FROG3", .70, 1),
    LionPet3(acUtils.rare + "Lion Pet", "PET", "PET_LION3", .70, 1),

    PenguinPet3(acUtils.rare + "Penguin Pet", "PET", "PET_PENGUIN3", .35, 1),
    PandaPet3(acUtils.rare + "Panda Pet", "PET", "PET_PANDA3", .35, 1),

    GiraffePet4(acUtils.legendary + "Giraffe Pet", "PET", "PET_GIRAFFE4", .25, 1),
    TurtlePet4(acUtils.legendary + "Turtle Pet", "PET", "PET_TURTLE4", .25, 1),
    ElephantPet4(acUtils.legendary + "Elephant Pet", "PET", "PET_ELEPHANT4", .25, 1),
    ChimpPet4(acUtils.legendary + "Chimp Pet", "PET", "PET_CHIMP4", .25, 1),
    FrogPet4(acUtils.legendary + "Frog Pet", "PET", "PET_FROG4", .25, 1),
    LionPet4(acUtils.legendary + "Lion Pet", "PET", "PET_LION4", .25, 1),

    PenguinPet4(acUtils.legendary + "Penguin Pet", "PET", "PET_PENGUIN4", .125, 1),
    PandaPet4(acUtils.legendary + "Panda Pet", "PET", "PET_PANDA4", .125, 1),

    GiraffePet5(acUtils.exotic + "Giraffe Pet", "PET", "PET_GIRAFFE5", .05, 1),
    TurtlePet5(acUtils.exotic + "Turtle Pet", "PET", "PET_TURTLE5", .05, 1),
    ElephantPet5(acUtils.exotic + "Elephant Pet", "PET", "PET_ELEPHANT5", .05, 1),
    ChimpPet5(acUtils.exotic + "Chimp Pet", "PET", "PET_CHIMP5", .05, 1),
    FrogPet(acUtils.exotic + "Frog Pet", "PET", "PET_FROG5", .05, 1),
    LionPet5(acUtils.exotic + "Lion Pet", "PET", "PET_LION5", .05, 1),

    PandaPet5(acUtils.exotic + "Panda Pet", "PET", "PET_PANDA5", .025, 1),
    PenguinPet5(acUtils.exotic + "Penguin Pet", "PET", "PET_PENGUIN5", .025, 1),

    GiraffePet6(acUtils.mythical + "Giraffe Pet", "PET", "PET_GIRAFFE6", .000001, 1),
    TurtlePet6(acUtils.mythical + "Turtle Pet", "PET", "PET_TURTLE6", .000001, 1),
    ElephantPet6(acUtils.mythical + "Elephant Pet", "PET", "PET_ELEPHANT6", .000001, 1),
    ChimpPet6(acUtils.mythical + "Chimp Pet", "PET", "PET_CHIMP6", .000001, 1),
    FrogPet6(acUtils.mythical + "Frog Pet", "PET", "PET_FROG6", .000001, 1),
    LionPet6(acUtils.mythical + "Lion Pet", "PET", "PET_LION6", .000001, 1),

    PandaPet6(acUtils.mythical + "Panda Pet", "PET", "PET_PANDA6", .0000005, 1),
    PenguinPet6(acUtils.mythical + "Penguin Pet", "PET", "PET_PENGUIN6", .0000005, 1);

    public final String displayName;
    public final String type;
    public final String id;
    public final double weight;
    public final int amount;

    ExoticPetEgg(String displayName, String type, String id, double weight, int amount) {
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
