package monzter.adventurescraft.plugin.network.PrisonGamemode.shared.events.pets;

import java.util.*;

public class Pet {
    private final String permissionPrefix;
    private final String name;
    private final String rarity;
    private final String permission;
    private final Map<Stats, Double> stats;

    public static Builder builder() {
        return new Builder();
    }

    public Pet(String permissionPrefix, String name, String rarity, Map<Stats, Double> stats) {
        this.permissionPrefix = permissionPrefix;
        this.name = name;
        this.rarity = rarity;
        this.stats = Collections.unmodifiableMap(stats);
        this.permission = String.join(".", permissionPrefix, name, rarity);

    }

    public String getPermissionPrefix() {
        return permissionPrefix;
    }

    public String getName() {
        return name;
    }

    public String getRarity() {
        return rarity;
    }

    public String getPermission() {
        return permission;
    }

    public OptionalDouble getStat(Stats type) {
        final Double value = stats.get(type);
        if (value == null){
            return OptionalDouble.empty();
        }
        return OptionalDouble.of(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return permissionPrefix.equals(pet.permissionPrefix) &&
                name.equals(pet.name) &&
                rarity.equals(pet.rarity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(permissionPrefix, name, rarity);
    }

    public static class Builder {
        private String permissionPrefix;
        private String name;
        private String rarity;
        private final Map<Stats, Double> stats;

        private Builder() {
            stats = new HashMap<>();
        }

        public Pet build() {
            return new Pet(permissionPrefix, name, rarity, stats);
        }

        public Builder setPermissionPrefix(String permissionPrefix) {
            this.permissionPrefix = permissionPrefix;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setRarity(String rarity) {
            this.rarity = rarity;
            return this;
        }

        public Builder addStat(Stats type, double value){
            this.stats.put(type, value);
            return this;
        }

    }
}
