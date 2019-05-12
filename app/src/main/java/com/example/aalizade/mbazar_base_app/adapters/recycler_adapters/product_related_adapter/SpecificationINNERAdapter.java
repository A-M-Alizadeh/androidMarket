package com.example.aalizade.mbazar_base_app.adapters.recycler_adapters.product_related_adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.network.models.attributes.AttributeValueFrontModel;
import com.example.aalizade.mbazar_base_app.network.models.attributes.FullAttributeGroupFrontModel;
import com.example.aalizade.mbazar_base_app.network.models.attributes.FullAttributeTitleFrontModel;
import com.example.aalizade.mbazar_base_app.network.models.product.ProductSpecificationListCustomModel;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class SpecificationINNERAdapter extends RecyclerView.Adapter<SpecificationINNERAdapter.NewsViewHolder> {

    Context context;
    private List<AttributeValueFrontModel> specs;
    private int lastPosition = -1;

    public SpecificationINNERAdapter(Context context, List<AttributeValueFrontModel> specs) {
        this.context = context;
        this.specs = specs;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.specification_list_designed_item, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final NewsViewHolder holder, final int position) {
        final AttributeValueFrontModel spec = specs.get(position);

        holder.specName.setText(spec.getAttributeTitle_title());
        holder.specValue.setText(spec.getValue());

    }

    @Override
    public int getItemCount() {
        return specs.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        private TextView specName;
        private TextView specValue;

        public NewsViewHolder(View itemView) {
            super(itemView);
            specName = (TextView) itemView.findViewById(R.id.specification_name_txt_id);
            specValue = (TextView) itemView.findViewById(R.id.specification_value_txt_id);
        }
    }

    //setting animation
    private void setAnimation(View viewToAnimate, int position)
    {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    //setting animation


}