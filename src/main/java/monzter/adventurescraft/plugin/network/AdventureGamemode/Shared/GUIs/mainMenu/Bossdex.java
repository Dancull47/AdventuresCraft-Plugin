package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.mainMenu;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Dependency;
import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import dev.dbassett.skullcreator.SkullCreator;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.network.AdventureGamemode.Enums.Bosses;
import monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.Commands.DropTables.Crates;
import monzter.adventurescraft.plugin.utilities.GUI.GUIHelper;
import monzter.adventurescraft.plugin.utilities.beton.BetonPointsManager;
import monzter.adventurescraft.plugin.utilities.enums.Prefix;
import net.Indyuce.mmoitems.MMOItems;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.betoncraft.betonquest.BetonQuest;

import java.util.ArrayList;
import java.util.List;

public class Bossdex extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final GUIHelper guiHelper;
    private final Material LOCKED = Material.RED_STAINED_GLASS_PANE;
    private final String LOCKED_TEXT = ChatColor.DARK_GRAY + "- " + ChatColor.RED + ChatColor.BOLD + "LOCKED";
    private final MMOItems mmoItems;
    private final BetonPointsManager betonPointsManager;


    public Bossdex(AdventuresCraft plugin, GUIHelper guiHelper, MMOItems mmoItems, BetonPointsManager betonPointsManager) {
        this.plugin = plugin;
        this.guiHelper = guiHelper;
        this.mmoItems = mmoItems;
        this.betonPointsManager = betonPointsManager;
    }

    @CommandAlias("bossdex|bossdexs")
    public void map(Player player) {

        ChestGui gui = new ChestGui(6, guiHelper.guiName("Bossdex"));
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        OutlinePane background = new OutlinePane(0, 0, 9, 6, Pane.Priority.LOWEST);
        StaticPane display = new StaticPane(0, 0, 9, 6, Pane.Priority.LOW);


        background.addItem(new GuiItem(guiHelper.background(Material.RED_STAINED_GLASS_PANE)));
        background.setRepeat(true);

//        for (Bosses boss : Bosses.values())

            display.addItem(itemGenerator(player, Bosses.REAPER), 1, 1);

        display.addItem(new GuiItem(farm(), e -> player.performCommand("warp farm")), 1, 2);

        gui.addPane(background);
        gui.addPane(display);
        gui.show(player);
    }

    private ItemStack farm() {
        final ItemStack farm = new ItemStack(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWFmMzI4Yzg3YjA2ODUwOWFjYTk4MzRlZmFjZTE5NzcwNWZlNWQ0ZjA4NzE3MzFiN2IyMWNkOTliOWZkZGMifX19"));
        final ItemMeta farmItemMeta = farm.getItemMeta();

        farmItemMeta.displayName(Component.text(ChatColor.GREEN + "The Farm"));

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.DARK_GRAY + "/warp farm");
        lore.add("");
        lore.add(ChatColor.GRAY + "Harvest the crop fields");
        lore.add(ChatColor.GRAY + "and help " + ChatColor.GREEN + "Billy " + ChatColor.GRAY + "& " + ChatColor.GREEN + "Mandy" + ChatColor.GRAY + "!");
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Travel");

        farm.setItemMeta(farmItemMeta);
        farm.setLore(lore);

        return farm;
    }

    private GuiItem itemGenerator(Player player, Bosses boss) {
        int points = (betonPointsManager.getPoints("bosses." + boss.getName().toUpperCase() + ".Reward", BetonQuest.getInstance().getPlayerData(player.getUniqueId().toString()).getPoints()));
        ItemStack itemStack = boss.getItemStack();
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName(boss.getName());

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.YELLOW.toString() + ChatColor.BOLD + "REWARDS:");
        /*
         *
         *   Loop CrateList with Bosses?
         *
         * */
        final List<Crates> rewards = Crates.getCrates(boss.getCrateList());
        for (Crates reward : rewards)
            lore.add(Prefix.PREFIX.getString() + mmoItems.getItem(reward.getType(), reward.getId()).getItemMeta().getDisplayName() + " " + ChatColor.WHITE + reward.getWeight() * 100 + "%");
        if (boss.isRespawn()) {
            lore.add("");
            if (player.hasPermission(boss.getName().toUpperCase() + ".TIMER"))
                lore.add(ChatColor.WHITE + "Time Until Spawn: " + ChatColor.GREEN + "PLACEHOLDER@(*@**(@(*@");
            else
                lore.add(ChatColor.WHITE + "Time Until Spawn: " + ChatColor.RED + ChatColor.BOLD + "LOCKED");
        }
        if (points > 0) {
            lore.add("");
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Claim Reward");
        }

        itemStack.setItemMeta(itemMeta);
        itemStack.setLore(lore);

        return new GuiItem(itemStack, e -> {
            if (e.isLeftClick() && points > 0)
                player.performCommand("DropTableViewer " + boss.getName());
            player.performCommand("bossdex");
        });
    }
}

