package com.example.aalizade.mbazar_base_app.adapters.recycler_adapters.checkout_steps_adapters;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.utility.PaymentOptionsTranslator;
import com.example.aalizade.mbazar_base_app.network.models.cart.CustomPaymentOptionsModel;

import java.util.List;

public class CheckoutPaymentOptionsTypeOUTERAdapter extends RecyclerView.Adapter<CheckoutPaymentOptionsTypeOUTERAdapter.NewsViewHolder> {

    private Context context;
    List<CustomPaymentOptionsModel> hireArchichalCustomPaymentOptionsModels;
    PaymentOptionsTranslator paymentOptionsTranslator;

    public CheckoutPaymentOptionsTypeOUTERAdapter(Context context, List<CustomPaymentOptionsModel> hireArchichalCustomPaymentOptionsModels) {
        this.context = context;
        this.hireArchichalCustomPaymentOptionsModels = hireArchichalCustomPaymentOptionsModels;
        paymentOptionsTranslator = new PaymentOptionsTranslator();
//        activity = (Activity) context;
    }

    @Override
    public CheckoutPaymentOptionsTypeOUTERAdapter.NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.payment_options_outer_adapter_item_layout, parent, false);
        return new CheckoutPaymentOptionsTypeOUTERAdapter.NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CheckoutPaymentOptionsTypeOUTERAdapter.NewsViewHolder holder, final int position) {
        CustomPaymentOptionsModel innerListWrapper = hireArchichalCustomPaymentOptionsModels.get(position);
        holder.title.setText(paymentOptionsTranslator.Translate(innerListWrapper.getTitle()));

        CheckoutPaymentOptionsTypeINNERAdapter newsAdapter = new CheckoutPaymentOptionsTypeINNERAdapter(context, innerListWrapper.getPaymentSystemCartModels());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        holder.outerRecler.setLayoutManager(linearLayoutManager);
        holder.outerRecler.setAdapter(newsAdapter);
        holder.outerRecler.setHasFixedSize(true);
        holder.outerRecler.setItemViewCacheSize(20);
        holder.outerRecler.setDrawingCacheEnabled(true);
        holder.outerRecler.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        holder.outerRecler.setNestedScrollingEnabled(false);


    }

    @Override
    public int getItemCount() {
        return hireArchichalCustomPaymentOptionsModels.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private RecyclerView outerRecler;

        public NewsViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.wrapper_header_title_txt_id);
            outerRecler = (RecyclerView) itemView.findViewById(R.id.outer_recycler_id);
        }
    }

    public void update(List<CustomPaymentOptionsModel> data) {
//        degrees.clear();
        hireArchichalCustomPaymentOptionsModels = data;
        notifyDataSetChanged();
    }

    public void removeAt(int position) {
        hireArchichalCustomPaymentOptionsModels.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, hireArchichalCustomPaymentOptionsModels.size());
    }

}