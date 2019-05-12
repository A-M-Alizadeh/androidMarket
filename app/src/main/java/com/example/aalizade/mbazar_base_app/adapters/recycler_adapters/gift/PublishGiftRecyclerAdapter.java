package com.example.aalizade.mbazar_base_app.adapters.recycler_adapters.gift;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.network.models.credit.PublishGiftModel;

import java.util.List;

public class PublishGiftRecyclerAdapter extends RecyclerView.Adapter<PublishGiftRecyclerAdapter.BonsViewHolder> {

    private Activity activity;
    private List<PublishGiftModel> bons;

    public PublishGiftRecyclerAdapter(Activity activity, List<PublishGiftModel> bons){
        this.activity = activity;
        this.bons = bons;
    }
    @Override
    public PublishGiftRecyclerAdapter.BonsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.publish_gift_item_layout,parent,false);
        Log.v("bonnnnn",bons.toString());
        return new PublishGiftRecyclerAdapter.BonsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PublishGiftRecyclerAdapter.BonsViewHolder holder, final int position) {
        PublishGiftModel bon = bons.get(position);
        holder.type.setText(bon.getType());
        holder.bonCode.setText(bon.getGiftCode());
        holder.publishAmount.setText(bon.getPublishedAmount());
        holder.remainAmount.setText(bon.getRemainAmount());
        holder.confirmDate.setText(bon.getUserGiftRequest_confirmRejectDate());
        holder.expireDate.setText(bon.getUserGiftRequest_expireDate());

    }

    @Override
    public int getItemCount() {
        return bons.size();
    }

    public class BonsViewHolder extends RecyclerView.ViewHolder{
        private TextView type,bonCode,publishAmount,remainAmount,confirmDate,expireDate;


        public BonsViewHolder(View itemView) {
            super(itemView);
            type=(TextView)itemView.findViewById(R.id.publish_gift_type_txt_id);
            bonCode=(TextView)itemView.findViewById(R.id.publish_gift_code_txt_id);
            publishAmount=(TextView)itemView.findViewById(R.id.publish_gift_publish_amount);
            remainAmount=(TextView)itemView.findViewById(R.id.publish_request_remain_amount);
            confirmDate=(TextView)itemView.findViewById(R.id.publish_gift_confirm_date);
            expireDate=(TextView)itemView.findViewById(R.id.publish_gift_expire_date);

        }
    }

}