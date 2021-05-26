package monzter.adventurescraft.plugin.shared.GUIs.quests.yard;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Dependency;
import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import me.clip.placeholderapi.PlaceholderAPI;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.GUI.GUIHelper;
import monzter.adventurescraft.plugin.utilities.beton.BetonTagManager;
import monzter.adventurescraft.plugin.utilities.enums.StatsDisplay;
import net.Indyuce.mmoitems.MMOItems;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class Finubar extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final GUIHelper guiHelper;
    private final BetonTagManager betonTagManager;
    final String quester = "Finubar";
    final String questerLocation = "Town";


    public Finubar(AdventuresCraft plugin, GUIHelper guiHelper, BetonTagManager betonTagManager) {
        this.plugin = plugin;
        this.guiHelper = guiHelper;
        this.betonTagManager = betonTagManager;
    }

    @CommandAlias("finubarQuests")
    public void questMenu(Player player) {
        ChestGui gui = new ChestGui(4, guiHelper.guiName("Finubar's Quests " + parsePlaceholder(player, "betonquest_default-Points:point.Finubar.amount")));
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        OutlinePane background = new OutlinePane(0, 0, 9, 4, Pane.Priority.LOWEST);
        StaticPane display = new StaticPane(0, 0, 9, 4, Pane.Priority.LOW);


        background.addItem(new GuiItem(guiHelper.background(Material.RED_STAINED_GLASS_PANE)));
        background.setRepeat(true);

        display.addItem(quest1(player), 3, 1);
        display.addItem(quest2(player), 5, 1);

        display.addItem(new GuiItem(guiHelper.backButton(), e -> player.performCommand("YardQuests")), 4, 3);

        gui.addPane(background);
        gui.addPane(display);
        gui.show(player);
    }


    private GuiItem quest1(Player player) {
        final String name = "Water Crystal";
        final TextComponent[] description = new TextComponent[]{
                Component.text()
                        .decoration(TextDecoration.ITALIC, false)
                        .content("Mine 64 ")
                        .color(NamedTextColor.WHITE)
                        .append(Component.text("Lapis Blocks", TextColor.color(0x345ec1)))
                        .build(),
                Component.text()
                        .decoration(TextDecoration.ITALIC, false)
                        .content("Mine 32 ")
                        .color(NamedTextColor.WHITE)
                        .append(Component.text("Blue Glass", TextColor.color(0x8397b2)))
                        .build(),
                Component.text()
                        .decoration(TextDecoration.ITALIC, false)
                        .content("Mine 16 ")
                        .color(NamedTextColor.WHITE)
                        .append(Component.text("Sea Lanterns", TextColor.color(0x2f5148)))
                        .build()};
        final TextComponent[] rewards = new TextComponent[]{Component.text(MMOItems.plugin.getItem("MATERIAL", "WATER_CRYSTAL").getItemMeta().getDisplayName()),
                Component.text()
                        .decoration(TextDecoration.ITALIC, false)
                        .content("500,000 " + StatsDisplay.PET_EXPERIENCE_AMOUNT.getName())
                        .color(NamedTextColor.WHITE)
                        .build(),
                Component.text()
                        .decoration(TextDecoration.ITALIC, false)
                        .content("10,000,000 " + StatsDisplay.MONEY_AMOUNT.getName())
                        .color(NamedTextColor.WHITE)
                        .build()};
        if (!betonTagManager.hasTag(player, "default-Yard-Finubar.q1_part1"))
            return new GuiItem(guiHelper.questInactive(name, description, rewards, quester, questerLocation));
//        if (betonTagManager.hasTag(player, "default-Yard-Finubar.q1_part1") && !betonTagManager.hasTag(player, "default-Yard-Finubar.q1_completed"))
//            return new GuiItem(guiHelper.questActive(name, description, rewards, quester, questerLocation));
//        if (betonTagManager.hasTag(player, "default-Yard-Finubar.q1_completed"))
//            return new GuiItem(guiHelper.questComplete(name, description, rewards, quester, questerLocation));
        return null;
    }

    private GuiItem quest2(Player player) {
        final String name = "Phoenix Crown";
        final TextComponent[] description = new TextComponent[]{
                Component.text()
                        .decoration(TextDecoration.ITALIC, false)
                        .content("Mine 64 ")
                        .color(NamedTextColor.WHITE)
                        .append(Component.text("Gold Blocks", TextColor.color(0xfde64b)))
                        .build(),
                Component.text()
                        .decoration(TextDecoration.ITALIC, false)
                        .content("Mine 32 ")
                        .color(NamedTextColor.WHITE)
                        .append(Component.text("Emerald Blocks", TextColor.color(0x15b038)))
                        .build(),
                Component.text()
                        .decoration(TextDecoration.ITALIC, false)
                        .content("Mine 16 ")
                        .color(NamedTextColor.WHITE)
                        .append(Component.text("Stained Glass", TextColor.color(0xcf1729)))
                        .build()};
        final TextComponent[] rewards = new TextComponent[]{Component.text(MMOItems.plugin.getItem("MATERIAL", "PHOENIX_CROWN").getItemMeta().getDisplayName()),
                Component.text()
                        .decoration(TextDecoration.ITALIC, false)
                        .content("500,000 " + StatsDisplay.PET_EXPERIENCE_AMOUNT.getName())
                        .color(NamedTextColor.WHITE)
                        .build(),
                Component.text()
                        .decoration(TextDecoration.ITALIC, false)
                        .content("10,000,000 " + StatsDisplay.MONEY_AMOUNT.getName())
                        .color(NamedTextColor.WHITE)
                        .build()};

        if (!betonTagManager.hasTag(player, "default-Yard-Finubar.q2_part1"))
            return new GuiItem(guiHelper.questInactive(name, description, rewards, quester, questerLocation));
        if (betonTagManager.hasTag(player, "default-Yard-Finubar.q2_part1") && !betonTagManager.hasTag(player, "default-Yard-Finubar.q2_completed"))
            return new GuiItem(guiHelper.questActive(name, description, rewards, quester, questerLocation));
        if (betonTagManager.hasTag(player, "default-Yard-Finubar.q2_completed"))
            return new GuiItem(guiHelper.questComplete(name, description, rewards, quester, questerLocation));
        return null;
    }


    private String parsePlaceholder(Player player, String string) {
        return PlaceholderAPI.setPlaceholders(player, "%" + string + "%");
    }

}

