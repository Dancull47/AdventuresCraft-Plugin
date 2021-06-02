package monzter.adventurescraft.plugin.network.PrisonGamemode.cell.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import world.bentobox.bentobox.api.flags.Flag;
import world.bentobox.bentobox.lists.Flags;

public enum CellFlagsGUIItems {
    AccessContainers(Material.CHEST, Flags.CONTAINER, ChatColor.GREEN + "Access Containers", "Allow others to access your " + ChatColor.YELLOW + "Chests, Shulker Boxes, &n " +ChatColor.YELLOW+"Flower Pots, Composters, and Barrels" + ChatColor.GRAY + "."),
    AccessDroppers(Material.DROPPER, Flags.DROPPER, ChatColor.GREEN + "Access Droppers", "Allow others to access your " + ChatColor.YELLOW + "Droppers" + ChatColor.GRAY + "."),
    AccessFurnace(Material.FURNACE, Flags.FURNACE, ChatColor.GREEN + "Access Furnaces", "Allow others to access your " + ChatColor.YELLOW + "Furnaces" + ChatColor.GRAY + "."),
    AccessItemFrames(Material.ITEM_FRAME, Flags.ITEM_FRAME, ChatColor.GREEN + "Access Item Frames", "Allow others to access your " + ChatColor.YELLOW + "Item Frames" + ChatColor.GRAY + "."),
    Anvil(Material.ANVIL, Flags.ANVIL, ChatColor.GREEN + "Anvil Usage", "Allow others to interact with Anvils."),
    ArmorStands(Material.ARMOR_STAND, Flags.ARMOR_STAND, ChatColor.GREEN + "Armor Stand Interaction", "Allow others to make changes &n by clicking on Armor Stands."),
    Beacons(Material.BEACON, Flags.BEACON, ChatColor.GREEN + "Beacon Changes", "Allow others to make &n changes with your Beacons."),
    BlockDestroying(Material.WOODEN_AXE, Flags.BREAK_BLOCKS, ChatColor.GREEN + "Block Breaking", "Allow others to Break Blocks!"),
    BlockPlacing(Material.OAK_PLANKS, Flags.PLACE_BLOCKS, ChatColor.GREEN + "Block Placing", "Allow others to Place Blocks!"),
    Boats(Material.OAK_BOAT, Flags.BOAT, ChatColor.GREEN + "Boats", "Place, Break, or Ride Boats."),
    Buttons(Material.OAK_BUTTON, Flags.BUTTON, ChatColor.GREEN + "Button Pressing", "Allow others to press your Buttons!"),
    Levers(Material.LEVER, Flags.LEVER, ChatColor.GREEN + "Lever Pulling", "Allow others to pull your Levers!"),
    PressurePlates(Material.STONE_PRESSURE_PLATE, Flags.PRESSURE_PLATE, ChatColor.GREEN + "Pressure Plates", "Allow others to pressure your Pressure Plates!"),
    CollectLava(Material.LAVA_BUCKET, Flags.COLLECT_LAVA, ChatColor.GREEN + "Collect Lava", "Allow others to collect your Lava."),
    CollectWater(Material.WATER_BUCKET, Flags.COLLECT_WATER, ChatColor.GREEN + "Collect Water", "Allow others to collect your Water."),
    DropItems(Material.DIAMOND, Flags.ITEM_DROP, ChatColor.GREEN + "Drop Items", "Allow others to drop items to you."),
    PickupItems(Material.REDSTONE, Flags.ITEM_PICKUP, ChatColor.GREEN + "Pickup Items", "Allow others to pickup items."),
    HurtAnimals(Material.WOODEN_SWORD, Flags.HURT_ANIMALS, ChatColor.GREEN + "Hurt Animals", "Allow others to hurt your Animals!"),
    HurtMonsters(Material.NETHERITE_SWORD, Flags.HURT_ANIMALS, ChatColor.GREEN + "Hurt Monsters", "Allow others to hurt your Monsters!"),
    OpenDoors(Material.OAK_DOOR, Flags.DOOR, ChatColor.GREEN + "Open Doors", "Allow others to open your Doors!"),
    OpenGates(Material.OAK_FENCE_GATE, Flags.GATE, ChatColor.GREEN + "Open Gates", "Allow others to open your Gates!"),
    OpenTrapDoors(Material.OAK_TRAPDOOR, Flags.TRAPDOOR, ChatColor.GREEN + "Open Trap Doors", "Allow others to open your Trap Doors!"),
    PickupExperience(Material.EXPERIENCE_BOTTLE, Flags.EXPERIENCE_PICKUP, ChatColor.GREEN + "Pickup Experience", "Allow others to pickup " + ChatColor.GREEN + "Experience" + ChatColor.GRAY + "!"),
    RideMinecarts(Material.MINECART, Flags.MINECART, ChatColor.GREEN + "Ride Minecarts", "Allow others to ride your Minecarts!"),
    ThrowEggs(Material.EGG, Flags.EGGS, ChatColor.GREEN + "Throw Eggs", "Allow others to throw Eggs!"),
    TrampleCrops(Material.WHEAT, Flags.CROP_TRAMPLE, ChatColor.GREEN + "Trample Crops", "Allow others to trample your Crops!"),
    Visitors(Material.TOTEM_OF_UNDYING, Flags.LOCK, ChatColor.GREEN + "Visitors", "Allow others to visit your Cell!"),
    ;

    private final Material material;
    private final Flag flag;
    private final String name;
    private final String info;

    CellFlagsGUIItems(Material material, Flag flag, String name, String info) {
        this.material = material;
        this.flag = flag;
        this.name = name;
        this.info = info;
    }

    public Material getMaterial() {
        return material;
    }

    public String getName() {
        return name;
    }

    public String getInfo() {
        return info;
    }

    public Flag getFlag() {
        return flag;
    }
}
