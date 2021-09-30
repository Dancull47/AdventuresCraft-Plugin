package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.Events;

import monzter.adventurescraft.plugin.utilities.enums.Prefix;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;

public enum TutorialMessages {
    RESOURCE_COLLECTOR(Prefix.TUT_PREFIX.getComponent().append(Component.text("The item you just picked up was deposited in your ")
            .color(NamedTextColor.GREEN)
            .append(Component.text("Resource Collector, ", NamedTextColor.GOLD))
            .hoverEvent(Component.text("Click to view Resource Collector!", NamedTextColor.GREEN))
            .clickEvent(ClickEvent.suggestCommand("/resourcecollector"))
            .append(Component.text("which holds common resources to help keep your inventory clean!")))),
    ;

    public final Component component;

    TutorialMessages(Component component) {
        this.component = component;
    }

    public Component getComponent() {
        return component;
    }

    public void disableMessage(Player player, String tutorialMessage) {
        player.sendMessage("");
        player.sendMessage(Component.text("You can disable this ")
                .color(NamedTextColor.GREEN)
                .append(Component.text("Tutorial Message ", NamedTextColor.YELLOW))
                .hoverEvent(Component.text("Click to disable message!", NamedTextColor.GREEN))
                .clickEvent(ClickEvent.runCommand("/TutorialMessage Disable " + tutorialMessage))
                .append(Component.text("by clicking HERE!")));
    }
}
