package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.MythicMobRegisters.Conditions;

import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
import io.lumine.xikage.mythicmobs.skills.SkillCondition;
import io.lumine.xikage.mythicmobs.skills.conditions.IEntityCondition;
import monzter.adventurescraft.plugin.utilities.beton.BetonTagManager;
import org.bukkit.Bukkit;

public class BQTagCondition extends SkillCondition implements IEntityCondition {

    final String bqPackage;
    final String tag;
    private final BetonTagManager betonTagManager;


    public BQTagCondition(MythicLineConfig config, BetonTagManager betonTagManager) {
        super(config.getLine());

        this.bqPackage = config.getString(new String[]{"package", "p"}, "default");
        this.tag = config.getString(new String[]{"tag", "t"});
        this.betonTagManager = betonTagManager;
    }

    @Override
    public boolean check(AbstractEntity target) {
        if (Bukkit.getPlayer(target.asPlayer().getUniqueId()) == null) return false;
        return betonTagManager.hasTag(Bukkit.getPlayer(target.asPlayer().getUniqueId()), bqPackage + "." + tag);
    }
}
