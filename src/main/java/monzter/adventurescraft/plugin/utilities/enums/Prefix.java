package monzter.adventurescraft.plugin.utilities.enums;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.ChatColor;

public enum Prefix {
    PREFIX(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + "» ",
            Component.text("» ").color(NamedTextColor.DARK_GRAY).decorate(TextDecoration.BOLD)),
    TUT_PREFIX(ChatColor.GOLD.toString() + ChatColor.BOLD + "[" + ChatColor.GREEN + ChatColor.BOLD + "TUT" + ChatColor.GOLD + ChatColor.BOLD + "] ",
            Component.text(NamedTextColor.GOLD.toString() + TextDecoration.BOLD + "[" + NamedTextColor.GREEN + TextDecoration.BOLD + "TUT" + NamedTextColor.GOLD + TextDecoration.BOLD + "] "));

    private final String string;
    private final Component component;

    Prefix(String string, Component component) {
        this.string = string;
        this.component = component;
    }

    public String getString() {
        return string;
    }

    public Component getComponent() {
        return component;
    }
}
