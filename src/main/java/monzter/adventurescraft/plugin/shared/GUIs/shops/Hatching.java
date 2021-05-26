package monzter.adventurescraft.plugin.shared.GUIs.shops;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Dependency;
import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.PaginatedPane;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import me.clip.placeholderapi.PlaceholderAPI;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.GUI.GUIHelper;
import monzter.adventurescraft.plugin.utilities.enums.PetEggList;
import monzter.adventurescraft.plugin.utilities.enums.Prefix;
import monzter.adventurescraft.plugin.utilities.enums.StatsDisplay;
import monzter.adventurescraft.plugin.utilities.general.ConsoleCommand;
import monzter.adventurescraft.plugin.utilities.general.FullInventory;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import monzter.adventurescraft.plugin.utilities.mmoitems.MMOItemsGive;
import monzter.adventurescraft.plugin.utilities.text.NumberFormat;
import monzter.adventurescraft.plugin.utilities.vault.Economy;
import net.Indyuce.mmoitems.MMOItems;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Hatching extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final SoundManager soundManager;
    private final GUIHelper guiHelper;
    private final ConsoleCommand consoleCommand;
    private final Economy economy;
    private final FullInventory fullInventory;
    private final MMOItemsGive mmoItemsGive;
    private final NumberFormat numberFormat;

    public Hatching(AdventuresCraft plugin, SoundManager soundManager, GUIHelper guiHelper, ConsoleCommand consoleCommand, Economy economy, FullInventory fullInventory, MMOItemsGive mmoItemsGive, NumberFormat numberFormat) {
        this.plugin = plugin;
        this.soundManager = soundManager;
        this.guiHelper = guiHelper;
        this.consoleCommand = consoleCommand;
        this.economy = economy;
        this.fullInventory = fullInventory;
        this.mmoItemsGive = mmoItemsGive;
        this.numberFormat = numberFormat;
    }

    @CommandAlias("hatchingShop")
    public void pets(Player player) {
        if (player.hasPermission("SHOPS")) {
            final String petExp = PlaceholderAPI.setPlaceholders(player, "%betonquest_items:point.PetExperience.amount%");

            ChestGui gui = new ChestGui(6, guiHelper.guiName("Hatching"));
            gui.setOnGlobalClick(event -> event.setCancelled(true));

            PaginatedPane page = new PaginatedPane(0, 0, 9, 6);
            OutlinePane background = new OutlinePane(0, 0, 9, 6, Pane.Priority.LOWEST);
            OutlinePane display = new OutlinePane(1, 1, 7, 1, Pane.Priority.LOW);
            OutlinePane display2 = new OutlinePane(1, 2, 7, 1, Pane.Priority.LOW);
            OutlinePane phoenixBuy = new OutlinePane(1, 3, 2, 1, Pane.Priority.LOW);
            OutlinePane phoenixHatch = new OutlinePane(1, 4, 2, 1, Pane.Priority.LOW);
            OutlinePane dragonBuy = new OutlinePane(6, 3, 2, 1, Pane.Priority.LOW);
            OutlinePane dragonHatch = new OutlinePane(6, 4, 2, 1, Pane.Priority.LOW);

            page.addPane(0, background);
            page.addPane(0, display);
            page.addPane(0, display2);
            page.addPane(0, phoenixBuy);
            page.addPane(0, phoenixHatch);
            page.addPane(0, dragonBuy);
            page.addPane(0, dragonHatch);

            background.addItem(new GuiItem(guiHelper.background(Material.GREEN_STAINED_GLASS_PANE)));
            background.setRepeat(true);

            int i = 0;
            for (HatchingList hatching : HatchingList.values()) {
                if (i < 7)
                    display.addItem(new GuiItem(buyEgg(economy.getBalance(player), hatching), e -> purchase(player, hatching.getItemID(), hatching.getPrice())));
                if (i > 6 && i < 14)
                    display2.addItem(new GuiItem(hatchEgg(Integer.valueOf(petExp), hatching), e -> {
                        if (e.isLeftClick())
                            player.performCommand("hatch " + hatching.getId());
                        if (e.isRightClick())
                            player.performCommand("droptableviewer " + hatching.getId()+"petegg");

                    }));
                if (i > 13 && i < 16)
                    phoenixBuy.addItem(new GuiItem(buyEgg(economy.getBalance(player), hatching), e -> purchase(player, hatching.getItemID(), hatching.getPrice())));
                if (i > 15 && i < 18)
                    phoenixHatch.addItem(new GuiItem(hatchEgg(Integer.valueOf(petExp), hatching), e -> {
                        if (e.isLeftClick())
                            player.performCommand("hatch " + hatching.getId());
                        if (e.isRightClick())
                            player.performCommand("droptableviewer " + hatching.getId()+"petegg");

                    }));
                if (i > 17 && i < 20)
                    dragonBuy.addItem(new GuiItem(buyEgg(economy.getBalance(player), hatching), e -> purchase(player, hatching.getItemID(), hatching.getPrice())));
                if (i > 19 && i < 22)
                    dragonHatch.addItem(new GuiItem(hatchEgg(Integer.valueOf(petExp), hatching), e -> {
                        if (e.isLeftClick())
                            player.performCommand("hatch " + hatching.getId());
                        if (e.isRightClick())
                            player.performCommand("droptableviewer " + hatching.getId()+"petegg");

                    }));
                i++;
            }
            gui.addPane(page);
            gui.show(player);
        }
    }


    private void purchase(Player player, String itemID, double price) {
        if (economy.hasMoney(player, price)) {
            if (!fullInventory.fullInventory(player)) {
                economy.takeMoney(player, price);
                mmoItemsGive.giveMMOItem(player, "PET", itemID);
            }
        }
    }


    private ItemStack buyEgg(double balance, HatchingList hatching) {
        ItemStack hatchingItem = MMOItems.plugin.getItem("PET", hatching.getItemID());
        final ItemMeta hatchingItemItemMeta = hatchingItem.getItemMeta();
        if (hatchingItem != null) {
            List<Component> lore = hatchingItem.lore();
            if (lore == null) {
                lore = new ArrayList<>();
            } else if (!lore.isEmpty()) {
                lore.add(Component.empty());
            }
            lore.add(Component.text(ChatColor.WHITE + "Price: " + StatsDisplay.MONEY_AMOUNT.getName() + " " + numberFormat.numberFormat(hatching.getPrice())));

            if (balance >= hatching.getPrice()) {
                lore.add(Component.text(""));
                lore.add(Component.text(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Purchase"));
            }
            hatchingItemItemMeta.lore(lore);
            hatchingItem.setItemMeta(hatchingItemItemMeta);
        }
        return hatchingItem;
    }

    private ItemStack hatchEgg(int petEXPAmount, HatchingList hatching) {
        ItemStack hatchingItem = MMOItems.plugin.getItem("PET", hatching.getItemID());
        final ItemMeta hatchingItemItemMeta = hatchingItem.getItemMeta();
        hatchingItemItemMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
        hatchingItemItemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        if (hatchingItem != null) {
            List<Component> lore = hatchingItem.lore();
            if (lore == null) {
                lore = new ArrayList<>();
            } else if (!lore.isEmpty()) {
                lore.add(Component.empty());
            }
            lore.add(Component.text(ChatColor.WHITE + "Price: " + StatsDisplay.PET_EXPERIENCE_AMOUNT.getName() + " " + numberFormat.numberFormat(hatching.getPrice())));
            lore.add(Component.text(""));

            if (Integer.valueOf(petEXPAmount) >= hatching.getPrice())
                lore.add(Component.text(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Left-Click to Hatch"));

            lore.add(Component.text(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Right-Click to View Hatchlings"));

            hatchingItemItemMeta.lore(lore);
            hatchingItem.setItemMeta(hatchingItemItemMeta);
        }
        return hatchingItem;
    }
}

enum HatchingList {
    COMMON("Common", "PET_EGG", 500),
    UNCOMMON("Uncommon", "PET_EGG2", 10_000),
    RARE("Rare", "PET_EGG3", 100_000),
    LEGENDARY("Legendary", "PET_EGG4", 1_000_000),
    EXOTIC("Exotic", "PET_EGG5", 10_000_000),
    MYTHICAL("Mythical", "PET_EGG6", 50_000_000),
    GODLY("Godly", "PET_EGG7", 200_000_000),

    COMMONHatch("Common", "PET_EGG", PetEggList.COMMON.getExpToHatch()),
    UNCOMMONHatch("Uncommon", "PET_EGG2", PetEggList.UNCOMMON.getExpToHatch()),
    RAREHatch("Rare", "PET_EGG3", PetEggList.RARE.getExpToHatch()),
    LEGENDARYHatch("Legendary", "PET_EGG4", PetEggList.LEGENDARY.getExpToHatch()),
    EXOTICHatch("Exotic", "PET_EGG5", PetEggList.EXOTIC.getExpToHatch()),
    MYTHICALHatch("Mythical", "PET_EGG6", PetEggList.MYTHICAL.getExpToHatch()),
    GODLYHatch("Godly", "PET_EGG7", PetEggList.GODLY.getExpToHatch()),

    RAREPhoenixBuy("Phoenix", "PHOENIX_EGG", 10_000_000),
    LEGENDARYPhoenixBuy("Phoenix2", "PHOENIX_EGG2", 100_000_000),

    RAREPhoenixHatch("Phoenix", "PHOENIX_EGG", PetEggList.PHOENIX.getExpToHatch()),
    LEGENDARYPhoenixHatch("Phoenix2", "PHOENIX_EGG2", PetEggList.PHOENIX2.getExpToHatch()),

    RAREDragonBuy("Dragon", "DRAGON_EGG", 10_000_000),
    LEGENDARYDragonBuy("Dragon2", "DRAGON_EGG2", 100_000_000),

    RAREDragonHatch("Dragon", "DRAGON_EGG", PetEggList.DRAGON.getExpToHatch()),
    LEGENDARYDragonHatch("Dragon2", "DRAGON_EGG2", PetEggList.DRAGON2.getExpToHatch()),


    ;
    private String id;
    private String itemID;
    private double price;

    HatchingList(String id, String itemID, double price) {
        this.id = id;
        this.itemID = itemID;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public String getItemID() {
        return itemID;
    }
}
