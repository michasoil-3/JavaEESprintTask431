package functools;

import java.text.DecimalFormat;

public class Formatting {
    public static String renderPrice(double price) {
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        return decimalFormat.format(price);
    }
}
