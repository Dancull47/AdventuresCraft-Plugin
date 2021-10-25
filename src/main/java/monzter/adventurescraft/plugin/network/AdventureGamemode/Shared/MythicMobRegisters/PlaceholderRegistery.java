package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.MythicMobRegisters;

import com.gmail.berndivader.mythicmobsext.Main;
import com.gmail.berndivader.mythicmobsext.utils.Utils;
import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicReloadedEvent;
import io.lumine.xikage.mythicmobs.skills.placeholders.Placeholder;
import io.lumine.xikage.mythicmobs.skills.placeholders.PlaceholderManager;
import monzter.adventurescraft.plugin.network.AdventureGamemode.Adventure.Events.MythicMobs.DamageTracker;
import monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.GUIs.mainMenu.Enchant;
import monzter.adventurescraft.plugin.utilities.general.EnchantmentCalculator;
import net.Indyuce.mmoitems.ItemStats;
import net.Indyuce.mmoitems.api.player.PlayerData;
import net.Indyuce.mmoitems.api.player.RPGPlayer;
import net.Indyuce.mmoitems.comp.mmocore.MMOCoreHook;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.EquipmentSlot;

public class PlaceholderRegistery implements Listener {

    static PlaceholderManager manager;

    static {
        manager = Utils.mythicmobs.getPlaceholderManager();
    }

    public PlaceholderRegistery() {
        Bukkit.getPluginManager().registerEvents(this, Main.getPlugin());
        register();
    }

    @EventHandler
    void mythicReload(MythicReloadedEvent e) {
        register();
    }

    void register() {

        manager.register("caster.last_attack_damage", Placeholder.meta((meta, arg) -> String.valueOf(DamageTracker.lastDamage.get(meta.getCaster().getEntity().getUniqueId()))));
        manager.register("caster.mmo_stat_damage", Placeholder.meta((meta, arg) -> {
            RPGPlayer rpgPlayer = new MMOCoreHook.MMOCoreRPGPlayer(PlayerData.get(meta.getCaster().getEntity().getUniqueId()));
            return String.valueOf(rpgPlayer.getPlayerData().getStats().getStat(ItemStats.ATTACK_DAMAGE));
        }));
        manager.register("enchant.reflect", Placeholder.meta((meta, arg) -> {
            Player player = Bukkit.getPlayer(meta.getCaster().getEntity().getUniqueId());
            double amount;
            amount = EnchantmentCalculator.calculateEnchantments(player, "REFLECT", EquipmentSlot.HEAD) * Enchant.Enchantments.REFLECT.getIncreasePerLevel();
            amount += EnchantmentCalculator.calculateEnchantments(player, "REFLECT", EquipmentSlot.CHEST) * Enchant.Enchantments.REFLECT.getIncreasePerLevel();
            amount += EnchantmentCalculator.calculateEnchantments(player, "REFLECT", EquipmentSlot.LEGS) * Enchant.Enchantments.REFLECT.getIncreasePerLevel();
            amount += EnchantmentCalculator.calculateEnchantments(player, "REFLECT", EquipmentSlot.FEET) * Enchant.Enchantments.REFLECT.getIncreasePerLevel();
            return String.valueOf(amount);
        }));
//        manager.register("caster.l.dy", Placeholder.meta((meta, arg) -> {
//            return Double.toString(meta.getCaster().getLocation().getY());
//        }));
//        manager.register("caster.l.dz", Placeholder.meta((meta, arg) -> {
//            return Double.toString(meta.getCaster().getLocation().getZ());
//        }));
//        manager.register("caster.holding", Placeholder.meta((meta, arg) -> {
//            if (meta.getCaster().getEntity().isLiving()) {
//                LivingEntity entity = (LivingEntity) meta.getCaster().getEntity().getBukkitEntity();
//                ItemStack item_stack = entity.getEquipment().getItemInMainHand();
//                Material material = item_stack == null ? Material.AIR : item_stack.getType();
//                return material.name();
//            }
//            return null;
//        }));
//        manager.register("caster.nbt", Placeholder.meta((meta, arg) -> {
//            return null;
//        }));
//        manager.register("trigger.l.dx", Placeholder.meta((meta, arg) -> {
//            return Double.toString(meta.getTrigger().getLocation().getX());
//        }));
//        manager.register("trigger.l.dy", Placeholder.meta((meta, arg) -> {
//            return Double.toString(meta.getTrigger().getLocation().getY());
//        }));
//        manager.register("trigger.l.dz", Placeholder.meta((meta, arg) -> {
//            return Double.toString(meta.getTrigger().getLocation().getZ());
//        }));
//        manager.register("trigger.holding", Placeholder.meta((meta, arg) -> {
//            if (meta.getTrigger().isLiving()) {
//                LivingEntity entity = (LivingEntity) meta.getTrigger().getBukkitEntity();
//                ItemStack item_stack = entity.getEquipment().getItemInMainHand();
//                Material material = item_stack == null ? Material.AIR : item_stack.getType();
//                return material.name();
//            }
//            return null;
//        }));
//        manager.register("trigger.nbt", Placeholder.meta((meta, arg) -> {
//            return null;
//        }));
//        manager.register("target.l.dx", Placeholder.entity((entity, arg) -> {
//            return Double.toString(entity.getLocation().getX());
//        }));
//        manager.register("target.l.dy", Placeholder.entity((entity, arg) -> {
//            return Double.toString(entity.getLocation().getY());
//        }));
//        manager.register("target.l.dz", Placeholder.entity((entity, arg) -> {
//            return Double.toString(entity.getLocation().getZ());
//        }));
//        manager.register("target.holding", Placeholder.entity((target, arg) -> {
//            if (target.isLiving()) {
//                LivingEntity entity = (LivingEntity) target.getBukkitEntity();
//                ItemStack item_stack = entity.getEquipment().getItemInMainHand();
//                Material material = item_stack == null ? Material.AIR : item_stack.getType();
//                return material.name();
//            }
//            return null;
//        }));
//        manager.register("target.nbt", Placeholder.entity((target, arg) -> {
//            return null;
//        }));
        manager.recheckForPlaceholders();

        Main.logger.info("registered AdventureCraft placeholders");
    }

}
