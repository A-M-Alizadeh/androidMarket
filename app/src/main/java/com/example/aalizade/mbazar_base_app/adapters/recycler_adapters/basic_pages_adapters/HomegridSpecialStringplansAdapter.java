package com.example.aalizade.mbazar_base_app.adapters.recycler_adapters.basic_pages_adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aalizade.mbazar_base_app.R;

import java.util.List;
import java.util.Random;

/**
 * Created by aalizade on 10/25/2017.
 */

public class HomegridSpecialStringplansAdapter extends RecyclerView.Adapter<HomegridSpecialStringplansAdapter.NewsViewHolder> {

    private Context context;
    private List<String> plans;

    public HomegridSpecialStringplansAdapter(Context context, List<String> plans){
        this.context = context;
        this.plans = plans;
    }
    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.locating_plans_recycler_item_designed,parent,false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, final int position) {
        String singleItem=plans.get(position);
        holder.title.setText(singleItem);
        switch (position){
            case 0:holder.cardView.setBackgroundResource(R.color.after_buy_btn_color2);break;
            case 1:holder.cardView.setBackgroundResource(R.color.dark_gradient_color);break;
            case 2:holder.cardView.setBackgroundResource(R.color.dark_yellow);break;
            case 3:holder.cardView.setBackgroundResource(R.color.dark_blue);break;
            default:{
                Random r = new Random();
                int red=r.nextInt(255 - 0 + 1)+0;
                int green=r.nextInt(255 - 0 + 1)+0;
                int blue=r.nextInt(255 - 0 + 1)+0;
                GradientDrawable draw = new GradientDrawable();
                draw.setColor(Color.rgb(red,green,blue));
                holder.cardView.setBackground(draw);
                break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return plans.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private CardView cardView;

        public NewsViewHolder(View itemView) {
            super(itemView);
            title=(TextView)itemView.findViewById(R.id.plan_name_txt_id);
            cardView=(CardView) itemView.findViewById(R.id.cardview_bkgrnd_id);
        }
    }
}
