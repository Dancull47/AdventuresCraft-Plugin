package monzter.adventurescraft.plugin.utilities.general;

import io.lumine.mythic.lib.api.item.NBTItem;
import monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.mainMenu.donation.DonationItemList;
import monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.shops.npcs.ItemList;
import monzter.adventurescraft.plugin.utilities.beton.BetonPointsManager;
import monzter.adventurescraft.plugin.utilities.enums.PrisonStatsDisplay;
import monzter.adventurescraft.plugin.utilities.text.NumberFormat;
import monzter.adventurescraft.plugin.utilities.vault.Economy;
import net.Indyuce.mmoitems.MMOItems;
import org.apache.commons.lang.WordUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.betoncraft.betonquest.BetonQuest;

public class PurchaseUtilsImpl implements PurchaseUtils {
    private final Economy economy;
    private final FullInventory fullInventory;
    private final SoundManager soundManager;
    private final NumberFormat numberFormat;
    private final MMOItems mmoItems;
    private final BetonPointsManager betonPointsManager;


    public PurchaseUtilsImpl(Economy economy, FullInventory fullInventory, SoundManager soundManager, NumberFormat numberFormat, MMOItems mmoItems, BetonPointsManager betonPointsManager) {
        this.economy = economy;
        this.fullInventory = fullInventory;
        this.soundManager = soundManager;
        this.numberFormat = numberFormat;
        this.mmoItems = mmoItems;
        this.betonPointsManager = betonPointsManager;
    }

    @Override
    public void purchase(Player player, ItemList itemList, int amount) {
        if (hasBasicCheck(player, itemList, amount) && !fullInventory.fullInventory(player)) {
            if (itemList.getID() == null) {
                player.sendMessage(ChatColor.GREEN + "You purchased " + ChatColor.GOLD + amount + "x " + ChatColor.YELLOW +
                        WordUtils.capitalizeFully(itemList.getItemStack().getType().toString().replace('_', ' ')) + ChatColor.GREEN + " for:");
                player.getInventory().addItem(new ItemStack(itemList.getItemStack().getType(), amount));
            } else {
                player.sendMessage(ChatColor.GREEN + "You purchased " + ChatColor.GOLD + amount + "x " + ChatColor.YELLOW +
                        mmoItems.getItem(itemList.getType(), itemList.getID()).getItemMeta().getDisplayName() + ChatColor.GREEN + " for:");
                player.getInventory().addItem(mmoItems.getItem(itemList.getType(), itemList.getID()).asQuantity(amount));
            }

            if (itemList.getItemPrice() != null)
                if (hasItem(player, itemList.getItemPrice(), amount)) {
                    for (String item : itemList.getItemPrice()) {
                        String[] itemPriceSplit = item.split(";");
                        String priceID = itemPriceSplit[1];
                        String priceType = itemPriceSplit[0];
                        int price = Integer.valueOf(itemPriceSplit[2]) * amount;
                        for (ItemStack currentItem : player.getInventory().getContents()) {
                            //        Cancels loop once price has been fulfilled (Could probably set to == instead of <=)
                            if (price <= 0)
                                break;
                            NBTItem nbtItem = NBTItem.get(currentItem);
                            if (nbtItem.hasType()) {
                                String ID = mmoItems.getID(nbtItem).toString();
                                String type = mmoItems.getType(nbtItem).toString();
                                if (ID.equalsIgnoreCase(priceID) && type.equalsIgnoreCase(priceType)) {
//                                    If the current price is less than the ItemStack's Quantity, it'll only take the amount it needs
                                    if (price > 0 && currentItem.getAmount() - price >= 0) {
                                        currentItem.setAmount(currentItem.getAmount() - price);
                                        price -= price;
//                                    Otherwise it'll take the full stack & for loop resets
                                    } else if (price > 0) {
                                        price -= currentItem.getAmount();
                                        currentItem.setAmount(0);
                                    }
                                }
                            }
                        }
                    }
                }
            if (itemList.getCoinPrice() > 0)
                economy.takeMoney(player, itemList.getCoinPrice() * amount);
            if (itemList.getExpPrice() > 0) {
                player.setLevel(player.getLevel() - (itemList.getExpPrice() * amount));
                player.sendMessage(ChatColor.GOLD + numberFormat.numberFormat(itemList.getExpPrice() * amount) + ChatColor.AQUA + " Ξ Levels " + ChatColor.RED + "have been deducted from your account!");
            }
            soundManager.soundYes(player, 1);
        }

    }

    public void purchase(Player player, DonationItemList itemList, int amount) {
        int balance = betonPointsManager.getPoints("items.AdventureCoin", BetonQuest.getInstance().getPlayerData(player.getUniqueId().toString()).getPoints());
        System.out.println("v" + balance);

        if (balance >= itemList.getAcPrice() * amount) {
            if (!fullInventory.fullInventory(player)) {
                player.sendMessage(ChatColor.GREEN + "You purchased " + ChatColor.GOLD + amount + "x " + ChatColor.YELLOW +
                        mmoItems.getItem(itemList.getType(), itemList.getID()).getItemMeta().getDisplayName() + ChatColor.GREEN + " for:");
                player.getInventory().addItem(mmoItems.getItem(itemList.getType(), itemList.getID()).asQuantity(amount));
                if (itemList.getAcPrice() > 0) {
                    betonPointsManager.takePointACs(player, itemList.getAcPrice() * amount);
                    player.sendMessage(ChatColor.GOLD + numberFormat.numberFormat(itemList.getAcPrice() * amount) + " " + PrisonStatsDisplay.ADVENTURE_COINS.getName() + ChatColor.RED + " have been deducted from your account!");
                    soundManager.soundYes(player, 1);
                }
            }
        }
    }

    //    Basic checks for Eco & Levels
    public boolean hasBasicCheck(Player player, ItemList itemList, int amount) {
        if (economy.getBalance(player) < (itemList.getCoinPrice() * amount))
            return false;
        else if (player.getLevel() < (itemList.getExpPrice() * amount))
            return false;
        return true;
    }

//    @Override
//    public void purchase(Player player, ItemStack itemStack, int amount, double coinPrice) {
//        if (economy.hasMoney(player, coinPrice)) {
//            if (!fullInventory.fullInventory(player)) {
//                economy.takeMoney(player, coinPrice * amount);
//                String id = mmoItems.getID(NBTItem.get(itemStack));
//                if (id == null) {
//                    player.getInventory().addItem(new ItemStack(itemStack.getType(), amount));
//                    player.sendMessage(ChatColor.GREEN + "You purchased " + ChatColor.GOLD + amount + "x " + ChatColor.YELLOW +
//                            WordUtils.capitalizeFully(itemStack.getType().toString().replace('_', ' ')) + ChatColor.GREEN + " for " +
//                            ChatColor.YELLOW + "⛂ " + numberFormat.numberFormat(coinPrice * amount) + "!");
//                } else {
//                    player.getInventory().addItem(mmoItems.getItem(mmoItems.getType(NBTItem.get(itemStack)), id).asQuantity(amount));
//                    player.sendMessage(ChatColor.GREEN + "You purchased " + ChatColor.GOLD + amount + "x " + ChatColor.YELLOW +
//                            mmoItems.getItem(mmoItems.getType(NBTItem.get(itemStack)), mmoItems.getID(NBTItem.get(itemStack))).getItemMeta().getDisplayName() +
//                            ChatColor.GREEN + " for " + ChatColor.YELLOW + "⛂ " + numberFormat.numberFormat(coinPrice * amount) + ChatColor.GREEN + "!");
//                }
//                soundManager.soundYes(player, 1);
//            }
//        }
//    }
//
//    @Override
//    public void purchase(Player player, ItemStack itemStack, int amount, double coinPrice, int expPrice) {
//        if (economy.hasMoney(player, coinPrice))
//            if (player.getLevel() >= expPrice)
//                if (!fullInventory.fullInventory(player)) {
//                    economy.takeMoney(player, coinPrice * amount);
//                    player.setLevel(player.getLevel() - (expPrice * amount));
//                    String id = mmoItems.getID(NBTItem.get(itemStack));
//                    if (id == null) {
//                        player.getInventory().addItem(new ItemStack(itemStack.getType(), amount));
//                        player.sendMessage(ChatColor.GREEN + "You purchased " + ChatColor.GOLD + amount + "x " + ChatColor.YELLOW +
//                                WordUtils.capitalizeFully(itemStack.getType().toString().replace('_', ' ')) + ChatColor.GREEN + " for " +
//                                ChatColor.YELLOW + "⛂ " + numberFormat.numberFormat(coinPrice * amount) + ChatColor.GREEN + ", "
//                                + ChatColor.AQUA + "Ξ " + numberFormat.numberFormat(expPrice * amount) + ChatColor.GREEN + "!");
//                    } else {
//                        player.getInventory().addItem(mmoItems.getItem(mmoItems.getType(NBTItem.get(itemStack)), id).asQuantity(amount));
//                        player.sendMessage(ChatColor.GREEN + "You purchased " + ChatColor.GOLD + amount + "x " + ChatColor.YELLOW +
//                                mmoItems.getItem(mmoItems.getType(NBTItem.get(itemStack)), mmoItems.getID(NBTItem.get(itemStack))).getItemMeta().getDisplayName() +
//                                ChatColor.GREEN + " for " + ChatColor.YELLOW + "⛂ " + numberFormat.numberFormat(coinPrice * amount) + ChatColor.GREEN + ", " +
//                                ChatColor.AQUA + "Ξ " + numberFormat.numberFormat(expPrice * amount) + ChatColor.GREEN + "!");
//                    }
//                    soundManager.soundYes(player, 1);
//                }
//    }

    @Override
    public boolean hasItem(Player player, String[] items, int purchaseAmount) {
//        Loops through all Items (Cost)
        for (String item : items) {
//        Creates a variable to count how many of that item is within the inventory
            int totalInInv = 0;
            String[] itemPriceSplit = item.split(";");
            String priceID = itemPriceSplit[1];
            String priceType = itemPriceSplit[0];
            int priceQuantity = Integer.valueOf(itemPriceSplit[2]);
            //        Loops through Player's inventory
            for (ItemStack currentItem : player.getInventory().getContents()) {
                NBTItem nbtItem = NBTItem.get(currentItem);
                if (nbtItem.hasType()) {
                    String ID = mmoItems.getID(nbtItem).toString();
                    String type = mmoItems.getType(nbtItem).toString();
                    if (ID.equalsIgnoreCase(priceID) && type.equalsIgnoreCase(priceType)) {
                        //        If the item is found within the inventory, the ItemStack amount is added to total
                        totalInInv += currentItem.getAmount();
                    }
                }
            }
            //        If total amount of the costing ItemStack is within the Inventory, returns false
            if (totalInInv < (priceQuantity * purchaseAmount))
                return false;
        }
        //        Otherwise return true because all items passed the check
        return true;
    }

//    String[] itemPriceSplit = item.split(";");
//    String priceID = itemPriceSplit[0].replace(" ", "");
//    String priceType = itemPriceSplit[1];
//    int priceQuantity = Integer.valueOf(itemPriceSplit[2]);
//            for (ItemStack currentItem : player.getInventory().getContents()) {
//        NBTItem nbtItem = NBTItem.get(currentItem);
//        if (nbtItem.hasType()) {
//            String ID = mmoItems.getID(nbtItem).toString().replace(" ", "");
//            String type = mmoItems.getType(nbtItem).toString().replace(" ", "");
//            if (ID.equalsIgnoreCase(priceID) && type.equalsIgnoreCase(priceType)){
//                System.out.println("Match");

}
