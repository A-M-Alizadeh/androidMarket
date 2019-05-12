package com.example.aalizade.mbazar_base_app.adapters.recycler_adapters.basic_pages_adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.entities.BigViewProduct;

import java.util.List;

/**
 * Created by aalizade on 10/25/2017.
 */

public class HomegridSpecialplansAdapter extends RecyclerView.Adapter<HomegridSpecialplansAdapter.NewsViewHolder> {

    private Context context;
    private List<BigViewProduct> plans;

    public HomegridSpecialplansAdapter(Context context, List<BigViewProduct> plans){
        this.context = context;
        this.plans = plans;
    }
    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.master_page_plan_item_layout,parent,false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, final int position) {
        BigViewProduct singleItem=plans.get(position);
        holder.title.setText(singleItem.getFa_name());
        holder.image.setImageDrawable(singleItem.getProductImage());
    }

    @Override
    public int getItemCount() {
        return plans.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private CardView cardView;
        private ImageView image;

        public NewsViewHolder(View itemView) {
            super(itemView);
            title=(TextView)itemView.findViewById(R.id.plan_name_txt_id);
            cardView=(CardView) itemView.findViewById(R.id.cardview_bkgrnd_id);
            image=(ImageView) itemView.findViewById(R.id.plan_img_id);
        }
    }
}
