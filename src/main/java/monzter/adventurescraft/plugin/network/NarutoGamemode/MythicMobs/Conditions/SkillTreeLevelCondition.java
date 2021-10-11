package monzter.adventurescraft.plugin.network.NarutoGamemode.MythicMobs.Conditions;

import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
import io.lumine.xikage.mythicmobs.skills.SkillCaster;
import io.lumine.xikage.mythicmobs.skills.SkillCondition;
import io.lumine.xikage.mythicmobs.skills.conditions.ICasterCondition;
import monzter.adventurescraft.plugin.utilities.beton.BetonPointsManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class SkillTreeLevelCondition extends SkillCondition implements ICasterCondition {

    private String skillTree;
    private int level;
    private final BetonPointsManager betonPointsManager;


    public SkillTreeLevelCondition(MythicLineConfig config, BetonPointsManager betonPointsManager) {
        super(config.getLine());

        this.skillTree = config.getString(new String[]{"skillTree", "tree"});
        this.level = config.getInteger(new String[]{"l", "level"});
        this.betonPointsManager = betonPointsManager;
    }

    @Override
    public boolean check(SkillCaster skillCaster) {
//        Player parent = Bukkit.getPlayer(MythicMobs.inst().getMobManager().getMythicMobInstance(skillCaster.getEntity()).getParent().getEntity().getUniqueId());
        Player parent = Bukkit.getPlayer(skillCaster.getEntity().getUniqueId()).getPlayer();

        int points = betonPointsManager.getPoints(parent, "SKILLTREE." + skillTree);
        return points == level;
    }
}
