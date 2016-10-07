package sellit.soict.com.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import sellit.soict.com.R;
import sellit.soict.com.adapter.PagerPicture;
import sellit.soict.com.libs.CirclePageIndicator;
import sellit.soict.com.utils.Values;

/**
 * Created by Dang Hien on 25/04/2016.
 */
public class ViewPicturesFragment extends Fragment {
    private ViewPager pagerPicture;
    private String[] pictures;
    private CirclePageIndicator circlePageIndicator;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.layout_view_pictures, container, false);
        pagerPicture = (ViewPager) view.findViewById(R.id.pagerPicture);
        pictures = getArguments().getStringArray(Values.PRODUCT_PICTURE);
        pagerPicture.setAdapter(new PagerPicture(getActivity(), pictures, true));
        circlePageIndicator = (CirclePageIndicator) view.findViewById(R.id.indicator);
        circlePageIndicator.setViewPager(pagerPicture);
        return view;
    }
}
