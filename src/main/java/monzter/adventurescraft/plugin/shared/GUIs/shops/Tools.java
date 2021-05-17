package monzter.adventurescraft.plugin.shared.GUIs.shops;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Dependency;
import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.PaginatedPane;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import me.clip.placeholderapi.PlaceholderAPI;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.GUI.GUIHelper;
import monzter.adventurescraft.plugin.utilities.enums.Prefix;
import monzter.adventurescraft.plugin.utilities.general.ConsoleCommand;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import monzter.adventurescraft.plugin.utilities.vault.Economy;
import net.Indyuce.mmoitems.MMOItems;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Tools extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final SoundManager soundManager;
    private final GUIHelper guiHelper;
    private final ConsoleCommand consoleCommand;
    private final Economy economy;

    public Tools(AdventuresCraft plugin, SoundManager soundManager, GUIHelper guiHelper, ConsoleCommand consoleCommand, Economy economy) {
        this.plugin = plugin;
        this.soundManager = soundManager;
        this.guiHelper = guiHelper;
        this.consoleCommand = consoleCommand;
        this.economy = economy;
    }

    String rarity;

    @CommandAlias("toolShop")
    public void pets(Player player) {
        final int petAmount = Integer.valueOf(parsePlaceholder(player, "ac_Stat_PetAmount"));
        ChestGui gui = new ChestGui(4, guiHelper.guiName("Pets"));
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        PaginatedPane page = new PaginatedPane(0, 0, 9, 4);
        OutlinePane background = new OutlinePane(0, 0, 9, 4, Pane.Priority.LOWEST);
        OutlinePane display = new OutlinePane(2, 1, 4, 1, Pane.Priority.LOW);
        StaticPane emptySlot = new StaticPane(2, 1, 5, 1, Pane.Priority.HIGHEST);
        StaticPane backButton = new StaticPane(4, 3, 1, 1, Pane.Priority.HIGHEST);

        page.addPane(0, background);
        page.addPane(0, emptySlot);
        page.addPane(0, display);
        page.addPane(0, backButton);

        background.addItem(new GuiItem(guiHelper.background(Material.GREEN_STAINED_GLASS_PANE)));
        background.setRepeat(true);
        plugin.getLogger().info(String.valueOf(petAmount));


        if (petAmount < 1)
            emptySlot.addItem(new GuiItem(empty(player)), 0, 0);
        if (petAmount < 2)
            emptySlot.addItem(new GuiItem(empty(player)), 1, 0);
        if (petAmount < 3)
            emptySlot.addItem(new GuiItem(locked(player)), 2, 0);
        if (petAmount < 4)
            emptySlot.addItem(new GuiItem(locked(player)), 3, 0);
        if (petAmount < 5)
            emptySlot.addItem(new GuiItem(locked(player)), 4, 0);
        if (player.hasPermission("DONATE.PET.SLOT.1"))
            emptySlot.addItem(new GuiItem(empty(player)), 2, 0);
        if (player.hasPermission("DONATE.PET.SLOT.2"))
            emptySlot.addItem(new GuiItem(empty(player)), 3, 0);
        if (player.hasPermission("DONATE.PET.SLOT.2"))
            emptySlot.addItem(new GuiItem(empty(player)), 4, 0);

            for (ToolList tool : ToolList.values()) {
                    ItemStack pet = MMOItems.plugin.getItem("TOOL", tool.getId());
                    final ItemMeta petItemMeta = pet.getItemMeta();

                    if (pet != null) {
                        List<Component> lore = pet.lore();
                        if (lore == null) {
                            lore = new ArrayList<>();
                        } else if (!lore.isEmpty()) {
                            lore.add(Component.empty());
                        }
                        if (economy.getBalance(player) >= tool.getPrice()) {
                        lore.add(Component.text(Prefix.PREFIX.getPrefix() + ChatColor.YELLOW + "Left-Click to Purchase 1"));
                    }
                        petItemMeta.lore(lore);
                        pet.setItemMeta(petItemMeta);
                    display.addItem(new GuiItem(pet, e -> {
                        player.performCommand("petUnequip " + "PET." + currentPetName + "." + currentRarity + " " + currentPetName + rarity);
                        pets(player);
                    }));
                }
            }
        }
        backButton.addItem(new GuiItem(guiHelper.backButton(), e -> player.performCommand("main")), 0, 0);

        gui.addPane(background);
        gui.addPane(backButton);
        gui.addPane(emptySlot);
        gui.addPane(display);
        gui.show(player);
    }

    private ItemStack empty(Player player) {
        final ItemStack empty = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        final ItemMeta emptyItemMeta = empty.getItemMeta();

        emptyItemMeta.displayName(Component.text(ChatColor.RED + "Empty Pet Slot"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "This slot can be filled with a " + ChatColor.GREEN + "Pet" + ChatColor.GRAY + "!");
        lore.add("");
        lore.add(ChatColor.GRAY + "You can obtain " + ChatColor.GREEN + "Pets " + ChatColor.GRAY + "by");
        lore.add(ChatColor.GREEN + "Hatching " + ChatColor.GRAY + "them with " + ChatColor.GREEN + "Jenny" + ChatColor.GRAY + "!");

        empty.setItemMeta(emptyItemMeta);
        empty.setLore(lore);

        return empty;
    }

    private ItemStack locked(Player player) {
        final ItemStack locked = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        final ItemMeta lockedItemMeta = locked.getItemMeta();

        lockedItemMeta.displayName(Component.text(ChatColor.RED + "Locked Pet Slot"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "This slot can be " + ChatColor.GREEN + "unlocked" + ChatColor.GRAY + "!");

        locked.setItemMeta(lockedItemMeta);
        locked.setLore(lore);

        return locked;
    }

    private String parsePlaceholder(Player player, String string) {
        return PlaceholderAPI.setPlaceholders(player, "%" + string + "%");
    }

}


