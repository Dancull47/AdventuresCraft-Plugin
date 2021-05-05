package monzter.adventurescraft.plugin.event;

import io.lumine.mythic.utils.Schedulers;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.luckperms.PermissionLP;
import monzter.adventurescraft.plugin.utilities.mmoitems.MMOItemsGive;
import monzter.adventurescraft.plugin.utilities.vault.Permission;
import net.Indyuce.mmocore.api.player.PlayerData;
import net.craftersland.data.bridge.PD;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class Join implements Listener {
    private final AdventuresCraft plugin;
    private final MMOItemsGive mmoItemsGive;
    private final PermissionLP permissionLP;
    private int tries = 0;

    private final TextComponent mining = Component.text("You can start mining by using ")
            .color(NamedTextColor.GREEN)
            .append(Component.text("/Mine", NamedTextColor.GOLD, TextDecoration.BOLD))
            .hoverEvent(Component.text("Click to visit your latest Mine!", NamedTextColor.GREEN))
            .clickEvent(ClickEvent.runCommand("/Mine"))
            .append(Component.text("!"));

    public Join(AdventuresCraft plugin, MMOItemsGive mmoItemsGive, PermissionLP permissionLP) {
        this.plugin = plugin;
        this.mmoItemsGive = mmoItemsGive;
        this.permissionLP = permissionLP;
    }

    @EventHandler
    private void onJoin(PlayerJoinEvent event) {
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
            player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());
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
            Schedulers.async().runRepeating(new BukkitRunnable() {
                @Override
                public void run() {
                    if (PD.api.isInventoryArmorSyncComplete(player)) {
                        permissionLP.givePermission(player, "KIT.RECEIVED");
                        mmoItemsGive.giveMMOItem(player, "TOOL", "PRISONER_PICKAXE");
                        mmoItemsGive.giveMMOItem(player, "ARMOR", "PRISONER_HAT");
                        mmoItemsGive.giveMMOItem(player, "ARMOR", "PRISONER_CHESTPLATE");
                        mmoItemsGive.giveMMOItem(player, "ARMOR", "PRISONER_LEGGINGS");
                        mmoItemsGive.giveMMOItem(player, "ARMOR", "PRISONER_SHOES");
                        cancel();
                    }
                    if (tries == 10) {
                        player.sendMessage(ChatColor.RED + "We were unable to give your starting Miner Items. Please rejoin the server and we will try again! If you have tried this multiple times, please PM an Admin on Discord, or ask for help in chat!");
                        cancel();
                    }
                    tries++;
                }
            }, 20, 60);
        }
    }
}