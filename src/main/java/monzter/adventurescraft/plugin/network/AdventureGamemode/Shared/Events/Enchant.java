package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.Events;

import monzter.adventurescraft.plugin.AdventuresCraft;
import org.bukkit.event.Listener;

public class Enchant implements Listener {
    private final AdventuresCraft plugin;


    public Enchant(AdventuresCraft plugin) {
        this.plugin = plugin;
    }

//    @EventHandler
//    public void pickup(EnchantItemEvent event) {
//        switch (plugin.SERVER) {
//            case "Adventure":
//            case "Home":
//                ItemStack item = event.getItem();
//                ItemMeta meta = item.getItemMeta();
//                List<String> lore = meta.getLore();
//                if (lore != null) {
//                    meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
//                    plugin.getLogger().info("Not null");
//
//                    int index = 0;
//                    while (!lore.get(index).equals("")) { //Skip to the first blank line in lore
//                        index++;
//                    }
//
//                    Map<Enchantment, Integer> enchants = event.getEnchantsToAdd();
//                    for (Enchantment enchantment : enchants.keySet()) {
//                        String enchantName = EnchantmentNames.get(enchantment);
//                        int enchantLevel = enchants.get(enchantment);
//                        lore.add(index, "§7" + enchantName + " " + enchantLevel);
//                        plugin.getLogger().info("Lore added");
//
//                        index++;
//                    }
//                    meta.setLore(lore);
//                    item.setItemMeta(meta);
//                }
//
////                        if (event.getItem().getItemMeta().getLore() != null) {
////                    int i = 0;
////                    for (String lore : event.getItem().getItemMeta().getLore()) {
////                        if (i == 2 && !lore.contains(ChatColor.DARK_PURPLE.toString() + ChatColor.BOLD + "ENCHANTMENTS:")) {
////                            List<String> newLore = new ArrayList<>();
////                            int i2 = 0;
////                            for (String originalLore : event.getItem().getItemMeta().getLore()) {
////                                newLore.add(originalLore);
////                                i++;
////                                if (i == 2) {
////                                    newLore.add(ChatColor.DARK_PURPLE.toString() + ChatColor.BOLD + "ENCHANTMENTS:");
////                                }
////                            }
////                        }
////                        i++;
////                    }
////                }
//        }
//    }
//
//    private static HashMap<Enchantment, String> EnchantmentNames = new HashMap<Enchantment, String>();
//
//    static {
//        for (Enchantment enchant : Enchantment.values()) {
//            EnchantmentNames.put(enchant, enchant.getKey().toString());
//        }//For loop just to give all enchants a temp name using NamespacedKey. Don't need this if you manually add all of them below.
//        EnchantmentNames.put(Enchantment.ARROW_DAMAGE, "Power");
//        EnchantmentNames.put(Enchantment.ARROW_KNOCKBACK, "Punch");
//    }
//
}
