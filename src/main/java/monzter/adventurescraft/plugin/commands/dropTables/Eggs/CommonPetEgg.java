package monzter.adventurescraft.plugin.commands.dropTables.Eggs;

import me.lucko.helper.random.Weighted;
import monzter.adventurescraft.plugin.utilities.acUtils;
import org.bukkit.ChatColor;

public enum CommonPetEgg implements Weighted {
    //        .5 = 50%
    GiraffePet(acUtils.common + "Giraffe Pet", "PET", "PET_GIRAFFE", .70, 1),
    TurtlePet(acUtils.common + "Turtle Pet", "PET", "PET_TURTLE", .70, 1),
    ElephantPet(acUtils.common + "Elephant Pet", "PET", "PET_ELEPHANT", .70, 1),
    ChimpPet(acUtils.common + "Chimp Pet", "PET", "PET_CHIMP", .70, 1),
    FrogPet(acUtils.common + "Frog Pet", "PET", "PET_FROG", .70, 1),
    LionPet(acUtils.common + "Lion Pet", "PET", "PET_LION", .70, 1),

    PandaPet(acUtils.common + "Panda Pet", "PET", "PET_PANDA", .35, 1),
    PenguinPet(acUtils.common + "Penguin Pet", "PET", "PET_PENGUIN", .35, 1),
    GiraffePet2(acUtils.uncommon + "Giraffe Pet", "PET", "PET_GIRAFFE2", .25, 1),
    TurtlePet2(acUtils.uncommon + "Turtle Pet", "PET", "PET_TURTLE2", .25, 1),
    ElephantPet2(acUtils.uncommon + "Elephant Pet", "PET", "PET_ELEPHANT2", .25, 1),
    ChimpPet2(acUtils.uncommon + "Chimp Pet", "PET", "PET_CHIMP2", .25, 1),
    FrogPet2(acUtils.uncommon + "Frog Pet", "PET", "PET_FROG2", .25, 1),
    LionPet2(acUtils.uncommon + "Lion Pet", "PET", "PET_LION2", .25, 1),

    PandaPet2(acUtils.uncommon + "Panda Pet", "PET", "PET_PANDA2", .125, 1),
    PenguinPet2(acUtils.uncommon + "Penguin Pet", "PET", "PET_PENGUIN2", .125, 1),

    GiraffePet3(acUtils.rare + "Giraffe Pet", "PET", "PET_GIRAFFE3", .04, 1),
    TurtlePet3(acUtils.rare + "Turtle Pet", "PET", "PET_TURTLE3", .04, 1),
    ElephantPet3(acUtils.rare + "Elephant Pet", "PET", "PET_ELEPHANT3", .04, 1),
    ChimpPet3(acUtils.rare + "Chimp Pet", "PET", "PET_CHIMP3", .04, 1),
    FrogPet3(acUtils.rare + "Frog Pet", "PET", "PET_FROG3", .04, 1),
    LionPet3(acUtils.rare + "Lion Pet", "PET", "PET_LION3", .04, 1),

    PenguinPet3(acUtils.rare + "Penguin Pet", "PET", "PET_PENGUIN3", .02, 1),
    PandaPet3(acUtils.rare + "Panda Pet", "PET", "PET_PANDA3", .02, 1),

    GiraffePet4(acUtils.legendary + "Giraffe Pet", "PET", "PET_GIRAFFE4", .01, 1),
    TurtlePet4(acUtils.legendary + "Turtle Pet", "PET", "PET_TURTLE4", .01, 1),
    ElephantPet4(acUtils.legendary + "Elephant Pet", "PET", "PET_ELEPHANT4", .01, 1),
    ChimpPet4(acUtils.legendary + "Chimp Pet", "PET", "PET_CHIMP4", .01, 1),
    FrogPet4(acUtils.legendary + "Frog Pet", "PET", "PET_FROG4", .01, 1),
    LionPet4(acUtils.legendary + "Lion Pet", "PET", "PET_LION4", .01, 1),

    PenguinPet4(acUtils.legendary + "Penguin Pet", "PET", "PET_PENGUIN4", .005, 1),
    PandaPet4(acUtils.legendary + "Panda Pet", "PET", "PET_PANDA4", .005, 1);

    public final String displayName;

    public final String type;
    public final String id;
    public final double weight;
    public final int amount;
    CommonPetEgg(String displayName, String type, String id, double weight, int amount) {
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

    public int getAmount() {
        return amount;
    }
}
