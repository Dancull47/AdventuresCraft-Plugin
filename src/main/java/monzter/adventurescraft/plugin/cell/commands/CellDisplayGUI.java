package monzter.adventurescraft.plugin.cell.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Dependency;
import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.PaginatedPane;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import dev.dbassett.skullcreator.SkullCreator;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import net.kyori.adventure.text.Component;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
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

public class CellDisplayGUI extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final SoundManager soundManager;
    private final BentoBox bentoBox;

    private final String prefix = ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "» ";

    public CellDisplayGUI(AdventuresCraft plugin, SoundManager soundManager, BentoBox bentoBox) {
        this.plugin = plugin;
        this.soundManager = soundManager;
        this.bentoBox = bentoBox;
    }

    private final ItemStack backgroundItem = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
    private final ItemMeta backgroundItemMeta = backgroundItem.getItemMeta();
    private final ItemStack previousPageItem = new ItemStack(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODY1MmUyYjkzNmNhODAyNmJkMjg2NTFkN2M5ZjI4MTlkMmU5MjM2OTc3MzRkMThkZmRiMTM1NTBmOGZkYWQ1ZiJ9fX0="));
    private final ItemMeta previousPageItemMeta = previousPageItem.getItemMeta();
    private final ItemStack nextPageItem = new ItemStack(SkullCreator.itemFromBase64("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmEzYjhmNjgxZGFhZDhiZjQzNmNhZThkYTNmZTgxMzFmNjJhMTYyYWI4MWFmNjM5YzNlMDY0NGFhNmFiYWMyZiJ9fX0="));
    private final ItemMeta nextPageItemMeta = nextPageItem.getItemMeta();

    private final ItemStack cell = new ItemStack(Material.IRON_BARS);
    final ItemMeta cellItemMeta = cell.getItemMeta();

    @CommandAlias("cellManager")
    public void hatchCommand(Player player) {
        Island island = bentoBox.getIslands().getIsland(Bukkit.getWorld("Cell_world"), player.getUniqueId());

        backgroundItemMeta.displayName(Component.text(" "));
        previousPageItemMeta.displayName(Component.text(ChatColor.GREEN + "Previous Page"));
        nextPageItemMeta.displayName(Component.text(ChatColor.GREEN + "Next Page"));
        backgroundItem.setItemMeta(backgroundItemMeta);

        ChestGui gui = new ChestGui(6, "Cell Manager");
        gui.setOnGlobalClick(event -> event.setCancelled(true));

        PaginatedPane page = new PaginatedPane(0, 0, 9, 6);
        OutlinePane background = new OutlinePane(0, 0, 9, 6, Pane.Priority.LOWEST);
        OutlinePane display = new OutlinePane(1, 1, 7, 4, Pane.Priority.LOW);

        page.addPane(0, background);
        page.addPane(0, display);

        background.addItem(new GuiItem(backgroundItem));
        background.setRepeat(true);

        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.WHITE + "Created: " + ChatColor.GREEN + creationDate(island.getCreatedDate()));
        if (island.getName() != null) {
            lore.add(ChatColor.WHITE + "Name: " + ChatColor.GREEN + island.getName());
        } else {
            lore.add(ChatColor.WHITE + "Name: " + ChatColor.GREEN + "Not Set!");
        }
//        + ChatColor.GREEN + island.getMembers().size()
        lore.add(ChatColor.WHITE + "Members: ");
        for (UUID playerUUID : island.getMemberSet()) {
            switch (island.getRank(playerUUID)){
                case 1000:
                    lore.add(ChatColor.GREEN + Bukkit.getPlayer(playerUUID).getName() + ChatColor.RED + " Owner");
                    break;
                case 900:
                    lore.add(ChatColor.GREEN + Bukkit.getPlayer(playerUUID).getName() + ChatColor.GOLD + " Sub-Owner");
                    break;
                case 500:
                    lore.add(ChatColor.GREEN + Bukkit.getPlayer(playerUUID).getName() + ChatColor.GREEN + " Member");
                    break;
            }
        }
        lore.add("");
        lore.add(prefix + ChatColor.YELLOW + "Left-Click to Decrease Allowance");
        lore.add(prefix + ChatColor.YELLOW + "Right-Click to Increase Allowance");

        cellItemMeta.setDisplayName(ChatColor.GOLD + player.getName() + "'s" + ChatColor.GREEN + " Cell");
        cell.setItemMeta(cellItemMeta);
        cell.setLore(lore);

        display.addItem(new GuiItem((cell)));
        gui.addPane(page);
        gui.show(player);
    }

    @CommandAlias("cellTestCommand")
    public void cell(Player player) {
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

    private GuiItem main(Island island, Player player){
        return null;
    }
}

