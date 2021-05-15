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
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.shared.GUIs.mainMenu.donation.miningPass.MiningPassLevels;
import monzter.adventurescraft.plugin.shared.events.extras.DonationRewardList;
import monzter.adventurescraft.plugin.utilities.GUI.GUIHelper;
import monzter.adventurescraft.plugin.utilities.enums.Prefix;
import monzter.adventurescraft.plugin.utilities.enums.StatsDisplay;
import monzter.adventurescraft.plugin.utilities.general.ConsoleCommand;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import monzter.adventurescraft.plugin.utilities.text.NumberFormat;
import net.Indyuce.mmoitems.MMOItems;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Material;
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
    private final String REWARD = "  " + ChatColor.GOLD + ChatColor.BOLD + "REWARDS" + ChatColor.WHITE + ":";

    public MiningPass(AdventuresCraft plugin, SoundManager soundManager, GUIHelper guiHelper, ConsoleCommand consoleCommand, NumberFormat numberFormat) {
        this.plugin = plugin;
        this.soundManager = soundManager;
        this.guiHelper = guiHelper;
        this.consoleCommand = consoleCommand;
        this.numberFormat = numberFormat;
    }

    @CommandAlias("miningPassMenus")
    public void donate(Player player) {

        ChestGui gui = new ChestGui(6, guiHelper.guiName("Adventure Store"));
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        PaginatedPane page = new PaginatedPane(0, 0, 9, 6);
        OutlinePane background = new OutlinePane(0, 0, 9, 6, Pane.Priority.LOWEST);
        OutlinePane freeDisplayP1 = new OutlinePane(0, 2, 9, 1, Pane.Priority.LOW);
        OutlinePane freeDisplayP2 = new OutlinePane(0, 2, 9, 1, Pane.Priority.LOW);
        OutlinePane freeDisplayP3 = new OutlinePane(0, 2, 9, 1, Pane.Priority.LOW);
        OutlinePane freeDisplayP4 = new OutlinePane(0, 2, 9, 1, Pane.Priority.LOW);
        StaticPane firstPage = new StaticPane(0, 5, 1, 1, Pane.Priority.HIGH);
        StaticPane back = new StaticPane(1, 5, 1, 1, Pane.Priority.HIGH);
        StaticPane backButton = new StaticPane(4, 5, 1, 1, Pane.Priority.HIGHEST);
        StaticPane forward = new StaticPane(7, 5, 1, 1, Pane.Priority.HIGH);
        StaticPane lastPage = new StaticPane(8, 5, 1, 1, Pane.Priority.HIGH);

        page.addPane(0, background);
        page.addPane(0, freeDisplayP1);
        page.addPane(1, background);
        page.addPane(1, freeDisplayP2);
        page.addPane(2, background);
        page.addPane(2, freeDisplayP3);
        page.addPane(3, background);
        page.addPane(3, freeDisplayP4);

        background.addItem(new GuiItem(guiHelper.background(Material.YELLOW_STAINED_GLASS_PANE)));
        background.setRepeat(true);

        backButton.addItem(new GuiItem(guiHelper.backButton(), e -> player.performCommand("donationMenu")), 0, 0);

        int i = 1;
        int amount = 0;
        for (MiningPassLevels reward : MiningPassLevels.values()) {

            ItemStack itemStack = new ItemStack(Material.TNT_MINECART);
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
                freeDisplayP1.addItem(new GuiItem(itemStack, e -> player.performCommand("/MiningPassReward " + reward.getName())));
            } else if (i > 9 && i < 18){
                freeDisplayP2.addItem(new GuiItem(itemStack, e -> player.performCommand("/MiningPassReward " + reward.getName())));
            } else if (i -1 > 18 && i -1 < 27){
                freeDisplayP3.addItem(new GuiItem(itemStack, e -> player.performCommand("/MiningPassReward " + reward.getName())));
            } else if (i -1 > 27 && i -1 < 36){
                freeDisplayP4.addItem(new GuiItem(itemStack, e -> player.performCommand("/MiningPassReward " + reward.getName())));
            }
            i++;

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
                page.setPage(page.getPages()-1);
                if (page.getPage() == page.getPages()-1) {
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


}

