package monzter.adventurescraft.plugin.mySQL;

import com.zaxxer.hikari.HikariDataSource;
import monzter.adventurescraft.plugin.AdventuresCraft;
import org.bukkit.ChatColor;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;

public class MySQL {
    AdventuresCraft plugin;
    private Connection connection;
    String host = ("localhost");
    String port = ("3306");
    String database = ("minecraft_prison");
    String user = ("minecraft");
    String password = ("kWz7Y8eK0tMCUvr0Cm");
    HikariDataSource hikari;

    public MySQL(AdventuresCraft plugin) {
        this.plugin = plugin;
    }

    public boolean isConnected() {
        return (connection == null ? false : true);
    }

    public void connect() {
        if (!isConnected()) {
            hikari = new HikariDataSource();
            hikari.setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
            hikari.addDataSourceProperty("serverName", host);
            hikari.addDataSourceProperty("port", port);
            hikari.addDataSourceProperty("databaseName", database);
            hikari.addDataSourceProperty("user", user);
            hikari.addDataSourceProperty("password", password);
            plugin.getLogger().info(ChatColor.GREEN + "MySQL connection established!");
            createTable();
        }
    }

    public void disconnect() {
        if (hikari != null){
            hikari.close();
            plugin.getLogger().log(Level.SEVERE, ChatColor.GREEN + "Successfully disconnected from MySQL!");
        }
    }

    public Connection getConnection() throws SQLException {
        return hikari.getConnection();
    }

    public void createTable() {
        try (Connection connection = hikari.getConnection();
             Statement statement = connection.createStatement();) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS ac_Points "
                    + "(name VARCHAR(36),uuid VARCHAR(36),pointType VARCHAR(100),pointAmount BIGINT(100), CONSTRAINT ac_Points PRIMARY KEY (uuid, pointType));");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS ac_Votes "
                    + "(name VARCHAR(36),uuid VARCHAR(36),voteWebsite VARCHAR(100),voteTime BIGINT(100), CONSTRAINT ac_Votes PRIMARY KEY (uuid, voteWebsite));");
        } catch (SQLException e) {
            plugin.getLogger().log(Level.SEVERE, ChatColor.RED + "Failed to create Table!", e);
        }
    }

}
