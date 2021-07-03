package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.Events;

import io.lumine.xikage.mythicmobs.MythicMobs;
import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Mount extends SkillMechanic implements ITargetedEntitySkill {

    public Mount(MythicLineConfig config) {
        super(config.getLine(), config);
        this.setAsyncSafe(false);
    }

    @Override
    public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
        if (Bukkit.getPlayer(MythicMobs.inst().getMobManager().getActiveMob(data.getCaster().getEntity().getUniqueId()).get().getParent().getEntity().getUniqueId()) != null) {
            Player player = Bukkit.getPlayer(MythicMobs.inst().getMobManager().getActiveMob(data.getCaster().getEntity().getUniqueId()).get().getParent().getEntity().getUniqueId());
            if (player.getVehicle() == null || !player.isOnline()) {
                MythicMobs.inst().getMobManager().getActiveMob(data.getCaster().getEntity().getUniqueId()).get().getEntity().remove();
                return true;
            }
            return false;
        }
        return false;
    }
}