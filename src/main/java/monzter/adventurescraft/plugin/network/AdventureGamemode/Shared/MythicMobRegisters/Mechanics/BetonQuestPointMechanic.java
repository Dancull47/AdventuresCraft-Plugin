package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.MythicMobRegisters.Mechanics;


import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
import monzter.adventurescraft.plugin.utilities.beton.BetonPointsManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class BetonQuestPointMechanic extends SkillMechanic implements ITargetedEntitySkill {
    private final BetonPointsManager betonPointsManager;
    String bqPackage;
    int amount;
    String math;


    public BetonQuestPointMechanic(String skill, MythicLineConfig config, BetonPointsManager betonPointsManager) {
        super(skill, config);
        this.betonPointsManager = betonPointsManager;
        this.bqPackage = config.getString(new String[]{"p", "package"});
        this.amount = config.getInteger(new String[]{"a", "amount"}, 1);
        this.math = config.getString(new String[]{"m", "math"}, "+");

        this.threadSafetyLevel = ThreadSafetyLevel.SYNC_ONLY;
    }

    @Override
    public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
        if (target.isDead() || target.getHealth() <= 0) return false;
        Player player = Bukkit.getPlayer(target.asPlayer().getUniqueId());
        switch (math) {
            case "+":
            case "add":
                betonPointsManager.givePoint(player, bqPackage, amount);
                break;
            case "-":
            case "minute":
            case "subtract":
                betonPointsManager.givePoint(player, bqPackage, -amount);
                break;
        }
        return true;
    }
}