package sellit.soict.com.ui;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.facebook.FacebookSdk;

import bolts.AppLinks;
import sellit.soict.com.R;
import sellit.soict.com.fragment.DetailProductFragment;
import sellit.soict.com.fragment.ViewPicturesFragment;
import sellit.soict.com.model.Product;
import sellit.soict.com.utils.ClientUtils;
import sellit.soict.com.utils.Utils;
import sellit.soict.com.utils.Values;

public class DetailProductActivity extends AppCompatActivity implements View.OnClickListener {
    private FrameLayout content;
    private DetailProductFragment fragment;
    private LinearLayout btnSms, btnCall;
    private String userNumber;
    private ClientUtils clientUtils;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnCall = (LinearLayout) findViewById(R.id.btnCall);
        btnSms = (LinearLayout) findViewById(R.id.btnSms);

        btnCall.setOnClickListener(this);
        btnSms.setOnClickListener(this);


        content = (FrameLayout) findViewById(R.id.content);

        fragment = new DetailProductFragment();
        fragment.setArguments(getIntent().getExtras());


        getSupportFragmentManager().beginTransaction().add(R.id.content, fragment).commit();

        FacebookSdk.sdkInitialize(getApplicationContext());

        Uri targetUrl = AppLinks.getTargetUrlFromInboundIntent(this, getIntent());
        if (targetUrl != null) {
            Log.i("Activity", "App Link Target URL: " + targetUrl.toString());
        }

    }

    public void setUserNumber(String number) {
        userNumber = number;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCall:
                Utils.makeCall(this, fragment.getProduct().getPhone());
                //Toast.makeText(DetailProductActivity.this, "Call " + userNumber, Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnSms:
                Utils.makeSms(this, fragment.getProduct().getPhone());
                //   Toast.makeText(DetailProductActivity.this, "Sms " + userNumber, Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void viewPictures(String[] pictures){
        ViewPicturesFragment viewPicturesFragment = new ViewPicturesFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArray(Values.PRODUCT_PICTURE,pictures);
        viewPicturesFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().add(R.id.root, viewPicturesFragment).addToBackStack("").commit();
    }
}
