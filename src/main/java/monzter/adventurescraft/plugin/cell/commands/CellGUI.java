package monzter.adventurescraft.plugin.cell.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Dependency;
import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.PaginatedPane;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import dev.dbassett.skullcreator.SkullCreator;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.shared.dropTables.LootLlama;
import monzter.adventurescraft.plugin.utilities.bukkit.ConsoleCommand;
import monzter.adventurescraft.plugin.utilities.bukkit.SoundManager;
import monzter.adventurescraft.plugin.utilities.mmoitems.DropTablesDelivery;
import net.Indyuce.mmoitems.MMOItems;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import world.bentobox.bentobox.BentoBox;
import world.bentobox.bentobox.database.objects.Island;
import world.bentobox.bentobox.lists.Flags;

import java.util.ArrayList;
import java.util.List;

public class CellGUI extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final SoundManager soundManager;
    private final BentoBox bentoBox;

    public CellGUI(AdventuresCraft plugin, SoundManager soundManager, BentoBox bentoBox) {
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

    private final ItemStack anvil = new ItemStack(Material.ANVIL);

    @CommandAlias("CellGUI")
    public void hatchCommand(Player player) {
        Island island = bentoBox.getIslands().getIsland(Bukkit.getWorld("Cell_world"), player.getUniqueId());

        backgroundItemMeta.displayName(Component.text(" "));
        previousPageItemMeta.displayName(Component.text(ChatColor.GREEN + "Previous Page"));
        nextPageItemMeta.displayName(Component.text(ChatColor.GREEN + "Next Page"));
        backgroundItem.setItemMeta(backgroundItemMeta);

        ChestGui gui = new ChestGui(6, "Cell Settings");
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        PaginatedPane page = new PaginatedPane(0, 0, 9, 6);
        OutlinePane background = new OutlinePane(0, 0, 9, 6, Pane.Priority.LOWEST);
        OutlinePane display = new OutlinePane(1, 1, 7, 4, Pane.Priority.LOW);

        page.addPane(0, background);
        page.addPane(0, display);

        background.addItem(new GuiItem(backgroundItem));
        background.setRepeat(true);
/*
  #   VISITOR   = 0
  #   COOP      = 200
  #   TRUSTED   = 400
  #   MEMBER    = 500
  #   SUB-OWNER = 900
  #   OWNER     = 1000
*/
        for (CellGUIItems item : CellGUIItems.values()) {
            final ItemStack itemStack = new ItemStack(item.getMaterial());
            final ItemMeta itemMeta = itemStack.getItemMeta();

            itemMeta.setDisplayName(item.getName());

            List<String> lore = new ArrayList<>();
            lore.add("");
            lore.add(ChatColor.GRAY + item.getInfo());
            lore.add("");
            lore.add(ChatColor.GRAY + "Allowed For:");
            switch (Integer.valueOf(island.getFlag(item.getFlag()))) {
                case 1000:
                    lore.add(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "» " + ChatColor.RED + "Visitor");
                    lore.add(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "» " + ChatColor.RED + "Coop");
                    lore.add(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "» " + ChatColor.RED + "Trusted");
                    lore.add(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "» " + ChatColor.RED + "Member");
                    lore.add(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "» " + ChatColor.RED + "Sub-Owner");
                    lore.add(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "» " + ChatColor.DARK_GREEN + "Owner");
                    break;
                case 900:
                    lore.add(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "» " + ChatColor.RED + "Visitor");
                    lore.add(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "» " + ChatColor.RED + "Coop");
                    lore.add(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "» " + ChatColor.RED + "Trusted");
                    lore.add(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "» " + ChatColor.RED + "Member");
                    lore.add(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "» " + ChatColor.DARK_GREEN + "Sub-Owner");
                    lore.add(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "» " + ChatColor.GREEN + "Owner");
                    break;
                case 500:
                    lore.add(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "» " + ChatColor.RED + "Visitor");
                    lore.add(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "» " + ChatColor.RED + "Coop");
                    lore.add(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "» " + ChatColor.RED + "Trusted");
                    lore.add(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "» " + ChatColor.DARK_GREEN + "Member");
                    lore.add(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "» " + ChatColor.GREEN + "Sub-Owner");
                    lore.add(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "» " + ChatColor.GREEN + "Owner");
                    break;
                case 400:
                    lore.add(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "» " + ChatColor.RED + "Visitor");
                    lore.add(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "» " + ChatColor.RED + "Coop");
                    lore.add(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "» " + ChatColor.DARK_GREEN + "Trusted");
                    lore.add(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "» " + ChatColor.GREEN + "Member");
                    lore.add(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "» " + ChatColor.GREEN + "Sub-Owner");
                    lore.add(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "» " + ChatColor.GREEN + "Owner");
                    break;
                case 200:
                    lore.add(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "» " + ChatColor.RED + "Visitor");
                    lore.add(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "» " + ChatColor.DARK_GREEN + "Coop");
                    lore.add(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "» " + ChatColor.GREEN + "Trusted");
                    lore.add(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "» " + ChatColor.GREEN + "Member");
                    lore.add(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "» " + ChatColor.GREEN + "Sub-Owner");
                    lore.add(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "» " + ChatColor.GREEN + "Owner");
                    break;
                case 0:
                    lore.add(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "» " + ChatColor.DARK_GREEN + "Visitor");
                    lore.add(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "» " + ChatColor.GREEN + "Coop");
                    lore.add(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "» " + ChatColor.GREEN + "Trusted");
                    lore.add(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "» " + ChatColor.GREEN + "Member");
                    lore.add(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "» " + ChatColor.GREEN + "Sub-Owner");
                    lore.add(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "» " + ChatColor.GREEN + "Owner");
                    break;


            }
            lore.add("");
            lore.add(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "» " + ChatColor.YELLOW + "Left-Click to Decrease Allowance");
            lore.add(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "» " + ChatColor.YELLOW + "Right-Click to Increase Allowance");

            itemStack.setItemMeta(itemMeta);
            itemStack.setLore(lore);

            display.addItem(new GuiItem((itemStack), event -> {
                if (event.getClick().isLeftClick()) {
                    switch (Integer.valueOf(island.getFlag(item.getFlag()))) {
                        case 0:
                            island.setFlag(item.getFlag(), 200);
                            hatchCommand(player);
                            break;
                        case 200:
                            island.setFlag(item.getFlag(), 400);
                            hatchCommand(player);
                            break;
                        case 400:
                            island.setFlag(item.getFlag(), 500);
                            hatchCommand(player);
                            break;
                        case 500:
                            island.setFlag(item.getFlag(), 900);
                            hatchCommand(player);
                            break;
                        case 900:
                            island.setFlag(item.getFlag(), 1000);
                            hatchCommand(player);
                            break;
                    }
                } else if (event.getClick().isRightClick()) {
                    switch (Integer.valueOf(island.getFlag(item.getFlag()))) {
                        case 200:
                            island.setFlag(item.getFlag(), 0);
                            hatchCommand(player);
                            break;
                        case 400:
                            island.setFlag(item.getFlag(), 200);
                            hatchCommand(player);
                            break;
                        case 500:
                            island.setFlag(item.getFlag(), 400);
                            hatchCommand(player);
                            break;
                        case 900:
                            island.setFlag(item.getFlag(), 500);
                            hatchCommand(player);
                            break;
                        case 1000:
                            island.setFlag(item.getFlag(), 900);
                            hatchCommand(player);
                            break;
                    }
                }
            }));
            gui.addPane(page);
            gui.show(player);
        }

    }


}

