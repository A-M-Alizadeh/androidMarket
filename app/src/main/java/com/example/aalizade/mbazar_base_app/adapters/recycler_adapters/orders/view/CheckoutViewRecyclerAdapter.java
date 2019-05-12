package com.example.aalizade.mbazar_base_app.adapters.recycler_adapters.orders.view;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.activities.orders.CarrierOrderActivity;
import com.example.aalizade.mbazar_base_app.network.models.checkout.view.CheckoutViewModel;

import java.util.List;

public class CheckoutViewRecyclerAdapter extends RecyclerView.Adapter<CheckoutViewRecyclerAdapter.BonsViewHolder> {


    private Activity activity;
    private List<CheckoutViewModel> bons;

    public CheckoutViewRecyclerAdapter(Activity activity, List<CheckoutViewModel> bons){
        this.activity = activity;
        this.bons = bons;
    }
    @Override
    public CheckoutViewRecyclerAdapter.BonsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.checkout_view_item_layout,parent,false);

        return new CheckoutViewRecyclerAdapter.BonsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CheckoutViewRecyclerAdapter.BonsViewHolder holder, final int position) {
        final CheckoutViewModel bon = bons.get(position);
        holder.code.setText(bon.getCode());
        holder.status.setText(bon.getStatus());
        holder.dateOfClearing.setText(bon.getDateOfClearing());
        holder.dateOfRegister.setText(bon.getDateOfRegister());

        holder.carrierOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, CarrierOrderActivity.class);
                String orderId = bon.getId();
                intent.putExtra("orderID",orderId);
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bons.size();
    }

    public class BonsViewHolder extends RecyclerView.ViewHolder{
        private TextView code,status,dateOfRegister,dateOfClearing;
        LinearLayout carrierOrder;

        public BonsViewHolder(View itemView) {
            super(itemView);
            code = (TextView)itemView.findViewById(R.id.checkout_view_code);
            status = (TextView)itemView.findViewById(R.id.checkout_view_status);
            dateOfRegister = (TextView)itemView.findViewById(R.id.checkout_view_register_date);
            dateOfClearing = (TextView)itemView.findViewById(R.id.checkout_view_clearing_date);

            carrierOrder = (LinearLayout) itemView.findViewById(R.id.checkout_view_carrier_order);

        }
    }

}