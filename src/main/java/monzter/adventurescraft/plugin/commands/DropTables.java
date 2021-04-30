//package monzter.adventurescraft.plugin.commands;
//
//import com.gmail.filoghost.holographicdisplays.api.Hologram;
//import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
//import me.clip.placeholderapi.PlaceholderAPI;
//import me.lucko.helper.random.RandomSelector;
//import monzter.adventurescraft.plugin.AdventuresCraft;
//import monzter.adventurescraft.plugin.commands.dropTables.testDrop;
//import monzter.adventurescraft.plugin.utilities.acUtils;
////import monzter.adventurescraft.plugin.commands.dropTables.Eggs.CommonPetEgg;
//import monzter.adventurescraft.plugin.event.extras.DonationRewardList;
//import monzter.adventurescraft.plugin.event.extras.StatsDisplay;
//import org.bukkit.*;
//import org.bukkit.command.Command;
//import org.bukkit.command.CommandExecutor;
//import org.bukkit.command.CommandSender;
//import org.bukkit.entity.Player;
//import org.bukkit.inventory.ItemStack;
//import org.bukkit.scheduler.BukkitRunnable;
//
//import java.util.Arrays;
//
//public class DropTables implements CommandExecutor {
//    private final AdventuresCraft plugin;
//
//    public DropTables(AdventuresCraft plugin) {
//        this.plugin = plugin;
//    }
//
//    @Override
//    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
//        if (sender instanceof Player) {
//            Player player = ((Player) sender).getPlayer();
//            double luck = Double.valueOf(PlaceholderAPI.setPlaceholders(player, "%ac_Stat_LuckMultiplier%"));
//            if (args.length < 1) {
//                player.sendMessage(ChatColor.RED + "/DropTable <Item>");
//                return true;
//            } else {
//                RandomSelector<testDrop> selector = RandomSelector.weighted(Arrays.asList(testDrop.values()));
//
//                testDrop prize1 = selector.pick();
//                acUtils.giveMMOItem(player, prize1.getType(), prize1.getId());
////                                for (int i = 0; i < 1; ) {
////                    switch (args[0]){
////                        case "PET_EGG":
////                        for (CommonPetEgg reward : CommonPetEgg.values()) {
////                            System.out.println("Reward " + reward + " Chance" + reward.chance*luck);
////                            if (acUtils.chanceCheck(reward.chance*luck, player, 1, reward.type, reward.id, reward.displayName)) {
////                                i++;
////                                break;
////                            }
////                        }
////                        case "PET_EGG2":
////                        for (CommonPetEgg reward : CommonPetEgg.values()) {
////                            if (acUtils.chanceCheck(reward.chance*luck, player, 1, reward.type, reward.id, reward.displayName)) {
////                                i++;
////                                break;
////                            }
////                            break;
////                        }
////                    }
////                }
////            }
////        }
//                return false;
//            }
//
////    public void commonPetEggTable(Player player, int amount, int luckLevel){
////        for (int i = 0; i < 1; ) {
////            for (CommonPetEgg reward : CommonPetEgg.values()) {
////                if (acUtils.chanceCheck(reward.chance*luckLevel)) {
////                    acUtils.giveMMOItem(player, reward.type, reward.id, true);
////                    player.sendMessage(ChatColor.YELLOW.toString() + ChatColor.BOLD + "Rewards:");
////                    player.sendMessage(reward.displayName + " " + ChatColor.GOLD + reward.chance + "%");
////                    i++;
////                    break;
////                }
////            }
////        }
////    }
//
//        }
//        return false;
//    }
//}
//
