package com.example.aalizade.mbazar_base_app.adapters.recycler_adapters.product_related_adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.network.models.product.VendorOtherConditionsCustomModel;

import java.util.List;

public class vendorsOtherConditionsAdapter extends RecyclerView.Adapter<vendorsOtherConditionsAdapter.NewsViewHolder> {

    private Context context;
    private List<VendorOtherConditionsCustomModel> conditions;

    public vendorsOtherConditionsAdapter(Context context, List<VendorOtherConditionsCustomModel> conditions) {
        this.context = context;
        this.conditions = conditions;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.vendor_other_condition_recycler_item_layout, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final NewsViewHolder holder, final int position) {
        final VendorOtherConditionsCustomModel item = conditions.get(position);

        holder.guaranyName.setText(item.getGuarantyName());
        holder.colorName.setText(item.getColorName());
        holder.priceTxt.setText(item.getPrice());

        holder.clickableLyaout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,item.getGuarantyName()+" "+item.getColorName()+" "+item.getPrice(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return conditions.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        private TextView guaranyName,colorName,priceTxt;
        private LinearLayout clickableLyaout;

        public NewsViewHolder(View itemView) {
            super(itemView);
            guaranyName = (TextView) itemView.findViewById(R.id.guranty_txt_id);
            colorName = (TextView) itemView.findViewById(R.id.color_txt_id);
            priceTxt = (TextView) itemView.findViewById(R.id.price_txt_id);
            clickableLyaout = (LinearLayout) itemView.findViewById(R.id.touch_layout_id);
        }
    }


}