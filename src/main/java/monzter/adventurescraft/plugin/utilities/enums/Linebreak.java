package monzter.adventurescraft.plugin.utilities.enums;

import net.kyori.adventure.text.Component;

public enum Linebreak {
    PREFIX("", Component.text(""));

    private final String string;
    private final Component component;

    Linebreak(String string, Component component) {
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
