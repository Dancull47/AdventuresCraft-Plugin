package monzter.adventurescraft.plugin.network.AdventureGamemode.Shared.Events;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;

public enum TutorialMessages {
    RESOURCE_COLLECTOR(MiniMessage.get().parse("<click:suggest_command:/resourcecollector><hover:show_text:'<green>Click to view Resource Collector!'>" +
            "<green>The item you just picked up was deposited into your " +
            "<gold>Resource Collector" +
            "<green>, which holds common resources to help keep your inventory clean!")),
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
