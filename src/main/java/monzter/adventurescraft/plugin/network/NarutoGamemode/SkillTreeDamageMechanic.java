package monzter.adventurescraft.plugin.network.NarutoGamemode;


import com.gmail.berndivader.mythicmobsext.Main;
import io.lumine.xikage.mythicmobs.MythicMobs;
import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
import io.lumine.xikage.mythicmobs.skills.SkillCaster;
import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
import monzter.adventurescraft.plugin.utilities.beton.BetonPointsManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

public class SkillTreeDamageMechanic extends SkillMechanic implements ITargetedEntitySkill {
    private double damage;
    private final String tree;
    boolean ia;
    boolean pk;
    boolean pi;
private final BetonPointsManager betonPointsManager;


    public SkillTreeDamageMechanic(String skill, MythicLineConfig config, BetonPointsManager betonPointsManager) {
        super(skill, config);
        this.betonPointsManager = betonPointsManager;
        this.threadSafetyLevel = ThreadSafetyLevel.SYNC_ONLY;
        this.damage = config.getDouble(new String[]{"damage", "d"}, 5);
        this.tree = config.getString(new String[]{"t", "tree"});
        this.ia = config.getBoolean(new String[]{"ignorearmor", "ignorearmour", "ia", "i"}, false);
        this.pk = config.getBoolean(new String[]{"preventknockback", "pkb", "pk"}, false);
        this.pi = config.getBoolean(new String[]{"preventimmunity", "pi"}, false);
    }

    @Override
    public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
        if (target.isDead() || target.getHealth() <= 0) return false;
        Player parent = Bukkit.getPlayer(MythicMobs.inst().getMobManager().getMythicMobInstance(data.getCaster().getEntity()).getParent().getEntity().getUniqueId());
        int points = betonPointsManager.getPoints(parent, tree);

        doDamage(data.getCaster(), target, damage, this.ia, this.pk, this.pi);
        return true;
    }

    public void doDamage(SkillCaster am, AbstractEntity t, double damage, boolean ignorearmor, boolean preventKnockback, boolean preventImmunity) {
        LivingEntity target;
        am.setUsingDamageSkill(true);
        if (am instanceof ActiveMob)
            ((ActiveMob) am).setLastDamageSkillAmount(damage);
        LivingEntity source = (LivingEntity) BukkitAdapter.adapt(am.getEntity());
        target = (LivingEntity) BukkitAdapter.adapt(t);
        target.setMetadata("IgnoreArmor", new FixedMetadataValue(Main.getPlugin(), ignorearmor));
        target.setMetadata("PreventKnockback", new FixedMetadataValue(Main.getPlugin(), preventKnockback));
        if (Double.isNaN(damage))
            damage = 0.001D;
        Math.round(damage);
        target.setMetadata("DamageAmount", new FixedMetadataValue(Main.getPlugin(), damage));
        target.damage(damage, source);
        if (preventImmunity)
            target.setNoDamageTicks(0);
        am.setUsingDamageSkill(false);
    }

}


//public class ModifierDamageMechanic extends DamagingMechanic implements ITargetedEntitySkill {
//
//    private final double baseDamage;
//    private final String modifiers;
//    private final String math;
//
//
////    Issue: Caster is not working
//
//    public ModifierDamageMechanic(String mechanicName, MythicLineConfig config) {
//        super(mechanicName, config);
//
//        this.baseDamage = config.getDouble(new String[]{"damage", "d"}, 100);
//        this.modifiers = config.getString(new String[]{"m", "modifier", "modifiers"}, "attack_damage");
//        this.math = config.getString(new String[]{"math"}, "multiplicative");
//    }
//
//    @Override
//    public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
//        RPGPlayer rpgPlayer = new MMOCoreHook.MMOCoreRPGPlayer(PlayerData.get(data.getCaster().getEntity().getUniqueId()));
//
//        double damage = this.baseDamage;
//
//        String[] modifiers = this.modifiers.split(",");
//        for (String modifier : modifiers) {
//            double modifierValue = 0;
//            switch (modifier.toUpperCase()) {
//                case "ATTACK_DAMAGE":
//                    modifierValue = rpgPlayer.getPlayerData().getStats().getStat(ItemStats.ATTACK_DAMAGE);
//                    break;
//                case "MAX_MANA":
//                    modifierValue = rpgPlayer.getPlayerData().getStats().getStat(ItemStats.MAX_MANA);
//                    break;
//                case "MAGIC_DAMAGE":
//                    modifierValue = rpgPlayer.getPlayerData().getStats().getStat(ItemStats.MAGIC_DAMAGE);
//                    break;
//                case "SKILL_DAMAGE":
//                    modifierValue = rpgPlayer.getPlayerData().getStats().getStat(ItemStats.SKILL_DAMAGE);
//                    break;
//            }
//            if (modifierValue != 0 && this.math.equals("multiplicative"))
//                damage = damage * modifierValue;
//            else if (modifierValue != 0 && this.math.equals("additive"))
//                damage = damage + modifierValue;
//        }
//        super.doDamage(data.getCaster(), target, this.baseDamage);
//        return true;
//    }
//
//}
