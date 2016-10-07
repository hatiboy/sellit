package sellit.soict.com.fragment;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.Locale;

import sellit.soict.com.R;
import sellit.soict.com.adapter.PagerPicture;
import sellit.soict.com.libs.CirclePageIndicator;
import sellit.soict.com.model.Product;
import sellit.soict.com.ui.DetailProductActivity;
import sellit.soict.com.utils.ClientUtils;
import sellit.soict.com.utils.DESUtils;
import sellit.soict.com.utils.DatabaseManager;
import sellit.soict.com.utils.DateUtil;
import sellit.soict.com.utils.PriceUtils;
import sellit.soict.com.utils.Values;
import sellit.soict.com.view.TextViewRobotoMedium;
import sellit.soict.com.view.TextViewRobotoRegular;

public class DetailProductFragment extends Fragment {
    private ViewPager pagerPicture;
    private Product product;
    private CirclePageIndicator circlePageIndicator;
    private long id;
    private int countPictres = 0;
    private String[] pictures;
    private ClientUtils clientUtils;
    private DatabaseManager databaseManager;
    private ProgressDialog progressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Add your menu entries here
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_detail, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
                final Dialog dialog = new Dialog(getActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.layout_dialog_delete);
                Button btnOk = (Button) dialog.findViewById(R.id.btnOk);
                Button btnCancel = (Button) dialog.findViewById(R.id.btnCancel);
                final EditText edtPass = (EditText) dialog.findViewById(R.id.edtPass);

                btnOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        progressDialog = new ProgressDialog(getActivity());
                        progressDialog.setMessage("Vui lòng đợi...");
                        progressDialog.show();
                        ClientUtils clientUtils = new ClientUtils(getActivity());
                        clientUtils.setUrl(Values.URL_DELETE);
                        clientUtils.addParam(Values.ID, id + "");
                        clientUtils.addParam(Values.USER_PASSWORD, DESUtils.encrypt(edtPass.getText().toString(), getActivity().getPackageName()));
                        clientUtils.setOnRequestCompleted(new ClientUtils.OnRequestCompleted() {
                            @Override
                            public void onCompleted(String result) {
                                //Toast.makeText(getActivity(), result, Toast.LENGTH_SHORT).show();
                                if (progressDialog != null && progressDialog.isShowing()) {
                                    progressDialog.dismiss();
                                }
                                if (result.contains("1")) {
                                    Toast.makeText(getActivity(), "Xóa thành công", Toast.LENGTH_SHORT).show();
                                    getActivity().finish();
                                }else{
                                    Toast.makeText(getActivity(), "Sai mật khẩu!", Toast.LENGTH_SHORT).show();
                                }


                            }
                        });

                        clientUtils.query();
                    }
                });
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();


                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_detail_product, container, false);
        circlePageIndicator = (CirclePageIndicator) view.findViewById(R.id.indicator);
        databaseManager = new DatabaseManager(getActivity());

        pagerPicture = (ViewPager) view.findViewById(R.id.pagerPicture);
        id = getArguments().getLong(Values.ID);

        clientUtils = new ClientUtils(getActivity());
        clientUtils.setUrl(Values.URL_GET_DETAIL);
        clientUtils.addParam(Values.ID, id + "");
        clientUtils.setOnRequestCompleted(new ClientUtils.OnRequestCompleted() {
            @Override
            public void onCompleted(String result) {
                product = new Gson().fromJson(result, Product.class);
                setDetailProduct(view, product);
                pagerPicture.setAdapter(new PagerPicture(getActivity(), pictures,false));
                circlePageIndicator.setViewPager(pagerPicture);

            }
        });
        ((ImageView)view.findViewById(R.id.viewPicture)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity() instanceof DetailProductActivity) {
                    DetailProductActivity detailProductActivity = (DetailProductActivity) getActivity();
                    detailProductActivity.viewPictures(pictures);
                }
            }
        });
        clientUtils.query();
        // get detail product


        return view;
    }

    public void setDetailProduct(View view, Product product) {
        ((TextViewRobotoMedium) view.findViewById(R.id.tvName)).setText(product.getName());
        ((TextViewRobotoRegular) view.findViewById(R.id.tvPrice)).setText(PriceUtils.convertPriceHaveDot(product.getPrice()));
        ((TextViewRobotoRegular) view.findViewById(R.id.tvNameUser)).setText(product.getUser_name());
        ((TextViewRobotoRegular) view.findViewById(R.id.tvDate)).setText(DateUtil.getExtendedRelativeTimeSpanString(getActivity(), new Locale("vi"), product.getDate()) + "");
        ((TextViewRobotoRegular) view.findViewById(R.id.tvContent)).setText(product.getContent());
        ((TextViewRobotoMedium) view.findViewById(R.id.tvAddress)).setText(databaseManager.getAddressFromId(product.getId_address()));
        ((TextViewRobotoMedium) view.findViewById(R.id.tvCategory)).setText(databaseManager.getCategoryFromId(product.getId_category()));
        ((TextViewRobotoMedium) view.findViewById(R.id.tvStatus)).setText(product.getStatus() + " %");
        pictures = product.getPictures().split(" ; ");
        countPictres = pictures.length;
    }

    public Product getProduct() {
        return product;
    }

}
