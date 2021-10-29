package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.mainMenu.quests.enums;

import monzter.adventurescraft.plugin.utilities.enums.AdventureStatsDisplay;
import org.bukkit.ChatColor;

public enum QuestList {
    //  Tutorial

    ACTIVE_QUESTS_JOURNAL(QuestGiver.TUTOR,
            new String[]{ChatColor.GREEN + "Right-Click " + ChatColor.WHITE + "while holding the " + ChatColor.BOLD + "Quest Journal"},
            null, null, 100, null, 100),
    ACTIVE_QUESTS_MENU(QuestGiver.TUTOR,
            new String[]{ChatColor.GREEN + "Left-Click " + ChatColor.WHITE + "while holding the " + ChatColor.BOLD + "Quest Journal", ChatColor.WHITE + "and looking at a " + ChatColor.GREEN + "Block"},
            null, null, 100, null, 100),
    UNCLAIMED_QUESTS_MENU(QuestGiver.TUTOR,
            new String[]{ChatColor.GREEN + "Crouch and Left-Click " + ChatColor.WHITE + "while holding the", ChatColor.WHITE.toString() + ChatColor.BOLD + "Quest Journal " + ChatColor.WHITE + "and looking at a " + ChatColor.GREEN + "Block"},
            null, null, 100, null, 100),
    FOLLOW_THE_ARROW(QuestGiver.TUTOR,
            new String[]{ChatColor.WHITE + "Follow the " + ChatColor.GREEN + "Arrow " + ChatColor.WHITE + "guiding ", ChatColor.WHITE + "you towards the next " + ChatColor.GOLD + "Quester"},
            null, null, 100, null, 100),

    MENU_OPENER(QuestGiver.GUIDER,
            new String[]{"&aClick &fwhile holding the", "&aMenu &fitem to open the &aMain Menu"},
            null, null, 100, null, 100),
    COMBAT_INSTRUCTOR(QuestGiver.GUIDER,
            new String[]{"&aFollow &fthe &aArrow &fguiding you", "&ftowards the &6Combat Instructor"},
            null, null, 100, null, 100),

    DAGGERS(QuestGiver.COMBAT_INSTRUCTOR,
            new String[]{"&aGrab &fthe &aTutorial Dagger &foff", "&fthe wall behind the &aCombat Instructor"},
            null, null, 100, null, 100),
    DAGGER_COMBAT(QuestGiver.COMBAT_INSTRUCTOR,
            new String[]{"&aSlay &fone of the &cDummies &finfront of the", "&aCombat Instructor &fwith the &aTutorial Dagger"},
            null, null, 100, null, 100),
    SWORDS(QuestGiver.COMBAT_INSTRUCTOR,
            new String[]{"&aGrab &fthe &aTutorial Sword &foff", "&fthe wall behind the &aCombat Instructor"},
            null, null, 100, null, 100),
    SWORD_COMBAT(QuestGiver.COMBAT_INSTRUCTOR,
            new String[]{"&aSlay &fone of the &cDummies &finfront of the", "&aCombat Instructor &fwith the &aTutorial Sword"},
            null, null, 100, null, 100),
    AXES(QuestGiver.COMBAT_INSTRUCTOR,
            new String[]{"&aGrab &fthe &aTutorial Axe &foff", "&fthe wall behind the &aCombat Instructor"},
            null, null, 100, null, 100),
    AXE_COMBAT(QuestGiver.COMBAT_INSTRUCTOR,
            new String[]{"&aSlay &fone of the &cDummies &finfront of the", "&aCombat Instructor &fwith the &aTutorial Axe"},
            null, null, 100, null, 100),
    STAFFS(QuestGiver.COMBAT_INSTRUCTOR,
            new String[]{"&aGrab &fthe &aTutorial Staff &foff", "&fthe wall behind the &aCombat Instructor"},
            null, null, 100, null, 100),
    STAFF_COMBAT(QuestGiver.COMBAT_INSTRUCTOR,
            new String[]{"&aSlay &fone of the &cDummies &finfront of the", "&aCombat Instructor &fwith the &aTutorial Staff"},
            null, null, 100, null, 100),
    ATTRIBUTOR(QuestGiver.COMBAT_INSTRUCTOR,
            new String[]{"&aFollow &fthe &aArrow &fguiding you", "&ftowards the &6Attributor"},
            null, null, 100, null, 100),

    ATTRIBUTES(QuestGiver.ATTRIBUTOR,
            new String[]{"&aUse &fthe &a/Attributes &fcommand", "&fto view the &aAttribute Trees"},
            null, null, 100, null, 100),
    REESE(QuestGiver.ATTRIBUTOR,
            new String[]{"&aFollow &fthe &aArrow &fguiding you", "&ftowards &6Reese"},
            null, null, 100, null, 100),

    COLLECT_A_RESOURCE(QuestGiver.REESE,
            new String[]{"&aHarvest &fand &apickup &fa piece of &aWheat"},
            null, null, 100, null, 100),
    RESOURCE_COLLECTOR(QuestGiver.REESE,
            new String[]{"&aUse &fthe &a/ResourceCollector &fcommand", "&fto view the &aCollector&f, then click the", "&aDiamond Hoe &fnamed &6Farming Resources", "&fto view and withdraw your stored &aWheat"},
            null, null, 100, null, 100),
    CRAFTER(QuestGiver.REESE,
            new String[]{"&aFollow &fthe &aArrow &fguiding", "&fyou towards &6Crafter"},
            null, null, 100, null, 100),

    MATERIAL_SHOP(QuestGiver.CRAFTER,
            new String[]{"&aAccess &fthe &aMaterial Shop &fby using", "&a/Materials &for &aShift-Right Clicking", "&fthe &aCrafting Table"},
            null, null, 100, null, 100),
    ENCHANTED_CRAFTER(QuestGiver.CRAFTER,
            new String[]{"&aCombine &fthe &a288 Seeds &finto an", "&9Enchanted Seed &fwithin the &aMaterial Shop"},
            null, null, 100, null, 100),
    HOMER(QuestGiver.CRAFTER,
            new String[]{"&aFollow &fthe &aArrow &fguiding", "&fyou towards &6Homer"},
            null, null, 100, null, 100),

    GOING_HOME(QuestGiver.HOMER,
            new String[]{"&aTravel &fto your &aHome &fby using &a/Home"},
            null, null, 100, null, 100),

    //  Graveyard

    HUNTING_THE_UNDEAD(QuestGiver.CIRL,
            new String[]{"&aSlay &f%betonquest_default-Graveyard-Cirl:point.HUNTING_THE_UNDEAD.amount%/20 &cUndead Skeletons", "&fwithin the &cGraveyard"},
            null, new String[]{"ARMOR BONE_SKULL2 1"}, 100, new String[]{AdventureStatsDisplay.SLAYER.getName() + ",100"}, 100),
    THE_SILVER_STOOL(QuestGiver.CIRL,
            new String[]{"&aUse &fthe &c&lSilver Stool &f(-157, 15, 145)", "&fwithin the &cGraveyard &fto", "&aUpgrade &fyour &lSilver Sword"},
            null, new String[]{"ARMOR BONE_CHESTPLATE2 1", "ARMOR BONE_LEGS2 1"}, 100, new String[]{AdventureStatsDisplay.SLAYER.getName() + ",100"}, 100),
    ARCHER_ANNIHILATOR(QuestGiver.CIRL,
            new String[]{"&aSlay &f%betonquest_default-Graveyard-Cirl:point.ARCHER_ANNIHILATOR.amount%/15 &cUndead Archers &fwithin the &cGraveyard"},
            null, new String[]{"ARMOR BONE_FEET2 1"}, 100, new String[]{AdventureStatsDisplay.SLAYER.getName() + ",100"}, 100),
    THE_REAPER(QuestGiver.CIRL,
            new String[]{"&aSlay &fthe &cReaper &f(-96, 15, 176)"},
            null, new String[]{"DAGGER REAPER_BLADE4 1"}, 100, new String[]{AdventureStatsDisplay.SLAYER.getName() + ",100"}, 100),
    COURTYARD_VENTURE(QuestGiver.CIRL,
            new String[]{"&aFollow &fthe &aGrass Path", "&fto enter the &cCourtyard &f(-111, 15, 248)"},
            null, null, 100, new String[]{AdventureStatsDisplay.SLAYER.getName() + ",100"}, 100),

    //  Courtyard

    THE_UNDEAD_SLAYER(QuestGiver.NAVID,
            new String[]{"&aSlay &f%betonquest_default-Courtyard-Navid:point.THE_UNDEAD_SLAYER.amount%/50 &cUndead &fwithin the &cCourtyard"},
            null, new String[]{"BOW UNDEAD_BOW3 1"}, 200, new String[]{AdventureStatsDisplay.SLAYER.getName() + ",200"}, 150),
    THE_MAGICAL_HUNT(QuestGiver.NAVID,
            new String[]{"&aSlay &cUndead Casters &fand &adeliver", "&f8 &lMagical Fragments &fto &aNavid"},
            null, new String[]{"CONSUMABLE MAGICAL_ESSENCE2 1"}, 200, new String[]{AdventureStatsDisplay.SLAYER.getName() + ",200"}, 150),
    SPIDER_EXTERMINATOR(QuestGiver.NAVID,
            new String[]{"&aSlay &f%betonquest_default-Courtyard-Navid:point.SPIDER_EXTERMINATOR.amount%/20 &cUndead Spiders &fwithin the &cCourtyard"},
            null, null, 200, new String[]{AdventureStatsDisplay.SLAYER.getName() + ",200"}, 150),
    THE_ALPHAS(QuestGiver.NAVID,
            new String[]{"&aSlay &f%betonquest_default-Courtyard-Navid:point.THE_ALPHAS.amount%/2 &cAlpha Souls &fwithin the &cCourtyard"},
            null, null, 200, new String[]{AdventureStatsDisplay.SLAYER.getName() + ",200"}, 150),
    THE_VILLAGE_HERO(QuestGiver.NAVID,
            new String[]{"&aRescue &f5 &aVillagers &ftrapped around the &cCourtyard"},
            null, null, 250, new String[]{AdventureStatsDisplay.SLAYER.getName() + ",250"}, 200),
    CASTLE_BREACH(QuestGiver.NAVID,
            new String[]{"&aBreach &fthe &cCastle &fby speaking with &aAnder", "&fat the &cCastle Gate &f(-95, 15, 434)"},
            null, null, 0, null, 0),

    SUMMONING_MORDEN(QuestGiver.KLAUS,
            new String[]{"&aSlay &f%betonquest_default-Castle-Klaus:point.SUMMONING_MORDEN.amount%/100 &cUndead &fwithin the &cCourtyard"},
            null, null, 0, null, 0),

    //  Forest

    OAK_TIMBER(QuestGiver.JENNY,
            new String[]{"&aChop &fand &adeliver &f%betonquest_default-Forest-Jenny:point.OAK_TIMBER.amount%/64 &aOak Logs", "&fwith a &lWooden Axe"},
            new String[]{"&aFast Travel to &2Oak Forest"}, null, 50, new String[]{AdventureStatsDisplay.FORAGING.getName() + ",50"}, 25),
    WORST_AXE(QuestGiver.JENNY,
            new String[]{"&aSpeak &fwith the &aLumberjack", "&fto upgrade your &aAxe"},
            null, null, 0, null, 0),
    SPRUCE_TIMBER(QuestGiver.JENNY,
            new String[]{"&aChop &fand &adeliver &f%betonquest_default-Forest-Jenny:point.SPRUCE_TIMBER.amount%/128 &aSpruce Logs", "&fwith a &lWooden Axe"},
            new String[]{"&aFast Travel to &2Spruce Forest"}, null, 100, new String[]{AdventureStatsDisplay.FORAGING.getName() + ",100"}, 50),
    POOR_AXE(QuestGiver.JENNY,
            new String[]{"&aSpeak &fwith the &aLumberjack", "&fto upgrade your &aAxe"},
            null, null, 0, null, 0),
    DARK_OAK_TIMBER(QuestGiver.JENNY,
            new String[]{"&aChop &fand &adeliver &f%betonquest_default-Forest-Jenny:point.DARK_OAK_TIMBER.amount%/192 &aDark Oak Logs", "&fwith a &lStone Axe"},
            new String[]{"&aFast Travel to &2Dark Oak Forest"}, null, 150, new String[]{AdventureStatsDisplay.FORAGING.getName() + ",150"}, 75),
    DECENT_AXE(QuestGiver.JENNY,
            new String[]{"&aSpeak &fwith the &aLumberjack", "&fto upgrade your &aAxe"},
            null, null, 0, null, 0),
    BIRCH_TIMBER(QuestGiver.JENNY,
            new String[]{"&aChop &fand &adeliver &f%betonquest_default-Forest-Jenny:point.BIRCH_TIMBER.amount%/256 &aBirch Logs", "&fwith a &lIron Axe"},
            new String[]{"&aFast Travel to &2Birch Forest"}, null, 200, new String[]{AdventureStatsDisplay.FORAGING.getName() + ",200"}, 100),
    BETTER_AXE(QuestGiver.JENNY,
            new String[]{"&aSpeak &fwith the &aLumberjack", "&fto upgrade your &aAxe"},
            null, null, 0, null, 0),
    ACACIA_TIMBER(QuestGiver.JENNY,
            new String[]{"&aChop &fand &adeliver &f%betonquest_default-Forest-Jenny:point.ACACIA_TIMBER.amount%/320 &aAcacia Logs", "&fwith a &lDiamond Axe"},
            new String[]{"&aFast Travel to &2Acacia Forest"}, null, 250, new String[]{AdventureStatsDisplay.FORAGING.getName() + ",250"}, 125),
    BEST_AXE(QuestGiver.JENNY,
            new String[]{"&aSpeak &fwith the &aLumberjack", "&fto upgrade your &aAxe"},
            null, null, 0, null, 0),
    JUNGLE_TIMBER(QuestGiver.JENNY,
            new String[]{"&aChop &fand &adeliver &f%betonquest_default-Forest-Jenny:point.JUNGLE_TIMBER.amount%/384 &aJungle Logs", "&fwith a &lGolden Axe"},
            new String[]{"&aFast Travel to &2Jungle Forest"}, null, 300, new String[]{AdventureStatsDisplay.FORAGING.getName() + ",300"}, 150),
    JACKS_JOURNEY(QuestGiver.JENNY,
            new String[]{"&aJourney &fup the &eHive &fto", "&arescue &aJack &fat (621, 119, -43)"},
            new String[]{"&aFast Travel to &eHive"}, null, 200, new String[]{AdventureStatsDisplay.FORAGING.getName() + ",200"}, 100),

    DONT_GET_STUNG(QuestGiver.BEAR,
            new String[]{"&aSlay &f%betonquest_default-Forest-Bear:point.DONT_GET_STUNG.amount%/15 &cBees"},
            null, null, 100, new String[]{AdventureStatsDisplay.FORAGING.getName() + ",100"}, 50),
    HARE_SAVIOR(QuestGiver.BEAR,
            new String[]{"&aRescue &f3 lost &aHares"},
            null, null, 300, new String[]{AdventureStatsDisplay.FORAGING.getName() + ",300"}, 150),
    HONEY_EXTRACTION(QuestGiver.BEAR,
            new String[]{"&aSmash &f%betonquest_default-Forest-Bear:point.HONEY_EXTRACTION.amount%/32 &aBee Nests", "&fto steal their &eHoney"},
            null, new String[]{"CONSUMABLE TASTY_HONEY3 1"}, 150, new String[]{AdventureStatsDisplay.FORAGING.getName() + ",150"}, 75),
    WASP_NEST(QuestGiver.BEAR,
            new String[]{"&aSlay &f%betonquest_default-Forest-Bear:point.WASP_NEST.amount%/15 &cWasps &faround the &2Acacia Forest"},
            null, null, 150, new String[]{AdventureStatsDisplay.FORAGING.getName() + ",150"}, 75),
    QUEEN_BEE(QuestGiver.BEAR,
            new String[]{"&aSlay &fa &cQueen Bee &fdeep within the &2Jungle Forest"},
            null, null, 300, new String[]{AdventureStatsDisplay.FORAGING.getName() + ",300"}, 150),

    CAT_CAPTURE(QuestGiver.CAT_LADY,
            new String[]{"&aLead &f%betonquest_default-Forest-Cat_Lady:point.CAT_CAPTURE.amount%/10 &aCats", "&fback to the &aCat Lady"},
            null, new String[]{"ACCESSORY CAT_CLAW2 1"}, 250, new String[]{AdventureStatsDisplay.FORAGING.getName() + ",250"}, 125),

    OAK_DRYAD_DESTROYER(QuestGiver.JACK,
            new String[]{"&aChop &f%betonquest_default-Forest-Jack:point.OAK_DRYAD_DESTROYER.amount%/320 &aOak Logs"},
            null, null, 0, null, 0),
    SPRUCE_DRYAD_DESTROYER(QuestGiver.JACK,
            new String[]{"&aChop &f%betonquest_default-Forest-Jack:point.SPRUCE_DRYAD_DESTROYER.amount%/320 &aSpruce Logs"},
            null, null, 0, null, 0),
    DARK_OAK_DRYAD_DESTROYER(QuestGiver.JACK,
            new String[]{"&aChop &f%betonquest_default-Forest-Jack:point.DARK_OAK_DRYAD_DESTROYER.amount%/320 &aDark Oak Logs"},
            null, null, 0, null, 0),
    BIRCH_DRYAD_DESTROYER(QuestGiver.JACK,
            new String[]{"&aChop &f%betonquest_default-Forest-Jack:point.BIRCH_DRYAD_DESTROYER.amount%/320 &aBirch Logs"},
            null, null, 0, null, 0),
    ACACIA_DRYAD_DESTROYER(QuestGiver.JACK,
            new String[]{"&aChop &f%betonquest_default-Forest-Jack:point.ACACIA_DRYAD_DESTROYER.amount%/320 &aAcacia Logs"},
            null, null, 0, null, 0),
    JUNGLE_DRYAD_DESTROYER(QuestGiver.JACK,
            new String[]{"&aChop &f%betonquest_default-Forest-Jack:point.JUNGLE_DRYAD_DESTROYER.amount%/320 &aJungle Logs"},
            null, null, 0, null, 0),

    //  Farm

    CUCCO_CATCH(QuestGiver.ANJU,
            new String[]{"&aGrab &fand &adeliver &f%betonquest_default-Farm-Anju:point.CUCCO_CATCH.amount%/20 &aCuccos", "&fwithin the &aFarm &fto &aAnju"},
            null, null, 250, new String[]{AdventureStatsDisplay.FARMING.getName() + ",250"}, 125),
    EGG_DELIVERY(QuestGiver.ANJU,
            new String[]{"&aDeliver &f25 &aEggs &fto &aAnju"},
            null, new String[]{"ACCESSORY SCRAMBLED_EGG 1"}, 250, new String[]{AdventureStatsDisplay.FARMING.getName() + ",250"}, 100),

    GRASS_GATHERER(QuestGiver.INGO,
            new String[]{"&aHarvest &f%betonquest_default-Farm-Ingo:point.GRASS_GATHERER.amount%/320 &aGrass &ffor &aEpona"},
            null, null, 200, new String[]{AdventureStatsDisplay.FARMING.getName() + ",200"}, 100),
    CURING_THE_HUNGER(QuestGiver.INGO,
            new String[]{"&aFeed &f320 &aGrass &fto &aEpona"},
            null, new String[]{"MOUNT EPONA 1"}, 0, null, 0),
    CUCCO_SEARCH(QuestGiver.INGO,
            new String[]{"&aRescue &f4 &aCuccos &ftrapped atop the &atrees"},
            null, new String[]{"MATERIAL GIANT_EGG 1"}, 200, new String[]{AdventureStatsDisplay.FARMING.getName() + ",200"}, 100),

    PESKY_CROP_STOMPERS(QuestGiver.MANDY,
            new String[]{"&aSlaughter &f%betonquest_default-Farm-Mandy:point.PESKY_CROP_STOMPERS.amount%/25 &aPigs &fusing the &lFarmer Rifle"},
            null, null, 100, new String[]{AdventureStatsDisplay.FARMING.getName() + ",100"}, 50),
    THE_FIRST_HARVEST(QuestGiver.MANDY,
            new String[]{"&aHarvest &f%betonquest_default-Farm-Mandy:point.THE_FIRST_HARVEST.amount%/320 &aWheat &fwith a &aWooden Hoe"},
            null, null, 100, new String[]{AdventureStatsDisplay.FARMING.getName() + ",100"}, 50),
    ACORN_KNOCKER(QuestGiver.MANDY,
            new String[]{"&aKnock &f%betonquest_default-Farm-Mandy:point.ACORN_KNOCKER.amount%/32 &aAcorns &foff the &aTrees"},
            null, null, 100, new String[]{AdventureStatsDisplay.FARMING.getName() + ",100"}, 50),

    CARROT_HARVEST(QuestGiver.BILLY,
            new String[]{"&aHarvest &f%betonquest_default-Farm-Mandy:point.CARROT_HARVEST.amount%/320 &aCarrots &fwith a &aWooden Hoe"},
            null, null, 100, new String[]{AdventureStatsDisplay.FARMING.getName() + ",100"}, 50),
    POTATO_HARVEST(QuestGiver.BILLY,
            new String[]{"&aHarvest &f%betonquest_default-Farm-Mandy:point.POTATO_HARVEST.amount%/320 &aPotatoes &fwith a &aWooden Hoe"},
            null, new String[]{"ARMOR FARMER_HAT 1", "ARMOR FARMER_SHIRT 1", "ARMOR FARMER_PANTS 1", "ARMOR FARMER_SHOES 1"}, 100, new String[]{AdventureStatsDisplay.FARMING.getName() + ",100"}, 50),

    //  Valley

    RESCUE_VALLEY_VILLAGERS(QuestGiver.MAEL,
            new String[]{"&aRescue &f3 &aTownfolk &ftrapped inside", "&fthe &cGoblin Camps &fwithin the &aValley"},
            null, null, 100, new String[]{AdventureStatsDisplay.FARMING.getName() + ",100"}, 50),
    VALLEY_GOBLIN_SLAUGHTER(QuestGiver.MAEL,
            new String[]{"&aSlay &f%betonquest_default-Valley-Mael:point.VALLEY_GOBLIN_SLAUGHTER.amount%/25 &cGoblins &fwithin the &aValley"},
            null, null, 200, new String[]{AdventureStatsDisplay.FARMING.getName() + ",200"}, 100),

    //  Estate

    BEET_THOSE_ROOTS(QuestGiver.BOWYER,
            new String[]{"&aHarvest &f%betonquest_default-Estate-Bowyer:point.BEET_THOSE_ROOTS.amount%/128 &aBeatroots &fwith a &aWooden Hoe"},
            null, null, 150, new String[]{AdventureStatsDisplay.FARMING.getName() + ",150"}, 75),
    GOBLIN_PILLAGE(QuestGiver.BOWYER,
            new String[]{"&aSlay &f%betonquest_default-Estate-Bowyer:point.GOBLIN_PILLAGE.amount%/25 &cGoblins &fwithin the &aEstate"},
            null, new String[]{"STAFF HARMING_STAFF2 1"}, 200, new String[]{AdventureStatsDisplay.FARMING.getName() + ",200"}, 100),
    SACRED_CANE(QuestGiver.BOWYER,
            new String[]{"&aHarvest &f%betonquest_default-Estate-Bowyer:point.SACRED_CANE.amount%/128 &aSugar Cane &fwith a &aWooden Hoe"},
            null, null, 150, new String[]{AdventureStatsDisplay.FARMING.getName() + ",150"}, 75),

    PLASTIC_PICKER(QuestGiver.DON,
            new String[]{"&aPickup &f%betonquest_default-Estate-Don:point.PLASTIC_PICKER.amount%/32 &aPlastic (Chains)", "&fwhich are &cpolluting &fthe &9pond"},
            null, null, 150, new String[]{AdventureStatsDisplay.FARMING.getName() + ",150"}, 75),
    CORAL_CLEANER(QuestGiver.DON,
            new String[]{"&aCleanup &f%betonquest_default-Estate-Don:point.CORAL_CLEANER.amount%/32 &aFire Coral &ffrom within the &9pond"},
            null, null, 150, new String[]{AdventureStatsDisplay.FARMING.getName() + ",150"}, 75),
    SEA_FEEDER(QuestGiver.DON,
            new String[]{"&aFeed Leon &f192 &aSea Grass &ffrom within the &9pond"},
            null, null, 150, new String[]{AdventureStatsDisplay.FARMING.getName() + ",150"}, 75),

    SHEEP_SHAVER(QuestGiver.SANDRAH,
            new String[]{"&aShave &f%betonquest_default-Estate-Sandrah:point.SHEEP_SHAVER.amount%/35 &aSheep &faround the &aEstate"},
            null, null, 150, new String[]{AdventureStatsDisplay.FARMING.getName() + ",150"}, 75),
    DYE_COLLECTOR(QuestGiver.SANDRAH,
            new String[]{"&aCollect &f%betonquest_default-Estate-Sandrah:point.DYE_COLLECTOR.amount%/128 &aRose Bushes", "&faround the &aValley &fand &aEstate"},
            null, null, 150, new String[]{AdventureStatsDisplay.FARMING.getName() + ",150"}, 75),
    WOOL_CLEANER(QuestGiver.SANDRAH,
            new String[]{"&aClean &f64 &aRed Wool &fat the", "&6Wool Cleaner &fnext to &aSandrah"},
            new String[]{"&eLoom Usage"}, null, 150, new String[]{AdventureStatsDisplay.FARMING.getName() + ",150"}, 75),

    //  Goblin Town

    PILLAGING_GOBLIN_TOWN(QuestGiver.ZRAS,
            new String[]{"&aSlay &f%betonquest_default-Goblin_Town-Zras:point.PILLAGING_GOBLIN_TOWN.amount%/50 &cGoblins &fwithin the &cGoblin Town"},
            null, new String[]{"DAGGER GOBLIN_CLUB3 1"}, 300, new String[]{AdventureStatsDisplay.FARMING.getName() + ",300"}, 150),
    PRISON_BREAK(QuestGiver.ZRAS,
            new String[]{"&aRescue &f5 &aTownfolk &ffrom the", "&fhanging &cCages &fwithin the &cGoblin Town"},
            null, null, 150, new String[]{AdventureStatsDisplay.FARMING.getName() + ",150"}, 75),
    THE_GOBLIN_CHIEF(QuestGiver.ZRAS,
            new String[]{"&aSlay &fthe &cGoblin Chief &fwithin his &cHeadquarters"},
            new String[]{"&aFast Travel to &cGoblin Town"}, null, 400, new String[]{AdventureStatsDisplay.FARMING.getName() + ",400"}, 200),

    //  Spirit Grounds

    CROP_PROTECTOR(QuestGiver.HAZEL,
            new String[]{"&aSlay &f%betonquest_default-Spirit_Grounds-Hazel:point.CROP_PROTECTOR.amount%/25 of the invading &cSpirit Bulls & Crop Spiders"},
            null, null, 150, new String[]{AdventureStatsDisplay.FARMING.getName() + ",150"}, 75),
    WITCHES_CHECKLIST(QuestGiver.HAZEL,
            new String[]{"&aDeliver &f128 &aBrown Mushrooms, Red", "&aMushrooms, & Melon Slices &fto &aHazel"},
            null, new String[]{"WAND RESOLVE 1"}, 200, new String[]{AdventureStatsDisplay.FARMING.getName() + ",200"}, 100),
    SPIRIT_WITCH(QuestGiver.HAZEL,
            new String[]{"&aSlay &fthe &cSpirit Witch &fwhich patrols the &dSpirit Grounds"},
            new String[]{"&aFast Travel to &dSpirit Grounds"}, null, 200, new String[]{AdventureStatsDisplay.FARMING.getName() + ",200"}, 100),

    THRALL_THRASHING(QuestGiver.KHAZIX,
            new String[]{"&aSlay &f%betonquest_default-Void-Khazix:point.THRALL_THRASHING.amount%/30 &cVoid Thralls &fwithin the &5Void"},
            null, new String[]{"SPELL THRALL_BALL3 4"}, 300, new String[]{AdventureStatsDisplay.SLAYER.getName() + ",300"}, 150),
    THRALL_EGG_DELIVERY(QuestGiver.KHAZIX,
            new String[]{"&aDeliver &f16 &2Thrall Eggs &fto &aKhazix"},
            null, null, 0, null, 0),
    WORSHIPER_WONDERS(QuestGiver.KHAZIX,
            new String[]{"&aSlay &f%betonquest_default-Void-Khazix:point.WORSHIPER_WONDERS.amount%/50 &cVoid Worshippers &fwithin the &5Void"},
            null, null, 500, new String[]{AdventureStatsDisplay.SLAYER.getName() + ",500"}, 250),
    WORSHIPER_DNA_DELIVERY(QuestGiver.KHAZIX,
            new String[]{"&aDeliver &f50 &fVoid DNA &fto &aKhazix"},
            null, null, 0, null, 0),
    UNBLIGHTED(QuestGiver.KHAZIX,
            new String[]{"&aSlay &f%betonquest_default-Void-Khazix:point.UNBLIGHTED.amount%/20 &cVoid Sources &fwithin the &5Void"},
            null, new String[]{"CATALYST UNBLINDER3 1"}, 400, new String[]{AdventureStatsDisplay.SLAYER.getName() + ",400"}, 200),
    BULBLIN_AND_BULLBO(QuestGiver.KHAZIX,
            new String[]{"&aSlay &cVoid Bulblin &f& &cVoid Bullbo", "&fwithin the &5Void"},
            null, new String[]{"MOUNT BULB3 1"}, 1000, new String[]{AdventureStatsDisplay.SLAYER.getName() + ",1000"}, 500),
    SPIRAL_CRYSTALS(QuestGiver.KHAZIX,
            new String[]{"&aSmash &f12 &cCrystals &fabove", "&fthe spirals around the &5Void"},
            null, null, 500, new String[]{AdventureStatsDisplay.SLAYER.getName() + ",500"}, 250),
    CHORUS_PREPERATIONS(QuestGiver.KHAZIX,
            new String[]{"&aHarvest &f%betonquest_default-Void-Khazix:point.CHORUS_PREPERATIONS.amount%/128 &aChorus Flowers &fwithin the &5Void"},
            null, null, 500, new String[]{AdventureStatsDisplay.SLAYER.getName() + ",500"}, 250),
    JOINING_THE_CHORUS(QuestGiver.KHAZIX,
            new String[]{"&aDeliver &f128 &fChorus Seeds &fto &aKhazix"},
            null, null, 0, null, 0),

    CORRUPT_LAND(QuestGiver.KASSADIN,
            new String[]{"&aMine &f%betonquest_default-Void-Kassadin:point.CORRUPT_LAND.amount%/512 &aEnd Stone &fwithin the &5Void"},
            null, null, 400, new String[]{AdventureStatsDisplay.MINING.getName() + ",400"}, 200),
    END_STONE_DELIVERY(QuestGiver.KASSADIN,
            new String[]{"&aDeliver &f512 &aEnd Stone &fto &aKassadin"},
            null, null, 0, null, 0),
    INCREASED_HARDENCITY(QuestGiver.KASSADIN,
            new String[]{"&aMine &f%betonquest_default-Void-Kassadin:point.INCREASED_HARDENCITY.amount%/256 &aObsidian &fwithin the &5Void"},
            null, null, 500, new String[]{AdventureStatsDisplay.MINING.getName() + ",500"}, 250),
    OBSIDIAN_DELIVERY(QuestGiver.KASSADIN,
            new String[]{"&aDeliver &f256 &aObsidian &fto &aKassadin"},
            null, null, 0, null, 0),
    RING_AROUND_THE_WITHER_ROSES(QuestGiver.KASSADIN,
            new String[]{"&aPick &f%betonquest_default-Void-Kassadin:point.RING_AROUND_THE_WITHER_ROSES.amount%/320 &aWither Roses &fatop", "&fthe spirals within the &5Void"},
            null, null, 500, new String[]{AdventureStatsDisplay.MINING.getName() + ",500"}, 250),
    WITHER_ROSE_DELIVERY(QuestGiver.KASSADIN,
            new String[]{"&aDeliver &f320 &aWither Roses &fto &aKassadin"},
            null, null, 0, null, 0),
    POP_A_VEIN(QuestGiver.KASSADIN,
            new String[]{"&aPop &f%betonquest_default-Void-Kassadin:point.POP_A_VEIN.amount%/10 &cVoid Veins &faround", "&fthe spirals within the &5Void"},
            null, null, 500, new String[]{AdventureStatsDisplay.MINING.getName() + ",500"}, 250),

    ANKLE_BITERS(QuestGiver.KOGMAW,
            new String[]{"&aSlay &f%betonquest_default-Void-Kogmaw:point.ANKLE_BITERS.amount%/25 &cVoid Monitors", "&fwithin the &5Void Maze"},
            null, null, 500, new String[]{AdventureStatsDisplay.SLAYER.getName() + ",500"}, 250),
    MAGICAL_BUTTONS(QuestGiver.KOGMAW,
            new String[]{"&aPush &f4 unique &aButtons &ffound", "&fthrougout the &5Void Maze"},
            null, null, 200, null, 100),
    CHAMPION_BRAWL(QuestGiver.KOGMAW,
            new String[]{"&aSlay &f%betonquest_default-Void-Kogmaw:point.CHAMPION_BRAWL.amount%/50 &cVoid Champions", "&fwithin the &5Void Maze"},
            null, null, 750, new String[]{AdventureStatsDisplay.SLAYER.getName() + ",750"}, 375),
    MEGA_BOOM(QuestGiver.KOGMAW,
            new String[]{"&aSlay &f%betonquest_default-Void-Kogmaw:point.MEGA_BOOM.amount%/25 &cVoid Mega Boomers", "&fwithin the &5Void Maze"},
            null, new String[]{"ACCESSORY DARKNESS_EXTINGUISHER3 1"}, 500, new String[]{AdventureStatsDisplay.SLAYER.getName() + ",500"}, 250),
    PORTAL_PREPARATION(QuestGiver.KOGMAW,
            new String[]{"&aSlay &cenemies &fwithin the &5Void Maze", "&funtil you find a &5Unstable Warp"},
            null, new String[]{"MATERIAL STABILIZER 9"}, 500, new String[]{AdventureStatsDisplay.SLAYER.getName() + ",500"}, 250),
    WARP_STABILIZER(QuestGiver.KOGMAW,
            new String[]{"&aCombine &fthe &9Stabilizers &fwith the", "&5Unstable Warp &fwithin the &5Void Shop", "&fto create a &eStable Warp"},
            null, null, 0, null, 0),
    PORTAL_OPENER(QuestGiver.KOGMAW,
            new String[]{"&aUse &fthe &eStable Warp &fto open", "&fa portal to the &5Void Abyss"},
            null, null, 750, new String[]{AdventureStatsDisplay.SLAYER.getName() + ",750"}, 375),

    ;

    private final QuestGiver questGiver;
    private final String[] questDescription;
    private final String[] rewardText;
    private final String[] rewardItems;
    private final int rewardMainEXP;
    private final String[] rewardProfessionEXP;
    private final int rewardMoney;


    QuestList(QuestGiver questGiver, String[] questDescription, String[] rewardText, String[] rewardItems, int rewardMainEXP, String[] rewardProfessionEXP, int rewardMoney) {
        this.questGiver = questGiver;
        this.questDescription = questDescription;
        this.rewardText = rewardText;
        this.rewardItems = rewardItems;
        this.rewardMainEXP = rewardMainEXP;
        this.rewardProfessionEXP = rewardProfessionEXP;
        this.rewardMoney = rewardMoney;
    }

    public QuestGiver getQuestGiver() {
        return questGiver;
    }

    public String[] getRewardText() {
        return rewardText;
    }

    public String[] getRewardItems() {
        return rewardItems;
    }

    public int getRewardMainEXP() {
        return rewardMainEXP;
    }

    public String[] getRewardProfessionEXP() {
        return rewardProfessionEXP;
    }

    public int getRewardMoney() {
        return rewardMoney;
    }

    public String[] getQuestDescription() {
        return questDescription;
    }
}
