package monzter.adventurescraft.plugin.network.PrisonGamemode.shared.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.Dependency;
import monzter.adventurescraft.plugin.AdventuresCraft;
import monzter.adventurescraft.plugin.utilities.beton.BetonPointsManager;
import monzter.adventurescraft.plugin.utilities.general.SoundManager;
import monzter.adventurescraft.plugin.utilities.luckperms.PermissionLP;
import monzter.adventurescraft.plugin.utilities.mmoitems.MMOItemsGive;

public class PetUnequip extends BaseCommand {

    @Dependency
    private final AdventuresCraft plugin;
    private final MMOItemsGive mmoItemsGive;
    private final SoundManager soundManager;
    private final PermissionLP permissionLP;
    private final BetonPointsManager betonPointsManager;

    public PetUnequip(AdventuresCraft plugin, MMOItemsGive mmoItemsGive, SoundManager soundManager, PermissionLP permissionLP, BetonPointsManager betonPointsManager) {
        this.plugin = plugin;
        this.mmoItemsGive = mmoItemsGive;
        this.soundManager = soundManager;
        this.permissionLP = permissionLP;
        this.betonPointsManager = betonPointsManager;
    }

//    @CommandAlias("petUnequip")
//    private void petUnequip(Player player, String petPermission, String pet) {
//        if (player.hasPermission(petPermission)) {
//            plugin.getLogger().info("Has permission");
//
//            if (!fullInventory(player)) {
//                betonPointsManager.givePoint(player, "items.PetAmount", -1);
//                permissionLP.takePermission(player, petPermission);
//                Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "mpet remove " + player.getName());
//                player.sendMessage(ChatColor.GREEN + "Your Pet has been unequipped!");
//                plugin.getLogger().info("PET_"+pet);
//                mmoItemsGive.giveMMOItem(player, "PET", "PET_"+pet);
//            }
//        }
//    }
//
//
//    private boolean fullInventory(Player player) {
//        if (player.getInventory().firstEmpty() == -1) {
//            player.sendMessage(ChatColor.RED + "You're inventory is full! Try again once you have at least one available slot!");
//            soundManager.playSound(player, Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
//            return true;
//        }
//        return false;
//    }

}

