package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.mainMenu;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Dependency;
import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.GUI.GUIHelper;
import monzter.adventurescraft.plugin.utilities.enums.Prefix;
import monzter.adventurescraft.plugin.utilities.general.ConsoleCommand;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import monzter.adventurescraft.plugin.utilities.luckperms.PermissionLP;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Social extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final SoundManager soundManager;
    private final GUIHelper guiHelper;
    private final ConsoleCommand consoleCommand;
    private final PermissionLP permissionLP;

    final TextComponent friend = Component.text(ChatColor.GREEN + "Add other " + ChatColor.GOLD + "Adventurers " + ChatColor.GREEN + "you meet along")
            .color(NamedTextColor.GREEN)
            .append(Component.text(ChatColor.GREEN + " your journey to your  " + ChatColor.GOLD + "Friends List" + ChatColor.GREEN + "!"))
            .hoverEvent(Component.text("Click to use /Friend Add!", NamedTextColor.GREEN))
            .clickEvent(ClickEvent.suggestCommand("/Friend Add "));
    final TextComponent visit = Component.text("Use ")
            .color(NamedTextColor.GREEN)
            .append(Component.text("/Home Visit <Name>", NamedTextColor.GOLD))
            .append(Component.text(" to visit another " + ChatColor.GOLD + "Adventurer's Home" + ChatColor.GREEN +"!"))
            .hoverEvent(Component.text("Click to use /Home Visit!", NamedTextColor.GREEN))
            .clickEvent(ClickEvent.suggestCommand("/Home Visit "));


    public Social(AdventuresCraft plugin, SoundManager soundManager, GUIHelper guiHelper, ConsoleCommand consoleCommand, PermissionLP permissionLP) {
        this.plugin = plugin;
        this.soundManager = soundManager;
        this.guiHelper = guiHelper;
        this.consoleCommand = consoleCommand;
        this.permissionLP = permissionLP;
    }

    @CommandAlias("social|socialMenu")
    public void setting(Player player) {
        ChestGui gui = new ChestGui(4, guiHelper.guiName("Social"));
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        OutlinePane background = new OutlinePane(0, 0, 9, 4, Pane.Priority.LOWEST);
        StaticPane display = new StaticPane(0, 0, 9, 4, Pane.Priority.LOW);


        background.addItem(new GuiItem(guiHelper.background(Material.LIME_STAINED_GLASS_PANE)));
        background.setRepeat(true);


        display.addItem(new GuiItem(visit(player), e -> player.sendMessage(visit)), 3, 1);
        display.addItem(new GuiItem(friends(player), e -> player.sendMessage(friend)), 5, 1);

        display.addItem(new GuiItem(guiHelper.backButton(), e -> player.performCommand("main")), 4, 3);

        gui.addPane(background);
        gui.addPane(display);
        gui.show(player);
    }


    private ItemStack visit(Player player) {
        final ItemStack visit = new ItemStack(Material.OAK_DOOR);
        final ItemMeta visitItemMeta = visit.getItemMeta();

        visitItemMeta.displayName(Component.text(ChatColor.GREEN + "/Home Visit " + ChatColor.YELLOW + "<Name>"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "Visit another " + ChatColor.YELLOW + "Adventurer's Home" + ChatColor.GRAY + ".");
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View Command");

        visit.setItemMeta(visitItemMeta);
        visit.setLore(lore);

        return visit;
    }

    private ItemStack friends(Player player) {
        final ItemStack friends = new ItemStack(Material.TOTEM_OF_UNDYING);
        final ItemMeta friendsItemMeta = friends.getItemMeta();

        friendsItemMeta.displayName(Component.text(ChatColor.GREEN + "/Friends"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "Add other " + ChatColor.YELLOW + "Adventurers " + ChatColor.GRAY + "you meet along");
        lore.add(ChatColor.GRAY + "your journey to your  " + ChatColor.YELLOW + "Friends List" + ChatColor.GRAY + "!");
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View Command");

        friends.setItemMeta(friendsItemMeta);
        friends.setLore(lore);

        return friends;
    }

}

