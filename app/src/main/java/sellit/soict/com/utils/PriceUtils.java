package sellit.soict.com.utils;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by Dang Hien on 07/04/2016.
 */
public class PriceUtils {

    public static String convertPriceHaveDot(long price) {
        return NumberFormat.getNumberInstance(new Locale("vi")).format(price);
    }
}
