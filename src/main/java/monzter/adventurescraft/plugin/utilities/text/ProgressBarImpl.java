package monzter.adventurescraft.plugin.utilities.text;

import com.google.common.base.Strings;
import org.bukkit.ChatColor;

public class ProgressBarImpl implements ProgressBar {
    @Override
    public String getProgressBar(int current, int max, int totalBars, char symbol, ChatColor completedColor, ChatColor notCompletedColor) {
        float percent = (float) current / max;
        int progressBars = (int) (totalBars * percent);

        if (current > max)
            progressBars = totalBars;

        return Strings.repeat("" + ChatColor.YELLOW + completedColor + symbol, progressBars)
                + Strings.repeat("" + ChatColor.WHITE + notCompletedColor + symbol, totalBars - progressBars);
    }
}
