package monzter.adventurescraft.plugin.event.extras;

public enum Stats {
    BLOCK_MULTIPLIER(1),
    EXPERIENCE_MULTIPLIER(1),
    LUCK_MULTIPLIER(1),
    MAX_WEIGHT(0),
    MAX_WEIGHT_MULTIPLIER(1),
    PET_EXPERIENCE(1),
    SELL_MULTIPLIER(1),
    ;

    private final double defaultValue;

    Stats(final double defaultValue) {
        this.defaultValue = defaultValue;
    }

    public double getDefaultValue() {
        return defaultValue;
    }
}
