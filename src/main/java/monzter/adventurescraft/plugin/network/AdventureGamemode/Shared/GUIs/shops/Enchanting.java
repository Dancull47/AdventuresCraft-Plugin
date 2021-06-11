package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.shops;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Dependency;
import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.PaginatedPane;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import dev.dbassett.skullcreator.SkullCreator;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.GUI.GUIHelper;
import monzter.adventurescraft.plugin.utilities.enums.Prefix;
import monzter.adventurescraft.plugin.utilities.general.ConsoleCommand;
import monzter.adventurescraft.plugin.utilities.general.ShopOpener;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import net.kyori.adventure.text.Component;
import org.apache.commons.lang.WordUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Enchanting extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final GUIHelper guiHelper;
    private final ConsoleCommand consoleCommand;
    private final ShopOpener shopOpener;
    private final SoundManager soundManager;


    private final List<Material> HELMET = Arrays.asList(Material.LEATHER_HELMET, Material.CHAINMAIL_HELMET, Material.IRON_HELMET, Material.GOLDEN_HELMET,
            Material.DIAMOND_HELMET, Material.NETHERITE_HELMET, Material.TURTLE_HELMET, Material.PLAYER_HEAD);
    private final List<Material> CHESTPLATE = Arrays.asList(Material.LEATHER_CHESTPLATE, Material.CHAINMAIL_CHESTPLATE, Material.IRON_CHESTPLATE, Material.GOLDEN_CHESTPLATE,
            Material.DIAMOND_CHESTPLATE, Material.NETHERITE_CHESTPLATE);
    private final List<Material> LEGGINGS = Arrays.asList(Material.LEATHER_LEGGINGS, Material.CHAINMAIL_LEGGINGS, Material.IRON_LEGGINGS, Material.GOLDEN_LEGGINGS,
            Material.DIAMOND_LEGGINGS, Material.NETHERITE_LEGGINGS);
    private final List<Material> BOOTS = Arrays.asList(Material.LEATHER_BOOTS, Material.CHAINMAIL_BOOTS, Material.IRON_BOOTS, Material.GOLDEN_BOOTS,
            Material.DIAMOND_BOOTS, Material.NETHERITE_BOOTS);
    private final List<Material> TOOLS = Arrays.asList(Material.WOODEN_AXE, Material.WOODEN_HOE, Material.WOODEN_PICKAXE, Material.WOODEN_SHOVEL,
            Material.STONE_AXE, Material.STONE_HOE, Material.STONE_PICKAXE, Material.STONE_SHOVEL,
            Material.IRON_AXE, Material.IRON_HOE, Material.IRON_PICKAXE, Material.IRON_SHOVEL,
            Material.GOLDEN_AXE, Material.GOLDEN_HOE, Material.GOLDEN_PICKAXE, Material.GOLDEN_SHOVEL,
            Material.DIAMOND_AXE, Material.DIAMOND_HOE, Material.DIAMOND_PICKAXE, Material.DIAMOND_SHOVEL,
            Material.NETHERITE_AXE, Material.NETHERITE_HOE, Material.NETHERITE_PICKAXE, Material.NETHERITE_SHOVEL);
    private final List<Material> MELEE = Arrays.asList(Material.WOODEN_SWORD, Material.STONE_SWORD, Material.IRON_SWORD, Material.GOLDEN_SWORD,
            Material.DIAMOND_SWORD, Material.NETHERITE_SWORD, Material.LEAD);
    private final List<Material> BOW = Arrays.asList(Material.BOW, Material.CROSSBOW);

    public Enchanting(AdventuresCraft plugin, GUIHelper guiHelper, ShopOpener shopOpener, ConsoleCommand consoleCommand, SoundManager soundManager) {
        this.plugin = plugin;
        this.guiHelper = guiHelper;
        this.consoleCommand = consoleCommand;
        this.shopOpener = shopOpener;
        this.soundManager = soundManager;
    }

    @CommandAlias("EnchanterShop")
    public void pets(Player player) {
        if (player.hasPermission("SHOPS")) {

            ChestGui gui = new ChestGui(6, guiHelper.guiName("Enchanter"));
            gui.setOnGlobalClick(event -> event.setCancelled(true));

            PaginatedPane page = new PaginatedPane(0, 0, 9, 6);
            OutlinePane background = new OutlinePane(0, 0, 9, 6, Pane.Priority.LOWEST);
            StaticPane display = new StaticPane(0, 0, 9, 5, Pane.Priority.LOW);

            page.addPane(0, background);
            page.addPane(0, display);

            background.addItem(new GuiItem(guiHelper.background(Material.PINK_STAINED_GLASS_PANE)));
            background.setRepeat(true);

            display.addItem(new GuiItem(wizardShop(), e -> {
                shopOpener.shopOpener(player, "enchantingshop");
                pets(player);
            }), 0, 2);

            display.addItem(helmetEnchantments(player), 4, 1);
            display.addItem(chestplateEnchantments(player), 4, 2);
            display.addItem(leggingsEnchantments(player), 4, 3);
            display.addItem(bootsEnchantments(player), 4, 4);

            display.addItem(weaponEnchantments(player), 3, 2);
            display.addItem(bowEnchantments(player), 5, 2);
            display.addItem(toolEnchantments(player), 6, 2);

            gui.addPane(page);
            gui.show(player);
        }
    }


    private ItemStack wizardShop() {
        final ItemStack lobby = new ItemStack(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjgyYzJiZjlkODJmNDBkNzExZWZmNWFkMmQ1MjBiYWJhM2U3YjRlYWI1MTAxYmZjNGQwZDg2NzA5ZmQwZWEzOSJ9fX0="));
        final ItemMeta lobbyItemMeta = lobby.getItemMeta();

        lobbyItemMeta.displayName(Component.text(ChatColor.GREEN + "Wizard Shop"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "Purchase common resources");
        lore.add(ChatColor.GRAY + "for " + ChatColor.LIGHT_PURPLE + "Enchanting " + ChatColor.GRAY + "your gear!");
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View");

        lobby.setItemMeta(lobbyItemMeta);
        lobby.setLore(lore);

        return lobby;
    }


    private GuiItem helmetEnchantments(Player player) {
        ItemStack itemStack = new ItemStack(Material.LEATHER_HELMET);
        String tool = "HELMET";
        if (hasTool(player, tool))
            itemStack.setType(Material.NETHERITE_HELMET);

        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        itemMeta.displayName(Component.text(ChatColor.GREEN + "Helmet Enchantments"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        if (!hasTool(player, tool)) {
            lore.add(ChatColor.RED + "You must be holding a");
            lore.add(ChatColor.GOLD + WordUtils.capitalizeFully(tool) + ChatColor.RED + " to Enchant it!");
        } else
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View");

        itemStack.setItemMeta(itemMeta);
        itemStack.setLore(lore);

        return new GuiItem(itemStack, e -> {
            if (hasTool(player, tool))
                consoleCommand.consoleCommand("mi stations open helmet-enchantments " + player.getName());
            else {
                player.sendMessage(ChatColor.RED + "You must be holding a " + ChatColor.GOLD + WordUtils.capitalizeFully(tool) + ChatColor.RED + " to enchant it!");
                soundManager.soundNo(player, 1);
            }
        });
    }

    private GuiItem chestplateEnchantments(Player player) {
        ItemStack itemStack = new ItemStack(Material.LEATHER_CHESTPLATE);
        String tool = "CHESTPLATE";
        if (hasTool(player, tool))
            itemStack.setType(Material.NETHERITE_CHESTPLATE);

        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        itemMeta.displayName(Component.text(ChatColor.GREEN + "Chestplate Enchantments"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        if (!hasTool(player, tool)) {
            lore.add(ChatColor.RED + "You must be holding a");
            lore.add(ChatColor.GOLD + WordUtils.capitalizeFully(tool) + ChatColor.RED + " to Enchant it!");
        } else
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View");

        itemStack.setItemMeta(itemMeta);
        itemStack.setLore(lore);

        return new GuiItem(itemStack, e -> {
            if (hasTool(player, tool))
                consoleCommand.consoleCommand("mi stations open chestplate-enchantments " + player.getName());
            else {
                player.sendMessage(ChatColor.RED + "You must be holding a " + ChatColor.GOLD + WordUtils.capitalizeFully(tool) + ChatColor.RED + " to enchant it!");
                soundManager.soundNo(player, 1);
            }
        });
    }

    private GuiItem leggingsEnchantments(Player player) {
        ItemStack itemStack = new ItemStack(Material.LEATHER_LEGGINGS);
        String tool = "LEGGINGS";
        if (hasTool(player, tool))
            itemStack.setType(Material.NETHERITE_LEGGINGS);

        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        itemMeta.displayName(Component.text(ChatColor.GREEN + "Leggings Enchantments"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        if (!hasTool(player, tool)) {
            lore.add(ChatColor.RED + "You must be holding a");
            lore.add(ChatColor.GOLD + WordUtils.capitalizeFully(tool) + ChatColor.RED + " to Enchant it!");
        } else
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View");

        itemStack.setItemMeta(itemMeta);
        itemStack.setLore(lore);

        return new GuiItem(itemStack, e -> {
            if (hasTool(player, tool))
                consoleCommand.consoleCommand("mi stations open leggings-enchantments " + player.getName());
            else {
                player.sendMessage(ChatColor.RED + "You must be holding a " + ChatColor.GOLD + WordUtils.capitalizeFully(tool) + ChatColor.RED + " to enchant it!");
                soundManager.soundNo(player, 1);
            }
        });
    }

    private GuiItem bootsEnchantments(Player player) {
        ItemStack itemStack = new ItemStack(Material.LEATHER_BOOTS);
        String tool = "BOOTS";
        if (hasTool(player, tool))
            itemStack.setType(Material.NETHERITE_BOOTS);

        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        itemMeta.displayName(Component.text(ChatColor.GREEN + "Boots Enchantments"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        if (!hasTool(player, tool)) {
            lore.add(ChatColor.RED + "You must be holding a");
            lore.add(ChatColor.GOLD + WordUtils.capitalizeFully(tool) + ChatColor.RED + " to Enchant it!");
        } else
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View");

        itemStack.setItemMeta(itemMeta);
        itemStack.setLore(lore);

        return new GuiItem(itemStack, e -> {
            if (hasTool(player, tool))
                consoleCommand.consoleCommand("mi stations open boots-enchantments " + player.getName());
            else {
                player.sendMessage(ChatColor.RED + "You must be holding a " + ChatColor.GOLD + WordUtils.capitalizeFully(tool) + ChatColor.RED + " to enchant it!");
                soundManager.soundNo(player, 1);
            }
        });
    }

    private GuiItem weaponEnchantments(Player player) {
        ItemStack itemStack = new ItemStack(Material.WOODEN_SWORD);
        String tool = "WEAPON";
        if (hasTool(player, tool))
            itemStack.setType(Material.NETHERITE_SWORD);

        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        itemMeta.displayName(Component.text(ChatColor.GREEN + "Melee Weapon Enchantments"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        if (!hasTool(player, tool)) {
            lore.add(ChatColor.RED + "You must be holding a " + ChatColor.GOLD + "Melee");
            lore.add(ChatColor.GOLD + WordUtils.capitalizeFully(tool) + ChatColor.RED + " to Enchant it!");
        } else
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View");

        itemStack.setItemMeta(itemMeta);
        itemStack.setLore(lore);

        return new GuiItem(itemStack, e -> {
            if (hasTool(player, tool))
                consoleCommand.consoleCommand("mi stations open weapon-enchantments " + player.getName());
            else {
                player.sendMessage(ChatColor.RED + "You must be holding a " + ChatColor.GOLD + "Melee " + WordUtils.capitalizeFully(tool) + ChatColor.RED + " to enchant it!");
                soundManager.soundNo(player, 1);
            }
        });
    }

    private GuiItem bowEnchantments(Player player) {
        ItemStack itemStack = new ItemStack(Material.BOW);
        String tool = "BOW";
        if (hasTool(player, tool))
            itemStack.setType(Material.CROSSBOW);

        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        itemMeta.displayName(Component.text(ChatColor.GREEN + "Bow Enchantments"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        if (!hasTool(player, tool)) {
            lore.add(ChatColor.RED + "You must be holding a");
            lore.add(ChatColor.GOLD + WordUtils.capitalizeFully(tool) + ChatColor.RED + " to Enchant it!");
        } else
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View");

        itemStack.setItemMeta(itemMeta);
        itemStack.setLore(lore);

        return new GuiItem(itemStack, e -> {
            if (hasTool(player, tool))
                consoleCommand.consoleCommand("mi stations open bow-enchantments " + player.getName());
            else {
                player.sendMessage(ChatColor.RED + "You must be holding a " + ChatColor.GOLD + "Bow " + WordUtils.capitalizeFully(tool) + ChatColor.RED + " to enchant it!");
                soundManager.soundNo(player, 1);
            }
        });
    }

    private GuiItem toolEnchantments(Player player) {
        ItemStack itemStack = new ItemStack(Material.WOODEN_HOE);
        String tool = "TOOL";
        if (hasTool(player, tool))
            itemStack.setType(Material.NETHERITE_HOE);

        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        itemMeta.displayName(Component.text(ChatColor.GREEN + "Tool Enchantments"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        if (!hasTool(player, tool)) {
            lore.add(ChatColor.RED + "You must be holding a");
            lore.add(ChatColor.GOLD + WordUtils.capitalizeFully(tool) + ChatColor.RED + " to Enchant it!");
        } else
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View");

        itemStack.setItemMeta(itemMeta);
        itemStack.setLore(lore);

        return new GuiItem(itemStack, e -> {
            if (hasTool(player, tool))
                consoleCommand.consoleCommand("mi stations open tool-enchantments " + player.getName());
            else {
                player.sendMessage(ChatColor.RED + "You must be holding a " + ChatColor.GOLD + "Tool " + WordUtils.capitalizeFully(tool) + ChatColor.RED + " to enchant it!");
                soundManager.soundNo(player, 1);
            }
        });
    }

    private boolean hasTool(Player player, String list) {
        switch (list) {
            case "HELMET":
                if (HELMET.contains(player.getInventory().getItemInMainHand().getType()))
                    return true;
                break;
            case "CHESTPLATE":
                if (CHESTPLATE.contains(player.getInventory().getItemInMainHand().getType()))
                    return true;
                break;
            case "LEGGINGS":
                if (LEGGINGS.contains(player.getInventory().getItemInMainHand().getType()))
                    return true;
                break;
            case "BOOTS":
                if (BOOTS.contains(player.getInventory().getItemInMainHand().getType()))
                    return true;
                break;
            case "TOOL":
            case "TOOLS":
                if (TOOLS.contains(player.getInventory().getItemInMainHand().getType()))
                    return true;
                break;
            case "WEAPON":
            case "WEAPONS":
            case "MELEE":
                if (MELEE.contains(player.getInventory().getItemInMainHand().getType()) || TOOLS.contains(player.getInventory().getItemInMainHand().getType()))
                    return true;
                break;
            case "BOW":
            case "RANGED":
                if (BOW.contains(player.getInventory().getItemInMainHand().getType()))
                    return true;
                break;
        }
        return false;
    }
}