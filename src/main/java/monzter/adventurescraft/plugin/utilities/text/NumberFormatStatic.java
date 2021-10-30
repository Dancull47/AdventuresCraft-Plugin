package monzter.adventurescraft.plugin.utilities.text;

import java.text.DecimalFormat;

public class NumberFormatStatic {
    public static String numberFormat(int number) {
        return shortened(number);
    }

    public static String numberFormat(double number) {
        return shortened((long) number);
    }

    public static String numberFormat(long number) {
        return shortened(number);
    }

    public static String shortened(long number) {
        if (number > 999_999) {
            String one = String.valueOf(number).charAt(0) + "." + String.valueOf(number).charAt(1) + String.valueOf(number).charAt(2);
            String two = String.valueOf(number).charAt(0) + "" + String.valueOf(number).charAt(1) + "." + String.valueOf(number).charAt(2);
            String three = String.valueOf(number).charAt(0) + "" + String.valueOf(number).charAt(1) + String.valueOf(number).charAt(2) + "." + String.valueOf(number).charAt(3);

            if (number >= 1_000_000_000_000_000_000L)
                return one + "QT";

            else if (number >= 100_000_000_000_000_000L)
                return three + "QD";
            else if (number >= 10_000_000_000_000_000L)
                return two + "QD";
            else if (number >= 1_000_000_000_000_000L)
                return one + "QD";

            else if (number >= 100_000_000_000_000L)
                return three + "T";
            else if (number >= 10_000_000_000_000L)
                return two + "T";
            else if (number >= 1_000_000_000_000L)
                return one + "T";

            else if (number >= 100_000_000_000L)
                return three + "B";
            else if (number >= 10_000_000_000L)
                return two + "B";
            else if (number >= 1_000_000_000)
                return one + "B";

            else if (number >= 100_000_000)
                return three + "M";
            else if (number >= 10_000_000)
                return two + "M";
            else if (number >= 1_000_000)
                return one + "M";

            else
                return commas(number);
        }
        return commas(number);
    }

    public static String commas(long number) {
        DecimalFormat format = new DecimalFormat("###,###,###");
        return format.format(number);
    }
}
