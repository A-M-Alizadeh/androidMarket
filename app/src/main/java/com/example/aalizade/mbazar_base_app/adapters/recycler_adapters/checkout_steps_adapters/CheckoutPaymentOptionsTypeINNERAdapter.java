package com.example.aalizade.mbazar_base_app.adapters.recycler_adapters.checkout_steps_adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.activities.gift.AttachBonActivity;
import com.example.aalizade.mbazar_base_app.fragments.checkout_frags.CheckOutPreviewAndPayFragment;
import com.example.aalizade.mbazar_base_app.network.models.cart.PaymentSystemCartModel;

import java.util.List;


/**
 * Created by aalizade on 11/13/2017.
 */

public class CheckoutPaymentOptionsTypeINNERAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    List<PaymentSystemCartModel> options;
    Activity activity;

    //, LinearLayout linearLayout
    public CheckoutPaymentOptionsTypeINNERAdapter(Context context, List<PaymentSystemCartModel> options) {
        this.context = context;
        this.options = options;
        activity = (Activity) context;
    }

    @Override
    public int getItemViewType(int position) {
//        return super.getItemViewType(position);
        if (options.get(position).getPaymentSystemCart_value().equals("USERGIFT")) {
            return 0;
        } else return 1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View view = LayoutInflater.from(context).inflate(R.layout.payment_type_with_bon_recycler_item, parent, false);
            return new OptionWithBonViewHoler(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.payment_type_recycler_item, parent, false);
            return new OptionViewHoler(view);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == 0) {
            PaymentSystemCartModel item = options.get(position);
            OptionWithBonViewHoler optionWithBonViewHoler = (OptionWithBonViewHoler) holder;
            optionWithBonViewHoler.option_Title.setText(item.getTitle());
            optionWithBonViewHoler.remaining_money_price_txt.setText(String.valueOf(CheckOutPreviewAndPayFragment.total_price_to_pay));
            optionWithBonViewHoler.sum_of_related_products_price_txt.setText(item.getRemainAmount());
            optionWithBonViewHoler.elsaghBonBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context, AttachBonActivity.class));
                }
            });
        } else {
            PaymentSystemCartModel item = options.get(position);
            OptionViewHoler optionViewHoler = (OptionViewHoler) holder;
            optionViewHoler.option_Title.setText(item.getTitle());
            optionViewHoler.remaining_money_price_txt.setText(String.valueOf(CheckOutPreviewAndPayFragment.total_price_to_pay));
            optionViewHoler.sum_of_related_products_price_txt.setText(item.getRemainAmount());
        }
    }

    @Override
    public int getItemCount() {
        return options.size();
    }

    public class OptionViewHoler extends RecyclerView.ViewHolder {

        private TextView option_Title;
        private TextView sum_of_related_products_price_txt;
        private TextView remaining_money_price_txt;
        private SwitchCompat transportCostSwitch;

        public OptionViewHoler(View itemView) {
            super(itemView);
            option_Title = (TextView) itemView.findViewById(R.id.option_title_txt_id);
            sum_of_related_products_price_txt = (TextView) itemView.findViewById(R.id.sum_of_related_products_price_txt_id);
            remaining_money_price_txt = (TextView) itemView.findViewById(R.id.remaining_money_price_txt_id);
            transportCostSwitch = (SwitchCompat) itemView.findViewById(R.id.transport_cost_swichCompat_id);
        }
    }

    public class OptionWithBonViewHoler extends RecyclerView.ViewHolder {

        private TextView option_Title;
        private TextView sum_of_related_products_price_txt;
        private TextView remaining_money_price_txt;
        private SwitchCompat transportCostSwitch;
        private Button elsaghBonBtn;

        public OptionWithBonViewHoler(View itemView) {
            super(itemView);
            option_Title = (TextView) itemView.findViewById(R.id.option_title_txt_id);
            sum_of_related_products_price_txt = (TextView) itemView.findViewById(R.id.sum_of_related_products_price_txt_id);
            remaining_money_price_txt = (TextView) itemView.findViewById(R.id.remaining_money_price_txt_id);
            transportCostSwitch = (SwitchCompat) itemView.findViewById(R.id.transport_cost_swichCompat_id);
            elsaghBonBtn = (Button) itemView.findViewById(R.id.elsagh_bon_btn_id);
        }
    }

}
