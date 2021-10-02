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
import monzter.adventurescraft.plugin.utilities.GUI.GUIHelperImplStatic;
import monzter.adventurescraft.plugin.utilities.enums.AdventureStatsDisplay;
import monzter.adventurescraft.plugin.utilities.enums.Prefix;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import net.kyori.adventure.text.Component;
import org.apache.commons.lang.StringUtils;
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
        OutlinePane outline = new OutlinePane(1, 2, 7, 5, Pane.Priority.LOW);


        background.addItem(new GuiItem(guiHelper.background(Material.LIGHT_BLUE_STAINED_GLASS_PANE)));
        background.setRepeat(true);

        for (Items item : Items.values())
            if (item.getxPosition() == 0 && item.getyPosition() == 0)
                outline.addItem(new GuiItem(itemStack(item, player)));

//        display.addItem(new GuiItem(booster()), 3, 1);
        display.addItem(new GuiItem(profile(player)), 4, 1);
        display.addItem(new GuiItem(attributes(), e -> player.performCommand("attributes")), 5, 1);


        display.addItem(new GuiItem(guiHelper.backButton(), e -> player.performCommand("main")), 4, 5);

        gui.addPane(background);
        gui.addPane(display);
        gui.addPane(outline);
        gui.show(player);
    }

    private ItemStack itemStack(Items item, Player player) {
        ItemStack itemStack = item.getGuiItem().getItem();
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(itemMeta.getDisplayName().replaceAll("(%.*?%)", PlaceholderAPI.setPlaceholders(player, "%" + StringUtils.substringBetween(itemMeta.getDisplayName(), "%", "%") + "%")));
        List<String> newLore = new ArrayList<>();
        for (String lore : item.getGuiItem().getItem().getLore()) {
            if (lore.contains("%"))
                lore = lore.replaceAll("(%.*?%)", PlaceholderAPI.setPlaceholders(player, "%" + StringUtils.substringBetween(lore, "%", "%") + "%"));
            newLore.add(lore);
        }
        itemMeta.setLore(newLore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private ItemStack booster() {
        String name = ChatColor.GREEN + "Active Boosters";
        return guiHelper.itemCreator(Material.EXPERIENCE_BOTTLE, name, new String[]{"",
                ChatColor.GRAY + "Boosters can increase the XP",
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
        lore.add(AdventureStatsDisplay.ATTACK_SPEED.getName() + ": " + parsePlaceholder(player, "mmocore_stat_attack_speed"));
        lore.add(AdventureStatsDisplay.CRITICAL_CHANCE.getName() + ": " + parsePlaceholder(player, "mmocore_stat_critical_strike_chance"));
        lore.add(AdventureStatsDisplay.CRITICAL_DAMAGE.getName() + ": " + parsePlaceholder(player, "mmocore_stat_critical_strike_power"));

        profile.setItemMeta(profileItemMeta);
        profile.setLore(lore);

        return profile;
    }

    private ItemStack attributes() {
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

    private ItemStack knockbackResistance(Player player) {
        String name = AdventureStatsDisplay.KNOCKBACK_RESISTANCE.getName() + ChatColor.WHITE + " = " + ChatColor.YELLOW + parsePlaceholder(player, "mmocore_stat_knockback_resistance");
        return guiHelper.itemCreator(Material.GRAY_DYE, name, new String[]{"",
                AdventureStatsDisplay.KNOCKBACK_RESISTANCE.getName() + ChatColor.GRAY + " decreases",
                ChatColor.GRAY + "the distance you get knocked back!"});
    }

    private String parsePlaceholder(Player player, String string) {
        return PlaceholderAPI.setPlaceholders(player, "%" + string + "%");
    }

    enum Items {
        HP(new GuiItem(GUIHelperImplStatic.itemCreator(Material.LIME_DYE, AdventureStatsDisplay.HP.getName() + ChatColor.WHITE + " = " + ChatColor.YELLOW + "%mmocore_stat_max_health%",
                new String[]{"",
                        ChatColor.translateAlternateColorCodes('&', AdventureStatsDisplay.HP.getName() + " &7is your &aHealth&7, which is"),
                        ChatColor.translateAlternateColorCodes('&', "&7key to staying alive!"),
                        "",
                        ChatColor.translateAlternateColorCodes('&', "&aHealth Regeneration&7: &e%mmocore_stat_health_regeneration%")}, false)), 0, 0),
        MANA(new GuiItem(GUIHelperImplStatic.itemCreator(Material.LIGHT_BLUE_DYE, AdventureStatsDisplay.MANA.getName() + ChatColor.WHITE + " = " + ChatColor.YELLOW + "%mmocore_stat_max_mana%",
                new String[]{"",
                        ChatColor.translateAlternateColorCodes('&', AdventureStatsDisplay.MANA.getName() + " &7is consumed by &aWands&7,"),
                        ChatColor.translateAlternateColorCodes('&', "&aSpells&7, and &aAbilities&7!"),
                        "",
                        ChatColor.translateAlternateColorCodes('&', "&bMana Regeneration&7: &e%mmocore_stat_mana_regeneration%")}, false)), 0, 0),
        ARMOR(new GuiItem(GUIHelperImplStatic.itemCreator(Material.YELLOW_DYE, AdventureStatsDisplay.ARMOR.getName() + ChatColor.WHITE + " = " + ChatColor.YELLOW + "%mmoitems_stat_defense%",
                new String[]{"",
                        ChatColor.translateAlternateColorCodes('&', AdventureStatsDisplay.ARMOR.getName() + " &areduces &7the"),
                        ChatColor.translateAlternateColorCodes('&', "&7amount of incoming &cdamage&7!"),
                        "",
                        ChatColor.translateAlternateColorCodes('&', "&e%mmoitems_stat_defense_percent% &7of &cdamage &7will be &areduced&7!")}, false)), 0, 0),
        SPEED(new GuiItem(GUIHelperImplStatic.itemCreator(Material.GREEN_DYE, AdventureStatsDisplay.SPEED.getName() + ChatColor.WHITE + " = " + ChatColor.YELLOW + "%mmocore_stat_movement_speed%",
                new String[]{"",
                        ChatColor.translateAlternateColorCodes('&', AdventureStatsDisplay.SPEED.getName() + " &7is how &afast &7you move&7!")}, false)), 0, 0),
        DAMAGE(new GuiItem(GUIHelperImplStatic.itemCreator(Material.RED_DYE, AdventureStatsDisplay.DAMAGE.getName() + ChatColor.WHITE + " = " + ChatColor.YELLOW + "%mmocore_stat_attack_damage%",
                new String[]{"",
                        ChatColor.translateAlternateColorCodes('&', AdventureStatsDisplay.DAMAGE.getName() + " &7increases the amount of"),
                        ChatColor.translateAlternateColorCodes('&', "&7damage done by &cPhysical Attacks&7!")}, false)), 0, 0),
        MAGIC_DAMAGE(new GuiItem(GUIHelperImplStatic.itemCreator(Material.PINK_DYE, AdventureStatsDisplay.MAGIC_DAMAGE.getName() + ChatColor.WHITE + " = " + ChatColor.YELLOW + "%mmocore_stat_magic_damage%",
                new String[]{"",
                        ChatColor.translateAlternateColorCodes('&', AdventureStatsDisplay.MAGIC_DAMAGE.getName() + " &7increases the amount of"),
                        ChatColor.translateAlternateColorCodes('&', "&7damage done by &dMagical Attacks&7!")}, false)), 0, 0),
        ABILITY_DAMAGE(new GuiItem(GUIHelperImplStatic.itemCreator(Material.ORANGE_DYE, AdventureStatsDisplay.ABILITY_DAMAGE.getName() + ChatColor.WHITE + " = " + ChatColor.YELLOW + "%mmocore_stat_skill_damage%",
                new String[]{"",
                        ChatColor.translateAlternateColorCodes('&', AdventureStatsDisplay.ABILITY_DAMAGE.getName() + " &7increases the amount of"),
                        ChatColor.translateAlternateColorCodes('&', "&7damage done by all &6Abilities&7!")}, false)), 0, 0),
        PROJECTILE_DAMAGE(new GuiItem(GUIHelperImplStatic.itemCreator(Material.WHITE_DYE, AdventureStatsDisplay.PROJECTILE_DAMAGE.getName() + ChatColor.WHITE + " = " + ChatColor.YELLOW + "%mmocore_stat_projectile_damage%",
                new String[]{"",
                        ChatColor.translateAlternateColorCodes('&', AdventureStatsDisplay.PROJECTILE_DAMAGE.getName() + " &7increases the amount of"),
                        ChatColor.translateAlternateColorCodes('&', "&7damage done by &6Projectiles&7!")}, false)), 0, 0),
        COOLDOWN_REDUCTION(new GuiItem(GUIHelperImplStatic.itemCreator(Material.ORANGE_DYE, AdventureStatsDisplay.COOLDOWN_REDUCTION.getName() + ChatColor.WHITE + " = " + ChatColor.YELLOW + "%mmocore_stat_cooldown_reduction%%",
                new String[]{"",
                        ChatColor.translateAlternateColorCodes('&', AdventureStatsDisplay.COOLDOWN_REDUCTION.getName() + " &7decreases"),
                        ChatColor.translateAlternateColorCodes('&', "&7the cooldown on all &6Abilities&7!")}, false)), 0, 0),
        ATTACK_SPEED(new GuiItem(GUIHelperImplStatic.itemCreator(Material.BLUE_DYE, AdventureStatsDisplay.ATTACK_SPEED.getName() + ChatColor.WHITE + " = " + ChatColor.YELLOW + "%mmocore_stat_attack_speed%",
                new String[]{"",
                        ChatColor.translateAlternateColorCodes('&', AdventureStatsDisplay.ATTACK_SPEED.getName() + " &7increases the amount"),
                        ChatColor.translateAlternateColorCodes('&', "&7of times you can attack per second!")}, false)), 0, 0),
        CRITICAL_CHANCE(new GuiItem(GUIHelperImplStatic.itemCreator(Material.ORANGE_DYE, AdventureStatsDisplay.CRITICAL_CHANCE.getName() + ChatColor.WHITE + " = " + ChatColor.YELLOW + "%mmocore_stat_critical_strike_chance%",
                new String[]{"",
                        ChatColor.translateAlternateColorCodes('&', AdventureStatsDisplay.CRITICAL_CHANCE.getName() + " &7increases the chance"),
                        ChatColor.translateAlternateColorCodes('&', "&7of hitting a &4critical strike&7!")}, false)), 0, 0),
        CRITICAL_DAMAGE(new GuiItem(GUIHelperImplStatic.itemCreator(Material.RED_DYE, AdventureStatsDisplay.CRITICAL_DAMAGE.getName() + ChatColor.WHITE + " = " + ChatColor.YELLOW + "%mmocore_stat_critical_strike_power%",
                new String[]{"",
                        ChatColor.translateAlternateColorCodes('&', AdventureStatsDisplay.CRITICAL_DAMAGE.getName() + " &7increases damage dealt"),
                        ChatColor.translateAlternateColorCodes('&', "&7by a &4critical strike&7!")}, false)), 0, 0),
        UNDEAD_DAMAGE(new GuiItem(GUIHelperImplStatic.itemCreator(Material.GREEN_DYE, AdventureStatsDisplay.UNDEAD_DAMAGE.getName() + ChatColor.WHITE + " = " + ChatColor.YELLOW + "%mmoitems_stat_faction_damage_Undead%",
                new String[]{"",
                        ChatColor.translateAlternateColorCodes('&', AdventureStatsDisplay.UNDEAD_DAMAGE.getName() + " &7increases the amount"),
                        ChatColor.translateAlternateColorCodes('&', "&7of damage dealt to &2Undead &cenemies&7!")}, false)), 0, 0),
        HELL_DAMAGE(new GuiItem(GUIHelperImplStatic.itemCreator(Material.RED_DYE, AdventureStatsDisplay.HELL_DAMAGE.getName() + ChatColor.WHITE + " = " + ChatColor.YELLOW + "%mmoitems_stat_faction_damage_Hell%",
                new String[]{"",
                        ChatColor.translateAlternateColorCodes('&', AdventureStatsDisplay.HELL_DAMAGE.getName() + " &7increases the amount"),
                        ChatColor.translateAlternateColorCodes('&', "&7of damage dealt to &cHell enemies&7!")}, false)), 0, 0),
        VOID_DAMAGE(new GuiItem(GUIHelperImplStatic.itemCreator(Material.PURPLE_DYE, AdventureStatsDisplay.VOID_DAMAGE.getName() + ChatColor.WHITE + " = " + ChatColor.YELLOW + "%mmoitems_stat_faction_damage_Void%",
                new String[]{"",
                        ChatColor.translateAlternateColorCodes('&', AdventureStatsDisplay.VOID_DAMAGE.getName() + " &7increases the amount"),
                        ChatColor.translateAlternateColorCodes('&', "&7of damage dealt to &5Void &cenemies&7!")}, false)), 0, 0),
        ;
        public GuiItem guiItem;
        public int xPosition;
        public int yPosition;

        Items(GuiItem guiItem, int xPosition, int yPosition) {
            this.guiItem = guiItem;
            this.xPosition = xPosition;
            this.yPosition = yPosition;
        }

        public GuiItem getGuiItem() {
            return guiItem;
        }

        public int getxPosition() {
            return xPosition;
        }

        public int getyPosition() {
            return yPosition;
        }

        public void setGuiItem(GuiItem guiItem) {
            this.guiItem = guiItem;
        }
    }

}

