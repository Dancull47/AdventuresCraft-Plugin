package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.MythicMobRegisters.Conditions;

import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
import io.lumine.xikage.mythicmobs.skills.SkillCondition;
import io.lumine.xikage.mythicmobs.skills.conditions.IEntityCondition;
import monzter.adventurescraft.plugin.utilities.general.Cooldown;

import java.util.UUID;

public class Enchantress extends SkillCondition implements IEntityCondition {

    public Enchantress(MythicLineConfig config) {
        super(config.getLine());
    }

    @Override
    public boolean check(AbstractEntity target) {
        return !Cooldown.isInCooldown(UUID.fromString("123e4567-e89b-12d3-a456-556642440000"), "VOID_ENCHANTRESS");
    }

}
