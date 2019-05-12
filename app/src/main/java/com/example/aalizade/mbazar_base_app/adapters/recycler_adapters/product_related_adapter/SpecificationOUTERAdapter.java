package com.example.aalizade.mbazar_base_app.adapters.recycler_adapters.product_related_adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.network.models.product.ProductSpecificationListCustomModel;

import java.util.List;

public class SpecificationOUTERAdapter extends RecyclerView.Adapter<SpecificationOUTERAdapter.NewsViewHolder> {

    Context context;
    private List<ProductSpecificationListCustomModel> specsWrapper;
    private int lastPosition = -1;

    public SpecificationOUTERAdapter(Context context, List<ProductSpecificationListCustomModel> specsWrapper) {
        this.context = context;
        this.specsWrapper = specsWrapper;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.specification_titles_designed_item, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final NewsViewHolder holder, final int position) {
        final ProductSpecificationListCustomModel singleWrapper = specsWrapper.get(position);

        holder.title.setText(singleWrapper.getTitle());
        SpecificationINNERAdapter newsAdapter = new SpecificationINNERAdapter(context, singleWrapper.getSpecs());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        holder.specItemsRecycler.setLayoutManager(linearLayoutManager);
        holder.specItemsRecycler.setAdapter(newsAdapter);
        holder.specItemsRecycler.setHasFixedSize(true);
        holder.specItemsRecycler.setItemViewCacheSize(20);
        holder.specItemsRecycler.setDrawingCacheEnabled(true);
        holder.specItemsRecycler.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        holder.specItemsRecycler.setNestedScrollingEnabled(false);
    }

    @Override
    public int getItemCount() {
        return specsWrapper.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private RecyclerView specItemsRecycler;

        public NewsViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.part_title_txt_id);
            specItemsRecycler = (RecyclerView) itemView.findViewById(R.id.specs_items_recycler_id);
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