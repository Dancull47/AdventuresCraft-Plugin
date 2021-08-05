package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.mainMenu;

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
import monzter.adventurescraft.plugin.utilities.enums.AdventureStatsDisplay;
import monzter.adventurescraft.plugin.utilities.enums.Prefix;
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

        display.addItem(new GuiItem(undeadDamage(player)), 3, 0);
        display.addItem(new GuiItem(hellDamage(player)), 4, 0);
        display.addItem(new GuiItem(voidDamage(player)), 5, 0);

        display.addItem(new GuiItem(booster(player)), 3, 1);
        display.addItem(new GuiItem(profile(player)), 4, 1);
        display.addItem(new GuiItem(attributes(player), e -> player.performCommand("attributes")), 5, 1);

        display.addItem(new GuiItem(hp(player)), 2, 2);
        display.addItem(new GuiItem(mana(player)), 3, 2);
        display.addItem(new GuiItem(armor(player)), 4, 2);
        display.addItem(new GuiItem(speed(player)), 5, 2);
        display.addItem(new GuiItem(damage(player)), 6, 2);

        display.addItem(new GuiItem(attackSpeed(player)), 2, 3);
        display.addItem(new GuiItem(critChance(player)), 3, 3);
        display.addItem(new GuiItem(critDamage(player)), 4, 3);
        display.addItem(new GuiItem(projectileDamage(player)), 5, 3);
        display.addItem(new GuiItem(knockbackResistance(player)), 6, 3);

        display.addItem(new GuiItem(skillDamage(player)), 2, 4);
        display.addItem(new GuiItem(magicDamage(player)), 3, 4);
        display.addItem(new GuiItem(cooldownReduction(player)), 4, 4);
        display.addItem(new GuiItem(bonusExperience(player)), 5, 4);


        display.addItem(new GuiItem(guiHelper.backButton(), e -> player.performCommand("main")), 4, 5);

        gui.addPane(background);
        gui.addPane(display);
        gui.show(player);
    }


    private ItemStack undeadDamage(Player player) {
        String name = AdventureStatsDisplay.UNDEAD_DAMAGE.getName() + ChatColor.WHITE + " = " + ChatColor.YELLOW + parsePlaceholder(player, "mmoitems_stat_faction_damage_Undead");
        return guiHelper.itemCreator(Material.GREEN_DYE, name, new String[]{"",
                AdventureStatsDisplay.UNDEAD_DAMAGE.getName() + ChatColor.GRAY + " increases the amount",
                ChatColor.GRAY + "of damage you deal to " + ChatColor.DARK_GREEN + "Undead " + ChatColor.GRAY + "enemies!",
                "",
                ChatColor.GRAY + "Increase your " + ChatColor.DARK_GREEN + "Undead Damage " + ChatColor.GRAY + "by",
                ChatColor.GRAY + "increasing your reputation in",
                ChatColor.GRAY + "the " + ChatColor.DARK_GREEN + "Undead" + ChatColor.GRAY + ", and by obtaining",
                ChatColor.GRAY + "items from within the " + ChatColor.DARK_GREEN + "Undead Areas" + ChatColor.GRAY + "!",
        });
    }

    private ItemStack hellDamage(Player player) {
        String name = AdventureStatsDisplay.HELL_DAMAGE.getName() + ChatColor.WHITE + " = " + ChatColor.YELLOW + parsePlaceholder(player, "mmoitems_stat_faction_damage_Hell");
        return guiHelper.itemCreator(Material.RED_DYE, name, new String[]{"",
                AdventureStatsDisplay.HELL_DAMAGE.getName() + ChatColor.GRAY + " increases the amount",
                ChatColor.GRAY + "of damage you deal to " + ChatColor.RED + "Hell " + ChatColor.GRAY + "enemies!",
                "",
                ChatColor.GRAY + "Increase your " + ChatColor.RED + "Hell Damage " + ChatColor.GRAY + "by",
                ChatColor.GRAY + "increasing your reputation in",
                ChatColor.GRAY + "the " + ChatColor.RED + "Hell" + ChatColor.GRAY + ", and by obtaining",
                ChatColor.GRAY + "items from within the " + ChatColor.DARK_RED + "Hell Areas" + ChatColor.GRAY + "!",
        });
    }

    private ItemStack voidDamage(Player player) {
        String name = AdventureStatsDisplay.VOID_DAMAGE.getName() + ChatColor.WHITE + " = " + ChatColor.YELLOW + parsePlaceholder(player, "mmoitems_stat_faction_damage_Void");
        return guiHelper.itemCreator(Material.PURPLE_DYE, name, new String[]{"",
                AdventureStatsDisplay.VOID_DAMAGE.getName() + ChatColor.GRAY + " increases the amount",
                ChatColor.GRAY + "of damage you deal to " + ChatColor.DARK_PURPLE + "Void " + ChatColor.GRAY + "enemies!",
                "",
                ChatColor.GRAY + "Increase your " + ChatColor.DARK_PURPLE + "Void Damage " + ChatColor.GRAY + "by",
                ChatColor.GRAY + "increasing your reputation in",
                ChatColor.GRAY + "the " + ChatColor.DARK_PURPLE + "Void" + ChatColor.GRAY + ", and by obtaining",
                ChatColor.GRAY + "items from within the " + ChatColor.DARK_PURPLE + "Void Areas" + ChatColor.GRAY + "!",
        });
    }


    private ItemStack booster(Player player) {
        String name = ChatColor.GREEN + "Active Boosters";
        return guiHelper.itemCreator(Material.EXPERIENCE_BOTTLE, name, new String[]{"",
                ChatColor.GRAY + " Boosters can increase the XP",
                ChatColor.GRAY + "you receive for " + ChatColor.GOLD + "Professions ",
                ChatColor.GRAY + "and " + ChatColor.GOLD + "Main " + ChatColor.GRAY + "level!",
                "",
                Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View",
        });
    }

    private ItemStack profile(Player player) {
        final ItemStack profile = new ItemStack(SkullCreator.itemFromUuid(player.getUniqueId()));
        final ItemMeta profileItemMeta = profile.getItemMeta();

        profileItemMeta.displayName(Component.text(ChatColor.GREEN + "Profile"));

        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.WHITE + "Level: " + ChatColor.GRAY + parsePlaceholder(player, "mmocore_level") + ChatColor.DARK_GRAY + " [" + ChatColor.YELLOW + parsePlaceholder(player, "mmocore_experience") + "/" + parsePlaceholder(player, "mmocore_next_level") + "EXP" + ChatColor.DARK_GRAY + "]");
        lore.add("");
        lore.add(AdventureStatsDisplay.HP.getName() + ": " + parsePlaceholder(player, "mmocore_health") + "/" + parsePlaceholder(player, "mmocore_max_health"));
        lore.add(AdventureStatsDisplay.MANA.getName() + ": " + parsePlaceholder(player, "mmocore_mana") + "/" + parsePlaceholder(player, "mmocore_stat_max_mana"));
        lore.add(AdventureStatsDisplay.ARMOR.getName() + ": " + parsePlaceholder(player, "mmocore_stat_defense"));
        lore.add(AdventureStatsDisplay.SPEED.getName() + ": " + parsePlaceholder(player, "mmocore_stat_movement_speed"));
        lore.add(AdventureStatsDisplay.DAMAGE.getName() + ": " + parsePlaceholder(player, "mmocore_stat_attack_damage"));
        lore.add(AdventureStatsDisplay.ATTACK_SPEED.getName() + ": " + parsePlaceholder(player, "mmocore_stat_attack_damage"));
        lore.add(AdventureStatsDisplay.CRITICAL_CHANCE.getName() + ": " + parsePlaceholder(player, "mmocore_stat_critical_strike_chance"));
        lore.add(AdventureStatsDisplay.CRITICAL_DAMAGE.getName() + ": " + parsePlaceholder(player, "mmocore_stat_critical_strike_power"));

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
        lore.add(ChatColor.GRAY + "which are rewarded upon " + ChatColor.GREEN + "leveling up" + ChatColor.GRAY + "!");
        lore.add("");
        lore.add(Prefix.PREFIX.getString() + ChatColor.YELLOW + "Click to View Attributes");

        attributes.setItemMeta(attributesItemMeta);
        attributes.setLore(lore);

        return attributes;
    }


    private ItemStack hp(Player player) {
        String name = AdventureStatsDisplay.HP.getName() + ChatColor.WHITE + " = " + ChatColor.YELLOW + parsePlaceholder(player, "mmocore_stat_max_health");
        return guiHelper.itemCreator(Material.LIME_DYE, name, new String[]{"",
                AdventureStatsDisplay.HP.getName() + ChatColor.GRAY + " is your Health, which is",
                ChatColor.GRAY + "important for staying alive!",
                "",
                ChatColor.GREEN + "Health Regeneration" + ChatColor.GRAY + ": " + ChatColor.YELLOW + parsePlaceholder(player, "mmocore_stat_health_regeneration")});
    }

    private ItemStack mana(Player player) {
        String name = AdventureStatsDisplay.MANA.getName() + ChatColor.WHITE + " = " + ChatColor.YELLOW + parsePlaceholder(player, "mmocore_stat_max_mana");
        return guiHelper.itemCreator(Material.CYAN_DYE, name, new String[]{"",
                AdventureStatsDisplay.MANA.getName() + ChatColor.GRAY + " is consumed by Wands,",
                ChatColor.GRAY + "Spells, and Abilities!",
                "",
                ChatColor.AQUA + "Mana Regeneration" + ChatColor.GRAY + ": " + ChatColor.YELLOW + parsePlaceholder(player, "mmocore_stat_mana_regeneration")});
    }

    private ItemStack armor(Player player) {
        String name = AdventureStatsDisplay.ARMOR.getName() + ChatColor.WHITE + " = " + ChatColor.YELLOW + parsePlaceholder(player, "mmoitems_stat_defense");
        return guiHelper.itemCreator(Material.YELLOW_DYE, name, new String[]{"",
                AdventureStatsDisplay.ARMOR.getName() + ChatColor.GRAY + " reduces the",
                ChatColor.GRAY + "amount of incoming damage!",
                "",
                ChatColor.YELLOW + parsePlaceholder(player, "mmoitems_stat_defense_percent") + ChatColor.GRAY + " damage will be reduced!"});
    }

    private ItemStack speed(Player player) {
        String name = AdventureStatsDisplay.SPEED.getName() + ChatColor.WHITE + " = " + ChatColor.YELLOW + parsePlaceholder(player, "mmocore_stat_movement_speed");
        return guiHelper.itemCreator(Material.GREEN_DYE, name, new String[]{"",
                AdventureStatsDisplay.SPEED.getName() + ChatColor.GRAY + " is how fast you are!"});
    }

    private ItemStack damage(Player player) {
        String name = AdventureStatsDisplay.DAMAGE.getName() + ChatColor.WHITE + " = " + ChatColor.YELLOW + parsePlaceholder(player, "mmocore_stat_attack_damage");
        return guiHelper.itemCreator(Material.RED_DYE, name, new String[]{"",
                AdventureStatsDisplay.DAMAGE.getName() + ChatColor.GRAY + " is determined",
        ChatColor.GRAY + "by how powerful you are!"});
    }


    private ItemStack attackSpeed(Player player) {
        String name = AdventureStatsDisplay.ATTACK_SPEED.getName() + ChatColor.WHITE + " = " + ChatColor.YELLOW + parsePlaceholder(player, "mmocore_stat_attack_speed");
        return guiHelper.itemCreator(Material.BLUE_DYE, name, new String[]{"",
                AdventureStatsDisplay.ATTACK_SPEED.getName() + ChatColor.GRAY + " increases the amount",
                ChatColor.GRAY + "of damage you can do per hit"});
    }

    private ItemStack critChance(Player player) {
        String name = AdventureStatsDisplay.CRITICAL_CHANCE.getName() + ChatColor.WHITE + " = " + ChatColor.YELLOW + parsePlaceholder(player, "mmocore_stat_critical_strike_chance");
        return guiHelper.itemCreator(Material.ORANGE_DYE, name, new String[]{"",
                AdventureStatsDisplay.CRITICAL_CHANCE.getName() + ChatColor.GRAY + " increases your",
                ChatColor.GRAY + "chance of hitting a critical attack!"});
    }

    private ItemStack critDamage(Player player) {
        String name = AdventureStatsDisplay.CRITICAL_DAMAGE.getName() + ChatColor.WHITE + " = " + ChatColor.YELLOW + parsePlaceholder(player, "mmocore_stat_critical_strike_power");
        return guiHelper.itemCreator(Material.RED_DYE, name, new String[]{"",
                AdventureStatsDisplay.CRITICAL_DAMAGE.getName() + ChatColor.GRAY + " increases the amount of",
                ChatColor.GRAY + "damage when hitting a critical attack!"});
    }

    private ItemStack projectileDamage(Player player) {
        String name = AdventureStatsDisplay.PROJECTILE_DAMAGE.getName() + ChatColor.WHITE + " = " + ChatColor.YELLOW + parsePlaceholder(player, "mmocore_stat_projectile_damage");
        return guiHelper.itemCreator(Material.BONE_MEAL, name, new String[]{"",
                AdventureStatsDisplay.PROJECTILE_DAMAGE.getName() + ChatColor.GRAY + " increases the",
                ChatColor.GRAY + "amount of damage done by projectiles!"});
    }

    private ItemStack knockbackResistance(Player player) {
        String name = AdventureStatsDisplay.KNOCKBACK_RESISTANCE.getName() + ChatColor.WHITE + " = " + ChatColor.YELLOW + parsePlaceholder(player, "mmocore_stat_knockback_resistance");
        return guiHelper.itemCreator(Material.GRAY_DYE, name, new String[]{"",
                AdventureStatsDisplay.KNOCKBACK_RESISTANCE.getName() + ChatColor.GRAY + " decreases",
                ChatColor.GRAY + "the distance you get knocked back!"});
    }

    private ItemStack skillDamage(Player player) {
        String name = AdventureStatsDisplay.SKILL_DAMAGE.getName() + ChatColor.WHITE + " = " + ChatColor.YELLOW + parsePlaceholder(player, "mmocore_stat_skill_damage");
        return guiHelper.itemCreator(Material.PURPLE_DYE, name, new String[]{"",
                AdventureStatsDisplay.SKILL_DAMAGE.getName() + ChatColor.GRAY + " increases the amount",
                ChatColor.GRAY + "of damage when using an " + ChatColor.GOLD + "Ability" + ChatColor.GRAY + "!"});
    }

    private ItemStack magicDamage(Player player) {
        String name = AdventureStatsDisplay.MAGIC_DAMAGE.getName() + ChatColor.WHITE + " = " + ChatColor.YELLOW + parsePlaceholder(player, "mmocore_stat_magic_damage");
        return guiHelper.itemCreator(Material.ORANGE_DYE, name, new String[]{"",
                AdventureStatsDisplay.MAGIC_DAMAGE.getName() + ChatColor.GRAY + " increases the amount of",
                ChatColor.GRAY + "damage done by " + ChatColor.LIGHT_PURPLE + "Magical attacks" + ChatColor.GRAY + "!"});
    }

    private ItemStack cooldownReduction(Player player) {
        String name = AdventureStatsDisplay.COOLDOWN_REDUCTION.getName() + ChatColor.WHITE + " = " + ChatColor.YELLOW + parsePlaceholder(player, "mmocore_stat_cooldown_reduction");
        return guiHelper.itemCreator(Material.BLUE_DYE, name, new String[]{"",
                AdventureStatsDisplay.COOLDOWN_REDUCTION.getName() + ChatColor.GRAY + " decreases",
                ChatColor.GRAY + "the cooldown timer on " + ChatColor.GOLD + "Abilities" + ChatColor.GRAY + "!"});
    }

    private ItemStack bonusExperience(Player player) {
        String name = AdventureStatsDisplay.BONUS_EXPERIENCE.getName() + ChatColor.WHITE + " = " + ChatColor.YELLOW + parsePlaceholder(player, "mmocore_stat_additional_experience");
        return guiHelper.itemCreator(Material.YELLOW_DYE, name, new String[]{"",
                AdventureStatsDisplay.BONUS_EXPERIENCE.getName() + ChatColor.GRAY + " increases the",
                ChatColor.GRAY + "amount of experience you receive",
                ChatColor.GRAY + "towards your " + ChatColor.GREEN + "Main " + ChatColor.GRAY + "and " + ChatColor.GOLD + "Professions" + ChatColor.GRAY + "!",
        });
    }

    private String parsePlaceholder(Player player, String string) {
        return PlaceholderAPI.setPlaceholders(player, "%" + string + "%");
    }

}

