package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.MythicMobRegisters.Mechanics;


import com.gmail.berndivader.mythicmobsext.Main;
import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
import io.lumine.xikage.mythicmobs.skills.SkillCaster;
import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
import net.Indyuce.mmoitems.ItemStats;
import net.Indyuce.mmoitems.api.player.PlayerData;
import net.Indyuce.mmoitems.api.player.RPGPlayer;
import net.Indyuce.mmoitems.comp.mmocore.MMOCoreHook;
import org.bukkit.entity.LivingEntity;
import org.bukkit.metadata.FixedMetadataValue;

public class LogSetMechanic extends SkillMechanic implements ITargetedEntitySkill {

    public LogSetMechanic(String skill, MythicLineConfig config) {
        super(skill, config);
        this.threadSafetyLevel = ThreadSafetyLevel.SYNC_ONLY;
    }

    @Override
    public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
        if (target.isDead() || target.getHealth() <= 0) return false;
        RPGPlayer rpgPlayer = new MMOCoreHook.MMOCoreRPGPlayer(PlayerData.get(data.getCaster().getEntity().getUniqueId()));
        double damage;
        double maxHP = rpgPlayer.getPlayerData().getStats().getStat(ItemStats.MAX_HEALTH);
        double armor = rpgPlayer.getPlayerData().getStats().getStat(ItemStats.DEFENSE);
        double skillDamage = rpgPlayer.getPlayerData().getStats().getStat(ItemStats.SKILL_DAMAGE);

        damage = maxHP + armor;

        if (skillDamage != 0)
            damage = damage * skillDamage;

        doDamage(data.getCaster(), target, damage, false, true, false);
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