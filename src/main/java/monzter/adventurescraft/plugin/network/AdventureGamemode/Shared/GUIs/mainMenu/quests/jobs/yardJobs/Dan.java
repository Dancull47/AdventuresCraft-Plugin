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

public class Dan extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final GUIHelper guiHelper;
    private final BetonTagManager betonTagManager;
    private final BetonPointsManager betonPointsManager;
    private final NumberFormat numberFormat;


    public Dan(AdventuresCraft plugin, GUIHelper guiHelper, BetonTagManager betonTagManager, BetonPointsManager betonPointsManager, NumberFormat numberFormat) {
        this.plugin = plugin;
        this.guiHelper = guiHelper;
        this.betonTagManager = betonTagManager;
        this.betonPointsManager = betonPointsManager;
        this.numberFormat = numberFormat;
    }

    @CommandAlias("jobsDan")
    public void questMenu(Player player) {
        ChestGui gui = new ChestGui(4, guiHelper.guiName("Dan's Jobs"));
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        OutlinePane background = new OutlinePane(0, 0, 9, 4, Pane.Priority.LOWEST);
        StaticPane display = new StaticPane(3, 1, 3, 1, Pane.Priority.LOW);
        OutlinePane backButton = new OutlinePane(4, 3, 1, 1, Pane.Priority.LOW);

        backButton.addItem(new GuiItem(guiHelper.backButton(), e -> player.performCommand("yardJobs")));

        background.addItem(new GuiItem(guiHelper.background(Material.GREEN_STAINED_GLASS_PANE)));
        background.setRepeat(true);

        if (!betonTagManager.hasTag(player, "default-MiningPass-Dan.QuestCooldown24") && !betonTagManager.hasTag(player, "default-MiningPass-Dan.q1_part1") &&
                !betonTagManager.hasTag(player, "default-MiningPass-Dan.q2_part1") && !betonTagManager.hasTag(player, "default-MiningPass-Dan.q3_part1"))
            display.addItem(available(), 0, 0);
        else if (betonTagManager.hasTag(player, "default-MiningPass-Dan.QuestCooldown24") && !betonTagManager.hasTag(player, "default-MiningPass-Dan.q1_part1") &&
                !betonTagManager.hasTag(player, "default-MiningPass-Dan.q2_part1") && !betonTagManager.hasTag(player, "default-MiningPass-Dan.q3_part1"))
            display.addItem(cooldown(player), 0, 0);
        else if (betonTagManager.hasTag(player, "default-MiningPass-Dan.q1_part1") && !betonTagManager.hasTag(player, "default-MiningPass-Dan.q2_part1") && !betonTagManager.hasTag(player, "default-MiningPass-Dan.q3_part1"))
            display.addItem(job1(player, "Mine Some Blocks", DanJobs.Dan1), 0, 0);
        else if (betonTagManager.hasTag(player, "default-MiningPass-Dan.q2_part1") && !betonTagManager.hasTag(player, "default-MiningPass-Dan.q1_part1") && !betonTagManager.hasTag(player, "default-MiningPass-Dan.q3_part1"))
            display.addItem(job2(player, "Smash Some Glass", DanJobs.Dan2), 0, 0);
        else if (betonTagManager.hasTag(player, "default-MiningPass-Dan.q3_part1") && !betonTagManager.hasTag(player, "default-MiningPass-Dan.q1_part1") && !betonTagManager.hasTag(player, "default-MiningPass-Dan.q2_part1"))
            display.addItem(job3(player, "Mine Some Ores", DanJobs.Dan3), 0, 0);

        if (betonTagManager.hasTag(player, "default-MiningPass-Dan.QuestCooldown242") && !betonTagManager.hasTag(player, "default-MiningPass-Dan.q4_part1") &&
                !betonTagManager.hasTag(player, "default-MiningPass-Dan.q5_part1") && !betonTagManager.hasTag(player, "default-MiningPass-Dan.q6_part1"))
            display.addItem(cooldown2(player), 1, 0);
        else if (player.hasPermission("MININGPASS.PURCHASED") && !betonTagManager.hasTag(player, "default-MiningPass-Dan.q4_part1") && !betonTagManager.hasTag(player, "default-MiningPass-Dan.q5_part1") && !betonTagManager.hasTag(player, "default-MiningPass-Dan.q6_part1")
                || betonPointsManager.getPoints("default-Points.Dan", BetonQuest.getInstance().getPlayerData(player.getUniqueId().toString()).getPoints()) >= 50 &&
                !betonTagManager.hasTag(player, "default-MiningPass-Dan.q4_part1") && !betonTagManager.hasTag(player, "default-MiningPass-Dan.q5_part1") && !betonTagManager.hasTag(player, "default-MiningPass-Dan.q6_part1"))
            display.addItem(available(), 1, 0);
        else if (betonPointsManager.getPoints("default-Points.Dan", BetonQuest.getInstance().getPlayerData(player.getUniqueId().toString()).getPoints()) < 50 && !betonTagManager.hasTag(player, "default-MiningPass-Dan.q4_part1") &&
                !betonTagManager.hasTag(player, "default-MiningPass-Dan.q5_part1") && !betonTagManager.hasTag(player, "default-MiningPass-Dan.q6_part1") && !player.hasPermission("MININGPASS.PURCHASED"))
            display.addItem(locked(player), 1, 0);
        else if (betonTagManager.hasTag(player, "default-MiningPass-Dan.q4_part1"))
            display.addItem(job4(player, "Mine More Blocks", DanJobs.Dan4), 1, 0);
        else if (betonTagManager.hasTag(player, "default-MiningPass-Dan.q5_part1"))
            display.addItem(job5(player, "Smash More Glass", DanJobs.Dan5), 1, 0);
        else if (betonTagManager.hasTag(player, "default-MiningPass-Dan.q6_part1"))
            display.addItem(job6(player, "Mine Rare Blocks", DanJobs.Dan6), 1, 0);

        if (!player.hasPermission("MININGPASS.PURCHASED"))
            display.addItem(locked2(player), 2, 0);
        else if (betonTagManager.hasTag(player, "default-MiningPass-Dan.QuestCooldown243") && !betonTagManager.hasTag(player, "default-MiningPass-Dan.q7_part1") &&
                !betonTagManager.hasTag(player, "default-MiningPass-Dan.q8_part1") && !betonTagManager.hasTag(player, "default-MiningPass-Dan.q9_part1"))
            display.addItem(cooldown2(player), 2, 0);
        else if (player.hasPermission("MININGPASS.PURCHASED") && !betonTagManager.hasTag(player, "default-MiningPass-Dan.q7_part1") &&
                !betonTagManager.hasTag(player, "default-MiningPass-Dan.q8_part1") && !betonTagManager.hasTag(player, "default-MiningPass-Dan.q9_part1"))
            display.addItem(available(), 2, 0);
        else if (betonTagManager.hasTag(player, "default-MiningPass-Dan.q7_part1"))
            display.addItem(job7(player, "Hardcore Miner", DanJobs.Dan7), 2, 0);
        else if (betonTagManager.hasTag(player, "default-MiningPass-Dan.q8_part1"))
            display.addItem(job8(player, "Veteran Miner", DanJobs.Dan8), 2, 0);
        else if (betonTagManager.hasTag(player, "default-MiningPass-Dan.q9_part1"))
            display.addItem(job9(player, "BIG MINER", DanJobs.Dan9), 2, 0);

        display.addItem(new GuiItem(guiHelper.backButton(), e -> player.performCommand("YardJobs")), 4, 3);

        gui.addPane(background);
        gui.addPane(display);
        gui.addPane(backButton);
        gui.show(player);
    }


    private GuiItem available() {
        ItemStack itemStack = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName(ChatColor.GREEN + "Available Job Slot");

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "Speak with " + ChatColor.YELLOW + "Dan " + ChatColor.GRAY + "in the");
        lore.add(ChatColor.GREEN + "Town" + ChatColor.GRAY + ", to receive a " + ChatColor.DARK_RED + "Job" + ChatColor.GRAY + "!");

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
        lore.add(ChatColor.GRAY + "Complete " + ChatColor.YELLOW + "50 Jobs for Dan " + ChatColor.GRAY + "or purchase the");
        lore.add(ChatColor.YELLOW + "Mining Pass" + ChatColor.GRAY + " to take more than one Job at a time!");
        lore.add("");
        lore.add(ChatColor.GRAY + "You've completed " + ChatColor.YELLOW + parsePlaceholder(player, "betonquest_default-Points:point.Dan.amount") + " Job" + ChatColor.GRAY + "!");

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
        lore.add(ChatColor.GRAY + "Purchase the " + ChatColor.YELLOW + "Mining Pass");
        lore.add(ChatColor.GRAY + "to unlock the " + ChatColor.YELLOW + "3rd Job Slot" + ChatColor.GRAY + "!");

        itemStack.setItemMeta(itemMeta);
        itemStack.setLore(lore);
        return new GuiItem(itemStack);
    }

    private GuiItem cooldown(Player player) {
        ItemStack itemStack = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName(ChatColor.YELLOW.toString() + ChatColor.BOLD + "Job Slot on Cooldown");

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.YELLOW + parsePlaceholder(player, "betonquest_default-MiningPass-Dan:objective.QuestCooldown24.left") + ChatColor.GREEN + " left until this Slot resets!");

        itemStack.setItemMeta(itemMeta);
        itemStack.setLore(lore);
        return new GuiItem(itemStack);
    }

    private GuiItem cooldown2(Player player) {
        ItemStack itemStack = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName(ChatColor.YELLOW.toString() + ChatColor.BOLD + "Job Slot on Cooldown");

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.YELLOW + parsePlaceholder(player, "betonquest_default-MiningPass-Dan:objective.QuestCooldown242.left") + ChatColor.GREEN + " left until this Slot resets!");

        itemStack.setItemMeta(itemMeta);
        itemStack.setLore(lore);
        return new GuiItem(itemStack);
    }

    private GuiItem cooldown3(Player player) {
        ItemStack itemStack = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName(ChatColor.YELLOW.toString() + ChatColor.BOLD + "Job Slot on Cooldown");

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.YELLOW + parsePlaceholder(player, "betonquest_default-MiningPass-Dan:objective.QuestCooldown243.left") + ChatColor.GREEN + " left until this Slot resets!");

        itemStack.setItemMeta(itemMeta);
        itemStack.setLore(lore);
        return new GuiItem(itemStack);
    }

    private GuiItem job1(Player player, String jobName, DanJobs lesterJobs) {
        String blocks = parsePlaceholder(player, "betonquest_default-MiningPass-Dan:point.Blocks.amount");
        String lore = ChatColor.GREEN + numberFormat.numberFormat(Integer.valueOf(blocks)) + ChatColor.GRAY + "/" + ChatColor.GREEN + numberFormat.numberFormat(DanJobs.Dan1.getMax()) + ChatColor.WHITE;
        return new GuiItem(guiHelper.jobActive(jobName, lesterJobs.getObjective().replace("-", lore), lesterJobs.getRewards(), lesterJobs.getRewardAmount(), lesterJobs.getCurrencyRewards(), lesterJobs.getCurrencyRewardsAmount(), parsePlaceholder(player, "betonquest_default-MiningPass-Dan:objective.QuestCooldown24.left")));
    }

    private GuiItem job2(Player player, String jobName, DanJobs lesterJobs) {
        String blocks = parsePlaceholder(player, "betonquest_default-MiningPass-Dan:point.Blocks.amount");
        String lore = ChatColor.GREEN + numberFormat.numberFormat(Integer.valueOf(blocks)) + ChatColor.GRAY + "/" + ChatColor.GREEN + numberFormat.numberFormat(DanJobs.Dan2.getMax()) + ChatColor.WHITE;
        return new GuiItem(guiHelper.jobActive(jobName, lesterJobs.getObjective().replace("-", lore), lesterJobs.getRewards(), lesterJobs.getRewardAmount(), lesterJobs.getCurrencyRewards(), lesterJobs.getCurrencyRewardsAmount(), parsePlaceholder(player, "betonquest_default-MiningPass-Dan:objective.QuestCooldown24.left")));
    }

    private GuiItem job3(Player player, String jobName, DanJobs lesterJobs) {
        String blocks = parsePlaceholder(player, "betonquest_default-MiningPass-Dan:point.Blocks.amount");
        String lore = ChatColor.GREEN + numberFormat.numberFormat(Integer.valueOf(blocks)) + ChatColor.GRAY + "/" + ChatColor.GREEN + numberFormat.numberFormat(DanJobs.Dan3.getMax()) + ChatColor.WHITE;
        return new GuiItem(guiHelper.jobActive(jobName, lesterJobs.getObjective().replace("-", lore), lesterJobs.getRewards(), lesterJobs.getRewardAmount(), lesterJobs.getCurrencyRewards(), lesterJobs.getCurrencyRewardsAmount(), parsePlaceholder(player, "betonquest_default-MiningPass-Dan:objective.QuestCooldown24.left")));
    }

    private GuiItem job4(Player player, String jobName, DanJobs lesterJobs) {
        String blocks = parsePlaceholder(player, "betonquest_default-MiningPass-Dan:point.Blocks2.amount");
        String lore = ChatColor.GREEN + numberFormat.numberFormat(Integer.valueOf(blocks)) + ChatColor.GRAY + "/" + ChatColor.GREEN + numberFormat.numberFormat(DanJobs.Dan4.getMax()) + ChatColor.WHITE;
        return new GuiItem(guiHelper.jobActive(jobName, lesterJobs.getObjective().replace("-", lore), lesterJobs.getRewards(), lesterJobs.getRewardAmount(), lesterJobs.getCurrencyRewards(), lesterJobs.getCurrencyRewardsAmount(), parsePlaceholder(player, "betonquest_default-MiningPass-Dan:objective.QuestCooldown242.left")));
    }

    private GuiItem job5(Player player, String jobName, DanJobs lesterJobs) {
        String blocks = parsePlaceholder(player, "betonquest_default-MiningPass-Dan:point.Blocks2.amount");
        String lore = ChatColor.GREEN + numberFormat.numberFormat(Integer.valueOf(blocks)) + ChatColor.GRAY + "/" + ChatColor.GREEN + numberFormat.numberFormat(DanJobs.Dan5.getMax()) + ChatColor.WHITE;
        return new GuiItem(guiHelper.jobActive(jobName, lesterJobs.getObjective().replace("-", lore), lesterJobs.getRewards(), lesterJobs.getRewardAmount(), lesterJobs.getCurrencyRewards(), lesterJobs.getCurrencyRewardsAmount(), parsePlaceholder(player, "betonquest_default-MiningPass-Dan:objective.QuestCooldown242.left")));
    }

    private GuiItem job6(Player player, String jobName, DanJobs lesterJobs) {
        String blocks = parsePlaceholder(player, "betonquest_default-MiningPass-Dan:point.Blocks2.amount");
        String lore = ChatColor.GREEN + numberFormat.numberFormat(Integer.valueOf(blocks)) + ChatColor.GRAY + "/" + ChatColor.GREEN + numberFormat.numberFormat(DanJobs.Dan6.getMax()) + ChatColor.WHITE;
        return new GuiItem(guiHelper.jobActive(jobName, lesterJobs.getObjective().replace("-", lore), lesterJobs.getRewards(), lesterJobs.getRewardAmount(), lesterJobs.getCurrencyRewards(), lesterJobs.getCurrencyRewardsAmount(), parsePlaceholder(player, "betonquest_default-MiningPass-Dan:objective.QuestCooldown242.left")));
    }

    private GuiItem job7(Player player, String jobName, DanJobs lesterJobs) {
        String blocks = parsePlaceholder(player, "betonquest_default-MiningPass-Dan:point.Blocks3.amount");
        String lore = ChatColor.GREEN + numberFormat.numberFormat(Integer.valueOf(blocks)) + ChatColor.GRAY + "/" + ChatColor.GREEN + numberFormat.numberFormat(DanJobs.Dan7.getMax()) + ChatColor.WHITE;
        return new GuiItem(guiHelper.jobActive(jobName, lesterJobs.getObjective().replace("-", lore), lesterJobs.getRewards(), lesterJobs.getRewardAmount(), lesterJobs.getCurrencyRewards(), lesterJobs.getCurrencyRewardsAmount(), parsePlaceholder(player, "betonquest_default-MiningPass-Dan:objective.QuestCooldown243.left")));
    }

    private GuiItem job8(Player player, String jobName, DanJobs lesterJobs) {
        String blocks = parsePlaceholder(player, "betonquest_default-MiningPass-Dan:point.Blocks3.amount");
        String lore = ChatColor.GREEN + numberFormat.numberFormat(Integer.valueOf(blocks)) + ChatColor.GRAY + "/" + ChatColor.GREEN + numberFormat.numberFormat(DanJobs.Dan8.getMax()) + ChatColor.WHITE;
        return new GuiItem(guiHelper.jobActive(jobName, lesterJobs.getObjective().replace("-", lore), lesterJobs.getRewards(), lesterJobs.getRewardAmount(), lesterJobs.getCurrencyRewards(), lesterJobs.getCurrencyRewardsAmount(), parsePlaceholder(player, "betonquest_default-MiningPass-Dan:objective.QuestCooldown243.left")));
    }

    private GuiItem job9(Player player, String jobName, DanJobs lesterJobs) {
        String blocks = parsePlaceholder(player, "betonquest_default-MiningPass-Dan:point.Blocks3.amount");
        String lore = ChatColor.GREEN + numberFormat.numberFormat(Integer.valueOf(blocks)) + ChatColor.GRAY + "/" + ChatColor.GREEN + numberFormat.numberFormat(DanJobs.Dan9.getMax()) + ChatColor.WHITE;
        return new GuiItem(guiHelper.jobActive(jobName, lesterJobs.getObjective().replace("-", lore), lesterJobs.getRewards(), lesterJobs.getRewardAmount(), lesterJobs.getCurrencyRewards(), lesterJobs.getCurrencyRewardsAmount(), parsePlaceholder(player, "betonquest_default-MiningPass-Dan:objective.QuestCooldown243.left")));
    }

    private String parsePlaceholder(Player player, String string) {
        return PlaceholderAPI.setPlaceholders(player, "%" + string + "%");
    }

}

enum DanJobs {
    Dan1(5_000, ChatColor.WHITE + "Mine - Blocks", new ItemStack[]{null}, new int[]{0}, new String[]{"miningpassexp"}, new int[]{1_000}),
    Dan2(50, ChatColor.WHITE + "Mine - Glass Blocks", new ItemStack[]{null}, new int[]{0}, new String[]{"miningpassexp"}, new int[]{1_000}),
    Dan3(1_000, ChatColor.WHITE + "Mine - Ores", new ItemStack[]{null}, new int[]{0}, new String[]{"miningpassexp"}, new int[]{1_000}),
    Dan4(5_000, ChatColor.WHITE + "Mine - Blocks", new ItemStack[]{null}, new int[]{0}, new String[]{"miningpassexp"}, new int[]{1_000}),
    Dan5(100, ChatColor.WHITE + "Mine - Glass Blocks", new ItemStack[]{null}, new int[]{0}, new String[]{"miningpassexp"}, new int[]{1_000}),
    Dan6(1_500, ChatColor.WHITE + "Mine - Rare Blocks", new ItemStack[]{null}, new int[]{0}, new String[]{"miningpassexp"}, new int[]{1_000}),
    Dan7(3_500, ChatColor.WHITE + "Mine - Blocks", new ItemStack[]{null}, new int[]{0}, new String[]{"miningpassexp"}, new int[]{1_000}),
    Dan8(4_000, ChatColor.WHITE + "Mine - Blocks", new ItemStack[]{null}, new int[]{0}, new String[]{"miningpassexp"}, new int[]{1_000}),
    Dan9(4_500, ChatColor.WHITE + "Mine - Blocks", new ItemStack[]{null}, new int[]{0}, new String[]{"miningpassexp"}, new int[]{1_000}),

//  MMOItems.plugin.getItem("ARMOR", "PRISONER_HAT")
    ;
    private int max;
    private String objective;
    private ItemStack[] rewards;
    private int[] rewardAmount;
    private String[] currencyRewards;
    private int[] currencyRewardsAmount;

    DanJobs(int max, String objective, ItemStack[] rewards, int[] rewardAmount, String[] currencyRewards, int[] currencyRewardsAmount) {
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
