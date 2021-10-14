package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.mainMenu;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Dependency;
import co.aikar.commands.annotation.Optional;
import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import io.lumine.mythic.lib.api.item.NBTItem;
import io.lumine.mythicenchants.MythicEnchants;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.GUI.GUIHelper;
import monzter.adventurescraft.plugin.utilities.beton.BetonPointsManager;
import monzter.adventurescraft.plugin.utilities.beton.BetonTagManager;
import monzter.adventurescraft.plugin.utilities.enums.AdventureStatsDisplay;
import monzter.adventurescraft.plugin.utilities.enums.Prefix;
import monzter.adventurescraft.plugin.utilities.general.ConsoleCommand;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import monzter.adventurescraft.plugin.utilities.text.NumberFormat;
import org.apache.commons.lang.WordUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Enchant extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final SoundManager soundManager;
    private final GUIHelper guiHelper;
    private final ConsoleCommand consoleCommand;
    private final BetonTagManager betonTagManager;
    private final BetonPointsManager betonPointsManager;
    private final NumberFormat numberFormat;


    public Enchant(AdventuresCraft plugin, SoundManager soundManager, GUIHelper guiHelper, ConsoleCommand consoleCommand, BetonTagManager betonTagManager, BetonPointsManager betonPointsManager, NumberFormat numberFormat) {
        this.plugin = plugin;
        this.soundManager = soundManager;
        this.guiHelper = guiHelper;
        this.consoleCommand = consoleCommand;
        this.betonTagManager = betonTagManager;
        this.betonPointsManager = betonPointsManager;
        this.numberFormat = numberFormat;
    }

    @CommandAlias("Enchant")
    public void questMenu(Player player, @Optional String enchantment) {
        System.out.println(enchantment);

        if (player.getInventory().getItemInMainHand() == null || player.getInventory().getItemInMainHand().getType() == Material.AIR) {
            player.sendMessage(ChatColor.RED + "You must be holding " + ChatColor.GOLD + "Gear " + ChatColor.RED + "to enchant!");
            return;
        }
        ItemStack itemStack = player.getInventory().getItemInMainHand();
        NBTItem nbtItem = NBTItem.get(itemStack);

        if (enchantment == null) {
            int exp = betonPointsManager.getPoints(player, "EXP.EXP");

            ChestGui gui = new ChestGui(5, guiHelper.guiName("Enchant"));
            gui.setOnGlobalClick(event -> event.setCancelled(true));

            OutlinePane background = new OutlinePane(0, 0, 9, 5, Pane.Priority.LOWEST);
            StaticPane display = new StaticPane(0, 0, 9, 5, Pane.Priority.LOW);
            OutlinePane main = new OutlinePane(1, 1, 7, 3, Pane.Priority.LOW);


            background.addItem(new GuiItem(guiHelper.background(Material.GREEN_STAINED_GLASS_PANE)));
            background.setRepeat(true);

            for (Enchantments enchantments : Enchantments.values()) {
                if (Arrays.asList(enchantments.getTypeAllowed()).contains(nbtItem.getType()))
                    main.addItem(itemGenerator(player, enchantments, exp));
            }

            display.addItem(new GuiItem(guiHelper.backButton(), e -> player.performCommand("main")), 4, 4);

            gui.addPane(background);
            gui.addPane(display);
            gui.addPane(main);
            gui.show(player);
        } else {
//            System.out.println(Enchantments.values());
//            if (!Arrays.asList(Enchantments.values()).contains(enchantment)) {
//                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f" + enchantment + " &cis not a valid &5Enchantment&c!"));
//                soundManager.soundNo(player, 1);
//                return;
//            }

            for (Enchantments enchantments : Enchantments.values()) {
                if (enchantments.name().equalsIgnoreCase(enchantment)) {
                    final int level = calculateEnchantments(player, enchantments.name());
                    final int nextLevel = level + 1;
                    final int cost = nextLevel * enchantments.getCost();
                    boolean maxed = level == enchantments.getMaxLevel();
                    int exp = betonPointsManager.getPoints(player, "EXP.EXP");

                    if (maxed) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou already reached the max level for this &5Enchantment&c!"));
                        soundManager.soundNo(player, 1);
                        return;
                    }
                    if (exp < cost) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou only have &b" + numberFormat.numberFormat(exp) + "&c/&b"
                                + numberFormat.numberFormat(cost) + " " + AdventureStatsDisplay.EXP.getName() + "&c!"));
                        soundManager.soundNo(player, 1);
                        return;
                    }
                    if (!Arrays.asList(enchantments.getTypeAllowed()).contains(nbtItem.getType())) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cThis &5Enchantment &ccannot be applied to this type of &6Gear&c!"));
                        soundManager.soundNo(player, 1);
                        return;
                    }
                    enchantLore(player.getInventory().getItemInMainHand(), enchantment, nextLevel);
                    MythicEnchants.inst().getEnchantManager().applyEnchantment(player.getInventory().getItemInMainHand(), enchantment, nextLevel);
                    betonPointsManager.takePoint(player, "EXP.EXP", enchantments.cost);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aYou successfully purchased &f" + WordUtils.capitalizeFully(enchantment) + " "
                            + nextLevel + " &afor &b" + numberFormat.numberFormat(cost) + " " + AdventureStatsDisplay.EXP.getName() + "&a!"));
                    soundManager.soundPurchase(player, 1);
                    soundManager.playSound(player, Sound.BLOCK_ENCHANTMENT_TABLE_USE, 1, 1);
                    break;
                }
            }
        }
    }


    private void enchantLore(ItemStack itemStack, String enchant, int level) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        boolean hasEnchants = hasEnchants(itemMeta.getLore());
        if (!hasEnchants) {
//            int i = 0;
//            List<String> lore = new ArrayList<>();
//            while (i < itemMeta.getLore().size()) {
//                System.out.println(itemMeta.getLore().get(i) + " " + i);
//                lore.add(itemMeta.getLore().get(i));
//                if (i == itemMeta.getLore().size() - 2) {
//                    lore.add("<#UNIQUE>" + ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "Enchantments:");
//                    lore.add(ChatColor.WHITE + "◆" + WordUtils.capitalizeFully(enchant) + " " + level);
//                    lore.add("");
//                }
//                i++;
//            }
//            itemMeta.setLore(lore);
//            itemStack.setItemMeta(itemMeta);
        }
    }


    private boolean hasEnchants(List<String> lore) {
        for (String itemLore : lore)
            if (itemLore.contains(ChatColor.DARK_PURPLE.toString() + ChatColor.BOLD + "Enchantments:"))
                return true;
        return false;
    }


    private GuiItem itemGenerator(Player player, Enchantments enchantment, int exp) {
        final ItemStack item = new ItemStack(Material.BOOK);
        final ItemMeta itemItemMeta = item.getItemMeta();
        final int level = calculateEnchantments(player, enchantment.name());
        final int nextLevel = level + 1;
        final int cost = nextLevel * enchantment.getCost();
        boolean maxed = level == enchantment.getMaxLevel();

        if (maxed) {
            item.setType(Material.ENCHANTED_BOOK);
            itemItemMeta.setDisplayName(ChatColor.WHITE + WordUtils.capitalizeFully(enchantment.name()) + ChatColor.GOLD + ChatColor.BOLD + " MAXED");
        } else
            itemItemMeta.setDisplayName(ChatColor.WHITE + WordUtils.capitalizeFully(enchantment.name()) + " " + nextLevel);

        List<String> lore = new ArrayList<>();
        lore.add("");
        if (maxed)
            for (String enchantmentLore : enchantment.getLore())
                lore.add(enchantmentLore.replaceAll("(%.*?%)", String.valueOf(enchantment.getIncreasePerLevel() * level)));
        else
            for (String enchantmentLore : enchantment.getLore())
                lore.add(enchantmentLore.replaceAll("(%.*?%)", String.valueOf(enchantment.getIncreasePerLevel() * nextLevel)));
        if (!maxed) {
            lore.add("");
            lore.add(ChatColor.GOLD + ChatColor.BOLD.toString() + "PRICE:");
            lore.add(ChatColor.AQUA + numberFormat.numberFormat(cost) + " " + AdventureStatsDisplay.EXP.getName());
            lore.add("");
            if (exp >= cost)
                lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Left-Click to Purchase");
            else if (level > 0)
                lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Shift-Right-Click to Remove Enchantment");
            else
                lore.add(ChatColor.RED + "You only have " + ChatColor.AQUA + numberFormat.numberFormat(exp) + " " + AdventureStatsDisplay.EXP.getName());
        }

        item.setItemMeta(itemItemMeta);
        item.setLore(lore);

        if (maxed)
            return new GuiItem(item);
        else
            return new GuiItem(item, e -> {
                if (e.isLeftClick())
                    player.performCommand("enchant " + enchantment.name());
                else if (e.isRightClick() && e.isShiftClick())
                    removeEnchantment(player, enchantment.name());
                player.performCommand("enchant");
            });
    }

    private int calculateEnchantments(Player player, String enchantment) {
        ItemMeta meta = player.getInventory().getItemInMainHand().getItemMeta();
        if (meta != null && meta.hasEnchants())
            for (Enchantment enchant : meta.getEnchants().keySet()) {
                if (enchant.getKey().getKey().equalsIgnoreCase(enchantment))
                    return meta.getEnchants().get(enchant);
            }
        return 0;
    }

    private void removeEnchantment(Player player, String enchantment) {
        ItemMeta meta = player.getInventory().getItemInMainHand().getItemMeta();
        if (meta != null && meta.hasEnchants())
            for (Enchantment enchant : meta.getEnchants().keySet()) {
                if (enchant.getKey().getKey().equalsIgnoreCase(enchantment)) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f" + WordUtils.capitalizeFully(enchantment) + " &ahas been successfully removed!"));
                    soundManager.soundYes(player, 1);
                    String romanNumeral = romanCalculator(meta.getEnchants().get(enchant));
                    List<String> lore = new ArrayList<>();
                    for (String originalLore : meta.getLore())
                        if (!originalLore.contains(WordUtils.capitalizeFully(enchantment) + " " + romanNumeral))
                            lore.add(originalLore);
                    meta.removeEnchant(enchant);
                    meta.setLore(lore);
                    player.getInventory().getItemInMainHand().setItemMeta(meta);
                }
            }
    }

    private String romanCalculator(int number) {
        switch (number) {
            case 1:
                return "I";
            case 2:
                return "II";
            case 3:
                return "III";
            case 4:
                return "IV";
            case 5:
                return "V";
            case 6:
                return "VI";
            case 7:
                return "VII";
            case 8:
                return "VIII";
            case 9:
                return "IX";
            case 10:
                return "X";
            case 11:
                return "XI";
            case 12:
                return "XII";
            case 13:
                return "XIII";
            case 14:
                return "XIV";
            case 15:
                return "XV";
            case 16:
                return "XVI";
            case 17:
                return "XVII";
            case 18:
                return "XVIII";
            case 19:
                return "XIX";
            case 20:
                return "XX";
            case 21:
                return "XXI";
            case 22:
                return "XXII";
            case 23:
                return "XXIII";
            case 24:
                return "XXIV";
            case 25:
                return "XXV";
        }
        return null;
    }

    enum Enchantments {
        DRAIN(25, 100 * .0004, 100, new String[]{"TOOL", "SWORD"}, null,
                new String[]{ChatColor.translateAlternateColorCodes('&', "&cDamage &7the &cenemy &7for"),
                        ChatColor.translateAlternateColorCodes('&', "&a%p%% &7of their &aMax HP&7!")});

        private final int maxLevel;
        private final double increasePerLevel;
        private final int cost;
        private final String[] typeAllowed;
        private final String[] idBlacklist;
        private final String[] lore;


        Enchantments(int maxLevel, double increasePerLevel, int cost, String[] typeAllowed, String[] idBlacklist, String[] lore) {
            this.maxLevel = maxLevel;
            this.increasePerLevel = increasePerLevel;
            this.cost = cost;
            this.typeAllowed = typeAllowed;
            this.idBlacklist = idBlacklist;
            this.lore = lore;
        }

        public int getMaxLevel() {
            return maxLevel;
        }

        public int getCost() {
            return cost;
        }

        public String[] getTypeAllowed() {
            return typeAllowed;
        }

        public String[] getIdBlacklist() {
            return idBlacklist;
        }

        public double getIncreasePerLevel() {
            return increasePerLevel;
        }

        public String[] getLore() {
            return lore;
        }
    }
}

