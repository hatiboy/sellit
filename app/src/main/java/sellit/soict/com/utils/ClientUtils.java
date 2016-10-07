package sellit.soict.com.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import sellit.soict.com.model.Product;

/**
 * Created by Dang Hien on 15/04/2016.
 */
public class ClientUtils {
    private Context context;
    private RequestQueue queue;
    private Map<String, String> params;
    private String url;

    public interface OnRequestCompleted {
        public void onCompleted(String result);
    }

    public ClientUtils(Context context) {
        this.context = context;
        queue = Volley.newRequestQueue(context);
        params = new HashMap<String, String>();
    }

    private OnRequestCompleted onRequestCompleted;

    public OnRequestCompleted getOnRequestCompleted() {
        return onRequestCompleted;
    }

    public void setOnRequestCompleted(OnRequestCompleted onRequestCompleted) {
        this.onRequestCompleted = onRequestCompleted;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void addParam(String key, String values) {
        if (params == null) {
            params = new HashMap<>();
        }
        params.put(key, values);
    }


    public void query() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (onRequestCompleted != null) {
                            onRequestCompleted.onCompleted(response);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }

        }) {
            @Override
            protected Map<String, String> getParams() {
                return params;
            }

        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                1000000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(stringRequest);
    }


    public void sell(final Product product, ArrayList<Bitmap> bitmaps) {
        String url = null;
        final String encodeBitmap = PictureUtils.encode(bitmaps);
        url = "http://192.168.1.37:8012/sell/insert.php?";
// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Toast.makeText(context, "Success !", Toast.LENGTH_SHORT).show();
                        Log.e("eeeee", response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                        Log.e("eeeee", error.getMessage());
            }

        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put(Values.PRODUCT_PICTURE, encodeBitmap);
                params.put(Values.PRODUCT_TITLE, product.getName());
                return params;
            }
        };
// Add the request to the RequestQueue.
        queue.add(stringRequest);

    }


    public void getListProduct(String idCategory, String id_address, String text) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = null;
        url = "http://192.168.1.47/datn/getproduct.php?";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if (onRequestCompleted != null) {
                            onRequestCompleted.onCompleted(response);
                        }

                        // Display the first 500 characters of the response string.
                        Toast.makeText(context, "Success !", Toast.LENGTH_SHORT).show();
                        Log.e("eeeee", response);
//                        try {
//                            JSONArray jsonArray = new JSONArray(response);
//                            Gson gson = new Gson();
//                            ArrayList<BaseProduct> baseProducts = new ArrayList<>();
//                            for (int i = 0; i < jsonArray.length(); i++) {
//                                BaseProduct baseProduct = gson.fromJson(jsonArray.get(i).toString(), BaseProduct.class);
//                                baseProducts.add(baseProduct);
//                            }
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                        Log.e("eeeee", error.getMessage());
            }

        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
//                params.put(Values.PRODUCT_CATEGORY_GROUP, 5 + "");
////                params.put(Values.PRODUCT_CATEGORY_GROUP, product.getCategory().getCategoryGroup().getIdGroup() + "");
////                params.put(Values.PRODUCT_CATEGORY, product.getCategory().getId() + "");
//                params.put(Values.PRODUCT_CATEGORY, 1 + "");
//                params.put(Values.PRODUCT_ADDRESS_GROUP, product.getAddress().getAddressGroup().getIdGroup() + "");
//                params.put(Values.PRODUCT_ADDRESS_GROUP, 4 + "");
//                params.put(Values.PRODUCT_ADDRESS, 3 + "");
////                params.put(Values.PRODUCT_ADDRESS, product.getAddress().getId() + "");
//                params.put(Values.PRODUCT_PRICE, product.getPrice() + "");
//                params.put(Values.PRODUCT_DATE, product.getDate() + "");
//                params.put(Values.PRODUCT_CONTENT, product.getContent() + "");
//                params.put(Values.PRODUCT_STATUS, product.getStatus() + "");
//                params.put(Values.USER_ID, 6+ "");
//                params.put(Values.USER_ID, product.getUser().getId() + "");

                return params;
            }

        };


// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }


}
