package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.MythicMobRegisters.Mechanics;


import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
import io.lumine.xikage.mythicmobs.skills.mechanics.DamageMechanic;
import net.Indyuce.mmoitems.ItemStats;
import net.Indyuce.mmoitems.api.player.PlayerData;
import net.Indyuce.mmoitems.api.player.RPGPlayer;
import net.Indyuce.mmoitems.comp.mmocore.MMOCoreHook;

public class ModifierDamageMechanic extends DamageMechanic implements ITargetedEntitySkill {

    private final double baseDamage;
    private final String modifiers;
    private final String math;


//    Issue: Caster is not working

    public ModifierDamageMechanic(String mechanicName, MythicLineConfig config) {
        super(mechanicName, config);

        this.baseDamage = config.getDouble(new String[]{"damage", "d"}, 100);
        this.modifiers = config.getString(new String[]{"m", "modifier", "modifiers"}, "attack_damage");
        this.math = config.getString(new String[]{"math"}, "multiplicative");
    }

    @Override
    public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
        RPGPlayer rpgPlayer = new MMOCoreHook.MMOCoreRPGPlayer(PlayerData.get(data.getCaster().getEntity().getUniqueId()));

        double damage = this.baseDamage;

        String[] modifiers = this.modifiers.split(",");
        for (String modifier : modifiers) {
            double modifierValue = 0;
            switch (modifier.toUpperCase()) {
                case "ATTACK_DAMAGE":
                    modifierValue = rpgPlayer.getPlayerData().getStats().getStat(ItemStats.ATTACK_DAMAGE);
                    break;
                case "MAX_MANA":
                    modifierValue = rpgPlayer.getPlayerData().getStats().getStat(ItemStats.MAX_MANA);
                    break;
                case "MAGIC_DAMAGE":
                    modifierValue = rpgPlayer.getPlayerData().getStats().getStat(ItemStats.MAGIC_DAMAGE);
                    break;
                case "SKILL_DAMAGE":
                    modifierValue = rpgPlayer.getPlayerData().getStats().getStat(ItemStats.SKILL_DAMAGE);
                    break;
            }
            if (modifierValue != 0 && this.math.equals("multiplicative"))
                damage = damage * modifierValue;
            else if (modifierValue != 0 && this.math.equals("additive"))
                damage = damage + modifierValue;
        }
        super.doDamage(data.getCaster(), target, this.baseDamage);
        return true;
    }

}

//    public ModifierDamageMechanic(MythicLineConfig config) {
//        super(config.getLine(), config);
//        this.threadSafetyLevel = AbstractSkill.ThreadSafetyLevel.SYNC_ONLY;
//
//        this.baseDamage = config.getDouble(new String[]{"damage", "d"}, 100);
//        this.modifiers = config.getString(new String[]{"m", "modifier", "modifiers"}, "attack_damage");
//        this.math = config.getString(new String[]{"math"}, "multiplicative");
//    }
//
//    @Override
//    public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
//        if (!target.isValid() || target.isDead() || target.getHealth() <= 0.0 || data.getCaster().isUsingDamageSkill())
//            return false;
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
//
//        doDamage(data.getCaster(), target, damage);
//        return true;
//    }

//public class ModifierDamageMechanic extends DamagingMechanic implements ITargetedEntitySkill {
//
//    protected final double baseDamage;
//    protected final String modifiers;
//    protected final String math;
//
//    public ModifierDamageMechanic(String line, MythicLineConfig config) {
//        super(line, config);
//        this.threadSafetyLevel = AbstractSkill.ThreadSafetyLevel.SYNC_ONLY;
//
//        this.baseDamage = config.getDouble(new String[]{"damage", "d"}, 100);
//        this.modifiers = config.getString(new String[]{"m", "modifier", "modifiers"}, "attack_damage");
//        this.math = config.getString(new String[]{"math"}, "multiplicative");
//
////        this.pk = config.getBoolean(new String[]{"preventknockback", "pkb", "pk"}, false);
////        this.ia = config.getBoolean(new String[]{"ignorearmor", "ignorearmour", "ia", "i"}, false);
////        this.pi = config.getBoolean(new String[]{"preventimmunity", "pi"}, false);
////        this.p = config.getBoolean(new String[]{"percentage", "p"}, false);
//
//    }
//
//    @Override
//    public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
//        if (!target.isDead() && target.getBukkitEntity() instanceof LivingEntity && !data.getCaster().isUsingDamageSkill() && (!target.isLiving() || !(target.getHealth() <= 0.0D))) {
//
//            double damage = baseDamage.get(data, target) * data.getPower();
//
//            StatMap.CachedStatMap statMap = data.getVariables().has("MMOStatMap") ? (StatMap.CachedStatMap) data.getVariables().get("MMOStatMap").get()
//                    : MMOPlayerData.get(data.getCaster().getEntity().getUniqueId()).getStatMap().cache(EquipmentSlot.MAIN_HAND);
//            AttackMetadata attackMeta = new AttackMetadata(new DamageMetadata(damage, types), statMap);
//
//            // Cooler damage types yeah
//            MythicLib.plugin.getDamage().damage(attackMeta, (LivingEntity) target.getBukkitEntity(), !this.preventKnockback, this.preventImmunity);
//
//            MythicLogger.debug(MythicLogger.DebugLevel.MECHANIC, "+ MMODamageMechanic fired for {0} with {1} power", new Object[]{damage, data.getPower()});
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//}


//Most working ish

//public class ModifierDamageMechanic extends DamagingMechanic implements ITargetedEntitySkill {
//
//    private final double baseDamage;
//    private final String modifiers;
//    private final String math;
//
//    boolean pk;
//    boolean pi;
//    boolean ia;
//    boolean iabs;
//    boolean p;
//
//
//    public ModifierDamageMechanic(MythicLineConfig config) {
//        super(config.getLine(), config);
//        this.threadSafetyLevel = AbstractSkill.ThreadSafetyLevel.SYNC_ONLY;
//
//        this.baseDamage = config.getDouble(new String[]{"damage", "d"}, 100);
//        this.modifiers = config.getString(new String[]{"m", "modifier", "modifiers"}, "attack_damage");
//        this.math = config.getString(new String[]{"math"}, "multiplicative");
//
//        this.pk = config.getBoolean(new String[]{"preventknockback", "pkb", "pk"}, false);
//        this.ia = config.getBoolean(new String[]{"ignorearmor", "ignorearmour", "ia", "i"}, false);
//        this.pi = config.getBoolean(new String[]{"preventimmunity", "pi"}, false);
//        this.p = config.getBoolean(new String[]{"percentage", "p"}, false);
//
//    }
//    @Override
//    public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
//        if (!target.isValid() || target.isDead() || target.getHealth() <= 0.0 || data.getCaster().isUsingDamageSkill())
//            return false;
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
//
//        damage(data.getCaster(), target, damage, this.ia, this.pk, this.pi);
//        return true;
//    }
//}

// Final attempt at extending DamagingMechanic
//@MythicMechanic(
//        author = "Monzter",
//        name = "ModifierDamageMechanic",
//        aliases = {"mdm"},
//        description = "Deals damage to the target (compatible with MMO)"
//)
//
//public class ModifierDamageMechanic extends DamagingMechanic implements ITargetedEntitySkill {
//
//    private final double amount;
//
//
//    public ModifierDamageMechanic(String line, MythicLineConfig config) {
//        super(line, config);
//
//        this.amount = config.getDouble(new String[]{"damage", "d"}, 100);
//    }
//
//    @Override
//    public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
//        if (!target.isDead() && target.getBukkitEntity() instanceof LivingEntity && !data.getCaster().isUsingDamageSkill() && (!target.isLiving() || !(target.getHealth() <= 0.0D))) {
//
//
//            StatMap.CachedStatMap statMap = data.getVariables().has("MMOStatMap") ? (StatMap.CachedStatMap) data.getVariables().get("MMOStatMap").get()
//                    : MMOPlayerData.get(data.getCaster().getEntity().getUniqueId()).getStatMap().cache(EquipmentSlot.MAIN_HAND);
//            AttackMetadata attackMeta = new AttackMetadata(new DamageMetadata(amount), statMap);
//
//            // Cooler damage types yeah
//            MythicLib.plugin.getDamage().damage(attackMeta, (LivingEntity) target.getBukkitEntity(), !this.preventKnockback, this.preventImmunity);
//
//            MythicLogger.debug(MythicLogger.DebugLevel.MECHANIC, "+ MMODamageMechanic fired for {0} with {1} power", new Object[]{amount, data.getPower()});
//            return true;
//        } else {
//            return false;
//        }
//    }
//}
