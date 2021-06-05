package monzter.adventurescraft.plugin.network.AdventureGamemode.Adventure.Events;

import io.lumine.mythic.lib.api.item.NBTItem;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.enchanting.CalculateEnchantments;
import monzter.adventurescraft.plugin.utilities.general.AreaCheck;
import monzter.adventurescraft.plugin.utilities.general.ChanceCheck;
import monzter.adventurescraft.plugin.utilities.general.ItemAdder;
import net.Indyuce.mmocore.api.event.CustomBlockMineEvent;
import net.Indyuce.mmoitems.MMOItems;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Catalysts implements Listener {
    private final AdventuresCraft plugin;
    private final CalculateEnchantments calculateEnchantments;
    private final ItemAdder itemAdder;
    private final AreaCheck areaCheck;
    private final MMOItems mmoItems;
    private final ChanceCheck chanceCheck;


    public Catalysts(AdventuresCraft plugin, CalculateEnchantments calculateEnchantments, ItemAdder itemAdder, AreaCheck areaCheck, MMOItems mmoItems, ChanceCheck chanceCheck) {
        this.plugin = plugin;
        this.calculateEnchantments = calculateEnchantments;
        this.itemAdder = itemAdder;
        this.areaCheck = areaCheck;
        this.mmoItems = mmoItems;
        this.chanceCheck = chanceCheck;
    }

    List<String> regions = Arrays.asList(ChatColor.BLACK + "Coal Mine", ChatColor.YELLOW + "Gold Mine", ChatColor.DARK_RED + "Redstone Mine",
            ChatColor.BLUE + "Lapis Mine", ChatColor.AQUA + "Diamond Mine", ChatColor.GREEN + "Emerald Mine",
            ChatColor.GREEN + "Oak Forest", ChatColor.GREEN + "Spruce Forest", ChatColor.GREEN + "Dark Oak Forest",
            ChatColor.GREEN + "Birch Forest", ChatColor.GREEN + "Acacia Forest", ChatColor.GREEN + "Jungle Forest");
    double chance = 0.00347222222;

    @EventHandler
    public void catalyst(CustomBlockMineEvent event) {
        switch (plugin.SERVER) {
            case "Adventure":
                if (event.getPlayer() != null) {
                    Player player = event.getPlayer();
                    if (event.canBreak()) {
                        int randomNumber = ThreadLocalRandom.current().nextInt(1, 3);
                        if (regions.contains(areaCheck.getAreaName(player))) {
                            final NBTItem nbtItem = NBTItem.get(event.getPlayer().getInventory().getItemInOffHand());
                            final String id = MMOItems.plugin.getID(nbtItem);
                            if (id != null)
                                switch (event.getBlock().getType()) {
                                    case COAL_ORE:
                                    case COAL_BLOCK:
                                        if (id.equals("COAL_CATALYST"))
                                            itemAdder.itemAdder(player, new ItemStack(Material.COAL, randomNumber));
                                        break;
                                    case IRON_ORE:
                                    case IRON_BLOCK:
                                        if (id.equals("IRON_CATALYST"))
                                            itemAdder.itemAdder(player, new ItemStack(Material.IRON_INGOT, randomNumber));
                                        break;
                                    case GOLD_ORE:
                                    case GOLD_BLOCK:
                                        if (id.equals("GOLD_CATALYST"))
                                            itemAdder.itemAdder(player, new ItemStack(Material.GOLD_INGOT, randomNumber));
                                        break;
                                    case REDSTONE_ORE:
                                    case REDSTONE_BLOCK:
                                        if (id.equals("REDSTONE_CATALYST"))
                                            itemAdder.itemAdder(player, new ItemStack(Material.REDSTONE, randomNumber));
                                        break;
                                    case LAPIS_ORE:
                                    case LAPIS_BLOCK:
                                        if (id.equals("LAPIS_CATALYST"))
                                            itemAdder.itemAdder(player, new ItemStack(Material.LAPIS_LAZULI, randomNumber));
                                        break;
                                    case DIAMOND_ORE:
                                    case DIAMOND_BLOCK:
                                        if (id.equals("DIAMOND_CATALYST"))
                                            itemAdder.itemAdder(player, new ItemStack(Material.DIAMOND, randomNumber));
                                        break;
                                    case EMERALD_ORE:
                                    case EMERALD_BLOCK:
                                        if (id.equals("EMERALD_CATALYST"))
                                            itemAdder.itemAdder(player, new ItemStack(Material.EMERALD, randomNumber));
                                        break;
                                    case OAK_LOG:
                                        if (id.equals("WOOD_CATALYST"))
                                            itemAdder.itemAdder(player, new ItemStack(Material.OAK_LOG, randomNumber));
                                        else if (id.equals("WOOD_CATALYST2"))
                                            if (chanceCheck.chanceCheck(chance))
                                                itemAdder.itemAdder(player, mmoItems.getItem("MATERIAL", "ENCHANTED_OAK_LOG"));
                                        break;
                                    case SPRUCE_LOG:
                                        if (id.equals("WOOD_CATALYST"))
                                            itemAdder.itemAdder(player, new ItemStack(Material.SPRUCE_LOG, randomNumber));
                                        else if (id.equals("WOOD_CATALYST2"))
                                            if (chanceCheck.chanceCheck(chance))
                                                itemAdder.itemAdder(player, mmoItems.getItem("MATERIAL", "ENCHANTED_SPRUCE_LOG"));
                                        break;
                                    case DARK_OAK_LOG:
                                        if (id.equals("WOOD_CATALYST"))
                                            itemAdder.itemAdder(player, new ItemStack(Material.DARK_OAK_LOG, randomNumber));
                                        else if (id.equals("WOOD_CATALYST2"))
                                            if (chanceCheck.chanceCheck(chance))
                                                itemAdder.itemAdder(player, mmoItems.getItem("MATERIAL", "ENCHANTED_DARK_OAK_LOG"));
                                        break;
                                    case BIRCH_LOG:
                                        if (id.equals("WOOD_CATALYST"))
                                            itemAdder.itemAdder(player, new ItemStack(Material.BIRCH_LOG, randomNumber));
                                        else if (id.equals("WOOD_CATALYST2"))
                                            if (chanceCheck.chanceCheck(chance))
                                                itemAdder.itemAdder(player, mmoItems.getItem("MATERIAL", "ENCHANTED_BIRCH_LOG"));
                                        break;
                                    case ACACIA_LOG:
                                        if (id.equals("WOOD_CATALYST"))
                                            itemAdder.itemAdder(player, new ItemStack(Material.ACACIA_LOG, randomNumber));
                                        else if (id.equals("WOOD_CATALYST2"))
                                            if (chanceCheck.chanceCheck(chance))
                                                itemAdder.itemAdder(player, mmoItems.getItem("MATERIAL", "ENCHANTED_ACACIA_LOG"));
                                        break;
                                    case JUNGLE_LOG:
                                        if (id.equals("WOOD_CATALYST"))
                                            itemAdder.itemAdder(player, new ItemStack(Material.JUNGLE_LOG, randomNumber));
                                        else if (id.equals("WOOD_CATALYST2"))
                                            if (chanceCheck.chanceCheck(chance))
                                                itemAdder.itemAdder(player, mmoItems.getItem("MATERIAL", "ENCHANTED_JUNGLE_LOG"));
                                        break;
                                }
                        }
                    }
                }
        }
    }

}