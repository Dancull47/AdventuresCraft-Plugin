package monzter.adventurescraft.plugin.utilities.general.Purchase;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.shops.npcs.ItemList;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.List;

public interface ShopBuilder {
    void menuBase(ChestGui gui, List<ItemList> guiContents, Player player, String shop, Material backgroundColor);

    public void wanderingTraderBase(ChestGui gui, ItemList guiContents, Player player, int shopNumber);

    GuiItem generateItem(Player player, ItemList itemList, String shop);
    }
