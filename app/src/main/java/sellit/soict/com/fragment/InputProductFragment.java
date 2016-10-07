package sellit.soict.com.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import sellit.soict.com.R;
import sellit.soict.com.model.Address;
import sellit.soict.com.model.Category;
import sellit.soict.com.model.Product;
import sellit.soict.com.ui.SellActivity;
import sellit.soict.com.utils.ClientUtils;
import sellit.soict.com.utils.DESUtils;
import sellit.soict.com.utils.DatabaseManager;
import sellit.soict.com.utils.PictureUtils;
import sellit.soict.com.utils.PriceUtils;
import sellit.soict.com.utils.Values;
import sellit.soict.com.view.TextViewRobotoRegular;

/**
 * Created by Dang Hien on 13/04/2016.
 */
public class InputProductFragment extends Fragment implements View.OnClickListener {
    private static final int TAKE_PHOTO_CODE = 123;
    private static final int TAKE_PHOTO_FROM_GALEYRY_CODE = 1233;
    private EditText title, price, status, content;
    private EditText name, phone, rePass, pass;

    private String sTitle, sStatus, sContent, sUserName, sPhone, sPass, sRePass;
    private String sPrice;

    private View picture;
    private ArrayList<View> pictures;
    private ArrayList<Bitmap> bitmaps;
    private TextViewRobotoRegular category, address;

    private Button btnSell;
    private DatabaseManager databaseManager;


    private Product product;

    AlertDialog.Builder builder;
    ArrayList<Address> listAddressGroup;
    ArrayList<Category> listCategoryGroup;
    private SellActivity sellActivity;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private boolean logined = false;
    private ImageView inputPic;

    private LinearLayout layoutImage;
    private long priceLong = 0;

    private Uri fileUri;
    private String pathPic = "";

    private ProgressDialog progressDialog;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getActivity() instanceof SellActivity) {
            sellActivity = (SellActivity) getActivity();
        }
        product = sellActivity.getProduct();


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        editor = sharedPreferences.edit();
        databaseManager = new DatabaseManager(getActivity());

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_input_product, container, false);
        title = (EditText) view.findViewById(R.id.title);
        category = (TextViewRobotoRegular) view.findViewById(R.id.category);
        address = (TextViewRobotoRegular) view.findViewById(R.id.address);
        price = (EditText) view.findViewById(R.id.price);
        status = (EditText) view.findViewById(R.id.status);
        content = (EditText) view.findViewById(R.id.content);
        btnSell = (Button) view.findViewById(R.id.btnSell);

        inputPic = (ImageView) view.findViewById(R.id.inputPic);

        name = (EditText) view.findViewById(R.id.name);
        pass = (EditText) view.findViewById(R.id.pass);
        rePass = (EditText) view.findViewById(R.id.rePass);
        phone = (EditText) view.findViewById(R.id.phone);

        name.setText(sharedPreferences.getString(Values.USER_NAME, ""));
        pass.setText(sharedPreferences.getString(Values.USER_PASSWORD, ""));
        rePass.setText(sharedPreferences.getString(Values.USER_PASSWORD, ""));
        phone.setText(sharedPreferences.getString(Values.USER_PHONENUMBER, ""));

        inputPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());


                builder.setItems(getActivity().getResources().getTextArray(R.array.take_photo), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                PictureUtils.getFromGallery(InputProductFragment.this, TAKE_PHOTO_FROM_GALEYRY_CODE);
                                break;
                            case 1:
                                pathPic = PictureUtils.saveToFile(InputProductFragment.this, TAKE_PHOTO_CODE);
                                break;
                        }
                    }
                });
                builder.show();


//                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(cameraIntent, TAKE_PHOTO_CODE);
            }
        });

        category.setOnClickListener(this);
        address.setOnClickListener(this);
        btnSell.setOnClickListener(this);


        price.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    if (priceLong == 0) {
                        price.setText("");
                    } else {
                        price.setText(priceLong + "");
                    }

                } else
                    try {
                        priceLong = Long.parseLong(price.getText().toString());
                        price.setText(PriceUtils.convertPriceHaveDot(priceLong));
                    } catch (Exception e) {

                    }
            }
        });


//        String user = sharedPreferences.getString(Values.USER, "");
//        if (user != null && !user.equalsIgnoreCase("")) {
//            Gson gson = new Gson();
//            User user1 = gson.fromJson(user, User.class);
//            if (user1 != null) {

//                logined = true;

//            } else {
        // user == null
//            logined = false;
//            btnSell.setText("Thêm thông tin liên hệ");
//            }
//        } else {
//            logined = false;
//            btnSell.setText("Thêm thông tin liên hệ");
//        }

        layoutImage = (LinearLayout) view.findViewById(R.id.layoutImage);
//        layoutImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(cameraIntent, TAKE_PHOTO_CODE);
////                dispatchTakePictureIntent();
//            }
//        });
        bitmaps = new ArrayList<>();
        pictures = new ArrayList<>();
        picture = LayoutInflater.from(getActivity()).inflate(R.layout.item_picture_sell, null);


        return view;
    }

    public void addPicture(final Bitmap bitmap) {
        final View picture = LayoutInflater.from(getActivity()).inflate(R.layout.item_picture_sell, null);
        ImageView imgPic = (ImageView) picture.findViewById(R.id.imgPic);
        imgPic.setImageBitmap(bitmap);
        ImageView imgClose = (ImageView) picture.findViewById(R.id.imgClose);
        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutImage.removeView(picture);
                bitmaps.remove(bitmap);
            }
        });
        pictures.add(imgPic);
        bitmaps.add(bitmap);
        if (picture.getParent() != null)
            ((ViewGroup) picture.getParent()).removeView(picture);


        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        layoutParams.setMargins(30, 20, 30, 0);
        layoutImage.addView(picture, layoutParams);
        layoutImage.postInvalidate();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap myBitmap = null;
        if (requestCode == TAKE_PHOTO_CODE && resultCode == Activity.RESULT_OK) {
            File imgFile = new File(pathPic);
            if (imgFile.exists()) {
                BitmapFactory.Options options = new BitmapFactory.Options();
                myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            }
        }
        if (requestCode == TAKE_PHOTO_FROM_GALEYRY_CODE && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getActivity().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            myBitmap = BitmapFactory.decodeFile(picturePath);

        }
//        int is = myBitmap.getWidth();
//        int i2s = myBitmap.getHeight();
//                Bitmap bitmap = Bitmap.createScaledBitmap(myBitmap,);
        int maxHeight = 500;
        int maxWidth = 500;
        float scale = 0;
        try {
            scale = Math.min(((float) maxHeight / myBitmap.getWidth()), ((float) maxWidth / myBitmap.getHeight()));
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);
        try {
            myBitmap = Bitmap.createBitmap(myBitmap, 0, 0, myBitmap.getWidth(), myBitmap.getHeight(), matrix, true);
            addPicture(myBitmap);
            Log.d("CameraDemo", "Pic saved");
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {

        builder = new AlertDialog.Builder(getActivity());
        ArrayAdapter arrayAdapter;
        switch (v.getId()) {
            case R.id.category:
                final ArrayList<Category.CategoryGroup> listGroupCategory = databaseManager.getCategoryGroup();

                arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, listGroupCategory);
                builder.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final ArrayList<Category> categories = databaseManager.getCategoryFromGroup(listGroupCategory.get(which).getIdGroup());
                        builder = new AlertDialog.Builder(getActivity());
//                        String group = ((listGroupCategory.get(which).getIdGroup()) + "").length() <= 1 ? "0" + (listGroupCategory.get(which).getIdGroup()) : (listGroupCategory.get(which).getIdGroup()) + "";
//                        id_category.append(group);
                        listCategoryGroup = databaseManager.getCategoryFromGroup(listGroupCategory.get(which).getIdGroup());
                        builder.setAdapter(new AdapterCategory(getActivity(), listCategoryGroup), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String idcategory = ((categories.get(which).getId()) + "").length() <= 1 ? "0" + (categories.get(which).getId()) : (categories.get(which).getId()) + "";
//                                id_category.append(idcategory);

                                product.setId_category(categories.get(which).getId());

                                category.setText(categories.get(which).getName() + "");
                            }
                        });
                        builder.show();
                    }
                });
                builder.show();
                break;
            case R.id.address:
                final ArrayList<Address.AddressGroup> listGroupAddress = databaseManager.getAddressGroup();
                arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, listGroupAddress);
                builder.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final ArrayList<Address> addresses = databaseManager.getAddressFromGroup(listGroupAddress.get(which).getIdGroup());
//                        String group = ((listGroupAddress.get(which).getIdGroup()) + "").length() <= 1 ? "0" + (listGroupAddress.get(which).getIdGroup()) : (listGroupAddress.get(which).getIdGroup()) + "";
//                        id_address.append(group);
                        builder = new AlertDialog.Builder(getActivity());
                        listAddressGroup = databaseManager.getAddressFromGroup(listGroupAddress.get(which).getIdGroup());
                        builder.setAdapter(new AdapterAddress(getActivity(), listAddressGroup), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String idAddress = ((addresses.get(which).getId()) + "").length() <= 1 ? "0" + ((addresses.get(which).getId())) : (addresses.get(which).getId()) + "";
//                                id_address.append(idAddress);
                                product.setId_address(addresses.get(which).getId());
                                address.setText(addresses.get(which).getName());
                            }
                        });
                        builder.show();
                    }
                });
                builder.show();
                break;

            case R.id.btnSell:


//                String url = null;
//                RequestQueue queue = Volley.newRequestQueue(getActivity());
//                final String encodeBitmap = PictureUtils.encode(bitmaps);
//                HashMap<String, String> detail = new HashMap<>();
//                detail.put("image", encodeBitmap);
//
////                    String s = PictureUtils.hashMapToUrl(detail);
//                    url = "http://192.168.1.41:8012/sell/uploadimage.php?";
////                    PictureUtils.post("http://192.168.1.41:8012/sell/uploadimage.php?" + s);
//
//
//
//// Request a string response from the provided URL.
//                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
//                        new Response.Listener<String>() {
//                            @Override
//                            public void onResponse(String response) {
//                                // Display the first 500 characters of the response string.
//                                Log.e("eeeee", response);
//                            }
//                        }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
////                        Log.e("eeeee", error.getMessage());
//                    }
//
//                }) {
//                    @Override
//                    protected Map<String, String> getParams() {
//                        Map<String, String> params = new HashMap<String, String>();
//                        params.put("image", encodeBitmap);
//                        return params;
//                    }
//
//                };
//// Add the request to the RequestQueue.
//                queue.add(stringRequest);

//                new AsyncTask<Void, Void, Void>() {
//
//                    @Override
//                    protected Void doInBackground(Void... params) {
//                        String encodeBitmap = PictureUtils.encode(bitmaps);
//                        HashMap<String, String> detail = new HashMap<>();
//                        detail.put("image", encodeBitmap);
//                        try {
//                            String s = PictureUtils.hashMapToUrl(detail);
//                            PictureUtils.post("http://192.168.1.41:8012/sell/uploadimage.php?" + s);
//                        } catch (UnsupportedEncodingException e) {
//                            e.printStackTrace();
//                        }
//
//
//                        return null;
//                    }
//                }.execute();


//                if (logined) {
                sTitle = title.getText().toString();
                sPrice = price.getText().toString();
                sStatus = status.getText().toString();
                sContent = content.getText().toString();

                sUserName = name.getText().toString();
                sPhone = phone.getText().toString();
                sPass = pass.getText().toString();
                sRePass = rePass.getText().toString();

                //Kiểm tra dữ liệu nhập vào
                if (sTitle.isEmpty() || sPrice.isEmpty() || sStatus.isEmpty() || sContent.isEmpty() || sUserName.isEmpty() || sPhone.isEmpty() || sPass.isEmpty() || sRePass.isEmpty()) {
                    Toast.makeText(getContext(), "Không được bỏ trống dữ liệu", Toast.LENGTH_SHORT).show();
                    break;
                }
                if (!sPass.equals(sRePass)) {
                    Toast.makeText(getContext(), "Mật khẩu nhập lại không giống nhau", Toast.LENGTH_SHORT).show();
                    break;
                }
                if (sTitle.split("").length == 0 || sPrice.split("").length == 0 || sStatus.split("").length == 0 || sContent.split("").length == 0 || sUserName.split("").length == 0 || sPhone.split("").length == 0 || sPass.split("").length == 0 || sRePass.split("").length == 0) {
                    Toast.makeText(getContext(), "Không được bỏ trống dữ liệu", Toast.LENGTH_SHORT).show();
                    break;
                }

                //check dieu kien
                ClientUtils clientUtils = new ClientUtils(getActivity());
                clientUtils.setUrl(Values.URL_SELL_PRODUCT);
                clientUtils.setOnRequestCompleted(new ClientUtils.OnRequestCompleted() {
                    @Override
                    public void onCompleted(String result) {
                        if (result.contains("success")) {
                            if (progressDialog != null && progressDialog.isShowing())
                                progressDialog.dismiss();
                            getActivity().finish();
                        }
                        Toast.makeText(getActivity(), result, Toast.LENGTH_LONG).show();
                    }
                });
                final String encodeBitmap = PictureUtils.encode(bitmaps);
                clientUtils.addParam(Values.PRODUCT_PICTURE, encodeBitmap);
                clientUtils.addParam(Values.PRODUCT_TITLE, sTitle);
                clientUtils.addParam(Values.PRODUCT_CATEGORY, product.getId_category());
                clientUtils.addParam(Values.PRODUCT_ADDRESS, product.getId_address());
                clientUtils.addParam(Values.PRODUCT_PRICE, priceLong + "");
                clientUtils.addParam(Values.PRODUCT_STATUS, sStatus);
                clientUtils.addParam(Values.PRODUCT_CONTENT, sContent);
                clientUtils.addParam(Values.USER_PASSWORD, DESUtils.encrypt(sPass, getActivity().getPackageName()));
                clientUtils.addParam(Values.USER_NAME, sUserName);
                clientUtils.addParam(Values.USER_PHONENUMBER, sPhone);
                clientUtils.query();

                progressDialog = new ProgressDialog(getActivity());
                progressDialog.setMessage("Vui lòng đợi...");
                progressDialog.show();

                editor.putString(Values.USER_NAME, sUserName);
                editor.putString(Values.USER_PHONENUMBER, sPhone);
                editor.putString(Values.USER_PASSWORD, sPass);
                editor.commit();


                if (sTitle == null || sTitle.length() <= 3) {

                } else {
                    product.setName(title.getText().toString());
                }
//                } else {
//                    SellActivity sellActivity = (SellActivity) getActivity();
//                    sellActivity.inputAccount();
//                }
//                ClientUtils clientUtils = new ClientUtils(getActivity());
//                clientUtils.sell(product, bitmaps);


                break;

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
            View view = LayoutInflater.from(getActivity()).inflate(android.R.layout.simple_list_item_1, null);
            AppCompatTextView tvName = (AppCompatTextView) view.findViewById(android.R.id.text1);
            tvName.setText(categories.get(position).getName());
            return view;
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
            View view = LayoutInflater.from(getActivity()).inflate(android.R.layout.simple_list_item_1, null);
            AppCompatTextView tvName = (AppCompatTextView) view.findViewById(android.R.id.text1);
            tvName.setText(addresses.get(position).getName());
            return view;
        }
    }

    String mCurrentPhotoPath;

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);

        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(photoFile));
                startActivityForResult(takePictureIntent, TAKE_PHOTO_CODE);
            }
        }
    }
}
