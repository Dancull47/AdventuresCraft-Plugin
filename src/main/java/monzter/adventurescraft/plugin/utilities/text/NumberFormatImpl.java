package monzter.adventurescraft.plugin.utilities.text;

import java.text.DecimalFormat;

public class NumberFormatImpl implements NumberFormat {
    @Override
    public String numberFormat(int number) {
        DecimalFormat format = new DecimalFormat("###,###,###");
        return commas(number);
    }

    @Override
    public String numberFormat(double number) {
        DecimalFormat format = new DecimalFormat("###,###,###");
        return commas((long) number);
    }

    @Override
    public String numberFormat(long number) {
        DecimalFormat format = new DecimalFormat("###,###,###");
        return commas(number);
    }

    private String commas(long number) {
        if (number > 100) {
            String firstNumbers = String.valueOf(number).charAt(0) + String.valueOf(number).charAt(1) + "." + String.valueOf(number).charAt(2);
            if (number >= 1_000_000_000_000_000_000L)
                return firstNumbers + "QT";
            else if (number >= 1_000_000_000_000_000L)
                return firstNumbers + "QD";
            else if (number >= 1_000_000_000_000L)
                return firstNumbers + "T";
            else if (number >= 1_000_000_000)
                return firstNumbers + "B";
            else if (number >= 1_000_000)
                return firstNumbers + "M";
            else
                return String.valueOf(number);
        }
        return String.valueOf(number);
    }
}
