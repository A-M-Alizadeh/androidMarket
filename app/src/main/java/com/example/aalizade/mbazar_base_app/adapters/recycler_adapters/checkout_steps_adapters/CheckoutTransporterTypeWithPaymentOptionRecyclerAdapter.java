package com.example.aalizade.mbazar_base_app.adapters.recycler_adapters.checkout_steps_adapters;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.network.models.product.CustomCategorizeTransportingItems;

import java.util.List;


/**
 * Created by aalizade on 11/13/2017.
 */

public class CheckoutTransporterTypeWithPaymentOptionRecyclerAdapter extends RecyclerView.Adapter<CheckoutTransporterTypeWithPaymentOptionRecyclerAdapter.NewsViewHolder> {
    private Context context;
    List<CustomCategorizeTransportingItems> categorizedList;
    LinearLayout linearLayout;


    public CheckoutTransporterTypeWithPaymentOptionRecyclerAdapter(Context context,List<CustomCategorizeTransportingItems> categorizedList,LinearLayout linearLayout) {
        this.context = context;
        this.categorizedList = categorizedList;
        this.linearLayout = linearLayout;
    }

    @Override
    public CheckoutTransporterTypeWithPaymentOptionRecyclerAdapter.NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.a_temp_checkout_transport_item, parent, false);
        return new CheckoutTransporterTypeWithPaymentOptionRecyclerAdapter.NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CheckoutTransporterTypeWithPaymentOptionRecyclerAdapter.NewsViewHolder holder, final int position) {
        CustomCategorizeTransportingItems trans_type = categorizedList.get(position);
        holder.transporter_name.setText(trans_type.getCarrierName());
        SetRecyclerAdapter(holder.trans_type_recycler,position);

    }

    @Override
    public int getItemCount() {
        return categorizedList.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        private TextView transporter_name;
//        private TextView transporter_info;
        private RecyclerView trans_type_recycler;
        private RelativeLayout touch_layout;

        public NewsViewHolder(View itemView) {
            super(itemView);
            touch_layout = (RelativeLayout) itemView.findViewById(R.id.checkout_transport_item_layout_id);
            transporter_name = (TextView) itemView.findViewById(R.id.transporter_name_txt_id);
//            transporter_info = (TextView) itemView.findViewById(R.id.transporter_description_txt_id);
            trans_type_recycler = (RecyclerView) itemView.findViewById(R.id.checkout_transporter_type_prds_recycler_id);
        }
    }

    private void SetRecyclerAdapter(RecyclerView recyclerView,int position) {
        CheckoutTransportTypesItemsWithPaymentOptionAdapter newsAdapter = new CheckoutTransportTypesItemsWithPaymentOptionAdapter(context ,categorizedList.get(position).getCarriedItems(),linearLayout);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(newsAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        recyclerView.setNestedScrollingEnabled(false);
    }

    public void update(List<CustomCategorizeTransportingItems> data) {
//        degrees.clear();
        categorizedList = data;
        notifyDataSetChanged();
    }

    public void removeAt(int position) {
        categorizedList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, categorizedList.size());
    }
}
