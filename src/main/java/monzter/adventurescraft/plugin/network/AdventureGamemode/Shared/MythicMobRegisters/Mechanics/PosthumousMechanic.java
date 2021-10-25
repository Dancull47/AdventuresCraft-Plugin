package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.MythicMobRegisters.Mechanics;


import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
import monzter.adventurescraft.plugin.utilities.general.EnchantmentCalculator;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class PosthumousMechanic extends SkillMechanic implements ITargetedEntitySkill {
    private Random random = new Random();
    private int rng;

    public PosthumousMechanic(String skill, MythicLineConfig config) {
        super(skill, config);
        this.threadSafetyLevel = ThreadSafetyLevel.SYNC_ONLY;
    }

    @Override
    public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
        if (target.isDead() || target.getHealth() <= 0) return false;
        Player player = Bukkit.getPlayer(target.asPlayer().getUniqueId());
        int enchantmentLevel = EnchantmentCalculator.calculateEnchantments(player, "POSTHUMOUS");

        if (enchantmentLevel > 0 && enchantmentLevel <= 5)
            player.addPotionEffect(PotionEffectType.SPEED.createEffect(100, 2));
        else if (enchantmentLevel > 5 && enchantmentLevel <= 10) {
            rng = random.nextInt(2);
            if (rng == 0)
                player.addPotionEffect(PotionEffectType.SPEED.createEffect(150, 3));
            else if (rng == 1)
                player.addPotionEffect(PotionEffectType.INCREASE_DAMAGE.createEffect(150, 3));
        }
        else if (enchantmentLevel > 10 && enchantmentLevel <= 15) {
            rng = random.nextInt(3);
            if (rng == 0)
                player.addPotionEffect(PotionEffectType.SPEED.createEffect(150, 4));
            else if (rng == 1)
                player.addPotionEffect(PotionEffectType.INCREASE_DAMAGE.createEffect(150, 4));
            else if (rng == 2)
                player.addPotionEffect(PotionEffectType.REGENERATION.createEffect(150, 4));
        }
        else if (enchantmentLevel > 15 && enchantmentLevel <= 20) {
            rng = random.nextInt(4);
            if (rng == 0)
                player.addPotionEffect(PotionEffectType.SPEED.createEffect(150, 5));
            else if (rng == 1)
                player.addPotionEffect(PotionEffectType.INCREASE_DAMAGE.createEffect(150, 5));
            else if (rng == 2)
                player.addPotionEffect(PotionEffectType.REGENERATION.createEffect(150, 5));
            else if (rng == 3)
                player.addPotionEffect(PotionEffectType.JUMP.createEffect(150, 5));
        }
        else if (enchantmentLevel > 20 && enchantmentLevel <= 25) {
            rng = random.nextInt(5);
            if (rng == 0)
                player.addPotionEffect(PotionEffectType.SPEED.createEffect(150, 6));
            else if (rng == 1)
                player.addPotionEffect(PotionEffectType.INCREASE_DAMAGE.createEffect(150, 6));
            else if (rng == 2)
                player.addPotionEffect(PotionEffectType.REGENERATION.createEffect(150, 6));
            else if (rng == 3)
                player.addPotionEffect(PotionEffectType.JUMP.createEffect(150, 6));
            else if (rng == 4)
                player.addPotionEffect(PotionEffectType.FAST_DIGGING.createEffect(150, 6));
        }

        return true;
    }
}