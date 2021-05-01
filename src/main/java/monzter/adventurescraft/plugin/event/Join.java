package monzter.adventurescraft.plugin.event;

import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.MMOItemsGiveItem;
import monzter.adventurescraft.plugin.utilities.PermissionsFacade;
import net.Indyuce.mmocore.api.player.PlayerData;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class Join implements Listener {
    private final AdventuresCraft plugin;
    private final MMOItemsGiveItem mmoItemsGiveItem;
    private final PermissionsFacade permissionsFacade;
    private final TextComponent mining = Component.text("You can start mining by using ")
            .color(NamedTextColor.GREEN)
            .append(Component.text("/Mine", NamedTextColor.GOLD, TextDecoration.BOLD))
            .hoverEvent(Component.text("Click to visit your latest Mine!", NamedTextColor.GREEN))
            .clickEvent(ClickEvent.runCommand("/Mine"))
            .append(Component.text("!"));

    public Join(AdventuresCraft plugin, MMOItemsGiveItem mmoItemsGiveItem, PermissionsFacade permissionsFacade) {
        this.plugin = plugin;
        this.mmoItemsGiveItem = mmoItemsGiveItem;
        this.permissionsFacade = permissionsFacade;
    }

    @EventHandler
    private final void onJoin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        player.setCollidable(true);
        if (player.getInventory().getItem(8).getType() == Material.WRITTEN_BOOK) {
            final ItemStack book = player.getInventory().getItem(8);
            if (book != null || book.getType() == Material.WRITTEN_BOOK) {
                player.openBook(book);
            }
        }
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> {
            PlayerData playerData = PlayerData.get(player.getUniqueId());
            player.setHealth(player.getMaxHealth());
            playerData.giveMana(999);
            if (player.getWorld().getName().equals("World")) {
                player.performCommand("spawn");
            } else if (player.getWorld().getName().equals("Homes")) {
                player.performCommand("home");
            }
        }, 20L);
        if (player.isOp()) { // OP Check
            if (!player.getAddress().getHostName().equals("c-68-80-205-205.hsd1.pa.comcast.net")) {
                player.setOp(false);
                player.sendMessage(ChatColor.DARK_RED + "Your OP has been removed!");
            }
        }
        if (!player.hasPermission("mines.tp.m")) {
            player.sendMessage(mining);
        }
        if (!player.hasPermission("KIT.RECEIVED")) {
            permissionsFacade.givePermission(player, "KIT.RECEIVED");
            mmoItemsGiveItem.giveMMOItem(player, "TOOL", "PRISONER_PICKAXE");
            mmoItemsGiveItem.giveMMOItem(player, "ARMOR", "PRISONER_HAT");
            mmoItemsGiveItem.giveMMOItem(player, "ARMOR", "PRISONER_CHESTPLATE");
            mmoItemsGiveItem.giveMMOItem(player, "ARMOR", "PRISONER_LEGGINGS");
            mmoItemsGiveItem.giveMMOItem(player, "ARMOR", "PRISONER_SHOES");
        }
    }
}