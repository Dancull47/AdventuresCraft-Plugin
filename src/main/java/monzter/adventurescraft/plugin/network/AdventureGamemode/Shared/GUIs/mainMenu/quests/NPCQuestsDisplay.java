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

    @Subcommand("Jenny")
    public void jenny(Player player) {
        guiHelper.questMenuGenerator(player, QuestGiver.JENNY, Material.GREEN_STAINED_GLASS_PANE);
    }

    @Subcommand("Bear")
    public void bear(Player player) {
        guiHelper.questMenuGenerator(player, QuestGiver.BEAR, Material.GREEN_STAINED_GLASS_PANE);
    }

    @Subcommand("CatLady|Cat_Lady")
    public void catLady(Player player) {
        guiHelper.questMenuGenerator(player, QuestGiver.CAT_LADY, Material.GREEN_STAINED_GLASS_PANE);
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

    @Subcommand("Billy")
    public void billy(Player player) {
        guiHelper.questMenuGenerator(player, QuestGiver.BILLY, Material.GREEN_STAINED_GLASS_PANE);
    }

    @Subcommand("Mael")
    public void mael(Player player) {
        guiHelper.questMenuGenerator(player, QuestGiver.MAEL, Material.YELLOW_STAINED_GLASS_PANE);
    }

    @Subcommand("Bowyer")
    public void bowyer(Player player) {
        guiHelper.questMenuGenerator(player, QuestGiver.BOWYER, Material.GREEN_STAINED_GLASS_PANE);
    }

    @Subcommand("Don")
    public void don(Player player) {
        guiHelper.questMenuGenerator(player, QuestGiver.DON, Material.GREEN_STAINED_GLASS_PANE);
    }
    @Subcommand("Sandrah")
    public void sandrah(Player player) {
        guiHelper.questMenuGenerator(player, QuestGiver.SANDRAH, Material.GREEN_STAINED_GLASS_PANE);
    }

    @Subcommand("Zras")
    public void zras(Player player) {
        guiHelper.questMenuGenerator(player, QuestGiver.ZRAS, Material.RED_STAINED_GLASS_PANE);
    }

    @Subcommand("Hazel")
    public void hazel(Player player) {
        guiHelper.questMenuGenerator(player, QuestGiver.HAZEL, Material.PINK_STAINED_GLASS_PANE);
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

