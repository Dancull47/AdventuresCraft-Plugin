package monzter.adventurescraft.plugin.mySQL;

import monzter.adventurescraft.plugin.AdventuresCraft;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;

public class SQLGetter {
    AdventuresCraft plugin;
    MySQL SQL;
    public SQLGetter(AdventuresCraft plugin, MySQL mySQL) {
        this.plugin = plugin;
        this.SQL = mySQL;
    }

    public void createPlayer(Player player, String pointType, long pointAmount) {
        try {
//            if (!exists(player.getUniqueId(), pointType)) {
                PreparedStatement ps2 = plugin.SQL.getConnection().prepareStatement("INSERT INTO ac_Points (name,uuid,pointType,pointAmount) VALUES (?,?,?,?) " +
                        "ON DUPLICATE KEY UPDATE pointAmount=pointAmount+?;");
                ps2.setString(1, player.getName());
                ps2.setString(2, player.getUniqueId().toString());
                ps2.setString(3, pointType);
                ps2.setLong(4, pointAmount);
                ps2.setLong(5, pointAmount);
                ps2.executeUpdate();
                return;
//            }
        } catch (SQLException e) {
            plugin.getLogger().log(Level.SEVERE, ChatColor.RED + "Failed to create Player!", e);
        }
    }

//    public boolean exists(UUID uuid, String pointType) {
//        try {
//            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT pointType FROM ac_Points WHERE uuid=? AND pointType=?");
//            ps.setString(1, pointType);
//            ps.setString(2, uuid.toString());
//            ps.setString(3, pointType);
//            ResultSet results = ps.executeQuery();
//            if (results.next()) {
//                return true;
//            } else {
//                return false;
//            }
//        } catch (SQLException e) {
//            plugin.getLogger().log(Level.SEVERE, ChatColor.RED + "Failed to check if Exists!", e);
//            return false;
//        }
//    }

    public void setPointAmount(UUID uuid, String pointType, long pointAmount) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE ac_Points SET pointAmount=? WHERE uuid=? AND pointType=?");
            ps.setLong(1, pointAmount + getPointAmount(uuid, pointType));
            ps.setString(2, uuid.toString());
            ps.setString(3, pointType);
            ps.executeUpdate();
        } catch (SQLException e) {
            plugin.getLogger().log(Level.SEVERE, ChatColor.RED + "Failed to set Point Amount!", e);
        }
    }

    public long getPointAmount(UUID uuid, String pointType) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT pointAmount FROM ac_Points WHERE uuid=? AND pointType=?");
            ps.setString(1, uuid.toString());
            ps.setString(2, pointType);
            ResultSet rs = ps.executeQuery();
            long point = 0;
            if (rs.next()) {
                point = rs.getLong("pointAmount");
                return point;
            }
        } catch (SQLException e) {
            plugin.getLogger().log(Level.SEVERE, ChatColor.RED + "Failed to get Point Amount!", e);
        }
        return 0;
    }

    private static final String INSERT = "INSERT INTO ac_Points (name,uuid,pointType,pointAmount) VALUES (?,?,?,?) " +
            "ON DUPLICATE KEY UPDATE pointAmount=pointAmount+?;";
    private static final String SELECT = "SELECT pointAmount FROM ac_Points WHERE uuid=? AND pointType=?";
    private static final String SAVE = "UPDATE ac_Points SET pointAmount=? WHERE uuid=? AND pointType=?";

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

    public void loadPlayer(Player player, String pointType, long pointAmount) {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, new Runnable() {
            @Override
            public void run() {
                try (Connection connection = SQL.getConnection();
                     PreparedStatement insert = connection.prepareStatement(INSERT);
                     PreparedStatement select = connection.prepareStatement(SELECT)) {
                    insert.setString(1, player.getName());
                    insert.setString(2, player.getUniqueId().toString());
                    insert.setString(3, pointType);
                    insert.setLong(4, pointAmount);
                    insert.setLong(5, pointAmount);
                    insert.execute();

                    select.setString(1, player.getUniqueId().toString());
                    select.setString(2, pointType);
                    ResultSet result = select.executeQuery();
                    if (result.next())
                        addPlayer(player, result.getInt("pointAmount"));
                    result.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void savePlayer(Player player, String pointType, long pointAmount) {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, new Runnable() {
            @Override
            public void run() {
                try (Connection connection = SQL.getConnection();
                     PreparedStatement statement = connection.prepareStatement(SAVE)){
                    statement.setLong(1, pointAmount + getPointAmount(player.getUniqueId(), pointType));
                    statement.setString(2, player.getUniqueId().toString());
                    statement.setString(3, pointType);
                    statement.executeUpdate();

                    removePlayer(player);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }


}

