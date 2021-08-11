package monzter.adventurescraft.plugin.network.PrisonGamemode.shared.mysql;

import me.vagdedes.mysql.database.SQL;
import monzter.adventurescraft.plugin.AdventuresCraft;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SQLSetup {
    private final AdventuresCraft plugin;
    private final Connection connection;

    public SQLSetup(AdventuresCraft plugin, Connection connection) {
        this.plugin = plugin;
        this.connection = connection;
    }

    public void createVoteTable() {
        SQL.createTable("ac_votes", "uuid CHAR (36) NOT NULL, vote_site VARCHAR (100), last_vote TIMESTAMP, PRIMARY KEY (vote_site)");
    }

    //    public void createPlayer(Player player) throws SQLException {
//        UUID uuid = player.getUniqueId();
//        if (!SQL.exists("uuid", uuid.toString(), "ac_votes")) {
//            PreparedStatement ps2 = MySQL.getConnection().prepareStatement("INSERT IGNORE INFO ac_votes (uuid,vote_site,last_vote) VALUES (?,MC-VOTE,0)");
//            ps2.setString(1, uuid.toString());
//            ps2.executeUpdate();
//
//        }
//    }
    public void createPlayer(Player player, String site) {
        SQL.set("last_vote", Timestamp.valueOf(LocalDateTime.now()), new String[]{"uuid = " + player.getUniqueId(), "vote_site = " + site}, "ac_votes");
    }

    //    public void addVote(Player player, String site) throws SQLException {
//        PreparedStatement stat = connection.prepareStatement("INSERT INTO ac_votes(uuid,vote_site,last_vote) VALUES (?,?,?)");
//        stat.setString(1, player.getUniqueId().toString());
//        stat.setString(2, site);
//        stat.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
//        stat.executeQuery();
//    }
//    public void setVote(Player player, String site) throws SQLException {
//        PreparedStatement stat = connection.prepareStatement("UPDATE ac_votes SET last_vote = ? WHERE uuid = ? AND vote_site = ?");
//        stat.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
//        stat.setString(2, player.getUniqueId().toString());
//        stat.setString(3, site);
//        stat.executeQuery();
//    }
    public Timestamp getVote(Player player, String site) {
        return (Timestamp) SQL.get("last_vote", new String[]{"uuid = " + player.getUniqueId(), "vote_site = " + site}, "ac_votes");
    }

    private static final String INSERT = "INSERT INTO Coins VALUES(?,?,?) ON DUPLICATE KEY UPDATE name=?";
    private static final String SELECT = "SELECT coins FROM Coins WHERE uuid=?";
    private static final String SAVE = "UPDATE Coins SET coins=? WHERE uuid=?";

    private Map<UUID, Integer> coins;

    public void Coins() {
        this.coins = new HashMap<>();
    }

    public void addPlayer(Player p, int amount) {
        coins.put(p.getUniqueId(), amount);
    }

    public void removePlayer(Player p) {
        coins.remove(p.getUniqueId());
    }

    public int getCoins(Player p) {
        return coins.get(p.getUniqueId());
    }

    public void setCoins(Player p, int amount) {
        coins.put(p.getUniqueId(), amount);
    }

    public void addCoins(Player p, int coins) {
        setCoins(p, getCoins(p) + coins);
    }

    public void removeCoins(Player p, int coins) {
        setCoins(p, getCoins(p) - coins);
    }


    public void loadPlayer(Player p) {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            try (Connection connection = (Connection) plugin.getHikari();
                 PreparedStatement insert = connection.prepareStatement(INSERT);
                 PreparedStatement select = connection.prepareStatement(SELECT)) {
                insert.setString(1, p.getUniqueId().toString());
                insert.setString(2, p.getName());
                insert.setInt(3, 0);
                insert.setString(4, p.getName());
                insert.execute();

                select.setString(1, p.getUniqueId().toString());
                ResultSet result = select.executeQuery();
                if (result.next())
                    addPlayer(p, result.getInt("coins"));
                result.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public void savePlayer(Player p) {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
                try (Connection connection = (Connection) plugin.getHikari();
                     PreparedStatement statement = connection.prepareStatement(SAVE)){
                    statement.setInt(1, getCoins(p));
                    statement.setString(2, p.getUniqueId().toString());
                    statement.execute();
                    removePlayer(p);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        });
    }


}
