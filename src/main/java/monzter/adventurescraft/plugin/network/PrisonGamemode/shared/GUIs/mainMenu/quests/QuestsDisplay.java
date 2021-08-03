package monzter.adventurescraft.plugin.network.PrisonGamemode.shared.GUIs.mainMenu.quests;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Dependency;
import co.aikar.commands.annotation.Subcommand;
import me.clip.placeholderapi.PlaceholderAPI;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.GUI.GUIHelper;
import monzter.adventurescraft.plugin.utilities.beton.BetonPointsManager;
import monzter.adventurescraft.plugin.utilities.beton.BetonTagManager;
import monzter.adventurescraft.plugin.utilities.general.ConsoleCommand;
import monzter.adventurescraft.plugin.utilities.general.FullInventory;
import monzter.adventurescraft.plugin.utilities.general.ItemAdder;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import monzter.adventurescraft.plugin.utilities.vault.Economy;
import net.Indyuce.mmoitems.MMOItems;
import org.bukkit.Material;
import org.bukkit.entity.Player;

@CommandAlias("QuestViewer")
public class QuestsDisplay extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final SoundManager soundManager;
    private final GUIHelper guiHelper;
    private final ConsoleCommand consoleCommand;
    private final MMOItems mmoItems;
    private final BetonTagManager betonTagManager;
    private final FullInventory fullInventory;
    private final ItemAdder itemAdder;
    private final BetonPointsManager betonPointsManager;
    private final Economy economy;

    public QuestsDisplay(AdventuresCraft plugin, SoundManager soundManager, GUIHelper guiHelper, ConsoleCommand consoleCommand, MMOItems mmoItems, BetonTagManager betonTagManager, FullInventory fullInventory, ItemAdder itemAdder, BetonPointsManager betonPointsManager, Economy economy) {
        this.plugin = plugin;
        this.soundManager = soundManager;
        this.guiHelper = guiHelper;
        this.consoleCommand = consoleCommand;
        this.mmoItems = mmoItems;
        this.betonTagManager = betonTagManager;
        this.fullInventory = fullInventory;
        this.itemAdder = itemAdder;
        this.betonPointsManager = betonPointsManager;
        this.economy = economy;
    }

    @Subcommand("Wizard")
    public void wizard(Player player) {
        guiHelper.questMenuGenerator(player, QuestGiver.WIZARD, Material.PURPLE_STAINED_GLASS_PANE);
    }

    @Subcommand("Joy")
    public void joy(Player player) {
        guiHelper.questMenuGenerator(player, QuestGiver.JOY, Material.PINK_STAINED_GLASS_PANE);
    }

    @Subcommand("Merle")
    public void merle(Player player) {
        guiHelper.jobMenuGenerator(player, QuestGiver.MERLE, Material.PURPLE_STAINED_GLASS_PANE);
    }

    @Subcommand("Dan|MiningPass")
    public void miningPass(Player player) {
        guiHelper.miningPassMenuGenerator(player, QuestGiver.DAN, Material.ORANGE_STAINED_GLASS_PANE);
    }

    @Subcommand("Ash")
    public void ash(Player player) {
        guiHelper.questMenuGenerator(player, QuestGiver.ASH, Material.RED_STAINED_GLASS_PANE);
    }

    private String parsePlaceholder(Player player, String string) {
        return PlaceholderAPI.setPlaceholders(player, "%" + string + "%");
    }
}

