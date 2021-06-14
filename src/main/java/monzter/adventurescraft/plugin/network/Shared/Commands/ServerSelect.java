package monzter.adventurescraft.plugin.network.Shared.Commands;

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
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class ServerSelect extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final SoundManager soundManager;
    private final GUIHelper guiHelper;
    private final String CURRENTLY_ON = ChatColor.DARK_GRAY + "- " + ChatColor.GOLD + ChatColor.BOLD + "CURRENTLY ON";


    public ServerSelect(AdventuresCraft plugin, SoundManager soundManager, GUIHelper guiHelper) {
        this.plugin = plugin;
        this.soundManager = soundManager;
        this.guiHelper = guiHelper;
    }

    @CommandAlias("Server|ServerSelect|Select|Server")
    public void mainMenu(Player player) {

        ChestGui gui = new ChestGui(5, guiHelper.guiName("Server Select"));
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        OutlinePane background = new OutlinePane(0, 0, 9, 5, Pane.Priority.LOWEST);
        StaticPane display = new StaticPane(0, 0, 9, 5, Pane.Priority.LOW);


        background.addItem(new GuiItem(guiHelper.background(Material.CYAN_STAINED_GLASS_PANE)));
        background.setRepeat(true);

        display.addItem(adventure(player), 2, 1);
        if (!plugin.SERVER.equals("Lobby"))
            display.addItem(lobby(player), 4, 1);

            display.addItem(prison(player), 6, 1);


        display.addItem(store(player), 1, 3);
        display.addItem(website(player), 3, 3);
        display.addItem(discord(player), 5, 3);
        if (plugin.SERVER.equals("Lobby"))
            display.addItem(trails(player), 7, 3);

        gui.addPane(background);
        gui.addPane(display);
        gui.show(player);
    }


    private GuiItem lobby(Player player) {
        if (isOnServer("Lobby"))
            return new GuiItem(guiHelper.itemCreator(Material.GREEN_BED, ChatColor.GREEN + "Lobby " + CURRENTLY_ON, new String[]{"",
                    ChatColor.GRAY + "Return to the previous server you",
                    ChatColor.GRAY + "were on to select another " + ChatColor.GREEN + "Gamemode" + ChatColor.GRAY + "!"}), e -> player.performCommand("Lobby"));
        else
            return new GuiItem(guiHelper.itemCreator(Material.GREEN_BED, ChatColor.GREEN + "Lobby", new String[]{"",
                    ChatColor.GRAY + "Return to the previous server you",
                    ChatColor.GRAY + "were on to select another " + ChatColor.GREEN + "Gamemode" + ChatColor.GRAY + "!",
                    "",
                    Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Return to the Lobby"}), e -> player.performCommand("Lobby"));

    }

    private GuiItem prison(Player player) {
        if (isOnServer("Prison"))
            return new GuiItem(guiHelper.itemCreator(Material.IRON_BARS, ChatColor.YELLOW + "Prison " + CURRENTLY_ON, new String[]{"",
                    ChatColor.GRAY + "Explore the most unique " + ChatColor.YELLOW + "Prison",
                    ChatColor.GRAY + "with custom " + ChatColor.BLUE + "Weight, " + ChatColor.GOLD + "Mining Speed,",
                    ChatColor.GREEN + "Events, " + ChatColor.GRAY + "and " + ChatColor.RED + "MMORPG aspects" + ChatColor.GRAY + "!",
            }));
        else
            return new GuiItem(guiHelper.itemCreator(Material.IRON_BARS, ChatColor.YELLOW + "Prison", new String[]{"",
                    ChatColor.GRAY + "Explore the most unique " + ChatColor.YELLOW + "Prison",
                    ChatColor.GRAY + "with custom " + ChatColor.BLUE + "Weight, " + ChatColor.GOLD + "Mining Speed,",
                    ChatColor.GREEN + "Events, " + ChatColor.GRAY + "and " + ChatColor.RED + "MMORPG aspects" + ChatColor.GRAY + "!",
                    "",
                    Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Play"}), e -> player.performCommand("Prison"));
    }

    private GuiItem adventure(Player player) {
        if (isOnServer("Adventure"))
            return new GuiItem(guiHelper.itemCreator("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmQ4MjEwOTJjZTVlNzU1NzQ1MWM3MjNhMDM0MWU5MGI5M2UwNTY0ZTJiMDE0ODFkZTgxZWVhMjcxZjA0YzViNiJ9fX0=", ChatColor.RED +
                    "Adventure " + CURRENTLY_ON, new String[]{"",
                    ChatColor.GRAY + "Begin your adventure on our " + ChatColor.RED + "MMORPG",
                    ChatColor.GRAY + "gamemode with " + ChatColor.YELLOW + "Quests, Bosses,",
                    ChatColor.YELLOW + "Mobs, Epic Loot, and much more" + ChatColor.GRAY + "!",
            }), e -> player.performCommand("Adventure"));
        else
            return new GuiItem(guiHelper.itemCreator("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmQ4MjEwOTJjZTVlNzU1NzQ1MWM3MjNhMDM0MWU5MGI5M2UwNTY0ZTJiMDE0ODFkZTgxZWVhMjcxZjA0YzViNiJ9fX0=", ChatColor.RED +
                    "Adventure ", new String[]{"",
                    ChatColor.GRAY + "Begin your adventure on our " + ChatColor.RED + "MMORPG",
                    ChatColor.GRAY + "gamemode with " + ChatColor.YELLOW + "Quests, Bosses,",
                    ChatColor.YELLOW + "Mobs, Epic Loot, and much more" + ChatColor.GRAY + "!",
                    "",
                    Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Play"
            }), e -> player.performCommand("Adventure"));
    }

    private GuiItem store(Player player) {
        return new GuiItem(guiHelper.itemCreator("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2U0MWM2MDU3MmM1MzNlOTNjYTQyMTIyODkyOWU1NGQ2Yzg1NjUyOTQ1OTI0OWMyNWMzMmJhMzNhMWIxNTE3In19fQ==", ChatColor.GREEN +
                "Store", new String[]{"",
                Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View"
        }), e -> player.performCommand("store"));
    }

    private GuiItem trails(Player player) {
        return new GuiItem(guiHelper.itemCreator(Material.BLAZE_POWDER, ChatColor.GREEN +
                "Trails", new String[]{"",
                ChatColor.GRAY + "Obtain cool " + ChatColor.GOLD + "Trails " + ChatColor.GRAY + "which",
                ChatColor.GRAY + "follow behind as you run around!",
                "",
                Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View"
        }), e -> player.performCommand("trails"));
    }

    private GuiItem website(Player player) {
        return new GuiItem(guiHelper.itemCreator("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjY5YWQxYTg4ZWQyYjA3NGUxMzAzYTEyOWY5NGU0YjcxMGNmM2U1YjRkOTk1MTYzNTY3ZjY4NzE5YzNkOTc5MiJ9fX0=", ChatColor.GREEN +
                "Website", new String[]{"",
                Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View"
        }), e -> player.performCommand("website"));
    }

    private GuiItem discord(Player player) {
        return new GuiItem(guiHelper.itemCreator("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzE5M2RjMGQ0YzVlODBmZjlhOGEwNWQyZmNmZTI2OTUzOWNiMzkyNzE5MGJhYzE5ZGEyZmNlNjFkNzEifX19", ChatColor.GREEN +
                "Discord", new String[]{"",
                Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Join"
        }), e -> player.performCommand("discord"));
    }

    private boolean isOnServer(String server) {
        if (plugin.SERVER.equals(server))
            return true;
        return false;
    }
}

