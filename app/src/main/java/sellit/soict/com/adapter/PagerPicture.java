package sellit.soict.com.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import sellit.soict.com.R;
import sellit.soict.com.utils.Values;

/**
 * Created by Dang Hien on 25/04/2016.
 */

public class PagerPicture extends PagerAdapter {
    private int COUNT_PIC;
    private String[] pictures;
    Bitmap[] bitmaps;

    private Context context;
    private LayoutInflater mLayoutInflater;
    private boolean viewDetail;

    public PagerPicture(Context context, String[] pictures, boolean isLink) {
        this.context = context;
        this.pictures = pictures;
        COUNT_PIC = pictures.length;
        this.viewDetail = isLink;
//Nullpoiter Exception
        try {
            mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public PagerPicture(Context context, Bitmap[] bitmaps, boolean isLink) {
        this.context = context;
        this.bitmaps = bitmaps;
        COUNT_PIC = bitmaps.length;
        this.viewDetail = isLink;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return COUNT_PIC;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.item_picture, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);

        if (viewDetail) {
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        } else {
//            imageView.setImageBitmap(bitmaps[position]);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        }
        Glide.with(context).load(Values.URL_SERVER_PICTURE + pictures[position]).into(imageView);
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}

