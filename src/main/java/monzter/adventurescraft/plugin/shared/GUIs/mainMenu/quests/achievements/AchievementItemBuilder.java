package monzter.adventurescraft.plugin.shared.GUIs.mainMenu.quests.achievements;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.Dependency;
import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import io.lumine.mythic.lib.api.util.SmartGive;
import me.clip.placeholderapi.PlaceholderAPI;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.GUI.GUIHelper;
import monzter.adventurescraft.plugin.utilities.beton.BetonPointsManager;
import monzter.adventurescraft.plugin.utilities.enums.Prefix;
import monzter.adventurescraft.plugin.utilities.enums.StatsDisplay;
import monzter.adventurescraft.plugin.utilities.general.ConsoleCommand;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import monzter.adventurescraft.plugin.utilities.luckperms.PermissionLP;
import monzter.adventurescraft.plugin.utilities.text.NumberFormat;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class AchievementItemBuilder extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final SoundManager soundManager;
    private final GUIHelper guiHelper;
    private final NumberFormat numberFormat;
    private final BetonPointsManager betonPointsManager;
    private final PermissionLP permissionLP;
    private final ConsoleCommand consoleCommand;

    private final String REWARD = "  " + ChatColor.GOLD + ChatColor.BOLD + "REWARDS" + ChatColor.WHITE + ":";

    public AchievementItemBuilder(AdventuresCraft plugin, SoundManager soundManager, GUIHelper guiHelper, NumberFormat numberFormat, BetonPointsManager betonPointsManager, PermissionLP permissionLP, ConsoleCommand consoleCommand) {
        this.plugin = plugin;
        this.soundManager = soundManager;
        this.guiHelper = guiHelper;
        this.numberFormat = numberFormat;
        this.betonPointsManager = betonPointsManager;
        this.permissionLP = permissionLP;
        this.consoleCommand = consoleCommand;
    }

    public GuiItem totalBlocks(Player player, Achievements achievement, String placeholder) {
        int playersAmount = Integer.valueOf(parsePlaceholder(player, placeholder));
        ItemStack complete = new ItemStack(Material.WOODEN_PICKAXE);
        final ItemMeta completeItemMeta = complete.getItemMeta();

        Material material = achievement.getMaterial();
        String name = ChatColor.GOLD + achievement.getName();

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.WHITE + achievement.getObjective() + ChatColor.GREEN + numberFormat.numberFormat(playersAmount) + ChatColor.DARK_GRAY + "/" + ChatColor.GREEN + numberFormat.numberFormat(achievement.getPrice()));
        lore.add("");
        lore.add(REWARD);

        int reward = 0;
        for (ItemStack lore3 : achievement.getRewards()) {
            if (lore3 != null) {
                lore.add( "  " + Prefix.PREFIX.getString() + ChatColor.GOLD + achievement.getRewardAmount()[reward] + "x " + lore3.getItemMeta().getDisplayName());
                reward++;
            }
        }

        int currency = 0;
        for (String lore2 : achievement.getCurrencyRewards()) {
            if (achievement.getCurrencyRewards() != null) {
                String newLore = "  " + Prefix.PREFIX.getString() + ChatColor.GOLD + numberFormat.numberFormat(achievement.getCurrencyRewardsAmount()[currency]) + " ";
                switch (lore2) {
                    case "exp":
                        lore.add(newLore + StatsDisplay.EXPERIENCE_AMOUNT.getName());
                        currency++;
                        break;
                    case "petexp":
                        lore.add(newLore + StatsDisplay.PET_EXPERIENCE_AMOUNT.getName());
                        currency++;
                        break;
                    case "miningpassexp":
                        lore.add(newLore + StatsDisplay.MINING_PASS_EXPERIENCE.getName());
                        currency++;
                        break;
                }
            }
        }
        lore.add("");

        if (playersAmount >= achievement.getPrice() && player.hasPermission("ACHIEVEMENT." + achievement.getPermission())) {
            name = ChatColor.GOLD + achievement.getName() + " " + ChatColor.DARK_GRAY + "-" + ChatColor.GREEN + ChatColor.BOLD + " COMPLETE";
            lore.add(ChatColor.GREEN.toString() + ChatColor.BOLD + "COMPLETE");
        } else if (playersAmount >= achievement.getPrice() && !player.hasPermission("ACHIEVEMENT." + achievement.getPermission())) {
            name = ChatColor.GOLD + achievement.getName() + " " + ChatColor.DARK_GRAY + "-" + ChatColor.YELLOW + ChatColor.BOLD + " CLAIMABLE";
            lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to Claim Rewards");
        } else if (playersAmount < achievement.getPrice()) {
            name = ChatColor.GOLD + achievement.getName() + " " + ChatColor.DARK_GRAY + "-" + ChatColor.RED + ChatColor.BOLD + " LOCKED";
            lore.add(ChatColor.RED.toString() + ChatColor.BOLD + "LOCKED");
        }

        complete.setType(material);
        completeItemMeta.setDisplayName(name);
        completeItemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        complete.setItemMeta(completeItemMeta);
        complete.setLore(lore);

        return new GuiItem(complete, e -> {
            if (levelCheck(player, playersAmount, achievement.getPrice(), achievement.getObjective().replace(": ", "")))
                if (claimedCheck(player, achievement.getPermission())) {
                    claim(player, achievement);
                    player.closeInventory();
                    player.performCommand("Achievement" + achievement.getGroup());
                }
        });
    }

    private void claim(Player player, Achievements minerAchievements) {
        permissionLP.givePermission(player, "ACHIEVEMENT." + minerAchievements.getPermission());
        int i = 0;
        for (String lore2 : minerAchievements.getCurrencyRewards()) {
            if (minerAchievements.getCurrencyRewards() != null) {
                switch (lore2) {
                    case "exp":
                        consoleCommand.consoleCommand("reward exp " + minerAchievements.getCurrencyRewardsAmount()[i] + " " + player.getName());
                        i++;
                        break;
                    case "petexp":
                        consoleCommand.consoleCommand("reward petexp " + minerAchievements.getCurrencyRewardsAmount()[i] + " " + player.getName());
                        i++;
                        break;
                    case "miningpassexp":
                        consoleCommand.consoleCommand("reward miningpass " + minerAchievements.getCurrencyRewardsAmount()[i] + " " + player.getName());
                        i++;
                        break;
                }
            }
        }
        int i2 = 0;
        for (ItemStack itemStack : minerAchievements.getRewards()) {
            if (itemStack != null) {
                new SmartGive(player).give(itemStack.asQuantity(minerAchievements.getRewardAmount()[i2]));
                player.sendMessage(ChatColor.YELLOW + "You received " + ChatColor.GOLD + minerAchievements.getRewardAmount()[i2] + ChatColor.YELLOW + "x " + itemStack.getItemMeta().getDisplayName() + ChatColor.YELLOW + "!");
                i2++;
            }

        }
    }

    private boolean levelCheck(Player player, int minedAmount, int price, String message) {
        if (minedAmount >= price) {
            return true;
        } else {
            player.sendMessage(ChatColor.RED + "You only have " + ChatColor.GOLD + numberFormat.numberFormat(minedAmount) + ChatColor.RED + " / " + ChatColor.GOLD + numberFormat.numberFormat(price) + " " + message + ChatColor.RED + "!");
            soundManager.soundNo(player, 1);
            return false;
        }
    }

    private boolean claimedCheck(Player player, String permission) {
        if (player.hasPermission("ACHIEVEMENT." + permission)) {
            player.sendMessage(ChatColor.RED + "You already claimed this reward!");
            soundManager.soundNo(player, 1);
            return false;
        } else {
            return true;
        }
    }

    private String parsePlaceholder(Player player, String string) {
        return PlaceholderAPI.setPlaceholders(player, "%" + string + "%");
    }

}

