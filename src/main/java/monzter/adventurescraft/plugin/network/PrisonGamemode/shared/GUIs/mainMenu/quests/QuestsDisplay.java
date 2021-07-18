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
import monzter.adventurescraft.plugin.utilities.enums.Prefix;
import monzter.adventurescraft.plugin.utilities.general.ConsoleCommand;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
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
    final String PREFIX = ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + Prefix.PREFIX.getString() + " ";


    public QuestsDisplay(AdventuresCraft plugin, SoundManager soundManager, GUIHelper guiHelper, ConsoleCommand consoleCommand, MMOItems mmoItems) {
        this.plugin = plugin;
        this.soundManager = soundManager;
        this.guiHelper = guiHelper;
        this.consoleCommand = consoleCommand;
        this.mmoItems = mmoItems;
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

        ChestGui gui = new ChestGui(height + 1, guiHelper.guiName(questGiver.getName() + " Quests " + questAmount));
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
        String startedPermission = quests.name() + ".STARTED";
        String completedPermission = quests.name() + ".COMPLETED";
        ItemStack item = new ItemStack(Material.PAPER);
        if (player.hasPermission(completedPermission))
            item = new ItemStack(Material.BOOK);
        else if (player.hasPermission(startedPermission))
            item = new ItemStack(Material.ENCHANTED_BOOK);
        final ItemMeta itemItemMeta = item.getItemMeta();

        itemItemMeta.displayName(Component.text(ChatColor.RED.toString() + ChatColor.BOLD + "[INACTIVE] " + ChatColor.WHITE + WordUtils.capitalizeFully(quests.name().replace("_", " "))));
        if (player.hasPermission(completedPermission))
            itemItemMeta.displayName(Component.text(ChatColor.GREEN.toString() + ChatColor.BOLD + "[ACTIVE] " + ChatColor.WHITE + WordUtils.capitalizeFully(quests.name().replace("_", " "))));
        else if (player.hasPermission(startedPermission))
            itemItemMeta.displayName(Component.text(ChatColor.GOLD.toString() + ChatColor.BOLD + "[COMPLETED] " + ChatColor.WHITE + WordUtils.capitalizeFully(quests.name().replace("_", " "))));

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
        if (quests.getRewardEXP() > 0)
            lore.add(PREFIX + ChatColor.DARK_PURPLE + quests.getRewardEXP() + " EXP");
        if (quests.getRewardProfessionEXP() != null)
            for (String questProfessionEXPReward : quests.getRewardProfessionEXP()) {
                String[] professionReward = questProfessionEXPReward.split(" ");
                lore.add(PREFIX + ChatColor.GOLD + professionReward[2] + ChatColor.DARK_GRAY + "x " + mmoItems.getItem(professionReward[0], professionReward[1]).getItemMeta().getDisplayName());
            }
//        Add Professions
        if (quests.getRewardMoney() > 0)
            lore.add(PREFIX + ChatColor.YELLOW + quests.getRewardMoney() + " Coins");

        item.setItemMeta(itemItemMeta);
        item.setLore(lore);

        return new GuiItem(item);
    }

    private String parsePlaceholder(Player player, String string) {
        return PlaceholderAPI.setPlaceholders(player, "%" + string + "%");
    }
}

