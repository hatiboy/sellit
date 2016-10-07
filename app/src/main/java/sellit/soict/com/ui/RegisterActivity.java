package sellit.soict.com.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import sellit.soict.com.R;
import sellit.soict.com.model.Product;

/**
 * Created by Dang Hien on 15/04/2016.
 */
public class RegisterActivity extends AppCompatActivity {
    private Button btnSell;
    private EditText name, mail, phone, rePass, pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_input_account);

        btnSell = (Button) findViewById(R.id.btnSell);
        btnSell.setText("Đăng kí");
        name = (EditText) findViewById(R.id.name);
        pass = (EditText) findViewById(R.id.pass);
        rePass = (EditText) findViewById(R.id.rePass);
        mail = (EditText) findViewById(R.id.mail);
        phone = (EditText) findViewById(R.id.phone);

        btnSell = (Button) findViewById(R.id.btnSell);
        btnSell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
