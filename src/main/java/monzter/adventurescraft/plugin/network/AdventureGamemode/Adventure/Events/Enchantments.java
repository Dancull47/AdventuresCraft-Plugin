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

    List<Material> blockList = Arrays.asList(Material.COAL_ORE, Material.COAL_BLOCK, Material.IRON_ORE, Material.IRON_INGOT, Material.GOLD_ORE, Material.GOLD_INGOT,
            Material.REDSTONE_ORE, Material.REDSTONE, Material.LAPIS_ORE, Material.LAPIS_BLOCK, Material.DIAMOND_ORE, Material.DIAMOND);

    @EventHandler
    public void graveyard(CustomBlockMineEvent event) {
        switch (plugin.SERVER) {
            case "Adventure":
                if (event.getPlayer() != null) {
                    Player player = event.getPlayer();
                    if (blockList.contains(event.getBlock().getType()))
                        if (calculateEnchantments.calculateEnchantments(player, "Fortune") > 0)
                            fortune(player, calculateEnchantments.calculateEnchantments(player, "Fortune"), event.getBlock().getType());
                }
        }
    }

    int maxOre;
    int maxOreRedLapis;

    private void fortune(Player player, int enchantmentLevel, Material material) {
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

        int randomOreNum = ThreadLocalRandom.current().nextInt(0, maxOre + 1);
        int randomOreRedLapis = ThreadLocalRandom.current().nextInt(0, maxOreRedLapis + 1);
        plugin.getLogger().info(String.valueOf(randomOreNum));
        plugin.getLogger().info(String.valueOf(randomOreRedLapis));
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
}