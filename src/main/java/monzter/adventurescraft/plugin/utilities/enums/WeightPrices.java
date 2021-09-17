package monzter.adventurescraft.plugin.utilities.enums;

import me.clip.placeholderapi.PlaceholderAPI;
import monzter.adventurescraft.plugin.utilities.general.ItemGenerator;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public enum WeightPrices implements ItemGenerator {
    COBBLESTONE(Material.COBBLESTONE, "Cobblestone", 1, 1, 2.5),
    ANDESITE(Material.ANDESITE, "Andesite", 2, 2, 5.5),
    DIORITE(Material.DIORITE, "Diorite", 2, 2, 6),
    STONE(Material.STONE, "Stone", 2, 2, 6.5),
    GRANITE(Material.GRANITE, "Granite", 2, 3, 7),
    SAND(Material.SAND, "Sand", 3, 2, 9999999),
    POLISHED_ANDESITE(Material.POLISHED_ANDESITE, "Polished Andesite", 4, 8, 8),
    POLISHED_DIORITE(Material.POLISHED_DIORITE, "Polished Diorite", 4, 9, 8.5),
    SMOOTH_STONE(Material.SMOOTH_STONE, "Smooth Stone", 4, 10, 9),
    POLISHED_GRANITE(Material.POLISHED_GRANITE, "Polished Granite", 4, 11, 9.5),
    RED_SAND(Material.RED_SAND, "Red Sand", 5, 3, 9999999),
    QUARTZ_BLOCK(Material.QUARTZ_BLOCK, "Quartz Block", 5, 12, 11),
    PURPUR_BLOCK(Material.PURPUR_BLOCK, "Purpur Block", 6, 14, 12),
    COAL_ORE(Material.COAL_ORE, "Coal Ore", 3, 6, 8),
    COAL_BLOCK(Material.COAL_BLOCK, "Coal Block", 9, 12, 16),
    IRON_ORE(Material.IRON_ORE, "Iron Ore", 3, 8, 8.5),
    IRON_BLOCK(Material.IRON_BLOCK, "Iron Block", 9, 16, 17),
    GOLD_ORE(Material.GOLD_ORE, "Gold Ore", 3, 10, 9),
    GOLD_BLOCK(Material.GOLD_BLOCK, "Gold Block", 9, 20, 18),
    LAPIS_ORE(Material.LAPIS_ORE, "Lapis Ore", 3, 12, 9.5),
    LAPIS_BLOCK(Material.LAPIS_BLOCK, "Lapis Block", 9, 24, 19),
    REDSTONE_ORE(Material.REDSTONE_ORE, "Redstone Ore", 3, 14, 10),
    REDSTONE_BLOCK(Material.REDSTONE_BLOCK, "Redstone Block", 9, 28, 20),
    EMERALD_ORE(Material.EMERALD_ORE, "Emerald Ore", 3, 16, 11),
    EMERALD_BLOCK(Material.EMERALD_BLOCK, "Emerald Block", 9, 32, 22),
    DIAMOND_ORE(Material.DIAMOND_ORE, "Diamond Ore", 3, 18, 12.5),
    DIAMOND_BLOCK(Material.DIAMOND_BLOCK, "Diamond Block", 9, 36, 25),
    WHITE_CONCRETE(Material.WHITE_CONCRETE, "White Concrete", 10, 10, 9999999),
    SANDSTONE(Material.SANDSTONE, "Sandstone", 9, 5, 9999999),
    RED_SANDSTONE(Material.RED_SANDSTONE, "Red Sandstone", 18, 7, 9999999),
    ORANGE_CONCRETE(Material.ORANGE_CONCRETE, "Orange Concrete", 20, 20, 9999999),
    ORANGE_STAINED_GLASS(Material.ORANGE_STAINED_GLASS, "Orange Glass", 100, 1000, 50),
    RED_STAINED_GLASS(Material.RED_STAINED_GLASS, "Red Glass", 100, 1000, 50),
    YELLOW_STAINED_GLASS(Material.YELLOW_STAINED_GLASS, "Yellow Glass", 100, 1000, 50),
    BLUE_STAINED_GLASS(Material.BLUE_STAINED_GLASS, "Blue Glass", 100, 1000, 50),
    GREEN_STAINED_GLASS(Material.GREEN_STAINED_GLASS, "Green Glass", 100, 1000, 50),
    SEA_LANTERN(Material.SEA_LANTERN, "Sea Lantern", 1000, 10000, 150);

    public final Material material;
    public final String name;
    public final int weight;
    public final double price;
    public final double miningSpeed;

    WeightPrices(Material material, String name, int weight, double price, double miningSpeed) {
        this.material = material;
        this.name = name;
        this.weight = weight;
        this.price = price;
        this.miningSpeed = miningSpeed;
    }

    public Material getMaterial() {
        return material;
    }

    public String getName() {
        return name;
    }

    public double getMiningSpeed() {
        return miningSpeed;
    }

    public double getWeight() {
        return weight;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public ItemStack generateItem() {
        return null;
    }

    @Override
    public ItemStack generateItem(Player player) {
        final ItemStack itemStack = new ItemStack(getMaterial());
        final String pointJoiner = String.join(".", "point", getMaterial().toString(), "amount");
        final int pointAmount = Integer.valueOf(PlaceholderAPI.setPlaceholders(player, "%betonquest_items:" + pointJoiner + "%"));
        if (itemStack != null) {
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.displayName(Component.text(ChatColor.GREEN + getName() + ChatColor.GRAY + " - " + ChatColor.YELLOW + pointAmount + ChatColor.GRAY + " Stored"));
            List<Component> lore = new ArrayList<>();
            lore.add(Component.empty());
            lore.add(Component.text(ChatColor.GRAY + "Item Info:"));
            lore.add(Component.text(ChatColor.GOLD + "⛏ Mining Speed: " + ChatColor.YELLOW + getMiningSpeed() + "secs"));
            lore.add(Component.text(ChatColor.YELLOW + "⛂ Sell Price: " + ChatColor.YELLOW + getPrice()));
            lore.add(Component.text(ChatColor.BLUE + "❂ Weight: " + ChatColor.BLUE + getWeight()));
            itemStack.setItemMeta(itemMeta);
            itemStack.lore(lore);
            return itemStack;
        }
        return null;
    }
}
