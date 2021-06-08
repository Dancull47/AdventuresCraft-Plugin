package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.mainMenu.resourceCollector;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Dependency;
import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.PaginatedPane;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import io.lumine.mythic.lib.api.item.NBTItem;
import io.lumine.mythic.utils.Schedulers;
import me.clip.placeholderapi.PlaceholderAPI;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.Events.Pickup;
import monzter.adventurescraft.plugin.utilities.GUI.GUIHelper;
import monzter.adventurescraft.plugin.utilities.beton.BetonPointsManager;
import monzter.adventurescraft.plugin.utilities.enums.Prefix;
import monzter.adventurescraft.plugin.utilities.general.ConsoleCommand;
import monzter.adventurescraft.plugin.utilities.general.ItemAdder;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import monzter.adventurescraft.plugin.utilities.luckperms.PermissionLP;
import net.Indyuce.mmoitems.MMOItems;
import org.apache.commons.lang.WordUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Slayer extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final SoundManager soundManager;
    private final GUIHelper guiHelper;
    private final ConsoleCommand consoleCommand;
    private final Pickup pickup;
    private final BetonPointsManager betonPointsManager;
    private final PermissionLP permissionLP;
    private final MMOItems mmoItems;
    private final ItemAdder itemAdder;

    public final List<ItemStack> SLAYER_MATERIALS;

    public Slayer(AdventuresCraft plugin, SoundManager soundManager, GUIHelper guiHelper, ConsoleCommand consoleCommand, Pickup pickup, BetonPointsManager betonPointsManager, PermissionLP permissionLP, MMOItems mmoItems, ItemAdder itemAdder) {
        this.plugin = plugin;
        this.soundManager = soundManager;
        this.guiHelper = guiHelper;
        this.consoleCommand = consoleCommand;
        this.pickup = pickup;
        this.betonPointsManager = betonPointsManager;
        this.permissionLP = permissionLP;
        this.mmoItems = mmoItems;
        this.itemAdder = itemAdder;
        SLAYER_MATERIALS = Arrays.asList(new ItemStack(Material.ROTTEN_FLESH), new ItemStack(Material.BONE), mmoItems.getItem("MATERIAL", "BONE_FRAGMENT"), new ItemStack(Material.ARROW), new ItemStack(Material.SPIDER_EYE), new ItemStack(Material.STRING),
                new ItemStack(Material.GUNPOWDER), new ItemStack(Material.SLIME_BALL), new ItemStack(Material.ENDER_PEARL), new ItemStack(Material.GHAST_TEAR), new ItemStack(Material.MAGMA_CREAM), new ItemStack(Material.BLAZE_ROD));
    }


    @CommandAlias("SlayerResources")
    private void createMenu(Player player) {
        final ChestGui gui = new ChestGui(5, "Slayer Resources");

        gui.setOnGlobalClick(event -> event.setCancelled(true));

        PaginatedPane page = new PaginatedPane(0, 0, 9, 5);
        OutlinePane background = new OutlinePane(0, 0, 9, 5, Pane.Priority.LOWEST);
        OutlinePane display = new OutlinePane(1, 1, 7, 4, Pane.Priority.LOW);
        StaticPane backButton = new StaticPane(4, 4, 1, 1, Pane.Priority.HIGHEST);

        background.addItem(new GuiItem(guiHelper.background(Material.RED_STAINED_GLASS_PANE)));
        background.setRepeat(true);

        page.addPane(0, background);
        page.addPane(0, display);

        int i = 0;

        for (ItemStack item : SLAYER_MATERIALS) {
            if (i < 28) {
                display.addItem(itemCreator(player, new ItemStack(item)));
                i++;
            }
        }

        backButton.addItem(new GuiItem(guiHelper.backButton(), e -> player.performCommand("main")), 0, 0);

        gui.addPane(page);
        gui.addPane(backButton);
        gui.show(player);
    }

    private GuiItem itemCreator(Player player, ItemStack itemStack) {
        final ItemStack forest = new ItemStack(itemStack);
        final ItemMeta forestItemMeta = forest.getItemMeta();
        int amount;
        String permission;

        String mmoItemID = mmoItems.getID(NBTItem.get(itemStack));
        if (mmoItemID == null) {
            permission = itemStack.getType().toString() + ".COLLECT";
            amount = Integer.valueOf(parsePlaceholder(player, "betonquest_items:point." + itemStack.getType().toString() + ".amount"));
            if (player.hasPermission(permission))
                forestItemMeta.setDisplayName((ChatColor.GREEN + WordUtils.capitalizeFully(itemStack.getType().toString().replace("_", " ")) + ChatColor.DARK_GRAY + " - " + ChatColor.GREEN + "ON"));
            else
                forestItemMeta.setDisplayName((ChatColor.GREEN + WordUtils.capitalizeFully(itemStack.getType().toString().replace("_", " ")) + ChatColor.DARK_GRAY + " - " + ChatColor.RED + "OFF"));
        } else {
            permission = mmoItemID + ".COLLECT";
            amount = Integer.valueOf(parsePlaceholder(player, "betonquest_items:point." + mmoItemID + ".amount"));
            if (player.hasPermission(permission))
                forestItemMeta.setDisplayName((ChatColor.GREEN + itemStack.getItemMeta().getDisplayName() + ChatColor.DARK_GRAY + " - " + ChatColor.GREEN + "ON"));
            else
                forestItemMeta.setDisplayName((ChatColor.GREEN + itemStack.getItemMeta().getDisplayName() + ChatColor.DARK_GRAY + " - " + ChatColor.RED + "OFF"));
        }
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "You have " + ChatColor.GOLD + amount + ChatColor.GRAY + " stored!");
        lore.add("");
        if (amount >= 1)
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Left-Click to Withdraw 1");
        if (amount >= 64)
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Right-Click to Withdraw 64");
        if (amount >= 512)
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Shift-Right-Click to Withdraw 512");
        if (player.hasPermission(permission))
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Shift-Left-Click to " + ChatColor.RED + "Disable Auto-Pickup");
        else
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Shift-Left-Click to " + ChatColor.GREEN + "Enable Auto-Pickup");

        forest.setItemMeta(forestItemMeta);
        forest.setLore(lore);

        return new GuiItem(forest, e -> {
            if (e.isShiftClick() && e.isLeftClick() && player.hasPermission(permission))
                permissionLP.takePermission(player, permission);
            else if (e.isShiftClick() && e.isLeftClick() && !player.hasPermission(permission))
                permissionLP.givePermission(player, permission);

            if (e.isShiftClick() && e.isRightClick() && amount >= 512)
                withdraw(player, itemStack, 512);

            if (!e.isShiftClick() && e.isLeftClick() && amount >= 1)
                withdraw(player, itemStack, 1);

            if (!e.isShiftClick() && e.isRightClick() && amount >= 64)
                withdraw(player, itemStack, 64);

            player.performCommand("SlayerResources");
        });
    }

    private void withdraw(Player player, ItemStack itemStack, int withdrawAmount) {
        int playerAmount;
        String mmoItemID = mmoItems.getID(NBTItem.get(itemStack));
        if (mmoItemID == null)
            playerAmount = Integer.valueOf(parsePlaceholder(player, "betonquest_items:point." + itemStack.getType().toString() + ".amount"));
        else
            playerAmount = Integer.valueOf(parsePlaceholder(player, "betonquest_items:point." + mmoItemID + ".amount"));

        if (playerAmount >= withdrawAmount) {
            if (mmoItemID == null) {
                betonPointsManager.takePoint(player, "items." + itemStack.getType().toString(), withdrawAmount);
            } else {
                betonPointsManager.takePoint(player, "items." + mmoItemID, withdrawAmount);
            }
            itemAdder.itemAdder(player, itemStack.asQuantity(withdrawAmount));

            Schedulers.sync().runLater(new BukkitRunnable() {
                @Override
                public void run() {
                    if (mmoItemID == null) {
                        player.sendMessage(ChatColor.GREEN + "You withdrew " + ChatColor.GOLD + withdrawAmount + " " + WordUtils.capitalizeFully(itemStack.getType().toString().replace("_", " ")) + ChatColor.GREEN + " and have " + ChatColor.YELLOW + Integer.valueOf(parsePlaceholder(player, "betonquest_items:point." + itemStack.getType().toString() + ".amount")) + ChatColor.GREEN + " left!");
                    } else {
                        player.sendMessage(ChatColor.GREEN + "You withdrew " + ChatColor.GOLD + withdrawAmount + " " + itemStack.getItemMeta().getDisplayName() + ChatColor.GREEN + " and have " + ChatColor.YELLOW + Integer.valueOf(parsePlaceholder(player, "betonquest_items:point." + mmoItemID + ".amount")) + ChatColor.GREEN + " left!");
                    }
                }
            }, 1);
        }
    }

    private String parsePlaceholder(Player player, String string) {
        return PlaceholderAPI.setPlaceholders(player, "%" + string + "%");
    }

}

