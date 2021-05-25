package monzter.adventurescraft.plugin.shared.GUIs.shops;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Dependency;
import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.PaginatedPane;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import me.clip.placeholderapi.PlaceholderAPI;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.enums.Enchantments;
import monzter.adventurescraft.plugin.utilities.GUI.GUIHelper;
import monzter.adventurescraft.plugin.utilities.enums.Prefix;
import monzter.adventurescraft.plugin.utilities.enums.StatsDisplay;
import monzter.adventurescraft.plugin.utilities.general.ConsoleCommand;
import monzter.adventurescraft.plugin.utilities.general.FullInventory;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import monzter.adventurescraft.plugin.utilities.mmoitems.MMOItemsGive;
import monzter.adventurescraft.plugin.utilities.text.NumberFormat;
import monzter.adventurescraft.plugin.utilities.vault.Economy;
import net.Indyuce.mmoitems.MMOItems;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Enchanting extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final SoundManager soundManager;
    private final GUIHelper guiHelper;
    private final ConsoleCommand consoleCommand;
    private final Economy economy;
    private final FullInventory fullInventory;
    private final MMOItemsGive mmoItemsGive;
    private final NumberFormat numberFormat;
    private final List<Material> tools = Arrays.asList(Material.WOODEN_AXE, Material.WOODEN_HOE, Material.WOODEN_PICKAXE, Material.WOODEN_SHOVEL,
            Material.STONE_AXE, Material.STONE_HOE, Material.STONE_PICKAXE, Material.STONE_SHOVEL,
            Material.IRON_AXE, Material.IRON_HOE, Material.IRON_PICKAXE, Material.IRON_SHOVEL,
            Material.GOLDEN_AXE, Material.GOLDEN_HOE, Material.GOLDEN_PICKAXE, Material.GOLDEN_SHOVEL,
            Material.DIAMOND_AXE, Material.DIAMOND_HOE, Material.DIAMOND_PICKAXE, Material.DIAMOND_SHOVEL,
            Material.NETHERITE_AXE, Material.NETHERITE_HOE, Material.NETHERITE_PICKAXE, Material.NETHERITE_SHOVEL);
    private final DecimalFormat df = new DecimalFormat("###.####");

    public Enchanting(AdventuresCraft plugin, SoundManager soundManager, GUIHelper guiHelper, ConsoleCommand consoleCommand, Economy economy, FullInventory fullInventory, MMOItemsGive mmoItemsGive, NumberFormat numberFormat) {
        this.plugin = plugin;
        this.soundManager = soundManager;
        this.guiHelper = guiHelper;
        this.consoleCommand = consoleCommand;
        this.economy = economy;
        this.fullInventory = fullInventory;
        this.mmoItemsGive = mmoItemsGive;
        this.numberFormat = numberFormat;
    }

    @CommandAlias("enchantmentShop")
    public void pets(Player player) {
        if (player.hasPermission("SHOPS")) {
            if (tools.contains(player.getInventory().getItemInMainHand().getType())) {
                final int exp = Integer.valueOf(PlaceholderAPI.setPlaceholders(player, "%betonquest_items:point.Experience.amount%"));

                ChestGui gui = new ChestGui(6, guiHelper.guiName("Enchanting"));
                gui.setOnGlobalClick(event -> event.setCancelled(true));

                PaginatedPane page = new PaginatedPane(0, 0, 9, 6);
                OutlinePane background = new OutlinePane(0, 0, 9, 6, Pane.Priority.LOWEST);
                StaticPane display = new StaticPane(1, 1, 9, 5, Pane.Priority.LOW);

                page.addPane(0, background);
                page.addPane(0, display);

                background.addItem(new GuiItem(guiHelper.background(Material.PINK_STAINED_GLASS_PANE)));
                background.setRepeat(true);
                display.addItem(new GuiItem(experience(exp, calculateEnchantments(player, "Experience") + 1), e -> player.sendMessage("1")), 1, 0);
                display.addItem(new GuiItem(petExperience(exp, calculateEnchantments(player, "Pet Experience") + 1), e -> player.sendMessage("1")), 2, 0);
                display.addItem(new GuiItem(luck(exp, calculateEnchantments(player, "Luck") + 1), e -> player.sendMessage("1")), 3, 0);
                display.addItem(new GuiItem(explosive(exp, calculateEnchantments(player, "Explosive") + 1), e -> player.sendMessage("1")), 4, 0);
                display.addItem(new GuiItem(explosiveChance(exp, calculateEnchantments(player, "Explosive Chance") + 1), e -> player.sendMessage("1")), 5, 0);

                display.addItem(new GuiItem(randomizer(exp, calculateEnchantments(player, "Randomizer") + 1), e -> player.sendMessage("1")), 2, 1);
                display.addItem(new GuiItem(treasurer(exp, calculateEnchantments(player, "Treasurer") + 1), e -> player.sendMessage("1")), 3, 1);
                display.addItem(new GuiItem(midasTouch(exp, calculateEnchantments(player, "Midas Touch") + 1), e -> player.sendMessage("1")), 4, 1);

                display.addItem(new GuiItem(statTracker(exp, calculateEnchantments(player, "Stat Tracker") + 1), e -> player.sendMessage("1")), 3, 2);

//            display2.addItem(new GuiItem(hatchEgg(Integer.valueOf(exp), enchantment), e -> {
//                if (e.isLeftClick())
//                    player.performCommand("hatch " + enchantment.getId());
//                if (e.isRightClick())
//                    player.performCommand("droptableviewer " + enchantment.getId() + "petegg");

//            }));
                gui.addPane(page);
                gui.show(player);
            } else {
                player.sendMessage(ChatColor.RED + "You must be holding a tool to enchant!");
            }
        }
    }


    private void purchase(Player player, String itemID, double price) {
        if (economy.hasMoney(player, price)) {
            if (!fullInventory.fullInventory(player)) {
                economy.takeMoney(player, price);
                mmoItemsGive.giveMMOItem(player, "PET", itemID);
            }
        }
    }


    private ItemStack experience(int balance, int enchantmentLevel) {
        TextComponent[] experience = new TextComponent[]{
                Component.text(ChatColor.GRAY + "Increase your " + StatsDisplay.EXPERIENCE_MULTIPLIER.getName()),
                Component.text(ChatColor.GRAY + "by " + ChatColor.GREEN + "+" + Double.valueOf(enchantmentLevel * Enchantments.Experience.getIncrease()) + ChatColor.GRAY + " while mining with this tool!")};
        if (enchantmentLevel - 1 < Enchantments.Experience.getMaxLevel())
            return enchantment(balance, ChatColor.GREEN + "Experience ", enchantmentLevel, experience, (Integer.valueOf(enchantmentLevel * Enchantments.Experience.getPrice())), false);
        return enchantment(balance, ChatColor.GREEN + "Experience ", enchantmentLevel, experience, (Integer.valueOf(enchantmentLevel * Enchantments.Experience.getPrice())), true);
    }

    private ItemStack petExperience(int balance, int enchantmentLevel) {
        TextComponent[] petExperience = new TextComponent[]{
                Component.text(ChatColor.GRAY + "Increase your " + StatsDisplay.PET_EXPERIENCE_MULTIPLIER.getName()),
                Component.text(ChatColor.GRAY + "by " + ChatColor.GREEN + "+" + Double.valueOf(enchantmentLevel * Enchantments.PetExperience.getIncrease()) + ChatColor.GRAY + " while mining with this tool!")};
        if (enchantmentLevel - 1 < Enchantments.PetExperience.getMaxLevel())
            return enchantment(balance, ChatColor.AQUA + "Pet Experience ", enchantmentLevel, petExperience, (Integer.valueOf(enchantmentLevel * Enchantments.PetExperience.getPrice())), false);
        return enchantment(balance, ChatColor.AQUA + "Pet Experience ", enchantmentLevel, petExperience, (Integer.valueOf(enchantmentLevel * Enchantments.PetExperience.getPrice())), true);
    }

    private ItemStack luck(int balance, int enchantmentLevel) {
        TextComponent[] petExperience = new TextComponent[]{
                Component.text(ChatColor.GRAY + "Increase your chances of finding " + ChatColor.GREEN + "Lootboxes "),
                Component.text(ChatColor.GRAY + "by " + ChatColor.GREEN + Double.valueOf(enchantmentLevel * Enchantments.Luck.getIncrease()) + "x" + ChatColor.GRAY + ", while mining with this tool!")};
        if (enchantmentLevel - 1 < Enchantments.Luck.getMaxLevel())
            return enchantment(balance, ChatColor.YELLOW + "Luck ", enchantmentLevel, petExperience, (Integer.valueOf(enchantmentLevel * Enchantments.Luck.getPrice())), false);
        return enchantment(balance, ChatColor.YELLOW + "Luck ", enchantmentLevel, petExperience, (Integer.valueOf(enchantmentLevel * Enchantments.Luck.getPrice())), true);
    }

    private ItemStack explosive(int balance, int enchantmentLevel) {
        TextComponent[] explosive = new TextComponent[]{
                Component.text(ChatColor.GRAY + "Occasionally break " + ChatColor.GREEN + "+" + enchantmentLevel + ChatColor.GRAY + " nearby"),
                Component.text(ChatColor.GRAY + "blocks, while mining with this tool!")};
        if (enchantmentLevel - 1 < Enchantments.Explosive.getMaxLevel())
            return enchantment(balance, ChatColor.DARK_RED + "Explosive ", enchantmentLevel, explosive, (Integer.valueOf(enchantmentLevel * Enchantments.Explosive.getPrice())), false);
        return enchantment(balance, ChatColor.DARK_RED + "Explosive ", enchantmentLevel, explosive, (Integer.valueOf(enchantmentLevel * Enchantments.Explosive.getPrice())), true);
    }

    private ItemStack explosiveChance(int balance, int enchantmentLevel) {
        TextComponent[] explosiveChance = new TextComponent[]{
                Component.text(ChatColor.GRAY + "Increase the chance of " + ChatColor.DARK_RED + "Explosive " + ChatColor.GRAY + "occurring"),
                Component.text(ChatColor.GRAY + "by " + ChatColor.GREEN + df.format(Double.valueOf(enchantmentLevel * Enchantments.ExplosiveChance.getIncrease() * 10)) + "% " + ChatColor.GRAY + "while mining with this tool!")};
        if (enchantmentLevel - 1 < Enchantments.ExplosiveChance.getMaxLevel())
            return enchantment(balance, ChatColor.RED + "Explosive Chance ", enchantmentLevel, explosiveChance, (Integer.valueOf(enchantmentLevel * Enchantments.ExplosiveChance.getPrice())), false);
        return enchantment(balance, ChatColor.RED + "Explosive Chance ", enchantmentLevel, explosiveChance, (Integer.valueOf(enchantmentLevel * Enchantments.ExplosiveChance.getPrice())), true);
    }

    private ItemStack randomizer(int balance, int enchantmentLevel) {
        TextComponent[] randomizer = new TextComponent[]{
                Component.text(ChatColor.GRAY + "Increase the chance of spawning a" + ChatColor.DARK_GREEN + " valuable block"),
                Component.text(ChatColor.GRAY + "above you by " + ChatColor.GREEN + df.format(Double.valueOf(enchantmentLevel * Enchantments.Randomizer.getIncrease() * 10)) + "% " + ChatColor.GRAY + "while mining with this tool!")};
        if (enchantmentLevel - 1 < Enchantments.Randomizer.getMaxLevel())
            return enchantment(balance, ChatColor.DARK_GREEN + "Randomizer ", enchantmentLevel, randomizer, (Integer.valueOf(enchantmentLevel * Enchantments.Randomizer.getPrice())), false);
        return enchantment(balance, ChatColor.DARK_GREEN + "Randomizer ", enchantmentLevel, randomizer, (Integer.valueOf(enchantmentLevel * Enchantments.Randomizer.getPrice())), true);
    }

    private ItemStack treasurer(int balance, int enchantmentLevel) {
        TextComponent[] treasurer = new TextComponent[]{
                Component.text(ChatColor.GRAY + "Increase the chance of spawning a" + ChatColor.GOLD + " special treasure"),
                Component.text(ChatColor.GRAY + "below you by " + ChatColor.GREEN + df.format(Double.valueOf(enchantmentLevel * Enchantments.Treasurer.getIncrease() * 10)) + "% " + ChatColor.GRAY + "while mining with this tool!")};
        if (enchantmentLevel - 1 < Enchantments.Treasurer.getMaxLevel())
            return enchantment(balance, ChatColor.GOLD + "Treasurer ", enchantmentLevel, treasurer, (Integer.valueOf(enchantmentLevel * Enchantments.Treasurer.getPrice())), false);
        return enchantment(balance, ChatColor.GOLD + "Treasurer ", enchantmentLevel, treasurer, (Integer.valueOf(enchantmentLevel * Enchantments.Treasurer.getPrice())), true);
    }

    private ItemStack midasTouch(int balance, int enchantmentLevel) {
        TextComponent[] midasTouch = new TextComponent[]{
                Component.text(ChatColor.GRAY + "Increase the chance of gaining a random amount "),
                Component.text(ChatColor.GRAY + "of " + StatsDisplay.MONEY_AMOUNT.getName() + ChatColor.GRAY + " by " + ChatColor.GREEN + df.format(Double.valueOf(enchantmentLevel * Enchantments.MidasTouch.getIncrease() * 10)) + "% " + ChatColor.GRAY + "while mining with this tool!")};
        if (enchantmentLevel - 1 < Enchantments.MidasTouch.getMaxLevel())
            return enchantment(balance, ChatColor.YELLOW + "Midas Touch ", enchantmentLevel, midasTouch, (Integer.valueOf(enchantmentLevel * Enchantments.MidasTouch.getPrice())), false);
        return enchantment(balance, ChatColor.YELLOW + "Midas Touch ", enchantmentLevel, midasTouch, (Integer.valueOf(enchantmentLevel * Enchantments.MidasTouch.getPrice())), true);
    }

    private final TextComponent[] statTracker = new TextComponent[]{
            Component.text(ChatColor.GRAY + "Track the amount of blocks broken with your"),
            Component.text(ChatColor.GRAY + "tool, earning " + ChatColor.GREEN + "rewards" + ChatColor.GRAY + " while mining!")};

    private ItemStack statTracker(int balance, int enchantmentLevel) {
        if (enchantmentLevel - 1 < Enchantments.StatTracker.getMaxLevel())
            return enchantment(balance, ChatColor.GREEN.toString() + ChatColor.BOLD + "Stat Tracker ", enchantmentLevel, statTracker, (Integer.valueOf(enchantmentLevel * Enchantments.StatTracker.getPrice())), false);
        return enchantment(balance, ChatColor.GREEN.toString() + ChatColor.BOLD + "Stat Tracker ", enchantmentLevel, statTracker, (Integer.valueOf(enchantmentLevel * Enchantments.StatTracker.getPrice())), true);
    }

    private ItemStack enchantment(int balance, String enchantmentName, int enchantmentLevel, TextComponent[] enchantmentLore, int enchantmentPrice, boolean maxed) {
        ItemStack enchantmentItem = new ItemStack(Material.BOOK);
        if (maxed)
            enchantmentItem = new ItemStack(Material.ENCHANTED_BOOK);
        final ItemMeta enchantmentItemItemMeta = enchantmentItem.getItemMeta();

        if (maxed)
            enchantmentItemItemMeta.setDisplayName(enchantmentName + ChatColor.DARK_GRAY + "- " + ChatColor.YELLOW + ChatColor.BOLD + "MAXED");
        else
            enchantmentItemItemMeta.setDisplayName(enchantmentName + enchantmentLevel);

        if (enchantmentItem != null) {
            List<Component> lore = enchantmentItem.lore();
            if (lore == null) {
                lore = new ArrayList<>();
            }
            lore.add(Component.text(ChatColor.WHITE + "Enchantment"));
            lore.add(Component.text(""));
            for (TextComponent enchLore : enchantmentLore)
                lore.add(enchLore);
            if (!maxed) {
                lore.add(Component.text(""));
                lore.add(Component.text(ChatColor.WHITE + "Price: " + StatsDisplay.EXPERIENCE_AMOUNT.getName() + " " + numberFormat.numberFormat(enchantmentPrice)));
                if (balance >= enchantmentPrice) {
                    lore.add(Component.text(""));
                    lore.add(Component.text(Prefix.PREFIX.getPrefix() + ChatColor.YELLOW + "Click to Purchase"));
                }
            }
            enchantmentItemItemMeta.lore(lore);
            enchantmentItem.setItemMeta(enchantmentItemItemMeta);
        }
        return enchantmentItem;
    }

    private int calculateEnchantments(Player player, String enchantment) {
        if (player.getPlayer().getInventory().getItemInMainHand().getItemMeta() != null) {
            Map<Enchantment, Integer> enchantmentMap = player.getPlayer().getInventory().getItemInMainHand().getItemMeta().getEnchants();
            if (!enchantmentMap.isEmpty()) {
                if (enchantmentMap.containsKey(org.bukkit.enchantments.Enchantment.getByName(enchantment))) {
                    return enchantmentMap.get(Enchantment.getByName(enchantment));
                }
            }
        }
        return 0;
    }

}