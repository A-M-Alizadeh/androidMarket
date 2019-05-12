package com.example.aalizade.mbazar_base_app.adapters.recycler_adapters.gift;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.entities.UserBonHedieEntity;

import java.util.List;

public class UserBonHedieRecyclerAdapter extends RecyclerView.Adapter<UserBonHedieRecyclerAdapter.BonsViewHolder> {

    private Activity activity;
    private List<UserBonHedieEntity> bons;

    public UserBonHedieRecyclerAdapter(Activity activity, List<UserBonHedieEntity> bons){
        this.activity = activity;
        this.bons = bons;
    }
    @Override
    public UserBonHedieRecyclerAdapter.BonsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.user_bon_hedie_recycler_item_layout,parent,false);

        return new UserBonHedieRecyclerAdapter.BonsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserBonHedieRecyclerAdapter.BonsViewHolder holder, final int position) {
        UserBonHedieEntity bon=bons.get(position);
        holder.type.setText(bon.getType());
        holder.bonCode.setText(bon.getBonCode());
        holder.user_username.setText(bon.getUser_username());
        holder.user_name.setText(bon.getUser_name());
        holder.givenPrice.setText(bon.getGivenPrice());
        holder.totalPrice.setText(bon.getTotalPrice());

//        holder.elsaghBtnLayer.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(activity, AttachBonActivity.class);
//                activity.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return bons.size();
    }

    public class BonsViewHolder extends RecyclerView.ViewHolder{
        private TextView type,bonCode,user_username,user_name,givenPrice,totalPrice;
        LinearLayout BonTransactionsBtnLayer;

        public BonsViewHolder(View itemView) {
            super(itemView);
            type=(TextView)itemView.findViewById(R.id.bon_type_txt_id);
            bonCode=(TextView)itemView.findViewById(R.id.bon_code_txt_id);
            user_username=(TextView)itemView.findViewById(R.id.user_username_txt_id);
            user_name=(TextView)itemView.findViewById(R.id.user_name_txt_id);
            givenPrice=(TextView)itemView.findViewById(R.id.given_price_txt_id);
            totalPrice=(TextView)itemView.findViewById(R.id.total_price_txt_id);


//            elsaghBtnLayer=(LinearLayout) itemView.findViewById(R.id.visit_elsagh_bon_btn_id);
            BonTransactionsBtnLayer=(LinearLayout) itemView.findViewById(R.id.visit_transaction_bon_btn_id);
        }
    }

}