package sellit.soict.com.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;


import sellit.soict.com.utils.Utils;


/**
 * Created by Admin on 23/08/2015.
 */
public class TextViewRobotoMedium extends TextView {
    public TextViewRobotoMedium(Context context) {
        super(context);
        setTypeface(Utils.getTypefaceRobotoMedium(context));
    }

    public TextViewRobotoMedium(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTypeface(Utils.getTypefaceRobotoMedium(context));

    }

    public TextViewRobotoMedium(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTypeface(Utils.getTypefaceRobotoMedium(context));

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TextViewRobotoMedium(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setTypeface(Utils.getTypefaceRobotoMedium(context));

    }


}
