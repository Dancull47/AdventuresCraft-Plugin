package monzter.adventurescraft.plugin.utilities.MySQL;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import me.vagdedes.mysql.basic.Config;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.sql.*;

public class MySQL {
    private static Connection con;
    private static HikariConfig dbConfig;

    public MySQL() {
    }

    static {
        dbConfig = new HikariConfig();
        dbConfig.setJdbcUrl("jdbc:mysql://localhost:3306/" + SQLConfig.getDatabase());
        dbConfig.setUsername(SQLConfig.getUser());
        dbConfig.setPassword(SQLConfig.getPassword());
        dbConfig.setDriverClassName("com.mysql.jdbc.Driver");
        dbConfig.addDataSourceProperty("cachePrepStmts", "true");
        dbConfig.addDataSourceProperty("prepStmtCacheSize", "250");
        dbConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
    }

    private static HikariDataSource ds = new HikariDataSource(dbConfig);

    public static <T> T execute(ConnectionCallback<T> callback) {
        try (Connection conn = ds.getConnection()) {
            return callback.doInConnection(conn);
        } catch (SQLException e) {
            throw new IllegalStateException("Error during execution.", e);
        }
    }

    public static interface ConnectionCallback<T> {
        public T doInConnection(Connection conn) throws SQLException;
    }

//    private void updateData(String sql, String[] sqlValues) {
//        Bukkit.getScheduler().runTaskAsynchronously(AdventuresCraft.plugin, () -> {
//                MySQL.execute( (conn) -> {
//                    PreparedStatement q = conn.prepareStatement(sql);
//                    q.setString(1, sqlValues[0]);
//                    q.setString(2, sqlValues[1]);
//                    System.out.println(q);
//                    q.executeUpdate();
//                }} );
//        };
//    }


















    public static Connection getConnection() {
        return con;
    }

    public static void setConnection(String host, String user, String password, String database, String port) {
        if (host != null && user != null && password != null && database != null) {
            disconnect(false);

            try {
                String driver = Config.getDriver();
                if (driver.length() == 0) {
                    Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Config Error: Driver is blank");
                } else {
                    con = DriverManager.getConnection("jdbc:" + driver + "://" + host + ":" + port + "/" + database + "?autoReconnect=true&useSSL=" + Config.getSSL(), user, password);
                    Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "SQL connected.");
                }
            } catch (Exception var6) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "SQL Connect Error: " + var6.getMessage());
            }

        }
    }

    public static void connect() {
        connect(true);
    }

    private static void connect(boolean message) {
        String host = SQLConfig.getHost();
        String user = SQLConfig.getUser();
        String password = SQLConfig.getPassword();
        String database = SQLConfig.getDatabase();
        String port = SQLConfig.getPort();
        if (isConnected()) {
            if (message) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "SQL Connect Error: Already connected");
            }
        } else if (host.length() == 0) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Config Error: Host is blank");
        } else if (user.length() == 0) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Config Error: User is blank");
        } else if (password.length() == 0) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Config Error: Password is blank");
        } else if (database.length() == 0) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Config Error: Database is blank");
        } else if (port.length() == 0) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Config Error: Port is blank");
        } else {
            setConnection(host, user, password, database, port);
        }

    }

    public static void disconnect() {
        disconnect(true);
    }

    private static void disconnect(boolean message) {
        try {
            if (isConnected()) {
                con.close();
                if (message) {
                    Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "SQL disconnected.");
                }
            } else if (message) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "SQL Disconnect Error: No existing connection");
            }
        } catch (Exception var2) {
            if (message) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "SQL Disconnect Error: " + var2.getMessage());
            }
        }

        con = null;
    }

    public static void reconnect() {
        disconnect();
        connect();
    }

    public static boolean isConnected() {
        if (con != null) {
            try {
                return !con.isClosed();
            } catch (Exception var1) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "SQL Connection:");
                Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Error: " + var1.getMessage());
            }
        }

        return false;
    }

    public static boolean update(String command) {
        if (command == null) {
            return false;
        } else {
            boolean result = false;
            connect(false);

            try {
                Statement st = getConnection().createStatement();
                st.executeUpdate(command);
                st.close();
                result = true;
            } catch (Exception var4) {
                String message = var4.getMessage();
                if (message != null) {
                    Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "SQL Update:");
                    Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Command: " + command);
                    Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Error: " + message);
                }
            }

            disconnect(false);
            return result;
        }
    }

    public static ResultSet query(String command) {
        if (command == null) {
            return null;
        } else {
            connect(false);
            ResultSet rs = null;

            try {
                Statement st = getConnection().createStatement();
                rs = st.executeQuery(command);
            } catch (Exception var4) {
                String message = var4.getMessage();
                if (message != null) {
                    Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "SQL Query:");
                    Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Command: " + command);
                    Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Error: " + message);
                }
            }

            return rs;
        }
    }
}
