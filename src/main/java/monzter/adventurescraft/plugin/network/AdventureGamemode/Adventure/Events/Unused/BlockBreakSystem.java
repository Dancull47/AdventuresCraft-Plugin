//package monzter.adventurescraft.plugin.network.AdventureGamemode.Adventure.Events;
//
//import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
//import com.sk89q.worldguard.protection.regions.ProtectedRegion;
//import io.lumine.mythic.lib.api.item.NBTItem;
//import monzter.adventurescraft.plugin.AdventuresCraft;
//import monzter.adventurescraft.plugin.utilities.general.ConsoleCommand;
//import monzter.adventurescraft.plugin.utilities.general.SoundManager;
//import monzter.adventurescraft.plugin.utilities.luckperms.PermissionLP;
//import net.Indyuce.mmoitems.MMOItems;
//import org.bukkit.*;
//import org.bukkit.entity.Player;
//import org.bukkit.event.EventHandler;
//import org.bukkit.event.Listener;
//import org.bukkit.event.block.BlockBreakEvent;
//import org.bukkit.event.player.PlayerInteractEvent;
//import org.bukkit.inventory.ItemStack;
//
//public class BlockBreakSystem implements Listener {
//    private final AdventuresCraft plugin;
//    private final SoundManager soundManager;
//    private final Location location = new Location(Bukkit.getWorld("Spawn"), -158.0, 16.0, 145.0, 0.0f, 0.0f);
//    private final Location woolCleanerLocation = new Location(Bukkit.getWorld("Spawn"), -541.0, 25.0, -542.0, 0.0f, 0.0f);
//
//    public BlockBreakSystem(AdventuresCraft plugin, SoundManager soundManager) {
//        this.plugin = plugin;
//        this.soundManager = soundManager;
//    }
//
//// Realized I'd need to make an entire Block Regen system
//    @EventHandler
//    public void graveyard(BlockBreakEvent event) {
//        ItemStack tool = event.getPlayer().getInventory().getItemInMainHand();
//        Material block = event.getBlock().getType();
//        for (BlockList blockList : BlockList.values()) {
//            if (block == blockList.getBlock() && toolCheck(tool, blockList.getVanillaTool(), blockList.getMmoItemTool()))
//
//        }
//    }
//
//    public boolean toolCheck(ItemStack playersTool, Material[] vanillaTools, String[] mmoItemTool) {
//        if (mmoItemTool == null & vanillaTools == null)
//            return true;
//        else if (vanillaTools != null)
//            for (Material tool: vanillaTools)
//                if (playersTool.getType() == tool)
//                    return true;
//        else if (mmoItemTool != null) {
//                    final NBTItem nbtItem = NBTItem.get(playersTool);
//                    final String id = MMOItems.getID(nbtItem);
//                    for (String toolID: mmoItemTool) {
//                        String[] split = toolID.split(",");
////                        Can add split[0] to check itemType as well, but currently we only use tool type
//                        if (split[1].equals(id))
//                            return true;
//                    }
//                }
//        return false;
//    }
//    public boolean regionCheck(Location location, String[] regions) {
//        if (regions == null)
//            return true;
//        else
//            for (String regionString: regions)
////                for (ProtectedRegion region: WorldGuardPlugin.inst().)
//        return false;
//    }
//
//    @EventHandler
//    public void woolCleaner(PlayerInteractEvent event) {
//        switch (plugin.SERVER) {
//            case "Adventure":
//                if (event.getClickedBlock() != null && event.getClickedBlock().getType().equals(Material.CAULDRON))
//                    if (event.getClickedBlock().getLocation().equals(woolCleanerLocation) && event.getPlayer().getInventory().getItemInMainHand().getType() != null) {
//                        ItemStack mainHand = event.getPlayer().getInventory().getItemInMainHand();
//                        Player player = event.getPlayer();
//                        if (Tag.WOOL.isTagged(mainHand.getType()) && mainHand.getType() != Material.WHITE_WOOL) {
//                            player.getInventory().setItemInMainHand(new ItemStack(Material.WHITE_WOOL, mainHand.getAmount()));
//                            player.sendMessage(ChatColor.GREEN + "Your wool has been cleaned!");
//                            soundManager.playSound(player, Sound.ITEM_BUCKET_EMPTY, 1, 1);
//                        } else if (mainHand.getType() == Material.WHITE_WOOL) {
//                            player.sendMessage(ChatColor.RED + "Your wool is already cleaned!");
//                            soundManager.soundNo(player, 1);
//                        }
//
//                    }
//        }
//    }
//
//    @EventHandler
//    public void loomControl(PlayerInteractEvent event) {
//        switch (plugin.SERVER) {
//            case "Adventure":
//            case "Home":
//                if (event.getClickedBlock() != null && event.getClickedBlock().getType().equals(Material.LOOM))
//                    if (!event.getPlayer().hasPermission("LOOM.UNLOCKED")) {
//                        event.getPlayer().sendMessage(ChatColor.RED + "You must help " + ChatColor.GREEN + "Sandrah " + ChatColor.RED + "within the " + ChatColor.GREEN + "Estate " +
//                                ChatColor.RED + "to use a " + ChatColor.GOLD + "Loom" + ChatColor.GREEN + "!");
//                        soundManager.soundNo(event.getPlayer(), 1);
//                        event.setCancelled(true);
//                    }
//        }
//    }
//}