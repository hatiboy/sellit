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
public class TextViewRobotoRegular extends TextView {
    public TextViewRobotoRegular(Context context) {
        super(context);
        setTypeface(Utils.getTypefaceRobotoRegular(context));
    }

    public TextViewRobotoRegular(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTypeface(Utils.getTypefaceRobotoRegular(context));

    }

    public TextViewRobotoRegular(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTypeface(Utils.getTypefaceRobotoRegular(context));

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TextViewRobotoRegular(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setTypeface(Utils.getTypefaceRobotoRegular(context));

    }


}
