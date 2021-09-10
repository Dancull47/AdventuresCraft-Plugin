//package monzter.adventurescraft.plugin.network.AdventureGamemode.Adventure.Events;
//
//import org.bukkit.Material;
//import org.bukkit.inventory.ItemStack;
//
//public enum BlockList {
//    WHEAT(Material.WHEAT, null, new Material[]{Material.WOODEN_HOE}, new String[]{("TOOL,WOODEN_HOE")},
//            new ItemStack[]{new ItemStack(Material.WHEAT, 1)}),
//    ;
//
//    private final Material block;
//    private final String[] region;
//    private final Material[] vanillaTool;
//    private final String[] mmoItemTool;
//    private final ItemStack[] rewards;
//
//
//
//    BlockList(Material block, String[] region, Material[] vanillaTool, String[] mmoItemTool, ItemStack[] rewards) {
//        this.block = block;
//        this.region = region;
//        this.vanillaTool = vanillaTool;
//        this.mmoItemTool = mmoItemTool;
//        this.rewards = rewards;
//    }
//
//    public Material getBlock() {
//        return block;
//    }
//
//    public String[] getRegion() {
//        return region;
//    }
//
//    public Material[] getVanillaTool() {
//        return vanillaTool;
//    }
//
//    public String[] getMmoItemTool() {
//        return mmoItemTool;
//    }
//
//    public ItemStack[] getRewards() {
//        return rewards;
//    }
//}
