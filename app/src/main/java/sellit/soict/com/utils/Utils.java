package sellit.soict.com.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;

/**
 * Created by Dang Hien on 07/04/2016.
 */
public class Utils {

    public static void makeCall(Context context, String number) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", number, null));
        context.startActivity(intent);
    }

    public static void makeSms(Context context, String number) {
        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", number, null)));
    }

    public static Typeface getTypefaceRobotoRegular(Context Context) {
        Typeface tf;
        try {
            tf = Typeface.createFromAsset(Context.getAssets(),
                    "fonts/Roboto-Regular.ttf");
            if (tf != null) {
                return tf;
            } else {
                return Typeface.DEFAULT;
            }
        } catch (Exception e) {
            return Typeface.DEFAULT;
        }
    }

    public static Typeface getTypefaceRobotoMedium(Context Context) {
        Typeface tf;
        try {
            tf = Typeface.createFromAsset(Context.getAssets(),
                    "fonts/Roboto-Medium.ttf");
            if (tf != null) {
                return tf;
            } else {
                return Typeface.DEFAULT;
            }
        } catch (Exception e) {
            return Typeface.DEFAULT;
        }
    }

    public static Typeface getTypefaceRobotoLight(Context Context) {
        Typeface tf;
        try {
            tf = Typeface.createFromAsset(Context.getAssets(),
                    "fonts/Roboto-Light.ttf");
            if (tf != null) {
                return tf;
            } else {
                return Typeface.DEFAULT;
            }
        } catch (Exception e) {
            return Typeface.DEFAULT;
        }
    }


}

