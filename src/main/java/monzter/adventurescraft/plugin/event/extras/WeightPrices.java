package monzter.adventurescraft.plugin.event.extras;

import org.bukkit.Material;

public enum WeightPrices {
    COBBLESTONE(Material.COBBLESTONE, 1, 1),
    ANDESITE(Material.ANDESITE, 2, 2),
    DIORITE(Material.DIORITE, 2, 2),
    STONE(Material.STONE, 2, 2),
    GRANITE(Material.GRANITE, 2, 3),
    POLISHED_ANDESITE(Material.POLISHED_ANDESITE, 4, 8),
    POLISHED_DIORITE(Material.POLISHED_DIORITE, 4, 9),
    SMOOTH_STONE(Material.SMOOTH_STONE, 4, 10),
    POLISHED_GRANITE(Material.POLISHED_GRANITE, 4, 11),
    QUARTZ_BLOCK(Material.QUARTZ_BLOCK, 5, 12),
    PURPUR_BLOCK(Material.PURPUR_BLOCK, 6, 14),
    COAL_ORE(Material.COAL_ORE, 3, 6),
    COAL_BLOCK(Material.COAL_BLOCK, 9, 12),
    IRON_ORE(Material.IRON_ORE, 3, 8),
    IRON_BLOCK(Material.IRON_BLOCK, 9, 16),
    GOLD_ORE(Material.GOLD_ORE, 3, 10),
    GOLD_BLOCK(Material.GOLD_BLOCK, 9, 20),
    LAPIS_ORE(Material.LAPIS_ORE, 3, 12),
    LAPIS_BLOCK(Material.LAPIS_BLOCK, 9, 24),
    REDSTONE_ORE(Material.REDSTONE_ORE, 3, 14),
    REDSTONE_BLOCK(Material.REDSTONE_BLOCK, 9, 28),
    EMERALD_ORE(Material.EMERALD_ORE, 3, 16),
    EMERALD_BLOCK(Material.EMERALD_BLOCK, 9, 32),
    DIAMOND_ORE(Material.DIAMOND_ORE, 3, 18),
    DIAMOND_BLOCK(Material.DIAMOND_BLOCK, 9, 36),
    ORANGE_STAINED_GLASS(Material.ORANGE_STAINED_GLASS, 100, 1000),
    RED_STAINED_GLASS(Material.RED_STAINED_GLASS, 100, 1000),
    YELLOW_STAINED_GLASS(Material.YELLOW_STAINED_GLASS, 100, 1000),
    BLUE_STAINED_GLASS(Material.BLUE_STAINED_GLASS, 100, 1000),
    GREEN_STAINED_GLASS(Material.GREEN_STAINED_GLASS, 100, 1000),
    SEA_LANTERN(Material.SEA_LANTERN, 1000, 10000);


    public final Material material;
    public final int weight;
    public final double price;

    WeightPrices(Material material, int weight, double price) {
        this.material = material;
        this.weight = weight;
        this.price = price;
    }

    public Material getMaterial() {
        return material;
    }

    public double getWeight() {
        return weight;
    }

    public double getPrice() {
        return price;
    }
}
