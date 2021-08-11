package monzter.adventurescraft.plugin.utilities.MySQL;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import monzter.adventurescraft.plugin.AdventuresCraft;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionManager implements AutoCloseable {
    /**
     * Controls MySQL connection pool using Hikari
     *
     * @author bob7l
     */
    private HikariDataSource connectionPool;

    public ConnectionManager() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");

        AdventuresCraft.plugin.getLogger().info("Attempting to connecting to database...");

        HikariConfig config = new HikariConfig();

        config.setMaximumPoolSize(10);

        config.setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");

        config.setUsername(SQLConfig.getUser());
        config.setPassword(SQLConfig.getPassword());

        config.addDataSourceProperty("serverName", SQLConfig.getHost());
        config.addDataSourceProperty("databaseName", SQLConfig.getDatabase());
        config.addDataSourceProperty("port", SQLConfig.getPort());

        config.addDataSourceProperty("rewriteBatchedStatements", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "275");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.addDataSourceProperty("cachePrepStmts", "true");

        config.setAutoCommit(false);

        connectionPool = new HikariDataSource(config);
    }

    @Override
    public void close() throws Exception {
        if (connectionPool != null) {
            connectionPool.close();
        }
    }

    public HikariDataSource getConnectionPool() {
        return connectionPool;
    }

    public Connection getConnection() throws SQLException {
        return connectionPool.getConnection();
    }

}