package com.example.aalizade.mbazar_base_app.adapters.recycler_adapters.product_related_adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.entities.FilterTypeItemEntity;

import java.util.List;

public class FilterTypeItemAdapter extends RecyclerView.Adapter<FilterTypeItemAdapter.NewsViewHolder> {

    private Context context;
    private List<FilterTypeItemEntity> filters;

    public FilterTypeItemAdapter(Context context, List<FilterTypeItemEntity> filters) {
        this.context = context;
        this.filters = filters;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.filter_type_item_designed_item, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final NewsViewHolder holder, final int position) {
        final FilterTypeItemEntity filterSubItem = filters.get(position);

        holder.typeSubItemName.setText(filterSubItem.getItemName());

        holder.clickableLyaout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.typeSubItemBox.isChecked()){
                    holder.typeSubItemBox.setChecked(false);
                }else {
                    holder.typeSubItemBox.setChecked(true);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return filters.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        private TextView typeSubItemName;
        private LinearLayout clickableLyaout;
        private CheckBox typeSubItemBox;

        public NewsViewHolder(View itemView) {
            super(itemView);
            typeSubItemName = (TextView) itemView.findViewById(R.id.type_subitem_name_txt_id);
            clickableLyaout = (LinearLayout) itemView.findViewById(R.id.type_subitems_backgrnd_layout_id);
            typeSubItemBox = (CheckBox) itemView.findViewById(R.id.filter_sub_item_checkbox);
        }
    }


}