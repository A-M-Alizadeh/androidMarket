package com.example.aalizade.mbazar_base_app.adapters.recycler_adapters.credit;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.entities.UserCreditTransaction;

import java.util.List;

public class UserCreditTransactionsDescriptionRecyclerAdapter extends RecyclerView.Adapter<UserCreditTransactionsDescriptionRecyclerAdapter.CreditsViewHolder> {

    private Context context;
    private List<UserCreditTransaction> credits;

    public UserCreditTransactionsDescriptionRecyclerAdapter(Context context, List<UserCreditTransaction> credits){
        this.context = context;
        this.credits = credits;
    }
    @Override
    public UserCreditTransactionsDescriptionRecyclerAdapter.CreditsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.user_credit_transactions_recycler_item,parent,false);
        return new UserCreditTransactionsDescriptionRecyclerAdapter.CreditsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserCreditTransactionsDescriptionRecyclerAdapter.CreditsViewHolder holder, final int position) {
        UserCreditTransaction credit=credits.get(position);
        holder.type.setText(credit.getType());
        holder.creditDate.setText(credit.getCreditDate());
        holder.remainingMoney.setText(credit.getRemainingMoney());
        holder.returnDate.setText(credit.getReturnDate());
        if (credit.getPositive()){
            holder.creditTransactionMoney.setText(credit.getCreditTransactionMoney()+"+");
            holder.creditTransactionMoney.setTextColor(context.getResources().getColor(R.color.current_price));
            holder.backLayout.setBackgroundResource(R.color.light_green);
        }else {
            holder.creditTransactionMoney.setText(credit.getCreditTransactionMoney()+"-");
            holder.creditTransactionMoney.setTextColor(context.getResources().getColor(R.color.depricated_price));
            holder.backLayout.setBackgroundResource(R.color.light_red);
        }
    }

    @Override
    public int getItemCount() {
        return credits.size();
    }

    public class CreditsViewHolder extends RecyclerView.ViewHolder{

        private TextView type,creditDate,creditTransactionMoney,remainingMoney,returnDate;
        private RelativeLayout backLayout;

        public CreditsViewHolder(View itemView) {
            super(itemView);
            type=(TextView)itemView.findViewById(R.id.action_type_txt_id);
            creditDate=(TextView)itemView.findViewById(R.id.action_date_txt_id);
            creditTransactionMoney=(TextView)itemView.findViewById(R.id.action_transaction_money_txt_id);
            remainingMoney=(TextView)itemView.findViewById(R.id.remaining_money_txt_id);
            returnDate=(TextView)itemView.findViewById(R.id.return_date_txt_id);
            backLayout=(RelativeLayout)itemView.findViewById(R.id.positive_negative_back_layout_id);
        }
    }

}