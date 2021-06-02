package monzter.adventurescraft.plugin.network.PrisonGamemode.shared.events;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.util.Location;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.StringFlag;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.network.PrisonGamemode.prison.events.mining.BlockBreakMining;
import monzter.adventurescraft.plugin.network.PrisonGamemode.shared.GUIs.mainMenu.donation.miningPass.MiningPassLevels;
import monzter.adventurescraft.plugin.network.PrisonGamemode.shared.events.extras.DonationRewardList;
import monzter.adventurescraft.plugin.network.PrisonGamemode.shared.events.extras.Pet;
import monzter.adventurescraft.plugin.utilities.enchanting.CalculateEnchantments;
import monzter.adventurescraft.plugin.utilities.enums.PetEggList;
import monzter.adventurescraft.plugin.network.PrisonGamemode.shared.events.extras.Stats;
import monzter.adventurescraft.plugin.network.PrisonGamemode.prison.events.mining.BeachEvent;
import monzter.adventurescraft.plugin.utilities.text.NumberFormat;
import monzter.adventurescraft.plugin.utilities.vault.Economy;
import net.milkbowl.vault.permission.Permission;
import org.apache.commons.lang.WordUtils;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import pl.betoncraft.betonquest.BetonQuest;
import pl.betoncraft.betonquest.Point;

import java.util.*;

public class Placeholder extends PlaceholderExpansion {

    private final AdventuresCraft plugin;
    private final Permission permission;
    private final NumberFormat numberFormat;
    
    private final Set<Pet> pets;
    private final StringFlag displayNameFlag;
    private long restartTime;
    private final Economy economy;

    private CalculateEnchantments calculateEnchantments;
    private final List<MiningPassLevels> reversedList = Lists.reverse(Arrays.asList(MiningPassLevels.values()));
//    List<Point> global = BetonQuest.getInstance().getGlobalData().getPoints();

    public Placeholder(AdventuresCraft plugin, Permission permission, NumberFormat numberFormat, Set<Pet> pets, StringFlag displayNameFlag, long restartTime, Economy economy, CalculateEnchantments calculateEnchantments) {
        this.plugin = plugin;
        this.permission = permission;
        this.numberFormat = numberFormat;
        this.pets = pets;
        this.displayNameFlag = displayNameFlag;
        this.restartTime = restartTime;
        this.economy = economy;
        this.calculateEnchantments = calculateEnchantments;
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public String getAuthor() {
        return "Monzter";
    }

    @Override
    public String getIdentifier() {
        return "ac";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }

    /**
     * This is the method called when a placeholder with our identifier
     * is found and needs a value.
     * <br>We specify the value identifier in this method.
     * <br>Since version 2.9.1 can you use OfflinePlayers in your requests.
     *
     * @param player     A {@link org.bukkit.OfflinePlayer OfflinePlayer}.
     * @param identifier A String containing the identifier/value.
     * @return Possibly-null String of the requested identifier.
     */
    @Override
    public String onRequest(OfflinePlayer player, String identifier) {
        final List<Point> points = BetonQuest.getInstance().getPlayerData(player.getUniqueId().toString()).getPoints();
        switch (identifier) {
//            case "Rank":
//                String ranks = PlaceholderAPI.setPlaceholders(player, "%prison_rank_tag_default%");
//                String prestiges = PlaceholderAPI.setPlaceholders(player, "%prison_rank_tag_prestiges%")
//                        .replace("§", "")
//                        .replace("7", "")
//                        .replace("[", "")
//                        .replace("c", "")
//                        .replace("]", "")
//                        ;
//                if (ranks
////                        .replace("§", "")
////                        .replace("7", "")
////                        .replace("[", "")
////                        .replace("c", "")
////                        .replace("]", "")
//                        .contains("Z")){
//                    if (Integer.valueOf(prestiges) > 0){
//                        return ChatColor.WHITE + "[" + ChatColor.GREEN + prestiges + ChatColor.GREEN + "]";
//                    }
//                }
//                return ranks;

            case "PetEggList":
                return String.valueOf(PetEggList.COMMON.expToHatch);
            case "PetEgg_formatted":
                return numberFormat.numberFormat(PetEggList.COMMON.expToHatch);
            case "PetEgg2":
                return String.valueOf(PetEggList.UNCOMMON.expToHatch);
            case "PetEgg2_formatted":
                return numberFormat.numberFormat(PetEggList.UNCOMMON.expToHatch);
            case "PetEgg3":
                return String.valueOf(PetEggList.RARE.expToHatch);
            case "PetEgg3_formatted":
                return numberFormat.numberFormat(PetEggList.RARE.expToHatch);
            case "PetEgg4":
                return String.valueOf(PetEggList.LEGENDARY.expToHatch);
            case "PetEgg4_formatted":
                return numberFormat.numberFormat(PetEggList.LEGENDARY.expToHatch);
            case "PetEgg5":
                return String.valueOf(PetEggList.EXOTIC.expToHatch);
            case "PetEgg5_formatted":
                return numberFormat.numberFormat(PetEggList.EXOTIC.expToHatch);
            case "PetEggPhoenix":
                return String.valueOf(PetEggList.PHOENIX.expToHatch);
            case "PetEggPhoenix_formatted":
                return numberFormat.numberFormat(PetEggList.PHOENIX.expToHatch);
            case "PetEggPhoenix2":
                return String.valueOf(PetEggList.PHOENIX2.expToHatch);
            case "PetEggPhoenix2_formatted":
                return numberFormat.numberFormat(PetEggList.PHOENIX2.expToHatch);
            case "PetEggDragon":
                return String.valueOf(PetEggList.DRAGON.expToHatch);
            case "PetEggDragon_formatted":
                return numberFormat.numberFormat(PetEggList.DRAGON.expToHatch);
            case "PetEggDragon2":
                return String.valueOf(PetEggList.DRAGON2.expToHatch);
            case "PetEggDragon2_formatted":
                return numberFormat.numberFormat(PetEggList.DRAGON2.expToHatch);

            case "Donation_PetSlot":
                return numberFormat.numberFormat(DonationRewardList.PetSlot.price);
            case "Donation_UnlimitedWeight":
                return numberFormat.numberFormat(DonationRewardList.UnlimitedWeight.price);
            case "Donation_ExoticLootbox5":
                return numberFormat.numberFormat(DonationRewardList.ExoticLootbox5.price);
            case "Donation_ExoticLootbox10":
                return numberFormat.numberFormat(DonationRewardList.ExoticLootbox10.price);
            case "Donation_MythicalLootbox5":
                return numberFormat.numberFormat(DonationRewardList.MythicalLootbox5.price);
            case "Donation_MythicalLootbox10":
                return numberFormat.numberFormat(DonationRewardList.MythicalLootbox10.price);
            case "Donation_GodlyLootbox5":
                return numberFormat.numberFormat(DonationRewardList.GodlyLootbox5.price);
            case "Donation_GodlyLootbox10":
                return numberFormat.numberFormat(DonationRewardList.GodlyLootbox10.price);
            case "Donation_ExoticPetEgg5":
                return numberFormat.numberFormat(DonationRewardList.ExoticPetEgg5.price);
            case "Donation_ExoticPetEgg10":
                return numberFormat.numberFormat(DonationRewardList.ExoticPetEgg10.price);
            case "Donation_MythicalPetEgg5":
                return numberFormat.numberFormat(DonationRewardList.MythicalPetEgg5.price);
            case "Donation_MythicalPetEgg10":
                return numberFormat.numberFormat(DonationRewardList.MythicalPetEgg10.price);
            case "Donation_GodlyPetEgg5":
                return numberFormat.numberFormat(DonationRewardList.GodlyPetEgg5.price);
            case "Donation_GodlyPetEgg10":
                return numberFormat.numberFormat(DonationRewardList.GodlyPetEgg10.price);
            case "Donation_LegendaryPhoenixPetEgg5":
                return numberFormat.numberFormat(DonationRewardList.LegendaryPhoenixPetEgg5.price);
            case "Donation_LegendaryPhoenixPetEgg10":
                return numberFormat.numberFormat(DonationRewardList.LegendaryPhoenixPetEgg10.price);
            case "Donation_LegendaryDragonPetEgg5":
                return numberFormat.numberFormat(DonationRewardList.LegendaryDragonPetEgg5.price);
            case "Donation_LegendaryDragonPetEgg10":
                return numberFormat.numberFormat(DonationRewardList.LegendaryDragonPetEgg10.price);

            // STATS
            case "Stat_TotalMined":
                return String.valueOf(getPoints("items.TotalBlocks", points));

            case "Stat_MiningSpeed":
                return PlaceholderAPI.setPlaceholders(player, "%mmoitems_stat_faction_damage_breakingspeed%");

            case "Stat_Money":
                return String.valueOf(economy.getBalance(player.getPlayer()));
            case "Stat_Money_formatted":
                return String.valueOf(numberFormat.numberFormat(economy.getBalance(player.getPlayer())));

            case "Stat_Weight":
                return String.valueOf(getPoints("items.Weight", points));
            case "Stat_Weight_formatted":
                return numberFormat.numberFormat(getPoints("items.Weight", points));

            case "Stat_MaxWeight":
                String maxWeight = String.valueOf(getPoints("items.MaxWeight", points));
                String maxWeightMultiplier = PlaceholderAPI.setPlaceholders(player, "%ac_Stat_MaxWeightMultiplier%");
                return String.valueOf(Math.round((50 + Integer.valueOf(maxWeight) + calculatePetStats(player, Stats.MAX_WEIGHT)) * Double.valueOf(maxWeightMultiplier)));
            case "Stat_MaxWeight_formatted":
                return numberFormat.numberFormat(Integer.valueOf(PlaceholderAPI.setPlaceholders(player, "%ac_Stat_MaxWeight%")));

            case "Stat_MaxWeightMultiplier":
                return String.valueOf(calculatePetStats(player, Stats.MAX_WEIGHT_MULTIPLIER));

            case "Stat_BlockMultiplier":
                String blockMultiplierDefault = PlaceholderAPI.setPlaceholders(player, "%mmoitems_stat_stamina_regeneration%");
                return String.valueOf(calculatePetStats(player, Stats.BLOCK_MULTIPLIER) + Double.valueOf(blockMultiplierDefault) + calculateBoosterStats(player, "block"));

            case "Stat_SellMultiplier":
                return String.valueOf(calculatePetStats(player, Stats.SELL_MULTIPLIER) + calculateBoosterStats(player, "sell"));

            case "Stat_LuckMultiplier":
                return String.valueOf((calculateEnchantments.calculateEnchantments(player, "Luck") * .5) + calculatePetStats(player, Stats.LUCK_MULTIPLIER) + calculateBoosterStats(player, "luck"));

            case "Stat_EXPMultiplier":
                return String.valueOf(calculateEnchantments.calculateEnchantments(player, "Experience") + calculatePetStats(player, Stats.EXPERIENCE_MULTIPLIER) + calculateBoosterStats(player, "exp"));

            case "Stat_Pet_EXPMultiplier":
                return String.valueOf(calculateEnchantments.calculateEnchantments(player, "Pet Experience") + calculatePetStats(player, Stats.PET_EXPERIENCE) + calculateBoosterStats(player, "pet_exp"));

            case "Gala_Block":
                return WordUtils.capitalizeFully(BlockBreakMining.getBlock().toString().replace("_", " "));
            case "Gala_Broken":
                return String.valueOf(BlockBreakMining.getBlocksBroken());
            case "Gala_Broken_formatted":
                return numberFormat.numberFormat(BlockBreakMining.getBlocksBroken());
            case "Gala_Max":
                return String.valueOf(BlockBreakMining.getMax());
            case "Gala_Max_formatted":
                return numberFormat.numberFormat(BlockBreakMining.getMax());
            case "Gala_Bar":
                return getProgressBar(BlockBreakMining.getBlocksBroken(), BlockBreakMining.getMax(), 5, '-', ChatColor.BOLD, ChatColor.BOLD);
            case "Gala_Percent":
                if (BlockBreakMining.getMax() != 0)
                    return String.valueOf(Math.round((float) BlockBreakMining.getBlocksBroken() / BlockBreakMining.getMax() * 100));
                else
                    return "0";
            case "Stat_Beach":
                return String.valueOf(BeachEvent.getBlocksBroken());
            case "Stat_Beach_Max":
                return String.valueOf(BeachEvent.getMax());
            case "Stat_Beach_Bar":
                return getProgressBar(BeachEvent.getBlocksBroken(), BeachEvent.getMax(), 5, '-', ChatColor.BOLD, ChatColor.BOLD);


            case "Stat_Pet_EXPAmount":
                return String.valueOf(getPoints("items.PetExperience", points));
            case "Stat_Pet_EXPAmount_formatted":
                return numberFormat.numberFormat(getPoints("items.PetExperience", points));

            case "Stat_EXPAmount":
                return String.valueOf(getPoints("items.Experience", points));
            case "Stat_EXPAmount_formatted":
                return numberFormat.numberFormat(getPoints("items.Experience", points));

            case "Stat_PetAmount":
                return String.valueOf(getPoints("items.PetAmount", points));
            case "Stat_MaxPetAmount":
                return String.valueOf(getPoints("items.MaxPet", points));

            case "Stat_MiningPassEXPAmount":
                return String.valueOf(getPoints("miningPass.EXP", points));
            case "Stat_MiningPassEXPAmount_formatted":
                return numberFormat.numberFormat(getPoints("miningPass.EXP", points));
            case "Stat_MiningPassLevel":
                return getMiningPassLevel(getPoints("miningPass.EXP", points));
            case "Stat_MiningPassNextLevelEXPAmount":
                return getMiningPassNextLevelEXP(getPoints("miningPass.EXP", points));

            // ENCHANTMENTS
            case "Enchantment_Randomizer":
                return String.valueOf(calculateEnchantments.calculateEnchantments(player, "Randomizer"));
            case "Enchantment_Midas_Touch":
                return String.valueOf(calculateEnchantments.calculateEnchantments(player, "Midas Touch"));
            case "Enchantment_Treasurer":
                return String.valueOf(calculateEnchantments.calculateEnchantments(player, "Treasurer"));
            case "Enchantment_Explosive":
                return String.valueOf(calculateEnchantments.calculateEnchantments(player, "Explosive"));
            case "Enchantment_Explosive_Chance":
                return String.valueOf(calculateEnchantments.calculateEnchantments(player, "Explosive Chance"));
            case "Enchantment_Experience":
                return String.valueOf(calculateEnchantments.calculateEnchantments(player, "Experience"));
            case "Enchantment_Pet_Experience":
                return String.valueOf(calculateEnchantments.calculateEnchantments(player, "Pet Experience"));
            case "Enchantment_Luck":
                return String.valueOf(calculateEnchantments.calculateEnchantments(player, "Luck"));
            case "Enchantment_Stat_Tracker":
                return String.valueOf(calculateEnchantments.calculateEnchantments(player, "Stat Tracker"));

            // CURRENCIES
            case "Currency_AdventureCoins":
                return String.valueOf(getPoints("items.AdventureCoin", points));
            case "Currency_AdventureCoins_formatted":
                return numberFormat.numberFormat(getPoints("items.AdventureCoin", points));
            case "Currency_VotingCoins":
                return String.valueOf(getPoints("items.Vote", points));

            case "Location":
                return location(player);

            // ACHIEVEMENTS
            case "Achievement_Ores":
                int coal = Integer.valueOf(parsePlaceholder((Player) player, "betonquest_blocks:point.COAL_ORE.amount"));
                int iron = Integer.valueOf(parsePlaceholder((Player) player, "betonquest_blocks:point.IRON_ORE.amount"));
                int gold = Integer.valueOf(parsePlaceholder((Player) player, "betonquest_blocks:point.GOLD_ORE.amount"));
                int lapis = Integer.valueOf(parsePlaceholder((Player) player, "betonquest_blocks:point.LAPIS_ORE.amount"));
                int redstone = Integer.valueOf(parsePlaceholder((Player) player, "betonquest_blocks:point.REDSTONE_ORE.amount"));
                int diamond = Integer.valueOf(parsePlaceholder((Player) player, "betonquest_blocks:point.DIAMOND_ORE.amount"));
                int emerald = Integer.valueOf(parsePlaceholder((Player) player, "betonquest_blocks:point.EMERALD_ORE.amount"));
                int netherQuartz = Integer.valueOf(parsePlaceholder((Player) player, "betonquest_blocks:point.NETHER_QUARTZ_ORE.amount"));
                int netherGold = Integer.valueOf(parsePlaceholder((Player) player, "betonquest_blocks:point.NETHER_GOLD_ORE.amount"));
                int total = coal + iron + gold + lapis + redstone + diamond + emerald + netherQuartz + netherGold;
                return String.valueOf(total);


//            case "Restart":
//                long timeUntil = restartTime - System.currentTimeMillis();
//                long seconds = TimeUnit.MILLISECONDS.toSeconds(timeUntil);
//                return String.valueOf(seconds);
//            case "Restart_formatted":
//                timeUntil = restartTime - System.currentTimeMillis();
//                long hours = TimeUnit.MILLISECONDS.toHours(timeUntil);
//                timeUntil -= TimeUnit.HOURS.toMillis(hours);
//                long minutes = TimeUnit.MILLISECONDS.toMinutes(timeUntil);
//                timeUntil -= TimeUnit.MINUTES.toMillis(minutes);
//                seconds = TimeUnit.MILLISECONDS.toSeconds(timeUntil);
//                StringBuilder sb = new StringBuilder();
//                sb.append(hours);
//                if (hours > 1) {
//                    sb.append(" Hours ");
//                } else if (hours == 1) {
//                    sb.append(" Hour ");
//                }
//                sb.append(minutes);
//                if (minutes > 1) {
//                    sb.append(" Minutes ");
//                } else if (minutes == 1) {
//                    sb.append(" Minute ");
//                }
//                if (hours < 1 && seconds > 1) {
//                    sb.append(seconds);
//                    sb.append(" Seconds ");
//                } else if (hours < 1 && seconds < 1) {
//                    sb.append(seconds);
//                    sb.append(" Second ");
//                }
//                if (hours < 1 && minutes < 1 && seconds < 1) {
//                    sb.append("NOW");
//                }
//                return (sb.toString());

            default:
                return null;

        }
    }

    public int getPoints(String pointCategory, List<Point> pointList) {
        for (final Point point : pointList)
            if (point.getCategory().equalsIgnoreCase(pointCategory))
                return point.getCount();
        return 0;
    }

    public String getMiningPassLevel(int exp) {
        for (MiningPassLevels cost : reversedList) {
            if (exp >= cost.getPrice()) {
                return String.valueOf(cost.getLevel());
            }
        }
        return "0";
    }

    public String getMiningPassNextLevelEXP(int exp) {
        int i = 0;
        for (MiningPassLevels cost : reversedList) {
            if (exp >= cost.getPrice()) {
                if (cost.getLevel().equals("50")) {
                    return "Max";
                } else {
                    return String.valueOf(reversedList.get(i - 1).getPrice());
                }
            }
            i++;
        }
        return "500";
    }

    public String getProgressBar(int current, int max, int totalBars, char symbol, ChatColor completedColor,
                                 ChatColor notCompletedColor) {
        float percent = (float) current / max;
        int progressBars = (int) (totalBars * percent);

        return Strings.repeat("" + ChatColor.YELLOW + completedColor + symbol, progressBars)
                + Strings.repeat("" + ChatColor.WHITE + notCompletedColor + symbol, totalBars - progressBars);
    }

    private String location(OfflinePlayer player) {
        if (player.getPlayer().isOnline()) {
            Player player1 = player.getPlayer();
            Location location = BukkitAdapter.adapt(player1.getLocation());
            RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
            RegionQuery query = container.createQuery();
            ApplicableRegionSet set = query.getApplicableRegions(location);
            if (set.queryValue(WorldGuardPlugin.inst().wrapPlayer(player1), displayNameFlag).equals(null)) {
                return "Unknown!";
            } else {
                return set.queryValue(WorldGuardPlugin.inst().wrapPlayer(player1), displayNameFlag);
            }
        }
        return null;
    }
    
    private double calculatePetStats(OfflinePlayer player, Stats petStat) {
        double statSum = 0;
        for (Pet pet : pets) {
            if (hasPermission(player, pet.getPermission())) {
                OptionalDouble stat = pet.getStat(petStat);
                if (stat.isPresent()) {
                    statSum += stat.getAsDouble();
                }
            }
        }
        if (statSum > 0) {
            return statSum;
        } else {
            return petStat.getDefaultValue();
        }
    }

    private String calculateStats(OfflinePlayer player, String placeholder, String placeholder2) {
        String stat = PlaceholderAPI.setPlaceholders(player, placeholder);
        String stat2 = PlaceholderAPI.setPlaceholders(player, placeholder2);
        return String.valueOf(Double.valueOf(stat + stat2));
    }

    private String calculateStats(OfflinePlayer player, String placeholder, String placeholder2, String placeholder3) {
        String stat = PlaceholderAPI.setPlaceholders(player, placeholder);
        String stat2 = PlaceholderAPI.setPlaceholders(player, placeholder2);
        String stat3 = PlaceholderAPI.setPlaceholders(player, placeholder3);
        return String.valueOf(Double.valueOf(stat + stat2 + stat3));
    }

    private String calculateStats(OfflinePlayer player, String placeholder, String placeholder2, String placeholder3, String placeholder4) {
        String stat = PlaceholderAPI.setPlaceholders(player, placeholder);
        String stat2 = PlaceholderAPI.setPlaceholders(player, placeholder2);
        String stat3 = PlaceholderAPI.setPlaceholders(player, placeholder3);
        String stat4 = PlaceholderAPI.setPlaceholders(player, placeholder4);
        return String.valueOf(Double.valueOf(stat + stat2 + stat3 + stat4));
    }

    private int calculateBoosterStats(OfflinePlayer player, String booster) {
        for (int i = 10; i > 0; i--) {
            if (hasPermission(player, booster + ".booster." + i)) {
                return i;
            }
        }
        return 0;
    }

    private boolean hasPermission(OfflinePlayer player, String permission) {
        if (player.isOnline()) {
            return player.getPlayer().hasPermission(permission);
        } else {
            return this.permission.playerHas(plugin.getServer().getWorlds().get(0).getName(), player, permission);
        }
    }

    private String parsePlaceholder(Player player, String string) {
        return PlaceholderAPI.setPlaceholders(player, "%" + string + "%");
    }

}
