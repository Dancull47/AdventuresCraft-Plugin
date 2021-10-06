package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.mainMenu;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Dependency;
import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import io.lumine.xikage.mythicmobs.MythicMobs;
import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
import io.lumine.xikage.mythicmobs.spawning.spawners.MythicSpawner;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.network.AdventureGamemode.Enums.Bosses;
import monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.Commands.DropTables.Crates;
import monzter.adventurescraft.plugin.utilities.GUI.GUIHelper;
import monzter.adventurescraft.plugin.utilities.beton.BetonPointsManager;
import monzter.adventurescraft.plugin.utilities.enums.Prefix;
import monzter.adventurescraft.plugin.utilities.general.ConsoleCommand;
import net.Indyuce.mmoitems.MMOItems;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.betoncraft.betonquest.BetonQuest;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class Bossdex extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final GUIHelper guiHelper;
    private final MMOItems mmoItems;
    private final BetonPointsManager betonPointsManager;
    private final ConsoleCommand consoleCommand;


    public Bossdex(AdventuresCraft plugin, GUIHelper guiHelper, MMOItems mmoItems, BetonPointsManager betonPointsManager, ConsoleCommand consoleCommand) {
        this.plugin = plugin;
        this.guiHelper = guiHelper;
        this.mmoItems = mmoItems;
        this.betonPointsManager = betonPointsManager;
        this.consoleCommand = consoleCommand;
    }

    @CommandAlias("bossdex|bossdexs")
    public void map(Player player) {

        ChestGui gui = new ChestGui(5, guiHelper.guiName("Bossdex"));
        gui.setOnGlobalClick(event -> event.setCancelled(true));
        OutlinePane background = new OutlinePane(0, 0, 9, 5, Pane.Priority.LOWEST);
        OutlinePane display = new OutlinePane(1, 1, 7, 3, Pane.Priority.LOW);
        StaticPane backButton = new StaticPane(4, 4, 1, 1, Pane.Priority.HIGHEST);


        background.addItem(new GuiItem(guiHelper.background(Material.RED_STAINED_GLASS_PANE)));
        background.setRepeat(true);

        for (Bosses boss : Bosses.values())
            display.addItem(itemGenerator(player, boss));

        backButton.addItem(new GuiItem(guiHelper.backButton(), e -> player.performCommand("main")), 0, 0);

        gui.addPane(background);
        gui.addPane(display);
        gui.addPane(backButton);
        gui.show(player);
    }

    DecimalFormat df = new DecimalFormat("#.####");

    private GuiItem itemGenerator(Player player, Bosses boss) {
        int points = (betonPointsManager.getPoints("bossReward." + boss.getName().toUpperCase().replace(' ', '_'), BetonQuest.getInstance().getPlayerData(player.getUniqueId().toString()).getPoints()));
        ItemStack itemStack = boss.getItemStack();
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName(ChatColor.RED + boss.getName());

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.YELLOW.toString() + ChatColor.BOLD + "REWARDS:");

        final List<Crates> rewards = Crates.getCrates(boss.getCrateList());
        for (Crates reward : rewards)
            lore.add(Prefix.PREFIX.getString() + reward.getItemStack().getItemMeta().getDisplayName() + " " + ChatColor.WHITE + df.format(reward.getWeight() * 100) + "%");
        if (boss.isRespawn()) {
            lore.add("");
            if (player.hasPermission(boss.getName().toUpperCase() + ".TIMER"))
                lore.add(ChatColor.WHITE + "Time Until Spawn: " + ChatColor.GREEN + spawner(boss));
            else
                lore.add(ChatColor.WHITE + "Time Until Spawn: " + ChatColor.RED + ChatColor.BOLD + "LOCKED");
        }
        lore.add("");
        lore.add(ChatColor.GRAY + "Rewards Available: " + ChatColor.GOLD + ChatColor.BOLD + points);
        lore.add("");
        if (points > 0)
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Left-Click to Claim Reward");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Right-Click to View Drop Table");
//        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Shift-Right-Click to View Achievements");

        itemStack.setItemMeta(itemMeta);
        itemStack.setLore(lore);

        return new GuiItem(itemStack, e -> {
            if (e.isLeftClick() && points > 0 && !e.isShiftClick()) {
                betonPointsManager.takePoint(player, "bossReward." + boss.getName().toUpperCase().replace(' ', '_'), 1);
                consoleCommand.consoleCommand("droptable " + player.getName() + " " + boss.getName().toUpperCase().replace(' ', '_') + " 1");
                player.performCommand("bossdex");
            } else if (e.isRightClick() && !e.isShiftClick()) {
                player.performCommand("DropTableViewer " + boss.getName().replace(" ", ""));
//            } else if (e.isShiftClick()) {
//                player.performCommand("Achievements " + boss.getName().replace(" ", ""));
            }
        });
    }

    private String spawner(Bosses bosses) {
        if (bosses.equals(Bosses.BULLBO))
            bosses = Bosses.BULBLIN;

        Iterator spawnerIterator = MythicMobs.inst().getSpawnerManager().listSpawners.iterator();

        MythicSpawner spawner;
        do {
            if (!spawnerIterator.hasNext())
                return null;
            spawner = (MythicSpawner) spawnerIterator.next();
        } while (!bosses.getName().replace(' ', '_').toUpperCase().equals(spawner.getName()));
        String message = null;
        if (!spawner.isOnWarmup()) {
            boolean alive = false;
            Iterator uuidIterator = spawner.getAssociatedMobs().iterator();

            while (uuidIterator.hasNext()) {
                UUID mob = (UUID) uuidIterator.next();
                if (!((ActiveMob) MythicMobs.inst().getMobManager().getActiveMob(mob).get()).isDead() && !alive) {
                    alive = true;
                    message = ChatColor.GREEN + "now!";
                }
            }

            if (!alive)
                message = this.secondsToString(spawner.getRemainingCooldownSeconds());
        } else
            message = this.secondsToString(spawner.getRemainingWarmupSeconds());

        if (message.equalsIgnoreCase("00:00"))
            message = ChatColor.GOLD + "soon...";

        return message;
    }

    private String secondsToString(int pTime) {
        return String.format("%02d:%02d", pTime / 60, pTime % 60);
    }
}

