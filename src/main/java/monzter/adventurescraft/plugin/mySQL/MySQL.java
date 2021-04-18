package monzter.adventurescraft.plugin.mySQL;

import monzter.adventurescraft.plugin.AdventuresCraft;
import org.bukkit.ChatColor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;

public class MySQL {
    AdventuresCraft plugin;
    private Connection connection;
    String host = ("localhost");
    String port = ("3306");
    String database = ("minecraft_prison");
    String user = ("minecraft");
    String password = ("kWz7Y8eK0tMCUvr0Cm");

    public MySQL(AdventuresCraft plugin) {
        this.plugin = plugin;
    }

    public boolean isConnected() {
        return (connection == null ? false : true);
    }

    public void connect() {
        if (!isConnected()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                String url = "jdbc:mysql://" + host + ":" + port + "/" + database;
                connection = DriverManager.getConnection(url, user, password);
                plugin.getLogger().info(ChatColor.GREEN + "MySQL connection established!");
                plugin.data.createTable();
            } catch (ClassNotFoundException | SQLException e) {
                plugin.getLogger().log(Level.SEVERE, ChatColor.RED + "Failed establishing connection to MySQL!", e);
            }
        }
    }

    public void disconnect() {
        if (isConnected()) {
            try {
                connection.close();
                plugin.getLogger().log(Level.SEVERE, ChatColor.GREEN + "Successfully disconnected from MySQL!");
            } catch (SQLException e) {
                plugin.getLogger().log(Level.SEVERE, ChatColor.RED + "Failed to properly disconnect from MySQL!", e);
            }
        }
    }

    public Connection getConnection(){
        return connection;
    }
}
