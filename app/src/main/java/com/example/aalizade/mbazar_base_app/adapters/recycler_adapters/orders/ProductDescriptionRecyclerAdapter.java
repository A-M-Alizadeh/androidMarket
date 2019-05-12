package com.example.aalizade.mbazar_base_app.adapters.recycler_adapters.orders;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.network.models.checkout.ProductDescriptionModel;

import java.util.List;

public class ProductDescriptionRecyclerAdapter extends RecyclerView.Adapter<ProductDescriptionRecyclerAdapter.BonsViewHolder> {


    private Activity activity;
    private List<ProductDescriptionModel> productModels;

    public ProductDescriptionRecyclerAdapter(Activity activity, List<ProductDescriptionModel> productModels){
        this.activity = activity;
        this.productModels = productModels;
    }
    @Override
    public ProductDescriptionRecyclerAdapter.BonsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.product_details_listview_item,parent,false);

        return new ProductDescriptionRecyclerAdapter.BonsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductDescriptionRecyclerAdapter.BonsViewHolder holder, final int position) {
        ProductDescriptionModel bon = productModels.get(position);
        holder.productTypeId.setText(bon.getProductTypeId());
        holder.productTypeTitle.setText(bon.getProductTypeTitle());
        holder.taxRate.setText(bon.getTaxRate());
        holder.productTypeUnitPriceTaxInclude.setText(bon.getProductTypeUnitPriceTaxInclude());
        holder.discountTotalPercentage.setText(bon.getDiscountTotalPercentage());
        holder.productTypeQuantityPrice.setText(bon.getProductTypeQuantityPrice());
        holder.verndorId.setText(bon.getVendorId());
        holder.quantity.setText(bon.getQuantity());
    }

    @Override
    public int getItemCount() {
        return productModels.size();
    }

    public class BonsViewHolder extends RecyclerView.ViewHolder{
        private TextView productTypeId,productTypeTitle,taxRate,productTypeUnitPriceTaxInclude,discountTotalPercentage,productTypeQuantityPrice,verndorId,quantity;

        public BonsViewHolder(View itemView) {
            super(itemView);
            productTypeId = (TextView)itemView.findViewById(R.id.product_details_langKey_id);
            productTypeTitle = (TextView)itemView.findViewById(R.id.product_details_title_txt);
            taxRate = (TextView)itemView.findViewById(R.id.product_details_tax_percent);
            productTypeUnitPriceTaxInclude = (TextView)itemView.findViewById(R.id.product_details_UnitPriceTaxInclude);
            discountTotalPercentage = (TextView)itemView.findViewById(R.id.product_details_discountTotalPercentage);
            productTypeQuantityPrice = (TextView)itemView.findViewById(R.id.product_details_QuantityPrice);
            verndorId = (TextView)itemView.findViewById(R.id.product_details_vendor_txt);
            quantity = (TextView)itemView.findViewById(R.id.product_details_quantity);
        }
    }

}