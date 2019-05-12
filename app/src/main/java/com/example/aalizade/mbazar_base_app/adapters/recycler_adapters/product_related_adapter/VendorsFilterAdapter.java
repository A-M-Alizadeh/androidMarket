package com.example.aalizade.mbazar_base_app.adapters.recycler_adapters.product_related_adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.bottom_sheets.ProductOtherProducersBottomSheet;
import com.example.aalizade.mbazar_base_app.network.models.attributes.AttributeValueFrontModel;
import com.example.aalizade.mbazar_base_app.network.models.product.GeneralAttributeModel;
import com.example.aalizade.mbazar_base_app.network.models.product.VendorsCustomFilterListModel;

import java.util.ArrayList;
import java.util.List;

public class VendorsFilterAdapter extends RecyclerView.Adapter<VendorsFilterAdapter.NewsViewHolder> {

    Context context;
    private List<VendorsCustomFilterListModel> attributes;
    private List<String> attributevaluesStringList;
    private int lastPosition = -1;
    public static Integer spinnerSelectedItem = -1;

    public VendorsFilterAdapter(Context context, List<VendorsCustomFilterListModel> attributes) {
        this.context = context;
        this.attributes = attributes;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.other_vendors_filter_recycler_item_layout, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final NewsViewHolder holder, final int position) {
        final VendorsCustomFilterListModel singleItem = attributes.get(position);
        final int spinnerNumber = position;

        holder.title.setText(singleItem.getTitle());
        setValuesSpinnerAdapter(holder.attributeValuesSpinner, singleItem.getAttributeModels());
        holder.attributeValuesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){
                    ProductOtherProducersBottomSheet.spinnersSelectedItem.put(spinnerNumber,-1);
                }else {
                    ProductOtherProducersBottomSheet.spinnersSelectedItem.put(spinnerNumber,position-1);
                }
                System.out.println("KEYs-> "+ProductOtherProducersBottomSheet.spinnersSelectedItem.keySet());
                System.out.println("VALUESs-> "+ProductOtherProducersBottomSheet.spinnersSelectedItem.values());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return attributes.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private Spinner attributeValuesSpinner;

        public NewsViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.attribute_title_txt_id);
            attributeValuesSpinner = (Spinner) itemView.findViewById(R.id.attribute_values_spinner_id);
        }
    }

    //setting animation
    private void setAnimation(View viewToAnimate, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    public void setValuesSpinnerAdapter(Spinner spinner, List<GeneralAttributeModel> values) {
        attributevaluesStringList = new ArrayList<>();
        for (int i = 0; i < values.size(); i++) {
            attributevaluesStringList.add(values.get(i).getTitle());
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context,
                android.R.layout.simple_spinner_item, attributevaluesStringList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        //address adapter
    }

    //setting animation


}