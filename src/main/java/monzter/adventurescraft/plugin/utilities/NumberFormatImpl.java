package monzter.adventurescraft.plugin.utilities;

import java.text.DecimalFormat;

public class NumberFormatImpl implements NumberFormat {
    @Override
    public String numberFormat(int number) {
        DecimalFormat format = new DecimalFormat("###,###,###");
        return format.format(number);
    }
}
