package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.mainMenu.quests.jobs.yardJobs;

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
import monzter.adventurescraft.plugin.utilities.text.NumberFormat;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.betoncraft.betonquest.BetonQuest;

import java.util.ArrayList;
import java.util.List;

public class Lester extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final GUIHelper guiHelper;
    private final BetonTagManager betonTagManager;
    private final BetonPointsManager betonPointsManager;
    private final NumberFormat numberFormat;



    public Lester(AdventuresCraft plugin, GUIHelper guiHelper, BetonTagManager betonTagManager, BetonPointsManager betonPointsManager, NumberFormat numberFormat) {
        this.plugin = plugin;
        this.guiHelper = guiHelper;
        this.betonTagManager = betonTagManager;
        this.betonPointsManager = betonPointsManager;
        this.numberFormat = numberFormat;
    }

    @CommandAlias("jobsLester")
    public void questMenu(Player player) {
        ChestGui gui = new ChestGui(4, guiHelper.guiName("Lester's Jobs"));
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        OutlinePane background = new OutlinePane(0, 0, 9, 4, Pane.Priority.LOWEST);
        StaticPane display = new StaticPane(3, 1, 3, 1, Pane.Priority.LOW);
        OutlinePane backButton = new OutlinePane(4, 3, 1, 1, Pane.Priority.LOW);

        backButton.addItem(new GuiItem(guiHelper.backButton(), e -> player.performCommand("yardJobs")));

        background.addItem(new GuiItem(guiHelper.background(Material.GREEN_STAINED_GLASS_PANE)));
        background.setRepeat(true);

        if (!betonTagManager.hasTag(player, "default-Jobs-Lester.q1_part1") && !betonTagManager.hasTag(player, "default-Jobs-Lester.q2_part1") && !betonTagManager.hasTag(player, "default-Jobs-Lester.q3_part1"))
            display.addItem(available(), 0, 0);
        else if (betonTagManager.hasTag(player, "default-Jobs-Lester.q1_part1"))
            display.addItem(job1(player, "Smashing Rocks", LesterJobs.Lester1), 0, 0);
        else if (betonTagManager.hasTag(player, "default-Jobs-Lester.q2_part1"))
            display.addItem(job2(player, "Smashing Bigger Rocks", LesterJobs.Lester2), 0, 0);
        else if (betonTagManager.hasTag(player, "default-Jobs-Lester.q3_part1"))
            display.addItem(job3(player, "Smashing HUGE Rocks", LesterJobs.Lester3), 0, 0);

        if (betonPointsManager.getPoints("default-Jobs-Lester.Lester", BetonQuest.getInstance().getPlayerData(player.getUniqueId().toString()).getPoints()) >= 250 && !betonTagManager.hasTag(player, "default-Jobs-Lester.q4_part1") &&
                !betonTagManager.hasTag(player, "default-Jobs-Lester.q5_part1") && !betonTagManager.hasTag(player, "default-Jobs-Lester.q6_part1"))
            display.addItem(available(), 1, 0);
        else if (!betonTagManager.hasTag(player, "default-Jobs-Lester.Lest250") && !betonTagManager.hasTag(player, "default-Jobs-Lester.q4_part1") &&
                !betonTagManager.hasTag(player, "default-Jobs-Lester.q5_part1") && !betonTagManager.hasTag(player, "default-Jobs-Lester.q6_part1"))
            display.addItem(locked(player), 1, 0);
        else if (betonTagManager.hasTag(player, "default-Jobs-Lester.q4_part1"))
            display.addItem(job4(player, "Smashing Rocks", LesterJobs.Lester4), 1, 0);
        else if (betonTagManager.hasTag(player, "default-Jobs-Lester.q5_part1"))
            display.addItem(job5(player, "Smashing Rocks", LesterJobs.Lester5), 1, 0);
        else if (betonTagManager.hasTag(player, "default-Jobs-Lester.q6_part1"))
            display.addItem(job6(player, "Smashing Rocks", LesterJobs.Lester6), 1, 0);

        if (betonPointsManager.getPoints("default-Jobs-Lester.Lester", BetonQuest.getInstance().getPlayerData(player.getUniqueId().toString()).getPoints()) >= 500 && !betonTagManager.hasTag(player, "default-Jobs-Lester.q7_part1") &&
                !betonTagManager.hasTag(player, "default-Jobs-Lester.q8_part1") && !betonTagManager.hasTag(player, "default-Jobs-Lester.q9_part1"))
            display.addItem(available(), 2, 0);
        else if (!betonTagManager.hasTag(player, "default-Jobs-Lester.Lest500") && !betonTagManager.hasTag(player, "default-Jobs-Lester.q7_part1") &&
                !betonTagManager.hasTag(player, "default-Jobs-Lester.q8_part1") && !betonTagManager.hasTag(player, "default-Jobs-Lester.q9_part1"))
            display.addItem(locked2(player), 2, 0);
        else if (betonTagManager.hasTag(player, "default-Jobs-Lester.q7_part1"))
            display.addItem(job7(player, "Smashing Bigger Rocks", LesterJobs.Lester7), 2, 0);
        else if (betonTagManager.hasTag(player, "default-Jobs-Lester.q8_part1"))
            display.addItem(job8(player, "Smashing Bigger Rocks", LesterJobs.Lester8), 2, 0);
        else if (betonTagManager.hasTag(player, "default-Jobs-Lester.q9_part1"))
            display.addItem(job9(player, "Smashing Bigger Rocks", LesterJobs.Lester9), 2, 0);

        display.addItem(new GuiItem(guiHelper.backButton(), e -> player.performCommand("YardJobs")), 4, 3);

        gui.addPane(background);
        gui.addPane(display);
        gui.addPane(backButton);
        gui.show(player);
    }



    private GuiItem available() {
        ItemStack itemStack = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName(ChatColor.GREEN + "Available Job Slot");

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "Speak with " + ChatColor.YELLOW + "Lester " + ChatColor.GRAY + "in the");
        lore.add(ChatColor.GREEN + "Yard" + ChatColor.GRAY + ", to receive a " + ChatColor.DARK_RED + "Job" + ChatColor.GRAY + "!");

        itemStack.setItemMeta(itemMeta);
        itemStack.setLore(lore);
        return new GuiItem(itemStack);
    }
    private GuiItem locked(Player player) {
        ItemStack itemStack = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName(ChatColor.RED.toString() + ChatColor.BOLD + "Locked Job Slot");

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "Complete " + ChatColor.YELLOW + "250 Jobs for Lester " + ChatColor.GRAY + "to");
        lore.add(ChatColor.GRAY + "take more than one Job at a time!");
        lore.add("");
        lore.add(ChatColor.GRAY + "You've completed " + ChatColor.YELLOW + parsePlaceholder(player, "betonquest_default-Jobs-Lester:point.Lester.amount") + " Job" + ChatColor.GRAY + "!");

        itemStack.setItemMeta(itemMeta);
        itemStack.setLore(lore);
        return new GuiItem(itemStack);
    }
    private GuiItem locked2(Player player) {
        ItemStack itemStack = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName(ChatColor.RED.toString() + ChatColor.BOLD + "Locked Job Slot");

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "Complete " + ChatColor.YELLOW + "500 Jobs for Lester " + ChatColor.GRAY + "to");
        lore.add(ChatColor.GRAY + "take more than one Job at a time!");
        lore.add("");
        lore.add(ChatColor.GRAY + "You've completed " + ChatColor.YELLOW + parsePlaceholder(player, "betonquest_default-Jobs-Lester:point.Lester.amount") + " Job" + ChatColor.GRAY + "!");

        itemStack.setItemMeta(itemMeta);
        itemStack.setLore(lore);
        return new GuiItem(itemStack);
    }

    private GuiItem job1(Player player, String jobName, LesterJobs lesterJobs) {
        String blocks = parsePlaceholder(player, "betonquest_default-Jobs-Lester:point.Blocks.amount");
        String lore = ChatColor.GREEN + numberFormat.numberFormat(Integer.valueOf(blocks)) + ChatColor.GRAY + "/" + ChatColor.GREEN + numberFormat.numberFormat(LesterJobs.Lester1.getMax()) + ChatColor.WHITE;
        return new GuiItem(guiHelper.jobActive(jobName, lesterJobs.getObjective().replace("-", lore), lesterJobs.getRewards(), lesterJobs.getRewardAmount(), lesterJobs.getCurrencyRewards(), lesterJobs.getCurrencyRewardsAmount(), "Lester", "Yard"));
    }
    private GuiItem job2(Player player, String jobName, LesterJobs lesterJobs) {
        String blocks = parsePlaceholder(player, "betonquest_default-Jobs-Lester:point.Blocks.amount");
        String lore = ChatColor.GREEN + numberFormat.numberFormat(Integer.valueOf(blocks)) + ChatColor.GRAY + "/" + ChatColor.GREEN +  numberFormat.numberFormat(LesterJobs.Lester2.getMax()) + ChatColor.WHITE;
        return new GuiItem(guiHelper.jobActive(jobName, lesterJobs.getObjective().replace("-", lore), lesterJobs.getRewards(), lesterJobs.getRewardAmount(), lesterJobs.getCurrencyRewards(), lesterJobs.getCurrencyRewardsAmount(), "Lester", "Yard"));
    }
    private GuiItem job3(Player player, String jobName, LesterJobs lesterJobs) {
        String blocks = parsePlaceholder(player, "betonquest_default-Jobs-Lester:point.Blocks.amount");
        String lore = ChatColor.GREEN + numberFormat.numberFormat(Integer.valueOf(blocks)) + ChatColor.GRAY + "/" + ChatColor.GREEN +  numberFormat.numberFormat(LesterJobs.Lester3.getMax()) + ChatColor.WHITE;
        return new GuiItem(guiHelper.jobActive(jobName, lesterJobs.getObjective().replace("-", lore), lesterJobs.getRewards(), lesterJobs.getRewardAmount(), lesterJobs.getCurrencyRewards(), lesterJobs.getCurrencyRewardsAmount(), "Lester", "Yard"));
    }
    private GuiItem job4(Player player, String jobName, LesterJobs lesterJobs) {
        String blocks = parsePlaceholder(player, "betonquest_default-Jobs-Lester:point.Blocks2.amount");
        String lore = ChatColor.GREEN + numberFormat.numberFormat(Integer.valueOf(blocks)) + ChatColor.GRAY + "/" + ChatColor.GREEN +  numberFormat.numberFormat(LesterJobs.Lester4.getMax()) + ChatColor.WHITE;
        return new GuiItem(guiHelper.jobActive(jobName, lesterJobs.getObjective().replace("-", lore), lesterJobs.getRewards(), lesterJobs.getRewardAmount(), lesterJobs.getCurrencyRewards(), lesterJobs.getCurrencyRewardsAmount(), "Lester", "Yard"));
    }
    private GuiItem job5(Player player, String jobName, LesterJobs lesterJobs) {
        String blocks = parsePlaceholder(player, "betonquest_default-Jobs-Lester:point.Blocks2.amount");
        String lore = ChatColor.GREEN + numberFormat.numberFormat(Integer.valueOf(blocks)) + ChatColor.GRAY + "/" + ChatColor.GREEN +  numberFormat.numberFormat(LesterJobs.Lester5.getMax()) + ChatColor.WHITE;
        return new GuiItem(guiHelper.jobActive(jobName, lesterJobs.getObjective().replace("-", lore), lesterJobs.getRewards(), lesterJobs.getRewardAmount(), lesterJobs.getCurrencyRewards(), lesterJobs.getCurrencyRewardsAmount(), "Lester", "Yard"));
    }
    private GuiItem job6(Player player, String jobName, LesterJobs lesterJobs) {
        String blocks = parsePlaceholder(player, "betonquest_default-Jobs-Lester:point.Blocks2.amount");
        String lore = ChatColor.GREEN + numberFormat.numberFormat(Integer.valueOf(blocks)) + ChatColor.GRAY + "/" + ChatColor.GREEN +  numberFormat.numberFormat(LesterJobs.Lester6.getMax()) + ChatColor.WHITE;
        return new GuiItem(guiHelper.jobActive(jobName, lesterJobs.getObjective().replace("-", lore), lesterJobs.getRewards(), lesterJobs.getRewardAmount(), lesterJobs.getCurrencyRewards(), lesterJobs.getCurrencyRewardsAmount(), "Lester", "Yard"));
    }
    private GuiItem job7(Player player, String jobName, LesterJobs lesterJobs) {
        String blocks = parsePlaceholder(player, "betonquest_default-Jobs-Lester:point.Blocks3.amount");
        String lore = ChatColor.GREEN + numberFormat.numberFormat(Integer.valueOf(blocks)) + ChatColor.GRAY + "/" + ChatColor.GREEN +  numberFormat.numberFormat(LesterJobs.Lester7.getMax()) + ChatColor.WHITE;
        return new GuiItem(guiHelper.jobActive(jobName, lesterJobs.getObjective().replace("-", lore), lesterJobs.getRewards(), lesterJobs.getRewardAmount(), lesterJobs.getCurrencyRewards(), lesterJobs.getCurrencyRewardsAmount(), "Lester", "Yard"));
    }
    private GuiItem job8(Player player, String jobName, LesterJobs lesterJobs) {
        String blocks = parsePlaceholder(player, "betonquest_default-Jobs-Lester:point.Blocks3.amount");
        String lore = ChatColor.GREEN + numberFormat.numberFormat(Integer.valueOf(blocks)) + ChatColor.GRAY + "/" + ChatColor.GREEN +  numberFormat.numberFormat(LesterJobs.Lester8.getMax()) + ChatColor.WHITE;
        return new GuiItem(guiHelper.jobActive(jobName, lesterJobs.getObjective().replace("-", lore), lesterJobs.getRewards(), lesterJobs.getRewardAmount(), lesterJobs.getCurrencyRewards(), lesterJobs.getCurrencyRewardsAmount(), "Lester", "Yard"));
    }
    private GuiItem job9(Player player, String jobName, LesterJobs lesterJobs) {
        String blocks = parsePlaceholder(player, "betonquest_default-Jobs-Lester:point.Blocks3.amount");
        String lore = ChatColor.GREEN + numberFormat.numberFormat(Integer.valueOf(blocks)) + ChatColor.GRAY + "/" + ChatColor.GREEN +  numberFormat.numberFormat(LesterJobs.Lester9.getMax()) + ChatColor.WHITE;
        return new GuiItem(guiHelper.jobActive(jobName, lesterJobs.getObjective().replace("-", lore), lesterJobs.getRewards(), lesterJobs.getRewardAmount(), lesterJobs.getCurrencyRewards(), lesterJobs.getCurrencyRewardsAmount(), "Lester", "Yard"));
    }

    private String parsePlaceholder(Player player, String string) {
        return PlaceholderAPI.setPlaceholders(player, "%" + string + "%");
    }

}

enum LesterJobs {
    Lester1(500, ChatColor.WHITE + "Mine - Blocks", new ItemStack[]{null}, new int[]{0}, new String[]{"exp"}, new int[]{1_000}),
    Lester2(1_000, ChatColor.WHITE + "Mine - Blocks", new ItemStack[]{null}, new int[]{0}, new String[]{"exp"}, new int[]{2_000}),
    Lester3(1_500, ChatColor.WHITE + "Mine - Blocks", new ItemStack[]{null}, new int[]{0}, new String[]{"exp"}, new int[]{3_000}),
    Lester4(2_000, ChatColor.WHITE + "Mine - Blocks", new ItemStack[]{null}, new int[]{0}, new String[]{"exp"}, new int[]{4_000}),
    Lester5(2_500, ChatColor.WHITE + "Mine - Blocks", new ItemStack[]{null}, new int[]{0}, new String[]{"exp"}, new int[]{5_000}),
    Lester6(3_000, ChatColor.WHITE + "Mine - Blocks", new ItemStack[]{null}, new int[]{0}, new String[]{"exp"}, new int[]{6_000}),
    Lester7(3_500, ChatColor.WHITE + "Mine - Blocks", new ItemStack[]{null}, new int[]{0}, new String[]{"exp"}, new int[]{7_000}),
    Lester8(4_000, ChatColor.WHITE + "Mine - Blocks", new ItemStack[]{null}, new int[]{0}, new String[]{"exp"}, new int[]{8_000}),
    Lester9(4_500, ChatColor.WHITE + "Mine - Blocks", new ItemStack[]{null}, new int[]{0}, new String[]{"exp"}, new int[]{9_000}),

//  MMOItems.plugin.getItem("ARMOR", "PRISONER_HAT")
    ;
    private int max;
    private String objective;
    private ItemStack[] rewards;
    private int[] rewardAmount;
    private String[] currencyRewards;
    private int[] currencyRewardsAmount;

    LesterJobs(int max, String objective, ItemStack[] rewards, int[] rewardAmount, String[] currencyRewards, int[] currencyRewardsAmount) {
        this.max = max;
        this.objective = objective;
        this.rewards = rewards;
        this.rewardAmount = rewardAmount;
        this.currencyRewards = currencyRewards;
        this.currencyRewardsAmount = currencyRewardsAmount;
    }

    public int getMax() {
        return max;
    }

    public ItemStack[] getRewards() {
        return rewards;
    }

    public int[] getRewardAmount() {
        return rewardAmount;
    }

    public String[] getCurrencyRewards() {
        return currencyRewards;
    }

    public int[] getCurrencyRewardsAmount() {
        return currencyRewardsAmount;
    }

    public String getObjective() {
        return objective;
    }
}
