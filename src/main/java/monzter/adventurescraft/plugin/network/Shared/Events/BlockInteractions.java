package monzter.adventurescraft.plugin.network.Shared.Events;

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

public class BlockInteractions implements Listener {
    private final AdventuresCraft plugin;
    private final SoundManager soundManager;
    private final PermissionLP permissionLP;


    private final List<Material> blocks = Arrays.asList(Material.ENCHANTING_TABLE, Material.CAULDRON, Material.ANVIL, Material.CHEST,
            Material.CHEST_MINECART, Material.ENDER_CHEST, Material.TRAPPED_CHEST, Material.CHEST, Material.BEACON, Material.BEE_NEST,
            Material.SMITHING_TABLE, Material.BARREL, Material.BREWING_STAND, Material.COMMAND_BLOCK,
            Material.CARTOGRAPHY_TABLE, Material.SHULKER_BOX, Material.GRINDSTONE, Material.LECTERN);
    private final List<Material> tools = Arrays.asList(Material.WOODEN_AXE, Material.WOODEN_HOE, Material.WOODEN_PICKAXE, Material.WOODEN_SHOVEL,
            Material.STONE_AXE, Material.STONE_HOE, Material.STONE_PICKAXE, Material.STONE_SHOVEL,
            Material.IRON_AXE, Material.IRON_HOE, Material.IRON_PICKAXE, Material.IRON_SHOVEL,
            Material.GOLDEN_AXE, Material.GOLDEN_HOE, Material.GOLDEN_PICKAXE, Material.GOLDEN_SHOVEL,
            Material.DIAMOND_AXE, Material.DIAMOND_HOE, Material.DIAMOND_PICKAXE, Material.DIAMOND_SHOVEL,
            Material.NETHERITE_AXE, Material.NETHERITE_HOE, Material.NETHERITE_PICKAXE, Material.NETHERITE_SHOVEL);

    public BlockInteractions(AdventuresCraft plugin, SoundManager soundManager, PermissionLP permissionLP) {
        this.plugin = plugin;
        this.soundManager = soundManager;
        this.permissionLP = permissionLP;
    }

    @EventHandler
    public void cancel(PlayerInteractEvent event) {
        if (event.getClickedBlock() != null)
            if (blocks.contains(event.getClickedBlock().getType())) {
                event.setCancelled(true);
            }
    }


    @EventHandler
    public void enchantingTable(PlayerInteractEvent event) {
        switch (plugin.SERVER) {
            case "Prison":
            case "Cell":
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
                    event.setCancelled(true);
                }
                break;
            case "Adventure":
            case "Home":
                if (event.getClickedBlock() != null && event.getClickedBlock().getType().equals(Material.ENCHANTING_TABLE)) {
                    final Player player = event.getPlayer();
                    final ItemStack itemStack = event.getItem();
                    if (itemStack != null) {
                        if (tools.contains(itemStack.getType())) {
                            player.performCommand("enchant");
                            soundManager.playSound(player, Sound.BLOCK_ENCHANTMENT_TABLE_USE, 1, 1);
                        } else {
                            player.sendMessage(ChatColor.RED + "You must be holding a tool to enchant!");
                            soundManager.soundNo(player, 1);
                        }
                    } else {
                        player.sendMessage(ChatColor.RED + "You must be holding a tool to enchant!");
                        soundManager.soundNo(player, 1);
                    }
                    event.setCancelled(true);
                }
                break;
        }
    }

    @EventHandler
    public void enderChest(PlayerInteractEvent event) {
        switch (plugin.SERVER) {
            case "Prison":
            case "Cell":
            case "Adventure":
            case "Home":
                if (event.getClickedBlock() != null && event.getClickedBlock().getType().equals(Material.ENDER_CHEST)) {
                    final Player player = event.getPlayer();
                    permissionLP.giveTempPermission(player, "bank.open.command", 2, "s");
                    player.performCommand("banks open");
                }
                event.setCancelled(true);
        }
    }
}