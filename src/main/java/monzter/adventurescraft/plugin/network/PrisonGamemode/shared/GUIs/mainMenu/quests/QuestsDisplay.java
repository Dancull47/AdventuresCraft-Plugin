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

        String packageBuilder = "default-" + WordUtils.capitalizeFully(questGiver.getArea().getName()) + "-" + WordUtils.capitalizeFully(questGiver.getName() + ".");

        int questsCompleted = 0;
        for (Quests quest : Quests.values())
            if (quest.getQuestGiver() == questGiver)
                if (betonTagManager.hasTag(player, packageBuilder + quest.name() + "_COMPLETED"))
                    questsCompleted++;

        ChestGui gui = new ChestGui(height + 1, guiHelper.guiName(questGiver.getName() + " Quests " + questsCompleted + "/" + questAmount));
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        OutlinePane background = new OutlinePane(0, 0, 9, height + 1, Pane.Priority.LOWEST);
        StaticPane display = new StaticPane(0, 0, 9, height + 1, Pane.Priority.LOW);
        OutlinePane main = new OutlinePane(startX, startY, length, height - 2, Pane.Priority.LOW);

        background.addItem(new GuiItem(guiHelper.background(backgroundColor)));
        background.setRepeat(true);

        for (Quests quest : Quests.values())
            if (quest.getQuestGiver() == questGiver)
                main.addItem(itemGenerator(player, quest));

        display.addItem(new GuiItem(guiHelper.backButton(), e -> player.performCommand("QuestAreaMenu " + questGiver.getArea().getName())), 4, height);

        gui.addPane(background);
        gui.addPane(display);
        gui.addPane(main);
        gui.show(player);
    }


    private GuiItem itemGenerator(Player player, Quests quests) {
        String startedTag = quests.name() + "_STARTED";
        String completedTag = quests.name() + "_COMPLETED";
        String claimedTag = quests.name() + "_CLAIMED";
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
            lore.add(PREFIX + ChatColor.GOLD + quests.getRewardMoney() + " " + PrisonStatsDisplay.EXPERIENCE_AMOUNT.getName());
        if (quests.getRewardPetExperience() > 0)
            lore.add(PREFIX + ChatColor.GOLD + quests.getRewardMoney() + " " + PrisonStatsDisplay.PET_EXPERIENCE_AMOUNT.getName());

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
                        wizard(player);
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
                    wizard(player);
                }
            }
        });
    }

    private String parsePlaceholder(Player player, String string) {
        return PlaceholderAPI.setPlaceholders(player, "%" + string + "%");
    }
}

