package sellit.soict.com.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

import sellit.soict.com.R;
import sellit.soict.com.fragment.InputAccountFragment;
import sellit.soict.com.fragment.InputProductFragment;
import sellit.soict.com.model.Product;
import sellit.soict.com.utils.Values;

public class SellActivity extends AppCompatActivity {

    private Product product;
    private InputProductFragment inputProductFragment;
    private InputAccountFragment inputAccountFragment;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sell);
        product = new Product();
//        User user = new User();
//        user.setAddress(new Address());
//        product.setUser(user);
        inputProductFragment = new InputProductFragment();
        inputAccountFragment =new InputAccountFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.content, inputProductFragment).commit();

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor=sharedPreferences.edit();
        String userJson= sharedPreferences.getString(Values.USER,"");
        if(userJson!=null &&!userJson.equalsIgnoreCase("")){

        }else {

        }


    }



    public void inputAccount(){
        getSupportFragmentManager().beginTransaction().add(R.id.content, inputAccountFragment).addToBackStack("").commit();
    }

    public void showStep(){

    }


}
