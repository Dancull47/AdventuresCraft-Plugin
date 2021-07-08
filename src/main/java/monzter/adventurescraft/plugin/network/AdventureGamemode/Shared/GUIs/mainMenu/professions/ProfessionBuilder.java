package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.mainMenu.professions;

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
import monzter.adventurescraft.plugin.utilities.enums.Prefix;
import monzter.adventurescraft.plugin.utilities.enums.Professions;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import monzter.adventurescraft.plugin.utilities.text.NumberFormat;
import monzter.adventurescraft.plugin.utilities.text.ProgressBar;
import org.apache.commons.lang.WordUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ProfessionBuilder extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final SoundManager soundManager;
    private final GUIHelper guiHelper;
    private final ProgressBar progressBar;
    private final NumberFormat numberFormat;
    private final String KNOWLEDGE = "  " + ChatColor.GOLD + ChatColor.BOLD + "KNOWLEDGE" + ChatColor.WHITE + ":";


    public ProfessionBuilder(AdventuresCraft plugin, SoundManager soundManager, GUIHelper guiHelper, ProgressBar progressBar, NumberFormat numberFormat) {
        this.plugin = plugin;
        this.soundManager = soundManager;
        this.guiHelper = guiHelper;
        this.progressBar = progressBar;
        this.numberFormat = numberFormat;
    }


    @CommandAlias("FarmingProfession")
    private void farmingProfession(Player player) {
        menuGenerator(player, Professions.FARMING, Material.GREEN_STAINED_GLASS_PANE);
    }

    @CommandAlias("slayerProfession")
    private void slayerProfession(Player player) {
        menuGenerator(player, Professions.SLAYER, Material.RED_STAINED_GLASS_PANE);
    }

    @CommandAlias("ForagingProfession")
    private void ForagingProfession(Player player) {
        menuGenerator(player, Professions.FORAGING, Material.GREEN_STAINED_GLASS_PANE);
    }

    @CommandAlias("MiningProfession")
    private void MiningProfession(Player player) {
        menuGenerator(player, Professions.MINING, Material.LIGHT_GRAY_STAINED_GLASS_PANE);
    }

    @CommandAlias("SpellforgingProfession")
    private void SpellforgingProfession(Player player) {
        menuGenerator(player, Professions.SPELLFORGING, Material.PINK_STAINED_GLASS_PANE);
    }

    @CommandAlias("EnchantingProfession")
    private void EnchantingProfession(Player player) {
        menuGenerator(player, Professions.ENCHANTING, Material.PURPLE_STAINED_GLASS_PANE);
    }

    @CommandAlias("CookingProfession")
    private void CookingProfession(Player player) {
        menuGenerator(player, Professions.COOKING, Material.ORANGE_STAINED_GLASS_PANE);
    }


//    public void createMenu(ChestGui gui, List<ProfessionLevels> guiContents, Player player) {
//
//        gui.setOnGlobalClick(event -> event.setCancelled(true));
//
//        OutlinePane background = new OutlinePane(0, 0, 9, 6, Pane.Priority.LOWEST);
//        StaticPane pane = new StaticPane(0, 0, 9, 6, Pane.Priority.LOW);
//        OutlinePane display = new OutlinePane(2, 2, 5, 2, Pane.Priority.LOW);
//
//        background.addItem(new GuiItem(guiHelper.background(Material.LIME_STAINED_GLASS_PANE)));
//        background.setRepeat(true);
//
//        int level = Integer.valueOf(parsePlaceholder(player, "mmocore_profession_Farming"));
//
//        int i = 1;
//        int amount = 0;
//        for (ProfessionLevels item : guiContents) {
//            ItemStack itemStack = new ItemStack(Material.WOODEN_HOE);
//            final ItemMeta itemMeta = itemStack.getItemMeta();
//
//            itemMeta.setDisplayName(ChatColor.YELLOW + "Farming Level " + i);
//
//            List<String> lore = new ArrayList<>();
//            lore.add(" ");
//            lore.add(KNOWLEDGE);
//            for (ItemStack itemStack1 : reward.getRewards()) {
//                plugin.getLogger().info(itemStack1.getItemMeta().getDisplayName());
//                lore.add("   " + Prefix.PREFIX.getString() + itemStack1.getItemMeta().getDisplayName());
//                amount++;
//            }
//            amount = 0;
//
//            if (level < Integer.valueOf(reward.getLevel())) {
//                itemMeta.setDisplayName(ChatColor.GREEN + "Farming Level " + i + ChatColor.DARK_GRAY + " -" + ChatColor.RED + ChatColor.BOLD + " LOCKED");
//                lore.add(" ");
//                lore.add(ChatColor.RED.toString() + ChatColor.BOLD + "LOCKED");
//            } else {
//                itemStack.setType(Material.STONE_HOE);
//                itemMeta.setDisplayName(ChatColor.GREEN + "Farming Level " + i + ChatColor.DARK_GRAY + " -" + ChatColor.GREEN + ChatColor.BOLD + " UNLOCKED");
//                lore.add(" ");
//                lore.add(ChatColor.GREEN.toString() + ChatColor.BOLD + "UNLOCKED");
//            }
//
//            itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
//
//            itemMeta.setLore(lore);
//            itemStack.setItemMeta(itemMeta);
//
//            display.addItem(new GuiItem(itemStack));
//            i++;
//        }
//
//
//        pane.addItem(new GuiItem(guiHelper.backButton(), e -> player.performCommand("professions")), 4, 5);
//        pane.addItem(new GuiItem(farming(player)), 4, 1);
//
//        gui.addPane(background);
//        gui.addPane(display);
//        gui.addPane(pane);
//    }

    public void menuGenerator(Player player, Professions profession, Material backgroundColor) {
        final ChestGui gui = new ChestGui(6, WordUtils.capitalizeFully(profession.name()));
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        OutlinePane background = new OutlinePane(0, 0, 9, 6, Pane.Priority.LOWEST);
        StaticPane pane = new StaticPane(0, 0, 9, 6, Pane.Priority.LOW);
        OutlinePane display = new OutlinePane(2, 2, 5, 2, Pane.Priority.LOW);

        background.addItem(new GuiItem(guiHelper.background(backgroundColor)));
        background.setRepeat(true);

        int level = Integer.valueOf(parsePlaceholder(player, "mmocore_profession_" + WordUtils.capitalizeFully(profession.name())));

        int i = 1;
        int amount = 0;
        ItemStack itemStack = new ItemStack(Material.valueOf("WOODEN_SWORD"));
        Material material = Material.PAPER;
        for (ProfessionLevels item : ProfessionLevels.values()) {
            if (item.getProfession() == profession) {
                switch (item.getProfession().name()) {
                    case "FARMING":
                        itemStack = new ItemStack(Material.WOODEN_HOE);
                        material = Material.STONE_HOE;
                        break;
                    case "SLAYER":
                        itemStack = new ItemStack(Material.WOODEN_SWORD);
                        material = Material.STONE_SWORD;
                        break;
                    case "MINING":
                        itemStack = new ItemStack(Material.WOODEN_PICKAXE);
                        material = Material.STONE_PICKAXE;
                        break;
                    case "FORAGING":
                        itemStack = new ItemStack(Material.WOODEN_AXE);
                        material = Material.STONE_AXE;
                        break;
                    case "SPELLFORGING":
                        itemStack = new ItemStack(Material.PAPER);
                        material = Material.STICK;
                        break;
                    case "COOKING":
                        itemStack = new ItemStack(Material.BOWL);
                        material = Material.MUSHROOM_STEW;
                        break;
                    case "ENCHANTING":
                        itemStack = new ItemStack(Material.BOWL);
                        material = Material.ENCHANTED_BOOK;
                        break;
                }

                final ItemMeta itemMeta = itemStack.getItemMeta();

                itemMeta.setDisplayName(ChatColor.YELLOW + WordUtils.capitalizeFully(item.getProfession().name()) + " Level " + i);

                List<String> lore = new ArrayList<>();
                lore.add(" ");
                lore.add(KNOWLEDGE);
                for (ItemStack itemStack1 : item.getRewards()) {
                    plugin.getLogger().info(itemStack1.getItemMeta().getDisplayName());
                    lore.add("   " + Prefix.PREFIX.getString() + itemStack1.getItemMeta().getDisplayName());
                    amount++;
                }
                amount = 0;

                if (level < Integer.valueOf(item.getLevel())) {
                    itemMeta.setDisplayName(ChatColor.GREEN + WordUtils.capitalizeFully(item.getProfession().name()) + " Level " + i + ChatColor.DARK_GRAY + " -" + ChatColor.RED + ChatColor.BOLD + " LOCKED");
                    lore.add(" ");
                    lore.add(ChatColor.RED.toString() + ChatColor.BOLD + "LOCKED");
                } else {
                    itemStack.setType(material);
                    itemMeta.setDisplayName(ChatColor.GREEN + WordUtils.capitalizeFully(item.getProfession().name()) + " Level " + i + ChatColor.DARK_GRAY + " -" + ChatColor.GREEN + ChatColor.BOLD + " UNLOCKED");
                    lore.add(" ");
                    lore.add(ChatColor.GREEN.toString() + ChatColor.BOLD + "UNLOCKED");
                }

                itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

                itemMeta.setLore(lore);
                itemStack.setItemMeta(itemMeta);

                display.addItem(new GuiItem(itemStack));
                i++;
            }
        }


        pane.addItem(new GuiItem(guiHelper.backButton(), e -> player.performCommand("professions")), 4, 5);
        pane.addItem(new GuiItem(professionInfo(player, profession)), 4, 1);

        gui.addPane(background);
        gui.addPane(display);
        gui.addPane(pane);
        gui.show(player);
    }
//    public void menuGenerator(Player player, Professions profession, Material backgroundColor) {
//        int npcAmount = 0;
//        int startX = 2;
//        int startY = 1;
//        int length = 5;
//        int height = 3;
//        for (ProfessionLevels professions : ProfessionLevels.values())
//            if (professions.getProfession() == profession)
//                npcAmount += 1;
//
//        if (npcAmount == 1)
//            startX = 4;
//        else if (npcAmount == 2 || npcAmount == 3)
//            startX = 3;
//        else if (npcAmount == 4)
//            startX = 2;
//
//        int questAmount = 0;
//        for (ProfessionLevels questGiver : ProfessionLevels.values())
//            if (questGiver.getQ() == profession)
//                questAmount += questGiver.getQuestAmount();
//
//        ChestGui gui = new ChestGui(height + 1, guiHelper.guiName(profession.getName() + " Quests " + parsePlaceholder(player, "betonquest_default-Points:point." + profession.getName().replace(" ", "").replace("GoblinTown", "GoblinVillage") + ".amount") + "/" + questAmount));
//        gui.setOnGlobalClick(event -> event.setCancelled(true));
//
//        OutlinePane background = new OutlinePane(0, 0, 9, height + 1, Pane.Priority.LOWEST);
//        StaticPane display = new StaticPane(0, 0, 9, height + 1, Pane.Priority.LOW);
//        OutlinePane main = new OutlinePane(startX, startY, length, height - 2, Pane.Priority.LOW);
//
//        background.addItem(new GuiItem(guiHelper.background(backgroundColor)));
//        background.setRepeat(true);
//        for (ProfessionLevels questGiver : ProfessionLevels.values())
//            if (questGiver.getArea() == profession)
//                main.addItem(itemGenerator(player, questGiver));
//
//        display.addItem(new GuiItem(guiHelper.backButton(), e -> player.performCommand("quests")), 4, height);
//
//        gui.addPane(background);
//        gui.addPane(display);
//        gui.addPane(main);
//        gui.show(player);
//    }


    private ItemStack professionInfo(Player player, Professions professions) {
        Material material = Material.PAPER;
        switch (professions.name()) {
            case "FARMING":
                break;
            case "SLAYER":
                material = Material.DIAMOND_SWORD;
                break;
            case "MINING":
                material = Material.DIAMOND_PICKAXE;
                break;
            case "FORAGING":
                material = Material.DIAMOND_AXE;
                break;
            case "SPELLFORGING":
                material = Material.BOOK;
                break;
            case "COOKING":
                material = Material.MUSHROOM_STEW;
                break;
            case "ENCHANTING":
                material = Material.ENCHANTED_BOOK;
                break;
        }

        int level = Integer.valueOf(parsePlaceholder(player, "mmocore_profession_" + WordUtils.capitalizeFully(professions.name())));
        String name = ChatColor.GREEN + WordUtils.capitalizeFully(professions.name()) + " Level " + ChatColor.GOLD + level;
        return guiHelper.itemCreator(material, name, new String[]{
                ChatColor.GRAY + "Help around the Farm, and",
                ChatColor.GRAY + "harvest crops to earn XP!",
                "",
                ChatColor.WHITE + "Progress to Level " + ChatColor.GOLD + Integer.valueOf(level + 1),
                progressBar.getProgressBar(Integer.valueOf(parsePlaceholder(player, "mmocore_profession_experience_" + WordUtils.capitalizeFully(professions.name()))), Integer.valueOf(parsePlaceholder(player, "mmocore_profession_next_level_" + WordUtils.capitalizeFully(professions.name()))), 13, '-', ChatColor.YELLOW, ChatColor.GRAY) + " " + ChatColor.GOLD + parsePlaceholder(player, "mmocore_profession_percent_" + WordUtils.capitalizeFully(professions.name())) + "%",
                ChatColor.GOLD + numberFormat.numberFormat(parsePlaceholder(player, "mmocore_profession_experience_" + WordUtils.capitalizeFully(professions.name()), true)) + ChatColor.WHITE + " / " + ChatColor.GOLD + numberFormat.numberFormat(parsePlaceholder(player, "mmocore_profession_next_level_" + WordUtils.capitalizeFully(professions.name()), true)) + ChatColor.GRAY + " EXP"
        });
    }

    private String parsePlaceholder(Player player, String string) {
        return PlaceholderAPI.setPlaceholders(player, "%" + string + "%");
    }

    private double parsePlaceholder(Player player, String string, boolean b) {
        return Double.parseDouble(PlaceholderAPI.setPlaceholders(player, "%" + string + "%"));
    }

}

