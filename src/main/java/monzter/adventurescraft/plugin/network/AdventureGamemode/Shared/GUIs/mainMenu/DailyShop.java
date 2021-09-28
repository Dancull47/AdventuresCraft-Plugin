package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.mainMenu;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.Dependency;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.network.AdventureGamemode.Enums.EnchantedMaterialList;
import monzter.adventurescraft.plugin.utilities.GUI.GUIHelper;
import monzter.adventurescraft.plugin.utilities.beton.BetonPointsManager;
import monzter.adventurescraft.plugin.utilities.general.ConsoleCommand;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import monzter.adventurescraft.plugin.utilities.mmoitems.MMOItemsGive;
import monzter.adventurescraft.plugin.utilities.mmoitems.MMOItemsHelperImpl;
import monzter.adventurescraft.plugin.utilities.text.NumberFormat;
import monzter.adventurescraft.plugin.utilities.vault.Economy;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Random;

public class DailyShop extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final SoundManager soundManager;
    private final GUIHelper guiHelper;
    private final ConsoleCommand consoleCommand;
    private final NumberFormat numberFormat;
    private final MMOItemsGive mmoItemsGive;
    private final BetonPointsManager betonPointsManager;
    private final Economy economy;


    public DailyShop(AdventuresCraft plugin, SoundManager soundManager, GUIHelper guiHelper, ConsoleCommand consoleCommand, NumberFormat numberFormat, MMOItemsGive mmoItemsGive, BetonPointsManager betonPointsManager, Economy economy) {
        this.plugin = plugin;
        this.soundManager = soundManager;
        this.guiHelper = guiHelper;
        this.consoleCommand = consoleCommand;
        this.numberFormat = numberFormat;
        this.mmoItemsGive = mmoItemsGive;
        this.betonPointsManager = betonPointsManager;
        this.economy = economy;
    }

//    @CommandAlias("daily|dailyReward|dailyShop")
//    public void daily(Player player) {
//
//        ChestGui gui = new ChestGui(4, guiHelper.guiName("Daily Store"));
//        int dailyPoints = Integer.valueOf(PlaceholderAPI.setPlaceholders(player, "%betonquest_items:point.Login.amount%"));
//        gui.setOnGlobalClick(event -> event.setCancelled(true));
//
//        OutlinePane background = new OutlinePane(0, 0, 9, 4, Pane.Priority.LOWEST);
//        OutlinePane display = new OutlinePane(1, 1, 7, 4, Pane.Priority.LOW);
//        StaticPane staticPane = new StaticPane(0, 0, 7, 4, Pane.Priority.LOW);
//
//        background.addItem(new GuiItem(guiHelper.background(Material.MAGENTA_STAINED_GLASS_PANE)));
//        background.setRepeat(true);
//
//        staticPane.addItem(new GuiItem(guiHelper.backButton(), e -> player.performCommand("main")), 4, 3);
//
//        for (DailyShopList reward : DailyShopList.values()) {
//            ItemStack itemStack = MMOItemsHelperImpl.getItem(reward.type, reward.getItemID());
//            if (itemStack == null) {
//                plugin.getLogger().info(reward.getItemID());
//                itemStack = new ItemStack(Material.valueOf(reward.getItemID()));
//                plugin.getLogger().info(reward.getItemID());
//            }
//
//            final ItemMeta itemMeta = itemStack.getItemMeta();
//            List<Component> lore = itemStack.lore();
//            if (lore == null) {
//                lore = new ArrayList<>();
//            }
//            lore.add(Component.text(" "));
//            switch (reward.getType()) {
//                case "COINS25_000":
//                    itemMeta.displayName(Component.text(ChatColor.YELLOW + "25,000 Coins ⛂"));
//                    break;
//                case "COMING_SOON":
//                    itemMeta.displayName(Component.text(ChatColor.DARK_RED.toString() + ChatColor.BOLD + "COMING SOON"));
//                    break;
//                case "ENCHANTED_MATERIALS":
//                    itemMeta.displayName(Component.text(ChatColor.GOLD + "16x " + ChatColor.BLUE + "Enchanted Materials"));
//                    itemMeta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
//                    itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
//                    break;
//            }
//            lore.add(Component.text(ChatColor.WHITE + "Price: " + ChatColor.GOLD + numberFormat.numberFormat(reward.getPrice()) + ChatColor.GREEN + " Daily Tokens"));
//            if (dailyPoints >= reward.getPrice()) {
//                lore.add(Component.text(" "));
//                lore.add(Component.text(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Purchase"));
//            }
////            if (reward.itemID.equalsIgnoreCase("ENGRAM1") || reward.itemID.equalsIgnoreCase("MAGICAL_BOX")
////                    || reward.itemID.equalsIgnoreCase("PROFESSION_BOOSTER_BOX"))
////                lore.add(Component.text(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Right-Click to View Rewards"));
//
//            if (!reward.getType().equals("COMING_SOON"))
//                itemMeta.lore(lore);
//            itemStack.setItemMeta(itemMeta);
//
//            display.addItem(new GuiItem(itemStack.asQuantity(reward.getAmount()), e -> {
//                if (dailyPoints >= reward.getPrice()) {
//                    reward(player, reward);
//                    takePoints(player, reward.getPrice());
//                    player.performCommand("daily");
//                } else {
//                    player.sendMessage(ChatColor.RED + "You only have " + ChatColor.GOLD + dailyPoints + ChatColor.RED + "/" + ChatColor.GOLD + reward.getPrice() + ChatColor.GREEN + " Daily Tokens" + ChatColor.RED + "!");
//                    soundManager.soundNo(player, 1);
//                }
//            }));
//
//
//        }
//
//        gui.addPane(background);
//        gui.addPane(display);
//        gui.addPane(staticPane);
//        gui.show(player);
//    }

    private void takePoints(Player player, int amount) {
        player.sendMessage(ChatColor.GOLD.toString() + amount + ChatColor.GREEN + " Daily Token" + ChatColor.RED + " has been deducted from your account!");
        betonPointsManager.takePoint(player, "items.Login", amount);
    }

    Random random = new Random();

    private void reward(Player player, DailyShopList dailyShopList) {
        if (MMOItemsHelperImpl.getItem(dailyShopList.type, dailyShopList.getItemID()) != null &&
                !dailyShopList.itemID.equals("ENCHANTED_PUFFERFISH"))
            mmoItemsGive.giveMMOItem(player, dailyShopList.type, dailyShopList.getItemID(), dailyShopList.amount);
        switch (dailyShopList.getType()) {
            case "COINS25_000":
                economy.giveMoney(player, dailyShopList.amount);
                break;
            case "ENCHANTED_MATERIALS":
                mmoItemsGive.giveMMOItem(player, "MATERIAL", EnchantedMaterialList.values()[random.nextInt(EnchantedMaterialList.values().length)].getID());
        }

    }

    public enum DailyShopList {
        ENGRAM("MATERIAL", "ENGRAM1", 1, 1),
        MAGICAL_BOX("CONSUMABLE", "MAGICAL_BOX5", 4, 2),
        ENCHANTED_MATERIALS("ENCHANTED_MATERIALS", "PUFFERFISH", 16, 3),
        COINS25_000("COINS25_000", "SUNFLOWER", 25_000, 4),
        PROFESSION_BOOSTER_BOX("CONSUMABLE", "PROFESSION_BOOSTER_BOX5", 1, 5),
        ENGRAM2("MATERIAL", "ENGRAM2", 1, 6),
        COMING_SOON("COMING_SOON", "RED_STAINED_GLASS_PANE", 1, 999),
        ;

        public final String type;
        public final String itemID;
        public final int amount;
        public final int price;

        DailyShopList(String type, String itemID, int amount, int price) {
            this.itemID = itemID;
            this.type = type;
            this.amount = amount;
            this.price = price;
        }

        public String getType() {
            return type;
        }

        public String getItemID() {
            return itemID;
        }

        public int getAmount() {
            return amount;
        }

        public int getPrice() {
            return price;
        }
    }
}

