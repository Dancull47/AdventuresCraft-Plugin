package monzter.adventurescraft.plugin.network.PrisonGamemode.shared.GUIs.mainMenu;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Dependency;
import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import dev.dbassett.skullcreator.SkullCreator;
import me.clip.placeholderapi.PlaceholderAPI;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.GUI.GUIHelper;
import monzter.adventurescraft.plugin.utilities.enums.Prefix;
import monzter.adventurescraft.plugin.utilities.enums.StatsDisplay;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ProfileMenu extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final SoundManager soundManager;
    private final GUIHelper guiHelper;
    private final int MAX_QUESTS = 4;

    public ProfileMenu(AdventuresCraft plugin, SoundManager soundManager, GUIHelper guiHelper) {
        this.plugin = plugin;
        this.soundManager = soundManager;
        this.guiHelper = guiHelper;
    }

    @CommandAlias("Profile|Profiles")
    public void profileMenu(Player player) {

        ChestGui gui = new ChestGui(6, guiHelper.guiName("Profile"));
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        OutlinePane background = new OutlinePane(0, 0, 9, 6, Pane.Priority.LOWEST);
        StaticPane display = new StaticPane(0, 0, 9, 6, Pane.Priority.LOW);


        background.addItem(new GuiItem(guiHelper.background(Material.LIGHT_BLUE_STAINED_GLASS_PANE)));
        background.setRepeat(true);

        display.addItem(new GuiItem(adventureCoins(player), e -> player.performCommand("donate")), 2, 0);
        display.addItem(new GuiItem(money(player)), 3, 0);
        display.addItem(new GuiItem(experience(player)), 4, 0);
        display.addItem(new GuiItem(petExperience(player)), 5, 0);
        display.addItem(new GuiItem(voteCoin(player)), 6, 0);

        display.addItem(new GuiItem(activeBoosters(player)), 3, 1);
        display.addItem(new GuiItem(profile(player)), 4, 1);
        display.addItem(new GuiItem(attributes(player)), 5, 1);

        display.addItem(new GuiItem(hp(player)), 2, 2);
        display.addItem(new GuiItem(mana(player)), 3, 2);
        display.addItem(new GuiItem(armor(player)), 4, 2);
        display.addItem(new GuiItem(speed(player)), 5, 2);
        display.addItem(new GuiItem(damage(player)), 6, 2);

        display.addItem(new GuiItem(miningSpeed(player)), 2, 3);
        display.addItem(new GuiItem(maxWeight(player)), 3, 3);
        display.addItem(new GuiItem(blockMultiplier(player)), 4, 3);
        display.addItem(new GuiItem(sellMultiplier(player)), 5, 3);
        display.addItem(new GuiItem(luckMultiplier(player)), 6, 3);

        display.addItem(new GuiItem(experienceMultiplier(player)), 2, 4);
        display.addItem(new GuiItem(petExperienceMultiplier(player)), 3, 4);

        display.addItem(new GuiItem(guiHelper.backButton(), e -> player.performCommand("main")), 4, 5);

        gui.addPane(background);
        gui.addPane(display);
        gui.show(player);
    }


    private ItemStack adventureCoins(Player player) {
        final ItemStack adventureCoins = new ItemStack(Material.PUFFERFISH);
        final ItemMeta adventureCoinsItemMeta = adventureCoins.getItemMeta();

        adventureCoinsItemMeta.displayName(Component.text(StatsDisplay.ADVENTURE_COINS.getName() + ChatColor.WHITE + " = " + ChatColor.YELLOW + parsePlaceholder(player, "ac_Currency_AdventureCoins_formatted")));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(StatsDisplay.ADVENTURE_COINS.getName() + ChatColor.GRAY + " are obtained from");
        lore.add(ChatColor.GREEN + "donating, giveaways, and lootboxes" + ChatColor.GRAY + "!");
        lore.add("");
        lore.add(StatsDisplay.ADVENTURE_COINS.getName() + ChatColor.GRAY + " are used");
        lore.add(ChatColor.GRAY + "to purchase exclusive rewards");
        lore.add(ChatColor.GRAY + "from the" + ChatColor.GOLD + " Adventure Shop" + ChatColor.GRAY + "!");
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View Shop");

        adventureCoins.setItemMeta(adventureCoinsItemMeta);
        adventureCoins.setLore(lore);

        return adventureCoins;
    }

    private ItemStack money(Player player) {
        final ItemStack money = new ItemStack(Material.SUNFLOWER);
        final ItemMeta moneyItemMeta = money.getItemMeta();

        moneyItemMeta.displayName(Component.text(StatsDisplay.MONEY_AMOUNT.getName() + ChatColor.WHITE + " = " + ChatColor.YELLOW + parsePlaceholder(player, "vault_eco_balance_commas")));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(StatsDisplay.MONEY_AMOUNT.getName() + ChatColor.GRAY + " is used to progress");
        lore.add(ChatColor.GRAY + "through " + ChatColor.GREEN + "Prestiges " + ChatColor.GRAY + "and " + ChatColor.BLUE + "Prestiges" + ChatColor.GRAY + "!");
        lore.add("");
        lore.add(StatsDisplay.MONEY_AMOUNT.getName() + ChatColor.GRAY + " can be earned from selling");
        lore.add(ChatColor.GRAY + "resources you collect while mining,");
        lore.add(ChatColor.GRAY + "opening" + ChatColor.GREEN + " Crates" + ChatColor.GRAY + ", and more!");

        money.setItemMeta(moneyItemMeta);
        money.setLore(lore);

        return money;
    }

    private ItemStack experience(Player player) {
        final ItemStack experience = new ItemStack(Material.GREEN_DYE);
        final ItemMeta experienceItemMeta = experience.getItemMeta();

        experienceItemMeta.displayName(Component.text(StatsDisplay.EXPERIENCE_AMOUNT.getName() + ChatColor.WHITE + " = " + ChatColor.YELLOW + parsePlaceholder(player, "ac_Stat_EXPAmount_formatted")));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(StatsDisplay.EXPERIENCE_AMOUNT.getName() + ChatColor.GRAY + " is used for " + ChatColor.DARK_PURPLE + "Enchanting" + ChatColor.GRAY + ", which");
        lore.add(ChatColor.GRAY + "improves the capabilities of your Gear!");
        lore.add("");
        lore.add(StatsDisplay.EXPERIENCE_AMOUNT.getName() + ChatColor.GRAY + " can be earned while mining,");
        lore.add(ChatColor.GRAY + "completing" + ChatColor.GREEN + " Quests" + ChatColor.GRAY + ", and opening " + ChatColor.GREEN + "Crates" + ChatColor.GRAY + "!");

        experience.setItemMeta(experienceItemMeta);
        experience.setLore(lore);

        return experience;
    }

    private ItemStack petExperience(Player player) {
        final ItemStack petExperience = new ItemStack(Material.BLUE_DYE);
        final ItemMeta petExperienceItemMeta = petExperience.getItemMeta();

        petExperienceItemMeta.displayName(Component.text(StatsDisplay.PET_EXPERIENCE_AMOUNT.getName() + ChatColor.WHITE + " = " + ChatColor.YELLOW + parsePlaceholder(player, "ac_Stat_Pet_EXPAmount_formatted")));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(StatsDisplay.PET_EXPERIENCE_AMOUNT.getName() + ChatColor.GRAY + " is used to");
        lore.add(ChatColor.GRAY + "hatch " + ChatColor.GREEN + "Eggs" + ChatColor.GRAY + " and evolve " + ChatColor.GREEN + "Pets" + ChatColor.GRAY + "!");
        lore.add("");
        lore.add(StatsDisplay.PET_EXPERIENCE_AMOUNT.getName() + ChatColor.GRAY + " can be earned while mining,");
        lore.add(ChatColor.GRAY + "completing" + ChatColor.GREEN + " Quests" + ChatColor.GRAY + ", and opening " + ChatColor.GREEN + "Crates" + ChatColor.GRAY + "!");

        petExperience.setItemMeta(petExperienceItemMeta);
        petExperience.setLore(lore);

        return petExperience;
    }

    private ItemStack voteCoin(Player player) {
        final ItemStack voteCoin = new ItemStack(Material.EMERALD);
        final ItemMeta voteCoinItemMeta = voteCoin.getItemMeta();

        voteCoinItemMeta.displayName(Component.text(ChatColor.GREEN + "Vote Coin" + ChatColor.WHITE + " = " + ChatColor.YELLOW + parsePlaceholder(player, "ac_Currency_VotingCoins")));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GREEN + "Vote Coin " + ChatColor.GRAY + " can be earned each day by");
        lore.add(ChatColor.GREEN + "voting " + ChatColor.GRAY + "for our Server every 24 hours!");
        lore.add("");
        lore.add(ChatColor.GRAY + "The coins can be redeemed in the");
        lore.add(ChatColor.GREEN + "Vote Shop " + ChatColor.GRAY + "for some awesome rewards!");
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View Shop");

        voteCoin.setItemMeta(voteCoinItemMeta);
        voteCoin.setLore(lore);

        return voteCoin;
    }


    private ItemStack activeBoosters(Player player) {
        final ItemStack activeBoosters = new ItemStack(Material.EMERALD);
        final ItemMeta activeBoostersItemMeta = activeBoosters.getItemMeta();

        activeBoostersItemMeta.displayName(Component.text(ChatColor.GREEN + "Active Boosters"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.YELLOW + "Boosters" + ChatColor.GRAY + " increase the amount of");
        lore.add(ChatColor.GREEN + "voting " + ChatColor.GRAY + "for our Server every 24 hours!");
        lore.add("");
        lore.add(ChatColor.GRAY + "The coins can be redeemed in the");
        lore.add(ChatColor.GREEN + "Vote Shop " + ChatColor.GRAY + "for some awesome rewards!");
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View Shop");

        activeBoosters.setItemMeta(activeBoostersItemMeta);
        activeBoosters.setLore(lore);

        return activeBoosters;
    }

    private ItemStack profile(Player player) {
        final ItemStack profile = new ItemStack(SkullCreator.itemFromUuid(player.getUniqueId()));
        final ItemMeta profileItemMeta = profile.getItemMeta();

        profileItemMeta.displayName(Component.text(ChatColor.GREEN + "Profile"));

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.WHITE + "Rank: " + parsePlaceholder(player, "rank_value"));
        lore.add("");
        lore.add(StatsDisplay.HP.getName() + ": " + parsePlaceholder(player, "mmocore_health") + "/" + parsePlaceholder(player, "mmocore_max_health"));
        lore.add(StatsDisplay.MANA.getName() + ": " + parsePlaceholder(player, "mmocore_mana") + "/" + parsePlaceholder(player, "mmocore_stat_max_mana"));
        lore.add(StatsDisplay.DAMAGE.getName() + ": " + parsePlaceholder(player, "mmocore_stat_attack_damage"));
        lore.add(StatsDisplay.SPEED.getName() + ": " + parsePlaceholder(player, "mmocore_stat_movement_speed"));
        lore.add("");
        lore.add(StatsDisplay.MINING_SPEED.getName() + ": " + parsePlaceholder(player, "mmoitems_stat_faction_damage_breakingspeed"));
        lore.add(StatsDisplay.MAX_WEIGHT.getName() + ": " + parsePlaceholder(player, "ac_Stat_MaxWeight") + ChatColor.BLUE +
                " (" + ChatColor.DARK_PURPLE + parsePlaceholder(player, "ac_Stat_MaxWeightMultiplier") + ChatColor.BLUE + ")");
        lore.add(StatsDisplay.BLOCK_MULTIPLIER.getName() + ": " + parsePlaceholder(player, "ac_Stat_BlockMultiplier") + "x");
        lore.add(StatsDisplay.SELL_MULTIPLIER.getName() + ": " + parsePlaceholder(player, "ac_Stat_SellMultiplier") + "x");
        lore.add(StatsDisplay.LUCK_MULTIPLIER.getName() + ": " + parsePlaceholder(player, "ac_Stat_LuckMultiplier") + "x");
        lore.add(StatsDisplay.EXPERIENCE_MULTIPLIER.getName() + ": " + parsePlaceholder(player, "ac_Stat_EXPMultiplier") + "x");
        lore.add(StatsDisplay.PET_EXPERIENCE_MULTIPLIER.getName() + ": " + parsePlaceholder(player, "ac_Stat_Pet_EXPMultiplier") + "x");
        lore.add("");
        lore.add(StatsDisplay.MONEY_AMOUNT.getName() + ": " + parsePlaceholder(player, "vault_eco_balance_commas"));
        lore.add(StatsDisplay.EXPERIENCE_AMOUNT.getName() + ": " + parsePlaceholder(player, "ac_Stat_EXPAmount_formatted"));
        lore.add(StatsDisplay.PET_EXPERIENCE_AMOUNT.getName() + ": " + parsePlaceholder(player, "ac_Stat_Pet_EXPAmount_formatted"));
        lore.add(StatsDisplay.MINING_PASS_EXPERIENCE.getName() + ": " + parsePlaceholder(player, "ac_Stat_MiningPassEXPAmount_formatted"));
        lore.add(StatsDisplay.ADVENTURE_COINS.getName() + ": " + parsePlaceholder(player, "ac_Currency_AdventureCoins_formatted"));

        profile.setItemMeta(profileItemMeta);
        profile.setLore(lore);

        return profile;
    }

    private ItemStack attributes(Player player) {
        final ItemStack attributes = new ItemStack(Material.CAULDRON);
        final ItemMeta attributesItemMeta = attributes.getItemMeta();

        attributesItemMeta.displayName(Component.text(ChatColor.GREEN + "Attributes"));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "Increase your stats through " + ChatColor.GREEN + "Attributes" + ChatColor.GRAY + ",");
        lore.add(ChatColor.GRAY + "which are rewarded from certain " + ChatColor.BLUE + "Prestiges" + ChatColor.GRAY + "!");
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View Attributes");

        attributes.setItemMeta(attributesItemMeta);
        attributes.setLore(lore);

        return attributes;
    }


    private ItemStack hp(Player player) {
        final ItemStack hp = new ItemStack(Material.LIME_DYE);
        final ItemMeta hpItemMeta = hp.getItemMeta();

        hpItemMeta.displayName(Component.text(StatsDisplay.HP.getName() + ChatColor.WHITE + " = " + ChatColor.GREEN + parsePlaceholder(player, "mmocore_stat_max_health")));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(StatsDisplay.HP.getName() + ChatColor.GRAY + " is your Health, which is");
        lore.add(ChatColor.GRAY + "important for staying alive!");
        lore.add("");
        lore.add(ChatColor.GREEN + "Health Regeneration" + ChatColor.GRAY + ": " + ChatColor.YELLOW + parsePlaceholder(player, "mmocore_stat_health_regeneration"));

        hp.setItemMeta(hpItemMeta);
        hp.setLore(lore);

        return hp;
    }

    private ItemStack mana(Player player) {
        final ItemStack mana = new ItemStack(Material.CYAN_DYE);
        final ItemMeta manaItemMeta = mana.getItemMeta();

        manaItemMeta.displayName(Component.text(StatsDisplay.MANA.getName() + ChatColor.WHITE + " = " + ChatColor.AQUA + parsePlaceholder(player, "mmocore_stat_max_mana")));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(StatsDisplay.MANA.getName() + ChatColor.GRAY + " is consumed by Wands,");
        lore.add(ChatColor.GRAY + "Spells, and Abilities!");
        lore.add("");
        lore.add(ChatColor.AQUA + "Mana Regeneration" + ChatColor.GRAY + ": " + ChatColor.YELLOW + parsePlaceholder(player, "mmocore_stat_mana_regeneration"));

        mana.setItemMeta(manaItemMeta);
        mana.setLore(lore);

        return mana;
    }

    private ItemStack armor(Player player) {
        final ItemStack armor = new ItemStack(Material.YELLOW_DYE);
        final ItemMeta armorItemMeta = armor.getItemMeta();

        armorItemMeta.displayName(Component.text(StatsDisplay.ARMOR.getName() + ChatColor.GRAY + ChatColor.WHITE + " = " + ChatColor.YELLOW + parsePlaceholder(player, "mmoitems_stat_defense")));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(StatsDisplay.ARMOR.getName() + ChatColor.GRAY + " reduces the");
        lore.add(ChatColor.GRAY + "amount of incoming damage!");
        lore.add("");
        lore.add(ChatColor.YELLOW + parsePlaceholder(player, "mmoitems_stat_defense_percent") + ChatColor.GRAY + " of damage will be reduced!");

        armor.setItemMeta(armorItemMeta);
        armor.setLore(lore);

        return armor;
    }

    private ItemStack speed(Player player) {
        final ItemStack speed = new ItemStack(Material.GREEN_DYE);
        final ItemMeta speedItemMeta = speed.getItemMeta();

        speedItemMeta.displayName(Component.text(StatsDisplay.SPEED.getName() + ChatColor.WHITE + " = " + ChatColor.DARK_GREEN + parsePlaceholder(player, "mmocore_stat_movement_speed")));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(StatsDisplay.SPEED.getName() + ChatColor.GRAY + " is how fast you are!");

        speed.setItemMeta(speedItemMeta);
        speed.setLore(lore);

        return speed;
    }

    private ItemStack damage(Player player) {
        final ItemStack damage = new ItemStack(Material.RED_DYE);
        final ItemMeta damageItemMeta = damage.getItemMeta();

        damageItemMeta.displayName(Component.text(StatsDisplay.DAMAGE.getName() + ChatColor.WHITE + " = " + ChatColor.RED + parsePlaceholder(player, "mmocore_stat_attack_damage")));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(StatsDisplay.DAMAGE.getName() + ChatColor.GRAY + " is determined");
        lore.add(ChatColor.GRAY + "by how powerful you are!");

        damage.setItemMeta(damageItemMeta);
        damage.setLore(lore);

        return damage;
    }


    private ItemStack miningSpeed(Player player) {
        final ItemStack miningSpeed = new ItemStack(Material.ORANGE_DYE);
        final ItemMeta miningSpeedItemMeta = miningSpeed.getItemMeta();

        miningSpeedItemMeta.displayName(Component.text(StatsDisplay.MINING_SPEED.getName() + ChatColor.WHITE + " = " + ChatColor.GOLD + parsePlaceholder(player, "ac_Stat_MiningSpeed")));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(StatsDisplay.MINING_SPEED.getName() + ChatColor.GRAY + " increases the rate");
        lore.add(ChatColor.GRAY + "of how quickly you break a block!");
        lore.add("");
        lore.add(ChatColor.YELLOW + "1 " + StatsDisplay.MINING_SPEED.getName() + ChatColor.GRAY + " = " + ChatColor.YELLOW + "-0.5 Seconds");

        miningSpeed.setItemMeta(miningSpeedItemMeta);
        miningSpeed.setLore(lore);

        return miningSpeed;
    }

    private ItemStack maxWeight(Player player) {
        final ItemStack maxWeight = new ItemStack(Material.BLUE_DYE);
        final ItemMeta maxWeightItemMeta = maxWeight.getItemMeta();

        maxWeightItemMeta.displayName(Component.text(StatsDisplay.MAX_WEIGHT.getName() + ChatColor.WHITE + " = " + ChatColor.BLUE + parsePlaceholder(player, "ac_Stat_MaxWeight")));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(StatsDisplay.MAX_WEIGHT.getName() + ChatColor.GRAY + " increases the amount");
        lore.add(ChatColor.GRAY + "of weight you can hold while mining!");
        lore.add("");
        lore.add(ChatColor.GRAY + "Each item has its own weight, which");
        lore.add(ChatColor.GRAY + "can be viewed within your " + ChatColor.GOLD + "/Backpack" + ChatColor.GRAY + "!");
        lore.add("");
        lore.add(StatsDisplay.MAX_WEIGHT_MULTIPLIER.getName() + ChatColor.GRAY + " = " + ChatColor.YELLOW + parsePlaceholder(player, "ac_Stat_MaxWeightMultiplier"));

        maxWeight.setItemMeta(maxWeightItemMeta);
        maxWeight.setLore(lore);

        return maxWeight;
    }

    private ItemStack blockMultiplier(Player player) {
        final ItemStack blockMultiplier = new ItemStack(Material.RED_DYE);
        final ItemMeta blockMultiplierItemMeta = blockMultiplier.getItemMeta();

        blockMultiplierItemMeta.displayName(Component.text(StatsDisplay.BLOCK_MULTIPLIER.getName() + ChatColor.WHITE + " = " + ChatColor.DARK_RED + parsePlaceholder(player, "ac_Stat_BlockMultiplier")));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(StatsDisplay.BLOCK_MULTIPLIER.getName() + ChatColor.GRAY + " increases the amount");
        lore.add(ChatColor.GRAY + "of blocks you receive while mining!");

        blockMultiplier.setItemMeta(blockMultiplierItemMeta);
        blockMultiplier.setLore(lore);

        return blockMultiplier;
    }

    private ItemStack sellMultiplier(Player player) {
        final ItemStack sellMultiplier = new ItemStack(Material.GREEN_DYE);
        final ItemMeta sellMultiplierItemMeta = sellMultiplier.getItemMeta();

        sellMultiplierItemMeta.displayName(Component.text(StatsDisplay.SELL_MULTIPLIER.getName() + ChatColor.WHITE + " = " + ChatColor.DARK_GREEN + parsePlaceholder(player, "ac_Stat_SellMultiplier")));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(StatsDisplay.SELL_MULTIPLIER.getName() + ChatColor.GRAY + " increases the amount");
        lore.add(StatsDisplay.MONEY_AMOUNT.getName() + ChatColor.GRAY + " you receive when selling resources!");

        sellMultiplier.setItemMeta(sellMultiplierItemMeta);
        sellMultiplier.setLore(lore);

        return sellMultiplier;
    }

    private ItemStack luckMultiplier(Player player) {
        final ItemStack luckMultiplier = new ItemStack(Material.YELLOW_DYE);
        final ItemMeta luckMultiplierItemMeta = luckMultiplier.getItemMeta();

        luckMultiplierItemMeta.displayName(Component.text(StatsDisplay.LUCK_MULTIPLIER.getName() + ChatColor.WHITE + " = " + ChatColor.YELLOW + parsePlaceholder(player, "ac_Stat_LuckMultiplier")));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(StatsDisplay.LUCK_MULTIPLIER.getName() + ChatColor.GRAY + " increases the chances of");
        lore.add(ChatColor.GRAY + "finding rare items like " + ChatColor.YELLOW + "Crates " + ChatColor.GRAY + "while mining!");

        luckMultiplier.setItemMeta(luckMultiplierItemMeta);
        luckMultiplier.setLore(lore);

        return luckMultiplier;
    }

    private ItemStack experienceMultiplier(Player player) {
        final ItemStack experienceMultiplier = new ItemStack(Material.LIME_DYE);
        final ItemMeta experienceMultiplierItemMeta = experienceMultiplier.getItemMeta();

        experienceMultiplierItemMeta.displayName(Component.text(StatsDisplay.EXPERIENCE_MULTIPLIER.getName() + ChatColor.WHITE + " = " + ChatColor.GREEN + parsePlaceholder(player, "ac_Stat_EXPMultiplier")));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(StatsDisplay.EXPERIENCE_MULTIPLIER.getName() + ChatColor.GRAY + " increases the amount");
        lore.add(ChatColor.GRAY + "of " + StatsDisplay.EXPERIENCE_AMOUNT.getName() + ChatColor.GRAY + " you earn while mining!");

        experienceMultiplier.setItemMeta(experienceMultiplierItemMeta);
        experienceMultiplier.setLore(lore);

        return experienceMultiplier;
    }

    private ItemStack petExperienceMultiplier(Player player) {
        final ItemStack petExperienceMultiplier = new ItemStack(Material.CYAN_DYE);
        final ItemMeta petExperienceMultiplierItemMeta = petExperienceMultiplier.getItemMeta();

        petExperienceMultiplierItemMeta.displayName(Component.text(StatsDisplay.PET_EXPERIENCE_MULTIPLIER.getName() + ChatColor.WHITE + " = " + ChatColor.AQUA + parsePlaceholder(player, "ac_Stat_Pet_EXPMultiplier")));

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(StatsDisplay.PET_EXPERIENCE_MULTIPLIER.getName() + ChatColor.GRAY + " increases the amount");
        lore.add(ChatColor.GRAY + "of " + StatsDisplay.PET_EXPERIENCE_AMOUNT.getName() + ChatColor.GRAY + " you earn while mining!");

        petExperienceMultiplier.setItemMeta(petExperienceMultiplierItemMeta);
        petExperienceMultiplier.setLore(lore);

        return petExperienceMultiplier;
    }

    private String parsePlaceholder(Player player, String string) {
        return PlaceholderAPI.setPlaceholders(player, "%" + string + "%");
    }

}

