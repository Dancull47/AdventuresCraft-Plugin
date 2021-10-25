package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.MythicMobRegisters.Conditions;

import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
import io.lumine.xikage.mythicmobs.skills.SkillCondition;
import io.lumine.xikage.mythicmobs.skills.conditions.IEntityCondition;
import monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.mainMenu.Enchant;
import monzter.adventurescraft.plugin.utilities.general.ChanceCheck;
import monzter.adventurescraft.plugin.utilities.general.Cooldown;
import monzter.adventurescraft.plugin.utilities.general.EnchantmentCalculator;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;

public class ChanceCondition extends SkillCondition implements IEntityCondition {

    final String skillName;
    private final ChanceCheck chanceCheck;

    public ChanceCondition(MythicLineConfig config, ChanceCheck chanceCheck) {
        super(config.getLine());
        this.skillName = config.getString(new String[]{"skill", "skillName", "s", "n", "name"}, "DEFAULT");
        this.chanceCheck = chanceCheck;
    }

    @Override
    public boolean check(AbstractEntity target) {
        LivingEntity bukkitTarget = (LivingEntity) BukkitAdapter.adapt(target);
        if (!Cooldown.isInCooldown(bukkitTarget.getUniqueId(), "enchant")) {
            Player player = (Player) bukkitTarget;
            for (Enchant.Enchantments enchantment : Enchant.Enchantments.values())
                if (enchantment.name().equalsIgnoreCase(skillName)) {
                    double chance = (enchantment.getIncreasePerLevel() * EnchantmentCalculator.calculateEnchantments(player, skillName)) / 100;
                    switch (enchantment) {
                        case KAMIKAZE:
                        case REFLECT:
                        case NEGATION:
                            chance = enchantment.getIncreasePerLevel() * EnchantmentCalculator.calculateEnchantments(player, skillName, EquipmentSlot.HEAD);
                            chance += enchantment.getIncreasePerLevel() * EnchantmentCalculator.calculateEnchantments(player, skillName, EquipmentSlot.CHEST);
                            chance += enchantment.getIncreasePerLevel() * EnchantmentCalculator.calculateEnchantments(player, skillName, EquipmentSlot.LEGS);
                            chance += enchantment.getIncreasePerLevel() * EnchantmentCalculator.calculateEnchantments(player, skillName, EquipmentSlot.FEET);
                            chance = chance / 100;
                            break;
                    }
                    return chanceCheck.chanceCheck(chance);
                }
        }
        return false;
    }
}
