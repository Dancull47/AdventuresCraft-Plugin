//package monzter.adventurescraft.plugin.network.AdventureGamemode.Adventure.Compatibility;
//
//import monzter.adventurescraft.plugin.utilities.general.PurchaseEvent;
//import org.bukkit.Bukkit;
//import org.bukkit.entity.Player;
//import org.bukkit.event.EventHandler;
//import org.bukkit.event.HandlerList;
//import org.bukkit.event.Listener;
//import org.bukkit.inventory.ItemStack;
//import org.bukkit.plugin.Plugin;
//import pl.betoncraft.betonquest.BetonQuest;
//import pl.betoncraft.betonquest.Instruction;
//import pl.betoncraft.betonquest.api.Objective;
//import pl.betoncraft.betonquest.exceptions.InstructionParseException;
//
//public class PurchaseObjective extends Objective implements Listener {
//    private final String itemType;
//    private final String itemId;
//
//    @SuppressWarnings("deprecation")
//    public PurchaseObjective(Instruction instruction) throws InstructionParseException {
//        super(instruction);
//        itemType = instruction.next();
//        itemId = instruction.next();
//    }
//
//    @Override
//    public void start() {
//        Bukkit.getPluginManager().registerEvents(this, (Plugin) BetonQuest.getInstance());
//    }
//
//    @Override
//    public void stop() {
//        HandlerList.unregisterAll(this);
//    }
//
//    @Override
//    public String getDefaultDataInstruction() {
//        return null;
//    }
//
//    @Override
//    public String getProperty(String s, String s1) {
//        return null;
//    }
//
//
//    @EventHandler
//    public void purchaseItemEvent(PurchaseEvent event) {
//        final Player player = event.getPlayer();
//        final ItemStack itemStack = event.getItemPurchased();
//        if (player == )
//
//    }
//}

//  This was meant to be an Objective similar to MMOItems Crafting Station