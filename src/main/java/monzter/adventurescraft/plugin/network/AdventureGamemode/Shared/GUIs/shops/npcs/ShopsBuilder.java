package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.shops.npcs;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Dependency;
import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.PaginatedPane;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.util.Location;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.StringFlag;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.GUI.GUIHelper;
import monzter.adventurescraft.plugin.utilities.enums.Prefix;
import monzter.adventurescraft.plugin.utilities.general.ConsoleCommand;
import monzter.adventurescraft.plugin.utilities.general.FullInventory;
import monzter.adventurescraft.plugin.utilities.general.PurchaseUtils;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import monzter.adventurescraft.plugin.utilities.luckperms.PermissionLP;
import monzter.adventurescraft.plugin.utilities.mmoitems.MMOItemsGive;
import monzter.adventurescraft.plugin.utilities.text.NumberFormat;
import monzter.adventurescraft.plugin.utilities.vault.Economy;
import net.Indyuce.mmoitems.MMOItems;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ShopsBuilder extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final SoundManager soundManager;
    private final GUIHelper guiHelper;
    private final ConsoleCommand consoleCommand;
    private final Economy economy;
    private final FullInventory fullInventory;
    private final MMOItemsGive mmoItemsGive;
    private final NumberFormat numberFormat;
    private final PurchaseUtils purchaseUtils;
    private final MMOItems mmoItems;
    private final PermissionLP permissionLP;
    private final StringFlag displayNameFlag;
    final TextComponent donate = Component.text("You can donate to get epic rewards from our")
            .color(NamedTextColor.GREEN)
            .append(Component.text(" Store", NamedTextColor.GOLD))
            .append(Component.text("!"))
            .append(Component.text(" <- CLICK HERE", NamedTextColor.GOLD, TextDecoration.BOLD))
            .hoverEvent(Component.text("Click to visit the Store!", NamedTextColor.GREEN))
            .clickEvent(ClickEvent.openUrl("https://store.adventurescraft.net"));


    public ShopsBuilder(AdventuresCraft plugin, SoundManager soundManager, GUIHelper guiHelper, ConsoleCommand consoleCommand, Economy economy, FullInventory fullInventory, MMOItemsGive mmoItemsGive, NumberFormat numberFormat, PurchaseUtils purchaseUtils, MMOItems mmoItems, PermissionLP permissionLP, StringFlag displayNameFlag) {
        this.plugin = plugin;
        this.soundManager = soundManager;
        this.guiHelper = guiHelper;
        this.consoleCommand = consoleCommand;
        this.economy = economy;
        this.fullInventory = fullInventory;
        this.mmoItemsGive = mmoItemsGive;
        this.numberFormat = numberFormat;
        this.purchaseUtils = purchaseUtils;
        this.mmoItems = mmoItems;
        this.permissionLP = permissionLP;
        this.displayNameFlag = displayNameFlag;
    }

    @CommandAlias("SpellforgingShop")
    public void spellforgingShop(Player player) {
        if (check(player, ChatColor.GREEN + "Town")) {
            ChestGui gui = new ChestGui(3, guiHelper.guiName("Spellforging"));
            gui.setOnGlobalClick(event -> event.setCancelled(true));

            OutlinePane background = new OutlinePane(0, 0, 9, 3, Pane.Priority.LOWEST);
            StaticPane display = new StaticPane(0, 0, 9, 3, Pane.Priority.LOW);


            background.addItem(new GuiItem(guiHelper.background(Material.LIGHT_BLUE_STAINED_GLASS_PANE)));
            background.setRepeat(true);

            display.addItem(new GuiItem(guiHelper.itemCreator(Material.PAPER,
                    ChatColor.GREEN + "Spell Shop", new String[]{"", Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View"}),
                    e -> consoleCommand.consoleCommand("mi stations open spells " + player.getName())), 2, 1);
            display.addItem(new GuiItem(guiHelper.itemCreator(Material.BLAZE_ROD,
                    ChatColor.GREEN + "Material Shop", new String[]{"", Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View"}),
                    e -> consoleCommand.consoleCommand("mi stations open enchanted-materials " + player.getName())), 4, 1);
            display.addItem(new GuiItem(guiHelper.itemCreator(Material.STICK,
                    ChatColor.GREEN + "Wand Shop", new String[]{"", Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View"}),
                    e -> consoleCommand.consoleCommand("mi stations open wand " + player.getName())), 6, 1);

            gui.addPane(background);
            gui.addPane(display);
            gui.show(player);
        }
    }

    @CommandAlias("BakerShop")
    public void bakerShop(Player player) {
        if (check(player, ChatColor.GREEN + "Town")) {
            ChestGui gui = new ChestGui(3, guiHelper.guiName("Baker"));
            gui.setOnGlobalClick(event -> event.setCancelled(true));

            OutlinePane background = new OutlinePane(0, 0, 9, 3, Pane.Priority.LOWEST);
            StaticPane display = new StaticPane(0, 0, 9, 3, Pane.Priority.LOW);


            background.addItem(new GuiItem(guiHelper.background(Material.YELLOW_STAINED_GLASS_PANE)));
            background.setRepeat(true);

            display.addItem(new GuiItem(guiHelper.itemCreator(Material.MUSHROOM_STEW,
                    ChatColor.GREEN + "Stew Shop", new String[]{"", Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View"}),
                    e -> consoleCommand.consoleCommand("mi stations open stews " + player.getName())), 2, 1);
            display.addItem(new GuiItem(guiHelper.itemCreator(Material.BREWING_STAND,
                    ChatColor.GREEN + "Material Shop", new String[]{"", Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View"}),
                    e -> consoleCommand.consoleCommand("mi stations open enchanted-materials " + player.getName())), 4, 1);
            display.addItem(new GuiItem(guiHelper.itemCreator(Material.TOTEM_OF_UNDYING,
                    ChatColor.GREEN + "Totem Shop", new String[]{"", Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View"}),
                    e -> consoleCommand.consoleCommand("mi stations open totems " + player.getName())), 6, 1);

            gui.addPane(background);
            gui.addPane(display);
            gui.show(player);
        }
    }

    @CommandAlias("FarmerShop")
    public void farmerShop(Player player) {
        if (check(player, ChatColor.GREEN + "Town")) {
            ChestGui gui = new ChestGui(3, guiHelper.guiName("Farmer"));
            gui.setOnGlobalClick(event -> event.setCancelled(true));

            OutlinePane background = new OutlinePane(0, 0, 9, 3, Pane.Priority.LOWEST);
            StaticPane display = new StaticPane(0, 0, 9, 3, Pane.Priority.LOW);


            background.addItem(new GuiItem(guiHelper.background(Material.GREEN_STAINED_GLASS_PANE)));
            background.setRepeat(true);

            display.addItem(new GuiItem(guiHelper.itemCreator(Material.NETHERITE_HOE,
                    ChatColor.GREEN + "Farmer Shop", new String[]{"", Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View"}),
                    e -> consoleCommand.consoleCommand("mi stations open farming " + player.getName())), 3, 1);
            display.addItem(new GuiItem(guiHelper.itemCreator(Material.WHEAT,
                    ChatColor.GREEN + "Material Shop", new String[]{"", Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View"}),
                    e -> consoleCommand.consoleCommand("mi stations open enchanted-materials " + player.getName())), 5, 1);

            gui.addPane(background);
            gui.addPane(display);
            gui.show(player);
        }
    }

    @CommandAlias("ForagingShop|ForagerShop")
    public void foragingShop(Player player) {
        if (check(player, ChatColor.GREEN + "Town")) {
            ChestGui gui = new ChestGui(3, guiHelper.guiName("Forager"));
            gui.setOnGlobalClick(event -> event.setCancelled(true));

            OutlinePane background = new OutlinePane(0, 0, 9, 3, Pane.Priority.LOWEST);
            StaticPane display = new StaticPane(0, 0, 9, 3, Pane.Priority.LOW);


            background.addItem(new GuiItem(guiHelper.background(Material.GREEN_STAINED_GLASS_PANE)));
            background.setRepeat(true);

            display.addItem(new GuiItem(guiHelper.itemCreator(Material.NETHERITE_AXE,
                    ChatColor.GREEN + "Forager Shop", new String[]{"", Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View"}),
                    e -> consoleCommand.consoleCommand("mi stations open foraging " + player.getName())), 2, 1);
            display.addItem(new GuiItem(guiHelper.itemCreator(Material.OAK_WOOD,
                    ChatColor.GREEN + "Material Shop", new String[]{"", Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View"}),
                    e -> consoleCommand.consoleCommand("mi stations open enchanted-materials " + player.getName())), 4, 1);
            display.addItem(new GuiItem(guiHelper.itemCreator(Material.GOLDEN_AXE,
                    ChatColor.GREEN + "Foraging Upgrade Shop", new String[]{"", Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View"}),
                    e -> consoleCommand.consoleCommand("mi stations open foraging-upgrades " + player.getName())), 6, 1);

            gui.addPane(background);
            gui.addPane(display);
            gui.show(player);
        }
    }

    @CommandAlias("SlayerShop")
    public void slayerShop(Player player) {
        if (check(player, ChatColor.GREEN + "Town")) {
            ChestGui gui = new ChestGui(3, guiHelper.guiName("Slayer"));
            gui.setOnGlobalClick(event -> event.setCancelled(true));

            OutlinePane background = new OutlinePane(0, 0, 9, 3, Pane.Priority.LOWEST);
            StaticPane display = new StaticPane(0, 0, 9, 3, Pane.Priority.LOW);


            background.addItem(new GuiItem(guiHelper.background(Material.RED_STAINED_GLASS_PANE)));
            background.setRepeat(true);

            display.addItem(new GuiItem(guiHelper.itemCreator(Material.NETHERITE_SWORD,
                    ChatColor.GREEN + "Weapon Shop", new String[]{"", Prefix.PREFIX.getString() + ChatColor.YELLOW + "Coming soon..."})), 2, 1);
            display.addItem(new GuiItem(guiHelper.itemCreator(Material.BLAZE_POWDER,
                    ChatColor.GREEN + "Material Shop", new String[]{"", Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View"}),
                    e -> consoleCommand.consoleCommand("mi stations open enchanted-materials " + player.getName())), 4, 1);
            display.addItem(new GuiItem(guiHelper.itemCreator("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTY3ZTE4NjAyZTAzMDM1YWQ2ODk2N2NlMDkwMjM1ZDg5OTY2NjNmYjllYTQ3NTc4ZDNhN2ViYmM0MmE1Y2NmOSJ9fX0=",
                    ChatColor.GREEN + "Companion Shop", new String[]{"", Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View"}),
                    e -> consoleCommand.consoleCommand("mi stations open slayer " + player.getName())), 6, 1);

            gui.addPane(background);
            gui.addPane(display);
            gui.show(player);
        }
    }


    @CommandAlias("MiningShop")
    private void miningShop(Player player) {
        if (check(player, ChatColor.GREEN + "Town")) {
            int height = 5;
            final ChestGui gui = new ChestGui(height, guiHelper.guiName("Mining Shop"));
            final List<ItemList> guiContents = ItemList.getShop(Shops.MINING);
            menuBase(gui, guiContents, player, "Mining", height, Material.BLACK_STAINED_GLASS_PANE);
            gui.show(player);
        }
    }

    @CommandAlias("FarmShop")
    private void farmShop(Player player) {
        if (check(player, ChatColor.GREEN + "Farm")) {
            int height = 5;
            final ChestGui gui = new ChestGui(height, guiHelper.guiName("Farm Shop"));
            final List<ItemList> guiContents = ItemList.getShop(Shops.FARMER);
            menuBase(gui, guiContents, player, "Farm", height, Material.LIME_STAINED_GLASS_PANE);
            gui.show(player);
        }
    }

    @CommandAlias("ForestShop|LumberjackShop")
    private void lumberjackShop(Player player) {
        if (check(player, ChatColor.DARK_GREEN + "Forest")) {
            int height = 6;
            final ChestGui gui = new ChestGui(height, guiHelper.guiName("Lumberjack Shop"));
            final List<ItemList> guiContents = ItemList.getShop(Shops.LUMBERJACK);
            menuBase(gui, guiContents, player, "Lumberjack", height, Material.GREEN_STAINED_GLASS_PANE);
            gui.show(player);
        }
    }

    @CommandAlias("MercenaryShop")
    private void mercenaryShop(Player player) {
        if (check(player, ChatColor.GREEN + "Town")) {
            int height = 5;
            final ChestGui gui = new ChestGui(height, guiHelper.guiName("Mercenary Shop"));
            final List<ItemList> guiContents = ItemList.getShop(Shops.MERCENARY);
            menuBase(gui, guiContents, player, "Mercenary", height, Material.RED_STAINED_GLASS_PANE);
            gui.show(player);
        }
    }

    @CommandAlias("EstateShop|Farm2Shop")
    private void estateShop(Player player) {
        if (check(player, ChatColor.GREEN + "Farm") || check(player, ChatColor.GREEN + "Estate")) {
            int height = 5;
            final ChestGui gui = new ChestGui(height, guiHelper.guiName("Estate Shop"));
            final List<ItemList> guiContents = ItemList.getShop(Shops.ESTATE);
            menuBase(gui, guiContents, player, "Estate", height, Material.GREEN_STAINED_GLASS_PANE);
            gui.show(player);
        }
    }

    @CommandAlias("LandscapeShop|LandscaperShop")
    private void landscapeShop(Player player) {
        if (check(player, ChatColor.GREEN + "Town")) {
            int height = 6;
            final ChestGui gui = new ChestGui(height, guiHelper.guiName("Landscaper Shop"));
            final List<ItemList> guiContents = ItemList.getShop(Shops.LANDSCAPER);
            menuBase(gui, guiContents, player, "Landscaper", height, Material.LIGHT_BLUE_STAINED_GLASS_PANE);
            gui.show(player);
        }
    }

    @CommandAlias("JoyShop")
    private void joyShop(Player player) {
        if (check(player, ChatColor.GREEN + "Town")) {
            int height = 4;
            final ChestGui gui = new ChestGui(height, guiHelper.guiName("Joy Shop"));
            final List<ItemList> guiContents = ItemList.getShop(Shops.JOY);
            menuBase(gui, guiContents, player, "Joy", height, Material.PINK_STAINED_GLASS_PANE, 2);
            gui.show(player);
        }
    }

    @CommandAlias("CatLadyShop")
    private void catLadyShop(Player player) {
        if (check(player, ChatColor.DARK_GREEN + "Forest")) {
            int height = 4;
            final ChestGui gui = new ChestGui(height, guiHelper.guiName("Cat Lady Shop"));
            final List<ItemList> guiContents = ItemList.getShop(Shops.CAT_LADY);
            menuBase(gui, guiContents, player, "CatLady", height, Material.BROWN_STAINED_GLASS_PANE, 4);
            gui.show(player);
        }
    }

    @CommandAlias("HellShop|DemonShop")
    private void demonShop(Player player) {
        if (check(player, ChatColor.RED + "Hell")) {
            int height = 5;
            final ChestGui gui = new ChestGui(height, guiHelper.guiName("Demon Shop"));
            final List<ItemList> guiContents = ItemList.getShop(Shops.DEMON);
            menuBase(gui, guiContents, player, "Demon", height, Material.RED_STAINED_GLASS_PANE);
            gui.show(player);
        }
    }

    @CommandAlias("EnchantingShop")
    private void enchantingShop(Player player) {
        if (check(player, ChatColor.GREEN + "Town")) {
            int height = 6;
            int length = 5;
            int x = 2;
            int y = 1;
            final ChestGui gui = new ChestGui(height, guiHelper.guiName("Enchanting Shop"));
            final List<ItemList> guiContents = ItemList.getShop(Shops.ENCHANTER);
            menuBase(gui, guiContents, player, "Enchanting", height, length, Material.PURPLE_STAINED_GLASS_PANE, x, y);
            gui.show(player);
        }
    }

    private void menuBase(ChestGui gui, List<ItemList> guiContents, Player player, String shop, int height,
                          Material backgroundColor) {
        menuBase(gui, guiContents, player, shop, height, 7, backgroundColor, 1, 1);
    }

    private void menuBase(ChestGui gui, List<ItemList> guiContents, Player player, String shop, int height,
                          Material backgroundColor, int displayX) {
        menuBase(gui, guiContents, player, shop, height, 7, backgroundColor, displayX, 1);
    }

    private void menuBase(ChestGui gui, List<ItemList> guiContents, Player player, String shop, int height,
                          Material backgroundColor, int displayX, int displayY) {
        menuBase(gui, guiContents, player, shop, height, 7, backgroundColor, displayX, displayY);
    }

    private void menuBase(ChestGui gui, List<ItemList> guiContents, Player player, String shop, int height, int width,
                          Material backgroundColor) {
        menuBase(gui, guiContents, player, shop, height, width, backgroundColor, 1, 1);
    }

    private void menuBase(ChestGui gui, List<ItemList> guiContents, Player player, String shop, int height, int length,
                          Material backgroundColor, int displayX) {
        menuBase(gui, guiContents, player, shop, height, length, backgroundColor, displayX, 1);
    }

    private void menuBase(ChestGui gui, List<ItemList> guiContents, Player player, String shop, int height, int length, Material backgroundColor, int displayX, int displayY) {
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        PaginatedPane page = new PaginatedPane(0, 0, 9, height);
        OutlinePane background = new OutlinePane(0, 0, 9, height, Pane.Priority.LOWEST);
        OutlinePane display = new OutlinePane(displayX, displayY, length, height, Pane.Priority.LOW);
        StaticPane sell = new StaticPane(0, 0, 9, height, Pane.Priority.LOW);

        page.addPane(0, background);
        page.addPane(0, display);
        page.addPane(0, sell);

        background.addItem(new GuiItem(guiHelper.background(backgroundColor)));
        background.setRepeat(true);

        for (ItemList item : guiContents)
            display.addItem(generateItem(player, item, shop, backgroundColor));

        sell.addItem(new GuiItem(guiHelper.itemCreator(Material.CAULDRON, ChatColor.GREEN + "Sell", new String[]{"", Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Sell"}), e -> player.performCommand("sell")), 4, height - 1);

        gui.addPane(page);
    }

    public GuiItem generateItem(Player player, ItemList itemList, String shop, Material backgroundColor) {
        if (itemList.getMaxPurchaseAmount() == 0 && itemList.getExpPrice() == 0 && itemList.getCoinPrice() == 0 && itemList.getItemStack() == null && itemList.getType() == null && itemList.getID() == null)
            return new GuiItem(guiHelper.background(backgroundColor));

        ItemStack itemStack;

        if (itemList.getType() == null)
            itemStack = new ItemStack(itemList.getItemStack());
        else
            itemStack = mmoItems.getItem(itemList.getType(), itemList.getID());

        final ItemMeta itemMeta = itemStack.getItemMeta();

        if (itemStack != null) {
            List<Component> lore = itemStack.lore();
            if (lore == null)
                lore = new ArrayList<>();
            lore.add(Component.empty());

            if (itemList.getCoinPrice() > 0 && itemList.getExpPrice() > 0 && itemList.getItemPrice() != null) {
                lore.add(Component.text(ChatColor.WHITE + "Price:"));
                lore.add(Component.text(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "- " + ChatColor.YELLOW + "⛂ " + numberFormat.numberFormat(itemList.getCoinPrice())));
                lore.add(Component.text(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "- " + ChatColor.AQUA + "Ξ " + numberFormat.numberFormat(itemList.getExpPrice())));
                for (String itemPrice : itemList.getItemPrice()) {
                    String[] itemPriceSplit = itemPrice.split(";");
                    lore.add(Component.text(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "- " + ChatColor.GOLD + itemPriceSplit[2] + "x " + mmoItems.getItem(itemPriceSplit[0], itemPriceSplit[1]).getItemMeta().getDisplayName()));
                }
            } else if (itemList.getCoinPrice() <= 0 && itemList.getExpPrice() > 0 && itemList.getItemPrice() != null) {
                lore.add(Component.text(ChatColor.WHITE + "Price:"));
                lore.add(Component.text(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "- " + ChatColor.AQUA + "Ξ " + numberFormat.numberFormat(itemList.getExpPrice())));
                for (String itemPrice : itemList.getItemPrice()) {
                    String[] itemPriceSplit = itemPrice.split(";");
                    lore.add(Component.text(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "- " + ChatColor.GOLD + itemPriceSplit[2] + "x " + mmoItems.getItem(itemPriceSplit[0], itemPriceSplit[1]).getItemMeta().getDisplayName()));
                }
            } else if (itemList.getCoinPrice() > 0 && itemList.getExpPrice() <= 0 && itemList.getItemPrice() != null) {
                lore.add(Component.text(ChatColor.WHITE + "Price:"));
                lore.add(Component.text(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "- " + ChatColor.YELLOW + "⛂ " + numberFormat.numberFormat(itemList.getCoinPrice())));
                for (String itemPrice : itemList.getItemPrice()) {
                    String[] itemPriceSplit = itemPrice.split(";");
                    lore.add(Component.text(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "- " + ChatColor.GOLD + itemPriceSplit[2] + "x " + mmoItems.getItem(itemPriceSplit[0], itemPriceSplit[1]).getItemMeta().getDisplayName()));
                }
            } else if (itemList.getCoinPrice() > 0 && itemList.getExpPrice() > 0 && itemList.getItemPrice() == null) {
                lore.add(Component.text(ChatColor.WHITE + "Price:"));
                lore.add(Component.text(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "- " + ChatColor.YELLOW + "⛂ " + numberFormat.numberFormat(itemList.getCoinPrice())));
                lore.add(Component.text(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "- " + ChatColor.AQUA + "Ξ " + numberFormat.numberFormat(itemList.getExpPrice())));
            } else if (itemList.getCoinPrice() > 0 && itemList.getExpPrice() <= 0 && itemList.getItemPrice() == null) {
                lore.add(Component.text(ChatColor.WHITE + "Price: " + ChatColor.YELLOW + "⛂ " + numberFormat.numberFormat(itemList.getCoinPrice())));
            } else if (itemList.getCoinPrice() <= 0 && itemList.getExpPrice() > 0 && itemList.getItemPrice() == null) {
                lore.add(Component.text(ChatColor.WHITE + "Price: " + ChatColor.AQUA + "Ξ " + numberFormat.numberFormat(itemList.getExpPrice())));
            } else if (itemList.getCoinPrice() <= 0 && itemList.getExpPrice() <= 0 && itemList.getItemPrice() != null) {
                lore.add(Component.text(ChatColor.WHITE + "Price:"));
                for (String itemPrice : itemList.getItemPrice()) {
                    String[] itemPriceSplit = itemPrice.split(";");
                    lore.add(Component.text(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "- " + ChatColor.GOLD + itemPriceSplit[2] + "x " + mmoItems.getItem(itemPriceSplit[0], itemPriceSplit[1]).getItemMeta().getDisplayName()));
                }

            }

            for (Component textComponent : itemLore(player, itemList))
                lore.add(textComponent);
            itemMeta.lore(lore);
            itemStack.setItemMeta(itemMeta);

            return new GuiItem(itemStack, e -> {
                if (itemList.getItemPrice() != null) {
                    if (e.isLeftClick() && !e.isShiftClick()
                            && purchaseUtils.hasItem(player, itemList.getItemPrice(), 1).size() >= itemList.getItemPrice().length)
                        purchaseUtils.purchase(player, itemList, 1);

                    if (e.isRightClick() && !e.isShiftClick() && economy.getBalance(player) >= itemList.getCoinPrice() * 16 && itemList.getMaxPurchaseAmount() >= 16
                            && purchaseUtils.hasItem(player, itemList.getItemPrice(), 16).size() >= itemList.getItemPrice().length)
                        purchaseUtils.purchase(player, itemList, 16);

                    if (e.isLeftClick() && e.isShiftClick() && economy.getBalance(player) >= itemList.getCoinPrice() * 32 && itemList.getMaxPurchaseAmount() >= 32
                            && purchaseUtils.hasItem(player, itemList.getItemPrice(), 32).size() >= itemList.getItemPrice().length)
                        purchaseUtils.purchase(player, itemList, 32);

                    if (e.isRightClick() && e.isShiftClick() && economy.getBalance(player) >= itemList.getCoinPrice() * 64 && itemList.getMaxPurchaseAmount() >= 64
                            && purchaseUtils.hasItem(player, itemList.getItemPrice(), 64).size() >= itemList.getItemPrice().length)
                        purchaseUtils.purchase(player, itemList, 64);
                } else {
                    if (e.isLeftClick() && !e.isShiftClick())
                        purchaseUtils.purchase(player, itemList, 1);
                    if (e.isRightClick() && !e.isShiftClick() && economy.getBalance(player) >= itemList.getCoinPrice() * 16 && itemList.getMaxPurchaseAmount() >= 16)
                        purchaseUtils.purchase(player, itemList, 16);
                    if (e.isLeftClick() && e.isShiftClick() && economy.getBalance(player) >= itemList.getCoinPrice() * 32 && itemList.getMaxPurchaseAmount() >= 32)
                        purchaseUtils.purchase(player, itemList, 32);
                    if (e.isRightClick() && e.isShiftClick() && economy.getBalance(player) >= itemList.getCoinPrice() * 64 && itemList.getMaxPurchaseAmount() >= 64)
                        purchaseUtils.purchase(player, itemList, 64);
                }
                player.performCommand(shop + "Shop");
            });
        }
        return null;
    }

    private List<Component> itemLore(Player player, ItemList itemList) {
        List<Component> lore = new ArrayList<>();
        if (itemList.getItemPrice() != null) {
            if (purchaseUtils.hasItem(player, itemList.getItemPrice(), 1).size() >= itemList.getItemPrice().length) {
                if (economy.getBalance(player) >= itemList.getCoinPrice() && player.getLevel() >= itemList.getExpPrice()) {
                    lore.add(Component.text(""));
                    lore.add(Component.text(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Left-Click to Purchase " + ChatColor.GOLD + "1x"));
                    if (purchaseUtils.hasItem(player, itemList.getItemPrice(), 16).size() >= itemList.getItemPrice().length)
                        if (economy.getBalance(player) >= itemList.getCoinPrice() * 16 && player.getLevel() >= itemList.getExpPrice() * 16 && itemList.getMaxPurchaseAmount() >= 16)
                            lore.add(Component.text(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Right-Click to Purchase " + ChatColor.GOLD + "16x " + ChatColor.YELLOW + "(⛂ " + numberFormat.numberFormat(itemList.getCoinPrice() * 16) + ")"));
                    if (purchaseUtils.hasItem(player, itemList.getItemPrice(), 32).size() >= itemList.getItemPrice().length)
                        if (economy.getBalance(player) >= itemList.getCoinPrice() * 32 && player.getLevel() >= itemList.getExpPrice() * 32 && itemList.getMaxPurchaseAmount() >= 32)
                            lore.add(Component.text(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Shift-Left-Click to Purchase " + ChatColor.GOLD + "32x " + ChatColor.YELLOW + "(⛂ " + numberFormat.numberFormat(itemList.getCoinPrice() * 32) + ")"));
                    if (purchaseUtils.hasItem(player, itemList.getItemPrice(), 64).size() >= itemList.getItemPrice().length) {
                        if (economy.getBalance(player) >= itemList.getCoinPrice() * 64 && player.getLevel() >= itemList.getExpPrice() * 64 && itemList.getMaxPurchaseAmount() >= 64)
                            lore.add(Component.text(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Shift-Right-Click to Purchase " + ChatColor.GOLD + "64x " + ChatColor.YELLOW + "(⛂ " + numberFormat.numberFormat(itemList.getCoinPrice() * 64) + ")"));
                    }
                }
            }
        } else if (economy.getBalance(player) >= itemList.getCoinPrice() && player.getLevel() >= itemList.getExpPrice()) {
            lore.add(Component.text(""));
            lore.add(Component.text(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Left-Click to Purchase " + ChatColor.GOLD + "1x"));
            if (economy.getBalance(player) >= itemList.getCoinPrice() * 16 && player.getLevel() >= itemList.getExpPrice() * 16 && itemList.getMaxPurchaseAmount() >= 16)
                lore.add(Component.text(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Right-Click to Purchase " + ChatColor.GOLD + "16x " + ChatColor.YELLOW + "(⛂ " + numberFormat.numberFormat(itemList.getCoinPrice() * 16) + ")"));
            if (economy.getBalance(player) >= itemList.getCoinPrice() * 32 && player.getLevel() >= itemList.getExpPrice() * 32 && itemList.getMaxPurchaseAmount() >= 32)
                lore.add(Component.text(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Shift-Left-Click to Purchase " + ChatColor.GOLD + "32x " + ChatColor.YELLOW + "(⛂ " + numberFormat.numberFormat(itemList.getCoinPrice() * 32) + ")"));
            if (economy.getBalance(player) >= itemList.getCoinPrice() * 64 && player.getLevel() >= itemList.getExpPrice() * 64 && itemList.getMaxPurchaseAmount() >= 64)
                lore.add(Component.text(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Shift-Right-Click to Purchase " + ChatColor.GOLD + "64x " + ChatColor.YELLOW + "(⛂ " + numberFormat.numberFormat(itemList.getCoinPrice() * 64) + ")"));
        }
        return lore;
    }
//        if (economy.getBalance(player) >= itemList.getCoinPrice() && player.getLevel() >= itemList.getExpPrice()) {
//            lore.add(Component.text(""));
//            lore.add(Component.text(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Left-Click to Purchase " + ChatColor.GOLD + "1x"));
//            if (economy.getBalance(player) >= itemList.getCoinPrice() * 16 && player.getLevel() >= itemList.getExpPrice() * 16 && itemList.getMaxPurchaseAmount() >= 16)
//                lore.add(Component.text(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Right-Click to Purchase " + ChatColor.GOLD + "16x " + ChatColor.YELLOW + "(⛂ " + numberFormat.numberFormat(itemList.getCoinPrice() * 16) + ")"));
//            if (economy.getBalance(player) >= itemList.getCoinPrice() * 32 && player.getLevel() >= itemList.getExpPrice() * 32 && itemList.getMaxPurchaseAmount() >= 32)
//                lore.add(Component.text(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Shift-Left-Click to Purchase " + ChatColor.GOLD + "32x " + ChatColor.YELLOW + "(⛂ " + numberFormat.numberFormat(itemList.getCoinPrice() * 32) + ")"));
//            if (economy.getBalance(player) >= itemList.getCoinPrice() * 64 && player.getLevel() >= itemList.getExpPrice() * 64 && itemList.getMaxPurchaseAmount() >= 64)
//                lore.add(Component.text(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Shift-Right-Click to Purchase " + ChatColor.GOLD + "64x " + ChatColor.YELLOW + "(⛂ " + numberFormat.numberFormat(itemList.getCoinPrice() * 64) + ")"));
//        }
//        return lore;
//    }

    /*
     *
     *
     *       Tired creating one method for all...
     *
     * */

//    public void professionVendors(Player player, String guiName, Material backgroundColor, List<GuiItem> guiItemList) {
//
//        ChestGui gui = new ChestGui(3, guiHelper.guiName("Baker"));
//        gui.setOnGlobalClick(event -> event.setCancelled(true));
//
//        OutlinePane background = new OutlinePane(0, 0, 9, 3, Pane.Priority.LOWEST);
//        StaticPane display = new StaticPane(0, 0, 9, 3, Pane.Priority.LOW);
//
//
//        background.addItem(new GuiItem(guiHelper.background(Material.YELLOW_STAINED_GLASS_PANE)));
//        background.setRepeat(true);
//
//        for (GuiItem guiItem : guiItemList) {
//            display.addItem(guiItem);
//        }
//            display.addItem(new GuiItem(guiHelper.itemCreator(Material.MUSHROOM_STEW,
//                    ChatColor.GREEN + "Stew Shop", new String[]{"", Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View"}),
//                    e -> consoleCommand.consoleCommand("mi stations open stews " + player.getName())), 2, 1);
//        display.addItem(new GuiItem(guiHelper.itemCreator(Material.BREWING_STAND,
//                ChatColor.GREEN + "Material Shop", new String[]{"", Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View"}),
//                e -> consoleCommand.consoleCommand("mi stations open enchanted-materials " + player.getName())), 4, 1);
//        display.addItem(new GuiItem(guiHelper.itemCreator(Material.TOTEM_OF_UNDYING,
//                ChatColor.GREEN + "Totem Shop", new String[]{"", Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View"}),
//                e -> consoleCommand.consoleCommand("mi stations open totems " + player.getName())), 6, 1);
//
//        gui.addPane(background);
//        gui.addPane(display);
//        gui.show(player);
//    }


    private boolean check(Player player, String area) {
        if (player.hasPermission("SHOPS") || areaCheck(player, area))
            return true;
        else {
            player.sendMessage(ChatColor.RED + "You must either have the " + ChatColor.BOLD + "Conquerer Rank "
                    + ChatColor.RED + "or be within the " + area + ChatColor.RED + "!");
            player.sendMessage(donate);
            soundManager.soundNo(player, 1);
            return false;
        }
    }

    private boolean areaCheck(Player player, String area) {
        Location location = BukkitAdapter.adapt(player.getLocation());
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionQuery query = container.createQuery();
        ApplicableRegionSet set = query.getApplicableRegions(location);
        if (set.queryValue(WorldGuardPlugin.inst().wrapPlayer(player), displayNameFlag).equals(area))
            return true;
        return false;
    }

}



