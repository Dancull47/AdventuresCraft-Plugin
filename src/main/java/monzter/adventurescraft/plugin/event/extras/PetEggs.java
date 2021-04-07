package monzter.adventurescraft.plugin.event.extras;

public class PetEggs {
    private final String name;
    private final int expToHatch;

    public PetEggs(String name, int expToHatch) {
        this.name = name;
        this.expToHatch = expToHatch;
    }
    public String getName() {
        return name;
    }

    public int getExpToHatch() {
        return expToHatch;
    }

}
