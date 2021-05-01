package monzter.adventurescraft.plugin.utilities;

import org.bukkit.Server;

public class BukkitConsoleCommand implements ConsoleCommand {
    private final Server server;

    public BukkitConsoleCommand(Server server) {
        this.server = server;
    }

    @Override
    public void consoleCommand(String command) {
        server.dispatchCommand(server.getConsoleSender(), command);
    }
}
