package com.example.aalizade.mbazar_base_app.adapters.recycler_adapters.department_hierarchy_adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.network.models.DepartmentCustomModel;

import java.util.List;

public class DepartmentsOUTERAdapter extends RecyclerView.Adapter<DepartmentsOUTERAdapter.NewsViewHolder> {

    private Context context;
    List<DepartmentCustomModel> outerList;
    RelativeLayout relativeLayout;

    public DepartmentsOUTERAdapter(Context context, List<DepartmentCustomModel> outerList, RelativeLayout relativeLayout) {
        this.context = context;
        this.outerList = outerList;
//        activity = (Activity) context;
        this.relativeLayout = relativeLayout;
    }

    @Override
    public DepartmentsOUTERAdapter.NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.department_header_layout, parent, false);
        return new DepartmentsOUTERAdapter.NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DepartmentsOUTERAdapter.NewsViewHolder holder, final int position) {
        DepartmentCustomModel innerListWrapper = outerList.get(position);

        holder.title.setText(innerListWrapper.getTitle());

        DepartmentINNERAdapter newsAdapter = new DepartmentINNERAdapter(context, innerListWrapper.getSubItems(),relativeLayout);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        holder.outerRecler.setLayoutManager(linearLayoutManager);
        holder.outerRecler.setAdapter(newsAdapter);
        holder.outerRecler.setHasFixedSize(true);
        holder.outerRecler.setItemViewCacheSize(20);
        holder.outerRecler.setDrawingCacheEnabled(true);
        holder.outerRecler.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        holder.outerRecler.setNestedScrollingEnabled(false);


    }

    @Override
    public int getItemCount() {
        return outerList.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private RecyclerView outerRecler;

        public NewsViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.wrapper_header_title_txt_id);
            outerRecler = (RecyclerView) itemView.findViewById(R.id.outer_recycler_id);
        }
    }

    public void update(List<DepartmentCustomModel> data) {
//        degrees.clear();
        outerList = data;
        notifyDataSetChanged();
    }

    public void removeAt(int position) {
        outerList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, outerList.size());
    }

}