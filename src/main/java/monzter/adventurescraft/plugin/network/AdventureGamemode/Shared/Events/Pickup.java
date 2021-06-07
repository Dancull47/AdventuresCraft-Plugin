package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.Events;

import io.lumine.mythic.lib.api.item.NBTItem;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.beton.BetonPointsManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAttemptPickupItemEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class Pickup implements Listener {
    private final AdventuresCraft plugin;
    private final BetonPointsManager betonPointsManager;
    private final net.Indyuce.mmoitems.MMOItems mmoItems;
    public final List<ItemStack> MINING_MATERIALS;

    public final List<Material> FARMING_MATERIALS = Arrays.asList(Material.WHEAT_SEEDS, Material.WHEAT, Material.CARROTS, Material.POTATOES, Material.SUGAR_CANE, Material.BEETROOT,
            Material.MELON_SLICE, Material.PUMPKIN, Material.RED_MUSHROOM, Material.BROWN_MUSHROOM);
    public final List<Material> FORAGING_MATERIALS = Arrays.asList(Material.OAK_LOG, Material.SPRUCE_LOG, Material.DARK_OAK_LOG, Material.BIRCH_LOG, Material.ACACIA_LOG, Material.JUNGLE_LOG,
            Material.HONEYCOMB);
    public final List<Material> SLAYER_MATERIALS = Arrays.asList(Material.ROTTEN_FLESH, Material.BONE, Material.STONE_BUTTON, Material.ARROW, Material.SPIDER_EYE, Material.STRING,
            Material.GUNPOWDER, Material.SLIME_BALL, Material.ENDER_PEARL, Material.GHAST_TEAR, Material.MAGMA_CREAM, Material.BLAZE_ROD);

    private final List<Material> MMOItems = Arrays.asList(Material.STONE_BUTTON);


    public Pickup(AdventuresCraft plugin, BetonPointsManager betonPointsManager, net.Indyuce.mmoitems.MMOItems mmoItems) {
        this.plugin = plugin;
        this.betonPointsManager = betonPointsManager;
        this.mmoItems = mmoItems;
        MINING_MATERIALS = Arrays.asList(new ItemStack(Material.STONE), mmoItems.getItem("MATERIAL", "BONE_FRAGMENT"), new ItemStack(Material.COBBLESTONE), new ItemStack(Material.COAL), new ItemStack(Material.IRON_INGOT), new ItemStack(Material.GOLD_INGOT), new ItemStack(Material.REDSTONE),
                new ItemStack(Material.LAPIS_LAZULI), new ItemStack(Material.DIAMOND), new ItemStack(Material.EMERALD), new ItemStack(Material.END_STONE), new ItemStack(Material.OBSIDIAN));
    }


    @EventHandler
    public void pickup(PlayerAttemptPickupItemEvent event) {
        switch (plugin.SERVER) {
            case "Adventure":
            case "Home":
                Player player = event.getPlayer();
                if (event.getItem().getItemStack().getEnchantments().isEmpty())
                    if (MINING_MATERIALS.contains(event.getItem().getItemStack().getType()) || FARMING_MATERIALS.contains(event.getItem().getItemStack().getType()) ||
                            FORAGING_MATERIALS.contains(event.getItem().getItemStack().getType()) || SLAYER_MATERIALS.contains(event.getItem().getItemStack().getType()))
                        if (!MMOItems.contains(event.getItem().getItemStack().getType())) {
                            addPoint(player, event.getItem().getItemStack().getType().toString(), event.getItem().getItemStack().getAmount());
                            event.getItem().remove();
                        } else {
                            addPoint(player, event.getItem().getItemStack());
                            event.getItem().remove();
                        }
        }
    }

    private void addPoint(Player player, String point, int amount) {
        betonPointsManager.givePoint(player, "items." + point, amount);
    }

    private void addPoint(Player player, ItemStack itemStack) {
        betonPointsManager.givePoint(player, "items." + mmoItems.getID(NBTItem.get(itemStack)), itemStack.getAmount());
    }
}