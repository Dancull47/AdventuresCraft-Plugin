package monzter.adventurescraft.plugin.shared.events;

import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import monzter.adventurescraft.plugin.utilities.luckperms.PermissionLP;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class EnchantingTableInteraction implements Listener {
    private final AdventuresCraft plugin;
    private final List<Material> tools = Arrays.asList(Material.WOODEN_AXE, Material.WOODEN_HOE, Material.WOODEN_PICKAXE, Material.WOODEN_SHOVEL,
            Material.STONE_AXE, Material.STONE_HOE, Material.STONE_PICKAXE, Material.STONE_SHOVEL,
            Material.IRON_AXE, Material.IRON_HOE, Material.IRON_PICKAXE, Material.IRON_SHOVEL,
            Material.GOLDEN_AXE, Material.GOLDEN_HOE, Material.GOLDEN_PICKAXE, Material.GOLDEN_SHOVEL,
            Material.DIAMOND_AXE, Material.DIAMOND_HOE, Material.DIAMOND_PICKAXE, Material.DIAMOND_SHOVEL,
            Material.NETHERITE_AXE, Material.NETHERITE_HOE, Material.NETHERITE_PICKAXE, Material.NETHERITE_SHOVEL);
    private final PermissionLP permissionLP;
    private final SoundManager soundManager;



    public EnchantingTableInteraction(AdventuresCraft plugin, PermissionLP permissionLP, SoundManager soundManager) {
        this.plugin = plugin;
        this.permissionLP = permissionLP;
        this.soundManager = soundManager;
    }

    @EventHandler
    public void enchantingTable(PlayerInteractEvent event) {
        if (event.getClickedBlock() != null && event.getClickedBlock().getType().equals(Material.ENCHANTING_TABLE)) {
            final Player player = event.getPlayer();
            final ItemStack itemStack = event.getItem();
            if (itemStack != null) {
                if (tools.contains(itemStack.getType())) {
                    player.performCommand("enchantmentShop");
                    soundManager.playSound(player, Sound.BLOCK_ENCHANTMENT_TABLE_USE, 1, 1);
                } else {
                    player.sendMessage(ChatColor.RED + "You must be holding a tool to enchant!");
                    soundManager.soundNo(player, 1);
                }
            } else {
                player.sendMessage(ChatColor.RED + "You must be holding a tool to enchant!");
                soundManager.soundNo(player, 1);
            }
        }
        event.setCancelled(true);
    }

    @EventHandler
    public void enderChest(PlayerInteractEvent event) {
        if (event.getClickedBlock() != null && event.getClickedBlock().getType().equals(Material.ENDER_CHEST)) {
            final Player player = event.getPlayer();
            try {
                permissionLP.givePermission(player, "bank.open.command");
                player.performCommand("banks open");
            } finally {
                permissionLP.takePermission(player, "bank.open.command");
                player.getWorld().playSound(event.getClickedBlock().getLocation(), Sound.BLOCK_ENDER_CHEST_OPEN, 1, 1);
            }
            event.setCancelled(true);
        }
    }
}