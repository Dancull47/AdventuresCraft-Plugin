package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.ClassSystem;

import java.util.ArrayList;
import java.util.List;

public enum EXPTable {
    MAIN1(Professions.MAIN, 1, 500),
    MAIN2(Professions.MAIN, 2, 750),
    MAIN3(Professions.MAIN, 3, 1_000),
    MAIN4(Professions.MAIN, 4, 1_500),
    MAIN5(Professions.MAIN, 5, 2_500),
    MAIN6(Professions.MAIN, 6, 5_000),
    MAIN7(Professions.MAIN, 7, 7_500),
    MAIN8(Professions.MAIN, 8, 15_000),
    MAIN9(Professions.MAIN, 9, 25_000),
    MAIN10(Professions.MAIN, 10, 35_000),

    FARMING1(Professions.FARMING, 1, 500),
    FARMING2(Professions.FARMING, 2, 750),
    FARMING3(Professions.FARMING, 3, 1_000),
    FARMING4(Professions.FARMING, 4, 1_500),
    FARMING5(Professions.FARMING, 5, 2_500),
    FARMING6(Professions.FARMING, 6, 5_000),
    FARMING7(Professions.FARMING, 7, 7_500),
    FARMING8(Professions.FARMING, 8, 15_000),
    FARMING9(Professions.FARMING, 9, 25_000),
    FARMING10(Professions.FARMING, 10, 35_000),
    ;

    private final Professions profession;
    private final int level;
    private final int expAmount;

    EXPTable(Professions profession, int level, int expAmount) {
        this.profession = profession;
        this.level = level;
        this.expAmount = expAmount;
    }

    public static List<EXPTable> getEXPTable(Professions profession) {
        List list = new ArrayList();
        for (EXPTable expTable : EXPTable.values()) {
            if (expTable.getProfession() == profession)
                list.add(expTable);
        }
        return list;
    }

    public static List<EXPTable> getLevelUps(Professions profession, int currentLevel, int currentEXP, int expAmount) {
        List list = new ArrayList();
        for (EXPTable expTable : EXPTable.values()) {
            if (expTable.getProfession() == profession && expTable.getLevel() > currentLevel)
                if ((currentEXP + expAmount) >= expTable.getExpAmount())
                    list.add(expTable);
        }

        return list;
    }

    public Professions getProfession() {
        return profession;
    }

    public int getLevel() {
        return level;
    }

    public int getExpAmount() {
        return expAmount;
    }
}
