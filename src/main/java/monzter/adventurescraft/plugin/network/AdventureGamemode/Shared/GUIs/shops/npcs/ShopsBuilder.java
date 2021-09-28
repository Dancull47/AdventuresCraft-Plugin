package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.shops.npcs;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.util.Location;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.StringFlag;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.mainMenu.quests.enums.QuestArea;
import monzter.adventurescraft.plugin.utilities.GUI.GUIHelper;
import monzter.adventurescraft.plugin.utilities.enums.Prefix;
import monzter.adventurescraft.plugin.utilities.enums.Region;
import monzter.adventurescraft.plugin.utilities.general.ConsoleCommand;
import monzter.adventurescraft.plugin.utilities.general.FullInventory;
import monzter.adventurescraft.plugin.utilities.general.Purchase.PurchaseUtils;
import monzter.adventurescraft.plugin.utilities.general.Purchase.ShopBuilder;
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
import org.apache.commons.lang.WordUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

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
    private final ShopBuilder shopBuilder;

    final TextComponent donate = Component.text("You can donate to get epic rewards from our")
            .color(NamedTextColor.GREEN)
            .append(Component.text(" Store", NamedTextColor.GOLD))
            .append(Component.text("!"))
            .append(Component.text(" <- CLICK HERE", NamedTextColor.GOLD, TextDecoration.BOLD))
            .hoverEvent(Component.text("Click to visit the Store!", NamedTextColor.GREEN))
            .clickEvent(ClickEvent.openUrl("https://store.adventurescraft.net"));


    public ShopsBuilder(AdventuresCraft plugin, SoundManager soundManager, GUIHelper guiHelper, ConsoleCommand consoleCommand, Economy economy, FullInventory fullInventory, MMOItemsGive mmoItemsGive, NumberFormat numberFormat, PurchaseUtils purchaseUtils, MMOItems mmoItems, PermissionLP permissionLP, StringFlag displayNameFlag, ShopBuilder shopBuilder) {
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
        this.shopBuilder = shopBuilder;
    }

    @CommandAlias("AreaShop")
    public void areaShopMenu(Player player, String area) {
        int height = guiHelper.heightCalc(Shops.getShop(area).size()) - 1;
        final ChestGui gui = new ChestGui(height, guiHelper.guiName(WordUtils.capitalizeFully(area) + " Shop"));
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        OutlinePane background = new OutlinePane(0, 0, 9, height, Pane.Priority.LOWEST);
        OutlinePane display = new OutlinePane(guiHelper.displayXCalc(Shops.getShop(area).size()), 1, 7, height, Pane.Priority.LOW);

        background.addItem(new GuiItem(guiHelper.background(Material.GREEN_STAINED_GLASS_PANE)));
        background.setRepeat(true);

        for (Shops shop : Shops.values()) {
            if (shop.name().contains(area.toUpperCase())) {
                if (shop.name().contains("_ACCESSORIES"))
                    display.addItem(new GuiItem(guiHelper.itemCreator("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjMxMmE1YTEyZWNiMjRkNjg1MmRiMzg4ZTZhMzQ3MjFjYzY3ZjUyMmNjZGU3ZTgyNGI5Zjc1ZTk1MDM2YWM5MyJ9fX0=",
                            ChatColor.GREEN + "Accessories", new String[]{"", Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View"}), e -> openShop(player, shop.name().replace("_", ""))));
                if (shop.name().contains("_ARMOR"))
                    display.addItem(new GuiItem(guiHelper.itemCreator(Material.DIAMOND_CHESTPLATE,
                            ChatColor.GREEN + "Armor", new String[]{"", Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View"}), e -> openShop(player, shop.name().replace("_", ""))));
                if (shop.name().contains("_CATALYSTS"))
                    display.addItem(new GuiItem(guiHelper.itemCreator("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzRkODkxOGU3ODk0NTRjYWNkNjYzOWE0ODA1OWE1Y2U3NzlmMmQ5ZWZhZGUzNjMzOThmNGRmZDUxMjg2MzQifX19",
                            ChatColor.GREEN + "Catalysts", new String[]{"", Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View"}), e -> openShop(player, shop.name().replace("_", ""))));
                if (shop.name().contains("_CONSUMABLES"))
                    display.addItem(new GuiItem(guiHelper.itemCreator(Material.APPLE,
                            ChatColor.GREEN + "Consumables", new String[]{"", Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View"}), e -> openShop(player, shop.name().replace("_", ""))));
                if (shop.name().contains("_GEM_STONES"))
                    display.addItem(new GuiItem(guiHelper.itemCreator(Material.EMERALD,
                            ChatColor.GREEN + "Gem Stones", new String[]{"", Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View"}), e -> openShop(player, shop.name().replace("_", ""))));
                if (shop.name().contains("_MATERIALS"))
                    display.addItem(new GuiItem(guiHelper.itemCreator(Material.BONE,
                            ChatColor.GREEN + "Materials", new String[]{"", Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View"}), e -> openShop(player, shop.name().replace("_", ""))));
                if (shop.name().contains("_MOUNTS"))
                    display.addItem(new GuiItem(guiHelper.itemCreator("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjIxNjY4ZWY3Y2I3OWRkOWMyMmNlM2QxZjNmNGNiNmUyNTU5ODkzYjZkZjRhNDY5NTE0ZTY2N2MxNmFhNCJ9fX0=",
                            ChatColor.GREEN + "Mounts", new String[]{"", Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View"}), e -> openShop(player, shop.name().replace("_", ""))));
                if (shop.name().contains("_TOOLS"))
                    display.addItem(new GuiItem(guiHelper.itemCreator(Material.DIAMOND_PICKAXE,
                            ChatColor.GREEN + "Tools", new String[]{"", Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View"}), e -> openShop(player, shop.name().replace("_", ""))));
                if (shop.name().contains("_UPGRADES"))
                    display.addItem(new GuiItem(guiHelper.itemCreator(Material.ENCHANTED_BOOK,
                            ChatColor.GREEN + "Upgrades", new String[]{"", Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View"}), e -> consoleCommand.consoleCommand("mi stations open " + shop.name().replace("_", "-").toLowerCase() + " " + player.getName())));
                if (shop.name().contains("_WEAPONS"))
                    display.addItem(new GuiItem(guiHelper.itemCreator(Material.DIAMOND_SWORD,
                            ChatColor.GREEN + "Weapons", new String[]{"", Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View"}), e -> openShop(player, shop.name().replace("_", ""))));
            }
        }

        if (display.getItems().size() == 0) {
            player.sendMessage(ChatColor.RED + "This Shop doesn't exist!");
            return;
        }

        gui.addPane(background);
        gui.addPane(display);
        gui.show(player);
    }

    /*
     *
     *   This is a Menu which shows all the available Material Shops
     *
     * */

    @CommandAlias("Materials")
    public void materialShopMenu(Player player) {
        int size = 5;
        int height = 3;
        final ChestGui gui = new ChestGui(height, guiHelper.guiName("Material Shop"));
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        OutlinePane background = new OutlinePane(0, 0, 9, height, Pane.Priority.LOWEST);
        OutlinePane display = new OutlinePane(guiHelper.displayXCalc(size), 1, 7, height, Pane.Priority.LOW);

        background.addItem(new GuiItem(guiHelper.background(Material.GREEN_STAINED_GLASS_PANE)));
        background.setRepeat(true);

        display.addItem(new GuiItem(guiHelper.itemCreator(ShopList.MATERIALS.getTexture(),
                ChatColor.GREEN + "Materials", new String[]{"", Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View"}, true), e -> materialShops(player, "MATERIALS")));
        display.addItem(new GuiItem(guiHelper.itemCreator(Material.WHEAT_SEEDS,
                ChatColor.GREEN + "Farming Materials", new String[]{"", Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View"}, true), e -> materialShops(player, "FARMING")));
        display.addItem(new GuiItem(guiHelper.itemCreator(Material.OAK_LOG,
                ChatColor.GREEN + "Foraging Materials", new String[]{"", Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View"}, true), e -> materialShops(player, "FORAGING")));
        display.addItem(new GuiItem(guiHelper.itemCreator(Material.COBBLESTONE,
                ChatColor.GREEN + "Mining Materials", new String[]{"", Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View"}, true), e -> materialShops(player, "MINING")));
        display.addItem(new GuiItem(guiHelper.itemCreator(Material.BONE,
                ChatColor.GREEN + "Combat Materials", new String[]{"", Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View"}, true), e -> materialShops(player, "COMBAT")));

        gui.addPane(background);
        gui.addPane(display);
        gui.show(player);
    }

    @CommandAlias("Material|Materials")
    private void materialShops(Player player, String shop) {
        materialShops(player, shop, 0);
    }

    /*
     *
     *   This will open the individual Material Shops
     *
     * */

    @CommandAlias("Material|Materials")
    @CommandCompletion("Farming|Foraging|Materials|Mining|Combat")
    private void materialShops(Player player, String shop, @Default("0") int page) {
        if (!shop.equalsIgnoreCase("MATERIALS")) {
            shop = "MATERIALS_" + shop.toUpperCase();
            for (Shops shopObj : Shops.values()) {
                if (shop.equals(shopObj.name())) {
                    final List<ItemList> guiContents = ItemList.getShop(shopObj);
                    final ChestGui gui = new ChestGui(guiHelper.heightCalc(guiContents.size()), guiHelper.guiName(shopObj.getTitle()));
                    shopBuilder.menuBase(gui, guiContents, player, "Materials " + shopObj.getCommand(), shopObj.getBackgroundMaterial(), page);
                    gui.show(player);
                }
            }
        } else {
            final List<ItemList> guiContents = ItemList.getMaterials();
            final ChestGui gui = new ChestGui(guiHelper.heightCalc(guiContents.size()), guiHelper.guiName("Materials"));
            shopBuilder.menuBase(gui, guiContents, player, "Materials Materials", Material.GREEN_STAINED_GLASS_PANE, page);
            gui.show(player);
        }
    }

    /*
     *
     *   This creates a Menu for all Shops IF a Shop is not given.
     *   If a Shop is given, then it'll open that Shop
     *
     * */

    @CommandAlias("Shop")
    private void shop(Player player, @Optional String shop, @Default("0") int page) {
        if (shop == null) {
            int size = ShopList.values().length;
            int height = guiHelper.heightCalc(size - 1);
            final ChestGui gui = new ChestGui(height, guiHelper.guiName("Shops"));
            gui.setOnGlobalClick(event -> event.setCancelled(true));

            OutlinePane background = new OutlinePane(0, 0, 9, height, Pane.Priority.LOWEST);
            OutlinePane display = new OutlinePane(guiHelper.displayXCalc(size), 1, 7, height, Pane.Priority.LOW);

            background.addItem(new GuiItem(guiHelper.background(Material.GREEN_STAINED_GLASS_PANE)));
            background.setRepeat(true);

            for (ShopList shopList : ShopList.values())
                if (!shopList.name().equals("MATERIALS"))
                    display.addItem(new GuiItem(guiHelper.itemCreator(shopList.getTexture(),
                            ChatColor.GREEN + shopList.getItemName() + " Shop", new String[]{"", Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View"}, true), e -> areaShopMenu(player, shopList.name())));
                else
                    display.addItem(new GuiItem(guiHelper.itemCreator(shopList.getTexture(),
                            ChatColor.GREEN + shopList.getItemName() + " Shop", new String[]{"", Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View"}, true), e -> materialShopMenu(player)));

            gui.addPane(background);
            gui.addPane(display);
            gui.show(player);
        } else {
            for (Shops shopObj : Shops.values()) {
                if (shop.equalsIgnoreCase(shopObj.getCommand()) && check(player, shopObj.getArea())) {
                    final List<ItemList> guiContents = ItemList.getShop(shopObj);
                    final ChestGui gui = new ChestGui(guiHelper.heightCalc(guiContents.size()), guiHelper.guiName(shopObj.getTitle()));
                    shopBuilder.menuBase(gui, guiContents, player, "Shop " + shopObj.getCommand(), shopObj.getBackgroundMaterial(), page);
                    gui.show(player);
                }
            }
        }
    }

    @CommandAlias("WanderingTraderShop")
    private void wanderingTraderShop(Player player, int number) {
        if (player.isOp()) {
            final ChestGui gui = new ChestGui(3, guiHelper.guiName("Wandering Trader"));
            shopBuilder.wanderingTraderBase(gui, ItemList.valueOf("WANDERING_TRADER" + number), player, number);
            gui.show(player);
        }
    }

    private void openShop(Player player, String shop) {
        if (!player.hasPermission("SHOPS"))
            try {
                permissionLP.givePermission(player, "SHOPS");
                player.performCommand("adventurescraft:shop " + shop);
            } finally {
                permissionLP.takePermission(player, "SHOPS");
            }
        else
            player.performCommand("adventurescraft:shop " + shop);
    }

    private boolean check(Player player, Region region) {
        if (region == null)
            return true;
        String area = region.getName();
        if (player.hasPermission("SHOPS") || areaCheck(player, area))
            return true;
        player.sendMessage(ChatColor.RED + "You must either have the " + ChatColor.BOLD + "Conquerer Rank "
                + ChatColor.RED + "or be within the " + area + ChatColor.RED + "!");
        player.sendMessage(donate);
        soundManager.soundNo(player, 1);
        return false;
    }

    private boolean areaCheck(Player player, String area) {
        Location location = BukkitAdapter.adapt(player.getLocation());
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionQuery query = container.createQuery();
        ApplicableRegionSet set = query.getApplicableRegions(location);
        for (ProtectedRegion region : set)
            if (region.getFlag(displayNameFlag).equals(area))
                return true;
        return false;
    }
}

enum ShopList {
    MATERIALS("Materials", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmNkYzBmZWI3MDAxZTJjMTBmZDUwNjZlNTAxYjg3ZTNkNjQ3OTMwOTJiODVhNTBjODU2ZDk2MmY4YmU5MmM3OCJ9fX0="),
    FARMING("Farming", QuestArea.FARM.getHead()),
    FORAGING("Foraging", QuestArea.FOREST.getHead()),
    LUMBERJACK("Lumberjack", "eyJ0aW1lc3RhbXAiOjE1NDkzMTYwODgxNDEsInByb2ZpbGVJZCI6Ijc3NDEwODQ1NWVhMzRmNGU4MmJmODFjODk0NjExZTYxIiwicHJvZmlsZU5hbWUiOiJsdW1iZXJqYWNrIiwic2lnbmF0dXJlUmVxdWlyZWQiOnRydWUsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9lNjYxZjMyYzZjZjAxYWY4NjdkYzViMjMxMmRhMjAxZWJkZTQ1NmFhNDJmOTQwNWY1OTJlNGRiYWEyOTgxMDk5In19fQ=="),
    GRAVEYARD("Graveyard", QuestArea.GRAVEYARD.getHead()),
    COURTYARD("Courtyard", QuestArea.COURTYARD.getHead()),
    CASTLE("Castle", QuestArea.CASTLE.getHead()),
    VOID("Void", QuestArea.VOID.getHead()),
    ;
    public final String itemName;
    public final String texture;


    ShopList(String itemName, String texture) {
        this.itemName = itemName;
        this.texture = texture;
    }

    public String getItemName() {
        return itemName;
    }

    public String getTexture() {
        return texture;
    }
}



