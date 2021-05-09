package monzter.adventurescraft.plugin.utilities.general;

import org.bukkit.Server;

public class ConsoleCommandImpl implements ConsoleCommand {
    private final Server server;

    public ConsoleCommandImpl(Server server) {
        this.server = server;
    }

    @Override
    public void consoleCommand(String command) {
        server.dispatchCommand(server.getConsoleSender(), command);
    }
}
