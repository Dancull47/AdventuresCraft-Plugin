package monzter.adventurescraft.plugin.event;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.util.Location;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.flags.StringFlag;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import io.lumine.mythic.lib.api.item.NBTItem;
import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.event.extras.DonationRewardList;
import monzter.adventurescraft.plugin.event.extras.Pet;
import monzter.adventurescraft.plugin.event.extras.PetEgg;
import monzter.adventurescraft.plugin.event.extras.Stats;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class Placeholder extends PlaceholderExpansion {

    private final AdventuresCraft plugin;
    private final Permission permission;
    private final Set<Pet> pets;
    private final StringFlag displayNameFlag;
    private long restartTime;

    public Placeholder(AdventuresCraft plugin, Permission permission, Set<Pet> pets, StringFlag displayNameFlag, long restartTime) {
        this.plugin = plugin;
        this.permission = permission;
        this.pets = pets;
        this.displayNameFlag = displayNameFlag;
        this.restartTime = restartTime;
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

            case "PetEgg":
                return String.valueOf(PetEgg.COMMON.expToHatch);
            case "PetEgg_formatted":
                return numberFormat(PetEgg.COMMON.expToHatch);
            case "PetEgg2":
                return String.valueOf(PetEgg.UNCOMMON.expToHatch);
            case "PetEgg2_formatted":
                return numberFormat(PetEgg.UNCOMMON.expToHatch);
            case "PetEgg3":
                return String.valueOf(PetEgg.RARE.expToHatch);
            case "PetEgg3_formatted":
                return numberFormat(PetEgg.RARE.expToHatch);
            case "PetEgg4":
                return String.valueOf(PetEgg.LEGENDARY.expToHatch);
            case "PetEgg4_formatted":
                return numberFormat(PetEgg.LEGENDARY.expToHatch);
            case "PetEgg5":
                return String.valueOf(PetEgg.EXOTIC.expToHatch);
            case "PetEgg5_formatted":
                return numberFormat(PetEgg.EXOTIC.expToHatch);
            case "PetEggPhoenix":
                return String.valueOf(PetEgg.PHOENIX.expToHatch);
            case "PetEggPhoenix_formatted":
                return numberFormat(PetEgg.PHOENIX.expToHatch);
            case "PetEggPhoenix2":
                return String.valueOf(PetEgg.PHOENIX2.expToHatch);
            case "PetEggPhoenix2_formatted":
                return numberFormat(PetEgg.PHOENIX2.expToHatch);
            case "PetEggDragon":
                return String.valueOf(PetEgg.DRAGON.expToHatch);
            case "PetEggDragon_formatted":
                return numberFormat(PetEgg.DRAGON.expToHatch);
            case "PetEggDragon2":
                return String.valueOf(PetEgg.DRAGON2.expToHatch);
            case "PetEggDragon2_formatted":
                return numberFormat(PetEgg.DRAGON2.expToHatch);

            case "Donation_PetSlot":
                return numberFormat(DonationRewardList.PetSlot.price);
            case "Donation_UnlimitedWeight":
                return numberFormat(DonationRewardList.UnlimitedWeight.price);
            case "Donation_ExoticLootbox5":
                return numberFormat(DonationRewardList.ExoticLootbox5.price);
            case "Donation_ExoticLootbox10":
                return numberFormat(DonationRewardList.ExoticLootbox10.price);
            case "Donation_MythicalLootbox5":
                return numberFormat(DonationRewardList.MythicalLootbox5.price);
            case "Donation_MythicalLootbox10":
                return numberFormat(DonationRewardList.MythicalLootbox10.price);
            case "Donation_GodlyLootbox5":
                return numberFormat(DonationRewardList.GodlyLootbox5.price);
            case "Donation_GodlyLootbox10":
                return numberFormat(DonationRewardList.GodlyLootbox10.price);
            case "Donation_ExoticPetEgg5":
                return numberFormat(DonationRewardList.ExoticPetEgg5.price);
            case "Donation_ExoticPetEgg10":
                return numberFormat(DonationRewardList.ExoticPetEgg10.price);
            case "Donation_MythicalPetEgg5":
                return numberFormat(DonationRewardList.MythicalPetEgg5.price);
            case "Donation_MythicalPetEgg10":
                return numberFormat(DonationRewardList.MythicalPetEgg10.price);
            case "Donation_GodlyPetEgg5":
                return numberFormat(DonationRewardList.GodlyPetEgg5.price);
            case "Donation_GodlyPetEgg10":
                return numberFormat(DonationRewardList.GodlyPetEgg10.price);
            case "Donation_LegendaryPhoenixPetEgg5":
                return numberFormat(DonationRewardList.LegendaryPhoenixPetEgg5.price);
            case "Donation_LegendaryPhoenixPetEgg10":
                return numberFormat(DonationRewardList.LegendaryPhoenixPetEgg10.price);
            case "Donation_LegendaryDragonPetEgg5":
                return numberFormat(DonationRewardList.LegendaryDragonPetEgg5.price);
            case "Donation_LegendaryDragonPetEgg10":
                return numberFormat(DonationRewardList.LegendaryDragonPetEgg10.price);

            // STATS
            case "Stat_TotalMined":
                return String.valueOf(Double.valueOf(PlaceholderAPI.setPlaceholders(player, "%betonquest_items:point.TotalBlocks.amount%")));
            case "Stat_MiningSpeed":
                String miningSpeed = PlaceholderAPI.setPlaceholders(player, "%mmoitems_stat_faction_damage_breakingspeed%");
                return miningSpeed;
            case "Stat_MaxWeight":
                String maxWeight = PlaceholderAPI.setPlaceholders(player, "%betonquest_items:point.MaxWeight.amount%");
                String maxWeightMultiplier = PlaceholderAPI.setPlaceholders(player, "%ac_Stat_MaxWeightMultiplier%");
                return String.valueOf((50 + Double.valueOf(maxWeight) + calculatePetStats(player, Stats.MAX_WEIGHT)) * Double.valueOf(maxWeightMultiplier));
            case "Stat_MaxWeightMultiplier":
                return String.valueOf(calculatePetStats(player, Stats.MAX_WEIGHT_MULTIPLIER));
            case "Stat_BlockMultiplier":
                String blockMultiplierDefault = PlaceholderAPI.setPlaceholders(player, "%mmoitems_stat_stamina_regeneration%");
                return String.valueOf(calculatePetStats(player, Stats.BLOCK_MULTIPLIER) + Double.valueOf(blockMultiplierDefault) + calculateBoosterStats(player, "block"));
            case "Stat_SellMultiplier":
                return String.valueOf(calculatePetStats(player, Stats.SELL_MULTIPLIER) + calculateBoosterStats(player, "sell"));
            case "Stat_LuckMultiplier":
                return String.valueOf((calculateEnchantments(player, "Luck") * .5) + calculatePetStats(player, Stats.LUCK_MULTIPLIER) + calculateBoosterStats(player, "luck"));
            case "Stat_EXPMultiplier":
                return String.valueOf(calculateEnchantments(player, "Experience") + calculatePetStats(player, Stats.EXPERIENCE_MULTIPLIER) + calculateBoosterStats(player, "exp"));
            case "Stat_Pet_EXPMultiplier":
                return String.valueOf(calculateEnchantments(player, "Pet Experience") + calculatePetStats(player, Stats.PET_EXPERIENCE) + calculateBoosterStats(player, "pet_exp"));

            case "Stat_Weight":
                String currentWeight = PlaceholderAPI.setPlaceholders(player, "%betonquest_items:point.Weight.amount%");
                return currentWeight;
            case "Stat_Weight_formatted":
                return numberFormat(Integer.valueOf(PlaceholderAPI.setPlaceholders(player, "%ac_Stat_Weight%")));
            case "Stat_Pet_EXPAmount":
                String petEXPAmount = PlaceholderAPI.setPlaceholders(player, "%betonquest_items:point.PetExperience.amount%");
                return petEXPAmount;
            case "Stat_Pet_EXPAmount_formatted":
                return numberFormat(Integer.valueOf(PlaceholderAPI.setPlaceholders(player, "%ac_Stat_Pet_EXPAmount%")));
            case "Stat_EXPAmount":
                String EXPAmount = PlaceholderAPI.setPlaceholders(player, "%betonquest_items:point.Experience.amount%");
                return EXPAmount;
            case "Stat_EXPAmount_formatted":
                return numberFormat(Integer.valueOf(PlaceholderAPI.setPlaceholders(player, "%ac_Stat_EXPAmount%")));
            case "Stat_PetAmount":
                String petAmount = PlaceholderAPI.setPlaceholders(player, "%betonquest_items:point.PetAmount.amount%");
                return petAmount;
            case "Stat_MaxPetAmount":
                String maxPetAmount = PlaceholderAPI.setPlaceholders(player, "%betonquest_items:point.MaxPetAmount.amount%");
                return String.valueOf(2 + Integer.valueOf(maxPetAmount));
            case "Stat_MiningPassEXPAmount":
                String miningPassEXPAmount = PlaceholderAPI.setPlaceholders(player, "%betonquest_miningPass:point.EXP.amount%");
                return miningPassEXPAmount;
            case "Stat_MiningPassEXPAmount_formatted":
                return numberFormat(Integer.valueOf(PlaceholderAPI.setPlaceholders(player, "%ac_Stat_MiningPassEXPAmount%")));

            // CURRENCIES
            case "Currency_AdventureCoins":
                String adventureCoins = PlaceholderAPI.setPlaceholders(player, "%betonquest_items:point.AdventureCoin.amount%");
                return adventureCoins;
            case "Currency_AdventureCoins_formatted":
                return numberFormat(Integer.valueOf(PlaceholderAPI.setPlaceholders(player, "%ac_Currency_AdventureCoins%")));
            case "Currency_VotingCoins":
                String voteCoins = PlaceholderAPI.setPlaceholders(player, "%betonquest_items:point.Vote.amount%");
                return voteCoins;

            // ENCHANTMENTS
            case "Enchantment_Randomizer":
                return String.valueOf(calculateEnchantments(player, "Randomizer"));
            case "Enchantment_Randomizer_math":
                return String.valueOf(Integer.valueOf(calculateEnchantments(player, "Randomizer")+1)*3500);
            case "Enchantment_Randomizer_math_formatted":
                return numberFormat(Integer.valueOf(calculateEnchantments(player, "Randomizer")+1)*3500);
            case "Enchantment_Randomizer_increase":
                return String.valueOf(Integer.valueOf(calculateEnchantments(player, "Randomizer"))+1);

            case "Enchantment_Midas_Touch":
                return String.valueOf(calculateEnchantments(player, "Midas Touch"));
            case "Enchantment_Midas_Touch_math":
                return String.valueOf(Integer.valueOf(calculateEnchantments(player, "Midas Touch")+1)*3500);
            case "Enchantment_Midas_Touch_math_formatted":
                return numberFormat(Integer.valueOf(calculateEnchantments(player, "Midas Touch")+1)*3500);
            case "Enchantment_Midas_Touch_increase":
                return String.valueOf(Integer.valueOf(calculateEnchantments(player, "Midas Touch"))+1);

            case "Enchantment_Treasurer":
                return String.valueOf(calculateEnchantments(player, "Treasurer"));
            case "Enchantment_Treasurer_math":
                return String.valueOf(Integer.valueOf(calculateEnchantments(player, "Treasurer")+1)*3500);
            case "Enchantment_Treasurer_math_formatted":
                return numberFormat(Integer.valueOf(calculateEnchantments(player, "Treasurer")+1)*3500);
            case "Enchantment_Treasurer_increase":
                return String.valueOf(Integer.valueOf(calculateEnchantments(player, "Treasurer"))+1);

            case "Enchantment_Explosive_Chance":
                return String.valueOf(calculateEnchantments(player, "Explosive Chance"));
            case "Enchantment_Explosive_Chance_math":
                return String.valueOf(Integer.valueOf(calculateEnchantments(player, "Explosive Chance")+1)*3500);
            case "Enchantment_Explosive_Chance_math_formatted":
                return numberFormat(Integer.valueOf(calculateEnchantments(player, "Explosive Chance")+1)*3500);
            case "Enchantment_Explosive_Chance_increase":
                return String.valueOf(Integer.valueOf(calculateEnchantments(player, "Explosive Chance"))+1);

            case "Enchantment_Experience":
                return String.valueOf(calculateEnchantments(player, "Experience"));
            case "Enchantment_Pet_Experience":
                return String.valueOf(calculateEnchantments(player, "Pet Experience"));
            case "Enchantment_Explosive":
                return String.valueOf(calculateEnchantments(player, "Explosive"));
            case "Enchantment_Luck":
                return String.valueOf(calculateEnchantments(player, "Luck"));

            case "Location":
                return location(player);
            case "Restart":
                long timeUntil = restartTime - System.currentTimeMillis();
                long seconds = TimeUnit.MILLISECONDS.toSeconds(timeUntil);
                return String.valueOf(seconds);
            case "Restart_formatted":
                timeUntil = restartTime - System.currentTimeMillis();
                long hours = TimeUnit.MILLISECONDS.toHours(timeUntil);
                timeUntil -= TimeUnit.HOURS.toMillis(hours);
                long minutes = TimeUnit.MILLISECONDS.toMinutes(timeUntil);
                timeUntil -= TimeUnit.MINUTES.toMillis(minutes);
                seconds = TimeUnit.MILLISECONDS.toSeconds(timeUntil);
                StringBuilder sb = new StringBuilder();
                sb.append(hours);
                if (hours > 1){
                    sb.append(" Hours ");
                } else if (hours == 1){
                    sb.append(" Hour ");
                }
                sb.append(minutes);
                if (minutes > 1){
                    sb.append(" Minutes ");
                } else if (minutes == 1){
                    sb.append(" Minute ");
                }
                if (hours < 1 && seconds > 1){
                    sb.append(seconds);
                    sb.append(" Seconds ");
                } else if (hours < 1 && seconds < 1){
                    sb.append(seconds);
                    sb.append(" Second ");
                }
                if (hours < 1 && minutes < 1 && seconds < 1){
                    sb.append("NOW");
                }
                    return (sb.toString());

            default:
                return null;

        }
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

    private String numberFormat(int number) {
        DecimalFormat format = new DecimalFormat("###,###,###");
        return format.format(number);
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

    private int calculateEnchantments(OfflinePlayer player, String enchantment) {
        if (player.isOnline()) {
            if (player.getPlayer().getInventory().getItemInMainHand().getItemMeta() != null){
                Map<Enchantment, Integer> enchantmentMap = player.getPlayer().getInventory().getItemInMainHand().getItemMeta().getEnchants();
                if (!enchantmentMap.isEmpty()) {
//                System.out.println(enchantmentMap + "TEST");
                    if (enchantmentMap.containsKey(Enchantment.getByName(enchantment))) {
                        return enchantmentMap.get(Enchantment.getByName(enchantment));
                    }
                }
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
}
