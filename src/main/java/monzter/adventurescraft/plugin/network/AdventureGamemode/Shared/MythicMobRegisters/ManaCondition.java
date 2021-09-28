package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.MythicMobRegisters;

import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
import io.lumine.xikage.mythicmobs.skills.SkillCondition;
import io.lumine.xikage.mythicmobs.skills.conditions.IEntityCondition;
import org.bukkit.entity.LivingEntity;

public class ManaCondition extends SkillCondition implements IEntityCondition {

    final double manaAmount;
    final long cooldownSeconds;
    final String skillName;

    public ManaCondition(MythicLineConfig config) {
        super(config.getLine());
        this.manaAmount = config.getInteger(new String[]{"mana", "m"}, 0);
        this.cooldownSeconds = config.getInteger(new String[]{"cooldown", "cd", "c"}, 1);
        this.skillName = config.getString(new String[]{"skill", "skillName", "s", "n", "name"}, "DEFAULT");
    }

    @Override
    public boolean check(AbstractEntity target) {
        LivingEntity bukkitTarget = (LivingEntity) BukkitAdapter.adapt(target);
        System.out.println("MANA");
        return false;
    }
}
