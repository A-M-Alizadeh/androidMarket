package com.example.aalizade.mbazar_base_app.adapters.recycler_adapters.orders;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.activities.orders.DescriptionCarrierOrderActivity;
import com.example.aalizade.mbazar_base_app.network.models.checkout.OrderCarrierModel;

import java.util.List;

public class OrderCarrierRecyclerAdapter extends RecyclerView.Adapter<OrderCarrierRecyclerAdapter.BonsViewHolder> {


    private Activity activity;
    private List<OrderCarrierModel> carrierModelList;

    public OrderCarrierRecyclerAdapter(Activity activity, List<OrderCarrierModel> carrierModelList){
        this.activity = activity;
        this.carrierModelList = carrierModelList;
    }
    @Override
    public OrderCarrierRecyclerAdapter.BonsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.order_carrier_item_layout,parent,false);

        return new OrderCarrierRecyclerAdapter.BonsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OrderCarrierRecyclerAdapter.BonsViewHolder holder, final int position) {
        final OrderCarrierModel bon = carrierModelList.get(position);
        holder.code.setText(bon.getCode());
        holder.sendStatus.setText(bon.getSendStatus());
        holder.dateOfReceive.setText(bon.getDateOfRecive());
        holder.dateOfSend.setText(bon.getDateOfSend());
        holder.duration.setText(bon.getDuration());
        holder.carrierAmount.setText(bon.getCarrierAmount());
        holder.totalWeight.setText(bon.getTotalWeight());
        holder.productCunt.setText(bon.getProductCunt());
        holder.carrierType.setText(bon.getCarrierType());

        holder.carrierDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, DescriptionCarrierOrderActivity.class);
                String orderId = bon.getId();
                intent.putExtra("orderProductId",orderId);
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return carrierModelList.size();
    }

    public class BonsViewHolder extends RecyclerView.ViewHolder{
        private TextView code,sendStatus,dateOfReceive,dateOfSend,duration,carrierAmount,totalWeight,productCunt,carrierType;
        LinearLayout carrierDescription;

        public BonsViewHolder(View itemView) {
            super(itemView);
            code = (TextView)itemView.findViewById(R.id.order_carrier_code);
            sendStatus = (TextView)itemView.findViewById(R.id.order_carrier_status);
            dateOfReceive = (TextView)itemView.findViewById(R.id.order_carrier_receive_date);
            dateOfSend = (TextView)itemView.findViewById(R.id.order_carrier_send_date);
            duration = (TextView)itemView.findViewById(R.id.order_carrier_duration);
            carrierAmount = (TextView)itemView.findViewById(R.id.order_carrier_amount);
            totalWeight = (TextView)itemView.findViewById(R.id.order_carrier_weight);
            productCunt = (TextView)itemView.findViewById(R.id.order_carrier_quantity);
            carrierType = (TextView)itemView.findViewById(R.id.order_carrier_method);

            carrierDescription = (LinearLayout) itemView.findViewById(R.id.order_carrier_description);

        }
    }

}