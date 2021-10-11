package monzter.adventurescraft.plugin.network.NarutoGamemode.GUIs;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Dependency;
import co.aikar.commands.annotation.Optional;
import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import me.clip.placeholderapi.PlaceholderAPI;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.network.NarutoGamemode.Enums.Class;
import monzter.adventurescraft.plugin.utilities.GUI.GUIHelper;
import monzter.adventurescraft.plugin.utilities.GUI.GUIHelperImplStatic;
import monzter.adventurescraft.plugin.utilities.beton.BetonPointsManager;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ClassSelection extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final SoundManager soundManager;
    private final GUIHelper guiHelper;
    private final BetonPointsManager betonPointsManager;


    public ClassSelection(AdventuresCraft plugin, SoundManager soundManager, GUIHelper guiHelper, BetonPointsManager betonPointsManager) {
        this.plugin = plugin;
        this.soundManager = soundManager;
        this.guiHelper = guiHelper;
        this.betonPointsManager = betonPointsManager;
    }

    @CommandAlias("NarutoSkillTree")
    public void profileMenu(Player player, @Optional String className) {


        ChestGui gui = new ChestGui(6, guiHelper.guiName("Naruto Skill Tree"));
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        OutlinePane background = new OutlinePane(0, 0, 9, 6, Pane.Priority.LOWEST);
        StaticPane display = new StaticPane(0, 0, 9, 6, Pane.Priority.LOW);
        OutlinePane outline1 = new OutlinePane(1, 1, 7, 1, Pane.Priority.LOW);


        background.addItem(new GuiItem(guiHelper.background(Material.LIGHT_BLUE_STAINED_GLASS_PANE)));
        background.setRepeat(true);

        int skill1 = 0;
        for (ClassItems item : ClassItems.values())
            if (item.getClassName().equals(className) && skill1 < 5) {
                outline1.addItem(new GuiItem(itemStack(item, player)));
                skill1++;
            }

//        display.addItem(new GuiItem(profile(player)), 4, 1);


        display.addItem(new GuiItem(guiHelper.backButton(), e -> player.performCommand("main")), 4, 5);

        gui.addPane(background);
        gui.addPane(display);
        gui.addPane(outline1);
        gui.show(player);
    }

    private ItemStack itemStack(ClassItems item, Player player) {
        ItemStack itemStack = item.getItemStack();
        ItemMeta itemMeta = itemStack.getItemMeta();

////        if (claimed)
////            itemMeta.setDisplayName(itemMeta.getDisplayName() + ChatColor.YELLOW + ChatColor.BOLD + "CLAIMED");
////        else if (level && enoughPoints && previousClaimed)
////            itemMeta.setDisplayName(itemMeta.getDisplayName() + ChatColor.GREEN + ChatColor.BOLD + "CLAIMABLE");
////        else
////            itemMeta.setDisplayName(itemMeta.getDisplayName() + ChatColor.RED + ChatColor.BOLD + "LOCKED");
////
//////        itemMeta.setDisplayName(itemMeta.getDisplayName().replaceAll("(%.*?%)", PlaceholderAPI.setPlaceholders(player, "%" + StringUtils.substringBetween(itemMeta.getDisplayName(), "%", "%") + "%")));
////        List<String> newLore = new ArrayList<>();
////        for (String lore : item.getItemStack().getLore()) {
////            if (lore.contains("%"))
////                lore = lore.replaceAll("(%.*?%)", PlaceholderAPI.setPlaceholders(player, "%" + StringUtils.substringBetween(lore, "%", "%") + "%"));
////            newLore.add(lore);
////        }
////
////        if (!claimed && level && enoughPoints && previousClaimed) {
////            newLore.add("");
////            newLore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Left-Click to Upgrade");
////        } else if (!claimed && level && enoughPoints && !previousClaimed) {
////            newLore.add("");
////            newLore.add(ChatColor.RED + "Upgrade previous level first!");
////        } else if (!claimed) {
////            newLore.add("");
////            newLore.add(ChatColor.RED + ChatColor.BOLD.toString() + "LOCKED");
////        }
//
//        itemMeta.setLore(newLore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }


    private String parsePlaceholder(Player player, String string) {
        return PlaceholderAPI.setPlaceholders(player, "%" + string + "%");
    }

    enum ClassItems {
        NARUTO(GUIHelperImplStatic.itemCreator(Material.LIME_DYE, ChatColor.GOLD.toString() + ChatColor.BOLD + "Naruto",
                new String[]{"&8Level: &f%ac_CLASS_NARUTO%",
                        "",
                        ChatColor.translateAlternateColorCodes('&', "&6&lABILITIES:"),
                        ChatColor.translateAlternateColorCodes('&', "&7- &aShadow Clone Jutsu"),
                        ChatColor.translateAlternateColorCodes('&', "&7- &aRashingan"),
                        ChatColor.translateAlternateColorCodes('&', "&7- &aRashin Shuriken")}, false, 0), Class.NARUTO_BASE, null),
        ;
        public ItemStack itemStack;
        public Class className;
        public String permission;

        ClassItems(ItemStack itemStack, Class className, String permission) {
            this.itemStack = itemStack;
            this.className = className;
            this.permission = permission;
        }

        public Class getClassName() {
            return className;
        }

        public ItemStack getItemStack() {
            return itemStack;
        }
    }

}

