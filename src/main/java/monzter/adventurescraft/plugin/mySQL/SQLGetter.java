package monzter.adventurescraft.plugin.mySQL;

import monzter.adventurescraft.plugin.AdventuresCraft;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import java.util.logging.Level;

public class SQLGetter {
    AdventuresCraft plugin;

    public SQLGetter(AdventuresCraft plugin) {
        this.plugin = plugin;
    }

    public void createTable() {
        PreparedStatement ps;
        try {
            ps = plugin.SQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS ac_daily_login "
                    + "(name VARCHAR(100),uuid VARCHAR(100),pointType VARCHAR(100),pointAmount BIGINT(100),PRIMARY KEY (name));");
            ps.executeUpdate();
        } catch (SQLException e) {
            plugin.getLogger().log(Level.SEVERE, ChatColor.RED + "Failed to create Table!", e);
        }
    }

    public void createPlayer(Player player, String pointType, long pointAmount) {
        try {
            if (!exists(player.getUniqueId(), pointType)) {
                PreparedStatement ps2 = plugin.SQL.getConnection().prepareStatement("INSERT INTO ac_daily_login (name,uuid,pointType,pointAmount) VALUES (?,?,?,?);");
                ps2.setString(1, player.getName());
                ps2.setString(2, player.getUniqueId().toString());
                ps2.setString(3, pointType);
                ps2.setLong(4, pointAmount);
                ps2.executeUpdate();
                return;
            }
        } catch (SQLException e) {
            plugin.getLogger().log(Level.SEVERE, ChatColor.RED + "Failed to create Player!", e);
        }
    }

    public boolean exists(UUID uuid, String pointType) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT pointType FROM ac_daily_login WHERE uuid=? AND pointType=?");
            ps.setString(1, pointType);
            ps.setString(2, uuid.toString());
            ps.setString(3, pointType);
            ResultSet results = ps.executeQuery();
            if (results.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            plugin.getLogger().log(Level.SEVERE, ChatColor.RED + "Failed to check if Exists!", e);
            return false;
        }
    }

    public void setPointAmount(UUID uuid, String pointType, long pointAmount) {
        try {
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("UPDATE ac_daily_login SET pointAmount=? WHERE uuid=? AND pointType=?");
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
            PreparedStatement ps = plugin.SQL.getConnection().prepareStatement("SELECT pointAmount FROM ac_daily_login WHERE uuid=? AND pointType=?");
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
}
