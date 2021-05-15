package monzter.adventurescraft.plugin.shared.GUIs.mainMenu.donation;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Dependency;
import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.PaginatedPane;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import io.lumine.mythic.lib.api.util.SmartGive;
import me.clip.placeholderapi.PlaceholderAPI;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.shared.GUIs.mainMenu.donation.miningPass.MiningPassLevels;
import monzter.adventurescraft.plugin.shared.GUIs.mainMenu.donation.miningPass.PremiumMiningPassLevels;
import monzter.adventurescraft.plugin.utilities.GUI.GUIHelper;
import monzter.adventurescraft.plugin.utilities.enums.Prefix;
import monzter.adventurescraft.plugin.utilities.general.ConsoleCommand;
import monzter.adventurescraft.plugin.utilities.general.FullInventory;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import monzter.adventurescraft.plugin.utilities.luckperms.PermissionLP;
import monzter.adventurescraft.plugin.utilities.text.NumberFormat;
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
    private final String REWARD = "  " + ChatColor.GOLD + ChatColor.BOLD + "REWARDS" + ChatColor.WHITE + ":";

    public MiningPass(AdventuresCraft plugin, SoundManager soundManager, GUIHelper guiHelper, ConsoleCommand consoleCommand, NumberFormat numberFormat, FullInventory fullInventory, PermissionLP permissionLP) {
        this.plugin = plugin;
        this.soundManager = soundManager;
        this.guiHelper = guiHelper;
        this.consoleCommand = consoleCommand;
        this.numberFormat = numberFormat;
        this.fullInventory = fullInventory;
        this.permissionLP = permissionLP;
    }

    @CommandAlias("miningPassMenus")
    public void donate(Player player) {
        int miningPassEXP = Integer.valueOf(parsePlaceholder(player, "ac_Stat_MiningPassEXPAmount"));

        ChestGui gui = new ChestGui(6, guiHelper.guiName("Adventure Store"));
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        PaginatedPane page = new PaginatedPane(0, 0, 9, 6);
        OutlinePane background = new OutlinePane(0, 0, 9, 6, Pane.Priority.LOWEST);
        OutlinePane freeDisplayP1 = new OutlinePane(0, 2, 9, 1, Pane.Priority.LOW);
        OutlinePane freeDisplayP2 = new OutlinePane(0, 2, 9, 1, Pane.Priority.LOW);
        OutlinePane freeDisplayP3 = new OutlinePane(0, 2, 9, 1, Pane.Priority.LOW);
        OutlinePane freeDisplayP4 = new OutlinePane(0, 2, 9, 1, Pane.Priority.LOW);
        OutlinePane premiumDisplayP1 = new OutlinePane(0, 3, 9, 1, Pane.Priority.LOW);
        OutlinePane premiumDisplayP2 = new OutlinePane(0, 3, 9, 1, Pane.Priority.LOW);
        OutlinePane premiumDisplayP3 = new OutlinePane(0, 3, 9, 1, Pane.Priority.LOW);
        OutlinePane premiumDisplayP4 = new OutlinePane(0, 3, 9, 1, Pane.Priority.LOW);
        StaticPane firstPage = new StaticPane(0, 5, 1, 1, Pane.Priority.HIGH);
        StaticPane back = new StaticPane(1, 5, 1, 1, Pane.Priority.HIGH);
        StaticPane backButton = new StaticPane(4, 5, 1, 1, Pane.Priority.HIGHEST);
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

        background.addItem(new GuiItem(guiHelper.background(Material.YELLOW_STAINED_GLASS_PANE)));
        background.setRepeat(true);

        backButton.addItem(new GuiItem(guiHelper.backButton(), e -> player.performCommand("donationMenu")), 0, 0);

        int i = 1;
        int amount = 0;
        for (MiningPassLevels reward : MiningPassLevels.values()) {
            ItemStack itemStack = new ItemStack(Material.MINECART);
            if (miningPassEXP >= reward.getPrice())
                itemStack = new ItemStack(Material.TNT_MINECART);

            final ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName(ChatColor.YELLOW + "Mining Pass Level " + i);

            List<String> lore = new ArrayList<>();
            lore.add(" ");
            lore.add(REWARD);
            for (ItemStack itemStack1 : reward.getRewards()) {
                lore.add("   " + Prefix.PREFIX.getPrefix() + ChatColor.GOLD + reward.getRewardAmount()[amount] + "x " + itemStack1.getItemMeta().getDisplayName());
                amount++;
            }
            amount = 0;
            itemMeta.setLore(lore);
            itemStack.setItemMeta(itemMeta);

            if (i < 10) {
                freeDisplayP1.addItem(new GuiItem(itemStack, e -> player.performCommand("/MiningPassReward " + reward.getLevel())));
            } else if (i > 9 && i < 18) {
                freeDisplayP2.addItem(new GuiItem(itemStack, e -> player.performCommand("/MiningPassReward " + reward.getLevel())));
            } else if (i - 1 > 18 && i - 1 < 27) {
                freeDisplayP3.addItem(new GuiItem(itemStack, e -> player.performCommand("/MiningPassReward " + reward.getLevel())));
            } else if (i - 1 > 27 && i - 1 < 36) {
                freeDisplayP4.addItem(new GuiItem(itemStack, e -> player.performCommand("/MiningPassReward " + reward.getLevel())));
            }
            i++;

        }

        int i2 = 1;
        int amount2 = 0;
        for (PremiumMiningPassLevels premiumReward : PremiumMiningPassLevels.values()) {
            ItemStack itemStack = new ItemStack(Material.MINECART);

            if (miningPassEXP >= premiumReward.getPrice())
                itemStack = new ItemStack(Material.HOPPER_MINECART);

            final ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName(ChatColor.YELLOW + "Premium Mining Pass Level " + i2);

            List<String> lore = new ArrayList<>();
            lore.add(" ");
            lore.add(REWARD);
            for (ItemStack itemStack1 : premiumReward.getRewards()) {
                lore.add("   " + Prefix.PREFIX.getPrefix() + ChatColor.GOLD + premiumReward.getRewardAmount()[amount2] + "x " + itemStack1.getItemMeta().getDisplayName());
                amount2++;
            }
            amount2 = 0;
            itemMeta.setLore(lore);
            itemStack.setItemMeta(itemMeta);

            if (i2 < 10) {
                premiumDisplayP1.addItem(new GuiItem(itemStack, e -> player.performCommand("/MiningPassReward p" + premiumReward.getLevel())));
            } else if (i2 > 9 && i2 < 18) {
                premiumDisplayP2.addItem(new GuiItem(itemStack, e -> player.performCommand("/MiningPassReward p" + premiumReward.getLevel())));
            } else if (i2 - 1 > 18 && i2 - 1 < 27) {
                premiumDisplayP3.addItem(new GuiItem(itemStack, e -> player.performCommand("/MiningPassReward p" + premiumReward.getLevel())));
            } else if (i2 - 1 > 27 && i2 - 1 < 36) {
                premiumDisplayP4.addItem(new GuiItem(itemStack, e -> player.performCommand("/MiningPassReward p" + premiumReward.getLevel())));
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
        gui.addPane(backButton);
        gui.addPane(page);
        gui.addPane(back);
        gui.addPane(firstPage);
        gui.addPane(lastPage);
        gui.addPane(forward);

        gui.show(player);
    }

    private String parsePlaceholder(Player player, String string) {
        return PlaceholderAPI.setPlaceholders(player, "%" + string + "%");
    }

    @CommandAlias("miningPassClaimReward")
    public void miningPassClaim(Player player, String reward) {
        int miningPassEXP = Integer.valueOf(parsePlaceholder(player, "ac_Stat_MiningPassEXPAmount"));

        int i = 0;
        for (MiningPassLevels miningPassReward : MiningPassLevels.values()) {
            if (String.valueOf(miningPassReward.getLevel()).equals(reward)) {
                if (levelCheck(player, miningPassEXP, miningPassReward.getPrice())) {
                    if (claimedCheck(player, miningPassReward.getLevel())) {
                        if (!fullInventory.fullInventory(player)) {
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

    private boolean levelCheck(Player player, int exp, int cost) {
        if (exp >= cost) {
            return true;
        } else {
            player.sendMessage(ChatColor.RED + "You only have " + ChatColor.DARK_RED + exp + ChatColor.RED + "/" + ChatColor.DARK_RED + cost + ChatColor.RED + "!");
            soundManager.soundNo(player, 1);
            return false;
        }
    }

    private boolean claimedCheck(Player player, String level) {
        if (player.hasPermission("MININGPASS.REWARD.CLAIMED." + level)) {
            player.sendMessage(ChatColor.RED + "You already claimed this reward!");
            soundManager.soundNo(player, 1);
            return false;
        } else {
            return true;
        }
    }
}

