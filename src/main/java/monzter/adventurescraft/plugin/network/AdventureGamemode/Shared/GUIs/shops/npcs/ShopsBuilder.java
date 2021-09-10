package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.shops.npcs;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Dependency;
import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
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
import monzter.adventurescraft.plugin.utilities.general.*;
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
            shopBuilder.menuBase(gui, guiContents, player, "Mining", height, Material.BLACK_STAINED_GLASS_PANE);
            gui.show(player);
        }
    }

    @CommandAlias("FarmShop")
    private void farmShop(Player player) {
        if (check(player, ChatColor.GREEN + "Farm")) {
            int height = 5;
            final ChestGui gui = new ChestGui(height, guiHelper.guiName("Farm Shop"));
            final List<ItemList> guiContents = ItemList.getShop(Shops.FARMER);
            shopBuilder.menuBase(gui, guiContents, player, "Farm", height, Material.LIME_STAINED_GLASS_PANE);
            gui.show(player);
        }
    }

    @CommandAlias("ForestShop|LumberjackShop")
    private void lumberjackShop(Player player) {
        if (check(player, ChatColor.DARK_GREEN + "Forest")) {
            int height = 6;
            final ChestGui gui = new ChestGui(height, guiHelper.guiName("Lumberjack Shop"));
            final List<ItemList> guiContents = ItemList.getShop(Shops.LUMBERJACK);
            shopBuilder.menuBase(gui, guiContents, player, "Lumberjack", height, Material.GREEN_STAINED_GLASS_PANE);
            gui.show(player);
        }
    }

    @CommandAlias("MercenaryShop")
    private void mercenaryShop(Player player) {
        if (check(player, ChatColor.GREEN + "Town")) {
            int height = 5;
            final ChestGui gui = new ChestGui(height, guiHelper.guiName("Mercenary Shop"));
            final List<ItemList> guiContents = ItemList.getShop(Shops.MERCENARY);
            shopBuilder.menuBase(gui, guiContents, player, "Mercenary", height, Material.RED_STAINED_GLASS_PANE);
            gui.show(player);
        }
    }

    @CommandAlias("EstateShop|Farm2Shop")
    private void estateShop(Player player) {
        if (check(player, ChatColor.GREEN + "Farm") || check(player, ChatColor.GREEN + "Estate")) {
            int height = 5;
            final ChestGui gui = new ChestGui(height, guiHelper.guiName("Estate Shop"));
            final List<ItemList> guiContents = ItemList.getShop(Shops.ESTATE);
            shopBuilder.menuBase(gui, guiContents, player, "Estate", height, Material.GREEN_STAINED_GLASS_PANE);
            gui.show(player);
        }
    }

    @CommandAlias("LandscapeShop|LandscaperShop")
    private void landscapeShop(Player player) {
        if (check(player, ChatColor.GREEN + "Town")) {
            int height = 6;
            final ChestGui gui = new ChestGui(height, guiHelper.guiName("Landscaper Shop"));
            final List<ItemList> guiContents = ItemList.getShop(Shops.LANDSCAPER);
            shopBuilder.menuBase(gui, guiContents, player, "Landscaper", height, Material.LIGHT_BLUE_STAINED_GLASS_PANE);
            gui.show(player);
        }
    }

    @CommandAlias("JoyShop")
    private void joyShop(Player player) {
        if (check(player, ChatColor.GREEN + "Town")) {
            int height = 4;
            final ChestGui gui = new ChestGui(height, guiHelper.guiName("Joy Shop"));
            final List<ItemList> guiContents = ItemList.getShop(Shops.JOY);
            shopBuilder.menuBase(gui, guiContents, player, "Joy", height, Material.PINK_STAINED_GLASS_PANE, 2);
            gui.show(player);
        }
    }

    @CommandAlias("CatLadyShop")
    private void catLadyShop(Player player) {
        if (check(player, ChatColor.DARK_GREEN + "Forest")) {
            int height = 4;
            final ChestGui gui = new ChestGui(height, guiHelper.guiName("Cat Lady Shop"));
            final List<ItemList> guiContents = ItemList.getShop(Shops.CAT_LADY);
            shopBuilder.menuBase(gui, guiContents, player, "CatLady", height, Material.BROWN_STAINED_GLASS_PANE, 4);
            gui.show(player);
        }
    }

    @CommandAlias("HellShop|DemonShop")
    private void demonShop(Player player) {
        if (check(player, ChatColor.RED + "Hell")) {
            int height = 5;
            final ChestGui gui = new ChestGui(height, guiHelper.guiName("Demon Shop"));
            final List<ItemList> guiContents = ItemList.getShop(Shops.DEMON);
            shopBuilder.menuBase(gui, guiContents, player, "Demon", height, Material.RED_STAINED_GLASS_PANE);
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
            shopBuilder.menuBase(gui, guiContents, player, "Enchanting", height, length, Material.PURPLE_STAINED_GLASS_PANE, x, y);
            gui.show(player);
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



