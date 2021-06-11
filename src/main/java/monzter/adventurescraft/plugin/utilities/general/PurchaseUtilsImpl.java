package monzter.adventurescraft.plugin.utilities.general;

import io.lumine.mythic.lib.api.item.NBTItem;
import monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.shops.npcs.ItemList;
import monzter.adventurescraft.plugin.utilities.text.NumberFormat;
import monzter.adventurescraft.plugin.utilities.vault.Economy;
import net.Indyuce.mmoitems.MMOItems;
import org.apache.commons.lang.WordUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class PurchaseUtilsImpl implements PurchaseUtils {
    private final Economy economy;
    private final FullInventory fullInventory;
    private final SoundManager soundManager;
    private final NumberFormat numberFormat;
    private final MMOItems mmoItems;


    public PurchaseUtilsImpl(Economy economy, FullInventory fullInventory, SoundManager soundManager, NumberFormat numberFormat, MMOItems mmoItems) {
        this.economy = economy;
        this.fullInventory = fullInventory;
        this.soundManager = soundManager;
        this.numberFormat = numberFormat;
        this.mmoItems = mmoItems;
    }

    @Override
    public void purchase(Player player, ItemList itemList, int amount) {
        if (economy.hasMoney(player, itemList.getCoinPrice())) {
            if (player.getLevel() >= itemList.getExpPrice()) {

                String id = mmoItems.getID(NBTItem.get(itemList.getItemStack()));
                if (id != null) {
                    player.sendMessage(ChatColor.GREEN + "You purchased " + ChatColor.GOLD + amount + "x " + ChatColor.YELLOW +
                            WordUtils.capitalizeFully(itemList.getItemStack().getType().toString().replace('_', ' ')) + ChatColor.GREEN + " for:");
                    player.getInventory().addItem(new ItemStack(itemList.getItemStack().getType(), amount));
                } else {
                    player.sendMessage(ChatColor.GREEN + "You purchased " + ChatColor.GOLD + amount + "x " + ChatColor.YELLOW +
                            mmoItems.getItem(itemList.getType(), itemList.getID()).getItemMeta().getDisplayName() + ChatColor.GREEN + " for:");
                    player.getInventory().addItem(mmoItems.getItem(itemList.getType(), itemList.getID()).asQuantity(amount));
                }

                if (itemList.getItemPrice() != null) {
                    if (hasItem(player, itemList.getItemPrice(), amount).size() >= itemList.getItemPrice().length) {
                        if (!fullInventory.fullInventory(player)) {
                            int index = 0;
                            List<Integer> indexes = hasItem(player, itemList.getItemPrice(), amount);
                            for (String item : itemList.getItemPrice()) {
                                String[] itemPriceSplit = item.split(";");

                                if (player.getInventory().getItem(indexes.get(index)).getAmount() - (Integer.valueOf(itemPriceSplit[2])*amount) == 0)
                                    player.getInventory().setItem(indexes.get(index), new ItemStack(Material.AIR));
                                else
                                    player.getInventory().setItem(indexes.get(index), player.getInventory().getItem(indexes.get(index)).asQuantity(player.getInventory().getItem(indexes.get(index)).getAmount() - (Integer.valueOf(itemPriceSplit[2])*amount)));
//                                if (player.getInventory().getItem(indexes.get(index)).equals(mmoItems.getItem(itemPriceSplit[0], itemPriceSplit[1]))
//                                        && player.getInventory().getItem(indexes.get(index)).getAmount() - Integer.valueOf(itemPriceSplit[2]) == 0)
//                                    player.getInventory().setItem(indexes.get(index), new ItemStack(Material.AIR));
//                                else if (player.getInventory().getItem(indexes.get(index)).equals(mmoItems.getItem(itemPriceSplit[0], itemPriceSplit[1]))
//                                        && player.getInventory().getItem(indexes.get(index)).getAmount() - Integer.valueOf(itemPriceSplit[2]) > 0)
//                                    player.getInventory().setItem(indexes.get(index), player.getInventory().getItem(indexes.get(index)).asQuantity(player.getInventory().getItem(indexes.get(index)).getAmount() - Integer.valueOf(itemPriceSplit[2])));
                                player.sendMessage(ChatColor.RED + "- " + ChatColor.GOLD + (Integer.valueOf(itemPriceSplit[2])*amount) + "x " +
                                        mmoItems.getItem(itemPriceSplit[0], itemPriceSplit[1]).getItemMeta().getDisplayName());
                                index++;
                            }
                        }
                    }
                }

                if (itemList.getCoinPrice() > 0) {
                    economy.takeMoney(player, itemList.getCoinPrice() * amount);
                }
                if (itemList.getCoinPrice() > 0) {
                    player.setLevel(player.getLevel() - (itemList.getExpPrice() * amount));
                    player.sendMessage(ChatColor.AQUA + "Ξ " + numberFormat.numberFormat(itemList.getExpPrice() * amount) + ChatColor.RED + " has been deducted from your account!");
                }
                soundManager.soundYes(player, 1);
            }
        }
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

    private List<Integer> hasItem(Player player, String[] items, int purchaseAmount) {
        List<Integer> indexes = new ArrayList<>();
        for (String item : items) {
            int index = 0;
            String[] itemPriceSplit = item.split(";");
            for (ItemStack currentItem : player.getInventory().getContents()) {
                NBTItem nbtItem = NBTItem.get(currentItem);
                if (mmoItems.getID(nbtItem) != null)
                    if (mmoItems.getType(nbtItem).toString().replace(" ", "").equals(itemPriceSplit[0].replace(" ", "")) &&
                            mmoItems.getID(nbtItem).toString().replace(" ", "").equals(itemPriceSplit[1]))
                        if (currentItem.getAmount() >= (Integer.valueOf(itemPriceSplit[2])*purchaseAmount)) {
                            indexes.add(index);
                            break;
                        }
                index++;
            }
        }
        return indexes;
    }

}
