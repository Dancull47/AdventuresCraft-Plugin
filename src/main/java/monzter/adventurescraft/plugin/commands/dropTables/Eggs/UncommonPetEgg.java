package monzter.adventurescraft.plugin.commands.dropTables.Eggs;

import me.lucko.helper.random.Weighted;
import monzter.adventurescraft.plugin.utilities.acUtils;

public enum UncommonPetEgg implements Weighted {
    //        .5 = 50%
    GiraffePet2(acUtils.uncommon + "Giraffe Pet", "PET", "PET_GIRAFFE2", .60, 1),
    TurtlePet2(acUtils.uncommon + "Turtle Pet", "PET", "PET_TURTLE2", .60, 1),
    ElephantPet2(acUtils.uncommon + "Elephant Pet", "PET", "PET_ELEPHANT2", .60, 1),
    ChimpPet2(acUtils.uncommon + "Chimp Pet", "PET", "PET_CHIMP2", .60, 1),
    FrogPet2(acUtils.uncommon + "Frog Pet", "PET", "PET_FROG2", .60, 1),
    LionPet2(acUtils.uncommon + "Lion Pet", "PET", "PET_LION2", .60, 1),

    GiraffePet(acUtils.common + "Giraffe Pet", "PET", "PET_GIRAFFE", .30, 1),
    TurtlePet(acUtils.common + "Turtle Pet", "PET", "PET_TURTLE", .30, 1),
    ElephantPet(acUtils.common + "Elephant Pet", "PET", "PET_ELEPHANT", .30, 1),
    ChimpPet(acUtils.common + "Chimp Pet", "PET", "PET_CHIMP", .30, 1),
    FrogPet(acUtils.common + "Frog Pet", "PET", "PET_FROG", .30, 1),
    LionPet(acUtils.common + "Lion Pet", "PET", "PET_LION", .30, 1),

    PenguinPet2(acUtils.uncommon + "Penguin Pet", "PET", "PET_PENGUIN2", .30, 1),
    PandaPet2(acUtils.uncommon + "Panda Pet", "PET", "PET_PANDA2", .30, 1),

    PandaPet(acUtils.common + "Panda Pet", "PET", "PET_PANDA", .15, 1),
    PenguinPet(acUtils.common + "Penguin Pet", "PET", "PET_PENGUIN", .15, 1),

    GiraffePet3(acUtils.rare + "Giraffe Pet", "PET", "PET_GIRAFFE3", .08, 1),
    TurtlePet3(acUtils.rare + "Turtle Pet", "PET", "PET_TURTLE3", .08, 1),
    ElephantPet3(acUtils.rare + "Elephant Pet", "PET", "PET_ELEPHANT3", .08, 1),
    ChimpPet3(acUtils.rare + "Chimp Pet", "PET", "PET_CHIMP3", .08, 1),
    FrogPet3(acUtils.rare + "Frog Pet", "PET", "PET_FROG3", .08, 1),
    LionPet3(acUtils.rare + "Lion Pet", "PET", "PET_LION3", .08, 1),

    PenguinPet3(acUtils.rare + "Penguin Pet", "PET", "PET_PENGUIN3", .04, 1),
    PandaPet3(acUtils.rare + "Panda Pet", "PET", "PET_PANDA3", .04, 1),

    GiraffePet4(acUtils.legendary + "Giraffe Pet", "PET", "PET_GIRAFFE4", .02, 1),
    TurtlePet4(acUtils.legendary + "Turtle Pet", "PET", "PET_TURTLE4", .02, 1),
    ElephantPet4(acUtils.legendary + "Elephant Pet", "PET", "PET_ELEPHANT4", .02, 1),
    ChimpPet4(acUtils.legendary + "Chimp Pet", "PET", "PET_CHIMP4", .02, 1),
    FrogPet4(acUtils.legendary + "Frog Pet", "PET", "PET_FROG4", .02, 1),
    LionPet4(acUtils.legendary + "Lion Pet", "PET", "PET_LION4", .02, 1),

    PenguinPet4(acUtils.legendary + "Penguin Pet", "PET", "PET_PENGUIN4", .01, 1),
    PandaPet4(acUtils.legendary + "Panda Pet", "PET", "PET_PANDA4", .01, 1);

    public final String displayName;
    public final String type;
    public final String id;
    public final double weight;
    public final int amount;

    UncommonPetEgg(String displayName, String type, String id, double weight, int amount) {
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
