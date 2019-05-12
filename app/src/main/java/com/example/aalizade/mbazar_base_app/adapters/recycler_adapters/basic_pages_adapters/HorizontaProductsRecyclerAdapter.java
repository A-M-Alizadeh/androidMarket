package com.example.aalizade.mbazar_base_app.adapters.recycler_adapters.basic_pages_adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.activities.basic_parts.AnewerMasterPageActivity;
import com.example.aalizade.mbazar_base_app.activities.products_related.ProductMainPageActivity;
import com.example.aalizade.mbazar_base_app.utility.GlobalVariables;
import com.example.aalizade.mbazar_base_app.network.models.product.ProductTypeBriefFrontModel;

import java.util.List;

/**
 * Created by aalizade on 10/25/2017.
 */

public class HorizontaProductsRecyclerAdapter extends RecyclerView.Adapter<HorizontaProductsRecyclerAdapter.NewsViewHolder> {

    private Context context;
    private List<ProductTypeBriefFrontModel> products;
//    RequestOptions options;
    String prd_img_url = "";
    RequestOptions options;
//    String selectedCityId;

    public HorizontaProductsRecyclerAdapter(Context context, List<ProductTypeBriefFrontModel> products){
        this.context = context;
        prd_img_url = context.getString(R.string.small_cover_url);
        this.products = products;
        options = new RequestOptions()
                .centerCrop()
                .override(100,100)
                .placeholder(R.drawable.mbazar_128p_gray_logo)
                .error(R.drawable.error_getting_data_from_server_128_png);
    }
    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.horizontal_scroll_card_design,parent,false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, final int position) {
        final ProductTypeBriefFrontModel singleProduct = products.get(position);

        String img_url = prd_img_url.replace("{id}", singleProduct.getId().toString());

        Glide.with(context)
                .load(img_url)
                .apply(options)
                .into(holder.productImage);

        holder.productNameTxt.setText(singleProduct.getTitle());
        holder.depricatedPriceTv.setText(String.valueOf(singleProduct.getUnitPriceTaxInclude()));
        holder.priceTV.setText(String.valueOf(singleProduct.getProductTypeUnitPriceTaxIncludeDiscountInclude().substring(0,singleProduct.getProductTypeUnitPriceTaxIncludeDiscountInclude().toString().lastIndexOf("."))));
        holder.depricatedPriceTv.setPaintFlags(holder.depricatedPriceTv.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        if (holder.depricatedPriceTv.getText().equals(holder.priceTV.getText())){
            holder.toomanTxt.setVisibility(View.GONE);
            holder.depricatedPriceTv.setVisibility(View.GONE);
        }

        holder.touchLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (GlobalVariables.selectedCity == null){
//                    Toast.makeText(context,"ابتدا ویترین مورد نظر را انتخاب کنید",Toast.LENGTH_SHORT).show();
                    AnewerMasterPageActivity.ShowCitySelecteionDialog();
                }else {
                    Intent intent = new Intent(context, ProductMainPageActivity.class);
                    GlobalVariables.selectedProductID = singleProduct.getId();
                    context.startActivity(intent);
                }
            }
        });
        holder.productImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (GlobalVariables.selectedCity == null){
//                    Toast.makeText(context,"ابتدا ویترین مورد نظر را انتخاب کنید",Toast.LENGTH_SHORT).show();
                    AnewerMasterPageActivity.ShowCitySelecteionDialog();
                }else {
                    Intent intent = new Intent(context, ProductMainPageActivity.class);
                    GlobalVariables.selectedProductID = singleProduct.getId();
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder{
        private ImageView productImage;
        private TextView productNameTxt,depricatedPriceTv,priceTV,toomanTxt;
        private RelativeLayout touchLayout;

        public NewsViewHolder(View itemView) {
            super(itemView);
            productImage =(ImageView)itemView.findViewById(R.id.card_image);
            productNameTxt=(TextView)itemView.findViewById(R.id.prd_name_txt_id);
            depricatedPriceTv=(TextView)itemView.findViewById(R.id.prd_depricated_price_txt_id);
            priceTV=(TextView)itemView.findViewById(R.id.prd_price_txt_id);
            toomanTxt=(TextView)itemView.findViewById(R.id.tooman_id);
            touchLayout=(RelativeLayout) itemView.findViewById(R.id.touch_layout_id);
        }
    }
}
