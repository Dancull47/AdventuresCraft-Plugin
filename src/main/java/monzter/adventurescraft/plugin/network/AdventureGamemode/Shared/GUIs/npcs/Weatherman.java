package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.npcs;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
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
import monzter.adventurescraft.plugin.utilities.vault.Economy;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class Weatherman extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final SoundManager soundManager;
    private final GUIHelper guiHelper;
    private final ConsoleCommand consoleCommand;
    private final Economy economy;


    public Weatherman(AdventuresCraft plugin, SoundManager soundManager, GUIHelper guiHelper, ConsoleCommand consoleCommand, Economy economy) {
        this.plugin = plugin;
        this.soundManager = soundManager;
        this.guiHelper = guiHelper;
        this.consoleCommand = consoleCommand;
        this.economy = economy;
    }

    @CommandAlias("Weatherman")
    @CommandPermission("*")
    public void mainMenu(Player player) {

        ChestGui gui = new ChestGui(3, guiHelper.guiName("Weather"));
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        OutlinePane background = new OutlinePane(0, 0, 9, 3, Pane.Priority.LOWEST);
        StaticPane display = new StaticPane(0, 0, 9, 3, Pane.Priority.LOW);


        background.addItem(new GuiItem(guiHelper.background(Material.PINK_STAINED_GLASS_PANE)));
        background.setRepeat(true);

        display.addItem(new GuiItem(guiHelper.itemCreator(Material.SUNFLOWER,
                ChatColor.GREEN + "Sunshine", new String[]{"", ChatColor.GRAY + "Change the weather to " + ChatColor.GOLD + "Sunny" + ChatColor.GRAY + "!",
                        "", ChatColor.WHITE + "Price: " + ChatColor.YELLOW + "⛂ " + ChatColor.WHITE + "5,000", "", Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Purchase"}),
                e -> {
                    if (!player.getWorld().isClearWeather())
                        if (economy.getBalance(player) >= 5_000) {
                            economy.takeMoney(player, 5_000);
                            player.getWorld().setStorm(false);
                            soundManager.playSound(player, Sound.ENTITY_TURTLE_EGG_HATCH, 1, 1);
                            soundManager.playSound(player, Sound.BLOCK_BELL_USE, 1, 2);
                        } else {
                            soundManager.soundNo(player, 1);
                            player.sendMessage(ChatColor.RED + "You don't have enough money!");
                        }
                    else {
                        soundManager.soundNo(player, 1);
                        player.sendMessage(ChatColor.RED + "It's already sunny!");
                    }
                }), 3, 1);
        display.addItem(new GuiItem(guiHelper.itemCreator(Material.WATER_BUCKET,
                ChatColor.GREEN + "Rain", new String[]{"", ChatColor.GRAY + "Change the weather to " + ChatColor.BLUE + "Rainy" + ChatColor.GRAY + "!",
                        "", ChatColor.WHITE + "Price: " + ChatColor.YELLOW + "⛂ " + ChatColor.WHITE + "10,000", "", Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Purchase"}),
                e -> {
                    if (player.getWorld().isClearWeather())
                        if (economy.getBalance(player) >= 10_000) {
                            economy.takeMoney(player, 10_000);
                            player.getWorld().setStorm(true);
                            soundManager.playSound(player, Sound.WEATHER_RAIN_ABOVE, 1, 1);
                            soundManager.playSound(player, Sound.BLOCK_BELL_USE, 1, .5f);
                        } else {
                            soundManager.soundNo(player, 1);
                            player.sendMessage(ChatColor.RED + "You don't have enough money!");
                        }
                    else {
                        soundManager.soundNo(player, 1);
                        player.sendMessage(ChatColor.RED + "It's already raining!");
                    }
                }), 5, 1);

        gui.addPane(background);
        gui.addPane(display);
        gui.show(player);
    }

    private boolean hasMoney(Player player, int price) {
        if (economy.getBalance(player) >= price) {
            return true;
        }
        soundManager.soundNo(player, 1);
        player.sendMessage(ChatColor.RED + "You don't have enough money!");
        return false;
    }

    private boolean isSunny(Player player, boolean sunny) {
        if (sunny) {
            if (player.getWorld().isClearWeather()) {
                soundManager.soundNo(player, 1);
                player.sendMessage(ChatColor.RED + "It's already sunny!");
                return true;
            }
        } else {
            if (!player.getWorld().isClearWeather()) {
                soundManager.soundNo(player, 1);
                player.sendMessage(ChatColor.RED + "It's already raining!");
                return true;
            }
        }
        return false;
    }
}

