package com.example.aalizade.mbazar_base_app.adapters.recycler_adapters.gift;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.activities.gift.PublishedGiftActivity;
import com.example.aalizade.mbazar_base_app.network.models.credit.GiftRequestModel;

import java.util.ArrayList;
import java.util.List;

public class UserGiftRequestRecyclerAdapter extends RecyclerView.Adapter<UserGiftRequestRecyclerAdapter.BonsViewHolder> {

    private Activity activity;
    private List<GiftRequestModel> bons;

    public UserGiftRequestRecyclerAdapter(Activity activity, List<GiftRequestModel> bons){
        this.activity = activity;
        this.bons = bons;
    }
    @Override
    public UserGiftRequestRecyclerAdapter.BonsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.requested_bon_item_layout,parent,false);
        Log.v("bonnnnn",bons.toString());
        return new UserGiftRequestRecyclerAdapter.BonsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserGiftRequestRecyclerAdapter.BonsViewHolder holder, final int position) {
        GiftRequestModel bon = bons.get(position);
        holder.type.setText(bon.getType());
        holder.bonCode.setText(bon.getCode());
        holder.quantity.setText(bon.getQuantity());
        holder.amount.setText(bon.getAmount());
        holder.paymentMethod.setText(bon.getPaymentMethod());
        holder.status.setText(bon.getStatus_langkey());
        holder.confirmDate.setText(bon.getUserGiftRequest_confirmRejectDate());
        holder.expireDate.setText(bon.getUserGiftRequest_expireDate());

        holder.published.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, PublishedGiftActivity.class);
                ArrayList<String> items = new ArrayList<>();
                items.add(bons.get(position).getId());
                Log.v("bonID",items.toString());
                intent.putStringArrayListExtra("giftId",items);
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bons.size();
    }

    public class BonsViewHolder extends RecyclerView.ViewHolder{
        private TextView type,bonCode,quantity,amount,paymentMethod,status,confirmDate,expireDate;
        LinearLayout details,published;

        public BonsViewHolder(View itemView) {
            super(itemView);
            type=(TextView)itemView.findViewById(R.id.request_gift_type_txt_id);
            bonCode=(TextView)itemView.findViewById(R.id.request_gift_code_txt_id);
            quantity=(TextView)itemView.findViewById(R.id.request_gift_quantity);
            amount=(TextView)itemView.findViewById(R.id.gift_request_amuont_type);
            paymentMethod=(TextView)itemView.findViewById(R.id.gift_request_payment_method);
            status=(TextView)itemView.findViewById(R.id.gift_request_status);
            confirmDate=(TextView)itemView.findViewById(R.id.gift_request_confirm_date);
            expireDate=(TextView)itemView.findViewById(R.id.request_gift_expire_date);

            details=(LinearLayout) itemView.findViewById(R.id.request_gift_details);
            published=(LinearLayout) itemView.findViewById(R.id.request_gift_published);
        }
    }

}