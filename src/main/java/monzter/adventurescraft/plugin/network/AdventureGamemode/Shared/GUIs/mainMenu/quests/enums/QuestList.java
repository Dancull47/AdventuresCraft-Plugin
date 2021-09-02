package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.mainMenu.quests.enums;

import monzter.adventurescraft.plugin.utilities.enums.AdventureStatsDisplay;
import org.bukkit.ChatColor;

public enum QuestList {
    ACTIVE_QUESTS_JOURNAL(QuestGiver.TUTOR,
            new String[]{ChatColor.GREEN + "Right-Click " + ChatColor.WHITE + "while holding the " + ChatColor.BOLD + "Quest Journal"},
            null, 100, null, 100),
    ACTIVE_QUESTS_MENU(QuestGiver.TUTOR,
            new String[]{ChatColor.GREEN + "Left-Click " + ChatColor.WHITE + "while holding the " + ChatColor.BOLD + "Quest Journal", ChatColor.WHITE + "and looking at a " + ChatColor.GREEN + "Block"},
            null, 100, null, 100),
    UNCLAIMED_QUESTS_MENU(QuestGiver.TUTOR,
            new String[]{ChatColor.GREEN + "Crouch and Left-Click " + ChatColor.WHITE + "while holding the", ChatColor.WHITE.toString() + ChatColor.BOLD + "Quest Journal " + ChatColor.WHITE + "and looking at a " + ChatColor.GREEN + "Block"},
            null, 100, null, 100),
    FOLLOW_THE_ARROW(QuestGiver.TUTOR,
            new String[]{ChatColor.WHITE + "Follow the " + ChatColor.GREEN + "Arrow " + ChatColor.WHITE + "guiding ", ChatColor.WHITE + "you towards the next " + ChatColor.GOLD + "Quester"},
            null, 100, null, 100),

    HUNTING_THE_UNDEAD(QuestGiver.CIRL,
            new String[]{"&aSlay &f%betonquest_default-Graveyard-Cirl:point.HUNTING_THE_UNDEAD.amount%/20 &cUndead Skeletons", "&fwithin the &cGraveyard"},
            new String[]{"ARMOR BONE_SKULL2 1"}, 100, new String[]{AdventureStatsDisplay.SLAYER.getName() + ",100"}, 100),
    THE_SILVER_STOOL(QuestGiver.CIRL,
            new String[]{"&aUse &fthe &c&lSilver Stool &f(-157, 15, 145)", "&fwithin the &cGraveyard &fto", "&aUpgrade &fyour &lSilver Sword"},
            new String[]{"ARMOR BONE_CHESTPLATE2 1", "ARMOR BONE_LEGS2 1"}, 100, new String[]{AdventureStatsDisplay.SLAYER.getName() + ",100"}, 100),
    ARCHER_ANNILATOR(QuestGiver.CIRL,
            new String[]{"&aSlay &f%betonquest_default-Graveyard-Cirl:point.ARCHER_ANNILATOR.amount%/15 &cUndead Archers &fwithin the &cGraveyard"},
            new String[]{"ARMOR BONE_FEET2 1"}, 100, new String[]{AdventureStatsDisplay.SLAYER.getName() + ",100"}, 100),
    THE_REAPER(QuestGiver.CIRL,
            new String[]{"&aSlay &fthe &cReaper &f(-96, 15, 176)"},
            new String[]{"WHIP BONEWHIP4 1"}, 100, new String[]{AdventureStatsDisplay.SLAYER.getName() + ",100"}, 100),
    COURTYARD_VENTURE(QuestGiver.CIRL,
            new String[]{"&aFollow &fthe &aGrass Path", "&fto enter the &cCourtyard &f(-111, 15, 248)"},
            null, 100, new String[]{AdventureStatsDisplay.SLAYER.getName() + ",100"}, 100),

    THE_UNDEAD_SLAYER(QuestGiver.NAVID,
            new String[]{"&aSlay &f%betonquest_default-Courtyard-Navid:point.THE_UNDEAD_SLAYER.amount%/50 &cUndead &fwithin the &cCourtyard"},
            new String[]{"BOW UNDEAD_BOW3 1"}, 200, new String[]{AdventureStatsDisplay.SLAYER.getName() + ",200"}, 150),
    THE_MAGICAL_HUNT(QuestGiver.NAVID,
            new String[]{"&aSlay &cUndead Casters &fand &adeliver", "&f8 &lMagical Fragments &fto &aNavid"},
            new String[]{"CONSUMABLE MAGICAL_ESSENSE2 1"}, 200, new String[]{AdventureStatsDisplay.SLAYER.getName() + ",200"}, 150),
    SPIDER_EXTERMINATOR(QuestGiver.NAVID,
            new String[]{"&aSlay &f%betonquest_default-Courtyard-Navid:point.SPIDER_EXTERMINATOR.amount%/20 &cUndead Spiders &fwithin the &cCourtyard"},
            null, 200, new String[]{AdventureStatsDisplay.SLAYER.getName() + ",200"}, 150),
    THE_ALPHAS(QuestGiver.NAVID,
            new String[]{"&aSlay &f%betonquest_default-Courtyard-Navid:point.THE_ALPHAS.amount%/2 &cAlpha Souls &fwithin the &cCourtyard"},
            null, 200, new String[]{AdventureStatsDisplay.SLAYER.getName() + ",200"}, 150),
    THE_VILLAGE_HERO(QuestGiver.NAVID,
            new String[]{"&aRescue &f5 &aVillagers &ftrapped around the &cCourtyard"},
            null, 250, new String[]{AdventureStatsDisplay.SLAYER.getName() + ",250"}, 200),
    CASTLE_BREACH(QuestGiver.NAVID,
            new String[]{"&aBreach &fthe &cCastle &fby speaking with &aAnder", "&fat the &cCastle Gate &f(-95, 15, 434)"},
            null, 0, null, 0),

    SUMMONING_MORDEN(QuestGiver.KLAUS,
            new String[]{"&aSlay &f%betonquest_default-Castle-Klaus:point.SUMMONING_MORDEN.amount%/100 &cUndead &fwithin the &cCourtyard"},
            null, 0, null, 0),

    CUCCO_CATCH(QuestGiver.ANJU,
            new String[]{"&aGrab &fand &adeliver &f%betonquest_default-Farm-Anju:point.CUCCO_CATCH.amount%/20 &aCuccos", "&fwithin the &aFarm &fto &aAnju"},
            null, 250, new String[]{AdventureStatsDisplay.FARMING.getName() + ",250"}, 100),
    EGG_DELIVERY(QuestGiver.ANJU,
            new String[]{"&aDeliver &f25 &aEggs &fto &aAnju"},
            new String[]{"ACCESSORY SCRAMBLED_EGG 1"}, 250, new String[]{AdventureStatsDisplay.FARMING.getName() + ",250"}, 100),

    GRASS_GATHERER(QuestGiver.INGO,
            new String[]{"&aHarvest &f%betonquest_default-Farm-Ingo:point.GRASS_GATHERER.amount%/320 &aGrass &ffor &aEpona"},
            null, 200, new String[]{AdventureStatsDisplay.FARMING.getName() + ",200"}, 100),
    CURING_THE_HUNGER(QuestGiver.INGO,
            new String[]{"&aFeed &f320 &aGrass &fto &aEpona"},
            new String[]{"MOUNT EPONA 1"}, 0, null, 0),
    CUCCO_SEARCH(QuestGiver.INGO,
            new String[]{"&aRescue &f4 &aCuccos &ftrapped atop the &atrees"},
            new String[]{"MATERIAL GIANT_EGG 1"}, 200, new String[]{AdventureStatsDisplay.FARMING.getName() + ",200"}, 100),

    PESKY_CROP_STOMPERS(QuestGiver.MANDY,
            new String[]{"&aSlaughter &f%betonquest_default-Farm-Mandy:point.PESKY_CROP_STOMPERS.amount%/25 &aPigs &fusing the &lFarmer Rifle"},
            null, 100, new String[]{AdventureStatsDisplay.FARMING.getName() + ",100"}, 50),
    THE_FIRST_HARVEST(QuestGiver.MANDY,
            new String[]{"&aHarvest &f%betonquest_default-Farm-Mandy:point.THE_FIRST_HARVEST.amount%/320 &aWheat &fwith a &aWooden Hoe"},
            null, 100, new String[]{AdventureStatsDisplay.FARMING.getName() + ",100"}, 50),
    ACORN_KNOCKER(QuestGiver.MANDY,
            new String[]{"&aKnock &f%betonquest_default-Farm-Mandy:point.ACORN_KNOCKER.amount%/32 &aAcorns &foff the &aTrees"},
            null, 100, new String[]{AdventureStatsDisplay.FARMING.getName() + ",100"}, 50),

    CARROT_HARVEST(QuestGiver.BILLY,
            new String[]{"&aHarvest &f%betonquest_default-Farm-Mandy:point.CARROT_HARVEST.amount%/320 &aCarrots &fwith a &aWooden Hoe"},
            null, 100, new String[]{AdventureStatsDisplay.FARMING.getName() + ",100"}, 50),
    POTATO_HARVEST(QuestGiver.BILLY,
            new String[]{"&aHarvest &f%betonquest_default-Farm-Mandy:point.POTATO_HARVEST.amount%/320 &aPotatoes &fwith a &aWooden Hoe"},
            null, 100, new String[]{AdventureStatsDisplay.FARMING.getName() + ",100"}, 50),

    RESCUE_VALLEY_VILLAGERS(QuestGiver.MAEL,
            new String[]{"&aRescue &f3 &aTownfolk &ftrapped inside", "&fthe &cGoblin Camps &fwithin the &aValley"},
            null, 100, new String[]{AdventureStatsDisplay.FARMING.getName() + ",100"}, 50),
    VALLEY_GOBLIN_SLAUGHTER(QuestGiver.MAEL,
            new String[]{"&aSlay &f%betonquest_default-Valley-Mael:point.VALLEY_GOBLIN_SLAUGHTER.amount%/25 &cGoblins &fwithin the &aValley"},
            null, 200, new String[]{AdventureStatsDisplay.FARMING.getName() + ",200"}, 100),

    BEET_THOSE_ROOTS(QuestGiver.BOWYER,
            new String[]{"&aHarvest &f%betonquest_default-Estate-Bowyer:point.BEET_THOSE_ROOTS.amount%/128 &aBeatroots &fwith a &aWooden Hoe"},
            null, 150, new String[]{AdventureStatsDisplay.FARMING.getName() + ",150"}, 75),
    GOBLIN_PILLAGE(QuestGiver.BOWYER,
            new String[]{"&aSlay &f%betonquest_default-Estate-Bowyer:point.GOBLIN_PILLAGE.amount%/25 &cGoblins &fwithin the &aEstate"},
            new String[]{"STAFF HARMING_STAFF2 1"}, 200, new String[]{AdventureStatsDisplay.FARMING.getName() + ",200"}, 100),
    SACRED_CANE(QuestGiver.BOWYER,
            new String[]{"&aHarvest &f%betonquest_default-Estate-Bowyer:point.SACRED_CANE.amount%/128 &aSugar Cane &fwith a &aWooden Hoe"},
            null, 150, new String[]{AdventureStatsDisplay.FARMING.getName() + ",150"}, 75),

    PLASTIC_PICKER(QuestGiver.DON,
            new String[]{"&aPickup &f%betonquest_default-Estate-Don:point.PLASTIC_PICKER.amount%/32 &aPlastic (Chains)", "&fwhich are &cpolluting &fthe &9pond"},
            null, 150, new String[]{AdventureStatsDisplay.FARMING.getName() + ",150"}, 75),
    CORAL_CLEANER(QuestGiver.DON,
            new String[]{"&aCleanup &f%betonquest_default-Estate-Don:point.CORAL_CLEANER.amount%/32 &aFire Coral &ffrom within the &9pond"},
            null, 150, new String[]{AdventureStatsDisplay.FARMING.getName() + ",150"}, 75),
    SEA_FEEDER(QuestGiver.DON,
            new String[]{"&aFeed Leon &f192 &aSea Grass &ffrom within the &9pond"},
            null, 150, new String[]{AdventureStatsDisplay.FARMING.getName() + ",150"}, 75),



//    UNDERSTANDING_EXPERIENCE(QuestGiver.WIZARD,
//            new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "%betonquest_default-Graveyard-Cirl:point.HUNTING_THE_UNDEAD.left:20%/100 Blocks" + ChatColor.WHITE + " to earn " +
//                    PrisonStatsDisplay.EXPERIENCE_AMOUNT.getName() + ChatColor.WHITE + "!"},
//            new String[]{"VOUCHER EXP_VOUCHER 1"}, 0, new String[]{Professions.ENCHANTING.name() + " 250"}, 0),
//    NEW_ENCHANTER(QuestGiver.WIZARD,
//            new String[]{ChatColor.WHITE + "Consume the " + ChatColor.BOLD + "EXP Voucher" + ChatColor.WHITE + " given to",
//                    ChatColor.WHITE + "you from the previous quest. ",
//                    "",
//                    ChatColor.WHITE + "Interact with the " + ChatColor.DARK_PURPLE + "Enchanter" + ChatColor.WHITE + " while",
//                    ChatColor.WHITE + "holding a " + ChatColor.BOLD + "Tool" + ChatColor.WHITE + ", then purchase",
//                    ChatColor.WHITE + "the " + ChatColor.GREEN + "Experience " + ChatColor.DARK_PURPLE + "Enchantment" + ChatColor.WHITE + "!"},
//            new String[]{"BOOSTER EXP_BOOSTER 1"}, 0, new String[]{Professions.ENCHANTING.name() + " 100"}, 0),
//    ENCHANTING_PIONEER(QuestGiver.WIZARD,
//            new String[]{ChatColor.WHITE + "Speak with " + ChatColor.GREEN + "Merle" + ChatColor.WHITE + " outside of the",
//                    ChatColor.DARK_PURPLE + "Enchanting Library " + ChatColor.WHITE + "to further increase",
//                    ChatColor.WHITE + "your " + ChatColor.DARK_PURPLE + "Enchanting Profession" + ChatColor.WHITE + "!"},
//            null, 0, new String[]{Professions.ENCHANTING.name() + " 100"}, 0),

//    EGG_EXERCISE(QuestGiver.JOY,
//            new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "%betonquest_default-Town-Joy:point.Mine1.amount%/100 Blocks" + ChatColor.WHITE + " with the " + ChatColor.BOLD + "Pet Egg" + ChatColor.WHITE + " inside",
//                    ChatColor.WHITE + "your inventory to earn 100 " + PrisonStatsDisplay.PET_EXPERIENCE_AMOUNT.getName() + ChatColor.WHITE + "!"},
//            null, 0, new String[]{Professions.COMPANIONSHIP.name() + " 100"}, 0, 0, 0),
//    HATCHING_AN_EGG(QuestGiver.JOY,
//            new String[]{ChatColor.WHITE + "Return to the " + ChatColor.GOLD + "Pet Shop" + ChatColor.WHITE + " and speak with " + ChatColor.GREEN + "Sarah" + ChatColor.WHITE + " to",
//                    ChatColor.WHITE + "hatch the Pet Egg, with your 100 " + PrisonStatsDisplay.PET_EXPERIENCE_AMOUNT.getName() + ChatColor.WHITE + "!"},
//            null, 0, new String[]{Professions.COMPANIONSHIP.name() + " 250"}, 0, 0, 0),
//    EVOLUTION(QuestGiver.JOY,
//            new String[]{ChatColor.WHITE + "Interact with the " + ChatColor.GOLD + "Evolver" + ChatColor.WHITE + " behind the",
//                    ChatColor.WHITE + "desk, to learn more about " + ChatColor.GREEN + "Evolving Pets" + ChatColor.WHITE + "!"},
//            null, 0, new String[]{Professions.COMPANIONSHIP.name() + " 250"}, 0, 0, 0),
//
//    WATER_CRYSTAL(QuestGiver.ASH,
//            new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "%betonquest_default-Town-Ash:point.LAPIS1.amount%/64 Lapis Blocks" + ChatColor.WHITE + ", ",
//                    ChatColor.GREEN + "%betonquest_default-Town-Ash:point.BLUE_GLASS1.amount%/32 Blue Stained Glass Blocks" + ChatColor.WHITE + "," + ChatColor.WHITE + " and",
//                    ChatColor.GREEN + "%betonquest_default-Town-Ash:point.SEA_LANTERN1.amount%/16 Sea Lanterns " + ChatColor.WHITE + "while the",
//                    ChatColor.DARK_PURPLE + "Fractured Crystal " + ChatColor.WHITE + "is within your inventory!"},
//            new String[]{"MATERIAL WATER_CRYSTAL5 1"}, 0, null, 100_000_000, 0, 500_000),
//    KINGS_CROWN(QuestGiver.ASH,
//            new String[]{ChatColor.WHITE + "Mine " + ChatColor.GREEN + "%betonquest_default-Town-Ash:point.GOLD1.amount%/64 Gold Blocks" + ChatColor.WHITE + ", ",
//                    ChatColor.GREEN + "%betonquest_default-Town-Ash:point.EMERALD1.amount%/32 Emerald Blocks" + ChatColor.WHITE + "," + ChatColor.WHITE + " and",
//                    ChatColor.GREEN + "%betonquest_default-Town-Ash:point.STAINED_GLASS1.amount%/16 Stained Glass Blocks " + ChatColor.WHITE + "while the",
//                    ChatColor.DARK_PURPLE + "Broken Crown " + ChatColor.WHITE + "is within your inventory!"},
//            new String[]{"MATERIAL PHOENIX_CROWN5 1"}, 0, null, 100_000_000, 0, 500_000),

    ;

    private final QuestGiver questGiver;
    private final String[] questDescription;
    private final String[] rewardItems;
    private final int rewardMainEXP;
    private final String[] rewardProfessionEXP;
    private final int rewardMoney;


    QuestList(QuestGiver questGiver, String[] questDescription, String[] rewardItems, int rewardMainEXP, String[] rewardProfessionEXP, int rewardMoney) {
        this.questGiver = questGiver;
        this.questDescription = questDescription;
        this.rewardItems = rewardItems;
        this.rewardMainEXP = rewardMainEXP;
        this.rewardProfessionEXP = rewardProfessionEXP;
        this.rewardMoney = rewardMoney;
    }

    public QuestGiver getQuestGiver() {
        return questGiver;
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
