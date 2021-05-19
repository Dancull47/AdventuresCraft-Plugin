package monzter.adventurescraft.plugin.shared.dropTables;

import me.lucko.helper.random.Weighted;
import monzter.adventurescraft.plugin.utilities.enums.Rarity;
import net.Indyuce.mmoitems.MMOItems;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.stream.Collectors;

public enum PetEgg implements Weighted, ItemGenerator {
    //        .5 = 50%
    GiraffePet(Rarity.COMMON, Rarity.COMMON, "Giraffe Pet", "PET", "PET_GIRAFFE", .70, 1),
    TurtlePet(Rarity.COMMON, Rarity.COMMON, "Turtle Pet", "PET", "PET_TURTLE", .70, 1),
    ElephantPet(Rarity.COMMON, Rarity.COMMON, "Elephant Pet", "PET", "PET_ELEPHANT", .70, 1),
    ChimpPet(Rarity.COMMON, Rarity.COMMON, "Chimp Pet", "PET", "PET_CHIMP", .70, 1),
    FrogPet(Rarity.COMMON, Rarity.COMMON, "Frog Pet", "PET", "PET_FROG", .70, 1),
    LionPet(Rarity.COMMON, Rarity.COMMON, "Lion Pet", "PET", "PET_LION", .70, 1),

    PandaPet(Rarity.COMMON, Rarity.COMMON, "Panda Pet", "PET", "PET_PANDA", .35, 1),
    PenguinPet(Rarity.COMMON, Rarity.COMMON, "Penguin Pet", "PET", "PET_PENGUIN", .35, 1),
    GiraffePet2(Rarity.COMMON, Rarity.UNCOMMON, "Giraffe Pet", "PET", "PET_GIRAFFE2", .25, 1),
    TurtlePet2(Rarity.COMMON, Rarity.UNCOMMON, "Turtle Pet", "PET", "PET_TURTLE2", .25, 1),
    ElephantPet2(Rarity.COMMON, Rarity.UNCOMMON, "Elephant Pet", "PET", "PET_ELEPHANT2", .25, 1),
    ChimpPet2(Rarity.COMMON, Rarity.UNCOMMON, "Chimp Pet", "PET", "PET_CHIMP2", .25, 1),
    FrogPet2(Rarity.COMMON, Rarity.UNCOMMON, "Frog Pet", "PET", "PET_FROG2", .25, 1),
    LionPet2(Rarity.COMMON, Rarity.UNCOMMON, "Lion Pet", "PET", "PET_LION2", .25, 1),

    PandaPet2(Rarity.COMMON, Rarity.UNCOMMON, "Panda Pet", "PET", "PET_PANDA2", .125, 1),
    PenguinPet2(Rarity.COMMON, Rarity.UNCOMMON, "Penguin Pet", "PET", "PET_PENGUIN2", .125, 1),

    GiraffePet3(Rarity.COMMON, Rarity.RARE, "Giraffe Pet", "PET", "PET_GIRAFFE3", .04, 1),
    TurtlePet3(Rarity.COMMON, Rarity.RARE, "Turtle Pet", "PET", "PET_TURTLE3", .04, 1),
    ElephantPet3(Rarity.COMMON, Rarity.RARE, "Elephant Pet", "PET", "PET_ELEPHANT3", .04, 1),
    ChimpPet3(Rarity.COMMON, Rarity.RARE, "Chimp Pet", "PET", "PET_CHIMP3", .04, 1),
    FrogPet3(Rarity.COMMON, Rarity.RARE, "Frog Pet", "PET", "PET_FROG3", .04, 1),
    LionPet3(Rarity.COMMON, Rarity.RARE, "Lion Pet", "PET", "PET_LION3", .04, 1),

    PenguinPet3(Rarity.COMMON, Rarity.RARE, "Penguin Pet", "PET", "PET_PENGUIN3", .02, 1),
    PandaPet3(Rarity.COMMON, Rarity.RARE, "Panda Pet", "PET", "PET_PANDA3", .02, 1),

    GiraffePet4(Rarity.COMMON, Rarity.LEGENDARY, "Giraffe Pet", "PET", "PET_GIRAFFE4", .01, 1),
    TurtlePet4(Rarity.COMMON, Rarity.LEGENDARY, "Turtle Pet", "PET", "PET_TURTLE4", .01, 1),
    ElephantPet4(Rarity.COMMON, Rarity.LEGENDARY, "Elephant Pet", "PET", "PET_ELEPHANT4", .01, 1),
    ChimpPet4(Rarity.COMMON, Rarity.LEGENDARY, "Chimp Pet", "PET", "PET_CHIMP4", .01, 1),
    FrogPet4(Rarity.COMMON, Rarity.LEGENDARY, "Frog Pet", "PET", "PET_FROG4", .01, 1),
    LionPet4(Rarity.COMMON, Rarity.LEGENDARY, "Lion Pet", "PET", "PET_LION4", .01, 1),

    PenguinPet4(Rarity.COMMON, Rarity.LEGENDARY, "Penguin Pet", "PET", "PET_PENGUIN4", .005, 1),
    PandaPet4(Rarity.COMMON, Rarity.LEGENDARY, "Panda Pet", "PET", "PET_PANDA4", .005, 1),

    //    UNCOMMON
    GiraffePet2U(Rarity.UNCOMMON, Rarity.UNCOMMON, "Giraffe Pet", "PET", "PET_GIRAFFE2", .60, 1),
    TurtlePet2U(Rarity.UNCOMMON, Rarity.UNCOMMON, "Turtle Pet", "PET", "PET_TURTLE2", .60, 1),
    ElephantPet2U(Rarity.UNCOMMON, Rarity.UNCOMMON, "Elephant Pet", "PET", "PET_ELEPHANT2", .60, 1),
    ChimpPet2U(Rarity.UNCOMMON, Rarity.UNCOMMON, "Chimp Pet", "PET", "PET_CHIMP2", .60, 1),
    FrogPet2U(Rarity.UNCOMMON, Rarity.UNCOMMON, "Frog Pet", "PET", "PET_FROG2", .60, 1),
    LionPet2U(Rarity.UNCOMMON, Rarity.UNCOMMON, "Lion Pet", "PET", "PET_LION2", .60, 1),

    GiraffePetU(Rarity.UNCOMMON, Rarity.COMMON, "Giraffe Pet", "PET", "PET_GIRAFFE", .30, 1),
    TurtlePetU(Rarity.UNCOMMON, Rarity.COMMON, "Turtle Pet", "PET", "PET_TURTLE", .30, 1),
    ElephantPetU(Rarity.UNCOMMON, Rarity.COMMON, "Elephant Pet", "PET", "PET_ELEPHANT", .30, 1),
    ChimpPetU(Rarity.UNCOMMON, Rarity.COMMON, "Chimp Pet", "PET", "PET_CHIMP", .30, 1),
    FrogPetU(Rarity.UNCOMMON, Rarity.COMMON, "Frog Pet", "PET", "PET_FROG", .30, 1),
    LionPetU(Rarity.UNCOMMON, Rarity.COMMON, "Lion Pet", "PET", "PET_LION", .30, 1),

    PenguinPet2U(Rarity.UNCOMMON, Rarity.UNCOMMON, "Penguin Pet", "PET", "PET_PENGUIN2", .30, 1),
    PandaPet2U(Rarity.UNCOMMON, Rarity.UNCOMMON, "Panda Pet", "PET", "PET_PANDA2", .30, 1),

    PandaPetU(Rarity.UNCOMMON, Rarity.COMMON, "Panda Pet", "PET", "PET_PANDA", .15, 1),
    PenguinPetU(Rarity.UNCOMMON, Rarity.COMMON, "Penguin Pet", "PET", "PET_PENGUIN", .15, 1),

    GiraffePet3U(Rarity.UNCOMMON, Rarity.RARE, "Giraffe Pet", "PET", "PET_GIRAFFE3", .08, 1),
    TurtlePet3U(Rarity.UNCOMMON, Rarity.RARE, "Turtle Pet", "PET", "PET_TURTLE3", .08, 1),
    ElephantPet3U(Rarity.UNCOMMON, Rarity.RARE, "Elephant Pet", "PET", "PET_ELEPHANT3", .08, 1),
    ChimpPet3U(Rarity.UNCOMMON, Rarity.RARE, "Chimp Pet", "PET", "PET_CHIMP3", .08, 1),
    FrogPet3U(Rarity.UNCOMMON, Rarity.RARE, "Frog Pet", "PET", "PET_FROG3", .08, 1),
    LionPet3U(Rarity.UNCOMMON, Rarity.RARE, "Lion Pet", "PET", "PET_LION3", .08, 1),

    PenguinPet3U(Rarity.UNCOMMON, Rarity.RARE, "Penguin Pet", "PET", "PET_PENGUIN3", .04, 1),
    PandaPet3U(Rarity.UNCOMMON, Rarity.RARE, "Panda Pet", "PET", "PET_PANDA3", .04, 1),

    GiraffePet4U(Rarity.UNCOMMON, Rarity.LEGENDARY, "Giraffe Pet", "PET", "PET_GIRAFFE4", .02, 1),
    TurtlePet4U(Rarity.UNCOMMON, Rarity.LEGENDARY, "Turtle Pet", "PET", "PET_TURTLE4", .02, 1),
    ElephantPet4U(Rarity.UNCOMMON, Rarity.LEGENDARY, "Elephant Pet", "PET", "PET_ELEPHANT4", .02, 1),
    ChimpPet4U(Rarity.UNCOMMON, Rarity.LEGENDARY, "Chimp Pet", "PET", "PET_CHIMP4", .02, 1),
    FrogPet4U(Rarity.UNCOMMON, Rarity.LEGENDARY, "Frog Pet", "PET", "PET_FROG4", .02, 1),
    LionPet4U(Rarity.UNCOMMON, Rarity.LEGENDARY, "Lion Pet", "PET", "PET_LION4", .02, 1),

    PenguinPet4U(Rarity.UNCOMMON, Rarity.LEGENDARY, "Penguin Pet", "PET", "PET_PENGUIN4", .01, 1),
    PandaPet4U(Rarity.UNCOMMON, Rarity.LEGENDARY, "Panda Pet", "PET", "PET_PANDA4", .01, 1),

    //  RARE
    GiraffePet2R(Rarity.RARE, Rarity.UNCOMMON, "Giraffe Pet", "PET", "PET_GIRAFFE2", .50, 1),
    TurtlePet2R(Rarity.RARE, Rarity.UNCOMMON, "Turtle Pet", "PET", "PET_TURTLE2", .50, 1),
    ElephantPet2R(Rarity.RARE, Rarity.UNCOMMON, "Elephant Pet", "PET", "PET_ELEPHANT2", .50, 1),
    ChimpPet2R(Rarity.RARE, Rarity.UNCOMMON, "Chimp Pet", "PET", "PET_CHIMP2", .50, 1),
    FrogPet2R(Rarity.RARE, Rarity.UNCOMMON, "Frog Pet", "PET", "PET_FROG2", .50, 1),
    LionPet2R(Rarity.RARE, Rarity.UNCOMMON, "Lion Pet", "PET", "PET_LION2", .50, 1),


    GiraffePetR(Rarity.RARE, Rarity.COMMON, "Giraffe Pet", "PET", "PET_GIRAFFE", .25, 1),
    TurtlePetR(Rarity.RARE, Rarity.COMMON, "Turtle Pet", "PET", "PET_TURTLE", .25, 1),
    ElephantPetR(Rarity.RARE, Rarity.COMMON, "Elephant Pet", "PET", "PET_ELEPHANT", .25, 1),
    ChimpPetR(Rarity.RARE, Rarity.COMMON, "Chimp Pet", "PET", "PET_CHIMP", .25, 1),
    FrogPetR(Rarity.RARE, Rarity.COMMON, "Frog Pet", "PET", "PET_FROG", .25, 1),
    LionPetR(Rarity.RARE, Rarity.COMMON, "Lion Pet", "PET", "PET_LION", .25, 1),

    PandaPet2R(Rarity.RARE, Rarity.UNCOMMON, "Panda Pet", "PET", "PET_PANDA2", .25, 1),
    PenguinPet2R(Rarity.RARE, Rarity.UNCOMMON, "Penguin Pet", "PET", "PET_PENGUIN2", .25, 1),

    GiraffePet3R(Rarity.RARE, Rarity.RARE, "Giraffe Pet", "PET", "PET_GIRAFFE3", .20, 1),
    TurtlePet3R(Rarity.RARE, Rarity.RARE, "Turtle Pet", "PET", "PET_TURTLE3", .20, 1),
    ElephantPet3R(Rarity.RARE, Rarity.RARE, "Elephant Pet", "PET", "PET_ELEPHANT3", .20, 1),
    ChimpPet3R(Rarity.RARE, Rarity.RARE, "Chimp Pet", "PET", "PET_CHIMP3", .20, 1),
    FrogPet3R(Rarity.RARE, Rarity.RARE, "Frog Pet", "PET", "PET_FROG3", .20, 1),
    LionPet3R(Rarity.RARE, Rarity.RARE, "Lion Pet", "PET", "PET_LION3", .20, 1),

    PandaPetR(Rarity.RARE, Rarity.COMMON, "Panda Pet", "PET", "PET_PANDA", .125, 1),
    PenguinPetR(Rarity.RARE, Rarity.COMMON, "Penguin Pet", "PET", "PET_PENGUIN", .125, 1),

    PenguinPet3R(Rarity.RARE, Rarity.RARE, "Penguin Pet", "PET", "PET_PENGUIN3", .10, 1),
    PandaPet3R(Rarity.RARE, Rarity.RARE, "Panda Pet", "PET", "PET_PANDA3", .10, 1),

    GiraffePet4R(Rarity.RARE, Rarity.LEGENDARY, "Giraffe Pet", "PET", "PET_GIRAFFE4", .05, 1),
    TurtlePet4R(Rarity.RARE, Rarity.LEGENDARY, "Turtle Pet", "PET", "PET_TURTLE4", .05, 1),
    ElephantPet4R(Rarity.RARE, Rarity.LEGENDARY, "Elephant Pet", "PET", "PET_ELEPHANT4", .05, 1),
    ChimpPet4R(Rarity.RARE, Rarity.LEGENDARY, "Chimp Pet", "PET", "PET_CHIMP4", .05, 1),
    FrogPet4R(Rarity.RARE, Rarity.LEGENDARY, "Frog Pet", "PET", "PET_FROG4", .05, 1),
    LionPet4R(Rarity.RARE, Rarity.LEGENDARY, "Lion Pet", "PET", "PET_LION4", .05, 1),

    PenguinPet4R(Rarity.RARE, Rarity.LEGENDARY, "Penguin Pet", "PET", "PET_PENGUIN4", .025, 1),
    PandaPet4R(Rarity.RARE, Rarity.LEGENDARY, "Panda Pet", "PET", "PET_PANDA4", .025, 1),

    //  LEGENDARY
    GiraffePet3L(Rarity.LEGENDARY, Rarity.RARE, "Giraffe Pet", "PET", "PET_GIRAFFE3", .50, 1),
    TurtlePet3L(Rarity.LEGENDARY, Rarity.RARE, "Turtle Pet", "PET", "PET_TURTLE3", .50, 1),
    ElephantPet3L(Rarity.LEGENDARY, Rarity.RARE, "Elephant Pet", "PET", "PET_ELEPHANT3", .50, 1),
    ChimpPet3L(Rarity.LEGENDARY, Rarity.RARE, "Chimp Pet", "PET", "PET_CHIMP3", .50, 1),
    FrogPet3L(Rarity.LEGENDARY, Rarity.RARE, "Frog Pet", "PET", "PET_FROG3", .50, 1),
    LionPet3L(Rarity.LEGENDARY, Rarity.RARE, "Lion Pet", "PET", "PET_LION3", .50, 1),

    GiraffePet2L(Rarity.LEGENDARY, Rarity.UNCOMMON, "Giraffe Pet", "PET", "PET_GIRAFFE2", .40, 1),
    TurtlePet2L(Rarity.LEGENDARY, Rarity.UNCOMMON, "Turtle Pet", "PET", "PET_TURTLE2", .40, 1),
    ElephantPet2L(Rarity.LEGENDARY, Rarity.UNCOMMON, "Elephant Pet", "PET", "PET_ELEPHANT2", .40, 1),
    ChimpPet2L(Rarity.LEGENDARY, Rarity.UNCOMMON, "Chimp Pet", "PET", "PET_CHIMP2", .40, 1),
    FrogPet2L(Rarity.LEGENDARY, Rarity.UNCOMMON, "Frog Pet", "PET", "PET_FROG2", .40, 1),
    LionPet2L(Rarity.LEGENDARY, Rarity.UNCOMMON, "Lion Pet", "PET", "PET_LION2", .40, 1),

    PenguinPet3L(Rarity.LEGENDARY, Rarity.RARE, "Penguin Pet", "PET", "PET_PENGUIN3", .25, 1),
    PandaPet3L(Rarity.LEGENDARY, Rarity.RARE, "Panda Pet", "PET", "PET_PANDA3", .25, 1),

    PandaPet2L(Rarity.LEGENDARY, Rarity.UNCOMMON, "Panda Pet", "PET", "PET_PANDA2", .20, 1),
    PenguinPet2L(Rarity.LEGENDARY, Rarity.UNCOMMON, "Penguin Pet", "PET", "PET_PENGUIN2", .20, 1),

    GiraffePet4L(Rarity.LEGENDARY, Rarity.LEGENDARY, "Giraffe Pet", "PET", "PET_GIRAFFE4", .10, 1),
    TurtlePet4L(Rarity.LEGENDARY, Rarity.LEGENDARY, "Turtle Pet", "PET", "PET_TURTLE4", .10, 1),
    ElephantPet4L(Rarity.LEGENDARY, Rarity.LEGENDARY, "Elephant Pet", "PET", "PET_ELEPHANT4", .10, 1),
    ChimpPet4L(Rarity.LEGENDARY, Rarity.LEGENDARY, "Chimp Pet", "PET", "PET_CHIMP4", .10, 1),
    FrogPet4L(Rarity.LEGENDARY, Rarity.LEGENDARY, "Frog Pet", "PET", "PET_FROG4", .10, 1),
    LionPet4L(Rarity.LEGENDARY, Rarity.LEGENDARY, "Lion Pet", "PET", "PET_LION4", .10, 1),

    PenguinPet4L(Rarity.LEGENDARY, Rarity.LEGENDARY, "Penguin Pet", "PET", "PET_PENGUIN4", .05, 1),
    PandaPet4L(Rarity.LEGENDARY, Rarity.LEGENDARY, "Panda Pet", "PET", "PET_PANDA4", .05, 1),

    GiraffePet5L(Rarity.LEGENDARY, Rarity.EXOTIC, "Giraffe Pet", "PET", "PET_GIRAFFE5", .01, 1),
    TurtlePet5L(Rarity.LEGENDARY, Rarity.EXOTIC, "Turtle Pet", "PET", "PET_TURTLE5", .01, 1),
    ElephantPet5L(Rarity.LEGENDARY, Rarity.EXOTIC, "Elephant Pet", "PET", "PET_ELEPHANT5", .01, 1),
    ChimpPet5L(Rarity.LEGENDARY, Rarity.EXOTIC, "Chimp Pet", "PET", "PET_CHIMP5", .01, 1),
    FrogPet5L(Rarity.LEGENDARY, Rarity.EXOTIC, "Frog Pet", "PET", "PET_FROG5", .01, 1),
    LionPet5L(Rarity.LEGENDARY, Rarity.EXOTIC, "Lion Pet", "PET", "PET_LION5", .01, 1),

    PandaPet5L(Rarity.LEGENDARY, Rarity.EXOTIC, "Panda Pet", "PET", "PET_PANDA5", .005, 1),
    PenguinPet5L(Rarity.LEGENDARY, Rarity.EXOTIC, "Penguin Pet", "PET", "PET_PENGUIN5", .005, 1),

    // EXOTIC
    GiraffePet3E(Rarity.EXOTIC, Rarity.RARE, "Giraffe Pet", "PET", "PET_GIRAFFE3", .70, 1),
    TurtlePet3E(Rarity.EXOTIC, Rarity.RARE, "Turtle Pet", "PET", "PET_TURTLE3", .70, 1),
    ElephantPet3E(Rarity.EXOTIC, Rarity.RARE, "Elephant Pet", "PET", "PET_ELEPHANT3", .70, 1),
    ChimpPet3E(Rarity.EXOTIC, Rarity.RARE, "Chimp Pet", "PET", "PET_CHIMP3", .70, 1),
    FrogPet3E(Rarity.EXOTIC, Rarity.RARE, "Frog Pet", "PET", "PET_FROG3", .70, 1),
    LionPet3E(Rarity.EXOTIC, Rarity.RARE, "Lion Pet", "PET", "PET_LION3", .70, 1),

    PenguinPet3E(Rarity.EXOTIC, Rarity.RARE, "Penguin Pet", "PET", "PET_PENGUIN3", .35, 1),
    PandaPet3E(Rarity.EXOTIC, Rarity.RARE, "Panda Pet", "PET", "PET_PANDA3", .35, 1),

    GiraffePet4E(Rarity.EXOTIC, Rarity.LEGENDARY, "Giraffe Pet", "PET", "PET_GIRAFFE4", .25, 1),
    TurtlePet4E(Rarity.EXOTIC, Rarity.LEGENDARY, "Turtle Pet", "PET", "PET_TURTLE4", .25, 1),
    ElephantPet4E(Rarity.EXOTIC, Rarity.LEGENDARY, "Elephant Pet", "PET", "PET_ELEPHANT4", .25, 1),
    ChimpPet4E(Rarity.EXOTIC, Rarity.LEGENDARY, "Chimp Pet", "PET", "PET_CHIMP4", .25, 1),
    FrogPet4E(Rarity.EXOTIC, Rarity.LEGENDARY, "Frog Pet", "PET", "PET_FROG4", .25, 1),
    LionPet4E(Rarity.EXOTIC, Rarity.LEGENDARY, "Lion Pet", "PET", "PET_LION4", .25, 1),

    PenguinPet4E(Rarity.EXOTIC, Rarity.LEGENDARY, "Penguin Pet", "PET", "PET_PENGUIN4", .125, 1),
    PandaPet4E(Rarity.EXOTIC, Rarity.LEGENDARY, "Panda Pet", "PET", "PET_PANDA4", .125, 1),

    GiraffePet5E(Rarity.EXOTIC, Rarity.EXOTIC, "Giraffe Pet", "PET", "PET_GIRAFFE5", .05, 1),
    TurtlePet5E(Rarity.EXOTIC, Rarity.EXOTIC, "Turtle Pet", "PET", "PET_TURTLE5", .05, 1),
    ElephantPet5E(Rarity.EXOTIC, Rarity.EXOTIC, "Elephant Pet", "PET", "PET_ELEPHANT5", .05, 1),
    ChimpPet5E(Rarity.EXOTIC, Rarity.EXOTIC, "Chimp Pet", "PET", "PET_CHIMP5", .05, 1),
    FrogPetE(Rarity.EXOTIC, Rarity.EXOTIC, "Frog Pet", "PET", "PET_FROG5", .05, 1),
    LionPet5E(Rarity.EXOTIC, Rarity.EXOTIC, "Lion Pet", "PET", "PET_LION5", .05, 1),

    PandaPet5E(Rarity.EXOTIC, Rarity.EXOTIC, "Panda Pet", "PET", "PET_PANDA5", .025, 1),
    PenguinPet5E(Rarity.EXOTIC, Rarity.EXOTIC, "Penguin Pet", "PET", "PET_PENGUIN5", .025, 1),

    GiraffePet6E(Rarity.EXOTIC, Rarity.MYTHICAL, "Giraffe Pet", "PET", "PET_GIRAFFE6", .000001, 1),
    TurtlePet6E(Rarity.EXOTIC, Rarity.MYTHICAL, "Turtle Pet", "PET", "PET_TURTLE6", .000001, 1),
    ElephantPet6E(Rarity.EXOTIC, Rarity.MYTHICAL, "Elephant Pet", "PET", "PET_ELEPHANT6", .000001, 1),
    ChimpPet6E(Rarity.EXOTIC, Rarity.MYTHICAL, "Chimp Pet", "PET", "PET_CHIMP6", .000001, 1),
    FrogPet6E(Rarity.EXOTIC, Rarity.MYTHICAL, "Frog Pet", "PET", "PET_FROG6", .000001, 1),
    LionPet6E(Rarity.EXOTIC, Rarity.MYTHICAL, "Lion Pet", "PET", "PET_LION6", .000001, 1),

    PandaPet6E(Rarity.EXOTIC, Rarity.MYTHICAL, "Panda Pet", "PET", "PET_PANDA6", .0000005, 1),
    PenguinPet6E(Rarity.EXOTIC, Rarity.MYTHICAL, "Penguin Pet", "PET", "PET_PENGUIN6", .0000005, 1),

    // MYTHICAL
    GiraffePet3M(Rarity.MYTHICAL, Rarity.RARE, "Giraffe Pet", "PET", "PET_GIRAFFE3", .50, 1),
    TurtlePet3M(Rarity.MYTHICAL, Rarity.RARE, "Turtle Pet", "PET", "PET_TURTLE3", .50, 1),
    ElephantPet3M(Rarity.MYTHICAL, Rarity.RARE, "Elephant Pet", "PET", "PET_ELEPHANT3", .50, 1),
    ChimpPet3M(Rarity.MYTHICAL, Rarity.RARE, "Chimp Pet", "PET", "PET_CHIMP3", .50, 1),
    FrogPet3M(Rarity.MYTHICAL, Rarity.RARE, "Frog Pet", "PET", "PET_FROG3", .50, 1),
    LionPet3M(Rarity.MYTHICAL, Rarity.RARE, "Lion Pet", "PET", "PET_LION3", .50, 1),

    PenguinPet3M(Rarity.MYTHICAL, Rarity.RARE, "Penguin Pet", "PET", "PET_PENGUIN3", .25, 1),
    PandaPet3M(Rarity.MYTHICAL, Rarity.RARE, "Panda Pet", "PET", "PET_PANDA3", .25, 1),

    GiraffePet4M(Rarity.MYTHICAL, Rarity.LEGENDARY, "Giraffe Pet", "PET", "PET_GIRAFFE4", .35, 1),
    TurtlePet4M(Rarity.MYTHICAL, Rarity.LEGENDARY, "Turtle Pet", "PET", "PET_TURTLE4", .35, 1),
    ElephantPet4M(Rarity.MYTHICAL, Rarity.LEGENDARY, "Elephant Pet", "PET", "PET_ELEPHANT4", .35, 1),
    ChimpPet4M(Rarity.MYTHICAL, Rarity.LEGENDARY, "Chimp Pet", "PET", "PET_CHIMP4", .35, 1),
    FrogPet4M(Rarity.MYTHICAL, Rarity.LEGENDARY, "Frog Pet", "PET", "PET_FROG4", .35, 1),
    LionPet4M(Rarity.MYTHICAL, Rarity.LEGENDARY, "Lion Pet", "PET", "PET_LION4", .35, 1),

    PenguinPet4M(Rarity.MYTHICAL, Rarity.LEGENDARY, "Penguin Pet", "PET", "PET_PENGUIN4", .175, 1),
    PandaPet4M(Rarity.MYTHICAL, Rarity.LEGENDARY, "Panda Pet", "PET", "PET_PANDA4", .175, 1),

    GiraffePet5M(Rarity.MYTHICAL, Rarity.EXOTIC, "Giraffe Pet", "PET", "PET_GIRAFFE5", .1, 1),
    TurtlePet5M(Rarity.MYTHICAL, Rarity.EXOTIC, "Turtle Pet", "PET", "PET_TURTLE5", .1, 1),
    ElephantPet5M(Rarity.MYTHICAL, Rarity.EXOTIC, "Elephant Pet", "PET", "PET_ELEPHANT5", .1, 1),
    ChimpPet5M(Rarity.MYTHICAL, Rarity.EXOTIC, "Chimp Pet", "PET", "PET_CHIMP5", .1, 1),
    FrogPetM(Rarity.MYTHICAL, Rarity.EXOTIC, "Frog Pet", "PET", "PET_FROG5", .1, 1),
    LionPet5M(Rarity.MYTHICAL, Rarity.EXOTIC, "Lion Pet", "PET", "PET_LION5", .1, 1),

    PandaPet5M(Rarity.MYTHICAL, Rarity.EXOTIC, "Panda Pet", "PET", "PET_PANDA5", .05, 1),
    PenguinPet5M(Rarity.MYTHICAL, Rarity.EXOTIC, "Penguin Pet", "PET", "PET_PENGUIN5", .05, 1),

    GiraffePet6M(Rarity.MYTHICAL, Rarity.MYTHICAL, "Giraffe Pet", "PET", "PET_GIRAFFE6", .000002, 1),
    TurtlePet6M(Rarity.MYTHICAL, Rarity.MYTHICAL, "Turtle Pet", "PET", "PET_TURTLE6", .000002, 1),
    ElephantPet6M(Rarity.MYTHICAL, Rarity.MYTHICAL, "Elephant Pet", "PET", "PET_ELEPHANT6", .000002, 1),
    ChimpPet6M(Rarity.MYTHICAL, Rarity.MYTHICAL, "Chimp Pet", "PET", "PET_CHIMP6", .000002, 1),
    FrogPet6M(Rarity.MYTHICAL, Rarity.MYTHICAL, "Frog Pet", "PET", "PET_FROG6", .000002, 1),
    LionPet6M(Rarity.MYTHICAL, Rarity.MYTHICAL, "Lion Pet", "PET", "PET_LION6", .000002, 1),

    PandaPet6M(Rarity.MYTHICAL, Rarity.MYTHICAL, "Panda Pet", "PET", "PET_PANDA6", .000001, 1),
    PenguinPet6M(Rarity.MYTHICAL, Rarity.MYTHICAL, "Penguin Pet", "PET", "PET_PENGUIN6", .000001, 1),

    // GODLY
    GiraffePet4G(Rarity.GODLY, Rarity.LEGENDARY, "Giraffe Pet", "PET", "PET_GIRAFFE4", .70, 1),
    TurtlePet4G(Rarity.GODLY, Rarity.LEGENDARY, "Turtle Pet", "PET", "PET_TURTLE4", .70, 1),
    ElephantPet4G(Rarity.GODLY, Rarity.LEGENDARY, "Elephant Pet", "PET", "PET_ELEPHANT4", .70, 1),
    ChimpPet4G(Rarity.GODLY, Rarity.LEGENDARY, "Chimp Pet", "PET", "PET_CHIMP4", .70, 1),
    FrogPet4G(Rarity.GODLY, Rarity.LEGENDARY, "Frog Pet", "PET", "PET_FROG4", .70, 1),
    LionPet4G(Rarity.GODLY, Rarity.LEGENDARY, "Lion Pet", "PET", "PET_LION4", .70, 1),

    PenguinPet4G(Rarity.GODLY, Rarity.LEGENDARY, "Penguin Pet", "PET", "PET_PENGUIN4", .35, 1),
    PandaPet4G(Rarity.GODLY, Rarity.LEGENDARY, "Panda Pet", "PET", "PET_PANDA4", .35, 1),

    GiraffePet5G(Rarity.GODLY, Rarity.EXOTIC, "Giraffe Pet", "PET", "PET_GIRAFFE5", .5, 1),
    TurtlePet5G(Rarity.GODLY, Rarity.EXOTIC, "Turtle Pet", "PET", "PET_TURTLE5", .5, 1),
    ElephantPet5G(Rarity.GODLY, Rarity.EXOTIC, "Elephant Pet", "PET", "PET_ELEPHANT5", .5, 1),
    ChimpPet5G(Rarity.GODLY, Rarity.EXOTIC, "Chimp Pet", "PET", "PET_CHIMP5", .5, 1),
    FrogPetG(Rarity.GODLY, Rarity.EXOTIC, "Frog Pet", "PET", "PET_FROG5", .5, 1),
    LionPet5G(Rarity.GODLY, Rarity.EXOTIC, "Lion Pet", "PET", "PET_LION5", .5, 1),

    PandaPet5G(Rarity.GODLY, Rarity.EXOTIC, "Panda Pet", "PET", "PET_PANDA5", .25, 1),
    PenguinPet5G(Rarity.GODLY, Rarity.EXOTIC, "Penguin Pet", "PET", "PET_PENGUIN5", .25, 1),

    GiraffePet6G(Rarity.GODLY, Rarity.MYTHICAL, "Giraffe Pet", "PET", "PET_GIRAFFE6", .0001, 1),
    TurtlePet6G(Rarity.GODLY, Rarity.MYTHICAL, "Turtle Pet", "PET", "PET_TURTLE6", .0001, 1),
    ElephantPet6G(Rarity.GODLY, Rarity.MYTHICAL, "Elephant Pet", "PET", "PET_ELEPHANT6", .0001, 1),
    ChimpPet6G(Rarity.GODLY, Rarity.MYTHICAL, "Chimp Pet", "PET", "PET_CHIMP6", .0001, 1),
    FrogPet6G(Rarity.GODLY, Rarity.MYTHICAL, "Frog Pet", "PET", "PET_FROG6", .0001, 1),
    LionPet6G(Rarity.GODLY, Rarity.MYTHICAL, "Lion Pet", "PET", "PET_LION6", .0001, 1),

    PandaPet6G(Rarity.GODLY, Rarity.MYTHICAL, "Panda Pet", "PET", "PET_PANDA6", .00005, 1),
    PenguinPet6G(Rarity.GODLY, Rarity.MYTHICAL, "Penguin Pet", "PET", "PET_PENGUIN6", .00005, 1),

    // Phoenix Rare
    PhoenixPet3P(Rarity.PHOENIX, Rarity.RARE, "Baby Phoenix Pet", "PET", "PET_PHOENIX3", .95, 1),
    PhoenixPet4P(Rarity.PHOENIX, Rarity.LEGENDARY,"Baby Phoenix Pet", "PET", "PET_PHOENIX4", .045, 1),
    PhoenixPet5P(Rarity.PHOENIX, Rarity.EXOTIC,"Adult Phoenix Pet", "PET", "PET_PHOENIX5", .005,1 ),

    // Phoenix Legendary
    PhoenixPet3P2(Rarity.PHOENIX2, Rarity.RARE, "Baby Phoenix Pet", "PET", "PET_PHOENIX3", .925, 1),
    PhoenixPet4P2(Rarity.PHOENIX2, Rarity.LEGENDARY,"Baby Phoenix Pet", "PET", "PET_PHOENIX4", .0675, 1),
    PhoenixPet5P2(Rarity.PHOENIX2, Rarity.EXOTIC,"Adult Phoenix Pet", "PET", "PET_PHOENIX5", .0075,1 ),

    // Dragon Rare
    DragonPet3D(Rarity.DRAGON, Rarity.RARE, "Baby Dragon Pet", "PET", "PET_DRAGON3", .95, 1),
    DragonPet4D(Rarity.DRAGON, Rarity.LEGENDARY,"Baby Dragon Pet", "PET", "PET_DRAGON4", .045, 1),
    DragonPet5D(Rarity.DRAGON, Rarity.EXOTIC,"Juvenile Dragon Pet", "PET", "PET_DRAGON5", .005,1 ),

    // Dragon Legendary
    DragonPet3D2(Rarity.DRAGON2, Rarity.RARE, "Baby Dragon Pet", "PET", "PET_DRAGON3", .925, 1),
    DragonPet4D2(Rarity.DRAGON2, Rarity.LEGENDARY,"Baby Dragon Pet", "PET", "PET_DRAGON4", .0675, 1),
    DragonPet5D2(Rarity.DRAGON2, Rarity.EXOTIC,"Juvenile Dragon Pet", "PET", "PET_DRAGON5", .0075,1 ),

    ;

    private static final Map<Rarity, List<PetEgg>> RARITY_LISTS = new EnumMap<>(Rarity.class);

    public static List<PetEgg> getEggs(Rarity rarity) {
        return RARITY_LISTS.computeIfAbsent(rarity, key -> Arrays.stream(values())
                .filter(egg -> egg.getEggRarity() == key)
                .collect(Collectors.toList()));
    }

    private final String displayName;

    private final String type;
    private final Rarity eggRarity;
    private final Rarity petRarity;
    private final String id;
    private final double weight;
    private final int amount;

    PetEgg(Rarity eggRarity, Rarity petRarity, String displayName, String type, String id, double weight, int amount) {
        this.eggRarity = eggRarity;
        this.petRarity = petRarity;
        this.id = id;
        this.type = type;
        this.displayName = petRarity.getColorString() + displayName;
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

    public String getDisplayName() {
        return displayName;
    }

    public Rarity getPetRarity() {
        return petRarity;
    }

    public Rarity getEggRarity() {
        return eggRarity;
    }

    @Override
    public ItemStack generateItem() {
        final ItemStack itemStack = MMOItems.plugin.getItem(type, id);
        if (itemStack != null) {
            List<Component> lore = itemStack.lore();
            if (lore == null) {
                lore = new ArrayList<>();
            } else if (!lore.isEmpty()) {
                lore.add(Component.empty());
            }
            lore.add(Component.text("CHANCE: " + weight * 10 + "%", NamedTextColor.GOLD, TextDecoration.BOLD));
            itemStack.lore(lore);
            return itemStack.asQuantity(amount);
        }
        return null;
    }

    @Override
    public ItemStack generateItem(Player player) {
        return null;
    }
}
