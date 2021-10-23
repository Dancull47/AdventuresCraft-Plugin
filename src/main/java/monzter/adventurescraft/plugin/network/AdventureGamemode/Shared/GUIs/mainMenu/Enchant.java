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
import net.Indyuce.mmoitems.MMOItems;
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
                    if (enchantments.getIdBlacklist() == null || !Arrays.asList(enchantments.getIdBlacklist()).contains(MMOItems.getID(nbtItem)))
                        if (enchantments.getMaterialAllowed() == null || Arrays.asList(enchantments.getMaterialAllowed()).contains(itemStack.getType()))
                            main.addItem(itemGenerator(player, enchantments, exp));
            }

            display.addItem(new GuiItem(guiHelper.backButton(), e -> player.performCommand("main")), 4, 4);

            gui.addPane(background);
            gui.addPane(display);
            gui.addPane(main);
            gui.show(player);
        } else {
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
                    MythicEnchants.inst().getEnchantManager().applyEnchantment(player.getInventory().getItemInMainHand(), enchantment, nextLevel);
                    enchantLore(player.getInventory().getItemInMainHand());
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
            if (level > 0)
                lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Shift-Right-Click to Remove Enchantment");
            if (exp >= cost)
                lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Left-Click to Purchase");
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


    private void enchantLore(ItemStack itemStack) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        boolean hasEnchants = hasEnchants(itemMeta.getLore());
        if (hasEnchants)
            clearEnchantLore(itemStack);
        addEnchantLore(itemStack);
    }

    private void clearEnchantLore(ItemStack itemStack) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        int enchantLine = enchantmentLine(itemMeta.getLore());
        List<String> lore = new ArrayList<>();
        int i = 0;
        for (String originalLore : itemMeta.getLore()) {
            if (i < enchantLine)
                lore.add(originalLore);
            i++;
        }
        lore.add(itemMeta.getLore().get(itemMeta.getLore().size() - 1));
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
    }

    private void addEnchantLore(ItemStack itemStack) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> lore = new ArrayList<>();
        List<String> enchantmentLine = enchantmentLines(itemStack);

        for (int i = 0; i < itemMeta.getLore().size(); i++) {
            lore.add(itemMeta.getLore().get(i));
            if (i == itemMeta.getLore().size() - 2) {
                lore.add(ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "Enchantments:");
                for (String enchantLine : enchantmentLine)
                    lore.add(enchantLine);
                lore.add("");
            }
        }
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
    }

    private String enchantmentColor(String enchantment) {
        for (Enchantments enchant : Enchantments.values())
            if (enchant.name().equalsIgnoreCase(enchantment))
                return enchant.getColor() + WordUtils.capitalizeFully(enchant.name().toUpperCase().replace('_', ' '));
        return "";
    }

    private List<String> enchantmentLines(ItemStack itemStack) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> enchantmentLine = new ArrayList<>();
        List<String> finalEnchantmentLine = new ArrayList<>();
        String helperLine = "";
        String enchantment;

        int a = 0;
        for (Enchantment enchant : itemMeta.getEnchants().keySet()) {
            enchantment = enchantmentColor(enchant.getName());
            if (a == itemMeta.getEnchants().size() - 1)
                enchantmentLine.add(enchantment + " " + itemMeta.getEnchants().get(enchant));
            else if (itemMeta.getEnchants().size() > 1)
                enchantmentLine.add(enchantment + " " + itemMeta.getEnchants().get(enchant) + ", ");
            a++;
        }

        /*
         *
         * Loops through Enchantment Line
         * If only 1 Enchantment, returns just that Enchantment
         *
         * Else it will then set HelperLine to first Enchantment
         * then add 1 to i
         * This will repeat either 2 MORE TIMES (3 total)
         * OR until Tracker = the amount of Enchantments
         * then it'll add that String (helperLine) to the List
         *
         * */


        int i = 0;
        int tracker = 0;
        for (String line : enchantmentLine) {

            if (enchantmentLine.size() == 1)
                return enchantmentLine;

            if (helperLine.isEmpty())
                helperLine = line;
            else if (i < 2)
                helperLine = helperLine + line;

            if (i == 1 || tracker == enchantmentLine.size() - 1)
                finalEnchantmentLine.add(helperLine);

            if (i == 1) {
                i = 0;
                helperLine = "";
            } else
                i++;
            tracker++;
        }
        return finalEnchantmentLine;
    }


    private boolean hasEnchants(List<String> lore) {
        for (String itemLore : lore)
            if (itemLore.contains(ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "Enchantments:"))
                return true;
        return false;
    }

    private int enchantmentLine(List<String> lore) {
        int i = 0;
        for (String itemLore : lore) {
            if (itemLore.contains(ChatColor.LIGHT_PURPLE.toString() + ChatColor.BOLD + "Enchantments:"))
                return i;
            i++;
        }
        return 0;
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
        ItemStack itemStack = player.getInventory().getItemInMainHand();
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null && meta.hasEnchants())
            for (Enchantment enchant : meta.getEnchants().keySet()) {
                if (enchant.getKey().getKey().equalsIgnoreCase(enchantment)) {
                    meta.removeEnchant(enchant);
                    itemStack.setItemMeta(meta);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f" + WordUtils.capitalizeFully(enchantment) + " &ahas been successfully removed!"));
                    soundManager.soundYes(player, 1);
                    enchantLore(itemStack);
                    if (meta.getEnchants().size() == 0) {
                        meta = player.getInventory().getItemInMainHand().getItemMeta();
                        List<String> newLore = meta.getLore();
                        newLore.remove(enchantmentLine(meta.getLore()));
                        newLore.remove(enchantmentLine(meta.getLore()));
                        meta.setLore(newLore);
                        itemStack.setItemMeta(meta);
                    }
                }
            }
    }

    public enum Enchantments {
        DRAIN(25, 100 * .0004, 100, ChatColor.RED.toString(), new String[]{"TOOL", "SWORD"}, null, null,
                new String[]{ChatColor.translateAlternateColorCodes('&', "&cDamage &7the &cenemy &7for"),
                        ChatColor.translateAlternateColorCodes('&', "&a%p%% &7of their &aMax HP&7!")}),
        SHARPNESS(25, 100 * .0004, 100, ChatColor.YELLOW.toString(), new String[]{"TOOL", "SWORD"}, null, null,
                new String[]{ChatColor.translateAlternateColorCodes('&', "&cDamage &7the &cenemy &7for"),
                        ChatColor.translateAlternateColorCodes('&', "&a%p%% &7of your " + AdventureStatsDisplay.DAMAGE.getName() + "&7!")}),
        LOOTING(25, 100 * .0004, 100, ChatColor.GREEN.toString(), new String[]{"TOOL", "SWORD"}, null, null,
                new String[]{ChatColor.translateAlternateColorCodes('&', "&7Increase the chance of &cenemies"),
                        ChatColor.translateAlternateColorCodes('&', "&7dropping their &aArmor &7by &a%p%%&7!")}),
        PARALYZE(25, 1, 100, ChatColor.GREEN.toString(), new String[]{"TOOL", "SWORD"}, null, null,
                new String[]{ChatColor.translateAlternateColorCodes('&', "&7Increase the chance of &4paralyzing &7an"),
                        ChatColor.translateAlternateColorCodes('&', "&cenemy &7when &cattacking &7them &7by &a%p%%&7!")}),
        POSTHUMOUS(25, 100 * .0004, 100, ChatColor.GREEN.toString(), new String[]{"TOOL", "SWORD"}, null, null,
                new String[]{ChatColor.translateAlternateColorCodes('&', "&7Increase the chance of gaining"),
                        ChatColor.translateAlternateColorCodes('&', "&7a &especial effect &7when &cslaying"),
                        ChatColor.translateAlternateColorCodes('&', "&7an &cenemy &7by &a%p%%&7!")}),

        FORTUNE(25, 100 * .0004, 100, ChatColor.GREEN.toString(), new String[]{"TOOL"}, null, null,
                new String[]{ChatColor.translateAlternateColorCodes('&', "&7Increase the chance of getting"),
                        ChatColor.translateAlternateColorCodes('&', "&adouble drops &7while mining &7by &a%p%%&7!")}),

        KAMIKAZE(25, 100 * .0004, 100, ChatColor.GREEN.toString(), new String[]{"ARMOR"}, new Material[]{Material.CHAINMAIL_CHESTPLATE, Material.DIAMOND_CHESTPLATE, Material.IRON_CHESTPLATE, Material.GOLDEN_CHESTPLATE, Material.LEATHER_CHESTPLATE, Material.NETHERITE_CHESTPLATE}, null,
                new String[]{ChatColor.translateAlternateColorCodes('&', "&7Increase the chance of &4exploding"),
                        ChatColor.translateAlternateColorCodes('&', "&7when you die &7by &a%p%%&7!")}),
        REFLECT(25, 100 * .0004, 100, ChatColor.GREEN.toString(), new String[]{"ARMOR"}, null, null,
                new String[]{ChatColor.translateAlternateColorCodes('&', "&eReflect &a%p%% &7of incoming"),
                        ChatColor.translateAlternateColorCodes('&', "&cdamage &7back to the &cattacker&7!")}),
        NEGATION(25, 100 * .0004, 100, ChatColor.GREEN.toString(), new String[]{"ARMOR"}, null, null,
                new String[]{ChatColor.translateAlternateColorCodes('&', "&7Increase the chance of &8negating"),
                        ChatColor.translateAlternateColorCodes('&', "&7incoming &cdamage &7by &a%p%%&7!")}),

        EXPERIENCE(25, 100 * .0004, 100, ChatColor.GREEN.toString(), new String[]{"TOOL", "SWORD"}, null, null,
                new String[]{ChatColor.translateAlternateColorCodes('&', "&7Increase the amount of " + AdventureStatsDisplay.EXP.getName()),
                        ChatColor.translateAlternateColorCodes('&', "&7gained by &aMining &7& &cSlaying &7by &a%p%%&7!")}),
        COINED(25, 100 * .0004, 100, ChatColor.GREEN.toString(), new String[]{"TOOL", "SWORD"}, null, null,
                new String[]{ChatColor.translateAlternateColorCodes('&', "&7Increase the amount of " + AdventureStatsDisplay.COINS.getName()),
                        ChatColor.translateAlternateColorCodes('&', "&7gained by &aMining &7& &cSlaying &7by &a%p%%&7!")}),
        ;

        private final int maxLevel;
        private final double increasePerLevel;
        private final int cost;
        private final String color;
        private final String[] typeAllowed;
        private final Material[] materialAllowed;
        private final String[] idBlacklist;
        private final String[] lore;


        Enchantments(int maxLevel, double increasePerLevel, int cost, String color, String[] typeAllowed, Material[] materialAllowed, String[] idBlacklist, String[] lore) {
            this.maxLevel = maxLevel;
            this.increasePerLevel = increasePerLevel;
            this.cost = cost;
            this.color = color;
            this.typeAllowed = typeAllowed;
            this.materialAllowed = materialAllowed;
            this.idBlacklist = idBlacklist;
            this.lore = lore;
        }

        public int getMaxLevel() {
            return maxLevel;
        }

        public Material[] getMaterialAllowed() {
            return materialAllowed;
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

        public String getColor() {
            return color;
        }
    }
}

