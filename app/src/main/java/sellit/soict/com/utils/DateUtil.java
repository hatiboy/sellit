package sellit.soict.com.utils;

import android.content.Context;
import android.text.format.DateFormat;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import sellit.soict.com.R;

/**
 * Created by Admin on 24/07/2015.
 */
public class DateUtil extends android.text.format.DateUtils {
    private static boolean isWithin(final long millis, final long span, final TimeUnit unit) {
        return System.currentTimeMillis() - millis <= unit.toMillis(span);

    }

    private static int convertDelta(final long millis, TimeUnit to) {
        return (int) to.convert(System.currentTimeMillis() - millis, TimeUnit.MILLISECONDS);
    }

    private static String getFormattedDateTime(long time, String template, Locale locale) {
        String localizedPattern = new SimpleDateFormat(template, locale).toLocalizedPattern();
        return new SimpleDateFormat(localizedPattern, locale).format(new Date(time));
    }

    public static String getBriefRelativeTimeSpanString(final Context c, final Locale locale, final long timestamp) {
        if (isWithin(timestamp, 1, TimeUnit.MINUTES)) {
            return c.getString(R.string.just_now);
        } else if (isWithin(timestamp, 1, TimeUnit.HOURS)) {
            int mins = convertDelta(timestamp, TimeUnit.MINUTES);
            return mins + " " + c.getResources().getString(R.string.minute_ago, mins);
        } else if (isWithin(timestamp, 1, TimeUnit.DAYS)) {
            int hours = convertDelta(timestamp, TimeUnit.HOURS);
            return c.getResources().getQuantityString(R.plurals.hours_ago, hours, hours);
        } else if (isWithin(timestamp, 6, TimeUnit.DAYS)) {
            return getFormattedDateTime(timestamp, "EEE", locale);
        } else if (isWithin(timestamp, 365, TimeUnit.DAYS)) {
            return getFormattedDateTime(timestamp, "MMM d", locale);
        } else {
            return getFormattedDateTime(timestamp, "MMM d, yyyy", locale);
        }
    }


    public static String getExtendedRelativeTimeSpanString(final Context c, final Locale locale, final long timestamp) {
        if (isWithin(timestamp, 1, TimeUnit.MINUTES)) {
            return c.getString(R.string.just_now);
        } else if (isWithin(timestamp, 1, TimeUnit.HOURS)) {
            int mins = (int) TimeUnit.MINUTES.convert(System.currentTimeMillis() - timestamp, TimeUnit.MILLISECONDS);
            return mins + " " + c.getResources().getString(R.string.minute_ago, mins);
        } else {
            StringBuilder format = new StringBuilder();
            if (isWithin(timestamp, 6, TimeUnit.DAYS)) format.append("EEE ");
            else if (isWithin(timestamp, 365, TimeUnit.DAYS)) format.append("MMM d, ");
            else format.append("MMM d, yyyy, ");
            try{
                if (DateFormat.is24HourFormat(c)) format.append("HH:mm");
                else format.append("hh:mm a");
            }catch (Exception e){
                e.printStackTrace();
            }


            return getFormattedDateTime(timestamp, format.toString(), locale);
        }
    }

    public static String getDateMonth(long date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM");
        return dateFormat.format(date);
    }

    public static String getDetailDate(long date) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
        return dateFormat.format(date);

    }

    public static SimpleDateFormat getDetailedDateFormatter(Context context, Locale locale) {
        String dateFormatPattern;

        if (DateFormat.is24HourFormat(context)) {
            dateFormatPattern = "MMM d, yyyy HH:mm:ss zzz";
        } else {
            dateFormatPattern = "MMM d, yyyy hh:mm:ss a zzz";
        }

        return new SimpleDateFormat(dateFormatPattern, locale);
    }

    public static int[] convertToDateString(long time) {
        int date = (int) time / 86400000;
        int hour = ((int) time % 86400000)/(60*60*1000);
        int[] arr = new int[]{date, hour};
        return arr;
    }

}
