package com.example.aalizade.mbazar_base_app.adapters.recycler_adapters.credit;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.entities.UserCreditEntity;

import java.util.List;

public class UserCreditRecyclerAdapter extends RecyclerView.Adapter<UserCreditRecyclerAdapter.CreditsViewHolder> {


    private Activity context;
    private List<UserCreditEntity> credits;
    private AlertDialog detailDialog;
    View detailAlertLayout;

    public UserCreditRecyclerAdapter(Activity context, List<UserCreditEntity> credits){
        this.context = context;
        this.credits = credits;
    }
    @Override
    public UserCreditRecyclerAdapter.CreditsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.user_credit_recycler_item,parent,false);

        LayoutInflater inflater = LayoutInflater.from(context);
        detailAlertLayout = inflater.inflate(R.layout.credit_detail_dialog_layout, null);
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setView(detailAlertLayout);
        alert.setCancelable(false);
        detailDialog = alert.create();
        detailDialog.setCanceledOnTouchOutside(true);

        return new UserCreditRecyclerAdapter.CreditsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final UserCreditRecyclerAdapter.CreditsViewHolder holder, final int position) {
        final UserCreditEntity credit = credits.get(position);

        Log.v("credit--:",credit.toString());

        holder.type.setText(credit.getType());
        holder.date.setText(credit.getLastUsageDate());
        holder.price.setText(credit.getRemainAmount());
        holder.detailLayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                holder.id.setText(credit.getId());
                ((TextView) detailAlertLayout.findViewById(R.id.detail_id_txt_id)).setText(credit.getId());
//                holder.type1.setText(credit.getType());
                ((TextView) detailAlertLayout.findViewById(R.id.type_txt_id)).setText(credit.getType());
//                holder.productGroup.setText(credit.getProductTypeGroup());
                ((TextView) detailAlertLayout.findViewById(R.id.product_group_title_details)).setText(credit.getProductTypeGroup());
//                holder.lastDate.setText(credit.getLastUsageDate());
                ((TextView) detailAlertLayout.findViewById(R.id.last_date)).setText(credit.getLastUsageDate());
//                holder.remain.setText(credit.getRemainAmount());
                ((TextView) detailAlertLayout.findViewById(R.id.remain_amount)).setText(credit.getRemainAmount());
//                holder.username.setText(credit.getDonator_name());
                ((TextView) detailAlertLayout.findViewById(R.id.username_ehda)).setText(credit.getDonator_name());
                detailDialog.show();
            }
        });
        holder.creditTransactionsLayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                context.startActivity(new Intent(context, UserCreditTransactionDescriptionActivity.class));
                Toast.makeText(context.getApplicationContext(),"مشکل ارتباط با سرور",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return credits.size();
    }

    public class CreditsViewHolder extends RecyclerView.ViewHolder{
        private TextView type,date,price;
        private TextView id ;
        private TextView type1 ;
        private TextView productGroup ;
        private TextView lastDate ;
        private TextView username ;
        private TextView remain;
        LinearLayout detailLayer,creditTransactionsLayer;

        public CreditsViewHolder(View itemView) {
            super(itemView);
            id = (TextView) detailAlertLayout.findViewById(R.id.detail_id_txt_id);
            type1 = (TextView) detailAlertLayout.findViewById(R.id.type_txt_id);
            productGroup = (TextView) detailAlertLayout.findViewById(R.id.product_group_title_details);
            lastDate = (TextView) detailAlertLayout.findViewById(R.id.last_date);
            username = (TextView) detailAlertLayout.findViewById(R.id.username_ehda);
            remain = (TextView) detailAlertLayout.findViewById(R.id.remain_amount);
            type = (TextView)itemView.findViewById(R.id.credit_type_txt_id);
            date = (TextView)itemView.findViewById(R.id.date_txt_id);
            price = (TextView)itemView.findViewById(R.id.credit_money_transactiontxt_id);
            detailLayer = (LinearLayout) itemView.findViewById(R.id.visit_detail_in_new_page_layer_id);
            creditTransactionsLayer = (LinearLayout) itemView.findViewById(R.id.visit_turn_over_layer_id);
        }
    }

}