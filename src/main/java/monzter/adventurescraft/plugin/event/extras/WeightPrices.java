package monzter.adventurescraft.plugin.event.extras;

import org.bukkit.Material;

public enum WeightPrices {
    COBBLESTONE(Material.COBBLESTONE, "Cobblestone", 1, 1),
    ANDESITE(Material.ANDESITE, "Andesite", 2, 2),
    DIORITE(Material.DIORITE, "Diorite", 2, 2),
    STONE(Material.STONE, "Stone", 2, 2),
    GRANITE(Material.GRANITE,  "Granite", 2, 3),
    SAND(Material.SAND, "Sand", 3, 2),
    POLISHED_ANDESITE(Material.POLISHED_ANDESITE, "Polished Andesite", 4, 8),
    POLISHED_DIORITE(Material.POLISHED_DIORITE, "Polished Diorite", 4, 9),
    SMOOTH_STONE(Material.SMOOTH_STONE, "Stone", 4, 10),
    POLISHED_GRANITE(Material.POLISHED_GRANITE, "Polished Granite", 4, 11),
    RED_SAND(Material.RED_SAND, "Red Sand", 5, 3),
    QUARTZ_BLOCK(Material.QUARTZ_BLOCK, "Quartz Block", 5, 12),
    PURPUR_BLOCK(Material.PURPUR_BLOCK, "Purpur Block", 6, 14),
    COAL_ORE(Material.COAL_ORE, "Coal Ore", 3, 6),
    COAL_BLOCK(Material.COAL_BLOCK, "Coal Block", 9, 12),
    IRON_ORE(Material.IRON_ORE, "Iron Ore", 3, 8),
    IRON_BLOCK(Material.IRON_BLOCK, "Iron Block", 9, 16),
    GOLD_ORE(Material.GOLD_ORE, "Gold Ore", 3, 10),
    GOLD_BLOCK(Material.GOLD_BLOCK, "Gold Block", 9, 20),
    LAPIS_ORE(Material.LAPIS_ORE, "Lapis Ore", 3, 12),
    LAPIS_BLOCK(Material.LAPIS_BLOCK, "Lapis Block", 9, 24),
    REDSTONE_ORE(Material.REDSTONE_ORE, "Redstone Ore", 3, 14),
    REDSTONE_BLOCK(Material.REDSTONE_BLOCK, "Redstone Block", 9, 28),
    EMERALD_ORE(Material.EMERALD_ORE, "Emerald Ore", 3, 16),
    EMERALD_BLOCK(Material.EMERALD_BLOCK, "Emerald Block", 9, 32),
    DIAMOND_ORE(Material.DIAMOND_ORE, "Diamond Ore", 3, 18),
    DIAMOND_BLOCK(Material.DIAMOND_BLOCK, "Diamond Block", 9, 36),
    WHITE_CONCRETE(Material.WHITE_CONCRETE, "White Concrete", 10, 10),
    SANDSTONE(Material.SANDSTONE, "Sandstone", 9, 5),
    RED_SANDSTONE(Material.RED_SANDSTONE, "Red Sandstone", 18, 7),
    ORANGE_CONCRETE(Material.ORANGE_CONCRETE, "Orange Concrete", 20, 20),
    ORANGE_STAINED_GLASS(Material.ORANGE_STAINED_GLASS, "Orange Glass", 100, 1000),
    RED_STAINED_GLASS(Material.RED_STAINED_GLASS, "Red Glass", 100, 1000),
    YELLOW_STAINED_GLASS(Material.YELLOW_STAINED_GLASS, "Yellow Glass", 100, 1000),
    BLUE_STAINED_GLASS(Material.BLUE_STAINED_GLASS, "Blue Glass", 100, 1000),
    GREEN_STAINED_GLASS(Material.GREEN_STAINED_GLASS, "Green Glass", 100, 1000),
    SEA_LANTERN(Material.SEA_LANTERN, "Sea Lantern", 1000, 10000);


    public final Material material;
    public final String name;
    public final int weight;
    public final double price;

    WeightPrices(Material material, String name, int weight, double price) {
        this.material = material;
        this.name = name;
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
