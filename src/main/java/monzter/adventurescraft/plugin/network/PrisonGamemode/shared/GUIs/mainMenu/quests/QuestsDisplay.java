package monzter.adventurescraft.plugin.network.PrisonGamemode.shared.GUIs.mainMenu.quests;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Dependency;
import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import me.clip.placeholderapi.PlaceholderAPI;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.GUI.GUIHelper;
import monzter.adventurescraft.plugin.utilities.beton.BetonPointsManager;
import monzter.adventurescraft.plugin.utilities.beton.BetonTagManager;
import monzter.adventurescraft.plugin.utilities.enums.Prefix;
import monzter.adventurescraft.plugin.utilities.enums.PrisonStatsDisplay;
import monzter.adventurescraft.plugin.utilities.general.ConsoleCommand;
import monzter.adventurescraft.plugin.utilities.general.FullInventory;
import monzter.adventurescraft.plugin.utilities.general.ItemAdder;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import monzter.adventurescraft.plugin.utilities.vault.Economy;
import net.Indyuce.mmoitems.MMOItems;
import net.kyori.adventure.text.Component;
import org.apache.commons.lang.WordUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

@CommandAlias("QuestViewer")
public class QuestsDisplay extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final SoundManager soundManager;
    private final GUIHelper guiHelper;
    private final ConsoleCommand consoleCommand;
    private final MMOItems mmoItems;
    private final BetonTagManager betonTagManager;
    private final FullInventory fullInventory;
    private final ItemAdder itemAdder;
    private final BetonPointsManager betonPointsManager;
    private final Economy economy;


    final String PREFIX = ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + Prefix.PREFIX.getString() + " ";


    public QuestsDisplay(AdventuresCraft plugin, SoundManager soundManager, GUIHelper guiHelper, ConsoleCommand consoleCommand, MMOItems mmoItems, BetonTagManager betonTagManager, FullInventory fullInventory, ItemAdder itemAdder, BetonPointsManager betonPointsManager, Economy economy) {
        this.plugin = plugin;
        this.soundManager = soundManager;
        this.guiHelper = guiHelper;
        this.consoleCommand = consoleCommand;
        this.mmoItems = mmoItems;
        this.betonTagManager = betonTagManager;
        this.fullInventory = fullInventory;
        this.itemAdder = itemAdder;
        this.betonPointsManager = betonPointsManager;
        this.economy = economy;
    }

    @CommandAlias("Wizard")
    public void wizard(Player player) {
        menuGenerator(player, QuestGiver.WIZARD, Material.PURPLE_STAINED_GLASS_PANE);
    }

    @CommandAlias("Joy")
    public void joy(Player player) {
        menuGenerator(player, QuestGiver.JOY, Material.PINK_STAINED_GLASS_PANE);
    }

    @CommandAlias("Merle")
    public void merle(Player player) {
        jobMenuGenerator(player, QuestGiver.MERLE, Material.PURPLE_STAINED_GLASS_PANE);
    }

    public void menuGenerator(Player player, QuestGiver questGiver, Material backgroundColor) {
        int questAmount = 0;
        int startX = 2;
        int startY = 1;
        int length = 5;
        int height = 3;

        for (Quests quest : Quests.values())
            if (quest.getQuestGiver() == questGiver)
                questAmount += 1;

        if (questAmount == 1)
            startX = 4;
        else if (questAmount == 2 || questAmount == 3)
            startX = 3;
        else if (questAmount == 4)
            startX = 2;

        String packageBuilder = "default-" + WordUtils.capitalizeFully(questGiver.getArea().name()) + "-" + WordUtils.capitalizeFully(questGiver.name() + ".");

        int questsCompleted = 0;
        for (Quests quest : Quests.values())
            if (quest.getQuestGiver() == questGiver)
                if (betonTagManager.hasTag(player, packageBuilder + quest.name() + "_COMPLETED"))
                    questsCompleted++;

        ChestGui gui = new ChestGui(height + 1, guiHelper.guiName(WordUtils.capitalizeFully(questGiver.name()) + " Quests " + questsCompleted + "/" + questAmount));
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        OutlinePane background = new OutlinePane(0, 0, 9, height + 1, Pane.Priority.LOWEST);
        StaticPane display = new StaticPane(0, 0, 9, height + 1, Pane.Priority.LOW);
        OutlinePane main = new OutlinePane(startX, startY, length, height - 2, Pane.Priority.LOW);

        background.addItem(new GuiItem(guiHelper.background(backgroundColor)));
        background.setRepeat(true);

        for (Quests quest : Quests.values())
            if (quest.getQuestGiver() == questGiver)
                main.addItem(itemGenerator(player, quest));

        display.addItem(new GuiItem(guiHelper.backButton(), e -> player.performCommand("QuestAreaMenu " + questGiver.getArea().name())), 4, height);

        gui.addPane(background);
        gui.addPane(display);
        gui.addPane(main);
        gui.show(player);
    }


    private GuiItem itemGenerator(Player player, Quests quests) {
        String packageDir = "default-" + WordUtils.capitalizeFully(quests.getQuestGiver().getArea().name()) + "-" + WordUtils.capitalizeFully(quests.getQuestGiver().name()) + ".";
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
        for (String questLore : quests.getQuestDescription())
            lore.add(questLore);
        lore.add("");
        lore.add(ChatColor.YELLOW.toString() + ChatColor.BOLD + "REWARDS:");
        if (quests.getRewardItems() != null)
            for (String questItemReward : quests.getRewardItems()) {
                String[] reward = questItemReward.split(" ");
                lore.add(PREFIX + ChatColor.GOLD + reward[2] + ChatColor.DARK_GRAY + "x " + mmoItems.getItem(reward[0], reward[1]).getItemMeta().getDisplayName());
            }
//        if (quests.getRewardEXP() > 0) We don't use normal EXP in
//            lore.add(PREFIX + ChatColor.DARK_PURPLE + quests.getRewardEXP() + " EXP");
        if (quests.getRewardProfessionEXP() != null)
            for (String questProfessionEXPReward : quests.getRewardProfessionEXP()) {
                String[] professionReward = questProfessionEXPReward.split(" ");
                lore.add(PREFIX + ChatColor.BLUE + professionReward[1] + " " + WordUtils.capitalizeFully(professionReward[0]) + " EXP");
            }
        if (quests.getRewardMoney() > 0)
            lore.add(PREFIX + ChatColor.GOLD + quests.getRewardMoney() + " " + PrisonStatsDisplay.MONEY_AMOUNT.getName());
        if (quests.getRewardExperience() > 0)
            lore.add(PREFIX + ChatColor.GOLD + quests.getRewardExperience() + " " + PrisonStatsDisplay.EXPERIENCE_AMOUNT.getName());
        if (quests.getRewardPetExperience() > 0)
            lore.add(PREFIX + ChatColor.GOLD + quests.getRewardPetExperience() + " " + PrisonStatsDisplay.PET_EXPERIENCE_AMOUNT.getName());

        if (betonTagManager.hasTag(player, completedTag) && !betonTagManager.hasTag(player, claimedTag)) {
            lore.add("");
            lore.add(PREFIX + ChatColor.YELLOW + "Click to Claim Reward");
        }

        item.setItemMeta(itemItemMeta);
        item.setLore(lore);

        return new GuiItem(item, e -> {
            if (betonTagManager.hasTag(player, completedTag) && !betonTagManager.hasTag(player, claimedTag)) {
                if (quests.getRewardItems() != null) {
                    if (!fullInventory.fullInventory(player)) {
                        for (String questItemReward : quests.getRewardItems()) {
                            String[] reward = questItemReward.split(" ");
                            itemAdder.itemAdder(player, MMOItems.plugin.getItem(reward[0], reward[1]).asQuantity(Integer.valueOf(reward[2])));
                        }
                        if (quests.getRewardProfessionEXP() != null)
                            for (String questProfessionEXPReward : quests.getRewardProfessionEXP()) {
                                String[] professionReward = questProfessionEXPReward.split(" ");
                                consoleCommand.consoleCommand("mmocore admin exp give " + player.getName() + " " + professionReward[0] + " " + professionReward[1]);
                            }
                        if (quests.getRewardMoney() > 0)
                            economy.giveMoney(player, quests.getRewardMoney());
                        if (quests.getRewardExperience() > 0)
                            betonPointsManager.givePointEXP(player, quests.getRewardExperience());
                        if (quests.getRewardPetExperience() > 0)
                            betonPointsManager.givePointEXP(player, quests.getRewardPetExperience());
                        betonTagManager.giveTag(player, claimedTag);
                        player.performCommand("questViewer " + quests.getQuestGiver().name());
                    }
                } else {
                    if (quests.getRewardProfessionEXP() != null)
                        for (String questProfessionEXPReward : quests.getRewardProfessionEXP()) {
                            String[] professionReward = questProfessionEXPReward.split(" ");
                            consoleCommand.consoleCommand("mmocore admin exp give " + player.getName() + " " + professionReward[0] + " " + professionReward[1]);
                        }
                    if (quests.getRewardMoney() > 0)
                        economy.giveMoney(player, quests.getRewardMoney());
                    if (quests.getRewardExperience() > 0)
                        betonPointsManager.givePointEXP(player, quests.getRewardExperience());
                    if (quests.getRewardPetExperience() > 0)
                        betonPointsManager.givePointPetEXP(player, quests.getRewardPetExperience());
                    betonTagManager.giveTag(player, claimedTag);
                    player.performCommand("questViewer " + quests.getQuestGiver().name());
                }
            }
        });
    }

    public void jobMenuGenerator(Player player, QuestGiver questGiver, Material backgroundColor) {
        int startX = 2;
        int startY = 1;
        int length = 5;
        int height = 3;

        String packageBuilder = "default-" + WordUtils.capitalizeFully(questGiver.getArea().name()) + "-" + WordUtils.capitalizeFully(questGiver.name() + ".");

        ChestGui gui = new ChestGui(height + 1, guiHelper.guiName(WordUtils.capitalizeFully(questGiver.name()) + " Jobs " + betonPointsManager.getPoints(player, packageBuilder + "QUESTS_COMPLETED")));
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        OutlinePane background = new OutlinePane(0, 0, 9, height + 1, Pane.Priority.LOWEST);
        StaticPane display = new StaticPane(0, 0, 9, height + 1, Pane.Priority.LOW);
        OutlinePane main = new OutlinePane(startX, startY, length, height - 2, Pane.Priority.LOW);
        OutlinePane main2 = new OutlinePane(startX, startY, length, height - 2, Pane.Priority.LOW);

        background.addItem(new GuiItem(guiHelper.background(backgroundColor)));
        background.setRepeat(true);

        main2.addItem(available(questGiver));
        main2.addItem(available(questGiver));
        main2.addItem(available(questGiver));
        if (betonPointsManager.getPoints(player, packageBuilder + "QUESTS_COMPLETED") >= 250)
            main2.addItem(available(questGiver));
        else
            main2.addItem(locked(player, packageBuilder, 1));
        if (betonPointsManager.getPoints(player, packageBuilder + "QUESTS_COMPLETED") >= 500)
            main2.addItem(available(questGiver));
        else
            main2.addItem(locked(player, packageBuilder, 2));

        for (Jobs jobs : Jobs.values())
            if (jobs.getQuestGiver() == questGiver) {
                String startedTag = packageBuilder + jobs.name() + "_STARTED";
//                plugin.getLogger().info(startedTag);
                if (betonTagManager.hasTag(player, startedTag))
                    main.addItem(jobItemGenerator(player, jobs));
            }

        display.addItem(new GuiItem(guiHelper.backButton(), e -> player.performCommand("QuestAreaMenu " + questGiver.getArea().name())), 4, height);

        gui.addPane(background);
        gui.addPane(display);
        gui.addPane(main);
        gui.addPane(main2);
        gui.show(player);
    }


    private GuiItem jobItemGenerator(Player player, Jobs jobs) {
        String packageDir = "default-" + WordUtils.capitalizeFully(jobs.getQuestGiver().getArea().name()) + "-" + WordUtils.capitalizeFully(jobs.getQuestGiver().name()) + ".";
        String startedTag = packageDir + jobs.name() + "_STARTED";
        ItemStack item = new ItemStack(Material.BOOK);
        final ItemMeta itemItemMeta = item.getItemMeta();

        itemItemMeta.displayName(Component.text(ChatColor.RED.toString() + ChatColor.BOLD + "[INACTIVE] " + ChatColor.WHITE + WordUtils.capitalizeFully(jobs.name().replace("_", " "))));
        if (betonTagManager.hasTag(player, startedTag))
            itemItemMeta.displayName(Component.text(ChatColor.GREEN.toString() + ChatColor.BOLD + "[ACTIVE] " + ChatColor.WHITE + WordUtils.capitalizeFully(jobs.name().replace("_", " "))));

        List<String> lore = new ArrayList<>();
        lore.add("");
        for (String questLore : jobs.getQuestDescription())
            lore.add(questLore);
        lore.add("");
        lore.add(ChatColor.YELLOW.toString() + ChatColor.BOLD + "REWARDS:");
        if (jobs.getRewardItems() != null)
            for (String questItemReward : jobs.getRewardItems()) {
                String[] reward = questItemReward.split(" ");
                lore.add(PREFIX + ChatColor.GOLD + reward[2] + ChatColor.DARK_GRAY + "x " + mmoItems.getItem(reward[0], reward[1]).getItemMeta().getDisplayName());
            }
        if (jobs.getRewardProfessionEXP() != null)
            for (String questProfessionEXPReward : jobs.getRewardProfessionEXP()) {
                String[] professionReward = questProfessionEXPReward.split(" ");
                lore.add(PREFIX + ChatColor.BLUE + professionReward[1] + " " + WordUtils.capitalizeFully(professionReward[0]) + " EXP");
            }
        if (jobs.getRewardMoney() > 0)
            lore.add(PREFIX + ChatColor.GOLD + jobs.getRewardMoney() + " " + PrisonStatsDisplay.MONEY_AMOUNT.getName());
        if (jobs.getRewardExperience() > 0)
            lore.add(PREFIX + ChatColor.GOLD + jobs.getRewardExperience() + " " + PrisonStatsDisplay.EXPERIENCE_AMOUNT.getName());
        if (jobs.getRewardPetExperience() > 0)
            lore.add(PREFIX + ChatColor.GOLD + jobs.getRewardPetExperience() + " " + PrisonStatsDisplay.PET_EXPERIENCE_AMOUNT.getName());

        item.setItemMeta(itemItemMeta);
        item.setLore(lore);

        return new GuiItem(item);
    }

    private GuiItem available(QuestGiver questGiver) {
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

    private GuiItem locked(Player player, String packageBuilder, int slot) {
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

    private String parsePlaceholder(Player player, String string) {
        return PlaceholderAPI.setPlaceholders(player, "%" + string + "%");
    }
}

