package monzter.adventurescraft.plugin.utilities.mmoitems;

import io.lumine.mythic.lib.api.util.SmartGive;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import net.Indyuce.mmoitems.MMOItems;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class MMOItemsGiveImpl implements MMOItemsGive {
    private final MMOItems mmoItems;
    private final SoundManager soundManager;
    public MMOItemsGiveImpl(MMOItems mmoItems, SoundManager soundManager) {
        this.mmoItems = mmoItems;
        this.soundManager = soundManager;
    }

    @Override
    public void giveMMOItem(Player player, String type, String id) {
        giveMMOItem(player, type, id, 1);
    }

    @Override
    public void giveMMOItem(Player player, String type, String id, int amount) {
        giveMMOItem(player, type, id, amount, false);
    }

    @Override
    public void giveMMOItem(Player player, String type, String id, int amount, boolean silent) {
        final ItemStack itemStack = mmoItems.getItem(type, id).asQuantity(amount);
        new SmartGive(player).give(itemStack);
        if (!silent) {
            player.sendMessage(ChatColor.YELLOW + "You received " + ChatColor.GOLD + amount + ChatColor.YELLOW + "x " + itemStack.getItemMeta().getDisplayName() + ChatColor.YELLOW + "!");
            soundManager.playSound(player, Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
        }
    }
}
