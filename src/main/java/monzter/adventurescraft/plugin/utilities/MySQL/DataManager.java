package monzter.adventurescraft.plugin.utilities.MySQL;

import java.sql.*;
import java.util.ArrayList;

/**
 * Handler for everything to do with the database.
 * All queries except searching goes through this class.
 *
 * @author oliverw92
 */

public class DataManager implements AutoCloseable {

    private ConnectionManager connectionManager;


    /**
     * Initiates database connection pool, checks tables, starts cleansing utility
     * Throws an exception if it is unable to complete setup
     *
     * @throws Exception
     */
    public DataManager() throws Exception {

        connectionManager = new ConnectionManager();

        //Check tables and update player/world lists
        createTables();

    }

    private void createTables() throws Exception {

        try (Connection conn = connectionManager.getConnection();
             Statement stmnt = conn.createStatement()) {

            String playerTable = "CREATE TABLE IF NOT EXISTS `" + Config.DbPlayerTable + "` (" +
                    "`player_id` SMALLINT(6) UNSIGNED NOT NULL AUTO_INCREMENT, " +
                    "`player` varchar(40) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL, " +
                    "PRIMARY KEY (`player_id`), " +
                    "UNIQUE KEY `player` (`player`)" +
                    ") COLLATE latin1_general_ci, ENGINE = INNODB;";

            String worldTable = "CREATE TABLE IF NOT EXISTS `" + Config.DbWorldTable + "` (" +
                    "`world_id` TINYINT(3) UNSIGNED NOT NULL AUTO_INCREMENT, " +
                    "`world` varchar(40) CHARACTER SET latin1 COLLATE latin1_general_ci NOT NULL, " +
                    "PRIMARY KEY (`world_id`), " +
                    "UNIQUE KEY `world` (`world`)" +
                    ") COLLATE latin1_general_ci, ENGINE = INNODB;";

            String dataTable = "CREATE TABLE `" + Config.DbHawkEyeTable + "` (" +
                    "`data_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT," +
                    "`timestamp` datetime NOT NULL," +
                    "`player_id` SMALLINT(6) UNSIGNED NOT NULL," +
                    "`action` TINYINT(3) UNSIGNED NOT NULL," +
                    "`world_id` TINYINT(3) UNSIGNED NOT NULL," +
                    "`x` int(11) NOT NULL," +
                    "`y` SMALLINT(6) NOT NULL," +
                    "`z` int(11) NOT NULL," +
                    "`data` varchar(500) CHARACTER SET latin1 COLLATE latin1_general_ci DEFAULT NULL," +
                    "PRIMARY KEY (`data_id`)," +
                    "KEY `timestamp` (`timestamp`)," +
                    "KEY `player` (`player_id`)," +
                    "KEY `action` (`action`)," +
                    "KEY `world_id` (`world_id`)," +
                    "KEY `x_y_z` (`x`,`y`,`z`)" +
                    ") COLLATE latin1_general_ci, ENGINE = INNODB;";

            DatabaseMetaData dbm = conn.getMetaData();

            //Check if tables exist
//            if (!JDBCUtil.tableExists(dbm, Config.DbPlayerTable)) {
//                Util.info("Table `" + Config.DbPlayerTable + "` not found, creating...");
//                stmnt.execute(playerTable);
//            }
        }
    }

    public boolean tableExists(String table) throws SQLException {
        try (
                Connection connection = connectionManager.getConnection();
                DatabaseMetaData metadata = connection.getMetaData();
            if(metadata ==null) {
            return false;
        }

        ResultSet rs = metadata.getTables((String) null, (String) null, table, (String[]) null);
        if (rs.next()) {
            return true;
        }
    } catch(
    Exception var4)

    {
    }

        return false;
}

    public static boolean insertData(String columns, String values, String table) {
        return MySQL.update("INSERT INTO " + table + " (" + columns + ") VALUES (" + values + ");");
    }

    public static boolean deleteData(String column, String logic_gate, String data, String table) {
        if (data != null) {
            data = "'" + data + "'";
        }

        return MySQL.update("DELETE FROM " + table + " WHERE " + column + logic_gate + data + ";");
    }

    public static boolean exists(String column, String data, String table) {
        if (data != null) {
            data = "'" + data + "'";
        }

        try {
            ResultSet rs = MySQL.query("SELECT * FROM " + table + " WHERE " + column + "=" + data + ";");
            if (rs.next()) {
                return true;
            }
        } catch (Exception var4) {
        }

        return false;
    }

    public static boolean deleteTable(String table) {
        return MySQL.update("DROP TABLE " + table + ";");
    }

    public static boolean truncateTable(String table) {
        return MySQL.update("TRUNCATE TABLE " + table + ";");
    }

    public static boolean createTable(String table, String columns) {
        return MySQL.update("CREATE TABLE IF NOT EXISTS " + table + " (" + columns + ");");
    }

    public static boolean upsert(String selected, Object object, String column, String data, String table) {
        if (object != null) {
            object = "'" + object + "'";
        }

        if (data != null) {
            data = "'" + data + "'";
        }

        try {
            ResultSet rs = MySQL.query("SELECT * FROM " + table + " WHERE " + column + "=" + data + ";");
            if (rs.next()) {
                MySQL.update("UPDATE " + table + " SET " + selected + "=" + object + " WHERE " + column + "=" + data + ";");
            } else {
                insertData(column + ", " + selected, data + ", " + object, table);
            }
        } catch (Exception var6) {
        }

        return false;
    }

    public static boolean set(String selected, Object object, String column, String logic_gate, String data, String table) {
        if (object != null) {
            object = "'" + object + "'";
        }

        if (data != null) {
            data = "'" + data + "'";
        }

        return MySQL.update("UPDATE " + table + " SET " + selected + "=" + object + " WHERE " + column + logic_gate + data + ";");
    }

    public static boolean set(String selected, Object object, String[] where_arguments, String table) {
        String arguments = "";
        String[] var5 = where_arguments;
        int var6 = where_arguments.length;

        for (int var7 = 0; var7 < var6; ++var7) {
            String argument = var5[var7];
            arguments = arguments + argument + " AND ";
        }

        if (arguments.length() <= 5) {
            return false;
        } else {
            arguments = arguments.substring(0, arguments.length() - 5);
            if (object != null) {
                object = "'" + object + "'";
            }

            return MySQL.update("UPDATE " + table + " SET " + selected + "=" + object + " WHERE " + arguments + ";");
        }
    }

    public static Object get(String selected, String[] where_arguments, String table) {
        String arguments = "";
        String[] var4 = where_arguments;
        int var5 = where_arguments.length;

        for (int var6 = 0; var6 < var5; ++var6) {
            String argument = var4[var6];
            arguments = arguments + argument + " AND ";
        }

        if (arguments.length() <= 5) {
            return false;
        } else {
            arguments = arguments.substring(0, arguments.length() - 5);

            try {
                ResultSet rs = MySQL.query("SELECT * FROM " + table + " WHERE " + arguments + ";");
                if (rs.next()) {
                    return rs.getObject(selected);
                }
            } catch (Exception var8) {
            }

            return null;
        }
    }

    public static ArrayList<Object> listGet(String selected, String[] where_arguments, String table) {
        ArrayList<Object> array = new ArrayList();
        String arguments = "";
        String[] var5 = where_arguments;
        int var6 = where_arguments.length;

        for (int var7 = 0; var7 < var6; ++var7) {
            String argument = var5[var7];
            arguments = arguments + argument + " AND ";
        }

        if (arguments.length() <= 5) {
            return array;
        } else {
            arguments = arguments.substring(0, arguments.length() - 5);

            try {
                ResultSet rs = MySQL.query("SELECT * FROM " + table + " WHERE " + arguments + ";");

                while (rs.next()) {
                    array.add(rs.getObject(selected));
                }
            } catch (Exception var9) {
            }

            return array;
        }
    }

    public static Object get(String selected, String column, String logic_gate, String data, String table) {
        if (data != null) {
            data = "'" + data + "'";
        }

        try {
            ResultSet rs = MySQL.query("SELECT * FROM " + table + " WHERE " + column + logic_gate + data + ";");
            if (rs.next()) {
                return rs.getObject(selected);
            }
        } catch (Exception var6) {
        }

        return null;
    }

    public static ArrayList<Object> listGet(String selected, String column, String logic_gate, String data, String table) {
        ArrayList<Object> array = new ArrayList();
        if (data != null) {
            data = "'" + data + "'";
        }

        try {
            ResultSet rs = MySQL.query("SELECT * FROM " + table + " WHERE " + column + logic_gate + data + ";");

            while (rs.next()) {
                array.add(rs.getObject(selected));
            }
        } catch (Exception var7) {
        }

        return array;
    }

    public static int countRows(String table) {
        int i = 0;
        if (table == null) {
            return i;
        } else {
            ResultSet rs = MySQL.query("SELECT * FROM " + table + ";");

            try {
                while (rs.next()) {
                    ++i;
                }
            } catch (Exception var4) {
            }

            return i;
        }
    }

    @Override
    public void close() throws Exception {

    }
}