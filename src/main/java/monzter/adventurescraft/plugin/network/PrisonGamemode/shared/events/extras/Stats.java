package monzter.adventurescraft.plugin.network.PrisonGamemode.shared.events.extras;

public enum Stats {
    BLOCK_MULTIPLIER(0),
    EXPERIENCE_MULTIPLIER(0),
    LUCK_MULTIPLIER(0),
    MAX_WEIGHT(0),
    MAX_WEIGHT_MULTIPLIER(0),
    PET_EXPERIENCE(0),
    SELL_MULTIPLIER(0),
    ;

    private final double defaultValue;

    Stats(final double defaultValue) {
        this.defaultValue = defaultValue;
    }

    public double getDefaultValue() {
        return defaultValue;
    }
}
