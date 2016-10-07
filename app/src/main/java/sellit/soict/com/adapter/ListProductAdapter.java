package sellit.soict.com.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Locale;

import sellit.soict.com.R;
import sellit.soict.com.model.BaseProduct;
import sellit.soict.com.ui.DetailProductActivity;
import sellit.soict.com.utils.DateUtil;
import sellit.soict.com.utils.PriceUtils;
import sellit.soict.com.utils.Values;
import sellit.soict.com.view.TextViewRobotoLight;
import sellit.soict.com.view.TextViewRobotoMedium;
import sellit.soict.com.view.TextViewRobotoRegular;

/**
 * Created by Dang Hien on 07/04/2016.
 */
public class ListProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<BaseProduct> listProduct;

    public ListProductAdapter(Context context, ArrayList<BaseProduct> listProduct) {
        this.context = context;
        this.listProduct = listProduct;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgPicture;
        private TextViewRobotoRegular tvName;
        private TextViewRobotoRegular countPic;
        private TextViewRobotoMedium tvPrice;
        private TextViewRobotoLight tvDate;
        private LinearLayout root;

        public ItemViewHolder(View itemView) {
            super(itemView);
            imgPicture = (ImageView) itemView.findViewById(R.id.imgPicture);
            tvName = (TextViewRobotoRegular) itemView.findViewById(R.id.tvName);
            countPic = (TextViewRobotoRegular) itemView.findViewById(R.id.countPic);
            tvPrice = (TextViewRobotoMedium) itemView.findViewById(R.id.tvPrice);
            tvDate = (TextViewRobotoLight) itemView.findViewById(R.id.tvDate);
            root = (LinearLayout) itemView.findViewById(R.id.root);

            root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DetailProductActivity.class);
                    intent.putExtra(Values.ID, listProduct.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                   // Toast.makeText(context, listProduct.get(getAdapterPosition()).getName(), Toast.LENGTH_SHORT).show();
                }
            });

        }

        public void setPicture(String url) {

            Glide.with(context).load(url).into(imgPicture);
        }

        public void setName(String name) {
            this.tvName.setText(name);
        }

        public void setCountPic(int count) {
            this.countPic.setText(count + "");
        }

        public void setPrice(long price) {
            this.tvPrice.setText(PriceUtils.convertPriceHaveDot(price) + " đ");
        }

        public void setDate(long date) {
            this.tvDate.setText(DateUtil.getExtendedRelativeTimeSpanString(context, new Locale("vi"), date) + "");
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.item_product, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            BaseProduct product = listProduct.get(position);
            ((ItemViewHolder) holder).setPicture(Values.URL_SERVER_PICTURE+product.getPictures());
            ((ItemViewHolder) holder).setName(product.getName());
            ((ItemViewHolder) holder).setPrice(product.getPrice());
            ((ItemViewHolder) holder).setDate(product.getDate());
            ((ItemViewHolder) holder).setCountPic(product.getCount_pic());
        }
    }


    @Override
    public int getItemCount() {
        return listProduct.size();
    }
}


