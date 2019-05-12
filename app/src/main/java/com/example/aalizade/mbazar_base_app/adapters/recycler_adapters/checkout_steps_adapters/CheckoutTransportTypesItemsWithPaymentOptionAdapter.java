package com.example.aalizade.mbazar_base_app.adapters.recycler_adapters.checkout_steps_adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.network.apiInterface.CartAPIInterface;
import com.example.aalizade.mbazar_base_app.network.models.cart.CartPaymentSystemModel;
import com.example.aalizade.mbazar_base_app.network.models.cart.CartProductTypeModel;
import com.example.aalizade.mbazar_base_app.network.retrofit.RetrofitOAuthClient;

import java.util.List;


/**
 * Created by aalizade on 11/13/2017.
 */

public class CheckoutTransportTypesItemsWithPaymentOptionAdapter extends RecyclerView.Adapter<CheckoutTransportTypesItemsWithPaymentOptionAdapter.NewsViewHolder> {
    private Context context;
    private List<CartProductTypeModel> products;
    //    private List<BarginTitlePercent> bargins;
    private Boolean isVisible = true;
    Animation fadeIn;
    RequestOptions options;
    String prd_img_url;
    Activity activity;
    CartAPIInterface cartAPIInterface;
    LinearLayout linearLayout;


    public CheckoutTransportTypesItemsWithPaymentOptionAdapter(Context context, List<CartProductTypeModel> products, LinearLayout linearLayout) {
        fadeIn = AnimationUtils.loadAnimation(context, R.anim.fade_in);
        this.context = context;
        prd_img_url = context.getString(R.string.small_cover_url);
        this.products = products;
        options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.mbazar_128p_gray_logo)
                .override(64, 64)
                .error(R.drawable.error_getting_data_from_server_128_png);
        this.activity = (Activity) context;
        cartAPIInterface = RetrofitOAuthClient.getOauthClient(context, linearLayout).create(CartAPIInterface.class);
        this.linearLayout = linearLayout;
    }

    @Override
    public CheckoutTransportTypesItemsWithPaymentOptionAdapter.NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.a_temp_transport_basket_with_payoption_item, parent, false);
        return new CheckoutTransportTypesItemsWithPaymentOptionAdapter.NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CheckoutTransportTypesItemsWithPaymentOptionAdapter.NewsViewHolder holder, final int position) {
        final CartProductTypeModel product = products.get(position);

        String img_url = prd_img_url.replace("{id}", product.getProductType_id());
        Glide.with(context)
                .load(img_url)
                .apply(options)
                .into(holder.productImage);

        holder.faName.setText(product.getProductType_title());

        holder.vendorName_txt.setText(product.getProductType_vendor_name());
        holder.quantity_txt.setText(product.getQuantity());

        //set prices
        holder.depricatedPriceWithTax.setText(product.getProductTypeQuantityPrice());
        holder.priceWithDiscountandTax.setText(String.valueOf(Integer.parseInt(product.getProductType_unitPriceTaxInclude()) * Integer.parseInt(product.getQuantity())));
        holder.unitPriceWithTaxandDFiscount.setText(product.getProductType_unitPriceTaxInclude());
        holder.depricatedUnitPriceWithTaxOnly.setText(product.getProductTypeUnitPriceTaxIncludeDiscountInclude());
        //set prices
        if (product.getDiscountTotalPercentage().equals("0.0")) {
            holder.discountLayout.setVisibility(View.GONE);
            holder.depricatedPriceWithTax.setVisibility(View.GONE);
            holder.depricatedUnitPriceWithTaxOnly.setVisibility(View.GONE);
        } else {
            holder.discountNames.setText(product.getDiscountVendorTitle() + " " + product.getDiscountMBazarTitle());
            holder.totalDiscountPercentage.setText(product.getDiscountTotalPercentage() + "%");
        }

        holder.pricemenusTrigerlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holder.expand_arrow.setRotation(holder.expand_arrow.getRotation() + 180);
                isVisible = !isVisible;
                if (isVisible) {
                    holder.pricemenusLayout.setVisibility(View.GONE);
                } else {
                    holder.pricemenusLayout.setVisibility(View.VISIBLE);
                    holder.pricemenusLayout.startAnimation(fadeIn);
                }
            }
        });

        for (CartPaymentSystemModel c : product.getPaymentSystemCartModelSet()){
            holder.paymentOptionsTV.setText(holder.paymentOptionsTV.getText()+c.getTitle()+" / ");
        }
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        private ImageView productImage;

        private TextView depricatedPriceWithTax, priceWithDiscountandTax;
        private TextView unitPriceWithTaxandDFiscount, depricatedUnitPriceWithTaxOnly;

        private TextView faName, vendorName_txt, quantity_txt;
        //        private TextView productPrice;
        private TextView discountNames;
        private TextView depricatedPrice, depricatedPriceWithDiscount;
        //        private RecyclerView barginTitleRecycler;
        private ImageView expand_arrow;
        private RelativeLayout pricemenusTrigerlayout, pricemenusLayout;

        private LinearLayout discountLayout;
        private TextView totalDiscountPercentage;
        private TextView paymentOptionsTV;

        public NewsViewHolder(View itemView) {
            super(itemView);
            productImage = (ImageView) itemView.findViewById(R.id.shopping_card_item_img_id);
            faName = (TextView) itemView.findViewById(R.id.shopping_card_item_fa_name_txt_id);
//            productPrice = (TextView) itemView.findViewById(R.id.shopping_card_item_price_txt_id);
//            barginTitleRecycler = (RecyclerView) itemView.findViewById(R.id.bargin_title_recycler_id);
            discountNames = (TextView) itemView.findViewById(R.id.discount_names_txt_id);

            vendorName_txt = (TextView) itemView.findViewById(R.id.producer_name_txt_id);
            quantity_txt = (TextView) itemView.findViewById(R.id.quantity_txt_id);

            //prices
            depricatedPriceWithTax = (TextView) itemView.findViewById(R.id.depricated_price_txt_id);
            priceWithDiscountandTax = (TextView) itemView.findViewById(R.id.price_withtaxanddiscount_txt_id);
            unitPriceWithTaxandDFiscount = (TextView) itemView.findViewById(R.id.shopping_card_item_price_with_tax_and_discount_txt_id);
            depricatedUnitPriceWithTaxOnly = (TextView) itemView.findViewById(R.id.shopping_card_item_price_txt_id);
            //prices
            //price for quantity
            depricatedPriceWithTax.setPaintFlags(depricatedPriceWithTax.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            depricatedUnitPriceWithTaxOnly.setPaintFlags(depricatedUnitPriceWithTaxOnly.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            //price for quantity

            expand_arrow = (ImageView) itemView.findViewById(R.id.expand_arrow_img_id);
            pricemenusTrigerlayout = (RelativeLayout) itemView.findViewById(R.id.price_sliding_menu_triger_layout_id);
            pricemenusLayout = (RelativeLayout) itemView.findViewById(R.id.prices_liding_list_layout_id);

            totalDiscountPercentage = (TextView) itemView.findViewById(R.id.discount_number_txt_id);
            paymentOptionsTV = (TextView) itemView.findViewById(R.id.payment_options_txt_id);
            discountLayout = (LinearLayout) itemView.findViewById(R.id.discount_layout_id);
        }
    }

//    private void SetRecyclerAdapter(RecyclerView recyclerView,List<BarginTitlePercent> bargins) {
//        ShoppingCardBarginListAdapter newsAdapter = new ShoppingCardBarginListAdapter(context, bargins);
//        GridLayoutManager myGridLayoutManager = new GridLayoutManager(context, 2);
//        recyclerView.setLayoutManager(myGridLayoutManager);
//        recyclerView.setAdapter(newsAdapter);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setItemViewCacheSize(20);
//        recyclerView.setDrawingCacheEnabled(true);
//        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
//        recyclerView.setNestedScrollingEnabled(false);
//    }

    public void update(List<CartProductTypeModel> data) {
//        degrees.clear();
        products = data;
        notifyDataSetChanged();
    }

    public void removeAt(int position) {
        products.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, products.size());
    }
}
