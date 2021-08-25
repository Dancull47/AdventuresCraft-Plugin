package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.MythicMobRegisters;

import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
import io.lumine.xikage.mythicmobs.skills.SkillCondition;
import io.lumine.xikage.mythicmobs.skills.conditions.IEntityCondition;
import org.bukkit.entity.LivingEntity;

public class LeashCondition extends SkillCondition implements IEntityCondition {

    public LeashCondition(MythicLineConfig config) {
        super(config.getLine());
    }

    @Override
    public boolean check(AbstractEntity target) {
        LivingEntity bukkitTarget = (LivingEntity) BukkitAdapter.adapt(target);
        return bukkitTarget.isLeashed();
    }
}
