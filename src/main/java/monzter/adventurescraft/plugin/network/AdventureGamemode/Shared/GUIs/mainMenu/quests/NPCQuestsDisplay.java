package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.mainMenu.quests;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Dependency;
import co.aikar.commands.annotation.Subcommand;
import me.clip.placeholderapi.PlaceholderAPI;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.mainMenu.quests.enums.QuestGiver;
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
public class NPCQuestsDisplay extends BaseCommand {

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

    public NPCQuestsDisplay(AdventuresCraft plugin, SoundManager soundManager, GUIHelper guiHelper, ConsoleCommand consoleCommand, MMOItems mmoItems, BetonTagManager betonTagManager, FullInventory fullInventory, ItemAdder itemAdder, BetonPointsManager betonPointsManager, Economy economy) {
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


    @Subcommand("Tutor")
    public void tutor(Player player) {
        guiHelper.questMenuGenerator(player, QuestGiver.TUTOR, Material.PURPLE_STAINED_GLASS_PANE);
    }

    @Subcommand("Anju")
    public void anju(Player player) {
        guiHelper.questMenuGenerator(player, QuestGiver.ANJU, Material.GREEN_STAINED_GLASS_PANE);
    }

    @Subcommand("Ingo")
    public void ingo(Player player) {
        guiHelper.questMenuGenerator(player, QuestGiver.INGO, Material.GREEN_STAINED_GLASS_PANE);
    }

    @Subcommand("Mandy")
    public void mandy(Player player) {
        guiHelper.questMenuGenerator(player, QuestGiver.MANDY, Material.GREEN_STAINED_GLASS_PANE);
    }

    @Subcommand("Cirl")
    public void cirl(Player player) {
        guiHelper.questMenuGenerator(player, QuestGiver.CIRL, Material.RED_STAINED_GLASS_PANE);
    }

    @Subcommand("Navid")
    public void navid(Player player) {
        guiHelper.questMenuGenerator(player, QuestGiver.NAVID, Material.RED_STAINED_GLASS_PANE);
    }

    @Subcommand("Klaus")
    public void klaus(Player player) {
        guiHelper.questMenuGenerator(player, QuestGiver.KLAUS, Material.RED_STAINED_GLASS_PANE);
    }


    private String parsePlaceholder(Player player, String string) {
        return PlaceholderAPI.setPlaceholders(player, "%" + string + "%");
    }
}

