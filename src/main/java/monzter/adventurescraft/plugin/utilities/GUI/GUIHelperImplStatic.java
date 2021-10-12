package monzter.adventurescraft.plugin.utilities.GUI;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import dev.dbassett.skullcreator.SkullCreator;
import me.clip.placeholderapi.PlaceholderAPI;
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
import java.util.UUID;

public class GUIHelperImplStatic {
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

    public GUIHelperImplStatic(NumberFormat numberFormat, BetonTagManager betonTagManager, BetonPointsManager betonPointsManager, FullInventory fullInventory, ItemAdder itemAdder, MMOItems mmoItems, ConsoleCommand consoleCommand, Economy economy, SoundManager soundManager) {
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


    public static ItemStack background() {
        final ItemStack backgroundItem = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        final ItemMeta backgroundItemMeta = backgroundItem.getItemMeta();

        backgroundItemMeta.displayName(Component.text(" "));
        backgroundItem.setItemMeta(backgroundItemMeta);
        return backgroundItem;
    }


    public static ItemStack background(Material material) {
        final ItemStack backgroundItem = new ItemStack(material);
        final ItemMeta backgroundItemMeta = backgroundItem.getItemMeta();

        backgroundItemMeta.displayName(Component.text(" "));
        backgroundItem.setItemMeta(backgroundItemMeta);
        return backgroundItem;
    }

//    Buttons


    public static ItemStack backButton() {
        final ItemStack backButton = new ItemStack(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODY1MmUyYjkzNmNhODAyNmJkMjg2NTFkN2M5ZjI4MTlkMmU5MjM2OTc3MzRkMThkZmRiMTM1NTBmOGZkYWQ1ZiJ9fX0="));
        final ItemMeta backButtonItemMeta = backButton.getItemMeta();

        backButtonItemMeta.displayName(Component.text(ChatColor.GREEN + "Go Back"));
        backButton.setItemMeta(backButtonItemMeta);
        return backButton;
    }


    public static ItemStack nextPageButton() {
        final ItemStack nextPageItem = new ItemStack(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmEzYjhmNjgxZGFhZDhiZjQzNmNhZThkYTNmZTgxMzFmNjJhMTYyYWI4MWFmNjM5YzNlMDY0NGFhNmFiYWMyZiJ9fX0="));
        final ItemMeta nextPageItemMeta = nextPageItem.getItemMeta();

        nextPageItemMeta.displayName(Component.text(ChatColor.GREEN + "Next Page"));
        nextPageItem.setItemMeta(nextPageItemMeta);
        return nextPageItem;
    }


    public static ItemStack previousPageButton() {
        final ItemStack previousPageItem = new ItemStack(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODY1MmUyYjkzNmNhODAyNmJkMjg2NTFkN2M5ZjI4MTlkMmU5MjM2OTc3MzRkMThkZmRiMTM1NTBmOGZkYWQ1ZiJ9fX0="));
        final ItemMeta previousPageItemMeta = previousPageItem.getItemMeta();

        previousPageItemMeta.displayName(Component.text(ChatColor.GREEN + "Previous Page"));
        previousPageItem.setItemMeta(previousPageItemMeta);
        return previousPageItem;
    }


    public static ItemStack firstPageButton() {
        final ItemStack firstPageItem = new ItemStack(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODE2ZWEzNGE2YTZlYzVjMDUxZTY5MzJmMWM0NzFiNzAxMmIyOThkMzhkMTc5ZjFiNDg3YzQxM2Y1MTk1OWNkNCJ9fX0="));
        final ItemMeta firstPageItemMeta = firstPageItem.getItemMeta();

        firstPageItemMeta.displayName(Component.text(ChatColor.GREEN + "First Page"));
        firstPageItem.setItemMeta(firstPageItemMeta);
        return firstPageItem;
    }


    public static ItemStack lastPageButton() {
        final ItemStack lastPageItem = new ItemStack(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWM5ZWM3MWMxMDY4ZWM2ZTAzZDJjOTI4N2Y5ZGE5MTkzNjM5ZjNhNjM1ZTJmYmQ1ZDg3YzJmYWJlNjQ5OSJ9fX0="));
        final ItemMeta lastPageItemMeta = lastPageItem.getItemMeta();

        lastPageItemMeta.displayName(Component.text(ChatColor.GREEN + "Last Page"));
        lastPageItem.setItemMeta(lastPageItemMeta);
        return lastPageItem;
    }

//    Name


    public static String guiName(String name) {
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

    public static ItemStack itemCreator(Material material, String name, String[] lore) {
        ItemStack complete = new ItemStack(material);
        return itemCreator(complete, name, lore, false, 0);
    }


    public static ItemStack itemCreator(Material material, String name, String[] lore, boolean enchanted, int customModelData) {
        ItemStack complete = new ItemStack(material);
        return itemCreator(complete, name, lore, enchanted, customModelData);
    }

    public static ItemStack itemCreator(Material material, String name, String[] lore, boolean enchanted) {
        ItemStack complete = new ItemStack(material);
        return itemCreator(complete, name, lore, enchanted, 0);
    }


    public static ItemStack itemCreator(String skullTexture, String name, String[] lore) {
        ItemStack complete = SkullCreator.itemFromBase64(skullTexture);
        return itemCreator(complete, name, lore, false, 0);
    }

    public static ItemStack itemCreator(UUID skullTexture, String name, String[] lore, int customModelData) {
        ItemStack complete = SkullCreator.itemFromUuid(skullTexture);
        return itemCreator(complete, name, lore, false, customModelData);
    }


    public static ItemStack itemCreator(String skullTexture, String name, String[] lore, boolean enchanted) {
        ItemStack complete = SkullCreator.itemFromBase64(skullTexture);
        return itemCreator(complete, name, lore, enchanted, 0);
    }

    public static ItemStack itemCreator(UUID skullTexture, String name, String[] lore, boolean enchanted, int customModelData) {
        ItemStack complete = SkullCreator.itemFromUuid(skullTexture);
        return itemCreator(complete, name, lore, enchanted, customModelData);
    }


    public static ItemStack itemCreator(ItemStack itemStack, String name, String[] lore, boolean enchanted, int customModelData) {
        ItemStack complete = itemStack;
        final ItemMeta completeItemMeta = complete.getItemMeta();
        completeItemMeta.setCustomModelData(customModelData);
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

    /*
     *   Displays Quests for Player
     * */

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

        item.setItemMeta(itemItemMeta);
        item.setLore(lore);

        return new GuiItem(item);
    }

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


    public static int displayXCalc(int size) {
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
            default:
                return 1;
        }
    }

    public static int heightCalc(int size) {
        switch (size) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
                return 4;
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
            default:
                return 6;
        }
    }

}
