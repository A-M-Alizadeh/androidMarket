package com.example.aalizade.mbazar_base_app.adapters.recycler_adapters.product_related_adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.entities.BriefSpecificationEntity;

import java.util.ArrayList;
import java.util.List;

public class ProductBriefDescriptionAdapter extends RecyclerView.Adapter<ProductBriefDescriptionAdapter.specificationViewHolder> {

    private Context context;
    private List<BriefSpecificationEntity> specificationEntities;

    public ProductBriefDescriptionAdapter(Context context, List<BriefSpecificationEntity> data){
        this.context = context;
        this.specificationEntities = data;
    }
    @Override
    public ProductBriefDescriptionAdapter.specificationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.product_brief_specification_designed_item,parent,false);
        return new ProductBriefDescriptionAdapter.specificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductBriefDescriptionAdapter.specificationViewHolder holder, final int position) {
        BriefSpecificationEntity degree=specificationEntities.get(position);
        holder.brief_spec_title.setText(degree.getSpecTitle());
        holder.brief_spec_value.setText(degree.getSpecValue());

    }

    @Override
    public int getItemCount() {
        return specificationEntities.size();
    }

    public class specificationViewHolder extends RecyclerView.ViewHolder{
        private TextView brief_spec_title;
        private TextView brief_spec_value;

        public specificationViewHolder(View itemView) {
            super(itemView);
            brief_spec_title=(TextView)itemView.findViewById(R.id.specification_title_txt_id);
            brief_spec_value=(TextView)itemView.findViewById(R.id.specification_value_txt_id);
        }
    }

    public void update(ArrayList<BriefSpecificationEntity> data) {
//        degrees.clear();
        specificationEntities = data;
        notifyDataSetChanged();
    }

    public void removeAt(int position) {
        specificationEntities.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, specificationEntities.size());
    }

}