package monzter.adventurescraft.plugin.utilities.GUI;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import dev.dbassett.skullcreator.SkullCreator;
import me.clip.placeholderapi.PlaceholderAPI;
import monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.mainMenu.quests.enums.MiningPassJobs;
import monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.mainMenu.quests.enums.QuestGiver;
import monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.mainMenu.quests.enums.QuestList;
import monzter.adventurescraft.plugin.utilities.beton.BetonPointsManager;
import monzter.adventurescraft.plugin.utilities.beton.BetonTagManager;
import monzter.adventurescraft.plugin.utilities.enums.AdventureStatsDisplay;
import monzter.adventurescraft.plugin.utilities.enums.Prefix;
import monzter.adventurescraft.plugin.utilities.enums.PrisonStatsDisplay;
import monzter.adventurescraft.plugin.utilities.general.ConsoleCommand;
import monzter.adventurescraft.plugin.utilities.general.FullInventory;
import monzter.adventurescraft.plugin.utilities.general.ItemAdder;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import monzter.adventurescraft.plugin.utilities.mmoitems.MMOItemsHelperImpl;
import monzter.adventurescraft.plugin.utilities.text.NumberFormat;
import monzter.adventurescraft.plugin.utilities.vault.Economy;
import net.Indyuce.mmoitems.MMOItems;
import net.kyori.adventure.text.Component;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class GUIHelperImpl implements GUIHelper {
    private final NumberFormat numberFormat;
    private final BetonTagManager betonTagManager;
    private final BetonPointsManager betonPointsManager;
    private final FullInventory fullInventory;
    private final ItemAdder itemAdder;
    private final MMOItems mmoItems;
    private final ConsoleCommand consoleCommand;
    private final Economy economy;
    private final SoundManager soundManager;


    final String PREFIX = ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + Prefix.PREFIX.getString() + " ";

    public GUIHelperImpl(NumberFormat numberFormat, BetonTagManager betonTagManager, BetonPointsManager betonPointsManager, FullInventory fullInventory, ItemAdder itemAdder, MMOItems mmoItems, ConsoleCommand consoleCommand, Economy economy, SoundManager soundManager) {
        this.numberFormat = numberFormat;
        this.betonTagManager = betonTagManager;
        this.betonPointsManager = betonPointsManager;
        this.fullInventory = fullInventory;
        this.itemAdder = itemAdder;
        this.mmoItems = mmoItems;
        this.consoleCommand = consoleCommand;
        this.economy = economy;
        this.soundManager = soundManager;
    }

//    Background

    @Override
    public ItemStack background() {
        final ItemStack backgroundItem = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        final ItemMeta backgroundItemMeta = backgroundItem.getItemMeta();

        backgroundItemMeta.displayName(Component.text(" "));
        backgroundItem.setItemMeta(backgroundItemMeta);
        return backgroundItem;
    }

    @Override
    public ItemStack background(Material material) {
        final ItemStack backgroundItem = new ItemStack(material);
        final ItemMeta backgroundItemMeta = backgroundItem.getItemMeta();

        backgroundItemMeta.displayName(Component.text(" "));
        backgroundItem.setItemMeta(backgroundItemMeta);
        return backgroundItem;
    }

//    Buttons

    @Override
    public ItemStack backButton() {
        final ItemStack backButton = new ItemStack(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODY1MmUyYjkzNmNhODAyNmJkMjg2NTFkN2M5ZjI4MTlkMmU5MjM2OTc3MzRkMThkZmRiMTM1NTBmOGZkYWQ1ZiJ9fX0="));
        final ItemMeta backButtonItemMeta = backButton.getItemMeta();

        backButtonItemMeta.displayName(Component.text(ChatColor.GREEN + "Go Back"));
        backButton.setItemMeta(backButtonItemMeta);
        return backButton;
    }

    @Override
    public ItemStack nextPageButton() {
        final ItemStack nextPageItem = new ItemStack(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmEzYjhmNjgxZGFhZDhiZjQzNmNhZThkYTNmZTgxMzFmNjJhMTYyYWI4MWFmNjM5YzNlMDY0NGFhNmFiYWMyZiJ9fX0="));
        final ItemMeta nextPageItemMeta = nextPageItem.getItemMeta();

        nextPageItemMeta.displayName(Component.text(ChatColor.GREEN + "Next Page"));
        nextPageItem.setItemMeta(nextPageItemMeta);
        return nextPageItem;
    }

    @Override
    public ItemStack previousPageButton() {
        final ItemStack previousPageItem = new ItemStack(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODY1MmUyYjkzNmNhODAyNmJkMjg2NTFkN2M5ZjI4MTlkMmU5MjM2OTc3MzRkMThkZmRiMTM1NTBmOGZkYWQ1ZiJ9fX0="));
        final ItemMeta previousPageItemMeta = previousPageItem.getItemMeta();

        previousPageItemMeta.displayName(Component.text(ChatColor.GREEN + "Previous Page"));
        previousPageItem.setItemMeta(previousPageItemMeta);
        return previousPageItem;
    }

    @Override
    public ItemStack firstPageButton() {
        final ItemStack firstPageItem = new ItemStack(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODE2ZWEzNGE2YTZlYzVjMDUxZTY5MzJmMWM0NzFiNzAxMmIyOThkMzhkMTc5ZjFiNDg3YzQxM2Y1MTk1OWNkNCJ9fX0="));
        final ItemMeta firstPageItemMeta = firstPageItem.getItemMeta();

        firstPageItemMeta.displayName(Component.text(ChatColor.GREEN + "First Page"));
        firstPageItem.setItemMeta(firstPageItemMeta);
        return firstPageItem;
    }

    @Override
    public ItemStack lastPageButton() {
        final ItemStack lastPageItem = new ItemStack(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWM5ZWM3MWMxMDY4ZWM2ZTAzZDJjOTI4N2Y5ZGE5MTkzNjM5ZjNhNjM1ZTJmYmQ1ZDg3YzJmYWJlNjQ5OSJ9fX0="));
        final ItemMeta lastPageItemMeta = lastPageItem.getItemMeta();

        lastPageItemMeta.displayName(Component.text(ChatColor.GREEN + "Last Page"));
        lastPageItem.setItemMeta(lastPageItemMeta);
        return lastPageItem;
    }

//    Name

    @Override
    public String guiName(String name) {
        if (name.length() > 21)
            System.out.println("Your GUI '" + name + "' found within " + Thread.currentThread().getStackTrace()[2].getClassName().substring(31) + " is longer than what the inventory can NEATLY contain.");
        return
                ChatColor.WHITE.toString() + ChatColor.BOLD + "»" +
                        ChatColor.GRAY + ChatColor.BOLD + "» " +
                        ChatColor.DARK_GRAY + name +
                        ChatColor.GRAY + ChatColor.BOLD + " «" +
                        ChatColor.WHITE + ChatColor.BOLD + "«";
    }

    //    Item Builders

    @Override
    public ItemStack itemCreator(Material material, String name, String[] lore, boolean enchanted) {
        ItemStack complete = new ItemStack(material);
        return itemCreator(complete, name, lore, enchanted);
    }

    @Override
    public ItemStack itemCreator(Material material, String name, String[] lore) {
        ItemStack complete = new ItemStack(material);
        return itemCreator(complete, name, lore, false);
    }

    @Override
    public ItemStack itemCreator(String skullTexture, String name, String[] lore) {
        ItemStack complete = SkullCreator.itemFromBase64(skullTexture);
        return itemCreator(complete, name, lore, false);
    }

    @Override
    public ItemStack itemCreator(ItemStack itemStack, String name, String[] lore, boolean enchanted) {
        ItemStack complete = itemStack;
        final ItemMeta completeItemMeta = complete.getItemMeta();
        completeItemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        if (enchanted) {
            completeItemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            completeItemMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        }

        completeItemMeta.setDisplayName(name);

        List<String> itemLore = new ArrayList<>();
        for (String lore2 : lore)
            itemLore.add(lore2);

        complete.setItemMeta(completeItemMeta);
        complete.setLore(itemLore);

        return complete;
    }

//    @Override
//    public ItemStack questInactive(String questName, TextComponent[] questDescription, TextComponent[] questRewards, String questGiver, String questGiverArea) {
//        ItemStack inactive = new ItemStack(Material.PAPER);
//        final ItemMeta inactiveItemMeta = inactive.getItemMeta();
//
//        inactiveItemMeta.displayName(Component.text(ChatColor.RED.toString() + ChatColor.BOLD + "[Inactive] " + ChatColor.WHITE + ChatColor.BOLD + questName));
//
//        List<Component> lore = new ArrayList<>();
//        lore.add(Linebreak.PREFIX.getComponent());
//        for (TextComponent lore2 : questDescription)
//            lore.add(lore2);
//        lore.add(Linebreak.PREFIX.getComponent());
//        lore.add(Component.text(ChatColor.YELLOW.toString() + ChatColor.BOLD + "Rewards:"));
//        for (TextComponent reward : questRewards)
//            lore.add(Component.text(Prefix.PREFIX.getString()).append(reward).color(NamedTextColor.WHITE));
//        lore.add(Linebreak.PREFIX.getComponent());
//        lore.add(Component.text(ChatColor.GREEN + "Given by " + ChatColor.GOLD + questGiver + ChatColor.GREEN + " in the " + ChatColor.YELLOW + questGiverArea + ChatColor.GREEN + "!"));
//
//        inactive.setItemMeta(inactiveItemMeta);
//        inactive.lore(lore);
//
//        return inactive;
//    }
//
//    @Override
//    public ItemStack questInactive(String questName, String[] questDescription, String[] questRewards, String questGiver, String questGiverArea) {
//        ItemStack inactive = new ItemStack(Material.PAPER);
//        final ItemMeta inactiveItemMeta = inactive.getItemMeta();
//
//        inactiveItemMeta.displayName(Component.text(ChatColor.RED.toString() + ChatColor.BOLD + "[Inactive] " + ChatColor.WHITE + ChatColor.BOLD + questName));
//
//        List<String> lore = new ArrayList<>();
//        lore.add("");
//        for (String lore2 : questDescription)
//            lore.add(lore2);
//        lore.add("");
//        lore.add(ChatColor.YELLOW.toString() + ChatColor.BOLD + "Rewards:");
//        for (String reward : questRewards)
//            lore.add(Prefix.PREFIX.getString() + ChatColor.WHITE + reward);
//        lore.add("");
//        lore.add(ChatColor.GREEN + "Given by " + ChatColor.GOLD + questGiver + ChatColor.GREEN + " in the " + ChatColor.YELLOW + questGiverArea + ChatColor.GREEN + "!");
//
//        inactive.setItemMeta(inactiveItemMeta);
//        inactive.setLore(lore);
//
//        return inactive;
//    }
//
//    @Override
//    public ItemStack questActive(String questName, TextComponent[] questDescription, TextComponent[] questRewards, String questGiver, String questGiverArea) {
//        ItemStack active = new ItemStack(Material.BOOK);
//        final ItemMeta activeItemMeta = active.getItemMeta();
//
//        activeItemMeta.displayName(Component.text(ChatColor.GOLD.toString() + ChatColor.BOLD + "[Active] " + ChatColor.WHITE + ChatColor.BOLD + questName));
//
//        List<Component> lore = new ArrayList<>();
//        lore.add(Linebreak.PREFIX.getComponent());
//        for (TextComponent lore2 : questDescription)
//            lore.add(lore2);
//        lore.add(Linebreak.PREFIX.getComponent());
//        lore.add(Component.text(ChatColor.YELLOW.toString() + ChatColor.BOLD + "Rewards:"));
//        for (TextComponent reward : questRewards)
//            lore.add(Component.text(Prefix.PREFIX.getString()).append(reward).color(NamedTextColor.WHITE));
//        lore.add(Linebreak.PREFIX.getComponent());
//        lore.add(Component.text(ChatColor.GREEN + "Given by " + ChatColor.GOLD + questGiver + ChatColor.GREEN + " in the " + ChatColor.YELLOW + questGiverArea + ChatColor.GREEN + "!"));
//
//        active.setItemMeta(activeItemMeta);
//        active.lore(lore);
//
//        return active;
//    }
//
//    @Override
//    public ItemStack questActive(String questName, String[] questDescription, String[] questRewards, String questGiver, String questGiverArea) {
//        ItemStack active = new ItemStack(Material.BOOK);
//        final ItemMeta activeItemMeta = active.getItemMeta();
//
//        activeItemMeta.displayName(Component.text(ChatColor.GOLD.toString() + ChatColor.BOLD + "[Active] " + ChatColor.WHITE + ChatColor.BOLD + questName));
//
//        List<String> lore = new ArrayList<>();
//        lore.add("");
//        for (String lore2 : questDescription)
//            lore.add(lore2);
//        lore.add("");
//        lore.add(ChatColor.YELLOW.toString() + ChatColor.BOLD + "Rewards:");
//        for (String reward : questRewards)
//            lore.add(Prefix.PREFIX.getString() + ChatColor.WHITE + reward);
//        lore.add("");
//        lore.add(ChatColor.GREEN + "Given by " + ChatColor.GOLD + questGiver + ChatColor.GREEN + " in the " + ChatColor.YELLOW + questGiverArea + ChatColor.GREEN + "!");
//
//        active.setItemMeta(activeItemMeta);
//        active.setLore(lore);
//
//        return active;
//    }
//
//    @Override
//    public ItemStack questComplete(String questName, TextComponent[] questDescription, TextComponent[] questRewards, String questGiver, String questGiverArea) {
//        ItemStack complete = new ItemStack(Material.ENCHANTED_BOOK);
//        final ItemMeta completeItemMeta = complete.getItemMeta();
//
//        completeItemMeta.displayName(Component.text(ChatColor.GREEN.toString() + ChatColor.BOLD + "[Complete] " + ChatColor.WHITE + ChatColor.BOLD + questName));
//
//        List<Component> lore = new ArrayList<>();
//        lore.add(Linebreak.PREFIX.getComponent());
//        for (TextComponent lore2 : questDescription)
//            lore.add(lore2);
//        lore.add(Linebreak.PREFIX.getComponent());
//        lore.add(Component.text(ChatColor.YELLOW.toString() + ChatColor.BOLD + "Rewards:"));
//        for (TextComponent reward : questRewards)
//            lore.add(Component.text(Prefix.PREFIX.getString()).append(reward).color(NamedTextColor.WHITE));
//        lore.add(Linebreak.PREFIX.getComponent());
//        lore.add(Component.text(ChatColor.GREEN + "Given by " + ChatColor.GOLD + questGiver + ChatColor.GREEN + " in the " + ChatColor.YELLOW + questGiverArea + ChatColor.GREEN + "!"));
//
//        complete.setItemMeta(completeItemMeta);
//        complete.lore(lore);
//
//        return complete;
//    }
//
//    @Override
//    public ItemStack questComplete(String questName, String[] questDescription, String[] questRewards, String questGiver, String questGiverArea) {
//        ItemStack complete = new ItemStack(Material.ENCHANTED_BOOK);
//        final ItemMeta completeItemMeta = complete.getItemMeta();
//
//        completeItemMeta.displayName(Component.text(ChatColor.GREEN.toString() + ChatColor.BOLD + "[Complete] " + ChatColor.WHITE + ChatColor.BOLD + questName));
//
//        List<String> lore = new ArrayList<>();
//        lore.add("");
//        for (String lore2 : questDescription)
//            lore.add(lore2);
//        lore.add("");
//        lore.add(ChatColor.YELLOW.toString() + ChatColor.BOLD + "Rewards:");
//        for (String reward : questRewards)
//            lore.add(Prefix.PREFIX.getString() + ChatColor.WHITE + reward);
//        lore.add("");
//        lore.add(ChatColor.GREEN + "Given by " + ChatColor.GOLD + questGiver + ChatColor.GREEN + " in the " + ChatColor.YELLOW + questGiverArea + ChatColor.GREEN + "!");
//
//        complete.setItemMeta(completeItemMeta);
//        complete.setLore(lore);
//
//        return complete;
//    }
//
//    @Override
//    public ItemStack questUnclaimed(String questName, String[] questDescription, String[] questRewards, String questGiver, String questGiverArea) {
//        ItemStack unclaimed = new ItemStack(Material.ENCHANTED_BOOK);
//        final ItemMeta unclaimedItemMeta = unclaimed.getItemMeta();
//
//        unclaimedItemMeta.displayName(Component.text(ChatColor.GOLD.toString() + ChatColor.BOLD + "[Unclaimed] " + ChatColor.WHITE + ChatColor.BOLD + questName));
//
//        List<String> lore = new ArrayList<>();
//        lore.add("");
//        for (String lore2 : questDescription)
//            lore.add(lore2);
//        lore.add("");
//        lore.add(ChatColor.YELLOW.toString() + ChatColor.BOLD + "Rewards:");
//        for (String reward : questRewards)
//            lore.add(Prefix.PREFIX.getString() + ChatColor.WHITE + reward);
//        lore.add("");
//        lore.add(ChatColor.GREEN + "Given by " + ChatColor.GOLD + questGiver + ChatColor.GREEN + " in the " + ChatColor.YELLOW + questGiverArea + ChatColor.GREEN + "!");
//        lore.add("");
//        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Claim Rewards");
//
//        unclaimed.setItemMeta(unclaimedItemMeta);
//        unclaimed.setLore(lore);
//
//        return unclaimed;
//    }

//    @Override
//    public ItemStack jobActive(String questName, String questDescription, ItemStack[] questRewards, int[] rewardsAmount, String[] currencyRewards, int[] currencyRewardsAmount, String questGiver, String questGiverArea) {
//        ItemStack unclaimed = new ItemStack(Material.PAPER);
//        final ItemMeta unclaimedItemMeta = unclaimed.getItemMeta();
//
//        unclaimedItemMeta.displayName(Component.text(ChatColor.GREEN.toString() + ChatColor.BOLD + "[Active] " + ChatColor.WHITE + ChatColor.BOLD + questName));
//
//        List<String> lore = new ArrayList<>();
//        lore.add("");
//        lore.add(questDescription);
//        lore.add("");
//        lore.add(ChatColor.YELLOW.toString() + ChatColor.BOLD + "Rewards:");
//        int reward = 0;
//        for (ItemStack lore3 : questRewards) {
//            if (lore3 != null) {
//                lore.add("  " + Prefix.PREFIX.getString() + ChatColor.GOLD + rewardsAmount[reward] + "x " + lore3.getItemMeta().getDisplayName());
//                reward++;
//            }
//        }
//
//        int currency = 0;
//        for (String lore2 : currencyRewards) {
//            if (currencyRewards != null) {
//                String newLore = "  " + Prefix.PREFIX.getString() + ChatColor.GOLD + numberFormat.numberFormat(currencyRewardsAmount[currency]) + " ";
//                switch (lore2) {
//                    case "exp":
//                        lore.add(newLore + PrisonStatsDisplay.EXPERIENCE_AMOUNT.getName());
//                        currency++;
//                        break;
//                    case "petexp":
//                        lore.add(newLore + PrisonStatsDisplay.PET_EXPERIENCE_AMOUNT.getName());
//                        currency++;
//                        break;
//                    case "miningpassexp":
//                        lore.add(newLore + PrisonStatsDisplay.MINING_PASS_EXPERIENCE.getName());
//                        currency++;
//                        break;
//                }
//            }
//        }
//        lore.add("");
//        lore.add(ChatColor.GREEN + "Given by " + ChatColor.GOLD + questGiver + ChatColor.GREEN + " in the " + ChatColor.YELLOW + questGiverArea + ChatColor.GREEN + "!");
//
//        unclaimed.setItemMeta(unclaimedItemMeta);
//        unclaimed.setLore(lore);
//
//        return unclaimed;
//    }
//
//    @Override
//    public ItemStack jobActive(String questName, String questDescription, ItemStack[] questRewards, int[] rewardsAmount, String[] currencyRewards, int[] currencyRewardsAmount, String resetTime) {
//        ItemStack unclaimed = new ItemStack(Material.PAPER);
//        final ItemMeta unclaimedItemMeta = unclaimed.getItemMeta();
//
//        unclaimedItemMeta.displayName(Component.text(ChatColor.GREEN.toString() + ChatColor.BOLD + "[Active] " + ChatColor.WHITE + ChatColor.BOLD + questName));
//
//        List<String> lore = new ArrayList<>();
//        lore.add("");
//        lore.add(questDescription);
//        lore.add("");
//        lore.add(ChatColor.YELLOW.toString() + ChatColor.BOLD + "Rewards:");
//        int reward = 0;
//        for (ItemStack lore3 : questRewards) {
//            if (lore3 != null) {
//                lore.add("  " + Prefix.PREFIX.getString() + ChatColor.GOLD + rewardsAmount[reward] + "x " + lore3.getItemMeta().getDisplayName());
//                reward++;
//            }
//        }
//
//        int currency = 0;
//        for (String lore2 : currencyRewards) {
//            if (currencyRewards != null) {
//                String newLore = "  " + Prefix.PREFIX.getString() + ChatColor.GOLD + numberFormat.numberFormat(currencyRewardsAmount[currency]) + " ";
//                switch (lore2) {
//                    case "exp":
//                        lore.add(newLore + PrisonStatsDisplay.EXPERIENCE_AMOUNT.getName());
//                        currency++;
//                        break;
//                    case "petexp":
//                        lore.add(newLore + PrisonStatsDisplay.PET_EXPERIENCE_AMOUNT.getName());
//                        currency++;
//                        break;
//                    case "miningpassexp":
//                        lore.add(newLore + PrisonStatsDisplay.MINING_PASS_EXPERIENCE.getName());
//                        currency++;
//                        break;
//                }
//            }
//        }
//        lore.add("");
//        lore.add(ChatColor.GREEN + resetTime + ChatColor.YELLOW + " left until this Job resets!");
//
//        unclaimed.setItemMeta(unclaimedItemMeta);
//        unclaimed.setLore(lore);
//
//        return unclaimed;
//    }

    /*
     *   Displays Quests for Player
     * */
    @Override
    public void questMenuGenerator(Player player, QuestGiver questGiver, Material backgroundColor) {
        int questAmount = 0;
        int startX = 2;
        int startY = 1;
        int length = 5;
        int height = 3;

        for (QuestList quest : QuestList.values())
            if (quest.getQuestGiver() == questGiver)
                questAmount += 1;

        switch (questAmount) {
            case 1:
                startX = 4;
                break;
            case 2:
            case 3:
                startX = 3;
                break;
            case 4:
            case 5:
                startX = 2;
                break;
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
                startX = 2;
                height = 4;
                break;
            case 11:
            case 12:
                startX = 2;
                height = 5;
                break;
        }

        String packageBuilder = "default-" + WordUtils.capitalizeFully(questGiver.getArea().name().replace('_', ' ')).replace(' ', '_') + "-" + WordUtils.capitalizeFully(questGiver.name() + ".");

        int questsCompleted = 0;
        for (QuestList quest : QuestList.values())
            if (quest.getQuestGiver() == questGiver)
                if (betonTagManager.hasTag(player, packageBuilder + quest.name() + "_COMPLETED"))
                    questsCompleted++;

        ChestGui gui = new ChestGui(height + 1, guiName(WordUtils.capitalizeFully(questGiver.name().replace('_', ' ')) + " Quests " + questsCompleted + "/" + questAmount));
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        OutlinePane background = new OutlinePane(0, 0, 9, height + 1, Pane.Priority.LOWEST);
        StaticPane display = new StaticPane(0, 0, 9, height + 1, Pane.Priority.LOW);
        OutlinePane main = new OutlinePane(startX, startY, length, height - 2, Pane.Priority.LOW);

        background.addItem(new GuiItem(background(backgroundColor)));
        background.setRepeat(true);

        for (QuestList quest : QuestList.values())
            if (quest.getQuestGiver() == questGiver && quest.getQuestGiver() != QuestGiver.KLAUS && quest.getQuestGiver() != QuestGiver.JACK)
                main.addItem(questItemGenerator(player, quest));
            else if (quest.getQuestGiver() == QuestGiver.KLAUS && questGiver == QuestGiver.KLAUS)
                main.addItem(repeatableQuestItemGenerator(player, quest));
            else if (quest.getQuestGiver() == QuestGiver.JACK && questGiver == QuestGiver.JACK)
                main.addItem(repeatableQuestItemGenerator(player, quest));

        display.addItem(new GuiItem(backButton(), e -> player.performCommand("QuestAreaMenu " + questGiver.getArea().name())), 4, height);

        gui.addPane(background);
        gui.addPane(display);
        gui.addPane(main);
        gui.show(player);
    }

    /*
     *   Displays Active Quests for Player
     * */
    @Override
    public void activeQuestMenu(Player player) {
        int questAmount = 0;
        int startX = 2;
        int startY = 1;
        int length = 5;
        int height = 3;

        for (QuestList quest : QuestList.values()) {
            if (betonTagManager.hasTag(player, "default-" + WordUtils.capitalizeFully(quest.getQuestGiver().getArea().name()) + "-" + WordUtils.capitalizeFully(quest.getQuestGiver().name()) + "." + quest.name() + "_STARTED") &&
                    !betonTagManager.hasTag(player, "default-" + WordUtils.capitalizeFully(quest.getQuestGiver().getArea().name()) + "-" + WordUtils.capitalizeFully(quest.getQuestGiver().name()) + "." + quest.name() + "_COMPLETED"))
                questAmount += 1;
        }
        if (questAmount == 1)
            startX = 4;
        else if (questAmount == 2 || questAmount == 3)
            startX = 3;
        else if (questAmount == 4)
            startX = 2;

        ChestGui gui = new ChestGui(height + 1, guiName("Active Quests " + questAmount));
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        OutlinePane background = new OutlinePane(0, 0, 9, height + 1, Pane.Priority.LOWEST);
        StaticPane display = new StaticPane(0, 0, 9, height + 1, Pane.Priority.LOW);
        OutlinePane main = new OutlinePane(startX, startY, length, height - 2, Pane.Priority.LOW);

        background.addItem(new GuiItem(background(Material.GREEN_STAINED_GLASS_PANE)));
        background.setRepeat(true);

        for (QuestList quest : QuestList.values())
            if (betonTagManager.hasTag(player, "default-" + WordUtils.capitalizeFully(quest.getQuestGiver().getArea().name()) + "-" + WordUtils.capitalizeFully(quest.getQuestGiver().name()) + "." + quest.name() + "_STARTED") &&
                    !betonTagManager.hasTag(player, "default-" + WordUtils.capitalizeFully(quest.getQuestGiver().getArea().name()) + "-" + WordUtils.capitalizeFully(quest.getQuestGiver().name()) + "." + quest.name() + "_COMPLETED"))
                main.addItem(questItemGenerator(player, quest));

        display.addItem(new GuiItem(backButton(), e -> player.performCommand("Quest")), 4, height);

        gui.addPane(background);
        gui.addPane(display);
        gui.addPane(main);
        gui.show(player);
    }

    /*
     *   Displays Unclaimed Quests for Player
     * */
    @Override
    public void unclaimedQuestMenu(Player player) {
        int questAmount = 0;
        int startX = 2;
        int startY = 1;
        int length = 5;
        int height = 3;

        for (QuestList quest : QuestList.values())
            if (betonTagManager.hasTag(player, "default-" + WordUtils.capitalizeFully(quest.getQuestGiver().getArea().name()) + "-" + WordUtils.capitalizeFully(quest.getQuestGiver().name()) + "." + quest.name() + "_COMPLETED") &&
                    !betonTagManager.hasTag(player, "default-" + WordUtils.capitalizeFully(quest.getQuestGiver().getArea().name()) + "-" + WordUtils.capitalizeFully(quest.getQuestGiver().name()) + "." + quest.name() + "_CLAIMED"))
                questAmount += 1;

        switch (questAmount) {
            case 1:
                startX = 4;
                break;
            case 2:
            case 3:
                startX = 3;
                break;
            case 4:
                startX = 2;
                break;
            case 5:
                startX = 2;
                height = 4;
                break;
        }

        ChestGui gui = new ChestGui(height + 1, guiName("Unclaimed Quests " + questAmount));
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        OutlinePane background = new OutlinePane(0, 0, 9, height + 1, Pane.Priority.LOWEST);
        StaticPane display = new StaticPane(0, 0, 9, height + 1, Pane.Priority.LOW);
        OutlinePane main = new OutlinePane(startX, startY, length, height - 2, Pane.Priority.LOW);

        background.addItem(new GuiItem(background(Material.YELLOW_STAINED_GLASS_PANE)));
        background.setRepeat(true);

        for (QuestList quest : QuestList.values())
            if (betonTagManager.hasTag(player, "default-" + WordUtils.capitalizeFully(quest.getQuestGiver().getArea().name()) + "-" + WordUtils.capitalizeFully(quest.getQuestGiver().name()) + "." + quest.name() + "_COMPLETED") &&
                    !betonTagManager.hasTag(player, "default-" + WordUtils.capitalizeFully(quest.getQuestGiver().getArea().name()) + "-" + WordUtils.capitalizeFully(quest.getQuestGiver().name()) + "." + quest.name() + "_CLAIMED"))
                main.addItem(questItemGenerator(player, quest));

        display.addItem(new GuiItem(backButton(), e -> player.performCommand("Quest")), 4, height);

        gui.addPane(background);
        gui.addPane(display);
        gui.addPane(main);
        gui.show(player);
    }

    private GuiItem questItemGenerator(Player player, QuestList quests) {
        String packageDir = "default-" + WordUtils.capitalizeFully(quests.getQuestGiver().getArea().name().replace('_', ' ')).replace(' ', '_') + "-" + WordUtils.capitalizeFully(quests.getQuestGiver().name().replace('_', ' ')).replace(' ', '_') + ".";
        String startedTag = packageDir + quests.name() + "_STARTED";
        String completedTag = packageDir + quests.name() + "_COMPLETED";
        String claimedTag = packageDir + quests.name() + "_CLAIMED";
        ItemStack item = new ItemStack(Material.PAPER);
        if (betonTagManager.hasTag(player, claimedTag) || betonTagManager.hasTag(player, completedTag))
            item = new ItemStack(Material.ENCHANTED_BOOK);
        else if (betonTagManager.hasTag(player, startedTag))
            item = new ItemStack(Material.BOOK);
        final ItemMeta itemItemMeta = item.getItemMeta();

        itemItemMeta.displayName(Component.text(ChatColor.RED.toString() + ChatColor.BOLD + "[INACTIVE] " + ChatColor.WHITE + WordUtils.capitalizeFully(quests.name().replace("_", " "))));
        if (betonTagManager.hasTag(player, claimedTag))
            itemItemMeta.displayName(Component.text(ChatColor.GOLD.toString() + ChatColor.BOLD + "[COMPLETED] " + ChatColor.WHITE + WordUtils.capitalizeFully(quests.name().replace("_", " "))));
        else if (betonTagManager.hasTag(player, completedTag))
            itemItemMeta.displayName(Component.text(ChatColor.YELLOW.toString() + ChatColor.BOLD + "[UNCLAIMED] " + ChatColor.WHITE + WordUtils.capitalizeFully(quests.name().replace("_", " "))));
        else if (betonTagManager.hasTag(player, startedTag))
            itemItemMeta.displayName(Component.text(ChatColor.GREEN.toString() + ChatColor.BOLD + "[ACTIVE] " + ChatColor.WHITE + WordUtils.capitalizeFully(quests.name().replace("_", " "))));

        List<String> lore = new ArrayList<>();
        lore.add("");
        if (betonTagManager.hasTag(player, startedTag) && !betonTagManager.hasTag(player, completedTag))
            for (String questLore : quests.getQuestDescription()) {
                if (questLore.contains("%"))
                    questLore = questLore.replaceAll("(%.*?%)", PlaceholderAPI.setPlaceholders(player, "%" + StringUtils.substringBetween(questLore, "%", "%") + "%"));
                lore.add(ChatColor.translateAlternateColorCodes('&', questLore));
            }
        else if (!betonTagManager.hasTag(player, startedTag) || betonTagManager.hasTag(player, completedTag))
            for (String questLore : quests.getQuestDescription()) {
                if (questLore.contains("%"))
                    questLore = questLore.replaceAll("(%.*?%\\/)", "");
                lore.add(ChatColor.translateAlternateColorCodes('&', questLore));
            }
        if (quests.getRewardText() != null || quests.getRewardItems() != null || quests.getRewardMainEXP() > 0 || quests.getRewardProfessionEXP() != null || quests.getRewardMoney() > 0) {
            lore.add("");
            lore.add(ChatColor.YELLOW.toString() + ChatColor.BOLD + "REWARDS:");
            if (quests.getRewardText() != null)
                for (String questTextReward : quests.getRewardText()) {
                    lore.add(PREFIX + ChatColor.translateAlternateColorCodes('&', questTextReward));
                }
            if (quests.getRewardItems() != null)
                for (String questItemReward : quests.getRewardItems()) {
                    String[] reward = questItemReward.split(" ");
                    lore.add(PREFIX + ChatColor.GOLD + reward[2] + ChatColor.DARK_GRAY + "x " + mmoItems.getItem(reward[0], reward[1]).getItemMeta().getDisplayName());
                }
            if (quests.getRewardMainEXP() > 0)
                lore.add(PREFIX + ChatColor.GOLD + quests.getRewardMainEXP() + " " + AdventureStatsDisplay.EXP.getName());
            if (quests.getRewardProfessionEXP() != null)
                for (String questProfessionEXPReward : quests.getRewardProfessionEXP()) {
                    String[] professionReward = questProfessionEXPReward.split(",");
                    lore.add(PREFIX + ChatColor.GOLD + numberFormat.numberFormat(Integer.valueOf(professionReward[1])) + " " + WordUtils.capitalizeFully(professionReward[0]) + " EXP");
                }
            if (quests.getRewardMoney() > 0)
                lore.add(PREFIX + ChatColor.GOLD + numberFormat.numberFormat(quests.getRewardMoney()) + " " + PrisonStatsDisplay.MONEY_AMOUNT.getName());

            if (betonTagManager.hasTag(player, completedTag) && !betonTagManager.hasTag(player, claimedTag)) {
                lore.add("");
                lore.add(PREFIX + ChatColor.YELLOW + "Click to Claim Reward");
            }
        }
        item.setItemMeta(itemItemMeta);
        item.setLore(lore);

        return new GuiItem(item, e -> {
            if (betonTagManager.hasTag(player, completedTag) && !betonTagManager.hasTag(player, claimedTag)) {
                if (quests.getRewardItems() != null) {
                    if (!fullInventory.fullInventory(player)) {
                        for (String questItemReward : quests.getRewardItems()) {
                            String[] reward = questItemReward.split(" ");
                            itemAdder.itemAdder(player, MMOItemsHelperImpl.getItem(reward[0], reward[1]).asQuantity(Integer.valueOf(reward[2])));
                        }
                        if (quests.getRewardProfessionEXP() != null)
                            for (String questProfessionEXPReward : quests.getRewardProfessionEXP()) {
                                String[] professionReward = questProfessionEXPReward.split(",");
                                consoleCommand.consoleCommand("mmocore admin exp give " + player.getName() + " " + professionReward[0] + " " + professionReward[1]);
                            }
                        if (quests.getRewardMoney() > 0)
                            economy.giveMoney(player, quests.getRewardMoney());
                        if (quests.getRewardMainEXP() > 0)
                            consoleCommand.consoleCommand("reward experience " + quests.getRewardMainEXP() + " " + player.getName());
                        betonTagManager.giveTag(player, claimedTag);
                        player.performCommand("questViewer " + quests.getQuestGiver().name());
                        soundManager.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                    }
                } else {
                    if (quests.getRewardProfessionEXP() != null)
                        for (String questProfessionEXPReward : quests.getRewardProfessionEXP()) {
                            String[] professionReward = questProfessionEXPReward.split(",");
                            consoleCommand.consoleCommand("mmocore admin exp give " + player.getName() + " " + professionReward[0] + " " + professionReward[1]);
                        }
                    if (quests.getRewardMoney() > 0)
                        economy.giveMoney(player, quests.getRewardMoney());
                    if (quests.getRewardMainEXP() > 0)
                        consoleCommand.consoleCommand("reward experience " + quests.getRewardMainEXP() + " " + player.getName());
                    betonTagManager.giveTag(player, claimedTag);
                    player.performCommand("questViewer " + quests.getQuestGiver().name());
                    soundManager.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
                }
            }
        });
    }

    private GuiItem repeatableQuestItemGenerator(Player player, QuestList quests) {
        String packageDir = "default-" + WordUtils.capitalizeFully(quests.getQuestGiver().getArea().name().replace('_', ' ')).replace(' ', '_') + "-" + WordUtils.capitalizeFully(quests.getQuestGiver().name()) + ".";
        String startedTag = packageDir + quests.name() + "_STARTED";
        ItemStack item = new ItemStack(Material.PAPER);
        if (betonTagManager.hasTag(player, startedTag))
            item = new ItemStack(Material.BOOK);
        final ItemMeta itemItemMeta = item.getItemMeta();

        itemItemMeta.displayName(Component.text(ChatColor.RED.toString() + ChatColor.BOLD + "[INACTIVE] " + ChatColor.WHITE + WordUtils.capitalizeFully(quests.name().replace("_", " "))));

        if (betonTagManager.hasTag(player, startedTag))
            itemItemMeta.displayName(Component.text(ChatColor.GREEN.toString() + ChatColor.BOLD + "[ACTIVE] " + ChatColor.WHITE + WordUtils.capitalizeFully(quests.name().replace("_", " "))));

        List<String> lore = new ArrayList<>();
        lore.add("");
        if (betonTagManager.hasTag(player, startedTag))
            for (String questLore : quests.getQuestDescription()) {
                if (questLore.contains("%"))
                    questLore = questLore.replaceAll("(%.*?%)", PlaceholderAPI.setPlaceholders(player, "%" + StringUtils.substringBetween(questLore, "%", "%") + "%"));
                lore.add(ChatColor.translateAlternateColorCodes('&', questLore));
            }
        else
            for (String questLore : quests.getQuestDescription()) {
                if (questLore.contains("%"))
                    questLore = questLore.replaceAll("(%.*?%\\/)", "");
                lore.add(ChatColor.translateAlternateColorCodes('&', questLore));
            }
        lore.add("");
        lore.add(ChatColor.YELLOW.toString() + ChatColor.BOLD + "SUMMONS: " + ChatColor.GOLD + betonPointsManager.getPoints(player, packageDir + quests.name() + "_SUMMONED"));
//            Currently not giving rewards for completing quest
//        lore.add(ChatColor.YELLOW.toString() + ChatColor.BOLD + "REWARDS:");
//        if (quests.getRewardItems() != null)
//            for (String questItemReward : quests.getRewardItems()) {
//                String[] reward = questItemReward.split(" ");
//                lore.add(PREFIX + ChatColor.GOLD + reward[2] + ChatColor.DARK_GRAY + "x " + mmoItems.getItem(reward[0], reward[1]).getItemMeta().getDisplayName());
//            }
//        if (quests.getRewardMainEXP() > 0)
//            lore.add(PREFIX + ChatColor.GOLD + quests.getRewardMainEXP() + " " + AdventureStatsDisplay.EXP.getName());
//        if (quests.getRewardProfessionEXP() != null)
//            for (String questProfessionEXPReward : quests.getRewardProfessionEXP()) {
//                String[] professionReward = questProfessionEXPReward.split(",");
//                lore.add(PREFIX + ChatColor.GOLD + numberFormat.numberFormat(Integer.valueOf(professionReward[1])) + " " + WordUtils.capitalizeFully(professionReward[0]) + " EXP");
//            }
//        if (quests.getRewardMoney() > 0)
//            lore.add(PREFIX + ChatColor.GOLD + numberFormat.numberFormat(quests.getRewardMoney()) + " " + PrisonStatsDisplay.MONEY_AMOUNT.getName());
//
//        if (betonTagManager.hasTag(player, completedTag) && !betonTagManager.hasTag(player, claimedTag)) {
//            lore.add("");
//            lore.add(PREFIX + ChatColor.YELLOW + "Click to Claim Reward");
//        }

        item.setItemMeta(itemItemMeta);
        item.setLore(lore);

        return new GuiItem(item);
    }

//    @Override
//    public void jobMenuGenerator(Player player, QuestGiver questGiver, Material backgroundColor) {
//        int startX = 2;
//        int startY = 1;
//        int length = 5;
//        int height = 3;
//
//        String packageBuilder = "default-" + WordUtils.capitalizeFully(questGiver.getArea().name().replace('_',' ')).replace(' ', '_') + "-" + WordUtils.capitalizeFully(questGiver.name() + ".");
//
//        ChestGui gui = new ChestGui(height + 1, guiName(WordUtils.capitalizeFully(questGiver.name()) + " Jobs " + betonPointsManager.getPoints(player, packageBuilder + "QUESTS_COMPLETED")));
//        gui.setOnGlobalClick(event -> event.setCancelled(true));
//
//        OutlinePane background = new OutlinePane(0, 0, 9, height + 1, Pane.Priority.LOWEST);
//        StaticPane display = new StaticPane(0, 0, 9, height + 1, Pane.Priority.LOW);
//        OutlinePane main = new OutlinePane(startX, startY, length, height - 2, Pane.Priority.LOW);
//        OutlinePane main2 = new OutlinePane(startX, startY, length, height - 2, Pane.Priority.LOW);
//
//        background.addItem(new GuiItem(background(backgroundColor)));
//        background.setRepeat(true);
//
//        main2.addItem(jobAvailable(questGiver));
//        main2.addItem(jobAvailable(questGiver));
//        main2.addItem(jobAvailable(questGiver));
//        if (betonPointsManager.getPoints(player, packageBuilder + "QUESTS_COMPLETED") >= 250)
//            main2.addItem(jobAvailable(questGiver));
//        else
//            main2.addItem(jobLocked(player, packageBuilder, 1));
//        if (betonPointsManager.getPoints(player, packageBuilder + "QUESTS_COMPLETED") >= 500)
//            main2.addItem(jobAvailable(questGiver));
//        else
//            main2.addItem(jobLocked(player, packageBuilder, 2));
//
//        for (Jobs jobs : Jobs.values())
//            if (jobs.getQuestGiver() == questGiver) {
//                String startedTag = packageBuilder + jobs.name() + "_STARTED";
////                plugin.getLogger().info(startedTag);
//                if (betonTagManager.hasTag(player, startedTag))
//                    main.addItem(jobItemGenerator(player, jobs));
//            }
//
//        display.addItem(new GuiItem(backButton(), e -> player.performCommand("QuestAreaMenu " + questGiver.getArea().name())), 4, height);
//
//        gui.addPane(background);
//        gui.addPane(display);
//        gui.addPane(main);
//        gui.addPane(main2);
//        gui.show(player);
//    }


//    private GuiItem jobItemGenerator(Player player, Jobs jobs) {
//        String packageDir = "default-" + WordUtils.capitalizeFully(jobs.getQuestGiver().getArea().name()) + "-" + WordUtils.capitalizeFully(jobs.getQuestGiver().name()) + ".";
//        String startedTag = packageDir + jobs.name() + "_STARTED";
//        ItemStack item = new ItemStack(Material.BOOK);
//        final ItemMeta itemItemMeta = item.getItemMeta();
//
//        itemItemMeta.displayName(Component.text(ChatColor.RED.toString() + ChatColor.BOLD + "[INACTIVE] " + ChatColor.WHITE + WordUtils.capitalizeFully(jobs.name().replace("_", " "))));
//        if (betonTagManager.hasTag(player, startedTag))
//            itemItemMeta.displayName(Component.text(ChatColor.GREEN.toString() + ChatColor.BOLD + "[ACTIVE] " + ChatColor.WHITE + WordUtils.capitalizeFully(jobs.name().replace("_", " "))));
//
//        List<String> lore = new ArrayList<>();
//        lore.add("");
//        for (String questLore : jobs.getQuestDescription()) {
//            if (questLore.contains("%"))
//                questLore = questLore.replaceAll("(%.*?%)", PlaceholderAPI.setPlaceholders(player, "%" + StringUtils.substringBetween(questLore, "%", "%") + "%"));
//            lore.add(questLore);
//        }
//        lore.add("");
//        lore.add(ChatColor.YELLOW.toString() + ChatColor.BOLD + "REWARDS:");
//        if (jobs.getRewardItems() != null)
//            for (String questItemReward : jobs.getRewardItems()) {
//                String[] reward = questItemReward.split(" ");
//                lore.add(PREFIX + ChatColor.GOLD + reward[2] + ChatColor.DARK_GRAY + "x " + mmoItems.getItem(reward[0], reward[1]).getItemMeta().getDisplayName());
//            }
//        if (jobs.getRewardProfessionEXP() != null)
//            for (String questProfessionEXPReward : jobs.getRewardProfessionEXP()) {
//                String[] professionReward = questProfessionEXPReward.split(",");
//                lore.add(PREFIX + ChatColor.BLUE + numberFormat.numberFormat(Integer.valueOf(professionReward[1])) + " " + WordUtils.capitalizeFully(professionReward[0]) + " EXP");
//            }
//        if (jobs.getRewardMoney() > 0)
//            lore.add(PREFIX + ChatColor.GOLD + numberFormat.numberFormat(jobs.getRewardMoney()) + " " + PrisonStatsDisplay.MONEY_AMOUNT.getName());
//        if (jobs.getRewardExperience() > 0)
//            lore.add(PREFIX + ChatColor.GOLD + numberFormat.numberFormat(jobs.getRewardExperience()) + " " + PrisonStatsDisplay.EXPERIENCE_AMOUNT.getName());
//        if (jobs.getRewardPetExperience() > 0)
//            lore.add(PREFIX + ChatColor.GOLD + numberFormat.numberFormat(jobs.getRewardPetExperience()) + " " + PrisonStatsDisplay.PET_EXPERIENCE_AMOUNT.getName());
//
//        item.setItemMeta(itemItemMeta);
//        item.setLore(lore);
//
//        return new GuiItem(item);
//    }

    private GuiItem jobAvailable(QuestGiver questGiver) {
        ItemStack item = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
        final ItemMeta itemItemMeta = item.getItemMeta();

        itemItemMeta.displayName(Component.text(ChatColor.GREEN.toString() + ChatColor.BOLD + "[AVAILABLE] Job Slot"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "Speak with " + ChatColor.YELLOW + WordUtils.capitalizeFully(questGiver.name()) + ChatColor.GRAY + " for a " + ChatColor.YELLOW + "Job" + ChatColor.GRAY + "!");

        item.setItemMeta(itemItemMeta);
        item.setLore(lore);

        return new GuiItem(item);
    }

    private GuiItem jobLocked(Player player, String packageBuilder, int slot) {
        ItemStack item = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        final ItemMeta itemItemMeta = item.getItemMeta();

        itemItemMeta.displayName(Component.text(ChatColor.RED.toString() + ChatColor.BOLD + "[LOCKED] Job Slot"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "Complete " + ChatColor.YELLOW + "Jobs " + ChatColor.GRAY + "to unlock more " + ChatColor.GREEN + "Job Slots" + ChatColor.GRAY + "!");
        lore.add("");
        lore.add(ChatColor.YELLOW + "Completed Jobs: " + betonPointsManager.getPoints(player, packageBuilder + "QUESTS_COMPLETED") + "/" + slot * 250);

        item.setItemMeta(itemItemMeta);
        item.setLore(lore);

        return new GuiItem(item);
    }

//    @Override
//    public void miningPassMenuGenerator(Player player, QuestGiver questGiver, Material backgroundColor) {
//        int height = 5;
//
//        String packageBuilder = "default-" + WordUtils.capitalizeFully(questGiver.getArea().name().replace('_',' ')).replace(' ', '_') + "-" + WordUtils.capitalizeFully(questGiver.name() + ".");
//
//        ChestGui gui = new ChestGui(height + 1, guiName(WordUtils.capitalizeFully(questGiver.name()) + " Jobs " + betonPointsManager.getPoints(player, packageBuilder + "QUESTS_COMPLETED")));
//        gui.setOnGlobalClick(event -> event.setCancelled(true));
//
//        OutlinePane background = new OutlinePane(0, 0, 9, height + 1, Pane.Priority.LOWEST);
//        StaticPane display = new StaticPane(0, 0, 9, height + 1, Pane.Priority.LOW);
//        StaticPane cover = new StaticPane(0, 0, 9, height + 1, Pane.Priority.LOW);
//        OutlinePane daily = new OutlinePane(3, 1, 3, 1, Pane.Priority.NORMAL);
//        OutlinePane weekly = new OutlinePane(2, 2, 5, 1, Pane.Priority.NORMAL);
//        OutlinePane bonus = new OutlinePane(3, 3, 1, 1, Pane.Priority.NORMAL);
//        OutlinePane bonus2 = new OutlinePane(4, 3, 1, 1, Pane.Priority.NORMAL);
//        OutlinePane bonus3 = new OutlinePane(5, 3, 1, 1, Pane.Priority.NORMAL);
//
//        background.addItem(new GuiItem(background(backgroundColor)));
//        background.setRepeat(true);
//
//        cover.addItem(jobAvailable(questGiver), 3, 1);
//        cover.addItem(jobAvailable(questGiver), 4, 1);
//        cover.addItem(jobAvailable(questGiver), 5, 1);
//
//        cover.addItem(jobAvailable(questGiver), 2, 2);
//        cover.addItem(jobAvailable(questGiver), 3, 2);
//        cover.addItem(jobAvailable(questGiver), 4, 2);
//        cover.addItem(jobAvailable(questGiver), 5, 2);
//        cover.addItem(jobAvailable(questGiver), 6, 2);
//
//        cover.addItem(jobAvailable(questGiver), 3, 3);
//        cover.addItem(jobAvailable(questGiver), 4, 3);
//        cover.addItem(jobAvailable(questGiver), 5, 3);
//
//        for (MiningPassJobs jobs : MiningPassJobs.values()) {
//            if (jobs.getType().equalsIgnoreCase("DAILY")) {
//                String startedTag = packageBuilder + jobs.name() + "_STARTED";
////                System.out.println(startedTag);
//                if (betonTagManager.hasTag(player, startedTag))
//                    daily.addItem(miningPassJobGenerator(player, jobs));
//            }
//        }
//
//        for (MiningPassJobs jobs : MiningPassJobs.values()) {
//            if (jobs.getType().equalsIgnoreCase("WEEKLY")) {
//                String startedTag = packageBuilder + jobs.name() + "_STARTED";
//                if (betonTagManager.hasTag(player, startedTag))
//                    weekly.addItem(miningPassJobGenerator(player, jobs));
//            }
//        }
//
//        for (MiningPassJobs jobs : MiningPassJobs.values()) {
//            if (jobs.getType().equalsIgnoreCase("BONUS1")) {
//                String startedTag = packageBuilder + jobs.name() + "_STARTED";
//                if (betonTagManager.hasTag(player, startedTag))
//                    bonus.addItem(miningPassJobGenerator(player, jobs));
//            }
//        }
//
//        for (MiningPassJobs jobs : MiningPassJobs.values()) {
//            if (jobs.getType().equalsIgnoreCase("BONUS2")) {
//                String startedTag = packageBuilder + jobs.name() + "_STARTED";
//                if (betonTagManager.hasTag(player, startedTag))
//                    bonus2.addItem(miningPassJobGenerator(player, jobs));
//            }
//        }
//
//        for (MiningPassJobs jobs : MiningPassJobs.values()) {
//            if (jobs.getType().equalsIgnoreCase("BONUS3")) {
//                String startedTag = packageBuilder + jobs.name() + "_STARTED";
//                if (betonTagManager.hasTag(player, startedTag))
//                    bonus3.addItem(miningPassJobGenerator(player, jobs));
//            }
//        }
//
//        if (betonPointsManager.getPoints(player, packageBuilder + "QUESTS_COMPLETED") >= 50)
//            bonus.addItem(jobAvailable(questGiver));
//        else
//            bonus.addItem(bonusSlotLocked(player, packageBuilder, 1));
//        if (betonPointsManager.getPoints(player, packageBuilder + "QUESTS_COMPLETED") >= 100)
//            bonus2.addItem(jobAvailable(questGiver));
//        else
//            bonus2.addItem(bonusSlotLocked(player, packageBuilder, 2));
//        if (betonPointsManager.getPoints(player, packageBuilder + "QUESTS_COMPLETED") >= 150)
//            bonus3.addItem(jobAvailable(questGiver));
//        else
//            bonus3.addItem(bonusSlotLocked(player, packageBuilder, 3));
//
//        display.addItem(new GuiItem(backButton(), e -> player.performCommand("QuestAreaMenu " + questGiver.getArea().name())), 4, height);
//
//        gui.addPane(background);
//        gui.addPane(display);
//        gui.addPane(daily);
//        gui.addPane(weekly);
//        gui.addPane(bonus);
//        gui.addPane(bonus2);
//        gui.addPane(bonus3);
//        gui.addPane(cover);
//        gui.show(player);
//    }

    private GuiItem bonusSlotLocked(Player player, String packageBuilder, int slot) {
        ItemStack item = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        final ItemMeta itemItemMeta = item.getItemMeta();

        itemItemMeta.displayName(Component.text(ChatColor.RED.toString() + ChatColor.BOLD + "[LOCKED] Job Slot"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "Complete " + ChatColor.YELLOW + "Jobs " + ChatColor.GRAY + "to unlock more " + ChatColor.GREEN + "Job Slots" + ChatColor.GRAY + "!");
        lore.add("");
        lore.add(ChatColor.YELLOW + "Completed Jobs: " + betonPointsManager.getPoints(player, packageBuilder + "QUESTS_COMPLETED") + "/" + slot * 50);

        item.setItemMeta(itemItemMeta);
        item.setLore(lore);

        return new GuiItem(item);
    }

    private GuiItem miningPassJobGenerator(Player player, MiningPassJobs quests) {
        String packageDir = "default-Town-Dan.";
        String startedTag = packageDir + quests.name() + "_STARTED";
        String completedTag = packageDir + quests.name() + "_COMPLETED";
        ItemStack item = new ItemStack(Material.PAPER);
        if (betonTagManager.hasTag(player, completedTag))
            item = new ItemStack(Material.ENCHANTED_BOOK);
        else if (betonTagManager.hasTag(player, startedTag))
            item = new ItemStack(Material.BOOK);
        final ItemMeta itemItemMeta = item.getItemMeta();

        if (betonTagManager.hasTag(player, completedTag))
            itemItemMeta.displayName(Component.text(ChatColor.GOLD.toString() + ChatColor.BOLD + "[COMPLETED] " + ChatColor.WHITE + WordUtils.capitalizeFully(quests.name().replace("_", " "))));
        else if (betonTagManager.hasTag(player, startedTag))
            itemItemMeta.displayName(Component.text(ChatColor.GREEN.toString() + ChatColor.BOLD + "[ACTIVE] " + ChatColor.WHITE + WordUtils.capitalizeFully(quests.name().replace("_", " "))));

        List<String> lore = new ArrayList<>();
        lore.add("");
        if (betonTagManager.hasTag(player, startedTag) && !betonTagManager.hasTag(player, completedTag))
            for (String questLore : quests.getQuestDescription()) {
                if (questLore.contains("%"))
                    questLore = questLore.replaceAll("(%.*?%)", PlaceholderAPI.setPlaceholders(player, "%" + StringUtils.substringBetween(questLore, "%", "%") + "%"));
                lore.add(questLore);
            }
        else if (!betonTagManager.hasTag(player, startedTag) || betonTagManager.hasTag(player, completedTag))
            for (String questLore : quests.getQuestDescription()) {
                if (questLore.contains("%"))
                    questLore = questLore.replaceAll("(%.*?%\\/)", "");
                lore.add(questLore);
            }
        lore.add("");
        lore.add(ChatColor.YELLOW.toString() + ChatColor.BOLD + "REWARDS:");
        if (quests.getRewardMoney() > 0)
            lore.add(PREFIX + ChatColor.GOLD + numberFormat.numberFormat(quests.getRewardMoney()) + " " + PrisonStatsDisplay.MONEY_AMOUNT.getName());
        if (quests.getRewardMiningPassExperience() > 0)
            lore.add(PREFIX + ChatColor.GOLD + numberFormat.numberFormat(quests.getRewardMiningPassExperience()) + " " + PrisonStatsDisplay.MINING_PASS_EXPERIENCE.getName());

        lore.add("");
        switch (quests.getType().toUpperCase()) {
            case "DAILY":
                lore.add(ChatColor.YELLOW + "Resets in " + ChatColor.GOLD + PlaceholderAPI.setPlaceholders(player, "%betonquest_default-Town-Dan:objective.DAILY_RESET.left%") + ChatColor.YELLOW + "!");
                break;
            case "WEEKLY":
                lore.add(ChatColor.YELLOW + "Resets in " + ChatColor.GOLD + PlaceholderAPI.setPlaceholders(player, "%betonquest_default-Town-Dan:objective.WEEKLY_RESET.left%") + ChatColor.YELLOW + "!");
                break;
            case "BONUS1":
                lore.add(ChatColor.YELLOW + "Resets in " + ChatColor.GOLD + PlaceholderAPI.setPlaceholders(player, "%betonquest_default-Town-Dan:objective.BONUS1_RESET.left%") + ChatColor.YELLOW + "!");
                break;
            case "BONUS2":
                lore.add(ChatColor.YELLOW + "Resets in " + ChatColor.GOLD + PlaceholderAPI.setPlaceholders(player, "%betonquest_default-Town-Dan:objective.BONUS2_RESET.left%") + ChatColor.YELLOW + "!");
                break;
            case "BONUS3":
                lore.add(ChatColor.YELLOW + "Resets in " + ChatColor.GOLD + PlaceholderAPI.setPlaceholders(player, "%betonquest_default-Town-Dan:objective.BONUS3_RESET.left%") + ChatColor.YELLOW + "!");
                break;
        }

        item.setItemMeta(itemItemMeta);
        item.setLore(lore);

        return new GuiItem(item);
    }

    public int displayXCalc(int size) {
        switch (size) {
            case 1:
                return 4;
            case 2:
            case 3:
                return 3;
            case 4:
            case 5:
                return 2;
            case 6:
            case 7:
                return 1;
        }
        return 1;
    }

    public int heightCalc(int size) {
        switch (size) {
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
                return 5;
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
                return 6;
        }
        return 4;
    }

}
