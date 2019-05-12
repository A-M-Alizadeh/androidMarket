package com.example.aalizade.mbazar_base_app.adapters.recycler_adapters.product_related_adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.entities.FilterTypeEntity;

import java.util.List;

public class FilterTypeAdapter extends RecyclerView.Adapter<FilterTypeAdapter.NewsViewHolder> {

    private Context context;
    private List<FilterTypeEntity> filters;
    private static int row_index = 0;
    private static int survivor = 0;

    public FilterTypeAdapter(Context context, List<FilterTypeEntity> filters) {
        this.context = context;
        this.filters = filters;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.filter_type_designed_item, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final NewsViewHolder holder, final int position) {
        final FilterTypeEntity filter = filters.get(position);

        holder.typeName.setText(filter.getName());

        holder.clickableLyaout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, String.valueOf(holder.getLayoutPosition() + " # " + holder.getAdapterPosition() + " @ " + survivor), Toast.LENGTH_SHORT).show();

                //new select
                row_index = position;
                notifyDataSetChanged();
                //new select

                survivor = position;
            }
        });
        //new select
        if (row_index == position) {
            holder.typeName.setTextColor(Color.BLACK);
            holder.clickableLyaout.setBackgroundColor(Color.WHITE);
        } else {
            holder.typeName.setTextColor(Color.WHITE);
            holder.clickableLyaout.setBackgroundColor(Color.BLACK);
        }
        //new select

    }

    //new select
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv1;
        LinearLayout row_linearlayout;
        RecyclerView rv2;

        public ViewHolder(final View itemView) {
            super(itemView);
            tv1=(TextView)itemView.findViewById(R.id.type_name_txt_id);
            row_linearlayout=(LinearLayout)itemView.findViewById(R.id.type_item_backgrnd_layout_id);
            rv2=(RecyclerView)itemView.findViewById(R.id.filter_types_recycler_id);
        }
    }
    //new select

    @Override
    public int getItemCount() {
        return filters.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        private TextView typeName;
        private LinearLayout clickableLyaout;

        public NewsViewHolder(View itemView) {
            super(itemView);
            typeName = (TextView) itemView.findViewById(R.id.type_name_txt_id);
            clickableLyaout = (LinearLayout) itemView.findViewById(R.id.type_item_backgrnd_layout_id);
        }
    }


}