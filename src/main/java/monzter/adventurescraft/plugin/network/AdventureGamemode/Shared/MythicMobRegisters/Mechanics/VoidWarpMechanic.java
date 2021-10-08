package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.MythicMobRegisters.Mechanics;


import io.lumine.mythic.lib.api.item.NBTItem;
import io.lumine.xikage.mythicmobs.api.bukkit.BukkitAPIHelper;
import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
import io.lumine.xikage.mythicmobs.skills.INoTargetSkill;
import io.lumine.xikage.mythicmobs.skills.SkillMechanic;
import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.general.ChanceCheck;
import monzter.adventurescraft.plugin.utilities.general.Cooldown;
import monzter.adventurescraft.plugin.utilities.general.ItemAdder;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import net.Indyuce.mmoitems.MMOItems;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class VoidWarpMechanic extends SkillMechanic implements INoTargetSkill {
    private final AdventuresCraft plugin;
    private final ChanceCheck chanceCheck;
    private final SoundManager soundManager;
    private final ItemAdder itemAdder;


    public VoidWarpMechanic(String skill, MythicLineConfig config, AdventuresCraft plugin, ChanceCheck chanceCheck, SoundManager soundManager, ItemAdder itemAdder) {
        super(skill, config);
        this.plugin = plugin;
        this.chanceCheck = chanceCheck;
        this.soundManager = soundManager;
        this.itemAdder = itemAdder;
        this.threadSafetyLevel = ThreadSafetyLevel.SYNC_ONLY;
    }

    @Override
    public boolean cast(SkillMetadata data) {
        Player player = Bukkit.getPlayer(data.getCaster().getEntity().getUniqueId());
        if (!enchantressAlive(player))
            return false;
        if (!portalAlive(player))
            return false;

        String mmoItemID = MMOItems.getID(NBTItem.get(player.getInventory().getItemInMainHand()));
        if (mmoItemID.equals("UNSTABLE_WARP4")) {
            if (chanceCheck.chanceCheck(.5))
                summon(player);
            else {
                failedSummon(player);
                return false;
            }
        }
        summon(player);
        return true;
    }

    public boolean enchantressAlive(Player player) {
        if (Cooldown.isInCooldown(UUID.fromString("123e4567-e89b-12d3-a456-556642440000"), "VOID_ENCHANTRESS")) {
            player.sendMessage(ChatColor.RED + "The " + ChatColor.DARK_PURPLE + "Void Enchantress " + ChatColor.RED + "is already alive! " +
                    "Try again in " + ChatColor.GOLD + Cooldown.getTimeLeftFormatted(UUID.fromString("123e4567-e89b-12d3-a456-556642440000"), "VOID_ENCHANTRESS") + ChatColor.RED + "!");
            return false;
        }
        return true;
    }

    public boolean portalAlive(Player player) {
        if (Cooldown.isInCooldown(UUID.fromString("123e4567-e89b-12d3-a456-556642440000"), "VOID_PORTAL")) {
            player.sendMessage(ChatColor.RED + "There already is a " + ChatColor.GOLD + "Portal " + ChatColor.RED + "open!");
            return false;
        }
        return true;
    }

    public void summon(Player player) {
        BukkitAPIHelper bukkitAPIHelper = new BukkitAPIHelper();
        bukkitAPIHelper.castSkill(player, "PORTAL_SUMMON");
        player.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
    }

    public void failedSummon(Player player) {
        player.sendMessage(ChatColor.RED + "Unlucky! The portal was not summoned!");
        player.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
    }
}