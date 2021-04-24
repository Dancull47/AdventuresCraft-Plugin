//package monzter.adventurescraft.plugin.event;
//
//import me.lucko.helper.item.ItemStackBuilder;
//import me.lucko.helper.menu.Gui;
//import me.lucko.helper.menu.scheme.MenuPopulator;
//import me.lucko.helper.menu.scheme.MenuScheme;
//import org.bukkit.Material;
//import org.bukkit.entity.Player;
//import org.bukkit.inventory.ItemStack;
//
//public class TestInv extends Gui {
//    private static final MenuScheme DISPLAY = new MenuScheme().mask("000010000");
//
//    // the keyboard buttons
//    private static final MenuScheme BUTTONS = new MenuScheme()
//            .mask("000000000")
//            .mask("000000001")
//            .mask("111111111")
//            .mask("111111111")
//            .mask("011111110")
//            .mask("000011000");
//
//    // we're limited to 9 keys per line, so add 'P' one line above.
//    private static final String KEYS = "PQWERTYUIOASDFGHJKLZXCVBNM";
//
//    private StringBuilder message = new StringBuilder();
//
//    public TestInv(Player player) {
//        super(player, 6, "&7Typewriter");
//    }
//
//    @Override
//    public void redraw() {
//
//        // perform initial setup.
//        if (isFirstDraw()) {
//            // when the GUI closes, send the resultant message to the player
//
//            // place the buttons
//            MenuPopulator populator = BUTTONS.newPopulator(this);
//            for (char keyChar : KEYS.toCharArray()) {
//                populator.accept(ItemStackBuilder.of(Material.CLAY_BALL)
//                        .name("&f&l" + keyChar)
//                        .lore("")
//                        .lore("&7Click to type this character")
//                        .build(() -> {
//                            message.append(keyChar);
//                            redraw();
//                        }));
//            }
//
//            populator.accept(ItemStackBuilder.of(Material.BOOK)
//                    .name("&fExperience 1")
//                    .lore("&fEnchantment")
//                    .lore("")
//                    .lore("&7Earn &a2x ۞ Experience &7while mining!")
//                    .lore("")
//                    .lore("&fPrice:")
//                    .lore("&8- &a%ac_Stat_EXPAmount% / &a2,500 ۞ Experience")
//                    .build(() -> {
//                        message.append(" ");
//                        redraw();
//                    }));
//
//                    populator.accept(ItemStackBuilder.of(Material.CLAY_BALL)
//                    .name("&f&lSPACE")
//                    .lore("")
//                    .lore("&7Click to type this character")
//                    .build(() -> {
//                        message.append(" ");
//                        redraw();
//                    }));
//            populator.acceptIfSpace(ItemStackBuilder.of())
//        }
//
//        // update the display every time the GUI is redrawn.
//        DISPLAY.newPopulator(this).accept(ItemStackBuilder.of(Material.BOOK)
//                .name("&f" + message.toString() + "&7_")
//                .lore("")
//                .lore("&f> &7Use the buttons below to type your message.")
//                .lore("&f> &7Hit ESC when you're done!")
//                .buildItem().build());
//    }
//
//}
