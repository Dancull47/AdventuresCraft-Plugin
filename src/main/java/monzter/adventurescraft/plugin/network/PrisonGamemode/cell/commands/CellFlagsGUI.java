package monzter.adventurescraft.plugin.network.PrisonGamemode.cell.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Dependency;
import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.PaginatedPane;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import dev.dbassett.skullcreator.SkullCreator;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import world.bentobox.bentobox.BentoBox;
import world.bentobox.bentobox.database.objects.Island;
import world.bentobox.bentobox.managers.RanksManager;

import java.util.ArrayList;
import java.util.List;

public class CellFlagsGUI extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final SoundManager soundManager;
    private final BentoBox bentoBox;

    private final String prefix = ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "» ";

    public CellFlagsGUI(AdventuresCraft plugin, SoundManager soundManager, BentoBox bentoBox) {
        this.plugin = plugin;
        this.soundManager = soundManager;
        this.bentoBox = bentoBox;
    }

    private final ItemStack backgroundItem = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
    private final ItemMeta backgroundItemMeta = backgroundItem.getItemMeta();
    private final ItemStack previousPageItem = new ItemStack(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODY1MmUyYjkzNmNhODAyNmJkMjg2NTFkN2M5ZjI4MTlkMmU5MjM2OTc3MzRkMThkZmRiMTM1NTBmOGZkYWQ1ZiJ9fX0="));
    private final ItemMeta previousPageItemMeta = previousPageItem.getItemMeta();
    private final ItemStack nextPageItem = new ItemStack(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmEzYjhmNjgxZGFhZDhiZjQzNmNhZThkYTNmZTgxMzFmNjJhMTYyYWI4MWFmNjM5YzNlMDY0NGFhNmFiYWMyZiJ9fX0="));
    private final ItemMeta nextPageItemMeta = nextPageItem.getItemMeta();

    private final ItemStack backButton = new ItemStack(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODY1MmUyYjkzNmNhODAyNmJkMjg2NTFkN2M5ZjI4MTlkMmU5MjM2OTc3MzRkMThkZmRiMTM1NTBmOGZkYWQ1ZiJ9fX0="));
    private final ItemMeta backButtonItemMeta = backButton.getItemMeta();

    @CommandAlias("cellSettings")
    public void cellFlagsGUI(Player player) {
        Island island = bentoBox.getIslands().getIsland(Bukkit.getWorld("Cell_world"), player.getUniqueId());
        if (island.getRank(player.getUniqueId()) >= RanksManager.OWNER_RANK) {
            backgroundItemMeta.displayName(Component.text(" "));
            previousPageItemMeta.displayName(Component.text(ChatColor.GREEN + "Previous Page"));
            nextPageItemMeta.displayName(Component.text(ChatColor.GREEN + "Next Page"));
            backgroundItem.setItemMeta(backgroundItemMeta);

            backButtonItemMeta.displayName(Component.text(ChatColor.GREEN + "Go Back"));
            backButton.setItemMeta(backButtonItemMeta);

            ChestGui gui = new ChestGui(6, "Cell Settings");
            gui.setOnGlobalClick(event -> event.setCancelled(true));

            PaginatedPane page = new PaginatedPane(0, 0, 9, 6);
            OutlinePane background = new OutlinePane(0, 0, 9, 6, Pane.Priority.LOWEST);
            OutlinePane display = new OutlinePane(1, 1, 7, 4, Pane.Priority.LOW);
            StaticPane back = new StaticPane(4, 5, 1, 1);

            page.addPane(0, background);
            page.addPane(0, display);
            page.addPane(0, back);


            background.addItem(new GuiItem(backgroundItem));
            background.setRepeat(true);

            back.addItem(new GuiItem(backButton, e -> player.performCommand("CellMenu")), 0, 0);
/*
  #   VISITOR   = 0
  #   COOP      = 200
  #   TRUSTED   = 400
  #   MEMBER    = 500
  #   SUB-OWNER = 900
  #   OWNER     = 1000
*/
            for (CellFlagsGUIItems item : CellFlagsGUIItems.values()) {
                final ItemStack itemStack = new ItemStack(item.getMaterial());
                final ItemMeta itemMeta = itemStack.getItemMeta();

                itemMeta.setDisplayName(item.getName());

                List<String> lore = new ArrayList<>();
                lore.add("");
                String[] lines = item.getInfo().split("&n ");
                if (lines.length > 1) {
                    for (String line : lines) {
                        lore.add(ChatColor.GRAY + ChatColor.translateAlternateColorCodes('&', line));
                    }
                } else {
                    lore.add(ChatColor.GRAY + item.getInfo());
                }
                lore.add("");
                lore.add(ChatColor.GRAY + "Allowed For:");
                switch (Integer.valueOf(island.getFlag(item.getFlag()))) {
                    case 1000:
                        lore.add(prefix + ChatColor.RED + "Visitor");
                        lore.add(prefix + ChatColor.RED + "Coop");
                        lore.add(prefix + ChatColor.RED + "Trusted");
                        lore.add(prefix + ChatColor.RED + "Member");
                        lore.add(prefix + ChatColor.RED + "Sub-Owner");
                        lore.add(prefix + ChatColor.DARK_GREEN + "Owner");
                        break;
                    case 900:
                        lore.add(prefix + ChatColor.RED + "Visitor");
                        lore.add(prefix + ChatColor.RED + "Coop");
                        lore.add(prefix + ChatColor.RED + "Trusted");
                        lore.add(prefix + ChatColor.RED + "Member");
                        lore.add(prefix + ChatColor.DARK_GREEN + "Sub-Owner");
                        lore.add(prefix + ChatColor.GREEN + "Owner");
                        break;
                    case 500:
                        lore.add(prefix + ChatColor.RED + "Visitor");
                        lore.add(prefix + ChatColor.RED + "Coop");
                        lore.add(prefix + ChatColor.RED + "Trusted");
                        lore.add(prefix + ChatColor.DARK_GREEN + "Member");
                        lore.add(prefix + ChatColor.GREEN + "Sub-Owner");
                        lore.add(prefix + ChatColor.GREEN + "Owner");
                        break;
                    case 400:
                        lore.add(prefix + ChatColor.RED + "Visitor");
                        lore.add(prefix + ChatColor.RED + "Coop");
                        lore.add(prefix + ChatColor.DARK_GREEN + "Trusted");
                        lore.add(prefix + ChatColor.GREEN + "Member");
                        lore.add(prefix + ChatColor.GREEN + "Sub-Owner");
                        lore.add(prefix + ChatColor.GREEN + "Owner");
                        break;
                    case 200:
                        lore.add(prefix + ChatColor.RED + "Visitor");
                        lore.add(prefix + ChatColor.DARK_GREEN + "Coop");
                        lore.add(prefix + ChatColor.GREEN + "Trusted");
                        lore.add(prefix + ChatColor.GREEN + "Member");
                        lore.add(prefix + ChatColor.GREEN + "Sub-Owner");
                        lore.add(prefix + ChatColor.GREEN + "Owner");
                        break;
                    case 0:
                        lore.add(prefix + ChatColor.DARK_GREEN + "Visitor");
                        lore.add(prefix + ChatColor.GREEN + "Coop");
                        lore.add(prefix + ChatColor.GREEN + "Trusted");
                        lore.add(prefix + ChatColor.GREEN + "Member");
                        lore.add(prefix + ChatColor.GREEN + "Sub-Owner");
                        lore.add(prefix + ChatColor.GREEN + "Owner");
                        break;


                }
                lore.add("");
                lore.add(prefix + ChatColor.YELLOW + "Left-Click to Decrease Allowance");
                lore.add(prefix + ChatColor.YELLOW + "Right-Click to Increase Allowance");

                itemStack.setItemMeta(itemMeta);
                itemStack.setLore(lore);

                display.addItem(new GuiItem((itemStack), event -> {
                    if (event.getClick().isLeftClick()) {
                        switch (Integer.valueOf(island.getFlag(item.getFlag()))) {
                            case 0:
                                island.setFlag(item.getFlag(), 200);
                                cellFlagsGUI(player);
                                soundManager.playSound(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 2);
                                break;
                            case 200:
                                island.setFlag(item.getFlag(), 400);
                                cellFlagsGUI(player);
                                soundManager.playSound(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1.5f);
                                break;
                            case 400:
                                island.setFlag(item.getFlag(), 500);
                                cellFlagsGUI(player);
                                soundManager.playSound(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
                                break;
                            case 500:
                                island.setFlag(item.getFlag(), 900);
                                cellFlagsGUI(player);
                                soundManager.playSound(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, .5f);
                                break;
                            case 900:
                                island.setFlag(item.getFlag(), 1000);
                                cellFlagsGUI(player);
                                soundManager.playSound(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, -1);
                                break;
                        }
                    } else if (event.getClick().isRightClick()) {
                        switch (Integer.valueOf(island.getFlag(item.getFlag()))) {
                            case 200:
                                island.setFlag(item.getFlag(), 0);
                                cellFlagsGUI(player);
                                soundManager.playSound(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 2);
                                break;
                            case 400:
                                island.setFlag(item.getFlag(), 200);
                                cellFlagsGUI(player);
                                soundManager.playSound(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1.5f);
                                break;
                            case 500:
                                island.setFlag(item.getFlag(), 400);
                                cellFlagsGUI(player);
                                soundManager.playSound(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
                                break;
                            case 900:
                                island.setFlag(item.getFlag(), 500);
                                cellFlagsGUI(player);
                                soundManager.playSound(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, .5f);
                                break;
                            case 1000:
                                island.setFlag(item.getFlag(), 900);
                                cellFlagsGUI(player);
                                soundManager.playSound(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, -1);
                                break;
                        }
                    }
                }));
                gui.addPane(page);
            }
            gui.show(player);
        }
    }
}

