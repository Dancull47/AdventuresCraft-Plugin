package monzter.adventurescraft.plugin.shared.Placeholders;

import com.google.common.base.Strings;
import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.text.NumberFormat;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.enchantments.Enchantment;

import java.util.*;

public class PlaceholderEnchanting extends PlaceholderExpansion {

    private final AdventuresCraft plugin;
    private final NumberFormat numberFormat;


    public PlaceholderEnchanting(AdventuresCraft plugin, NumberFormat numberFormat) {
        this.plugin = plugin;
        this.numberFormat = numberFormat;
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
        return "acE";
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
     * @param player     A {@link OfflinePlayer OfflinePlayer}.
     * @param identifier A String containing the identifier/value.
     * @return Possibly-null String of the requested identifier.
     */
    @Override
    public String onRequest(OfflinePlayer player, String identifier) {

        switch (identifier) {
            case "TEST":
                return String.valueOf("TRUE");

            // ENCHANTMENTS
            case "Enchantment_Randomizer":
                return String.valueOf(calculateEnchantments(player, "Randomizer"));
            case "Enchantment_Randomizer_math":
                return String.valueOf(Integer.valueOf(calculateEnchantments(player, "Randomizer") + 1) * 3500);
            case "Enchantment_Randomizer_math_formatted":
                return numberFormat.numberFormat(Integer.valueOf(calculateEnchantments(player, "Randomizer") + 1) * 3500);
            case "Enchantment_Randomizer_increase":
                return String.valueOf(Integer.valueOf(calculateEnchantments(player, "Randomizer")) + 1);

            case "Enchantment_Midas_Touch":
                return String.valueOf(calculateEnchantments(player, "Midas Touch"));
            case "Enchantment_Midas_Touch_math":
                return String.valueOf(Integer.valueOf(calculateEnchantments(player, "Midas Touch") + 1) * 3500);
            case "Enchantment_Midas_Touch_math_formatted":
                return numberFormat.numberFormat(Integer.valueOf(calculateEnchantments(player, "Midas Touch") + 1) * 3500);
            case "Enchantment_Midas_Touch_increase":
                return String.valueOf(Integer.valueOf(calculateEnchantments(player, "Midas Touch")) + 1);

            case "Enchantment_Treasurer":
                return String.valueOf(calculateEnchantments(player, "Treasurer"));
            case "Enchantment_Treasurer_math":
                return String.valueOf(Integer.valueOf(calculateEnchantments(player, "Treasurer") + 1) * 3500);
            case "Enchantment_Treasurer_math_formatted":
                return numberFormat.numberFormat(Integer.valueOf(calculateEnchantments(player, "Treasurer") + 1) * 3500);
            case "Enchantment_Treasurer_increase":
                return String.valueOf(Integer.valueOf(calculateEnchantments(player, "Treasurer")) + 1);

            case "Enchantment_Explosive_Chance":
                return String.valueOf(calculateEnchantments(player, "Explosive Chance"));
            case "Enchantment_Explosive_Chance_math":
                return String.valueOf(Integer.valueOf(calculateEnchantments(player, "Explosive Chance") + 1) * 3500);
            case "Enchantment_Explosive_Chance_math_formatted":
                return numberFormat.numberFormat(Integer.valueOf(calculateEnchantments(player, "Explosive Chance") + 1) * 3500);
            case "Enchantment_Explosive_Chance_increase":
                return String.valueOf(Integer.valueOf(calculateEnchantments(player, "Explosive Chance")) + 1);

            case "Enchantment_Experience":
                return String.valueOf(calculateEnchantments(player, "Experience"));
            case "Enchantment_Pet_Experience":
                return String.valueOf(calculateEnchantments(player, "Pet Experience"));
            case "Enchantment_Explosive":
                return String.valueOf(calculateEnchantments(player, "Explosive"));
            case "Enchantment_Luck":
                return String.valueOf(calculateEnchantments(player, "Luck"));
            case "Enchantment_Stat_Tracker":
                return String.valueOf(calculateEnchantments(player, "Stat Tracker"));

            default:
                return null;

        }
    }

    public String getProgressBar(int current, int max, int totalBars, char symbol, ChatColor completedColor,
                                 ChatColor notCompletedColor) {
        float percent = (float) current / max;
        int progressBars = (int) (totalBars * percent);

        return Strings.repeat("" + ChatColor.YELLOW + completedColor + symbol, progressBars)
                + Strings.repeat("" + ChatColor.WHITE + notCompletedColor + symbol, totalBars - progressBars);
    }

    private int calculateEnchantments(OfflinePlayer player, String enchantment) {
        if (player.isOnline()) {
            if (player.getPlayer().getInventory().getItemInMainHand().getItemMeta() != null) {
                Map<Enchantment, Integer> enchantmentMap = player.getPlayer().getInventory().getItemInMainHand().getItemMeta().getEnchants();
                if (!enchantmentMap.isEmpty()) {
                    if (enchantmentMap.containsKey(Enchantment.getByName(enchantment))) {
                        return enchantmentMap.get(Enchantment.getByName(enchantment));
                    }
                }
            }
        }
        return 0;
    }
}
