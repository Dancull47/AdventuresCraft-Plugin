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
import monzter.adventurescraft.plugin.utilities.enums.Region;
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
        if (check(player, Region.TOWN)) {
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
        if (check(player, Region.TOWN)) {
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
        if (check(player, Region.TOWN)) {
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
        if (check(player, Region.TOWN)) {
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
        if (check(player, Region.TOWN)) {
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

//    Covers mostly all Vendors
    @CommandAlias("Shop")
    private void minerShop(Player player, String shop) {
        for (Shops shopObj : Shops.values()) {
            if (shop.equalsIgnoreCase(shopObj.getCommand()) && check(player, shopObj.getArea())) {
                final List<ItemList> guiContents = ItemList.getShop(shopObj);
                final ChestGui gui = new ChestGui(heightCalculator(guiContents.size()), guiHelper.guiName(shopObj.getTitle()));
                shopBuilder.menuBase(gui, guiContents, player, "Shop " + shopObj.getCommand(), shopObj.getBackgroundMaterial());
                gui.show(player);
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

    private boolean check(Player player, Region region) {
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
        if (set.queryValue(WorldGuardPlugin.inst().wrapPlayer(player), displayNameFlag).equals(area))
            return true;
        return false;
    }

    public int heightCalculator(int size) {
        switch (size) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
                return 4;
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
                return 5;
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
                return 6;
        }
        return 0;
    }

}



