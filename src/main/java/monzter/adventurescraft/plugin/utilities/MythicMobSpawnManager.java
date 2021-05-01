package monzter.adventurescraft.plugin.utilities;

import io.lumine.xikage.mythicmobs.MythicMobs;
import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
import io.lumine.xikage.mythicmobs.mobs.MythicMob;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class MythicMobSpawnManager implements MythicMobsSpawn {

    @Override
    public void spawnMob(Location location, String mobName) {
        MythicMob mob = MythicMobs.inst().getMobManager().getMythicMob(mobName);
        mob.spawn(BukkitAdapter.adapt(location), 0).getEntity().getBukkitEntity();
    }
}
