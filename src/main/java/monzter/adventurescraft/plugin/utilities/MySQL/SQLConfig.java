package monzter.adventurescraft.plugin.utilities.MySQL;


import monzter.adventurescraft.plugin.AdventuresCraft;

public class SQLConfig {
    private static final String host = "host";
    private static final String user = "user";
    private static final String password = "password";
    private static final String database = "database";
    private static final String port = "port";
    private static final String driver = "driver";
    private static final String ssl = "use_SSL";

    public SQLConfig() {
    }

    public static void clear() {
        set("host", "", false);
        set("user", "", false);
        set("password", "", false);
        set("database", "", false);
        set("port", "3306", false);
        set("driver", "mysql", false);
        set("use_SSL", true, false);
    }

    public static void create() {
        set("host", "", true);
        set("user", "", true);
        set("password", "", true);
        set("database", "", true);
        set("port", "3306", true);
        set("driver", "mysql", true);
        set("use_SSL", true, true);
    }


    public static void setHost(String s) {
        if (!getHost().equalsIgnoreCase(s)) {
            set("host", s, false);
        }

    }

    public static void setUser(String s) {
        if (!getUser().equalsIgnoreCase(s)) {
            set("user", s, false);
        }

    }

    public static void setPassword(String s) {
        if (!getPassword().equalsIgnoreCase(s)) {
            set("password", s, false);
        }

    }

    public static void setDatabase(String s) {
        if (!getDatabase().equalsIgnoreCase(s)) {
            set("database", s, false);
        }

    }

    public static void setPort(String s) {
        if (!getPort().equalsIgnoreCase(s)) {
            set("port", s, false);
        }

    }

    public static void setDriver(String s) {
        if (!getDriver().equalsIgnoreCase(s)) {
            set("driver", s, false);
        }

    }

    public static void setSSL(boolean b) {
        if (getSSL() != b) {
            set("use_SSL", b, false);
        }

    }

    public static String getHost() {
        return get("host");
    }

    public static String getUser() {
        return get("user");
    }

    public static String getPassword() {
        return get("password");
    }

    public static String getDatabase() {
        return get("database");
    }

    public static String getPort() {
        return get("port");
    }

    public static String getDriver() {
        return get("driver");
    }

    public static boolean getSSL() {
        return getBoolean("use_SSL");
    }

    private static void set(String name, Object value, boolean checkIfExists) {
        if (name != null && value != null && (!checkIfExists || !AdventuresCraft.plugin.getConfig().contains(name))) {
            AdventuresCraft.plugin.getConfig().set(name, value);
            AdventuresCraft.plugin.saveConfig();
        }
    }

    private static String get(String name) {
        return name != null && AdventuresCraft.plugin.getConfig().contains(name) ? AdventuresCraft.plugin.getConfig().getString(name) : "";
    }

    private static boolean getBoolean(String name) {
        return name != null && AdventuresCraft.plugin.getConfig().contains(name) && AdventuresCraft.plugin.getConfig().getBoolean(name);
    }

}
