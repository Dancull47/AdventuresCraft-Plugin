package monzter.adventurescraft.plugin.network.PrisonGamemode.shared.GUIs.quests;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Dependency;
import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import dev.dbassett.skullcreator.SkullCreator;
import me.clip.placeholderapi.PlaceholderAPI;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.GUI.GUIHelper;
import monzter.adventurescraft.plugin.utilities.enums.Prefix;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Yard extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final GUIHelper guiHelper;

    public Yard(AdventuresCraft plugin, GUIHelper guiHelper) {
        this.plugin = plugin;
        this.guiHelper = guiHelper;
    }

    @CommandAlias("YardQuests")
    public void questMenu(Player player) {

        ChestGui gui = new ChestGui(4, guiHelper.guiName("Yard Quests " + parsePlaceholder(player, "betonquest_default-Points:point.QuestTotal.amount")));
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        OutlinePane background = new OutlinePane(0, 0, 9, 4, Pane.Priority.LOWEST);
        StaticPane display = new StaticPane(0, 0, 9, 4, Pane.Priority.LOW);


        background.addItem(new GuiItem(guiHelper.background(Material.GRAY_STAINED_GLASS_PANE)));
        background.setRepeat(true);

        display.addItem(new GuiItem(enchanter(player), e -> player.performCommand("enchanterQuests")), 3, 1);
        display.addItem(new GuiItem(joy(player), e -> player.performCommand("joyQuests")), 4, 1);
        display.addItem(new GuiItem(finubar(player), e -> player.performCommand("finubarQuests")), 5, 1);

        display.addItem(new GuiItem(guiHelper.backButton(), e -> player.performCommand("quests")), 4, 3);

        gui.addPane(background);
        gui.addPane(display);
        gui.show(player);
    }


    private ItemStack enchanter(Player player) {
        final ItemStack yard = new ItemStack(SkullCreator.itemFromBase64("eyJ0aW1lc3RhbXAiOjE1NTQyMjkyMzIzMDksInByb2ZpbGVJZCI6IjkxZmUxOTY4N2M5MDQ2NTZhYTFmYzA1OTg2ZGQzZmU3IiwicHJvZmlsZU5hbWUiOiJoaGphYnJpcyIsInNpZ25hdHVyZVJlcXVpcmVkIjp0cnVlLCJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzZiNzk1MzZkZjA4ZTI4ODQxMjI0OGIxNTNkZDVlNDMwYmE1YTc4ZTE0YWNkZTc5MDU1NDMzNjNkYzg4MGRhOCJ9fX0="));
        final ItemMeta yardItemMeta = yard.getItemMeta();

        yardItemMeta.displayName(Component.text(ChatColor.GREEN + "Enchanter " + parsePlaceholder(player, "betonquest_default-Points:point.Enchanter.amount") + ChatColor.GREEN + "/" + "1"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View Quests");

        yard.setItemMeta(yardItemMeta);
        yard.setLore(lore);

        return yard;
    }

    private ItemStack joy(Player player) {
        final ItemStack yard = new ItemStack(SkullCreator.itemFromBase64("eyJ0aW1lc3RhbXAiOjE1MTQ5MTIyNzQ3NDksInByb2ZpbGVJZCI6ImI0NDkxZGI5NmU4MjQ0MTRiMjRiZmZmZDlhNzU3MDJkIiwicHJvZmlsZU5hbWUiOiJOdXJzZV9Kb3kiLCJzaWduYXR1cmVSZXF1aXJlZCI6dHJ1ZSwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzRlNzYyMjg1NDBjYjc1ZWFhMmY4Yzc2OWM4OGJkY2E1YmM2YzllZThlNGI3NjQzNjgwYTJlNGFmYzEwZGY4MSJ9fX0="));
        final ItemMeta yardItemMeta = yard.getItemMeta();

        yardItemMeta.displayName(Component.text(ChatColor.GREEN + "Joy " + parsePlaceholder(player, "betonquest_default-Points:point.Joy.amount") + ChatColor.GREEN + "/" + "1"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View Quests");

        yard.setItemMeta(yardItemMeta);
        yard.setLore(lore);

        return yard;
    }

    private ItemStack finubar(Player player) {
        final ItemStack yard = new ItemStack(SkullCreator.itemFromBase64("eyJ0aW1lc3RhbXAiOjE1ODc4OTg4Njg4NTEsInByb2ZpbGVJZCI6IjQ4YTVlODJkODNkZjQyMDliZmNiYjNjYmZiZWI5NDY5IiwicHJvZmlsZU5hbWUiOiJraW5nbG9sMTIzNDUiLCJzaWduYXR1cmVSZXF1aXJlZCI6dHJ1ZSwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzRmMTg0MDEyYzU5NDMxYjM3NGMzY2UyZGUwOTlkYWQ0ZDg2ZDJhOGFmYWNhODY3NmQ2ZWE4YzlkYzUyZTliM2IifX19"));
        final ItemMeta yardItemMeta = yard.getItemMeta();

        yardItemMeta.displayName(Component.text(ChatColor.GREEN + "Finubar " + parsePlaceholder(player, "betonquest_default-Points:point.Finubar.amount") + ChatColor.GREEN + "/" + "1"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View Quests");

        yard.setItemMeta(yardItemMeta);
        yard.setLore(lore);

        return yard;
    }

    private String parsePlaceholder(Player player, String string) {
        return PlaceholderAPI.setPlaceholders(player, "%" + string + "%");
    }

}

