package monzter.adventurescraft.plugin.network.PrisonGamemode.shared.commands.dropTables;

import me.lucko.helper.random.Weighted;
import monzter.adventurescraft.plugin.utilities.general.ItemGenerator;
import net.Indyuce.mmoitems.MMOItems;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

public enum DropTables implements Weighted, ItemGenerator {
    //        .5 = 50%
    GiraffePet(DropTableTypes.COMMON_PET_EGG, "PET", "PET_GIRAFFE", .70, 1),
    TurtlePet(DropTableTypes.COMMON_PET_EGG, "PET", "PET_TURTLE", .70, 1),
    ElephantPet(DropTableTypes.COMMON_PET_EGG, "PET", "PET_ELEPHANT", .70, 1),
    GorillaPet(DropTableTypes.COMMON_PET_EGG, "PET", "PET_GORILLA", .70, 1),
    FrogPet(DropTableTypes.COMMON_PET_EGG, "PET", "PET_FROG", .70, 1),
    LionPet(DropTableTypes.COMMON_PET_EGG, "PET", "PET_LION", .70, 1),

    RedPandaPet(DropTableTypes.COMMON_PET_EGG, "PET", "PET_RED_PANDA", .35, 1),
    PenguinPet(DropTableTypes.COMMON_PET_EGG, "PET", "PET_PENGUIN", .35, 1),
    GiraffePet2(DropTableTypes.COMMON_PET_EGG, "PET", "PET_GIRAFFE2", .25, 1),
    TurtlePet2(DropTableTypes.COMMON_PET_EGG, "PET", "PET_TURTLE2", .25, 1),
    ElephantPet2(DropTableTypes.COMMON_PET_EGG, "PET", "PET_ELEPHANT2", .25, 1),
    GorillaPet2(DropTableTypes.COMMON_PET_EGG, "PET", "PET_GORILLA2", .25, 1),
    FrogPet2(DropTableTypes.COMMON_PET_EGG, "PET", "PET_FROG2", .25, 1),
    LionPet2(DropTableTypes.COMMON_PET_EGG, "PET", "PET_LION2", .25, 1),

    RedPandaPet2(DropTableTypes.COMMON_PET_EGG, "PET", "PET_RED_PANDA2", .125, 1),
    PenguinPet2(DropTableTypes.COMMON_PET_EGG, "PET", "PET_PENGUIN2", .125, 1),

    GiraffePet3(DropTableTypes.COMMON_PET_EGG, "PET", "PET_GIRAFFE3", .04, 1),
    TurtlePet3(DropTableTypes.COMMON_PET_EGG, "PET", "PET_TURTLE3", .04, 1),
    ElephantPet3(DropTableTypes.COMMON_PET_EGG, "PET", "PET_ELEPHANT3", .04, 1),
    GorillaPet3(DropTableTypes.COMMON_PET_EGG, "PET", "PET_GORILLA3", .04, 1),
    FrogPet3(DropTableTypes.COMMON_PET_EGG, "PET", "PET_FROG3", .04, 1),
    LionPet3(DropTableTypes.COMMON_PET_EGG, "PET", "PET_LION3", .04, 1),

    PenguinPet3(DropTableTypes.COMMON_PET_EGG, "PET", "PET_PENGUIN3", .02, 1),
    RedPandaPet3(DropTableTypes.COMMON_PET_EGG, "PET", "PET_RED_PANDA3", .02, 1),

    GiraffePet4(DropTableTypes.COMMON_PET_EGG, "PET", "PET_GIRAFFE4", .01, 1),
    TurtlePet4(DropTableTypes.COMMON_PET_EGG, "PET", "PET_TURTLE4", .01, 1),
    ElephantPet4(DropTableTypes.COMMON_PET_EGG, "PET", "PET_ELEPHANT4", .01, 1),
    GorillaPet4(DropTableTypes.COMMON_PET_EGG, "PET", "PET_GORILLA4", .01, 1),
    FrogPet4(DropTableTypes.COMMON_PET_EGG, "PET", "PET_FROG4", .01, 1),
    LionPet4(DropTableTypes.COMMON_PET_EGG, "PET", "PET_LION4", .01, 1),

    PenguinPet4(DropTableTypes.COMMON_PET_EGG, "PET", "PET_PENGUIN4", .005, 1),
    RedPandaPet4(DropTableTypes.COMMON_PET_EGG, "PET", "PET_RED_PANDA4", .005, 1),

    //    UNCOMMON
    GiraffePet2U(DropTableTypes.UNCOMMON_PET_EGG, "PET", "PET_GIRAFFE2", .60, 1),
    TurtlePet2U(DropTableTypes.UNCOMMON_PET_EGG, "PET", "PET_TURTLE2", .60, 1),
    ElephantPet2U(DropTableTypes.UNCOMMON_PET_EGG, "PET", "PET_ELEPHANT2", .60, 1),
    GorillaPet2U(DropTableTypes.UNCOMMON_PET_EGG, "PET", "PET_GORILLA2", .60, 1),
    FrogPet2U(DropTableTypes.UNCOMMON_PET_EGG, "PET", "PET_FROG2", .60, 1),
    LionPet2U(DropTableTypes.UNCOMMON_PET_EGG, "PET", "PET_LION2", .60, 1),

    GiraffePetU(DropTableTypes.UNCOMMON_PET_EGG, "PET", "PET_GIRAFFE", .30, 1),
    TurtlePetU(DropTableTypes.UNCOMMON_PET_EGG, "PET", "PET_TURTLE", .30, 1),
    ElephantPetU(DropTableTypes.UNCOMMON_PET_EGG, "PET", "PET_ELEPHANT", .30, 1),
    GorillaPetU(DropTableTypes.UNCOMMON_PET_EGG, "PET", "PET_GORILLA", .30, 1),
    FrogPetU(DropTableTypes.UNCOMMON_PET_EGG, "PET", "PET_FROG", .30, 1),
    LionPetU(DropTableTypes.UNCOMMON_PET_EGG, "PET", "PET_LION", .30, 1),

    PenguinPet2U(DropTableTypes.UNCOMMON_PET_EGG, "PET", "PET_PENGUIN2", .30, 1),
    RedPandaPet2U(DropTableTypes.UNCOMMON_PET_EGG, "PET", "PET_RED_PANDA2", .30, 1),

    RedPandaPetU(DropTableTypes.UNCOMMON_PET_EGG, "PET", "PET_RED_PANDA", .15, 1),
    PenguinPetU(DropTableTypes.UNCOMMON_PET_EGG, "PET", "PET_PENGUIN", .15, 1),

    GiraffePet3U(DropTableTypes.UNCOMMON_PET_EGG, "PET", "PET_GIRAFFE3", .08, 1),
    TurtlePet3U(DropTableTypes.UNCOMMON_PET_EGG, "PET", "PET_TURTLE3", .08, 1),
    ElephantPet3U(DropTableTypes.UNCOMMON_PET_EGG, "PET", "PET_ELEPHANT3", .08, 1),
    GorillaPet3U(DropTableTypes.UNCOMMON_PET_EGG, "PET", "PET_GORILLA3", .08, 1),
    FrogPet3U(DropTableTypes.UNCOMMON_PET_EGG, "PET", "PET_FROG3", .08, 1),
    LionPet3U(DropTableTypes.UNCOMMON_PET_EGG, "PET", "PET_LION3", .08, 1),

    PenguinPet3U(DropTableTypes.UNCOMMON_PET_EGG, "PET", "PET_PENGUIN3", .04, 1),
    RedPandaPet3U(DropTableTypes.UNCOMMON_PET_EGG, "PET", "PET_RED_PANDA3", .04, 1),

    GiraffePet4U(DropTableTypes.UNCOMMON_PET_EGG, "PET", "PET_GIRAFFE4", .02, 1),
    TurtlePet4U(DropTableTypes.UNCOMMON_PET_EGG, "PET", "PET_TURTLE4", .02, 1),
    ElephantPet4U(DropTableTypes.UNCOMMON_PET_EGG, "PET", "PET_ELEPHANT4", .02, 1),
    GorillaPet4U(DropTableTypes.UNCOMMON_PET_EGG, "PET", "PET_GORILLA4", .02, 1),
    FrogPet4U(DropTableTypes.UNCOMMON_PET_EGG, "PET", "PET_FROG4", .02, 1),
    LionPet4U(DropTableTypes.UNCOMMON_PET_EGG, "PET", "PET_LION4", .02, 1),

    PenguinPet4U(DropTableTypes.UNCOMMON_PET_EGG, "PET", "PET_PENGUIN4", .01, 1),
    RedPandaPet4U(DropTableTypes.UNCOMMON_PET_EGG, "PET", "PET_RED_PANDA4", .01, 1),

    //  RARE
    GiraffePet2R(DropTableTypes.RARE_PET_EGG, "PET", "PET_GIRAFFE2", .50, 1),
    TurtlePet2R(DropTableTypes.RARE_PET_EGG, "PET", "PET_TURTLE2", .50, 1),
    ElephantPet2R(DropTableTypes.RARE_PET_EGG, "PET", "PET_ELEPHANT2", .50, 1),
    GorillaPet2R(DropTableTypes.RARE_PET_EGG, "PET", "PET_GORILLA2", .50, 1),
    FrogPet2R(DropTableTypes.RARE_PET_EGG, "PET", "PET_FROG2", .50, 1),
    LionPet2R(DropTableTypes.RARE_PET_EGG, "PET", "PET_LION2", .50, 1),


    GiraffePetR(DropTableTypes.RARE_PET_EGG, "PET", "PET_GIRAFFE", .25, 1),
    TurtlePetR(DropTableTypes.RARE_PET_EGG, "PET", "PET_TURTLE", .25, 1),
    ElephantPetR(DropTableTypes.RARE_PET_EGG, "PET", "PET_ELEPHANT", .25, 1),
    GorillaPetR(DropTableTypes.RARE_PET_EGG, "PET", "PET_GORILLA", .25, 1),
    FrogPetR(DropTableTypes.RARE_PET_EGG, "PET", "PET_FROG", .25, 1),
    LionPetR(DropTableTypes.RARE_PET_EGG, "PET", "PET_LION", .25, 1),

    RedPandaPet2R(DropTableTypes.RARE_PET_EGG, "PET", "PET_RED_PANDA2", .25, 1),
    PenguinPet2R(DropTableTypes.RARE_PET_EGG, "PET", "PET_PENGUIN2", .25, 1),

    GiraffePet3R(DropTableTypes.RARE_PET_EGG, "PET", "PET_GIRAFFE3", .20, 1),
    TurtlePet3R(DropTableTypes.RARE_PET_EGG, "PET", "PET_TURTLE3", .20, 1),
    ElephantPet3R(DropTableTypes.RARE_PET_EGG, "PET", "PET_ELEPHANT3", .20, 1),
    GorillaPet3R(DropTableTypes.RARE_PET_EGG, "PET", "PET_GORILLA3", .20, 1),
    FrogPet3R(DropTableTypes.RARE_PET_EGG, "PET", "PET_FROG3", .20, 1),
    LionPet3R(DropTableTypes.RARE_PET_EGG, "PET", "PET_LION3", .20, 1),

    RedPandaPetR(DropTableTypes.RARE_PET_EGG, "PET", "PET_RED_PANDA", .125, 1),
    PenguinPetR(DropTableTypes.RARE_PET_EGG, "PET", "PET_PENGUIN", .125, 1),

    PenguinPet3R(DropTableTypes.RARE_PET_EGG, "PET", "PET_PENGUIN3", .10, 1),
    RedPandaPet3R(DropTableTypes.RARE_PET_EGG, "PET", "PET_RED_PANDA3", .10, 1),

    GiraffePet4R(DropTableTypes.RARE_PET_EGG, "PET", "PET_GIRAFFE4", .05, 1),
    TurtlePet4R(DropTableTypes.RARE_PET_EGG, "PET", "PET_TURTLE4", .05, 1),
    ElephantPet4R(DropTableTypes.RARE_PET_EGG, "PET", "PET_ELEPHANT4", .05, 1),
    GorillaPet4R(DropTableTypes.RARE_PET_EGG, "PET", "PET_GORILLA4", .05, 1),
    FrogPet4R(DropTableTypes.RARE_PET_EGG, "PET", "PET_FROG4", .05, 1),
    LionPet4R(DropTableTypes.RARE_PET_EGG, "PET", "PET_LION4", .05, 1),

    PenguinPet4R(DropTableTypes.RARE_PET_EGG, "PET", "PET_PENGUIN4", .025, 1),
    RedPandaPet4R(DropTableTypes.RARE_PET_EGG, "PET", "PET_RED_PANDA4", .025, 1),

    //  LEGENDARY
    GiraffePet3L(DropTableTypes.LEGENDARY_PET_EGG, "PET", "PET_GIRAFFE3", .50, 1),
    TurtlePet3L(DropTableTypes.LEGENDARY_PET_EGG, "PET", "PET_TURTLE3", .50, 1),
    ElephantPet3L(DropTableTypes.LEGENDARY_PET_EGG, "PET", "PET_ELEPHANT3", .50, 1),
    GorillaPet3L(DropTableTypes.LEGENDARY_PET_EGG, "PET", "PET_GORILLA3", .50, 1),
    FrogPet3L(DropTableTypes.LEGENDARY_PET_EGG, "PET", "PET_FROG3", .50, 1),
    LionPet3L(DropTableTypes.LEGENDARY_PET_EGG, "PET", "PET_LION3", .50, 1),

    GiraffePet2L(DropTableTypes.LEGENDARY_PET_EGG, "PET", "PET_GIRAFFE2", .40, 1),
    TurtlePet2L(DropTableTypes.LEGENDARY_PET_EGG, "PET", "PET_TURTLE2", .40, 1),
    ElephantPet2L(DropTableTypes.LEGENDARY_PET_EGG, "PET", "PET_ELEPHANT2", .40, 1),
    GorillaPet2L(DropTableTypes.LEGENDARY_PET_EGG, "PET", "PET_GORILLA2", .40, 1),
    FrogPet2L(DropTableTypes.LEGENDARY_PET_EGG, "PET", "PET_FROG2", .40, 1),
    LionPet2L(DropTableTypes.LEGENDARY_PET_EGG, "PET", "PET_LION2", .40, 1),

    PenguinPet3L(DropTableTypes.LEGENDARY_PET_EGG, "PET", "PET_PENGUIN3", .25, 1),
    RedPandaPet3L(DropTableTypes.LEGENDARY_PET_EGG, "PET", "PET_RED_PANDA3", .25, 1),

    RedPandaPet2L(DropTableTypes.LEGENDARY_PET_EGG, "PET", "PET_RED_PANDA2", .20, 1),
    PenguinPet2L(DropTableTypes.LEGENDARY_PET_EGG, "PET", "PET_PENGUIN2", .20, 1),

    GiraffePet4L(DropTableTypes.LEGENDARY_PET_EGG, "PET", "PET_GIRAFFE4", .10, 1),
    TurtlePet4L(DropTableTypes.LEGENDARY_PET_EGG, "PET", "PET_TURTLE4", .10, 1),
    ElephantPet4L(DropTableTypes.LEGENDARY_PET_EGG, "PET", "PET_ELEPHANT4", .10, 1),
    GorillaPet4L(DropTableTypes.LEGENDARY_PET_EGG, "PET", "PET_GORILLA4", .10, 1),
    FrogPet4L(DropTableTypes.LEGENDARY_PET_EGG, "PET", "PET_FROG4", .10, 1),
    LionPet4L(DropTableTypes.LEGENDARY_PET_EGG, "PET", "PET_LION4", .10, 1),

    PenguinPet4L(DropTableTypes.LEGENDARY_PET_EGG, "PET", "PET_PENGUIN4", .05, 1),
    RedPandaPet4L(DropTableTypes.LEGENDARY_PET_EGG, "PET", "PET_RED_PANDA4", .05, 1),

    GiraffePet5L(DropTableTypes.LEGENDARY_PET_EGG, "PET", "PET_GIRAFFE5", .01, 1),
    TurtlePet5L(DropTableTypes.LEGENDARY_PET_EGG, "PET", "PET_TURTLE5", .01, 1),
    ElephantPet5L(DropTableTypes.LEGENDARY_PET_EGG, "PET", "PET_ELEPHANT5", .01, 1),
    GorillaPet5L(DropTableTypes.LEGENDARY_PET_EGG, "PET", "PET_GORILLA5", .01, 1),
    FrogPet5L(DropTableTypes.LEGENDARY_PET_EGG, "PET", "PET_FROG5", .01, 1),
    LionPet5L(DropTableTypes.LEGENDARY_PET_EGG, "PET", "PET_LION5", .01, 1),

    RedPandaPet5L(DropTableTypes.LEGENDARY_PET_EGG, "PET", "PET_RED_PANDA5", .005, 1),
    PenguinPet5L(DropTableTypes.LEGENDARY_PET_EGG, "PET", "PET_PENGUIN5", .005, 1),

    // EXOTIC
    GiraffePet3E(DropTableTypes.EXOTIC_PET_EGG, "PET", "PET_GIRAFFE3", .70, 1),
    TurtlePet3E(DropTableTypes.EXOTIC_PET_EGG, "PET", "PET_TURTLE3", .70, 1),
    ElephantPet3E(DropTableTypes.EXOTIC_PET_EGG, "PET", "PET_ELEPHANT3", .70, 1),
    GorillaPet3E(DropTableTypes.EXOTIC_PET_EGG, "PET", "PET_GORILLA3", .70, 1),
    FrogPet3E(DropTableTypes.EXOTIC_PET_EGG, "PET", "PET_FROG3", .70, 1),
    LionPet3E(DropTableTypes.EXOTIC_PET_EGG, "PET", "PET_LION3", .70, 1),

    PenguinPet3E(DropTableTypes.EXOTIC_PET_EGG, "PET", "PET_PENGUIN3", .35, 1),
    RedPandaPet3E(DropTableTypes.EXOTIC_PET_EGG, "PET", "PET_RED_PANDA3", .35, 1),

    GiraffePet4E(DropTableTypes.EXOTIC_PET_EGG, "PET", "PET_GIRAFFE4", .25, 1),
    TurtlePet4E(DropTableTypes.EXOTIC_PET_EGG, "PET", "PET_TURTLE4", .25, 1),
    ElephantPet4E(DropTableTypes.EXOTIC_PET_EGG, "PET", "PET_ELEPHANT4", .25, 1),
    GorillaPet4E(DropTableTypes.EXOTIC_PET_EGG, "PET", "PET_GORILLA4", .25, 1),
    FrogPet4E(DropTableTypes.EXOTIC_PET_EGG, "PET", "PET_FROG4", .25, 1),
    LionPet4E(DropTableTypes.EXOTIC_PET_EGG, "PET", "PET_LION4", .25, 1),

    PenguinPet4E(DropTableTypes.EXOTIC_PET_EGG, "PET", "PET_PENGUIN4", .125, 1),
    RedPandaPet4E(DropTableTypes.EXOTIC_PET_EGG, "PET", "PET_RED_PANDA4", .125, 1),

    GiraffePet5E(DropTableTypes.EXOTIC_PET_EGG, "PET", "PET_GIRAFFE5", .05, 1),
    TurtlePet5E(DropTableTypes.EXOTIC_PET_EGG, "PET", "PET_TURTLE5", .05, 1),
    ElephantPet5E(DropTableTypes.EXOTIC_PET_EGG, "PET", "PET_ELEPHANT5", .05, 1),
    GorillaPet5E(DropTableTypes.EXOTIC_PET_EGG, "PET", "PET_GORILLA5", .05, 1),
    FrogPetE(DropTableTypes.EXOTIC_PET_EGG, "PET", "PET_FROG5", .05, 1),
    LionPet5E(DropTableTypes.EXOTIC_PET_EGG, "PET", "PET_LION5", .05, 1),

    RedPandaPet5E(DropTableTypes.EXOTIC_PET_EGG, "PET", "PET_RED_PANDA5", .025, 1),
    PenguinPet5E(DropTableTypes.EXOTIC_PET_EGG, "PET", "PET_PENGUIN5", .025, 1),

    GiraffePet6E(DropTableTypes.EXOTIC_PET_EGG, "PET", "PET_GIRAFFE6", .000001, 1),
    TurtlePet6E(DropTableTypes.EXOTIC_PET_EGG, "PET", "PET_TURTLE6", .000001, 1),
    ElephantPet6E(DropTableTypes.EXOTIC_PET_EGG, "PET", "PET_ELEPHANT6", .000001, 1),
    GorillaPet6E(DropTableTypes.EXOTIC_PET_EGG, "PET", "PET_GORILLA6", .000001, 1),
    FrogPet6E(DropTableTypes.EXOTIC_PET_EGG, "PET", "PET_FROG6", .000001, 1),
    LionPet6E(DropTableTypes.EXOTIC_PET_EGG, "PET", "PET_LION6", .000001, 1),

    RedPandaPet6E(DropTableTypes.EXOTIC_PET_EGG, "PET", "PET_RED_PANDA6", .0000005, 1),
    PenguinPet6E(DropTableTypes.EXOTIC_PET_EGG, "PET", "PET_PENGUIN6", .0000005, 1),

    // MYTHICAL
    GiraffePet3M(DropTableTypes.MYTHICAL_PET_EGG, "PET", "PET_GIRAFFE3", .50, 1),
    TurtlePet3M(DropTableTypes.MYTHICAL_PET_EGG, "PET", "PET_TURTLE3", .50, 1),
    ElephantPet3M(DropTableTypes.MYTHICAL_PET_EGG, "PET", "PET_ELEPHANT3", .50, 1),
    GorillaPet3M(DropTableTypes.MYTHICAL_PET_EGG, "PET", "PET_GORILLA3", .50, 1),
    FrogPet3M(DropTableTypes.MYTHICAL_PET_EGG, "PET", "PET_FROG3", .50, 1),
    LionPet3M(DropTableTypes.MYTHICAL_PET_EGG, "PET", "PET_LION3", .50, 1),

    PenguinPet3M(DropTableTypes.MYTHICAL_PET_EGG, "PET", "PET_PENGUIN3", .25, 1),
    RedPandaPet3M(DropTableTypes.MYTHICAL_PET_EGG, "PET", "PET_RED_PANDA3", .25, 1),

    GiraffePet4M(DropTableTypes.MYTHICAL_PET_EGG, "PET", "PET_GIRAFFE4", .35, 1),
    TurtlePet4M(DropTableTypes.MYTHICAL_PET_EGG, "PET", "PET_TURTLE4", .35, 1),
    ElephantPet4M(DropTableTypes.MYTHICAL_PET_EGG, "PET", "PET_ELEPHANT4", .35, 1),
    GorillaPet4M(DropTableTypes.MYTHICAL_PET_EGG, "PET", "PET_GORILLA4", .35, 1),
    FrogPet4M(DropTableTypes.MYTHICAL_PET_EGG, "PET", "PET_FROG4", .35, 1),
    LionPet4M(DropTableTypes.MYTHICAL_PET_EGG, "PET", "PET_LION4", .35, 1),

    PenguinPet4M(DropTableTypes.MYTHICAL_PET_EGG, "PET", "PET_PENGUIN4", .175, 1),
    RedPandaPet4M(DropTableTypes.MYTHICAL_PET_EGG, "PET", "PET_RED_PANDA4", .175, 1),

    GiraffePet5M(DropTableTypes.MYTHICAL_PET_EGG, "PET", "PET_GIRAFFE5", .1, 1),
    TurtlePet5M(DropTableTypes.MYTHICAL_PET_EGG, "PET", "PET_TURTLE5", .1, 1),
    ElephantPet5M(DropTableTypes.MYTHICAL_PET_EGG, "PET", "PET_ELEPHANT5", .1, 1),
    GorillaPet5M(DropTableTypes.MYTHICAL_PET_EGG, "PET", "PET_GORILLA5", .1, 1),
    FrogPetM(DropTableTypes.MYTHICAL_PET_EGG, "PET", "PET_FROG5", .1, 1),
    LionPet5M(DropTableTypes.MYTHICAL_PET_EGG, "PET", "PET_LION5", .1, 1),

    RedPandaPet5M(DropTableTypes.MYTHICAL_PET_EGG, "PET", "PET_RED_PANDA5", .05, 1),
    PenguinPet5M(DropTableTypes.MYTHICAL_PET_EGG, "PET", "PET_PENGUIN5", .05, 1),

    GiraffePet6M(DropTableTypes.MYTHICAL_PET_EGG, "PET", "PET_GIRAFFE6", .000002, 1),
    TurtlePet6M(DropTableTypes.MYTHICAL_PET_EGG, "PET", "PET_TURTLE6", .000002, 1),
    ElephantPet6M(DropTableTypes.MYTHICAL_PET_EGG, "PET", "PET_ELEPHANT6", .000002, 1),
    GorillaPet6M(DropTableTypes.MYTHICAL_PET_EGG, "PET", "PET_GORILLA6", .000002, 1),
    FrogPet6M(DropTableTypes.MYTHICAL_PET_EGG, "PET", "PET_FROG6", .000002, 1),
    LionPet6M(DropTableTypes.MYTHICAL_PET_EGG, "PET", "PET_LION6", .000002, 1),

    RedPandaPet6M(DropTableTypes.MYTHICAL_PET_EGG, "PET", "PET_RED_PANDA6", .000001, 1),
    PenguinPet6M(DropTableTypes.MYTHICAL_PET_EGG, "PET", "PET_PENGUIN6", .000001, 1),

    // GODLY
    GiraffePet4G(DropTableTypes.GODLY_PET_EGG, "PET", "PET_GIRAFFE4", .70, 1),
    TurtlePet4G(DropTableTypes.GODLY_PET_EGG, "PET", "PET_TURTLE4", .70, 1),
    ElephantPet4G(DropTableTypes.GODLY_PET_EGG, "PET", "PET_ELEPHANT4", .70, 1),
    GorillaPet4G(DropTableTypes.GODLY_PET_EGG, "PET", "PET_GORILLA4", .70, 1),
    FrogPet4G(DropTableTypes.GODLY_PET_EGG, "PET", "PET_FROG4", .70, 1),
    LionPet4G(DropTableTypes.GODLY_PET_EGG, "PET", "PET_LION4", .70, 1),

    PenguinPet4G(DropTableTypes.GODLY_PET_EGG, "PET", "PET_PENGUIN4", .35, 1),
    RedPandaPet4G(DropTableTypes.GODLY_PET_EGG, "PET", "PET_RED_PANDA4", .35, 1),

    GiraffePet5G(DropTableTypes.GODLY_PET_EGG, "PET", "PET_GIRAFFE5", .5, 1),
    TurtlePet5G(DropTableTypes.GODLY_PET_EGG, "PET", "PET_TURTLE5", .5, 1),
    ElephantPet5G(DropTableTypes.GODLY_PET_EGG, "PET", "PET_ELEPHANT5", .5, 1),
    GorillaPet5G(DropTableTypes.GODLY_PET_EGG, "PET", "PET_GORILLA5", .5, 1),
    FrogPetG(DropTableTypes.GODLY_PET_EGG, "PET", "PET_FROG5", .5, 1),
    LionPet5G(DropTableTypes.GODLY_PET_EGG, "PET", "PET_LION5", .5, 1),

    RedPandaPet5G(DropTableTypes.GODLY_PET_EGG, "PET", "PET_RED_PANDA5", .25, 1),
    PenguinPet5G(DropTableTypes.GODLY_PET_EGG, "PET", "PET_PENGUIN5", .25, 1),

    GiraffePet6G(DropTableTypes.GODLY_PET_EGG, "PET", "PET_GIRAFFE6", .0001, 1),
    TurtlePet6G(DropTableTypes.GODLY_PET_EGG, "PET", "PET_TURTLE6", .0001, 1),
    ElephantPet6G(DropTableTypes.GODLY_PET_EGG, "PET", "PET_ELEPHANT6", .0001, 1),
    GorillaPet6G(DropTableTypes.GODLY_PET_EGG, "PET", "PET_GORILLA6", .0001, 1),
    FrogPet6G(DropTableTypes.GODLY_PET_EGG, "PET", "PET_FROG6", .0001, 1),
    LionPet6G(DropTableTypes.GODLY_PET_EGG, "PET", "PET_LION6", .0001, 1),

    RedPandaPet6G(DropTableTypes.GODLY_PET_EGG, "PET", "PET_RED_PANDA6", .00005, 1),
    PenguinPet6G(DropTableTypes.GODLY_PET_EGG, "PET", "PET_PENGUIN6", .00005, 1),

    // Phoenix Rare
    PhoenixPet3P(DropTableTypes.PHOENIX_PET_EGG, "PET", "PET_PHOENIX3", .95, 1),
    PhoenixPet4P(DropTableTypes.PHOENIX_PET_EGG, "PET", "PET_PHOENIX4", .045, 1),
    PhoenixPet5P(DropTableTypes.PHOENIX_PET_EGG, "PET", "PET_PHOENIX5", .005, 1),

    // Phoenix Legendary
    PhoenixPet3P2(DropTableTypes.PHOENIX_PET_EGG2, "PET", "PET_PHOENIX3", .925, 1),
    PhoenixPet4P2(DropTableTypes.PHOENIX_PET_EGG2, "PET", "PET_PHOENIX4", .0675, 1),
    PhoenixPet5P2(DropTableTypes.PHOENIX_PET_EGG2, "PET", "PET_PHOENIX5", .0075, 1),

    // Dragon Rare
    DragonPet3D(DropTableTypes.DRAGON_PET_EGG, "PET", "PET_DRAGON3", .95, 1),
    DragonPet4D(DropTableTypes.DRAGON_PET_EGG, "PET", "PET_DRAGON4", .045, 1),
    DragonPet5D(DropTableTypes.DRAGON_PET_EGG, "PET", "PET_DRAGON5", .005, 1),

    // Dragon Legendary
    DragonPet3D2(DropTableTypes.DRAGON_PET_EGG2, "PET", "PET_DRAGON3", .925, 1),
    DragonPet4D2(DropTableTypes.DRAGON_PET_EGG2, "PET", "PET_DRAGON4", .0675, 1),
    DragonPet5D2(DropTableTypes.DRAGON_PET_EGG2, "PET", "PET_DRAGON5", .0075, 1),

    /*
     *
     *   LOOTBOXES
     *
     */
    MoneyVoucherC(DropTableTypes.COMMON_LOOTBOX, "VOUCHER", "MONEY_VOUCHER", .5, 1),
    EXPVoucherC(DropTableTypes.COMMON_LOOTBOX, "VOUCHER", "EXP_VOUCHER", .5, 1),
    PetEXPVoucherC(DropTableTypes.COMMON_LOOTBOX, "VOUCHER", "PET_EXP_VOUCHER", .5, 1),
    BreakingGemC(DropTableTypes.COMMON_LOOTBOX, "GEM_STONE", "BREAKING_GEM", .5, 1),
    BlockGemC(DropTableTypes.COMMON_LOOTBOX, "GEM_STONE", "BLOCK_GEM", .5, 1),

    BreakingGem2C(DropTableTypes.COMMON_LOOTBOX, "GEM_STONE", "BREAKING_GEM2", .25, 1),
    BlockGem2C(DropTableTypes.COMMON_LOOTBOX, "GEM_STONE", "BLOCK_GEM2", .25, 1),

    PetEggC(DropTableTypes.COMMON_LOOTBOX, "PET", "PET_EGG", .2, 1),

    SellBoosterC(DropTableTypes.COMMON_LOOTBOX, "BOOSTER", "SELL_BOOSTER", .1, 1),
    EXPBoosterC(DropTableTypes.COMMON_LOOTBOX, "BOOSTER", "EXP_BOOSTER", .1, 1),
    PetEXPBoosterC(DropTableTypes.COMMON_LOOTBOX, "BOOSTER", "PET_EXP_BOOSTER", .1, 1),
    PetEgg2C(DropTableTypes.COMMON_LOOTBOX, "PET", "PET_EGG2", .1, 1),

    BreakingGem3C(DropTableTypes.COMMON_LOOTBOX, "GEM_STONE", "BREAKING_GEM3", .05, 1),
    BlockGem3C(DropTableTypes.COMMON_LOOTBOX, "GEM_STONE", "BLOCK_GEM3", .05, 1),

    PetEgg3C(DropTableTypes.COMMON_LOOTBOX, "PET", "PET_EGG3", .01, 1),
    //  UNCOMMON
    MoneyVoucher2U(DropTableTypes.UNCOMMON_LOOTBOX, "VOUCHER", "MONEY_VOUCHER2", .5, 1),
    EXPVoucher2U(DropTableTypes.UNCOMMON_LOOTBOX, "VOUCHER", "EXP_VOUCHER2", .5, 1),
    PetEXPVoucher2U(DropTableTypes.UNCOMMON_LOOTBOX, "VOUCHER", "PET_EXP_VOUCHER2", .5, 1),


    BreakingGem2U(DropTableTypes.UNCOMMON_LOOTBOX, "GEM_STONE", "BREAKING_GEM2", .30, 1),
    BlockGem2U(DropTableTypes.UNCOMMON_LOOTBOX, "GEM_STONE", "BLOCK_GEM2", .30, 1),

    PetEggU(DropTableTypes.UNCOMMON_LOOTBOX, "PET", "PET_EGG", .2, 1),
    PetEgg2U(DropTableTypes.UNCOMMON_LOOTBOX, "PET", "PET_EGG2", .2, 1),

    SellBoosterU(DropTableTypes.UNCOMMON_LOOTBOX, "BOOSTER", "SELL_BOOSTER", .15, 1),
    EXPBoosterU(DropTableTypes.UNCOMMON_LOOTBOX, "BOOSTER", "EXP_BOOSTER", .15, 1),
    PetEXPBoosterU(DropTableTypes.UNCOMMON_LOOTBOX, "BOOSTER", "PET_EXP_BOOSTER", .15, 1),


    BreakingGem3U(DropTableTypes.UNCOMMON_LOOTBOX, "GEM_STONE", "BREAKING_GEM3", .1, 1),
    BlockGem3U(DropTableTypes.UNCOMMON_LOOTBOX, "GEM_STONE", "BLOCK_GEM3", .1, 1),

    PetEgg3U(DropTableTypes.UNCOMMON_LOOTBOX, "PET", "PET_EGG3", .02, 1),
    BreakingGem4U(DropTableTypes.UNCOMMON_LOOTBOX, "GEM_STONE", "BREAKING_GEM4", .02, 1),
    BlockGem4U(DropTableTypes.UNCOMMON_LOOTBOX, "GEM_STONE", "BLOCK_GEM4", .02, 1),

    PetEgg4U(DropTableTypes.UNCOMMON_LOOTBOX, "PET", "PET_EGG4", .002, 1),
    //  RARE
    MoneyVoucher3R(DropTableTypes.RARE_LOOTBOX, "VOUCHER", "MONEY_VOUCHER3", .5, 1),
    EXPVoucher3R(DropTableTypes.RARE_LOOTBOX, "VOUCHER", "EXP_VOUCHER3", .5, 1),
    PetEXPVoucher3R(DropTableTypes.RARE_LOOTBOX, "VOUCHER", "PET_EXP_VOUCHER3", .5, 1),


    BreakingGem2R(DropTableTypes.RARE_LOOTBOX, "GEM_STONE", "BREAKING_GEM2", .25, 1),
    BlockGem2R(DropTableTypes.RARE_LOOTBOX, "GEM_STONE", "BLOCK_GEM2", .25, 1),

    SellBooster2R(DropTableTypes.RARE_LOOTBOX, "BOOSTER", "SELL_BOOSTER2", .15, 1),
    EXPBooster2R(DropTableTypes.RARE_LOOTBOX, "BOOSTER", "EXP_BOOSTER2", .15, 1),
    PetEXPBooster2R(DropTableTypes.RARE_LOOTBOX, "BOOSTER", "PET_EXP_BOOSTER2", .15, 1),

    BreakingGem3R(DropTableTypes.RARE_LOOTBOX, "GEM_STONE", "BREAKING_GEM3", .15, 1),
    BlockGem3R(DropTableTypes.RARE_LOOTBOX, "GEM_STONE", "BLOCK_GEM3", .15, 1),

    PetEgg2R(DropTableTypes.RARE_LOOTBOX, "PET", "PET_EGG2", .15, 1),
    PetEgg3R(DropTableTypes.RARE_LOOTBOX, "PET", "PET_EGG3", .15, 1),

    BreakingGem4R(DropTableTypes.RARE_LOOTBOX, "GEM_STONE", "BREAKING_GEM4", .05, 1),
    BlockGem4R(DropTableTypes.RARE_LOOTBOX, "GEM_STONE", "BLOCK_GEM4", .05, 1),

    BreakingGem5R(DropTableTypes.RARE_LOOTBOX, "GEM_STONE", "BREAKING_GEM5", .01, 1),
    BlockGem5R(DropTableTypes.RARE_LOOTBOX, "GEM_STONE", "BLOCK_GEM5", .01, 1),
    PetEgg4R(DropTableTypes.RARE_LOOTBOX, "PET", "PET_EGG4", .01, 1),
    // LEGENDARY
    MoneyVoucher4L(DropTableTypes.LEGENDARY_LOOTBOX, "VOUCHER", "MONEY_VOUCHER4", .5, 1),
    EXPVoucher4L(DropTableTypes.LEGENDARY_LOOTBOX, "VOUCHER", "EXP_VOUCHER4", .5, 1),
    PetEXPVoucher4L(DropTableTypes.LEGENDARY_LOOTBOX, "VOUCHER", "PET_EXP_VOUCHER4", .5, 1),

    PetEgg3L(DropTableTypes.LEGENDARY_LOOTBOX, "PET", "PET_EGG3", .25, 1),

    BreakingGem4L(DropTableTypes.LEGENDARY_LOOTBOX, "GEM_STONE", "BREAKING_GEM4", .2, 1),

    SellBooster3L(DropTableTypes.LEGENDARY_LOOTBOX, "BOOSTER", "SELL_BOOSTER3", .15, 1),
    EXPBooster3L(DropTableTypes.LEGENDARY_LOOTBOX, "BOOSTER", "EXP_BOOSTER3", .15, 1),
    PetEXPBooster3L(DropTableTypes.LEGENDARY_LOOTBOX, "BOOSTER", "PET_EXP_BOOSTER3", .15, 1),

    BreakingGem5L(DropTableTypes.LEGENDARY_LOOTBOX, "GEM_STONE", "BREAKING_GEM5", .1, 1),

    BlockGem4L(DropTableTypes.LEGENDARY_LOOTBOX, "GEM_STONE", "BLOCK_GEM4", .075, 1),

    PetEgg4L(DropTableTypes.LEGENDARY_LOOTBOX, "PET", "PET_EGG4", .05, 1),

    BreakingGem6L(DropTableTypes.LEGENDARY_LOOTBOX, "GEM_STONE", "BREAKING_GEM6", .03, 1),

    BlockGem5L(DropTableTypes.LEGENDARY_LOOTBOX, "GEM_STONE", "BLOCK_GEM5", .02, 1),

    PetEgg5L(DropTableTypes.LEGENDARY_LOOTBOX, "PET", "PET_EGG5", .01, 1),
    BlockGem6L(DropTableTypes.LEGENDARY_LOOTBOX, "GEM_STONE", "BLOCK_GEM6", .01, 1),
    LootBox4L(DropTableTypes.LEGENDARY_LOOTBOX, "CONSUMABLE", "LOOTBOX4", .01, 1),

    LootBox5L(DropTableTypes.LEGENDARY_LOOTBOX, "CONSUMABLE", "LOOTBOX5", .001, 1),
    //  EXOTIC
    MoneyVoucher5E(DropTableTypes.EXOTIC_LOOTBOX, "VOUCHER", "MONEY_VOUCHER5", .5, 1),
    EXPVoucher5E(DropTableTypes.EXOTIC_LOOTBOX, "VOUCHER", "EXP_VOUCHER5", .5, 1),
    PetEXPVoucher5E(DropTableTypes.EXOTIC_LOOTBOX, "VOUCHER", "PET_EXP_VOUCHER5", .5, 1),

    PetEgg3E(DropTableTypes.EXOTIC_LOOTBOX, "PET", "PET_EGG3", .25, 1),

    BreakingGem5E(DropTableTypes.EXOTIC_LOOTBOX, "GEM_STONE", "BREAKING_GEM5", .25, 1),
    BlockGem5E(DropTableTypes.EXOTIC_LOOTBOX, "GEM_STONE", "BLOCK_GEM5", .25, 1),

    SellBooster4E(DropTableTypes.EXOTIC_LOOTBOX, "BOOSTER", "SELL_BOOSTER4", .15, 1),
    EXPBooster4E(DropTableTypes.EXOTIC_LOOTBOX, "BOOSTER", "EXP_BOOSTER4", .15, 1),
    PetEXPBooster4E(DropTableTypes.EXOTIC_LOOTBOX, "BOOSTER", "PET_EXP_BOOSTER4", .15, 1),

    PetEgg4E(DropTableTypes.EXOTIC_LOOTBOX, "PET", "PET_EGG4", .15, 1),

    BreakingGem6E(DropTableTypes.EXOTIC_LOOTBOX, "GEM_STONE", "BREAKING_GEM6", .15, 1),
    BlockGem6E(DropTableTypes.EXOTIC_LOOTBOX, "GEM_STONE", "BLOCK_GEM6", .15, 1),

    PetEgg5E(DropTableTypes.EXOTIC_LOOTBOX, "PET", "PET_EGG5", .05, 1),

    LootBox5E(DropTableTypes.EXOTIC_LOOTBOX, "CONSUMABLE", "LOOTBOX5", .05, 5),

    BreakingGem7E(DropTableTypes.EXOTIC_LOOTBOX, "GEM_STONE", "BREAKING_GEM7", .01, 1),
    BlockGem7E(DropTableTypes.EXOTIC_LOOTBOX, "GEM_STONE", "BLOCK_GEM7", .01, 1),

    LootBox6E(DropTableTypes.EXOTIC_LOOTBOX, "CONSUMABLE", "LOOTBOX6", .002, 1),

    LootBox7E(DropTableTypes.EXOTIC_LOOTBOX, "CONSUMABLE", "LOOTBOX7", .0001, 1),
    // MYTHICAL
    MoneyVoucher6M(DropTableTypes.MYTHICAL_LOOTBOX, "VOUCHER", "MONEY_VOUCHER6", .3, 1),
    EXPVoucher6M(DropTableTypes.MYTHICAL_LOOTBOX, "VOUCHER", "EXP_VOUCHER6", .3, 1),
    PetEXPVoucher6M(DropTableTypes.MYTHICAL_LOOTBOX, "VOUCHER", "PET_EXP_VOUCHER6", .3, 1),

    SellBooster5M(DropTableTypes.MYTHICAL_LOOTBOX, "BOOSTER", "SELL_BOOSTER5", .3, 1),
    EXPBooster5M(DropTableTypes.MYTHICAL_LOOTBOX, "BOOSTER", "EXP_BOOSTER5", .3, 1),
    PetEXPBooster5M(DropTableTypes.MYTHICAL_LOOTBOX, "BOOSTER", "PET_EXP_BOOSTER5", .3, 1),

    PetEgg4M(DropTableTypes.MYTHICAL_LOOTBOX, "PET", "PET_EGG4", .25, 1),

    PetEgg5M(DropTableTypes.MYTHICAL_LOOTBOX, "PET", "PET_EGG5", .15, 1),

    LootBox6M(DropTableTypes.MYTHICAL_LOOTBOX, "CONSUMABLE", "LOOTBOX6", .15, 1),

    LootBox7M(DropTableTypes.MYTHICAL_LOOTBOX, "CONSUMABLE", "LOOTBOX7", .1, 1),
    // GODLY
    SellBooster6G(DropTableTypes.GODLY_LOOTBOX, "BOOSTER", "SELL_BOOSTER6", .3, 1),
    EXPBooster6G(DropTableTypes.GODLY_LOOTBOX, "BOOSTER", "EXP_BOOSTER6", .3, 1),
    PetEXPBooster6G(DropTableTypes.GODLY_LOOTBOX, "BOOSTER", "PET_EXP_BOOSTER6", .3, 1),

    PetEgg4G(DropTableTypes.GODLY_LOOTBOX, "PET", "PET_EGG4", .3, 1),

    PetEgg5G(DropTableTypes.GODLY_LOOTBOX, "PET", "PET_EGG5", .2, 1),

    LootBox6G(DropTableTypes.GODLY_LOOTBOX, "CONSUMABLE", "LOOTBOX6", .15, 3),

    LootBox7G(DropTableTypes.GODLY_LOOTBOX, "CONSUMABLE", "LOOTBOX7", .1, 2),

    PetEgg6G(DropTableTypes.GODLY_LOOTBOX, "PET", "PET_EGG6", .01, 1),
    PetEgg7G(DropTableTypes.GODLY_LOOTBOX, "PET", "PET_EGG7", .01, 1),

    /*
     *
     *
     *   VOTE?
     *
     *
     */

    VOTE1(DropTableTypes.VOTE, "VOUCHER", "MONEY_VOUCHER2", .5, 1),
    VOTE2(DropTableTypes.VOTE, "VOUCHER", "EXP_VOUCHER2", .5, 1),
    VOTE3(DropTableTypes.VOTE, "VOUCHER", "PET_EXP_VOUCHER2", .5, 1),

    VOTE4(DropTableTypes.VOTE, "BOOSTER", "SELL_BOOSTER2", .15, 1),
    VOTE5(DropTableTypes.VOTE, "BOOSTER", "EXP_BOOSTER2", .15, 1),
    VOTE6(DropTableTypes.VOTE, "BOOSTER", "PET_EXP_BOOSTER2", .15, 1),

    VOTE7(DropTableTypes.VOTE, "PET", "PET_EGG2", .15, 1),

    VOTE8(DropTableTypes.VOTE, "PET", "PET_EGG3", .1, 1),

    VOTE9(DropTableTypes.VOTE, "PET", "PET_EGG4", .05, 1),

    VOTE10(DropTableTypes.VOTE, "CONSUMABLE", "CONSUMABLE_LOOTBOX5", .01, 1),

    /*
     *
     *   LLAMA
     *
     *
     * */

    Lootbox(DropTableTypes.LLAMA, "CONSUMABLE", "LOOTBOX3", .70, 1),
    MoneyVoucher(DropTableTypes.LLAMA, "VOUCHER", "MONEY_VOUCHER3", .65, 1),
    LlamaGemStone(DropTableTypes.LLAMA, "GEM_STONE", "LLAMA_GEM", .60, 1),
    LlamaGemStone2(DropTableTypes.LLAMA, "GEM_STONE", "LLAMA_GEM2", .50, 1),
    EXPVoucher(DropTableTypes.LLAMA, "VOUCHER", "EXP_VOUCHER4", .45, 1),
    Lootbox2(DropTableTypes.LLAMA, "CONSUMABLE", "LOOTBOX4", .4, 1),
    LlamaGemStone3(DropTableTypes.LLAMA, "GEM_STONE", "LLAMA_GEM3", .4, 1),
    Lootbox3(DropTableTypes.LLAMA, "CONSUMABLE", "LOOTBOX5", .25, 1),
    LlamaGemStone4(DropTableTypes.LLAMA, "GEM_STONE", "LLAMA_GEM4", .25, 1),
    PetEXPVoucher(DropTableTypes.LLAMA, "VOUCHER", "PET_EXP_VOUCHER5", .1, 1),
    LlamaGemStone5(DropTableTypes.LLAMA, "GEM_STONE", "LLAMA_GEM5", .05, 1),

    /*
     *
     *   TREASURE
     *
     * */

    MoneyVoucher2C(DropTableTypes.TREASURE, "VOUCHER", "MONEY_VOUCHER2", .5, 1),
    EXPVoucher2C(DropTableTypes.TREASURE, "VOUCHER", "EXP_VOUCHER2", .5, 1),
    PetEXPVoucher2C(DropTableTypes.TREASURE, "VOUCHER", "PET_EXP_VOUCHER2", .5, 1),

    MoneyVoucher3C(DropTableTypes.TREASURE, "VOUCHER", "MONEY_VOUCHER3", .20, 1),
    EXPVoucher3C(DropTableTypes.TREASURE, "VOUCHER", "EXP_VOUCHER3", .20, 1),
    PetEXPVoucher3C(DropTableTypes.TREASURE, "VOUCHER", "PET_EXP_VOUCHER3", .20, 1),

    MoneyVoucher4C(DropTableTypes.TREASURE, "VOUCHER", "MONEY_VOUCHER4", .05, 1),
    EXPVoucher4C(DropTableTypes.TREASURE, "VOUCHER", "EXP_VOUCHER4", .05, 1),
    PetEXPVoucher4C(DropTableTypes.TREASURE, "VOUCHER", "PET_EXP_VOUCHER4", .05, 1),

    MoneyVoucher5C(DropTableTypes.TREASURE, "VOUCHER", "MONEY_VOUCHER5", .005, 1),
    EXPVoucher5C(DropTableTypes.TREASURE, "VOUCHER", "EXP_VOUCHER5", .005, 1),
    PetEXPVoucher5C(DropTableTypes.TREASURE, "VOUCHER", "PET_EXP_VOUCHER5", .005, 1);


    private static final Map<DropTableTypes, List<DropTables>> DROP_TABLE_LIST = new EnumMap<>(DropTableTypes.class);

    public static List<DropTables> getEggs(DropTableTypes rarity) {
        return DROP_TABLE_LIST.computeIfAbsent(rarity, key -> Arrays.stream(values())
                .filter(dropTable -> dropTable.getDropTable() == key)
                .collect(Collectors.toList()));
    }

    private final String type;
    private final DropTableTypes dropTable;
    private final String id;
    private final double weight;
    private final int amount;

    DropTables(DropTableTypes dropTable, String type, String id, double weight, int amount) {
        this.dropTable = dropTable;
        this.id = id;
        this.type = type;
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

    public DropTableTypes getDropTable() {
        return dropTable;
    }

    DecimalFormat df = new DecimalFormat("#.####");

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
            lore.add(Component.text("CHANCE: " + df.format(weight * 100) + "%", NamedTextColor.GOLD, TextDecoration.BOLD));
            itemStack.lore(lore);
//            Should probably remove NBT in case someone is able to steal an item from a GUI, the item would have no effect
            return itemStack.asQuantity(amount);
        }
        return null;
    }

    @Override
    public ItemStack generateItem(Player player) {
        return null;
    }
}
