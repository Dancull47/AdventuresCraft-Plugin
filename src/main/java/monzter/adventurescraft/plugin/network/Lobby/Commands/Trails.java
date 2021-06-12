package monzter.adventurescraft.plugin.network.Lobby.Commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.Dependency;
import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.PaginatedPane;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import io.lumine.xikage.mythicmobs.MythicMobs;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.GUI.GUIHelper;
import monzter.adventurescraft.plugin.utilities.enums.Prefix;
import monzter.adventurescraft.plugin.utilities.enums.Rarity;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import org.apache.commons.lang.WordUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Trails extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final SoundManager soundManager;
    private final GUIHelper guiHelper;

    public Trails(AdventuresCraft plugin, SoundManager soundManager, GUIHelper guiHelper) {
        this.plugin = plugin;
        this.soundManager = soundManager;
        this.guiHelper = guiHelper;
    }

    @CommandAlias("Trails")
    public void mainMenu(Player player) {
        mainMenu(player, "Common");
    }

    @CommandAlias("Trails")
    @CommandCompletion("Common|Uncommon|Rare|Legendary|Exotic")
    public void mainMenu(Player player, String rarity) {
        Rarity RARITY = Rarity.COMMON;
        if (rarity != null)
            switch (rarity.toLowerCase()) {
                case "common":
                    RARITY = Rarity.COMMON;
                    break;
                case "uncommon":
                    RARITY = Rarity.UNCOMMON;
                    break;
                case "rare":
                    RARITY = Rarity.RARE;
                    break;
                case "legendary":
                    RARITY = Rarity.LEGENDARY;
                    break;
                case "exotic":
                    RARITY = Rarity.EXOTIC;
                    break;
            }

        ChestGui gui = new ChestGui(6, guiHelper.guiName(WordUtils.capitalizeFully(RARITY.getName()) + " Trails"));
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        PaginatedPane page = new PaginatedPane(0, 0, 9, 6);
        OutlinePane background = new OutlinePane(0, 0, 9, 6, Pane.Priority.LOWEST);
        OutlinePane display = new OutlinePane(2, 1, 5, 6, Pane.Priority.LOW);
        OutlinePane display2 = new OutlinePane(2, 1, 5, 6, Pane.Priority.LOW);
        OutlinePane display3 = new OutlinePane(2, 1, 5, 6, Pane.Priority.LOW);
        StaticPane button = new StaticPane(0, 0, 9, 6, Pane.Priority.LOW);
        StaticPane back = new StaticPane(0, 5, 1, 1, Pane.Priority.HIGH);
        StaticPane forward = new StaticPane(8, 5, 1, 1, Pane.Priority.HIGH);
        StaticPane firstPage = new StaticPane(0, 5, 1, 1, Pane.Priority.HIGH);
        StaticPane lastPage = new StaticPane(8, 5, 1, 1, Pane.Priority.HIGH);

        page.addPane(0, background);
        page.addPane(0, display);
        page.addPane(0, button);
        page.addPane(1, background);
        page.addPane(1, display2);
        page.addPane(2, background);
        page.addPane(2, display3);

        background.addItem(new GuiItem(guiHelper.background(Material.ORANGE_STAINED_GLASS_PANE)));
        background.setRepeat(true);

        final List<TrailList> guiContents = TrailList.getTrail(RARITY);

        int i = 0;
        for (TrailList trail : guiContents) {
            if (i < 15) {
                display.addItem(trailGenerator(player, trail));
                i++;
            } else if (i >= 15 && i < 30) {
                display2.addItem(trailGenerator(player, trail));
            } else if (i >= 30 && i < 45) {
                display3.addItem(trailGenerator(player, trail));
            }
        }

        if (!display2.getItems().isEmpty()) {
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

        if (page.getPage() == 0) {
            back.setVisible(false);
            firstPage.setVisible(false);
            forward.setVisible(true);
            lastPage.setVisible(true);
        } else if (page.getPage() == 1 && !display3.getItems().isEmpty()) {
            back.setVisible(true);
            firstPage.setVisible(true);
            forward.setVisible(true);
            lastPage.setVisible(true);
        } else if (page.getPage() == 1 && display3.getItems().isEmpty()) {
            back.setVisible(true);
            firstPage.setVisible(true);
            forward.setVisible(false);
            lastPage.setVisible(false);
        }


        button.addItem(menuItem(player, Rarity.COMMON), 2, 5);
        button.addItem(menuItem(player, Rarity.UNCOMMON), 3, 5);
        button.addItem(menuItem(player, Rarity.RARE), 4, 5);
        button.addItem(menuItem(player, Rarity.LEGENDARY), 5, 5);
        button.addItem(menuItem(player, Rarity.EXOTIC), 6, 5);

        gui.addPane(firstPage);
        gui.addPane(lastPage);
        gui.addPane(page);
        gui.addPane(back);
        gui.addPane(forward);
        gui.addPane(button);
        gui.show(player);
    }


    private GuiItem trailGenerator(Player player, TrailList trailList) {
        String trail = trailList.getName();
        final ItemStack itemStack = MythicMobs.inst().getItemManager().getItemStack(trail);
        final ItemMeta itemMeta = itemStack.getItemMeta();

        List<String> lore = new ArrayList<>();
        for (String oldLore : MythicMobs.inst().getItemManager().getItemStack(trail).getLore())
            lore.add(oldLore);
        lore.add("");
        if (player.hasPermission(trail))
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Equip");
        else {
            lore.add(ChatColor.RED.toString() + ChatColor.BOLD + "LOCKED!");
            lore.add(ChatColor.WHITE + "Obtained From: " + trailList.getUnlockMethod());
        }


        itemStack.setItemMeta(itemMeta);
        itemStack.setLore(lore);

        return new GuiItem(itemStack, e -> {
            if (player.hasPermission(trail)) {
                player.getInventory().setLeggings(MythicMobs.inst().getItemManager().getItemStack(trail));
                player.sendMessage(ChatColor.GREEN + "You've equipped the " + MythicMobs.inst().getItemManager().getItemStack(trail).getItemMeta().getDisplayName() + ChatColor.GREEN + "!");
                soundManager.soundYes(player, 1);
            } else {
                player.sendMessage(ChatColor.RED + "You don't have the " + MythicMobs.inst().getItemManager().getItemStack(trail).getItemMeta().getDisplayName() + ChatColor.RED + " unlocked!");
                soundManager.soundNo(player, 1);
            }
        });
    }

    private GuiItem menuItem(Player player, Rarity rarity) {
        String trail = rarity.getColorString() + WordUtils.capitalizeFully(rarity.getName()) + " Trails";
        ItemStack itemStack = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
        switch (rarity) {
            case COMMON:
                itemStack = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
                break;
            case UNCOMMON:
                itemStack = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
                break;
            case RARE:
                itemStack = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);
                break;
            case LEGENDARY:
                itemStack = new ItemStack(Material.PURPLE_STAINED_GLASS_PANE);
                break;
            case EXOTIC:
                itemStack = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
                break;
        }

        final ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(trail);

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View");


        itemStack.setItemMeta(itemMeta);
        itemStack.setLore(lore);

        return new GuiItem(itemStack, e -> player.performCommand("trails " + rarity.getName()));
    }

}

