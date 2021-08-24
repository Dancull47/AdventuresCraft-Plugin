package monzter.adventurescraft.plugin.network.AdventureGamemode.Adventure.Events;

import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
import io.lumine.xikage.mythicmobs.skills.ITargetedEntitySkill;
import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
import net.Indyuce.mmoitems.MMOItems;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class VoidMythicMobSkills extends SkillMechanic implements ITargetedEntitySkill {

    private final MMOItems mmoItems;
    private final int duration;


    public VoidMythicMobSkills(MythicLineConfig config, MMOItems mmoItems) {
        super(config.getLine(), config);
        this.mmoItems = mmoItems;
        this.setAsyncSafe(false);
        this.duration = config.getInteger(new String[]{"duration", "d"}, 100);
    }

    @Override
    public boolean castAtEntity(SkillMetadata data, AbstractEntity target) {
        Player player = (Player) BukkitAdapter.adapt(target);
        if (player != null)
            if (!player.getInventory().getItemInOffHand().equals(mmoItems.getItem("CATALYST", "CORRUPTION_REVOLTER3"))
                    && !player.getInventory().getItemInOffHand().equals(mmoItems.getItem("CATALYST", "CORRUPTION_REVOLTER4"))
                    && !player.getInventory().getItemInOffHand().equals(mmoItems.getItem("CATALYST", "UNBLINDER3"))) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, duration, 2));
                return true;
            }
        return false;
    }
}