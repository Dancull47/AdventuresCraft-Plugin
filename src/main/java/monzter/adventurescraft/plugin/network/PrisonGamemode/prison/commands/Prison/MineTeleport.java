package monzter.adventurescraft.plugin.network.PrisonGamemode.prison.commands.Prison;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Dependency;
import monzter.adventurescraft.plugin.AdventuresCraft;
import org.bukkit.entity.Player;

public class MineTeleport extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;

    public MineTeleport(AdventuresCraft plugin) {
        this.plugin = plugin;
    }

    //  The idea with the p's is to have a Prestige mine every 10 or so Prestiges
    String ranks = "p10,p1,z,y,x,w,v,u,t,s,r,q,p,o,n,m,l,k,j,l,h,g,f,e,d,c,b,a";

    @CommandAlias("mine")
    public void mineCommand(Player player) {
        for (String rank : ranks.split(","))
            if (player.hasPermission("mines.tp." + rank))
                player.performCommand("warp mine" + rank);
    }

}

