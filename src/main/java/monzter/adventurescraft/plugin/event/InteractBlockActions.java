package monzter.adventurescraft.plugin.event;

import io.lumine.mythic.lib.api.item.NBTItem;
import me.clip.placeholderapi.PlaceholderAPI;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.event.extras.PetEgg;
import net.Indyuce.mmoitems.MMOItems;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class InteractBlockActions implements Listener {
    private AdventuresCraft plugin;
    private final List<Material> tools = Arrays.asList(Material.WOODEN_AXE, Material.WOODEN_HOE, Material.WOODEN_PICKAXE, Material.WOODEN_SHOVEL,
            Material.STONE_AXE, Material.STONE_HOE, Material.STONE_PICKAXE, Material.STONE_SHOVEL,
            Material.IRON_AXE, Material.IRON_HOE, Material.IRON_PICKAXE, Material.IRON_SHOVEL,
            Material.GOLDEN_AXE, Material.GOLDEN_HOE, Material.GOLDEN_PICKAXE, Material.GOLDEN_SHOVEL,
            Material.DIAMOND_AXE, Material.DIAMOND_HOE, Material.DIAMOND_PICKAXE, Material.DIAMOND_SHOVEL,
            Material.NETHERITE_AXE, Material.NETHERITE_HOE, Material.NETHERITE_PICKAXE, Material.NETHERITE_SHOVEL);

    public InteractBlockActions(AdventuresCraft plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void enchantingTable(PlayerInteractEvent event) {
        if (event.getClickedBlock() != null && event.getClickedBlock().getType().equals(Material.ENCHANTING_TABLE)) {
            Player player = event.getPlayer();
            ItemStack itemStack = event.getItem();
            if (itemStack != null) {
                if (tools.contains(itemStack.getType())) {
                    event.setCancelled(true);
                    Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "bs open Enchantment " + player.getName());
                    player.getWorld().playSound(player.getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, 1f, .5f);
                } else {
                    event.setCancelled(true);
                    player.sendMessage(ChatColor.RED + "You must be holding a tool to enchant!");
                }
            } else {
                event.setCancelled(true);
                player.sendMessage(ChatColor.RED + "You must be holding a tool to enchant!");
            }
        }
    }
}