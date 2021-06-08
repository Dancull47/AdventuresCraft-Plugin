package monzter.adventurescraft.plugin.utilities.text;

import org.bukkit.ChatColor;

public interface ProgressBar {
    String getProgressBar(int current, int max, int totalBars, char symbol, ChatColor completedColor, ChatColor notCompletedColor);
}
