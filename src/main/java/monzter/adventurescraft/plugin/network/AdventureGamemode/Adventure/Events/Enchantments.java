package monzter.adventurescraft.plugin.network.AdventureGamemode.Adventure.Events;

import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.enchanting.CalculateEnchantments;
import monzter.adventurescraft.plugin.utilities.general.ItemAdder;
import net.Indyuce.mmocore.api.event.CustomBlockMineEvent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Enchantments implements Listener {
    private final AdventuresCraft plugin;
    private final CalculateEnchantments calculateEnchantments;
    private final ItemAdder itemAdder;


    public Enchantments(AdventuresCraft plugin, CalculateEnchantments calculateEnchantments, ItemAdder itemAdder) {
        this.plugin = plugin;
        this.calculateEnchantments = calculateEnchantments;
        this.itemAdder = itemAdder;
    }

    List<Material> fortuneBlockList = Arrays.asList(Material.COAL_ORE, Material.COAL_BLOCK, Material.IRON_ORE, Material.IRON_INGOT, Material.GOLD_ORE, Material.GOLD_INGOT,
            Material.REDSTONE_ORE, Material.REDSTONE, Material.LAPIS_ORE, Material.LAPIS_BLOCK, Material.DIAMOND_ORE, Material.DIAMOND);
    List<Material> yieldBlockList = Arrays.asList(Material.WHEAT, Material.CARROTS, Material.POTATOES, Material.BEETROOT, Material.SUGAR_CANE, Material.PUMPKIN,
            Material.MELON, Material.RED_MUSHROOM, Material.BROWN_MUSHROOM);

    @EventHandler
    public void enchantments(CustomBlockMineEvent event) {
        switch (plugin.SERVER) {
            case "Adventure":
                if (event.getPlayer() != null) {
                    Player player = event.getPlayer();
                    if (event.canBreak()) {
                        if (calculateEnchantments.calculateEnchantments(player, "Fortune") > 0) {
                            if (fortuneBlockList.contains(event.getBlock().getType()))
                                fortune(player, calculateEnchantments.calculateEnchantments(player, "Fortune"), event.getBlock().getType());
                        }
                        if (calculateEnchantments.calculateEnchantments(player, "Yield") > 0) {
                            if (yieldBlockList.contains(event.getBlock().getType()))
                                yield(player, calculateEnchantments.calculateEnchantments(player, "Yield"), event.getBlock().getType());
                        }
                        if (calculateEnchantments.calculateEnchantments(player, "Experience") > 0) {
                            if (fortuneBlockList.contains(event.getBlock().getType()))
                                giveEXP(player, calculateEnchantments.calculateEnchantments(player, "Experience"));
                        }
                        if (calculateEnchantments.calculateEnchantments(player, "Furtherance") > 0) {
                            if (fortuneBlockList.contains(event.getBlock().getType()))
                                furtherance(player);
                        }
                    }
                }
        }
    }

    List<PotionEffectType> potionEffectTypes = Arrays.asList(PotionEffectType.JUMP, PotionEffectType.SPEED, PotionEffectType.FAST_DIGGING,
            PotionEffectType.DAMAGE_RESISTANCE, PotionEffectType.NIGHT_VISION, PotionEffectType.REGENERATION, PotionEffectType.SLOW_FALLING, PotionEffectType.WATER_BREATHING, PotionEffectType.INCREASE_DAMAGE);

    private void furtherance(Player player) {
        int random = ThreadLocalRandom.current().nextInt(1, potionEffectTypes.size() + 1);
        player.addPotionEffect(new PotionEffect(potionEffectTypes.get(random), 20 * 10, 5));
    }

    private void giveEXP(Player player, int enchantmentLevel) {
        int randomEXP = ThreadLocalRandom.current().nextInt(1, (enchantmentLevel + 1) * 2);
        player.giveExp(randomEXP);
    }

    int maxOre;
    int maxOreRedLapis;

    private void fortune(Player player, int enchantmentLevel, Material material) {
        plugin.getLogger().info("RAN!");
        switch (enchantmentLevel) {
            case 1:
                maxOre = 1;
                maxOreRedLapis = 2;
                break;
            case 2:
                maxOre = 2;
                maxOreRedLapis = 3;
                break;
            case 3:
                maxOre = 3;
                maxOreRedLapis = 4;
                break;
        }

        int randomOreNum = ThreadLocalRandom.current().nextInt(1, maxOre + 1);
        int randomOreRedLapis = ThreadLocalRandom.current().nextInt(1, maxOreRedLapis + 1);
        switch (material) {
            case IRON_ORE:
            case GOLD_ORE:
            case DIAMOND_ORE:
                itemAdder.itemAdder(player, new ItemStack(Material.valueOf(material.toString().replace("ORE", "INGOT")), randomOreNum));
                break;
            case COAL_BLOCK:
            case IRON_BLOCK:
            case GOLD_BLOCK:
            case LAPIS_BLOCK:
            case REDSTONE_BLOCK:
            case DIAMOND_BLOCK:
                itemAdder.itemAdder(player, new ItemStack(material, randomOreNum));
                break;
            case COAL_ORE:
                itemAdder.itemAdder(player, new ItemStack(Material.valueOf(material.toString().replace("_ORE", "")), randomOreNum));
                break;
            case REDSTONE_ORE:
                itemAdder.itemAdder(player, new ItemStack(Material.valueOf(material.toString().replace("_ORE", "")), randomOreRedLapis));
                break;
            case LAPIS_ORE:
                itemAdder.itemAdder(player, new ItemStack(Material.valueOf(material.toString().replace("ORE", "LAZULI")), randomOreRedLapis));
                break;
        }
    }

    int maxYield;

    private void yield(Player player, int enchantmentLevel, Material material) {
        switch (enchantmentLevel) {
            case 1:
                maxYield = 1;
                break;
            case 2:
                maxYield = 2;
                break;
            case 3:
                maxYield = 3;
                break;
        }

        int randomYield = ThreadLocalRandom.current().nextInt(1, maxYield + 1);
        switch (material) {
            default:
                itemAdder.itemAdder(player, new ItemStack(material, randomYield));
                break;
        }
    }
}