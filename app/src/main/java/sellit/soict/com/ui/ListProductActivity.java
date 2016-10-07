package sellit.soict.com.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import sellit.soict.com.R;
import sellit.soict.com.adapter.ListProductAdapter;
import sellit.soict.com.model.Address;
import sellit.soict.com.model.BaseProduct;
import sellit.soict.com.model.Category;
import sellit.soict.com.utils.ClientUtils;
import sellit.soict.com.utils.DatabaseManager;
import sellit.soict.com.utils.Values;
import sellit.soict.com.view.TextViewRobotoRegular;

import static sellit.soict.com.R.drawable;
import static sellit.soict.com.R.id;
import static sellit.soict.com.R.layout;
import static sellit.soict.com.R.string;

public class ListProductActivity extends AppCompatActivity implements View.OnClickListener, ClientUtils.OnRequestCompleted {
    private RecyclerView recyclerProduct;
    private ListProductAdapter adapter;
    private RelativeLayout address, category;
    private TextViewRobotoRegular tvAddress, tvCategory;
    private Address addressCurrent;
    private Category categoryCurrent;
    private DatabaseManager databaseManager;
    private ClientUtils clientUtils;
    private EditText edtSearch;
    private ImageView btnDelete;
    private ImageView back;
    private RelativeLayout root;
    private Toolbar toolbar;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private SwipeRefreshLayout swipeRefreshLayout;

    //Value of Drawer Layout
    ArrayList<String> categories;
    DrawerLayout drawerlayout;
    ListView listview;
    ActionBarDrawerToggle drawertoggle;
    String drawertitle;
    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_list_product);
//        Values.setUrlServer("http://192.168.43.141:8012/sell");
//        Values.URL_SERVER ="http://192.168.1.36:8012";
//        String encrypt= DESUtils.encrypt("123456",getPackageName());
//        String s = Values.URL_DELETE;

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();
        root = (RelativeLayout) findViewById(id.root);
        recyclerProduct = (RecyclerView) findViewById(id.recyclerProduct);
        address = (RelativeLayout) findViewById(id.address);
        category = (RelativeLayout) findViewById(id.category);
        toolbar = (Toolbar) findViewById(id.toolbar);
        //refresh recycleview by query
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                String name = edtSearch.getText().toString();
                String category = categoryCurrent == null ? "" : categoryCurrent.getId();
                String address = addressCurrent == null ? "" : addressCurrent.getId();
                query(name, category, address);
                swipeRefreshLayout.setRefreshing(true);
            }
        });
        //set toolbar
        setSupportActionBar(toolbar);

        tvAddress = (TextViewRobotoRegular) findViewById(id.tvAddress);
        tvCategory = (TextViewRobotoRegular) findViewById(id.tvCategory);
        back = (ImageView)findViewById(id.ic);
        edtSearch = (EditText) findViewById(id.edtSearch);
        btnDelete = (ImageView) findViewById(id.btnDelete);
        btnDelete.setVisibility(View.INVISIBLE);
        btnDelete.setOnClickListener(this);
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (edtSearch.getText().toString().length() > 0) {
                    btnDelete.setVisibility(View.VISIBLE);
                } else {
                    btnDelete.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        address.setOnClickListener(this);
        category.setOnClickListener(this);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back.setImageResource(drawable.ic_search);
                back.setClickable(false);
                query("","","");
                ((TextViewRobotoRegular)findViewById(R.id.tvAddress)).setText("Tất cả");
                ((TextViewRobotoRegular)findViewById(id.tvCategory)).setText("Tất cả");
                edtSearch.setText("");
            }
        });

        databaseManager = new DatabaseManager(this);

        addressCurrent = databaseManager.getAddress(sharedPreferences.getString(Values.PRODUCT_ADDRESS, "-1"));
        categoryCurrent = databaseManager.getCategory(sharedPreferences.getString(Values.PRODUCT_CATEGORY, "-1"));
        tvAddress.setText(addressCurrent == null ? "Địa Chỉ" : addressCurrent.getName());
        tvCategory.setText(categoryCurrent == null ? "Danh Mục" : categoryCurrent.getName());

        ((Button) findViewById(id.btnFilter)).setOnClickListener(this);


        recyclerProduct.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

            }
        });
        //18-8 Them Drawer Layout
        drawerlayout = (DrawerLayout) findViewById(id.drawer_layout);
        listview = (ListView) findViewById(id.drawer_list);

        ArrayList<Category.CategoryGroup> listGroupCategory = databaseManager.getCategoryGroup();
        categories = new ArrayList<String>();
        for (int i = 0; i < listGroupCategory.size(); i++) {
            categories.add(listGroupCategory.get(i).getNameGroup());
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(getApplicationContext(), layout.drawer_list_item, categories);
        listview.setAdapter(arrayAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (addressCurrent != null) {
                    query("", "0" + position, addressCurrent.getId());
                } else {
                    query("", "0" + position, "");
                }
                Log.d("drawer picked: ", categories.get(position));
                drawerlayout.closeDrawers();
            }
        });

        title = drawertitle = (String) getTitle();
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, drawerlayout,
                drawable.ic_avatar, string.drawer_open, string.drawer_close) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        drawerlayout.setDrawerListener(drawertoggle);
    }

    @Override
    protected void onResume() {
        String name = edtSearch.getText().toString();
        String category = categoryCurrent == null ? "" : categoryCurrent.getId();
        String address = addressCurrent == null ? "" : addressCurrent.getId();
        query(name, category, address);
        super.onResume();
    }
    //    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//
//        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
//            View v = getCurrentFocus();
//            if ( v instanceof EditText) {
//                Rect outRect = new Rect();
//                v.getGlobalVisibleRect(outRect);
//                if (!outRect.contains((int)ev.getRawX(), (int)ev.getRawY())) {
//                    Log.d("focus", "touchevent");
//                    v.clearFocus();
//                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
//                    return super.dispatchTouchEvent(ev);
//                }
//            }
//        }
//        return true;
//    }

    public void query(String title, String id_category, String id_address) {
        clientUtils = new ClientUtils(this);
        clientUtils.setUrl(Values.URL_GET_LIST_FILTER);
        clientUtils.addParam(Values.PRODUCT_CATEGORY, id_category);
        clientUtils.addParam(Values.PRODUCT_ADDRESS, id_address);
        clientUtils.addParam(Values.PRODUCT_TITLE, title);
        clientUtils.query();
        editor.putString(Values.PRODUCT_CATEGORY, id_category);
        editor.putString(Values.PRODUCT_ADDRESS, id_address);
        editor.commit();
        clientUtils.setOnRequestCompleted(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_sell, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case id.action_sell:
                startActivity(new Intent(this, SellActivity.class));
                break;
//            case R.id.action_account:
//                startActivity(new Intent(this, LoginActivity.class));
//                break;
        }
        return super.onOptionsItemSelected(item);
    }

    AlertDialog.Builder builder;
    ArrayList<Address> listAddressGroup;
    ArrayList<Category> listCategoryGroup;

    //    StringBuilder id_address=new StringBuilder();
    //    StringBuilder id_category=new StringBuilder();
    // Đọc dữ liệu dạng JSON từ server về và gán vào recycle view
    @Override
    public void onCompleted(String result) {
        ArrayList<BaseProduct> baseProducts = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(result);
            Gson gson = new Gson();

            for (int i = 0; i < jsonArray.length(); i++) {
                BaseProduct baseProduct = gson.fromJson(jsonArray.get(i).toString(), BaseProduct.class);
                baseProducts.add(baseProduct);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        adapter = new ListProductAdapter(this, baseProducts);
        recyclerProduct.setLayoutManager(new LinearLayoutManager(this));
        recyclerProduct.setAdapter(adapter);
        if (swipeRefreshLayout.isShown()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onClick(View v) {
        builder = new AlertDialog.Builder(this);
        ArrayAdapter arrayAdapter;
        switch (v.getId()) {

            case id.btnFilter:
                String name = edtSearch.getText().toString();
                String category = categoryCurrent == null ? "" : categoryCurrent.getId();
                String address = addressCurrent == null ? "" : addressCurrent.getId();
                query(name, category, address);
                back.setImageResource(drawable.ic_back);
                back.setClickable(true);
                break;
            case id.address:
                final ArrayList<Address.AddressGroup> listGroup = databaseManager.getAddressGroup();
                arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listGroup);
                builder.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            dialog.dismiss();
                            tvAddress.setText(listGroup.get(which).getNameGroup());
                            addressCurrent = new Address("", listGroup.get(which).getNameGroup(), listGroup.get(which));
                        } else {
                            builder = new AlertDialog.Builder(ListProductActivity.this);
                            listAddressGroup = databaseManager.getAddressFromGroup(listGroup.get(which).getIdGroup());
                            builder.setAdapter(new AdapterAddress(ListProductActivity.this, listAddressGroup), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    addressCurrent = listAddressGroup.get(which);
                                    tvAddress.setText(addressCurrent.getName());
                                }

                            });
                            builder.show();
                        }
//                        }

                    }
                });
                builder.show();
                break;

            case id.category:
                final ArrayList<Category.CategoryGroup> listGroupCategory = databaseManager.getCategoryGroup();
                arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listGroupCategory);
                builder.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            dialog.dismiss();
                            tvCategory.setText(listGroupCategory.get(which).getNameGroup());
                            categoryCurrent = new Category("", listGroupCategory.get(which).getNameGroup(), listGroupCategory.get(which));
                        } else {
                            builder = new AlertDialog.Builder(ListProductActivity.this);
                            listCategoryGroup = databaseManager.getCategoryFromGroup(listGroupCategory.get(which).getIdGroup());
                            builder.setAdapter(new AdapterCategory(ListProductActivity.this, listCategoryGroup), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    categoryCurrent = listCategoryGroup.get(which);
                                    tvCategory.setText(categoryCurrent.getName());
                                }
                            });
                            builder.show();
                        }
                    }
                });
                builder.show();
                break;

            case id.btnDelete:
                edtSearch.setText("");
                break;

        }
    }


    class AdapterAddress extends ArrayAdapter {
        ArrayList<Address> addresses;

        public AdapterAddress(Context context, ArrayList<Address> addresses) {
            super(context, android.R.layout.simple_list_item_1, addresses);
            this.addresses = addresses;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = LayoutInflater.from(ListProductActivity.this).inflate(android.R.layout.simple_list_item_1, null);
            AppCompatTextView tvName = (AppCompatTextView) view.findViewById(android.R.id.text1);
            tvName.setText(addresses.get(position).getName());
            return view;
        }
    }

    class AdapterCategory extends ArrayAdapter {
        ArrayList<Category> categories;

        public AdapterCategory(Context context, ArrayList<Category> categories) {
            super(context, android.R.layout.simple_list_item_1, categories);
            this.categories = categories;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = LayoutInflater.from(ListProductActivity.this).inflate(android.R.layout.simple_list_item_1, null);
            AppCompatTextView tvName = (AppCompatTextView) view.findViewById(android.R.id.text1);
            tvName.setText(categories.get(position).getName());
            return view;
        }
    }
}
