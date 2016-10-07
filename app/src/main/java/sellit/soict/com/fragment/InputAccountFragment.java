package sellit.soict.com.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import sellit.soict.com.R;
import sellit.soict.com.model.Address;
import sellit.soict.com.model.Product;
import sellit.soict.com.ui.SellActivity;

/**
 * Created by Dang Hien on 13/04/2016.
 */
public class InputAccountFragment extends Fragment {
    private EditText name, mail, phone, rePass, pass;
    private Button btnSell;

    private SellActivity sellActivity;
    private Product product;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getActivity() instanceof SellActivity) {
            sellActivity = (SellActivity) getActivity();
        }
        product = sellActivity.getProduct();

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_input_account, container, false);

        name = (EditText) view.findViewById(R.id.name);
        pass = (EditText) view.findViewById(R.id.pass);
        rePass = (EditText) view.findViewById(R.id.rePass);
        mail = (EditText) view.findViewById(R.id.mail);
        phone = (EditText) view.findViewById(R.id.phone);

        btnSell = (Button) view.findViewById(R.id.btnSell);
        btnSell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameUser = name.getText().toString();

                if (nameUser == null || nameUser.length() <= 3) {

                } else {

//                    product.getUser().setName(nameUser);

                    Product product = sellActivity.getProduct();
                    int i = 0;
                    i++;
                }
            }
        });


        return view;
    }
}
