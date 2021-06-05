package monzter.adventurescraft.plugin.network.PrisonGamemode.shared.GUIs.mainMenu.donation;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Dependency;
import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.PaginatedPane;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import com.google.common.base.Strings;
import io.lumine.mythic.lib.api.util.SmartGive;
import me.clip.placeholderapi.PlaceholderAPI;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.network.PrisonGamemode.shared.GUIs.mainMenu.donation.miningPass.MiningPassLevels;
import monzter.adventurescraft.plugin.network.PrisonGamemode.shared.GUIs.mainMenu.donation.miningPass.PremiumMiningPassLevels;
import monzter.adventurescraft.plugin.utilities.GUI.GUIHelper;
import monzter.adventurescraft.plugin.utilities.beton.BetonPointsManager;
import monzter.adventurescraft.plugin.utilities.enums.Prefix;
import monzter.adventurescraft.plugin.utilities.enums.PrisonStatsDisplay;
import monzter.adventurescraft.plugin.utilities.general.ConsoleCommand;
import monzter.adventurescraft.plugin.utilities.general.FullInventory;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import monzter.adventurescraft.plugin.utilities.luckperms.PermissionLP;
import monzter.adventurescraft.plugin.utilities.text.NumberFormat;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class MiningPass extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final SoundManager soundManager;
    private final GUIHelper guiHelper;
    private final ConsoleCommand consoleCommand;
    private final NumberFormat numberFormat;
    private final FullInventory fullInventory;
    private final PermissionLP permissionLP;
    private final BetonPointsManager betonPointsManager;
    private final String REWARD = "  " + ChatColor.GOLD + ChatColor.BOLD + "REWARDS" + ChatColor.WHITE + ":";
    final TextComponent miningPass = Component.text("You haven't purchased the ")
            .color(NamedTextColor.RED)
            .append(Component.text("Premium ", NamedTextColor.GOLD, TextDecoration.BOLD))
            .append(Component.text("Mining Pass ", NamedTextColor.YELLOW, TextDecoration.BOLD))
            .append(Component.text("which is required to claim this reward!"))
            .append(Component.text(" <- CLICK HERE TO PURCHASE", NamedTextColor.GOLD, TextDecoration.BOLD))
            .hoverEvent(Component.text("Click to visit the Store's Battle Pass!", NamedTextColor.GREEN))
            .clickEvent(ClickEvent.openUrl("https://store.adventurescraft.net"));

    public MiningPass(AdventuresCraft plugin, SoundManager soundManager, GUIHelper guiHelper, ConsoleCommand consoleCommand, NumberFormat numberFormat, FullInventory fullInventory, PermissionLP permissionLP, BetonPointsManager betonPointsManager) {
        this.plugin = plugin;
        this.soundManager = soundManager;
        this.guiHelper = guiHelper;
        this.consoleCommand = consoleCommand;
        this.numberFormat = numberFormat;
        this.fullInventory = fullInventory;
        this.permissionLP = permissionLP;
        this.betonPointsManager = betonPointsManager;
    }

    @CommandAlias("miningPassMenu|miningpass|mp|battlepass")
    public void donate(Player player) {
        donate(player, 0);
    }

    public void donate(Player player, int openPage) {
        int miningPassEXP = Integer.valueOf(parsePlaceholder(player, "ac_Stat_MiningPassEXPAmount"));
        int miningPassLevel = Integer.valueOf(PlaceholderAPI.setPlaceholders(player, "%ac_Stat_MiningPassLevel%"));

        ChestGui gui = new ChestGui(6, guiHelper.guiName("Mining Pass"));
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        PaginatedPane page = new PaginatedPane(0, 0, 9, 6);
        OutlinePane background = new OutlinePane(0, 0, 9, 6, Pane.Priority.LOWEST);
        OutlinePane freeDisplayP1 = new OutlinePane(0, 2, 9, 1, Pane.Priority.LOW);
        OutlinePane freeDisplayP2 = new OutlinePane(0, 2, 9, 1, Pane.Priority.LOW);
        OutlinePane freeDisplayP3 = new OutlinePane(0, 2, 9, 1, Pane.Priority.LOW);
        OutlinePane freeDisplayP4 = new OutlinePane(0, 2, 9, 1, Pane.Priority.LOW);
        OutlinePane freeDisplayP5 = new OutlinePane(0, 2, 9, 1, Pane.Priority.LOW);
        OutlinePane freeDisplayP6 = new OutlinePane(0, 2, 9, 1, Pane.Priority.LOW);
        OutlinePane premiumDisplayP1 = new OutlinePane(0, 3, 9, 1, Pane.Priority.LOW);
        OutlinePane premiumDisplayP2 = new OutlinePane(0, 3, 9, 1, Pane.Priority.LOW);
        OutlinePane premiumDisplayP3 = new OutlinePane(0, 3, 9, 1, Pane.Priority.LOW);
        OutlinePane premiumDisplayP4 = new OutlinePane(0, 3, 9, 1, Pane.Priority.LOW);
        OutlinePane premiumDisplayP5 = new OutlinePane(0, 3, 9, 1, Pane.Priority.LOW);
        OutlinePane premiumDisplayP6 = new OutlinePane(0, 3, 9, 1, Pane.Priority.LOW);
        StaticPane firstPage = new StaticPane(0, 5, 1, 1, Pane.Priority.HIGH);
        StaticPane back = new StaticPane(1, 5, 1, 1, Pane.Priority.HIGH);
        StaticPane backButton = new StaticPane(4, 5, 1, 1, Pane.Priority.HIGHEST);
        StaticPane miningPassExplanation = new StaticPane(4, 0, 1, 1, Pane.Priority.HIGHEST);
        StaticPane forward = new StaticPane(7, 5, 1, 1, Pane.Priority.HIGH);
        StaticPane lastPage = new StaticPane(8, 5, 1, 1, Pane.Priority.HIGH);

        page.addPane(0, background);
        page.addPane(0, freeDisplayP1);
        page.addPane(0, premiumDisplayP1);
        page.addPane(1, background);
        page.addPane(1, freeDisplayP2);
        page.addPane(1, premiumDisplayP2);
        page.addPane(2, background);
        page.addPane(2, freeDisplayP3);
        page.addPane(2, premiumDisplayP3);
        page.addPane(3, background);
        page.addPane(3, freeDisplayP4);
        page.addPane(3, premiumDisplayP4);
        page.addPane(4, background);
        page.addPane(4, freeDisplayP5);
        page.addPane(4, premiumDisplayP5);
        page.addPane(5, background);
        page.addPane(5, freeDisplayP6);
        page.addPane(5, premiumDisplayP6);
        background.addItem(new GuiItem(guiHelper.background(Material.YELLOW_STAINED_GLASS_PANE)));
        background.setRepeat(true);

        backButton.addItem(new GuiItem(guiHelper.backButton(), e -> player.performCommand("mainMenu")), 0, 0);
        miningPassExplanation.addItem(new GuiItem(miningPassExplanation(player, miningPassLevel, miningPassEXP), e -> {
            if (!player.hasPermission("MININGPASS.PREMIUM.PURCHASED"))
                player.sendMessage(miningPass);
        }), 0, 0);

        int i = 1;
        int amount = 0;
        for (MiningPassLevels reward : MiningPassLevels.values()) {
            ItemStack itemStack = new ItemStack(Material.MINECART);

            if (player.hasPermission("MININGPASS.REWARD.CLAIMED." + reward.getLevel()))
                itemStack = new ItemStack(Material.CHEST_MINECART);
            else if (miningPassEXP >= reward.getPrice())
                itemStack = new ItemStack(Material.TNT_MINECART);

            final ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName(ChatColor.YELLOW + "Mining Pass Level " + i);

            List<String> lore = new ArrayList<>();
            lore.add(" ");
            lore.add(REWARD);
            if (reward.isAdventureCoinReward())
                lore.add("   " + Prefix.PREFIX.getString() + ChatColor.GOLD + reward.getCoins() + "x " + PrisonStatsDisplay.ADVENTURE_COINS.getName());
            for (ItemStack itemStack1 : reward.getRewards()) {
                lore.add("   " + Prefix.PREFIX.getString() + ChatColor.GOLD + reward.getRewardAmount()[amount] + "x " + itemStack1.getItemMeta().getDisplayName());
                amount++;
            }
            amount = 0;

            if (miningPassEXP < reward.getPrice()) {
                itemMeta.setDisplayName(ChatColor.YELLOW + "Mining Pass Level " + i + ChatColor.DARK_GRAY + " -" + ChatColor.RED + ChatColor.BOLD + " LOCKED");
                lore.add(" ");
                lore.add(ChatColor.RED.toString() + ChatColor.BOLD + "LOCKED");
            } else if (player.hasPermission("MININGPASS.REWARD.CLAIMED." + reward.getLevel())) {
                itemMeta.setDisplayName(ChatColor.YELLOW + "Mining Pass Level " + i + ChatColor.DARK_GRAY + " -" + ChatColor.YELLOW + ChatColor.BOLD + " CLAIMED");
                lore.add(" ");
                lore.add(ChatColor.YELLOW.toString() + ChatColor.BOLD + "CLAIMED");
            } else if (miningPassEXP >= reward.getPrice()) {
                itemMeta.setDisplayName(ChatColor.YELLOW + "Mining Pass Level " + i + ChatColor.DARK_GRAY + " -" + ChatColor.GREEN + ChatColor.BOLD + " UNLOCKED");
                lore.add(" ");
                lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Claim Reward");
            }

            itemMeta.setLore(lore);
            itemStack.setItemMeta(itemMeta);

            if (i < 10) {
                freeDisplayP1.addItem(new GuiItem(itemStack, e -> {
                    player.performCommand("miningPassClaimReward " + reward.getLevel());
                    donate(player, 0);
                }));
            } else if (i > 9 && i < 19) {
                freeDisplayP2.addItem(new GuiItem(itemStack, e -> {
                    player.performCommand("miningPassClaimReward " + reward.getLevel());
                    donate(player, 1);
                }));
            } else if (i - 1 > 18 && i - 1 < 28) {
                freeDisplayP3.addItem(new GuiItem(itemStack, e -> {
                    player.performCommand("miningPassClaimReward " + reward.getLevel());
                    donate(player, 2);
                }));
            } else if (i - 1 > 27 && i - 1 < 37) {
                freeDisplayP4.addItem(new GuiItem(itemStack, e -> {
                    player.performCommand("miningPassClaimReward " + reward.getLevel());
                    donate(player, 3);
                }));
            } else if (i - 1 > 36 && i - 1 < 46) {
                freeDisplayP5.addItem(new GuiItem(itemStack, e -> {
                    player.performCommand("miningPassClaimReward " + reward.getLevel());
                    donate(player, 4);
                }));
            } else if (i - 1 > 45 && i - 1 < 55) {
                freeDisplayP6.addItem(new GuiItem(itemStack, e -> {
                    player.performCommand("miningPassClaimReward " + reward.getLevel());
                    donate(player, 5);
                }));
            }
            i++;

        }

        int i2 = 1;
        int amount2 = 0;
        for (PremiumMiningPassLevels premiumReward : PremiumMiningPassLevels.values()) {
            ItemStack itemStack = new ItemStack(Material.MINECART);

            if (player.hasPermission("MININGPASS.PREMIUM.PURCHASED"))
                if (player.hasPermission("MININGPASS.PREMIUM.CLAIMED." + premiumReward.getLevel()))
                    itemStack = new ItemStack(Material.CHEST_MINECART);
                else if (miningPassEXP >= premiumReward.getPrice())
                    itemStack = new ItemStack(Material.HOPPER_MINECART);

            final ItemMeta itemMeta = itemStack.getItemMeta();

            List<String> lore = new ArrayList<>();
            lore.add(" ");
            lore.add(REWARD);
            if (premiumReward.isAdventureCoinReward())
                lore.add("   " + Prefix.PREFIX.getString() + ChatColor.GOLD + premiumReward.getCoins() + "x " + PrisonStatsDisplay.ADVENTURE_COINS.getName());
            for (ItemStack itemStack1 : premiumReward.getRewards()) {
                lore.add("   " + Prefix.PREFIX.getString() + ChatColor.GOLD + premiumReward.getRewardAmount()[amount2] + "x " + itemStack1.getItemMeta().getDisplayName());
                amount2++;
            }
            amount2 = 0;

            if (miningPassEXP < premiumReward.getPrice()) {
                itemMeta.setDisplayName(ChatColor.GOLD.toString() + ChatColor.BOLD + "Premium " + ChatColor.YELLOW + "Mining Pass Level " + i2 + ChatColor.DARK_GRAY + " -" + ChatColor.RED + ChatColor.BOLD + " LOCKED");
                lore.add(" ");
                lore.add(ChatColor.RED.toString() + ChatColor.BOLD + "LOCKED");
            } else if (player.hasPermission("MININGPASS.PREMIUM.CLAIMED." + premiumReward.getLevel())) {
                itemMeta.setDisplayName(ChatColor.GOLD.toString() + ChatColor.BOLD + "Premium " + ChatColor.YELLOW + "Mining Pass Level " + i2 + ChatColor.DARK_GRAY + " -" + ChatColor.YELLOW + ChatColor.BOLD + " CLAIMED");
                lore.add(" ");
                lore.add(ChatColor.YELLOW.toString() + ChatColor.BOLD + "CLAIMED");
            } else if (miningPassEXP >= premiumReward.getPrice()) {
                itemMeta.setDisplayName(ChatColor.GOLD.toString() + ChatColor.BOLD + "Premium " + ChatColor.YELLOW + "Mining Pass Level " + i2 + ChatColor.DARK_GRAY + " -" + ChatColor.GREEN + ChatColor.BOLD + " UNLOCKED");
                lore.add(" ");
                lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Claim Reward");
            }
            itemMeta.setLore(lore);
            itemStack.setItemMeta(itemMeta);

            if (i2 < 10) {
                premiumDisplayP1.addItem(new GuiItem(itemStack, e -> {
                    player.performCommand("miningPassClaimRewardP " + premiumReward.getLevel());
                    donate(player, 0);
                }));
            } else if (i2 > 9 && i2 < 19) {
                premiumDisplayP2.addItem(new GuiItem(itemStack, e -> {
                    player.performCommand("miningPassClaimRewardP " + premiumReward.getLevel());
                    donate(player, 1);
                }));
            } else if (i2 - 1 > 18 && i2 - 1 < 28) {
                premiumDisplayP3.addItem(new GuiItem(itemStack, e -> {
                    player.performCommand("miningPassClaimRewardP " + premiumReward.getLevel());
                    donate(player, 2);
                }));
            } else if (i2 - 1 > 27 && i2 - 1 < 37) {
                premiumDisplayP4.addItem(new GuiItem(itemStack, e -> {
                    player.performCommand("miningPassClaimRewardP " + premiumReward.getLevel());
                    donate(player, 3);
                }));
            } else if (i2 - 1 > 36 && i2 - 1 < 46) {
                premiumDisplayP5.addItem(new GuiItem(itemStack, e -> {
                    player.performCommand("miningPassClaimRewardP " + premiumReward.getLevel());
                    donate(player, 4);
                }));
            } else if (i2 - 1 > 45 && i2 - 1 < 55) {
                premiumDisplayP6.addItem(new GuiItem(itemStack, e -> {
                    player.performCommand("miningPassClaimRewardP " + premiumReward.getLevel());
                    donate(player, 5);
                }));
            }
            i2++;

        }

        if (!freeDisplayP2.getItems().isEmpty()) {
            back.addItem(new GuiItem((guiHelper.previousPageButton()), event -> {
                page.setPage(page.getPage() - 1);
                if (page.getPage() == 0) {
                    firstPage.setVisible(false);
                    back.setVisible(false);
                }
                forward.setVisible(true);
                lastPage.setVisible(true);
                gui.update();
            }), 0, 0);
            back.setVisible(false);

            firstPage.addItem(new GuiItem((guiHelper.firstPageButton()), event -> {
                page.setPage(page.getPages() - page.getPages());
                if (page.getPage() == page.getPages() - page.getPages()) {
                    firstPage.setVisible(false);
                    back.setVisible(false);
                }
                forward.setVisible(true);
                lastPage.setVisible(true);
                gui.update();
            }), 0, 0);
            firstPage.setVisible(false);

            forward.addItem(new GuiItem((guiHelper.nextPageButton()), event -> {
                page.setPage(page.getPage() + 1);
                if (page.getPage() == page.getPages() - 1) {
                    forward.setVisible(false);
                    lastPage.setVisible(false);
                }
                back.setVisible(true);
                firstPage.setVisible(true);
                gui.update();
            }), 0, 0);

            lastPage.addItem(new GuiItem((guiHelper.lastPageButton()), event -> {
                page.setPage(page.getPages() - 1);
                if (page.getPage() == page.getPages() - 1) {
                    forward.setVisible(false);
                    lastPage.setVisible(false);
                }
                back.setVisible(true);
                firstPage.setVisible(true);
                gui.update();
            }), 0, 0);
        }
        page.setPage(openPage);
        if (page.getPage() > 0) {
            back.setVisible(true);
            firstPage.setVisible(true);
        }
        if (page.getPage() > 2) {
            forward.setVisible(false);
            lastPage.setVisible(false);
        }
        gui.addPane(forward);
        gui.addPane(firstPage);
        gui.addPane(lastPage);
        gui.addPane(backButton);
        gui.addPane(miningPassExplanation);
        gui.addPane(page);
        gui.addPane(back);
        gui.show(player);
    }

    @CommandAlias("miningPassClaimReward")
    public void miningPassClaim(Player player, String reward) {
        int miningPassEXP = Integer.valueOf(parsePlaceholder(player, "ac_Stat_MiningPassEXPAmount"));

        int i = 0;
        for (MiningPassLevels miningPassReward : MiningPassLevels.values()) {
            if (reward.equals(String.valueOf(miningPassReward.getLevel()))) {
                if (levelCheck(player, miningPassEXP, miningPassReward.getPrice())) {
                    if (claimedCheck(player, miningPassReward.getLevel(), "REWARD")) {
                        if (!fullInventory.fullInventory(player)) {
                            if (miningPassReward.isAdventureCoinReward())
                                rewardAdventureCoins(player, miningPassReward.getCoins());
                            for (ItemStack itemStack : miningPassReward.getRewards()) {
                                new SmartGive(player).give(itemStack.asQuantity(miningPassReward.getRewardAmount()[i]));
                                player.sendMessage(ChatColor.YELLOW + "You received " + ChatColor.GOLD + miningPassReward.getRewardAmount()[i] + ChatColor.YELLOW + "x " + itemStack.getItemMeta().getDisplayName() + ChatColor.YELLOW + "!");
                                i++;
                            }
                            soundManager.playSound(player, Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
                            permissionLP.givePermission(player, "MININGPASS.REWARD.CLAIMED." + miningPassReward.getLevel());
                            player.sendMessage(ChatColor.GREEN + "You claimed your " + ChatColor.YELLOW + ChatColor.BOLD + "Level " + miningPassReward.getLevel() + " Mining Pass Reward" + ChatColor.GREEN + "!");
                            i = 0;
                        }
                    }
                }
            }
        }
    }

    @CommandAlias("miningPassClaimRewardP")
    public void miningPassClaimP(Player player, String reward) {
        if (hasMiningPass(player)) {
            int miningPassEXP = Integer.valueOf(parsePlaceholder(player, "ac_Stat_MiningPassEXPAmount"));
            int i = 0;
            for (PremiumMiningPassLevels miningPassReward : PremiumMiningPassLevels.values()) {
                if (String.valueOf(miningPassReward.getLevel()).equals(reward)) {
                    if (levelCheck(player, miningPassEXP, miningPassReward.getPrice())) {
                        if (claimedCheck(player, miningPassReward.getLevel(), "PREMIUM")) {
                            if (!fullInventory.fullInventory(player)) {
                                // NEW
                                if (miningPassReward.isAdventureCoinReward())
                                    rewardAdventureCoins(player, miningPassReward.getCoins());
                                for (ItemStack itemStack : miningPassReward.getRewards()) {
                                    new SmartGive(player).give(itemStack.asQuantity(miningPassReward.getRewardAmount()[i]));
                                    player.sendMessage(ChatColor.YELLOW + "You received " + ChatColor.GOLD + miningPassReward.getRewardAmount()[i] + ChatColor.YELLOW + "x " + itemStack.getItemMeta().getDisplayName() + ChatColor.YELLOW + "!");
                                    i++;
                                }
                                soundManager.playSound(player, Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
                                permissionLP.givePermission(player, "MININGPASS.PREMIUM.CLAIMED." + miningPassReward.getLevel());
                                player.sendMessage(ChatColor.GREEN + "You claimed your " + ChatColor.YELLOW + ChatColor.BOLD + "Level " + miningPassReward.getLevel() + ChatColor.GOLD + " Premium" + ChatColor.YELLOW + " Mining Pass Reward" + ChatColor.GREEN + "!");
                                i = 0;
                            }
                        }
                    }
                }
            }
        }
    }

    private ItemStack miningPassExplanation(Player player, int miningPassLevel, int expAmount) {
        String nextLevelEXPAmount = parsePlaceholder(player, "ac_Stat_MiningPassNextLevelEXPAmount");
        final ItemStack miningPass = new ItemStack(Material.MINECART);
        final ItemMeta miningPassItemMeta = miningPass.getItemMeta();

        String pass = player.hasPermission("MININGPASS.PREMIUM.PURCHASED") ? ChatColor.GOLD.toString() + ChatColor.BOLD + "Premium " : "";
        miningPassItemMeta.displayName(Component.text(pass + ChatColor.YELLOW + ChatColor.BOLD + "Mining Pass Level " + ChatColor.GOLD + ChatColor.BOLD + miningPassLevel));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "Level up your " + ChatColor.GOLD + ChatColor.BOLD + "Mining Pass " + ChatColor.GRAY + "by completing");
        lore.add(ChatColor.GRAY + "daily objectives for unique rewards!");
        if (!player.hasPermission("MININGPASS.PREMIUM.PURCHASED")) {
            lore.add("");
            lore.add(ChatColor.GRAY + "You can also purchase the" + ChatColor.GOLD + ChatColor.BOLD + " Premium " + ChatColor.YELLOW + ChatColor.BOLD + "Mining Pass");
            lore.add(ChatColor.GRAY + "to receive additional rewards as you level up!");
            lore.add("");
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Purchase");
        }
        lore.add("");
        if (!nextLevelEXPAmount.equals("Max")) {
            if (expAmount != 0)
                lore.add(ChatColor.GOLD.toString() + ChatColor.BOLD + "Progress to " + ChatColor.YELLOW + ChatColor.BOLD + "Level " + ChatColor.GOLD + ChatColor.BOLD + Integer.valueOf(miningPassLevel + 1) + ": " + ChatColor.YELLOW + (((float) expAmount / Float.valueOf(nextLevelEXPAmount))*100) + "%");
            else
                lore.add(ChatColor.GOLD.toString() + ChatColor.BOLD + "Progress to " + ChatColor.YELLOW + ChatColor.BOLD + "Level " + ChatColor.GOLD + ChatColor.BOLD + Integer.valueOf(miningPassLevel + 1) + ": " + ChatColor.YELLOW + 0 + "%");
            lore.add(getProgressBar(expAmount, Integer.valueOf(nextLevelEXPAmount), 19, '-', ChatColor.YELLOW, ChatColor.BOLD) + " " + ChatColor.YELLOW + numberFormat.numberFormat(expAmount) + "/" + numberFormat.numberFormat(Integer.valueOf(nextLevelEXPAmount)) + " Experience");
        } else {
            lore.add(ChatColor.GOLD.toString() + ChatColor.BOLD + "MAX LEVEL REACHED");
        }

        miningPass.setItemMeta(miningPassItemMeta);
        miningPass.setLore(lore);

        return miningPass;
    }

    private void rewardAdventureCoins(Player player, int advenutreCoins) {
        betonPointsManager.takePointACs(player, advenutreCoins);
        player.sendMessage(ChatColor.GREEN + "You gained +" + ChatColor.GOLD + advenutreCoins + ChatColor.YELLOW + "x " + PrisonStatsDisplay.ADVENTURE_COINS.getName() + ChatColor.GREEN + "!");
    }

    private boolean levelCheck(Player player, int exp, int cost) {
        if (exp >= cost) {
            return true;
        } else {
            player.sendMessage(ChatColor.RED + "You only have " + ChatColor.GOLD + numberFormat.numberFormat(exp) + ChatColor.RED + " / " + ChatColor.GOLD + numberFormat.numberFormat(cost) + " " + PrisonStatsDisplay.MINING_PASS_EXPERIENCE.getName() + ChatColor.RED + "!");
            soundManager.soundNo(player, 1);
            return false;
        }
    }

    private boolean hasMiningPass(Player player) {
        if (player.hasPermission("MININGPASS.PREMIUM.PURCHASED")) {
            return true;
        } else {
            player.sendMessage(miningPass);
            soundManager.soundNo(player, 1);
            return false;
        }
    }

    private boolean claimedCheck(Player player, String level, String pass) {
        if (player.hasPermission("MININGPASS." + pass + ".CLAIMED." + level)) {
            player.sendMessage(ChatColor.RED + "You already claimed this reward!");
            soundManager.soundNo(player, 1);
            return false;
        } else {
            return true;
        }
    }

    private String parsePlaceholder(Player player, String string) {
        return PlaceholderAPI.setPlaceholders(player, "%" + string + "%");
    }

    public String getProgressBar(int current, int max, int totalBars, char symbol, ChatColor completedColor,
                                 ChatColor notCompletedColor) {
        float percent = (float) current / max;
        int progressBars = (int) (totalBars * percent);

        return Strings.repeat("" + ChatColor.YELLOW + completedColor + symbol, progressBars)
                + Strings.repeat("" + ChatColor.WHITE + notCompletedColor + symbol, totalBars - progressBars);
    }

}

