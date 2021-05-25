package monzter.adventurescraft.plugin.utilities.enums;

public enum Enchantments {
    Experience("Experience", 250, 1_000, .01),
    PetExperience("Pet Experience", 250, 1_000, .01),
    Luck("Luck", 250, 1_000, 0.002),
    ExplosiveChance("Explosive Chance", 250, 15_000, 0.00030),
    Explosive("Explosive", 5, 100_000_000, 1),
    Randomizer("Randomizer", 250, 15_000, 0.00005),
    Treasurer("Treasurer", 250, 15_000, 0.00005),
    MidasTouch("Midas Touch", 250, 15_000, 0.00005),
    StatTracker("Stat Tracker", 1, 1_000_000_000, 1),
    ;

    private String name;
    private int maxLevel;
    private int price;
    private double increase;

    Enchantments(String name, int maxLevel, int price, double increase) {
        this.name = name;
        this.maxLevel = maxLevel;
        this.price = price;
        this.increase = increase;
    }

    public String getName() {
        return name;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public int getPrice() {
        return price;
    }

    public double getIncrease() {
        return increase;
    }
}
