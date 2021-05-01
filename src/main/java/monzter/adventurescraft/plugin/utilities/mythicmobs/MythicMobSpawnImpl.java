package monzter.adventurescraft.plugin.utilities.mythicmobs;

import io.lumine.xikage.mythicmobs.MythicMobs;
import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
import io.lumine.xikage.mythicmobs.mobs.MythicMob;
import org.bukkit.Location;

public class MythicMobSpawnImpl implements MythicMobsSpawn {

    @Override
    public void spawnMob(Location location, String mobName) {
        MythicMob mob = MythicMobs.inst().getMobManager().getMythicMob(mobName);
        mob.spawn(BukkitAdapter.adapt(location), 0).getEntity().getBukkitEntity();
    }
}
