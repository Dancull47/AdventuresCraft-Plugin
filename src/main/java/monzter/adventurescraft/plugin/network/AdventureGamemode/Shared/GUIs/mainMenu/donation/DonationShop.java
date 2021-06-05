package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.mainMenu.donation;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Dependency;
import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.network.PrisonGamemode.shared.events.extras.DonationRewardList;
import monzter.adventurescraft.plugin.utilities.GUI.GUIHelper;
import monzter.adventurescraft.plugin.utilities.enums.Prefix;
import monzter.adventurescraft.plugin.utilities.enums.PrisonStatsDisplay;
import monzter.adventurescraft.plugin.utilities.general.ConsoleCommand;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import monzter.adventurescraft.plugin.utilities.text.NumberFormat;
import net.Indyuce.mmoitems.MMOItems;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class DonationShop extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final SoundManager soundManager;
    private final GUIHelper guiHelper;
    private final ConsoleCommand consoleCommand;
    private final NumberFormat numberFormat;

    public DonationShop(AdventuresCraft plugin, SoundManager soundManager, GUIHelper guiHelper, ConsoleCommand consoleCommand, NumberFormat numberFormat) {
        this.plugin = plugin;
        this.soundManager = soundManager;
        this.guiHelper = guiHelper;
        this.consoleCommand = consoleCommand;
        this.numberFormat = numberFormat;
    }

    @CommandAlias("donateShop|donationShop")
    public void donate(Player player) {

        ChestGui gui = new ChestGui(6, guiHelper.guiName("Adventure Store"));
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        OutlinePane background = new OutlinePane(0, 0, 9, 6, Pane.Priority.LOWEST);
        StaticPane display = new StaticPane(0, 0, 9, 6, Pane.Priority.LOW);

        background.addItem(new GuiItem(guiHelper.background(Material.RED_STAINED_GLASS_PANE)));
        background.setRepeat(true);

        display.addItem(new GuiItem(guiHelper.backButton(), e -> player.performCommand("donationMenu")), 4, 5);

        for (DonationRewardList reward : DonationRewardList.values()) {
            ItemStack itemStack = MMOItems.plugin.getItem(reward.type, reward.getItemID());

            if (itemStack != null) {
                final ItemMeta itemMeta = itemStack.getItemMeta();
                List<Component> lore = itemStack.lore();
                if (lore == null) {
                    lore = new ArrayList<>();
                } else if (!lore.isEmpty()) {
                    lore.add(Component.empty());
                }
                lore.add(Component.text(ChatColor.WHITE + "Price: " + ChatColor.GOLD + numberFormat.numberFormat(reward.getPrice()) + " " + PrisonStatsDisplay.ADVENTURE_COINS.getName()));
                lore.add(Component.text(" "));
                lore.add(Component.text(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Left-Click to Purchase"));
                if (reward.type.equalsIgnoreCase("PET") || reward.type.equalsIgnoreCase("CONSUMABLE"))
                    lore.add(Component.text(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Right-Click to View Rewards"));

                itemMeta.lore(lore);
                itemStack.setItemMeta(itemMeta);

            } else {
                itemStack = new ItemStack(Material.valueOf(reward.getItemID()));
                final ItemMeta itemMeta = itemStack.getItemMeta();

                List<String> lore = new ArrayList<>();
                switch (reward.getId()) {
                    case "PetSlot":
                        itemMeta.setDisplayName(ChatColor.GOLD + "Pet Slot");
                        lore.add(" ");
                        lore.add(ChatColor.GRAY + "Gain an additional " + ChatColor.GREEN + "Pet Slot");
                        lore.add(ChatColor.GRAY + "allowing you to hold an extra " + ChatColor.GREEN + "Pet" + ChatColor.GRAY + "!");
                        lore.add(" ");
                        lore.add(ChatColor.DARK_GRAY + "This item may only be");
                        lore.add(ChatColor.DARK_GRAY + "purchased twice per player!");
                        break;
                    case "UnlimitedWeight":
                        itemMeta.setDisplayName(ChatColor.GOLD + "Unlimited Weight");
                        lore.add(" ");
                        lore.add(ChatColor.GRAY + "Increase your " + ChatColor.BLUE + "Weight " + ChatColor.GRAY + "to infinity!");
                        lore.add(" ");
                        lore.add(ChatColor.DARK_GRAY + "This item may only be");
                        lore.add(ChatColor.DARK_GRAY + "purchased once per player!");
                        break;
                }
                lore.add("");
                lore.add((ChatColor.WHITE + "Price: " + ChatColor.GOLD + numberFormat.numberFormat(reward.getPrice()) + " " + PrisonStatsDisplay.ADVENTURE_COINS.getName()));
                lore.add((" "));
                lore.add((Prefix.PREFIX.getString() + ChatColor.YELLOW + "Left-Click to Purchase"));

                itemMeta.setLore(lore);
                itemStack.setItemMeta(itemMeta);
            }

            switch (reward.getId()) {
                case "PetSlot":
                    display.addItem(new GuiItem(itemStack.asQuantity(2), e -> player.performCommand("donationrewards PetSlot")), 4, 1);
                    break;
                case "UnlimitedWeight":
                    display.addItem(new GuiItem(itemStack.asQuantity(1), e -> player.performCommand("donationrewards UnlimitedWeight")), 4, 3);
                    break;

                case "ExoticLootbox5":
                    display.addItem(new GuiItem(itemStack.asQuantity(5), e -> {
                        if (e.isLeftClick())
                            player.performCommand("donationrewards " + reward.getId());
                        if (e.isRightClick())
                            player.performCommand("droptableviewer lootbox5");
                    }), 1, 1);
                    break;
                case "ExoticLootbox10":
                    display.addItem(new GuiItem(itemStack.asQuantity(10), e -> {
                        if (e.isLeftClick())
                            player.performCommand("donationrewards " + reward.getId());
                        if (e.isRightClick())
                            player.performCommand("droptableviewer lootbox5");
                    }), 2, 1);
                    break;

                case "MythicalLootbox5":
                    display.addItem(new GuiItem(itemStack.asQuantity(5), e -> {
                        if (e.isLeftClick())
                            player.performCommand("donationrewards " + reward.getId());
                        if (e.isRightClick())
                            player.performCommand("droptableviewer lootbox6");
                    }), 1, 2);
                    break;
                case "MythicalLootbox10":
                    display.addItem(new GuiItem(itemStack.asQuantity(10), e -> {
                        if (e.isLeftClick())
                            player.performCommand("donationrewards " + reward.getId());
                        if (e.isRightClick())
                            player.performCommand("droptableviewer lootbox6");
                    }), 2, 2);
                    break;

                case "GodlyLootbox5":
                    display.addItem(new GuiItem(itemStack.asQuantity(5), e -> {
                        if (e.isLeftClick())
                            player.performCommand("donationrewards " + reward.getId());
                        if (e.isRightClick())
                            player.performCommand("droptableviewer lootbox7");
                    }), 1, 3);
                    break;
                case "GodlyLootbox10":
                    display.addItem(new GuiItem(itemStack.asQuantity(10), e -> {
                        if (e.isLeftClick())
                            player.performCommand("donationrewards " + reward.getId());
                        if (e.isRightClick())
                            player.performCommand("droptableviewer lootbox7");
                    }), 2, 3);
                    break;


                case "ExoticPetEgg5":
                    display.addItem(new GuiItem(itemStack.asQuantity(5), e -> {
                        if (e.isLeftClick())
                            player.performCommand("donationrewards " + reward.getId());
                        if (e.isRightClick())
                            player.performCommand("droptableviewer exoticpetegg");
                    }), 6, 1);
                    break;
                case "ExoticPetEgg10":
                    display.addItem(new GuiItem(itemStack.asQuantity(10), e -> {
                        if (e.isLeftClick())
                            player.performCommand("donationrewards " + reward.getId());
                        if (e.isRightClick())
                            player.performCommand("droptableviewer exoticpetegg");
                    }), 7, 1);
                    break;

                case "MythicalPetEgg5":
                    display.addItem(new GuiItem(itemStack.asQuantity(5), e -> {
                        if (e.isLeftClick())
                            player.performCommand("donationrewards " + reward.getId());
                        if (e.isRightClick())
                            player.performCommand("droptableviewer mythicalpetegg");
                    }), 6, 2);
                    break;
                case "MythicalPetEgg10":
                    display.addItem(new GuiItem(itemStack.asQuantity(10), e -> {
                        if (e.isLeftClick())
                            player.performCommand("donationrewards " + reward.getId());
                        if (e.isRightClick())
                            player.performCommand("droptableviewer mythicalpetegg");
                    }), 7, 2);
                    break;

                case "GodlyPetEgg5":
                    display.addItem(new GuiItem(itemStack.asQuantity(5), e -> {
                        if (e.isLeftClick())
                            player.performCommand("donationrewards " + reward.getId());
                        if (e.isRightClick())
                            player.performCommand("droptableviewer godlypetegg");
                    }), 6, 3);
                    break;
                case "GodlyPetEgg10":
                    display.addItem(new GuiItem(itemStack.asQuantity(10), e -> {
                        if (e.isLeftClick())
                            player.performCommand("donationrewards " + reward.getId());
                        if (e.isRightClick())
                            player.performCommand("droptableviewer godlypetegg");
                    }), 7, 3);
                    break;

                case "LegendaryPhoenixPetEgg5":
                    display.addItem(new GuiItem(itemStack.asQuantity(5), e -> {
                        if (e.isLeftClick())
                            player.performCommand("donationrewards " + reward.getId());
                        if (e.isRightClick())
                            player.performCommand("droptableviewer phoenixpetegg2");
                    }), 3, 1);
                    break;
                case "LegendaryPhoenixPetEgg10":
                    display.addItem(new GuiItem(itemStack.asQuantity(10), e -> {
                        if (e.isLeftClick())
                            player.performCommand("donationrewards " + reward.getId());
                        if (e.isRightClick())
                            player.performCommand("droptableviewer phoenixpetegg2");
                    }), 5, 1);
                    break;

                case "LegendaryDragonPetEgg5":
                    display.addItem(new GuiItem(itemStack.asQuantity(5), e -> {
                        if (e.isLeftClick())
                            player.performCommand("donationrewards " + reward.getId());
                        if (e.isRightClick())
                            player.performCommand("droptableviewer dragonpetegg2");
                    }), 3, 3);
                    break;
                case "LegendaryDragonPetEgg10":
                    display.addItem(new GuiItem(itemStack.asQuantity(10), e -> {
                        if (e.isLeftClick())
                            player.performCommand("donationrewards " + reward.getId());
                        if (e.isRightClick())
                            player.performCommand("droptableviewer dragonpetegg2");
                    }), 5, 3);
                    break;
            }
        }

        gui.addPane(background);
        gui.addPane(display);
        gui.show(player);
    }


}

