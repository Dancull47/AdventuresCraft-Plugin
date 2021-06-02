package monzter.adventurescraft.plugin.network.PrisonGamemode.cell.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import co.aikar.commands.annotation.Optional;
import co.aikar.commands.bukkit.contexts.OnlinePlayer;
import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.PaginatedPane;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import dev.dbassett.skullcreator.SkullCreator;
import io.lumine.mythic.utils.Schedulers;
import me.clip.placeholderapi.PlaceholderAPI;
import me.lucko.helper.Events;
import me.lucko.helper.event.filter.EventFilters;
import me.lucko.helper.metadata.Metadata;
import me.lucko.helper.metadata.MetadataKey;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.GUI.GUIHelper;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import net.kyori.adventure.text.Component;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import world.bentobox.bentobox.BentoBox;
import world.bentobox.bentobox.api.localization.TextVariables;
import world.bentobox.bentobox.api.user.User;
import world.bentobox.bentobox.database.objects.Island;
import world.bentobox.bentobox.managers.RanksManager;
import world.bentobox.bentobox.util.Util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@CommandAlias("Cells")
public class CellDisplayGUI extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final SoundManager soundManager;
    private final BentoBox bentoBox;
    private final GUIHelper guiHelper;

    private final String PREFIX = ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "» ";
    private final String COOP = ChatColor.DARK_GREEN + "Coop";
    private final String TRUSTED = ChatColor.DARK_AQUA + "Trusted";
    private final String MEMBER = ChatColor.AQUA + "Member";
    private final String SUBOWNER = ChatColor.GOLD + "Sub-Owner";
    private final String OWNER = ChatColor.RED + "Owner";

    public CellDisplayGUI(AdventuresCraft plugin, SoundManager soundManager, BentoBox bentoBox, GUIHelper guiHelper) {
        this.plugin = plugin;
        this.soundManager = soundManager;
        this.bentoBox = bentoBox;
        this.guiHelper = guiHelper;
    }

    private final ItemStack backgroundItem = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
    private final ItemMeta backgroundItemMeta = backgroundItem.getItemMeta();

    private final ItemStack backButton = new ItemStack(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODY1MmUyYjkzNmNhODAyNmJkMjg2NTFkN2M5ZjI4MTlkMmU5MjM2OTc3MzRkMThkZmRiMTM1NTBmOGZkYWQ1ZiJ9fX0="));
    private final ItemMeta backButtonItemMeta = backButton.getItemMeta();

    private final ItemStack viewTeamManager = new ItemStack(Material.TOTEM_OF_UNDYING);
    private final ItemMeta viewTeamManagerItemMeta = viewTeamManager.getItemMeta();

    private final ItemStack viewCellSettings = new ItemStack(Material.CAULDRON);
    private final ItemMeta viewCellSettingsItemMeta = viewCellSettings.getItemMeta();

    private final ItemStack setHome = new ItemStack(Material.OAK_DOOR);
    private final ItemMeta setHomeItemMeta = setHome.getItemMeta();

    @Default
    @Subcommand("Menu")
    @CommandAlias("CellMenu|CellManager")
    public void cellMenu(Player player) {
        Island island = bentoBox.getIslands().getIsland(Bukkit.getWorld("Cell_world"), player.getUniqueId());

        backgroundItemMeta.displayName(Component.text(" "));
        backgroundItem.setItemMeta(backgroundItemMeta);

        viewTeamManagerItemMeta.displayName(Component.text(ChatColor.GREEN + "Team Manager"));
        viewTeamManager.setItemMeta(viewTeamManagerItemMeta);
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "Manage the " + MEMBER + "s");
        lore.add(ChatColor.GRAY + "within your Cell!");
        lore.add("");
        lore.add(PREFIX + ChatColor.YELLOW + "Click to View");
        viewTeamManager.setLore(lore);

        viewCellSettingsItemMeta.displayName(Component.text(ChatColor.GREEN + "Setting Manager"));
        viewCellSettings.setItemMeta(viewCellSettingsItemMeta);
        List<String> lore2 = new ArrayList<>();
        lore2.add("");
        lore2.add(ChatColor.GRAY + "Manage the " + ChatColor.GREEN + "Settings");
        lore2.add(ChatColor.GRAY + "within your Cell!");
        lore2.add("");
        lore2.add(PREFIX + ChatColor.YELLOW + "Click to View");
        viewCellSettings.setLore(lore2);


        ChestGui gui = new ChestGui(4, "Cell Manager");
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        PaginatedPane page = new PaginatedPane(0, 0, 9, 4);
        OutlinePane background = new OutlinePane(0, 0, 9, 4, Pane.Priority.LOWEST);
        StaticPane display = new StaticPane(0, 0, 9, 4, Pane.Priority.LOW);

        page.addPane(0, background);
        page.addPane(0, display);

        background.addItem(new GuiItem(backgroundItem));
        background.setRepeat(true);

        for (UUID playerUUID : island.getMemberSet()) {
            if (island.getRank(playerUUID) >= RanksManager.OWNER_RANK) {
                setHomeItemMeta.displayName(Component.text(ChatColor.GREEN + "Set Home"));
                setHome.setItemMeta(setHomeItemMeta);
                List<String> lore3 = new ArrayList<>();
                lore3.add("");
                lore3.add(ChatColor.GRAY + "You can set your spawn");
                lore3.add(ChatColor.GRAY + "by using " + ChatColor.GOLD + "/cell sethome" + ChatColor.GRAY + "!");
                setHome.setLore(lore3);
                display.addItem(new GuiItem(setHome, e -> player.performCommand("cell sethome")), 2, 1);
            }
        }

        display.addItem(new GuiItem(viewTeamManager, e -> cellTeamManager(player)), 4, 1);
        display.addItem(new GuiItem(viewCellSettings, e -> player.performCommand("CellSettings")), 6, 1);

        display.addItem(new GuiItem(guiHelper.backButton(), e -> player.performCommand("main")), 4, 3);

        gui.addPane(page);
        gui.show(player);
    }

    @Subcommand("TeamManager")
    @CommandAlias("CellTeamManager")
    public void cellTeamManager(Player player) {
        Island island = bentoBox.getIslands().getIsland(Bukkit.getWorld("Cell_world"), player.getUniqueId());
        backgroundItemMeta.displayName(Component.text(" "));
        backgroundItem.setItemMeta(backgroundItemMeta);

        backButtonItemMeta.displayName(Component.text(ChatColor.GREEN + "Go Back"));
        backButton.setItemMeta(backButtonItemMeta);

        ChestGui gui = new ChestGui(6, "Cell Team Manager");
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        PaginatedPane page = new PaginatedPane(0, 0, 9, 6);
        OutlinePane background = new OutlinePane(0, 0, 9, 6, Pane.Priority.LOWEST);
        StaticPane display = new StaticPane(1, 1, 7, 4, Pane.Priority.LOW);
        StaticPane back = new StaticPane(4, 5, 1, 1);
        page.addPane(0, background);
        page.addPane(0, display);
        page.addPane(0, back);

        background.addItem(new GuiItem(backgroundItem));
        background.setRepeat(true);

        back.addItem(new GuiItem(backButton, e -> cellMenu(player)), 0, 0);

        display.addItem(new GuiItem(mainCell(island, player), e -> {
            if (e.getClick().isLeftClick())
                if (island.getRank(player.getUniqueId()) >= RanksManager.OWNER_RANK)
                    changeName(island, player);
        }), 3, 0);
        display.addItem(new GuiItem(coopMembers(island)), 1, 1);
        display.addItem(new GuiItem(trustedMembers(island)), 2, 1);
        display.addItem(new GuiItem(memberMembers(island)), 3, 1);
        display.addItem(new GuiItem(subOwners(island)), 4, 1);
        display.addItem(new GuiItem(owners(island)), 5, 1);
        display.addItem(new GuiItem(ranking(island, player)), 1, 2);
        display.addItem(new GuiItem(invite(island, player), e -> {
            if (island.getRank(player.getUniqueId()) >= RanksManager.OWNER_RANK)
                player.performCommand("CellSettings");
        }), 2, 2);
        display.addItem(new GuiItem(promote()), 4, 2);

        gui.addPane(page);
        gui.show(player);
    }

    @Subcommand("Viewer")
    @CommandAlias("CellViewer")
    @CommandCompletion("*")
    public void cellViewer(Player player, @Optional OnlinePlayer target) {
        Island island = null;
        Player mainTarget = null;
        if (target == null) {
            if (bentoBox.getIslands().getIslandAt(player.getLocation()) != null) {
                island = bentoBox.getIslands().getIslandAt(player.getLocation()).get();
                mainTarget = player;
            }
        } else {
            island = bentoBox.getIslands().getIsland(Bukkit.getWorld("Cell_world"), target.player.getUniqueId());
            mainTarget = target.player;
        }
        if (island != null) {
            backgroundItemMeta.displayName(Component.text(" "));
            backgroundItem.setItemMeta(backgroundItemMeta);

            ChestGui gui = new ChestGui(6, "Cell Team Manager");
            gui.setOnGlobalClick(event -> event.setCancelled(true));

            PaginatedPane page = new PaginatedPane(0, 0, 9, 6);
            OutlinePane background = new OutlinePane(0, 0, 9, 6, Pane.Priority.LOWEST);
            StaticPane display = new StaticPane(1, 1, 7, 4, Pane.Priority.LOW);

            page.addPane(0, background);
            page.addPane(0, display);

            background.addItem(new GuiItem(backgroundItem));
            background.setRepeat(true);

            display.addItem(new GuiItem(mainCell(island, mainTarget)), 3, 0);
            display.addItem(new GuiItem(coopMembers(island)), 1, 1);
            display.addItem(new GuiItem(trustedMembers(island)), 2, 1);
            display.addItem(new GuiItem(memberMembers(island)), 3, 1);
            display.addItem(new GuiItem(subOwners(island)), 4, 1);
            display.addItem(new GuiItem(owners(island)), 5, 1);
            gui.addPane(page);
            gui.show(player);
        }
    }

    @CommandAlias("cellTeamDetails")
    public void cellTeamDeatils(Player player) {
        showMembers(bentoBox.getIslands().getIsland(Bukkit.getWorld("Cell_world"), player.getUniqueId()), bentoBox.getPlayers().getUser(player.getUniqueId()));
    }

    private void showMembers(Island island, User user) {
        // Gather online members
        List<UUID> onlineMembers = island
                .getMemberSet(RanksManager.MEMBER_RANK)
                .stream()
                .filter(uuid -> Bukkit.getOfflinePlayer(uuid)
                        .isOnline())
                .collect(Collectors.toList());

        // List of ranks that we will loop through
        Integer[] ranks = new Integer[]{RanksManager.OWNER_RANK, RanksManager.SUB_OWNER_RANK, RanksManager.MEMBER_RANK, RanksManager.TRUSTED_RANK, RanksManager.COOP_RANK};

        // Show header:
        user.sendMessage("commands.island.team.info.header",
                "[max]", String.valueOf(bentoBox.getIslands().getMaxMembers(island, RanksManager.MEMBER_RANK)),
                "[total]", String.valueOf(island.getMemberSet().size()),
                "[online]", String.valueOf(onlineMembers.size()));

        // We now need to get all online "members" of the island - incl. Trusted and coop
        onlineMembers = island.getMemberSet(RanksManager.COOP_RANK).stream()
                .filter(uuid -> Util.getOnlinePlayerList(user).contains(Bukkit.getOfflinePlayer(uuid).getName())).collect(Collectors.toList());

        for (int rank : ranks) {
            Set<UUID> players = island.getMemberSet(rank, false);
            if (!players.isEmpty()) {
                if (rank == RanksManager.OWNER_RANK) {
                    // Slightly special handling for the owner rank
                    user.sendMessage("commands.island.team.info.rank-layout.owner",
                            TextVariables.RANK, user.getTranslation(RanksManager.OWNER_RANK_REF));
                } else {
                    user.sendMessage("commands.island.team.info.rank-layout.generic",
                            TextVariables.RANK, user.getTranslation(bentoBox.getRanksManager().getRank(rank)),
                            TextVariables.NUMBER, String.valueOf(island.getMemberSet(rank, false).size()));
                }
                for (UUID member : island.getMemberSet(rank, false)) {
                    OfflinePlayer offlineMember = Bukkit.getOfflinePlayer(member);
                    if (onlineMembers.contains(member)) {
                        // the player is online
                        user.sendMessage("commands.island.team.info.member-layout.online",
                                TextVariables.NAME, offlineMember.getName());
                    } else {
                        // A bit of handling for the last joined date
                        Instant lastJoined = Instant.ofEpochMilli(offlineMember.getLastPlayed());
                        Instant now = Instant.now();

                        Duration duration = Duration.between(lastJoined, now);
                        String lastSeen;
                        final String reference = "commands.island.team.info.last-seen.layout";
                        if (duration.toMinutes() < 60L) {
                            lastSeen = user.getTranslation(reference,
                                    TextVariables.NUMBER, String.valueOf(duration.toMinutes()),
                                    TextVariables.UNIT, user.getTranslation("commands.island.team.info.last-seen.minutes"));
                        } else if (duration.toHours() < 24L) {
                            lastSeen = user.getTranslation(reference,
                                    TextVariables.NUMBER, String.valueOf(duration.toHours()),
                                    TextVariables.UNIT, user.getTranslation("commands.island.team.info.last-seen.hours"));
                        } else {
                            lastSeen = user.getTranslation(reference,
                                    TextVariables.NUMBER, String.valueOf(duration.toDays()),
                                    TextVariables.UNIT, user.getTranslation("commands.island.team.info.last-seen.days"));
                        }

                        if (island.getMemberSet(RanksManager.MEMBER_RANK, true).contains(member)) {
                            user.sendMessage("commands.island.team.info.member-layout.offline",
                                    TextVariables.NAME, offlineMember.getName(),
                                    "[last_seen]", lastSeen);
                        } else {
                            // This will prevent anyone that is trusted or below to not have a last-seen status
                            user.sendMessage("commands.island.team.info.member-layout.offline-not-last-seen",
                                    TextVariables.NAME, offlineMember.getName());
                        }
                    }
                }
            }
        }
    }

    private String creationDate(long time) {
        Date date = new Date(time);
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        dateFormat.setTimeZone(TimeZone.getTimeZone("est"));
        return dateFormat.format(date);
    }


    private ItemStack mainCell(Island island, Player player) {
        final ItemStack cell = new ItemStack(SkullCreator.itemFromUuid(player.getUniqueId()));
        final ItemMeta cellItemMeta = cell.getItemMeta();
        List<String> lore = new ArrayList<>();
        lore.add("");
        if (island.getName() != null) {
            lore.add(ChatColor.GRAY + "Name: " + ChatColor.GREEN + island.getName());
        } else {
            lore.add(ChatColor.GRAY + "Name: " + ChatColor.GREEN + "Not Set!");
        }
        lore.add(ChatColor.GRAY + "Created: " + ChatColor.GREEN + creationDate(island.getCreatedDate()));
        lore.add(ChatColor.GRAY + "Visitors: " + ChatColor.GREEN + island.getVisitors().size());
        lore.add(ChatColor.GRAY + "Likes: " + ChatColor.GREEN + PlaceholderAPI.setPlaceholders(player, "%bskyblock_likes_addon_island_likes_count%"));
        if (island.getRank(player.getUniqueId()) >= RanksManager.OWNER_RANK) {
            lore.add(" ");
            lore.add(PREFIX + ChatColor.YELLOW + "Left-Click to Rename Cell");
        }

        cellItemMeta.setDisplayName(ChatColor.GOLD + player.getName() + "'s" + ChatColor.GREEN + " Cell");
        cell.setItemMeta(cellItemMeta);
        cell.setLore(lore);
        return cell;
    }

    final ItemStack coop = new ItemStack(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjFlOTMxZGRjMTk3OTMxOTFlM2JiMzgxYzg0ZTZlZDY5NTVjZDkzNTM2YmE3YmUxMDhkNzg2NGUzOWFkYmRlIn19fQ=="));
    final ItemMeta coopItemMeta = coop.getItemMeta();

    private ItemStack coopMembers(Island island) {
        List<String> lore = new ArrayList<>();
        lore.add("");
        for (UUID playerUUID : island.getMemberSet())
            if (island.getRank(playerUUID) >= 200) {
                lore.add(PREFIX + ChatColor.DARK_GREEN + Bukkit.getOfflinePlayer(playerUUID).getName());
            }
        coopItemMeta.setDisplayName(ChatColor.GREEN + "Coop Members " + ChatColor.YELLOW + island.getMemberSet(RanksManager.COOP_RANK).size());
        coop.setItemMeta(coopItemMeta);
        coop.setLore(lore);
        return coop;
    }

    final ItemStack trusted = new ItemStack(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWZkZWY2OTI5ZWJhZjM5NGJjZTJlZTdlYTJhZGJiYjVjODNkN2NlMTdlM2I4NjE1YTkyOGFlZmFiZjg1YiJ9fX0="));
    final ItemMeta trustedItemMeta = trusted.getItemMeta();

    private ItemStack trustedMembers(Island island) {
        List<String> lore = new ArrayList<>();
        lore.add("");
        for (UUID playerUUID : island.getMemberSet())
            if (island.getRank(playerUUID) >= RanksManager.TRUSTED_RANK)
                lore.add(PREFIX + ChatColor.DARK_AQUA + Bukkit.getOfflinePlayer(playerUUID).getName());
        trustedItemMeta.setDisplayName(ChatColor.GREEN + "Trusted Members " + ChatColor.YELLOW + island.getMemberSet(RanksManager.TRUSTED_RANK).size());
        trusted.setItemMeta(trustedItemMeta);
        trusted.setLore(lore);
        return trusted;
    }

    final ItemStack member = new ItemStack(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmZiM2RiMTAzMjg1ZTkyNTg4OTg0Yjg3MWQ4ZjViMTljODlmNGUyOTVjOTkyNjA4MDhhNjBlNDNmOGJkM2QifX19"));
    final ItemMeta memberItemMeta = member.getItemMeta();

    private ItemStack memberMembers(Island island) {
        List<String> lore = new ArrayList<>();
        lore.add("");
        for (UUID playerUUID : island.getMemberSet())
            if (island.getRank(playerUUID) >= RanksManager.MEMBER_RANK)
                lore.add(PREFIX + ChatColor.AQUA + Bukkit.getOfflinePlayer(playerUUID).getName());
        memberItemMeta.setDisplayName(ChatColor.GREEN + "Members " + ChatColor.YELLOW + island.getMemberSet(RanksManager.MEMBER_RANK).size());
        member.setItemMeta(memberItemMeta);
        member.setLore(lore);
        return member;
    }

    final ItemStack subOwner = new ItemStack(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmM0ODg2ZWYzNjJiMmM4MjNhNmFhNjUyNDFjNWM3ZGU3MWM5NGQ4ZWM1ODIyYzUxZTk2OTc2NjQxZjUzZWEzNSJ9fX0="));
    final ItemMeta subOwnerItemMeta = subOwner.getItemMeta();

    private ItemStack subOwners(Island island) {
        List<String> lore = new ArrayList<>();
        lore.add("");
        for (UUID playerUUID : island.getMemberSet())
            if (island.getRank(playerUUID) >= RanksManager.SUB_OWNER_RANK)
                lore.add(PREFIX + ChatColor.GOLD + Bukkit.getOfflinePlayer(playerUUID).getName());
        subOwnerItemMeta.setDisplayName(ChatColor.GREEN + "Sub-Owners " + ChatColor.YELLOW + island.getMemberSet(RanksManager.SUB_OWNER_RANK).size());
        subOwner.setItemMeta(subOwnerItemMeta);
        subOwner.setLore(lore);
        return subOwner;
    }

    final ItemStack owner = new ItemStack(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWZkZTNiZmNlMmQ4Y2I3MjRkZTg1NTZlNWVjMjFiN2YxNWY1ODQ2ODRhYjc4NTIxNGFkZDE2NGJlNzYyNGIifX19"));
    final ItemMeta ownerItemMeta = owner.getItemMeta();

    private ItemStack owners(Island island) {
        List<String> lore = new ArrayList<>();
        lore.add("");
        for (UUID playerUUID : island.getMemberSet())
            if (island.getRank(playerUUID) >= RanksManager.OWNER_RANK)
                lore.add(PREFIX + ChatColor.RED + Bukkit.getOfflinePlayer(playerUUID).getName());
        ownerItemMeta.setDisplayName(ChatColor.GREEN + "Owners " + ChatColor.YELLOW + island.getMemberSet(RanksManager.OWNER_RANK).size());
        owner.setItemMeta(ownerItemMeta);
        owner.setLore(lore);
        return owner;
    }

    final ItemStack ranking = new ItemStack(Material.LADDER);
    final ItemMeta rankingItemMeta = ranking.getItemMeta();

    private ItemStack ranking(Island island, Player player) {
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "Each Cell has its own Ranking system");
        lore.add(ChatColor.GRAY + "to restrict what each player is");
        lore.add(ChatColor.GRAY + "capable of doing in your Cell!");
        lore.add("");
        lore.add(ChatColor.WHITE.toString() + ChatColor.BOLD + "Ranks:");
        lore.add(PREFIX + COOP + ChatColor.DARK_GRAY + " (Temporary)");
        lore.add(PREFIX + TRUSTED);
        lore.add(PREFIX + MEMBER);
        lore.add(PREFIX + SUBOWNER);
        lore.add(PREFIX + OWNER);
        if (island.getRank(player.getUniqueId()) >= RanksManager.OWNER_RANK) {
            lore.add(" ");
            lore.add(PREFIX + ChatColor.YELLOW + "Click to View Settings");
        }
        rankingItemMeta.setDisplayName(ChatColor.GREEN + "Member Ranking System");
        ranking.setItemMeta(rankingItemMeta);
        ranking.setLore(lore);
        return ranking;
    }

    final ItemStack invite = new ItemStack(Material.TOTEM_OF_UNDYING);
    final ItemMeta inviteItemMeta = invite.getItemMeta();

    private ItemStack invite(Island island, Player player) {
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "You can use:");
        lore.add(PREFIX + ChatColor.YELLOW + "/Cell Team Coop <Player>");
        lore.add("");
        lore.add(ChatColor.GRAY + "To " + ChatColor.GREEN + "temporarily " + ChatColor.GRAY + "increase the");
        lore.add(ChatColor.GRAY + "privileges of another " + ChatColor.GREEN + "Prisoner" + ChatColor.GRAY + "!");
        lore.add(ChatColor.YELLOW + "Privileges " + ChatColor.DARK_GRAY + "are revoked upon");
        lore.add(ChatColor.DARK_GRAY + "the " + ChatColor.RED + "Owner " + ChatColor.DARK_GRAY + "logging out!");
        lore.add("");
        lore.add(ChatColor.GRAY + "You can use:");
        lore.add(PREFIX + ChatColor.YELLOW + "/Cell Team Trust <Player>");
        lore.add("");
        lore.add(ChatColor.GRAY + "To give the Player the privileges");
        lore.add(ChatColor.GRAY + "of a " + TRUSTED + ChatColor.GRAY + " Member of your Cell!");
        lore.add("");
        lore.add(ChatColor.GRAY + "You can use:");
        lore.add(PREFIX + ChatColor.YELLOW + "/Cell Team Invite <Player>");
        lore.add("");
        lore.add(ChatColor.GRAY + "To invite the Player to");
        lore.add(ChatColor.GRAY + "your Cell, which will");
        lore.add(ChatColor.GRAY + "make them a " + MEMBER + ChatColor.GRAY + "!");
        lore.add("");
        lore.add(ChatColor.DARK_RED.toString() + ChatColor.BOLD + "WARNING: " + ChatColor.RED + "You should verify your");
        lore.add(ChatColor.RED + "Cell Settings before trusting, cooping,");
        lore.add(ChatColor.RED + "or inviting another player!");
        if (island.getRank(player.getUniqueId()) >= RanksManager.OWNER_RANK) {
            lore.add(" ");
            lore.add(PREFIX + ChatColor.YELLOW + "Click to View Settings");
        }

        inviteItemMeta.setDisplayName(ChatColor.GREEN + "Invite Members");
        invite.setItemMeta(inviteItemMeta);
        invite.setLore(lore);
        return invite;
    }

    final ItemStack promote = new ItemStack(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzA0MGZlODM2YTZjMmZiZDJjN2E5YzhlYzZiZTUxNzRmZGRmMWFjMjBmNTVlMzY2MTU2ZmE1ZjcxMmUxMCJ9fX0="));
    final ItemMeta promoteItemMeta = promote.getItemMeta();

    private ItemStack promote() {
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.GRAY + "You can use:");
        lore.add(PREFIX + ChatColor.YELLOW + "/Cell Team Demote <Player>");
        lore.add(PREFIX + ChatColor.YELLOW + "/Cell Team Promote <Player>");
        lore.add("");
        lore.add(ChatColor.GRAY + "To increase or decrease");
        lore.add(ChatColor.GRAY + "the Rank of a " + MEMBER);
        lore.add(ChatColor.GRAY + "currently in your Cell!");
        lore.add("");
        lore.add(ChatColor.GRAY + "You can use:");
        lore.add(PREFIX + ChatColor.YELLOW + "/Cell Team Uncoop <Player>");
        lore.add(PREFIX + ChatColor.YELLOW + "/Cell Team Untrust <Player>");
        lore.add("");
        lore.add(ChatColor.GRAY + "To remove a " + COOP + ChatColor.GRAY + " or a ");
        lore.add(TRUSTED + ChatColor.GRAY + " member from your Cell!");

        promoteItemMeta.setDisplayName(ChatColor.GREEN + "Promote & Demote Members");
        promote.setItemMeta(promoteItemMeta);
        promote.setLore(lore);
        return promote;
    }

    private final MetadataKey<Boolean> ISLAND_TO_RENAME = MetadataKey.createBooleanKey("CELL-NAME");

    private void changeName(Island island, Player player) {
        if (island.getRank(player.getUniqueId()) == RanksManager.OWNER_RANK) {
            player.closeInventory();
            player.sendMessage(ChatColor.GREEN + "Type the new name of your Cell in chat: ");
            soundManager.playSound(player, Sound.ENTITY_ARROW_HIT, 1, 1);
            Metadata.provideForPlayer(player).put(ISLAND_TO_RENAME, true);

            Events.subscribe(AsyncPlayerChatEvent.class, EventPriority.LOWEST)
                    .filter(EventFilters.playerHasMetadata(ISLAND_TO_RENAME))
                    .expireAfter(1)
                    .handler(e -> {
                        Metadata.provideForPlayer(player).remove(ISLAND_TO_RENAME);
                        island.setName(e.getMessage());
                        for (UUID memberUUID : island.getMemberSet(RanksManager.COOP_RANK)) {
                            final Player member = Bukkit.getPlayer(memberUUID);
                            if (member != null) {
                                member.sendMessage(ChatColor.GREEN + "The Cell name has been changed to " + ChatColor.YELLOW + e.getMessage() + ChatColor.GREEN + ", by " + ChatColor.GOLD + player.getName() + ChatColor.GREEN + "!");
                                soundManager.soundYes(member, 1);
                            }
                        }
                        e.setCancelled(true);
                    });
            Schedulers.sync().runLater(new BukkitRunnable() {
                @Override
                public void run() {
                    if (Metadata.getForPlayer(player).get().has(ISLAND_TO_RENAME)) {
                        Metadata.provideForPlayer(player).remove(ISLAND_TO_RENAME);
                        player.sendMessage(ChatColor.RED + "You took too long to set the name!");
                        soundManager.soundNo(player, 1);
                    }
                }
            }, 100);
        }
    }

}

